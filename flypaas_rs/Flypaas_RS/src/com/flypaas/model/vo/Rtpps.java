package com.flypaas.model.vo;

import java.util.List;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年1月5日 上午11:31:11
* 类说明
*/
public class Rtpps {
	
	private String action;
	private String area;
	@SuppressWarnings("rawtypes")
	private List rtpps;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	@SuppressWarnings("rawtypes")
	public List getRtpps() {
		return rtpps;
	}
	@SuppressWarnings("rawtypes")
	public void setRtpps(List rtpps) {
		this.rtpps = rtpps;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	
}

