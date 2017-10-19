package com.flypaas.entity;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias(value="tb_bill_acct_balance")
public class AcctBalance {

	private long acctId ;
	private String sid;
	private double balance;
	private String enableFlag;
	private Date createTime;
	private Date validDate ;
	private Date vipValidDate;
	private double creditBalance;
	
	private double rechargeBalance;
	
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
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
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
	public double getCreditBalance() {
		return creditBalance;
	}
	public void setCreditBalance(double creditBalance) {
		this.creditBalance = creditBalance;
	}
	public double getRechargeBalance() {
		return rechargeBalance;
	}
	public void setRechargeBalance(double rechargeBalance) {
		this.rechargeBalance = rechargeBalance;
	}
	
}
