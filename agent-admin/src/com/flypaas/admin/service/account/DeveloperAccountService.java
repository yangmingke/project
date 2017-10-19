package com.flypaas.admin.service.account;

import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 账务管理-开发者账务
 * 
 * @author xiejiaan
 */
public interface DeveloperAccountService {

	/**
	 * 分页查询开发者账务
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 查看开发者账务
	 * 
	 * @param sid
	 *            开发者sid
	 * @param rechargeParams
	 *            充值记录分页参数
	 * @param consumptionParams
	 *            消费日志分页参数
	 * @return
	 */
	Map<String, Object> view(String sid, Map<String, String> rechargeParams, Map<String, String> consumptionParams);

	/**
	 * 状态变更
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> updateEnableFlag(String sid, Map<String, String> params);

	/**
	 * 状态变更
	 * 
	 * @param params
	 * @return
	 */
	int updateEnableFlagDao(Map<String, String> params);

	/**
	 * 余额变更
	 * 
	 * @param sid
	 * @param formData
	 * @return
	 */
	Map<String, Object> updateBalance(String sid, Map<String, String> params);

	/**
	 * 设置信用额度
	 * @param sid
	 * @param formData
	 * @return
	 */
	Map<String, Object> saveCreditBalance(Map<String, String> params);

}
