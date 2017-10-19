package com.flypaas.admin.action.data;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.data.KeywordService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 信息管理-管理员中心-敏感字管理
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/data/keyword/query.jsp"),
		@Result(name = "edit", location = "/WEB-INF/content/data/keyword/edit.jsp") })
public class KeywordAction extends BaseAction {
	private static final long serialVersionUID = -2171822253762251184L;
	@Autowired
	private KeywordService keywordService;

	/**
	 * 查询敏感字
	 * 
	 * @return
	 */
	@Action("/keyword/query")
	public String query() {
		page = keywordService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 添加敏感字
	 * 
	 * @return
	 */
	@Action("/keyword/add")
	public String add() {
		return "edit";
	}

	/**
	 * 修改敏感字
	 * 
	 * @return
	 */
	@Action("/keyword/edit")
	public String edit() {
		String wordId = StrutsUtils.getParameterTrim("word_id");
		if (NumberUtils.isDigits(wordId)) {
			data = keywordService.view(Integer.parseInt(wordId));
		}
		return "edit";
	}

	/**
	 * 保存敏感字，包括添加、修改
	 * 
	 * @return
	 */
	@Action("/keyword/save")
	public void save() {
		data = keywordService.save(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}
	
	/**
	 * 删除敏感字
	 * 
	 * @return
	 */
	@Action("/keyword/delete")
	public void delete() {
		data = keywordService.delete(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

}
