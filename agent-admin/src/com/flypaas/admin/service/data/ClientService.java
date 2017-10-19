package com.flypaas.admin.service.data;

import java.util.Map;

import com.flypaas.admin.model.PageContainer;
public interface ClientService {
	
	/**
	 *
	 *@author   chengxu
	 *查询主库client 相关
	 * 
	 * 
	 */
	PageContainer query(Map<String, String> params);
	PageContainer query2(Map<String, String> params);
	PageContainer query3(Map<String, String> params);
	PageContainer query4(Map<String, String> params);
	Map<String, Object> getClient(Map<String, String> params);
	Map<String, Object> getClientbill(Map<String, String> params);
	Map<String, Object> getChargebill(Map<String, String> params);
	Map<String, Object> getChargeview(Map<String, String> params);
	
}
