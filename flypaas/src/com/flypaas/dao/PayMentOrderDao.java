package com.flypaas.dao;

import java.util.Map;

import com.flypaas.entity.PaymentOrder;
import com.flypaas.entity.vo.PageContainer;

public interface PayMentOrderDao {

	public void add(PaymentOrder order);
	
	public PaymentOrder get(long id);
	
	public PaymentOrder getByPayId(PaymentOrder order);
	
	public void update(PaymentOrder order);
	
	public PageContainer getChargeList(PageContainer page);
	
	public void updateOrderId(Map<String, Object> map);
	
	public long getAllPayMoneyLastYear(String sid);
}
