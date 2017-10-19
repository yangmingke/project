package com.flypaas.dao;

import com.flypaas.model.TbRsRoleUser;

public interface TbRsRoleUserMapper {
    int deleteByPrimaryKey(Integer ruId);

    int insert(TbRsRoleUser record);

    int insertSelective(TbRsRoleUser record);

    TbRsRoleUser selectByPrimaryKey(Integer ruId);

    int updateByPrimaryKeySelective(TbRsRoleUser record);

    int updateByPrimaryKey(TbRsRoleUser record);
    
    public TbRsRoleUser queryUserRole(String sid);
    
    //将管理员角色给当前注册用户
    public int assignRole(TbRsRoleUser tbRsRoleUser);

	void updateSelective(TbRsRoleUser tbRsRoleUser);
}