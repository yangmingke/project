package com.flypaas.admin.action;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.convention.annotation.Action;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.constant.DbConstant.DbType;
import com.flypaas.admin.constant.DbConstant.TableSchema;
import com.flypaas.admin.service.CommonService;
import com.flypaas.admin.service.data.AppService;
import com.flypaas.admin.util.ConfigUtils;
import com.flypaas.admin.util.file.BizException;
import com.flypaas.admin.util.file.FastDfsClient;
import com.flypaas.admin.util.file.ZipUtils;
import com.flypaas.admin.util.file.ZipUtils.ZipEntryData;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 下载账单文件<br/>
 * 
 * <pre>
 * 参数： <br/>
 * type 		账单类型：day、week、month<br/>
 * appSid		应用sid<br/>
 * clientNumber	下载client账单才需要<br/>
 * date		账单日期：
 * 			type=day时，20140601表示下载2014年6月1日的日账单；
 * 			type=week时，20140602表示下载周一日期为20140602的周账单；
 * 			type=month时，201406表示下载2014年6月份的月账单
 * </pre>
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
public class DownloadAction extends BaseAction {
	private static final long serialVersionUID = -2509039967315835137L;
	private static final Logger logger = LoggerFactory.getLogger(DownloadAction.class);
	@Autowired
	private CommonService commonService;
	@Autowired
	private AppService appService;
	private String type;
	private String app_sid;
	private String sid;
	private String client_number;
	private String date;

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setApp_sid(String app_sid) {
		this.app_sid = app_sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public void setClient_number(String client_number) {
		this.client_number = client_number;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Action("/download/developerBill")
	public String developerBillDownload() throws ServletException, IOException {
		String msg = check(type, date);// 参数校验
		if (msg != null) {
			writeMsg(msg);
			return NONE;
		}
		if (StringUtils.isEmpty(sid)) {
			writeMsg("参数不能为空，下载失败");
			return NONE;
		}
		ByteArrayOutputStream outTmp = new ByteArrayOutputStream();
		String fileName = "账单" + date + ".zip";
		Map<String, String> params = new HashMap<String, String>();
		params.put("sid", sid);
		params.put("status", "1");
		List<Map<String, Object>> lst = appService.queryAppSidBySid(params);
		Object tmpObj = null;
		List<ZipEntryData> zipList = new ArrayList<ZipEntryData>();
		ZipEntryData ze = null;
		for (Map<String, Object> map : lst) {
			tmpObj = map.get("app_sid");
			if (null != tmpObj) {
				app_sid = String.valueOf(tmpObj);
				ze = new ZipEntryData("应用[" + app_sid + "]账单" + date + ".xls");
				try {
					ze.setDatas(getRemotePathBytes(type, date, app_sid, client_number));
					zipList.add(ze);
				} catch (BizException e) {
				}
			}
		}
		if (zipList.isEmpty()) {
			writeMsg("账单不存在,或未产生!");
			return NONE;
		}
		ZipUtils.zip(outTmp, zipList);
		return toDownload(fileName, outTmp.toByteArray());
	}

	@Action("/download/clientBill")
	public String clientbillDownload() throws ServletException, IOException {
		String msg = check(type, date);// 参数校验
		if (msg != null) {
			writeMsg(msg);
			return NONE;
		}
		if (StringUtils.isEmpty(app_sid)) {
			writeMsg("参数不能为空，下载失败");
			return NONE;
		}
		String fileName = "账单" + date + ".xls";
		try {
			return toDownload(fileName, getRemotePathBytes(type, date, app_sid, client_number));
		} catch (BizException e) {
			writeMsg(e.getMessage());
			return NONE;
		}
	}

	String toDownload(String fileName, byte[] bytes) {
		HttpServletResponse resp = StrutsUtils.getResponse();
		OutputStream out = null;
		try {
			resp.reset();
			resp.setCharacterEncoding("GBK");
			resp.setHeader("Content-Disposition", "attachment;filename="
					+ new String(fileName.getBytes("GBK"), "ISO-8859-1"));
			resp.setContentType("application/download");
			out = new BufferedOutputStream(resp.getOutputStream());
			out.write(bytes);
			out.flush();
		} catch (Throwable e) {
			logger.error("文件下载失败", e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				logger.error("关闭输出流失败", e);
			}
		}
		logger.debug("本次文件下载成功");
		return NONE;
	}

	byte[] getRemotePathBytes(String type, String date, String app_sid, String client_number) {
		String table = "tb_srv_file_";
		if (type.equalsIgnoreCase("day")) {
			DateTime dt = new DateTime(parseDate(date, "yyyyMMdd"));
			table = table + dt.plusDays(1).toString("yyyyMMdd");

		} else if (type.equalsIgnoreCase("week")) {
			DateTime dt = new DateTime(parseDate(date, "yyyyMMdd"));
			table = table + dt.plusWeeks(1).toString("yyyyMMdd");
		} else {
			DateTime dt = new DateTime(parseDate(date, "yyyyMM"));
			table = table + dt.plusMonths(1).toString("yyyyMMdd");
		}
		if (!commonService.hasTable(DbType.stat, TableSchema.statistics, table)) {
			logger.debug("文件日志表不存在，下载失败：table=" + table + ", date=" + date);
			throw new BizException(1, "文件不存在，下载失败");
		}

		/*
		 * localPath 本地文件保存目录 应用账单：save_path/taskType_EnName/appSid/privDate.xls
		 * client账单：save_path/taskType_EnName/appSid/clientNumber/privDate.xls
		 */
		StringBuilder localPath = new StringBuilder();
		localPath.append(ConfigUtils.save_path);
		localPath.append("/");
		localPath.append(type);
		localPath.append("/");
		localPath.append(app_sid);
		if (StringUtils.isNotBlank(client_number)) {
			localPath.append("/");
			localPath.append(client_number);
		}
		localPath.append("/");
		localPath.append(date);
		localPath.append(".xls");
		String remotePath = commonService.queryRemotePath(table, localPath.toString());
		if (StringUtils.isBlank(remotePath)) {
			logger.debug("文件日志不存在，下载失败：table=" + table + ", localPath=" + localPath + ", remotePath=" + remotePath);
			throw new BizException(2, "文件不存在，下载失败");
		}
		byte[] byteArray = null;
		try {
			byteArray = FastDfsClient.download(remotePath);
		} catch (Throwable e) {
			logger.error("FastDFS服务器上的文件下载失败：table=" + table + ", localPath=" + localPath + ", remotePath="
					+ remotePath, e);
			throw new BizException(3, "文件不存在，下载失败");
		}
		if (byteArray == null || byteArray.length < 1) {
			logger.error("FastDFS服务器上的文件为空：table=" + table + ", localPath=" + localPath + ", remotePath=" + remotePath);
			throw new BizException(4, "文件不存在，下载失败");
		}
		return byteArray;
	}

	/**
	 * 参数校验
	 * 
	 * @param type
	 * @param appSid
	 * @param clientNumber
	 * @param date
	 * @return
	 */
	private String check(String type, String date) {
		if (StringUtils.isBlank(type) || StringUtils.isBlank(date)) {
			return "参数不能为空，下载失败";
		}
		if (type.equalsIgnoreCase("day") || type.equalsIgnoreCase("week")) {
			Date d = parseDate(date, "yyyyMMdd");
			if (d == null) {
				return "日期格式必须为yyyyMMdd";
			}
			int today = Integer.parseInt(DateTime.now().toString("yyyyMMdd"));
			if (Integer.parseInt(date) >= today) {
				return "日期必须小于今天";
			}
			if (type.equalsIgnoreCase("week")) {
				DateTime dt = new DateTime(d);
				if (dt.dayOfWeek().get() != 1) {
					return "日期不是周一，下载失败";
				}
				int monday = Integer.parseInt(DateTime.now().dayOfWeek().withMinimumValue().toString("yyyyMMdd"));// 当前时间的周一
				if (Integer.parseInt(date) >= monday) {
					return "日期必须小于本周";
				}
			}

		} else if (type.equalsIgnoreCase("month")) {
			Date d = parseDate(date, "yyyyMM");
			if (d == null) {
				return "日期格式必须为yyyyMM";
			}
			int month = Integer.parseInt(DateTime.now().toString("yyyyMM"));
			if (Integer.parseInt(date) >= month) {
				return "日期必须小于本月";
			}
		} else {
			return "type只能为：day、week、month";
		}
		return null;
	}

	/**
	 * 字符串转换为日期
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	private Date parseDate(String date, String format) {
		Date d = null;
		try {
			d = DateUtils.parseDateStrictly(date, format);
		} catch (ParseException e) {
		}
		return d;
	}

	/**
	 * 输出信息
	 * 
	 * @param resp
	 * @param msg
	 * @throws IOException
	 */
	private void writeMsg(String msg) throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>文件下载失败</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<script type=\"text/javascript\">");
		sb.append("alert(\"" + msg + "\");");
		sb.append("window.close();");
		sb.append("</script>");
		sb.append("</body>");
		sb.append("</html>");
		HttpServletResponse resp = StrutsUtils.getResponse();
		resp.setHeader("Cache-Control", "no-cache");
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(sb.toString());
	}

}
