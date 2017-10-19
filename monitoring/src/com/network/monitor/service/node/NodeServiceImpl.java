package com.network.monitor.service.node;

import java.util.ArrayList;
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
import com.network.monitor.util.GooleMapUtil;
import com.network.monitor.util.LngAndLatUtil;

/**
 * SR节点信息管理Service接口实现
 * 
 */
@Service
@Transactional
public class NodeServiceImpl implements NodeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(NodeServiceImpl.class);

	@Autowired
	private StatDao dao;

	/**
	 * 查询SR节点信息
	 */
	@Override
	public PageContainer query(Map<String, String> params) {
		return dao.getSearchPage("node.getNode", "node.getNodeCount", params);
	}

	/**
	 * 确认配置SR信息
	 */
	@Override
	public Map<String, Object> confirmNodeInfo(Map<String, String> params) {
		LOGGER.debug("确认配置SR节点信息，添加/修改：" + params);

		//计算是否已配置邻居信息
		int count = 0;
		// 计算SR节点经纬度

		// 计算经纬度
		Float lng = 0F;// SR节点经度
		Float lat = 0F;// SR节点纬度
		List<Map<String, Object>> list = dao.getSearchList("node.getByCityId", params);
		if (null != list && !list.isEmpty()) {// 已有相同城市
			lng = (float) (Float.valueOf(list.get(0).get("lng").toString()) + Math.random() * 2 - 1.7);
			lat = Float.valueOf(list.get(0).get("lat").toString());
		} else {
			try {
				// 通过google地图获取经纬度
				Map<String, Object> map = GooleMapUtil.getCoordinate(params.get("cityName").toString());
				List<Map<String, Object>> listMap = (List<Map<String, Object>>) map.get("result");
				if (listMap.size() > 0) {
					lng = Float.valueOf(listMap.get(0).get("lng").toString());
					lat = Float.valueOf(listMap.get(0).get("lat").toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		params.put("lng", lng.toString());
		params.put("lat", lat.toString());

		Map<String, Object> data = new HashMap<String, Object>();
		if (null != params.get("operate")) {// 修改SR节点信息
			String sr_id = params.get("operate").toString();
			Map<String, Object> node = dao.getOneInfo("node.getNodeByID", sr_id);
			int i = 0;
			if (null != node && !node.isEmpty()
					&& node.get("cityId").toString().equals(params.get("cityId").toString())) {
				i = dao.update("node.updaeNodeInfo", params);// 改变经纬度
			} else {
				i = dao.update("node.updaeChangeCity", params);
			}

			if (i > 0) {
				data.put("result", "success");
				data.put("msg", "修改SR节点信息成功");
			} else {
				data.put("result", "success");
				data.put("msg", "修改SR节点信息失败");
			}
			params.put("sr_id", sr_id);
			count = dao.getSearchSize("topology.neighborsCount", params);
			if(count == 0){
				dao.delete("topology.deleteNeighborstemp", params);
			}
		} else {// 配置SR节点信息
			Map<String, Object> nodeInfo = dao.getOneInfo("node.getNodeByID", params.get("sr_id"));
			if (null != nodeInfo && null != nodeInfo.get("sr_id") && !"".equals(nodeInfo.get("sr_id"))) {
				data.put("result", "fail");
				data.put("msg", "SR节点已经存在，请重新确认后再提交！");
				return data;
			}

			int i = dao.insert("node.insertNodeInfo", params);
			if (i > 0) {
				data.put("result", "success");
				data.put("msg", "配置SR节点信息成功");
			} else {
				data.put("result", "fail");
				data.put("msg", "配置SR节点信息失败");
			}
		}
		if(!"1".equals(params.get("level")) && count == 0){
			List<Map<String, Object>> nodeList = dao.getSearchList("node.getNeighborNodes", params);
			Map paraMap = new HashMap();
			paraMap.put("nodeList",nodeList);
			paraMap.put("sr_id", params.get("sr_id"));
			dao.insert("topology.insertbatchtemp", paraMap);
		}
		
		return data;
	}

	/**
	 * 获取SR节点信息
	 */
	@Override
	public Map<String, Object> getNodeInfo(String sr_id) {
		LOGGER.debug("根据sr_id获取SR节点信息：" + sr_id);
		Map<String, Object> data = new HashMap<String, Object>();

		if (null == sr_id && "".equals(sr_id)) {
			data.put("code", -1);
			data.put("msg", "sr_id为空，无法获取SR节点信息");
			return data;
		}

		data = dao.getOneInfo("node.getNodeByID", sr_id);
		if (null != data && !data.isEmpty()) {
			data.put("code", 1);
			data.put("msg", "获取SR节点信息成功");
		} else {
			data.put("code", -1);
			data.put("msg", "SR节点信息为空");
		}

		return data;
	}

	/**
	 * 删除SR节点配置信息
	 */
	@Override
	public Map<String, Object> deleteNodeInfo(String sr_id) {
		LOGGER.debug("删除SR节点信息：" + sr_id);
		Map<String, Object> data = new HashMap<String, Object>();

		if (null == sr_id && "".equals(sr_id)) {
			data.put("code", -1);
			data.put("msg", "sr_id为空，无法删除SR节点信息");
			return data;
		}

		dao.delete("node.deleteNodeINfo", sr_id);
		dao.delete("topology.deleteNeighborsTemp", sr_id);
		data.put("code", 1);
		data.put("msg", "删除SR节点信息成功");

		return data;
	}

	/**
	 * 获取监控的所有SR节点
	 */
	@Override
	public List<Map<String, Object>> queryNode() {
		return dao.selectList("node.queryQualityNode", null);
	}

	/**
	 * 获取省份所有城市
	 */
	@Override
	public List<Map<String, Object>> getCitys(String provinceId) {
		LOGGER.debug("获取省份所有城市 provinceId:" + provinceId);
		return dao.getSearchList("tag.city", provinceId);
	}

	/**
	 * 获取所有SR节点信息
	 */
	@Override
	public List<Map<String, Object>> getNodeList() {
		LOGGER.debug("查询所有SR节点间");

		List<Map<String, Object>> nodes = new ArrayList<Map<String, Object>>();// 保存SR节点

		// 获取所有SR节点
		List<Map<String, Object>> list = dao.getSearchList("node.getNodes", null);
		Float lng = 0F;
		Float lat = 0F;
		if (null != list && !list.isEmpty()) {
			for (Map<String, Object> map : list) {
				Map<String, Object> temp = new HashMap<String, Object>();// 节点属性

				lng = Float.valueOf(map.get("lng").toString());
				lat = Float.valueOf(map.get("lat").toString());
				if (lng <= 0 || lat <= 0) {
					// 计算经纬度
					try {
						String[] lngAndLat = LngAndLatUtil.getCoordinate(map.get("city").toString());
						lng = Float.valueOf(lngAndLat[0]);
						lat = Float.valueOf(lngAndLat[1]);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				// 将该节点加入节点集合
				temp.put("sr_id", map.get("sr_id"));
				temp.put("level", map.get("level"));
				temp.put("sr_name", map.get("sr_name"));
				temp.put("city", map.get("city"));
				temp.put("lng", lng);
				temp.put("lat", lat);
				nodes.add(temp);
			}
		}
		return nodes;
	}

	public void deleteBatch(Map<String, String> formData) {
		String ids = formData.get("ids");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids.toString().split(","));
		dao.delete("node.deleteBatch", map);
	}

	public List<Map<String, Object>> getProvinces(String countryId) {
		LOGGER.debug("获取省份所有城市 provinceId:" + countryId);
		return dao.getSearchList("tag.province", countryId);
	}

	/**
	 * 获取所有SR中国节点信息
	 */
	@Override
	public List<Map<String, Object>> getChinaNodeList() {
		LOGGER.debug("查询所有SR节点间");

		List<Map<String, Object>> nodes = new ArrayList<Map<String, Object>>();// 保存SR节点

		// 获取所有SR节点
		List<Map<String, Object>> list = dao.getSearchList("node.getChinaNodes", null);
		Float lng = 0F;
		Float lat = 0F;
		if (null != list && !list.isEmpty()) {
			for (Map<String, Object> map : list) {
				Map<String, Object> temp = new HashMap<String, Object>();// 节点属性

				lng = Float.valueOf(map.get("lng").toString());
				lat = Float.valueOf(map.get("lat").toString());
				if (lng <= 0 || lat <= 0) {
					// 计算经纬度
					try {
						String[] lngAndLat = LngAndLatUtil.getCoordinate(map.get("city").toString());
						lng = Float.valueOf(lngAndLat[0]);
						lat = Float.valueOf(lngAndLat[1]);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				// 将该节点加入节点集合
				temp.put("sr_id", map.get("sr_id"));
				temp.put("level", map.get("level"));
				temp.put("sr_name", map.get("sr_name"));
				temp.put("city", map.get("city"));
				temp.put("lng", lng);
				temp.put("lat", lat);
				nodes.add(temp);
			}
		}
		return nodes;
	}
}
