package com.flypaas.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.FlypaasId;
import com.flypaas.entity.vo.PageContainer;
import com.flypaas.service.OtherService;
@Service
@Transactional
public class OtherServiceImpl extends DaoCenter implements OtherService {

	public int getExperienceCountByMobile(Map<String, Object> map) {
		return otherDao.getExperienceCountByMobile(map);
	}

	public int getExperienceCountByIp(Map<String, Object> map) {
		return otherDao.getExperienceCountByIp(map);
	}

	public void addExperience(Map<String, Object> map) {
		otherDao.addExperience(map);
	}

	public int getResetPwdCount(String sid) {
		return otherDao.getResetPwdCount(sid);
	}
	
	public void addResetPwdLog(Map<String, Object> map){
		otherDao.addResetPwdLog(map);
	}

	public int insertCloudNotifyCall(Map<String, Object> map) {
		return otherDao.insertCloudNotifyCall(map);
	}

	public Map<String, Object> getFeeConfig() {
		return otherDao.getFeeConfig();
	}

	public FlypaasId getId() {
		return otherDao.getId();
	}

	public void addAuthFile(Map<String, Object> map) {
		myBatisDao.insert("addAuthFile", map);
	}

	public Map<String, Object> getAuthFile(String sid) {
		return myBatisDao.getOneInfo("getAuthFile", sid);
	}

	public void updateAuthFile(Map<String, Object> map) {
		myBatisDao.update("updateAuthFile", map);
	}

	public PageContainer lyList(PageContainer container) {
		return statisticsMyBatisDao.getSearchPage("lyList", "lyListCount", container);
	}

	public Map<String, Object> lyFileSize(Map<String, Object> map) {
		return statisticsMyBatisDao.getOneInfo("lyFileSize", map);
	}

	public void lyDel(Map<String, Object> map) {
		statisticsMyBatisDao.delete("lyDel", map);
	}

	public Map<String, Object> ly(Map<String, Object> map) {
		return statisticsMyBatisDao.getOneInfo("ly", map);
	}
}
