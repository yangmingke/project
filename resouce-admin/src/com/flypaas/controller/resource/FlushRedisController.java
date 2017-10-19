package com.flypaas.controller.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.flypaas.constant.RouterConstant;
import com.flypaas.model.TbRsAccountInfo;
import com.flypaas.model.vo.ResultRedis;
import com.flypaas.service.account.AccountService;
import com.flypaas.service.impl.RedisService;
import com.flypaas.service.route.RouteService;
import com.flypaas.util.PageContainer;
import com.flypaas.util.TransformUtil;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年2月7日 下午6:58:03
* 类说明
*/
@Controller
@RequestMapping("/flushRedisController")
public class FlushRedisController {
	@Autowired
	RouteService routeServiceImpl;
	@Autowired
	AccountService accountServiceImpl;
	public static final Logger logger = Logger.getLogger(FlushRedisController.class);
	
	
	/**
	 * 查询Redis中资源的最新状态
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/flushRedisListFenYe")
	public ModelAndView flushRedisListFenYe(HttpServletRequest request,ModelMap model,String concurrent,String currentPage){
		logger.info("查询Redis中资源的最新状态--------------------------->>");
		int page = currentPage == null ? 1 : Integer.valueOf(currentPage);
		RedisService redisService = RedisService.getInstance();
		List<Object> ipInfoList = new ArrayList<Object>();
		PageContainer pageContainer = new PageContainer();
		//redis分页查询
		String ip = request.getParameter("IP");
		String routeDomain = request.getParameter("routeDomain");
		String isRunning = request.getParameter("isRunning");
		String status = request.getParameter("status");
		String username = request.getParameter("username");
		String pageRowCount = request.getParameter("pageRowCount");//一页显示最大数据条数
		if(StringUtils.isNotEmpty(pageRowCount)){
			pageContainer.setPageRowCount(Integer.valueOf(pageRowCount));
		}
		if(routeDomain == null || "".equals(routeDomain)){
			routeDomain = RouterConstant.ROUTE_DEFAULT_KEY_CN;
		}
		Set<String> set = new HashSet<String>();
		if(null == ip || ip.equals("")){
			set = redisService.getKeys(RouterConstant.PRE_RTPP_INTO+routeDomain+"*");
		}else{
			set = redisService.getKeys(RouterConstant.PRE_RTPP_INTO+routeDomain+"*" + ip + "*");
		}
		List<String> keyList = new ArrayList();
		keyList.addAll(set);
		List<TbRsAccountInfo> accountList = accountServiceImpl.queryAllAccountNameByName(username);
		Map<String, String> accountCache = new HashMap<String, String>();
		for(TbRsAccountInfo account : accountList){
			accountCache.put(account.getNetSid(), account.getUsername());
		}
		for (int i = 0; i < keyList.size(); i++) {
			Map<String, String> ipInfo = redisService.hgetall(keyList.get(i));
			if (StringUtils.isNotEmpty(isRunning)) {//过滤并发量
				int concurrency = ipInfo.get("concurrency") == null ? 0 : Integer.valueOf(ipInfo.get("concurrency"));
				if ("1".equals(isRunning) && concurrency <= 0) {
					keyList.remove(i);
					i--;
					continue;
				}
				if ("0".equals(isRunning) && concurrency > 0) {
					keyList.remove(i);
					i--;
					continue;
				}
			}
			if (StringUtils.isNotEmpty(status)) {//过滤节点状态
				if(!status.equals(ipInfo.get("status"))){
					keyList.remove(i);
					i--;
					continue;
				}
			}
			if (StringUtils.isNotEmpty(username)) {//过滤资源方
				String userName = accountCache.get(ipInfo.get("net_sid"));
				if(userName == null || !userName.contains(username)){
					keyList.remove(i);
					i--;
					continue;
				}
			}
		}
		Map<String, Integer> para = pageContainer.createRedisParameter(page, keyList.size());
		
		for(int i = para.get("begin"); i < pageContainer.getTotalCount() && i < para.get("end"); i++){
			Map<String, String> ipInfo= redisService.hgetall(keyList.get(i));
			int concurrency = ipInfo.get("concurrency") == null ? 0 : Integer.valueOf(ipInfo.get("concurrency"));
			ResultRedis resultRedis = new ResultRedis();
			resultRedis.setStatus(ipInfo.get("status"));
			resultRedis.setConcurrency(concurrency);
			resultRedis.setConcurrencyLimit(Integer.valueOf(ipInfo.get("concurrency_limit")==null ? "0" : ipInfo.get("concurrency_limit") ));
			resultRedis.setTrafficIn(Integer.valueOf(ipInfo.get("bandwidth_in")==null ? "0" : ipInfo.get("bandwidth_in") ));
			resultRedis.setTrafficOut(Integer.valueOf(ipInfo.get("bandwidth_out")==null ? "0" : ipInfo.get("bandwidth_out") ));
			resultRedis.setIp(ipInfo.get("ip"));
			resultRedis.setIp(ipInfo.get("ip"));
			resultRedis.setName(ipInfo.get("name"));
			resultRedis.setNetSid(ipInfo.get("net_sid"));
			resultRedis.setVersion(ipInfo.get("version"));
			resultRedis.setUsername(accountCache.get(ipInfo.get("net_sid")));
			int time = ipInfo.get("uptime") == null ? 0 : Integer.parseInt(ipInfo.get("uptime").toString());
			resultRedis.setUptime(TransformUtil.secToDateTime(time));
			ipInfoList.add(resultRedis);
		}
		pageContainer.setResult(ipInfoList);
		request.setAttribute("page", pageContainer);
			
		//redis获取所有域名
		Set<String> routeKeyList = routeServiceImpl.getDomainList();
		request.setAttribute("isRunning", isRunning);
		request.setAttribute("ip", ip);
		request.setAttribute("routeDomain", routeDomain);
		request.setAttribute("routeKeyList", routeKeyList);
		request.setAttribute("status", status);
		request.setAttribute("username", username);
		return new ModelAndView("jsp/resource/resource-showRedisList");
		
	}
	
}
