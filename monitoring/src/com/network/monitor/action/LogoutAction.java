package com.network.monitor.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.network.monitor.util.web.AuthorityUtils;

/**
 * 退出登录
 * 
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "success", type = "redirectAction", location = "index") })
public class LogoutAction extends BaseAction {
	private static final long serialVersionUID = 2400270752750424789L;
	private static final Logger LOGGER = LoggerFactory.getLogger(LogoutAction.class);

	@Action("/logout")
	public String logout() {
		AuthorityUtils.setLogoutUser();
		LOGGER.debug("退出登录成功");
		return SUCCESS;
	}
}
