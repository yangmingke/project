package com.flypaas.admin.service.audit;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.constant.AuditConstant;
import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.util.web.AuthorityUtils;
import com.flypaas.admin.util.web.HttpClientUtils;

/**
 * 公共的审核业务
 * 
 * @author xiejiaan
 */
@Service
@Transactional
public class AuditServiceImpl implements AuditService {
	private static final Logger logger = LoggerFactory.getLogger(AuditServiceImpl.class);
	@Autowired
	private MasterDao dao;

	@Override
	public int saveAudit(Map<String, Object> auditParams) {
		notifyCallback(auditParams);// 通知代理商业务审核结果

		auditParams.put("user_id", AuthorityUtils.getLoginSid());
		int i = dao.getSearchSize("audit.isAudit", auditParams);// 是否已经存在审核记录
		if (i == 0) {
			return dao.insert("audit.insertAudit", auditParams);
		} else {
			return dao.update("audit.updateAudit", auditParams);
		}
	}

	@Override
	public int saveReason(int auditType, String auditedId, String auditDesc) {
		Map<String, Object> auditParams = new HashMap<String, Object>();
		auditParams.put("audit_type", auditType);
		auditParams.put("audited_id", auditedId);
		auditParams.put("audit_desc", auditDesc);
		return dao.update("audit.saveReason", auditParams);
	}

	/**
	 * 通知代理商业务审核结果
	 * 
	 * @param auditParams
	 */
	private void notifyCallback(Map<String, Object> auditParams) {
		int audit_type = Integer.parseInt(auditParams.get("audit_type").toString());
		String audited_id = auditParams.get("audited_id").toString();
		String status = Objects.toString(auditParams.get("status"), "");
		String audit_desc = Objects.toString(auditParams.get("audit_desc"), "");
		String event = null;
		Map<String, Object> user = null;
		String call_address = null;

		switch (audit_type) {
		case AuditConstant.AUDIT_TYPE_RING: // 语音验证码的语音文件
			event = "voiceCodeFileAudit";
			user = dao.getOneInfo("audit.getCallAddressForCallRing", audited_id);
			break;
		case AuditConstant.AUDIT_TYPE_NOTIFY_CALL: // 语音通知的语音文件
			event = "voiceNotifyFileAudit";
			user = dao.getOneInfo("audit.getCallAddressForCallFile", audited_id);
			break;
		case AuditConstant.AUDIT_TYPE_SHOW_NBR_CALL: // 显示的号码
			event = "showNbrAudit";
			user = dao.getOneInfo("audit.getCallAddressForShownbrs", audited_id);
			if (user != null) {
				audited_id = user.get("nbr").toString();
			}
			break;
		case AuditConstant.AUDIT_TYPE_SMS: // 短信模板
			event = "smsTemplateAudit";
			user = dao.getOneInfo("audit.getCallAddressForSmsTemplate", audited_id);
			break;
		default:
			return;
		}
		if (user != null) {
			call_address = Objects.toString(user.get("call_address"), "");
		}

		if (StringUtils.isNotBlank(call_address)) {
			StringBuilder sb = new StringBuilder();
			sb.append("<?xml version=\"1.0\"?><request><event>");
			sb.append(event);
			sb.append("</event><accountid>");
			sb.append(user.get("sid"));
			sb.append("</accountid><appid>");
			sb.append(user.get("app_sid"));
			sb.append("</appid><id>");
			sb.append(audited_id);
			sb.append("</id><result>");
			sb.append(status);
			sb.append("</result><reason>");
			sb.append(audit_desc);
			sb.append("</reason><time>");
			sb.append(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
			sb.append("</time></request>");

			logger.debug("通知代理商业务审核结果：call_address={}, sb={}", call_address, sb);
			HttpClientUtils.postXml(call_address, sb.toString());// 回调用户通知接口
		}
	}

}
