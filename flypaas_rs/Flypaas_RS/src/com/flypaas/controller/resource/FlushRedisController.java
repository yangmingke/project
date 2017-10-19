package com.flypaas.controller.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.flypaas.model.TbRsAccountInfo;
import com.flypaas.model.TbRsRTPP;
import com.flypaas.model.vo.ResultRedis;
import com.flypaas.service.impl.resource.RedisService;
import com.flypaas.service.resource.ResourceService;
import com.google.gson.Gson;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年1月9日 上午11:58:03
* 类说明
*/
@Controller
@RequestMapping("/flushRedisController")
public class FlushRedisController {
	public static Logger logger = Logger.getLogger(ResourceController.class);
	public static Gson gson = new Gson();
	
	@Autowired
	private ResourceService resourceServiceImpl;
	
	/**
	 * 查询该账号所有的节点  并访问redis获取最新状态
	 * @param session
	 * @return
	 */
	@RequestMapping("/queryRedisList")
	public ModelAndView  queryRedisList(HttpSession session,HttpServletRequest request){
		logger.info("开始刷新redis获取最新节点状态数据，从redis中查询某个ip的状态------------------>>");
		TbRsAccountInfo tbUser = (TbRsAccountInfo) session.getAttribute("userSideSession");
		String netSid = tbUser.getNetSid();
//		String routeDomain = tbUser.getNetArea() == null ? RouterConstant.ROUTE_DEFAULT_KEY_CN : tbUser.getNetArea();
		List<ResultRedis> list2 = new ArrayList<ResultRedis>();
		RedisService redisService = new RedisService();
		//redis分页查询
		String ipParam = request.getParameter("ip");
		request.setAttribute("ip",ipParam);
		Set<String> set =new HashSet<String>();
		if(ipParam == null || "".equals(ipParam)){
			set = redisService.getKeys("RTPP_INFO_"  + "*");
		}else{
			set = redisService.getKeys("RTPP_INFO_"  + "*" + ipParam + "*");
		}
		if(null==set ||set.isEmpty()){
			request.setAttribute("redisList", list2);
			return new ModelAndView("jspPage/resource/resource-redisList");
		}else{
			Map<String, Map<String, Object>> ipMap = new HashMap<String, Map<String, Object>>();
			Iterator<String> it = set.iterator();  
	 		while (it.hasNext()) { 
	 			Map<String,String> result = new HashMap<String,String>();
	 			String str = it.next();
	 			result=redisService.hgetall(str);
	 			if(result.isEmpty()){
	 				 continue;
	 			}
	 			String net_sid = result.get("net_sid");
	 			if(netSid != null && netSid.equals(net_sid) ){
	 				String ip = result.get("ip") == null ? "" : result.get("ip");
	 				int concurrency = result.get("concurrency") == null ? 0 : Integer.parseInt(result.get("concurrency"));
	 				int bandwidth_in = result.get("bandwidth_in") == null ? 0 : Integer.parseInt(result.get("bandwidth_in"));
	 				int bandwidth_out = result.get("bandwidth_out") == null ? 0 : Integer.parseInt(result.get("bandwidth_out"));
	 				if(ipMap.containsKey(ip)){
	 					Map<String, Object> node = ipMap.get(ip);
	 					node.put("concurrency", concurrency + (Integer)node.get("concurrency"));
	 					node.put("bandwidth_in", bandwidth_in + (Integer)node.get("bandwidth_in"));
	 					node.put("bandwidth_out", bandwidth_out + (Integer)node.get("bandwidth_out"));
	 				}else{
	 					Map<String, Object> node = new HashMap<String, Object>();
	 					String name = result.get("name") == null ? "" : result.get("name").toString();
	 					String status = result.get("status") == null ? "" : result.get("status").toString();
	 					String bandwidth_limit = result.get("bandwidth_limit") == null ? "" : result.get("bandwidth_limit").toString();
	 					node.put("ip", ip);
	 					node.put("name", name);
	 					node.put("status", status);
	 					node.put("bandwidth_limit", bandwidth_limit);
	 					node.put("concurrency", concurrency);
	 					node.put("bandwidth_in", bandwidth_in);
	 					node.put("bandwidth_out", bandwidth_out);
	 					ipMap.put(ip, node);
	 				}
	 			}
	 		} 
	 		for(Map<String, Object> node : ipMap.values()){
	 			ResultRedis resultRedis = new ResultRedis();
	 			resultRedis.setStatus(node.get("status").toString());
	 			resultRedis.setBandwidthLimit(Integer.parseInt(node.get("bandwidth_limit").toString()));
	 			resultRedis.setTrafficIn((Integer)node.get("bandwidth_in"));//bandwidth_in
	 			resultRedis.setTrafficOut((Integer)node.get("bandwidth_out"));//bandwidth_out
	 			resultRedis.setConcurrency((Integer)node.get("concurrency"));//concurrency
	 			resultRedis.setIp(node.get("ip").toString());
	 			resultRedis.setName(node.get("name").toString());
	 			list2.add(resultRedis);
	 		}
	 		request.setAttribute("redisList", list2);
			return new ModelAndView("jspPage/resource/resource-redisList");
		}
	}
	
	
	/**
	 * 查询该账号的所有资源
	 * 1.从session获取当前登录的netSid
	 * 2.获取该netSid所有的资源节点
	 * 3.获取该netSid滋源节点数量
	 * 4.将获取的信息保存在ModelMap中返回到界面
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	
	public List<TbRsRTPP> queryAllResource(HttpSession session){
		logger.info("查询该账号下所有资源节点--------->>");
		TbRsAccountInfo tbRsAccountInfo = (TbRsAccountInfo) session.getAttribute("userSideSession");
		List<TbRsRTPP> list = resourceServiceImpl.queryAllResource(tbRsAccountInfo.getNetSid());
		return list;
	}
}
