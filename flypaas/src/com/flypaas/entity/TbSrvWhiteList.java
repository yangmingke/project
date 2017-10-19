package com.flypaas.entity;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias(value="tb_srv_white_list")
public class TbSrvWhiteList {

	private String appSid ;
	private String whiteAddress ;
	private String wType ;
	private String brandId ;
	//temp表适用
	private String status;
	private Date createDate;
	private Date updateDate;
	
	
	public String getAppSid() {
		return appSid;
	}
	public void setAppSid(String appSid) {
		this.appSid = appSid;
	}
	public String getWhiteAddress() {
		return whiteAddress;
	}
	public void setWhiteAddress(String whiteAddress) {
		this.whiteAddress = whiteAddress;
	}
	public String getwType() {
		return wType;
	}
	public void setwType(String wType) {
		this.wType = wType;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
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
	
	
	
}
