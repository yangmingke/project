package com.yzx.rest.models;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "account")
public class Account extends BaseInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5202434864787522163L;
	private String accountSid;
	private String createDate;
	private String updateDate;
	private String nickName;
//	private String userType;
	private String status;
	private String token;
	private String balance;
	private String username;
	private String email;
	private String mobile;
	private String type;
	private String account;
	private String password;
	
//	private String address;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAccountSid() {
		return accountSid;
	}
	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	/*public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}*/
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
