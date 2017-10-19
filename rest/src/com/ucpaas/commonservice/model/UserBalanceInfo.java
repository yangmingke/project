package com.ucpaas.commonservice.model;

import java.util.Date;

import com.ucpaas.commonservice.model.base.BaseInfo;

/**
 * 用户余额表实体类
 * @author luke
 *
 */
public class UserBalanceInfo extends BaseInfo {
	private static final long serialVersionUID = -8521921904054455712L;

	private Long acctId;

    private String sid;

    private Long balance;

    private String enableFlag;

    private Date createTime;

    private Date validDate;

    private Date vipValidDate;

    private Long creditBalance;

    public Long getAcctId() {
        return acctId;
    }

    public void setAcctId(Long acctId) {
        this.acctId = acctId;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag == null ? null : enableFlag.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public Date getVipValidDate() {
        return vipValidDate;
    }

    public void setVipValidDate(Date vipValidDate) {
        this.vipValidDate = vipValidDate;
    }

    public Long getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(Long creditBalance) {
        this.creditBalance = creditBalance;
    }
}