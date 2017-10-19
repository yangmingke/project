package com.flypaas.admin.action.audit;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.audit.SecurityExtractionService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 审核管理-保障金审核提现
 * 
 * @author zenglb
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/audit/securityExtraction/query.jsp"),
		@Result(name = "view", location = "/WEB-INF/content/audit/securityExtraction/view.jsp") })
public class SecurityExtractionAction extends BaseAction {
	private static final long serialVersionUID = -8050303601312051986L;
	private SecurityExtractionService securityExtractionService;

	@Resource
	public void setSecurityExtractionService(SecurityExtractionService securityExtractionService) {
		this.securityExtractionService = securityExtractionService;
	}

	/**
	 * 查询
	 * 
	 * @return
	 */
	@Action("/securityExtraction/query")
	public String query() {
		page = securityExtractionService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 查看
	 * 
	 * @return
	 */
	@Action("/securityExtraction/view")
	public String view() {
		String id = StrutsUtils.getParameterTrim("id");
		if (StringUtils.isNotBlank(id)) {
			data = securityExtractionService.view(Long.parseLong(id));
		}
		return "view";
	}

	/**
	 * 审核
	 * 
	 * @return
	 */
	@Action("/securityExtraction/audit")
	public void audit() {
		data = securityExtractionService.audit(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	/**
	 * 保存补充原因
	 * 
	 * @return
	 */
	@Action("/securityExtraction/saveReason")
	public void saveReason() {
		data = securityExtractionService.saveReason(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

}
