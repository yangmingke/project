package com.flypaas.dao;

import java.util.List;

import com.flypaas.model.TbRsBillAcctRTPP;

public interface TbRsBillAcctRTPPMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbRsBillAcctRTPP record);

    int insertSelective(TbRsBillAcctRTPP record);

    TbRsBillAcctRTPP selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbRsBillAcctRTPP record);

    int updateByPrimaryKey(TbRsBillAcctRTPP record);
    
    /**
     * 查询资源节点日消耗流量账单
     * @param netSid
     * @param date
     * @return
     */
    public List<TbRsBillAcctRTPP> queryResourceTDC(String netSid,String date);
    
    /**
     * 查询资源节点月消耗流量账单
     * @param netSid
     * @param date
     * @return
     */
    public List<TbRsBillAcctRTPP> queryResourceTMC(String netSid,String date);
}