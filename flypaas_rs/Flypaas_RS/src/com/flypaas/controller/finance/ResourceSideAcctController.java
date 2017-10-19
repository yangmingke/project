package com.flypaas.controller.finance;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.flypaas.model.TbRsAccountInfo;
import com.flypaas.model.TbRsBillAcct;
import com.flypaas.service.finance.ResourceSideAcctService;
import com.google.gson.Gson;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年1月11日 上午11:26:37
* 类说明
*/
@Controller
@RequestMapping("/accountSideAcctController")
public class ResourceSideAcctController {
	public static  Logger logger = Logger.getLogger(ResourceSideAcctController.class);
	
	public static  Gson gson = new Gson();
	
	@Autowired
	private ResourceSideAcctService resourceSideAcctServiceImpl;
	
	/**
	 * 查询自家资源的日消耗流量账单
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryResourceSideTDC")
	public ModelAndView queryResourceSideTDC(HttpSession session,HttpServletRequest request,ModelMap model){
		logger.info("查询自家资源的日消耗流量账单---------------------->>");
		TbRsAccountInfo user = (TbRsAccountInfo) session.getAttribute("userSideSession");
		String date = request.getParameter("date");
		if(null == date || date.equals("") || date == ""){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	        String dateNowStr = sdf.format(new Date());
			date = dateNowStr;
		}
		//date = "2017-01-11";
		List<TbRsBillAcct> TMCList = resourceSideAcctServiceImpl.queryResourceSideTDC(user.getNetSid(), date);
		model.put("list", TMCList);
		return new ModelAndView("jspPage/finance/ResourceSideTDC",model);
	}
	
	/**
	 * 查询自家资源的月消耗流量账单
	 * @param session
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryResourceSideTMC")
	public ModelAndView queryResourceSideTMC(HttpSession session,HttpServletRequest request,ModelMap model){
		logger.info("查询自家资源的月消耗流量账单---------------------->>");
		TbRsAccountInfo user = (TbRsAccountInfo) session.getAttribute("userSideSession");
		String Month = request.getParameter("date");
		if(null == Month || null == ""){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");  
	        String dateNowStr = sdf.format(new Date());
	        Month = dateNowStr;
		}
		Month = "2017-01";
		List<TbRsBillAcct> TMCList = resourceSideAcctServiceImpl.queryResourceSideTMC(user.getNetSid(), Month);
		model.put("list", TMCList);
		return new ModelAndView("jspPage/finance/ResourceSideTMC",model);
	}
}
