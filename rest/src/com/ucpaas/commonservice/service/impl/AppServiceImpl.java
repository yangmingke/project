package com.ucpaas.commonservice.service.impl;

import java.util.Date;
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
import com.ucpaas.commonservice.dao.AppDao;
import com.ucpaas.commonservice.model.AppInfo;
import com.ucpaas.commonservice.service.AppService;
import com.ucpaas.commonservice.service.base.RedisBaseService;
import com.ucpaas.commonservice.util.properties.PropertiesUtil;

@Service("appService")
public class AppServiceImpl extends RedisBaseService<String,AppInfo> implements AppService {
	private static final Logger log = LoggerFactory.getLogger(AppServiceImpl.class);
	
	@Resource(name = "appDao")
	private AppDao appDao;
	
	@Override
	public AppInfo getByAppSid(String appSid,Boolean isEqual,String status) throws Exception{
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("appSid", appSid);
		parameterMap.put("isEqual", isEqual);
		parameterMap.put("status", status);
		log.debug("appSid={},isEqual={},status={},parameterMap={}",appSid,isEqual,status,parameterMap); 
		return this.appDao.selectByMap(parameterMap);
	}
	
	@Override
	public AppInfo getByAppSidCache(String appSid, Boolean isEqual, String status) throws Exception {
		AppInfo appInfo = null;
		//1.从缓存拿数据
		appInfo = this.getFromCache(Constants.REDIS_KEY_APP + appSid);
		if(appInfo == null){
			//2.如果缓存中没有数据，从数据库拿数据
			appInfo = this.getByAppSid(appSid, isEqual, status);
			if(appInfo != null){
				//3.将数据放入缓存中,有效期n天
				//this.setToCacheDays(Constants.REDIS_KEY_APP + appSid, appInfo,RandomUtils.nextInt(Constants.REDIS_DAY_MIN, Constants.REDIS_DAY_MAX));
				//有效期n秒，2015-10-28修改
				PropertiesUtil propertiesUtil = PropertiesUtil.createPropertiesUtil();
				String redisSeconds = propertiesUtil.getProperty(Constants.REDIS_EFFECTIVE_SECONDS);
				this.setToCacheSeconds(Constants.REDIS_KEY_APP + appSid, appInfo, Long.valueOf(redisSeconds));
			}
		}
		return appInfo;
	}


	@Transactional(propagation = Propagation.REQUIRED)
	public int updateByAppSid(AppInfo appInfo) throws Exception {
		return this.appDao.updateByAppSid(appInfo);
	}
	
	
	@Override
	public int updateByAppSidCache(AppInfo appInfo) throws Exception {
		//删除缓存中的数据
		this.deleteFromCache(Constants.REDIS_KEY_APP + appInfo.getAppSid());
		return this.updateByAppSid(appInfo);
	}




	@Transactional(propagation = Propagation.REQUIRED)
	public int updateClientCount(String appSid, boolean isAdd, Integer clientCount) throws Exception  {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("appSid", appSid);
		parameterMap.put("isAdd", isAdd);
		parameterMap.put("clientCount", clientCount);
		parameterMap.put("updateDate", new Date());
		
		log.debug("appSid={},isAdd={},clientCount={},parameterMap={}",appSid,isAdd,clientCount,parameterMap); 
		return this.appDao.updateClientCountByMap(parameterMap);
	}

	@Override
	public List<AppInfo> getBySid(String sid) {
		
		
		return this.appDao.selectSid(sid);
	}











}
