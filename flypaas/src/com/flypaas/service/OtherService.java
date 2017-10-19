package com.flypaas.service;

import java.util.Map;

import com.flypaas.entity.FlypaasId;
import com.flypaas.entity.vo.PageContainer;

public interface OtherService {

	public int getExperienceCountByMobile(Map<String, Object> map);
	
	public int getExperienceCountByIp(Map<String, Object> map);
	
	public void addExperience(Map<String, Object> map);
	
	public int getResetPwdCount(String sid);
	
	public void addResetPwdLog(Map<String, Object> map);
	
	public int insertCloudNotifyCall(Map<String, Object> map);
	
	public Map<String, Object> getFeeConfig();
	
	public FlypaasId getId();
	
	public void addAuthFile(Map<String, Object> map);
	
	public Map<String, Object> getAuthFile(String sid);
	
	public void updateAuthFile(Map<String, Object> map);
	
	public PageContainer lyList(PageContainer container);
	
	public Map<String, Object> lyFileSize(Map<String, Object> map);
	
	public void lyDel(Map<String, Object> map);
	
	public Map<String, Object> ly(Map<String, Object> map);
}
