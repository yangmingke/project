package com.yzx.rest.models;


/**
 * 扣费消息model
 * 
 * 
 * @author liuLu
 */

public class PayMessage extends BaseModel {

	/**
	 * 扣费消息请求实体类
	 */
	private static final long serialVersionUID = -3761858940983476240L;
	private String payId; // 扣费流水id
	private String sid; // sid
	private String appSid; // appSid
	private String clientNumber;// client唯一标识 标识是否需要client扣费
								// 带有clientNumber则进行client扣费
	private String hostIp;// 业务ip地址
	private String eventId;// 业务id
	private String payMoney;// 扣费金额 （乘以1000000）
	private String businessBeginTime;// 业务开始时间,格式yyyy-MM-dd HH:mm:ss
	private String requestReceiveTime;// payServer从MQ中收到消息的时间
	private String requestPayTime;// 请求支付时间,yyyy-MM-dd HH:mm:ss
	public String getPayId() {
		return payId;
	}
	public void setPayId(String payId) {
		this.payId = payId;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getAppSid() {
		return appSid;
	}
	public void setAppSid(String appSid) {
		this.appSid = appSid;
	}
	public String getClientNumber() {
		return clientNumber;
	}
	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public String getHostIp() {
		return hostIp;
	}
	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}
	public String getBusinessBeginTime() {
		return businessBeginTime;
	}
	public void setBusinessBeginTime(String businessBeginTime) {
		this.businessBeginTime = businessBeginTime;
	}
	public String getRequestPayTime() {
		return requestPayTime;
	}
	public void setRequestPayTime(String requestPayTime) {
		this.requestPayTime = requestPayTime;
	}
	public String getRequestReceiveTime() {
		return requestReceiveTime;
	}
	public void setRequestReceiveTime(String requestReceiveTime) {
		this.requestReceiveTime = requestReceiveTime;
	}

}
