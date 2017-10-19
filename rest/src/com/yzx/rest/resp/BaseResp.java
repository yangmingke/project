package com.yzx.rest.resp;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="resp")
public class BaseResp implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4755754645534739746L;
	public BaseResp(){
		this.setRespCode(String.valueOf(RespCode.NODEFINE));
	}
	@XmlElement(name = "respCode")
	private String respCode;
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	
}
