package com.network.monitor.action.node;

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
import com.network.monitor.service.node.NodeService;
import com.network.monitor.util.web.StrutsUtils;

/**
 * SR节点管理
 *
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "view", location = "/WEB-INF/content/node/view.jsp"),
			@Result(name = "add", location = "/WEB-INF/content/node/add.jsp")})
public class NodeAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6595141164814935833L;
	
	@Autowired
	private NodeService nodeService;
	
	private String sr_id;//SR节点ID
	
	private String provinceId;//省份ID
	
	private String countryId;//国家ID
	/**
	 * 查询SR节点信息
	 */
	@Action("/node/view")
	public String view() {
		page = nodeService.query(StrutsUtils.getFormData());
		return "view";
	}
	
	/**
	 * 配置SR节点信息页面
	 * @return
	 */
	@Action("/node/add")
	public String add() {
		return "add";
	}
	
	/**
	 * 确认配置SR节点信息
	 */
	@Action("/node/confirmNodeInfo")
	public void confirmNodeInfo(){
		data = nodeService.confirmNodeInfo(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	/**
	 * 获取SR节点信息
	 */
	@Action("/node/getNodeInfo")
	public void getNodeInfo(){
		data = nodeService.getNodeInfo(sr_id);
		StrutsUtils.renderJson(data);
	}
	
	/**
	 * 删除SR节点配置信息
	 */
	@Action("/node/deleteNodeInfo")
	public void deleteNodeInfo(){
		data = nodeService.deleteNodeInfo(sr_id);
		StrutsUtils.renderJson(data);
	}
	/**
	 * 获取国家所有省份
	 */
	@Action("/node/getProvinces")
	public void getProvinces(){
			data = new HashMap<String, Object>();
			if (null == countryId && "".equals(countryId)) {
				data.put("code", -1);
				data.put("msg", "国家ID为空，无法获取省份");
			}
			List<Map<String, Object>> list= nodeService.getProvinces(countryId);
			if (null != list && !list.isEmpty()) {
				data.put("code", 1);
				data.put("msg", "获取省份成功");
				data.put("provinces", list);
			}else {
				data.put("code", -1);
				data.put("msg", "获取省份为空");
			}
			StrutsUtils.renderJson(data);
		
	}
	
	/**
	 * 获取省份所有城市
	 */
	@Action("/node/getCitys")
	public void getCitys(){
		data = new HashMap<String, Object>();
		if (null == provinceId && "".equals(provinceId)) {
			data.put("code", -1);
			data.put("msg", "省份ID为空，无法获取省市");
		}
		List<Map<String, Object>> list= nodeService.getCitys(provinceId);
		if (null != list && !list.isEmpty()) {
			data.put("code", 1);
			data.put("msg", "获取省市成功");
			data.put("cities", list);
		}else {
			data.put("code", -1);
			data.put("msg", "获取省市为空");
		}
		StrutsUtils.renderJson(data);
	}
	
	/**
	 * 获取所有SR节点
	 */
	@Action("/node/getNodeList")
	public void getNodeList(){
		data = new HashMap<String, Object>();
		List<Map<String, Object>> list= nodeService.getNodeList();
		if (null != list && !list.isEmpty()) {
			data.put("code", 1);
			data.put("msg", "获取所有SR节点成功");
			data.put("nodeList", list);
		}else {
			data.put("code", -1);
			data.put("msg", "SR节点为空");
		}
		StrutsUtils.renderJson(data);
	}
	
	/**
	 * 获取中国SR节点
	 */
	@Action("/node/getChinaNodeList")
	public void getChinaNodeList(){
		data = new HashMap<String, Object>();
		List<Map<String, Object>> list= nodeService.getChinaNodeList();
		if (null != list && !list.isEmpty()) {
			data.put("code", 1);
			data.put("msg", "获取所有SR节点成功");
			data.put("nodeList", list);
		}else {
			data.put("code", -1);
			data.put("msg", "SR节点为空");
		}
		StrutsUtils.renderJson(data);
	}
	
	/**
	 * 
	 * 批量删除SR节点
	 */
	@Action("/node/deleteBatch")
	public void deleteBatch(){
		nodeService.deleteBatch(StrutsUtils.getFormData());
	}
	
	
	public String getSr_id() {
		return sr_id;
	}

	public void setSr_id(String sr_id) {
		this.sr_id = sr_id;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
}
