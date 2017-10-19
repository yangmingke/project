package com.flypaas.admin.service.audit;

import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 审核管理-短信模板审核
 * 
 * @author xiejiaan
 */
public interface SmsAuditService {

	/**
	 * 分页查询短信模板
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 查看短信模板
	 * 
	 * @param templateId
	 * @return
	 */
	Map<String, Object> view(long templateId);

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
