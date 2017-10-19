package com.flypaas.admin.action.audit;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.audit.SmsAuditService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 审核管理-短信模板审核
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/audit/smsAudit/query.jsp"),
		@Result(name = "view", location = "/WEB-INF/content/audit/smsAudit/view.jsp") })
public class SmsAuditAction extends BaseAction {
	private static final long serialVersionUID = 6179593955939636470L;
	@Autowired
	private SmsAuditService smsAuditService;

	/**
	 * 查询短信模板
	 * 
	 * @return
	 */
	@Action("/smsAudit/query")
	public String query() {
		page = smsAuditService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 查看短信模板
	 * 
	 * @return
	 */
	@Action("/smsAudit/view")
	public String view() {
		String templateId = StrutsUtils.getParameterTrim("template_id");
		if (NumberUtils.isDigits(templateId)) {
			data = smsAuditService.view(Long.parseLong(templateId));
		}
		return "view";
	}

	/**
	 * 审核
	 * 
	 * @return
	 */
	@Action("/smsAudit/audit")
	public void audit() {
		data = smsAuditService.audit(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	/**
	 * 保存补充原因
	 * 
	 * @return
	 */
	@Action("/smsAudit/saveReason")
	public void saveReason() {
		data = smsAuditService.saveReason(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

}
