package com.network.monitor.util.rest.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel文件信息
 * 
 * @author xiejiaan
 */
public class Excel {
	private String filePath;// 导出的文件路径
	private String title;// 标题
	private List<String> remarkList = new ArrayList<String>();// 备注
	private List<Map<String, String>> headerList = new ArrayList<Map<String, String>>();// 表头：name、width、key
	private List<Map<String, Object>> dataList;// 导出的数据
	private int pageRowCount = 60000;// 每页显示行数，默认是60000
	private boolean showPage = true;// 是否显示分页信息，默认是true
	private boolean showRownum = true;// 是否显示序号，默认是true

	/**
	 * 添加备注
	 * 
	 * @param remark
	 *            备注
	 */
	public void addRemark(String remark) {
		remarkList.add(remark);
	}

	/**
	 * 添加表头
	 * 
	 * @param width
	 *            列宽
	 * @param name
	 *            名称
	 * @param key
	 *            数据的key
	 */
	public void addHeader(int width, String name, String key) {
		Map<String, String> header = new HashMap<String, String>();
		header.put("width", String.valueOf(width));
		header.put("name", name);
		header.put("key", key);
		headerList.add(header);
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getRemarkList() {
		return remarkList;
	}

	public void setRemarkList(List<String> remarkList) {
		this.remarkList = remarkList;
	}

	public List<Map<String, String>> getHeaderList() {
		return headerList;
	}

	public void setHeaderList(List<Map<String, String>> headerList) {
		this.headerList = headerList;
	}

	public List<Map<String, Object>> getDataList() {
		return dataList;
	}

	public void setDataList(List<Map<String, Object>> dataList) {
		this.dataList = dataList;
	}

	public int getPageRowCount() {
		return pageRowCount;
	}

	public void setPageRowCount(int pageRowCount) {
		this.pageRowCount = pageRowCount;
	}

	public boolean isShowPage() {
		return showPage;
	}

	public void setShowPage(boolean showPage) {
		this.showPage = showPage;
	}

	public boolean isShowRownum() {
		return showRownum;
	}

	public void setShowRownum(boolean showRownum) {
		this.showRownum = showRownum;
	}

}
