package com.flypaas.admin.service.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.constant.SysConstant;
import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.model.PageContainer;

/**
 * 业务管理-测试DEMO
 * 
 * @author xiejiaan
 */
@Service
@Transactional
public class TestDemoServiceImpl implements TestDemoService {
	@Autowired
	private MasterDao dao;

	@Override
	public PageContainer query(Map<String, String> params) {
		params.put("money_rate", SysConstant.money_rate);
		return dao.getSearchPage("testDemo.query", "testDemo.queryCount", params);
	}

	@Override
	public Map<String, Object> view(String appSid) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("app_sid", appSid);
		params.put("money_rate", SysConstant.money_rate);
		List<Map<String, Object>> list = dao.getSearchList("testDemo.getDemo", params);// Demo和Rest-URL
		if (list.size() == 0) {
			return null;
		}
		Map<String, Object> data = list.get(0);
		for (Map<String, Object> map : list) {
			if ("1".equals(map.get("rest_param_key"))) {
				data.put("rest_ip", map.get("rest_param_value"));
			}
			data.put("rest_port", map.get("rest_param_value"));
		}

		list = dao.getSearchList("testDemo.getAppAllClient", appSid);// 获取应用的所有client
		data.put("client_list", list);
		return data;
	}
}
