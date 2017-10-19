package com.network.monitor.util.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.network.monitor.constant.SysConstant;

/**
 * 权限控制工具类
 * 
 */
public class AuthorityUtils {

	/**
	 * 当前登录用户的sid、roleId保存在session中的key
	 */
	private static final String LOGIN_USER_ID = "LOGIN_USER_ID";
	private static final String LOGIN_USER_REALNAME = "LOGIN_USER_REALNAME";
	private static final String LOGIN_ROLE_ID = "LOGIN_ROLE_ID";

	/**
	 * 保存自动登录用户的id、roleId
	 * 
	 * @param request
	 */
	public static void setAutoLoginUser(HttpServletRequest request) {
		setLoginUser(request, SysConstant.SUPER_ADMIN_USER_ID, SysConstant.SUPER_ADMIN_USER_REALNAME);
	}

	/**
	 * 保存当前登录用户的sid、roleId
	 * 
	 * @param sid
	 * @param roleId
	 */
	public static void setLoginUser(String userId, String userName) {
		setLoginUser(StrutsUtils.getRequest(), userId, userName);
	}

	/**
	 * 保存当前登录用户的sid、roleId
	 * 
	 * @param request
	 * @param sid
	 * @param roleId
	 */
	public static void setLoginUser(HttpServletRequest request, String userId, String userName) {
		HttpSession session = request.getSession();
		session.setAttribute(LOGIN_USER_ID, userId);
		session.setAttribute(LOGIN_USER_REALNAME, userName);
	}

	/**
	 * 获取当前登录用户的sid
	 * 
	 * @return
	 */
	public static String getLoginUserId() {
		return getLoginUserId(StrutsUtils.getRequest());
	}

	/**
	 * 获取当前登录用户的sid
	 * 
	 * @param request
	 * @return
	 */
	public static String getLoginUserId(HttpServletRequest request) {
		String id = null;
		Object obj = request.getSession().getAttribute(LOGIN_USER_ID);
		if (obj != null) {
			id = obj.toString();
		}
		return id;
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
		Object obj = request.getSession().getAttribute(LOGIN_ROLE_ID);
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
		session.removeAttribute(LOGIN_USER_ID);
		session.removeAttribute(LOGIN_ROLE_ID);
		session.removeAttribute(LOGIN_USER_REALNAME);
	}

	/**
	 * 当前登录的用户名
	 * 
	 * @return
	 */
	public static final String getLoginRealName() {
		return (String) StrutsUtils.getRequest().getSession(true).getAttribute(LOGIN_USER_REALNAME);
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
		String sid = getLoginUserId(request);
		if (sid != null) {// 已经登录
			return true;
		}
		return false;
	}

}
