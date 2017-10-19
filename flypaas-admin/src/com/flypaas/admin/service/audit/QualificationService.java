package com.flypaas.admin.service.audit;

import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 审核管理-资质审核
 * 
 * @author xiejiaan
 */
public interface QualificationService {

	/**
	 * 分页查询资质
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 查看资质
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
}
