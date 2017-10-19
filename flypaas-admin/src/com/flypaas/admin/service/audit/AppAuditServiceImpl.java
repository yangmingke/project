package com.flypaas.admin.service.audit;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.constant.AppConstant;
import com.flypaas.admin.constant.AuditConstant;
import com.flypaas.admin.constant.LogConstant.LogType;
import com.flypaas.admin.constant.MsgConstant.MsgType;
import com.flypaas.admin.constant.MsgConstant.TemplateId;
import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.service.LogService;
import com.flypaas.admin.service.data.MsgService;
import com.flypaas.admin.util.FlypaasParamUtils;
import com.flypaas.admin.util.cache.RedisUtils;

/**
 * 审核管理-应用审核
 * 
 * @author xiejiaan
 */
@Service
@Transactional
public class AppAuditServiceImpl implements AppAuditService {
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
		return dao.getSearchPage("appAudit.query", "appAudit.queryCount", params);
	}

	@Override
	public Map<String, Object> view(String appSid) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("app", dao.getOneInfo("appAudit.getAppAudit", appSid));// 获取应用
		data.put("callback", dao.getSearchList("appAudit.getCallback", appSid));// 获取回调地址
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
		String appSid = params.get("app_sid");
		String msg;
		TemplateId templateId;
		Map<String, Object> app = dao.getOneInfo("appAudit.getAppAudit", appSid);// 获取应用

		String nowStr = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
		params.put("update_date", nowStr);
		String route_area = params.get("route_area");// 路由域

		if (type == AuditConstant.AUDIT_STATUS_PASS) {
			msg = "审核通过";
			templateId = TemplateId.appAudit_pass;
			params.put("route_area", route_area);
			params.put("status", String.valueOf(AppConstant.STATUS_1));
			params.put("audit_date", nowStr);// 添加审核时间
			String smsMsgNbr = Objects.toString(app.get("sms_msg_nbr"), null);
			if (type == AuditConstant.AUDIT_STATUS_PASS && StringUtils.isBlank(smsMsgNbr)) {
				long s = Math.abs(UUID.randomUUID().getMostSignificantBits());
				String sms_msg_nbr = String.valueOf(s);
				if (sms_msg_nbr.length() > 11) {
					sms_msg_nbr = sms_msg_nbr.substring(0, 11);
				}
				params.put("sms_msg_nbr", sms_msg_nbr);// app审核通过后，分配短信号码
			}
		} else {
			msg = "审核不通过";
			templateId = TemplateId.appAudit_no_pass;
			params.put("status", String.valueOf(AppConstant.STATUS_2));
		}

		int i = dao.update("appAudit.audit", params);
		if (i > 0) {
			Map<String, Object> auditParams = new HashMap<String, Object>();
			auditParams.put("audit_type", AuditConstant.AUDIT_TYPE_APP);
			auditParams.put("status", type);
			auditParams.put("audit_desc", params.get("audit_desc"));
			auditParams.put("audited_id", appSid);
			auditService.saveAudit(auditParams);// 保存审核结果

			Map<String, Object> templateParams = new HashMap<String, Object>();
			templateParams.put("developer_email", app.get("email"));
			templateParams.put("app_name", app.get("app_name"));
			templateParams.put("app_sid", app.get("app_sid"));
			templateParams.put("app_kind", FlypaasParamUtils.get("app_kind", app.get("app_kind").toString(), null));
			String reason = params.get("audit_desc");
			templateParams.put("reason", null == reason ? "" : reason);
			msgService.sendMsg(app.get("sid").toString(), MsgType.system_msg, templateId, templateParams);// 发送消息

			RedisUtils.flushWhiteListCache(appSid);// 刷新白名单缓存
			RedisUtils.flushShowCache(appSid);// 刷新显号缓存
			RedisUtils.flushAppCache(appSid);// 刷新应用缓存

			data.put("result", "success");
			data.put("msg", msg + "成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "应用不存在，" + msg + "失败");
		}
		logService.add(LogType.update, "审核管理-应用审核：" + msg, params, data);
		return data;
	}

	@Override
	public Map<String, Object> saveReason(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		int i = auditService.saveReason(AuditConstant.AUDIT_TYPE_APP, params.get("app_sid"), params.get("audit_desc"));
		if (i > 0) {
			RedisUtils.flushAppCache(params.get("app_sid"));// 刷新前台缓存信息
			data.put("result", "success");
			data.put("msg", "补充原因成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "应用不存在，补充原因失败");
		}
		logService.add(LogType.update, "审核管理-应用审核：补充原因", params, data);
		return data;
	}
}
