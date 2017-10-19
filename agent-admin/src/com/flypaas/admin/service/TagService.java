package com.flypaas.admin.service;

import java.util.List;
import java.util.Map;

/**
 * 自定义标签业务
 * 
 * @author xiejiaan
 */
public interface TagService {

	/**
	 * 获取自定义标签数据，配置在tb_flypaas_params.param_type字段中
	 * 
	 * @param dictionaryType
	 *            字典类型，即tb_flypaas_params.param_type字段
	 * @param sqlParams
	 * @return
	 */
	List<Map<String, Object>> getTagDataForDictionary(String dictionaryType, Map<String, Object> sqlParams);

	/**
	 * 获取自定义标签数据，配置在tagMapper.xml中，sql语句可以使用money_rate、event_id_1017
	 * 
	 * @param sqlId
	 * @param sqlParams
	 * @return
	 */
	List<Map<String, Object>> getTagDataForSql(String sqlId, Map<String, Object> sqlParams);

	/**
	 * 判断当前角色是否对menuId有访问权限
	 * 
	 * @param menuId
	 * @return
	 */
	boolean isAuthority(int menuId);

}
