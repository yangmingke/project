package com.flypaas.admin.service.data;

import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 信息管理-管理员中心-敏感字管理
 * 
 * @author xiejiaan
 */
public interface KeywordService {

	/**
	 * 查询敏感字
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 查看敏感字
	 * 
	 * @param wordId
	 * @return
	 */
	Map<String, Object> view(int wordId);

	/**
	 * 保存敏感字，包括添加、修改
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> save(Map<String, String> params);

	/**
	 * 删除敏感字
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> delete(Map<String, String> params);

}
