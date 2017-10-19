package com.flypaas.admin.action.audit;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.audit.AppAuditService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 审核管理-应用审核
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/audit/appAudit/query.jsp"),
		@Result(name = "view", location = "/WEB-INF/content/audit/appAudit/view.jsp") })
public class AppAuditAction extends BaseAction {
	private static final long serialVersionUID = 4611235166805765456L;
	@Autowired
	private AppAuditService appAuditService;

	/**
	 * 查询应用
	 * 
	 * @return
	 */
	@Action("/appAudit/query")
	public String query() {
		page = appAuditService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 查看应用
	 * 
	 * @return
	 */
	@Action("/appAudit/view")
	public String view() {
		String appSid = StrutsUtils.getParameterTrim("app_sid");
		if (StringUtils.isNotBlank(appSid)) {
			data = appAuditService.view(appSid);
		}
		return "view";
	}

	/**
	 * 审核
	 * 
	 * @return
	 */
	@Action("/appAudit/audit")
	public void audit() {
		data = appAuditService.audit(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	/**
	 * 保存补充原因
	 * 
	 * @return
	 */
	@Action("/appAudit/saveReason")
	public void saveReason() {
		data = appAuditService.saveReason(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

}
