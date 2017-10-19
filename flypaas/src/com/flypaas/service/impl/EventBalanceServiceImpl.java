package com.flypaas.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.daocenter.DaoCenter;
import com.flypaas.service.EventBalanceService;
import com.flypaas.utils.DateUtil;

@Service
@Transactional
public class EventBalanceServiceImpl extends DaoCenter implements EventBalanceService {

	public synchronized void add(Map<String, Object> map) {
		int count = eventBalanceDao.get(map);
		if(count>0){
			eventBalanceDao.update(map);
		}else{
			eventBalanceDao.add(map);
		}
		//代金券领取记录
		String sid = map.get("sid").toString();
		String couponNum = map.get("couponNum").toString();
		String meetId = map.get("meetId").toString();
		String charge = map.get("balance").toString();
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("sid", sid);
		map1.put("couponNum", couponNum);
		map1.put("meetId", meetId);
		map1.put("money", charge);
		map1.put("createDate", DateUtil.getCurrentDate());
		couponDao.addCouponLog(map);
	}

	public List<Map<String, Object>> getEventBalanceBySid(String sid) {
		return eventBalanceDao.getEventBalanceBySid(sid);
	}

}
