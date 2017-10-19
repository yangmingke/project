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
import com.flypaas.admin.constant.UserConstant;
import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.service.LogService;
import com.flypaas.admin.service.data.MsgService;
import com.flypaas.admin.util.cache.RedisUtils;

/**
 * 审核管理-协议用户审核
 * 
 * @author zenglb
 */
@Service
@Transactional
public class ContractServiceImpl implements ContractService {
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
		return dao.getSearchPage("contract.query", "contract.queryCount", params);
	}

	@Override
	public Map<String, Object> view(String sid) {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> contractUser = dao.getOneInfo("contract.getContractUser", sid);// 获取资质
		if (contractUser != null) {
			data.put("contractUser", contractUser);
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
		String msg;
		String oauth_status_name = null;
		TemplateId templateId;
		boolean setContract = false;
		if (type == AuditConstant.AUDIT_STATUS_PASS) {
			msg = "审核通过";
			oauth_status_name = "审核通过";
			Map<String, Object> balance = dao.getOneInfo("contract.findBalance", params);
			Long balance_tmp = (Long) balance.get("balance");
			templateId = TemplateId.contract_id_19;
			params.put("security_status", String.valueOf(UserConstant.SECURITY_STATUS_2));
			if (null != balance_tmp && balance_tmp.longValue() >= SysConstant.SECURITY_MONEY) {
				templateId = TemplateId.contract_id_20;
				setContract = true;
				params.put("security_status", String.valueOf(UserConstant.SECURITY_STATUS_4));
				oauth_status_name = "协议生效";
			}
			params.put("balance_status", "1");
		} else {
			msg = "审核不通过";
			oauth_status_name = "审核不通过";
			templateId = TemplateId.contract_id_21;
			params.put("security_status", String.valueOf(UserConstant.SECURITY_STATUS_3));
		}

		int i = dao.update("contract.audit", params);
		if (i > 0) {
			if (setContract) {// 需更新用户状态为协议用户
				dao.update("contract.setContract", params);
				dao.update("contract.frozenBalance", params);
			}
			Map<String, Object> auditParams = new HashMap<String, Object>();
			auditParams.put("audit_type", AuditConstant.AUDIT_TYPE_SECURITY);
			auditParams.put("status", type);
			auditParams.put("audit_desc", params.get("audit_desc"));
			auditParams.put("audited_id", sid);
			auditService.saveAudit(auditParams);// 保存审核记录

			Map<String, Object> templateParams = new HashMap<String, Object>();
			templateParams.put("developer_email", params.get("email"));
			templateParams.put("reason", params.get("audit_desc"));
			msgService.sendMsg(sid, MsgType.system_msg, templateId, templateParams);// 发送消息
			RedisUtils.flushDeveloperCache(sid);// 刷新前台缓存信息
			data.put("result", "success");
			data.put("msg", msg + "成功");
			data.put("oauth_status_name", oauth_status_name);
		} else {
			data.put("result", "fail");
			data.put("msg", "开发者不存在，" + msg + "失败");
		}
		logService.add(LogType.update, "审核管理-协议用户审核：" + msg, params, data);
		return data;
	}

	@Override
	public Map<String, Object> saveReason(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		int i = auditService.saveReason(AuditConstant.AUDIT_TYPE_SECURITY, params.get("sid"), params.get("audit_desc"));
		if (i > 0) {
			RedisUtils.flushDeveloperCache(params.get("sid"));// 刷新前台缓存信息
			data.put("result", "success");
			data.put("msg", "补充原因成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "开发者不存在，补充原因失败");
		}
		logService.add(LogType.update, "审核管理-协议用户审核：补充原因", params, data);
		return data;
	}

	@Override
	public Map<String, Object> setContract(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		params.put("is_contract", "1");
		params.put("s_status", "4");
		params.put("t_relieve_type", "2");
		params.put("s_status_old", "2");
		int i = dao.update("setContractWithNoBalance", params);
		if (i > 0) {
			RedisUtils.flushDeveloperCache(params.get("sid"));// 刷新前台缓存信息
			data.put("result", "success");
			data.put("msg", "设置成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "开发者不存在，设置失败");
		}
		logService.add(LogType.update, "审核管理-协议用户审核：设置协议用户", params, data);
		return data;
	}
}
