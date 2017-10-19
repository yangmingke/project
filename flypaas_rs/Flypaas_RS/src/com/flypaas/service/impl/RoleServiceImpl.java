package com.flypaas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flypaas.dao.TbRsRoleMapper;
import com.flypaas.model.TbRsRole;
import com.flypaas.service.RoleService;

@Service("rolerserviceImpl")
public class RoleServiceImpl implements RoleService{
	@Autowired
	private TbRsRoleMapper tbRsRoleMapper;
	@Override
	public TbRsRole queryRole(String roleName) {
		return tbRsRoleMapper.queryRole(roleName);
	}
	@Override
	public TbRsRole selectByPrimaryKey(Integer roleId) {
		return tbRsRoleMapper.selectByPrimaryKey(roleId);
	}

}
