package com.flypaas.admin.service.account;

import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 计费类型
 * 
 * @author yangmingke
 */
public interface FeeTypeService {

	/**
	 * 分页查询计费类型
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 增加计费项目
	 * @param formData
	 * @return
	 */
	Map<String, Object> addFeeTypeItem(Map<String, String> formData);

	/**
	 * 更新计费项目
	 * @param formData
	 * @return
	 */
	Map<String, Object> updateFeeTypeItem(Map<String, String> formData);

	/**
	 * 删除计费项目
	 * @param formData
	 * @return
	 */
	Map<String, Object> delFeeTypeItem(Map<String, String> formData);

}
