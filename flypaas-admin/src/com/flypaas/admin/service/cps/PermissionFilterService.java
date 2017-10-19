package com.flypaas.admin.service.cps;

import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 策略管理-策略权限配置-策略权限过滤
 * 
 * @author xiejiaan
 */
public interface PermissionFilterService {

	/**
	 * 查询策略权限过滤
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 查看策略权限过滤
	 * 
	 * @param id
	 * @return
	 */
	Map<String, Object> view(int id);

	/**
	 * 保存策略权限过滤，包括添加、修改
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> save(Map<String, String> params);

	/**
	 * 删除策略权限过滤
	 * 
	 * @param id
	 * @return
	 */
	Map<String, Object> delete(int id);

}
