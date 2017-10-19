package com.ucpaas.commonservice.model;

import java.util.Date;

import com.ucpaas.commonservice.model.base.BaseInfo;

/**
 * TestClient总表实体类
 * 
 * @author luke
 *
 */
public class TestClientInfo extends BaseInfo {
	private static final long serialVersionUID = 2740913693344798840L;

	private Integer uin;

	private String clientSid;

	private String clientToken;

	private String friendlyName;

	private String clientNumber;

	private String clientPwd;

	private String status;

	private String appSid;

	private String sid;

	private Integer charge;

	private String chargeType;

	private String clientType;

	private Date createDate;

	private Date updateDate;

	private String mobile;

	private Integer isShowNbr;

	private Integer isCallFr;

	public TestClientInfo() {
	}

	public TestClientInfo(ClientInfo clientInfo) {

		this.uin = clientInfo.getUin();
		this.appSid = clientInfo.getAppSid();
		this.charge = new Integer(clientInfo.getCharge().toString());
		this.chargeType = clientInfo.getChargeType();
		this.clientNumber = clientInfo.getClientNumber();
		this.clientPwd = clientInfo.getClientPwd();
		this.clientSid = clientInfo.getClientSid();
		this.clientToken = clientInfo.getClientToken();
		this.clientType = clientInfo.getClientType();
		this.createDate = clientInfo.getCreateDate();
		this.friendlyName = clientInfo.getFriendlyName();
		this.isCallFr = clientInfo.getIsCallFrDel();
		this.isShowNbr = clientInfo.getIsShowNbr();
		this.mobile = clientInfo.getMobile();
		this.sid = clientInfo.getSid();
		this.status = clientInfo.getStatus();
		this.updateDate = clientInfo.getUpdateDate();

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

	public Integer getCharge() {
		return charge;
	}

	public void setCharge(Integer charge) {
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

	public Integer getIsCallFr() {
		return isCallFr;
	}

	public void setIsCallFr(Integer isCallFr) {
		this.isCallFr = isCallFr;
	}
}