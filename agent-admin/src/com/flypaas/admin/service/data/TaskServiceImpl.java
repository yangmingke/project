package com.flypaas.admin.service.data;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.constant.LogConstant.LogType;
import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.service.LogService;

/**
 * 信息管理-管理员中心-任务管理
 * 
 * @author xiejiaan
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {
	@Autowired
	private MasterDao dao;
	@Autowired
	private LogService logService;

	@Override
	public PageContainer query(Map<String, String> params) {
		return dao.getSearchPage("task.query", "task.queryCount", params);
	}

	@Override
	public Map<String, Object> view(int taskId) {
		return dao.getOneInfo("task.view", taskId);
	}

	@Override
	public Map<String, Object> save(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		if (dao.getSearchSize("task.check", params) > 0) {// 查重
			data.put("result", "fail");
			data.put("msg", "任务名称已被使用，请重新输入");
			return data;
		}

		String taskId = params.get("task_id");
		if (StringUtils.isBlank(taskId)) {// 添加任务
			int i = dao.insert("task.insert", params);
			if (i > 0) {
				data.put("result", "success");
				data.put("msg", "添加成功");
			} else {
				data.put("result", "fail");
				data.put("msg", "添加失败");
			}
			logService.add(LogType.add, "信息管理-管理员中心-任务管理：添加任务", params, data);

		} else {// 修改任务
			int i = dao.update("task.update", params);
			if (i > 0) {
				data.put("result", "success");
				data.put("msg", "修改成功");
			} else {
				data.put("result", "fail");
				data.put("msg", "任务不存在，修改失败");
			}
			logService.add(LogType.update, "信息管理-管理员中心-任务管理：修改任务", params, data);
		}
		return data;
	}

	@Override
	public Map<String, Object> updateStatus(int taskId, int status) {
		Map<String, Object> data = new HashMap<String, Object>();
		String msg;
		switch (status) {
		case 0:
			msg = "关闭";
			break;
		case 1:
			msg = "启用";
			break;
		case 3:
			msg = "删除";
			break;
		default:
			data.put("result", "fail");
			data.put("msg", "状态不正确，操作失败");
			return data;
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("task_id", taskId);
		params.put("status", status);
		int i = dao.update("task.updateStatus", params);
		if (i > 0) {
			data.put("result", "success");
			data.put("msg", msg + "成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "任务不存在，" + msg + "失败");
		}
		logService.add(LogType.update, "信息管理-管理员中心-任务管理：修改任务状态", params, data);
		return data;
	}

}
