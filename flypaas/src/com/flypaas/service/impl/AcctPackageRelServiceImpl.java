package com.flypaas.service.impl;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.AcctPackageRel;
import com.flypaas.service.AcctPackageRelService;
@Service
@Transactional
public class AcctPackageRelServiceImpl extends DaoCenter implements AcctPackageRelService {
	
	public AcctPackageRel getAcctPackageRel(String sid) {
		return acctPackageRelDao.getAcctPackageRel(sid);
	}
	public void update(AcctPackageRel rel) {
		acctPackageRelDao.update(rel);
	}
	public void addDefaultPck(AcctPackageRel rel) {
		acctPackageRelDao.addDefaultPck(rel); 
	}
	public void updateTempPck(Map<String, Object> map) {
		acctPackageRelDao.updateTempPck(map);
	}
	public void insertTempPck(Map<String, Object> map) {
		acctPackageRelDao.insertTempPck(map);
	}
	public Map<String, Object> getTempPck(Map<String, Object> map) {
		return acctPackageRelDao.getTempPck(map);
	}

}
