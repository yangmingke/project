package com.flypaas.rest;

import com.flypaas.constant.AppConstant;
import com.flypaas.constant.SysConstant;

public class CreateClientRestThread implements Runnable {
	private String sid ;
	private String token;
	private String appSid;
	
	public CreateClientRestThread(String sid,String token ,String appSid){
		this.appSid = appSid;
		this.token = token ;
		this.sid = sid ;
	}

	public void run() {
		addClient(sid, token, appSid);
	}

	//添加测试client  
	private void addClient(String sid,String token,String appSid){
		for(int i=0;i<SysConstant.cNum;i++){
			int temp = i+1;
			RestClient.createClient(sid, token, appSid, "test"+temp, AppConstant.CHARGETYPE, AppConstant.CHARGE, "","0");
		}
	}
}
