package com.ucpaas.commonservice.aop;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ucpaas.commonservice.config.Properties;
import com.ucpaas.commonservice.constant.ErrorCode;
import com.ucpaas.commonservice.util.IpUtils;
import com.ucpaas.commonservice.util.JsonObjectUtil;
import com.ucpaas.commonservice.util.RedisBaseUtil;


@Aspect
@Component
public class MyInterceptor {
	private Logger logger = LoggerFactory.getLogger(MyInterceptor.class);

	@Autowired
	private RedisBaseUtil<String, String> redisBaseUtil;
	
	@Resource(name = "properties")
	private Properties properties;
	
	@Pointcut("execution(* com.ucpaas.commonservice.controller.*.*(..))")
	public void method() {
		
	}

	@Around("method()")
	public Object doAccessCheck(ProceedingJoinPoint pjp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		// 获取ip地址
		String ip = IpUtils.getIPAddr(request);
		logger.info("【获取到的ip地址为】,ip={}", ip);
		String name=properties.getIp_redis_key()+"_"+ip;
        String value=redisBaseUtil.getFromCache(name);
		if (!StringUtils.isBlank(value)) {
			logger.info("【ip鉴权通过】，执行正常操作");
			return pjp.proceed();
		} else {
			logger.info("【ip鉴权失败】，操作终止");
			send();
			return null;
		}
	}

	public void send() {
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getResponse();
		response.setContentType("text/html; charset=utf-8"); 
		response.setCharacterEncoding("utf-8");
		PrintWriter pt = null;
		try {
			pt = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("respCode",ErrorCode.C200006);
		map.put("desc","此ip不在授权范围内");
		String json=JsonObjectUtil.object2json(map);
		logger.info(json);
		pt.write(json);
		pt.flush();
		pt.close();
	}

}
