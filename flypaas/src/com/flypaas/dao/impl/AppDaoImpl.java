package com.flypaas.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.AppDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.daocenter.StatisticsMyBatisDao;
import com.flypaas.entity.Application;
import com.flypaas.entity.vo.PageContainer;

@Repository
public class AppDaoImpl extends MyBatisDao implements AppDao {

	private static final String ADD = "addApp";
	private static final String UPDATE = "updateApp";
	private static final String GETAPPBYSID = "getAppBySid";
	private static final String GETAPPBYOTHER = "getAppByOther";
	private static final String GETAPPBYOTHERCOUNT = "getAppByOtherCount";
	private static final String APPNAMEEXIST = "appNameExist";
	private static final String GETAPP = "getAppById";
	private static final String GETTESTAPP = "getTestApp";
	private static final String GETONLINEAPP = "getOnlineApp";
	private static final String GETOUTHEDAPP = "getOuthedApp";
	private static final String GET_ACTIVE_COUNT = "getActiveCount";
	private static final String GETALLAPPBYSID="getAllAppBySid";
	private static final String GETAPPSNOTCONTAINSTESTAPPBYSID="getAppsNotContainsTestAppBySid";
	private static final String GETAPPBYSIDAPPSID="getAppBySidAppSid";
	private static final String GETSESSIONIDALIAS="getSessionIdAlias";
	
	private StatisticsMyBatisDao statisticsMyBatisDao;
	
	@Resource(name="statisticsMyBatisDao")
	public void setStatisticsMyBatisDao(StatisticsMyBatisDao statisticsMyBatisDao) {
		this.statisticsMyBatisDao = statisticsMyBatisDao;
	}

	public void add(Application app) {
		sqlSessionTemplate.insert(ADD, app);
	}

	public void update(Application app) {
		sqlSessionTemplate.update(UPDATE, app);
	}

	public PageContainer getApp(PageContainer page) {
		PageContainer result = null;
		Map<String, Object> params = null;
		result = getSearchPage(GETAPPBYOTHER, GETAPPBYOTHERCOUNT, page);
		params = result.getParams();
		if (null == params) {
			params = new HashMap<String, Object>();
			result.setParams(params);
		}
		if (null == params.get("total")) {
			params.put("total", 0);
		}
		return result;
	}

	public List<Application> getAppBySid(String sid) {
		return sqlSessionTemplate.selectList(GETAPPBYSID, sid);
	}

	public int appNameExist(Application app) {
		return sqlSessionTemplate.selectOne(APPNAMEEXIST, app);
	}

	public Application getAppById(String id) {
		return sqlSessionTemplate.selectOne(GETAPP, id);
	}

	public Application getTestApp(String sid) {
		return sqlSessionTemplate.selectOne(GETTESTAPP, sid);
	}

	public int getOnlineAppCount(String sid) {
		return sqlSessionTemplate.selectOne(GETONLINEAPP, sid);
	}

	public List<Application> getAppSmsNumBySid(String sid) {
		return sqlSessionTemplate.selectList(GETOUTHEDAPP, sid);
	}

	public Map<String, Object> getActiveCount(Map<String, Object> params) {
		return statisticsMyBatisDao.getSqlSessionTemplate().selectOne(GET_ACTIVE_COUNT, params);
	}

	public List<Application> getAllAppBySid(String sid) {
		return sqlSessionTemplate.selectList(GETALLAPPBYSID, sid);
	}
	public List<Application> getAppsNotContainsTestAppBySid(String sid) {
		return sqlSessionTemplate.selectList(GETAPPSNOTCONTAINSTESTAPPBYSID, sid);
	}

	public Application getAppBySidAppSid(Application app) {
		return sqlSessionTemplate.selectOne(GETAPPBYSIDAPPSID, app);
	}

	@Override
	public List<Map<String, String>> getSessionIdAlias(Map<String, Object> param) {
		return sqlSessionTemplate.selectList(GETSESSIONIDALIAS, param);
	}
}
