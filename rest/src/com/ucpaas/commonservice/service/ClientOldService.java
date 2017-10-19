package com.ucpaas.commonservice.service;

import java.util.Map;

import com.ucpaas.commonservice.model.ClientBalanceInfo;
import com.ucpaas.commonservice.model.ClientInfo;

/**
 * 子账号（old）注册service
 * 仅供rest2014注册client同步调用
 *
 */
public interface ClientOldService {
	
	/**
	 * rest2014注册子账号<br/>
	 * tb_ucpaas_client(子账号)<br/>
	 * tb_bill_client_balance(子账号余额)<br/>
	 * tb_ucpaas_client_pool(手机号码池)<br/>
	 * 
	 * 2016-03-21
	 * @param clientInfo
	 * @param clientBalanceInfo
	 * @return
	 * @throws Exception
	 */
	Map<String,Object> clientReg(ClientInfo clientInfo,ClientBalanceInfo clientBalanceInfo) throws Exception;
	
	/**
	 * 异步调用计费中间件client余额充值接口，<br/>
	 * 
	 * 2016-05-11
	 * @param sid
	 * @param appSid
	 * @param clientNumber
	 * @param clientbalance
	 */
	public void clientBalanceChargeInterface(String sid,String appSid,String clientNumber,String clientbalance);

	/**
	 * 回滚134写入的数据
	 * tb_ucpaas_client表  client_number唯一
	 * tb_client_balance表 client_number唯一
	 * tb_ucpaas_client_pool  appid和mobile唯一
	 * @param clientInfo
	 * @throws Exception
	 */
	void rollbackClient(ClientInfo clientInfo)throws Exception;
	
}
