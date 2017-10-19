package com.flypaas.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringKit implements ApplicationContextAware{
	public static ApplicationContext ctx;
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		if(null == SpringKit.ctx){
			SpringKit.ctx = applicationContext;
		}
	}
	public static ApplicationContext getCtx() {
		return ctx;
	}
	public static <T> T getBean(Class<T> clazz) {
		return ctx.getBean(clazz);
	}
	@SuppressWarnings("all")
	public static <T> T getBean(String beanId) {
		return (T) ctx.getBean(beanId);
	}
}
