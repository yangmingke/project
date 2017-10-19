package com.flypaas.service;

import com.flypaas.entity.AcctBalance;

public interface AcctBalanceService {

	public AcctBalance getAcctBalanceBySid(String sid);
	
	public void add(AcctBalance acctb);
	
	public void update(AcctBalance acctb);
}
