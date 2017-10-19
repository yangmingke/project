package com.flypaas.entity;

import java.util.Date;

import org.apache.ibatis.type.Alias;
@Alias(value="tb_flypaas_user_log")
public class UserLog {

	private int id;
	private String sid;
	private String token;
	private Date createDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
