package com.network.monitor.entity;

import java.sql.Timestamp;

/**
 * 路由封装类
 *
 */
public class RouteVO {

	/**
	 * 路由ID
	 */
	private Integer id;
	
	/**
	 * 源SR节点ID
	 */
	private String sr_id;
	
	/**
	 * 目的网络地址
	 */
	private String dest;
	
	/**
	 * 目的网络掩码
	 */
	private String mask;
	
	/**
	 * 下一跳IP地址
	 */
	private String next_hop;
	
	/**
	 * 出口接口名
	 */
	private String iface;
	
	/**
	 * 链路消耗值
	 */
	private String metric;
	
	/**
	 * 上报时间
	 */
	private Timestamp time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSr_id() {
		return sr_id;
	}

	public void setSr_id(String sr_id) {
		this.sr_id = sr_id;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public String getNext_hop() {
		return next_hop;
	}

	public void setNext_hop(String next_hop) {
		this.next_hop = next_hop;
	}

	public String getIface() {
		return iface;
	}

	public void setIface(String iface) {
		this.iface = iface;
	}

	public String getMetric() {
		return metric;
	}

	public void setMetric(String metric) {
		this.metric = metric;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}
}
