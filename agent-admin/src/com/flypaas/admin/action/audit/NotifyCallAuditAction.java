package com.flypaas.admin.action.audit;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.audit.NotifyCallAuditService;
import com.flypaas.admin.service.audit.NotifyCallAuditTaskService;
import com.flypaas.admin.util.ConfigUtils;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 审核管理-语音通知审核
 * 
 * @author zenglb
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/audit/notifyCallAudit/query.jsp")})
public class NotifyCallAuditAction extends BaseAction {
	private static final long serialVersionUID = -3109783061638805774L;
	
	private NotifyCallAuditService notifyCallAuditService;
	
	@Resource
	public void setNotifyCallAuditService(NotifyCallAuditService notifyCallAuditService) {
		this.notifyCallAuditService = notifyCallAuditService;
	}
	
	private NotifyCallAuditTaskService notifyCallAuditTaskService;
	
	@Resource
	public void setNotifyCallAuditTaskService(NotifyCallAuditTaskService notifyCallAuditTaskService) {
		this.notifyCallAuditTaskService = notifyCallAuditTaskService;
	}
	/**
	 * 查询
	 * 
	 * @return
	 */
	@Action("/notifyCallAudit/query")
	public String query() {
		StrutsUtils.setAttribute("RING_BASE", ConfigUtils.ring_base_path);
		page = notifyCallAuditService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 审核
	 * 
	 * @return
	 */
	@Action("/notifyCallAudit/audit")
	public void audit() {
		data = notifyCallAuditService.audit(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}
	
	/**
	 * 保存补充原因
	 * 
	 * @return
	 */
	@Action("/notifyCallAudit/saveReason")
	public void saveReason() {
		data = notifyCallAuditService.saveReason(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}
	
	@Action("/notifyCallAudit/runtask")
	public void runtask() {
		notifyCallAuditTaskService.run();
		StrutsUtils.renderJson("runtask()");
	}
}
