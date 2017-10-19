package com.ucpaas.commonservice.service;

import java.util.List;

import com.ucpaas.commonservice.model.AppInfo;

/**
 * 应用接口
 * 
 * @author luke
 * 
 */
public interface AppService {
	
	/**
	 * 根据appSid查询某状态下的应用信息
	 * @param appSid	主键
	 * @param isEqual	等于为true,不等于为false
	 * @param status	状态
	 * @return
	 */
	AppInfo getByAppSid(String appSid,Boolean isEqual,String status) throws Exception;
	
	/**
	 * 根据appSid从缓存查询某状态下的应用信息
	 * @param appSid	主键
	 * @param isEqual	等于为true,不等于为false
	 * @param status	状态
	 * @return
	 */
	AppInfo getByAppSidCache(String appSid,Boolean isEqual,String status) throws Exception;
	

	/**
	 * 更新应用记录
	 * @param appInfo
	 * @return
	 */
	int updateByAppSid(AppInfo appInfo) throws Exception;
	
	/**
	 * 更新应用记录,同时在缓存中删除值
	 * @param appInfo
	 * @return
	 */
	int updateByAppSidCache(AppInfo appInfo) throws Exception;
	
	/**
	 * 根据appSid更新应用下的子账号总数
	 * 
	 * @param appSid		应用主键
	 * @param isAdd			增加为true，减少为false
	 * @param clientCount	增加或减少的子账号数量
	 * @return
	 */
	int updateClientCount(String appSid,boolean isAdd,Integer clientCount)throws Exception;

	/**
	 * 根据sid查询所有的app信息
	 * @param sid
	 * @return
	 */
	List<AppInfo> getBySid(String sid);


}
