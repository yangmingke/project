package com.flypaas.controller.user;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.flypaas.annotation.Log;
import com.flypaas.constant.FlypaasConstant;
import com.flypaas.model.TbRsAccountInfo;
import com.flypaas.model.TbRsRole;
import com.flypaas.model.TbRsRoleUser;
import com.flypaas.model.TbRsUserInfo;
import com.flypaas.service.ResourceSideService;
import com.flypaas.service.RoleUserService;
import com.flypaas.service.UserInfoService;
import com.flypaas.service.user.UserService;
import com.flypaas.util.DateUtil;
import com.flypaas.util.RandomUtil;
import com.google.gson.Gson;
/**
 * 用户管理
 * 1.修改用户信息
 * 2.添加用户
 * 3.修改密码
 * 4.查看当前账号所有用户
 * 5.检测用户邮箱
 * 6.修改用户状态
 * 7.删除用户
 * 
 * @author win10
 *
 */
@Controller
@RequestMapping("/userController")
public class UserController {
	public static Logger logger = Logger.getLogger(UserController.class);
	public static Gson gson = new Gson(); 
	@Autowired
	private UserService userServiceImpl;
	@Autowired
	private RoleUserService roleUserServiceImpl;
	@Autowired
	private UserInfoService userInfoServiceImpl;
	@Autowired
	ResourceSideService resourceSideServiceImpl;
	/**
	 * 修改用户信息
	 * 
	 * 1.从session中获取sid和netsid
	 * 2.将页面数据赋给两个对象
	 * @param request
	 * @param session
	 * @return
	 */
	
	@RequestMapping("/updateUser")
	@ResponseBody
	@Log(name = "修改用户资料", type = "4")
	public Object updateUser(HttpServletRequest request,HttpSession session,TbRsAccountInfo tbRsAccountInfo,TbRsUserInfo tbRsUserInfo,String roleId){
		logger.info("修改用户信息---------------->>");
		ModelMap model = new ModelMap();
		TbRsAccountInfo tbRsAccountInfo2 = (TbRsAccountInfo) session.getAttribute("userSideSession");
		TbRsUserInfo tbRsUserInfo2 = (TbRsUserInfo) session.getAttribute("userSession");
		if("0".equals(roleId)){
			tbRsAccountInfo2.setName(tbRsAccountInfo.getName());
			tbRsAccountInfo2.setAddress(tbRsAccountInfo.getAddress());
			tbRsAccountInfo2.setChatNbr(tbRsAccountInfo.getChatNbr());
			tbRsAccountInfo2.setChatType(tbRsAccountInfo.getChatType());
			tbRsAccountInfo2.setCity(tbRsAccountInfo.getCity());
			tbRsAccountInfo2.setProvince(tbRsAccountInfo.getProvince());
			tbRsAccountInfo2.setCompanyNbr(tbRsAccountInfo.getCompanyNbr());
			tbRsAccountInfo2.setEmail(tbRsAccountInfo.getEmail());
			tbRsAccountInfo2.setIdNbr(tbRsAccountInfo.getChatNbr());
			tbRsAccountInfo2.setIdType(tbRsAccountInfo.getIdType());
			tbRsAccountInfo2.setIsPrivateNet(tbRsAccountInfo.getIsPrivateNet());
			tbRsAccountInfo2.setLegalPerson(tbRsAccountInfo.getLegalPerson());
			tbRsAccountInfo2.setMobile(tbRsAccountInfo.getMobile());
			tbRsAccountInfo2.setNational(tbRsAccountInfo.getNational());
			tbRsAccountInfo2.setOrgId(tbRsAccountInfo.getOrgId());
			tbRsAccountInfo2.setUpdateDate(new Date());
			tbRsAccountInfo2.setUsername(tbRsAccountInfo.getUsername());
			tbRsAccountInfo2.setWebSite(tbRsAccountInfo.getWebSite());
			tbRsAccountInfo2.setRealname(tbRsAccountInfo.getRealname());
			tbRsAccountInfo2.setRank(tbRsAccountInfo.getRank());
			tbRsAccountInfo2.setPoint(tbRsAccountInfo.getPoint());
			tbRsAccountInfo2.setIdNbr(tbRsAccountInfo.getIdNbr());
			tbRsAccountInfo2.setAlipayAccount(tbRsAccountInfo.getAlipayAccount());
			tbRsAccountInfo2.setAlipayName(tbRsAccountInfo.getAlipayName());
		}else{
			tbRsAccountInfo2 = null;
		}
		
		tbRsUserInfo2.setAddress(tbRsUserInfo.getAddress());
		tbRsUserInfo2.setChatNbr(tbRsUserInfo.getChatNbr());
		tbRsUserInfo2.setChatType(tbRsUserInfo.getChatType());
		tbRsUserInfo2.setCity(tbRsUserInfo.getCity());
		tbRsUserInfo2.setEmail(tbRsUserInfo.getEmail());
		tbRsUserInfo2.setMobile(tbRsUserInfo.getMobile());
		tbRsUserInfo2.setNational(tbRsUserInfo.getNational());
		tbRsUserInfo2.setProvince(tbRsUserInfo.getProvince());
		tbRsUserInfo2.setRealname(tbRsUserInfo.getRealname());
		tbRsUserInfo2.setUpdateDate(new Date());
		tbRsUserInfo2.setUsername(tbRsUserInfo.getUsername());
		
		int temp = userInfoServiceImpl.updateByPrimaryKeySelective(tbRsUserInfo2,tbRsAccountInfo2,null);
		if(temp>0){
			logger.info("修改成功");
			session.setAttribute("userSession", tbRsUserInfo2);
			if("0".equals(roleId)){
				session.setAttribute("userSideSession", tbRsAccountInfo2);
			}
			model.put("message", "修改成功");
			return gson.toJson(1);
		}else{
			logger.info("修改失败");
			model.put("message", "修改失败");
			return gson.toJson(0);
		}
		
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @param session
	 * @param tbRsUserInfo
	 * @return
	 */
	@RequestMapping("/editPwd")
	@Log(name = "修改密码", type = "4")
	public ModelAndView editPwd(HttpServletRequest request,HttpSession session,TbRsUserInfo tbRsUserInfo,ModelMap model){
		logger.info("进入修改密码Controller");
		tbRsUserInfo =  (TbRsUserInfo) session.getAttribute("userSession");
		//String sid = user.getSid();
		String newPwd = request.getParameter("newPwd1");
		String oldPwd1 = request.getParameter("oldPwd1");
		String oldPwd2 = request.getParameter("oldPwd2");
		if(tbRsUserInfo.getPassword().equals(oldPwd1) && oldPwd1.equals(oldPwd2)){
			tbRsUserInfo.setPassword(newPwd);
			int flag = userServiceImpl.editUserPwd(tbRsUserInfo);
			if(flag>0){
				logger.info("修改成功");
				session.setAttribute("userSession",tbRsUserInfo);
//				session.removeAttribute("userSideSession");
				model.put("msg", "修改成功");
				return new  ModelAndView("jspPage/user/user-password-edit",model);
			}else{
				logger.info("修改失败");
				return new  ModelAndView("500.jsp");
			}
		}else{
			logger.info("修改失败");
			model.put("msg2", "原密码错误");
			return new  ModelAndView("jspPage/user/user-password-edit",model);
		}
	}
	/**
	 * 修改用户密码
	 * @param request
	 * @param session
	 * @param tbUser
	 * @return
	 */
	@RequestMapping("/editUserPwd")
	@ResponseBody
	public String editUserPwd(HttpServletRequest request){
		logger.info("进入修改密码用户Controller");
		TbRsUserInfo tbUser = new TbRsUserInfo();
		String userId = request.getParameter("sId");
		String newPwd = request.getParameter("newPwd");
		tbUser.setSid(userId);
		tbUser.setPassword(newPwd);
		int temp = userServiceImpl.editUserPwd(tbUser);
		if(temp > 0){
			logger.info("修改成功");
			return gson.toJson(1);
		}else{
			logger.info("修改失败");
			return gson.toJson(0);
		}
	}
	
	
	/**
	 * 查看 用户列表
	 * @param request
	 * @param session
	 * @param tbRsUserInfo
	 * @return
	 */
	@RequestMapping("/queryUserList")
	public ModelAndView queryUserList(HttpServletRequest request,HttpSession session,TbRsUserInfo tbRsUserInfo,ModelMap model){
		logger.info("查询用户列表Controller");
		TbRsAccountInfo tbRsAccountInfo = (TbRsAccountInfo) session.getAttribute("userSideSession");	
		
		//查询当前账号所有用户
		List<TbRsUserInfo> list = userServiceImpl.queryUserList(tbRsAccountInfo.getNetSid());
		//查询当前账号用户数量
//		int count = userServiceImpl.queryCount(tbRsAccountInfo.getNetSid());
		int count = list == null ? 0 : list.size();
		logger.info(list.get(0).getCreateDate());
		model.addAttribute("userList", list);
		model.addAttribute("userCount",count);
		return new ModelAndView("jspPage/user/user-list",model);
	}
	
	/**
	 * 管理员添加用户
	 * @param request
	 * @param session
	 * @param tbRsUserInfo
	 * @param tbRsAccountInfo
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/addUser")
	@ResponseBody
	@Log(name = "添加用户", type = "1")
	public String addUser(HttpServletRequest request,HttpSession session,TbRsUserInfo tbRsUserInfo,TbRsAccountInfo tbRsAccountInfo,ModelMap model) throws ParseException{
		logger.info("添加用户Controller");
		//从页面得到RoleID
		TbRsRoleUser roleUser = new TbRsRoleUser();
		int roleId = Integer.valueOf(request.getParameter("userRole"));
		
		//从session中得到neiSids
		tbRsAccountInfo = (TbRsAccountInfo) session.getAttribute("userSideSession");
		tbRsUserInfo.setNetSid(tbRsAccountInfo.getNetSid());
		tbRsUserInfo.setCreateDate(new Date());
		tbRsUserInfo.setStatus(1);
		tbRsUserInfo.setLoginTimes(0);
		tbRsUserInfo.setPassword(FlypaasConstant.password);
		//随机分配一个sid
		tbRsUserInfo.setSid(RandomUtil.randomToStr());
		int flag = userServiceImpl.addUser(tbRsUserInfo);
		
		if(flag>0){
			logger.info("添加成功");
			roleUser.setRuId(RandomUtil.randomToInt());
			roleUser.setRoleId(roleId);
			roleUser.setSid(tbRsUserInfo.getSid());
			int temp = roleUserServiceImpl.addUserRole(roleUser);
			if(temp>0){
				return gson.toJson(1);
			}else{
				return gson.toJson(0);
			}
		}else{
			logger.info("添加失败");
			return gson.toJson(0);
		}
	}
	
	/**
	 * 检测邮箱是否已经被注册
	 * @param tbRsUserInfo
	 * @return
	 */
	@RequestMapping("/checkEmail")
	@ResponseBody
	public String checkEmail(HttpServletRequest request){
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		TbRsUserInfo tbRsUserInfo = userInfoServiceImpl.queryUserInfo2(email);
		if(tbRsUserInfo!=null){
			return gson.toJson(0);
		}
		TbRsUserInfo user = userInfoServiceImpl.queryUserByMobile(mobile);
		if(user!=null){
			return gson.toJson(2);
		}
		
		return gson.toJson(1);
	}
	
	/**
	 * 管理员修改用户状态
	 * @param request
	 * @return
	 */
	@RequestMapping("/editUserStatus")
	@ResponseBody
	@Log(name = "修改用户状态", type = "4")
	public String editUserStatus(HttpServletRequest request,TbRsUserInfo tbRsUserInfo){
		logger.info("修改用户状态Controller");
		String sid = request.getParameter("sid");
		request.getSession().getAttribute("userSideSession");
		int status1 = Integer.valueOf(request.getParameter("status"));
		int status = 0;
		if(status1 ==1){
			
			status = 0;
		}
		if(status1 ==0){
			status = 1;
		}
		tbRsUserInfo.setStatus(status);
		tbRsUserInfo.setSid(sid);
		int temp = userServiceImpl.updateByPrimaryKeySelective(tbRsUserInfo);
		if(temp > 0){
			logger.info("修改成功");
			return gson.toJson(1);
		}else{
			logger.info("修改失败");
			return gson.toJson(0);
		}
	}
	
	/**
	 * 删除选中的用户
	 * @param request
	 * @return
	 */
	@RequestMapping("/delUser")
	@ResponseBody
	@Log(name = "删除用户", type = "5")
	public String delUser(HttpServletRequest request){
		logger.info("删除用户Controller");
		String sid = request.getParameter("sid");
		
		int temp = userServiceImpl.delUser(sid);
		if(temp > 0){
			logger.info("删除成功");
			return gson.toJson(1);
		}else{
			logger.info("删除失败");
			return gson.toJson(0);
		}
	}
	
	/**
	 * 按sid查询用户的信息以及角色
	 * @param request
	 * @param tbRsUserInfo
	 * @return
	 */
	@RequestMapping("/queryUserInfoById")
	public ModelAndView queryUserInfoById(HttpServletRequest request,TbRsUserInfo tbRsUserInfo){
		logger.info("按sid查询用户的信息以及角色");
		Map<String, Object> model = new HashMap<String, Object>();
		TbRsUserInfo tbRsUserInfo2 = (TbRsUserInfo) request.getSession().getAttribute("userSession");
		String sid = tbRsUserInfo2.getSid();
		tbRsUserInfo = userServiceImpl.queryUserInfoById(sid);
		logger.info(tbRsUserInfo);
		String netSid = tbRsUserInfo.getNetSid();
		List<TbRsRole> roleList = new ArrayList<TbRsRole>(tbRsUserInfo.getRole());
		TbRsRole role = roleList.get(0);
		if(role.getRoleId() == 0){
			TbRsAccountInfo tbRsAccountInfo = resourceSideServiceImpl.selectByPrimaryKey(netSid);
			model.put("userSide", tbRsAccountInfo);
		}
		model.put("user", tbRsUserInfo);
		model.put("roleId", role.getRoleId());
		return new ModelAndView("jspPage/user/user-loginUser",model);
	}
	
	/**
	 * 按sid查询用户的信息以及角色
	 * @param request
	 * @param tbRsUserInfo
	 * @return
	 */
	@RequestMapping("/queryUserInfo")
	@ResponseBody
	public Object queryUserInfo(HttpServletRequest request,TbRsUserInfo tbRsUserInfo){
		logger.info("按sid查询用户的信息以及角色");
		String sid = request.getParameter("sid");
		tbRsUserInfo = userServiceImpl.queryUserInfoById(sid);
		logger.info(tbRsUserInfo);
		return gson.toJson(tbRsUserInfo);
	}
	
	/**
	 * 管理员修改用户信息后提交到editUser
	 * @param request
	 * @param tbRsUserInfo
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/editUser")
	@ResponseBody
	@Log(name = "管理员修改用户信息", type = "4")
	public String editUser(HttpServletRequest request,TbRsUserInfo tbRsUserInfo,TbRsRole tbRsRole) throws ParseException{
		logger.info("按sid查询用户的信息以及角色");
		String sid = request.getParameter("sid");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String realname = request.getParameter("realname");
//		int chatType = Integer.valueOf(request.getParameter("chatType"));
		String chatNbr = request.getParameter("chatNbr");
		String address = request.getParameter("address");
		int roleId = Integer.valueOf(request.getParameter("roleId"));
		
		TbRsUserInfo userMail = userInfoServiceImpl.queryUserInfo2(email);
		if(userMail!=null && !userMail.getSid().equals(sid)){
			return gson.toJson(0);
		}
		TbRsUserInfo userMobile = userInfoServiceImpl.queryUserByMobile(mobile);
		if(userMobile!=null && !userMobile.getSid().equals(sid)){
			return gson.toJson(2);
		}
		
		
		//按sid查询用户信息
		tbRsUserInfo = userServiceImpl.queryUserInfoById(sid);
		//按roleId查询角色信息
//		tbRsRole =  roleServiceImpl.selectByPrimaryKey(roleId);
//		Set<TbRsRole> set = new HashSet<TbRsRole>();
//		set.add(tbRsRole);
		tbRsUserInfo.setAddress(address);
		tbRsUserInfo.setChatNbr(chatNbr);
//		tbRsUserInfo.setChatType(chatType);
		tbRsUserInfo.setEmail(email);
		tbRsUserInfo.setMobile(mobile);
		tbRsUserInfo.setRealname(realname);
		tbRsUserInfo.setUsername(username);
		tbRsUserInfo.setUpdateDate(DateUtil.getCurrentDate());
//		tbRsUserInfo.setRole(set);
		TbRsRoleUser tbRsRoleUser = new TbRsRoleUser();
		tbRsRoleUser.setSid(sid);
		tbRsRoleUser.setRoleId(roleId);
		
		int temp = userServiceImpl.updateSelective(tbRsUserInfo,tbRsRoleUser);
		if(temp > 0){
			logger.info("修改成功");
			return gson.toJson(1);
		}else{
			logger.info("修改失败");
			return gson.toJson(0);
		}
	}
	
	/**
	 * 用户退出
	 * @param session
	 * @return
	 */
	
	@RequestMapping("/userExit")
	public String  userExit(HttpSession session){
		logger.info("用户退出--------->>");
		session.removeAttribute("userSession");
		session.removeAttribute("userSideSession");
		return ("redirect:/login.jsp");
	}
}
