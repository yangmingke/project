package com.flypaas.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.AcctBalance;
import com.flypaas.service.AcctBalanceService;
@Service
@Transactional
public class AcctBalanceServiceImpl extends DaoCenter implements AcctBalanceService {
	
	public AcctBalance getAcctBalanceBySid(String sid) {
		return acctBalanceDao.getAcctBalanceBySid(sid);
	}
	public void add(AcctBalance acctb) {
		acctBalanceDao.add(acctb);
	}
	public void update(AcctBalance acctb) {
		acctBalanceDao.update(acctb);
	}

}
