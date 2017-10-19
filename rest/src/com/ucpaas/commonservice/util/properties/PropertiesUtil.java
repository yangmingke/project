package com.ucpaas.commonservice.util.properties;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ucpaas.commonservice.constant.Constants;

public class PropertiesUtil {
	private static final Logger log = LoggerFactory.getLogger(PropertiesUtil.class);

	/**
	 * 配置环境信息
	 */
	public static final String SPRING_PROFILES_ACTIVE = "spring.profiles.active";

	// 不同环境下，配置文件命名规则：config_${spring.profiles.active}.properties
	// properties配置文件前缀
	private static final String PROPERTIES_PREFIX = "config_";

	// properties配置文件后缀
	private static final String PROPERTIES_SUFFIX = ".properties";

	private static Properties properties = new Properties();

	private static PropertiesUtil propertiesUtil;

	private PropertiesUtil() {
	}

	private static void loadFile(String filename) {
		try {

			properties.load(PropertiesUtil.class.getResourceAsStream("/config/" + filename));
			log.debug("【加载properties文件】," + "/config/" + filename);
		} catch (Exception e) {
			log.error("【加载properties文件】错误,filename=" + filename + ",path=/config/" + filename, e);
		}
	}

	public static synchronized PropertiesUtil createPropertiesUtil() {
		if (propertiesUtil == null) {
			propertiesUtil = new PropertiesUtil();
		}
		loadFile(getFileName());
		return propertiesUtil;
	}

	/**
	 * 根据key获取配置信息
	 * 
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	/**
	 * 获取环境配置信息
	 * 
	 * @return
	 */
	public static String getSpringProfilesActive(String springProfiles) {
		return System.getProperty(springProfiles);
	}

	/**
	 * 获取不同环境下配置文件名称
	 * 
	 * @return
	 */
	public static String getFileName() {
		return PROPERTIES_PREFIX + getSpringProfilesActive(SPRING_PROFILES_ACTIVE) + PROPERTIES_SUFFIX;
	}

	public static void main(String[] args) {
		PropertiesUtil propertiesUtil = PropertiesUtil.createPropertiesUtil();
		System.out.println(propertiesUtil.getProperty("client.delete.cache"));
		System.out.println(propertiesUtil.getProperty(Constants.CLIENT_DELETE_CACHE));

	}
}
