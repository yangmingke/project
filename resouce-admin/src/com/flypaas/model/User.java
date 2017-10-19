package com.flypaas.model;

import java.util.Date;

public class User {
    private String sid;

    private String superSid;

    private String token;

    private String feeStrategy;

    private String netId;

    private String username;

    private String email;

    private String password;

    private String status;

    private String authStatus;

    private String mobile;

    private Integer chatType;

    private String chatNbr;

    private String realname;

    private String address;

    private String developerType;

    private Integer national;

    private String idType;

    private String idNbr;

    private Long orgId;

    private String oConType1;

    private String oConNbr1;

    private String oConType2;

    private String oConNbr2;

    private Long point;

    private String rank;

    private String legalPerson;

    private String companyNbr;

    private String webSite;

    private Date createDate;

    private Date updateDate;

    private Integer loginTimes;

    private Integer province;

    private Integer city;

    private Integer guide;

    private String randomNbr;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public String getSuperSid() {
        return superSid;
    }

    public void setSuperSid(String superSid) {
        this.superSid = superSid == null ? null : superSid.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getFeeStrategy() {
        return feeStrategy;
    }

    public void setFeeStrategy(String feeStrategy) {
        this.feeStrategy = feeStrategy == null ? null : feeStrategy.trim();
    }

    public String getNetId() {
        return netId;
    }

    public void setNetId(String netId) {
        this.netId = netId == null ? null : netId.trim();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus == null ? null : authStatus.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getChatType() {
        return chatType;
    }

    public void setChatType(Integer chatType) {
        this.chatType = chatType;
    }

    public String getChatNbr() {
        return chatNbr;
    }

    public void setChatNbr(String chatNbr) {
        this.chatNbr = chatNbr == null ? null : chatNbr.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getDeveloperType() {
        return developerType;
    }

    public void setDeveloperType(String developerType) {
        this.developerType = developerType == null ? null : developerType.trim();
    }

    public Integer getNational() {
        return national;
    }

    public void setNational(Integer national) {
        this.national = national;
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

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
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

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank == null ? null : rank.trim();
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

    public Integer getGuide() {
        return guide;
    }

    public void setGuide(Integer guide) {
        this.guide = guide;
    }

    public String getRandomNbr() {
        return randomNbr;
    }

    public void setRandomNbr(String randomNbr) {
        this.randomNbr = randomNbr == null ? null : randomNbr.trim();
    }
}