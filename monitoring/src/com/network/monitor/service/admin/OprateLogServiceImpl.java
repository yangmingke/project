package com.network.monitor.service.admin;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.network.monitor.dao.StatDao;
import com.network.monitor.model.PageContainer;
import com.network.monitor.service.LogService;

/**
 * 管理员中心-操作日志
 * 
 * @author xiejiaan
 */
@Service
@Transactional
public class OprateLogServiceImpl implements OprateLogService {
	@Autowired
	private StatDao dao;
	@Autowired
	private LogService logService;

	@Override
	public PageContainer query(Map<String, String> params) {
		return dao.getSearchPage("oprateLog.query", "oprateLog.queryCount", params);
	}

	@Override
	public Map<String, Object> view(int logId) {
		return dao.getOneInfo("oprateLog.view", logId);
	}
}
