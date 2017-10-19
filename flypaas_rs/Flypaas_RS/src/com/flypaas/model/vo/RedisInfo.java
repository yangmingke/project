package com.flypaas.model.vo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.core.env.Environment;
import org.springframework.web.context.support.StandardServletEnvironment;

public class RedisInfo{
	private String redis_servers;
	private String redis_port;
	private String redis_maxActive;
	private String redis_maxIdle;
	private String redis_maxWait;
	private boolean redis_testOnBorrow;
	
	
	public void init(){
		Properties pName = new Properties();
		try {
			Environment environment = new StandardServletEnvironment();
			String spring_profiles_active = environment.getProperty("flypaas_env");

			
	        InputStream iStr=this.getClass().getResourceAsStream("/config_"+spring_profiles_active+".properties");
	        pName.load(iStr);
	        System.out.println(pName.getProperty("redis_servers"));
	        this.redis_servers = pName.getProperty("redis_servers");
	        this.setRedis_servers(pName.getProperty("redis_servers"));
	        this.setRedis_port(pName.getProperty("redis_port"));
	        this.setRedis_maxActive(pName.getProperty("redis_maxActive"));
	        this.setRedis_maxIdle(pName.getProperty("redis_maxIdle"));
	        this.setRedis_maxWait(pName.getProperty("redis_maxWait"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public String getRedis_servers() {
		return redis_servers;
	}

	public void setRedis_servers(String redis_servers) {
		this.redis_servers = redis_servers;
	}

	public String getRedis_port() {
		return redis_port;
	}

	public void setRedis_port(String redis_port) {
		this.redis_port = redis_port;
	}

	public String getRedis_maxActive() {
		return redis_maxActive;
	}

	public void setRedis_maxActive(String redis_maxActive) {
		this.redis_maxActive = redis_maxActive;
	}

	public String getRedis_maxIdle() {
		return redis_maxIdle;
	}

	public void setRedis_maxIdle(String redis_maxIdle) {
		this.redis_maxIdle = redis_maxIdle;
	}

	public String getRedis_maxWait() {
		return redis_maxWait;
	}

	public void setRedis_maxWait(String redis_maxWait) {
		this.redis_maxWait = redis_maxWait;
	}

	public boolean isRedis_testOnBorrow() {
		return redis_testOnBorrow;
	}

	public void setRedis_testOnBorrow(boolean redis_testOnBorrow) {
		this.redis_testOnBorrow = redis_testOnBorrow;
	}

}
