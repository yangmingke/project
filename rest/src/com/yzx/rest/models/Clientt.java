package com.yzx.rest.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "client")
public class Clientt {

	private String client_pwd;
	private String client_number;
	private String mobile;
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getClient_pwd() {
		return client_pwd;
	}
	public void setClient_pwd(String client_pwd) {
		this.client_pwd = client_pwd;
	}
	public String getClient_number() {
		return client_number;
	}
	public void setClient_number(String client_number) {
		this.client_number = client_number;
	}
	
	
}
