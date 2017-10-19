package com.flypaas.entity;

import org.apache.ibatis.type.Alias;

@Alias(value="tb_bill_fee_item")
public class FeeItem {

	private int feeId;
	private String feeName;
	private String feeType;
	private double fee;
	private String feeDesc;
	private String prefix;
	private int prefixLen;
	private int isShowNbr;
	
	private int interFee;//是否为国际套餐
	
	public int getFeeId() {
		return feeId;
	}
	public void setFeeId(int feeId) {
		this.feeId = feeId;
	}
	public String getFeeName() {
		return feeName;
	}
	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getFeeDesc() {
		return feeDesc;
	}
	public void setFeeDesc(String feeDesc) {
		this.feeDesc = feeDesc;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public int getPrefixLen() {
		return prefixLen;
	}
	public void setPrefixLen(int prefixLen) {
		this.prefixLen = prefixLen;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public int getInterFee() {
		return interFee;
	}
	public void setInterFee(int interFee) {
		this.interFee = interFee;
	}
	public int getIsShowNbr() {
		return isShowNbr;
	}
	public void setIsShowNbr(int isShowNbr) {
		this.isShowNbr = isShowNbr;
	}
	
	
}
