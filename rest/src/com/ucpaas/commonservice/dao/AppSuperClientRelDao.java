package com.ucpaas.commonservice.dao;

import org.springframework.stereotype.Repository;

import com.ucpaas.commonservice.constant.SqlConstant;
import com.ucpaas.commonservice.dao.base.MasterDao;
import com.ucpaas.commonservice.model.AppSuperClientRelInfo;


/**
 * 应用超级子账号关系Dao
 * 
 * @author luke
 * 
 */
@Repository("appSuperClientRelDao")
public class AppSuperClientRelDao extends MasterDao {
	
	
	public int insert(AppSuperClientRelInfo appSuperClientRelInfo) throws Exception {
		return this.insert(SqlConstant.AppSuperClientRelMapper.insert, appSuperClientRelInfo);
	}
	
	
	public AppSuperClientRelInfo getByAppSid(String appSid) throws Exception {
		return this.selectOne(SqlConstant.AppSuperClientRelMapper.selectByAppSid, appSid);
	}
	
	

}
