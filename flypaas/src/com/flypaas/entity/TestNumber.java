package com.flypaas.entity;

import org.apache.ibatis.type.Alias;

@Alias(value="tb_flypaas_test_number")
public class TestNumber {

	private long nbrId ;
	private String sid;
	private String testId;
	private String isdefault;
	private long countrynum ;
	private String countryname;
	private String clientnum;
	
	public long getNbrId() {
		return nbrId;
	}
	public void setNbrId(long nbrId) {
		this.nbrId = nbrId;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public String getIsdefault() {
		return isdefault;
	}
	public void setIsdefault(String isdefault) {
		this.isdefault = isdefault;
	}
	public long getCountrynum() {
		return countrynum;
	}
	public void setCountrynum(long countrynum) {
		this.countrynum = countrynum;
	}
	public String getCountryname() {
		return countryname;
	}
	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}
	public String getClientnum() {
		return clientnum;
	}
	public void setClientnum(String clientnum) {
		this.clientnum = clientnum;
	}
	
	
}
