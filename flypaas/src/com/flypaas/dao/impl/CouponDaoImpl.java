package com.flypaas.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.CouponDao;
import com.flypaas.daocenter.MyBatisDao;
@Repository
public class CouponDaoImpl extends MyBatisDao implements CouponDao {
	
	private static final String GET="getCouponByNum";
	private static final String GETLIST="getCouponByOther";
	private static final String GETMEETIDLIST="getMeetIdList";
	private static final String ADD="addCoupon";
	private static final String ADDLOG="addCouponLog";
	private static final String GETLOGCOUNT="getLogCount";
	private static final String GETCOUPONLOGCOUNTBYNUM="getCouponLogCountByNum";
	private static final String GETRODOMNUM="getRodomNum";
	private static final String UPDATESENDED="updateSended";
	private static final String ADDMEET="addMeet";
	private static final String DELETEMEET="deletemeet";
	private static final String GETCURRENTMEET="getCurrentMeet";

	public Map<String, Object> getCouponByNum(String num) {
		return sqlSessionTemplate.selectOne(GET, num);
	}

	public void addCouponLog(Map<String, Object> map) {
		sqlSessionTemplate.insert(ADDLOG, map);
	}

	public void add(Map<String, Object> map) {
		sqlSessionTemplate.insert(ADD, map);
	}

	public List<Map<String, Object>> getCouponByOtherList(
			Map<String, Object> map) {
		return sqlSessionTemplate.selectList(GETLIST, map);
	}

	public List<Map<String, Object>> getMeetIdList() {
		return sqlSessionTemplate.selectList(GETMEETIDLIST);
	}

	public int getCouponLogCount(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne(GETLOGCOUNT, map);
	}

	public int getCouponLogCountByNum(String num) {
		return sqlSessionTemplate.selectOne(GETCOUPONLOGCOUNTBYNUM, num);
	}
	public Map<String, Object> getRodomNum(String meetId){
		return sqlSessionTemplate.selectOne(GETRODOMNUM,meetId);
	}

	public void updateSended(String id) {
		sqlSessionTemplate.update(UPDATESENDED,id);
	}

	public void addMeet(String meetId) {
		sqlSessionTemplate.insert(ADDMEET, meetId);
	}

	public void deleteMeet() {
		sqlSessionTemplate.insert(DELETEMEET);
	}

	public String getCurrentMeet() {
		return sqlSessionTemplate.selectOne(GETCURRENTMEET);
	}
}
