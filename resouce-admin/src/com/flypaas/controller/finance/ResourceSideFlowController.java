package com.flypaas.controller.finance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.flypaas.service.finance.financeService;
import com.flypaas.util.PageContainer;
import com.flypaas.util.StrUtil;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年2月13日 下午6:51:29
* 类说明：
* 	资源方流量管理
* 		日消耗流量
* 		月消耗流量
*/
@Controller
@RequestMapping("/resourceSideFlowController")
public class ResourceSideFlowController {
	public static Logger logger = Logger.getLogger(ResourceSideFlowController.class);
	@Autowired
	private financeService financeServiceImpl;
	
	/**
	 * 资源方日消耗流量
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryResourceSideFlowDay")
	public ModelAndView queryResourceFlowSideDay(HttpServletRequest request,ModelMap model){
		String dateTime = request.getParameter("dateTime");
		String username = request.getParameter("keyWord");
		String currentPage = request.getParameter("page");
		String pageRowCount = request.getParameter("pageRowCount");//一页显示最大数据条数
		int page = 0;
		if(StrUtil.isEmpty(dateTime)){
			Calendar cal=Calendar.getInstance();
			cal.add(Calendar.DATE,-1);
			Date time=cal.getTime();
			dateTime = new SimpleDateFormat("yyyy-MM-dd").format(time);
		}
		if(StrUtil.isEmpty(currentPage)){
			page = 1;
		}else{
			page = Integer.valueOf(currentPage);
		}
		//缓存资源方Sid和名称
		List<Map<String,String>> accountList = financeServiceImpl.queryAccountByName(username);
		if(accountList == null || accountList.isEmpty()){
			model.put("dateTime", dateTime);
			model.put("keyWord", username);
			return new ModelAndView("jsp/finance/finance-showResourceSideFlowDay",model);
		}
		Map<String,String> accountMap = new HashMap<String,String>();//缓存资源方Sid和名称
		List<String> sidList = new ArrayList<String>();//缓存资源方Sid
		for(Map<String,String> map : accountList){
			accountMap.put(map.get("net_sid"), map.get("username"));//缓存资源方姓名
			sidList.add(map.get("net_sid"));
		}
		PageContainer pageContainer = new PageContainer();
		if(StringUtils.isNotEmpty(pageRowCount)){
			pageContainer.setPageRowCount(Integer.parseInt(pageRowCount));
		}
		try{
			pageContainer = financeServiceImpl.queryResourceSideFlowDay(sidList, dateTime, page);
		}catch(Exception e){
			model.put("dateTime", dateTime);
			model.put("keyWord", username);
			return new ModelAndView("jsp/finance/finance-showResourceSideFlowDay",model);
		}
		List<Map<String,String>> resultMapList = pageContainer.getResultMap();
		for(Map<String,String> result : resultMapList){
			String userName = accountMap.get(result.get("node_sid"));
			result.put("userName", userName);
		}
		model.put("page", pageContainer);
		model.put("dateTime", dateTime);
		model.put("keyWord", username);
		return new ModelAndView("jsp/finance/finance-showResourceSideFlowDay",model);
	}

	
	/**
	 * 资源方月消耗流量
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryResourceSideFlowMonth")
	public ModelAndView queryResourceSideFlowMonth(HttpServletRequest request,ModelMap model){
		String dateTime = request.getParameter("dateTime");
		String username = request.getParameter("keyWord");
		String currentPage = request.getParameter("page");
		String pageRowCount = request.getParameter("pageRowCount");//一页显示最大数据条数
		int page = 0;
		if(StrUtil.isEmpty(dateTime)){
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			dateTime = sdf.format(date);
		}
		if(StrUtil.isEmpty(currentPage)){
			page = 1;
		}else{
			page = Integer.valueOf(currentPage);
		}
		//缓存资源方Sid和名称
		List<Map<String,String>> accountList = financeServiceImpl.queryAccountByName(username);
		if(accountList == null || accountList.isEmpty()){
			model.put("dateTime", dateTime);
			model.put("keyWord", username);
			return new ModelAndView("jsp/finance/finance-showResourceSideFlowMonth",model);
		}
		Map<String,String> accountMap = new HashMap<String,String>();//缓存资源方Sid和名称
		List<String> sidList = new ArrayList<String>();//缓存资源方Sid
		for(Map<String,String> map : accountList){
			accountMap.put(map.get("net_sid"), map.get("username"));//缓存资源方姓名
			sidList.add(map.get("net_sid"));
		}
		PageContainer pageContainer = new PageContainer();
		if(StringUtils.isNotEmpty(pageRowCount)){
			pageContainer.setPageRowCount(Integer.parseInt(pageRowCount));
		}
		try{
			pageContainer = financeServiceImpl.queryResourceSideFlowMonth(sidList,dateTime, page);
		}catch(Exception e){
			model.put("dateTime", dateTime);
			model.put("keyWord", username);
			return new ModelAndView("jsp/finance/finance-showResourceSideFlowMonth",model);
		}
		List<Map<String,String>> resultMapList = pageContainer.getResultMap();
		for(Map<String,String> result : resultMapList){
			String userName = accountMap.get(result.get("node_sid"));
			result.put("userName", userName);
		}
		model.put("page", pageContainer);
		model.put("dateTime", dateTime);
		model.put("keyWord", username);
		return new ModelAndView("jsp/finance/finance-showResourceSideFlowMonth",model);
	}
}
