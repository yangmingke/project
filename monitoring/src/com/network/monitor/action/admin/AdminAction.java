package com.network.monitor.action.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.network.monitor.action.BaseAction;
import com.network.monitor.service.admin.AdminService;
import com.network.monitor.util.web.AuthorityUtils;
import com.network.monitor.util.web.StrutsUtils;

/**
 * 管理员中心
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "view", location = "/WEB-INF/content/csm/totalcsm/currentcsm.jsp"),
		@Result(name = "editPassword", location = "/WEB-INF/content/admin/editPassword.jsp"),
		@Result(name = "query", location = "/WEB-INF/content/admin/query.jsp"),
		@Result(name = "edit", location = "/WEB-INF/content/admin/edit.jsp"),
		@Result(name = "topHeader", location = "/WEB-INF/content/admin/topHeader.jsp") })
public class AdminAction extends BaseAction {
	private static final long serialVersionUID = -1234214414349439397L;
	@Autowired
	private AdminService adminService;

	/**
	 * 管理员资料
	 * 
	 * @return
	 */
	@Action("/admin/topHeader")
	public String topHeader() {
		HttpServletRequest request = StrutsUtils.getRequest();
		request.setAttribute("login_user_id", AuthorityUtils.getLoginUserId());
		request.setAttribute("login_user_realName", AuthorityUtils.getLoginRealName());
		return "topHeader";
	}

	/**
	 * 管理员资料
	 * 
	 * @return
	 */
	@Action("/admin/view")
	public String view() {
		data = adminService.getAdmin(AuthorityUtils.getLoginUserId());
		return "view";
	}

	/**
	 * 修改管理员自己的资料页面
	 * 
	 * @return
	 */
	@Action("/admin/editForSelf")
	public String editForSelf() {
		return "forword:/admin/edit?id=" + AuthorityUtils.getLoginUserId();
	}

	/**
	 * 保存管理员资料，包括添加、修改
	 * 
	 * @return
	 */
	@Action("/admin/save")
	public void save() {
		data = adminService.saveAdmin(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	/**
	 * 修改密码页面
	 * 
	 * @return
	 */
	@Action("/admin/editPassword")
	public String editPassword() {
		StrutsUtils.setAttribute("id", AuthorityUtils.getLoginUserId());
		return "editPassword";
	}

	/**
	 * 保存密码
	 * 
	 * @return
	 */
	@Action("/admin/savePassword")
	public void savePassword() {
		data = adminService.savePassword(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	/**
	 * 管理员列表
	 * 
	 * @return
	 */
	@Action("/admin/query")
	public String query() {
		page = adminService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 添加管理员
	 * 
	 * @return
	 */
	@Action("/admin/add")
	public String add() {
		return "edit";
	}

	/**
	 * 修改管理员
	 * 
	 * @return
	 */
	@Action("/admin/edit")
	public String edit() {
		String id = StrutsUtils.getParameterTrim("id");
		if (StringUtils.isNotBlank(id)) {
			data = adminService.getAdmin(id);
		}
		return "edit";
	}

	/**
	 * 修改状态：恢复、删除
	 * 
	 * @return
	 */
	@Action("/admin/updateStatus")
	public void updateStatus() {
		String id = StrutsUtils.getParameterTrim("id");
		String status = StrutsUtils.getParameterTrim("status");
		if (StringUtils.isNotBlank(id) && NumberUtils.isDigits(status)) {
			data = adminService.updateStatus(Long.valueOf(id), Integer.parseInt(status));
			StrutsUtils.renderJson(data);
		}
	}
}
