package com.ucpaas.commonservice.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ucpaas.commonservice.constant.SqlConstant;
import com.ucpaas.commonservice.dao.base.MasterDao;
import com.ucpaas.commonservice.model.UserInfo;

/**
 * 用户Dao
 * 
 * @author luke
 * 
 */

@Repository("userDao")
public class UserDao extends MasterDao {
	
	
	/**
	 * 根据sid获取用户信息
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	public UserInfo selectByMap(Map<String, Object> parameterMap) throws Exception {
		return this.selectOne(SqlConstant.UserInfoMapper.selectByMap, parameterMap);
	}
	
	
	/**
	 * 更新用户信息
	 * @param userInfo
	 * @return
	 */
	public int updateBySid(UserInfo userInfo) throws Exception {
		return this.update(SqlConstant.UserInfoMapper.updateBySid, userInfo);
	}
	
	/**
	 * 根据密码，email或mobile，查询开发者用户信息
	 * email或mobile存放在username中
	 * 2015-09-21添加
	 * 
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	public List<UserInfo> selectByPwdUserName(UserInfo userInfo) throws Exception {
		return this.selectList(SqlConstant.UserInfoMapper.selectByPwdUserName, userInfo);
	}
	
	
	
	

}
