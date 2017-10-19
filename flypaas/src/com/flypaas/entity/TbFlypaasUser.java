package com.flypaas.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * @author  chenxijun
 * @version 
 */
@Alias(value="tb_flypaas_user")
public class TbFlypaasUser implements Serializable {
	private static final long serialVersionUID = -4415990281535582814L;
	private String sid;          	//主账号sid
	private String userName;		//用户昵称		
	private String email;			//邮件地址
	private String password;		//密码
	private String status;				//用户状态
	private String mobile;			//用户手机号码
	private String chatType ;			//聊天工具类型
	private String chatNbr;			//社交号码
	private Integer province;
	private Integer city;
	private String address;			//联系地址/公司地址
	private String userType;		//开发者类型
	private String national;			//国籍
	private String realname ;		//真实姓名/公司名称
	private String idType;				//证件类型
	private String idNbr ;			//证件号码
	private String orgId;			//机构号
	private String oconType1;			//其他联系类型1
	private String oconNbr1;		//其他联系号码1
	private String oconType2;			//其他联系类型2
	private String oconNbr2;		//其他联系号码2
	private String oconType3;			//其他联系类型3
	private String oconNbr3;		//其他联系号码1
	private String legalPerson;		//企业法人
	private String companyNbr;		//公司电话
	private String webSite;			//公司网址
	private String token ;			//开发者令牌
	private Date createDate ;		//创建时间
	private Date updateDate;		//更新时间
	private Integer loginTimes;         //登录次数
	private Integer guide ;             //新手指引
	private String revisability ;      //是否可以修改认证资料
	private String randomNbr;
	private String oauthStatus;
	private Date oauthDate;
	private Integer isContract;
	private Integer isHeavybuyer;
	private Integer channelId;
	private int countMsg;
	private String authType;		//第三方账号登录的类型，1：明道
	private String authId;			//第三方账号登录的用户id
	private int isProxy;
	private String superSid;
	private String agentUrl;
	private String agentLogo;
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getChatType() {
		return chatType;
	}
	public void setChatType(String chatType) {
		this.chatType = chatType;
	}
	public String getChatNbr() {
		return chatNbr;
	}
	public void setChatNbr(String chatNbr) {
		this.chatNbr = chatNbr;
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
		this.address = address;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getNational() {
		return national;
	}
	public void setNational(String national) {
		this.national = national;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdNbr() {
		return idNbr;
	}
	public void setIdNbr(String idNbr) {
		this.idNbr = idNbr;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOconType1() {
		return oconType1;
	}
	public void setOconType1(String oconType1) {
		this.oconType1 = oconType1;
	}
	public String getOconNbr1() {
		return oconNbr1;
	}
	public void setOconNbr1(String oconNbr1) {
		this.oconNbr1 = oconNbr1;
	}
	public String getOconType2() {
		return oconType2;
	}
	public void setOconType2(String oconType2) {
		this.oconType2 = oconType2;
	}
	public String getOconNbr2() {
		return oconNbr2;
	}
	public void setOconNbr2(String oconNbr2) {
		this.oconNbr2 = oconNbr2;
	}
	public String getOconType3() {
		return oconType3;
	}
	public void setOconType3(String oconType3) {
		this.oconType3 = oconType3;
	}
	public String getOconNbr3() {
		return oconNbr3;
	}
	public void setOconNbr3(String oconNbr3) {
		this.oconNbr3 = oconNbr3;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getCompanyNbr() {
		return companyNbr;
	}
	public void setCompanyNbr(String companyNbr) {
		this.companyNbr = companyNbr;
	}
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
		this.revisability = revisability;
	}
	public String getRandomNbr() {
		return randomNbr;
	}
	public void setRandomNbr(String randomNbr) {
		this.randomNbr = randomNbr;
	}
	public String getOauthStatus() {
		return oauthStatus;
	}
	public void setOauthStatus(String oauthStatus) {
		this.oauthStatus = oauthStatus;
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
	public int getCountMsg() {
		return countMsg;
	}
	public void setCountMsg(int countMsg) {
		this.countMsg = countMsg;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
		this.authType = authType;
	}
	public String getAuthId() {
		return authId;
	}
	public void setAuthId(String authId) {
		this.authId = authId;
	}
	public int getIsProxy() {
		return isProxy;
	}
	public void setIsProxy(int isProxy) {
		this.isProxy = isProxy;
	}
	public String getSuperSid() {
		return superSid;
	}
	public void setSuperSid(String superSid) {
		this.superSid = superSid;
	}
	public String getAgentUrl() {
		return agentUrl;
	}
	public void setAgentUrl(String agentUrl) {
		this.agentUrl = agentUrl;
	}
	public String getAgentLogo() {
		return agentLogo;
	}
	public void setAgentLogo(String agentLogo) {
		this.agentLogo = agentLogo;
	}
	
}
