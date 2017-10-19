package com.flypaas.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flypaas.entity.Params;
import com.flypaas.entity.TbFlypaasUser;
import com.flypaas.servicecenter.ServiceCenter;

public abstract class BaseAction extends ServiceCenter  implements ServletResponseAware,ServletRequestAware {
	protected HttpServletResponse response;
	protected HttpServletRequest request ;
	protected HttpSession session;
	protected List<Params> paramsList ; 
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request ;
		session = request.getSession(true);
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		response.setCharacterEncoding("utf-8");
	}
	
	//获取登录的用户信息
	protected TbFlypaasUser getSessionUser(){
		TbFlypaasUser u = (TbFlypaasUser) session.getAttribute("user");
		return u ;
	}
	
	//记录session
	protected void recordSession(TbFlypaasUser user){
		session.setAttribute("user", user);
		System.out.println(user);
	}
	
	//清除用户信息
	protected void clearSession(){
		session.removeAttribute("user");
	}
	
	//打印后台输出编码
	protected void printMsg(String code){
		try {
			PrintWriter pw = response.getWriter();
			pw.write(code);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("--------------------------打印后台输出编码错误----------------------------");
			logger.error(e.getMessage());
		}
	}
	
	//获取数据字典配置信息(如：发布的rest地址)
	protected List<Params> getParams(String type){
		return paramsService.getParams(type);
	}
	
	/*******************************************get set***************************************************/
	
	public List<Params> getParamsList() {
		return paramsList;
	}
	public void setParamsList(List<Params> paramsList) {
		this.paramsList = paramsList;
	}
}
