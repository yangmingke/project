package com.network.monitor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 分页查询容器
 * 
 * @author xiejiaan
 */
public class PageContainer implements Serializable {
	private static final long serialVersionUID = 8976331697912229579L;

	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();// 查询结果
	private Integer currentPage = 1; // 当前页号
	private Integer totalPage = 1; // 总页数
	private Integer totalCount = 0;// 总行数
	private Integer pageRowCount = 30; // 每页显示行数

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageRowCount() {
		return pageRowCount;
	}

	public void setPageRowCount(Integer pageRowCount) {
		this.pageRowCount = pageRowCount;
	}

}
