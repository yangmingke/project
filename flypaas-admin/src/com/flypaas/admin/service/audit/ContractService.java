package com.flypaas.admin.service.audit;

import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 审核管理-协议用户审核
 * 
 * @author zenglb
 */
public interface ContractService {

	/**
	 * 分页查询协议用户
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 查看协议用户
	 * 
	 * @param sid
	 * @return
	 */
	Map<String, Object> view(String sid);

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

	/**
	 *　设置协议用户
	 * @param formData
	 * @return
	 */
	Map<String, Object> setContract(Map<String, String> params);
}
