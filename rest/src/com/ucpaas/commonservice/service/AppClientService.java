package com.ucpaas.commonservice.service;

import java.util.List;

import com.ucpaas.commonservice.model.AppClientInfo;

/**
 * 应用子账号总表查询接口
 * @author luke
 *
 */
public interface AppClientService {

	

	/**
	 * 根据应用id分页查询记录集合
	 * @param appSid	应用id
	 * @param start		起始行
	 * @param pageSize	每页行数
	 * @return
	 */
	List<AppClientInfo> getPageListByAppSid(String appSid, Integer start, Integer pageSize) throws Exception;
	
	
	/**
	 * 根据appSid查询应用下子账号总数
	 * @param appSid
	 * @return
	 * @throws Exception
	 */
	Integer getAppClientCountByAppSid(String appSid) throws Exception;
	

}
