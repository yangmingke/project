package com.network.monitor.service.route;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.network.monitor.dao.StatDao;

/**
 * 路由表信息管理Service接口实现
 *
 */
@Service
@Transactional
public class RouteServiceImpl implements RouteService{

	private static final Logger logger = LoggerFactory.getLogger(RouteServiceImpl.class);
	
	@Autowired
	private StatDao dao;
	
	/**
	 * 查找SR路由表信息
	 */
	@Override
	public List<Map<String, Object>> getRoutes(String sr_id) {
		logger.debug("查找SR路由表信息：" + sr_id);
		
		return dao.getSearchList("node.getRoutes", sr_id);
	}

	/**
	 *  将接收到的路由报文信息入库
	 */
	@Override
	public void saveRoutes(List<Map<String, Object>> params) {
//		logger.info("将接收到的路由报文信息入库");
		
		if (null != params && !params.isEmpty()) {
			    int count=0;
			for (int i = 0; i < params.size(); i++) {
				
				String sr_id = params.get(i).get("sr_id").toString();
				String next_hop=params.get(i).get("next_hop").toString();
				String dest=params.get(i).get("dest").toString();
				
				//路由表保存最新的一份数据 先删除在插入
				Map<String,Object> param=new HashMap<String,Object>();
				param.put("sr_id", sr_id);
				param.put("next_hop", next_hop);
				param.put("dest",dest);
				logger.debug("   sr_id   "+sr_id  +"  dest "+dest);
				List<Map<String, Object>> list = dao.getSearchList("route.getRoutes", param);
				
				logger.debug(" list的长度  "+list.size());
				if (null !=list && !list.isEmpty()) {
					count++;
					logger.debug("   我执行了单条删除操作  ");
					dao.delete("route.deleteRoutes", param);//删除
				}
			}
			//如果上传的都在库中   则整个删除
//			if(count==params.size()){
//				Map<String,Object> param=new HashMap<String,Object>();
//				param.put("sr_id", params.get(0).get("sr_id").toString());
//				dao.delete("route.deleteAllRoutes",param);//删除
//			}
			int i = dao.insert("route.insertbatch", params);//批量更新
			if (i > 0) {
//				logger.info("更新路由成功");
			}else {
				logger.error("删除原来的路由信息失败，无法更新路由");
			}
		}
		
	}

}
