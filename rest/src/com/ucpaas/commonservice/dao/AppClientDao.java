package com.ucpaas.commonservice.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ucpaas.commonservice.constant.SqlConstant;
import com.ucpaas.commonservice.dao.base.SlaveDao;
import com.ucpaas.commonservice.model.AppClientInfo;

/**
 * 应用子账号总表AppClientDao
 * @author luke
 *
 */
@Repository("appClientDao")
public class AppClientDao extends SlaveDao {
	
	/**
	 * 查询应用子账号总表
	 * @param map
	 * @return
	 * @throws Exception
	 */
    public AppClientInfo selectOneByMap(Map<String,Object> map) throws Exception{
    	return this.selectOne(SqlConstant.AppClientInfoMapper.selectByMap, map);
    }
    
    /**
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<AppClientInfo> selectListByMap(Map<String,Object> map) throws Exception{
    	return this.selectList(SqlConstant.AppClientInfoMapper.selectByMap, map);
    }
    
    public Integer selectCountAppSid(String appSid) throws Exception{
    	return this.selectOne(SqlConstant.AppClientInfoMapper.selectCountAppSid, appSid);
    }

}