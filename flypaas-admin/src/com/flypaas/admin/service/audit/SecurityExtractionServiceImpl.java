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
import com.flypaas.admin.constant.SysConstant;
import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.service.LogService;
import com.flypaas.admin.service.data.MsgService;
import com.flypaas.admin.util.cache.RedisUtils;

/**
 * 审核管理-保障金提现审核
 * 
 * @author zenglb
 */
@Service
@Transactional
public class SecurityExtractionServiceImpl implements SecurityExtractionService {
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
		params.put("money_rate", SysConstant.money_rate);
		return dao.getSearchPage("securityExtraction.query", "securityExtraction.queryCount", params);
	}

	@Override
	public Map<String, Object> view(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("money_rate", SysConstant.money_rate);
		params.put("id", id);
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> contractUser = dao.getOneInfo("securityExtraction.getEntity", params);// 获取资质
		if (contractUser != null) {
			data.put("entity", contractUser);
		}
		return data;
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
		String sid = params.get("sid");
		String id = params.get("id");
		String msg;
		TemplateId templateId;
		if (type == AuditConstant.AUDIT_STATUS_PASS) {
			msg = "审核通过";
			templateId = TemplateId.extraction_id_25;
			params.put("security_status", "0");
		} else {
			msg = "审核不通过";
			templateId = TemplateId.extraction_id_26;
			params.put("security_status", "2");
		}

		int i = dao.update("securityExtraction.audit", params);
		if (i > 0) {
			if (type == AuditConstant.AUDIT_STATUS_PASS) {// 审核通过
				dao.update("securityExtraction.addExtractionLog", params);
				dao.update("securityExtraction.extractionbalance", params);
			}
			Map<String, Object> auditParams = new HashMap<String, Object>();
			auditParams.put("audit_type", AuditConstant.AUDIT_TYPE_SECURITY_RELIEVE_APPLYFOR);
			auditParams.put("status", type);
			auditParams.put("audit_desc", params.get("audit_desc"));
			auditParams.put("audited_id", id);
			auditService.saveAudit(auditParams);// 保存审核记录

			Map<String, Object> templateParams = new HashMap<String, Object>();
			templateParams.put("developer_email", params.get("email"));
			templateParams.put("reason", params.get("audit_desc"));
			msgService.sendMsg(sid, MsgType.system_msg, templateId, templateParams);// 发送消息
			RedisUtils.flushDeveloperCache(sid);// 刷新前台缓存信息
			data.put("result", "success");
			data.put("msg", msg + "成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "开发者不存在，" + msg + "失败");
		}
		logService.add(LogType.update, "审核管理-保障金提现审核：" + msg, params, data);
		return data;
	}

	@Override
	public Map<String, Object> saveReason(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		int i = auditService.saveReason(AuditConstant.AUDIT_TYPE_SECURITY_RELIEVE_APPLYFOR, params.get("id"),
				params.get("audit_desc"));
		if (i > 0) {
			RedisUtils.flushDeveloperCache(params.get("sid"));// 刷新前台缓存信息
			data.put("result", "success");
			data.put("msg", "补充原因成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "开发者不存在，补充原因失败");
		}
		logService.add(LogType.update, "审核管理-保障金提现审核：补充原因", params, data);
		return data;
	}
}
