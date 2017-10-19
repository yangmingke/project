package com.ucpaas.commonservice.util;

import com.google.gson.Gson;

/**
 * json工具类
 * 
 */
public class JsonUtil {
	private static final Gson gson = new Gson();

	public static String toJson(Object obj) {
		return gson.toJson(obj);
	}
	
	public static Object fromJson(String jsonString, Class<?> classofT) {
		return gson.fromJson(jsonString, classofT);
	}
	
	
	
	
	

}
