package com.flypaas.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页查询容器
 * 
 * @author yangmingke
 */
@SuppressWarnings("rawtypes")
public class PageContainer implements Serializable {
	private static final long serialVersionUID = 8976331697912229579L;

	private Integer currentPage = 1; // 当前页号
	private Integer totalPage = 1; // 总页数
	private Integer totalCount = 0;// 总行数
	private Integer pageRowCount = 10; // 默认每页显示行数
	private Integer[] pageRowArray = { 10, 30, 50, 100 };// 每页显示行数下拉框
	private List<Object> result = new ArrayList<Object>();//页面显示内容
	private List<Map<String, String>> resultMap = new ArrayList<Map<String, String>>();
	private Map parameter = new HashMap();//请求参数
	private Map rsMap = new HashMap();//Map结果
	private List<Integer> pageList = new ArrayList<Integer>();  //显示的可选条页 
	

	public List<Integer> getPageList() {
		return pageList;
	}

	public void setPageList(List<Integer> pageList) {
		this.pageList = pageList;
	}
	
	public Map<String,Object> createParameter(Map<String,Object> para){
		int page = (int) para.get("page");
		int total = (int) para.get("total");
		
		return createParameter(para, page, total);
	}

	public Map<String,Object> createParameter(Map<String,Object> para,int page,int total){
		Integer pageRowCount = (Integer) para.get("pageRowCount");
		if(pageRowCount != null){
			this.pageRowCount = pageRowCount;
		}
		this.parameter = para;
		this.currentPage = page;
		this.totalPage = (total-1) / this.pageRowCount + 1;
		this.totalCount = total;
		int begin = (page - 1) * this.pageRowCount;
		int count = this.pageRowCount;
		if(currentPage  >= totalPage){
			count = this.totalCount - (totalPage - 1) * this.pageRowCount;
		}
		//添加可选当页前三页
		for(int i = 3; i > 0; i--){
			int tempPage = this.currentPage - i;
			if(tempPage > 0){
				pageList.add(tempPage);
			}
		}
		pageList.add(currentPage);//添加可选当页
		//添加可选当页后三页
		for(int i = 0; i < 3; i++){
			int tempPage = this.currentPage + i + 1;
			if(tempPage <= this.totalPage){
				pageList.add(tempPage);
			}else{
				break;
			}
		}
		para.put("count", count);
		para.put("begin", begin);
		return para;
	}
	
	public Map<String,Integer> createRedisParameter(int page,int total){
		Map<String, Object> para = createParameter(new HashMap<String,Object>(), page, total);
		
		Map<String,Integer> redisPara = new HashMap<String,Integer>();
		int begin = (int)para.get("begin");
		int count = (int)para.get("count");
		
		redisPara.put("begin", begin);
		redisPara.put("end", begin + count);
		
		return redisPara;
		
	}
	
	public List<Map<String, String>> getResultMap() {
		return resultMap;
	}

	public void setResultMap(List<Map<String, String>> resultMap) {
		this.resultMap = resultMap;
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

	public Integer[] getPageRowArray() {
		return pageRowArray;
	}

	public void setPageRowArray(Integer[] pageRowArray) {
		this.pageRowArray = pageRowArray;
	}

	public List<Object> getResult() {
		return result;
	}

	public void setResult(List<Object> result) {
		this.result = result;
	}

	public Map getParameter() {
		return parameter;
	}

	public void setParameter(Map parameter) {
		this.parameter = parameter;
	}

	public Map getRsMap() {
		return rsMap;
	}

	public void setRsMap(Map rsMap) {
		this.rsMap = rsMap;
	}




}
