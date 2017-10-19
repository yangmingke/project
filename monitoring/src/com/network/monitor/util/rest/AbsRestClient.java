package com.network.monitor.util.rest;

import java.io.ByteArrayInputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.network.monitor.util.ConfigUtils;
import com.network.monitor.util.rest.utils.EncryptUtil;

@SuppressWarnings("all")
public abstract class AbsRestClient {
	public static String ip;
	public static int port;
	private Logger logger = LoggerFactory.getLogger(JsonReqClient.class);
	static {
		String v = ConfigUtils.rest_domain.trim();
		v = v.substring("https://".length()).trim();
		int t = v.indexOf(":");
		if (t > -1) {
			ip = v.substring(0, t);
			port = Integer.valueOf(v.substring(t + 1));
		} else {
			ip = v;
			port = 443;
		}
	}

	/**
	 * 创建client
	 */
	public abstract String createClient(String accountSid, String authToken, String appId, String clientName,
			String chargeType, String charge, String mobile, String clientType);

	/**
	 * 语音验证码
	 * 
	 * @param accountSid
	 * @param authToken
	 * @param appId
	 * @param to
	 * @param verifyCode
	 * @param displayNum
	 * @return
	 */
	public abstract String voiceCode(String accountSid, String authToken, String appId, String to, String verifyCode,
			String displayNum, String chargeType);

	/**
	 * 短信验证码
	 * 
	 * @param accountSid
	 * @param authToken
	 * @param appId
	 * @param bodys
	 * @param to
	 * @param msgType
	 * @return
	 */
	public abstract String templateSMS(String accountSid, String authToken, String appId, String templateId, String to,
			String param, String chargeType);

	/**
	 * 回拨
	 * 
	 * @param accountSid
	 * @param authToken
	 * @param appId
	 * @param fromClient
	 * @param to
	 * @param toSerNum
	 * @return
	 */
	public abstract String callback(String accountSid, String authToken, String appId, String fromClient, String to,
			String toSerNum);

	/**
	 * 绑定测试号码 通知rest
	 * 
	 * @param accountSid
	 * @param auth
	 * @param appId
	 * @param clientNumber
	 * @param mobile
	 * @return
	 */
	public abstract String bandClients(String accountSid, String auth, String appId, String clientNumber, String mobile);

	public StringBuffer getStringBuffer() {
		StringBuffer sb = new StringBuffer("https://");
		sb.append(ip);
		return sb;
	}

	private boolean isTest() {
		String v = System.getProperty("spring.profiles.active");
		if (!"production".equalsIgnoreCase(v)) {
			logger.info("当前环境为测试环境...");
			return true;
		}
		return false;
	}

	public DefaultHttpClient getDefaultHttpClient() {
		DefaultHttpClient httpclient = null;
		if (isTest()) {
			try {
				SSLHttpClient chc = new SSLHttpClient();
				httpclient = chc.registerSSL(ip, "TLS", port, "https");
				HttpParams hParams = new BasicHttpParams();
				hParams.setParameter("https.protocols", "SSLv3,SSLv2Hello");
				httpclient.setParams(hParams);
			} catch (KeyManagementException e) {
				// TODO: handle exception
				logger.error(e.getMessage());
			} catch (NoSuchAlgorithmException e) {
				// TODO: handle exception
				logger.error(e.getMessage());
			}
		} else {
			httpclient = new DefaultHttpClient();
		}
		return httpclient;
	}

	@SuppressWarnings("static-access")
	public String getSignature(String accountSid, String authToken, String timestamp, EncryptUtil encryptUtil)
			throws Exception {
		// md5(主账户Id +主账户授权令牌 + 时间戳)
		String sig = accountSid + authToken + timestamp;
		String signature = encryptUtil.md5Digest(sig);
		return signature;
	}

	@SuppressWarnings("static-access")
	public HttpResponse get(String cType, String accountSid, String authToken, String timestamp, String url,
			DefaultHttpClient httpclient, EncryptUtil encryptUtil) throws Exception {
		// 创建HttpPost
		HttpGet httppost = new HttpGet(url);
		httppost.setHeader("Accept", cType);//
		httppost.setHeader("Content-Type", cType + ";charset=utf-8");
		String src = accountSid + ":" + timestamp;
		// base64(主账户Id + 冒号 +时间戳)
		String auth = encryptUtil.base64Encoder(src);
		httppost.setHeader("Authorization", auth);
		// 执行客户端请求
		HttpResponse response = httpclient.execute(httppost);
		return response;
	}

	@SuppressWarnings("static-access")
	public HttpResponse post(String cType, String accountSid, String authToken, String timestamp, String url,
			DefaultHttpClient httpclient, EncryptUtil encryptUtil, String body) throws Exception {
		// 创建HttpPost
		HttpPost httppost = new HttpPost(url);
		httppost.setHeader("Accept", cType);
		httppost.setHeader("Content-Type", cType + ";charset=utf-8");
		String src = accountSid + ":" + timestamp;
		// base64(主账户Id + 冒号 +时间戳)
		String auth = encryptUtil.base64Encoder(src);
		httppost.setHeader("Authorization", auth);
		System.out.println("---------------------------- find account for xml begin----------------------------");
		System.out.println("Response content is: " + body);
		BasicHttpEntity requestBody = new BasicHttpEntity();
		requestBody.setContent(new ByteArrayInputStream(body.getBytes("UTF-8")));
		requestBody.setContentLength(body.getBytes("UTF-8").length);
		httppost.setEntity(requestBody);
		// 执行客户端请求
		HttpResponse response = httpclient.execute(httppost);
		return response;
	}

	@SuppressWarnings("static-access")
	public HttpResponse delete(String cType, String accountSid, String authToken, String timestamp, String url,
			DefaultHttpClient httpclient, EncryptUtil encryptUtil) throws Exception {
		HttpDelete httpDelete = new HttpDelete(url);
		httpDelete.setHeader("Accept", cType);
		httpDelete.setHeader("Content-Type", cType + ";charset=utf-8");
		String src = accountSid + ":" + timestamp;
		// base64(主账户Id + 冒号 +时间戳)
		String auth = encryptUtil.base64Encoder(src);
		httpDelete.setHeader("Authorization", auth);
		HttpResponse response = httpclient.execute(httpDelete);
		return response;
	}

	@SuppressWarnings("static-access")
	public HttpResponse put(String cType, String accountSid, String authToken, String timestamp, String url,
			DefaultHttpClient httpclient, EncryptUtil encryptUtil, String body) throws Exception {
		// 创建HttpPost
		HttpPut httpPut = new HttpPut(url);
		httpPut.setHeader("Accept", cType);
		httpPut.setHeader("Content-Type", cType + ";charset=utf-8");
		String src = accountSid + ":" + timestamp;
		// base64(主账户Id + 冒号 +时间戳)
		String auth = encryptUtil.base64Encoder(src);
		httpPut.setHeader("Authorization", auth);
		System.out.println("Response content is: " + body);
		BasicHttpEntity requestBody = new BasicHttpEntity();
		requestBody.setContent(new ByteArrayInputStream(body.getBytes("UTF-8")));
		requestBody.setContentLength(body.getBytes("UTF-8").length);
		httpPut.setEntity(requestBody);
		// 执行客户端请求
		HttpResponse response = httpclient.execute(httpPut);
		return response;
	}
}
