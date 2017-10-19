package com.flypaas.admin.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * 
 * @author xiejiaan
 */
public class StrUtils {

	/**
	 * 替换字符串中的占位符，占位符的正则为\[@\w+@\]
	 * 
	 * @param data
	 *            处理的字符串
	 * @param params
	 *            替换的参数
	 * @return
	 */
	public static String replacePlaceholder(String data, Map<String, Object> params) {
		Pattern p = Pattern.compile("\\[@\\w+@\\]");
		Matcher m = p.matcher(data);
		String key;
		Object value;
		while (m.find()) {
			key = m.group();
			value = params.get(key.substring(2, key.length() - 2));
			if (value != null) {
				data = data.replace(key, value.toString());
			}
		}
		return data;
	}
}
