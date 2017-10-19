package com.flypaas.dao;

import java.util.List;
import java.util.Map;

import com.flypaas.model.TbRsBillAcctRTPP;

public interface TbRsBillAcctRTPPMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbRsBillAcctRTPP record);

    int insertSelective(TbRsBillAcctRTPP record);

    TbRsBillAcctRTPP selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbRsBillAcctRTPP record);

    int updateByPrimaryKey(TbRsBillAcctRTPP record);

	List<TbRsBillAcctRTPP> queryFinanceByIp(String ip, String dateMin, String dateMax);
	
	/**
	 * 查询资源节点账单表
	 * 		日流量消耗   (netSid,ip,dateTime)  默认当天
	 * @param para
	 * @return
	 */
	public List<Map<String, String>> queryResourceFlowDay(Map para);
	
	/**
	 * 统计流量日消耗账单
	 * @param para
	 * @return
	 */
	public Map<String,String> statisticsFlowDay(Map para);
	
	/**
	 * 查询资源节点账单表
	 * 		月流量消耗   (netSid,ip,monthTime) 默认当月
	 * @param para
	 * @return
	 */
	public List<Map<String, String>> queryResourceFlowMonth(Map para);
	
	/**
	 * 查询某月的账单数量
	 * @param monthTime
	 * @return
	 */
	public int queryCount(Map para);
}