package com.flypaas.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias(value="tb_bill_client_balance")
public class ClientBalance implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long acctId ;
	private String sid ;
	private String clientNumber ;
	private long  balance;
	private String enableFlag ;
	private Date createTime;
	private Date validDate ;
	private Date vipValidDate;
	public long getAcctId() {
		return acctId;
	}
	public void setAcctId(long acctId) {
		this.acctId = acctId;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getClientNumber() {
		return clientNumber;
	}
	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public String getEnableFlag() {
		return enableFlag;
	}
	public void setEnableFlag(String enableFlag) {
		this.enableFlag = enableFlag;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getValidDate() {
		return validDate;
	}
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	public Date getVipValidDate() {
		return vipValidDate;
	}
	public void setVipValidDate(Date vipValidDate) {
		this.vipValidDate = vipValidDate;
	}
	
	

}
