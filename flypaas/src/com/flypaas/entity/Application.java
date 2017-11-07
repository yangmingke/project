package com.flypaas.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;
@Alias(value="tb_ucpaas_application")
public class Application implements Serializable {

	private static final long serialVersionUID = 6114834085064037034L;
	private String appSid ;
	private String sid;
	private String appName;
	private String appKind ;
	private String isIvr ;
	private String isTts;
	private String isShowNbr;
	private String isVoicecodenbrStatus;
	private String isOfficernbrStatus;
	private String industry;
	private String status ;
	private String ivrTestNbr ;
	private String smsMsgNbr;
	private Date createDate ;
	private Date updateDate ;
	private String trusteeship;
	private String appType ;
	private Date auditDate;
	private String paramValue;
	private String brand;
	private String maxHopNum;
	private String routeNum;
	private String routePolicy;
	private String nodeMaxPrice;
	private Integer callFr;
	private String ckKey;
	private String ckCallbackUrl;
	private Integer ckEnddate;
	private Integer ckWay;
	private Integer ckNum;
	
	private double balance;
	private String blcStatus;
	private int fileTimeout;
	private String oauthFileUrl;
	private String oauthAppId;
	
	public String getAppKind() {
		return appKind;
	}
	public void setAppKind(String appKind) {
		this.appKind = appKind;
	}
	public String getAppSid() {
		return appSid;
	}
	public void setAppSid(String appSid) {
		this.appSid = appSid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getIsIvr() {
		return isIvr;
	}
	public void setIsIvr(String isIvr) {
		this.isIvr = isIvr;
	}
	public String getIsTts() {
		return isTts;
	}
	public void setIsTts(String isTts) {
		this.isTts = isTts;
	}
	public String getIsShowNbr() {
		return isShowNbr;
	}
	public void setIsShowNbr(String isShowNbr) {
		this.isShowNbr = isShowNbr;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIvrTestNbr() {
		return ivrTestNbr;
	}
	public void setIvrTestNbr(String ivrTestNbr) {
		this.ivrTestNbr = ivrTestNbr;
	}
	public String getSmsMsgNbr() {
		return smsMsgNbr;
	}
	public void setSmsMsgNbr(String smsMsgNbr) {
		this.smsMsgNbr = smsMsgNbr;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getTrusteeship() {
		return trusteeship;
	}
	public void setTrusteeship(String trusteeship) {
		this.trusteeship = trusteeship;
	}
	public Date getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	public String getIsVoicecodenbrStatus() {
		return isVoicecodenbrStatus;
	}
	public void setIsVoicecodenbrStatus(String isVoicecodenbrStatus) {
		this.isVoicecodenbrStatus = isVoicecodenbrStatus;
	}
	public String getIsOfficernbrStatus() {
		return isOfficernbrStatus;
	}
	public void setIsOfficernbrStatus(String isOfficernbrStatus) {
		this.isOfficernbrStatus = isOfficernbrStatus;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public Integer getCallFr() {
		return callFr;
	}
	public void setCallFr(Integer callFr) {
		this.callFr = callFr;
	}
	public String getCkKey() {
		return ckKey;
	}
	public void setCkKey(String ckKey) {
		this.ckKey = ckKey;
	}
	public String getCkCallbackUrl() {
		return ckCallbackUrl;
	}
	public void setCkCallbackUrl(String ckCallbackUrl) {
		this.ckCallbackUrl = ckCallbackUrl;
	}
	public Integer getCkEnddate() {
		return ckEnddate;
	}
	public void setCkEnddate(Integer ckEnddate) {
		this.ckEnddate = ckEnddate;
	}
	public Integer getCkWay() {
		return ckWay;
	}
	public void setCkWay(Integer ckWay) {
		this.ckWay = ckWay;
	}
	public Integer getCkNum() {
		return ckNum;
	}
	public void setCkNum(Integer ckNum) {
		this.ckNum = ckNum;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getBlcStatus() {
		return blcStatus;
	}
	public void setBlcStatus(String blcStatus) {
		this.blcStatus = blcStatus;
	}
	public int getFileTimeout() {
		return fileTimeout;
	}
	public void setFileTimeout(int fileTimeout) {
		this.fileTimeout = fileTimeout;
	}
	public String getOauthFileUrl() {
		return oauthFileUrl;
	}
	public void setOauthFileUrl(String oauthFileUrl) {
		this.oauthFileUrl = oauthFileUrl;
	}
	public String getOauthAppId() {
		return oauthAppId;
	}
	public void setOauthAppId(String oauthAppId) {
		this.oauthAppId = oauthAppId;
	}
	public String getMaxHopNum() {
		return maxHopNum;
	}
	public void setMaxHopNum(String maxHopNum) {
		this.maxHopNum = maxHopNum;
	}
	public String getRouteNum() {
		return routeNum;
	}
	public void setRouteNum(String routeNum) {
		this.routeNum = routeNum;
	}
	public String getRoutePolicy() {
		return routePolicy;
	}
	public void setRoutePolicy(String routePolicy) {
		this.routePolicy = routePolicy;
	}
	public String getNodeMaxPrice() {
		return nodeMaxPrice;
	}
	public void setNodeMaxPrice(String nodeMaxPrice) {
		this.nodeMaxPrice = nodeMaxPrice;
	}
	
}
