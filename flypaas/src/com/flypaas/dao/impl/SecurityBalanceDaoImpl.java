package com.flypaas.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.SecurityBalanceDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.SecurityBalance;
@Repository
public class SecurityBalanceDaoImpl extends MyBatisDao implements SecurityBalanceDao {

	private static final String ADD="addSecurityBalance";
	private static final String GET="getSecurityBalance";
	private static final String UPDATE="updateSecurityBalance";
	private static final String ADDMONEY="addSecurityMoney";
	private static final String UNFREEZEBALANCE="unfreezeBalance";
	
	public void add(SecurityBalance securityBalance) {
		sqlSessionTemplate.insert(ADD, securityBalance);
	}

	public SecurityBalance get(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne(GET, map);
	}

	public void update(SecurityBalance securityBalance) {
		sqlSessionTemplate.update(UPDATE, securityBalance);
	}

	public void addMoney(SecurityBalance securityBalance) {
		sqlSessionTemplate.update(ADDMONEY, securityBalance);
	}

	public void unfreezeBalance(Map<String, Object> map) {
		sqlSessionTemplate.update(UNFREEZEBALANCE, map);
	}

}
