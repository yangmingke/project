package com.flypaas.model.vo;
/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年1月17日 下午7:03:10
* 类说明
*/
public class ResultRedis {
	private String netSid;
	private String netName;
	private String ip;
	private String name;
	private String status;
	private int concurrencyLimit;
	private int trafficIn;
	private int trafficOut;
	private int concurrency;
	private String uptime;
	private String version;
	private String username;
	
	
	
	public String getNetSid() {
		return netSid;
	}
	public void setNetSid(String netSid) {
		this.netSid = netSid;
	}
	
	public String getNetName() {
		return netName;
	}
	public void setNetName(String netName) {
		this.netName = netName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getConcurrencyLimit() {
		return concurrencyLimit;
	}
	public void setConcurrencyLimit(int concurrencyLimit) {
		this.concurrencyLimit = concurrencyLimit;
	}
	public int getTrafficIn() {
		return trafficIn;
	}
	public void setTrafficIn(int trafficIn) {
		this.trafficIn = trafficIn;
	}
	public int getTrafficOut() {
		return trafficOut;
	}
	public void setTrafficOut(int trafficOut) {
		this.trafficOut = trafficOut;
	}
	public int getConcurrency() {
		return concurrency;
	}
	public void setConcurrency(int concurrency) {
		this.concurrency = concurrency;
	}
	public String getUptime() {
		return uptime;
	}
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
