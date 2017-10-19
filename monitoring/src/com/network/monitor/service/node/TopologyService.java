package com.network.monitor.service.node;

import java.util.List;
import java.util.Map;

import com.network.monitor.model.PageContainer;

/**
 * SR节点间拓扑图关系Service接口
 *
 */
public interface TopologyService {

	/**
	 * 获取SR节点拓扑关系图
	 * @return
	 */
	public List<Map<String, Object>> getTopology(Map<String, String> params);
	

	/**
	 * 获取中国SR节点拓扑关系图
	 * @return
	 */
	public List<Map<String, Object>> getChinaTopology(Map<String, String> params);
	
	
	/**
	 * 保存TCP发来的SR节点的邻居信息
	 * @param params
	 */
	public void saveFromTCP(List<Map<String, Object>> params);
	
	/**
	 * 获取网络参数（拓扑图使用）
	 * @param src_sr_id
	 * @param dst_sr_id
	 * @return
	 */
	public Map<String, Object> getNetworkData(String src_sr_id,String dst_sr_id);
	
	/**
	 * 获取SR节点间的最短路径
	 * @param src_sr_id
	 * @param dst_sr_id
	 * @return
	 */
	public List<Map<String, Object>> getBestPath(String src_sr_id, String dst_sr_id);
	
	/**
	 * 分页查询SR节点的邻居关系信息
	 * @param map
	 * @return
	 */
	public PageContainer query(Map<String, String> params);
}
