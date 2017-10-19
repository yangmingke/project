package com.flypaas.utils;

import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * 第三方账号登录，工具类
 * 
 * @author xiejiaan
 */
public class AuthUtils {
	private static final Logger logger = LoggerFactory.getLogger(AuthUtils.class);

	/**
	 * 发送get请求，返回Map对象
	 * 
	 * @param url
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> doGetReturnMap(String url) {
		return new Gson().fromJson(doGet(url), Map.class);
	}

	/**
	 * 发送get请求
	 * 
	 * @param url
	 * @return
	 */
	private static String doGet(String url) {
		logger.debug("发送get请求：url={}", url);
		String body = null;
		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			HttpGet httpget = new HttpGet(url);
			HttpResponse response = httpclient.execute(httpget);
			body = EntityUtils.toString(response.getEntity());
			logger.debug("发送get请求成功：url={}, body={}", url, body);
		} catch (Throwable e) {
			logger.error("发送get请求失败：url=" + url + ", body=" + body, e);
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return body;
	}

}
