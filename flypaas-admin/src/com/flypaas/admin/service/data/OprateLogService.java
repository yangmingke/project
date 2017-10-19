package com.flypaas.admin.service.data;

import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 信息管理-管理员中心-操作日志
 * 
 * @author xiejiaan
 */
public interface OprateLogService {

	/**
	 * 查询操作日志
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 查看操作日志
	 * 
	 * @param logId
	 * @return
	 */
	Map<String, Object> view(int logId);

}
