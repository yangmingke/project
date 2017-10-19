package com.flypaas.entity;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;
@Alias(value="tb_flypaas_city")
public class City implements Serializable {
	private static final long serialVersionUID = -4421604861206051457L;
	private long id;
	private long provinceId;
	private String name;
	private String ireaCode;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(long provinceId) {
		this.provinceId = provinceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIreaCode() {
		return ireaCode;
	}
	public void setIreaCode(String ireaCode) {
		this.ireaCode = ireaCode;
	}
}
