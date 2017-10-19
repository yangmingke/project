package com.network.monitor.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.network.monitor.util.web.AuthorityUtils;

/**
 * 首页
 * 
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "success", location = "/WEB-INF/content/login.jsp"),
		@Result(name = "isLogin", type = "redirect", location = "/admin/index") ,
		@Result(name = "index",location = "/WEB-INF/content/index.jsp") })
public class IndexAction extends BaseAction {
	private static final long serialVersionUID = 4783786334291109411L;

	@Action("/index")
	public String index() {
		if (AuthorityUtils.isLogin()) {// 已经登录
			return "isLogin";
		}
		return SUCCESS;
	}

	@Action("/admin/index")
	public String adminIndex() {
		return "index";
	}
}
