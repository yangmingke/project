package com.network.monitor.util.rest;

public class CreateClientRestThread implements Runnable {
	// 系统为新开发者创建的client数
	private static final int cNum = 6;
	// 计费模式（0主号计费、1 主子都计费）
	public static final String CHARGETYPE = "0";
	public static final String CHARGE = "0";
	private String sid;
	private String token;
	private String appSid;

	public CreateClientRestThread(String sid, String token, String appSid) {
		this.appSid = appSid;
		this.token = token;
		this.sid = sid;
	}

	public void run() {
		addClient(sid, token, appSid);
	}

	// 添加测试client
	private void addClient(String sid, String token, String appSid) {
		for (int i = 0; i < cNum; i++) {
			int temp = i + 1;
			RestClient.createClient(sid, token, appSid, "test" + temp, CHARGETYPE, CHARGE, "", "0");
		}
	}
}
