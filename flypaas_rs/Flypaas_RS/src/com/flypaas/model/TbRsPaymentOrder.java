package com.flypaas.model;

import java.util.Date;

public class TbRsPaymentOrder {
    private Long orderId;

    private String netSid;

    private Long charge;

    private String chargeType;

    private String status;

    private Date createDate;

    private Date payDate;

    private Date payResultDate;

    private Long chargeBalance;

    private String payId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getNetSid() {
        return netSid;
    }

    public void setNetSid(String netSid) {
        this.netSid = netSid == null ? null : netSid.trim();
    }

    public Long getCharge() {
        return charge;
    }

    public void setCharge(Long charge) {
        this.charge = charge;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType == null ? null : chargeType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getPayResultDate() {
        return payResultDate;
    }

    public void setPayResultDate(Date payResultDate) {
        this.payResultDate = payResultDate;
    }

    public Long getChargeBalance() {
        return chargeBalance;
    }

    public void setChargeBalance(Long chargeBalance) {
        this.chargeBalance = chargeBalance;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId == null ? null : payId.trim();
    }
}