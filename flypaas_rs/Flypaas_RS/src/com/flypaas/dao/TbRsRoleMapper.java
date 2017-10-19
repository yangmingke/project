package com.flypaas.dao;

import com.flypaas.model.TbRsRole;
import com.flypaas.model.TbRsRoleMenu;

public interface TbRsRoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(TbRsRole record);

    int insertSelective(TbRsRole record);

    TbRsRole selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(TbRsRole record);

    int updateByPrimaryKey(TbRsRole record);
    
    public TbRsRoleMenu queryRoleMenu(Integer rid);
    
	//得到管理员角色的role
	public TbRsRole queryRole(String roleName);
}