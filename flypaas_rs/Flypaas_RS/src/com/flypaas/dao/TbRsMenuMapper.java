package com.flypaas.dao;

import java.util.List;

import com.flypaas.model.TbRsMenu;

public interface TbRsMenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(TbRsMenu record);

    int insertSelective(TbRsMenu record);

    TbRsMenu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(TbRsMenu record);

    int updateByPrimaryKey(TbRsMenu record);
    
    //根据用户Sid查询该用户的一级菜单项
    public List<TbRsMenu> queryMenu_1(String sid);
    
  //根据用户Sid查询该用户的二级菜单项
    public List<TbRsMenu> queryMenu_2(String sid);
}