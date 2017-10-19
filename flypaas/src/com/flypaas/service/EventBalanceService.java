package com.flypaas.service;

import java.util.List;
import java.util.Map;

public interface EventBalanceService {

	public void add(Map<String, Object> map);
	
	public List<Map<String, Object>> getEventBalanceBySid(String sid);
	
}
