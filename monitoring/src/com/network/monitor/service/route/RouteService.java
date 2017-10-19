package com.network.monitor.service.route;

import java.util.List;
import java.util.Map;

/**
 * 路由表信息管理Service接口
 *
 */
public interface RouteService {

	/**
	 * 查找SR路由表信息
	 * @param sr_id
	 * @return
	 */
	public List<Map<String, Object>> getRoutes(String sr_id);
	
	/**
	 * 将接收到的路由报文信息入库
	 * @param params
	 */
	public void saveRoutes(List<Map<String, Object>> params);
	
}
