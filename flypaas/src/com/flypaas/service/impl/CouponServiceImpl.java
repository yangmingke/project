package com.flypaas.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.daocenter.DaoCenter;
import com.flypaas.service.CouponService;
@Service
@Transactional
public class CouponServiceImpl extends DaoCenter implements CouponService {

	public Map<String, Object> getCouponByNum(String num) {
		return couponDao.getCouponByNum(num);
	}

	public void add(Map<String, Object> map) {
		couponDao.add(map);
	}

	public List<Map<String, Object>> getCouponByOtherList(
			Map<String, Object> map) {
		return couponDao.getCouponByOtherList(map);
	}

	public List<Map<String, Object>> getMeetIdList() {
		return couponDao.getMeetIdList();
	}

	public int getCouponLogCount(Map<String, Object> map) {
		return couponDao.getCouponLogCount(map);
	}

	public int getCouponLogCountByNum(String num) {
		return couponDao.getCouponLogCountByNum(num);
	}
	
	public Map<String, Object> getRodomNum(String meetId){
		Map<String, Object> map = couponDao.getRodomNum(meetId);
		if(map!=null){
			String id = map.get("id").toString();
			couponDao.updateSended(id);
		}
		return map;
	}

	public void addMeet(String meetId) {
		couponDao.deleteMeet();
		couponDao.addMeet(meetId);
	}

	public String getCurrentMeet() {
		return couponDao.getCurrentMeet();
	}

}
