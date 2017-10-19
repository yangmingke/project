package com.flypaas.controller.finance;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.flypaas.annotation.Log;
import com.flypaas.controller.resource.ResourceController;
import com.flypaas.model.TbRsAccountBalance;
import com.flypaas.model.TbRsAccountInfo;
import com.flypaas.service.ResourceSideService;
import com.flypaas.service.finance.AccountBalanceService;
import com.flypaas.util.TransformUtil;
import com.google.gson.Gson;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2016年12月27日 下午7:18:49
* 类说明
*/
@Controller
@RequestMapping("/accountBalanceController")
public class AccountBalanceController {
	public static Logger logger = Logger.getLogger(ResourceController.class);
	public static Gson gson = new Gson();
	
	@Autowired
	private AccountBalanceService accountBalanceServiceImpl;
	@Autowired
	private ResourceSideService resourceSideServiceImpl;
	/**
	 * 余额管理
	 * @param request
	 * @param session
	 * @param tbRsAccountBalance
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryAccountBalanceBynetSid")
	public ModelAndView queryAccountBalanceBynetSid(HttpSession session){
		Map<String, Object> model = getBalanceInfo(session);
		return new ModelAndView("jspPage/finance/bill-show",model);
	}
	/**
	 * 余额管理
	 * @param request
	 * @param session
	 * @param tbRsAccountBalance
	 * @param model
	 * @return
	 */
	@RequestMapping("/createOrder")
	public ModelAndView createOrder(HttpSession session){
		Map<String, Object> model = getBalanceInfo(session);
		return new ModelAndView("jspPage/finance/payOrder",model);
	}
	
	public Map<String, Object> getBalanceInfo(HttpSession session){
		TbRsAccountInfo user = (TbRsAccountInfo) session.getAttribute("userSideSession");//缓存资源方信息
		TbRsAccountBalance  tbRsAccountBalance = accountBalanceServiceImpl.queryAccountBalanceBynetSid(user.getNetSid());
		double balance = tbRsAccountBalance.getBalance();
		TbRsAccountInfo tbRsAccountInfo = resourceSideServiceImpl.selectByPrimaryKey(user.getNetSid());//实时资源方信息
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("account", tbRsAccountInfo);
		model.put("balanceShow", TransformUtil.doubleDown2Str(balance, 2));//显示截取两位小数余额
		model.put("accountBalance", tbRsAccountBalance);
		return model;
	}
}
