package com.flypaas.admin.action.data;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.data.NewsService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 信息管理-管理员中心-新闻管理
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/data/news/query.jsp"),
		@Result(name = "view", location = "/WEB-INF/content/data/news/view.jsp"),
		@Result(name = "edit", location = "/WEB-INF/content/data/news/edit.jsp") })
public class NewsAction extends BaseAction {
	private static final long serialVersionUID = 1890723099495273093L;
	@Autowired
	private NewsService newsService;

	/**
	 * 查询新闻
	 * 
	 * @return
	 */
	@Action("/news/query")
	public String query() {
		page = newsService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 查看新闻
	 * 
	 * @return
	 */
	@Action("/news/view")
	public String view() {
		String newsId = StrutsUtils.getParameterTrim("news_id");
		if (NumberUtils.isDigits(newsId)) {
			data = newsService.view(Integer.parseInt(newsId));
		}
		return "view";
	}

	/**
	 * 添加新闻
	 * 
	 * @return
	 */
	@Action("/news/add")
	public String add() {
		return "edit";
	}

	/**
	 * 修改新闻
	 * 
	 * @return
	 */
	@Action("/news/edit")
	public String edit() {
		String newsId = StrutsUtils.getParameterTrim("news_id");
		if (NumberUtils.isDigits(newsId)) {
			data = newsService.view(Integer.parseInt(newsId));
		}
		return "edit";
	}

	/**
	 * 保存新闻，包括添加、修改
	 * 
	 * @return
	 */
	@Action("/news/save")
	public void save() {
		data = newsService.save(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	/**
	 * 修改新闻状态：关闭、启用、删除
	 * 
	 * @return
	 */
	@Action("/news/updateStatus")
	public void updateStatus() {
		String newsId = StrutsUtils.getParameterTrim("news_id");
		String status = StrutsUtils.getParameterTrim("status");
		if (NumberUtils.isDigits(newsId) && NumberUtils.isDigits(status)) {
			data = newsService.updateStatus(Integer.parseInt(newsId), Integer.parseInt(status));
			StrutsUtils.renderJson(data);
		}
	}

}
