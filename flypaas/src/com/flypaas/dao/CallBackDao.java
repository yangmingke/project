package com.flypaas.dao;


import java.util.List;
import com.flypaas.entity.TbSrvCallback;

/**
 * @author chenxijun
 * @version
 */
public interface CallBackDao {
	
	public void add(TbSrvCallback cb);
	
	public void delete(String appSid);
	
	public List<TbSrvCallback> get(String appSid);
}