package com.flypaas.dao;

import com.flypaas.entity.Security;

public interface AgreementDao {
	
	public void addAgreementUser(Security security);
	
	public void update(Security security);
	
	public Security getAgreementUser(String sid);
	
}
