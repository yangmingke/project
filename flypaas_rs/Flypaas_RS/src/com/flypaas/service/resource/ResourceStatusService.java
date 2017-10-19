package com.flypaas.service.resource;


import java.util.List;
import java.util.Map;

import com.flypaas.model.TbRsRTPPRealtimeStatus;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2016年12月23日 上午9:40:32
* 类说明
*/
public interface ResourceStatusService{

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
    public List<Map<String, String>> queryStatusByIpTime(String ip,String dateTime);
}
