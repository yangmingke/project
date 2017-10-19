package com.ucpaas.commonservice.service;

import java.util.List;

import com.ucpaas.commonservice.model.TestClientInfo;

/**
 * 测试子账号接口
 * @author luke
 *
 */
public interface TestClientService {
	
	/**
	 * 根据clientNumber更新测试子账号记录
	 * @param testClientInfo
	 * @return
	 */
	int updateByClientNumber(TestClientInfo testClientInfo) throws Exception;
	
	/**
	 * 查询应用下的测试子账号集合
	 * @param appSid
	 * @return
	 * @throws Exception
	 */
	List<TestClientInfo> getListByAppSid(String appSid) throws Exception;
	
	/**
	 * 根据clientNumber查询测试子账号
	 * @param clientNumbe
	 * @return
	 * @throws Exception
	 */
	TestClientInfo getByClientNumber(String clientNumber) throws Exception;
	
	/**
	 * 插入测试子账号
	 * @param testClientInfo
	 * @return
	 * @throws Exception
	 */
	int insert(TestClientInfo testClientInfo) throws Exception;
	
	/**
	 * 根据开发者sid查询测试子账号集合
	 * @param sid
	 * @return
	 * @throws Exception
	 */
	List<TestClientInfo> getListBySid(String sid) throws Exception;
	
	/**
	 * 根据uin删除测试client
	 * @return
	 * @throws Exception
	 */
	int deleteTestClientByUin(int uin) throws Exception;

}
