package com.flypaas.model;

import java.util.Date;

public class TbRsRTPP {
    private Integer rtppSid;

    private String netSid;

    private String name;

    private String ip;

    private String mainIp;
    
    private String fromIp;
    
    private String signIp;
    
    private String mediaIp;
    
    private String pingIp;

    private String operator;

    private String type;

    private String netLevel;

    private Integer zone;	//城市

    private Integer region; //大区
    
    private Integer province;
    
    private Integer country;

    private Integer price;
    
    private Integer agentPrice;
    
    private Integer continent;

    private String blockPrice;

    private String isToLine;

    private String toIp;

    private Integer bandwidthLimit;

    private Integer concurrencyLimit;

    private String status;

    private Date createDate;

    private Date updateDate;

    private Date auditDate;
    
    private String routeArea;
    
    private int isBdr;
    
	

	public int getIsBdr() {
		return isBdr;
	}

	public void setIsBdr(int isBdr) {
		this.isBdr = isBdr;
	}

	public Integer getContinent() {
		return continent;
	}

	public void setContinent(Integer continent) {
		this.continent = continent;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public Integer getZone() {
		return zone;
	}

	public Integer getRegion() {
		return region;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public void setZone(Integer zone) {
		this.zone = zone;
	}

	public void setRegion(Integer region) {
		this.region = region;
	}

	public String getRouteArea() {
		return routeArea;
	}

	public void setRouteArea(String routeArea) {
		this.routeArea = routeArea;
	}

	public Integer getRtppSid() {
        return rtppSid;
    }

    public void setRtppSid(Integer rtppSid) {
        this.rtppSid = rtppSid;
    }

    public String getNetSid() {
        return netSid;
    }

    public void setNetSid(String netSid) {
        this.netSid = netSid == null ? null : netSid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getMainIp() {
        return mainIp;
    }

    public void setMainIp(String mainIp) {
        this.mainIp = mainIp == null ? null : mainIp.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getNetLevel() {
        return netLevel;
    }

    public void setNetLevel(String netLevel) {
        this.netLevel = netLevel == null ? null : netLevel.trim();
    }


	public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getBlockPrice() {
        return blockPrice;
    }

    public void setBlockPrice(String blockPrice) {
        this.blockPrice = blockPrice == null ? null : blockPrice.trim();
    }

    public String getIsToLine() {
        return isToLine;
    }

    public void setIsToLine(String isToLine) {
        this.isToLine = isToLine == null ? null : isToLine.trim();
    }

    public String getToIp() {
        return toIp;
    }

    public void setToIp(String toIp) {
        this.toIp = toIp == null ? null : toIp.trim();
    }

    public Integer getBandwidthLimit() {
        return bandwidthLimit;
    }

    public void setBandwidthLimit(Integer bandwidthLimit) {
        this.bandwidthLimit = bandwidthLimit;
    }

    public Integer getConcurrencyLimit() {
        return concurrencyLimit;
    }

    public void setConcurrencyLimit(Integer concurrencyLimit) {
        this.concurrencyLimit = concurrencyLimit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

	public Integer getAgentPrice() {
		return agentPrice;
	}

	public void setAgentPrice(Integer agentPrice) {
		this.agentPrice = agentPrice;
	}

	public String getFromIp() {
		return fromIp;
	}

	public void setFromIp(String fromIp) {
		this.fromIp = fromIp;
	}

	public String getSignIp() {
		return signIp;
	}

	public void setSignIp(String signIp) {
		this.signIp = signIp;
	}

	public String getMediaIp() {
		return mediaIp;
	}

	public void setMediaIp(String mediaIp) {
		this.mediaIp = mediaIp;
	}

	public String getPingIp() {
		return pingIp;
	}

	public void setPingIp(String pingIp) {
		this.pingIp = pingIp;
	}
}