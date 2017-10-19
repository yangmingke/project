package com.flypaas.admin.action.audit;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.audit.ShowNbrsAuditService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 审核管理-号码白名单
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/audit/showNbrsAudit/query.jsp")})
public class ShowNbrsAuditAction extends BaseAction {
	private static final long serialVersionUID = 4611235166805765456L;
	@Autowired
	private ShowNbrsAuditService showNbrsAuditService;

	/**
	 * 查询应用
	 * 
	 * @return
	 */
	@Action("/showNbrsAudit/query")
	public String query() {
		DateTime d  = DateTime.now().plusYears(1);
		StrutsUtils.setAttribute("exp_date_default", d.toString("yyyy-MM-dd HH:mm:ss"));
		page = showNbrsAuditService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 审核
	 * 
	 * @return
	 */
	@Action("/showNbrsAudit/audit")
	public void audit() {
		data = showNbrsAuditService.audit(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	/**
	 * 保存补充原因
	 * 
	 * @return
	 */
	@Action("/showNbrsAudit/saveReason")
	public void saveReason() {
		data = showNbrsAuditService.saveReason(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}
}
