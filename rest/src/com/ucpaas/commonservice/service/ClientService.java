package com.ucpaas.commonservice.service;

import java.util.List;

import com.ucpaas.commonservice.model.ClientBalanceInfo;
import com.ucpaas.commonservice.model.ClientInfo;

/**
 * 子账号分表接口
 * 
 * @author luke
 * 
 */
public interface ClientService {

	/**
	 * 根据ClientNumber更新ClientInfo
	 * 
	 * @param clientInfo
	 * @return
	 */
	int updateByClientNumber(ClientInfo clientInfo) throws Exception;

	/**
	 * 根据uin查询client
	 * 
	 * @param uin
	 * @return
	 */
	ClientInfo getByUin(Integer uin) throws Exception;

	/**
	 * 通过clientNumber获取client
	 * 
	 * @param clientNumber
	 * @return Exception
	 */
	ClientInfo getByClientNumber(String clientNumber) throws Exception;

	/**
	 * 根据手机号,应用id获取client 先根据attr=mobile_appSid在缓存中查询反向表， 再从反向表根据uin查询client
	 * 
	 * @param mobile
	 *            手机号
	 * @param appSid
	 *            应用id
	 * @return Exception
	 */
	ClientInfo getByMobileAndAppSidCache(String mobile, String appSid) throws Exception;

	/**
	 * 先根据attr=userId_appSid在缓存中查询反向表
	 * 
	 * @param userId
	 *            client子账号用户id
	 * @param appSid
	 *            应用id
	 * @return
	 * @throws Exception
	 */
	ClientInfo getByUserIdAndAppSidCache(String userId, String appSid) throws Exception;

	/**
	 * 根据uin更新子账号信息
	 * 
	 * @param clientInfo
	 * @return
	 * @throws Exception
	 */
	int updateByUin(ClientInfo clientInfo) throws Exception;

	/**
	 * 根据userId和appSid更新子账号信息
	 * 
	 * @param clientInfo
	 * @return
	 * @throws Exception
	 */
	int updateByUserIdAndAppSid(ClientInfo clientInfo) throws Exception;

	/**
	 * 插入子账号
	 * 
	 * @param clientInfo
	 * @return
	 * @throws Exception
	 */
	int insert(ClientInfo clientInfo) throws Exception;

	/**
	 * 插入子账号信息和反向表信息
	 * 
	 * @param clientInfo
	 * @throws Exception
	 */
	int insetClientAndAttr(ClientInfo clientInfo) throws Exception;

	/**
	 * 更新client信息；同时调用接口，删除缓存中的信息 同时调用第三方接口，删除第三方缓存中的client信息
	 * 
	 * @param clientInfo
	 * @return
	 * @throws Exception
	 */
	int updateByClientNumberCache(ClientInfo clientInfo) throws Exception;

	/**
	 * 根据userId和appSid更新子账号信息 同时调用第三方接口，删除第三方缓存中的client信息
	 * 
	 * @param clientInfo
	 * @return
	 * @throws Exception
	 */
	int updateByUserIdAndAppSidCache(ClientInfo clientInfo) throws Exception;

	/**
	 * 更新client子账号的手机号码； 如果手机号为空，则删除反向表的旧数据； 如果手机号不为空，则删除反向表的旧数据，插入新数据。
	 * 同时调用第三方接口，删除第三方缓存中的client信息
	 * 
	 * @param clientInfo
	 *            存放oldMobile
	 * @param newMobile
	 * @return
	 * @throws Exception
	 */
	int updateMobileCache(ClientInfo clientInfo, String newMobile) throws Exception;

	/**
	 * 兼容rest2014双写版本 更新client子账号的手机号码； 如果手机号为空，则删除反向表的旧数据；
	 * 如果手机号不为空，则删除反向表的旧数据，插入新数据。 同时调用第三方接口，删除第三方缓存中的client信息
	 * 
	 * @param clientInfo
	 *            包含client_number:必填;appSid:必填;mobile:和数据库记录一致
	 * @param newMobile
	 *            必填，不能为null,空字符串或有值
	 * @return
	 * @throws Exception
	 */
	int updateMobileCacheByClientNumber(ClientInfo clientInfo, String newMobile) throws Exception;

	/**
	 * 根据clientnumber从缓存查询client
	 * 
	 * @param clientNumber
	 * @return
	 * @throws Exception
	 *             TODO test
	 */
	ClientInfo getByClientNumberCache(String clientNumber) throws Exception;

	/**
	 * 根据uin从缓存查询client
	 * 
	 * @param uin
	 * @return
	 * @throws Exception
	 *             TODO test
	 */
	ClientInfo getByUinCache(Integer uin) throws Exception;

	/**
	 * 关闭client子账号
	 * 
	 * 1.删除反向表中mobile、userId信息。 
	 * 2.更新client状态为已关闭，mobile、userId设为空字符串。client状态0:关闭，1:正常，2:充值未平账 
	 * 3.更新clientbalance状态。0：冻结 1 正常
	 * 4.调用第三方接口，删除第三方缓存中的client信息(刘晓健)
	 * 
	 * 2015-11-02添加
	 * 
	 * @param clientInfo
	 * @return
	 * @throws Exception
	 */

	int closeClient(ClientInfo clientInfo,ClientBalanceInfo clientBalanceInfo) throws Exception;

	/**
	 * 根据clientnumber关闭client子账号
	 * 
	 * 1.删除反向表中mobile、userId信息。 
	 * 2.更新client状态为已关闭，mobile、userId设为空字符串。client状态0:关闭，1:正常，2:充值未平账 
	 * 3.更新clientbalance状态。0：冻结 1 正常
	 * 4.调用第三方接口，删除第三方缓存中的client信息(刘晓健)
	 * 
	 * 2015-11-02添加
	 * 兼容rest2014双写版
	 * 
	 * @param clientInfo
	 * @param clientBalanceInfo
	 * @return
	 * @throws Exception
	 */
	int closeClientByClientNumber(ClientInfo clientInfo, ClientBalanceInfo clientBalanceInfo) throws Exception;
	
	
	/**
	 * 根据uin删除client
	 * 2016-03-21新增
	 * @param clientInfo
	 * @return
	 * @throws Exception
	 */
	int deleteByUin(ClientInfo clientInfo) throws Exception;

	/**
	 * IM要求 查询 每个分表的所有client信息（可用status=1）
	 * @param appSid
	 * @param tablie_index
	 * @return
	 */
	List<ClientInfo> getByAppSid(String appSid, int tablie_index);
	
	

}
