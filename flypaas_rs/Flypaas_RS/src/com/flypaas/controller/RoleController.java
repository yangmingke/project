package com.flypaas.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.flypaas.model.TbRsRole;
import com.flypaas.model.TbRsRoleUser;
import com.flypaas.model.TbRsUserInfo;
import com.flypaas.service.RoleService;
import com.flypaas.service.RoleUserService;
import com.flypaas.service.UserInfoService;
import com.flypaas.util.RandomUtil;
/**
 * 
 * 当用户注册成功后给用户分配角色
 * @author win10
 *
 */
@Controller
@RequestMapping("/roleController")
public class RoleController {
	
	public static Logger logger = Logger.getLogger(RoleController.class);
	@Autowired
	private RoleUserService roleUserServiceImpl;
	@Autowired
	private RoleService roleServiceImpl;
	@Autowired
	private UserInfoService userInfoServiceImpl;
	
	
	@RequestMapping("/queryRole")
	public ModelAndView queryRole(HttpSession session,HttpServletRequest request){
		
		return new ModelAndView("redirect:/index.jsp");
	}
	
	
	/**
	 * 分配管理员角色给刚注册的用户
	 * 1.查出管理员的roleId
	 * 2.得到注册用户的sid
	 * 3.非配一个角色用户Id给注册用户
	 * 4.将roleId和sid插在角色用户关系表
	 * @param session
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/assignRole")
	public ModelAndView assignRole(HttpSession session,HttpServletRequest request,TbRsUserInfo tbRsUserInfo){
		
		TbRsRole tbRsRole = roleServiceImpl.queryRole("管理员");
		int rid = tbRsRole.getRoleId();
		String sid = request.getParameter("sid");
		int ruId = RandomUtil.randomToInt();
		TbRsRoleUser tbRsRoleUser = new TbRsRoleUser();
		tbRsRoleUser.setRuId(ruId);
		tbRsRoleUser.setRoleId(rid);
		tbRsRoleUser.setSid(sid);
		int flag = roleUserServiceImpl.assignRole(tbRsRoleUser);
		Map<String,Object> model = new HashMap<String,Object>();
		if(flag > 0){
			logger.info("分配成功...");
			tbRsUserInfo.setStatus(1);
			tbRsUserInfo.setSid(sid);
			userInfoServiceImpl.updateUserStatusBySid(sid);
			model.put("assignMessage", "注册成功");
			return new ModelAndView("user-register",model);
		}else{
			logger.info("分配失败...");
			model.put("assignMessage", "注册失败");
			return new ModelAndView("user-register",model);
		}
	}
}
