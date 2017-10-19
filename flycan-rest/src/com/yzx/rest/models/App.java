package com.yzx.rest.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "app")
public class App extends BaseInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1700541653944696760L;

	private String balance;

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}
	private String minute;
	
	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}
	private String appId;
	private String sid;
	private String appName;
	private String appKind;
	private String isShowNbr;
	private String industry;
	private String internation;
	private String curConc;
	
	public String getCurConc() {
		return curConc;
	}

	public void setCurConc(String curConc) {
		this.curConc = curConc;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppKind() {
		return appKind;
	}

	public void setAppKind(String appKind) {
		this.appKind = appKind;
	}

	public String getIsShowNbr() {
		return isShowNbr;
	}

	public void setIsShowNbr(String isShowNbr) {
		this.isShowNbr = isShowNbr;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getInternation() {
		return internation;
	}

	public void setInternation(String internation) {
		this.internation = internation;
	}
}
