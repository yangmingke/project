package com.flypaas.model;

import java.util.Date;

public class TbRsMailProp {
    private Long id;

    private String frm;

    private String fromnickname;

    private String tonbr;

    private String cc;

    private String bcc;

    private String subject;

    private String atturl;

    private String type;

    private Date createtime;

    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrm() {
        return frm;
    }

    public void setFrm(String frm) {
        this.frm = frm == null ? null : frm.trim();
    }

    public String getFromnickname() {
        return fromnickname;
    }

    public void setFromnickname(String fromnickname) {
        this.fromnickname = fromnickname == null ? null : fromnickname.trim();
    }

    public String getTonbr() {
        return tonbr;
    }

    public void setTonbr(String tonbr) {
        this.tonbr = tonbr == null ? null : tonbr.trim();
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc == null ? null : cc.trim();
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc == null ? null : bcc.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getAtturl() {
        return atturl;
    }

    public void setAtturl(String atturl) {
        this.atturl = atturl == null ? null : atturl.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

    
}