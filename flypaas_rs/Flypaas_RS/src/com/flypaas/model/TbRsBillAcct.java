package com.flypaas.model;

import java.util.Date;

public class TbRsBillAcct {
    private Long id;

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
}