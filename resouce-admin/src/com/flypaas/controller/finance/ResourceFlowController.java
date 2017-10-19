package com.flypaas.controller.finance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import com.flypaas.service.finance.financeService;
import com.flypaas.util.PageContainer;
import com.flypaas.util.StrUtil;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年2月13日 下午6:57:43
* 类说明
* 	资源节点流量管理
*/
@Controller
@RequestMapping("/resourceFlowController")
public class ResourceFlowController {
	public static Logger logger = Logger.getLogger(ResourceFlowController.class);
	@Autowired
	private financeService financeServiceImpl;
	/**
	 * 资源节点日消耗流量
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryResourceFlowDay")
	public ModelAndView queryResourceFlowDay(HttpServletRequest request,ModelMap model){
		logger.info("查询资源节点日消耗流量--------------------------------->>");
		String dateTime = request.getParameter("dateTime");
		String keyWord = request.getParameter("keyWord");
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
		if(StrUtil.isEmpty(keyWord)){
			keyWord = null;
		}
		PageContainer pageContainer = new PageContainer();
		if(StringUtils.isNotEmpty(pageRowCount)){
			pageContainer.setPageRowCount(Integer.parseInt(pageRowCount));
		}
		try{
			pageContainer = financeServiceImpl.queryResourceFlowDay(keyWord, dateTime, page);
		}catch(Exception e){
			model.put("dateTime", dateTime);
			model.put("keyWord", keyWord);
			return new ModelAndView("jsp/finance/finance-showResourceFlowDay",model);
		}
		//因为数据源不同，需额外查询资源方名称
		List<Map<String,String>> resultMapList = pageContainer.getResultMap();
		if(resultMapList == null || resultMapList.isEmpty()){
			model.put("dateTime", dateTime);
			model.put("keyWord", keyWord);
			return new ModelAndView("jsp/finance/finance-showResourceFlowDay",model);
		}
		Set nodeSidSet = new HashSet();
		for(Map<String,String> result : resultMapList){
			nodeSidSet.add(result.get("node_sid"));
		}
		List<Map<String,String>> accountList = financeServiceImpl.findRsAccountBySids(new ArrayList(nodeSidSet));
		Map<String,String> accountMap = new HashMap<String,String>();
		for(Map<String,String> map : accountList){
			accountMap.put(map.get("net_sid"), map.get("username"));//缓存资源方姓名
		}
		for(Map<String,String> result : resultMapList){
			String userName = accountMap.get(result.get("node_sid"));
			result.put("userName", userName);
		}
		model.put("page", pageContainer);
		model.put("dateTime", dateTime);
		model.put("keyWord", keyWord);
		return new ModelAndView("jsp/finance/finance-showResourceFlowDay",model);
	}
	
	/**
	 * 资源节点月消耗流量
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryResourceFlowMonth")
	public ModelAndView queryResourceFlowMonth(HttpServletRequest request,ModelMap model){
		logger.info("查询资源节点月消耗流量--------------------------------->>");
		String dateTime = request.getParameter("dateTime");
		String keyWord = request.getParameter("keyWord");
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
		if(StrUtil.isEmpty(keyWord)){
			keyWord = null;
		}
		PageContainer pageContainer = new PageContainer();
		if(StringUtils.isNotEmpty(pageRowCount)){
			pageContainer.setPageRowCount(Integer.parseInt(pageRowCount));
		}
		try{
			pageContainer = financeServiceImpl.queryResourceFlowMonth(keyWord,dateTime, page);
		}catch(Exception e){
			model.put("dateTime", dateTime);
			model.put("keyWord", keyWord);
			return new ModelAndView("jsp/finance/finance-showResourceFlowMonth",model);
		}
		//因为数据源不同，需额外查询资源方名称
		List<Map<String,String>> resultMapList = pageContainer.getResultMap();
		if(resultMapList == null || resultMapList.isEmpty()){
			model.put("dateTime", dateTime);
			model.put("keyWord", keyWord);
			return new ModelAndView("jsp/finance/finance-showResourceFlowMonth",model);
		}
		Set nodeSidSet = new HashSet();
		for(Map<String,String> result : resultMapList){
			nodeSidSet.add(result.get("node_sid"));
		}
		List<Map<String,String>> accountList = financeServiceImpl.findRsAccountBySids(new ArrayList(nodeSidSet));
		Map<String,String> accountMap = new HashMap<String,String>();
		for(Map<String,String> map : accountList){
			accountMap.put(map.get("net_sid"), map.get("username"));//缓存资源方姓名
		}
		for(Map<String,String> result : resultMapList){
			String userName = accountMap.get(result.get("node_sid"));
			result.put("userName", userName);
		}
		
		model.put("page", pageContainer);
		model.put("dateTime", dateTime);
		model.put("keyWord", keyWord);
		return new ModelAndView("jsp/finance/finance-showResourceFlowMonth",model);
	}
}
