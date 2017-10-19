package com.network.monitor.action.statistics;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.network.monitor.action.BaseAction;
import com.network.monitor.model.Highstock;
import com.network.monitor.service.statistics.QualityStatisticsService;
import com.network.monitor.service.statistics.ThroughputStatisticsService;
import com.network.monitor.util.UcpaasDateUtils;
import com.network.monitor.util.web.StrutsUtils;

/**
 * SR节点间吞吞吐量数据统计管理Action
 *
 */
@Controller
@Scope("prototype")
@Results({
		@Result(name = "view", location = "/WEB-INF/content/throughput/view.jsp"),
		@Result(name = "viewDiagrm", location = "/WEB-INF/content/throughput/throughput_diagram.jsp"),
		@Result(name = "show", location = "/WEB-INF/content/throughput/show.jsp"),
		@Result(name = "showworld", location = "/WEB-INF/content/throughput/showworld.jsp"),
        @Result(name="route",location="/WEB-INF/content/throughput/route.jsp")		
})
public class ThroughputStatisticsAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -484907734526825345L;

	@Autowired
	private ThroughputStatisticsService throughputStatisticsService;

	@Autowired
	private QualityStatisticsService qualityStatisticsService;// SR节点质量统计Service
	private String src_sr_id;// 发起SR节点ID

	private String dst_sr_id;// 目的SR节点ID

	private String start_time;// 开始时间

	private String end_time;// 结束时间

	/**
	 * SR节点间吞吐量饼状图（目前不实现）
	 * 
	 * @return
	 */
	@Action("/throughput/view")
	public String view() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		end_time = df.format(c.getTime());
		c.add(Calendar.MINUTE, -5);// 1分钟前
		start_time = df.format(c.getTime());
		// 输出参数到页面
		data = new HashMap<String, Object>();
		data.put("start_time", start_time);
		data.put("end_time", end_time);

		return "view";
	}

	/**
	 * 饼状图数据（目前不实现）
	 */
	@Action("/throughput/pieData")
	public void DrawThroughputPie() {
		Map<String, String> params = new HashMap<String, String>();
		String opDateTmp = new SimpleDateFormat("yyyyMM").format(new Date());
		params.put("opDate", opDateTmp);
		if (null == start_time || null == end_time) {// 默认查询前5分钟
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar c = Calendar.getInstance();
			end_time = df.format(c.getTime());
			c.add(Calendar.MINUTE, -5);// 1分钟前
			start_time = df.format(c.getTime());
		}
		// 查询时间
		params.put("start_time", start_time);
		params.put("end_time", end_time);
		if (null != dst_sr_id && !"".equals(dst_sr_id)) {// 查询单个SR节点间的吞吐量
			params.put("dst_sr_id", dst_sr_id);
		}
		if (null != src_sr_id && !"".equals(src_sr_id)) {// 确认监控SR节点才可监控数据
			params.put("sr_id", src_sr_id);
			data = throughputStatisticsService.queryThroughput(params);
		} else {// 未确定监控目标不显示
			data = new HashMap<String, Object>();
			data.put("code", 0);
		}
		StrutsUtils.renderJson(data);
	}

	/**
	 * SR节点间的吞吐量曲线图
	 */
	@Action("/throughput/viewDiagrm")
	public String viewThroughputDiagrm() {
		// 获取节点间的吐吞量数据
		Map<String, String> params = StrutsUtils.getFormData();
		String opDateTmp = new SimpleDateFormat("yyyyMM").format(new Date());
		params.put("opDate", opDateTmp);
		String startTime = params.get("start_time");
		String endTime = params.get("end_time");
		if (null == startTime || null == endTime) {// 默认查询一天的数据
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar c = Calendar.getInstance();
			endTime = df.format(c.getTime());
			c.add(Calendar.MINUTE, -1);// 1分钟前
			startTime = df.format(c.getTime());
		}

		// 定义曲线
		Highstock chart = new Highstock();
		chart.setTitle("SR节点吞吐量曲线图");
		chart.setyTitle("吞吐量(byte)");
		chart.setyUnit("byte");
		chart.setxKey("time1");
		chart.setIsFloat(false);
		chart.setRangeSelectorEnable(false);
		chart.setxMin(UcpaasDateUtils.parseDate(startTime,
				"yyyy-MM-dd HH:mm:ss").getMillis());
		chart.setxMax(UcpaasDateUtils.parseDate(endTime, "yyyy-MM-dd HH:mm:ss")
				.getMillis());

		// 加载曲线数据
		if (null != params.get("sr_id") && !"".equals(params.get("sr_id"))
				&& null != params.get("dst_sr_id")
				&& !"".equals(params.get("dst_sr_id"))) {// 确定监控点再查询
			Map<String, List<Map<String, Object>>> data = throughputStatisticsService
					.queryList(params);
			chart.setDataMap(data);
			chart.setChangeData(true);
			for (Entry<String, List<Map<String, Object>>> entry : data
					.entrySet()) {
				chart.addSeries(entry.getKey().toString(), "y");
			}
		} else {
			chart.setDataList(null);
		}
		// 查找监控点
		List<Map<String, Object>> map = qualityStatisticsService
				.query_monitor();

		data = new HashMap<String, Object>();
		data.put("chart", chart);
		data.put("start_time", startTime);
		data.put("end_time", endTime);
		data.put("map", map);
		return "viewDiagrm";
	}

	/**
	 * SR节点间的吞吐量曲线图(实时)
	 */
	@Action("/throughput/realviewThroughput")
	public void realviewThroughputDiagrm() {
		// 获取节点间的吐吞量数据
		Map<String, String> params = StrutsUtils.getFormData();
		String opDateTmp = new SimpleDateFormat("yyyyMM").format(new Date());
		params.put("opDate", opDateTmp);

		// 查询当前一分钟数据
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		String endTime = df.format(c.getTime());
		String startTime = params.get("start_time");
		params.put("end_time", endTime);

		// 定义曲线
		Highstock chart = new Highstock();
		chart.setTitle("SR节点吞吐量曲线图");
		chart.setyTitle("吞吐量(byte)");
		chart.setyUnit("byte");
		chart.setxKey("time1");
		chart.setIsFloat(false);
		chart.setRangeSelectorEnable(false);
		chart.setxMin(UcpaasDateUtils.parseDate(startTime,
				"yyyy-MM-dd HH:mm:ss").getMillis());
		chart.setxMax(UcpaasDateUtils.parseDate(endTime, "yyyy-MM-dd HH:mm:ss")
				.getMillis());

		// 加载曲线数据
		if (null != params.get("sr_id") && !"".equals(params.get("sr_id"))
				&& null != params.get("dst_sr_id")
				&& !"".equals(params.get("dst_sr_id"))) {// 确定监控点再查询
			Map<String, List<Map<String, Object>>> data = throughputStatisticsService
					.queryList(params);
			chart.setDataMap(data);
			chart.setChangeData(true);
			for (Entry<String, List<Map<String, Object>>> entry : data
					.entrySet()) {
				chart.addSeries(entry.getKey().toString(), "y");
			}
		} else {
			chart.setDataList(null);
		}

		data = new HashMap<String, Object>();
		data.put("chart", chart);
		data.put("start_time", startTime);
		data.put("end_time", endTime);
		StrutsUtils.renderJson(data);
	}

	/**
	 * 流量图
	 */
	@Action("/throughput/show")
	public String diagram() {
		Map<String, String> params = StrutsUtils.getFormData();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		// String startTime = params.get("start_time");
		// String endTime = params.get("end_time");
		String startTime = null;
		String endTime = null;
		if (null == endTime) {
			endTime = df.format(c.getTime());
		}
		if (null == startTime) {// 默认查询前1分钟
			c.add(Calendar.MINUTE, -1);// 1分钟前
			startTime = df.format(c.getTime());
		}
		// 刷新页面的时间间隔
		int time = 60;
		if (!("".equals(params.get("time")) || null == params.get("time"))) {
			time = Integer.parseInt(params.get("time"));
		}

		data = new HashMap<String, Object>();
		List<Map<String, Object>> map = qualityStatisticsService
				.query_monitor();
		data.put("map", map);
		data.put("start_time", startTime);
		data.put("end_time", endTime);
		data.put("time", time);
		return "show";
	}
	
	/**
	 * 流量图
	 */
	@Action("/throughput/showworld")
	public String diagramworld() {
		Map<String, String> params = StrutsUtils.getFormData();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		// String startTime = params.get("start_time");
		// String endTime = params.get("end_time");
		String startTime = null;
		String endTime = null;
		if (null == endTime) {
			endTime = df.format(c.getTime());
		}
		if (null == startTime) {// 默认查询前1分钟
			c.add(Calendar.MINUTE, -1);// 1分钟前
			startTime = df.format(c.getTime());
		}
		// 刷新页面的时间间隔
		int time = 60;
		if (!("".equals(params.get("time")) || null == params.get("time"))) {
			time = Integer.parseInt(params.get("time"));
		}

		data = new HashMap<String, Object>();
		List<Map<String, Object>> map = qualityStatisticsService
				.query_monitor();
		data.put("map", map);
		data.put("start_time", startTime);
		data.put("end_time", endTime);
		data.put("time", time);
		return "showworld";
	}

	/**
	 * SR流量图
	 */
	@Action("/throughput/drawThroughput")
	public void drawThroughput() {
		data = new HashMap<String, Object>();
		List<Map<String, Object>> throughputList = throughputStatisticsService
				.drawThroughputNew(StrutsUtils.getFormData());
		if (null != throughputList && !throughputList.isEmpty()) {
			data.put("code", 1);
			data.put("throughputList", throughputList);
			data.put("msg", "SR节点流量图获取成功");
		} else {
			data = new HashMap<String, Object>();
			data.put("code", -1);
			data.put("msg", "SR节点流量数据为空");
		}
		StrutsUtils.renderJson(data);
	}
	
	/**
	 * SR流量图China
	 */
	@Action("/throughput/drawThroughputChina")
	public void drawThroughputChina() {
		data = new HashMap<String, Object>();
		List<Map<String, Object>> throughputList = throughputStatisticsService.drawThroughputChinaNew(StrutsUtils.getFormData());
		if (null != throughputList && !throughputList.isEmpty()) {
			data.put("code", 1);
			data.put("throughputList", throughputList);
			data.put("msg", "SR节点流量图获取成功");
		} else {
			data = new HashMap<String, Object>();
			data.put("code", -1);
			data.put("msg", "SR节点流量数据为空");
		}
		StrutsUtils.renderJson(data);
	}

	/**
	 * 路由监控
	 */

	@Action("/throughput/route")
	public String routeMonitor(){
		if(StrutsUtils.getFormData().size()>0){
		data = new HashMap<String, Object>();
		List<Map<String, Object>> list=throughputStatisticsService
				.routeMonitor(StrutsUtils.getFormData());
		data.put("list",list);
		}
		return "route";
		
	}
	/**
	 * 根据源节点来获取源节点ip
	 * 
	 */
	@Action("/throughput/srIp")
	public void srIp(){
		if(StrutsUtils.getFormData().size()>0){
		List<Map<String,Object>> list=throughputStatisticsService.srIp(StrutsUtils.getFormData());
		StrutsUtils.renderJson(list);
		}
	}
	/**
	 * 
	 * 根据目的节点来获取目的节点ip
	 */
	@Action("/throughput/dstIp")
	public void dstIp(){
		if(StrutsUtils.getFormData().size()>0){
			List<Map<String,Object>> list=throughputStatisticsService.dstIp(StrutsUtils.getFormData());
			StrutsUtils.renderJson(list);			
		}
	}
	
	public String getSrc_sr_id() {
		return src_sr_id;
	}

	public void setSrc_sr_id(String src_sr_id) {
		this.src_sr_id = src_sr_id;
	}

	public String getDst_sr_id() {
		return dst_sr_id;
	}

	public void setDst_sr_id(String dst_sr_id) {
		this.dst_sr_id = dst_sr_id;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
}
