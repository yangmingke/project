package com.flypaas.dao.impl;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.SecurityRelieveApplyforDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.SecurityRelieveApplyfor;
@Repository
public class SecurityRelieveApplyforDaoImpl extends MyBatisDao implements
		SecurityRelieveApplyforDao {

	private static final String ADD="addSecurityRelieveApplyfor" ;
	private static final String GET="getSecurityRelieveApplyfor" ;
	private static final String GETBYSID="getSecurityRelieveApplyforBySid" ;

	public void add(SecurityRelieveApplyfor apply) {
		sqlSessionTemplate.insert(ADD, apply);
	}

	public SecurityRelieveApplyfor get(long securityId) {
		return sqlSessionTemplate.selectOne(GET, securityId);
	}

	public SecurityRelieveApplyfor getBySid(String sid) {
		return sqlSessionTemplate.selectOne(GETBYSID, sid);
	}

}
