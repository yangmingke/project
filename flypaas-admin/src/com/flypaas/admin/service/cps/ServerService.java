package com.flypaas.admin.service.cps;

import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 策略管理-策略权限配置-服务器地址
 * 
 * @author xiejiaan
 */
public interface ServerService {

	/**
	 * 查询服务器地址
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 查看服务器地址
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> view(Map<String, String> params);

	/**
	 * 保存服务器地址，包括添加、修改
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> save(Map<String, String> params);

	/**
	 * 删除服务器地址
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> delete(Map<String, String> params);

}
