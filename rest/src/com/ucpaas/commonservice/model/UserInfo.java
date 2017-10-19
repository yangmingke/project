package com.ucpaas.commonservice.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ucpaas.commonservice.model.base.BaseInfo;

/**
 * 用户表实体类
 * 
 * @author luke
 * 
 */
@JsonInclude(Include.NON_NULL)
public class UserInfo extends BaseInfo {
	private static final long serialVersionUID = -1904447708683894915L;

	private String sid;

	private String token;

	private String username;

	private String email;

	private String password;

	private String userType;

	private String status;

	private String oauthStatus;

	private String mobile;

	private String chatType;

	private String chatNbr;

	private Integer province;

	private Integer city;

	private String address;

	private String national;

	private String realname;

	private String idType;

	private String idNbr;

	private String orgId;

	private String oConType1;

	private String oConNbr1;

	private String oConType2;

	private String oConNbr2;

	private String oConType3;

	private String oConNbr3;

	private String legalPerson;

	private String companyNbr;

	private String webSite;

	private Date createDate;

	private Date updateDate;

	private Integer loginTimes;

	private Integer guide;

	private String revisability;

	private String randomNbr;

	private Date oauthDate;

	private Integer isContract;

	private Integer isHeavybuyer;

	private Integer channelId;

	private String authType;

	private String authId;

	private Integer isProxy;

	private Integer internRate;
	
	/**
	 * 开发者标记，0：老开发者；1：新开发者
	 * 2015-11-09添加，新老系统兼容需求
	 */
	private Integer userTag;
	

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid == null ? null : sid.trim();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token == null ? null : token.trim();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType == null ? null : userType.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getOauthStatus() {
		return oauthStatus;
	}

	public void setOauthStatus(String oauthStatus) {
		this.oauthStatus = oauthStatus == null ? null : oauthStatus.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getChatType() {
		return chatType;
	}

	public void setChatType(String chatType) {
		this.chatType = chatType == null ? null : chatType.trim();
	}

	public String getChatNbr() {
		return chatNbr;
	}

	public void setChatNbr(String chatNbr) {
		this.chatNbr = chatNbr == null ? null : chatNbr.trim();
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getNational() {
		return national;
	}

	public void setNational(String national) {
		this.national = national == null ? null : national.trim();
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname == null ? null : realname.trim();
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType == null ? null : idType.trim();
	}

	public String getIdNbr() {
		return idNbr;
	}

	public void setIdNbr(String idNbr) {
		this.idNbr = idNbr == null ? null : idNbr.trim();
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId == null ? null : orgId.trim();
	}

	public String getoConType1() {
		return oConType1;
	}

	public void setoConType1(String oConType1) {
		this.oConType1 = oConType1 == null ? null : oConType1.trim();
	}

	public String getoConNbr1() {
		return oConNbr1;
	}

	public void setoConNbr1(String oConNbr1) {
		this.oConNbr1 = oConNbr1 == null ? null : oConNbr1.trim();
	}

	public String getoConType2() {
		return oConType2;
	}

	public void setoConType2(String oConType2) {
		this.oConType2 = oConType2 == null ? null : oConType2.trim();
	}

	public String getoConNbr2() {
		return oConNbr2;
	}

	public void setoConNbr2(String oConNbr2) {
		this.oConNbr2 = oConNbr2 == null ? null : oConNbr2.trim();
	}

	public String getoConType3() {
		return oConType3;
	}

	public void setoConType3(String oConType3) {
		this.oConType3 = oConType3 == null ? null : oConType3.trim();
	}

	public String getoConNbr3() {
		return oConNbr3;
	}

	public void setoConNbr3(String oConNbr3) {
		this.oConNbr3 = oConNbr3 == null ? null : oConNbr3.trim();
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson == null ? null : legalPerson.trim();
	}

	public String getCompanyNbr() {
		return companyNbr;
	}

	public void setCompanyNbr(String companyNbr) {
		this.companyNbr = companyNbr == null ? null : companyNbr.trim();
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite == null ? null : webSite.trim();
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

	public Integer getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	public Integer getGuide() {
		return guide;
	}

	public void setGuide(Integer guide) {
		this.guide = guide;
	}

	public String getRevisability() {
		return revisability;
	}

	public void setRevisability(String revisability) {
		this.revisability = revisability == null ? null : revisability.trim();
	}

	public String getRandomNbr() {
		return randomNbr;
	}

	public void setRandomNbr(String randomNbr) {
		this.randomNbr = randomNbr == null ? null : randomNbr.trim();
	}

	public Date getOauthDate() {
		return oauthDate;
	}

	public void setOauthDate(Date oauthDate) {
		this.oauthDate = oauthDate;
	}

	public Integer getIsContract() {
		return isContract;
	}

	public void setIsContract(Integer isContract) {
		this.isContract = isContract;
	}

	public Integer getIsHeavybuyer() {
		return isHeavybuyer;
	}

	public void setIsHeavybuyer(Integer isHeavybuyer) {
		this.isHeavybuyer = isHeavybuyer;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType == null ? null : authType.trim();
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId == null ? null : authId.trim();
	}

	public Integer getIsProxy() {
		return isProxy;
	}

	public void setIsProxy(Integer isProxy) {
		this.isProxy = isProxy;
	}

	public Integer getInternRate() {
		return internRate;
	}

	public void setInternRate(Integer internRate) {
		this.internRate = internRate;
	}

	public Integer getUserTag() {
		return userTag;
	}

	public void setUserTag(Integer userTag) {
		this.userTag = userTag;
	}
	
	
}