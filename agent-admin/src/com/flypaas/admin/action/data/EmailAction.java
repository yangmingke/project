package com.flypaas.admin.action.data;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.data.EmailService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 信息管理-管理员中心-邮件管理
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/data/email/query.jsp"),
		@Result(name = "edit", location = "/WEB-INF/content/data/email/edit.jsp") })
public class EmailAction extends BaseAction {
	private static final long serialVersionUID = -3536999615025429552L;
	@Autowired
	private EmailService emailService;

	/**
	 * 查询邮件
	 * 
	 * @return
	 */
	@Action("/email/query")
	public String query() {
		page = emailService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 添加邮件
	 * 
	 * @return
	 */
	@Action("/email/add")
	public String add() {
		data = emailService.getTemplate();
		return "edit";
	}

	/**
	 * 保存邮件
	 * 
	 * @return
	 */
	@Action("/email/save")
	public void save() {
		data = emailService.save(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

}
