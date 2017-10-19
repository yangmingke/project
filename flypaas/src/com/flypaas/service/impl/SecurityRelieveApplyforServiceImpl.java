package com.flypaas.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.SecurityRelieveApplyfor;
import com.flypaas.service.SecurityRelieveApplyforService;
@Service
@Transactional
public class SecurityRelieveApplyforServiceImpl extends DaoCenter implements
		SecurityRelieveApplyforService {

	public void add(SecurityRelieveApplyfor apply) {
		securityRelieveApplyforDao.add(apply);
	}

	public SecurityRelieveApplyfor get(long securityId) {
		return securityRelieveApplyforDao.get(securityId);
	}
	
	public SecurityRelieveApplyfor getBySid(String sid){
		return securityRelieveApplyforDao.getBySid(sid);
	}

}
