package com.yzx.rest.util;

import java.io.ByteArrayInputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

public class HttpRequest {    
	public static Logger logger = Logger.getLogger(HttpRequest.class);
    @SuppressWarnings("deprecation")
	public HttpResponse post(String cType,String url,Object obj) throws Exception{
		DefaultHttpClient httpclient=new DefaultHttpClient();
		//创建HttpPost
		HttpPost httppost = new HttpPost(url);
		httppost.setHeader("Accept", cType);
		httppost.setHeader("Content-Type", cType+";charset=utf-8");
		
		System.out.println("---------------------------- find account for xml begin----------------------------");
		System.out.println("Response content is: " + obj);
		
		BasicHttpEntity requestBody = new BasicHttpEntity();
        requestBody.setContent(new ByteArrayInputStream(JsonUtils.toJson(obj).getBytes("UTF-8")));
        requestBody.setContentLength(JsonUtils.toJson(obj).getBytes("UTF-8").length);
        httppost.setEntity(requestBody);
        // 执行客户端请求
		HttpResponse response = httpclient.execute(httppost);
		httpclient.getConnectionManager().shutdown();
		return response;
	}
    
    public HttpResponse get(String url) throws Exception{
    	HttpGet httpRequst = new HttpGet(url);
    	HttpResponse response = new DefaultHttpClient().execute(httpRequst);
		return response;
	}
    
    
    /*@SuppressWarnings("deprecation")
	public DefaultHttpClient getDefaultHttpClient(){
		DefaultHttpClient httpclient=null;
		if (isTest()) {
			try {
				RouterInfo ri =new RouterInfo();
				SSLHttpClient chc = new SSLHttpClient();
				httpclient = chc.registerSSL(ri.getIp(),"TLS",ri.getPost(),"https");
				HttpParams hParams=new BasicHttpParams();
				hParams.setParameter("https.protocols", "SSLv3,SSLv2Hello");
				httpclient.setParams(hParams);
			} catch (KeyManagementException e) {
				logger.error(e.getMessage());
			}catch (NoSuchAlgorithmException e) {
				logger.error(e.getMessage());
			}
		}else {
			httpclient=new DefaultHttpClient();
		}
		return httpclient;
	}*/
    
    
   /* private boolean isTest(){
		String SpringPrefix = SysConfig.getInstance().getSpringPrefixActive();
		if(!StrUtil.isEmpty(SpringPrefix)&&!SpringPrefix.equals("production")){
			logger.info("当前环境为测试环境...");
			return true ;
		}
		return false;
	}*/
}