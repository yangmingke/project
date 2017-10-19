package com.flypaas.entity;

import java.util.List;

import org.apache.ibatis.type.Alias;
@Alias(value="tb_bill_fee_item_rel")
public class FeeItemRel {
	private int relId;
	private int feeId;
	private int packageId;
	private List<FeeItem> feeItemList;
	
	public int getRelId() {
		return relId;
	}
	public void setRelId(int relId) {
		this.relId = relId;
	}
	public int getFeeId() {
		return feeId;
	}
	public void setFeeId(int feeId) {
		this.feeId = feeId;
	}
	public int getPackageId() {
		return packageId;
	}
	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}
	public List<FeeItem> getFeeItemList() {
		return feeItemList;
	}
	public void setFeeItemList(List<FeeItem> feeItemList) {
		this.feeItemList = feeItemList;
	}
	
	
}
