package com.yzx.rest.models;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "SDK")
public class SDK extends BaseInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5201763125511406865L;
	private String SDKID;
	private String appId;
	private String createDate;
	public String getSDKID() {
		return SDKID;
	}
	public void setSDKID(String sDKID) {
		SDKID = sDKID;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
