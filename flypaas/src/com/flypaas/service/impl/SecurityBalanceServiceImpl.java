package com.flypaas.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.SecurityBalance;
import com.flypaas.service.SecurityBalanceService;
@Service
@Transactional
public class SecurityBalanceServiceImpl extends DaoCenter implements SecurityBalanceService {

	public void add(SecurityBalance securityBalance) {
		securityBalanceDao.add(securityBalance);
	}

	public void addMoney(SecurityBalance securityBalance) {
		securityBalanceDao.addMoney(securityBalance);
	}
	
	public SecurityBalance get(Map<String, Object> map){
		return securityBalanceDao.get(map);
	}

	public void update(SecurityBalance securityBalance) {
		securityBalanceDao.update(securityBalance);
	}

}
