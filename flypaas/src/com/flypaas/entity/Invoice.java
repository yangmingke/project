package com.flypaas.entity;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias(value="tb_srv_invoice")
public class Invoice {

	private long id;
	private String sid;
	private String opentype;
	private long money;
	private String title;
	private String type;
	private String identificationnum;
	private String identificationimg;
	private String bankaccount;
	private String bankaddr;
	private String status;
	private long postaddr;
	private Date createDate;
	private Date updateDate;
	
	private String beginDate;
	private String endDate;
	
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
	public String getOpentype() {
		return opentype;
	}
	public void setOpentype(String opentype) {
		this.opentype = opentype;
	}
	public long getMoney() {
		return money;
	}
	public void setMoney(long money) {
		this.money = money;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIdentificationnum() {
		return identificationnum;
	}
	public void setIdentificationnum(String identificationnum) {
		this.identificationnum = identificationnum;
	}
	public String getIdentificationimg() {
		return identificationimg;
	}
	public void setIdentificationimg(String identificationimg) {
		this.identificationimg = identificationimg;
	}
	public String getBankaddr() {
		return bankaddr;
	}
	public void setBankaddr(String bankaddr) {
		this.bankaddr = bankaddr;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getPostaddr() {
		return postaddr;
	}
	public void setPostaddr(long postaddr) {
		this.postaddr = postaddr;
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
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getBankaccount() {
		return bankaccount;
	}
	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}
	
	
	
}
