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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.flypaas.model.TbRsAccountInfo;
import com.flypaas.model.TbRsBillAcctRTPP;
import com.flypaas.service.finance.ResourceAcctService;
import com.flypaas.util.DateUtil;
import com.google.gson.Gson;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年1月11日 上午11:26:21
* 类说明
*/
@Controller
@RequestMapping("/resourceAcctController")
public class ResourceAcctController {
	public static Logger logger = Logger.getLogger(ResourceAcctController.class);
	
	public static Gson gson = new Gson();
	
	@Autowired
	private ResourceAcctService resourceAcctServiceImpl;
	
	/**
	 * 查询自家资源节点的日流量消耗
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryResourceTDC")
	public ModelAndView queryResourceTDC(HttpSession session,HttpServletRequest request,ModelMap model){
		logger.info("查询自家节点的日流量消耗------------------------->>");
		TbRsAccountInfo tbUser = (TbRsAccountInfo) session.getAttribute("userSideSession");
		String date = request.getParameter("date");
		if(date == null || date == "" || date.equals("")){
			date = DateUtil.getYstdDate();
		}
		//date = "2017-01-11";
		model.put("date", date);
		try{
			List<TbRsBillAcctRTPP>  TDCList = resourceAcctServiceImpl.queryResourceTDC(tbUser.getNetSid(), date);
			model.put("list", TDCList);
			TbRsBillAcctRTPP total = resourceAcctServiceImpl.queryResourceTDCTotal(tbUser.getNetSid(), date);
			model.put("total", total);
		}catch(Exception e){
			System.out.println(e);
			return new ModelAndView("jspPage/finance/ResourceTDC",model);
		}
		return new ModelAndView("jspPage/finance/ResourceTDC",model);
	}
	
	/**
	 * 查询自家资源节点的日流量消耗
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryResourceTDCAjax")
	@ResponseBody
	public String queryResourceTDCAjax(HttpSession session,HttpServletRequest request,ModelMap model){
		logger.info("查询自家节点的日流量消耗------------------------->>");
		TbRsAccountInfo tbUser = (TbRsAccountInfo) session.getAttribute("userSideSession");
		String date = request.getParameter("date");
		if(null == date || date.equals("")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	        String dateNowStr = sdf.format(new Date());
			date = dateNowStr;
		}
		//date = "2017-01-11";
		List<TbRsBillAcctRTPP>  TDCList = resourceAcctServiceImpl.queryResourceTDC(tbUser.getNetSid(), date);
		return gson.toJson(TDCList);
	}
	
	
	/**
	 * 查询自家资源节点的月流量消耗
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryResourceTMC")
	public ModelAndView queryResourceTMC(HttpSession session,HttpServletRequest request,ModelMap model){
		logger.info("查询自家节点的日流量消耗------------------------->>");
		TbRsAccountInfo tbUser = (TbRsAccountInfo) session.getAttribute("userSideSession");
		String month = request.getParameter("date");
		if(null == month || "".equals(month)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
	        String dateNowStr = sdf.format(new Date());
	        month = dateNowStr;
		}
		model.put("date", month);
		//Month = "2017-01";
		try{
			List<TbRsBillAcctRTPP>  TMCList = resourceAcctServiceImpl.queryResourceTMC(tbUser.getNetSid(), month);
			model.put("list", TMCList);
			TbRsBillAcctRTPP total = resourceAcctServiceImpl.queryResourceTMCTotal(tbUser.getNetSid(), month);
			model.put("total", total);
		}catch(Exception e){
			System.out.println(e);
			return new ModelAndView("jspPage/finance/ResourceTMC",model);
		}
		return new ModelAndView("jspPage/finance/ResourceTMC",model);
		
	}
} 
