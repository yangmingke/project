package com.flypaas.entity;

import org.apache.ibatis.type.Alias;

@Alias(value="tariff")
public class Tariff {
	
	private long tariffId ;
	private String brandId ;
	private char callType ;
	private String prefix ;
	private String prefixLen ;
	private String callerPrefix;
	private int callerPrefixLen ;
	private String areaName;
	private int firstFee;
	private int firstTime;
	private int unitFee ;
	private int unitTime;
	private int minMoney;
	private char discountFlag;
	private char enableFlag ;
	
	public long getTariffId() {
		return tariffId;
	}
	public void setTariffId(long tariffId) {
		this.tariffId = tariffId;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public char getCallType() {
		return callType;
	}
	public void setCallType(char callType) {
		this.callType = callType;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getPrefixLen() {
		return prefixLen;
	}
	public void setPrefixLen(String prefixLen) {
		this.prefixLen = prefixLen;
	}
	public String getCallerPrefix() {
		return callerPrefix;
	}
	public void setCallerPrefix(String callerPrefix) {
		this.callerPrefix = callerPrefix;
	}
	public int getCallerPrefixLen() {
		return callerPrefixLen;
	}
	public void setCallerPrefixLen(int callerPrefixLen) {
		this.callerPrefixLen = callerPrefixLen;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public int getFirstFee() {
		return firstFee;
	}
	public void setFirstFee(int firstFee) {
		this.firstFee = firstFee;
	}
	public int getFirstTime() {
		return firstTime;
	}
	public void setFirstTime(int firstTime) {
		this.firstTime = firstTime;
	}
	public int getUnitFee() {
		return unitFee;
	}
	public void setUnitFee(int unitFee) {
		this.unitFee = unitFee;
	}
	public int getUnitTime() {
		return unitTime;
	}
	public void setUnitTime(int unitTime) {
		this.unitTime = unitTime;
	}
	public int getMinMoney() {
		return minMoney;
	}
	public void setMinMoney(int minMoney) {
		this.minMoney = minMoney;
	}
	public char getDiscountFlag() {
		return discountFlag;
	}
	public void setDiscountFlag(char discountFlag) {
		this.discountFlag = discountFlag;
	}
	public char getEnableFlag() {
		return enableFlag;
	}
	public void setEnableFlag(char enableFlag) {
		this.enableFlag = enableFlag;
	}
	
	
}
