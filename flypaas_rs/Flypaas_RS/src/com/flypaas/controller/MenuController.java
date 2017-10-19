package com.flypaas.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.flypaas.model.TbRsAccountInfo;
import com.flypaas.model.TbRsMenu;
import com.flypaas.model.TbRsUserInfo;
import com.flypaas.service.MenuService;
import com.flypaas.util.SysConstant;
import com.google.gson.Gson;
/**
 * 查询角色菜单
 * @author win10
 *
 */
@Controller
@RequestMapping("/menuController")
public class MenuController  {
	public static Logger logger = Logger.getLogger(MenuController.class);
	private static Gson gson = new Gson();  
			
	@Autowired
	public MenuService menuServiceImpl;
	
	/**
	 * 登录成功后进入该方法  查询该账单的菜单
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryMenu")
	public ModelAndView queryMenu(HttpSession session,HttpServletRequest request){
		logger.info("进入查询菜单Controller");
		TbRsUserInfo user = (TbRsUserInfo) session.getAttribute("userSession");
		List<TbRsMenu> menuList1 = menuServiceImpl.queryMenu_1(user.getSid());
		List<TbRsMenu> menuList2 = menuServiceImpl.queryMenu_2(user.getSid());
		TbRsAccountInfo accountInfo =(TbRsAccountInfo) session.getAttribute("userSideSession");
		String isPrivateNet = accountInfo.getIsPrivateNet();
		String routeArea = accountInfo.getNetArea();
		List<Integer> removeMenu;
		if(isPrivateNet != null && "1".equals(isPrivateNet) && StringUtils.isNotEmpty(routeArea)){//开启独立域
			removeMenu = SysConstant.removePublicNetMenu;
		}else{//公共域
			removeMenu = SysConstant.removePrivateNetMenu;
		}
		for(int i = 0; i < menuList2.size(); i++){//移除相对应的域菜单
			TbRsMenu menuLev2 = menuList2.get(i);
			int menuId = menuLev2.getMenuId();
			if(removeMenu.contains(menuId)){
				menuList2.remove(menuLev2);
				i--;
			}
		}
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("userMenu1", menuList1);
		model.put("userMenu2", menuList2);
		if(menuList1 == null){
			return new ModelAndView("403");
		}else{
			return new ModelAndView("index",model);
		}
	}
	
	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		MenuController.logger = logger;
	}

	public static Gson getGson() {
		return gson;
	}

	public static void setGson(Gson gson) {
		MenuController.gson = gson;
	}

	

	public MenuService getMenuServiceImpl() {
		return menuServiceImpl;
	}

	public void setMenuServiceImpl(MenuService menuServiceImpl) {
		this.menuServiceImpl = menuServiceImpl;
	}
	
	
	

}
