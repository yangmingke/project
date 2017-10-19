package com.flypaas.daocenter;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.flypaas.entity.vo.PageContainer;
/**
 * MyBatis公用dao类 统计库
 * 
 */
@Repository
public class StatisticsMyBatisDao {

	protected SqlSessionTemplate sqlSessionTemplate;
	@Resource(name="sqlSessionTemplateStatistics")
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	/**
	 * 查询List
	 * 
	 * @param queryStr
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getSearchList(String queryStr, Object params) {
		List<Map<String, Object>> list = sqlSessionTemplate.selectList(queryStr, params);
		return list;
	}

	/**
	 * 计算列表总数
	 * 
	 * @param countStr
	 * @param params
	 * @return
	 */
	public int getSearchSize(String countStr, Object params) {
		int totalCount = 0;
		List<Map<String, Object>> list = sqlSessionTemplate.selectList(countStr, params);
		if (list.size() > 0) {
			totalCount = Integer.parseInt(ObjectUtils.toString(list.get(0).get("totalCount")));
		}
		return totalCount;
	}

	/**
	 * 分页查询
	 * 
	 * @param queryStr
	 * @param countStr
	 * @param params
	 * @return
	 */
	public PageContainer getSearchPage(String queryStr, String countStr, PageContainer pageContainer) {
		PageContainer p = new PageContainer();
		Map<String, Object> params = pageContainer.getParams();
		int totalCount = getSearchSize(countStr, params);
		p.setTotalCount(totalCount);
		p.setPageRowCount(pageContainer.getPageRowCount());
		if (totalCount > 0) {
			int totalPage = totalCount / p.getPageRowCount() + (totalCount % p.getPageRowCount() == 0 ? 0 : 1);
			p.setTotalPage(totalPage);

			String currentPageS = pageContainer.getCurrentPage() + "";
			if (NumberUtils.isDigits(currentPageS)) {
				int currentPage = Integer.parseInt(currentPageS);
				if (currentPage > 0 && currentPage <= totalPage) {
					p.setCurrentPage(currentPage);
				}
			}

			int startRow = (p.getCurrentPage() - 1) * p.getPageRowCount(); // 分页开始行号
			int rows = p.getPageRowCount();// 分页返回行数
			params.put("limit", "LIMIT " + startRow + ", " + rows);

			List<Map<String, Object>> list = getSearchList(queryStr, params);
			for (Map<String, Object> map : list) {
				map.put("rownum", ++startRow);
			}
			p.setList(list);
		}
		return p;
	}

	/**
	 * 插入SQL执行
	 * 
	 * @param insertStr
	 * @param params
	 * @return
	 */
	public int insert(String insertStr, Object params) {
		return sqlSessionTemplate.insert(insertStr, params);
	}

	/**
	 * 更新SQL执行
	 * 
	 * @param updateStr
	 * @param params
	 * @return
	 */
	public int update(String updateStr, Object params) {
		return sqlSessionTemplate.update(updateStr, params);
	}

	/**
	 * 删除SQL执行
	 * 
	 * @param updateStr
	 * @param params
	 * @return
	 */
	public int delete(String updateStr, Object params) {
		return sqlSessionTemplate.delete(updateStr, params);
	}

	/**
	 * 根据条件返回单个对象
	 * 
	 * @param queryStr
	 * @param params
	 * @return
	 */
	public Map<String, Object> getOneInfo(String queryStr, Object params) {
		return sqlSessionTemplate.selectOne(queryStr, params);
	}
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
}
