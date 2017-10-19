package com.flypaas.admin.action.cps;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.cps.PermissionFilterService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 策略管理-策略权限配置-策略权限过滤
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/cps/permissionFilter/query.jsp"),
		@Result(name = "view", location = "/WEB-INF/content/cps/permissionFilter/view.jsp"),
		@Result(name = "edit", location = "/WEB-INF/content/cps/permissionFilter/edit.jsp") })
public class PermissionFilterAction extends BaseAction {
	private static final long serialVersionUID = -4006131822125853331L;
	@Autowired
	private PermissionFilterService permissionFilterService;

	/**
	 * 查询策略权限过滤
	 * 
	 * @return
	 */
	@Action("/permissionFilter/query")
	public String query() {
		page = permissionFilterService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 查看策略权限过滤
	 * 
	 * @return
	 */
	@Action("/permissionFilter/view")
	public String view() {
		String id = StrutsUtils.getParameterTrim("id");
		if (NumberUtils.isDigits(id)) {
			data = permissionFilterService.view(Integer.parseInt(id));
		}
		return "view";
	}

	/**
	 * 添加策略权限过滤
	 * 
	 * @return
	 */
	@Action("/permissionFilter/add")
	public String add() {
		return "edit";
	}

	/**
	 * 修改策略权限过滤
	 * 
	 * @return
	 */
	@Action("/permissionFilter/edit")
	public String edit() {
		String id = StrutsUtils.getParameterTrim("id");
		if (NumberUtils.isDigits(id)) {
			data = permissionFilterService.view(Integer.parseInt(id));
		}
		return "edit";
	}

	/**
	 * 保存策略权限过滤，包括添加、修改
	 * 
	 * @return
	 */
	@Action("/permissionFilter/save")
	public void save() {
		data = permissionFilterService.save(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	/**
	 * 删除策略权限过滤
	 * 
	 * @return
	 */
	@Action("/permissionFilter/delete")
	public void delete() {
		String id = StrutsUtils.getParameterTrim("id");
		if (NumberUtils.isDigits(id)) {
			data = permissionFilterService.delete(Integer.parseInt(id));
			StrutsUtils.renderJson(data);
		}
	}

}
