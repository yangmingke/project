package com.flypaas.service;

import java.util.List;

import com.flypaas.model.TbRsRTPP;
import com.flypaas.util.PageContainer;

public interface ResourceService {
	/**
     * 检测此Ip是否存在
     * @param ip
     * @return
     */
    public TbRsRTPP checkIp(String ip);
	
	/**
	 * 按节点Id删除资源
	 * @param rtppSid
	 * @return
	 */
	public int deleteByPrimaryKey(Integer rtppSid);
	
	/**
	 * 插入资源节点信息 不做null判断
	 * @param record
	 * @return
	 */
	public int insert(TbRsRTPP record);
	
	/**
	 * 插入资源节点  做null判断
	 * @param record
	 * @return
	 */
	public int insertSelective(TbRsRTPP record);
	
	/**
	 * 按rtppSid查询资源节点
	 * @param rtppSid
	 * @return
	 */
	public TbRsRTPP selectByPrimaryKey(Integer rtppSid);
	
	/**
	 * 修改资源节点信息 做null判断
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeySelective(TbRsRTPP record);
	
	/**
	 * 修改资源节点信息  不做null判断
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKey(TbRsRTPP record);
	
	/**
	 * 查询该账号下的所有资源节点
	 * @return
	 */
	public PageContainer queryAllResourceFenYe(String netSid,int page,String ipOrName,String dateMin,String dateMax);
	
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
    public int queryResourceCount(String netSid,String ipOrName,String dateMin,String dateMax);
    
    /**
     * 批量删除资源节点
     * @param checkedId
     * @return
     */
    public int batchDelResource(String checkedId);
    
    /**
     * 根据IP或者Name查询该账户某个节点
     * @param netSid
     * @param IpOrName
     * @return
     */
    public TbRsRTPP queryResourceByIpOrName(TbRsRTPP tbRsRTPP1);
    
   
}
