package com.ucpaas.commonservice.dao;

import org.springframework.stereotype.Repository;

import com.ucpaas.commonservice.constant.SqlConstant;
import com.ucpaas.commonservice.dao.base.MasterDao;
import com.ucpaas.commonservice.model.AppBalanceInfo;
/**
 * 应用余额Dao
 * 
 * @author luke
 * 
 */
@Repository("appBalanceDao")
public class AppBalanceDao extends MasterDao {
    



    /**
     * 根据主键查询应用余额记录
     * @param id	主键
     * @return
     * @throws Exception
     */
    public AppBalanceInfo selectByAppSid(String appSid) throws Exception{
    	return this.selectOne(SqlConstant.AppBalanceInfoMapper.selectByAppSid, appSid);
    }


    /**
     * 更新应用余额记录
     * @param appBalanceInfo
     * @return
     * @throws Exception
     */
    public int updateByAppSid(AppBalanceInfo appBalanceInfo) throws Exception{
    	return this.update(SqlConstant.AppBalanceInfoMapper.updateByAppSid, appBalanceInfo);
    }
    
    
    
}