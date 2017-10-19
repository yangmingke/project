package com.flypaas.entity;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias(value="tb_flypaas_security_relieve_applyfor")
public class SecurityRelieveApplyfor {

	private Long id ;
	private String sid;
	private Long securityId;
	private Long banknum;
	private String bankaddr;
	private String company;
	private String status;
	private Date createDate;
	private Date updateDate;
	private Long money;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public Long getBanknum() {
		return banknum;
	}
	public void setBanknum(Long banknum) {
		this.banknum = banknum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public Long getSecurityId() {
		return securityId;
	}
	public void setSecurityId(Long securityId) {
		this.securityId = securityId;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getBankaddr() {
		return bankaddr;
	}
	public void setBankaddr(String bankaddr) {
		this.bankaddr = bankaddr;
	}
	public Long getMoney() {
		return money;
	}
	public void setMoney(Long money) {
		this.money = money;
	}
	
	
	
}
