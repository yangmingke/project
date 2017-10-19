package com.ucp.bean;

public class ClientFee extends BaseInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4261725522065593912L;
	private String orderId;
	private String acctId;
	private String clientId;
	private long fee;
	private long preBalance;
	private String stype;
	private String clientCount;
	private String createDate;
	private String appId;
	private long clientBalance;
	private String clientName;
	private String clientNumber;
	private String pwd;
	private String token;
	private String status;
	private String charges;
	private String clientType;
	private String mobile;
	private String cType;
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCharges() {
		return charges;
	}
	public void setCharges(String charges) {
		this.charges = charges;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getcType() {
		return cType;
	}
	public void setcType(String cType) {
		this.cType = cType;
	}
	public String getClientNumber() {
		return clientNumber;
	}
	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public long getClientBalance() {
		return clientBalance;
	}
	public void setClientBalance(long clientBalance) {
		this.clientBalance = clientBalance;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getAcctId() {
		return acctId;
	}
	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public long getPreBalance() {
		return preBalance;
	}
	public void setPreBalance(long preBalance) {
		this.preBalance = preBalance;
	}
	public String getClientCount() {
		return clientCount;
	}
	public void setClientCount(String clientCount) {
		this.clientCount = clientCount;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public long getFee() {
		return fee;
	}
	public void setFee(long fee) {
		this.fee = fee;
	}
	public String getStype() {
		return stype;
	}
	public void setStype(String stype) {
		this.stype = stype;
	}

}
