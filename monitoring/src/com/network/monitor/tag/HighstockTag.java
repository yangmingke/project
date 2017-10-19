package com.network.monitor.tag;

import java.util.HashMap;
import java.util.Map;

import com.network.monitor.model.Highstock;
import com.network.monitor.service.TagService;
import com.network.monitor.util.JsonUtils;

/**
 * 股票图表标签
 * 
 * @author xiejiaan
 */
public class HighstockTag extends HighchartsTag {
	private static final long serialVersionUID = -3868951789092300437L;

	private Highstock highstock;// 股票图表信息
	private String id;// html的id标记
	private String onLoad;// 图表加载完之后的回调函数

	@Override
	public String getTemplateFile() {
		return "highstock.ftl";
	}

	@Override
	public Map<String, Object> getTemplateParams(TagService tagService) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("onLoad", onLoad);
		params.put("chart", highstock);
		params.put("chartInfo", JsonUtils.toJson(highstock));
		return params;
	}

	public Highstock getHighstock() {
		return highstock;
	}

	public void setHighstock(Highstock highstock) {
		this.highstock = highstock;
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
