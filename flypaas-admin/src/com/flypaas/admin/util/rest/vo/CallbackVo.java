package com.flypaas.admin.util.rest.vo;

public class CallbackVo {

	private String appId;
	private String fromClient;
	private String to;
	private String toSerNum;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getFromClient() {
		return fromClient;
	}
	public void setFromClient(String fromClient) {
		this.fromClient = fromClient;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getToSerNum() {
		return toSerNum;
	}
	public void setToSerNum(String toSerNum) {
		this.toSerNum = toSerNum;
	}
	
	
}
