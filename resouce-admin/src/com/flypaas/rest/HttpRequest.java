package com.flypaas.rest;

import java.io.ByteArrayInputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;

import com.flypaas.model.vo.RouterInfo;
import com.flypaas.model.vo.Rtpps;
import com.flypaas.util.JsonUtil;
import com.flypaas.util.StrUtil;
import com.flypaas.util.SysConfig;

public class HttpRequest {    
	public static Logger logger = Logger.getLogger(HttpRequest.class);
    @SuppressWarnings("deprecation")
	public HttpResponse post(String cType,String url,Rtpps rtpps){
		DefaultHttpClient httpclient=getDefaultHttpClient();
		//创建HttpPost
		HttpPost httppost = new HttpPost(url);
		httppost.setHeader("Accept", cType);
		httppost.setHeader("Content-Type", cType+";charset=utf-8");
		
		System.out.println("---------------------------- find account for xml begin----------------------------");
		System.out.println("Response content is: " + rtpps);
		
		BasicHttpEntity requestBody = new BasicHttpEntity();
		HttpResponse response = null;
        try {
			requestBody.setContent(new ByteArrayInputStream(JsonUtil.toJsonStr(rtpps).getBytes("UTF-8")));
	        requestBody.setContentLength(JsonUtil.toJsonStr(rtpps).getBytes("UTF-8").length);
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
    
    
    @SuppressWarnings("deprecation")
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
	}
    
    
    private boolean isTest(){
		String SpringPrefix = SysConfig.getInstance().getSpringPrefixActive();
		if(!StrUtil.isEmpty(SpringPrefix)&&!SpringPrefix.equals("production")){
			logger.info("当前环境为测试环境...");
			return true ;
		}
		return false;
	}


	public HttpResponse get(String url){
		DefaultHttpClient httpclient=getDefaultHttpClient();
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