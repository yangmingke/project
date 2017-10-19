package com.flypaas.service;

import java.util.Map;

import com.flypaas.entity.PaymentOrder;
import com.flypaas.entity.vo.PageContainer;

public interface PayMentOrderService {

	public PaymentOrder addPaymentOrder(long charge,String sid,String accountType,String chargeType,String appSid);
	
	public void add(PaymentOrder order);
	
	public PaymentOrder get(long id);
	
	public PaymentOrder getByPayId(PaymentOrder order);
	
	public void update(PaymentOrder order);
	
	public PageContainer getChargeList(PageContainer page);
	
	public void updateOrderId(Map<String, Object> map);
	
	public long getAllPayMoneyLastYear(String sid);
	
	public void callBack(PaymentOrder order,String payAmount,String sid);
	
	public void coupon(String couponNum,long charge,String sid,String meetId,String chargeType);
	
	public boolean checkOrder(PaymentOrder order);
}
