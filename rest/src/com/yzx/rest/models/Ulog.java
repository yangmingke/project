package com.yzx.rest.models;

public class Ulog extends BaseInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7270256591468655167L;

	private String logEvent;
	private String ifType;
	private String ifName;
	private String errorCode;
	private String appId;
	private String clientNumber;
	
	public String getClientNumber() {
		return clientNumber;
	}
	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getIfName() {
		return ifName;
	}
	public void setIfName(String ifName) {
		this.ifName = ifName;
	}
	public String getLogEvent() {
		return logEvent;
	}
	public void setLogEvent(String logEvent) {
		this.logEvent = logEvent;
	}
	public String getIfType() {
		return ifType;
	}
	public void setIfType(String ifType) {
		this.ifType = ifType;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
}
