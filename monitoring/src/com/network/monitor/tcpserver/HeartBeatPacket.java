package com.network.monitor.tcpserver;

/**
 * UDP心跳包封装类
 *
 */
public class HeartBeatPacket {
	private int len; //总长度
	private int sn;  //报文序列号
	private short asn; //应答码
	private short msgType; //消息类型
	
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
