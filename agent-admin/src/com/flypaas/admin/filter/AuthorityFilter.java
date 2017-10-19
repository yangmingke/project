package com.flypaas.admin.filter;

import java.io.IOException;
import java.util.Map;

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

import com.flypaas.admin.service.data.AuthorityService;
import com.flypaas.admin.util.ConfigUtils;
import com.flypaas.admin.util.web.AuthorityUtils;

/**
 * 权限控制filter
 * 配置过滤器拦截权限实例详解
 * ***重要
 * @author xiejiaan
 */
@Component

//过滤器实现方法 ① 创建一个实现了javax.servlet.Filter接口的类。
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

		if (AuthorityUtils.isLogin(request)) {
			int roleId = AuthorityUtils.getLoginRoleId(request);
			Map<String, Object> data = authorityService.isAuthority(roleId, reqUrl);
			if (data.get("result").toString().equals("1")) {// 是否有访问权限
				request.setAttribute("select_menu", data.get("select_menu"));// 当前选中的菜单，用于MenuAction.sideMenu()、main.jsp
				return true;
			}
		}
		logger.debug("没有访问权限：reqUrl={}", reqUrl);
		return false;
	}

	@Override
	//③重写方法doFilter(ServletRequest,ServletResponse,FilterChain)  
	public void doFilter(ServletRequest paramServletRequest, ServletResponse paramServletResponse,
			FilterChain paramFilterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) paramServletRequest;
		HttpServletResponse response = (HttpServletResponse) paramServletResponse;

		
	//④在doFilter()方法的最后，使用FilterChain参数的doFilter()方法将请求和响应后传。
		if (check(request)) {
			paramFilterChain.doFilter(paramServletRequest, paramServletResponse);
			return;
		}
		response.sendRedirect(request.getContextPath() + "/common/403.jsp");
		return;
	}
	
	//⑤对响应的Servlet程序和JSP页面注册过滤器，在部署描述文件(web.xml)中使用<filter-apping>和<filter>元素对过滤器进行配置。
	  

	@Override
   //②重写init(FilterConfig) 方法 
	public void init(FilterConfig paramFilterConfig) throws ServletException {
		ServletContext servletContext = paramFilterConfig.getServletContext();
		servletContext.setAttribute("ctx", servletContext.getContextPath());
		logger.info("set ctx = " + servletContext.getContextPath());
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
