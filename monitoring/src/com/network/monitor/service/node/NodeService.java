package com.network.monitor.service.node;

import java.util.List;
import java.util.Map;

import com.network.monitor.model.PageContainer;

/**
 * SR节点信息管理Service接口
 *
 */
public interface NodeService {

	/**
	 * 查询SR节点信息
	 * @param map
	 * @return
	 */
	PageContainer query(Map<String, String> map);
	
	/**
	 * 确认配置SR信息
	 * @param params
	 * @return
	 */
	Map<String, Object> confirmNodeInfo(Map<String, String> params);
	
	/**
	 * 获取SR节点信息
	 * @param sr_id
	 * @return
	 */
	Map<String, Object> getNodeInfo(String sr_id);
	
	/**
	 * 删除SR节点配置信息
	 * @param sr_id
	 * @return
	 */
	Map<String, Object> deleteNodeInfo(String sr_id);
	
	/**
	 * 获取监控的所有SR节点
	 * @return
	 */
	List<Map<String,Object>> queryNode();
	
	/**
	 * 获取省份所有城市
	 * @param provinceId
	 * @return
	 */
	List<Map<String, Object>> getCitys(String provinceId);
	
	/**
	 * 获取所有SR节点信息
	 * @return
	 */
	List<Map<String, Object>> getNodeList();

	void deleteBatch(Map<String, String> formData);

	List<Map<String, Object>> getProvinces(String countryId);
	
	/**
	 * 获取所有SR中国节点信息
	 * @return
	 */
	List<Map<String, Object>> getChinaNodeList();
}
