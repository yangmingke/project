package com.flypaas.service.route;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.flypaas.util.PageContainer;

public interface RouteService {

	/**
	 * 获取所有路由域名称
	 * @return
	 */
	public Set<String> getDomainList();

	/**
	 * 
	 * @param concurrent 
	 * @param page 
	 * @return
	 */
	public PageContainer getIpList(String concurrent, int page);

	public Map queryResourceById(String ip, String routeDomain);

	/**
	 * 查询节点邻居信息
	 * @param ip
	 * @param sort 
	 * @param order 
	 * @return
	 */
	public List queryNeighborById(String ip, String sort, String order, String routeDomain);

	/**
	 * 查询节点拓扑信息
	 * @return
	 */
	
	public Map topology();

	public LinkedList sortDESC(Map<String, String> resource);

	public LinkedList sortAsc(Map<String, String> resource);

	/**
	 * 查询节点流量信息
	 * @return
	 */
	public Map trafficMap();

	public Map linkStatus();

	public PageContainer querySessions(int page, String paraKey);

	public Map topologyNew(Map data);

	public Map trafficMapNew(Map model);

	public Map linkStatusNew(Map data);

	Map queryResource(String ip, String netSid,String preRouteKey);

	public Map<String, Object> locationNew(Map<String, Object> model);
}
