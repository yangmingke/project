package com.flypaas.entity;

import java.util.Date;

import org.apache.ibatis.type.Alias;
@Alias(value="tb_flypaas_security_deduction_log")
public class SecurityDeductionLog {
	private long id;
	private String sid;
	private long charge;
	private String frmAccountType;
	private String toAccountType;
	private long frmId;
	private long toId;
	private String banknum;
	private String bankaddr;
	private String company;
	private Date createDate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public long getCharge() {
		return charge;
	}
	public void setCharge(long charge) {
		this.charge = charge;
	}
	
	public String getFrmAccountType() {
		return frmAccountType;
	}
	public void setFrmAccountType(String frmAccountType) {
		this.frmAccountType = frmAccountType;
	}
	public String getToAccountType() {
		return toAccountType;
	}
	public void setToAccountType(String toAccountType) {
		this.toAccountType = toAccountType;
	}
	public long getFrmId() {
		return frmId;
	}
	public void setFrmId(long frmId) {
		this.frmId = frmId;
	}
	public long getToId() {
		return toId;
	}
	public void setToId(long toId) {
		this.toId = toId;
	}
	public String getBanknum() {
		return banknum;
	}
	public void setBanknum(String banknum) {
		this.banknum = banknum;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getBankaddr() {
		return bankaddr;
	}
	public void setBankaddr(String bankaddr) {
		this.bankaddr = bankaddr;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	
}
