package com.network.monitor.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.network.monitor.service.admin.AuthorityService;
import com.network.monitor.util.ConfigUtils;
import com.network.monitor.util.StrUtils;
import com.network.monitor.util.web.AuthorityUtils;

/**
 * 权限控制filter
 * 
 */
@Component
public class AuthorityFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(AuthorityFilter.class);
	private String[] excludeEqualUrls; // 等于此路径不需要控制
	private String[] excludeStartUrls; // 以此路径开始不需要控制
	@Autowired
	private AuthorityService authorityService;

	/**
	 * 检查是否有访问权限
	 * 
	 * @param request
	 * @return
	 */
	private boolean check(HttpServletRequest request) {
		if (ConfigUtils.is_auto_login && !AuthorityUtils.isLogin(request)) {
			AuthorityUtils.setAutoLoginUser(request);
			logger.debug("自动登录");
		}
		String reqUrl = request.getServletPath();

		if (reqUrl.indexOf('.') > -1) {// url包含.且不是.action，直接跳过
			if (reqUrl.endsWith(".action")) {
				reqUrl = reqUrl.substring(0, reqUrl.length() - 7);
			} else {
				return true;
			}
		}
		for (String excludeUrl : excludeStartUrls) { // 以此路径开始不需要控制，直接跳过
			if (reqUrl.startsWith(excludeUrl)) {
				return true;
			}
		}
		for (String excludeUrl : excludeEqualUrls) { // 等于此路径不需要控制，直接跳过
			if (reqUrl.equals(excludeUrl)) {
				return true;
			}
		}

		if (!AuthorityUtils.isLogin(request)) {//未登录
			return false;
		}
		return true;
	}
	
	private boolean checkSql(Object str){
		if(str==null || str.equals("")){
			return false;
		}
        if (StrUtils.sqlValidate(str.toString())) {
            return true;
        } 
        return false;
	}

	@Override
	public void doFilter(ServletRequest paramServletRequest, ServletResponse paramServletResponse,
			FilterChain paramFilterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) paramServletRequest;
		HttpServletResponse response = (HttpServletResponse) paramServletResponse;

		if (check(request)) {
			Object str = request.getParameter("orderName");
			if(checkSql(str)){
				response.sendRedirect(request.getContextPath() + "/common/404.jsp");
				return;
			}
			
			paramFilterChain.doFilter(paramServletRequest, paramServletResponse);
			return;
		}
		paramFilterChain.doFilter(paramServletRequest, paramServletResponse);
//		response.sendRedirect(request.getContextPath() + "/index.action");
		return;
	}

	@Override
	public void init(FilterConfig paramFilterConfig) throws ServletException {
		ServletContext servletContext = paramFilterConfig.getServletContext();
		servletContext.setAttribute("ctx", servletContext.getContextPath());
		logger.info("set ctx = " +  servletContext.getContextPath());

		String exclude = paramFilterConfig.getInitParameter("excludeEqualUrls");
		if (exclude != null) {
			excludeEqualUrls = exclude.split(",");
		}

		exclude = paramFilterConfig.getInitParameter("excludeStartUrls");
		if (exclude != null) {
			excludeStartUrls = exclude.split(",");
		}
	}

	@Override
	public void destroy() {
	}
}
