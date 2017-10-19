package com.flypaas.admin.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * action类的基类
 * 
 * @author xiejiaan
 */
public class BaseAction extends ActionSupport {
	private static final long serialVersionUID = -6024322463400978622L;

	/**
	 * 分页信息
	 */
	protected PageContainer page;

	/**
	 * 返回数据
	 */
	protected Map<String, Object> data;

	/**
	 * 请求页面，用于返回刷新
	 */
	private String referer;

	public PageContainer getPage() {
		return page;
	}

	public void setPage(PageContainer page) {
		this.page = page;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public String getReferer() {
		referer = StrutsUtils.getReferer();
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}
}
