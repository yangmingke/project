package com.flypaas.service.admin.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flypaas.dao.TbRsUserInfoMapper;
import com.flypaas.model.TbRsUserInfo;
import com.flypaas.service.admin.UserInfoService;
import com.flypaas.util.PageContainer;
/**
 * 登陆注册管理
 * @author win10
 *
 */
@Service
public class UserInfoServiceImpl implements UserInfoService{
	@Autowired
	public TbRsUserInfoMapper tbRsUserInfoMapper;
	
	@Override
	public TbRsUserInfo queryUserInfo(TbRsUserInfo record) {
		
		return tbRsUserInfoMapper.queryUserInfo(record);
	}

	@Override
	public TbRsUserInfo selectByPrimaryKey(String sid) {
		
		return tbRsUserInfoMapper.selectByPrimaryKey(sid);
	}

	@Override
	public int updateByPrimaryKeySelective(TbRsUserInfo record) {
		
		return tbRsUserInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int editUserPwd(TbRsUserInfo tbRsUserInfo) {
		return tbRsUserInfoMapper.editUserPwd(tbRsUserInfo);
	}

	@Override
	public PageContainer queryUserList(Integer valueOf) {
		return null;
	}

	@Override
	public Map<String,String> queryManagerSidBynetSid(String netSid) {
		Map<String,String> para = new HashMap<String,String>();
		para.put("netSid", netSid);
		return tbRsUserInfoMapper.queryManagerSidBynetSid(para);
	}

	

	

}
