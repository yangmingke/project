package com.ucpaas.commonservice.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ucpaas.commonservice.dao.UserBalanceDao;
import com.ucpaas.commonservice.model.UserBalanceInfo;
import com.ucpaas.commonservice.service.UserBalanceService;


@Service("userBalanceService")
public class UserBalanceServiceImpl implements UserBalanceService {

	@Resource(name = "userBalanceDao")
	private UserBalanceDao userBalanceDao;
	
	@Override
	public UserBalanceInfo getBySid(String sid) throws Exception {
		return this.userBalanceDao.selectBySid(sid);
	}
	

	@Transactional(propagation = Propagation.REQUIRED)
	public int updateBySid(UserBalanceInfo userBalance) throws Exception {
		return this.userBalanceDao.updateBySid(userBalance);
	}


}
