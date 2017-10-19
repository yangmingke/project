package com.flypaas.service;

import java.io.File;

import com.flypaas.entity.Security;
import com.flypaas.entity.TbFlypaasUser;

public interface AgreementService {

	public void addAgreementUser(String sid ,File file,String type);
	
	public void update(Security security);
	
	public Security getAgreementUser(String sid);
	
	public void unfreezeBalanceToCloud(String sid);
	
	public void relieveSecurity(Security security,TbFlypaasUser user);
	
}
