package com.yzx.rest.util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * json字符串工具类
 * 
 * @author xiejiaan
 */
public class JsonUtils {
	private static final Gson gson = new Gson();
	private static final JsonParser jsonParser=new JsonParser();
	private static final GsonBuilder gsonBuilder=new GsonBuilder();
	/**
	 * 将对象转换成为json字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		return gson.toJson(obj);
	}

	/**
	 * 将json字符串转换成为对象
	 * 
	 * @param json
	 * @param classOfT
	 * @return
	 */
	public static <T> T toObject(String json, Class<T> classOfT) {
		return gson.fromJson(json, classOfT);
	}
	/**
	 * 将json字符串转化成jsonobject对象
	 * @todo TODO
	 * @return JsonObject
	 * @params
	 */
	public static JsonObject toJsonObj(String str){
		return jsonParser.parse(str).getAsJsonObject();
	}
	/**
	 * 将pojo转化成jsonobject对象
	 * @todo TODO
	 * @return JsonObject
	 * @params
	 */
	public static JsonObject toJsonObj(Object obj){
		String jsonStr = gsonBuilder.create().toJson(obj);
		return toJsonObj(jsonStr);
	}
	
	public static void main(String[] args) {
		Map<String, Object> map = null ;
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>() ;
		map = new HashMap<String, Object>();
		map.put("member", "123");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("member", "456");
		list.add(map);
		System.out.println(toJson(list));
	}
	public static String json2URLCode(String str) {
        str = str.replace("{", "%7b").replace("}", "%7d").replace("\"", "%22").replace("[", "%5b").replace("]", "%5d");
        return str;
    }
}
