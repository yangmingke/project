package com.flypaas.admin.service.business;

import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 业务管理-语音
 * 
 * @author xiejiaan
 */
public interface VoiceService {

	/**
	 * 分页查询语音
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 查看语音
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> view(Map<String, String> params);

}
