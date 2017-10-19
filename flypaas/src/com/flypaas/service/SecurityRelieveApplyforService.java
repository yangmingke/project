package com.flypaas.service;

import com.flypaas.entity.SecurityRelieveApplyfor;

public interface SecurityRelieveApplyforService {

	public void add(SecurityRelieveApplyfor apply);
	
	public SecurityRelieveApplyfor get(long securityId);
	
	public SecurityRelieveApplyfor getBySid(String sid);
}
