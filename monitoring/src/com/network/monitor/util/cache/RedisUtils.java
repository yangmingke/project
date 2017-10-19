package com.network.monitor.util.cache;

import com.network.monitor.util.ConfigUtils;
import com.network.monitor.util.web.HttpClientUtils;

/**
 * Redis缓存工具类
 * 
 * @author xiejiaan
 */
public class RedisUtils {

	/**
	 * 刷新开发者的Redis缓存
	 * 
	 * @param sid
	 */
	public static void flushDeveloperCache(String sid) {
		HttpClientUtils.doGet(ConfigUtils.interface_url_flush + "main:" + sid);
	}

	/**
	 * 刷新应用的Redis缓存
	 * 
	 * @param appSid
	 */
	public static void flushAppCache(String appSid) {
		HttpClientUtils.doGet(ConfigUtils.interface_url_flush + "app:" + appSid);
	}

	/**
	 * 刷新client的Redis缓存
	 * 
	 * @param clientNumber
	 */
	public static void flushClientCache(String clientNumber) {
		HttpClientUtils.doGet(ConfigUtils.interface_url_flush + "client:" + clientNumber);
	}

	/**
	 * 刷新白名单的Redis缓存
	 * 
	 * @param appSid
	 */
	public static void flushWhiteListCache(String appSid) {
		HttpClientUtils.doGet(ConfigUtils.interface_url_flush + "wl:" + appSid);
	}

	/**
	 * 刷新短信模板的Redis缓存
	 * 
	 * @param templateId
	 */
	public static void flushSmsTemplateCache(String templateId) {
		HttpClientUtils.doGet(ConfigUtils.interface_url_flush + "tl:" + templateId);
	}

}
