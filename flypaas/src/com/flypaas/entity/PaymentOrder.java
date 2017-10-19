package com.flypaas.entity;

import java.util.Date;

import org.apache.ibatis.type.Alias;
@Alias(value="tb_srv_payment_order")
public class PaymentOrder {

	private long orderId ;
	private String sid ;
	private long charge ;
	private String chargeType ;
	private String status ;
	private Date createDate ;
	private Date payDate ;
	private Date payResultDate;
	private long chargeBalance;
	private String payId;
	private String accountType;
	private String appSid;
	private String beginDate;
	private String endDate;
	
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public long getCharge() {
		return charge;
	}
	public void setCharge(long charge) {
		this.charge = charge;
	}
	public String getChargeType() {
		return chargeType;
	}
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Date getPayResultDate() {
		return payResultDate;
	}
	public void setPayResultDate(Date payResultDate) {
		this.payResultDate = payResultDate;
	}
	public long getChargeBalance() {
		return chargeBalance;
	}
	public void setChargeBalance(long chargeBalance) {
		this.chargeBalance = chargeBalance;
	}
	public String getPayId() {
		return payId;
	}
	public void setPayId(String payId) {
		this.payId = payId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAppSid() {
		return appSid;
	}
	public void setAppSid(String appSid) {
		this.appSid = appSid;
	}
	
	
}
