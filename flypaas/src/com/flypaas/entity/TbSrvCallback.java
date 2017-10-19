package com.flypaas.entity;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias(value="tb_srv_callback")
public class TbSrvCallback {

	private long cbId ;
	private String callAddress ;
	private int callType ;
	private String appSid ;
	private String method;
	
	//temp表适用
	private String status;
	private Date createDate;
	private Date updateDate;
	
	public long getCbId() {
		return cbId;
	}
	public void setCbId(long cbId) {
		this.cbId = cbId;
	}
	public String getCallAddress() {
		return callAddress;
	}
	public void setCallAddress(String callAddress) {
		this.callAddress = callAddress;
	}
	public int getCallType() {
		return callType;
	}
	public void setCallType(int callType) {
		this.callType = callType;
	}
	public String getAppSid() {
		return appSid;
	}
	public void setAppSid(String appSid) {
		this.appSid = appSid;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
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
