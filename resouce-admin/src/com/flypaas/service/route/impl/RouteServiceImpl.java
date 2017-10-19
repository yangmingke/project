package com.flypaas.service.route.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.flypaas.constant.RouterConstant;
import com.flypaas.dao.TbRsCityMapper;
import com.flypaas.dao.TbRsIspMapper;
import com.flypaas.model.RouteAnalysis;
import com.flypaas.model.TbRsCity;
import com.flypaas.rest.HttpRequest;
import com.flypaas.service.city.CityService;
import com.flypaas.service.impl.RedisService;
import com.flypaas.service.route.RouteService;
import com.flypaas.util.GooleMapUtil;
import com.flypaas.util.JsonUtil;
import com.flypaas.util.PageContainer;
import com.flypaas.util.StrUtil;
import com.flypaas.util.SysConstant;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * @author 作者 yangmingke:
 * @version 创建时间：2017年2月13日 下午12:32:00 类说明
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Service
public class RouteServiceImpl implements RouteService {

	@Autowired
	private TbRsCityMapper tbRsCityMapper;
	@Autowired
	private TbRsIspMapper tbRsIspMapper;
	@Autowired
	private CityService cityServiceImpl;
	private static Log log = LogFactory.getLog(RouteServiceImpl.class);
	
	@Override
	public Set<String> getDomainList() {
		return RedisService.getInstance().getKeys(RouterConstant.ROUTE_KEYS);
	}

	@Override
	public PageContainer getIpList(String concurrent, int page,String ipKeyWord,String pageRowCount) {
		if (StrUtil.isEmpty(concurrent)) {
			concurrent = RouterConstant.ROUTE_DEFAULT_KEY;
		}
		Set<String> res = RedisService.getInstance().zrange(concurrent, 0, -1);
		List<String> ipList = new ArrayList<String>();
		ipList.addAll(res);
		if(StringUtils.isNotEmpty(ipKeyWord)){
			for(int i = 0;i < ipList.size(); i++){
				String ip = ipList.get(i);
				if(!ip.contains(ipKeyWord)){
					ipList.remove(ip);
					i--;
				}
			}
		}
		List<Object> ipInfoList = new ArrayList<Object>();
		PageContainer pageContainer = new PageContainer();
		if(StringUtils.isNotEmpty(pageRowCount)){
			pageContainer.setPageRowCount(Integer.parseInt(pageRowCount));
		}
		Map<String, Integer> para = pageContainer.createRedisParameter(page, ipList.size());

		for (int i = para.get("begin"); i < pageContainer.getTotalCount() && i < para.get("end"); i++) {
			Map<String, String> ipInfo = queryResourceById(ipList.get(i),concurrent);
			if (ipInfo == null || ipInfo.isEmpty()) {
				continue;
			}
			Integer bandwidthLimit = Integer.valueOf(ipInfo.get("bandwidth_limit"));
			bandwidthLimit = bandwidthLimit / 1024;
			ipInfo.put("bandwidth_limit", bandwidthLimit.toString());
			ipInfoList.add(ipInfo);
		}
		pageContainer.setResult(ipInfoList);
		return pageContainer;
	}

	@SuppressWarnings("null")
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
	public Map queryResource(String ip,String netLevel, String preRouteKey) {
		Map resource = RedisService.getInstance().hgetall(preRouteKey + ip);
		if (resource != null && !resource.isEmpty()) {
			if(netLevel != null && !netLevel.equals("")){
				if(!netLevel.equals((String)resource.get("net_level"))){
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
			if(tbRsCity.getLatitude() == null || tbRsCity.getLongitude() == null){//如果数据库未记录该城市经纬坐标，则查询谷歌地图城市经纬度，并存入city表中
				List<Map> coordinatesList = (List) GooleMapUtil.getCoordinate(cityName).get("result");
				if(cityName.contains("香港")){//香港与深圳太接近，点容易重合，将香港坐标偏移
					String latitude = (String) coordinatesList.get(0).get("lat");
					String longitude = (String) coordinatesList.get(0).get("lng");
					Float lat = (Float.valueOf(latitude) - 0.3F);
					Float lng = (Float.valueOf(longitude) + 0.3F);
					resource.put("latitude", lat.toString());
					resource.put("longitude", lng.toString());
					tbRsCity.setLatitude(Double.valueOf(lat.toString()));
					tbRsCity.setLongitude(Double.valueOf(lng.toString()));
				}else{
					resource.put("latitude", coordinatesList.get(0).get("lat"));
					resource.put("longitude", coordinatesList.get(0).get("lng"));
					tbRsCity.setLatitude(Double.valueOf(coordinatesList.get(0).get("lat").toString()));
					tbRsCity.setLongitude(Double.valueOf(coordinatesList.get(0).get("lng").toString()));
				}
				tbRsCityMapper.updateByPrimaryKeySelective(tbRsCity);
			}else{
				resource.put("latitude", tbRsCity.getLatitude());
				resource.put("longitude", tbRsCity.getLongitude());
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
		Map<String, String> resource = RedisService.getInstance().hgetall(sort + routeDomain.replace(RouterConstant.PRE_ROUTE_KEY, "") + "_" + ip);// 性价比、质量、价格排序
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
	public Map topology(Map data) {
		data = getAllRTPP(data);
		data = getTopologyline(data, RouterConstant.NBR_QUALITY);
		return data;
	}

	@Override
	public Map trafficMap(Map data) {
		data = getAllRTPP(data);
		data = getStrategyLine(data, RouterConstant.PRE_TRAFIC_KEYS);
		return data;
	}

	@Override
	public Map linkStatus(Map data) {
		long startTime = new Date().getTime();
		data = getAllRTPP(data);
		data = getStrategyLine(data, RouterConstant.NBR_QUALITY);
		System.out.println("查询节点信息用时: " + (new Date().getTime()-startTime));
		return data;
	}


	/**
	 * 地图上的节点信息
	 * 
	 * @param data
	 * @return
	 */
	private Map getAllRTPP(Map data) {
		long startTime = new Date().getTime();
		Map nodeMap = new HashMap();
		String domain = (String) data.get("domain");
		String netLevel = (String) data.get("netLevel");
		String preRouteKey = RouterConstant.PRE_RTPP_INTO + domain.replace(RouterConstant.PRE_ROUTE_KEY, "")+"_";
		/*Set<String> res = null;
		if(StringUtils.isNotEmpty(domain)){
			res = RedisService.getInstance().zrange(domain, 0, -1);
		}*/
		Map<String, ArrayList<Map>> cityInfo = new HashMap();// 存储城市节点信息
		// 获取所有节点key
		Set<String> keySet = RedisService.getInstance().getKeys(preRouteKey + "*");
		if (keySet == null) {
			return data;
		}
		for (String infoKey : keySet) {// 城市节点信息
			String ip = infoKey.replace(preRouteKey, "");
			/*if(StringUtils.isNotEmpty(domain) && !res.contains(ip)){
				continue;
			}*/
			Map<String, String> ipInfo = queryResource(ip,netLevel,preRouteKey);
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

		for (List<Map> tempList : cityInfo.values()) {//将同一城市节点画成一个圈
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
		System.out.println("查询节点信息用时: " + (new Date().getTime()-startTime));
		return data;
	}

	
	/**
	 * 拓扑地图上的连线信息
	 * 
	 * @param data
	 * @return
	 */
	public Map getTopologyline(Map data, String strategy){
		long startTime = new Date().getTime();
		String domain = (String) data.get("domain");
		domain = domain.replace(RouterConstant.PRE_ROUTE_KEY, "")+"_";
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
			data.put("linesCount", linesMap.size());
			data.put("lines", linesMap);
		}
		System.out.println("查询连线信息用时: " + (new Date().getTime()-startTime));
		return data;
	}


	/**
	 * 传输流量地图上的连线信息
	 * 
	 * @param data
	 * @param nbr
	 * @return
	 */
	public Map getStrategyLine(Map data, String strategy) {
		long startTime = new Date().getTime();
		int linesCount = 0;//线的数量
		String domain = (String) data.get("domain");
		domain = domain.replace(RouterConstant.PRE_ROUTE_KEY, "")+"_";
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
				String cityName = ipInfo.get("cityName");
				for (Map.Entry<String, String> entry : qualityNB.entrySet()) {
					Map line =  new HashMap();
					String NBip = entry.getKey();
					String value = entry.getValue();
					Map<String, String> NBipInfo = nodeMap.get(NBip);
					if (NBipInfo == null || NBipInfo.isEmpty()) {
						continue;
					}
					linesCount++;
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
			data.put("linesCount", linesCount);
			data.put("lines", linesMap);
		}
		System.out.println("查询连线信息用时: " + (new Date().getTime()-startTime));
		return data;
	}

	@Override
	public PageContainer querySessions(int page, String paraKey, String pageRowCount) {
		Map sessions = new HashMap();
		Set<String> keySet = RedisService.getInstance().getKeys(paraKey);
		if (keySet == null) {
			return null;
		}
		for (String key : keySet) {
			String[] str = key.split("_");
			String strategy = str[3];// 会话策略
			String sessionKey = str[6]+"_"+str[7];// 会话key
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
		if(StringUtils.isNotEmpty(pageRowCount)){
			pageContainer.setPageRowCount(Integer.valueOf(pageRowCount));
		}
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
	public Map<String, Object> transmit(String domain, String srcIP, String destIP) throws Exception{
		String routeArea = domain.replace(RouterConstant.PRE_ROUTE_KEY, "");
		Map srcCityInfo = queryCityInfo(srcIP,"源IP");
		Map destCityInfo = queryCityInfo(destIP,"目的IP");
		List srcRtpplist = getrtpplist(srcIP,routeArea);//获取源接入点
		String srcInfo = createIpInfo(srcIP,srcRtpplist);//转换字符串参数
		List destRtpplist = getrtpplist(destIP,routeArea);//获取目的接入点
		String destInfo = createIpInfo(destIP,destRtpplist);//转换字符串参数
		Map routeInfo = displayRoute(srcInfo, destInfo);//获取传递路径
		if (routeInfo == null) {
			return null;
		}
		Set<String> nodes = (Set) routeInfo.get("nodes");
		Map<String, ArrayList<Map>> cityInfo = new HashMap();// 存储城市节点信息
		String preRouteKey = RouterConstant.PRE_RTPP_INTO + routeArea +"_";
		for (String ip : nodes) {// 城市节点信息
			Map<String, String> ipInfo = queryResource(ip,null,preRouteKey);
			String city = ipInfo.get("city");
			if (!cityInfo.containsKey(city)) {
				cityInfo.put(city, new ArrayList());
			}
			((List) cityInfo.get(city)).add(ipInfo);
		}
		Map nodeMap = new HashMap();//存储所有节点
		for (List<Map> tempList : cityInfo.values()) {//将同一城市节点画成一个圈
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
		Map data = new HashMap();
		data.put("srcIpInfo", srcCityInfo);
		data.put("destIpInfo", destCityInfo);
		data.put("effectScatter", nodeMap);
		data.put("routeList", routeInfo.get("lines"));//用来制定连线顺序
		return data;
	}
	
	private Map queryCityInfo(String ip,String nodeName) throws Exception{
		Map IPInfo = getIPInfo(ip);
		Map cityInfo = cityServiceImpl.queryCity(IPInfo.get("country").toString(), IPInfo.get("city").toString());//IP位置信息
		cityInfo.put("name",nodeName);
		cityInfo.put("ip",ip);
		if(IPInfo.get("isp").toString() != null && !"0".equals(IPInfo.get("isp").toString())){//"0"代表无数据
			String srcOperatorName = tbRsIspMapper.queryById(IPInfo.get("isp").toString());
			cityInfo.put("operatorName",srcOperatorName);
		}
		return cityInfo;
	}
	
	private Map getIPInfo(String ip) throws Exception{
        String ipInfoUrl = StrUtil.getConfigVal("ip_info");
		HttpRequest req = new HttpRequest();
		HttpResponse response;
		//获取源IP信息
		response = req.get(ipInfoUrl+"?ip="+ip);
		Map ipInfoMap = null;
		//获取响应实体信息
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			String result = EntityUtils.toString(entity, "UTF-8");
			ipInfoMap = JSON.parseObject(result, Map.class);
			// 确保HTTP响应内容全部被读出或者内容流被关闭
			EntityUtils.consume(entity);
		}
		return ipInfoMap;
	}
	
	private List getrtpplist(String ip , String routeArea) throws Exception{
        String routerRtppUrl = StrUtil.getConfigVal("router_rtpp");
		HttpRequest req = new HttpRequest();
		HttpResponse response;
		List rtppList = null;
		//获取IP信息
		response = req.get(routerRtppUrl+"?ip="+ip+"&area="+routeArea);
		//获取响应实体信息
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			String result = EntityUtils.toString(entity, "UTF-8");
			Map map = JSON.parseObject(result, Map.class);
			rtppList = (List) map.get("rtpp");
			// 确保HTTP响应内容全部被读出或者内容流被关闭
			EntityUtils.consume(entity);
		}
		return rtppList;
	}
	
	private String createIpInfo(String srcIp,List rtppList) {
		JsonObject ipInfo = new JsonObject();
		ipInfo.addProperty("ip", srcIp);
		ipInfo.addProperty("uid", srcIp);
		JsonArray aplist = new JsonArray();
		for(int i = 0; i < rtppList.size(); i++){
			JsonObject temp = new JsonObject();
			String ip = (String) rtppList.get(i);
			temp.addProperty("ip", ip.split(":")[0]);
			temp.addProperty("delay", 0);
			temp.addProperty("lost", 0);
			aplist.add(temp);
		}
		ipInfo.add("aplist", aplist);
		return ipInfo.toString();
	}
	
	private Map displayRoute(String srcInfo , String destInfo) throws Exception{
		Map routeMap = new HashMap();
		srcInfo = JsonUtil.json2URLCode(srcInfo); 
		destInfo = JsonUtil.json2URLCode(destInfo); 
		StringBuffer para= new StringBuffer();
		para.append("?srcinfo=").append(srcInfo).append("&dstinfo=").append(destInfo);
		String displayRouteUrl = StrUtil.getConfigVal("route_display");
		HttpRequest req = new HttpRequest();
		HttpResponse response;
		response = req.get(displayRouteUrl+para);
		//获取响应实体信息
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			String result = EntityUtils.toString(entity, "UTF-8");
//			String result = "{\n   \"routes\" : [\n      {\n         \"path\" : \"192.168.0.8:192.168.0.174\",\n         \"routeid\" : 1\n      },{\n         \"path\" : \"192.168.0.11:192.168.0.10,192.168.0.174\",\n         \"routeid\" : 2\n      },{\n         \"path\" : \"192.168.0.8:192.168.0.11,192.168.0.174\",\n         \"routeid\" : 3\n      }\n   ]\n}\n\n";
			Map map = JSON.parseObject(result, Map.class);
			String respCode = (String) map.get("result");
			if(respCode != null && !respCode.equals(SysConstant.C000000)){
				return null;
			}
			List lineList = new ArrayList();//用于连线
			Set nodeSet = new HashSet();//用来描出所有节点
			List<Map> routes = (List) map.get("routes");//
			for(Map routeInfo : routes){
				String path = routeInfo.get("path").toString();
				int routeid = (int) routeInfo.get("routeid");
				String[] pathList = path.split(":");
				lineList.add(routeid-1,pathList);
				for(String nodeList : pathList){
					for(String node : nodeList.split(",")){
						nodeSet.add(node);
					}
				}
			}
			routeMap.put("nodes", nodeSet);
			routeMap.put("lines", lineList);
			return routeMap;
		}
		log.error("错误，displayRoute接口返回数据为空");
		return null;
	}

	@Override
	public LinkedList<String> queryAllIp(String routeDomain) {
		Set<String> ipKeys = RedisService.getInstance().getKeys(RouterConstant.PRE_RTPP_INTO+routeDomain+"_*");
		LinkedList<String> ipAddressList = new LinkedList<String>();
		for(String ipKey : ipKeys){//升序去重
			String ip = ipKey.split("_")[3];
			int i = 0;
			for(String ipAddress : ipAddressList){
				if(StrUtil.firstIpAddressIsLess(ip, ipAddress)){
					ipAddressList.add(i,ip);
					break;
				}
				i++;
			}
			if(ipAddressList.size() == i){
				ipAddressList.addLast(ip);
			}
		}
		return ipAddressList;
	}

	@Override
	public PageContainer analysis(String resultSelection, String analysisStr, String routeDomain, Integer currentPage, Integer pageRowCount) {
		PageContainer pageContainer = new PageContainer();
		if(pageRowCount != null){
			pageContainer.setPageRowCount(pageRowCount);
		}
		List<Object> routeAnalysisList = new ArrayList<Object>();
		String[] analysisIpArray = analysisStr.split(",");
		for(String srcIp : analysisIpArray){
			for(String destIp : analysisIpArray){
				if(srcIp.equals(destIp)){//同一个IP不分析
					continue;
				}else{
					RouteAnalysis routeAnalysis = new RouteAnalysis();
					Map<String, String> nbrQuality = RedisService.getInstance().hgetall(RouterConstant.NBR_QUALITY+routeDomain+"_"+srcIp);//邻居信息质量COST
					routeAnalysis.setNbrQuality(nbrQuality.get(destIp));
					
					Map<String, String> weightQuality = RedisService.getInstance().hgetall(RouterConstant.WEIGHT_QUALITY+routeDomain+"_"+srcIp);//权重信息质量COST，16777215代表不可达
					routeAnalysis.setWeightQuality(RouterConstant.DISCONNECT.toString().equals(weightQuality.get(destIp)) ? null : weightQuality.get(destIp));
					if(StringUtils.isNotEmpty(resultSelection)){
						if(RouterConstant.resultUnsure.equals(resultSelection)){
							if(routeAnalysis.getNbrQuality() != null && routeAnalysis.getWeightQuality() != null){
								continue;
							}
						}else if(routeAnalysis.getNbrQuality() == null || routeAnalysis.getWeightQuality() == null){
							continue;
						}else if(RouterConstant.resultSure.equals(resultSelection)){
							if(routeAnalysis.getNbrQuality() == null || routeAnalysis.getWeightQuality() == null){
								continue;
							}
						}else if(RouterConstant.resultBetter.equals(resultSelection)){
							if(!(Integer.parseInt(routeAnalysis.getWeightQuality()) < Integer.parseInt(routeAnalysis.getNbrQuality()))){
								continue;
							}
						}else if(RouterConstant.resultWorse.equals(resultSelection)){
							if(!(Integer.parseInt(routeAnalysis.getWeightQuality()) > Integer.parseInt(routeAnalysis.getNbrQuality()))){
								continue;
							}
						}else if(RouterConstant.resultUnchanged.equals(resultSelection)){
							if(!(Integer.parseInt(routeAnalysis.getWeightQuality()) == Integer.parseInt(routeAnalysis.getNbrQuality()))){
								continue;
							}
						}
					}
					
					Map<String, String> sptQuality = RedisService.getInstance().hgetall(RouterConstant.SPT_QUALITY+routeDomain+"_"+srcIp);//最优路径
					StringBuffer bestPath = new StringBuffer();
					if(!sptQuality.isEmpty()){
						bestPath.append(srcIp);
						String pathIp = sptQuality.get(destIp);
						while(pathIp != null && !srcIp.equals(pathIp)){
							bestPath.append(" &rarr; ").append(pathIp);
							pathIp = sptQuality.get(pathIp);
						}
						bestPath.append(" &rarr; ").append(destIp);
					}
					routeAnalysis.setBestPath(bestPath.toString());
					routeAnalysis.setSrcIp(srcIp);
					routeAnalysis.setDestIp(destIp);;
					routeAnalysisList.add(routeAnalysis);
				}
			}
		}
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();//将结果放入缓存，便于翻页
		request.getSession().setAttribute(RouterConstant.routeAnalysisResult, routeAnalysisList);
		
		Map<String, Integer> para= pageContainer.createRedisParameter(currentPage == null ? 1 : currentPage, routeAnalysisList.size());//分页工具
		pageContainer.setResult(routeAnalysisList.subList(para.get("begin"), para.get("end")));
		return pageContainer;
	}

	@Override
	public PageContainer analysisChangePage(Integer currentPage, Integer pageRowCount) {
		PageContainer pageContainer = new PageContainer();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		List routeAnalysisList = (List) request.getSession().getAttribute(RouterConstant.routeAnalysisResult);
		
		if(pageRowCount != null){
			pageContainer.setPageRowCount(pageRowCount);
		}
		Map<String, Integer> para= pageContainer.createRedisParameter(currentPage == null ? 1 : currentPage, routeAnalysisList.size());//分页工具
		pageContainer.setResult(routeAnalysisList.subList(para.get("begin"), para.get("end")));
		return pageContainer;
	}
}
