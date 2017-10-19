package com.flypaas.entity;

import org.apache.ibatis.type.Alias;

@Alias(value="tb_flypaas_country_mobile_prefix")
public class CountryMobilePrefix {
	private String id ;
	private String country;
	private long mobilePrefix ;
	private String area;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public long getMobilePrefix() {
		return mobilePrefix;
	}
	public void setMobilePrefix(long mobilePrefix) {
		this.mobilePrefix = mobilePrefix;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
}
