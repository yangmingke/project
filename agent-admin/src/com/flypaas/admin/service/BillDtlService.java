package com.flypaas.admin.service;

import java.util.Map;

/**
 * 充值，扣费，赠送操作
 * 
 * @author zenglb
 */
public interface BillDtlService {
	/**
	 * 充值
	 * 
	 * @param params
	 * @return
	 */
	int recharge(Map<String, String> params);

	/**
	 * 赠送
	 * 
	 * @param params
	 * @return
	 */
	int gifts(Map<String, String> params);

	/**
	 * 扣费
	 * 
	 * @param params
	 * @return
	 */
	int deduction(Map<String, String> params);
}
