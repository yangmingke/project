package com.ucpaas.commonservice.service;

import com.ucpaas.commonservice.model.AppBalanceInfo;

/**
 * 应用余额接口
 * 
 * @author luke
 * 
 */
public interface AppBalanceService {

	/**
	 * 根据appSid查询应用余额记录
	 * @param appSid
	 * @return
	 * @throws Exception
	 */
	AppBalanceInfo getByAppSid(String appSid) throws Exception;
	

	/**
	 *  更新应用余额记录
	 * @param AppBalanceInfo
	 * @return
	 */
	int updateByAppSid(AppBalanceInfo appBalanceInfo) throws Exception;
	


}
