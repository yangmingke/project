package com.flypaas.action;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.constant.AppConstant;
import com.flypaas.constant.SqlCode;
import com.flypaas.constant.SysConstant;
import com.flypaas.entity.Application;
import com.flypaas.hdfs.FastDfsClient;
import com.flypaas.utils.DateUtil;
import com.flypaas.utils.Des3Utils;
import com.flypaas.utils.FileUtil;
import com.flypaas.utils.StrUtil;
import com.flypaas.utils.SysConfig;
import com.flypaas.utils.ZipUtils;
import com.flypaas.utils.ZipUtils.ZipEntryData;

@Controller
@Scope("prototype")
@Results({
	@Result(name = "descSuc",location = "/front/desc.jsp")
})
public class DownloadAction extends BaseAction {

	private String fileName;
	private String type;
	private String version;
	private String path;
	private String key;
	private List<Map<String, Object>> descList;
	private static final Logger logger = LoggerFactory.getLogger(DownloadAction.class);
	
	@Action("/download/acctDayZip")
	public void acctDayZip(){
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			logger.error("-------------------------设置requestuest字符集失败-------------------------");
			e1.printStackTrace();
		}
		String type = SysConstant.DOWN_BILL_TYPE;
		String year = request.getParameter("year");
		int day ;
		int mon ;
		try {
			day = Integer.parseInt(request.getParameter("day"));
			mon = Integer.parseInt(request.getParameter("month").toString());
		} catch (Exception e) {
			StrUtil.writeMsg(response, "参数错误，请检查后再试",null);
			return;
		}
		String tableDate =year+(mon>=10?mon:"0"+mon)+(day>=9?day+1:"0"+(day+1));
		String fileDate =year+(mon>=10?mon:"0"+mon)+(day>=9?day:"0"+day);
		
		String msg = check(type, fileDate);
		if(msg!=null){
			StrUtil.writeMsg(response, msg,null);
			return;
		}
		String table = SqlCode.FILE_TABLE_PREFIX;
		table = table + tableDate;
		
		int exists = consumeService.isExistsTable(table) ;
		if (exists==0) {
			logger.info("文件日志表不存在，下载失败：table=" + table + ", date=" + tableDate);
			StrUtil.writeMsg(response, "文件不存在，下载失败",null);
			return;
		}
		table = SqlCode.FILE_DATA_PREFIX+"."+table;
		String sid = getSessionUser().getSid();
		List<Application> appList = appService.getAppsNotContainsTestAppBySid(sid);
		if(appList==null || appList.size()==0){
			StrUtil.writeMsg(response, "主账号没有产生话单文件",null);
			return;
		}
		ZipEntryData ze = null;
		List<ZipEntryData> tmp = new ArrayList<ZipEntryData>();
		for(Application app : appList){
			byte[] bt = null;
			try {
				bt = getFileByte(type, app.getAppSid(), null ,fileDate,table, request, response);
			} catch (IOException e) {
				logger.error("应用sid："+app.getAppSid()+"，下载文件失败。");
				e.printStackTrace();
			}
			if(bt!=null){
				ze = new ZipEntryData(app.getAppName()+AppConstant.CALL_LOG_SUFFIX);
				ze.setDatas(bt);
				tmp.add(ze);
			}
		}
		if(tmp.size()==0){
			StrUtil.writeMsg(response, "主账号应用没有生成账单文件",null);
			return;
		}
		try {
			ByteArrayOutputStream outTmp = new ByteArrayOutputStream();
			ZipUtils.zip(outTmp, tmp);
			String fileName = "账单" + fileDate + ".zip";
			toDownload(response,fileName, outTmp.toByteArray());
			return;
		} catch (IOException e) {
			logger.error("----------------下载失败-------------------");
			e.printStackTrace();
		}
	}
	@Action("/download/versionDesc")
	public String versionDesc(){
		if(!StrUtil.isEmpty(key)){
			descList = sDDownloadCountService.desc(key);
		}
		return "descSuc";
	}
	@Action("/download/updateDownloadCount")
	public void updateDownloadCount(){
		if(StrUtil.isEmpty(key)){
			return;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key", key);
		sDDownloadCountService.update(map);
		map = new HashMap<String, Object>();
		String fix = key.substring(key.length()-1);
		map.put("type", fix);
		map.put("version", version);
		if(key.startsWith(SysConstant.SDK)){
			map.put("date",DateUtil.dateToStr(DateUtil.getCurrentDate(), DateUtil.NO_SLASH));
			Map<String, Object> cutMap = sDDownloadCountService.getStatictisSdk(map);
			if(cutMap==null){
				map.put("date",DateUtil.getCurrentDate());
				sDDownloadCountService.addStatictisSdk(map);
			}else{
				sDDownloadCountService.updateStatictisSdk(map);
			}
		}else if(key.startsWith(SysConstant.CLIENT_DEMO)){
			map.put("date",DateUtil.dateToStr(DateUtil.getCurrentDate(), DateUtil.NO_SLASH));
			Map<String, Object> cutMap = sDDownloadCountService.getStatictisDemo(map);
			if(cutMap==null){
				map.put("date",DateUtil.getCurrentDate());
				sDDownloadCountService.addStatictisDemo(map);
			}else{
				sDDownloadCountService.updateStatictisDemo(map);
			}
		}
	}
	@Action("/download/downLocal")
	public void downLocalFile(){
		if(path==null){
			return;
		}
		path = Des3Utils.decodeDes3(path);
		FileUtil.download(path, response);
	}
	private String toDownload(HttpServletResponse resp,String fileName, byte[] bytes) {
		OutputStream out = null;
		try {
			resp.setCharacterEncoding("GBK");
			resp.setHeader("Content-Disposition", "attachment;filename="
					+ new String(fileName.getBytes("GBK"), "ISO-8859-1"));
			resp.setContentType("application/octet-stream");
			out = new BufferedOutputStream(resp.getOutputStream());
			out.write(bytes);
			out.flush();
		} catch (Exception e) {
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
		logger.info("本次文件下载成功");
		return null;
	}
	private byte[] getFileByte(String type,String appSid,String clientNumber,String date,String table,HttpServletRequest request, HttpServletResponse response) throws IOException{
		/*
		 * localPath 本地文件保存目录
		 * app话单：save_path/taskType_EnName/appSid/privDate.txt
		 * client话单：save_path/taskType_EnName/appSid/clientNumber/privDate.txt
		 */
		StringBuilder localPath = new StringBuilder();
		localPath.append(SysConfig.getInstance().getProperty("save_path"));
		localPath.append("/");
		localPath.append(type);
		localPath.append("/");
		localPath.append(appSid);
		if (StringUtils.isNotBlank(clientNumber)) {
			localPath.append("/");
			localPath.append(clientNumber);
		}
		localPath.append("/");
		localPath.append(date);
		localPath.append(AppConstant.CALL_LOG_SUFFIX);
		String sql = SysConstant.DOWNLOAD_SQL;
		sql = sql.replace("[table]", table);
		sql = sql.replace("[local_path]", localPath);
		sql = sql.replaceAll("\r", " ").replaceAll("\n|\t", "");
		List<Map<String, Object>> list = consumeService.getFastDFS(sql);// 查询FastDFS服务器上的文件路径
		if (list == null || list.size() < 1) {
			logger.debug("应用："+appSid+"，文件不存在，下载失败：sql=" + sql);
			return null;
		}
		String remotePath = list.get(0).get("remote_path").toString();
		FastDfsClient fastDfsClient = new FastDfsClient();
		byte[] byteArray = null;
		try {
			byteArray = fastDfsClient.download(remotePath);
		} catch (Exception e) {
			logger.error("FastDFS服务器上的文件下载失败：table=" + table + ", localPath=" + localPath + ", remotePath="
					+ remotePath, e);
			return null;
		}
		if (byteArray == null || byteArray.length < 1) {
			logger.error("FastDFS服务器上的文件为空：table=" + table + ", localPath=" + localPath + ", remotePath=" + remotePath);
			return null;
		}
		logger.info("文件存放地址：localPath=" + localPath);
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
	private String check(String type,String date) {
		if (StringUtils.isBlank(type) || StringUtils.isBlank(date)) {
			logger.info("-------------type为空或日期为空-------------------");
			return "参数错误，请检查后再试";
		}
		if (type.equalsIgnoreCase("day") || type.equalsIgnoreCase("week")) {
			Date d = parseDate(date, "yyyyMMdd");
			if (d == null) {
				logger.info("-------------日期格式必须为yyyyMMdd-------------------");
				return "参数错误，请检查后再试";
			}
			int today = Integer.parseInt(DateTime.now().toString("yyyyMMdd"));
			if (Integer.parseInt(date) >= today) {
				logger.info("----------------日期必须小于今天----------------");
				return "日期必须小于今天";
			}
			if (type.equalsIgnoreCase("week")) {
				DateTime dt = new DateTime(d);
				if (dt.dayOfWeek().get() != 1) {
					logger.info("-------------日期不是周一，下载失败-------------------");
					return "参数错误，请检查后再试";
				}
				int monday = Integer.parseInt(DateTime.now().dayOfWeek().withMinimumValue().toString("yyyyMMdd"));// 当前时间的周一
				if (Integer.parseInt(date) >= monday) {
					logger.info("-------------日期必须小于本周-------------------");
					return "日期必须小于本周";
				}
			}

		} else if (type.equalsIgnoreCase("month")) {
			Date d = parseDate(date, "yyyyMM");
			if (d == null) {
				logger.info("-------------日期格式必须为yyyyMM-------------------");
				return "参数错误，请检查后再试";
			}
			int month = Integer.parseInt(DateTime.now().toString("yyyyMM"));
			if (Integer.parseInt(date) >= month) {
				logger.info("-------------日期必须小于本月-------------------");
				return "日期必须小于本月";
			}
		} else {
			logger.info("-------------type只能为：day、week、month-------------------");
			return "参数错误，请检查后再试";
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
	 * 下载录音文件
	 */
	@Action("/download/lyFile")
	public void lyDownload(){
		path = Des3Utils.decodeDes3(path);
		if(StrUtil.isEmpty(path)){
			StrUtil.writeMsg(response, "下载地址错误", null);
			return;
		}
		byte[] byteArray = null;
		FastDfsClient fastDfsClient = new FastDfsClient();
		try {
			byteArray = fastDfsClient.download(path);
		} catch (Exception e) {
			logger.error("下载录音文件失败："+path);
			return;
		}
		fileName = path.substring(path.lastIndexOf("/")+1);
		logger.info("下载文件："+fileName+",大小："+byteArray==null?"0":byteArray.length+"");
		toDownload(response, fileName, byteArray);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<Map<String, Object>> getDescList() {
		return descList;
	}
	public void setDescList(List<Map<String, Object>> descList) {
		this.descList = descList;
	}
	
	
}
