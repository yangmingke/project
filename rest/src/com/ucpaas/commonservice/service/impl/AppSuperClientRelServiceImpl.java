package com.ucpaas.commonservice.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ucpaas.commonservice.dao.AppSuperClientRelDao;
import com.ucpaas.commonservice.model.AppSuperClientRelInfo;
import com.ucpaas.commonservice.model.ClientInfo;
import com.ucpaas.commonservice.service.AppSuperClientRelService;
import com.ucpaas.commonservice.service.ClientService;

@Service("appSuperClientRelService")
public class AppSuperClientRelServiceImpl implements AppSuperClientRelService {
	
	@Resource(name = "appSuperClientRelDao")
	private AppSuperClientRelDao appSuperClientRelDao;
	
	@Resource(name = "clientService")
	private ClientService clientService;

	@Override
	public AppSuperClientRelInfo getByAppSid(String appSid) throws Exception {
		return this.appSuperClientRelDao.getByAppSid(appSid);
	}

	@Override
	public int insert(AppSuperClientRelInfo appSuperClientRelInfo) throws Exception {
		return this.appSuperClientRelDao.insert(appSuperClientRelInfo);
	}

	@Override
	public ClientInfo getClientByAppSid(String appSid) throws Exception {
		ClientInfo clientInfo = null;
		AppSuperClientRelInfo rel = this.getByAppSid(appSid);
		if(null != rel && StringUtils.isNotBlank(rel.getClientNumber())){
			clientInfo = this.clientService.getByClientNumber(rel.getClientNumber());
		}
		return clientInfo;
	}

}
