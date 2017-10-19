package com.flypaas.admin.action.data;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.data.ChannelService;
import com.flypaas.admin.util.encrypt.EncryptUtils;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 信息管理-渠道管理
 * 
 * @author zenglb
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/data/channel/query.jsp"),
		@Result(name = "edit", location = "/WEB-INF/content/data/channel/edit.jsp"),
		@Result(name = "url", location = "/WEB-INF/content/data/channel/url.jsp") })
public class ChannelAction extends BaseAction {
	private static final long serialVersionUID = 3921296232478999108L;
	@Autowired
	private ChannelService channelService;
	String default_url = "http://www.flypaas.com/user/toSign";
	String fr_url = "http://www.flypaas.com/fr?";

	/**
	 * 查询
	 * 
	 * @return
	 */
	@Action("/channel/query")
	public String query() {
		page = channelService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 修改状态
	 * 
	 * @return
	 */
	@Action("/channel/updateStatus")
	public void updateStatus() {
		data = channelService.updateStatus(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	@Action("/channel/save")
	public void save() {
		data = channelService.save(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	@Action("/channel/edit")
	public String edit() {
		String id = StrutsUtils.getParameterTrim("id");
		if (StringUtils.isNotBlank(id)) {
			data = channelService.view(Integer.valueOf(id));
		}
		return "edit";
	}

	@Action("/channel/url")
	public String url() {
		String id = StrutsUtils.getParameterTrim("id");
		String url_des = getUrl(id, default_url);
		data = new HashMap<String, Object>();
		data.put("url", default_url);
		data.put("url_des", url_des);
		return "url";
	}

	private String getUrl(String id, String url) {
		url = url.replace("http://www.flypaas.com", "");
		String p = "cid=" + id + "&fr=" + EncryptUtils.encodeBase64(url);
		return fr_url + p;
	}

	@Action("/channel/createdUrl")
	public void createdUrl() {
		String id = StrutsUtils.getParameterTrim("id");
		String url = StrutsUtils.getParameterTrim("url");
		data = new HashMap<String, Object>();
		String url_des = getUrl(id, url);
		data.put("url_des", url_des);
		StrutsUtils.renderJson(data);
	}
}
