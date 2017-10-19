package com.flypaas.admin.service.cps;

import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 策略管理-音频驱动适配-音频驱动智能适配
 * 
 * @author xiejiaan
 */
public interface VoiceDeviceAutoService {

	/**
	 * 查询音频驱动智能适配
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 查看音频驱动智能适配
	 * 
	 * @param id
	 * @return
	 */
	Map<String, Object> view(int id);

	/**
	 * 保存音频驱动智能适配，包括添加、修改
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> save(Map<String, String> params);

	/**
	 * 删除音频驱动智能适配
	 * 
	 * @param id
	 * @return
	 */
	Map<String, Object> delete(int id);

}
