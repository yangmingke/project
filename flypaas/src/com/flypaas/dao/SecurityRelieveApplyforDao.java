package com.flypaas.dao;

import com.flypaas.entity.SecurityRelieveApplyfor;

public interface SecurityRelieveApplyforDao {

	public void add(SecurityRelieveApplyfor apply);
	
	public SecurityRelieveApplyfor get(long securityId);
	
	public SecurityRelieveApplyfor getBySid(String sid);
}
