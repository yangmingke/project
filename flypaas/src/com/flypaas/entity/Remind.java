package com.flypaas.entity;

import org.apache.ibatis.type.Alias;

@Alias(value="tb_srv_remind")
public class Remind {

	private long remindId ;
	private String method ;
	private String duration ;
	private String sid ;
	private long money ;
	private String rate ;
	private String status;
	private int remindType;
	
	public long getRemindId() {
		return remindId;
	}
	public void setRemindId(long remindId) {
		this.remindId = remindId;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public long getMoney() {
		return money;
	}
	public void setMoney(long money) {
		this.money = money;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getRemindType() {
		return remindType;
	}
	public void setRemindType(int remindType) {
		this.remindType = remindType;
	}
	
	
}
