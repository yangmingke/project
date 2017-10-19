package com.flypaas.dao.impl;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.AcctBalanceDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.AcctBalance;
@Repository
public class AcctBalanceDaoImpl extends MyBatisDao implements AcctBalanceDao {
	private static final String GETACCTBALANCE="getAcctBalance";
	private static final String ADD="addAcctBalance";
	private static final String UPDATEACCTBALANCE="updateAcctBalance";
	private static final String GETACCT="getAcct";
	
	public AcctBalance getAcctBalanceBySid(String sid) {
		return sqlSessionTemplate.selectOne(GETACCTBALANCE, sid);
	}
	public void add(AcctBalance acctb) {
		sqlSessionTemplate.insert(ADD, acctb);
	}
	public void update(AcctBalance acctb) {
		sqlSessionTemplate.update(UPDATEACCTBALANCE, acctb);
	}
	public AcctBalance getAcctBySid(String sid) {
		return sqlSessionTemplate.selectOne(GETACCT, sid);
	}

}
