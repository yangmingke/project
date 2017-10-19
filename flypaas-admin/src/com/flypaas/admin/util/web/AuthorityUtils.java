package com.flypaas.admin.util.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.flypaas.admin.constant.RoleConstant;
import com.flypaas.admin.constant.SysConstant;

/**
 * 权限控制工具类
 * 
 * @author xiejiaan
 */
public class AuthorityUtils {

	/**
	 * 当前登录用户的sid、roleId保存在session中的key
	 */
	private static final String login_sid = "login_sid";
	private static final String login_role_id = "login_role_id";

	/**
	 * 保存自动登录用户的sid、roleId
	 * 
	 * @param request
	 */
	public static void setAutoLoginUser(HttpServletRequest request) {
		setLoginUser(request, SysConstant.super_admin_sid, RoleConstant.role_super_admin);
	}

	/**
	 * 保存当前登录用户的sid、roleId
	 * 
	 * @param sid
	 * @param roleId
	 */
	public static void setLoginUser(String sid, Integer roleId) {
		setLoginUser(StrutsUtils.getRequest(), sid, roleId);
	}

	/**
	 * 保存当前登录用户的sid、roleId
	 * 
	 * @param request
	 * @param sid
	 * @param roleId
	 */
	public static void setLoginUser(HttpServletRequest request, String sid, Integer roleId) {
		HttpSession session = request.getSession();
		session.setAttribute(login_sid, sid);
		session.setAttribute(login_role_id, roleId);
	}

	/**
	 * 获取当前登录用户的sid
	 * 
	 * @return
	 */
	public static String getLoginSid() {
		return getLoginSid(StrutsUtils.getRequest());
	}

	/**
	 * 获取当前登录用户的sid
	 * 
	 * @param request
	 * @return
	 */
	public static String getLoginSid(HttpServletRequest request) {
		String sid = null;
		Object obj = request.getSession().getAttribute(login_sid);
		if (obj != null) {
			sid = obj.toString();
		}
		return sid;
	}

	/**
	 * 获取当前登录用户的roleId
	 * 
	 * @return
	 */
	public static Integer getLoginRoleId() {
		return getLoginRoleId(StrutsUtils.getRequest());
	}

	/**
	 * 获取当前登录用户的roleId
	 * 
	 * @param request
	 * @return
	 */
	public static Integer getLoginRoleId(HttpServletRequest request) {
		Integer roleId = null;
		Object obj = request.getSession().getAttribute(login_role_id);
		if (obj != null) {
			roleId = Integer.parseInt(obj.toString());
		}
		return roleId;
	}

	/**
	 * 退出当前登录用户
	 * 
	 */
	public static void setLogoutUser() {
		setLogoutUser(StrutsUtils.getRequest());
	}

	/**
	 * 退出当前登录用户
	 * 
	 * @param request
	 */
	public static void setLogoutUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute(login_sid);
		session.removeAttribute(login_role_id);
	}

	/**
	 * 判断当前是否已登录
	 * 
	 * @return
	 */
	public static boolean isLogin() {
		return isLogin(StrutsUtils.getRequest());
	}

	/**
	 * 判断当前是否已登录
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isLogin(HttpServletRequest request) {
		String sid = getLoginSid(request);
		if (sid != null) {// 已经登录
			return true;
		}
		return false;
	}

}
