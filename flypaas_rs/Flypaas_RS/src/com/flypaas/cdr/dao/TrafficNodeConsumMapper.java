package com.flypaas.cdr.dao;

import java.util.List;
import java.util.Map;

import com.flypaas.model.TbRsBillAcctRTPP;
import com.flypaas.model.TbRsRTPPRealtimeStatus;
@SuppressWarnings("rawtypes")
public interface TrafficNodeConsumMapper {
	/**
	 * 查询资源节点账单表
	 * 		日流量消耗     默认当天
	 * @param para
	 * @return
	 */
	public List<TbRsBillAcctRTPP> queryResourceFlowDay(Map para);
	
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
	public List<TbRsBillAcctRTPP> queryResourceFlowMonth(Map para);
	
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
	public List<TbRsBillAcctRTPP> queryResourceSideFlowDay(Map<String, String> para);
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
	public List<TbRsBillAcctRTPP> queryResourceSideFlowMonth(Map<String, String> para);
	/**
	 * 查询节点流量日明细
	 * @param para
	 * @return
	 */
	public List<Map<String, String>> queryNodeTodayTraffic(Map<String, String> para);
}