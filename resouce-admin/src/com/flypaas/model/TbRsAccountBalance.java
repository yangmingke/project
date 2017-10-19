package com.flypaas.model;

import java.util.Date;
public class TbRsAccountBalance {
    private Long id;

    private String netSid;

    private Double balance;

    private String enableFlag;

    private Date createTime;

    private Date updateDate;

    private Date vipValidDate;
    
    private Long creditBalance;
    

    public Long getCreditBalance() {
		return creditBalance;
	}

	public void setCreditBalance(Long creditBalance) {
		this.creditBalance = creditBalance;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNetSid() {
        return netSid;
    }

    public void setNetSid(String netSid) {
        this.netSid = netSid;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getVipValidDate() {
        return vipValidDate;
    }

    public void setVipValidDate(Date vipValidDate) {
        this.vipValidDate = vipValidDate;
    }
}