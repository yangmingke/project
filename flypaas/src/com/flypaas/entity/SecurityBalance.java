package com.flypaas.entity;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias(value="tb_flypaas_security_balance")
public class SecurityBalance {

	private long id;
	private long securityId;
	private String sid;
	private long balance;
	private String status;
	private String relieveType;
	private Date createDate;
	private Date updateDate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getSecurityId() {
		return securityId;
	}
	public void setSecurityId(long securityId) {
		this.securityId = securityId;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRelieveType() {
		return relieveType;
	}
	public void setRelieveType(String relieveType) {
		this.relieveType = relieveType;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
	
}
