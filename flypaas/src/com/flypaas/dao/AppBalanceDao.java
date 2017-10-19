package com.flypaas.dao;

import com.flypaas.entity.AppBalance;

public interface AppBalanceDao {
	
	public AppBalance getAppBalance(AppBalance appBalance);
	
	public void addAppBalance(AppBalance appBalance);
	
	public void updateAppBalance(AppBalance appBalance);
	
	public double getAllBalance(String sid);
	
	public void deleteAppBalance(AppBalance appBalance);
	
	public void unBindAppBalance(AppBalance appBalance);

}
