package com.flypaas.service;

import java.util.Map;

import com.flypaas.entity.AcctPackageRel;

public interface AcctPackageRelService {
	
	public AcctPackageRel getAcctPackageRel(String sid);
	
	public void update(AcctPackageRel rel);
	
	public void addDefaultPck(AcctPackageRel rel);
	
	public void updateTempPck(Map<String,Object> map);
	
	public void insertTempPck(Map<String,Object> map);
	
	public Map<String, Object> getTempPck(Map<String,Object> map);
	
}
