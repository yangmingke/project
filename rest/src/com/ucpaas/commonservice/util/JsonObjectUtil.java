package com.ucpaas.commonservice.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonObjectUtil {

	private static Logger logger = LoggerFactory.getLogger(JsonObjectUtil.class);

	public static <T> T json2object(String json, Class<T> objectClass) {

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, objectClass);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("json字符串转换对象失败，json={},class ={} ,excption={}", json, objectClass.toString(), e.getMessage());
		}

		return null;

	}

	public static String object2json(Object object) {

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("json字符串转换对象失败，objuect={},,excption={}", object.toString(), e.getMessage());
		}

		return null;

	}

}
