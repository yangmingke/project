package com.flypaas.admin.action.cps;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.cps.ServerService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 策略管理-策略权限配置-服务器地址
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/cps/server/query.jsp"),
		@Result(name = "view", location = "/WEB-INF/content/cps/server/view.jsp"),
		@Result(name = "edit", location = "/WEB-INF/content/cps/server/edit.jsp") })
public class ServerAction extends BaseAction {
	private static final long serialVersionUID = 8988961813310378080L;
	@Autowired
	private ServerService serverService;

	/**
	 * 查询服务器地址
	 * 
	 * @return
	 */
	@Action("/server/query")
	public String query() {
		page = serverService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 查看服务器地址
	 * 
	 * @return
	 */
	@Action("/server/view")
	public String view() {
		String id = StrutsUtils.getParameterTrim("id");
		if (NumberUtils.isDigits(id)) {
			data = serverService.view(StrutsUtils.getFormData());
		}
		return "view";
	}

	/**
	 * 添加服务器地址
	 * 
	 * @return
	 */
	@Action("/server/add")
	public String add() {
		return "edit";
	}

	/**
	 * 修改服务器地址
	 * 
	 * @return
	 */
	@Action("/server/edit")
	public String edit() {
		String id = StrutsUtils.getParameterTrim("id");
		if (NumberUtils.isDigits(id)) {
			data = serverService.view(StrutsUtils.getFormData());
		}
		return "edit";
	}

	/**
	 * 保存服务器地址，包括添加、修改
	 * 
	 * @return
	 */
	@Action("/server/save")
	public void save() {
		data = serverService.save(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	/**
	 * 删除服务器地址
	 * 
	 * @return
	 */
	@Action("/server/delete")
	public void delete() {
		String id = StrutsUtils.getParameterTrim("id");
		if (NumberUtils.isDigits(id)) {
			data = serverService.delete(StrutsUtils.getFormData());
			StrutsUtils.renderJson(data);
		}
	}

}
