package com.flypaas.admin.action;

import java.util.HashMap;

import org.apache.struts2.convention.annotation.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.service.CommonService;
import com.flypaas.admin.util.CheckCodeUtil;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 登录
 * 
 * @author xiejiaan
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
		if(!CheckCodeUtil.check(StrutsUtils.getRequest())){
			data = new HashMap<String, Object>();
			data.put("result", "fail");
			data.put("msg", "验证码错误");
			StrutsUtils.renderJson(data);
			return ;
		}
		String username = StrutsUtils.getParameterTrim("username");
		String password = StrutsUtils.getParameterTrim("password");

		data = commonService.login(username, password);

		String url = StrutsUtils.getContextPath() + "/admin/view";// 登录成功后跳转的路径
		data.put("url", url);
		StrutsUtils.renderJson(data);
	}
	@Action("/checkCode")
	public void checkCode(){
		try {
			CheckCodeUtil.makeCheckCode(StrutsUtils.getRequest(), StrutsUtils.getResponse());
		} catch (Throwable e) {
			logger.error(e.getMessage(),e);
		}
	}
}
