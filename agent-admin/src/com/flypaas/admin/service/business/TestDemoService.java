package com.flypaas.admin.service.business;

import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 业务管理-测试DEMO
 * 
 * @author xiejiaan
 */
public interface TestDemoService {

	/**
	 * 分页查询测试DEMO
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 查看测试DEMO
	 * 
	 * @param appSid
	 * @return
	 */
	Map<String, Object> view(String appSid);

}
