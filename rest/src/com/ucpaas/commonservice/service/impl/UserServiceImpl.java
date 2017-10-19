package com.ucpaas.commonservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ucpaas.commonservice.constant.Constants;
import com.ucpaas.commonservice.dao.UserDao;
import com.ucpaas.commonservice.model.UserInfo;
import com.ucpaas.commonservice.service.UserService;
import com.ucpaas.commonservice.service.base.RedisBaseService;
import com.ucpaas.commonservice.util.properties.PropertiesUtil;

@Service("userService")
public class UserServiceImpl extends RedisBaseService<String,UserInfo> implements UserService {
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Resource(name = "userDao")
	private UserDao userDao;

	@Override
	public UserInfo getBySid(String sid, Boolean isEqual, String status) throws Exception {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("sid", sid);
		parameterMap.put("isEqual", isEqual);
		parameterMap.put("status", status);
		log.debug("【查询用户】,parameterMap={}", parameterMap);
		return this.userDao.selectByMap(parameterMap);
	}


	@Transactional(propagation = Propagation.REQUIRED)
	public int updateBySid(UserInfo userInfo) throws Exception {
		return this.userDao.updateBySid(userInfo);
	}


	@Override
	public UserInfo getBySidCache(String sid, Boolean isEqual, String status) throws Exception {
		UserInfo userInfo = null;
		//1.从缓存拿数据
		userInfo = this.getFromCache(Constants.REDIS_KEY_USER + sid);
		if(userInfo == null){
			//2.如果缓存中没有数据，从数据库拿数据
			userInfo = this.getBySid(sid, isEqual, status);
			if(userInfo != null){
				//3.将数据放入缓存中,有效期n天
				//this.setToCacheDays(Constants.REDIS_KEY_USER + sid, userInfo,RandomUtils.nextInt(Constants.REDIS_DAY_MIN, Constants.REDIS_DAY_MAX));
				//有效期n秒，2015-10-28修改
				PropertiesUtil propertiesUtil = PropertiesUtil.createPropertiesUtil();
				String redisSeconds = propertiesUtil.getProperty(Constants.REDIS_EFFECTIVE_SECONDS);
				this.setToCacheSeconds(Constants.REDIS_KEY_USER + sid, userInfo, Long.valueOf(redisSeconds));
			}
		}
		return userInfo;
	}


	@Override
	public int updateBySidCache(UserInfo userInfo) throws Exception {
		//删除缓存中的数据
		this.deleteFromCache(Constants.REDIS_KEY_USER + userInfo.getSid());
		return this.updateBySid(userInfo);
	}


	@Override
	public List<UserInfo> getByPwdUserName(String password, String userName) throws Exception {
		UserInfo user = new UserInfo();
		user.setPassword(password);
		user.setUsername(userName);
		return this.userDao.selectByPwdUserName(user);
	}

}
