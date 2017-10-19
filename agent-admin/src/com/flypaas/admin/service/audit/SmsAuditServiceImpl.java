package com.flypaas.admin.service.audit;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.constant.AuditConstant;
import com.flypaas.admin.constant.LogConstant.LogType;
import com.flypaas.admin.constant.MsgConstant.MsgType;
import com.flypaas.admin.constant.MsgConstant.TemplateId;
import com.flypaas.admin.constant.SmsConstant;
import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.service.LogService;
import com.flypaas.admin.service.data.MsgService;
import com.flypaas.admin.util.cache.RedisUtils;

/**
 * 审核管理-短信模板审核
 * 
 * @author xiejiaan
 */
@Service
@Transactional
public class SmsAuditServiceImpl implements SmsAuditService {
	@Autowired
	private MasterDao dao;
	@Autowired
	private LogService logService;
	@Autowired
	private AuditService auditService;
	@Autowired
	private MsgService msgService;

	@Override
	public PageContainer query(Map<String, String> params) {
		return dao.getSearchPage("smsAudit.query", "smsAudit.queryCount", params);
	}

	@Override
	public Map<String, Object> view(long templateId) {
		return dao.getOneInfo("smsAudit.getSmsAudit", templateId);
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
		String templateId = params.get("template_id");
		String msg;
		TemplateId msgTemplateId;
		if (type == AuditConstant.AUDIT_STATUS_PASS) {
			msg = "审核通过";
			msgTemplateId = TemplateId.smsAudit_pass;
			params.put("status", String.valueOf(SmsConstant.STATUS_2));
		} else {
			msg = "审核不通过";
			msgTemplateId = TemplateId.smsAudit_no_pass;
			params.put("status", String.valueOf(SmsConstant.STATUS_3));
		}

		int i = dao.update("smsAudit.audit", params);
		if (i > 0) {
			Map<String, Object> auditParams = new HashMap<String, Object>();
			auditParams.put("audit_type", AuditConstant.AUDIT_TYPE_SMS);
			auditParams.put("status", type);
			auditParams.put("audit_desc", params.get("audit_desc"));
			auditParams.put("audited_id", templateId);
			auditService.saveAudit(auditParams);// 保存审核结果

			Map<String, Object> template = dao.getOneInfo("smsAudit.getSmsAudit", templateId);

			Map<String, Object> templateParams = new HashMap<String, Object>();
			templateParams.put("developer_email", template.get("email"));
			templateParams.put("template_name", template.get("name"));
			templateParams.put("template_id", templateId);
			templateParams.put("app_name", template.get("app_name"));
			templateParams.put("reason", params.get("audit_desc"));
			msgService.sendMsg(template.get("sid").toString(), MsgType.system_msg, msgTemplateId, templateParams);// 发送消息
			RedisUtils.flushSmsTemplateCache(templateId);// 刷新前台缓存信息
			data.put("result", "success");
			data.put("msg", msg + "成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "短信模板不存在，" + msg + "失败");
		}
		logService.add(LogType.update, "审核管理-短信模板审核：" + msg, params, data);
		return data;
	}

	@Override
	public Map<String, Object> saveReason(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		int i = auditService.saveReason(AuditConstant.AUDIT_TYPE_SMS, params.get("template_id"),
				params.get("audit_desc"));
		if (i > 0) {
			RedisUtils.flushSmsTemplateCache(params.get("template_id"));// 刷新前台缓存信息
			data.put("result", "success");
			data.put("msg", "补充原因成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "短信模板不存在，补充原因失败");
		}
		logService.add(LogType.update, "审核管理-短信模板审核：补充原因", params, data);
		return data;
	}
}
