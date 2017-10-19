package com.flypaas.admin.action.cps;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.cps.PermissionService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 策略管理-策略权限配置-策略权限
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/cps/permission/query.jsp"),
		@Result(name = "view", location = "/WEB-INF/content/cps/permission/view.jsp"),
		@Result(name = "edit", location = "/WEB-INF/content/cps/permission/edit.jsp") })
public class PermissionAction extends BaseAction {
	private static final long serialVersionUID = 3405121726399298721L;
	@Autowired
	private PermissionService permissionService;

	/**
	 * 查询策略权限
	 * 
	 * @return
	 */
	@Action("/permission/query")
	public String query() {
		page = permissionService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 查看策略权限
	 * 
	 * @return
	 */
	@Action("/permission/view")
	public String view() {
		String id = StrutsUtils.getParameterTrim("id");
		if (NumberUtils.isDigits(id)) {
			data = permissionService.view(Integer.parseInt(id));
		}
		return "view";
	}

	/**
	 * 添加策略权限
	 * 
	 * @return
	 */
	@Action("/permission/add")
	public String add() {
		return "edit";
	}

	/**
	 * 修改策略权限
	 * 
	 * @return
	 */
	@Action("/permission/edit")
	public String edit() {
		String id = StrutsUtils.getParameterTrim("id");
		if (NumberUtils.isDigits(id)) {
			data = permissionService.view(Integer.parseInt(id));
		}
		return "edit";
	}

	/**
	 * 保存策略权限，包括添加、修改
	 * 
	 * @return
	 */
	@Action("/permission/save")
	public void save() {
		data = permissionService.save(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	/**
	 * 删除策略权限
	 * 
	 * @return
	 */
	@Action("/permission/delete")
	public void delete() {
		String id = StrutsUtils.getParameterTrim("id");
		if (NumberUtils.isDigits(id)) {
			data = permissionService.delete(Integer.parseInt(id));
			StrutsUtils.renderJson(data);
		}
	}

}
