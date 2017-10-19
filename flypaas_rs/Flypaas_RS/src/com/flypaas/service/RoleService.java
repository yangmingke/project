package com.flypaas.service;

import com.flypaas.model.TbRsRole;

public interface RoleService {
	//得到管理员角色的role
	public TbRsRole queryRole(String rolename);
	
	public TbRsRole selectByPrimaryKey(Integer roleId);
}
