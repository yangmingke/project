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
import com.network.monitor.entity.RouteVO;
import com.network.monitor.model.PageContainer;

/**
 * SR节点间拓扑图关系Service接口实现
 *
 */
@Service
@Transactional
public class TopologyServiceImpl implements TopologyService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TopologyServiceImpl.class);
	
	@Autowired
	private StatDao dao;
	
	@Override
	public List<Map<String, Object>> getTopology(Map<String, String> params) {
		LOGGER.debug("查询SR节点间拓扑图关系");
		//获取临时节点列表
		List<Map<String, Object>> tempSrlist = dao.getSearchList("topology.queryTopologyTempSrId", null);
		//获取SR节点间的拓扑关系图
		List<Map<String, Object>> neighborlist = dao.getSearchList("topology.queryTopology", params);
		List<Map<String, Object>> topologyList = null;//拓扑关系图
		if (null != neighborlist && !neighborlist.isEmpty()) {
			topologyList = new ArrayList<Map<String,Object>>();
			for (Map<String, Object> map : neighborlist) {
				String sr_id = map.get("src_sr_id").toString();//源节点
				String dst_sr_id = map.get("dst_sr_id").toString();//邻居节点
				
				//巡回线只描述一条
				boolean isExist = false;
				int simpleChain = 1;//单链
				float smoothness = 0.1f;//曲线弧度
				if (null != topologyList && !topologyList.isEmpty()) {
					for (Map<String, Object> temp : topologyList) {//来回节点只画一条曲线，双链的画两条
						if (temp.get("src_sr_id").equals(dst_sr_id)
								&& temp.get("dst_sr_id").equals(sr_id)) {
							isExist = true;
						}
						if (temp.get("src_sr_id").equals(sr_id)
								&& temp.get("dst_sr_id").equals(dst_sr_id)) {//多链
							temp.put("simpleChain", 0);
							simpleChain = 0;
							smoothness = (Float) temp.get("smoothness");
						}
					}
				}
				if (isExist) {
					continue;
				}
				if (0 == simpleChain) {//曲线弧度
					smoothness +=0.05;
				}
				
				Map<String, Object> topology = new HashMap<String, Object>();
				//源节点
				topology.put("sr_lng", map.get("sr_lng"));
				topology.put("sr_lat", map.get("sr_lat"));
				topology.put("src_sr_id", map.get("src_sr_id"));
				topology.put("src_sr_name", map.get("src_sr_name"));
				topology.put("level", map.get("level"));//节点类型
				
				//目的节点
				topology.put("dst_sr_lng", map.get("dst_sr_lng"));
				topology.put("dst_sr_lat", map.get("dst_sr_lat"));
				topology.put("dst_sr_id", map.get("dst_sr_id"));
				topology.put("dst_sr_name", map.get("dst_sr_name"));
				topology.put("src_iframe",map.get("src_iframe"));
				topology.put("dst_iframe",map.get("dst_iframe"));
				//曲线弧度
				topology.put("smoothness", smoothness);
				topology.put("simpleChain", simpleChain);
				topologyList.add(topology);
				//如果已配置过拓扑 则删除临时信息
				for(Map tempMap : tempSrlist){
					if(sr_id.equals(tempMap.get("sr_id")) || dst_sr_id.equals(tempMap.get("sr_id"))){
						dao.delete("topology.deleteNeighborsTemp", tempMap.get("sr_id"));
					}
				}
			}
		}
		
		//获取SR节点间的拓扑关系图
		List<Map<String, Object>> neighborlistTemp = dao.getSearchList("topology.queryTopologyTemp", params);
		if (null != neighborlistTemp && !neighborlistTemp.isEmpty()) {
			for (Map<String, Object> map : neighborlistTemp) {
				String sr_id = map.get("src_sr_id").toString();//源节点
				String dst_sr_id = map.get("dst_sr_id").toString();//邻居节点
				
				//巡回线只描述一条
				boolean isExist = false;
				int simpleChain = 1;//单链
				float smoothness = 0.1f;//曲线弧度
				if (null != topologyList && !topologyList.isEmpty()) {
					for (Map<String, Object> temp : topologyList) {//来回节点只画一条曲线，双链的画两条
						if (temp.get("src_sr_id").equals(dst_sr_id)
								&& temp.get("dst_sr_id").equals(sr_id)) {
							isExist = true;
						}
						if (temp.get("src_sr_id").equals(sr_id)
								&& temp.get("dst_sr_id").equals(dst_sr_id)) {//多链
							temp.put("simpleChain", 0);
							simpleChain = 0;
							smoothness = (Float) temp.get("smoothness");
						}
					}
				}
				if (isExist) {
					continue;
				}
				if (0 == simpleChain) {//曲线弧度
					smoothness +=0.05;
				}
				
				Map<String, Object> topology = new HashMap<String, Object>();
				//源节点
				topology.put("sr_lng", map.get("sr_lng"));
				topology.put("sr_lat", map.get("sr_lat"));
				topology.put("src_sr_id", map.get("src_sr_id"));
				topology.put("src_sr_name", map.get("src_sr_name"));
				topology.put("level", map.get("level"));//节点类型
				
				//目的节点
				topology.put("dst_sr_lng", map.get("dst_sr_lng"));
				topology.put("dst_sr_lat", map.get("dst_sr_lat"));
				topology.put("dst_sr_id", map.get("dst_sr_id"));
				topology.put("dst_sr_name", map.get("dst_sr_name"));
				topology.put("src_iframe","available");
				topology.put("dst_iframe","available");
				//曲线弧度
				topology.put("smoothness", smoothness);
				topology.put("simpleChain", simpleChain);
				//临时拓扑标志
				topology.put("isTemp", true);
				topologyList.add(topology);
			}
		}
		
		//返回封装的数据
		return topologyList;
	}
	
	public List<Map<String, Object>> getChinaTopology(Map<String, String> params) {
		LOGGER.debug("查询SR节点间拓扑图关系");
		//获取临时节点列表
		List<Map<String, Object>> tempSrlist = dao.getSearchList("topology.queryTopologyTempSrId", null);
		//获取SR节点间的拓扑关系图
		List<Map<String, Object>> neighborlist = dao.getSearchList("topology.queryChinaTopology", params);
		List<Map<String, Object>> topologyList = null;//拓扑关系图
		if (null != neighborlist && !neighborlist.isEmpty()) {
			topologyList = new ArrayList<Map<String,Object>>();
			for (Map<String, Object> map : neighborlist) {
				String sr_id = map.get("src_sr_id").toString();//源节点
				String dst_sr_id = map.get("dst_sr_id").toString();//邻居节点
				
				//巡回线只描述一条
				boolean isExist = false;
				int simpleChain = 1;//单链
				float smoothness = 0.1f;//曲线弧度
				if (null != topologyList && !topologyList.isEmpty()) {
					for (Map<String, Object> temp : topologyList) {//来回节点只画一条曲线，双链的画两条
						if (temp.get("src_sr_id").equals(dst_sr_id)
								&& temp.get("dst_sr_id").equals(sr_id)) {
							isExist = true;
						}
						if (temp.get("src_sr_id").equals(sr_id)
								&& temp.get("dst_sr_id").equals(dst_sr_id)) {//多链
							temp.put("simpleChain", 0);
							simpleChain = 0;
							smoothness = (Float) temp.get("smoothness");
						}
					}
				}
				if (isExist) {
					continue;
				}
				if (0 == simpleChain) {//曲线弧度
					smoothness +=0.05;
				}
				
				Map<String, Object> topology = new HashMap<String, Object>();
				//源节点
				topology.put("sr_lng", map.get("sr_lng"));
				topology.put("sr_lat", map.get("sr_lat"));
				topology.put("src_sr_id", map.get("src_sr_id"));
				topology.put("src_sr_name", map.get("src_sr_name"));
				topology.put("level", map.get("level"));//节点类型
				
				//目的节点
				topology.put("dst_sr_lng", map.get("dst_sr_lng"));
				topology.put("dst_sr_lat", map.get("dst_sr_lat"));
				topology.put("dst_sr_id", map.get("dst_sr_id"));
				topology.put("dst_sr_name", map.get("dst_sr_name"));
				topology.put("src_iframe",map.get("src_iframe"));
				topology.put("dst_iframe",map.get("dst_iframe"));
				//曲线弧度
				topology.put("smoothness", smoothness);
				topology.put("simpleChain", simpleChain);
				topologyList.add(topology);
				//如果已配置过拓扑 则删除临时信息
				for(Map tempMap : tempSrlist){
					if(sr_id.equals(tempMap.get("sr_id")) || dst_sr_id.equals(tempMap.get("sr_id"))){
						dao.delete("topology.deleteNeighborsTemp", tempMap.get("sr_id"));
					}
				}
			}
		}
		
		//获取SR节点间的拓扑关系图(临时)
		List<Map<String, Object>> neighbortemplist = dao.getSearchList("topology.queryChinaTopologyTemp", params);
		if (null != neighbortemplist && !neighbortemplist.isEmpty()) {
			for (Map<String, Object> map : neighbortemplist) {
				String sr_id = map.get("src_sr_id").toString();//源节点
				String dst_sr_id = map.get("dst_sr_id").toString();//邻居节点
				
				//巡回线只描述一条
				boolean isExist = false;
				int simpleChain = 1;//单链
				float smoothness = 0.1f;//曲线弧度
				if (null != topologyList && !topologyList.isEmpty()) {
					for (Map<String, Object> temp : topologyList) {//来回节点只画一条曲线，双链的画两条
						if (temp.get("src_sr_id").equals(dst_sr_id)
								&& temp.get("dst_sr_id").equals(sr_id)) {
							isExist = true;
						}
						if (temp.get("src_sr_id").equals(sr_id)
								&& temp.get("dst_sr_id").equals(dst_sr_id)) {//多链
							temp.put("simpleChain", 0);
							simpleChain = 0;
							smoothness = (Float) temp.get("smoothness");
						}
					}
				}
				if (isExist) {
					continue;
				}
				if (0 == simpleChain) {//曲线弧度
					smoothness +=0.05;
				}
				
				Map<String, Object> topology = new HashMap<String, Object>();
				//源节点
				topology.put("sr_lng", map.get("sr_lng"));
				topology.put("sr_lat", map.get("sr_lat"));
				topology.put("src_sr_id", map.get("src_sr_id"));
				topology.put("src_sr_name", map.get("src_sr_name"));
				topology.put("level", map.get("level"));//节点类型
				
				//目的节点
				topology.put("dst_sr_lng", map.get("dst_sr_lng"));
				topology.put("dst_sr_lat", map.get("dst_sr_lat"));
				topology.put("dst_sr_id", map.get("dst_sr_id"));
				topology.put("dst_sr_name", map.get("dst_sr_name"));
				topology.put("src_iframe","available");
				topology.put("dst_iframe","available");
				//曲线弧度
				topology.put("smoothness", smoothness);
				topology.put("simpleChain", simpleChain);
				//临时拓扑标志
				topology.put("isTemp", true);
				
				topologyList.add(topology);
			}
		}
				
		
		//返回封装的数据
		return topologyList;
	}

	/**
	 * 保存TCP发来的SR节点的邻居信息
	 */
	@Override
	public void saveFromTCP(List<Map<String, Object>> params) {
//		LOGGER.info("保存TCP发来的SR节点的邻居信息");
		
		if (null != params && !params.isEmpty()) {
			String sr_id = params.get(0).get("sr_id").toString();
			
			//保存邻居表之前先删除，确保是最新的邻居数据
			dao.delete("topology.deleteNeighbors", sr_id);
			
			//批量更新邻居路由
			int i = dao.insert("topology.insertbatch", params);
			if (i > 0) {
//				LOGGER.info("更新SR节点邻居信息成功");
			}else {
				LOGGER.error("更新SR节点邻居信息失败");
			}
		}else {
			LOGGER.error("数据为空，无法保存路由信息");
		}
		
	}

	/**
	 * 获取网络参数（拓扑图使用）
	 */
	@Override
	public Map<String, Object> getNetworkData(String src_sr_id, String dst_sr_id) {
		Map<String, Object> returnMap = new HashMap<String, Object>();//返回值
		if (null == src_sr_id || "".equals(src_sr_id)) {
			returnMap.put("code", -1);
			returnMap.put("msg", "SR节点ID为空");
		}
		if (null != dst_sr_id && !"".equals(dst_sr_id)
				&& !"-1".equals(dst_sr_id)) {//获取节点间的丢包和延时
			Map<String, String> params = new HashMap<String, String>();//参数
			params.put("src_sr_id", src_sr_id);
			params.put("dst_sr_id", dst_sr_id);
			Map<String,Object> quality = dao.getOneInfo("qualityStatistics.getCurrentQuality", params);
			returnMap.put("lost_rate", (null ==quality || null == quality.get("lost_rate"))?0:quality.get("lost_rate"));//节点丢包率
			returnMap.put("average_delay", (null ==quality || null == quality.get("average_delay"))?0:quality.get("average_delay"));//节点平均延时
		}else {//获取SR节点吞吐量
			Map<String, Object> through = dao.getOneInfo("throughputStatistics.getTotalThrough", src_sr_id);
			returnMap.put("throughput", (null ==through || null == through.get("throughput"))?0:through.get("throughput"));//节点吐吞量
		}
		
		returnMap.put("code", 1);
		return returnMap;
	}

	/**
	 * 获取SR节点间的最短路径
	 */
	@Override
	public List<Map<String, Object>> getBestPath(String src_sr_id,String dst_sr_id) {
		boolean isFind = true;//是否继续查找
		RouteVO routeVO = new RouteVO();//查询参数
		List<Map<String, Object>> bestPath = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = null;//保存SR间的路径
		
		if (null == src_sr_id && "".equals(src_sr_id)
				&& null == dst_sr_id && "".equals(dst_sr_id)) {
			return null;
		}
		
		routeVO.setSr_id(src_sr_id);//源节点ID
		routeVO.setDest(dst_sr_id);//目的节点ID
		
		//查找最短路径
		while (isFind) {
			routeVO = dao.getOneInfo("route.getNextHop", routeVO);
			if (null != routeVO) {
				if (null != routeVO.getNext_hop()) {
					if (!dst_sr_id.equals(routeVO.getNext_hop())) {//下一跳地址不是目的节点，继续查找路由
						map = new HashMap<String, Object>();
						map.put("src_sr_id", routeVO.getSr_id());//源节点
						map.put("dst_sr_id", routeVO.getNext_hop());//目的节点
						bestPath.add(map);//加入最短路劲集合
						
						//查询条件变换,继续查找
						routeVO.setSr_id(routeVO.getNext_hop());
					}else {//查找到目的路由
						//将最后一段路径加入集合，然后查找结束
						map = new HashMap<String, Object>();
						map.put("src_sr_id", routeVO.getSr_id());//源节点
						map.put("dst_sr_id", routeVO.getNext_hop());//目的节点
						bestPath.add(map);//加入最短路劲集合
						
						isFind = false;
					}
				}else {//下一跳为空退出
					isFind = false;
				}
			}else {//不存在路由表路径，退出查询
				isFind = false;
			}
		}
		
		return bestPath;
	}

	/**
	 * 分页查询SR节点的邻居关系信息
	 */
	@Override
	public PageContainer query(Map<String, String> params) {
		return dao.getSearchPage("topology.query", "topology.queryCount", params);
	}

}
