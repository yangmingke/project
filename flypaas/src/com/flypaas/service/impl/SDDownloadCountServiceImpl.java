package com.flypaas.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.daocenter.DaoCenter;
import com.flypaas.service.SDDownloadCountService;
@Service
@Transactional
public class SDDownloadCountServiceImpl extends DaoCenter implements SDDownloadCountService {

	public Map<String, Object> getCount(Map<String, Object> map) {
		return sDDownloadCountDao.getCount(map);
	}

	public void update(Map<String, Object> map) {
		sDDownloadCountDao.update(map);
	}

	public List<Map<String, Object>> getCountList() {
		return sDDownloadCountDao.getCountList();
	}

	public List<Map<String, Object>> desc(String key) {
		return sDDownloadCountDao.desc(key);
	}

	public Map<String, Object> getStatictisSdk(Map<String, Object> map) {
		return sDDownloadCountDao.getStatictisSdk(map);
	}

	public Map<String, Object> getStatictisDemo(Map<String, Object> map) {
		return sDDownloadCountDao.getStatictisDemo(map);
	}

	public void addStatictisSdk(Map<String, Object> map) {
		sDDownloadCountDao.addStatictisSdk(map);
	}

	public void addStatictisDemo(Map<String, Object> map) {
		sDDownloadCountDao.addStatictisDemo(map);
	}

	public void updateStatictisSdk(Map<String, Object> map) {
		sDDownloadCountDao.updateStatictisSdk(map);
	}

	public void updateStatictisDemo(Map<String, Object> map) {
		sDDownloadCountDao.updateStatictisDemo(map);
	}

}
