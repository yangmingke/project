package com.flypaas.dao;



import java.util.List;
import java.util.Map;

import com.flypaas.model.TbRsRTPPRealtimeStatus;

public interface TbRsRTPPRealtimeStatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbRsRTPPRealtimeStatus record);

    int insertSelective(TbRsRTPPRealtimeStatus record);

    TbRsRTPPRealtimeStatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbRsRTPPRealtimeStatus record);

    int updateByPrimaryKey(TbRsRTPPRealtimeStatus record);
    
    /**
     * 根据ip查询该ip节点的信息
     * @param ip
     * @return
     */
    public TbRsRTPPRealtimeStatus queryStatusByIp(String ip);
    
    /**
     * 查询该ip在一天之内产生的状态
     * @param ip
     * @return
     */
    public List<TbRsRTPPRealtimeStatus> queryStatusByIpTime(Map<String,String> map);
    
    /**
     * 根据ip或名称和时间段查询资源实时状态
     * @param ip
     * @param dateMin
     * @param dateMax
     * @return
     */
    public List<TbRsRTPPRealtimeStatus> queryResourceRealStatus(String ip,String dateMin,String dateMax);
    
}