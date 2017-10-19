package com.ucpaas.commonservice.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ucpaas.commonservice.dao.AppBalanceDao;
import com.ucpaas.commonservice.model.AppBalanceInfo;
import com.ucpaas.commonservice.service.AppBalanceService;

@Service("appBalanceService")
public class AppBalanceServiceImpl implements AppBalanceService {

	@Resource(name = "appBalanceDao")
	private AppBalanceDao appBalanceDao;
	

	@Override
	public AppBalanceInfo getByAppSid(String appSid) throws Exception {
		return this.appBalanceDao.selectByAppSid(appSid);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public int updateByAppSid(AppBalanceInfo appBalanceInfo) throws Exception {
		return this.appBalanceDao.updateByAppSid(appBalanceInfo);
	}



}
