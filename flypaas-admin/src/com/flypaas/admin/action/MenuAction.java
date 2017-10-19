package com.flypaas.admin.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.service.MenuService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 菜单
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "header", location = "/WEB-INF/content/menu/header.jsp"),
		@Result(name = "sideMenu", location = "/WEB-INF/content/menu/sideMenu.jsp") })
public class MenuAction extends BaseAction {
	private static final long serialVersionUID = 346913635975674774L;
	@Autowired
	private MenuService menuService;

	/**
	 * 页面顶部1、2级菜单
	 * 
	 * @return
	 */
	@Action("/menu/header")
	public String header() {
		data = menuService.getHeaderMenu();
		return "header";
	}

	/**
	 * 页面左边3级菜单
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Action("/menu/sideMenu")
	public String sideMenu() {
		Object obj = StrutsUtils.getRequest().getAttribute("select_menu");
		System.out.println(obj);
		if (obj != null) {
			Map<String, Object> selectMenu = (Map<String, Object>) obj;
			System.out.println(selectMenu.size());
			if (selectMenu.get("menu3_id") != null) {
				data = menuService.getSideMenu(selectMenu.get("menu2_id").toString());
				System.out.println(data);
			}
		}
		return "sideMenu";
	}

}
