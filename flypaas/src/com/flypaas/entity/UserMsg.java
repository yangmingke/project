package com.flypaas.entity;

import java.util.Date;

import org.apache.ibatis.type.Alias;
@Alias(value="tb_flypaas_user_msg")
public class UserMsg {

	private Long msgId ;
	private String sid ;
	private Integer msgType ;
	private String msgDesc;
	private Date createDate ;
	private String hasread;
	private String msgTitle;
	
	public Long getMsgId() {
		return msgId;
	}
	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public Integer getMsgType() {
		return msgType;
	}
	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}
	public String getMsgDesc() {
		return msgDesc;
	}
	public void setMsgDesc(String msgDesc) {
		this.msgDesc = msgDesc;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getHasread() {
		return hasread;
	}
	public void setHasread(String hasread) {
		this.hasread = hasread;
	}
	public String getMsgTitle() {
		return msgTitle;
	}
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	
	
}
