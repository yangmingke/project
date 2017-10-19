package com.ucpaas.commonservice.service;

import com.ucpaas.commonservice.model.ClientBalanceInfo;
import com.ucpaas.commonservice.model.ClientInfo;


/**
 * client注册servcie
 *
 */
public interface ClientRegService {
	
	/**
	 * 注册client信息<br/>
	 * 反向表：userid_appid;mobile_appid<br/>
	 * clientbalance信息<br/>
	 * 2016-03-22
	 * @param clientInfo
	 * @param clientBalanceInfo
	 * @return
	 * @throws Exception
	 */
	int regClientAndAttrAndBalance(ClientInfo clientInfo,ClientBalanceInfo clientBalanceInfo) throws Exception;		
	
	/**
	 * 删除client信息；<br/>
	 * 反向表信息，uin_useridType,uin_mobileType；<br/>
	 * clientbalance信息；<br/>
	 * 2016-03-22
	 * @param clientInfo
	 * @param clientBalanceInfo
	 * @throws Exception
	 */
	void rollbackClient(ClientInfo clientInfo,ClientBalanceInfo clientBalanceInfo)throws Exception;

}
