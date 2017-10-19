package com.flypaas.admin.action.data;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.data.TaskService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 信息管理-管理员中心-任务管理
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/data/task/query.jsp"),
		@Result(name = "view", location = "/WEB-INF/content/data/task/view.jsp"),
		@Result(name = "edit", location = "/WEB-INF/content/data/task/edit.jsp") })
public class TaskAction extends BaseAction {
	private static final long serialVersionUID = -1013543974536933267L;
	@Autowired
	private TaskService taskService;

	/**
	 * 查询任务
	 * 
	 * @return
	 */
	@Action("/task/query")
	public String query() {
		page = taskService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 查看任务
	 * 
	 * @return
	 */
	@Action("/task/view")
	public String view() {
		String taskId = StrutsUtils.getParameterTrim("task_id");
		if (NumberUtils.isDigits(taskId)) {
			data = taskService.view(Integer.parseInt(taskId));
		}
		return "view";
	}

	/**
	 * 添加任务
	 * 
	 * @return
	 */
	@Action("/task/add")
	public String add() {
		return "edit";
	}

	/**
	 * 修改任务
	 * 
	 * @return
	 */
	@Action("/task/edit")
	public String edit() {
		String taskId = StrutsUtils.getParameterTrim("task_id");
		if (NumberUtils.isDigits(taskId)) {
			data = taskService.view(Integer.parseInt(taskId));
		}
		return "edit";
	}

	/**
	 * 保存任务，包括添加、修改
	 * 
	 * @return
	 */
	@Action("/task/save")
	public void save() {
		data = taskService.save(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	/**
	 * 修改任务状态：关闭、启用、删除
	 * 
	 * @return
	 */
	@Action("/task/updateStatus")
	public void updateStatus() {
		String taskId = StrutsUtils.getParameterTrim("task_id");
		String status = StrutsUtils.getParameterTrim("status");
		if (NumberUtils.isDigits(taskId) && NumberUtils.isDigits(status)) {
			data = taskService.updateStatus(Integer.parseInt(taskId), Integer.parseInt(status));
			StrutsUtils.renderJson(data);
		}
	}

}
