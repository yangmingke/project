package com.network.monitor.service.srinterface;

import java.util.List;
import java.util.Map;

import com.network.monitor.model.PageContainer;

/**
 * SR节点接口和IP管理
 *
 */
public interface SRInterfaceService {

	/**
	 * 保存TCP发来的SR节点的接口和IP信息
	 * @param params
	 */
	public void saveFromTCP(List<Map<String, Object>> params);
	
	/**
	 * 分页查询SR节点的接口信息
	 * @param map
	 * @return
	 */
	public PageContainer query(Map<String, String> params);
}
