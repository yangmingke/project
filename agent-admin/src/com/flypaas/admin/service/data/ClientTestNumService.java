package com.flypaas.admin.service.data;

import java.util.HashMap;
import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 信息管理-测试号码
 * 
 * @author zenglb
 */
public interface ClientTestNumService {

	/**
	 * 分页查询测试号码
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, Object> params);

	/**
	 * 绑定测试号码
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> bindTestNum(HashMap<String, Object> params);
}
