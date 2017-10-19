package com.flypaas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flypaas.dao.TbRsRoleUserMapper;
import com.flypaas.model.TbRsRoleUser;
import com.flypaas.service.RoleUserService;

@Service("roleServiceImpl")
public class RoleUserServiceImpl implements RoleUserService{
	@Autowired
	private TbRsRoleUserMapper tbRsRoleUserMapper;
	@Override
	public int assignRole(TbRsRoleUser tbRsRoleUser) {
		
		return tbRsRoleUserMapper.assignRole(tbRsRoleUser);
	}
	@Override
	public int addUserRole(TbRsRoleUser tbRsRoleUser) {
		
		return tbRsRoleUserMapper.insertSelective(tbRsRoleUser);
	}

}
