package com.ucpaas.commonservice.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ucpaas.commonservice.model.base.BaseInfo;

/**
 * 应用表实体类
 * @author luke
 *
 */
@JsonInclude(Include.NON_NULL)
public class AppInfo extends BaseInfo {
	private static final long serialVersionUID = -603798362983263739L;

	private String appSid;

    private String sid;

    private String appName;

    private String appSignature;

    private String appKind;

    private String isIvr;

    private String isTts;

    private String isShowNbr;

    private String isVoicecodenbrStatus;

    private String isOfficernbrStatus;

    private String industry;

    private String status;

    private String ivrTestNbr;

    private String smsMsgNbr;

    private Date createDate;

    private Date updateDate;

    private String trusteeship;

    private String appType;

    private Date auditDate;

    private String brand;

    private Integer callFr;

    private String ckKey;

    private String ckCallbackUrl;

    private Integer ckEnddate;

    private Integer ckWay;

    private Integer ckNum;

    private Integer fileTimeout;

    private Integer clientcount;

    public String getAppSid() {
        return appSid;
    }

    public void setAppSid(String appSid) {
        this.appSid = appSid == null ? null : appSid.trim();
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public String getAppSignature() {
        return appSignature;
    }

    public void setAppSignature(String appSignature) {
        this.appSignature = appSignature == null ? null : appSignature.trim();
    }

    public String getAppKind() {
        return appKind;
    }

    public void setAppKind(String appKind) {
        this.appKind = appKind == null ? null : appKind.trim();
    }

    public String getIsIvr() {
        return isIvr;
    }

    public void setIsIvr(String isIvr) {
        this.isIvr = isIvr == null ? null : isIvr.trim();
    }

    public String getIsTts() {
        return isTts;
    }

    public void setIsTts(String isTts) {
        this.isTts = isTts == null ? null : isTts.trim();
    }

    public String getIsShowNbr() {
        return isShowNbr;
    }

    public void setIsShowNbr(String isShowNbr) {
        this.isShowNbr = isShowNbr == null ? null : isShowNbr.trim();
    }

    public String getIsVoicecodenbrStatus() {
        return isVoicecodenbrStatus;
    }

    public void setIsVoicecodenbrStatus(String isVoicecodenbrStatus) {
        this.isVoicecodenbrStatus = isVoicecodenbrStatus == null ? null : isVoicecodenbrStatus.trim();
    }

    public String getIsOfficernbrStatus() {
        return isOfficernbrStatus;
    }

    public void setIsOfficernbrStatus(String isOfficernbrStatus) {
        this.isOfficernbrStatus = isOfficernbrStatus == null ? null : isOfficernbrStatus.trim();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getIvrTestNbr() {
        return ivrTestNbr;
    }

    public void setIvrTestNbr(String ivrTestNbr) {
        this.ivrTestNbr = ivrTestNbr == null ? null : ivrTestNbr.trim();
    }

    public String getSmsMsgNbr() {
        return smsMsgNbr;
    }

    public void setSmsMsgNbr(String smsMsgNbr) {
        this.smsMsgNbr = smsMsgNbr == null ? null : smsMsgNbr.trim();
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
        this.trusteeship = trusteeship == null ? null : trusteeship.trim();
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType == null ? null : appType.trim();
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
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
        this.ckKey = ckKey == null ? null : ckKey.trim();
    }

    public String getCkCallbackUrl() {
        return ckCallbackUrl;
    }

    public void setCkCallbackUrl(String ckCallbackUrl) {
        this.ckCallbackUrl = ckCallbackUrl == null ? null : ckCallbackUrl.trim();
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

    public Integer getFileTimeout() {
        return fileTimeout;
    }

    public void setFileTimeout(Integer fileTimeout) {
        this.fileTimeout = fileTimeout;
    }

    public Integer getClientcount() {
        return clientcount;
    }

    public void setClientcount(Integer clientcount) {
        this.clientcount = clientcount;
    }
}