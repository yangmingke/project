package com.flypaas.admin.service.cps;

import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 策略管理-音频驱动适配-音频驱动适配异常
 * 
 * @author xiejiaan
 */
public interface VoiceDeviceExceptionService {

	/**
	 * 查询音频驱动适配异常
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 查看音频驱动适配异常
	 * 
	 * @param id
	 * @return
	 */
	Map<String, Object> view(int id);

	/**
	 * 删除音频驱动适配异常
	 * 
	 * @param id
	 * @return
	 */
	Map<String, Object> delete(int id);
}
