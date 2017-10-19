package com.network.monitor.util.cache;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.base.EntryRefreshPolicy;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;
import com.opensymphony.oscache.web.ServletCacheAdministrator;
import com.opensymphony.oscache.web.filter.ExpiresRefreshPolicy;

/**
 * OSCache缓存工具类
 * 
 * @author xiejiaan
 * 
 */
public class OSCacheUtils {
	private static final Logger logger = LoggerFactory.getLogger(OSCacheUtils.class);
	private static final GeneralCacheAdministrator cache_admin = new GeneralCacheAdministrator();

	/**
	 * Cache缓存范围
	 * 
	 * @author xiejiaan
	 * 
	 */
	public enum CacheScope {
		session(3), application(4);
		private int value;

		CacheScope(int value) {
			this.value = value;
		}
	}

	/**
	 * 缓存类型
	 * 
	 * @author xiejiaan
	 */
	public enum CacheType {
		/**
		 * url访问权限
		 */
		authority_url,
		/**
		 * menuId访问权限
		 */
		authority_menuId,
		/**
		 * 公共
		 */
		common;
	}

	/**
	 * 根据key刷新<cache:cache中定义的缓存
	 * 
	 * @param cacheScope
	 *            Cache缓存范围
	 * @param key
	 *            缓存的key
	 */
	public static void flushServletKey(CacheScope cacheScope, Object key) {
		ServletCacheAdministrator sAdmin = ServletCacheAdministrator.getInstance(ServletActionContext
				.getServletContext());
		HttpServletRequest request = ServletActionContext.getRequest();

		String actualKey = sAdmin.generateEntryKey(key.toString(), request, cacheScope.value, null);
		Cache cache = sAdmin.getCache(request, cacheScope.value);
		cache.flushEntry(actualKey);
	}

	/**
	 * 根据group刷新<cache:cache中定义的缓存
	 * 
	 * @param cacheScope
	 *            Cache缓存范围
	 * @param group
	 *            缓存分组
	 */
	public static void flushServletGroup(CacheScope cacheScope, Object group) {
		ServletCacheAdministrator sAdmin = ServletCacheAdministrator.getInstance(ServletActionContext
				.getServletContext());
		HttpServletRequest request = ServletActionContext.getRequest();

		Cache cache = sAdmin.getCache(request, cacheScope.value);
		cache.flushGroup(group.toString());
	}

	/**
	 * 刷新页面中的菜单缓存
	 * 
	 * @param roleId
	 *            需要刷新的角色
	 */
	public static void flushMenuCache(int roleId) {
		flushServletGroup(CacheScope.application, "menuCache_" + roleId);
	}

	/**
	 * 获取真正的缓存分组
	 * 
	 * @param cacheType
	 *            缓存类型
	 * @param group
	 *            缓存分组
	 * @return
	 */
	private static String getRealGroup(CacheType cacheType, Object group) {
		return cacheType + group.toString();
	}

	/**
	 * 获取真正的缓存的key
	 * 
	 * @param cacheType
	 *            缓存类型
	 * @param group
	 *            缓存分组
	 * @param key
	 *            缓存的key
	 * @return
	 */
	private static String getRealKey(CacheType cacheType, Object group, Object key) {
		return getRealGroup(cacheType, group) + key;
	}

	/**
	 * 添加缓存
	 * 
	 * @param cacheType
	 *            缓存类型
	 * @param group
	 *            缓存分组
	 * @param key
	 *            缓存的key
	 * @param value
	 *            缓存的值
	 */
	public static void addCache(CacheType cacheType, Object group, Object key, Object value) {
		String realGroup = getRealGroup(cacheType, group);
		String realKey = getRealKey(cacheType, group, key);

		String[] groups = { realGroup };
		cache_admin.putInCache(realKey, value, groups);
	}

	/**
	 * 添加缓存
	 * 
	 * @param key
	 *            缓存的key
	 * @param value
	 *            缓存的值
	 * @param refreshPeriod
	 *            缓存时间（秒），-1表示永远缓存
	 */
	public static void addCache(Object key, Object value, int refreshPeriod) {
		String realKey = getRealKey(CacheType.common, "", key);
		EntryRefreshPolicy policy = new ExpiresRefreshPolicy(refreshPeriod);

		cache_admin.putInCache(realKey, value, policy);
	}

	/**
	 * 添加缓存，永远缓存
	 * 
	 * @param key
	 *            缓存的key
	 * @param value
	 *            缓存的值
	 */
	public static void addCache(Object key, Object value) {
		addCache(key, value, -1);
	}

	/**
	 * 获取缓存
	 * 
	 * @param cacheType
	 *            缓存类型
	 * @param group
	 *            缓存分组
	 * @param key
	 *            缓存的key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getCache(CacheType cacheType, Object group, Object key) {
		String realKey = getRealKey(cacheType, group, key);

		Object value = null;
		try {
			value = cache_admin.getFromCache(realKey);
		} catch (NeedsRefreshException e) {
			logger.debug("缓存不存在：realKey=" + realKey);
			cache_admin.cancelUpdate(realKey);
		}
		return (T) value;
	}

	/**
	 * 获取缓存
	 * 
	 * @param key
	 *            缓存的key
	 * @return
	 */
	public static <T> T getCache(Object key) {
		return getCache(CacheType.common, "", key);
	}

	/**
	 * 刷新分组内的所有缓存
	 * 
	 * @param cacheType
	 *            缓存类型
	 * @param group
	 *            缓存分组
	 */
	public static void flushCache(CacheType cacheType, Object group) {
		String realGroup = getRealGroup(cacheType, group);
		cache_admin.flushGroup(realGroup);
	}

	/**
	 * 刷新缓存
	 * 
	 * @param key
	 *            缓存的key
	 */
	public static void flushCache(Object key) {
		String realKey = getRealKey(CacheType.common, "", key);
		cache_admin.flushEntry(realKey);
	}

	public static void main(String[] args) throws Exception {
		long sleepTime = 2 * 1000;
		Object key = "key1";
		Object initValue = "value1";
		OSCacheUtils.addCache(key, initValue, 5);// 添加缓存
		// OSCacheUtils.flushCache(key);// 刷新缓存

		Thread.sleep(sleepTime);
		logger.debug("缓存测试：key={}, initValue={}, cacheValue={}", key, initValue, OSCacheUtils.getCache(key));
		Thread.sleep(sleepTime);
		logger.debug("缓存测试：key={}, initValue={}, cacheValue={}", key, initValue, OSCacheUtils.getCache(key));
		Thread.sleep(sleepTime);
		logger.debug("缓存测试：key={}, initValue={}, cacheValue={}", key, initValue, OSCacheUtils.getCache(key));
		Thread.sleep(sleepTime);
		logger.debug("缓存测试：key={}, initValue={}, cacheValue={}", key, initValue, OSCacheUtils.getCache(key));
		Thread.sleep(sleepTime);
		logger.debug("缓存测试：key={}, initValue={}, cacheValue={}", key, initValue, OSCacheUtils.getCache(key));
	}

}
