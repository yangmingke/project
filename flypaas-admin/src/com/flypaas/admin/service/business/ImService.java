package com.flypaas.admin.service.business;

import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 业务管理-即时消息
 * 
 * @author xiejiaan
 */
public interface ImService {

	/**
	 * 分页查询即时消息
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 查看即时消息
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> view(Map<String, String> params);

}
