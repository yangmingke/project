package com.flypaas.entity;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias(value="tb_bill_package")
public class Package {
	private Long packageId ;
	private String packageName;
	private String packageDescribe;
	private String status;
	private Date effDate;
	private Date expDate;
	private String isLowestCharge;
	private String lowestCharge;
	private String isDefault;
	private int packageType;
	private FeeItemRel itemRel;
	private List<FeeItem> feeItemList;
	public Long getPackageId() {
		return packageId;
	}
	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getPackageDescribe() {
		return packageDescribe;
	}
	public void setPackageDescribe(String packageDescribe) {
		this.packageDescribe = packageDescribe;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getEffDate() {
		return effDate;
	}
	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	public String getIsLowestCharge() {
		return isLowestCharge;
	}
	public void setIsLowestCharge(String isLowestCharge) {
		this.isLowestCharge = isLowestCharge;
	}
	public String getLowestCharge() {
		return lowestCharge;
	}
	public void setLowestCharge(String lowestCharge) {
		this.lowestCharge = lowestCharge;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public List<FeeItem> getFeeItemList() {
		return feeItemList;
	}
	public void setFeeItemList(List<FeeItem> feeItemList) {
		this.feeItemList = feeItemList;
	}
	public FeeItemRel getItemRel() {
		return itemRel;
	}
	public void setItemRel(FeeItemRel itemRel) {
		this.itemRel = itemRel;
	}
	public int getPackageType() {
		return packageType;
	}
	public void setPackageType(int packageType) {
		this.packageType = packageType;
	}
	
	
	
}
