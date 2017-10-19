package com.flypaas.admin.service.account;

import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 账务管理-账单信息
 * 
 * @author zenglb
 */
public interface BillService {

	Map<String, Object> getBill(Map<String, String> params);

	PageContainer query(Map<String, String> params);

	Map<String, Object> updateCloseOrder(String sid, Map<String, String> params);
}
