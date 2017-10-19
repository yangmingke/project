package com.network.monitor.tag;

import java.util.HashMap;
import java.util.Map;

import com.network.monitor.model.PageContainer;
import com.network.monitor.service.TagService;

/**
 * 分页标签
 * 
 * @author xiejiaan
 */
public class PageTag extends BaseTag {
	private static final long serialVersionUID = 7941249268861678376L;

	private PageContainer page;
	/**
	 * 查询的表单id
	 */
	private String formId;

	@Override
	public String getTemplateFile() {
		return "page.ftl";
	}

	@Override
	public Map<String, Object> getTemplateParams(TagService tagService) {
		if (page == null) {
			page = new PageContainer();
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", page);
		params.put("formId", formId);
		return params;
	}

	public PageContainer getPage() {
		return page;
	}

	public void setPage(PageContainer page) {
		this.page = page;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

}
