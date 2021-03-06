package com.flypaas.admin.action.data;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.data.AppService;
import com.flypaas.admin.util.ConfigUtils;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 信息管理-应用管理
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({
	@Result(name = "query", location = "/WEB-INF/content/data/app/query.jsp") ,
	@Result(name = "view", location = "/WEB-INF/content/data/app/view.jsp"),
	@Result(name = "update", location = "/WEB-INF/content/data/app/update.jsp")
})
public class AppAction extends BaseAction {
	private static final long serialVersionUID = 3921296232478999108L;
	@Autowired
	private AppService appService;

	/**
	 * 查询应用
	 * 
	 * @return
	 */
	@Action("/app/query")
	public String query() {
		page = appService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 修改应用状态：强制下线
	 * 
	 * @return
	 */
	@Action("/app/updateStatus")
	public void updateStatus() {
		data = appService.updateStatus(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}
	
	@Action("/app/view") 
	public String view() {
		StrutsUtils.setAttribute("RING_BASE", ConfigUtils.ring_base_path);
		String appSid = StrutsUtils.getParameterTrim("app_sid");
		if (StringUtils.isNotBlank(appSid)) {
			data = appService.view(appSid);
		}
		return "view";
	}
	
	@Action("/app/update") 
	public String update() {
		StrutsUtils.setAttribute("RING_BASE", ConfigUtils.ring_base_path);
		String appSid = StrutsUtils.getParameterTrim("app_sid");
		try {
			appSid = new String(appSid.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (StringUtils.isNotBlank(appSid)) {
			data = appService.view(appSid);
		}
		return "update";
	}
	
	@Action("/app/updateApp") 
	public String updateApp() {
		StrutsUtils.setAttribute("RING_BASE", ConfigUtils.ring_base_path);
		String app_sid = StrutsUtils.getParameterTrim("app_sid");
		String max_hop_num = StrutsUtils.getParameterTrim("max_hop_num");
		String route_num = StrutsUtils.getParameterTrim("route_num");
		String node_max_price = StrutsUtils.getParameterTrim("node_max_price");
		String ver_can = StrutsUtils.getParameterTrim("ver_can");
		String route_policy = StrutsUtils.getParameterTrim("route_policy");
		Map para = new HashMap();
		para.put("app_sid", app_sid);
		para.put("max_hop_num", max_hop_num);
		para.put("route_num", route_num);
		para.put("node_max_price", node_max_price);
		para.put("ver_can", ver_can);
		para.put("route_policy", route_policy);
		appService.update(para);
		return view();
	}

	/**
	 * 添加应用的品牌
	 * 
	 * @return
	 */
	@Action("/app/saveBrand")
	public void saveBrand() {
		data = appService.saveBrand(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

}
