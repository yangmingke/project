package com.flypaas.admin.action.audit;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.audit.QualificationService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 审核管理-资质审核
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/audit/qualification/query.jsp"),
		@Result(name = "view", location = "/WEB-INF/content/audit/qualification/view.jsp") })
public class QualificationAction extends BaseAction {
	private static final long serialVersionUID = -8050303601312051986L;
	@Autowired
	private QualificationService qualificationService;

	/**
	 * 查询资质
	 * 
	 * @return
	 */
	@Action("/qualification/query")
	public String query() {
		page = qualificationService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 查看资质
	 * 
	 * @return
	 */
	@Action("/qualification/view")
	public String view() {
		String sid = StrutsUtils.getParameterTrim("sid");
		if (StringUtils.isNotBlank(sid)) {
			data = qualificationService.view(sid);
		}
		return "view";
	}

	/**
	 * 审核
	 * 
	 * @return
	 */
	@Action("/qualification/audit")
	public void audit() {
		data = qualificationService.audit(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	/**
	 * 保存补充原因
	 * 
	 * @return
	 */
	@Action("/qualification/saveReason")
	public void saveReason() {
		data = qualificationService.saveReason(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

}
