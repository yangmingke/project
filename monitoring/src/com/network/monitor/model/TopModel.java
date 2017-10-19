package com.network.monitor.model;

import java.util.List;
import java.util.Map;

public class TopModel {

	private String year;
	private String month;
	private List<Map<String, Object>> dataList;
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public List<Map<String, Object>> getDataList() {
		return dataList;
	}
	public void setDataList(List<Map<String, Object>> dataList) {
		this.dataList = dataList;
	}
	
	
}
