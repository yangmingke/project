package com.flypaas.dao;


import java.util.List;
import java.util.Map;

import com.flypaas.model.TbRsRTPP;

public interface TbRsRTPPMapper {
    int deleteByPrimaryKey(Integer rtppSid);

    int insert(TbRsRTPP record);


    TbRsRTPP selectByPrimaryKey(Integer rtppSid);

    int updateByPrimaryKey(TbRsRTPP record);
    
    /**
     * 查询该账号下的所有资源节点
     * @param netSid
     * @return
     */
    public List<TbRsRTPP> queryAllResource(String netSid);
    
/*---------------------------------------资源管理系统--------------------------------------------*/
    /**
     * 分页查询所有的资源节点
     * @param para
     * @return
     */
    public List<Map<String, String>> queryResourceFenYe(Map<String,Object> para);
    
    /**
     * 查询该账号下的节点数量
     * @param netSid
     * @return
     */
    public int queryResourceCount(Map<String,Object> para);
    
    /**
     * 根据资源节点ID 查询资源信息  四表关联查询
     * @param rtppSid
     * @return
     */
    public Map<String,String> queryResourceById(int rtppSid);
    
    /**
     * 批量删除资源节点
     * @param String[]strings
     * @return
     */
    public int batchDelResource(String[] checkedId);
    
    /**
     * 修改资源节点信息  状态等
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(TbRsRTPP record);
    
    /**
     * 修改资源的分时间段定价
     * @param record
     * @return
     */
    public int updateBlockPriceById(TbRsRTPP record);
    
    /**
     * 添加资源节点根据netSid
     * @param record
     * @return
     */
    public int insertSelective(TbRsRTPP record);
    
    /**
     * 检测此Ip是否存在
     * @param ip
     * @return
     */
    public List<TbRsRTPP> checkIp(String ip);

    public List<String> queryAllRTPP();

    public List<String> queryOtherRTPP(List<String> nbList);
    /**
     * 检测域内此Ip是否存在
     * @param ip
     * @return
     */
	public List<TbRsRTPP> checkRTPP(TbRsRTPP tbRsRTPP);

}