package com.flypaas.admin.service.audit;

import java.io.File;
import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 审核管理-发票审核
 * 
 * @author zenglb
 */
public interface InvoiceAuditService {

	/**
	 * 分页查询发票
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 查看发票
	 * 
	 * @param sid
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
	 * 邮寄
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> postBill(Map<String, String> params);

	/**
	 * 保存补充原因
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> saveReason(Map<String, String> params);

	/**
	 *加载开发者基本信息
	 * @param sid
	 * @return
	 */
	Map<String, Object> loadUserInfo(String sid);

	/**
	 * 保存数据
	 * @param params
	 * @return
	 */
	Map<String, Object> save(File f,String path,String fileName,Map<String, String> params);
}
