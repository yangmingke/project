package com.network.monitor.util;

import java.lang.reflect.Field;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 系统配置工具类
 * 
 * @author xiejiaan
 */
@Component
public class ConfigUtils {
	private static final Logger logger = LoggerFactory.getLogger(ConfigUtils.class);

	/**
	 * 运行环境：development（开发）、devtest（开发测试）、test（测试）、production（线上）
	 */
	public static String spring_profiles_active;
	/**
	 * 配置文件路径
	 */
	public static String config_file_path;
	/**
	 * 是否自动登录
	 */
	public static Boolean is_auto_login;

	/**
	 * 接口地址：刷新前台缓存信息，不可使用
	 */
	public static String interface_url_flush;

	/**
	 * rest接口的域名，不可使用
	 */
	public static String rest_domain;
	/**
	 * rest接口的版本，不可使用
	 */
	public static String rest_version;

	@Autowired
	private Properties configproperties;

	/**
	 * 初始化
	 */
	@PostConstruct
	public void init() {
		String path = ConfigUtils.class.getClassLoader().getResource("").getPath() + "config/";
		ConfigUtils.spring_profiles_active = System.getProperty("spring.profiles.active");
		ConfigUtils.config_file_path = path + "config_" + spring_profiles_active + ".properties";

		logger.debug("\n\n-------------------------【network-monitor，{}服务器启动】\n加载配置文件：\n{}\n",
				ConfigUtils.spring_profiles_active, ConfigUtils.config_file_path);

		initValue();
	}

	/**
	 * 初始化配置项的值
	 */
	private void initValue() {
		Field[] fields = ConfigUtils.class.getFields();
		Object fieldValue = null;
		String name = null, value = null, tmp = null;
		Class<?> type = null;
		Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}");
		Matcher matcher = null;
		try {
			for (Field field : fields) {
				name = field.getName();
				value = configproperties.getProperty(name);
				if (StringUtils.isNotBlank(value)) {
					matcher = pattern.matcher(value);
					while (matcher.find()) {
						tmp = configproperties.getProperty(matcher.group(1));
						if (StringUtils.isBlank(tmp)) {
							logger.error("配置{}存在其它配置{}，请检查您的配置文件", name, matcher.group(1));
						}
						value = value.replace(matcher.group(0), tmp);
					}

					type = field.getType();
					if (String.class.equals(type)) {
						fieldValue = value;
					} else if (Integer.class.equals(type)) {
						fieldValue = Integer.valueOf(value);
					} else if (Boolean.class.equals(type)) {
						fieldValue = Boolean.valueOf(value);
					} else {
						fieldValue = value;
					}
					field.set(this, fieldValue);
				}
				logger.debug("加载配置：{}={}", name, field.get(this));
			}
		} catch (Throwable e) {
			logger.error("初始化配置项的值失败：" + name + "=" + value, e);
		}
	}

}
