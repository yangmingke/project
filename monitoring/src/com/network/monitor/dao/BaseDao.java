package com.network.monitor.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.mybatis.spring.SqlSessionTemplate;

import com.network.monitor.model.PageContainer;
import com.network.monitor.model.TaskInfo;

/**
 * dao类的基类
 * 
 * @author xiejiaan
 */
public abstract class BaseDao {
	protected SqlSessionTemplate sqlSessionTemplate;

	/**
	 * 设置SqlSessionTemplate
	 * 
	 * @param sqlSessionTemplate
	 */
	protected abstract void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate);

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	/**
	 * 查询List
	 * 
	 * @param queryStr
	 * @param params
	 * @return
	 */
	public <T> List<T> selectList(String queryStr, Object params) {
		return sqlSessionTemplate.selectList(queryStr, params);
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
			totalCount = Integer.parseInt(list.get(0).get("totalCount").toString());
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageContainer getSearchPage(String queryStr, String countStr, Map params) {
		PageContainer p = new PageContainer();
		if(params.containsKey("pageRowCount")){
			Object pageRowCount = params.get("pageRowCount");
			if(pageRowCount!=null&&!pageRowCount.equals("")){
				p.setPageRowCount(Integer.parseInt(pageRowCount.toString()));
			}
		}
		int totalCount = getSearchSize(countStr, params);
		p.setTotalCount(totalCount);
		if (totalCount > 0) {
			String pageRowCountS = params.get("pageRowCount") != null ? params.get("pageRowCount").toString() : null;
			if (NumberUtils.isDigits(pageRowCountS)) {
				int pageRowCount = Integer.parseInt(pageRowCountS);
				if (pageRowCount > 0) {
					p.setPageRowCount(pageRowCount);
				}
			}
			int totalPage = totalCount / p.getPageRowCount() + (totalCount % p.getPageRowCount() == 0 ? 0 : 1);
			p.setTotalPage(totalPage);

			String currentPageS = params.get("currentPage") != null ? params.get("currentPage").toString() : null;
			if (NumberUtils.isDigits(currentPageS)) {
				int currentPage = Integer.parseInt(currentPageS);
				if (currentPage > 0 && currentPage <= totalPage) {
					p.setCurrentPage(currentPage);
				}
			}
			
			Object orderName = params.get("orderName");
			orderName = orderName==null?"":" order by "+orderName;
			int startRow = (p.getCurrentPage() - 1) * p.getPageRowCount(); // 分页开始行号
			int rows = p.getPageRowCount();// 分页返回行数
			params.put("limit", orderName+" LIMIT " + startRow + ", " + rows);

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
	public <T> T getOneInfo(String queryStr, Object params) {
		return sqlSessionTemplate.selectOne(queryStr, params);
	}

	/**
	 * 调用存储过程
	 * 
	 * @param taskInfo
	 * @return 是否成功
	 */
	public boolean callProcedure(TaskInfo taskInfo) {
		String procedureName = taskInfo.getProcedureName();
		String executePrev = taskInfo.getExecutePrev();
		if (executePrev == null) {
			executePrev = "0";
		}
		String sql = "{call " + procedureName + "(?, ?)}";
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = sqlSessionTemplate.getSqlSessionFactory().openSession().getConnection();
			cs = conn.prepareCall(sql);
			cs.setLong(1, Long.parseLong(executePrev));
			cs.registerOutParameter(2, Types.INTEGER); // 注册出参
			cs.execute();
			int result = cs.getInt(2);
			return true;

		} catch (Throwable e) {
			return false;
		} finally {
			try {
				if (cs != null) {
					cs.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
			}
		}
	}
}
