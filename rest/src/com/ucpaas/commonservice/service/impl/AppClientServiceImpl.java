package com.ucpaas.commonservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ucpaas.commonservice.dao.AppClientDao;
import com.ucpaas.commonservice.model.AppClientInfo;
import com.ucpaas.commonservice.service.AppClientService;

@Service("appClientService")
public class AppClientServiceImpl implements AppClientService {
	private static final Logger log = LoggerFactory.getLogger(AppClientServiceImpl.class);

	
	@Resource(name="appClientDao")
	private AppClientDao appClientDao;


	@Override
	public List<AppClientInfo> getPageListByAppSid(String appSid, Integer start, Integer pageSize) throws Exception {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("appSid", appSid);
		parameterMap.put("start", start);
		parameterMap.put("pageSize", pageSize);
		log.debug("appSid={},start={},pageSize={},parameterMap={}",appSid,start,pageSize,parameterMap);
		return this.appClientDao.selectListByMap(parameterMap);

	}


	@Override
	public Integer getAppClientCountByAppSid(String appSid) throws Exception {
		return this.appClientDao.selectCountAppSid(appSid);
	}



}
