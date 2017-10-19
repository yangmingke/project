package com.flypaas.service;

import java.util.List;
import java.util.Map;

public interface CouponService {
	
	public Map<String, Object> getCouponByNum(String num);
	
	public List<Map<String, Object>> getCouponByOtherList(Map<String, Object> map);
	
	public void add(Map<String, Object> map);
	
	public List<Map<String, Object>> getMeetIdList();
	
	public int getCouponLogCount(Map<String, Object> map);
	
	public int getCouponLogCountByNum(String num);
	
	public Map<String, Object> getRodomNum(String meetId);
	
	public void addMeet(String meetId);
	
	public String getCurrentMeet();
}
