package com.flypaas.service.route.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flypaas.constant.RouterConstant;
import com.flypaas.dao.TbRsCityMapper;
import com.flypaas.dao.TbRsIspMapper;
import com.flypaas.model.TbRsCity;
import com.flypaas.service.RedisService;
import com.flypaas.service.route.RouteService;
import com.flypaas.util.GooleMapUtil;
import com.flypaas.util.PageContainer;
import com.flypaas.util.StrUtil;

/**
 * @author 作者 yangmingke:
 * @version 创建时间：2017年2月13日 下午12:32:00 类说明
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Service
public class RouteServiceImpl implements RouteService {

	@Autowired
	TbRsCityMapper tbRsCityMapper;
	@Autowired
	TbRsIspMapper tbRsIspMapper;

	@Override
	public Set<String> getDomainList() {
		return RedisService.getInstance().getKeys(RouterConstant.ROUTE_KEYS);
	}

	@Override
	public PageContainer getIpList(String concurrent, int page) {
		if (StrUtil.isEmpty(concurrent)) {
			concurrent = RouterConstant.ROUTE_DEFAULT_KEY;
		}
		Set<String> res = RedisService.getInstance().zrange(concurrent, 0, -1);
		List<String> ipList = new ArrayList<String>();
		ipList.addAll(res);
		List<Object> ipInfoList = new ArrayList<Object>();
		PageContainer pageContainer = new PageContainer();

		Map<String, Integer> para = pageContainer.createRedisParameter(page, ipList.size());

		for (int i = para.get("begin"); i < pageContainer.getTotalCount() && i < para.get("end"); i++) {
			Map<String, String> ipInfo = queryResourceById(ipList.get(i),concurrent);
			if (ipInfo == null || ipInfo.isEmpty()) {
				continue;
			}
			ipInfoList.add(ipInfo);
		}
		pageContainer.setResult(ipInfoList);
		return pageContainer;
	}

	
	@Override
	public Map queryResourceById(String ip,String routeDomain) {
		routeDomain = routeDomain.replace(RouterConstant.PRE_ROUTE_KEY, "");
		Map resource = RedisService.getInstance().hgetall(RouterConstant.PRE_RTPP_INTO + routeDomain+ "_" + ip);
		if (resource != null && !resource.isEmpty()) {
			String country = (String) resource.get("country");
			String city = (String) resource.get("city");
			String operator = (String) resource.get("operator");
			if (StrUtil.isEmpty(country) || StrUtil.isEmpty(city)) {
				return resource;
			}
			TbRsCity tbRsCity = tbRsCityMapper.queryCityByCityId(country, city);
			String cityName = StrUtil.isEmpty(tbRsCity.getCity()) ? tbRsCity.getCountry() : tbRsCity.getCity();
			String operatorName = tbRsIspMapper.queryById(operator);
			List<Map> coordinatesList = (List) GooleMapUtil.getCoordinate(cityName).get("result");
			if(coordinatesList == null && coordinatesList.size() != 1){
				resource.put("latitude", tbRsCity.getLatitude());
				resource.put("longitude", tbRsCity.getLongitude());
			}else{
				resource.put("latitude", coordinatesList.get(0).get("lat"));
				resource.put("longitude", coordinatesList.get(0).get("lng"));
			}
			resource.put("cityName", cityName);
			resource.put("operatorName", operatorName);
		}
		return resource;
	}
	
	@Override
	public Map queryResource(String ip,String netSid, String preRouteKey) {
		Map resource = RedisService.getInstance().hgetall(preRouteKey + ip);
		if (resource != null && !resource.isEmpty()) {
			if(netSid != null && !netSid.equals("")){
				if(!netSid.equals((String)resource.get("net_sid"))){
					return null;	
				}
			}
			String country = (String) resource.get("country");
			String city = (String) resource.get("city");
			String operator = (String) resource.get("operator");
			if (StrUtil.isEmpty(country) || StrUtil.isEmpty(city)) {
				return resource;
			}
			TbRsCity tbRsCity = tbRsCityMapper.queryCityByCityId(country, city);
			String cityName = StrUtil.isEmpty(tbRsCity.getCity()) ? tbRsCity.getCountry() : tbRsCity.getCity();
			String operatorName = tbRsIspMapper.queryById(operator);
			List<Map> coordinatesList = (List) GooleMapUtil.getCoordinate(cityName).get("result");
			if(coordinatesList == null && coordinatesList.size() != 1){
				resource.put("latitude", tbRsCity.getLatitude());
				resource.put("longitude", tbRsCity.getLongitude());
			}else{
				resource.put("latitude", coordinatesList.get(0).get("lat"));
				resource.put("longitude", coordinatesList.get(0).get("lng"));
			}
			resource.put("cityName", cityName);
			resource.put("operatorName", operatorName);
		}
		return resource;
	}
	

	
	@Override
	public List queryNeighborById(String ip, String sort, String order, String routeDomain) {
		List neighbor = new ArrayList();
		// 排序
		Map<String, String> resource = RedisService.getInstance().hgetall(sort + routeDomain.replace(RouterConstant.PRE_ROUTE_KEY, "") + "_" + ip);// 性价比、质量、
																						// 价格排序
		LinkedList<Entry<String, String>> sortResList = null;
		if (RouterConstant.ASC.equals(order)) {
			sortResList = sortAsc(resource);
		} else {
			sortResList = sortDESC(resource);
		}

		Map<String, String> qualityNB = RedisService.getInstance().hgetall(RouterConstant.NBR_QUALITY + routeDomain.replace(RouterConstant.PRE_ROUTE_KEY, "") + "_" + ip);
		Map<String, String> priceNB = RedisService.getInstance().hgetall(RouterConstant.NBR_PRICE + routeDomain.replace(RouterConstant.PRE_ROUTE_KEY, "") + "_" + ip);
		Map<String, String> perfNB = RedisService.getInstance().hgetall(RouterConstant.NBR_PERF + routeDomain.replace(RouterConstant.PRE_ROUTE_KEY, "") + "_" + ip);

		// 获取排序后的邻居域数据
		for (Entry<String, String> entry : sortResList) {
			String nodeIp = entry.getKey();
			Map node = queryResourceById(nodeIp, routeDomain);
			if (node == null || node.isEmpty()) {
				continue;
			}
			String quality = qualityNB.get(nodeIp);
			String price = priceNB.get(nodeIp);
			String perf = perfNB.get(nodeIp);
			node.put("quality", quality);
			node.put("price", price);
			node.put("perf", perf);
			neighbor.add(node);
		}

		return neighbor;
	}

	/**
	 * 升序
	 * 
	 * @param resource
	 * @return
	 */
	@Override
	public LinkedList sortAsc(Map<String, String> resource) {
		LinkedList sortResList = new LinkedList();
		if (resource != null && resource.size() > 0) {
			Iterator<Entry<String, String>> it = resource.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, String> entry = it.next();
				if (0 == sortResList.size()) {
					sortResList.add(entry);
				} else {
					for (int i = 0; i < sortResList.size(); i++) {
						Entry<String, String> note = (Entry<String, String>) sortResList.get(i);
						if (Integer.valueOf(entry.getValue()) <= Integer.valueOf(note.getValue())) {
							sortResList.add(i, entry);
							break;
						}
						if (i == (sortResList.size() - 1)) {
							sortResList.add(i + 1, entry);
							break;
						}

					}

				}

			}
		}
		return sortResList;
	}

	/**
	 * 降序
	 * 
	 * @param resource
	 * @return
	 */
	@Override
	public LinkedList sortDESC(Map<String, String> resource) {
		LinkedList sortResList = new LinkedList();
		if (resource != null && resource.size() > 0) {
			Iterator<Entry<String, String>> it = resource.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, String> entry = it.next();
				if (0 == sortResList.size()) {
					sortResList.add(entry);
				} else {
					for (int i = 0; i < sortResList.size(); i++) {
						Entry<String, String> note = (Entry<String, String>) sortResList.get(i);
						if (Integer.valueOf(entry.getValue()) >= Integer.valueOf(note.getValue())) {
							sortResList.add(i, entry);
							break;
						}
						if (i == (sortResList.size() - 1)) {
							sortResList.add(i + 1, entry);
							break;
						}

					}

				}

			}
		}
		return sortResList;
	}

	@Override
	public Map topology() {
		Map data = new HashMap();
		data = getTopologyline(data);
		data = getAllcityRTPP(data);
		return data;
	}

	@Override
	public Map trafficMap() {
		Map data = new HashMap();
		data = getStrategyline(data, RouterConstant.PRE_TRAFIC_KEYS);
		data = getAllRTPP(data);
		return data;
	}

	@Override
	public Map linkStatus() {
		Map data = new HashMap();
		data = getStrategyline(data, RouterConstant.NBR_QUALITY);
		data = getAllcityRTPP(data);
		return data;
	}

	@Override
	public Map topologyNew(Map data) {
		data = getAllRTPP(data);
		data = getTopologylineNew(data, RouterConstant.NBR_QUALITY);
		return data;
	}

	@Override
	public Map trafficMapNew(Map data) {
		data = getAllRTPP(data);
		data = getStrategyLineNew(data, RouterConstant.PRE_TRAFIC_KEYS);
		return data;
	}

	@Override
	public Map linkStatusNew(Map data) {
		data = getAllRTPP(data);
		data = getStrategyLineNew(data, RouterConstant.NBR_QUALITY);
		return data;
	}

	/**
	 * 地图上的城市节点信息
	 * 
	 * @param data
	 * @return
	 */
	public Map getAllcityRTPP(Map data) {
		Map cityInfo = new HashMap();// 存储城市节点信息
		String routeDomain = (String)data.get("routeDomain");
		// 获取所有节点key
		Set<String> keySet = RedisService.getInstance().getKeys(RouterConstant.INFO_KEYS);
		if (keySet == null) {
			return data;
		}
		for (String infoKey : keySet) {// 城市节点信息
			String ip = infoKey.replace(RouterConstant.PRE_RTPP_INTO, "");
			Map<String, String> ipInfo = queryResourceById(ip,routeDomain);
			String city = ipInfo.get("city");
			if (StrUtil.isEmpty(city)) {
				continue;
			}
			if (!cityInfo.containsKey(city)) {
				cityInfo.put(city, new ArrayList());
			}
			((List) cityInfo.get(city)).add(ipInfo);
		}
		data.put("effectScatter", cityInfo);
		return data;
	}

	/**
	 * 地图上的节点信息
	 * 
	 * @param data
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map getAllRTPP(Map data) {
		Map nodeMap = new HashMap();
		String domain = (String) data.get("domain");
		String netSid = (String) data.get("netSid");
		String preRouteKey = RouterConstant.PRE_RTPP_INTO + domain + "_";
		Map<String, ArrayList<Map>> cityInfo = new HashMap();// 存储城市节点信息
		// 获取所有节点key
		Set<String> keySet = RedisService.getInstance().getKeys(preRouteKey + "*");
		if (keySet == null) {
			return data;
		}
		for (String infoKey : keySet) {// 城市节点信息
			String ip = infoKey.replace(preRouteKey, "");
			Map<String, String> ipInfo = queryResource(ip,netSid,preRouteKey);
			if(ipInfo == null || ipInfo.isEmpty()){
				continue;
			}
			String city = ipInfo.get("city");
			if (StrUtil.isEmpty(city)) {
				continue;
			}
			if (!cityInfo.containsKey(city)) {
				cityInfo.put(city, new ArrayList());
			}
			((List) cityInfo.get(city)).add(ipInfo);
		}

		for (List<Map> tempList : cityInfo.values()) {
			int size = tempList.size();
			double pi = Math.toRadians(180);
			double per = (2 * pi) / size;
			double r = RouterConstant.RADII;
			for (int i = 0; i < size; i++) {
				Map ipInfo = tempList.get(i);
				double latitude = Double.valueOf(ipInfo.get("latitude").toString());
				double longitude = Double.valueOf(ipInfo.get("longitude").toString());
				latitude = latitude + r * Math.cos(per * i);
				longitude = longitude + r * Math.sin(per * i);
				ipInfo.put("latitude", latitude );
				ipInfo.put("longitude", longitude );
				nodeMap.put(ipInfo.get("ip"), ipInfo);
			}
		}

		data.put("effectScatter", nodeMap);
		return data;
	}

	/**
	 * 拓扑地图上的连线信息
	 * 
	 * @param data
	 * @return
	 */
	public Map getTopologyline(Map data) {
		String routeDomain = (String)data.get("routeDomain");
		// 获取所有包含邻居的节点key
		Set<String> NBRSet = RedisService.getInstance().getKeys(RouterConstant.NBR_PERF + '*');
		List<List> linesList = new ArrayList<List>();// 存储城市之间的连线
		if (NBRSet != null) {
			for (String infoKey : NBRSet) {
				Map<String, String> qualityNB = RedisService.getInstance().hgetall(infoKey);
				if (qualityNB.isEmpty()) {
					continue;
				}
				String ip = infoKey.replace(RouterConstant.NBR_PERF, "");
				Map<String, String> ipInfo = queryResourceById(ip,routeDomain);
				if (ipInfo == null || ipInfo.isEmpty()) {
					continue;
				}
				String cityId = ipInfo.get("city");
				String cityName = ipInfo.get("cityName");
				Iterator<Entry<String, String>> it = qualityNB.entrySet().iterator();
				while (it.hasNext()) {
					String ipNB = it.next().getKey();
					Map<String, String> ipNBInfo = queryResourceById(ipNB,routeDomain);
					if (ipNBInfo == null || ipNBInfo.isEmpty()) {
						continue;
					}
					String cityNBId = ipNBInfo.get("city");
					String cityNBNmae = ipNBInfo.get("cityName");
					System.out.println(cityName + " - " + cityNBNmae);
					if (!cityId.equals(cityNBId)) {//// 保证连接线是两城市之间
						int size = linesList.size();
						String fromTo = cityName + cityNBNmae;
						for (int i = 0; i <= size; i++) {// 遍历已存储的连线信息
							if (i < size) {
								List line = linesList.get(i);
								if (fromTo.equals((String) line.get(0) + line.get(1))) {
									List ipList = (List) line.get(2);
									if (!ipList.contains(ipNB + " - " + ip)) {// 保证非矢量节点只有一个
																				// 192.0.0.1
																				// -
																				// 192.0.0.2
																				// 与
																				// 192.0.0.2
																				// -
																				// 192.0.0.1
																				// 只能存在一个
										ipList.add(ip + " - " + ipNB); // 保证
																		// city1-city2与ip1-ip2能对应
									}
									break;
								}
								if (fromTo.equals((String) line.get(1) + line.get(0))) {
									List ipList = (List) line.get(2);
									if (!ipList.contains(ipNB + " - " + ip)) {// 保证非矢量节点只有一个
																				// 192.0.0.1
																				// -
																				// 192.0.0.2
																				// 与
																				// 192.0.0.2
																				// -
																				// 192.0.0.1
																				// 只能存在一个
										ipList.add(ipNB + " - " + ip);// 保证
																		// city1-city2与ip1-ip2能对应
									}
									break;
								}
							} else {// 用于存储新的两地连线信息
									// 组成List<List>的结构 ---> [城市1，城市2，[ip1 -
									// ip2,ip3 - ip4....]]
								List line = new ArrayList();
								line.add(cityName);
								line.add(cityNBNmae);
								List ipList = new ArrayList();// 存储同一个城市的ip
								ipList.add(ip + " - " + ipNB);
								line.add(ipList);
								linesList.add(line);
							}
						}
					}
				}
			}
			data.put("lines", linesList);
		}
		return data;
	}
	
	/**
	 * 拓扑地图上的连线信息
	 * 
	 * @param data
	 * @return
	 */
	public Map getTopologylineNew(Map data, String strategy){
		String domain = (String) data.get("domain");
		domain = domain + "_";
		Map<String, Map> nodeMap = (Map) data.get("effectScatter");
		// 获取所有包含邻居的节点key
		Set<String> NBRSet = RedisService.getInstance().getKeys(strategy + domain +"*");
		Map<String, List> linesMap = new HashMap<String, List>();// 存储节点之间的连线
		if (NBRSet != null) {
			for (String infoKey : NBRSet) {
				String ip = infoKey.replace(strategy + domain, "");
				Map<String, String> ipInfo = nodeMap.get(ip);
				if (ipInfo == null || ipInfo.isEmpty()) {
					continue;
				}
				Map<String, String> qualityNB = RedisService.getInstance().hgetall(infoKey);
				if (qualityNB.isEmpty()) {
					continue;
				}
				String cityId = ipInfo.get("city");
				String cityName = ipInfo.get("cityName");
				for (Map.Entry<String, String> entry : qualityNB.entrySet()) {
					Map line =  new HashMap();
					String NBip = entry.getKey();
					Map<String, String> NBipInfo = nodeMap.get(NBip);
					if (NBipInfo == null || NBipInfo.isEmpty()) {
						continue;
					}
					String NBcityName = NBipInfo.get("cityName");
					String ip2NBip = ip + " —— " + NBip;
					String ct2NBcy = cityName + " —— " + NBcityName;
					String NBip2ip = NBip + " —— " + ip;
					
					line.put("ip", ip2NBip);//封装连线信息
					line.put("city", ct2NBcy);
					line.put("fromLatitude", ipInfo.get("latitude"));
					line.put("fromLongitude", ipInfo.get("longitude"));
					line.put("toLatitude", NBipInfo.get("latitude"));
					line.put("toLongitude", NBipInfo.get("longitude"));

					if (linesMap.containsKey(NBip2ip)) {//两点之间的来回连线并为一条线
						List<Map> lineList = linesMap.get(NBip2ip);
						lineList.add(line);
					} else {
						List<Map> lineList = new ArrayList();
						lineList.add(line);
						linesMap.put(ip2NBip, lineList);
					}
				}
			}
			data.put("lines", linesMap);
		}
		return data;
	}

	/**
	 * 传输流量地图上的连线信息
	 * 
	 * @param data
	 * @param nbr
	 * @return
	 */
	public Map getStrategyline(Map data, String strategy) {
		String routeDomain = (String)data.get("routeDomain");
		// 获取所有包含邻居的节点key
		Set<String> NBRSet = RedisService.getInstance().getKeys(strategy + '*');
		List<List> linesList = new ArrayList<List>();// 存储城市之间的连线
		if (NBRSet != null) {
			for (String infoKey : NBRSet) {
				Map<String, String> qualityNB = RedisService.getInstance().hgetall(infoKey);
				if (qualityNB.isEmpty()) {
					continue;
				}
				String ip = infoKey.replace(strategy, "");
				Map<String, String> ipInfo = queryResourceById(ip,routeDomain);
				if (ipInfo == null || ipInfo.isEmpty()) {
					continue;
				}
				String cityId = ipInfo.get("city");
				String cityName = ipInfo.get("cityName");
				Iterator<Entry<String, String>> it = qualityNB.entrySet().iterator();
				while (it.hasNext()) {
					Entry<String, String> en = it.next();
					String ipNB = en.getKey();

					Map<String, String> ipNBInfo = queryResourceById(ipNB,routeDomain);
					if (ipNBInfo == null || ipNBInfo.isEmpty()) {
						continue;
					}
					String cityNBId = ipNBInfo.get("city");
					String cityNBNmae = ipNBInfo.get("cityName");
					System.out.println(cityName + " - " + cityNBNmae);
					if (!cityId.equals(cityNBId)) {//// 保证连接线是两城市之间
						int size = linesList.size();
						String fromTo = cityName + cityNBNmae;
						for (int i = 0; i <= size; i++) {// 遍历已存储的连线信息
							if (i < size) {
								List line = linesList.get(i);
								if (fromTo.equals((String) line.get(0) + line.get(1))) {
									for (int j = 2; j <= line.size(); j++) {
										if (j < line.size()) {
											List nodesList = (List) line.get(j);
											Map<String, String> nodes = (Map) nodesList.get(0);
											if (nodes.get("ip").equals(ipNB + " &rarr; " + ip)) {
												Map<String, String> addNode = new HashMap<String, String>();
												addNode.put("ip", ip + " &rarr; " + ipNB);
												addNode.put("value", en.getValue());
												nodesList.add(addNode);
												break;
											}
										} else {
											List nodesList = new ArrayList();
											Map<String, String> addNode = new HashMap<String, String>();
											addNode.put("ip", ip + " &rarr; " + ipNB);
											addNode.put("value", en.getValue());
											nodesList.add(addNode);
											line.add(nodesList);
											break;
										}
									}
									break;
								} else if (fromTo.equals((String) line.get(1) + line.get(0))) {
									for (int j = 2; j <= line.size(); j++) {
										if (j < line.size()) {
											List nodesList = (List) line.get(j);
											Map<String, String> nodes = (Map) nodesList.get(0);
											if (nodes.get("ip").equals(ipNB + " &rarr; " + ip)) {
												Map<String, String> addNode = new HashMap<String, String>();
												addNode.put("ip", ip + " &rarr; " + ipNB);
												addNode.put("value", en.getValue());
												nodesList.add(0, addNode);
												break;
											}
										} else {
											List nodesList = new ArrayList();
											Map<String, String> addNode = new HashMap<String, String>();
											addNode.put("ip", ip + " &rarr; " + ipNB);
											addNode.put("value", en.getValue());
											nodesList.add(0, addNode);
											line.add(nodesList);
											break;
										}
									}
									break;
								}
							} else {// 用于存储新的两地连线信息
									// 组成数据的结构 --->
									// [城市1，城市2，["ip1-ip2"信息,"ip2-ip1"信息],["ip3-ip4"信息,"ip4-ip3"信息]....]]
								List line = new ArrayList();
								line.add(cityName);
								line.add(cityNBNmae);
								List noteLists = new ArrayList();
								Map<String, String> node = new HashMap<String, String>();
								node.put("ip", ip + " &rarr; " + ipNB);
								node.put("value", en.getValue());
								noteLists.add(node);
								line.add(noteLists);
								linesList.add(line);
							}
						}
					}
				}
			}
			data.put("lines", linesList);
		}
		return data;
	}

	/**
	 * 传输流量地图上的连线信息
	 * 
	 * @param data
	 * @param nbr
	 * @return
	 */
	public Map getStrategyLineNew(Map data, String strategy) {
		String domain = (String) data.get("domain");
		domain = domain + "_";
		Map<String, Map> nodeMap = (Map) data.get("effectScatter");
		// 获取所有包含邻居的节点key
		Set<String> NBRSet = RedisService.getInstance().getKeys(strategy + domain + '*');
		Map<String, List> linesMap = new HashMap<String, List>();// 存储节点之间的连线
		if (NBRSet != null) {
			for (String infoKey : NBRSet) {
				String ip = infoKey.replace(strategy + domain, "");
				Map<String, String> ipInfo = nodeMap.get(ip);
				if (ipInfo == null || ipInfo.isEmpty()) {
					continue;
				}
				Map<String, String> qualityNB = RedisService.getInstance().hgetall(infoKey);
				if (qualityNB.isEmpty()) {
					continue;
				}
				String cityId = ipInfo.get("city");
				String cityName = ipInfo.get("cityName");
				for (Map.Entry<String, String> entry : qualityNB.entrySet()) {
					Map line =  new HashMap();
					String NBip = entry.getKey();
					String value = entry.getValue();
					Map<String, String> NBipInfo = nodeMap.get(NBip);
					if (NBipInfo == null || NBipInfo.isEmpty()) {
						continue;
					}
					String NBcityName = NBipInfo.get("cityName");
					String ip2NBip = ip + " &rarr; " + NBip;
					String ct2NBcy = cityName + " &rarr; " + NBcityName;
					String NBip2ip = NBip + " &rarr; " + ip;
					
					line.put("ip", ip2NBip);//封装连线信息
					line.put("city", ct2NBcy);
					line.put("value", value);
					line.put("fromLatitude", ipInfo.get("latitude"));
					line.put("fromLongitude", ipInfo.get("longitude"));
					line.put("toLatitude", NBipInfo.get("latitude"));
					line.put("toLongitude", NBipInfo.get("longitude"));

					if (linesMap.containsKey(NBip2ip)) {//两点之间的来回连线归在一起，以便于曲率设置
						List<Map> lineList = linesMap.get(NBip2ip);
						lineList.add(line);
					} else {
						List<Map> lineList = new ArrayList();
						lineList.add(line);
						linesMap.put(ip2NBip, lineList);
					}
				}
			}
			data.put("lines", linesMap);
		}
		return data;
	}

	@Override
	public PageContainer querySessions(int page, String paraKey) {
		Map sessions = new HashMap();
		Set<String> keySet = RedisService.getInstance().getKeys(paraKey);
		if (keySet == null) {
			return null;
		}
		for (String key : keySet) {
			String[] str = key.split("_");
			String strategy = str[3];// 会话策略
			String sessionKey = str[5];// 会话key
			Map session = RedisService.getInstance().hgetall(key);// 获取一条会话路径信息
			String path = (String)session.get("path");
			path.replaceAll(":", "->");
			session.put("strategy", strategy);
			session.put("sessionKey", sessionKey);
			if (sessions.containsKey(sessionKey)) {// 同一会话路径汇集
				List sessionArray = (List) sessions.get(sessionKey);
				sessionArray.add(session);
			} else {
				List sessionArray = new ArrayList();
				sessionArray.add(session);
				sessions.put(sessionKey, sessionArray);
			}
		}

		PageContainer pageContainer = new PageContainer();
		Map para = pageContainer.createRedisParameter(page, sessions.size());
		Map result = pageContainer.getRsMap();
		int begin = (int) para.get("begin");
		int end = (int) para.get("end");

		Iterator<Map.Entry<String, String>> it = sessions.entrySet().iterator();
		for (int i = 0; i < end; i++) {
			Map.Entry<String, String> entry = it.next();
			if (i >= begin) {
				result.put(entry.getKey(), entry.getValue());
			}
		}

		return pageContainer;
	}

	@Override
	public Map<String, Object> locationNew(Map<String, Object> data) {
		data = getAllRTPP(data);
		return data;
	}
}
