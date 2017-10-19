package com.flypaas.rest;

import com.flypaas.constant.SysConstant;

public class CreateAppClientThread implements Runnable {
	private String sid ;
	private String token;
	private String appSid;
	
	public CreateAppClientThread(String sid,String token ,String appSid){
		this.appSid = appSid;
		this.token = token ;
		this.sid = sid ;
	}

	public void run() {
		addClient(sid, token, appSid);
	}

	//添加client  
	private void addClient(String sid,String token,String appSid){
		for(int i=0;i<SysConstant.cNum;i++){
			RestClient.createClient(sid, token, appSid);
		}
	}
}
