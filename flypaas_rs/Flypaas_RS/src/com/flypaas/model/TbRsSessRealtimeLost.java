package com.flypaas.model;

import java.util.Date;

public class TbRsSessRealtimeLost {
    private Long id;

    private Date time;

    private String srcIp;

    private Integer srcPort;

    private String destIp;

    private Integer destPort;

    private String caller;

    private String callee;

    private Integer ssrc;

    private String lostrate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSrcIp() {
        return srcIp;
    }

    public void setSrcIp(String srcIp) {
        this.srcIp = srcIp == null ? null : srcIp.trim();
    }

    public Integer getSrcPort() {
        return srcPort;
    }

    public void setSrcPort(Integer srcPort) {
        this.srcPort = srcPort;
    }

    public String getDestIp() {
        return destIp;
    }

    public void setDestIp(String destIp) {
        this.destIp = destIp == null ? null : destIp.trim();
    }

    public Integer getDestPort() {
        return destPort;
    }

    public void setDestPort(Integer destPort) {
        this.destPort = destPort;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller == null ? null : caller.trim();
    }

    public String getCallee() {
        return callee;
    }

    public void setCallee(String callee) {
        this.callee = callee == null ? null : callee.trim();
    }

    public Integer getSsrc() {
        return ssrc;
    }

    public void setSsrc(Integer ssrc) {
        this.ssrc = ssrc;
    }

    public String getLostrate() {
        return lostrate;
    }

    public void setLostrate(String lostrate) {
        this.lostrate = lostrate == null ? null : lostrate.trim();
    }
}