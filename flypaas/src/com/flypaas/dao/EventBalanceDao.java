package com.flypaas.dao;

import java.util.List;
import java.util.Map;

public interface EventBalanceDao {
	public void add(Map<String, Object> map);
	public void update(Map<String, Object> map);
	public int get(Map<String, Object> map);
	public List<Map<String, Object>> getEventBalanceBySid(String sid);
}
