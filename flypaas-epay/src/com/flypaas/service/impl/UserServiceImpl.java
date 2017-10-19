package com.flypaas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flypaas.dao.UserMapper;
import com.flypaas.service.UserServiceI;
@Service
public class UserServiceImpl implements UserServiceI {
	@Autowired(required=false)
	private UserMapper userMapper;
	
	@Override
	public com.flypaas.model.User queryUser(String sid) {
		System.out.println(userMapper+"------------------");
		return userMapper.selectByPrimaryKey(sid);
	}

}
