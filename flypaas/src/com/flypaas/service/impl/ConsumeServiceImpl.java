package com.flypaas.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.constant.UserConstant;
import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.vo.PageContainer;
import com.flypaas.service.ConsumeService;

@Service
@Transactional
public class ConsumeServiceImpl extends DaoCenter implements ConsumeService {

	public List<Map<String, Object>> getCurrentCsm(Map<String, Object> map) {
		String type = map.get("type").toString();
		if(UserConstant.TODAY_CSM_TRAFFIC.equals(type)){
			return newConsumeDao.getCurrentTrafficCsm(map);
		}
		return consumeDao.getCurrentCsm(map);
	}

	public PageContainer getApp45Csm(PageContainer page) {
		return consumeDao.getApp45Csm(page);
	}

	public List<Map<String, Object>> getBillMonth(Map<String, Object> param) {
		return consumeDao.getBillMonth(param);
	}

	public String getBillCsmSum(Map<String, Object> param) {
		return consumeDao.getBillCsmSum(param);
	}

	public List<Map<String, Object>> getClientMonthBill(Map<String, Object> param) {
		return consumeDao.getClientMonthBill(param);
	}

	public int isExistsTable(String tableName) {
		return consumeDao.isExistsTable(tableName);
	}

	public List<Map<String, Object>> getFastDFS(String sql) {
		return consumeDao.getFastDFS(sql);
	}

	public Map<String, Object> getYstCsm(Map<String, String> map) {
		return newConsumeDao.getYstCsm(map);
	}

	public List<Map<String, Object>> appCsmDataByMonth(Map<String, String> map) {
		return newConsumeDao.appCsmDataByMonth(map);
	}

	public PageContainer lyDetail(PageContainer page) {
		return consumeDao.lyDetail(page);
	}

}
