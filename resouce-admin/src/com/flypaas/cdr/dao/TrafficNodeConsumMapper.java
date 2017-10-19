package com.flypaas.cdr.dao;

import java.util.List;
import java.util.Map;
@SuppressWarnings("rawtypes")
public interface TrafficNodeConsumMapper {
	/**
	 * 查询资源节点账单表
	 * 		日流量消耗     默认当天
	 * @param para
	 * @return
	 */
	public List<Map<String, String>> queryResourceFlowDay(Map para);
	
	/**
	 * 查询某日的账单数量
	 * @param 
	 * @return
	 */
	public int queryCountResourceFlowDay(Map para);
	/**
	 * 查询资源节点账单表
	 * 		月流量消耗    
	 * @param para
	 * @return
	 */
	public List<Map<String, String>> queryResourceFlowMonth(Map para);
	
	/**
	 * 查询某月的账单数量
	 * @param 
	 * @return
	 */
	public int queryCountResourceFlowMonth(Map para);
	/**
	 * 查询资源方某日的账单数量
	 * @param 
	 * @return
	 */
	public int queryResourceSideCount(Map<String, Object> para);
	/**
	 * 查询资源方日流量消耗     
	 * @param para
	 * @return
	 */
	public List<Map<String, String>> queryResourceSideFlowDay(Map<String, Object> para);
	/**
	 * 查询资源方某月的账单数量
	 * @param para
	 * @return
	 */
	public int queryResourceSideCountFlowMonth(Map<String, Object> para);
	/**
	 * 查询资源方月流量消耗    
	 * @param 
	 * @return
	 */
	public List<Map<String, String>> queryResourceSideFlowMonth(Map<String, Object> para);

	/**
	 * 查询节点流量日明细
	 * @param para
	 * @return
	 */
	public List<Map<String, String>> queryNodeTodayTraffic(Map<String, String> para);
}