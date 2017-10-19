package com.flypaas.tag;

import java.util.HashMap;
import java.util.Map;

import com.flypaas.entity.vo.PageContainer;

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
	/**
	 * 是否使用ajax请求
	 */
	private String ajax;
	/**
	 * ajax请求结果呈现容器divId
	 */
	private String divId;
	/**
	 * ajax返回页面的标示(用于判断是否请求到对的页面)
	 */
	private String dataDivId;
	@Override
	public String getTemplateFile() {
		return "page.ftl";
	}

	@Override
	public Map<String, Object> getTemplateParams() {
		if (page == null) {
			page = new PageContainer();
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", page);
		params.put("formId", formId);
		params.put("ajax", ajax==null?0:1);
		params.put("divId", divId==null?"":divId);
		params.put("dataDivId", dataDivId==null?"":divId);
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

	public String getAjax() {
		return ajax;
	}

	public void setAjax(String ajax) {
		this.ajax = ajax;
	}

	public String getDivId() {
		return divId;
	}

	public void setDivId(String divId) {
		this.divId = divId;
	}

	public String getDataDivId() {
		return dataDivId;
	}

	public void setDataDivId(String dataDivId) {
		this.dataDivId = dataDivId;
	}
	

}
