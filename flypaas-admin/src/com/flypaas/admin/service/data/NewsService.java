package com.flypaas.admin.service.data;

import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 信息管理-管理员中心-新闻管理
 * 
 * @author xiejiaan
 */
public interface NewsService {

	/**
	 * 查询新闻
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 查看新闻
	 * 
	 * @param newsId
	 * @return
	 */
	Map<String, Object> view(int newsId);

	/**
	 * 保存新闻，包括添加、修改
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> save(Map<String, String> params);

	/**
	 * 修改新闻状态：关闭、启用、删除
	 * 
	 * @param newsId
	 * @param status
	 * @return
	 */
	Map<String, Object> updateStatus(int newsId, int status);

}
