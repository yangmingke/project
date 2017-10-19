package com.flypaas.admin.service.audit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Resource;

import org.apache.commons.lang3.math.NumberUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.constant.AuditConstant;
import com.flypaas.admin.constant.LogConstant.LogType;
import com.flypaas.admin.constant.MsgConstant.MsgType;
import com.flypaas.admin.constant.MsgConstant.TemplateId;
import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.service.CommonService;
import com.flypaas.admin.service.LogService;
import com.flypaas.admin.service.data.MsgService;
import com.flypaas.admin.util.ConfigUtils;
import com.flypaas.admin.util.ProcessObject;
import com.flypaas.admin.util.file.FastDfsClient;
import com.flypaas.admin.util.rest.HttpHelper;
import com.flypaas.admin.util.rest.utils.JsonUtil;

/**
 * 审核管理-语音通知审核
 * 
 * @author zenglb
 */
@Service
@Transactional
public class NotifyCallAuditServiceImpl implements NotifyCallAuditService {
	private static final Logger logger = LoggerFactory.getLogger(NotifyCallAuditServiceImpl.class);
	@Autowired
	private MasterDao dao;
	@Autowired
	private LogService logService;
	@Autowired
	private AuditService auditService;
	@Autowired
	private MsgService msgService;
	@Autowired
	private CommonService commonService;

	@Override
	public PageContainer query(Map<String, String> params) {
		return dao.getSearchPage("notifyCall.query", "notifyCall.queryCount", params);
	}

	private RingAuditService ringAuditService;

	@Resource
	public void setRingAuditService(RingAuditService ringAuditService) {
		this.ringAuditService = ringAuditService;
	}

	@Override
	public Map<String, Object> audit(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		int type = NumberUtils.toInt(params.get("type"), -1);// 审核结果
		if (type != AuditConstant.AUDIT_STATUS_PASS && type != AuditConstant.AUDIT_STATUS_NO_PASS) {
			data.put("result", "fail");
			data.put("msg", "不是审核通过或审核不通过，操作失败");
			return data;
		}
		String id = params.get("id");
		String msg;
		TemplateId msgTemplateId;
		if (type == AuditConstant.AUDIT_STATUS_PASS) {
			msg = "审核通过";
			msgTemplateId = TemplateId.notify_call_33;
			params.put("audit_status", "2");
			params.put("upload_status", "2");
			params.put("send_status", "2");
		} else {
			msg = "审核不通过";
			msgTemplateId = TemplateId.notify_call_34;
			params.put("audit_status", "3");
		}

		int i = dao.update("notifyCall.audit", params);

		if (i > 0) {
			Map<String, Object> auditParams = new HashMap<String, Object>();
			auditParams.put("audit_type", AuditConstant.AUDIT_TYPE_NOTIFY_CALL);
			auditParams.put("status", type);
			auditParams.put("audit_desc", params.get("audit_desc"));
			auditParams.put("audited_id", id);
			auditService.saveAudit(auditParams);// 保存审核结果

			Map<String, Object> templateParams = new HashMap<String, Object>();
			templateParams.put("template_id", id);
			templateParams.put("reason", params.get("audit_desc"));
			msgService.sendMsg(params.get("sid"), MsgType.system_msg, msgTemplateId, templateParams);// 发送消息
			data.put("result", "success");
			data.put("msg", msg + "成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "语音通知不存在，" + msg + "失败");
		}
		logService.add(LogType.update, "审核管理-语音通知审核：" + msg, params, data);
		return data;
	}

	@Override
	public Map<String, Object> saveReason(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		int i = auditService.saveReason(AuditConstant.AUDIT_TYPE_NOTIFY_CALL, params.get("id"),
				params.get("audit_desc"));
		if (i > 0) {
			data.put("result", "success");
			data.put("msg", "补充原因成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "语音通知不存在，补充原因失败");
		}
		logService.add(LogType.update, "审核管理-语音通知审核：补充原因", params, data);
		return data;
	}

	@Override
	public List<Map<String, Object>> findAllTasks() {
		return dao.getSearchList("notifyCall.findAllTasks", null);
	}

	@Override
	public void updateRun(List<Map<String, Object>> list) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("send_status", 5);// 发送中
		params.put("list", list);
		dao.update("notifyCall.updateRun", params);
	}

	final Integer n0 = Integer.valueOf(0);
	final Integer n1 = Integer.valueOf(1);
	final Integer n2 = Integer.valueOf(2);
	final Integer n3 = Integer.valueOf(3);
	final Integer n4 = Integer.valueOf(4);
	final Integer n5 = Integer.valueOf(5);

	@Override
	public void processNotifyCall(Map<String, Object> map) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", map.get("id"));

		Integer audit_status = (Integer) map.get("audit_status");
		Integer is_contract = (Integer) map.get("is_contract");
		Integer upload_status = (Integer) map.get("upload_status");
		Integer type = (Integer) map.get("type");

		/**
		 * type=0表示文本通知
		 */
		if (n0.equals(type) && (n1.equals(is_contract) || n2.equals(audit_status))) {
			if (n1.equals(audit_status)) {
				params.put("audit_status", 5);// 自动审核
			}
			if (null == map.get("file_name")) {
				String key = commonService.generatedGlobalId() + "";
				map.put("file_path", "voicemsg0");
				map.put("file_name", key + ".text");
				params.put("file_path", "voicemsg0");
				params.put("file_name", key + ".pcm");
			}

			if (covertAndUpload(type, map)) {// 转换上传
				String local_path = map.get("local_path").toString();
				String remote_path = FastDfsClient.upload(local_path);

				params.put("remote_path", remote_path);
				params.put("upload_status", 3);// 上传成功
				if (send(remote_path, map)) {
					params.put("send_status", 3);// 发送成功
				} else {
					params.put("send_status", 4);// 发送失败
				}
			} else {
				params.put("upload_status", 4);// 上传失败
			}
			/**
			 * type=1表示语音文件 audit_status=2 表示审核通过 upload_status=1|2 表示 待上传
			 */
		} else if (n1.equals(type) && n2.equals(audit_status) && (n1.equals(upload_status) || n2.equals(upload_status))) {// 审核通过
			if (covertAndUpload(type, map)) {// 转换上传
				params.put("upload_status", 3);// 上传成功
			} else {
				params.put("upload_status", 4);// 上传失败
			}
		} else {
			return;
		}
		// 保存处理结果
		dao.update("notifyCall.updateProcessResult", params);
	}

	/**
	 * <pre>
	 * 通过shell脚本将文字转成语音文件，然后通过ftp上传到cb服务器
	 * 命令格式："脚本地址" "文件名" "文件地址" "转换类型" "内容" "cb地址列表" "cb侧的文件夹名称"
	 * 示例：
	 * 文字	'/home/file/cbrsy_text/rsync.sh' 'xja_100' '/home/flypaas/oauth_ring/voicemsg0' 'text' '语音通知内容' '' 'voicemsg0'
	 * 语音id	'/home/file/cbrsy_text/rsync.sh' '143' '/home/flypaas/oauth_ring/voicemsg1' 'wav' '' '113.31.89.149' 'voicemsg1'
	 * </pre>
	 * 
	 * @param type
	 *            type=0表示文本通知，type=1表示语音文件
	 * @param map
	 * @return
	 */
	private boolean covertAndUpload(Integer type, Map<String, Object> map) {
		final String[] cmds = { "脚本地址", "文件名", "文件地址", "转换类型", "内容", "cb地址列表", "cb侧的文件夹名称" };
		cmds[0] = ConfigUtils.rsync_shell;
		String fileName = (String) map.get("file_name");
		String[] ss = fileName.split("\\.");
		fileName = ss[0];
		String fileType = ss[1];

		cmds[1] = fileName;
		cmds[2] = ConfigUtils.notify_call_base_path + "/" + (String) map.get("file_path");
		cmds[3] = "pcm".equals(fileType) ? "text" : fileType;

		StringBuffer sb = new StringBuffer();
		if (n0.equals(type)) {// type=0表示文本通知
			map.put("local_path", cmds[2] + "/" + cmds[1] + ".g729");

		} else {// type=1表示语音文件，需要上传到所有cb
			List<Map<String, Object>> list = ringAuditService.reloadCbIpList(true);
			for (Map<String, Object> m : list) {
				sb.append((String) m.get("cb_ip")).append(",");
			}
			int len = sb.length();
			if (len > 0) {
				sb.setLength(len - 1);
			}
		}
		cmds[4] = null != map.get("content") ? (String) map.get("content") : "";
		cmds[5] = sb.toString();
		cmds[6] = (String) map.get("file_path");
		logger.debug("【文字转语音，上传cb】开始：" + Arrays.toString(cmds));

		final AtomicBoolean boo = new AtomicBoolean(false);
		ProcessObject.execute(cmds, new ProcessObject() {
			protected boolean processOutStreamLine(String line) {
				if (!line.startsWith("Frame")) {
					logger.info("====" + line);
				}
				if (line.startsWith("result=")) {
					if ("result=OK".equals(line.trim())) {
						logger.info("====转语音成功！");
						boo.set(true);
					} else {
						logger.info("====转语音失败！" + line);
						boo.set(false);
					}
					return true;
				}
				return false;
			}
		});
		return boo.get();
	}

	String interface_req_url = null;

	/**
	 * 发送语音通知
	 * 
	 * @param remote_path
	 *            FastDFS服务器上的文件路径
	 * @param map
	 * @return
	 */
	private boolean send(String remote_path, Map<String, Object> map) {
		if (null == interface_req_url) {
			synchronized (this) {
				if (null == interface_req_url) {
					interface_req_url = ConfigUtils.interface_base_url
							+ "/mem?func=voiceNotify&sid=%1$s&appId=%2$s&to=%3$s&toSerNum=%4$s&type=2&playTimes=%5$s&voiceName=%6$s&voiceId=%7$s&callId=%8$s&callTime=%9$s&remotePath=%10$s";
				}
			}
		}
		String fileName = (String) map.get("file_name");
		int i = fileName.lastIndexOf("\\.");
		int j = fileName.lastIndexOf(".");
		if (i > 0) {
			fileName = fileName.substring(0, i);
		} else if (j > 0) {
			fileName = fileName.substring(0, j);
		}
		/**
		 * 参数表 1.sid,2.appId,3.to,4.toSerNum,5.playTimes,6.voiceName
		 */
		String strUrl = String.format(interface_req_url, map.get("sid"), map.get("app_sid"), map.get("to"),
				map.get("toServNum"), map.get("playTimes"), (map.get("file_path") + "/" + fileName), map.get("id"),
				map.get("call_id"), map.get("call_time"), remote_path);

		logger.debug("【发送语音通知】开始：strUrl={}", strUrl);
		String result = HttpHelper.httpConnectionGet(strUrl);
		logger.debug("【发送语音通知】结束：result={}, strUrl={}", result, strUrl);
		if (null != result) {
			try {
				JSONObject obj = JsonUtil.toJsonObj(result);
				if (0 == obj.getInt("resultcode")) {
					return true;
				} else {
					logger.debug("result[" + result + "]url[" + strUrl + "]");
					return false;
				}
			} catch (Throwable e) {
				logger.debug("result[" + result + "]url[" + strUrl + "]");
				logger.error(e.getMessage(), e);
			}
		} else {
			logger.debug("null result by url[" + strUrl + "]");
		}
		return false;
	}

}
