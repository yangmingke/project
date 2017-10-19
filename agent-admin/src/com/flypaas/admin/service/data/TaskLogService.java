package com.flypaas.admin.service.data;

import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 信息管理-管理员中心-任务日志
 * 
 * @author xiejiaan
 */
public interface TaskLogService {

	/**
	 * 查询任务日志
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 查看任务日志
	 * 
	 * @param logId
	 * @return
	 */
	Map<String, Object> view(int logId);

}
