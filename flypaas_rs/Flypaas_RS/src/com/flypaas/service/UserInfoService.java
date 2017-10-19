package com.flypaas.service;

import java.util.Map;

import com.flypaas.model.TbRsAccountBalance;
import com.flypaas.model.TbRsAccountInfo;
import com.flypaas.model.TbRsUserInfo;

public interface UserInfoService {
	//登录 通过用户名/邮箱/手机+密码登录验证
	public Map queryUserInfo(TbRsUserInfo record);
	
	//验证邮箱是否被注册
	public TbRsUserInfo queryUserInfo2(String email);
	
	public TbRsUserInfo selectByPrimaryKey(String sid);
	
	//邮箱激活并注册（只填Rid） 添加资源方信息  资源账户
	int insertSelective(TbRsUserInfo record1);
	
	//修改用户状态  激活邮箱
	public  int updateUserStatusBySid(String sid);
	 
	//更新用户激活码  再次发送
	public int updateUserValiCode(TbRsUserInfo tbRsUserInfo1);
	
	//注册用户信息  添加资源方信息  资源账户
	public int updateByPrimaryKeySelective(TbRsUserInfo record1,TbRsAccountInfo record2,TbRsAccountBalance record3);

	public TbRsUserInfo queryUserByMobile(String mobile);
}
