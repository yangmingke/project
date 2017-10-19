package com.network.monitor.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 普通图表信息
 * 
 * @author xiejiaan
 */
public class Highcharts implements Cloneable {
	private static final Logger logger = LoggerFactory.getLogger(Highcharts.class);

	private String title;// 标题
	private String subtitle;// 副标题
	private String xTitle = "时间";// x轴的标题
	private String yTitle;// y轴的标题
	private String xUnit;// x轴的单位
	private String yUnit;// y轴的单位
	private Long xMin;// x轴的最小值（时间戳）
	private Long yMin = 0L;// y轴的最小值，默认是0
	private Long xMax;// x轴的最大值（时间戳）
	private Long yMax;// y轴的最大值
	private Long interval;// 相邻点的间隔时间（毫秒），用于补全曲线
	private String xKey;// x轴数据的key
	private List<Map<String, Object>> seriesList = new ArrayList<Map<String, Object>>();// 曲线
	private List<Map<String, Object>> dataList;// 图表数据

	/**
	 * 添加曲线，默认显示，默认值是0
	 * 
	 * @param name
	 *            名称
	 * @param yKey
	 *            y轴数据的key
	 */
	public void addSeries(String name, String yKey) {
		addSeries(name, yKey, true, 0);
	}

	/**
	 * 添加曲线，默认显示，默认值是null
	 * 
	 * @param name
	 *            名称
	 * @param yKey
	 *            y轴数据的key
	 */
	public void addSeriesDefaultNull(String name, String yKey) {
		addSeries(name, yKey, true, null);
	}

	/**
	 * 添加曲线
	 * 
	 * @param name
	 *            名称
	 * @param yKey
	 *            y轴数据的key
	 * @param showSeries
	 *            是否默认显示
	 * @param defaultValue
	 *            默认值
	 */
	public void addSeries(String name, String yKey, boolean showSeries, Object defaultValue) {
		Map<String, Object> series = new HashMap<String, Object>();
		series.put("name", name);
		series.put("yKey", yKey);
		series.put("showSeries", String.valueOf(showSeries));
		series.put("defaultValue", defaultValue);
		seriesList.add(series);
	}

	public Highcharts clone() {
		try {
			return (Highcharts) super.clone();
		} catch (CloneNotSupportedException e) {
			logger.error("克隆Highcharts对象失败：title=" + this.title, e);
		}
		return null;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getxTitle() {
		return xTitle;
	}

	public void setxTitle(String xTitle) {
		this.xTitle = xTitle;
	}

	public String getyTitle() {
		return yTitle;
	}

	public void setyTitle(String yTitle) {
		this.yTitle = yTitle;
	}

	public String getxUnit() {
		return xUnit;
	}

	public void setxUnit(String xUnit) {
		this.xUnit = xUnit;
	}

	public String getyUnit() {
		return yUnit;
	}

	public void setyUnit(String yUnit) {
		this.yUnit = yUnit;
	}

	public Long getxMin() {
		return xMin;
	}

	public void setxMin(Long xMin) {
		this.xMin = xMin;
	}

	public Long getyMin() {
		return yMin;
	}

	public void setyMin(Long yMin) {
		this.yMin = yMin;
	}

	public Long getxMax() {
		return xMax;
	}

	public void setxMax(Long xMax) {
		this.xMax = xMax;
	}

	public Long getyMax() {
		return yMax;
	}

	public void setyMax(Long yMax) {
		this.yMax = yMax;
	}

	public Long getInterval() {
		return interval;
	}

	public void setInterval(Long interval) {
		this.interval = interval;
	}

	public String getxKey() {
		return xKey;
	}

	public void setxKey(String xKey) {
		this.xKey = xKey;
	}

	public List<Map<String, Object>> getSeriesList() {
		return seriesList;
	}

	public void setSeriesList(List<Map<String, Object>> seriesList) {
		this.seriesList = seriesList;
	}

	public List<Map<String, Object>> getDataList() {
		return dataList;
	}

	public void setDataList(List<Map<String, Object>> dataList) {
		this.dataList = dataList;
	}

}
