package com.flypaas.service.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flypaas.dao.TbRsRoleUserMapper;
import com.flypaas.dao.TbRsUserInfoMapper;
import com.flypaas.model.TbRsRoleUser;
import com.flypaas.model.TbRsUserInfo;
import com.flypaas.service.user.UserService;

/**
 * 用户管理
 * @author win10
 *
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	@Autowired
	private TbRsUserInfoMapper tbRsUserInfoMapper;
	@Autowired
	private TbRsRoleUserMapper tbRsRoleUserMapper;
	
	@Override
	public int editUserPwd(TbRsUserInfo tbRsUserInfo) {
		
		return tbRsUserInfoMapper.editUserPwd(tbRsUserInfo);
	}

	@Override
	public int editUser(TbRsUserInfo tbRsUserInfo) {
		
		return tbRsUserInfoMapper.editUser(tbRsUserInfo);
	}

	@Override
	public List<TbRsUserInfo> queryUserList(String netSid) {
		
		return tbRsUserInfoMapper.queryAllUser(netSid);
	}

	@Override
	public int queryCount(String netSid) {
		
		return tbRsUserInfoMapper.queryCount(netSid);
	}

	@Override
	public int addUser(TbRsUserInfo tbRsUserInfo) {
		
		return tbRsUserInfoMapper.insert(tbRsUserInfo);
	}

	@Override
	public int updateByPrimaryKeySelective(TbRsUserInfo record) {
		return tbRsUserInfoMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public int updateSelective(TbRsUserInfo record, TbRsRoleUser tbRsRoleUser) {
		tbRsRoleUserMapper.updateSelective(tbRsRoleUser);
		return tbRsUserInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delUser(String sid) {
		
		return tbRsUserInfoMapper.delUser(sid);
	}

	@Override
	public TbRsUserInfo queryUserInfoById(String sid) {

		return tbRsUserInfoMapper.queryUserInfoById(sid);
	}
	
}
