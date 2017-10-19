package com.network.monitor.action;

import java.util.List;
import java.util.Map;

import com.network.monitor.model.PageContainer;
import com.network.monitor.util.encrypt.Des3Utils;
import com.network.monitor.util.rest.utils.JsonUtil;
import com.network.monitor.util.web.StrutsUtils;
import com.opensymphony.xwork2.ActionSupport;

/**
 * action类的基类
 * 
 * @author xiejiaan
 */
public class BaseAction extends ActionSupport {
	private static final long serialVersionUID = -6024322463400978622L;

	/**
	 * 分页信息
	 */
	protected PageContainer page;

	/**
	 * 返回数据
	 */
	protected Map<String, Object> data;
	
	/**
	 * 返回数据
	 */
	protected List<Map<String, Object>> dataList;

	/**
	 * 请求页面，用于返回刷新
	 */
	private String referer;
	
	/**
	 * 导出Excel的sql
	 */
	protected String excelSqlParams;

	public PageContainer getPage() {
		return page;
	}

	public void setPage(PageContainer page) {
		this.page = page;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public String getReferer() {
		referer = StrutsUtils.getReferer();
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public List<Map<String, Object>> getDataList() {
		return dataList;
	}

	public void setDataList(List<Map<String, Object>> dataList) {
		this.dataList = dataList;
	}

	public String getExcelSqlParams() {
		return excelSqlParams;
	}

	public void setExcelSqlParams(String excelSqlParams) {
		this.excelSqlParams = excelSqlParams;
	}
	public String buildSql(Map<String, String> map){
		if(map==null){
			return null;
		}
		String str = JsonUtil.toJsonStr(map);
		return Des3Utils.encodeDes3(str) ;
	}
}
