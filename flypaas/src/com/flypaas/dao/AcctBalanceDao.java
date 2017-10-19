package com.flypaas.dao;

import com.flypaas.entity.AcctBalance;

/** 
* TODO 余额
* @author 29p9g02
* @version 
*/
public interface AcctBalanceDao {

	public AcctBalance getAcctBalanceBySid(String sid);
	
	public void add(AcctBalance acctb);
	
	public void update(AcctBalance acctb);
	
	public AcctBalance getAcctBySid(String sid);
	
}
