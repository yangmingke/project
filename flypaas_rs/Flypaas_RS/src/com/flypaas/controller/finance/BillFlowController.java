package com.flypaas.controller.finance;

import java.math.BigDecimal;
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

import com.flypaas.model.TbRsAccountBalance;
import com.flypaas.model.TbRsAccountInfo;
import com.flypaas.model.TbRsBillFlow;
import com.flypaas.model.TbRsUserInfo;
import com.flypaas.service.finance.AccountBalanceService;
import com.flypaas.service.finance.BillFlowService;
import com.flypaas.util.SysConstant;
import com.flypaas.util.TransformUtil;
import com.google.gson.Gson;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年1月11日 下午7:08:04
* 类说明
*/
@Controller
@RequestMapping("/billFlowController")
public class BillFlowController {
	
	public static Logger logger = Logger.getLogger(BillFlowController.class);
	
	public static Gson gson = new Gson();
	
	@Autowired
	private BillFlowService billFlowServiceImpl;
	
	@Autowired
	private AccountBalanceService accountBalanceServiceImpl;
	
	/**
	 * 查看该资源方的转账记录
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryAllBillFlowBynetSid")
	public ModelAndView queryAllBillFlowBynetSid(HttpSession session,HttpServletRequest request){
		ModelMap model = new ModelMap();
		logger.info("查询该资源方所有的提款记录数据------------------->>");
		String datemin = request.getParameter("datemin");
		String datemax = request.getParameter("datemax");
		model.put("datemin", datemin);
		model.put("datemax", datemax);
		if(null == datemin || datemin.equals("") ){
			datemin = "1770-01-01";
		}
		if(null == datemax || datemax.equals("")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			datemax = sdf.format(new Date());
		}
		TbRsAccountInfo user = (TbRsAccountInfo) session.getAttribute("userSideSession");
		List<TbRsBillFlow> billFowList = billFlowServiceImpl.queryAllBillFlowBynetSid(user.getNetSid(),datemin,datemax);
		for(TbRsBillFlow billFow : billFowList){
			String balance = TransformUtil.doubleDown2Str(billFow.getBalance(), 2);
			billFow.setBalanceShow(balance);
		}
		model.put("list", billFowList);
		return new ModelAndView("jspPage/finance/bill-flow",model);
	}
	
	/**
	 * 生成账户记录
	 * 订单状态 	0未审核  1审核通过  2审核未通过 3作废 4已转账
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/addBillFlow")
	@ResponseBody
	public String addBillFlow(HttpServletRequest request, HttpSession session,TbRsBillFlow tbRsBFlow,TbRsAccountBalance tbRsAB){
		logger.info("生成该资源方的账户记录------------------------->>");
		TbRsAccountInfo tbAccountUser = (TbRsAccountInfo) session.getAttribute("userSideSession");
		TbRsUserInfo tbUser = (TbRsUserInfo) session.getAttribute("userSession");
		Long money = Long.valueOf(request.getParameter("money"));
		double balance =Double.valueOf(request.getParameter("balance"));
		String demo = request.getParameter("demo");
		String alipayAccount = request.getParameter("alipayAccount");
		String alipayName = request.getParameter("alipayName");
		
		tbRsBFlow.setStatus("0");
		tbRsBFlow.setMainSid(tbAccountUser.getNetSid());
		tbRsBFlow.setOperUser(tbUser.getUsername());
		tbRsBFlow.setBalance(BigDecimal.valueOf(balance).subtract(BigDecimal.valueOf(money)).doubleValue());
		tbRsBFlow.setCreateDate(new Date());
		tbRsBFlow.setActualFee((long) money);
		tbRsBFlow.setDemo(demo);
		tbRsBFlow.setAlipayAccount(alipayAccount);
		tbRsBFlow.setAlipayName(alipayName);
		
		int result = billFlowServiceImpl.insertSelective(tbRsBFlow,tbAccountUser,balance,money);
		
		return gson.toJson(result);
	}
	/**
	 * 修改账单信息
	 * 作废账单   将余进行修改   操作余额表TbRsAccountBalance  +  TbRsBillFlow
	 * 将订单记录中的actualFee加到余额表的余额中
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/updateBillFlow")
	@ResponseBody
	public String updateBillFlow(HttpServletRequest request,HttpSession session,ModelMap model){
		logger.info("修改账单信息 ------------------------>>");
		TbRsAccountInfo tbRsUser = (TbRsAccountInfo) session.getAttribute("userSideSession");
		//int actualFee = Integer.valueOf(request.getParameter("actualFee"));
		int id = Integer.valueOf(request.getParameter("id"));
		logger.info(id);
		//查询tbRsUser的余额表
		TbRsAccountBalance tbRsAB = accountBalanceServiceImpl.queryAccountBalanceBynetSid(tbRsUser.getNetSid());
		//查询该用户的该账单(id)的信息
		TbRsBillFlow tbRsBF = billFlowServiceImpl.selectByPrimaryKey((long) id);
		if(!SysConstant.BILL_FLOW_NOT_AUDITED.equals(tbRsBF.getStatus())){
			logger.info("作废失败，订单状态已改变");
			return gson.toJson(2);
		}
		//先将记录表的数据修改
		tbRsBF.setAdultDate(new Date());
		tbRsBF.setStatus(SysConstant.BILL_FLOW_INVALID);
		//再将余额表的数据修改
		tbRsAB.setUpdateDate(new Date());
		BigDecimal balance = BigDecimal.valueOf(tbRsAB.getBalance());
		BigDecimal actualFee = BigDecimal.valueOf(tbRsBF.getActualFee());
		tbRsAB.setBalance(balance.add(actualFee).doubleValue());
		int temp = billFlowServiceImpl.updateByPrimaryKeySelective(tbRsBF,tbRsAB);
		if(temp > 0){
			return gson.toJson(1);
		}else{
			return gson.toJson(0);
		}
	}
	
}
