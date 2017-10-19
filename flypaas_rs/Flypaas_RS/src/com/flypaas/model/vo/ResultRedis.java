package com.flypaas.model.vo;
/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年1月17日 下午7:03:10
* 类说明
*/
public class ResultRedis {
	private String ip;
	private String name;
	private String status;
	private int bandwidthLimit;
	private int trafficIn;
	private int trafficOut;
	private int concurrency;
	
	
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
	public int getBandwidthLimit() {
		return bandwidthLimit;
	}
	public void setBandwidthLimit(int bandwidthLimit) {
		this.bandwidthLimit = bandwidthLimit;
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
	
	
}
