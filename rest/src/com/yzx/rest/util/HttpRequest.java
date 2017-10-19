package com.yzx.rest.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

public class HttpRequest {    
	public static Logger logger = Logger.getLogger(HttpRequest.class);
    @SuppressWarnings("deprecation")
	public HttpResponse post(String cType,String url,Object obj){
		DefaultHttpClient httpclient=new DefaultHttpClient();
		//创建HttpPost
		HttpPost httppost = new HttpPost(url);
		httppost.setHeader("Accept", cType);
		httppost.setHeader("Content-Type", cType+";charset=utf-8");
		
		System.out.println("---------------------------- find account for xml begin----------------------------");
		System.out.println("Response content is: " + obj);
		
		BasicHttpEntity requestBody = new BasicHttpEntity();
		HttpResponse response = null;
        try {
			requestBody.setContent(new ByteArrayInputStream(JsonUtils.toJson(obj).getBytes("UTF-8")));
	        requestBody.setContentLength(JsonUtils.toJson(obj).getBytes("UTF-8").length);
	        httppost.setEntity(requestBody);
	        // 执行客户端请求
			response = httpclient.execute(httppost);
        } catch (Exception e) {
        	e.printStackTrace();
        }finally{
        	httpclient.getConnectionManager().shutdown();
        }
		return response;
	}
    
    public HttpResponse get(String url){
    	DefaultHttpClient httpclient=new DefaultHttpClient();
    	HttpGet httpRequst = new HttpGet(url);
    	HttpResponse response = null;
		try {
			response = httpclient.execute(httpRequst);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			httpclient.getConnectionManager().shutdown();
		}
		return response;
	}
    
}