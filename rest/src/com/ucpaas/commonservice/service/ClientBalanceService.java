package com.ucpaas.commonservice.service;

import com.ucpaas.commonservice.model.ClientBalanceInfo;
/**
 * 子账号余额接口
 * @author luke
 *
 */
public interface ClientBalanceService {
	
	/**
	 * 根据clientNumber查询子账号余额记录
	 * @param clientNumber
	 * @return
	 */
	ClientBalanceInfo getByClientNumber(String clientNumber) throws Exception;
	
	
	
	/**
	 * 根据ClientNumber更新ClientBalanceInfo
	 * @param clientBalanceInfo
	 * @return
	 */
	int updateByClientNumber(ClientBalanceInfo clientBalanceInfo) throws Exception;
	
	
	/**
	 * 根据uin更新子账号余额信息
	 * @param clientBalanceInfo
	 * @return
	 * @throws Exception
	 */
	int updateByUin(ClientBalanceInfo clientBalanceInfo) throws Exception;
	
	
	
	/**
	 * 插入子账号余额信息
	 * 兼容rest2014双写版
	 * 2015-09-01添加
	 * @param clientBalanceInfo
	 * @return
	 * @throws Exception
	 */
	int insert(ClientBalanceInfo clientBalanceInfo) throws Exception;
	
	
	/**
	 * 根据clientNumber更新balance余额信息
	 * 兼容rest2014双写版
	 * 
	 * @param clientNumber
	 * @param chargeType		0 充值；1 回收。
	 * @param balance			充值或回收的金额。
	 * @return
	 * @throws Exception
	 */
	int chargeClientBalanceByClientNumber(String clientNumber,String chargeType,Long balance) throws Exception;
	
	
	/**
	 * 根据uin删除子账号余额信息
	 * 2016-03-21
	 * @param clientBalanceInfo
	 * @return
	 * @throws Exception
	 */
	int deleteByUin(ClientBalanceInfo clientBalanceInfo) throws Exception;

	
	

}
