package com.flypaas.dao;

import java.util.List;

import com.flypaas.model.TbRsBillAcct;

public interface TbRsBillAcctMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbRsBillAcct record);

    int insertSelective(TbRsBillAcct record);

    TbRsBillAcct selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbRsBillAcct record);

    int updateByPrimaryKey(TbRsBillAcct record);
    
    /**
     * 查询资源方日消耗流量账单
     * @param netSid
     * @param date
     * @return
     */
    public List<TbRsBillAcct> queryResourceSideTDC(String netSid,String date);
    
    /**
     * 查询资源方月消耗流量账单
     * @param netSid
     * @param date
     * @return
     */
    public List<TbRsBillAcct> queryResourceSideTMC(String netSid,String date);
}