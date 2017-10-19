package com.flypaas.admin.util.cache;

import com.flypaas.admin.util.ConfigUtils;
import com.flypaas.admin.util.web.HttpClientUtils;

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
		HttpClientUtils.get(ConfigUtils.interface_url_flush + "main:" + sid);
	}

	/**
	 * 刷新应用的Redis缓存
	 * 
	 * @param appSid
	 */
	public static void flushAppCache(String appSid) {
		HttpClientUtils.get(ConfigUtils.interface_url_flush + "app:" + appSid);
	}

	/**
	 * 刷新client的Redis缓存
	 * 
	 * @param clientNumber
	 */
	public static void flushClientCache(String clientNumber) {
		HttpClientUtils.get(ConfigUtils.interface_url_flush + "client:" + clientNumber);
	}

	/**
	 * 刷新白名单的Redis缓存
	 * 
	 * @param appSid
	 */
	public static void flushWhiteListCache(String appSid) {
		HttpClientUtils.get(ConfigUtils.interface_url_flush + "wl:" + appSid);
	}

	/**
	 * 刷新应用显号缓存
	 * 
	 * @param appSid
	 */
	public static void flushShowCache(String appSid) {
		HttpClientUtils.get(ConfigUtils.interface_url_flush + "show:" + appSid);
	}

	/**
	 * 刷新短信模板的Redis缓存
	 * 
	 * @param templateId
	 */
	public static void flushSmsTemplateCache(String templateId) {
		HttpClientUtils.get(ConfigUtils.interface_url_flush + "tl:" + templateId);
	}

	/**
	 * 刷新敏感字的Redis缓存
	 */
	public static void flushKeywordCache() {
		HttpClientUtils.get(ConfigUtils.interface_url_flush + "keyword");
	}

}
