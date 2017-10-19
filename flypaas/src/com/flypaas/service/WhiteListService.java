package com.flypaas.service;

import com.flypaas.entity.TbSrvWhiteList;
/** 
* TODO 白名单service
* @author 29p9g02
* @version 
*/
public interface WhiteListService {
	
	public void add(TbSrvWhiteList whiteList);
	
	public TbSrvWhiteList get(String id);
	
	public void update(TbSrvWhiteList whiteList);
	
	public void delete(String appSid);
}
