package com.flypaas.admin.action.data;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.data.AuthorityService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 信息管理-管理员中心-权限管理
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/data/authority/query.jsp"),
		@Result(name = "edit", location = "/WEB-INF/content/data/authority/edit.jsp") })
public class AuthorityAction extends BaseAction {
	private static final long serialVersionUID = 7398375055342636165L;

	@Autowired
	private AuthorityService authorityService;

	/**
	 * 查询权限
	 * 
	 * @return
	 */
	@Action("/authority/query")
	public String query() {
		page = authorityService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 添加管理权限
	 * 
	 * @return
	 */
	@Action("/authority/add")
	public String add() {
		data = authorityService.getAuthority(null);
		return "edit";
	}

	/**
	 * 修改管理权限
	 * 
	 * @return
	 */
	@Action("/authority/edit")
	public String edit() {
		Integer roleId = null;
		String roleIdS = StrutsUtils.getParameterTrim("role_id");
		if (NumberUtils.isDigits(roleIdS)) {
			roleId = Integer.valueOf(roleIdS);
		}
		data = authorityService.getAuthority(roleId);
		return "edit";
	}

	/**
	 * 保存管理权限，包括添加、修改
	 * 
	 * @return
	 */
	@Action("/authority/save")
	public void save() {
		data = authorityService.saveAuthority(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	/**
	 * 修改状态：恢复、删除
	 * 
	 * @return
	 */
	@Action("/authority/updateStatus")
	public void updateStatus() {
		String roleId = StrutsUtils.getParameterTrim("role_id");
		String status = StrutsUtils.getParameterTrim("status");
		if (NumberUtils.isDigits(roleId) && NumberUtils.isDigits(status)) {
			data = authorityService.updateStatus(Integer.parseInt(roleId), Integer.parseInt(status));
		}
		StrutsUtils.renderJson(data);
	}

}
