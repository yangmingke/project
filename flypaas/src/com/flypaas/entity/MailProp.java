package com.flypaas.entity;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias(value="mailprop")
public class MailProp {

	private long id;
	private String frm ;
	private String fromNickname;
	private String toNbr ;
	private String cc ;
	private String bcc;
	private String subject ;
	private String text;
	private String attUrl;
	private String type;
	private Date createtime;
	
	public String getToNbr() {
		return toNbr;
	}
	public void setToNbr(String toNbr) {
		this.toNbr = toNbr;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFrm() {
		return frm;
	}
	public void setFrm(String frm) {
		this.frm = frm;
	}
	public String getFromNickname() {
		return fromNickname;
	}
	public void setFromNickname(String fromNickname) {
		this.fromNickname = fromNickname;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getAttUrl() {
		return attUrl;
	}
	public void setAttUrl(String attUrl) {
		this.attUrl = attUrl;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
}
