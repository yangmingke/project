package com.ucpaas.commonservice.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ucpaas.commonservice.model.base.BaseInfo;
/**
 * client分表实体类
 * @author luke
 *
 */
@JsonInclude(Include.NON_NULL)
public class ClientInfo extends BaseInfo {
	private static final long serialVersionUID = 1498829890965993871L;

	private Integer uin;

    private String clientSid;

    private String clientToken;

    private String friendlyName;

    private String clientNumber;

    private String clientPwd;

    private String status;

    private String appSid;

    private String sid;

    private Long charge;

    private String chargeType;

    private String clientType;

    private Date createDate;

    private Date updateDate;

    private String mobile;

    private Integer isShowNbr;

    private Integer isCallFrDel;

    private String nickname;

    private String userid;

    private String portraituri;
	
    //1验证，0未验证;default=0;2015-08-24添加
	private Integer isVerify;
    
    //分表信息
    private String uin_mod;
    
    //子账号是否收费，0：免费；1：收费，默认值0；
    private Integer isFee;
    
    
    //clientNumber前5位
    private Integer clnPrefix;
    
    //开发者token，供生成新clientToken使用
    private String userToken;
    

	public ClientInfo() {
	}
	

	public String getUin_mod() {
		return uin_mod;
	}

	public void setUin_mod(String uin_mod) {
		this.uin_mod = uin_mod;
	}

	public Integer getUin() {
        return uin;
    }

    public void setUin(Integer uin) {
        this.uin = uin;
    }

    public String getClientSid() {
        return clientSid;
    }

    public void setClientSid(String clientSid) {
        this.clientSid = clientSid == null ? null : clientSid.trim();
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken == null ? null : clientToken.trim();
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName == null ? null : friendlyName.trim();
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber == null ? null : clientNumber.trim();
    }

    public String getClientPwd() {
        return clientPwd;
    }

    public void setClientPwd(String clientPwd) {
        this.clientPwd = clientPwd == null ? null : clientPwd.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

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

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType == null ? null : clientType.trim();
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getIsShowNbr() {
        return isShowNbr;
    }

    public void setIsShowNbr(Integer isShowNbr) {
        this.isShowNbr = isShowNbr;
    }

    public Integer getIsCallFrDel() {
        return isCallFrDel;
    }

    public void setIsCallFrDel(Integer isCallFrDel) {
        this.isCallFrDel = isCallFrDel;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getPortraituri() {
        return portraituri;
    }

    public void setPortraituri(String portraituri) {
        this.portraituri = portraituri == null ? null : portraituri.trim();
    }

    public Integer getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Integer isVerify) {
        this.isVerify = isVerify;
    }


	public Integer getIsFee() {
		return isFee;
	}


	public void setIsFee(Integer isFee) {
		this.isFee = isFee;
	}


	public Integer getClnPrefix() {
		return clnPrefix;
	}


	public void setClnPrefix(Integer clnPrefix) {
		this.clnPrefix = clnPrefix;
	}


	public String getUserToken() {
		return userToken;
	}


	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	
	
    
    
    
    
}