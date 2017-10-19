package com.flypaas.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.constant.AppConstant;
import com.flypaas.constant.SysConstant;
import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.AppBalance;
import com.flypaas.service.AppBalanceService;
import com.flypaas.utils.DateUtil;
import com.flypaas.utils.StrUtil;
@Service
@Transactional
public class AppBalanceServiceImpl extends DaoCenter implements AppBalanceService {

	public AppBalance getAppBalance(AppBalance appBalance) {
		return appBalanceDao.getAppBalance(appBalance);
	}

	public void addAppBalance(AppBalance appBalance) {
		appBalanceDao.addAppBalance(appBalance);
	}

	public void updateAppBalance(AppBalance appBalance) {
		appBalanceDao.updateAppBalance(appBalance);
	}

	public void appBalance(AppBalance appBalance) {
		AppBalance bl = getAppBalance(appBalance);
		appBalance.setBalance(appBalance.getBalance()*SysConstant.RATE);
		appBalance.setStatus(AppConstant.APP_BALANCE_STATUS_1);
		if(StrUtil.isEmpty(bl)){
			Date date = DateUtil.getCurrentDate();
			appBalance.setCreateDate(date);
			appBalance.setUpdateDate(date);
			addAppBalance(appBalance);
		}else{
			updateAppBalance(appBalance);
		}
	}

	public double getAllBalance(String sid) {
		return appBalanceDao.getAllBalance(sid);
	}
	public void unBindAppBalance(AppBalance appBalance){
		appBalanceDao.unBindAppBalance(appBalance);
	}

}
