package com.network.monitor.tcpserver;

/**
 * OTT协议包
 * @author 郭剑锋
 *
 */
public class TCPPack {

	private int len; //总长度
	private int sn;  //报文序列号
	private short asn; //应答码
	private short msgType; //消息类型
	private String jsonStr; //包体json字符串
	
	public String getJsonStr() {
		return jsonStr;
	}
	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}
	public int getLen() {
		return len;
	}
	public void setLen(int len) {
		this.len = len;
	}
	public int getSn() {
		return sn;
	}
	public void setSn(int sn) {
		this.sn = sn;
	}
	public short getAsn() {
		return asn;
	}
	public void setAsn(short asn) {
		this.asn = asn;
	}
	public short getMsgType() {
		return msgType;
	}
	public void setMsgType(short msgType) {
		this.msgType = msgType;
	}
	
}
