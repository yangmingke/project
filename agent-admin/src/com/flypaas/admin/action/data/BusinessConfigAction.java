package com.flypaas.admin.action.data;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.data.BusinessConfigService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 信息管理-管理员中心-业务配置
 * 
 * @author zenglb
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/data/businessConfig/query.jsp"),
		@Result(name = "edit", location = "/WEB-INF/content/data/businessConfig/edit.jsp") })
public class BusinessConfigAction extends BaseAction {
	private static final long serialVersionUID = 1890723099495273093L;
	@Autowired
	private BusinessConfigService businessConfigService;

	/**
	 * 查询
	 * 
	 * @return
	 */
	@Action("/businessConfig/query")
	public String query() {
		page = businessConfigService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 修改
	 * 
	 * @return
	 */
	@Action("/businessConfig/edit")
	public String edit() {
		String id = StrutsUtils.getParameterTrim("id");
		if (NumberUtils.isDigits(id)) {
			data = businessConfigService.view(Integer.parseInt(id));
		}
		return "edit";
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	@Action("/businessConfig/save")
	public void save() {
		data = businessConfigService.save(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	/**
	 * 修改状态：关闭、启用、删除
	 * 
	 * @return
	 */
	@Action("/businessConfig/updateStatus")
	public void updateStatus() {
		String id = StrutsUtils.getParameterTrim("id");
		String status = StrutsUtils.getParameterTrim("status");
		if (NumberUtils.isDigits(id) && NumberUtils.isDigits(status)) {
			data = businessConfigService.updateStatus(Integer.parseInt(id), Integer.parseInt(status));
			StrutsUtils.renderJson(data);
		}
	}

}
