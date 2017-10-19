package com.flypaas.dao;

import java.util.Map;

import com.flypaas.entity.SecurityBalance;

public interface SecurityBalanceDao {

	public void add(SecurityBalance securityBalance);
	
	public SecurityBalance get(Map<String, Object> map);
	
	public void update(SecurityBalance securityBalance);
	
	public void addMoney(SecurityBalance securityBalance);
	
	public void unfreezeBalance(Map<String, Object> map);
}
