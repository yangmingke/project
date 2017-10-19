package com.ucpaas.commonservice.service;

import com.ucpaas.commonservice.model.UserBalanceInfo;

/**
 * 开发者用户余额接口
 * @author luke
 *
 */

public interface UserBalanceService {
	
	/**
	 * 根据用户sid查询用户余额记录
	 * @param sid
	 * @return
	 */
	UserBalanceInfo getBySid(String sid) throws Exception;
	
	
	/**
	 * 据sid更新用户余额记录
	 * @param userBalance	用户余额
	 * @return
	 */
	int updateBySid(UserBalanceInfo userBalance) throws Exception;
	
	

	
	
	

}
