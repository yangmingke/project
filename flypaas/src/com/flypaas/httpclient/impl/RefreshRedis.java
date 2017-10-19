package com.flypaas.httpclient.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flypaas.httpclient.HttpXmlClient;
import com.flypaas.utils.SysConfig;
import com.flypaas.utils.ThreadPool;

public class RefreshRedis {
	private static String url = null;
	private static String clientUrl=null ;
	private static String key = null ;
	private static Map<String, String> params = null ;
	private static Logger log = LoggerFactory.getLogger(HttpXmlClient.class);
	
	static{
		if(url==null){
			url = SysConfig.getInstance().getProperty("inteface_url")+"/interface/mem?func=delKey";
			clientUrl=SysConfig.getInstance().getProperty("inteface_url")+"/interface/mem?func=delKeys&type=client";
		}
		if(params==null){
			params = new HashMap<String, String>();
		}
		
	}
	private static void sendPost(String urlStr,Map<String, String> params){
		log.info("开始刷新redis："+urlStr+"  参数:"+params);
		CurrentThread post = new CurrentThread(urlStr, params);
		ThreadPool.excute(post);
	}
	public static void updateUser(String sid){
		key = "main:"+sid ;
		params.put("key", key);
		sendPost(url,params);
		
	}
	public static void updateApp(String appSid,String sid){
		key = "app:"+appSid ;
		params.put("key", key);
		sendPost(url,params);
		String cl = clientUrl+"&sid="+sid;
		sendPost(cl,new HashMap<String, String>());
		
		key = "show:"+appSid ;
		params.put("key", key);
		sendPost(url,params);
		cl = clientUrl+"&sid="+sid;
		sendPost(cl,new HashMap<String, String>());
	}
	public static void updateWhiteList(String appSid){
		key = "wl:"+appSid ;
		params.put("key", key);
		sendPost(url,params);
	}
	public static void updateSmsTempalte(String id){
		key = "tl:"+id ;
		params.put("key", key);
		sendPost(url,params);
	}
	public static void updateTestWhiteList(String sid){
		key = "swl:"+sid ;
		params.put("key", key);
		sendPost(url,params);
	}
	
}
