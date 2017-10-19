package com.flypaas.dao;

import com.flypaas.model.TbRsRoleMenu;

public interface TbRsRoleMenuMapper {
    int deleteByPrimaryKey(Integer roleMenuId);

    int insert(TbRsRoleMenu record);

    int insertSelective(TbRsRoleMenu record);

    TbRsRoleMenu selectByPrimaryKey(Integer roleMenuId);

    int updateByPrimaryKeySelective(TbRsRoleMenu record);

    int updateByPrimaryKey(TbRsRoleMenu record);
}