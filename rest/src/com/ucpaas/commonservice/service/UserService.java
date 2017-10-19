package com.ucpaas.commonservice.service;

import java.util.List;

import com.ucpaas.commonservice.model.UserInfo;

/**
 * 用户接口
 * 
 * @author luke
 * 
 */
public interface UserService {
	/**
	 * 根据sid查询某状态下的用户信息
	 * 
	 * @param sid
	 *            主键
	 * @param isEqual
	 *            等于为true,不等于为false
	 * @param status
	 *            状态
	 * @return
	 */
	UserInfo getBySid(String sid, Boolean isEqual, String status) throws Exception;
	
	/**
	 * 根据sid查询某状态下的用户信息，先查询缓存，再查数据库
	 * 
	 * @param sid
	 * @param isEqual
	 * @param status
	 * @return
	 * @throws Exception
	 */
	UserInfo getBySidCache(String sid, Boolean isEqual, String status) throws Exception;
	
	

	/**
	 * 更新用户信息
	 * 
	 * @param userInfo
	 * @return
	 */
	int updateBySid(UserInfo userInfo) throws Exception;
	
	/**
	 * 更新用户信息,同时在缓存中删除该用户信息
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	int updateBySidCache(UserInfo userInfo) throws Exception;
	
	/**
	 * 根据密码，email或mobile，查询开发者用户信息
	 * email或mobile存放在username中
	 * 2015-09-21添加
	 * 
	 * @param userInfo
	 * @return
	 */
	List<UserInfo> getByPwdUserName(String password, String userName) throws Exception;

}
