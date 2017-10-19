package com.network.monitor.action.srinterface;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.network.monitor.action.BaseAction;
import com.network.monitor.service.srinterface.SRInterfaceService;
import com.network.monitor.util.web.StrutsUtils;

/**
 * SR节点管理
 *
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/srinterface/query.jsp")})
public class NodeInterfaceAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4975115193002551671L;
	
	@Autowired
	private SRInterfaceService srInterfaceService;//SR接口信息Service
	
	/**
	 * 分页查询SR节点接口信息
	 */
	@Action("/interface/query")
	public String query(){
		page = srInterfaceService.query(StrutsUtils.getFormData());
		return "query";
	}

}
