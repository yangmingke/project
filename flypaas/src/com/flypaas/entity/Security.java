package com.flypaas.entity;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias(value="tb_flypaas_security")
public class Security {

	private long id;
	private String sid;
	private String status;
	private String agreenmentFile;
	private Date createDate;
	private Date updateDate;
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getAgreenmentFile() {
		return agreenmentFile;
	}
	public void setAgreenmentFile(String agreenmentFile) {
		this.agreenmentFile = agreenmentFile;
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
