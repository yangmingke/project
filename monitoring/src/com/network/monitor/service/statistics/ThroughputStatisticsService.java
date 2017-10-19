package com.network.monitor.service.statistics;

import java.util.List;
import java.util.Map;

/**
 * SR节点间吞吐量统计数据Service接口实现
 *
 */
public interface ThroughputStatisticsService {

	/**
	 * 统计吞吐量数据
	 * @param params
	 * @return
	 */
	public Map<String, Object> queryThroughput(Map<String, String> params);
	
	/**
	 * 获取节点间吞吐量数据，画出曲线图
	 * @param params
	 * @return
	 */
	public Map<String, List<Map<String, Object>>> queryList(Map<String, String> params);
	
	/**
	 * 批量插入SR节点间吞吐量
	 * @param params
	 */
	public void insertBatch(Map<String, Object> params);
	
	/**
	 * 流量统计图
	 */
	public List<Map<String, Object>> drawThroughput(Map<String, String> param);
	
	/**
	 * 流量统计图中国
	 */
	public List<Map<String, Object>> drawThroughputChina(Map<String, String> param);

	public List<Map<String, Object>> srIp(Map<String, String> formData);

	public List<Map<String, Object>> dstIp(Map<String, String> formData);

	public List<Map<String, Object>> routeMonitor(Map<String, String> formData);

	public List<Map<String, Object>> drawThroughputNew(Map<String, String> param);

	public List<Map<String, Object>> drawThroughputChinaNew(Map<String, String> param);
}
