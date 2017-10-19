package com.flypaas.admin.action.data;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.data.AgentService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 信息管理-代理商管理
 * 
 * @author yangmingke
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/data/agent/query.jsp"),
		   @Result(name = "view", location = "/WEB-INF/content/data/agent/view.jsp")
		 })
public class AgentAction extends BaseAction {
	private static final long serialVersionUID = -6398668354512197029L;
	@Autowired
	private AgentService agentService;

	/**
	 * 查询代理商
	 * 
	 * @return
	 */
	@Action("/agent/query")
	public String query() {
		page = agentService.query(StrutsUtils.getFormData());
		return "query";
	}
	
	/**
	 * 修改代理商状态：关闭、重新激活
	 * 
	 * @return
	 */
	@Action("/agent/updateStatus")
	public void updateStatus() {
		data = agentService.updateStatus(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}
	
	/**
	 * 设置或取消为代理商
	 * 
	 * @return
	 */
	@Action("/agent/setProxy")
	public void setProxy() {
		data = agentService.setProxy(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}
	/**
	 * 修改代理商登录url
	 */
	@Action("/agent/editUrl")
	public void editUrl() {
		data = agentService.editUrl(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}
	

	/**
	 * 查看代理商资料
	 * 
	 * @return
	 */
	@Action("/agent/view")
	public String view() {
		getDeveloperInfo();
		return "view";
	}

	/**
	 * 修改代理商资料页面
	 * 
	 * @return
	 */
	@Action("/agent/edit")
	public String edit() {
		getDeveloperInfo();
		return "edit";
	}

	/**
	 * 获取代理商资料
	 * 
	 * @return
	 */
	private void getDeveloperInfo() {
		String sid = StrutsUtils.getParameterTrim("sid");
		if (StringUtils.isNotBlank(sid)) {
			data = agentService.getDeveloper(sid);
		}
	}

	/**
	 * 保存代理商资料
	 * 
	 * @return
	 */
	@Action("/agent/save")
	public void save() {
		data = agentService.saveDeveloper(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}
}
