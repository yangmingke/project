package com.flypaas.controller.route;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.flypaas.constant.RouterConstant;
import com.flypaas.service.route.RouteService;
import com.flypaas.util.PageContainer;
import com.flypaas.util.StrUtil;

@Controller
@RequestMapping("/route")
public class RouteContrller{
	
	@Autowired
	RouteService routeServiceImpl;
	public static Logger logger = Logger.getLogger(RouteContrller.class);
	
	@RequestMapping("/queryDomain")
	public ModelAndView queryDomain(String concurrent,String currentPage,String ipKeyWord,String pageRowCount){

		int page = currentPage == null ? 1 : Integer.valueOf(currentPage);
		if(StrUtil.isEmpty(concurrent)){
			concurrent = RouterConstant.ROUTE_DEFAULT_KEY;
		}
		
		//redis获取数据分页
		PageContainer pageContainer = routeServiceImpl.getIpList(concurrent,page,ipKeyWord,pageRowCount);
		//redis获取所有域名
		Set<String> routeKeyList = routeServiceImpl.getDomainList();
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("page", pageContainer);
		model.put("routeKeyList", routeKeyList);
		model.put("concurrent", concurrent);
		model.put("ipKeyWord", ipKeyWord);
		
		return new ModelAndView("jsp/route/domain",model);
	}
	
	@SuppressWarnings({ "unchecked" })
	@RequestMapping("/queryResourceById")
	public ModelAndView queryResourceById(String ip, String routeDomain){
		Map<String,String> resource = routeServiceImpl.queryResourceById(ip, routeDomain);
		Integer bandwidthLimit = Integer.valueOf(resource.get("bandwidth_limit"));
		bandwidthLimit = bandwidthLimit / 1024;
		resource.put("bandwidth_limit", bandwidthLimit.toString());
		resource.put("routeDomain", routeDomain.replace(RouterConstant.PRE_ROUTE_KEY, ""));
		return new ModelAndView("jsp/route/resourceInfo",resource);
	}
	
	@RequestMapping("/queryNeighborById")
	public ModelAndView queryNeighborById(String ip,String sort,String order, String routeDomain){
		Map<String,Object> model = new HashMap<String,Object>();
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
	 * 拓扑页面
	 * @return
	 */
	@RequestMapping("/topologyPage")
	public ModelAndView topologyPage(){
		Map<String,Object> model = new HashMap<String,Object>();
		//redis获取所有域名
		Set<String> routeKeyList = routeServiceImpl.getDomainList();
		model.put("routeKeyList", routeKeyList);
		model.put("routeDomain", RouterConstant.ROUTE_DEFAULT_KEY);
		return  new ModelAndView("jsp/route/topology",model);
	}
	
	/**
	 * 以节点为单位
	 * @return
	 */
	@RequestMapping("/topology")
	@ResponseBody
	public Map<String,Object> topology(String routeDomain,String netLevel){
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("netLevel", netLevel);
		model.put("domain", routeDomain);
		model = routeServiceImpl.topology(model);
		return  model;
	}
	
	/**
	 * 传输流量图页面
	 * @return
	 */
	@RequestMapping("/trafficMapPage")
	public ModelAndView trafficMapPage(){
		Map<String,Object> model = new HashMap<String,Object>();
		//redis获取所有域名
		Set<String> routeKeyList = routeServiceImpl.getDomainList();
		model.put("routeKeyList", routeKeyList);
		model.put("routeDomain", RouterConstant.ROUTE_DEFAULT_KEY);
		return  new ModelAndView("jsp/route/trafficMap",model);
	}
	
	/**
	 * 以节点为单位
	 * @return
	 */
	@RequestMapping("/trafficMap")
	@ResponseBody
	public Map<String,Object> trafficMap(String domain,String netLevel){
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("domain", domain);
		model.put("netLevel", netLevel);
		model = routeServiceImpl.trafficMap(model);
		return  model;
	}
	/**
	 * 链路质量页面
	 * @return
	 */
	@RequestMapping("/linkStatusPage")
	public ModelAndView linkStatusPage(){
		Map<String,Object> model = new HashMap<String,Object>();
		//redis获取所有域名
		Set<String> routeKeyList = routeServiceImpl.getDomainList();
		model.put("routeKeyList", routeKeyList);
		model.put("routeDomain", RouterConstant.ROUTE_DEFAULT_KEY);
		return  new ModelAndView("jsp/route/link-status",model);
	}
	
	/**
	 * 以节点为单位
	 * @return
	 */
	@RequestMapping("/linkStatus")
	@ResponseBody
	public Map<String,Object> linkStatus(String domain,String netLevel){
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("domain", domain);
		model.put("netLevel", netLevel);
		model = routeServiceImpl.linkStatus(model);
		return  model;
	}
	
	@RequestMapping("/querySession")
	public ModelAndView querySession(HttpServletRequest request){
		
		String currentPage = request.getParameter("currentPage");//页码
		String sessionKey = request.getParameter("sessionKey");//会话ID
		String routeDoamin = request.getParameter("routeDoamin");//路由域
		String pageRowCount = request.getParameter("pageRowCount");//一页显示最大数据条数
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
		
		PageContainer PageContainer = routeServiceImpl.querySessions(page,paraKey,pageRowCount);
		PageContainer.getParameter().put("paraKey", sessionKey);
		Map<String,Object> model = new HashMap<String,Object>();
		//redis获取所有域名
		Set<String> routeKeyList = routeServiceImpl.getDomainList();
		model.put("routeKeyList", routeKeyList);
		model.put("routeDoamin", routeDoamin);
		model.put("page",PageContainer);
		
		return new ModelAndView("jsp/route/session",model);
	}
	
	/**
	 * 路由传输页面
	 * @return
	 */
	@RequestMapping("/transmitPage")
	public ModelAndView transmitPage(){
		Map<String,Object> model = new HashMap<String,Object>();
		//redis获取所有域名
		Set<String> routeKeyList = routeServiceImpl.getDomainList();
		model.put("routeKeyList", routeKeyList);
		model.put("routeDomain", RouterConstant.ROUTE_DEFAULT_KEY);
		return  new ModelAndView("jsp/route/transmit",model);
	}
	
	/**
	 * 路由查找
	 * @return
	 */
	@RequestMapping("/transmit")
	@ResponseBody
	public Map<String,Object> transmit(String domain, String srcIP, String destIP){
		Map<String,Object> model = new HashMap<String,Object>();
		try {
			model = routeServiceImpl.transmit(domain,srcIP,destIP);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  model;
	}
	
	/**
	 * 路由分析页面
	 * @return
	 */
	@RequestMapping("/analysisPage")
	public ModelAndView analysisPage(String routeDomain){
		Map<String,Object> model = new HashMap<String,Object>();
		routeDomain = StringUtils.isEmpty(routeDomain) ? RouterConstant.ROUTE_DEFAULT_KEY_CN : routeDomain;
		LinkedList<String> ipAddress = routeServiceImpl.queryAllIp(routeDomain);
		//redis获取所有域名
		Set<String> routeKeyList = routeServiceImpl.getDomainList();
		
		model.put("ipList",ipAddress);
		model.put("routeKeyList", routeKeyList);
		model.put("routeDomain", routeDomain);
		return  new ModelAndView("jsp/route/analysisPage",model);
	}
	/**
	 * 路由分析
	 * @return
	 */
	@RequestMapping("/analysis")
	public ModelAndView analysis(String resultSelection, String analysisStr,String routeDomain,Integer currentPage,Integer pageRowCount){
		Map<String,Object> model = new HashMap<String,Object>();
		PageContainer pageContainer = routeServiceImpl.analysis(resultSelection,analysisStr,routeDomain,currentPage,pageRowCount);
		model.put("page",pageContainer);
		model.put("routeDomain", routeDomain);
		model.put("analysisStr", analysisStr);
		model.put("resultSelection", resultSelection);
		return new ModelAndView("jsp/route/analysisResult",model);
	}
	
	/**
	 * 路由分析
	 * @return
	 */
	@RequestMapping("/analysisChangePage")
	public ModelAndView analysisChangePage(String analysisStr,String routeDomain,Integer currentPage,Integer pageRowCount){
		Map<String,Object> model = new HashMap<String,Object>();
		PageContainer pageContainer = routeServiceImpl.analysisChangePage(currentPage,pageRowCount);
		model.put("page",pageContainer);
		model.put("routeDomain", routeDomain);
		model.put("analysisStr", analysisStr);
		return new ModelAndView("jsp/route/analysisResult",model);
	}

}
