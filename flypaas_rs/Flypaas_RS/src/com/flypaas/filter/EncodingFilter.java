package com.flypaas.filter;  
import java.io.IOException;  
import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.flypaas.controller.city.AreaController;

@SuppressWarnings("serial")
public class EncodingFilter extends HttpServlet implements Filter { 
	public static Logger logger = Logger.getLogger(AreaController.class);
    private String encoding = null;  
    
    public void init(FilterConfig filterConfig) throws ServletException {  
    	logger.info("初始化过滤器..................");
        encoding = filterConfig.getInitParameter("encoding");  
    }  
    
    public void doFilter(ServletRequest request, ServletResponse response,  
            FilterChain chain) throws IOException, ServletException { 
    
        request.setCharacterEncoding(encoding);  
        
        response.setCharacterEncoding(encoding); 
        
        chain.doFilter(request, response);  
    }  
}  