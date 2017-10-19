package com.flypaas.model;

import java.util.Date;

public class TbRsRTPPRealtimeStatus {
    private Integer id;

    private Date time;
    
    private String time1;

    private String ip;

    private String name;

    private Integer trafficIn;

    private Integer trafficOut;

    private Integer throughputIn;

    private Integer throughputOut;

	private Integer concurrency;

	
	
    public String getTime1() {
		return time1;
	}

	public void setTime1(String time1) {
		this.time1 = time1;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getConcurrency() {
        return concurrency;
    }

    public void setConcurrency(Integer concurrency) {
        this.concurrency = concurrency;
    }

    public Integer getTrafficIn() {
        return trafficIn;
    }

    public void setTrafficIn(Integer trafficIn) {
        this.trafficIn = trafficIn;
    }

    public Integer getTrafficOut() {
        return trafficOut;
    }

    public void setTrafficOut(Integer trafficOut) {
        this.trafficOut = trafficOut;
    }

    public Integer getThroughputIn() {
        return throughputIn;
    }

    public void setThroughputIn(Integer throughputIn) {
        this.throughputIn = throughputIn;
    }

    public Integer getThroughputOut() {
        return throughputOut;
    }

    public void setThroughputOut(Integer throughputOut) {
        this.throughputOut = throughputOut;
    }
}