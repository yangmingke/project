package com.network.monitor.service.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.network.monitor.constant.TaskConstant.TaskStatus;
import com.network.monitor.dao.StatDao;
import com.network.monitor.model.TaskInfo;
import com.network.monitor.util.TaskUtils;

/**
 * 定时任务业务
 *
 */
@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private StatDao dao;
	
	/**
	 *  查询需要执行的任务
	 */
	@Override
	public List<Map<String, Object>> queryTask() {
		return dao.getSearchList("task.query_task", null);
	}

	@Override
	public Long insertLog(TaskInfo taskInfo, String remark) {
		Map<String, Object> sqlParams = new HashMap<String, Object>();
		sqlParams.put("task_id", taskInfo.getTaskId());
		sqlParams.put("data_date", taskInfo.getExecutePrev());
		sqlParams.put("remark", remark);
		dao.insert("task.insert_log", sqlParams);
		return Long.valueOf(sqlParams.get("log_id").toString());
	}

	@Override
	public void updateLog(Long logId, boolean result, String remark) {
		Map<String, Object> sqlParams = new HashMap<String, Object>();
		sqlParams.put("log_id", logId);
		sqlParams.put("remark", remark);
		sqlParams.put("status", result ? 2 : 3);
		dao.update("task.update_log", sqlParams);
	}
	
	/**
	 * 修改任务状态
	 */
	@Override
	public void updateStatus(Integer taskId, TaskStatus taskStatus) {
		Map<String, Object> sqlParams = new HashMap<String, Object>();
		sqlParams.put("task_id", taskId);
		sqlParams.put("status", taskStatus.getValue());
		dao.update("task.update_task", sqlParams);
	}
	
	@Override
	public void updateExecuteNext(TaskInfo taskInfo) {
		Map<String, Object> sqlParams = new HashMap<String, Object>();
		sqlParams.put("task_id", taskInfo.getTaskId());
		sqlParams.put("execute_next", taskInfo.getNewExecuteNext());
		dao.update("task.update_task", sqlParams);
	}

	@Override
	public void updateScanNext(TaskInfo taskInfo) {
		Map<String, Object> sqlParams = new HashMap<String, Object>();
		sqlParams.put("task_id", taskInfo.getTaskId());
		sqlParams.put("scan_next", TaskUtils.getNewScanNext(taskInfo));
		dao.update("task.update_task", sqlParams);
	}

}
