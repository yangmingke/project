package com.flypaas.dao.impl;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.PayMentOrderDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.PaymentOrder;
import com.flypaas.entity.vo.PageContainer;
@Repository
public class PayMentOrderDaoImpl extends MyBatisDao implements PayMentOrderDao {
	private static final String ADDPAYORDER="addPayOrder" ;
	private static final String GETPAYORDERBYID="getPayOrderById";
	private static final String UPDATEPAYORDER="updatePayOrder";
	private static final String GETPAYMENTLIST="getPayMentList" ;
	private static final String GETCHARGECOUNT="getChargeCount" ;
	private static final String UPDATEORDERID="updateOrderid" ;
	private static final String GETORDERBYPAYID="getOrderByPayId";
	private static final String GETALLPAYMONEYLASTYEAR="getAllPayMoneyLastYear";
	public void add(PaymentOrder order) {
		sqlSessionTemplate.insert(ADDPAYORDER, order);
	}
	public PaymentOrder get(long id) {
		return sqlSessionTemplate.selectOne(GETPAYORDERBYID, id);
	}
	public void update(PaymentOrder order) {
		sqlSessionTemplate.update(UPDATEPAYORDER, order);
	}
	public PageContainer getChargeList(PageContainer page) {
		PageContainer result = null;
		Map<String, Object> params = null;
		result = getSearchPage(GETPAYMENTLIST, GETCHARGECOUNT, page);
		params = result.getParams();
		if (null == params) {
			params = new HashMap<String, Object>();
			result.setParams(params);
		}
		if (null == params.get("total")) {
			params.put("total", 0);
		}
		return result;
	}
	public void updateOrderId(Map<String, Object> map) {
		sqlSessionTemplate.update(UPDATEORDERID, map);
	}
	public PaymentOrder getByPayId(PaymentOrder order) {
		return sqlSessionTemplate.selectOne(GETORDERBYPAYID, order);
	}
	public long getAllPayMoneyLastYear(String sid) {
		return sqlSessionTemplate.selectOne(GETALLPAYMONEYLASTYEAR, sid);
	}

}
