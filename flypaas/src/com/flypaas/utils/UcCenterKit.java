package com.flypaas.utils;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fivestars.interfaces.bbs.client.Client;
import com.fivestars.interfaces.bbs.util.XMLHelper;

public class UcCenterKit {
	static final Logger logger = LoggerFactory.getLogger(UcCenterKit.class);
	static final String SESSION_KEY ="SCRIPT_UC_CENTER";
	static final boolean isOnline ;
	static{
		String s = System.getProperty("bbs_isOnline","true");
		if("false".equals(s)){
			isOnline = false;
		}else{
			isOnline =true;
		}
	}
	public final static Object getScript(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		Object result = session.getAttribute(SESSION_KEY);
		if(null != result){
			session.removeAttribute(SESSION_KEY);
		}else{
			result = "";
		}
		return result;
	}
	public final static boolean login(HttpServletRequest request,String userName,String email,String password){
		if(!isOnline){
			return false;
		}
		if(StringUtils.isBlank(userName)||StringUtils.isBlank(email)||StringUtils.isBlank(password) ){
			logger.info("登录失败：username["+userName+"] email["+email+"] password["+password+"]");
			return false;
		}
		password = processPassword(password);
		try {
			Client e = new Client();
			String xml = e.uc_user_login(userName,password);
			LinkedList<String> rs = XMLHelper.uc_unserialize(xml);
			if(rs.size()>0){
				int $uid = Integer.parseInt(rs.get(0));
				if($uid > 0) {
					String $ucsynlogin = e.uc_user_synlogin($uid);
					String script_uc =$ucsynlogin;
					if(null != script_uc){
						request.getSession(true).setAttribute(SESSION_KEY, script_uc);
					}
					return true;
				}else if ($uid == -1){
					if(register(request,userName, email, password)){
						return login(request,userName, email,password);
					}
				}else{
					logger.info("Ucenter登录失败");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return false;
	}
	
	private static String processPassword(String password) {
		if(null != password){
			if (password.length() > 30){
				return password.substring(0,30);
			}
		}
		return password;
	}
	public final static boolean resetPwd(HttpServletRequest request,String userName,String email,String $oldpw,String $newpw){
		if(!isOnline){
			return false;
		}
		if(StringUtils.isBlank(userName)||StringUtils.isBlank(email)||StringUtils.isBlank($oldpw) ||StringUtils.isBlank($newpw) ){
			logger.info("修改密码失败：username["+userName+"] email["+email+"] $oldpw["+$oldpw+"] $newpw["+$newpw+"]");
			return false;
		}
		$oldpw = processPassword($oldpw);
		$newpw = processPassword($newpw);
		try {
			Client e = new Client();
			String xml = e.uc_user_edit(userName, $oldpw, $newpw, email, 1, "", "");
			if("1".equals(xml)){
				logger.info("更新成功！");
				return true;
			}else{
				logger.info("更新失败！");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return false;
	}
	public static void logout(HttpServletRequest request) {
		if(!isOnline){
			return ;
		}
		try {
			Client uc = new Client();
			String $ucsynlogout = uc.uc_user_synlogout();
			request.getSession(true).setAttribute(SESSION_KEY, $ucsynlogout);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	public static boolean register(HttpServletRequest request,String userName,String email,String password){
		if(!isOnline){
			return false;
		}
		if(StringUtils.isBlank(userName)||StringUtils.isBlank(email)||StringUtils.isBlank(password) ){
			logger.info("注册失败：username["+userName+"] email["+email+"] password["+password+"]");
			return false;
		}
		if(userName.length()<4 && userName.length() >15){
			logger.info("注册失败：username["+userName+"] email["+email+"] password["+password+"] username 长度要在 4 - 15 位内");
			return false;
		}
		password = processPassword(password);
		try {
			Client uc = new Client();
			String $returns = uc.uc_user_register(userName, password,email);
			int $uid = -99;
			if(StringUtils.isNotBlank($returns)){
				$uid = Integer.parseInt($returns);
			}
			if($uid <= 0) {
				if($uid == -1) {
					logger.info("用户名不合法");
				} else if($uid == -2) {
					logger.info("包含要允许注册的词语");
				} else if($uid == -3) {
					logger.info("用户名已经存在");
				} else if($uid == -4) {
					logger.info("Email 格式有误");
				} else if($uid == -5) {
					logger.info("Email 不允许注册");
				} else if($uid == -6) {
					logger.info("该 Email 已经被注册");
				}else if($uid == -99) {
					logger.info("注册失败,可能是网络异常,或无BBS环境!");
				} else {
					logger.info("未定义");
				}
			} else {
				logger.info("OK:"+$returns);
				return true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return false;
	}
}
