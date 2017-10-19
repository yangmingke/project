package com.flypaas.admin.util.cache;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;
import com.opensymphony.oscache.web.ServletCacheAdministrator;

/**
 * OSCache缓存工具类
 * 
 * @author xiejiaan
 * 
 */
public class OSCacheUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(OSCacheUtils.class);

	private static final GeneralCacheAdministrator admin = new GeneralCacheAdministrator();

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
		authority_menuId;
	}

	/**
	 * 根据key刷新<cache:cache中定义的缓存
	 * 
	 * @param key
	 * @param cacheScope
	 *            Cache缓存范围
	 */
	public static void flushServletKey(String key, CacheScope cacheScope) {
		ServletCacheAdministrator sAdmin = ServletCacheAdministrator.getInstance(ServletActionContext
				.getServletContext());
		HttpServletRequest request = ServletActionContext.getRequest();

		String actualKey = sAdmin.generateEntryKey(key, request, cacheScope.value, null);
		Cache cache = sAdmin.getCache(request, cacheScope.value);
		cache.flushEntry(actualKey);
	}

	/**
	 * 根据group刷新<cache:cache中定义的缓存
	 * 
	 * @param group
	 * @param cacheScope
	 *            Cache缓存范围
	 */
	public static void flushServletGroup(String group, CacheScope cacheScope) {
		ServletCacheAdministrator sAdmin = ServletCacheAdministrator.getInstance(ServletActionContext
				.getServletContext());
		HttpServletRequest request = ServletActionContext.getRequest();

		Cache cache = sAdmin.getCache(request, cacheScope.value);
		cache.flushGroup(group);
	}

	/**
	 * 刷新页面中的菜单缓存
	 * 
	 * @param roleId
	 *            需要刷新的角色
	 */
	public static void flushMenuCache(int roleId) {
		flushServletGroup("menuCache_" + roleId, CacheScope.application);
	}

	/**
	 * 放入缓存
	 * 
	 * @param cacheType
	 *            缓存类型
	 * @param group
	 *            分组
	 * @param key
	 * @param content
	 */
	public static void putInGeneralCache(CacheType cacheType, Object group, Object key, Object content) {
		String realGroup = cacheType + group.toString();
		String realKey = cacheType + group.toString() + key;

		String[] groups = { realGroup };
		admin.putInCache(realKey, content, groups);
	}

	/**
	 * 获取缓存
	 * 
	 * @param cacheType
	 *            缓存类型
	 * @param group
	 *            分组
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getFromGeneralCache(CacheType cacheType, Object group, Object key) {
		String realKey = cacheType + group.toString() + key;

		Object content = null;
		try {
			content = admin.getFromCache(realKey);
		} catch (NeedsRefreshException e) {
			LOGGER.debug("第一次获取缓存内容：realKey=" + realKey);
		}
		return (T) content;
	}

	/**
	 * 刷新分组内的所有缓存
	 * 
	 * @param cacheType
	 *            缓存类型
	 * @param group
	 */
	public static void flushGeneralGroup(CacheType cacheType, Object group) {
		String realGroup = cacheType + group.toString();

		admin.flushGroup(realGroup);
	}

}
