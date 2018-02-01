package com.flypaas.admin.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.filter.StrutsPrepareFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义的StrutsPrepareFilter，解决UEditor上传文件失败的问题
 * 
 * @author xiejiaan
 */
public class FlypaasStrutsPrepareFilter extends StrutsPrepareFilter {
	private static final Logger logger = LoggerFactory.getLogger(FlypaasStrutsPrepareFilter.class);

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String reqUrl = request.getServletPath();
		if (reqUrl.startsWith("/ueditor")) {
			logger.debug("过滤UEditor请求：reqUrl=" + reqUrl);
			chain.doFilter(req, res);
		} else {
			super.doFilter(req, res, chain);
		}
	}

}
