package com.yzx.rest.resp;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.yzx.rest.models.Account;
import com.yzx.rest.models.App;
import com.yzx.rest.models.AppBill;
import com.yzx.rest.models.Client;
import com.yzx.rest.models.SDK;
import com.yzx.rest.models.SessionInfo;
import com.yzx.rest.models.Ulog;
import com.yzx.rest.util.Consts;
import com.yzx.rest.util.LogAction;
/**
 * client2014 rest接口返回结果集
 * @author chenxj
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="resp")
@XmlSeeAlso({Client.class})
public class ListResp implements Serializable{
	private static final long serialVersionUID = 5191301225295903600L;
	//结果码
	@XmlElement(name = "respCode")
	private String respCode;
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode,String ifName,String appId,String clientNumber) {
		addLog(respCode, ifName,appId,clientNumber);
		this.respCode = respCode;
	}
	
	//state状态
	@XmlElement(name = "state")
	private String state;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}	
	
	//success
	@XmlElement(name = "success")
	private Integer success;
	public Integer getSuccess() {
		return success;
	}
	public void setSuccess(Integer success) {
		this.success = success;
	}
	
	
	//failure
	@XmlElement(name = "failure")
	private Integer failure;
	public Integer getFailure() {
		return failure;
	}
	public void setFailure(Integer failure) {
		this.failure = failure;
	}
	
	/**
	 * 日志上报
	 * 1：上报rest并发量
	 * 2：上报rest接入错误码
	 * @param respCode
	 * @param ifName
	 * @param appId
	 * @param clientNumber
	 */
	private void addLog(String respCode,String ifName,String appId,String clientNumber){
		if (!respCode.equals(Consts.C000000)) {
			Ulog ulog=new Ulog();
			ulog.setLogEvent(Consts.evt_errorCode);
			ulog.setErrorCode(respCode);
			ulog.setIfName(ifName);
			ulog.setAppId(appId);
			ulog.setClientNumber(clientNumber);
			LogAction.getInstance().addQuene(ulog);
		}
	}
	
	//数量
	@XmlElement(name = "count")
	private Integer count;
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	//开发者sid
	@XmlElement(name = "sid")
	private String sid;
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	
	//应用id
	@XmlElement(name = "appId")
	private String appId;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	//主账号模型集合
	@XmlElement(name="account")
	private List<Account> account;
	
	public List<Account> getAccount() {
		return account;
	}
	public void setAccount(List<Account> account) {
		this.account = account;
	}
	
	//client模型集合
	@XmlElement(name="client")
	private List<Client> clients;

	public List<Client> getClients() {
		return clients;
	}
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	
	//client模型集合
	@XmlElement(name="SDK")
	private List<SDK> sdks;
	public List<SDK> getSdks() {
		return sdks;
	}
	public void setSdks(List<SDK> sdks) {
		this.sdks = sdks;
	}

	//应用模型集合
	@XmlElement(name="app")
	private List<App> app;
	public List<App> getApp() {
		return app;
	}
	public void setApp(List<App> app) {
		this.app = app;
	}
	
	//主账号账单模型集合
	@XmlElement(name="accountBill")
	private List<AppBill> accountBills;
	public List<AppBill> getAccountBills() {
		return accountBills;
	}
	public void setAccountBills(List<AppBill> accountBills) {
		this.accountBills = accountBills;
	}
	
	//路由模型集合
	@XmlElement(name="routes")
	private List<SessionInfo> sessionInfo;
	public List<SessionInfo> getSessionInfo() {
		return sessionInfo;
	}
	public void setSessionInfo(List<SessionInfo> sessionInfo) {
		this.sessionInfo = sessionInfo;
	}
	
	//数量
	@XmlElement(name = "routeSize")
	private Integer routeSize;
	public Integer getRouteSize() {
		return routeSize;
	}
	public void setRouteSize(Integer routeSize) {
		this.routeSize = routeSize;
	}
	
	//会话ID
	@XmlElement(name = "sessionId")
	private String sessionId;
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	//连接方式
	@XmlElement(name = "direct")
	private String direct;
	public String getDirect() {
		return direct;
	}
	public void setDirect(String direct) {
		this.direct = direct;
	}
	
	//RTPPList模型集合
	@XmlElement(name="apList")
	private List<String> apList;
	public List<String> getApList() {
		return apList;
	}
	public void setApList(List<String> apList) {
		this.apList = apList;
	}
		
	//结果描述
	@XmlElement(name = "descb")
	private String descb;
	public String getDescb() {
		return descb;
	}
	public void setDescb(String descb) {
		this.descb = descb;
	}
}
