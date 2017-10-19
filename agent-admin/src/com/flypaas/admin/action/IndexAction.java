package com.flypaas.admin.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.util.web.AuthorityUtils;

/**
 * 首页
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "success", location = "/WEB-INF/content/index.jsp"),
		@Result(name = "isLogin", type = "redirect", location = "/admin/view") })
public class IndexAction extends BaseAction {
	private static final long serialVersionUID = 4783786334291109411L;

	@Action("/index")
	public String index() {
		if (AuthorityUtils.isLogin()) {// 已经登录
			return "isLogin";
		}
		return SUCCESS;
	}

}
