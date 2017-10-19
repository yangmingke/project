package com.flypaas.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.flypaas.dao.SDDownloadCountDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.daocenter.StatisticsMyBatisDao;
@Repository
public class SDDownloadCountDaoImpl extends MyBatisDao implements SDDownloadCountDao {
	
	@Autowired
	private StatisticsMyBatisDao statisticsMyBatisDao;

	private static final String GETSDDOWNLOADCOUNT="getSDDownloadCount";
	private static final String UPDATESDDOWNLOADCOUNT="updateSDDownloadCount";
	private static final String GETCOUNTLIST="getCountList";
	private static final String GETSDKDESC="getSdkDesc";
	
	private static final String GETSTATICTISSDK="getStatictisSdk";
	private static final String GETSTATICTISDEMO="getStatictisDemo";
	private static final String ADDSTATICTISSDK="addStatictisSdk";
	private static final String ADDSTATICTISDEMO="addStatictisDemo";
	private static final String UPDATESTATICTISSDK="updateStatictisSdk";
	private static final String UPDATESTATICTISDEMO="updateStatictisDemo";
	
	public Map<String, Object> getCount(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne(GETSDDOWNLOADCOUNT, map);
	}

	public void update(Map<String, Object> map) {
		sqlSessionTemplate.update(UPDATESDDOWNLOADCOUNT, map);
	}

	public List<Map<String, Object>> getCountList() {
		return sqlSessionTemplate.selectList(GETCOUNTLIST, null);
	}

	public List<Map<String, Object>> desc(String key) {
		return sqlSessionTemplate.selectList(GETSDKDESC, key);
	}

	public Map<String, Object> getStatictisSdk(Map<String, Object> map) {
		return statisticsMyBatisDao.getOneInfo(GETSTATICTISSDK, map);
	}

	public Map<String, Object> getStatictisDemo(Map<String, Object> map) {
		return statisticsMyBatisDao.getOneInfo(GETSTATICTISDEMO, map);
	}

	public void addStatictisSdk(Map<String, Object> map) {
		statisticsMyBatisDao.insert(ADDSTATICTISSDK, map);
	}

	public void addStatictisDemo(Map<String, Object> map) {
		statisticsMyBatisDao.insert(ADDSTATICTISDEMO, map);
	}

	public void updateStatictisSdk(Map<String, Object> map) {
		statisticsMyBatisDao.update(UPDATESTATICTISSDK, map);
	}

	public void updateStatictisDemo(Map<String, Object> map) {
		statisticsMyBatisDao.update(UPDATESTATICTISDEMO, map);
	}

}
