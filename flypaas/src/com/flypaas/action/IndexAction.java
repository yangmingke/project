package com.flypaas.action;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
@Results({
	//@Result(name="indexSuc",location="/front/index.jsp"),
	@Result(name="indexSuc",location="/front/phone_or_pc.jsp"),
	@Result(name="noLogin",location="/front/no_login.jsp"),
	@Result(name="pcIndex",location="/front/index_new2.jsp"),
	@Result(name="phoneIndex",location="/front/index_phone.jsp"),
	@Result(name="loginView",location="/front/login.jsp"),
	@Result(name="bindPhoneSuc",location="/front/verifySuc.jsp"),
	@Result(name="items",location="/front/items.jsp")
})
public class IndexAction {
	/*---------------------------------------------跳转首页--------------------------------*/
	@Action("/index")
	public String index(){
		return "indexSuc";
	}
	
	@Action("/noLogin")
	public String logOut(){
		return "noLogin";
	}
	
	/*---------------------------------------------跳转首页--------------------------------*/
	@Action("/pcIndex")
	public String pcIndex(){
		ActionContext ctx = ActionContext.getContext();
	    HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE); 
	    response.setStatus(301);
	    response.addHeader("Location", "/front/index_new2.jsp");
		return "pcIndex";
	}
	
	/*---------------------------------------------跳转首页--------------------------------*/
	@Action("/phoneIndex")
	public String phoneIndex(){
		ActionContext ctx = ActionContext.getContext();
	    HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE); 
		response.setStatus(301);
		response.addHeader("Location", "/front/index_phone.jsp");
		return "phoneIndex";
	}
	
	/*---------------------------------------------跳转首页--------------------------------*/
	@Action("/login")
	public String login(){
		return "loginView";
	}
	
	/*---------------------------------------------跳转首页--------------------------------*/
	@Action("/bindPhone")
	public String bindPhone(){
		return "bindPhoneSuc";
	}
	
	/*---------------------------------------------跳转服务条款--------------------------------*/
	@Action("/items")
	public String items(){
		return "items";
	}
}
