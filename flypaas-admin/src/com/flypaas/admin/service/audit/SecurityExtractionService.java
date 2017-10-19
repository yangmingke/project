package com.flypaas.admin.service.audit;

import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 审核管理-保障金提现审核
 * 
 * @author zenglb
 */
public interface SecurityExtractionService {

	/**
	 * 分页查询
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 查看
	 * 
	 * @param id
	 * @return
	 */
	Map<String, Object> view(Long id);

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
