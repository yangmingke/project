package com.network.monitor.service;

import java.util.Map;

/**
 * 菜单业务
 * 
 * @author xiejiaan
 */
public interface MenuService {

	/**
	 * 获取页面顶部1、2级菜单
	 * 
	 * @return
	 */
	Map<String, Object> getHeaderMenu();
}
