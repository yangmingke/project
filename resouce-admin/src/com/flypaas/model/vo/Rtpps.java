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
	private List rtpps;
	private String old_area;
	
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public List getRtpps() {
		return rtpps;
	}
	public void setRtpps(List rtpps) {
		this.rtpps = rtpps;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getOld_area() {
		return old_area;
	}
	public void setOld_area(String old_area) {
		this.old_area = old_area;
	}
	
	
}

