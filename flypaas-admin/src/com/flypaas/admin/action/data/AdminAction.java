package com.flypaas.admin.action.data;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.data.AdminService;
import com.flypaas.admin.util.web.AuthorityUtils;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 信息管理-管理员中心
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "view", location = "/WEB-INF/content/data/admin/view.jsp"),
		@Result(name = "editForSelf", location = "/WEB-INF/content/data/admin/editForSelf.jsp"),
		@Result(name = "editPassword", location = "/WEB-INF/content/data/admin/editPassword.jsp"),
		@Result(name = "query", location = "/WEB-INF/content/data/admin/query.jsp"),
		@Result(name = "edit", location = "/WEB-INF/content/data/admin/edit.jsp") })
public class AdminAction extends BaseAction {
	private static final long serialVersionUID = -1234214414349439397L;
	@Autowired
	private AdminService adminService;

	/**
	 * 管理员资料
	 * 
	 * @return
	 */
	@Action("/admin/view")
	public String view() {
		data = adminService.getAdmin(AuthorityUtils.getLoginSid());
		return "view";
	}

	/**
	 * 修改管理员自己的资料页面
	 * 
	 * @return
	 */
	@Action("/admin/editForSelf")
	public String editForSelf() {
		data = adminService.getAdmin(AuthorityUtils.getLoginSid());
		return "editForSelf";
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
		StrutsUtils.setAttribute("sid", AuthorityUtils.getLoginSid());
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
		String sid = StrutsUtils.getParameterTrim("sid");
		if (StringUtils.isNotBlank(sid)) {
			data = adminService.getAdmin(sid);
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
		String sid = StrutsUtils.getParameterTrim("sid");
		String status = StrutsUtils.getParameterTrim("status");
		if (StringUtils.isNotBlank(sid) && NumberUtils.isDigits(status)) {
			data = adminService.updateStatus(sid, Integer.parseInt(status));
			StrutsUtils.renderJson(data);
		}
	}

	/**
	 * 发送短信验证码
	 * 
	 * @return
	 */
	@Action("/admin/sendVerifyCode")
	public void sendVerifyCode() {
		String mobile = StrutsUtils.getParameterTrim("mobile");
		if (NumberUtils.isDigits(mobile) && mobile.matches("1\\d{10}")) {
			data = adminService.sendVerifyCode(mobile);
			StrutsUtils.renderJson(data);
		}
	}

}
