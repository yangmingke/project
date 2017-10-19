package com.flypaas.service;

import com.flypaas.entity.AppBalance;

public interface AppBalanceService {

	public AppBalance getAppBalance(AppBalance appBalance);
	
	public void addAppBalance(AppBalance appBalance);
	
	public void updateAppBalance(AppBalance appBalance);
	
	public void appBalance(AppBalance appBalance);
	
	public double getAllBalance(String sid);
	
	public void unBindAppBalance(AppBalance appBalance);
}
