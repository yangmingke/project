package com.flypaas.dao;


import com.flypaas.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(String sid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String sid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}