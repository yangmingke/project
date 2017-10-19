package com.flypaas.controller.account;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.flypaas.constant.FlypaasConstant;
import com.flypaas.constant.UserConstant;
import com.flypaas.model.TbRsAccountBalance;
import com.flypaas.model.TbRsAccountInfo;
import com.flypaas.model.TbRsRoleUser;
import com.flypaas.model.TbRsUserInfo;
import com.flypaas.service.account.AccountService;
import com.flypaas.util.PageContainer;
import com.flypaas.util.RandomUtil;
import com.flypaas.util.StrUtil;
/**
 * 资源方管理
 * @author win10
 *
 */
@Controller
@RequestMapping("/account")
public class AccountContrller{
	
	public static Logger logger = Logger.getLogger(AccountContrller.class);

	@Autowired
	AccountService accountServiceImpl;

	
	@RequestMapping("/queryAccount")
	public ModelAndView queryAccount(HttpServletRequest request,HttpServletResponse response){
		String isPrivateNet = request.getParameter("isPrivateNet");//是否形成私有加速网
		String status = request.getParameter("status");;//资源方状态
		String realname = request.getParameter("realname");;//资源方名称
		String currentPage = request.getParameter("page");//页码
		String pageRowCount = request.getParameter("pageRowCount");//一页显示最大数据条数
		realname = StrUtil.isEmpty(realname) ? null : realname.trim();
		int page = currentPage == null ? 1 : Integer.valueOf(currentPage);
		
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("isPrivateNet", StrUtil.isEmpty(isPrivateNet) ? null : isPrivateNet);
		para.put("status", StrUtil.isEmpty(status) ? null : status);
		para.put("realname", StrUtil.isEmpty(realname) ? null : realname);
		para.put("pageRowCount", StrUtil.isEmpty(pageRowCount) ? null : Integer.valueOf(pageRowCount));
		
		PageContainer  pageContainer  = accountServiceImpl.queryAccount(para,page);
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("page",pageContainer);
		model.put("status",status);
		model.put("realname",realname);
		model.put("isPrivateNet",isPrivateNet);
		
		return new ModelAndView("jsp/account/list/account",model);
	}
	
	/**
	 * 查询所有的资源方  并显示在添加资源节点界面
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryAccountToAddResource")
	public ModelAndView queryAccountToAddResource(HttpServletRequest request){
		
		String isPrivateNet = request.getParameter("isPrivateNet");//是否形成私有加速网
		String status = request.getParameter("status");;//资源方状态
		String realname = request.getParameter("realname");;//资源方名称
		String currentPage = request.getParameter("page");//页码
		realname = StrUtil.isEmpty(realname) ? null : realname.trim();
		int page = currentPage == null ? 1 : Integer.valueOf(currentPage);
		
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("isPrivateNet", StrUtil.isEmpty(isPrivateNet) ? null : isPrivateNet);
		para.put("status", StrUtil.isEmpty(status) ? null : status);
		para.put("realname", StrUtil.isEmpty(realname) ? null : realname);
		
		PageContainer  pageContainer  = accountServiceImpl.queryAccount(para,page);
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("page",pageContainer);
		model.put("status",status);
		model.put("realname",realname);
		model.put("isPrivateNet",isPrivateNet);
		
		return new ModelAndView("jsp/resource/resourceSide-showResourceSideList",model);
	}
	
	@RequestMapping("/queryAccountById")
	public ModelAndView queryAccountById(String netSid){
		
		TbRsAccountInfo  account  = accountServiceImpl.queryAccountById(netSid);
		
		return new ModelAndView("jsp/account/list/account_info","account",account);
	}
	
	@RequestMapping("/queryUsersByNetId")
	public ModelAndView queryUsersByNetId(String netSid){
		
		List<TbRsUserInfo> userList  = accountServiceImpl.queryUsersByNetId(netSid);
		
		return new ModelAndView("jsp/account/list/user","userList",userList);
	}
	
	@RequestMapping("/modify")
	@ResponseBody
	public int modify(TbRsAccountInfo account){
		if(account == null){
			return 0;
		}
			
		int result = accountServiceImpl.updateAccount(account);
		
		return result;
	}
	
	@RequestMapping("/unLockorLock")
	@ResponseBody
	public int unLockorLock(String netSid,String status){
		if(StrUtil.isEmpty(netSid) || StrUtil.isEmpty(status)){
			return 0;
		}
			
		int result = accountServiceImpl.unLockorLock(netSid,status);
		
		return result;
	}
	
	@RequestMapping("/open")
	@ResponseBody
	public int open(TbRsAccountInfo tbRsAccountInfo){
		Date createDate = new Date();
		//手机邮箱查重
		int count = accountServiceImpl.queryUsers(tbRsAccountInfo);
		if(count > 0){
			return 0;
		}
		
		//添加资源方信息
		tbRsAccountInfo.setNetSid(RandomUtil.randomToStr());
		tbRsAccountInfo.setRank(FlypaasConstant.DEFUALT_RANK);
		tbRsAccountInfo.setPoint(FlypaasConstant.DEFUALT_POINT);
		tbRsAccountInfo.setStatus(FlypaasConstant.OPEN_STATUS);
		tbRsAccountInfo.setPassword(FlypaasConstant.DEFUALT_PWD);
		tbRsAccountInfo.setCreateDate(createDate);
		//添加用户信息
		TbRsUserInfo tbRsUserInfo = new TbRsUserInfo();
		tbRsUserInfo.setMobile(tbRsAccountInfo.getMobile());
		tbRsUserInfo.setEmail(tbRsAccountInfo.getEmail());
		tbRsUserInfo.setSid(RandomUtil.randomToStr());
		tbRsUserInfo.setUsername(tbRsAccountInfo.getUsername());
		tbRsUserInfo.setNetSid(tbRsAccountInfo.getNetSid());
		tbRsUserInfo.setRealname(tbRsAccountInfo.getRealname());
		tbRsUserInfo.setPassword(tbRsAccountInfo.getPassword());
		tbRsUserInfo.setCreateDate(createDate);
		tbRsUserInfo.setLoginTimes(FlypaasConstant.DEFUALT_LOGIN_TIMES);
		tbRsUserInfo.setStatus(Integer.valueOf(FlypaasConstant.OPEN_STATUS));
		//为用户添加管理员角色
		TbRsRoleUser tbRsRoleUser = new TbRsRoleUser();
		tbRsRoleUser.setRuId(RandomUtil.randomToInt());
		tbRsRoleUser.setSid(tbRsUserInfo.getSid());
		tbRsRoleUser.setRoleId(UserConstant.ROLE_MANAGER);
		
		//激活成功后  给用户余额表中添加数据  默认数据
		TbRsAccountBalance tbRsAccountBalance = new TbRsAccountBalance();
		tbRsAccountBalance.setBalance(FlypaasConstant.DEFUALT_BALANCE);
		tbRsAccountBalance.setCreateTime(createDate);
		tbRsAccountBalance.setEnableFlag(FlypaasConstant.OPEN_STATUS);
		tbRsAccountBalance.setNetSid(tbRsAccountInfo.getNetSid());
		tbRsAccountBalance.setId((long) RandomUtil.randomToInt());
		tbRsAccountBalance.setCreditBalance(0L);

		accountServiceImpl.openAccount(tbRsAccountInfo,tbRsUserInfo,tbRsAccountBalance,tbRsRoleUser);
		
		return 1;
		
	}
	
}
