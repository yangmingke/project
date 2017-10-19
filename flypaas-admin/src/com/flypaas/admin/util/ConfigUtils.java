package com.flypaas.admin.util;

import java.lang.reflect.Field;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
	 * 是否自动登录
	 */
	public static boolean is_auto_login;

	/**
	 * 配置的地址
	 */
	public static String interface_base_url;

	/**
	 * 配置文件路径
	 */
	public static String config_file_path;

	/**
	 * UEdiotr配置文件路径
	 */
	public static String ueditor_config_file_path;

	/**
	 * <pre>
	 * 接口地址：刷新前台缓存信息
	 * 
	 * 主账号key=main:[sid]
	 * 应用key=app:[appSid]
	 * 子账户key=client:[clientNumber]
	 * 白名单key=wl:[appSid]
	 * 短信模板key=tl:[templateId]
	 * 显号key=show:[templateId]
	 * </pre>
	 */
	public static String interface_url_flush;

	/**
	 * 接口地址：app审核通过后，分配短信号码
	 */
	public static String interface_url_getMsgNbr;

	/**
	 * rest接口的域名
	 */
	public static String rest_domain;
	/**
	 * rest接口的版本
	 */
	public static String rest_version;
	/**
	 * 前台站点的域名
	 */
	public static String flypaas_domain;
	/**
	 * 账单文件本地保存路径
	 */
	public static String save_path;

	/**
	 * ring_base_path : 铃声文件路径
	 */
	public static String ring_base_path;
	/**
	 * 同步转码脚本
	 */
	public static String rsync_shell;

	/**
	 * 版本管理
	 */
	public static String version_base_path;

	/**
	 * 资源的根路径
	 */
	public static String resource_base_path;

	/**
	 * 语音通知的基本地址
	 */
	public static String notify_call_base_path;

	@Value("${spring.profiles.active}")
	public void setSpring_profiles_active(String spring_profiles_active) {
		String path = ConfigUtils.class.getClassLoader().getResource("").getPath() + "config/";
		ConfigUtils.spring_profiles_active = spring_profiles_active;
		ConfigUtils.is_auto_login = Boolean.parseBoolean(System.getProperty("is_auto_login"));
		ConfigUtils.config_file_path = path + "config_" + spring_profiles_active + ".properties";
		ConfigUtils.ueditor_config_file_path = path + "ueditor_" + spring_profiles_active + ".json";

		logger.debug("\n\n-------------------------【flypaas-admin，{}服务器启动】\n是否自动登录：{}\n加载配置文件：\n{}\n{}\n",
				ConfigUtils.spring_profiles_active, ConfigUtils.is_auto_login, ConfigUtils.config_file_path,
				ConfigUtils.ueditor_config_file_path);
	}

	private static final Pattern pattern_param = Pattern.compile("\\$\\{([_a-zA-Z0-9\\u4e00-\\u9fa5]+)\\}");
	private Properties configproperties;

	@Resource(name = "configproperties")
	public void setPropertyPlaceholderConfigurer(Properties configproperties) {
		this.configproperties = configproperties;
	}

	@PostConstruct
	public void init() {
		Field[] fields = ConfigUtils.class.getFields();
		String name, value, tmp;
		Class<?> type;
		Object fieldValue = null;
		Matcher m = null;
		for (Field field : fields) {
			name = field.getName();
			value = configproperties.getProperty(name);
			if (StringUtils.isNotBlank(value)) {
				m = pattern_param.matcher(value);
				while (m.find()) {
					tmp = configproperties.getProperty(m.group(1));
					if (StringUtils.isBlank(tmp)) {
						logger.error(String.format("配置 %1$s 存在其它配置 %2$s ,请检查您的配置文件!", name, m.group(1)));
					}
					value = value.replace("${" + m.group(1) + "}", tmp);
				}

				fieldValue = null;
				type = field.getType();
				try {
					if (String.class.equals(type)) {
						fieldValue = value;
					} else if (Integer.class.equals(type)) {
						fieldValue = Integer.valueOf(value);
					}
					if (null != fieldValue) {
						field.set(this, fieldValue);
					}
					logger.debug(String.format("加载配置 %25s = %s", name, fieldValue));
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
			try {
				fieldValue = field.get(this);
				if (null == fieldValue) {
					logger.debug(String.format("没有配置 %s ,请检查您的配置文件!", name));
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
}
