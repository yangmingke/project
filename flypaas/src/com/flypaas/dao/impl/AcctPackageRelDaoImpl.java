package com.flypaas.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.AcctPackageRelDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.AcctPackageRel;
@Repository
public class AcctPackageRelDaoImpl extends MyBatisDao implements AcctPackageRelDao {
	
	private static final String GETACCTPACKAGE="getAcctPackageRel" ;
	private static final String UPDATEACCTPACKAGE="updateAcctPackage";
	private static final String ADDDEFAULTPCK="addDefaultPck";
	private static final String INSERTTEMPPCK="insertTempPck";
	private static final String UPDATETEMPPCK="updateTempPck";
	private static final String GETTEMPPCKBYSID="getTempPckBySid";
	
	public AcctPackageRel getAcctPackageRel(String sid) {
		return sqlSessionTemplate.selectOne(GETACCTPACKAGE, sid);
	}
	public void update(AcctPackageRel rel) {
		sqlSessionTemplate.update(UPDATEACCTPACKAGE, rel);
	}
	public void addDefaultPck(AcctPackageRel rel) {
		sqlSessionTemplate.insert(ADDDEFAULTPCK, rel);
	}
	public void updateTempPck(Map<String, Object> map) {
		sqlSessionTemplate.update(UPDATETEMPPCK, map);
	}
	public void insertTempPck(Map<String, Object> map) {
		sqlSessionTemplate.insert(INSERTTEMPPCK, map);
	}
	public Map<String, Object> getTempPck(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne(GETTEMPPCKBYSID, map);
	}

}
