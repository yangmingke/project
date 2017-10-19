package com.yzx.rest.resp;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.yzx.rest.models.Client;
import com.yzx.rest.models.Clientt;
import com.yzx.rest.models.Ulog;
import com.yzx.rest.util.Consts;
import com.yzx.rest.util.LogAction;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="resp")
@XmlSeeAlso({Client.class})
public class ListRespt implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8237749476221493106L;
	@XmlElement(name = "respCode")
	private String respCode;
	private String sid_pwd;
	private String token;
	private String appId;
	private String sid;
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode,String ifName,String appId,String clientNumber) {
		addLog(respCode, ifName,appId,clientNumber);
		this.respCode = respCode;
	}
	private void addLog(String respCode,String ifName,String appId,String clientNumber){
		if (!respCode.equals(Consts.C000000)) {
			Ulog ulog=new Ulog();
			ulog.setLogEvent(Consts.evt_errorCode);
			ulog.setErrorCode(respCode);
			ulog.setIfName(ifName);
			ulog.setAppId(appId);
			LogAction.getInstance().addQuene(ulog);
		}
	}
	public String getSid_pwd() {
		return sid_pwd;
	}
	public void setSid_pwd(String sid_pwd) {
		this.sid_pwd = sid_pwd;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}

	@XmlElement(name="client")
	private List<Clientt> clients;
	public List<Clientt> getClients() {
		return clients;
	}
	public void setClients(List<Clientt> clients) {
		this.clients = clients;
	}
}
