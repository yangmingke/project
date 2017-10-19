package com.flypaas.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.OtherDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.FlypaasId;
@Repository
public class OtherDaoImpl extends MyBatisDao implements OtherDao {
	
	private static final String getByMobile="getExperienceCountByMobile";
	private static final String getByIp="getExperienceCountByIp";
	private static final String add="addExperience";
	private static final String getRodamCuctomerManager="getRodamCuctomerManager";
	private static final String getResetPwdCount="getResetPwdCount";
	private static final String addResetPwdLog="addResetPwdLog";
	private static final String getFeeConfig="getFeeConfig";
	private static final String getId="getId";

	public int getExperienceCountByMobile(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne(getByMobile, map);
	}

	public int getExperienceCountByIp(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne(getByIp, map);
	}

	public void addExperience(Map<String, Object> map) {
		sqlSessionTemplate.insert(add,map);
	}

	public Map<String, Object> getRodamCuctomerManager() {
		return sqlSessionTemplate.selectOne(getRodamCuctomerManager);
	}

	public int getResetPwdCount(String sid) {
		return sqlSessionTemplate.selectOne(getResetPwdCount,sid);
	}

	public void addResetPwdLog(Map<String, Object> map) {
		sqlSessionTemplate.insert(addResetPwdLog,map);
	}

	public int insertCloudNotifyCall(Map<String, Object> map) {
		return sqlSessionTemplate.insert("insertCloudNotifyCall",map);
	}

	public Map<String, Object> getFeeConfig() {
		return sqlSessionTemplate.selectOne(getFeeConfig);
	}

	public FlypaasId getId() {
		FlypaasId vo = new FlypaasId();
		sqlSessionTemplate.insert(getId, vo);
		return vo;

	}
}
