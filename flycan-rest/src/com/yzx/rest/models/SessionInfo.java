package com.yzx.rest.models;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "sessionInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class SessionInfo extends BaseInfo{

	private static final long serialVersionUID = -5201763125501406864L;
	
	private String appId;
	private String sessionId;
	private String policy;
	private String hopNum;
	private String routeNum;
	private String protocol;
	private String udpPortNum;
	private String vflag;
	private String srcIp;
	private String srcPort;
	private String dstIp;
	private String dstPort;
	private String routeid;
	private String srcap;
	private String dstap;
	private String result;
	private String ip;
	private String delay;
	private String srcApIp;
	private String srcApPort;
	private String dstApIp;
	private String dstApPort;
	
	private List<SessionInfo> srcApList;
	public List<SessionInfo> getSrcApList() {
		return srcApList;
	}
	public void setSrcApList(List<SessionInfo> srcApList) {
		this.srcApList = srcApList;
	}
	private List<SessionInfo> dstApList;
	public List<SessionInfo> getDstApList() {
		return dstApList;
	}
	public void setDstApList(List<SessionInfo> dstApList) {
		this.dstApList = dstApList;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String AppId) {
		this.appId = AppId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String cookie) {
		this.sessionId = cookie;
	}
	public String getPolicy() {
		return policy;
	}
	public void setPolicy(String police) {
		this.policy = police;
	}
	public String getHopNum() {
		return hopNum;
	}
	public void setHopNum(String hopNum) {
		this.hopNum = hopNum;
	}
	public String getRouteNum() {
		return routeNum;
	}
	public void setRouteNum(String routeNum) {
		this.routeNum = routeNum;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getSrcIp() {
		return srcIp;
	}
	public void setSrcIp(String srcIp) {
		this.srcIp = srcIp;
	}
	public String getDstIp() {
		return dstIp;
	}
	public void setDstIp(String dstIp) {
		this.dstIp = dstIp;
	}
	public String getRouteid() {
		return routeid;
	}
	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}
	public String getSrcap() {
		return srcap;
	}
	public void setSrcap(String srcap) {
		this.srcap = srcap;
	}
	public String getDstap() {
		return dstap;
	}
	public void setDstap(String dstap) {
		this.dstap = dstap;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getSrcPort() {
		return srcPort;
	}
	public void setSrcPort(String srcPort) {
		this.srcPort = srcPort;
	}
	public String getDstPort() {
		return dstPort;
	}
	public void setDstPort(String dstPort) {
		this.dstPort = dstPort;
	}
	public String getDelay() {
		return delay;
	}
	public void setDelay(String delay) {
		this.delay = delay;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUdpPortNum() {
		return udpPortNum;
	}
	public void setUdpPortNum(String udpPortNum) {
		this.udpPortNum = udpPortNum;
	}
	public String getVflag() {
		return vflag;
	}
	public void setVflag(String vflag) {
		this.vflag = vflag;
	}
	public String getSrcApIp() {
		return srcApIp;
	}
	public void setSrcApIp(String srcApIp) {
		this.srcApIp = srcApIp;
	}
	public String getSrcApPort() {
		return srcApPort;
	}
	public void setSrcApPort(String srcApPort) {
		this.srcApPort = srcApPort;
	}
	public String getDstApIp() {
		return dstApIp;
	}
	public void setDstApIp(String dstApIp) {
		this.dstApIp = dstApIp;
	}
	public String getDstApPort() {
		return dstApPort;
	}
	public void setDstApPort(String dstApPort) {
		this.dstApPort = dstApPort;
	}
	
}
