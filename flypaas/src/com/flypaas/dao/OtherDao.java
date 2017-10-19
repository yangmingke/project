package com.flypaas.dao;

import java.util.Map;

import com.flypaas.entity.FlypaasId;


public interface OtherDao {
	
	public int getExperienceCountByMobile(Map<String, Object> map);
	
	public int getExperienceCountByIp(Map<String, Object> map);
	
	public void addExperience(Map<String, Object> map);
	
	public Map<String, Object> getRodamCuctomerManager();
	
	public int getResetPwdCount(String sid);
	
	public void addResetPwdLog(Map<String, Object> map);

	public int insertCloudNotifyCall(Map<String, Object> map);
	
	public Map<String, Object> getFeeConfig();
	
	public FlypaasId getId();
}
