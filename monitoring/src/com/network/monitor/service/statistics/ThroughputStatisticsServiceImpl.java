package com.network.monitor.service.statistics;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.network.monitor.dao.StatDao;

/**
 * SR节点间吞吐量统计数据Service接口实现
 *
 */
@Service
@Transactional
public class ThroughputStatisticsServiceImpl implements
		ThroughputStatisticsService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(QualityStatisticsServiceImpl.class);

	@Autowired
	private StatDao dao;

	/**
	 * 统计吞吐量数据
	 */
	@Override
	public Map<String, Object> queryThroughput(Map<String, String> params) {
		LOGGER.debug("SR节点间的吞吐量统计数据：" + params);
		Map<String, Object> data = new HashMap<String, Object>();
		String src_sr_id = params.get("src_sr_id");
		if (null == src_sr_id && "".equals(src_sr_id)) {
			data.put("code", -1);
			data.put("msg", "监控目标ID为空，无法获取吞吐量统计数据");
			return data;
		}
		data = dao.getOneInfo("throughputStatistics.throughputRate", params);
		if (null == data) {
			data = new HashMap<String, Object>();
			data.put("code", -1);
			data.put("msg", "监控目标:" + src_sr_id + "吞吐量统计数据为空");
		} else {
			data.put("code", 1);
			data.put("msg", "监控目标:" + src_sr_id + "吞吐量统计数据获取成功");
		}
		return data;
	}

	/**
	 * 获取节点间吞吐量数据，画出曲线图
	 */
	@Override
	public Map<String, List<Map<String, Object>>> queryList(
			Map<String, String> params) {
		LOGGER.debug("获取SR节点间的吞吐量数据：" + params);
		List<Map<String, Object>> nbrips = dao.getSearchList(
				"topology.getNbripsBetweenSR", params);
		Map<String, List<Map<String, Object>>> data = new HashMap<String, List<Map<String, Object>>>();// 所有曲线数据
		if (null != nbrips && !nbrips.isEmpty()) {
			for (Map<String, Object> map : nbrips) {
				String ifname = map.get("ifname").toString();
				params.put("ifname", ifname);
				List<Map<String, Object>> dataList = dao.getSearchList(
						"throughputStatistics.queryThrough", params);
				data.put(ifname, dataList);
			}
		}
		return data;
	}

	/**
	 * 批量插入SR节点间吞吐量
	 */
	@Override
	public void insertBatch(Map<String, Object> params) {
		// LOGGER.info("批量插入SR节点间吞吐量数据：" + params.toString());
		int i = dao.insert("throughputStatistics.insertbatch", params);
		dao.insert("throughputStatistics.insertbatchtmp", params);
		if (i > 0) {
			// LOGGER.info("批量插入SR节点间吞吐量数据成功");
		} else {
			LOGGER.error("批量插入SR节点间吞吐量数据失败");
		}
	}

	/**
	 * 流量统计图
	 */
	@Override
	public List<Map<String, Object>> drawThroughput(Map<String, String> param) {
		LOGGER.debug("查询SR节点间拓流量图");

		List<Map<String, Object>> throughputList = null;// 保存流量图

		// 获取流量图
		List<Map<String, Object>> nbrips = dao.getSearchList(
				"topology.getNeighbors", param);// 获取节点所有本地接口名称
		if (null != nbrips && !nbrips.isEmpty()) {
			throughputList = new ArrayList<Map<String, Object>>();
			float smoothness = 0.1f;// 曲线弧度
			for (Map<String, Object> mapTemp : nbrips) {
				String ifname = mapTemp.get("ifname").toString();// SR节点本地接口名称
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("sr_id", mapTemp.get("src_sr_id").toString());
				params.put("ifname", ifname);
				String opDateTmp = new SimpleDateFormat("yyyyMM")
						.format(new Date());
				params.put("opDate", opDateTmp);
				params.put("start_time", param.get("start_time"));
				params.put("end_time", param.get("end_time"));
				Map<String, Object> throughput = dao.getOneInfo(
						"throughputStatistics.drawThroughput", params);
				if (null != throughput && !throughput.isEmpty()) {
					Map<String, Object> nodeQualitys = new HashMap<String, Object>();// 每个节点的链路质量
					// 源节点
					nodeQualitys.put("sr_lng", mapTemp.get("sr_lng"));
					nodeQualitys.put("sr_lat", mapTemp.get("sr_lat"));
					nodeQualitys.put("src_sr_id", mapTemp.get("src_sr_id"));
					nodeQualitys.put("src_sr_name", mapTemp.get("src_sr_name"));

					// 目的节点信息
					nodeQualitys.put("dst_sr_lng", mapTemp.get("dst_sr_lng"));
					nodeQualitys.put("dst_sr_lat", mapTemp.get("dst_sr_lat"));
					nodeQualitys.put("dst_sr_id", mapTemp.get("dst_sr_id"));
					nodeQualitys.put("dst_sr_name", mapTemp.get("dst_sr_name"));

					boolean flag = false;
					if (null != throughputList && !throughputList.isEmpty()) {
						for (Map<String, Object> temp : throughputList) {
							if (temp.get("src_sr_id")
									.toString()
									.equals(mapTemp.get("src_sr_id").toString())
									&& temp.get("dst_sr_id")
											.toString()
											.equals(mapTemp.get("dst_sr_id")
													.toString())) {
								smoothness = (Float) temp.get("smoothness");
								flag = true;
							}
							if (temp.get("src_sr_id").toString()
									.equals(mapTemp.get("dst_sr_id"))
									&& temp.get("dst_sr_id")
											.toString()
											.equals(mapTemp.get("src_sr_id")
													.toString())) {
								smoothness = (Float) temp.get("smoothness");
								flag = true;
							}
						}
					}
					if (flag) {
						smoothness += 0.05;
					}

					nodeQualitys.put("smoothness", smoothness);// 曲线弧度
					nodeQualitys.put("ifname", ifname);// 流入字节数
					nodeQualitys.put("in_bytes", throughput.get("in_bytes"));// 流入字节数
					nodeQualitys.put("out_bytes", throughput.get("out_bytes"));// 流出字节数
					throughputList.add(nodeQualitys);
				}
			}
		}

		// 返回封装的数据
		return throughputList;
	}
	
	/**
	 * 流量统计图
	 */
	@Override
	public List<Map<String, Object>> drawThroughputNew(Map<String, String> param) {
		LOGGER.debug("查询SR节点间拓流量图");

		List<Map<String, Object>> throughputList = null;// 保存流量图

		// 获取流量图
		List<Map<String, Object>> nbrips = dao.getSearchList("topology.getNeighbors", param);// 获取节点所有本地接口名称
		StringBuffer bf = new StringBuffer();
		for (Map<String, Object> mapTemp : nbrips) {
			String nbrip = mapTemp.get("src_sr_id").toString();
			String ifname = mapTemp.get("ifname").toString();// SR节点本地接口名称
			bf.append("'").append(nbrip).append(ifname).append("',");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		bf.deleteCharAt(bf.length() - 1);
		params.put("ipIfList", bf.toString());
		params.put("count", nbrips.size());
		List<Map<String, Object>> qualitys = dao.getSearchList("throughputStatistics.drawThroughputNew", params);
		if (null != nbrips && !nbrips.isEmpty()) {
			throughputList = new ArrayList<Map<String, Object>>();
			float smoothness = 0.1f;// 曲线弧度
			for (Map<String, Object> mapTemp : nbrips) {
				String nbrip = mapTemp.get("src_sr_id").toString();
				String ifname = mapTemp.get("ifname").toString();// SR节点本地接口名称
				Map<String, Object> throughput = new HashMap<String, Object>();
				for (Map<String, Object> map : qualitys) {
					if (map.get("ipIf").toString().equals(nbrip + ifname)) {
						throughput = map;
						break;
					}
				}
				if (null != throughput && !throughput.isEmpty()) {
					Map<String, Object> nodeQualitys = new HashMap<String, Object>();// 每个节点的链路质量
					// 源节点
					nodeQualitys.put("sr_lng", mapTemp.get("sr_lng"));
					nodeQualitys.put("sr_lat", mapTemp.get("sr_lat"));
					nodeQualitys.put("src_sr_id", mapTemp.get("src_sr_id"));
					nodeQualitys.put("src_sr_name", mapTemp.get("src_sr_name"));

					// 目的节点信息
					nodeQualitys.put("dst_sr_lng", mapTemp.get("dst_sr_lng"));
					nodeQualitys.put("dst_sr_lat", mapTemp.get("dst_sr_lat"));
					nodeQualitys.put("dst_sr_id", mapTemp.get("dst_sr_id"));
					nodeQualitys.put("dst_sr_name", mapTemp.get("dst_sr_name"));

					boolean flag = false;
					if (null != throughputList && !throughputList.isEmpty()) {
						for (Map<String, Object> temp : throughputList) {
							if (temp.get("src_sr_id")
									.toString()
									.equals(mapTemp.get("src_sr_id").toString())
									&& temp.get("dst_sr_id")
											.toString()
											.equals(mapTemp.get("dst_sr_id")
													.toString())) {
								smoothness = (Float) temp.get("smoothness");
								flag = true;
							}
							if (temp.get("src_sr_id").toString()
									.equals(mapTemp.get("dst_sr_id"))
									&& temp.get("dst_sr_id")
											.toString()
											.equals(mapTemp.get("src_sr_id")
													.toString())) {
								smoothness = (Float) temp.get("smoothness");
								flag = true;
							}
						}
					}
					if (flag) {
						smoothness += 0.05;
					}

					nodeQualitys.put("smoothness", smoothness);// 曲线弧度
					nodeQualitys.put("ifname", ifname);// 流入字节数
					nodeQualitys.put("in_bytes", throughput.get("in_bytes"));// 流入字节数
					nodeQualitys.put("out_bytes", throughput.get("out_bytes"));// 流出字节数
					throughputList.add(nodeQualitys);
				}
			}
		}

		// 返回封装的数据
		return throughputList;
	}
	
	/**
	 * 流量统计图
	 */
	@Override
	public List<Map<String, Object>> drawThroughputChina(Map<String, String> param) {
		LOGGER.debug("查询SR节点间拓流量图");

		List<Map<String, Object>> throughputList = null;// 保存流量图

		// 获取流量图
		List<Map<String, Object>> nbrips = dao.getSearchList(
				"topology.getNeighborsChina", param);// 获取节点所有本地接口名称
		if (null != nbrips && !nbrips.isEmpty()) {
			throughputList = new ArrayList<Map<String, Object>>();
			float smoothness = 0.1f;// 曲线弧度
			for (Map<String, Object> mapTemp : nbrips) {
				String ifname = mapTemp.get("ifname").toString();// SR节点本地接口名称
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("sr_id", mapTemp.get("src_sr_id").toString());
				params.put("ifname", ifname);
				String opDateTmp = new SimpleDateFormat("yyyyMM")
						.format(new Date());
				params.put("opDate", opDateTmp);
				params.put("start_time", param.get("start_time"));
				params.put("end_time", param.get("end_time"));
				Map<String, Object> throughput = dao.getOneInfo(
						"throughputStatistics.drawThroughput", params);
				if (null != throughput && !throughput.isEmpty()) {
					Map<String, Object> nodeQualitys = new HashMap<String, Object>();// 每个节点的链路质量
					// 源节点
					nodeQualitys.put("sr_lng", mapTemp.get("sr_lng"));
					nodeQualitys.put("sr_lat", mapTemp.get("sr_lat"));
					nodeQualitys.put("src_sr_id", mapTemp.get("src_sr_id"));
					nodeQualitys.put("src_sr_name", mapTemp.get("src_sr_name"));

					// 目的节点信息
					nodeQualitys.put("dst_sr_lng", mapTemp.get("dst_sr_lng"));
					nodeQualitys.put("dst_sr_lat", mapTemp.get("dst_sr_lat"));
					nodeQualitys.put("dst_sr_id", mapTemp.get("dst_sr_id"));
					nodeQualitys.put("dst_sr_name", mapTemp.get("dst_sr_name"));

					boolean flag = false;
					if (null != throughputList && !throughputList.isEmpty()) {
						for (Map<String, Object> temp : throughputList) {
							if (temp.get("src_sr_id")
									.toString()
									.equals(mapTemp.get("src_sr_id").toString())
									&& temp.get("dst_sr_id")
											.toString()
											.equals(mapTemp.get("dst_sr_id")
													.toString())) {
								smoothness = (Float) temp.get("smoothness");
								flag = true;
							}
							if (temp.get("src_sr_id").toString()
									.equals(mapTemp.get("dst_sr_id"))
									&& temp.get("dst_sr_id")
											.toString()
											.equals(mapTemp.get("src_sr_id")
													.toString())) {
								smoothness = (Float) temp.get("smoothness");
								flag = true;
							}
						}
					}
					if (flag) {
						smoothness += 0.05;
					}

					nodeQualitys.put("smoothness", smoothness);// 曲线弧度
					nodeQualitys.put("ifname", ifname);// 流入字节数
					nodeQualitys.put("in_bytes", throughput.get("in_bytes"));// 流入字节数
					nodeQualitys.put("out_bytes", throughput.get("out_bytes"));// 流出字节数
					throughputList.add(nodeQualitys);
				}
			}
		}

		// 返回封装的数据
		return throughputList;
	}
	
	/**
	 * 流量统计图
	 */
	@Override
	public List<Map<String, Object>> drawThroughputChinaNew(Map<String, String> param) {
		LOGGER.debug("查询SR节点间拓流量图");

		List<Map<String, Object>> throughputList = null;// 保存流量图

		// 获取流量图
		List<Map<String, Object>> nbrips = dao.getSearchList("topology.getNeighborsChina", param);// 获取节点所有本地接口名称
		StringBuffer bf = new StringBuffer();
		for (Map<String, Object> mapTemp : nbrips) {
			String nbrip = mapTemp.get("src_sr_id").toString();
			String ifname = mapTemp.get("ifname").toString();// SR节点本地接口名称
			bf.append("'").append(nbrip).append(ifname).append("',");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		bf.deleteCharAt(bf.length() - 1);
		params.put("ipIfList", bf.toString());
		params.put("count", nbrips.size());
		List<Map<String, Object>> qualitys = dao.getSearchList("throughputStatistics.drawThroughputNew", params);
		if (null != nbrips && !nbrips.isEmpty()) {
			throughputList = new ArrayList<Map<String, Object>>();
			float smoothness = 0.1f;// 曲线弧度
			for (Map<String, Object> mapTemp : nbrips) {
				String nbrip = mapTemp.get("src_sr_id").toString();
				String ifname = mapTemp.get("ifname").toString();// SR节点本地接口名称
				Map<String, Object> throughput = new HashMap<String, Object>();
				for (Map<String, Object> map : qualitys) {
					if (map.get("ipIf").toString().equals(nbrip + ifname)) {
						throughput = map;
						break;
					}
				}
				if (null != throughput && !throughput.isEmpty()) {
					Map<String, Object> nodeQualitys = new HashMap<String, Object>();// 每个节点的链路质量
					// 源节点
					nodeQualitys.put("sr_lng", mapTemp.get("sr_lng"));
					nodeQualitys.put("sr_lat", mapTemp.get("sr_lat"));
					nodeQualitys.put("src_sr_id", mapTemp.get("src_sr_id"));
					nodeQualitys.put("src_sr_name", mapTemp.get("src_sr_name"));

					// 目的节点信息
					nodeQualitys.put("dst_sr_lng", mapTemp.get("dst_sr_lng"));
					nodeQualitys.put("dst_sr_lat", mapTemp.get("dst_sr_lat"));
					nodeQualitys.put("dst_sr_id", mapTemp.get("dst_sr_id"));
					nodeQualitys.put("dst_sr_name", mapTemp.get("dst_sr_name"));

					boolean flag = false;
					if (null != throughputList && !throughputList.isEmpty()) {
						for (Map<String, Object> temp : throughputList) {
							if (temp.get("src_sr_id")
									.toString()
									.equals(mapTemp.get("src_sr_id").toString())
									&& temp.get("dst_sr_id")
											.toString()
											.equals(mapTemp.get("dst_sr_id")
													.toString())) {
								smoothness = (Float) temp.get("smoothness");
								flag = true;
							}
							if (temp.get("src_sr_id").toString()
									.equals(mapTemp.get("dst_sr_id"))
									&& temp.get("dst_sr_id")
											.toString()
											.equals(mapTemp.get("src_sr_id")
													.toString())) {
								smoothness = (Float) temp.get("smoothness");
								flag = true;
							}
						}
					}
					if (flag) {
						smoothness += 0.05;
					}

					nodeQualitys.put("smoothness", smoothness);// 曲线弧度
					nodeQualitys.put("ifname", ifname);// 流入字节数
					nodeQualitys.put("in_bytes", throughput.get("in_bytes"));// 流入字节数
					nodeQualitys.put("out_bytes", throughput.get("out_bytes"));// 流出字节数
					throughputList.add(nodeQualitys);
				}
			}
		}

		// 返回封装的数据
		return throughputList;
	}

	public List<Map<String, Object>> srIp(Map<String, String> formData) {
		List<Map<String, Object>> list = dao.getSearchList(
				"throughputStatistics.srIp", formData);// 获取节点所有本地接口名称
		return list;
	}

	public List<Map<String, Object>> dstIp(Map<String, String> formData) {

		return dao.getSearchList("throughputStatistics.dstIp", formData);
	}

	public List<Map<String, Object>> routeMonitor(Map<String, String> formData) {
		/**
		 * 1 检索出源节点的所有路由集A 2 在A中检索(dest_ip = 目的IP &src_ip = 源节点IP)的集合B
		 * 3如B为空，则无路由，如非空则根据该路由的下一跳查找到下一跳的SR； 4 检索该SR的所有接口IP是否包含目的IP，如有则路由查找完成；
		 * 5如不包含目的IP，则该SR为中转SR，继续步骤1继续查找。
		 */

		// 获取路由集合
		List<Map<String, Object>> listAll = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> listReturn= new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> listB = dao.getSearchList(
				"throughputStatistics.listRoute", formData);
		if (listB.size() == 0) {
			return null;
		} else {

				for (Map<String, Object> map : listB) {
					if(get(map, formData, "",0)!=null&&get(map, formData, "",0).size()>0){
					listAll.add(get(map, formData, "",0));
					}
				}

		}
		LOGGER.debug("******************" + listAll.size() + "   " + listAll);
		HashSet h = new HashSet(listAll);
		listAll.clear();
		listAll.addAll(h);
		if(listAll.size()>1){
			listReturn.add(listAll.get(0));
		}else{
			listReturn=listAll;
		}
		return listReturn;
	}


	public Map<String, Object> get(Map<String, Object> map,
			Map<String, String> formData,String transferRoad,int metric) {
		// 通过下一跳来寻找下一个节点
		List<Map<String, Object>> listNextSR = new ArrayList<Map<String, Object>>();
		listNextSR = dao.getSearchList(
				"throughputStatistics.listNextIp", map);
		if (listNextSR.size() == 0) {
			transferRoad="";
			metric=0;
			return null;
		} else {	
			
			Map<String,Object> obj=new HashMap<String, Object>();
			for (int i = 0; i < listNextSR.size(); i++) {
				if (listNextSR.get(i).get("sr_id")
						.equals(formData.get("dst_sr_id"))) {
					// 找到路径了
//					metric+=Integer.parseInt(listNextSR.get(i).get("metric").toString());
//					transferRoad+=listNextSR.get(i).get("sr_id");
					if(transferRoad.contains("-->")){
						transferRoad=transferRoad.substring(0,transferRoad.lastIndexOf("-->"));
					}
					obj.put("metric", metric);
					obj.put("transferRoad", transferRoad);
					return obj;
				}
			}
			// 如果没找到 那么根据节点的下一跳继续找
			for (int i = 0; i < listNextSR.size(); i++) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("next_hop", listNextSR.get(i).get("next_hop").toString());
				m.put("dst_ip", formData.get("dst_ip").toString());
				transferRoad+=listNextSR.get(i).get("sr_name")+"-->";
				String xxp[] =transferRoad.split("-->");
				if(xxp.length>5){
					metric=0;
					transferRoad="";
					break;
				}
				metric+=Integer.parseInt(listNextSR.get(i).get("metric").toString());
				if(obj.size()>0){
					return obj;
				}else{
					obj=get(m, formData, transferRoad, metric);
				}
			}
			return obj;
		}

	}
}
