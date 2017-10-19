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
	 * @param ipKeyWord 
	 * @param pageRowCount 
	 * @return
	 */
	public PageContainer getIpList(String concurrent, int page, String ipKeyWord, String pageRowCount);

	public Map queryResourceById(String ip, String routeDomain);

	/**
	 * 查询节点邻居信息
	 * @param ip
	 * @param sort 
	 * @param order 
	 * @return
	 */
	public List queryNeighborById(String ip, String sort, String order, String routeDomain);

	public LinkedList sortDESC(Map<String, String> resource);

	public LinkedList sortAsc(Map<String, String> resource);


	public PageContainer querySessions(int page, String paraKey, String pageRowCount);

	public Map topology(Map data);

	public Map trafficMap(Map model);

	public Map linkStatus(Map data);

	Map queryResource(String ip, String netLevel,String preRouteKey);

	public Map<String, Object> transmit(String domain, String srcIP, String destIP) throws Exception;

	public LinkedList<String> queryAllIp(String routeDomain);

	public PageContainer analysis(String resultSelection, String analysisStr, String routeDomain, Integer currentPage, Integer pageRowCount);
	/**
	 * 取路由分析缓存结果
	 * @param currentPage
	 * @param pageRowCount
	 * @return
	 */
	public PageContainer analysisChangePage(Integer currentPage, Integer pageRowCount);
}
