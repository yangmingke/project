package com.flypaas.model;

import java.util.Date;

public class TbRsBillFlow {
    private Long id;

    private String balanceShow;
    
    private String mainSid;

    private Double balance;

    private Long actualFee;

    private Date createDate;
    
    private Date adultDate;
    
    private Date finshDate;

    private String feeName;

    private String operUser;

    private String demo;
    
    private String status;
    
    private String alipayName;

    private String alipayAccount;

    public String getAlipayName() {
		return alipayName;
	}

	public void setAlipayName(String alipayName) {
		this.alipayName = alipayName;
	}

	public String getAlipayAccount() {
		return alipayAccount;
	}

	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}

	public Date getAdultDate() {
		return adultDate;
	}

	public void setAdultDate(Date adultDate) {
		this.adultDate = adultDate;
	}

	public Date getFinshDate() {
		return finshDate;
	}

	public void setFinshDate(Date finshDate) {
		this.finshDate = finshDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMainSid() {
        return mainSid;
    }

    public void setMainSid(String mainSid) {
        this.mainSid = mainSid == null ? null : mainSid.trim();
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Long getActualFee() {
        return actualFee;
    }

    public void setActualFee(Long actualFee) {
        this.actualFee = actualFee;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getFeeName() {
        return feeName;
    }

    public void setFeeName(String feeName) {
        this.feeName = feeName == null ? null : feeName.trim();
    }

    public String getOperUser() {
        return operUser;
    }

    public void setOperUser(String operUser) {
        this.operUser = operUser == null ? null : operUser.trim();
    }

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo == null ? null : demo.trim();
    }

	public String getBalanceShow() {
		return balanceShow;
	}

	public void setBalanceShow(String balanceShow) {
		this.balanceShow = balanceShow;
	}
}