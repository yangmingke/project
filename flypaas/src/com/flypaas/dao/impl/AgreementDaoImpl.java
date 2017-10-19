package com.flypaas.dao.impl;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.AgreementDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.Security;
@Repository
public class AgreementDaoImpl extends MyBatisDao implements AgreementDao {

	private static final String ADD="addAgreementUser";
	private static final String UPDATE="updateAgreementUser";
	private static final String GET="getAgreementUser";
	
	public void addAgreementUser(Security security) {
		sqlSessionTemplate.insert(ADD, security);
	}

	public void update(Security security) {
		sqlSessionTemplate.update(UPDATE, security);
	}

	public Security getAgreementUser(String sid) {
		return sqlSessionTemplate.selectOne(GET, sid);
	}

}
