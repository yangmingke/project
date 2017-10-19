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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutFilter implements Filter  {

	private static List<String> whiteList;

    static {
        whiteList = new ArrayList<String>();
        
        whiteList.add("/login.jsp");
        whiteList.add("/loginController/getVerify.action"); 		
        whiteList.add("/loginController/checkVerify.action"); 
        whiteList.add("/registerController/registerUser.action");				
        whiteList.add("/user");		
        whiteList.add("/registerController/sendValiCode.action");
        whiteList.add("/roleController/assignRole.action");
        whiteList.add("/user/verifyMail");			
        whiteList.add("/user/verifyMail.action");
        whiteList.add("/user/sendverifyMail");		
        whiteList.add("/user/sendverifyMail.action");
        whiteList.add("/user/sendverifyMailAjax"); 
        whiteList.add("/user/sendverifyMailAjax.action");
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
		Object u = session.getAttribute("userSession");
		if(null == u){
			response.sendRedirect("/login.jsp");
			return;
		}else{
			chain.doFilter(req, resp);
			return;
		}
		
	}
	
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
