package com.flypaas.service;

import java.util.List;
import com.flypaas.entity.TbSrvCallback;

/** 
* TODO 白名单service
* @author 29p9g02
* @version 
*/
public interface CallBackService {
	
	public void add(TbSrvCallback cb);
	
	public void delete(String appSid);
	
	public List<TbSrvCallback> get(String appSid);
}
