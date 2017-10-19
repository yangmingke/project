package com.flypaas.admin.service.audit;

import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 审核管理-号码白名单
 * 
 * @author zenglb
 */
public interface ShowNbrsAuditService {

	/**
	 * 分页查询应用
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 审核
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> audit(Map<String, String> params);

	/**
	 * 保存补充原因
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> saveReason(Map<String, String> params);
}
