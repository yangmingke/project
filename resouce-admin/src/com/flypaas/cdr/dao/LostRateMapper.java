package com.flypaas.cdr.dao;

import java.util.List;
import java.util.Map;

import com.flypaas.model.TbRsSessRealtimeLost;

public interface LostRateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbRsSessRealtimeLost record);

    int insertSelective(TbRsSessRealtimeLost record);

    TbRsSessRealtimeLost selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbRsSessRealtimeLost record);

    int updateByPrimaryKey(TbRsSessRealtimeLost record);

    /**
     * 查询丢包状况
     * @param para
     * @return
     */
	List<TbRsSessRealtimeLost> queryPaketLoss(Map<String, String> para);
	
	 /**
     * 查询丢包来源
     * @param para
     * @return
     */
	List queryPaketLossSource(Map<String, String> para);
	
	 /**
     * 查询丢包状况
     * @param para
     * @return
     */
	int queryPaketLossSourceCount(Map<String, String> para);

	List<Map<String,Object>> querySessionPaketLoss(Map<String, Object> para);
}