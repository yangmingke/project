package com.flypaas.dao;
import com.flypaas.entity.TbSrvWhiteList;

/**
 * @author chenxijun
 * @version
 */
public interface WhiteListDao {
	
	public void add(TbSrvWhiteList whitelist);
	
	public TbSrvWhiteList get(String id);
	
	public void update(TbSrvWhiteList whiteList);
	
	public void delete(String appSid);
}