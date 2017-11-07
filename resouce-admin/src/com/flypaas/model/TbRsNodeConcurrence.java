package com.flypaas.model;

import java.util.Date;

public class TbRsNodeConcurrence {
    private Integer id;
    private String ip;
    private Integer concurrence;
    private Date datetime;
    private String datetimeStr;
    private String month;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getConcurrence() {
		return concurrence;
	}
	public void setConcurrence(Integer concurrence) {
		this.concurrence = concurrence;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDatetimeStr() {
		return datetimeStr;
	}
	public void setDatetimeStr(String datetimeStr) {
		this.datetimeStr = datetimeStr;
	}
}