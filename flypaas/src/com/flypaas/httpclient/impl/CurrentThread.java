package com.flypaas.httpclient.impl;

import java.util.Map;

import com.flypaas.httpclient.HttpXmlClient;

public class CurrentThread extends HttpXmlClient implements Runnable {
	private String url = null ;
	private Map<String, String> params = null ;
	public CurrentThread(String url,Map<String, String> params){
		this.params = params;
		this.url = url ;
	}
	public void run() {
		post(url, params);
	}

}
