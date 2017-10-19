package com.flypaas.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.flypaas.entity.TbFlypaasUser;
import com.flypaas.utils.StrUtil;
import com.flypaas.utils.SysConfig;
public class LogoutFilter implements Filter  {

	private static List<String> whiteList;

    static {
        whiteList = new ArrayList<String>();
        whiteList.add("/login.jsp"); 				//登录
        whiteList.add("/front/log_out.jsp"); 				//注销
        whiteList.add("/user/login"); 				//登录
        whiteList.add("/user/accountLogin"); 	//跨域登录
        whiteList.add("/user/toLogin"); 		
        whiteList.add("/user/toLogin.action"); 		
        whiteList.add("/user/userAdd");				//注册
        whiteList.add("/user/userAdd.action");		
        whiteList.add("/user/sendResetPwd");		//发送重置秘密邮件
        whiteList.add("/user/sendResetPwd.action");
        whiteList.add("/user/verifyMail");			//邮件激活
        whiteList.add("/user/verifyMail.action");
        whiteList.add("/user/sendverifyMail");		//发送激活邮件
        whiteList.add("/user/sendverifyMail.action");
        whiteList.add("/user/sendverifyMailAjax");  //发送激活邮件（ajax）
        whiteList.add("/user/sendverifyMailAjax.action");
        whiteList.add("/pay/payResult");    		//充值回调
        whiteList.add("/pay/payResult.action");
        whiteList.add("/pay/aliPayResult");    		//aliPay充值回调
        whiteList.add("/pay/aliPayResult.action");
        whiteList.add("/user/ckMobileAndmailEnable"); //判断邮箱是否存在
        whiteList.add("/user/toExtEmail");
        whiteList.add("/user/toExtEmail.action");
        whiteList.add("/user/verifyMailOpt.action");
        whiteList.add("/user/verifyMailOpt");
        whiteList.add("/user/resetPwd");
        whiteList.add("/user/resetPwd.action");
        whiteList.add("/user/userResetPwd.action");
        whiteList.add("/user/userResetPwd");
        whiteList.add("/user/forgetPwd");
        whiteList.add("/user/forgetPwd.action");
        whiteList.add("/ext/closeAcct.action");
        whiteList.add("/ext/closeAcct");
        whiteList.add("/ext/updateUser.action");
        whiteList.add("/ext/updateUser");
        whiteList.add("/user/feeRate.action");
        whiteList.add("/user/feeRate");
        whiteList.add("/user/toSign");
        whiteList.add("/user/toSign.action");
        whiteList.add("/user/callBack");
        whiteList.add("/user/callBack.action");
        whiteList.add("/user/ckUserName");
        whiteList.add("/user/ckUserName.action");
        whiteList.add("/coupon/scratch");
        whiteList.add("/coupon/scratch.action");
        whiteList.add("/coupon/couponDate");
        whiteList.add("/coupon/couponDate.action");
        whiteList.add("/user/resetPwdTiming");
        whiteList.add("/user/resetPwdTiming.action");
        whiteList.add("/user/sendUpdatePasswordMail");//发送修改密码的邮件
        whiteList.add("/pay/extrecharge");
        whiteList.add("/pay/extrecharge.action");
        whiteList.add("/user/addEmail");
        whiteList.add("/user/addAgentEmail");
        whiteList.add("/user/addAgentEmail.action");
        whiteList.add("/user/addEmail.action");
        whiteList.add("/user/vmailExt");
        whiteList.add("/user/vmailExt.action");
        whiteList.add("/user/toExtMailServer");
        whiteList.add("/user/toExtMailServer.action");
        whiteList.add("/checkcode/smsCheckCode");
        whiteList.add("/checkcode/smsCheckCode.action");
        whiteList.add("/checkcode/voiceCheckCode");
        whiteList.add("/checkcode/voiceCheckCode.action");
    }

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse  response = (HttpServletResponse ) resp;
        String requstURI = request.getRequestURI();
        
        // 白名单不验证登陆
        if (whiteList.contains(requstURI)) {
            chain.doFilter(req, resp);
            return;
        }
        
        HttpSession session = request.getSession();
		Object u = session.getAttribute("user");
		if(u==null){
			String return_url = StrUtil.getCookiValue(request, SysConfig.getInstance().getProperty("return_url"));
			response.sendRedirect(StringUtils.isEmpty(return_url) ? "/noLogin" : return_url);
			return;
		}else{
			TbFlypaasUser user = (TbFlypaasUser)u;
			if(StrUtil.isEmpty(user.getMobile())){
				if(!requstURI.contains("/user/logout")&&!requstURI.contains("/checkcode/voiceCheckCode")&&!requstURI.contains("/checkcode/smsCheckCode")&&!requstURI.contains("/user/verifyMobile")){
					response.sendRedirect("/bindPhone");
					return;
				}
			}
			chain.doFilter(req, resp);
			return;
		}
		
	}
	
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
