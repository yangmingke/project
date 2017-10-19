package com.network.monitor.tag;

import java.util.HashMap;
import java.util.Map;

import com.network.monitor.model.Highcharts;
import com.network.monitor.service.TagService;
import com.network.monitor.util.JsonUtils;

/**
 * 普通图表标签
 * 
 * @author xiejiaan
 */
public class HighchartsTag extends BaseTag {
	private static final long serialVersionUID = 7227471429498403124L;

	private Highcharts highcharts;// 普通图表信息
	private String id;// html的id标记
	private String onLoad;// 图表加载完之后的回调函数

	@Override
	public String getTemplateFile() {
		return "highcharts.ftl";
	}

	@Override
	public Map<String, Object> getTemplateParams(TagService tagService) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("onLoad", onLoad);
		params.put("chart", highcharts);
		params.put("chartInfo", JsonUtils.toJson(highcharts));
		return params;
	}

	public Highcharts getHighcharts() {
		return highcharts;
	}

	public void setHighcharts(Highcharts highcharts) {
		this.highcharts = highcharts;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOnLoad() {
		return onLoad;
	}

	public void setOnLoad(String onLoad) {
		this.onLoad = onLoad;
	}

}
