package com.flypaas.service;

import java.util.Map;

import com.flypaas.entity.SecurityBalance;

public interface SecurityBalanceService {

	public void add(SecurityBalance securityBalance);
	
	public void addMoney(SecurityBalance securityBalance);
	
	public SecurityBalance get(Map<String, Object> map);
	
	public void update(SecurityBalance securityBalance);
}
