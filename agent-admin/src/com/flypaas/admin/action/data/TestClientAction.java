package com.flypaas.admin.action.data;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.data.ClientTestNumService;
import com.flypaas.admin.util.file.BizException;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 信息管理-测试号码
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/data/clientTestNum/query.jsp") })
public class TestClientAction extends BaseAction {
	private static final long serialVersionUID = 6512758908262400747L;
	protected ClientTestNumService clientTestNumService;

	@Resource
	public void setClientTestNumService(ClientTestNumService clientTestNumService) {
		this.clientTestNumService = clientTestNumService;
	}

	/**
	 * 查询client
	 * 
	 * @return
	 */
	@Action("/client/testNum")
	public String query() {
		String text = StrutsUtils.getRequest().getParameter("text");
		if (StringUtils.isNotBlank(text)) {
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("email", text);
			page = clientTestNumService.query(params);
		}
		return "query";
	}

	/**
	 * 绑定
	 * 
	 * @return
	 */
	@Action("/client/bindTestNum")
	public void bindTestNum() {
		HttpServletRequest request = StrutsUtils.getRequest();
		String nbr_id = request.getParameter("nbr_id");
		String sid = request.getParameter("sid");
		String app_sid = request.getParameter("app_sid");
		String client_number = request.getParameter("client_number");
		String test_id = request.getParameter("test_id");
		if (StringUtils.isNotBlank(sid) && StringUtils.isNotBlank(app_sid) && StringUtils.isNotBlank(client_number)
				&& StringUtils.isNotBlank(test_id)) {
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("nbr_id", nbr_id);
			params.put("sid", sid);
			params.put("app_sid", app_sid);
			params.put("client_number", client_number);
			params.put("test_id", test_id);
			try {
				data = clientTestNumService.bindTestNum(params);
			} catch (BizException e) {
				data = new HashMap<String, Object>();
				data.put("code", e.getCode());
				data.put("msg", e.getMessage());
			}

		} else {
			data = new HashMap<String, Object>();
			data.put("code", "-1");
			data.put("msg", "参数错误");
		}
		StrutsUtils.renderJson(data);
	}
}
