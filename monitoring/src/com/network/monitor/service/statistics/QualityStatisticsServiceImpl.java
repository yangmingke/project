package com.network.monitor.service.statistics;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.network.monitor.dao.StatDao;
import com.network.monitor.model.PageContainer;

/**
 * SR节点间质量统计Service接口实现类
 *
 */
@Service
@Transactional
public class QualityStatisticsServiceImpl implements QualityStatisticsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(QualityStatisticsServiceImpl.class);

	@Autowired
	private StatDao dao;

	/**
	 * 分页搜索SR节点间的质量统计数据
	 */
	@Override
	public PageContainer query(Map<String, String> params) {
		LOGGER.debug("分页搜索SR节点间的质量统计数据：" + params);
		return dao.getSearchPage("qualityStatistics.query", "qualityStatistics.queryCount", params);
	}

	/**
	 * 获取丢包率数据
	 */
	@Override
	public Map<String, List<Map<String, Object>>> queryLostRate(Map<String, String> params) {
		LOGGER.debug("获取SR节点间的丢包率数据：" + params);
		List<Map<String, Object>> nbrips = dao.getSearchList("topology.getNbripsBetweenSR", params);
		Map<String, List<Map<String, Object>>> data = new HashMap<String, List<Map<String, Object>>>();// 所有曲线数据
		if (null != nbrips && !nbrips.isEmpty()) {
			for (Map<String, Object> map : nbrips) {
				String nbrip = map.get("nbrip").toString();
				params.put("nbrip", nbrip);
				List<Map<String, Object>> dataList = dao.getSearchList("qualityStatistics.lostRate", params);
				data.put(nbrip, dataList);
			}
		}
		return data;
	}

	/**
	 * 获取节点间的延时数据
	 */
	@Override
	public Map<String, List<Map<String, Object>>> queryDelay(Map<String, String> params) {
		LOGGER.debug("获取SR节点间的延时数据：" + params);
		List<Map<String, Object>> nbrips = dao.getSearchList("topology.getNbripsBetweenSR", params);
		Map<String, List<Map<String, Object>>> data = new HashMap<String, List<Map<String, Object>>>();// 所有曲线数据
		if (null != nbrips && !nbrips.isEmpty()) {
			for (Map<String, Object> map : nbrips) {
				String nbrip = map.get("nbrip").toString();
				params.put("nbrip", nbrip);
				List<Map<String, Object>> dataList = dao.getSearchList("qualityStatistics.delay", params);
				data.put(nbrip, dataList);
			}
		}
		return data;
	}

	/**
	 * 批量插入SR节点质量数据
	 */
	@Override
	public void insertBatch(Map<String, Object> params) {
		LOGGER.info("批量插入SR节点质量数据：" + params);
		int i = dao.insert("qualityStatistics.insertbatch", params);
		dao.insert("qualityStatistics.insertbatchtmp", params);
		if (i > 0) {
			// LOGGER.info("批量插入SR节点质量数据成功");
		} else {
			LOGGER.error("批量插入SR节点质量数据失败");
		}
	}

	/**
	 * 链路质量图
	 */
	@Override
	public List<Map<String, Object>> drawQuality(Map<String, String> param) {
		LOGGER.debug("查询SR节点间拓扑图关系");

		List<Map<String, Object>> qualityList = null;// 链路质量数据
		// 获取链路质量图
		List<Map<String, Object>> nbrips = dao.getSearchList("topology.getNeighbors", param);// 获取节点所有接口IP
		if (null != nbrips && !nbrips.isEmpty()) {
			qualityList = new ArrayList<Map<String, Object>>();
			float smoothness = 0.1f;// 曲线弧度
			for (Map<String, Object> mapTemp : nbrips) {
				String nbrip = mapTemp.get("nbrip").toString();
				String nbrid = mapTemp.get("dst_sr_id").toString();
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("sr_id", mapTemp.get("src_sr_id").toString());
				params.put("nbrip", nbrip);
				String opDateTmp = new SimpleDateFormat("yyyyMM").format(new Date());
				params.put("opDate", opDateTmp);
				// params.put("end_time", param.get("end_time"));
				// params.put("start_time", param.get("start_time"));
				Map<String, Object> quality = dao.getOneInfo("qualityStatistics.drawQuality", params);
				if (null != quality && !quality.isEmpty()) {
					Map<String, Object> nodeQualitys = new HashMap<String, Object>();// 每个节点的链路质量
					// 源节点
					nodeQualitys.put("sr_lng", mapTemp.get("sr_lng"));
					nodeQualitys.put("sr_lat", mapTemp.get("sr_lat"));
					nodeQualitys.put("src_sr_id", mapTemp.get("src_sr_id"));
					nodeQualitys.put("src_sr_name", mapTemp.get("src_sr_name"));

					// 目的节点信息
					nodeQualitys.put("dst_sr_lng", mapTemp.get("dst_sr_lng"));
					nodeQualitys.put("dst_sr_lat", mapTemp.get("dst_sr_lat"));
					nodeQualitys.put("dst_sr_id", nbrid);
					nodeQualitys.put("dst_sr_name", mapTemp.get("dst_sr_name"));

					boolean flag = false;
					if (null != qualityList && !qualityList.isEmpty()) {
						for (Map<String, Object> temp : qualityList) {
							if (temp.get("src_sr_id").toString().equals(mapTemp.get("src_sr_id").toString())
									&& temp.get("dst_sr_id").toString().equals(mapTemp.get("dst_sr_id").toString())) {
								smoothness = (Float) temp.get("smoothness");
								flag = true;
							}
						}
					}
					if (flag) {
						smoothness += 0.05;
					}
					nodeQualitys.put("smoothness", smoothness);// 曲线弧度
					nodeQualitys.put("nbrip", nbrip);
					nodeQualitys.put("metric", quality.get("metric"));// 链路样本值
					nodeQualitys.put("lost_rate", quality.get("lost_rate"));// 丢包率
					nodeQualitys.put("average_delay", quality.get("average_delay"));// 延时
					qualityList.add(nodeQualitys);
				}
			}
		}
		LOGGER.debug("  查询出了结果     " + qualityList);
		// 返回封装的数据
		return qualityList;
	}

	/**
	 * 链路质量图
	 */
	@Override
	public List<Map<String, Object>> drawQualityNew(Map<String, String> param) {
		LOGGER.debug("查询SR节点间拓扑图关系");

		List<Map<String, Object>> qualityList = null;// 链路质量数据
		// 获取链路质量图
		List<Map<String, Object>> nbrips = dao.getSearchList("topology.getNeighbors", param);// 获取节点所有接口IP
		StringBuffer bf = new StringBuffer();
		for (Map<String, Object> mapTemp : nbrips) {
			String nbrip = mapTemp.get("nbrip").toString();
			String sr_id = mapTemp.get("src_sr_id").toString();
			bf.append("'").append(sr_id).append(" ").append(nbrip).append("',");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		bf.deleteCharAt(bf.length() - 1);
		params.put("ipList", bf.toString());
		params.put("count", nbrips.size());
		List<Map<String, Object>> qualitys = dao.getSearchList("qualityStatistics.drawQualityNew", params);
		if (null != nbrips && !nbrips.isEmpty()) {
			qualityList = new ArrayList<Map<String, Object>>();
			float smoothness = 0.1f;// 曲线弧度
			for (Map<String, Object> mapTemp : nbrips) {
				String nbrip = mapTemp.get("nbrip").toString();
				String sr_id = mapTemp.get("src_sr_id").toString();
				String nbrid = mapTemp.get("dst_sr_id").toString();
				Map<String, Object> quality = new HashMap<String, Object>();
				for (Map<String, Object> map : qualitys) {
					if (map.get("ip").toString().equals(sr_id + " " + nbrip)) {
						quality = map;
						break;
					}
				}

				if (null != quality && !quality.isEmpty()) {
					Map<String, Object> nodeQualitys = new HashMap<String, Object>();// 每个节点的链路质量
					// 源节点
					nodeQualitys.put("sr_lng", mapTemp.get("sr_lng"));
					nodeQualitys.put("sr_lat", mapTemp.get("sr_lat"));
					nodeQualitys.put("src_sr_id", mapTemp.get("src_sr_id"));
					nodeQualitys.put("src_sr_name", mapTemp.get("src_sr_name"));

					// 目的节点信息
					nodeQualitys.put("dst_sr_lng", mapTemp.get("dst_sr_lng"));
					nodeQualitys.put("dst_sr_lat", mapTemp.get("dst_sr_lat"));
					nodeQualitys.put("dst_sr_id", nbrid);
					nodeQualitys.put("dst_sr_name", mapTemp.get("dst_sr_name"));

					boolean flag = false;
					if (null != qualityList && !qualityList.isEmpty()) {
						for (Map<String, Object> temp : qualityList) {
							if (temp.get("src_sr_id").toString().equals(mapTemp.get("src_sr_id").toString())
									&& temp.get("dst_sr_id").toString().equals(mapTemp.get("dst_sr_id").toString())) {
								smoothness = (Float) temp.get("smoothness");
								flag = true;
							}
						}
					}
					if (flag) {
						smoothness += 0.05;
					}
					nodeQualitys.put("smoothness", smoothness);// 曲线弧度
					nodeQualitys.put("nbrip", nbrip);
					nodeQualitys.put("metric", quality.get("metric"));// 链路样本值
					nodeQualitys.put("lost_rate", quality.get("lost_rate"));// 丢包率
					nodeQualitys.put("average_delay", quality.get("average_delay"));// 延时
					qualityList.add(nodeQualitys);
				}
			}
		}
		LOGGER.debug("  查询出了结果     " + qualityList);
		// 返回封装的数据
		return qualityList;
	}

	/**
	 * 链路质量图
	 */
	@Override
	public List<Map<String, Object>> drawQualityChina(Map<String, String> param) {
		LOGGER.debug("查询SR节点间拓扑图关系");

		List<Map<String, Object>> qualityList = null;// 链路质量数据
		// 获取链路质量图
		List<Map<String, Object>> nbrips = dao.getSearchList("topology.getNeighborsChina", param);// 获取节点所有接口IP
		StringBuffer bf = new StringBuffer();
		for (Map<String, Object> mapTemp : nbrips) {
			String nbrip = mapTemp.get("nbrip").toString();
			String sr_id = mapTemp.get("src_sr_id").toString();
			bf.append("'").append(sr_id).append(" ").append(nbrip).append("',");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		bf.deleteCharAt(bf.length() - 1);
		params.put("ipList", bf.toString());
		params.put("count", nbrips.size());
		List<Map<String, Object>> qualitys = dao.getSearchList("qualityStatistics.drawQualityNew", params);
		if (null != nbrips && !nbrips.isEmpty()) {
			qualityList = new ArrayList<Map<String, Object>>();
			float smoothness = 0.1f;// 曲线弧度
			for (Map<String, Object> mapTemp : nbrips) {
				String nbrip = mapTemp.get("nbrip").toString();
				String sr_id = mapTemp.get("src_sr_id").toString();
				String nbrid = mapTemp.get("dst_sr_id").toString();
				Map<String, Object> quality = new HashMap<String, Object>();
				for (Map<String, Object> map : qualitys) {
					if (map.get("ip").toString().equals(sr_id + " " + nbrip)) {
						quality = map;
						break;
					}
				}
				if (null != quality && !quality.isEmpty()) {
					Map<String, Object> nodeQualitys = new HashMap<String, Object>();// 每个节点的链路质量
					// 源节点
					nodeQualitys.put("sr_lng", mapTemp.get("sr_lng"));
					nodeQualitys.put("sr_lat", mapTemp.get("sr_lat"));
					nodeQualitys.put("src_sr_id", mapTemp.get("src_sr_id"));
					nodeQualitys.put("src_sr_name", mapTemp.get("src_sr_name"));

					// 目的节点信息
					nodeQualitys.put("dst_sr_lng", mapTemp.get("dst_sr_lng"));
					nodeQualitys.put("dst_sr_lat", mapTemp.get("dst_sr_lat"));
					nodeQualitys.put("dst_sr_id", nbrid);
					nodeQualitys.put("dst_sr_name", mapTemp.get("dst_sr_name"));

					boolean flag = false;
					if (null != qualityList && !qualityList.isEmpty()) {
						for (Map<String, Object> temp : qualityList) {
							if (temp.get("src_sr_id").toString().equals(mapTemp.get("src_sr_id").toString())
									&& temp.get("dst_sr_id").toString().equals(mapTemp.get("dst_sr_id").toString())) {
								smoothness = (Float) temp.get("smoothness");
								flag = true;
							}
						}
					}
					if (flag) {
						smoothness += 0.05;
					}
					nodeQualitys.put("smoothness", smoothness);// 曲线弧度
					nodeQualitys.put("nbrip", nbrip);
					nodeQualitys.put("metric", quality.get("metric"));// 链路样本值
					nodeQualitys.put("lost_rate", quality.get("lost_rate"));// 丢包率
					nodeQualitys.put("average_delay", quality.get("average_delay"));// 延时
					qualityList.add(nodeQualitys);
				}
			}
		}
		LOGGER.debug("  查询出了结果     " + qualityList);

		// 返回封装的数据
		return qualityList;
	}

	// 获取监控点
	public List<Map<String, Object>> query_monitor() {
		return dao.getSearchList("tag.sr_monitor", null);
	}

	// 获取监控目标
	public List<Map<String, Object>> getDst_sr_id(Map<String, String> formData) {
		return dao.getSearchList("tag.query_monitor_target", formData);
	}
}
