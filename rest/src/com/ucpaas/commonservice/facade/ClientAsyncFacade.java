package com.ucpaas.commonservice.facade;

import com.ucpaas.commonservice.model.ClientBalanceInfo;
import com.ucpaas.commonservice.model.ClientInfo;
import com.ucpaas.commonservice.model.TestClientInfo;

/**
 * 子账号异步调用服务接口 
 * <br/>
 * 添加时间：2015-09-01<br/>
 * 添加原因：兼容rest2旧版升级，异步向110新库插入、更新、删除子账号记录。<br/>
 * 
 * @author luke
 * 
 */
public interface ClientAsyncFacade {

	/**
	 * 添加子账号信息和反向表信息
	 * 
	 * <p>
	 * 根据clientNumber生成db_node,uin_mod,uin,将信息插入client分表	<br/>
	 * 
	 * 插入反向表规则如下：	<br/>
	 * 如果子账号的userid非空，则插入type=101：attr={userId}_{appId}	<br/>
	 * 如果子账号的userid为空，则插入type=101：attr={clientNumber}_{appId}	<br/>
	 * 如果手机号不为空，则插入type=102：attr={mobile}_{appId}	<br/>
	 * 
	 * 根据attr生成db_node,uin_mod,将反向表信息插入attr2uin分表	<br/>
	 * 
	 * @param clientInfo
	 */
	void insertClientAndAttr(ClientInfo clientInfo);

	/**
	 * 添加子账号余额信息
	 * 
	 * @param clientBalanceInfo
	 */
	void insert(ClientBalanceInfo clientBalanceInfo);

	/**
	 * 添加测试子账号
	 * 
	 * @param testClientInfo
	 */
	void insert(TestClientInfo testClientInfo);
	

	/**
	 * 根据ClientNumber更新ClientInfo <br/>
	 * (注意，不包括更新手机号,即换绑解绑关闭手机号接口,更新手机号调用updateMobileCache接口)<br/>
	 * 同时调用第三方接口，删除第三方缓存中的client信息<br/>
	 * @param clientInfo
	 */
	void updateByClientNumberCache(ClientInfo clientInfo);
	


	/**
	 * 根据clientNumber更新子账号余额信息<br/>
	 * 修改除balance之外的所有信息<br/>
	 *
	 * 更新balance调用接口updateClientBalanceByClientNumber(String clientNumber,String chargeType,String balance);				
	 * 
	 * @param clientBalanceInfo
	 */
	void updateByClientNumber(ClientBalanceInfo clientBalanceInfo);

	/**
	 * 根据clientNumber更新测试子账号
	 * 
	 * @param testClientInfo
	 */
	void updateByClientNumber(TestClientInfo testClientInfo);
	
	
	/**
	 * 根据clientnumber更新client子账号的手机号码；换绑解绑手机号。<br/>
	 * 如果手机号为空，则删除反向表的旧数据；<br/>
	 * 如果手机号不为空，则删除反向表的旧数据，插入新数据。<br/>
	 * 同时调用第三方接口，删除第三方缓存中的client信息<br/>
	 * @param clientInfo	包含client_number:必填;appSid:必填;mobile:和数据库记录一致
	 * @param newMobile		必填，不能为null,空字符串或有值
	 * @return
	 */ 
	 void updateMobileCacheByClientNumber(ClientInfo clientInfo,String newMobile);
	 
	 
	 /**
	  * 根据clientNumber给clientbalance充值
	  * 
	  * @param clientNumber
	  * @param clientChargeType		0 充值；1 回收。
	  * @param balance			充值或回收的金额。金额为正数。充值回收逻辑在代码实现。
	  */
	 void chargeClientBalanceByClientNumber(String clientNumber,String clientChargeType,Long balance);
	
	
	 

	 /**
	  * 根据clientnumber关闭client子账号<br/>
	  * 
	  * 1.删除反向表中mobile、userId信息。<br/>
	  * 2.更新client状态为已关闭，mobile、userId设为空字符串。client状态  0:关闭，1:正常，2:充值未平账<br/>
	  * 3.更新clientbalance状态。0：冻结 1 正常<br/>
	  * 4.调用第三方接口，删除第三方缓存中的client信息(刘晓健)<br/>
	  * 
	  * 2015-11-02添加
	  * @param clientInfo
	  * @param clientBalanceInfo
	  */
	 void closeClientByClientNumber(ClientInfo clientInfo,ClientBalanceInfo clientBalanceInfo);
	 
	 
	 /**
	  * 复制老库的client，clientbalance信息到新库client分表<br/>
	  * 异步调用<br/>
	  * 1.写入反向表信息，userid_appid;mobile_appid<br/>
	  * 2.写入client信息<br/>
	  * 3.写入clientbalance信息<br/>
	  * 说明：client，clientbalance对象的uin，clientNumber，userid需要赋值<br/>
	  * userid=clientNumber。<br/>
	  * 2016-03-22
	  * 
	  * @param clientInfo
	  * @param clientBalanceInfo，值为null，则不生成clientbalance信息
	  */
	 void copyClientReg2015(ClientInfo clientInfo,ClientBalanceInfo clientBalanceInfo);

}
