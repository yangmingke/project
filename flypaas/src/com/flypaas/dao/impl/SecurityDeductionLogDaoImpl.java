package com.flypaas.dao.impl;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.SecurityDeductionLogDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.SecurityDeductionLog;
@Repository
public class SecurityDeductionLogDaoImpl extends MyBatisDao implements SecurityDeductionLogDao {

	private static final String ADD="addSecurityDeductionLog";
	
	public void addSecurityDeductionLog(SecurityDeductionLog log) {
		sqlSessionTemplate.insert(ADD, log);
	}

}
