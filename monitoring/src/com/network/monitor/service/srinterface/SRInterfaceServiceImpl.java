package com.network.monitor.service.srinterface;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.network.monitor.dao.StatDao;
import com.network.monitor.model.PageContainer;
import com.network.monitor.service.node.NodeServiceImpl;

/**
 * SR节点接口和IP管理
 *
 */
@Service
@Transactional
public class SRInterfaceServiceImpl implements SRInterfaceService {

	private static final Logger LOGGER = LoggerFactory.getLogger(NodeServiceImpl.class);
	
	@Autowired
	private StatDao dao;
	
	/**
	 * 保存TCP发来的SR节点的接口和IP信息
	 */
	public void saveFromTCP(List<Map<String, Object>> params){
//		LOGGER.info("保存TCP发来的SR节点的接口信息");
		
		if (null != params && !params.isEmpty()) {
			String sr_id = params.get(0).get("sr_id").toString();
			
			//保存SR节点的接口信息之前先删除，确保是最新的SR节点的接口信息
			dao.delete("interface.deleteInterfaces", sr_id);
			
			//批量更新SR接口信息
			int i = dao.insert("interface.insertInterfaces", params);
			if (i > 0) {
//				LOGGER.info("更新SR节点接口信息成功");
			}else {
				LOGGER.error("更新SR节点接口信息失败");
			}
		}else {
			LOGGER.error("数据为空，无法保存接口信息");
		}
	}

	/**
	 * 分页查询SR节点的接口信息
	 */
	@Override
	public PageContainer query(Map<String, String> params) {
		return dao.getSearchPage("interface.query", "interface.queryCount", params);
	}
}
