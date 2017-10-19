package com.network.monitor.service.statistics;

import java.util.List;
import java.util.Map;

import com.network.monitor.model.PageContainer;

/**
 * SR节点间质量统计Service接口
 *
 */
public interface QualityStatisticsService {

	/**
	 * 分页搜索SR节点间的质量统计数据
	 * @param map
	 * @return
	 */
	public PageContainer query(Map<String, String> params);
	
	/**
	 * 获取丢包率数据
	 * @param param
	 * @return
	 */
	public Map<String, List<Map<String, Object>>> queryLostRate(Map<String, String> params);
	
	/**
	 * 获取节点间的延时数据
	 * @param params
	 * @return
	 */
	public Map<String, List<Map<String, Object>>> queryDelay(Map<String, String> params);
	
	/**
	 * 批量插入SR节点质量数据
	 * @param list
	 */
	public void insertBatch(Map<String, Object> params);
	
	/**
	 * 链路质量图
	 * @return
	 */
	public List<Map<String, Object>> drawQuality(Map<String, String> param);
	
	/**
	 * 链路质量图china
	 * @return
	 */
	public List<Map<String, Object>> drawQualityChina(Map<String, String> param);

	public List<Map<String, Object>> query_monitor();

	public List<Map<String, Object>> getDst_sr_id(Map<String, String> formData);

	public List<Map<String, Object>> drawQualityNew(Map<String, String> param);
}
