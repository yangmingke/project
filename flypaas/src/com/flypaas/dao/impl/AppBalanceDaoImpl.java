package com.flypaas.dao.impl;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.AppBalanceDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.AppBalance;
@Repository
public class AppBalanceDaoImpl extends MyBatisDao implements AppBalanceDao {
	
	private static final String GETAPPBALANCE="getAppBalance";
	private static final String ADDAPPBALANCE="addAppBalance";
	private static final String UPDATEAPPBALANCE="updateAppBalance";
	private static final String GETALLBALANCE="getAllBalance";
	private static final String DELETEAPPBALANCE="deleteAppBalance";
	private static final String UNBINDAPPBALANCE="unBindAppBalance";

	public AppBalance getAppBalance(AppBalance appBalance) {
		return sqlSessionTemplate.selectOne(GETAPPBALANCE, appBalance);
	}

	public void addAppBalance(AppBalance appBalance) {
		insert(ADDAPPBALANCE, appBalance);
	}

	public void updateAppBalance(AppBalance appBalance) {
		update(UPDATEAPPBALANCE, appBalance);
	}

	public double getAllBalance(String sid) {
		return sqlSessionTemplate.selectOne(GETALLBALANCE, sid);
	}

	public void deleteAppBalance(AppBalance appBalance) {
		update(DELETEAPPBALANCE, appBalance);
	}

	public void unBindAppBalance(AppBalance appBalance) {
		update(UNBINDAPPBALANCE, appBalance);
	}

}
