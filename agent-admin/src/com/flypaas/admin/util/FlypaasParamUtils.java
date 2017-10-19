package com.flypaas.admin.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.flypaas.admin.service.TagService;

public class FlypaasParamUtils {
	private static TagService tagService;

	@Resource
	public void setTagService(TagService tagService) {
		if (null == FlypaasParamUtils.tagService) {
			FlypaasParamUtils.tagService = tagService;
		}
	}

	final static Map<String, Long> loadTime = new HashMap<String, Long>();
	final static long LOAD_TIME_MS = 3600 * 1000;// 一小时

	final static ConcurrentHashMap<String, ConcurrentHashMap<String, String>> cache = new ConcurrentHashMap<String, ConcurrentHashMap<String, String>>();

	public static final String get(String key, String type, String sqlId) {
		String mapKey = type + "_" + sqlId;

		String result = null;
		if (!loadTime.containsKey(mapKey)) {
			loadTime.put(mapKey, 0L);
		}
		long a = loadTime.get(mapKey);
		long now = System.currentTimeMillis();
		if (a < now) {
			if (!cache.containsKey(mapKey)) {
				load(type, sqlId);
			}
		}
		ConcurrentHashMap<String, String> tmp = cache.get(mapKey);
		if (null != tmp) {
			result = tmp.get(key);
		}
		return result;
	}

	private static void load(String type, String sqlId) {
		String mapKey = type + "_" + sqlId;

		List<Map<String, Object>> tmp = null;
		Map<String, Object> sqlParams = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(type)) {
			tmp = tagService.getTagDataForDictionary(type, sqlParams);
		} else {
			tmp = tagService.getTagDataForSql(sqlId, sqlParams);
		}
		ConcurrentHashMap<String, String> cc = new ConcurrentHashMap<String, String>();
		String key = null;
		for (Map<String, Object> map : tmp) {
			key = String.valueOf(map.get("value"));
			if (!StringUtils.isEmpty(key)) {
				cc.put(key, String.valueOf(map.get("text")));
			}
		}
		cache.put(mapKey, cc);
		loadTime.put(mapKey, System.currentTimeMillis() + LOAD_TIME_MS);
	}

	public static final void remove(String type, String sqlId) {
		String mapKey = type + "_" + sqlId;
		cache.remove(mapKey);
		loadTime.put(mapKey, 0L);
	}

	public static final void reload(String type, String sqlId) {
		load(type, sqlId);
	}

}
