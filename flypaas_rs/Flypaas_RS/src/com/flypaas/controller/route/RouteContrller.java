package com.flypaas.controller.route;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.flypaas.constant.RouterConstant;
import com.flypaas.model.TbRsAccountInfo;
import com.flypaas.service.ResourceSideService;
import com.flypaas.service.route.RouteService;
import com.flypaas.util.PageContainer;
import com.flypaas.util.StrUtil;

@Controller
@RequestMapping("/route")
@SuppressWarnings("unchecked")
public class RouteContrller{
	
	public static Logger logger = Logger.getLogger(RouteContrller.class);
	@Autowired
	RouteService routeServiceImpl;
	@Autowired
	private ResourceSideService ResourceSideServiceImpl;
	
	@RequestMapping("/queryDomain")
	public ModelAndView queryDomain(String concurrent,String currentPage){

		int page = currentPage == null ? 1 : Integer.valueOf(currentPage);
		if(StrUtil.isEmpty(concurrent)){
			concurrent = RouterConstant.ROUTE_DEFAULT_KEY;
		}
		//redis获取数据分页
		PageContainer pageContainer = routeServiceImpl.getIpList(concurrent,page);
		
		
		Map<String,Object> model = new HashMap<String,Object>();
		//redis获取所有域名
		Set<String> routeKeyList = routeServiceImpl.getDomainList();
		
		model.put("page", pageContainer);
		model.put("routeKeyList", routeKeyList);
		model.put("concurrent", concurrent);
		
		return new ModelAndView("jsp/route/domain",model);
	}
	
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping("/queryResourceById")
	public ModelAndView queryResourceById(String ip, String routeDomain){
		Map resource = routeServiceImpl.queryResourceById(ip, routeDomain);
		resource.put("routeDomain", routeDomain.replace(RouterConstant.PRE_ROUTE_KEY, ""));
		return new ModelAndView("jsp/route/resourceInfo",resource);
	}
	
	@RequestMapping("/queryNeighborById")
	public ModelAndView queryNeighborById(String ip,String sort,String order, String routeDomain){
		Map model = new HashMap();
		if(StrUtil.isEmpty(sort)){
			sort = RouterConstant.NBR_PERF;
		}
		if(StrUtil.isEmpty(order)){
			order = RouterConstant.ASC;
		}
		List neighborRtppList = routeServiceImpl.queryNeighborById(ip, sort, order, routeDomain);
		
		model.put("neighborRtppList", neighborRtppList);
		model.put("ip", ip);
		model.put("routeDomain", routeDomain);
		
		return new ModelAndView("jsp/route/neighbor", model);
	}
	
	/**
	 * 以城市为单位
	 * @return
	 */
	@RequestMapping("/topology")
	@ResponseBody
	public Map topology(){
		Map model = routeServiceImpl.topology();
		return  model;
	}
	
	/**
	 * 以城市为单位
	 * @return
	 */
	@RequestMapping("/trafficMap")
	@ResponseBody
	public Map trafficMap(){
		Map model = routeServiceImpl.trafficMap();
		return  model;
	}
	
	/**
	 * 以城市为单位
	 * @return
	 */
	@RequestMapping("/linkStatus")
	@ResponseBody
	public Map linkStatus(){
		Map model = routeServiceImpl.linkStatus();
		return  model;
	}
	
	/**
	 * 拓扑页面
	 * @return
	 */
	@RequestMapping("/topologyPage")
	public ModelAndView topologyPage(){
		return  new ModelAndView("jspPage/route/topology");
	}
	
	/**
	 * 以节点为单位
	 * @return
	 */
	@RequestMapping("/topologyNew")
	@ResponseBody
	public Map<String, Object> topologyNew(HttpSession session){
		Map<String, Object> model = setMapPara(session);
		model = routeServiceImpl.topologyNew(model);
		return  model;
	}
	
	/**
	 * 传输流量图页面
	 * @return
	 */
	@RequestMapping("/trafficMapPage")
	public ModelAndView trafficMapPage(){
		return  new ModelAndView("jspPage/route/trafficMap");
	}
	
	/**
	 * 以节点为单位
	 * @return
	 */
	@RequestMapping("/trafficMapNew")
	@ResponseBody
	public Map trafficMapNew(HttpSession session){
		Map<String, Object> model = setMapPara(session);
		model = routeServiceImpl.trafficMapNew(model);
		return  model;
	}
	/**
	 * 链路质量页面
	 * @return
	 */
	@RequestMapping("/linkStatusPage")
	public ModelAndView linkStatusPage(){
		return  new ModelAndView("jspPage/route/link-status");
	}
	
	/**
	 * 以节点为单位
	 * @return
	 */
	@RequestMapping("/linkStatusNew")
	@ResponseBody
	public Map linkStatusNew(HttpSession session){
		Map<String, Object> model = setMapPara(session);
		model = routeServiceImpl.linkStatusNew(model);
		return  model;
	}
	
	@RequestMapping("/querySession")
	public ModelAndView querySession(HttpServletRequest request){
		
		String currentPage = request.getParameter("currentPage");//页码
		String sessionKey = request.getParameter("sessionKey");//会话ID
		String routeDoamin = request.getParameter("routeDoamin");//路由域
		if(routeDoamin == null || "".equals(routeDoamin)){
			routeDoamin = RouterConstant.ROUTE_DEFAULT_KEY;
		}
		int page = StrUtil.isEmpty(currentPage) ? 1 : Integer.valueOf(currentPage);
		String paraKey = "";
		if(StrUtil.isEmpty(sessionKey)){
			paraKey=RouterConstant.PRE_SESSION_KEYS  +routeDoamin.replace(RouterConstant.PRE_ROUTE_KEY, "") + "*";
		}else{
			paraKey = RouterConstant.PRE_SESSION_KEYS + routeDoamin.replace(RouterConstant.PRE_ROUTE_KEY, "") + "*" + sessionKey + "*";
		}
		
		PageContainer PageContainer = routeServiceImpl.querySessions(page,paraKey);
		PageContainer.getParameter().put("paraKey", sessionKey);
		Map model = new HashMap();
		//redis获取所有域名
		Set<String> routeKeyList = routeServiceImpl.getDomainList();
		model.put("routeKeyList", routeKeyList);
		model.put("routeDoamin", routeDoamin);
		model.put("page",PageContainer);
		
		return new ModelAndView("jsp/route/session",model);
	}
	
	/**
	 * 以节点为单位
	 * @return
	 */
	@RequestMapping("/locationNew")
	@ResponseBody
	public Map<String, Object> locationNew(HttpSession session){
		Map<String, Object> model = setMapPara(session);
		model = routeServiceImpl.locationNew(model);
		return  model;
	}

	public Map<String, Object> setMapPara(HttpSession session){
		Map<String, Object> model = new HashMap<String, Object>();
		TbRsAccountInfo user =(TbRsAccountInfo) session.getAttribute("userSideSession");
		String netSid = user.getNetSid(); 
		String routeDomain = ResourceSideServiceImpl.getRouteArea(netSid);
		model.put("netSid", netSid);
		model.put("domain", routeDomain);
		return model;
	}
	

}
