package com.flypaas.controller.finance;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.flypaas.model.TbRsBillFlow;
import com.flypaas.service.finance.financeService;
import com.flypaas.util.PageContainer;
import com.flypaas.util.StrUtil;

/**
* @author 作者 yangmingke
* @version 创建时间：2017年2月20日 下午6:53:19
* 类说明
*/
@Controller
@RequestMapping("/accountManagement")
public class AccountManagementController {
	public static Logger logger = Logger.getLogger(AccountManagementController.class);
	
	@Autowired
	private financeService financeServiceImpl;
	
	@RequestMapping("/queryAccount")
	public ModelAndView queryAccount(HttpServletRequest request,HttpServletResponse response){
		
		String username = request.getParameter("username");;//资源方名称
		String status = request.getParameter("status");;//资源方名称
		String currentPage = request.getParameter("page");//页码
		String pageRowCount = request.getParameter("pageRowCount");//一页显示最大数据条数
		int page = currentPage == null ? 1 : Integer.valueOf(currentPage);
		
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("username", StrUtil.isEmpty(username) ? null : username.trim());
		para.put("status", StrUtil.isEmpty(status) ? null : status);
		if(StringUtils.isNotEmpty(pageRowCount)){
			para.put("pageRowCount", Integer.valueOf(pageRowCount));
		}
		
		PageContainer  pageContainer  = financeServiceImpl.queryAccount(para,page);
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("page",pageContainer);
		
		return new ModelAndView("jsp/finance/finance-account",model);
	}
	
	@RequestMapping("/unLockorLock")
	@ResponseBody
	public int unLockorLock(String netSid,String status){
		if(StrUtil.isEmpty(netSid) || StrUtil.isEmpty(status)){
			return 0;
		}
			
		int result = financeServiceImpl.unLockorLock(netSid,status);
		
		return result;
	}
	
	@RequestMapping("/enchashment")
	public ModelAndView enchashment(HttpServletResponse response, String netSid){
		if(StrUtil.isEmpty(netSid)){
			StrUtil.writeMsg(response, "系统参数发生异常，请联系管理员！");
			return null;
		}
			
		Map model = financeServiceImpl.enchashment(netSid);
		
		return new ModelAndView("jsp/finance/enchashment",model);
	}
	
	@RequestMapping("/apply")
	public ModelAndView apply(HttpServletResponse response, TbRsBillFlow tbRsBillFlow){
		if(StrUtil.isEmpty(tbRsBillFlow.getAlipayAccount()) || StrUtil.isEmpty(tbRsBillFlow.getAlipayAccount())){
			StrUtil.writeMsg(response, "提款申请失败，请先完善“提款账号”和“提款人”","/account/queryAccountById?netSid=" + tbRsBillFlow.getMainSid());
			return null;
		}
		if(tbRsBillFlow.getBalance() < tbRsBillFlow.getActualFee()){
			StrUtil.writeMsg(response, "参数错误，“提款金额”不能大于“余额”");
			return null;
		}
		if(tbRsBillFlow.getActualFee() == null || tbRsBillFlow.getActualFee() <= 0){
			StrUtil.writeMsg(response, "参数错误，“提款金额”必须大于0");
			return null;
		}
		int result = financeServiceImpl.createApply(tbRsBillFlow);
		if(result == -1){
			StrUtil.writeMsg(response, "申请失败，期间用户钱包被修改，请重新申请","/accountManagement/queryAccount");
			return null;
		}
		if(result == -1){
			StrUtil.writeMsg(response, "系统参数发生错误，请联系管理员","/accountManagement/queryAccount");
			return null;
		}
		
		return new ModelAndView("redirect:/accountManagement/record");
	}
	
	@RequestMapping("/record")
	public ModelAndView record(HttpServletRequest request){
		String status = request.getParameter("status");//状态
		String datemin = request.getParameter("datemin");;//日期起
		String datemax = request.getParameter("datemax");;//日期止
		String keyword = request.getParameter("keyword");;//关键字
		String currentPage = request.getParameter("page");//页码
		String pageRowCount = request.getParameter("pageRowCount");//一页显示最大数据条数
		keyword = StrUtil.isEmpty(keyword) ? null : keyword.trim();
		int page = currentPage == null ? 1 : Integer.valueOf(currentPage);
		
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("page", page);
		para.put("status", StrUtil.isEmpty(status) ? null : status);
		para.put("datemin", StrUtil.isEmpty(datemin) ? null : datemin);
		para.put("datemax", StrUtil.isEmpty(datemax) ? null : datemax);
		para.put("keyword", StrUtil.isEmpty(keyword) ? null : keyword);
		if(StringUtils.isNotEmpty(pageRowCount)){
			para.put("pageRowCount", Integer.valueOf(pageRowCount));
		}
		Map<String, PageContainer> model = new HashMap<String, PageContainer>();
		
		PageContainer pageContainer = financeServiceImpl.queryAllBillFlow(para);
		model.put("page", pageContainer);
		
		return new ModelAndView("/jsp/finance/record",model);
	}
	
	@RequestMapping("/queryBillFlow")
	public ModelAndView queryBillFlow(HttpServletResponse response,String id){
		if(StrUtil.isEmpty(id)){
			StrUtil.writeMsg(response, "获取系统参数错误，请联系管理员");
			return null;
		}
		Map<String,String> flow = financeServiceImpl.queryBillFlow(id);
		
		return new ModelAndView("jsp/finance/approval",flow);
	}
	
	@RequestMapping("/approval")
	public ModelAndView approval(HttpServletResponse response,String id, String state,String netSid, long actualFee, String demo){
		if(StrUtil.isEmpty(id) || StrUtil.isEmpty(state) || StrUtil.isEmpty(netSid)){
			StrUtil.writeMsg(response, "获取系统参数错误，请联系管理员");
			return null;
		}
		try {
			demo = new String(demo.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int result = financeServiceImpl.updateFlow(netSid,id,state,actualFee, demo);
		
		if(result == -1){
			StrUtil.writeMsg(response, "钱包余额不足，重新审核失败");
			return null;
		}else if(result == 0){
			StrUtil.writeMsg(response, "获取系统参数错误，请联系管理员");
			return null;
		}
		
		return new ModelAndView("redirect:/accountManagement/record");
	}
}
