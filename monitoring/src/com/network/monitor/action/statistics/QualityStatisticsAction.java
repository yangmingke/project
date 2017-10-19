package com.network.monitor.action.statistics;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.network.monitor.service.node.NodeService;
import com.network.monitor.service.statistics.QualityStatisticsService;
import com.network.monitor.util.web.StrutsUtils;
import com.network.monitor.model.Highstock;
import com.network.monitor.util.UcpaasDateUtils;

/**
 * SR节点间质量统计管理Action
 *
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "view", location = "/WEB-INF/content/statistics/quality_view.jsp"),
			@Result(name = "lost", location = "/WEB-INF/content/statistics/lost_diagram.jsp"),
			@Result(name = "delay", location = "/WEB-INF/content/statistics/delay_diagram.jsp"),
			@Result(name = "diagram", location = "/WEB-INF/content/statistics/view.jsp"),
			@Result(name = "diagramworld", location = "/WEB-INF/content/statistics/viewworld.jsp")})
public class QualityStatisticsAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7965107287235806191L;
	
	@Autowired
	private NodeService nodeService;//SR节点Sercive
	
	@Autowired
	private QualityStatisticsService qualityStatisticsService;//SR节点质量统计Service
	
	/**
	 * SR节点质量统计
	 */
	@Action("/quality/view")
	public String view() {
		Map<String,String> params = StrutsUtils.getFormData();
		String opDateTmp = new SimpleDateFormat("yyyyMM").format(new Date());
		params.put("opDate", opDateTmp);
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c=Calendar.getInstance();
		String startTime = params.get("start_time");
		String endTime = params.get("end_time");
		if (null == endTime) {
			endTime = df.format(c.getTime());
		}
		if (null == startTime) {//默认查询前1分钟
			c.add(Calendar.MINUTE, -1);//1分钟前
			startTime = df.format(c.getTime());
		}
		data = new HashMap<String, Object>();
		//查找监控点
		List<Map<String,Object>> map=qualityStatisticsService.query_monitor();
		data.put("map",map);
		data.put("start_time", startTime);
		data.put("end_time", endTime);
		
		if (null != params.get("sr_id") && !"".equals(params.get("sr_id"))
				&& null != params.get("dst_sr_id") && !"".equals(params.get("dst_sr_id"))) {//确定监控点再查询
			page = qualityStatisticsService.query(params);
		}
		
		return "view";
	}

	/**
	 * 获取需要监控的SR节点
	 */
	@Action("/quality/getMonitorNodes")
	public void getMonitorNodes(){
		List<Map<String, Object>> nodes = nodeService.queryNode();//获取监控的SR节点
		List<Map<String, Object>> monitorNodes = null;//封装监控的SR节点
		if (null != nodes && !nodes.isEmpty()) {
			monitorNodes = new ArrayList<Map<String,Object>>();
			for (Map<String, Object> map : nodes) {
				Map<String, Object> mapTemp = new HashMap<String, Object>();
				mapTemp.put("value", map.get("sr_id"));
				mapTemp.put("text", map.get("sr_name"));
				monitorNodes.add(mapTemp);
			}
			data = new HashMap<String, Object>();
			data.put("monitorNodes", monitorNodes);
			data.put("code", 1);
			StrutsUtils.renderJson(data);
		}
	}
	
	/**
	 * SR节点间丢包曲线图
	 */
	@Action("/packet/lost")
	public String packetLossDiagram(){
		
		//获取丢包率数据
		Map<String,String> params = StrutsUtils.getFormData();
		String opDateTmp = new SimpleDateFormat("yyyyMM").format(new Date());
		params.put("opDate", opDateTmp);
		String startTime = params.get("start_time");
		String endTime = params.get("end_time");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c=Calendar.getInstance();
		if (null == endTime) {
			endTime = df.format(c.getTime());
		}
		if (null == startTime) {//默认查询一天的数据
			c.add(Calendar.MINUTE, -1);//1分钟前
			startTime = df.format(c.getTime());
		}
		
		//定义曲线
		Highstock chart = new Highstock();
		chart.setTitle("SR节点丢包曲线图");
		chart.setyTitle("百分比(%)");
		chart.setyUnit("%");
		chart.setxKey("time1");
		chart.setIsFloat(false);
		chart.setRangeSelectorEnable(false);
		chart.setxMin(UcpaasDateUtils.parseDate(startTime, "yyyy-MM-dd HH:mm:ss").getMillis());
		chart.setxMax(UcpaasDateUtils.parseDate(endTime, "yyyy-MM-dd HH:mm:ss").getMillis());
				
		//加载曲线数据
		if (null != params.get("sr_id") && !"".equals(params.get("sr_id"))
				&& null != params.get("dst_sr_id") && !"".equals(params.get("dst_sr_id"))) {//确定监控点再查询
			Map<String, List<Map<String, Object>>> data = qualityStatisticsService.queryLostRate(params);
			chart.setDataMap(data);
			chart.setChangeData(true);
			for (Entry<String, List<Map<String, Object>>> entry : data.entrySet()) {
				chart.addSeries(entry.getKey().toString(), "y");
			}
		}else {
			chart.setDataList(null);
		}
		//查找监控点
		List<Map<String,Object>> map=qualityStatisticsService.query_monitor();
		
		data = new HashMap<String, Object>();
		data.put("chart", chart);
		data.put("start_time", startTime);
		data.put("end_time", endTime);
		data.put("map",map);
		return "lost";
	}
	
	/**
	 * SR节点间丢包曲线图(实时)
	 */
	@Action("/packet/realLost")
	public void realLost(){
		
		//获取丢包率数据
		Map<String,String> params = StrutsUtils.getFormData();
		String opDateTmp = new SimpleDateFormat("yyyyMM").format(new Date());
		params.put("opDate", opDateTmp);
		
		//查询当前一分钟数据
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c=Calendar.getInstance();
		String endTime = df.format(c.getTime());
		String startTime = params.get("start_time").toString();
		params.put("end_time", endTime);
				
		//定义曲线
		Highstock chart = new Highstock();
		chart.setTitle("SR节点丢包曲线图");
		chart.setyTitle("百分比(%)");
		chart.setyUnit("%");
		chart.setxKey("time1");
		chart.setIsFloat(false);
		chart.setRangeSelectorEnable(false);
		chart.setxMin(UcpaasDateUtils.parseDate(startTime, "yyyy-MM-dd HH:mm:ss").getMillis());
		chart.setxMax(UcpaasDateUtils.parseDate(endTime, "yyyy-MM-dd HH:mm:ss").getMillis());
						
		//加载曲线数据
		if (null != params.get("sr_id") && !"".equals(params.get("sr_id"))
				&& null != params.get("dst_sr_id") && !"".equals(params.get("dst_sr_id"))) {//确定监控点再查询
				Map<String, List<Map<String, Object>>> data = qualityStatisticsService.queryLostRate(params);
				chart.setDataMap(data);
				chart.setChangeData(true);
				for (Entry<String, List<Map<String, Object>>> entry : data.entrySet()) {
					chart.addSeries(entry.getKey().toString(), "y");
				}
		}else {
			chart.setDataList(null);
		}
		
		data = new HashMap<String, Object>();
		data.put("chart", chart);
		data.put("start_time", startTime);
		data.put("end_time", endTime);
		StrutsUtils.renderJson(data);
	}
	
	/**
	 * SR节点间延时曲线图
	 */
	@Action("/packet/delay")
	public String packetDelayDiagram(){
		
		//获取丢包率数据
		Map<String,String> params = StrutsUtils.getFormData();
		String opDateTmp = new SimpleDateFormat("yyyyMM").format(new Date());
		params.put("opDate", opDateTmp);
		String startTime = params.get("start_time");
		String endTime = params.get("end_time");
		if (null == startTime || null == endTime) {//默认查询一分钟的数据
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar c=Calendar.getInstance();
			endTime = df.format(c.getTime());
			c.add(Calendar.MINUTE, -1);//1分钟前
			startTime = df.format(c.getTime());
		}
		
		//定义曲线
		Highstock chart = new Highstock();
		chart.setTitle("SR节点间延时曲线图");
		chart.setyTitle("延时时间");
		chart.setyUnit("ms");
		chart.setxKey("time1");
		chart.setIsFloat(false);
		chart.setRangeSelectorEnable(false);
		chart.setxMin(UcpaasDateUtils.parseDate(startTime, "yyyy-MM-dd HH:mm:ss").getMillis());
		chart.setxMax(UcpaasDateUtils.parseDate(endTime, "yyyy-MM-dd HH:mm:ss").getMillis());
		
		//加载曲线数据
		if (null != params.get("sr_id") && !"".equals(params.get("sr_id"))
				&& null != params.get("dst_sr_id") && !"".equals(params.get("dst_sr_id"))) {//确定监控点再查询
			Map<String, List<Map<String, Object>>> data = qualityStatisticsService.queryDelay(params);
			chart.setDataMap(data);
			chart.setChangeData(true);
			for (Entry<String, List<Map<String, Object>>> entry : data.entrySet()) {
				chart.addSeries(entry.getKey().toString(), "y");
			}
		}else {
			chart.setDataList(null);
		}
		//查找监控点
				List<Map<String,Object>> map=qualityStatisticsService.query_monitor();
		data = new HashMap<String, Object>();
		data.put("chart", chart);
		data.put("start_time", startTime);
		data.put("end_time", endTime);
		data.put("map", map);
		return "delay";
	}
	
	/**
	 * SR节点间延时曲线图(实时)
	 */
	@Action("/packet/realDelay")
	public void realDelay(){
		
		//获取丢包率数据
		Map<String,String> params = StrutsUtils.getFormData();
		String opDateTmp = new SimpleDateFormat("yyyyMM").format(new Date());
		params.put("opDate", opDateTmp);
		
		//查询当前一分钟数据
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c=Calendar.getInstance();
		String endTime = df.format(c.getTime());
		String startTime = params.get("start_time").toString();
		params.put("end_time", endTime);
		
		//定义曲线
		Highstock chart = new Highstock();
		chart.setTitle("SR节点间延时曲线图");
		chart.setyTitle("延时时间");
		chart.setyUnit("ms");
		chart.setxKey("time1");
		chart.setIsFloat(false);
		chart.setRangeSelectorEnable(false);
		chart.setxMin(UcpaasDateUtils.parseDate(startTime, "yyyy-MM-dd HH:mm:ss").getMillis());
		chart.setxMax(UcpaasDateUtils.parseDate(endTime, "yyyy-MM-dd HH:mm:ss").getMillis());
				
		//加载曲线数据
		if (null != params.get("sr_id") && !"".equals(params.get("sr_id"))
				&& null != params.get("dst_sr_id") && !"".equals(params.get("dst_sr_id"))) {//确定监控点再查询
				Map<String, List<Map<String, Object>>> data = qualityStatisticsService.queryDelay(params);
				chart.setDataMap(data);
				chart.setChangeData(true);
				for (Entry<String, List<Map<String, Object>>> entry : data.entrySet()) {
					chart.addSeries(entry.getKey().toString(), "y");
				}
		}else {
			chart.setDataList(null);
		}
				
		data = new HashMap<String, Object>();
		data.put("chart", chart);
		data.put("start_time", startTime);
		data.put("end_time", endTime);
		StrutsUtils.renderJson(data);
	}
	
	/**
	 * 链路质量图
	 */
	@Action("/link/quality")
	public String diagram(){
		Map<String,String> params = StrutsUtils.getFormData();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c=Calendar.getInstance();
//		String startTime = params.get("start_time");
//		String endTime = params.get("end_time");
		String startTime =null;
		String endTime =null;
		if (null == endTime) {
			endTime = df.format(c.getTime());
		}
		if (null == startTime) {//默认查询前1分钟
			c.add(Calendar.MINUTE, -1);//1分钟前
			startTime = df.format(c.getTime());
		}
		//刷新页面的时间间隔
		int time =60;
		if(!("".equals(params.get("time"))||null==params.get("time"))){
		   time=Integer.parseInt(params.get("time"));
		}
		data = new HashMap<String, Object>();
		data = new HashMap<String, Object>();
		List<Map<String,Object>> map=qualityStatisticsService.query_monitor();
		data.put("map",map);
		data.put("start_time", startTime);
		data.put("end_time", endTime);
		data.put("time", time);
		return "diagram";
	}
	
	/**
	 * 链路质量图
	 */
	@Action("/link/qualityworld")
	public String diagramworld(){
		Map<String,String> params = StrutsUtils.getFormData();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c=Calendar.getInstance();
//		String startTime = params.get("start_time");
//		String endTime = params.get("end_time");
		String startTime =null;
		String endTime =null;
		if (null == endTime) {
			endTime = df.format(c.getTime());
		}
		if (null == startTime) {//默认查询前1分钟
			c.add(Calendar.MINUTE, -1);//1分钟前
			startTime = df.format(c.getTime());
		}
		//刷新页面的时间间隔
		int time =60;
		if(!("".equals(params.get("time"))||null==params.get("time"))){
		   time=Integer.parseInt(params.get("time"));
		}
		data = new HashMap<String, Object>();
		data = new HashMap<String, Object>();
		List<Map<String,Object>> map=qualityStatisticsService.query_monitor();
		data.put("map",map);
		data.put("start_time", startTime);
		data.put("end_time", endTime);
		data.put("time", time);
		return "diagramworld";
	}
	
	/**
	 * 获取链路质量
	 */
	@Action("/link/drawQuality")
	public void drawQuality(){
		data = new HashMap<String, Object>();
		List<Map<String, Object>> qualityList = qualityStatisticsService.drawQualityNew(StrutsUtils.getFormData());
		if (null != qualityList && !qualityList.isEmpty()) {
			data.put("code", 1);
			data.put("qualityList", qualityList);
			data.put("msg", "SR节点链路图获取成功");
		}else {
			data.put("code", -1);
			data.put("msg", "SR节点链路数据为空");
		}
		StrutsUtils.renderJson(data);
	}
	
	/**
	 * 获取链路质量China
	 */
	@Action("/link/drawQualityChina")
	public void drawQualityChina(){
		data = new HashMap<String, Object>();
		List<Map<String, Object>> qualityList = qualityStatisticsService.drawQualityChina(StrutsUtils.getFormData());
		if (null != qualityList && !qualityList.isEmpty()) {
			data.put("code", 1);
			data.put("qualityList", qualityList);
			data.put("msg", "SR节点链路图获取成功");
		}else {
			data.put("code", -1);
			data.put("msg", "SR节点链路数据为空");
		}
		StrutsUtils.renderJson(data);
	}
	
	
	/**
	 * 获取监控目标
	 */
	@Action("/link/dst_sr_id")
	public void getDst_sr_id(){
		List<Map<String, Object>> list = qualityStatisticsService.getDst_sr_id(StrutsUtils.getFormData());	
		StrutsUtils.renderJson(list);
	}
}
