package com.yzx.rest.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("deprecation")
public class HttpHelper {
	private static final Logger log = LoggerFactory.getLogger(HttpHelper.class);

	/**
	 * 发送get请求（短信）
	 * 
	 * @param strUrl
	 * @param timeOut
	 * @return
	 */
	@SuppressWarnings("resource")
	public static String httpConnectionGetForSms(String strUrl, int timeOut) {
		log.info("【发送get请求】开始：strUrl={}, timeOut={}", strUrl, timeOut);
		BufferedReader br = null;
		HttpClient httpClient =null;
		try {
			httpClient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(strUrl);
			httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeOut);// 连接时间
			httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, timeOut);// 数据传输时间
			HttpResponse response = httpClient.execute(httpget);
			StringBuffer result = new StringBuffer();
			String line = "";
			br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
			while ((line = br.readLine()) != null) {
				result.append(line);
				result.append("\r\n");
			}
			log.info("【发送get请求】结束：result={}, strUrl={}, timeOut={}", result, strUrl, timeOut);
			return result.toString();
		} catch (Throwable e) {
			log.error("【发送get请求】失败：strUrl=" + strUrl, e);
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Throwable e) {
				log.error("【发送get请求】关闭输出流失败：strUrl=" + strUrl, e);
			}
		}
		return null;
	}

	/**
	 * 发送get请求（回拨）
	 * 
	 * @param strUrl
	 * @param timeOut
	 * @return
	 */
	public static String httpConnectionGet(String strUrl, int timeOut) {
		log.info("【回拨发送get请求】开始：strUrl={}, timeOut={}", strUrl, timeOut);
		try {
			InputStream ins = null;
			BufferedReader l_reader = null;
			URL url = new URL(strUrl);
			URLConnection connection = url.openConnection();
			connection.setConnectTimeout(timeOut);
			connection.setReadTimeout(timeOut);
			connection.connect();
			ins = connection.getInputStream();
			l_reader = new BufferedReader(new InputStreamReader(ins, "utf-8"));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = l_reader.readLine()) != null) {
				result.append(line);
				result.append("\r\n");
			}
			l_reader.close();
			ins.close();
			log.info("【回拨发送get请求】结束：result={}, strUrl={}, timeOut={}", result, strUrl, timeOut);
			return result.toString();
		} catch (Throwable e) {
			log.error("【回拨发送get请求】失败：strUrl=" + strUrl, e);
		}
		return null;
	}

	public static String httpConnectionGet(String strUrl) {
		try {
			InputStream ins = null;
			BufferedReader l_reader = null;
			URL url = new URL(strUrl);
			URLConnection connection = url.openConnection();
			connection.setConnectTimeout(SysConfig.getInstance().getPropertyInt("time_out", 20000));
			connection.setReadTimeout(SysConfig.getInstance().getPropertyInt("time_out", 20000));
			connection.connect();
			ins = connection.getInputStream();
			l_reader = new BufferedReader(new InputStreamReader(ins, "utf-8"));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = l_reader.readLine()) != null) {
				result.append(line);
				result.append("\r\n");
			}
			l_reader.close();
			ins.close();
			log.info(result.toString());
			return result.toString();
		} catch (Exception e) {
			log.error(e + "," + strUrl);
		}
		return null;
	}
	public static String httpConnectionPost(String strUrl, String content) {
		try {
			URL url = new URL(strUrl);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setConnectTimeout(SysConfig.getInstance().getPropertyInt("time_out", 20000));
			connection.setReadTimeout(SysConfig.getInstance().getPropertyInt("time_out", 20000));
			connection.connect();
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(content);
			out.flush();
			out.close();
			InputStream ins = connection.getInputStream();
			BufferedReader l_reader = new BufferedReader(new InputStreamReader(ins, "utf-8"));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = l_reader.readLine()) != null) {
				result.append(line);
				result.append("\r\n");
			}
			l_reader.close();
			ins.close();
			log.info(result.toString());
			return result.toString();
		} catch (Exception e) {
			log.error(e + "," + strUrl);
		}
		return null;
	}
	public static String httpConnectionPostJson(String strUrl, String content) {
		try {
			URL url = new URL(strUrl);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
			connection.setConnectTimeout(SysConfig.getInstance().getPropertyInt("time_out", 20000));
			connection.setReadTimeout(SysConfig.getInstance().getPropertyInt("time_out", 20000));
			connection.connect();
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(new String(content.getBytes("UTF-8")));
			out.flush();
			out.close();
			InputStream ins = connection.getInputStream();
			BufferedReader l_reader = new BufferedReader(new InputStreamReader(ins, "utf-8"));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = l_reader.readLine()) != null) {
				result.append(line);
				result.append("\r\n");
			}
			l_reader.close();
			ins.close();
			log.info(result.toString());
			return result.toString();
		} catch (Exception e) {
			log.error(e + "," + strUrl);
		}
		return null;
	}
}
