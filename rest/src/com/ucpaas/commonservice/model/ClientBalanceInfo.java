package com.ucpaas.commonservice.model;

import java.util.Date;

import com.ucpaas.commonservice.model.base.BaseInfo;

/**
 * ClientBalance分表实体类
 * @author luke
 *
 */
public class ClientBalanceInfo extends BaseInfo {
	private static final long serialVersionUID = -6458215175106760590L;

	private Integer uin;

    private String sid;

    private String clientNumber;

    private Long balance;

    private String enableFlag;

    private Date createTime;

    private Date validDate;

    private Date vipValidDate;

    private Date updateDate;
    
    //分表信息
    private String uin_mod;
    
    /**反向表中；类型，101：attr={userId}_{appId}；102：attr={mobile}_{appId}*/
    private String attr;
    
    
    public ClientBalanceInfo() {
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

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber == null ? null : clientNumber.trim();
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


	public String getAttr() {
		return attr;
	}


	public void setAttr(String attr) {
		this.attr = attr;
	}
    
    
}