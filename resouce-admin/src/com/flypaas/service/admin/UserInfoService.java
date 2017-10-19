package com.flypaas.service.admin;


import java.util.Map;

import com.flypaas.model.TbRsUserInfo;
import com.flypaas.util.PageContainer;

public interface UserInfoService {
	//登录 通过用户名/邮箱/手机+密码登录验证
	public TbRsUserInfo queryUserInfo(TbRsUserInfo record);
	
	public TbRsUserInfo selectByPrimaryKey(String sid);
	//注册用户信息
	public int updateByPrimaryKeySelective(TbRsUserInfo record);
	
	public int editUserPwd(TbRsUserInfo tbRsUserInfo);

	public PageContainer queryUserList(Integer valueOf);
	
	/**
	 * 通过netSid查询管理员的sid
	 * @param netSid
	 * @return
	 */
	public Map<String,String> queryManagerSidBynetSid(String netSid);
}
