package com.flypaas.service;

import com.flypaas.model.TbRsRoleUser;

public interface RoleUserService {
	public int assignRole(TbRsRoleUser tbRsRoleUser);
	
	/**
	 * 管理员添加用户后分配角色
	 * @param tbRsRoleUser
	 * @return
	 */
	public int addUserRole(TbRsRoleUser tbRsRoleUser);
}
