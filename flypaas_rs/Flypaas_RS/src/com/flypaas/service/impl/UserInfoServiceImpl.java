package com.flypaas.service.impl;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flypaas.dao.TbRsAccountBalanceMapper;
import com.flypaas.dao.TbRsAccountInfoMapper;
import com.flypaas.dao.TbRsUserInfoMapper;
import com.flypaas.model.TbRsAccountBalance;
import com.flypaas.model.TbRsAccountInfo;
import com.flypaas.model.TbRsUserInfo;
import com.flypaas.service.UserInfoService;
/**
 * 登陆注册管理
 * @author win10
 *
 */
@Service
public class UserInfoServiceImpl implements UserInfoService{
	@Autowired
	public TbRsUserInfoMapper tbRsUserInfoMapper;
	
	@Autowired
	public TbRsAccountBalanceMapper tbRsAccountBalanceMapper;
	
	@Autowired
	public TbRsAccountInfoMapper tbRsAccountInfoMapper;
	
	@Override
	public Map queryUserInfo(TbRsUserInfo record) {
		
		return tbRsUserInfoMapper.queryUserInfo(record);
	}

	@Override
	public TbRsUserInfo queryUserInfo2(String email) {
//		Map<String,String> para = new HashMap<String,String>();
//		para.put("email", email);
//		para.put("mobile", mobile);
		return tbRsUserInfoMapper.queryUserInfo2(email);
	}

	@Override
	public int insertSelective(TbRsUserInfo record) {
		return tbRsUserInfoMapper.insertSelective(record);
	}
	
	@Override
	public int updateUserStatusBySid(String sid) {
		
		return tbRsUserInfoMapper.updateUserStatusBySid(sid);
	}

	@Override
	public TbRsUserInfo selectByPrimaryKey(String sid) {
		
		return tbRsUserInfoMapper.selectByPrimaryKey(sid);
	}

	@Override
	public int updateUserValiCode(TbRsUserInfo tbRsUserInfo1) {
		
		return tbRsUserInfoMapper.updateUserValiCode(tbRsUserInfo1);
	}

	@Override
	public int updateByPrimaryKeySelective(TbRsUserInfo record1,TbRsAccountInfo record2,TbRsAccountBalance record3) {
		int temp = 0;
		if(record1!=null){
			temp = tbRsUserInfoMapper.updateByPrimaryKeySelective(record1);
		}
		if(record2!=null){
			temp =tbRsAccountInfoMapper.updateByPrimaryKey(record2);
		}
		if(record3!=null){
			temp =tbRsAccountBalanceMapper.insertSelective(record3);
		}
		return temp;
	}

	@Override
	public TbRsUserInfo queryUserByMobile(String mobile) {
		return tbRsUserInfoMapper.queryUserByMobile(mobile);
	}

	

	

}
