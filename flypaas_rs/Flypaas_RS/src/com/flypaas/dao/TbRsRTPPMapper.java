package com.flypaas.dao;


import java.util.List;
import java.util.Map;

import com.flypaas.model.TbRsRTPP;

public interface TbRsRTPPMapper {
    int deleteByPrimaryKey(Integer rtppSid);

    int insert(TbRsRTPP record);

    int insertSelective(TbRsRTPP record);

    TbRsRTPP selectByPrimaryKey(Integer rtppSid);

    int updateByPrimaryKeySelective(TbRsRTPP record);

    int updateByPrimaryKey(TbRsRTPP record);
    
    /**
     * 检测此Ip是否存在
     * @param ip
     * @return
     */
    public List<TbRsRTPP> checkIp(String ip);
    
    /**
     * 查询该账号下的所有资源节点
     * @param netSid
     * @return
     */
    public List<Map<String, String>> queryAllResourceFenYe(Map para);
    
    /**
     * 查询该账号下的所有资源节点
     * @param netSid
     * @return
     */
    public List<TbRsRTPP> queryAllResource(String netSid);
    /**
     * 查询该账号下的节点数量
     * @param netSid
     * @return
     */
    public int queryResourceCount1(String netSid);
    /**
     * 查询该账号下的节点数量
     * @param netSid
     * @return
     */
    public int queryResourceCount(Map para);
    
    /**
     * 批量删除资源节点
     * @param String[]strings
     * @return
     */
    public int batchDelResource(String[] checkedId);
    
    /**
     * 根据IP或者Name查询该账户某个节点
     * @param netSid
     * @param IpOrName
     * @return
     */
    public TbRsRTPP queryResourceByIpOrName(TbRsRTPP tbRsRTPP1);
    
    /**
     * 根据netSid查询某一段时间内的资源节点或者根据ipOr直接擦查询
     * @param netSid
     * @param dateMin
     * @param dateMax
     * @param ipOrName
     * @return
     */
    public List<TbRsRTPP> queryResourceBynetSidAndTime(Map<String,String> para);
}