package com.ucpaas.commonservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.context.support.StandardServletEnvironment;


@Configuration
public class Properties {
	private static final Logger log = LoggerFactory.getLogger(Properties.class);
	
	/**调用第三方client缓存删除接口，例如IM（刘晓健提供）*/
	@Value("${client.delete.cache}")
	private String client_delete_cache;
	
	/**关闭client，调刘晓健提供的接口删除client和反向表缓存信息*/
	@Value("${clientAndAttr.delete.cache}")
	private String clientAndAttr_delete_cache;
	
	
	@Value("${redis.effective.seconds}")
	private String redis_effective_seconds;
	
	@Value("${uin.section.id}")
	private Integer uin_section_id;
	
	@Value("${interface.clientAccount}")
	private String interface_clientAccount;
	
	@Value("${ip_redis_key}")
	private String ip_redis_key;

	
	static{
		Environment environment = new StandardServletEnvironment();
		String spring_profiles_active = environment.getProperty("spring.profiles.active");
		log.info("加载配置文件config_"+spring_profiles_active+".properties" + "  success!!!");
	}
	
	public String getClient_delete_cache() {
		return client_delete_cache;
	}

	public String getRedis_effective_seconds() {
		return redis_effective_seconds;
	}

	public Integer getUin_section_id() {
		return uin_section_id;
	}

	public String getClientAndAttr_delete_cache() {
		return clientAndAttr_delete_cache;
	}

	public String getInterface_clientAccount() {
		return interface_clientAccount;
	}

	public String getIp_redis_key() {
		return ip_redis_key;
	}

	public void setIp_redis_key(String ip_redis_key) {
		this.ip_redis_key = ip_redis_key;
	}
	

	
	
	
	
}
