package com.flypaas.entity;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias(value="tb_flypaas_application_shownbrs")
public class AppShowNbrs {

	private long id ;
	private String appSid;
	private String sid;
	private String nbrType;
	private String nbr;
	private String status;
	private Date createDate;
	private Date updateDate;
	
	private String newNbr;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAppSid() {
		return appSid;
	}
	public void setAppSid(String appSid) {
		this.appSid = appSid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getNbr() {
		return nbr;
	}
	public void setNbr(String nbr) {
		this.nbr = nbr;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNbrType() {
		return nbrType;
	}
	public void setNbrType(String nbrType) {
		this.nbrType = nbrType;
	}
	public String getNewNbr() {
		return newNbr;
	}
	public void setNewNbr(String newNbr) {
		this.newNbr = newNbr;
	}
	
	
	
}
