package com.ucpaas.commonservice.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ucpaas.commonservice.constant.SqlConstant;
import com.ucpaas.commonservice.dao.base.MasterDao;
import com.ucpaas.commonservice.model.AppInfo;

/**
 * 应用Dao
 * 
 * @author luke
 * 
 */
@Repository("appDao")
public class AppDao extends MasterDao {

	/**
	 * 添加应用
	 * 
	 * @param appInfo
	 * @return
	 * @throws Exception
	 */
	public int insert(AppInfo appInfo) throws Exception {
		// return this.insert(SqlConstant.AppInfoMapper.insert, appInfo);
		return 0;
	}

	/**
	 * 查询应用记录
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	public AppInfo selectByMap(Map<String, Object> parameterMap) throws Exception {
		return this.selectOne(SqlConstant.AppInfoMapper.selectByMap, parameterMap);
	}

	/**
	 * 查询应用记录,不包含clientcount子账号总数的值
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	public AppInfo selectPartByMap(Map<String, Object> parameterMap) throws Exception {
		return this.selectOne(SqlConstant.AppInfoMapper.selectPartByMap, parameterMap);
	}

	/**
	 * 更新应用记录
	 * 
	 * @param appInfo
	 * @return
	 * @throws Exception
	 */
	public int updateByAppSid(AppInfo appInfo) throws Exception {
		return this.update(SqlConstant.AppInfoMapper.updateByAppSid, appInfo);
	}

	/**
	 * 根据主键删除应用记录
	 * 
	 * @param appSid
	 * @return
	 * @throws Exception
	 */
	public int deleteByAppSid(String appSid) throws Exception {
		return 0;
	}

	/**
	 * 根据appSid更新应用下子账号总数
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	public int updateClientCountByMap(Map<String, Object> parameterMap) throws Exception {
		return this.update(SqlConstant.AppInfoMapper.updateClientCountByMap, parameterMap);
	}

	public List<AppInfo> selectSid(String sid) {
		return this.selectList(SqlConstant.AppInfoMapper.selectBySid, sid);
	}

}