package com.flypaas.admin.service.data;

import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 信息管理-管理员中心-权限管理
 * 
 * @author xiejiaan
 */
public interface AuthorityService {

	/**
	 * 判断角色是否对url有访问权限<br/>
	 * 有权限的条件：1.没有配置菜单；2.分配了菜单，且菜单和角色状态是1
	 * 
	 * @param roleId
	 * @param url
	 * @return 当前选中的菜单
	 */
	Map<String, Object> isAuthority(int roleId, String url);

	/**
	 * 判断当前角色是否对menuId有访问权限
	 * 
	 * @param menuId
	 * @return
	 */
	boolean isAuthority(int menuId);

	/**
	 * 分页查询权限
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 获取权限资料
	 * 
	 * @param roleId
	 * @return
	 */
	Map<String, Object> getAuthority(Integer roleId);

	/**
	 * 保存管理权限，包括添加、修改
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> saveAuthority(Map<String, String> params);

	/**
	 * 修改状态：恢复、删除
	 * 
	 * @param roleId
	 * @param status
	 * @return
	 */
	Map<String, Object> updateStatus(int roleId, int status);

}
