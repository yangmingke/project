package com.flypaas.admin.action.data;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.data.PageConfigService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 信息管理-管理员管理-前台页面配置
 * 
 * @author zenglb
 */
@Controller
@Scope("prototype")
@Results({
		@Result(name = "query", location = "/WEB-INF/content/data//pageConfig/query.jsp"),
		@Result(name = "edit", location = "/WEB-INF/content/data/pageConfig/edit.jsp") })
public class PageConfigAction extends BaseAction {
	private static final long serialVersionUID = 4296765223715779624L;
	private PageConfigService pageConfigService;

	@Resource
	public void setPageConfigService(PageConfigService pageConfigService) {
		this.pageConfigService = pageConfigService;
	}

	/**
	 * 查询
	 * 
	 * @return
	 */
	@Action("/pageConfig/query")
	public String query() {
		page = pageConfigService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 修改状态
	 * 
	 * @return
	 */
	@Action("/pageConfig/updateStatus")
	public void updateStatus() {
		data = pageConfigService.updateStatus(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	@Action("/pageConfig/save")
	public void save() {
		Map<String, String> params = StrutsUtils.getFormData();
		data = pageConfigService.save(params);
		StrutsUtils.renderJson(data);
	}

	@Action("/pageConfig/edit")
	public String edit() {
		String id = StrutsUtils.getParameterTrim("id");
		if (StringUtils.isNotBlank(id)) {
			data = pageConfigService.view(Integer.valueOf(id));
		} else {
			data = new HashMap<String, Object>();
			data.put("entity", new HashMap<String, Object>());
		}
		return "edit";
	}
}
