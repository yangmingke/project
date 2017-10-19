package com.flypaas.admin.service.data;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.service.LogService;

/**
 * 信息管理-管理员中心-任务日志
 * 
 * @author xiejiaan
 */
@Service
@Transactional
public class TaskLogServiceImpl implements TaskLogService {
	@Autowired
	private MasterDao dao;
	@Autowired
	private LogService logService;

	@Override
	public PageContainer query(Map<String, String> params) {
		return dao.getSearchPage("taskLog.query", "taskLog.queryCount", params);
	}

	@Override
	public Map<String, Object> view(int logId) {
		return dao.getOneInfo("taskLog.view", logId);
	}
}
