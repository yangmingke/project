package com.flypaas.service.resource;

import java.util.List;
import java.util.Map;

import com.flypaas.model.TbRsRTPP;
import com.flypaas.model.TbRsRTPPRealtimeStatus;
import com.flypaas.util.PageContainer;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年2月6日 下午12:30:33
* 类说明
*/
public interface ResourceService {
	/**
	 * 分页查询所有资源
	 * @param para
	 * @param page
	 * @return
	 */
	public PageContainer queryResourceFenYe(Map<String, Object> para, int page);
	
	/**
	 * 按照rtppSid删除资源节点
	 * @param rtppSid
	 * @return
	 */
	public int deleteByPrimaryKey(Integer rtppSid);
	
	/**
	 * 根据rtppSid查询资源节点
	 * @param rtppSid
	 * @return
	 */
	public TbRsRTPP selectByPrimaryKey(Integer rtppSid);
	
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
    public int batchDelResource(String checkedId);
    
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
    public TbRsRTPP checkIp(String ip);
    
    /**
     * 根据ip或名称和时间段查询资源实时状态
     * @param ip
     * @param dateMin
     * @param dateMax
     * @return
     */
    public List<Map<String, String>> queryResourceRealStatus(String ip,String dateTime);

	public Map showNeighbor(String rtppId, String showNeighbor);

	public int updateNeighbor(String[] neighborArray, String[] notNeighborArray, String ip, String routeDomain);

	public List<TbRsRTPP> getRTPPListByIp(String originalIp);

	public List<TbRsRTPP> checkRTPP(TbRsRTPP tbRsRTPP);
}
