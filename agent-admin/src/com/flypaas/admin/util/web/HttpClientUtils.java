package com.flypaas.admin.util.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HttpClient工具类
 * 
 * @author xiejiaan
 */
public class HttpClientUtils {
	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

	/**
	 * 发送get请求
	 * 
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		String msg = null;
		try {
			msg = Request.Get(url).execute().returnContent().asString();
			msg = new String(msg.getBytes("iso-8859-1"), "utf-8");
		} catch (Throwable e) {
			logger.error("发送get请求失败, url=" + url, e);
		}
		logger.debug("发送get请求, url=" + url + ", msg=" + msg);
		return msg;
	}

	/**
	 * 发送post请求，数据为表单
	 * 
	 * @param url
	 * @param data
	 * @return
	 */
	public static String postForm(String url, Map<String, Object> data) {
		String result = null;
		try {
			// 创建参数列表
			List<NameValuePair> formParams = new ArrayList<NameValuePair>();
			for (Map.Entry<String, Object> d : data.entrySet()) {
				if (d.getValue() != null) {
					formParams.add(new BasicNameValuePair(d.getKey(), d.getValue().toString()));
				}
			}
			Request request = Request.Post(url).bodyForm(formParams, Consts.UTF_8);

			result = request.execute().returnContent().asString();
		} catch (Throwable e) {
			logger.error("发送post请求，数据为表单失败：url=" + url + ", data=" + data, e);
		}
		logger.debug("发送post请求，数据为表单成功：url=" + url + ", data=" + data + ", result=" + result);
		return result;
	}

	/**
	 * 发送post请求，数据为xml字符串
	 * 
	 * @param url
	 * @param data
	 * @return
	 */
	public static String postXml(String url, String data) {
		String result = null;
		Request request = Request.Post(url);
		request.setHeader("Accept", "application/xml");
		request.setHeader("Content-Type", "text/xml;charset=utf-8");
		request.bodyString(data, ContentType.create("text/xml", Consts.UTF_8));
		try {
			result = request.execute().returnContent().asString();
		} catch (Throwable e) {
			logger.error("发送post请求，数据为xml字符串失败：url=" + url + ", data=" + data, e);
		}
		logger.debug("发送post请求，数据为xml字符串成功：url=" + url + ", data=" + data + ", result=" + result);
		return result;
	}

	public static void main(String[] args) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("sid", "啊啊啊饭的方法VH2345");
		data.put("appId", "为切尔长v吃饭嘎嘎嘎嘎~!@#$%^&*()_+");
		data.put("bb", "sgiocv,p<>:\"}+_(^*)");
		postForm("http://localhost:8080/maap/voice/code", data);
	}

}
