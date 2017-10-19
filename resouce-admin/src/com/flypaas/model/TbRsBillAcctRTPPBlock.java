package com.flypaas.model;

import java.util.Date;

public class TbRsBillAcctRTPPBlock {
    private Long id;

    private String ip;

    private Date timeBlock;

    private String mainSid;

    private Integer trafficIn;

    private Integer trafficOut;

    private Float price;

    private Double balance;

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

    public Date getTimeBlock() {
        return timeBlock;
    }

    public void setTimeBlock(Date timeBlock) {
        this.timeBlock = timeBlock;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}