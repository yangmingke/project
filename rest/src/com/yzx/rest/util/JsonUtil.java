package com.yzx.rest.util;

import java.util.Collection;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 概述：json工具类
 * 日期：2013.05.02
 */
public class JsonUtil {
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static String toJsonString(Object obj, String datePattern) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor(datePattern));
		jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor(datePattern));
		if (obj instanceof Collection<?> || obj instanceof Object[]) {
			return JSONArray.fromObject(obj, jsonConfig).toString();
		} else {
			return JSONObject.fromObject(obj, jsonConfig).toString();
		}
	}
	
	public static String toJsonString(Object obj) {
		return toJsonString(obj, DEFAULT_DATE_PATTERN);
	}
}
