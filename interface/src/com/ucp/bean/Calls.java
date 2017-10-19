package com.ucp.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "call")
public class Calls extends BaseInfo{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2293376010782054769L;

	private String appId;
	private String body;
	private String msgType;
	private String fromClient;
	private String to;
	private String toSerNum;
	private String fromSerNum;
	private String param;
	
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
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
	public String getFromSerNum() {
		return fromSerNum;
	}
	public void setFromSerNum(String fromSerNum) {
		this.fromSerNum = fromSerNum;
	}
	
}
