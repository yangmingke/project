package com.flypaas.admin.service.audit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
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
import com.flypaas.admin.service.LogService;
import com.flypaas.admin.service.data.MsgService;

/**
 * 审核管理-铃声审核
 * 
 * @author zenglb
 */
@Service
@Transactional
public class RingAuditServiceImpl implements RingAuditService {
	@Autowired
	private MasterDao dao;
	@Autowired
	private LogService logService;
	@Autowired
	private AuditService auditService;
	@Autowired
	private MsgService msgService;
	Logger logger = LoggerFactory.getLogger(RingAuditServiceImpl.class);
	@Override
	public PageContainer query(Map<String, String> params) {
		return dao.getSearchPage("ringAudit.query", "ringAudit.queryCount", params);
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
			msgTemplateId = TemplateId.ring_pass_30;
			params.put("audit_status", "2");
			params.put("upload_status", "1");
		} else {
			msg = "审核不通过";
			msgTemplateId = TemplateId.ring_no_pass_31;
			params.put("audit_status", "3");
		}

		int i = dao.update("ringAudit.audit", params);
		
		String type_name = "2".equals(params.get("ring_type"))?"语音验证提示语音":"回拨欢迎语音";
		if (i > 0) {
			Map<String, Object> auditParams = new HashMap<String, Object>();
			auditParams.put("audit_type", AuditConstant.AUDIT_TYPE_RING);
			auditParams.put("status", type);
			auditParams.put("audit_desc", params.get("audit_desc"));
			auditParams.put("audited_id", id);
			auditService.saveAudit(auditParams);// 保存审核结果

			Map<String, Object> templateParams = new HashMap<String, Object>();
			templateParams.put("type_name", type_name);
			templateParams.put("template_id", id);
			templateParams.put("reason", params.get("audit_desc"));
			msgService.sendMsg(params.get("sid"), MsgType.system_msg, msgTemplateId, templateParams);// 发送消息
			data.put("result", "success");
			data.put("msg", msg + "成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "铃声不存在，" + msg + "失败");
		}
		logService.add(LogType.update, "审核管理-铃声审核：" + msg, params, data);
		return data;
	}

	@Override
	public Map<String, Object> saveReason(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		int i = auditService.saveReason(AuditConstant.AUDIT_TYPE_RING, params.get("id"),
				params.get("audit_desc"));
		if (i > 0) {
			data.put("result", "success");
			data.put("msg", "补充原因成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "铃声不存在，补充原因失败");
		}
		logService.add(LogType.update, "审核管理-铃声审核：补充原因", params, data);
		return data;
	}
	List<Map<String, Object>> cbipList = null;
	@Override
	public List<Map<String, Object>> reloadCbIpList(boolean cb) {
		if(null == cbipList || cb){
			synchronized (RingAuditServiceImpl.class) {
				if(null == cbipList || cb){
					cbipList = dao.getSearchList("ringAudit.reloadCbIpList",new HashMap<String,Object>());
				}
			}
		}
		return cbipList;
	}
	
	@Override
	public void saveUploadResult(Map<String, Object> params,boolean canLog) {
		int i = dao.update("ringAudit.saveUploadResult", params);
		if(canLog){
			if (i > 0) {
				logService.add(LogType.update, "审核管理-铃声审核：保存上传结果", params);
			} else {
				logService.add(LogType.update, "审核管理-铃声审核：保存上传结果", params);
			}
		}else{
			logger.info("审核管理-铃声审核：保存上传结果 : " + params);
		}
	}
	
	@Override
	public Map<String, Object> getOneById(String id) {
		return dao.getOneInfo("ringAudit.getRing", id);
	}
}
