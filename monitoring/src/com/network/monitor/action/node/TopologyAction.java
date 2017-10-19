package com.network.monitor.action.node;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.network.monitor.action.BaseAction;
import com.network.monitor.service.node.TopologyService;
import com.network.monitor.util.web.StrutsUtils;

/**
 * 网络拓扑图Action
 *
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "view", location = "/WEB-INF/content/topology/view.jsp"),
			@Result(name = "viewworld", location = "/WEB-INF/content/topology/view_world.jsp"),
			@Result(name = "query", location = "/WEB-INF/content/topology/query.jsp")})
public class TopologyAction extends BaseAction{

	@Autowired
	private TopologyService topologyService;
	
	private String src_sr_id;//源节点
	
	private String dst_sr_id;//目的节点
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5058499984009729593L;
	
	/**
	 * 分页查询SR节点邻居表
	 */
	@Action("/neighbors/query")
	public String query(){
		page = topologyService.query(StrutsUtils.getFormData());
		return "query";
	}
	
	@Action("/network/topology")
	public String view(){
		Map<String,String> params = StrutsUtils.getFormData();

		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c=Calendar.getInstance();
		String startTime = params.get("start_time");
		String endTime = df.format(c.getTime());
		c.add(Calendar.MINUTE, -1);//1分钟前
		startTime = df.format(c.getTime());
		data = new HashMap<String, Object>();
		data.put("start_time", startTime);
		data.put("end_time", endTime);
		params.put("end_time", endTime);
		return "view";
	}
	
	@Action("/network/topologyworld")
	public String viewWorld(){
		Map<String,String> params = StrutsUtils.getFormData();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c=Calendar.getInstance();
		String startTime = params.get("start_time");
		String endTime = df.format(c.getTime());
		c.add(Calendar.MINUTE, -1);//1分钟前
		startTime = df.format(c.getTime());
		data = new HashMap<String, Object>();
		data.put("start_time", startTime);
		data.put("end_time", endTime);
		params.put("end_time", endTime);
		return "viewworld";
	}

	/**
	 * 获取SR节点拓扑关系图
	 */
	@Action("/network/getTopology")
	public void getTopology(){
		data = new HashMap<String, Object>();
		List<Map<String, Object>> topologyList = topologyService.getTopology(StrutsUtils.getFormData());//获取SR节点和拓扑结构
		if (null != topologyList && !topologyList.isEmpty()) {
			data.put("topologyList", topologyList);
			data.put("code", 1);
			data.put("msg", "SR节点拓扑图获取成功");
		}else {
			data = new HashMap<String, Object>();
			data.put("code", -1);
			data.put("msg", "SR节点拓扑图为空");
		}
		StrutsUtils.renderJson(data);
	}
	
	/**
	 * 获取SR节点拓扑关系图
	 */
	@Action("/network/getChinaTopology")
	public void getChinaTopology(){
		data = new HashMap<String, Object>();
		//获取中国SR节点和拓扑结构
		List<Map<String, Object>> topologyList = topologyService.getChinaTopology(StrutsUtils.getFormData());		
		if (null != topologyList && !topologyList.isEmpty()) {
			data.put("topologyList", topologyList);
			data.put("code", 1);
			data.put("msg", "SR节点拓扑图获取成功");
		}else {
			data = new HashMap<String, Object>();
			data.put("code", -1);
			data.put("msg", "SR节点拓扑图为空");
		}
		StrutsUtils.renderJson(data);
	}
	
	/**
	 * 获取网络参数（拓扑图点击查看）
	 */
	@Action("/network/getNetworkData")
	public void getNetworkData(){
		data = new HashMap<String, Object>();
		if (null == src_sr_id || "".equals(src_sr_id)) {
			data.put("code", -1);
			data.put("msg", "SR节点ID为空");
		}else {
			data = topologyService.getNetworkData(src_sr_id, dst_sr_id);
		}
		
		StrutsUtils.renderJson(data);
	}

	/**
	 * 获取SR节点间的最短路径
	 */
	@Action("/network/getBestPath")
	public void getBestPath(){
		data = new HashMap<String, Object>();
		
		if (null == src_sr_id && "".equals(src_sr_id)
				&& null == dst_sr_id && "".equals(dst_sr_id)) {
			data.put("code", -1);
			data.put("msg", "源节点或者目的节点ID为空,无法查询最短路径");
		}
		
		List<Map<String, Object>> bestPath = topologyService.getBestPath(src_sr_id, dst_sr_id);
		if (null != bestPath && !bestPath.isEmpty()) {
			data.put("code", 1);
			data.put("bestPath", bestPath);
		}else {
			data.put("code", -1);
			data.put("msg", "源节点到目的节点不存在最短路径");
		}
		
		StrutsUtils.renderJson(data);
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
}
