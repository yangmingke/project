package com.flypaas.admin.service.data;

import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 信息管理-渠道管理
 * 
 * @author zenglb
 */
public interface ChannelService {

	/**
	 * 分页查询
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 修改状态
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> updateStatus(Map<String, String> params);

	/**
	 * 保存
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> save(Map<String, String> params);

	/**
	 * 明细页面
	 * 
	 * @param id
	 * @return
	 */
	Map<String, Object> view(Integer id);

}
