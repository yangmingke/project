package com.network.monitor.action;

import org.apache.struts2.convention.annotation.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.network.monitor.service.CommonService;
import com.network.monitor.util.CheckCodeUtil;
import com.network.monitor.util.web.StrutsUtils;

/**
 * 登录
 * 
 */
@Controller
@Scope("prototype")
public class LoginAction extends BaseAction {
	private static final long serialVersionUID = 3830209747022145354L;
	private static final Logger logger = LoggerFactory.getLogger(LoginAction.class);
	@Autowired
	private CommonService commonService;

	@Action("/login")
	public void login() {
		// if(!CheckCodeUtil.check(StrutsUtils.getRequest())){
		// data = new HashMap<String, Object>();
		// data.put("result", "fail");
		// data.put("msg", "验证码错误");
		// StrutsUtils.renderJson(data);
		// return ;
		// }
		String username = StrutsUtils.getParameterTrim("username");
		String password = StrutsUtils.getParameterTrim("password");

		data = commonService.login(username, password);

		String url = StrutsUtils.getContextPath() + "/index";// 登录成功后跳转的路径
		data.put("url", url);
		StrutsUtils.renderJson(data);
	}

	@Action("/checkCode")
	public void checkCode() {
		try {
			CheckCodeUtil.makeCheckCode(StrutsUtils.getRequest(), StrutsUtils.getResponse());
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		}
	}
}
