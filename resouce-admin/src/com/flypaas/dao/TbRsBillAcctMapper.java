package com.flypaas.dao;

import java.util.List;
import java.util.Map;

import com.flypaas.model.TbRsBillAcct;

public interface TbRsBillAcctMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbRsBillAcct record);

    int insertSelective(TbRsBillAcct record);

    TbRsBillAcct selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbRsBillAcct record);

    int updateByPrimaryKey(TbRsBillAcct record);
    
    /**
     * 查询自家的账单
     * @param netSid
     * @param date
     * @return
     */
    public TbRsBillAcct queryAccount(String netSid,String date);
    
    
 /*-----------------------------·············--资源管理系统··············----------------------------------*/
    
    /**
	 * 查询资源方账单表
	 * 		日流量消耗   (netSid,ip,dateTime)  默认当天
	 * @param para
	 * @return
	 */
	public List<Map<String, String>> queryResourceSideFlowDay(Map para);
	
	/**
	 * 查询资源方账单表
	 * @param para
	 * @return
	 */
	public Map<String,String> statisticsFlowDay(Map para);
	
	/**
	 * 查询资源方账单表
	 * 		月流量消耗   (netSid,ip,monthTime) 默认当月
	 * @param para
	 * @return
	 */
	public List<Map<String, String>> queryResourceSideFlowMonth(Map para);
	/**
	 * 查询资源方账单表
	 * @param para
	 * @return
	 */
	public Map<String,String> statisticsFlowDMonth(Map para);
	
	
	/**
	 * 查询某月的账单数量
	 * @param monthTime
	 * @return
	 */
	public int queryResourceSideCount(Map para);
}