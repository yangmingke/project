package com.ucpaas.commonservice.service;

import com.ucpaas.commonservice.model.AppSuperClientRelInfo;
import com.ucpaas.commonservice.model.ClientInfo;

public interface AppSuperClientRelService {
	
	AppSuperClientRelInfo getByAppSid(String appSid) throws Exception;
	
	int insert(AppSuperClientRelInfo appSuperClientRelInfo) throws Exception;
	
	ClientInfo getClientByAppSid(String appSid) throws Exception;

}
