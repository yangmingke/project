package com.flypaas.model;

import java.util.Date;

public class TbRsBillAcctRTPP {
    private Long id;

    private String ip;
    
    private String sid;

    private Date startTime;

    private Date endTime;

    private String mainSid;

    private Integer trafficIn;

    private Integer trafficOut;

    private Integer trafficTotal;

    private Float price;

    private Double fee;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getMainSid() {
        return mainSid;
    }

    public void setMainSid(String mainSid) {
        this.mainSid = mainSid == null ? null : mainSid.trim();
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

    public Integer getTrafficTotal() {
        return trafficTotal;
    }

    public void setTrafficTotal(Integer trafficTotal) {
        this.trafficTotal = trafficTotal;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
}