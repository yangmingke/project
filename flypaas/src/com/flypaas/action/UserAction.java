package com.flypaas.action;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.constant.AuthConstant;
import com.flypaas.constant.AuthConstant.AuthType;
import com.flypaas.constant.SysConstant;
import com.flypaas.constant.UserConstant;
import com.flypaas.entity.AcctBalance;
import com.flypaas.entity.AcctPackageRel;
import com.flypaas.entity.Application;
import com.flypaas.entity.City;
import com.flypaas.entity.Client;
import com.flypaas.entity.Package;
import com.flypaas.entity.Params;
import com.flypaas.entity.Province;
import com.flypaas.entity.Remind;
import com.flypaas.entity.TbFlypaasUser;
import com.flypaas.entity.TestNumber;
import com.flypaas.entity.UserLog;
import com.flypaas.entity.UserMsg;
import com.flypaas.httpclient.impl.RefreshRedis;
import com.flypaas.rest.RestClient;
import com.flypaas.utils.AuthUtils;
import com.flypaas.utils.DateUtil;
import com.flypaas.utils.Des3Utils;
import com.flypaas.utils.JsonUtil;
import com.flypaas.utils.MD5;
import com.flypaas.utils.MD5Util;
import com.flypaas.utils.StrUtil;
import com.flypaas.utils.SysConfig;

/**
 * @author chenxijun
 * @version
 */
@Controller
@Scope("prototype")
@Results({
	@Result(name="toLogin",location="/front/log.jsp"),
	@Result(name="agentUserlogout",location="/front/log_out.jsp"),
//	@Result(name="toSignSuc",location="/front/reg_step1.jsp"),
	@Result(name="toSignSuc",location="/front/reg1-1.jsp"),
	@Result(name="toExtEmailSuc",location="/front/reg1-2.jsp"),
//	@Result(name="vmailExtSuc",location="/front/reg1-2.jsp"),
	@Result(name="vmailExtSuc",location="/front/reg1-2.jsp"),
	@Result(name="addUserSuc",type="redirectAction",location="toExtEmail",params={"email","${email}","sid","${sid}"}),
	@Result(name="vmailSucView",type="redirectAction",location="vmailExt",params={"email","${vemail}"}),
	@Result(name="logoutSuc",location="/front/index_new2.jsp"),
	@Result(name="userCenter",location="/page/user/developer_info.jsp"),
	@Result(name="account",location="/page/account.jsp"),
	@Result(name="restTokenSuc",type="redirectAction",location="account"),
	@Result(name="oauthSuc",type="redirectAction",location="userCenter"),
	@Result(name="resetPwdMailSuccess",type="redirectAction",location="toExtMailServer",params={"email","${email}"}),
	@Result(name="toExtMailServerSuc",location="/front/forget_pwd_submit.jsp"),
	@Result(name="resetPwdSuc",location="/front/reset_pwd_success.jsp"),
	@Result(name="viewResetPwd",location="/front/reset_pwd.jsp"),
	@Result(name="index",location="/front/index_new.jsp"),
	@Result(name="verifyMailOptSuc",location="/front/reg3.jsp"),
//	@Result(name="verifyMailOptSuc",location="/front/verifySuc.jsp"),
//	@Result(name="verifyMailSuc",location="/front/verifyMail.jsp"),
	@Result(name="verifyMailSuc",location="/front/reg2.jsp"),
	@Result(name="verifyMailTimeOut",type="redirectAction",location="sendverifyMail",params={"sid","${sid}"}),
	@Result(name="error",location="/front/verify_error.jsp"),
	@Result(name="sendMail",type="redirectAction",location="toExtEmail",params={"email","${email}","sid","${sid}"}),
	@Result(name="modifyPage",location="/page/user/developer_info_modify.jsp"),
	@Result(name="modifySuc",type="redirectAction",location="userCenter",params={"resultCode","${resultCode}"}),
	@Result(name="correctPwdPage",location="/page/user/correct_pwd.jsp"),
	@Result(name="remindSuc",type="redirectAction",location="userCenter"),
	@Result(name="verifyMobileSuc",location="/front/reg3.jsp"),
	@Result(name="forgetPwd",location="/front/forget_pwd.jsp"),
	@Result(name="authUrl",type="redirect",location="${authUrl}"),
	@Result(name="authSuccess",type="redirectAction",location="../user/account"),
	@Result(name="authLoginRedirect",type="redirectAction",location="../auth/login"),
	@Result(name="authLogin",location="/front/auth_login.jsp"),
	@Result(name="viewResetPwd21",type="redirectAction",location="resetPwdTiming"),
	@Result(name="sessionPacketLossPage",location="/page/session/session_lost.jsp"),
	@Result(name="redirectUrl",type="redirect",location="${url}")
})
public class UserAction extends BaseAction {
	private TbFlypaasUser user;
	private Remind remind;
	private UserMsg userMsg;
	private String url;  

	private List<TbFlypaasUser> userList;
	private List<Province> provinceList;
	private List<Params> remindParamsList ;
	private Package pck;
	private String vemail;
	private String vmobile;
	private String vuserName;
	private String sid;
	private String email;
	private AcctBalance acctBalance;
	//登录
	private String userid;
	private String password;
	private String agentId;
	//邮件验证参数
	private String vcode;
	//省编号
	private String provinceId;
	//省名称
	private Province province;
	//城市名称
	private City ccity;
	//昨日消费
	private String ysdConsume;
	
	private Logger log = LoggerFactory.getLogger(UserAction.class);
	//上线app个数
	private int onLineAppCount ;
	private Map<String, Object> activeCount;
	
	private String authUrl;		//第三方账号登录：授权页面
	private String authType;	//第三方账号登录：类型
	private String authId;		//第三方账号登录：用户id
	
	private String viewTokenCode;
	private String resetTokenType;
	
	private String sign;
	
	private String resultCode;
	
	private String datetime;
	
	private String cookieId;
	
	private List<Application> appList;
	
	private String appSid;
	
	private Map<String, Object> sessionLoss;
	
	private String packetLossTime;
	
	private String resultJson;
	
	@Action("/user/toLogin")
	public String toLogin(){
		return "toLogin";
	}
	@Action("/user/forgetPwd")
	public String forgetPwd(){
		return "forgetPwd";
	}
	/*-------------------------登陆验证--------------------------------*/
	@Action("/user/login")
	public void login() {
		String errorCode = login(userid, password);
		if(StringUtils.isNotEmpty(agentId)){
			errorCode = "loginCallback"+"({\"code\":\""+errorCode+"\"})";
		}
		printMsg(errorCode);
	}
	
	@Action("/user/accountLogin")
	public String accountLogin() {
		password = MD5.md5(password);
		login(userid, password);
		return account();
	}
	
	/**
	 * 登陆验证
	 * @param userid 手机号或邮箱
	 * @param password 密码
	 * @return 页面错误提示
	 */
	public String login(String userid, String password) {
			String errorCode = null; 
			password = MD5.md5(password);
			boolean bo = userid.contains("@");
			TbFlypaasUser loginUser = new TbFlypaasUser();
			String loginUserName = null ;
			if(bo){
				loginUser.setEmail(userid);
				loginUserName = loginUser.getEmail();
			}else{
				loginUser.setMobile(userid);
				loginUserName = loginUser.getMobile();
			}
			user = userService.findeUser(loginUser);
			log.info("用户信息为:"+user);
			//用户名不存在
			if(user==null){
				log.info("------------------登录失败 用户不存在！----------------");
				setCodeErrorCount();
				errorCode = UserConstant.USERNAME_FAIL+"|"+""+"|"+getCodeErrorCount() ;
				return errorCode;
			}
			//未激活
			if(UserConstant.STATUS_0.equals(user.getStatus())){
				log.info("------------------登录失败 用户未激活！----------------");
				setCodeErrorCount();
				if(password.equals(user.getPassword())){
					errorCode = UserConstant.VERIFY_FAIL+"|"+user.getSid()+"|"+getCodeErrorCount();
				}else{
					errorCode = UserConstant.PASSWORD_FAIL+"|"+""+"|"+getCodeErrorCount() ;
				}
				return errorCode;
			}
			//用户失效或管理员
			if(UserConstant.STATUS_6.equals(user.getStatus())||UserConstant.USER_TYPE_3.equals(user.getUserType())){
				log.info("------------------登录失败 用户状态失效！----------------");
				setCodeErrorCount();
				errorCode = UserConstant.USER_INVALID+"|"+""+"|"+getCodeErrorCount();
				return errorCode;
			}
			//是否被锁定
			if(UserConstant.STATUS_5.equals(user.getStatus())){
				log.info("------------------用户处于被锁定状态！----------------");
				log.info("------------------开始判断是否自动解锁！----------------");
				Date updateDate = user.getUpdateDate();
				long result = DateUtil.compare(DateUtil.getCurrentDate(), updateDate, SysConstant.FORBID_TIME);
				if(result>0){
					log.info("------------------时间未到自动解锁时间！----------------");
					errorCode = UserConstant.USER_FORBID+"|"+""+"|"+getCodeErrorCount()  ;
					return errorCode;
				}else{
					log.info("------------------时间已满24小时自动解锁！----------------");
					TbFlypaasUser u = new TbFlypaasUser();
					u.setSid(user.getSid());
					u.setStatus(UserConstant.STATUS_1);
					update(u);
					//removeTempSession();
					user.setStatus(UserConstant.STATUS_1);
				}
			}
			//成功
			if (password.equals(user.getPassword())) {
				if(agentId == null && user.getSuperSid() != null){
					log.info("------------------登录失败 代理商ID异常！----------------");
					errorCode = UserConstant.SUPERID_FAIL+"|"+""+"|"+getCodeErrorCount();
					return errorCode;
				}
				if(agentId !=null && !agentId.equals(user.getSuperSid())){
					log.info("------------------登录失败 代理商ID异常！----------------");
					errorCode = UserConstant.SUPERID_FAIL+"|"+""+"|"+getCodeErrorCount();
					return errorCode;
				}
				int resetCount = otherService.getResetPwdCount(user.getSid());
				if(resetCount==0){
					session.setAttribute("viewResetPwd21_email", user.getEmail());
					return "viewResetPwd21|"+user.getEmail()+"|a";
				}
				if(StringUtils.isNotEmpty(user.getSuperSid())){
					TbFlypaasUser agent = userService.findeUserById(agentId);
					if(!"1".equals(agent.getStatus())){
						log.info("------------------登录失败 代理商被锁定！----------------");
						errorCode = UserConstant.AGENT_LOCK+"|"+""+"|"+getCodeErrorCount();
						return errorCode;
					}
					if(0 == agent.getIsProxy()){
						log.info("------------------登录失败 代理商资格已取消！----------------");
						errorCode = UserConstant.AGENT_FAIL+"|"+""+"|"+getCodeErrorCount();
						return errorCode;
					}
					user.setAgentUrl(agent.getAgentUrl());
					user.setAgentLogo(agent.getAgentLogo());
				}
				log.info("------------------密码正确.开始登录！----------------");
				int count = userMsgService.getCountMsg(user.getSid());
				user.setCountMsg(count);
				recordSession(user);
				removeTempSession(loginUserName);
				TbFlypaasUser u = new TbFlypaasUser();
				u.setSid(user.getSid());
				u.setLoginTimes(user.getLoginTimes()==null?1:user.getLoginTimes()+1);
				String e = user.getEmail();
				String userName = user.getUserName();
				if(null == userName&&e.indexOf("@") > -1){
					userName = e.substring(0,e.indexOf("@"));
					if(userName.length() < 4){
						userName += "user";
					}else if (userName.length() > 15){
						userName = userName.substring(15);
					}
					u.setUserName(userName);
				}
				if(StringUtils.isNotBlank(authType) && StringUtils.isNotBlank(authId)){
					u.setAuthType(authType);
					u.setAuthId(authId);
				}
				update(u);
				errorCode = UserConstant.LOG_SUCCESS+"|"+user.getSid() ;
				log.info("------------------登录完成！----------------");
				//UcCenterKit.login(request,userName,user.getEmail(), password); by yangmingke
				//将页面超时返回URL存入cookie
				String url = SysConfig.getInstance().getProperty("host")+"/front/index_new2.jsp";
				if(StringUtils.isNotEmpty(user.getAgentUrl())){
					url = user.getAgentUrl().contains("http://") || user.getAgentUrl().contains("https://")? user.getAgentUrl() : "http://" + user.getAgentUrl();
				}
				Cookie cookie = new Cookie(SysConfig.getInstance().getProperty("return_url"),url);
				cookie.setPath("/");
				cookie.setMaxAge(60*60*24);
				response.addCookie(cookie);
				return errorCode;
			}
			//密码错误
			else{
				log.info("------------------登录失败  密码错误！----------------");
				setCodeErrorCount();
				setErrorCount(loginUserName);
				forbidLoginControl(loginUserName);
				errorCode = UserConstant.PASSWORD_FAIL+"|"+""+"|"+getCodeErrorCount();
				return errorCode;
			}
	}
	/*----------------------------强制修改密码--------------------------------*/
	@Action("/user/resetPwdTiming")
	public String resetPwdTiming(){
		Object obj = session.getAttribute("viewResetPwd21_email");
		if(!StrUtil.isEmpty(obj)){
			if(!obj.toString().equals(email)){
				StrUtil.writeMsg(response, "非法的邮箱", null);
				return null ;
			}
		}
		user = new TbFlypaasUser();
		user.setEmail(email);
		user = userService.findeUser(user);
		Map<String, Object> map = otherService.getAuthFile(user.getSid());
		vcode = map.get("vcode").toString();
		session.setAttribute("rdmCode",StrUtil.toHMACSHA(SysConstant.sidBaseString + StrUtil.getUUID(), SysConstant.KEY));
		session.setAttribute("email", email);
		return "viewResetPwd" ;
	}
	/*----------------------------退出--------------------------------*/
	@Action("/user/logout")
	public String logout(){
		TbFlypaasUser u = (TbFlypaasUser) session.getAttribute("user");
		session.removeAttribute("user");
		session.removeAttribute("view_token_auth");
//		UcCenterKit.logout(request);	by yangmingke
		String fr = request.getParameter("fr");
		if(null != fr){
			try {
				response.sendRedirect(fr);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(StringUtils.isNotEmpty(u.getAgentUrl())){
			url = u.getAgentUrl().contains("http://") || u.getAgentUrl().contains("https://")? u.getAgentUrl() : "http://" + u.getAgentUrl();
			return "redirectUrl";
		}
		if(StringUtils.isNotEmpty(u.getSuperSid())){
			return "agentUserlogout";
		}
		
		return "logoutSuc";
	}
	
	
	@Action("/user/toSign")
	public String toSign(){
		return "toSignSuc";
	}
	
	/*----------------------------手机认证--------------------------------*/
	@Action("/user/verifyMobile")
	public String verifyMobile(){
		if(StrUtil.isEmpty(user)){
			StrUtil.writeMsg(response, "参数错误", null);
			return null ;
		}
		user.setSid(getSessionUser().getSid());
		update(user);
		user = getUserInfo(user.getSid(),null,user.getMobile());
		getSessionUser().setMobile(user.getMobile());
		return "verifyMobileSuc" ;
	}
	@Action("/user/updateMobile")
	public void updateMobile(){
		if(StrUtil.isEmpty(user)){
			StrUtil.writeMsg(response, "参数错误", null);
			return;
		}
		user.setSid(getSessionUser().getSid());
		update(user);
		getSessionUser().setMobile(user.getMobile());;
	}
	
	/**
	 * 新注册第一步：添加邮箱
	 * @return
	 */
	@Action("/user/addEmail")
	public String regEmail(){
		if (StrUtil.isEmpty(vemail)) {
			StrUtil.writeMsg(response, "邮件不能为空", null);
			return null;
		}
		if(!StrUtil.checkEmailForStr(vemail)){
			StrUtil.writeMsg(response, "请输入正确的邮箱", null);
			return null;
		}
		synchronized (this) {
			user = new TbFlypaasUser();
			user.setEmail(vemail);
//			int ct = userService.mailAndMobEnable(user);
			TbFlypaasUser u =userService.findeUser(user);
			if (!StrUtil.isEmpty(u)&&!u.getStatus().equals(UserConstant.STATUS_0)) {
				StrUtil.writeMsg(response, "邮箱已经被占用",null);
				return null;
			}
			String sid = null;
			//发送邮件
			if(!StrUtil.isEmpty(u)&&!StrUtil.isEmpty(u.getSid())){
				sid = u.getSid();
			}else{
				sid = StrUtil.toHMACSHA(SysConstant.sidBaseString + StrUtil.getUUID(), SysConstant.KEY);
			}
			String token = StrUtil.toHMACSHA(SysConstant.tokenBaseString + StrUtil.getUUID(), SysConstant.KEY);
			try {
				sendActivateMail(sid,vemail);
			} catch (Exception e) {
				String ex = e.getMessage();
				System.out.println(e);
				if(ex.contains("550")){
					log.error("邮件地址不可用");
					log.error(e.getMessage());
					StrUtil.writeMsg(response, "邮件不可用，请检查后重试",null);
					return null;
				}
			}
			if(!StrUtil.isEmpty(u)&&u.getStatus().equals(UserConstant.STATUS_0)){
				return "vmailSucView";
			}
			user.setSid(sid);
			user.setToken(token);
			user.setStatus(UserConstant.STATUS_0);
			user.setCreateDate(new Date());
			user.setUserType(UserConstant.USER_TYPE_1);
			user.setRandomNbr(StrUtil.getRandom4()+"");
			if(StringUtils.isNotEmpty(agentId)){
				user.setSuperSid(agentId);
			}
			Object cid = session.getAttribute("CHANNEL_CID");
			if(StrUtil.isEmpty(cid)){
				user.setChannelId(SysConstant.CHANNEL_ID);
			}else{
				user.setChannelId(Integer.parseInt(cid.toString()));
			}
			if(StringUtils.isNotBlank(authType) && StringUtils.isNotBlank(authId)){
				user.setAuthType(authType);
				user.setAuthId(authId);
			}
			// 保存用户
			int count = userService.addUser(user);
			if(count>0){
				StrUtil.writeMsg(response, "用户名已被占用，请检查后重试.",null);
				return null;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sid", user.getSid());
			map.put("email", user.getEmail());
			map.put("ip", StrUtil.getClientIP(request));
			map.put("createDate",DateUtil.getCurrentDate());
			otherService.addResetPwdLog(map);
			return "vmailSucView";
		}
	}
	
	@Action("/user/addAgentEmail")
	public void addAgentEmail(){
		if(StringUtils.isNotEmpty(agentId)){
			TbFlypaasUser agent = userService.findeUserById(agentId);
			if(agent == null){
				log.info("------------------注册失败 代理商不存在！----------------");
				String errorCode = "注册失败,系统参数错误,请联系管理员";
				errorCode = "regCallback"+"({\"info\":\""+errorCode+"\"})";
				printMsg(errorCode);
				return;
			}
			if(!"1".equals(agent.getStatus())){
				log.info("------------------注册失败 代理商被锁定！----------------");
				String errorCode = "注册失败,系统已被锁定,请联系管理员";
				errorCode = "regCallback"+"({\"info\":\""+errorCode+"\"})";
				printMsg(errorCode);
				return;
			}
			if(0 == agent.getIsProxy()){
				log.info("------------------注册失败 代理商资格已取消！----------------");
				String errorCode = "注册失败,系统暂停使用,请联系管理员";
				errorCode = "regCallback"+"({\"info\":\""+errorCode+"\"})";
				printMsg(errorCode);
				return;
			}
		}else{
			log.info("------------------注册失败 代理商不存在！----------------");
			String errorCode = "注册失败,系统参数错误,请联系管理员";
			errorCode = "regCallback"+"({\"info\":\""+errorCode+"\"})";
			printMsg(errorCode);
			return;
		}
		if (StrUtil.isEmpty(vemail)) {
			String errorCode = "邮件不能为空";
			errorCode = "regCallback"+"({\"info\":\""+errorCode+"\"})";
			printMsg(errorCode);
			return;
		}
		if(!StrUtil.checkEmailForStr(vemail)){
			String errorCode = "请输入正确的邮箱";
			errorCode = "regCallback"+"({\"info\":\""+errorCode+"\"})";
			printMsg(errorCode);
			return;
		}
		synchronized (this) {
			user = new TbFlypaasUser();
			user.setEmail(vemail);
//			int ct = userService.mailAndMobEnable(user);
			TbFlypaasUser u =userService.findeUser(user);
			if (!StrUtil.isEmpty(u)&&!u.getStatus().equals(UserConstant.STATUS_0)) {
				String errorCode = "邮箱已经被占用";
				errorCode = "regCallback"+"({\"info\":\""+errorCode+"\"})";
				printMsg(errorCode);
				return;
			}
			String sid = null;
			//发送邮件
			if(!StrUtil.isEmpty(u)&&!StrUtil.isEmpty(u.getSid())){
				sid = u.getSid();
			}else{
				sid = StrUtil.toHMACSHA(SysConstant.sidBaseString + StrUtil.getUUID(), SysConstant.KEY);
			}
			String token = StrUtil.toHMACSHA(SysConstant.tokenBaseString + StrUtil.getUUID(), SysConstant.KEY);
			try {
				sendActivateMail(sid,vemail);
			} catch (Exception e) {
				String ex = e.getMessage();
				System.out.println(e);
				if(ex.contains("550")){
					log.error("邮件地址不可用");
					log.error(e.getMessage());
					String errorCode = "邮件不可用，请检查后重试";
					errorCode = "regCallback"+"({\"info\":\""+errorCode+"\"})";
					printMsg(errorCode);
					return;
				}
			}
			if(!StrUtil.isEmpty(u)&&u.getStatus().equals(UserConstant.STATUS_0)){
				String errorCode = "ok";
				errorCode = "regCallback"+"({\"info\":\""+errorCode+"\"})";
				printMsg(errorCode);
				return;
			}
			user.setSid(sid);
			user.setToken(token);
			user.setStatus(UserConstant.STATUS_0);
			user.setCreateDate(new Date());
			user.setUserType(UserConstant.USER_TYPE_1);
			user.setRandomNbr(StrUtil.getRandom4()+"");
			if(StringUtils.isNotEmpty(agentId)){
				user.setSuperSid(agentId);
			}
			Object cid = session.getAttribute("CHANNEL_CID");
			if(StrUtil.isEmpty(cid)){
				user.setChannelId(SysConstant.CHANNEL_ID);
			}else{
				user.setChannelId(Integer.parseInt(cid.toString()));
			}
			if(StringUtils.isNotBlank(authType) && StringUtils.isNotBlank(authId)){
				user.setAuthType(authType);
				user.setAuthId(authId);
			}
			// 保存用户
			int count = userService.addUser(user);
			if(count>0){
				String errorCode = "用户名已被占用，请检查后重试.";
				errorCode = "regCallback"+"({\"info\":\""+errorCode+"\"})";
				printMsg(errorCode);
				return;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sid", user.getSid());
			map.put("email", user.getEmail());
			map.put("ip", StrUtil.getClientIP(request));
			map.put("createDate",DateUtil.getCurrentDate());
			otherService.addResetPwdLog(map);
			String errorCode = "ok";
			errorCode = "regCallback"+"({\"info\":\""+errorCode+"\"})";
			printMsg(errorCode);
			return;
		}
	}
	
	/*----------------------------注册：添加用户基础信息【第一步：邮箱，密码】--------------------------------*/
	@Action("/user/userAdd")
	public String add() {
		boolean isContainsJs = check(user);
		if(isContainsJs){
			StrUtil.writeMsg(response, "应用信息包含html标签,请检查后重试",null);
			return null;
		}
		if (StrUtil.isEmpty(user)) {
			StrUtil.writeMsg(response, "用户信息不能为空",null);
			return null;
		}
		email = user.getEmail();
		boolean isEmail = StrUtil.checkEmailForStr(email);
		if(!isEmail){
			StrUtil.writeMsg(response, "邮箱格式不正确",null);
			return null;
		}
		if(email.length()>64){
			StrUtil.writeMsg(response, "邮箱长度不符合规范（小于64个字符）",null);
			return null;
		}
		
		vuserName = user.getUserName();
		boolean isUserName = StrUtil.checkUserName(vuserName);
		if(!isUserName){
			StrUtil.writeMsg(response, "昵称格式错误",null);
			return null;
		}
		if(vuserName.length()>15 || vuserName.length()<4){
			StrUtil.writeMsg(response, "昵称长度不符合规范（4-15）",null);
			return null;
		}
		
		synchronized (this) {
			int ct = userService.mailAndMobEnable(user);
			if (ct>0) {
				StrUtil.writeMsg(response, "邮箱已经被占用",null);
				return null;
			}
			int ct1 = userService.getUserCountByUserName(vuserName);
			if(ct1>0){
				StrUtil.writeMsg(response, "昵称已经被占用",null);
				return null;
			}
			//发送邮件
			String token = null;
			email = user.getEmail();
			sid = StrUtil.toHMACSHA(SysConstant.sidBaseString + StrUtil.getUUID(), SysConstant.KEY);
			token = StrUtil.toHMACSHA(SysConstant.tokenBaseString + StrUtil.getUUID(), SysConstant.KEY);
			try {
				sendActivateMail(sid,email);
			} catch (Exception e) {
				String ex = e.getMessage();
				if(ex.contains("550")){
					log.error("邮件地址不可用");
					log.error(e.getMessage());
					StrUtil.writeMsg(response, "邮件不可用，请检查后重试",null);
					return null;
				}
			}
			String md5Password = MD5.md5(MD5Util.GetMD5Code(user.getPassword()));
			user.setPassword(md5Password);
			user.setSid(sid);
			user.setToken(token);
			user.setStatus(UserConstant.STATUS_0);
			user.setCreateDate(new Date());
			user.setUserType(UserConstant.USER_TYPE_1);
			user.setRandomNbr(StrUtil.getRandom4()+"");
			Object cid = session.getAttribute("CHANNEL_CID");
			if(StrUtil.isEmpty(cid)){
				user.setChannelId(SysConstant.CHANNEL_ID);
			}else{
				user.setChannelId(Integer.parseInt(cid.toString()));
			}
			if(StringUtils.isNotBlank(authType) && StringUtils.isNotBlank(authId)){
				user.setAuthType(authType);
				user.setAuthId(authId);
			}
			// 保存用户
			int count = userService.addUser(user);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sid", user.getSid());
			map.put("email", user.getEmail());
			map.put("ip", StrUtil.getClientIP(request));
			map.put("createDate",DateUtil.getCurrentDate());
			otherService.addResetPwdLog(map);
			if(count>0){
				StrUtil.writeMsg(response, "用户名已被占用，请检查后重试.",null);
				return null;
			}
		}
		//通知uccenter 
		//UcCenterKit.register(request, user.getUserName(),user.getEmail(), user.getPassword()); by yangmingke
		return "addUserSuc";
	}
	private boolean check(TbFlypaasUser user,Object...objs){
		boolean result = false;
		if(!StrUtil.isEmpty(user)){
			if(StrUtil.checkJsForStr(user.getUserName())){
				return true;
			}
			if(StrUtil.checkJsForStr(user.getAddress())){
				return true;
			}
			if(StrUtil.checkJsForStr(user.getEmail())){
				return true;
			}
			if(StrUtil.checkJsForStr(user.getMobile())){
				return true;
			}
			if(StrUtil.checkJsForStr(user.getRealname())){
				return true;
			}
			if(StrUtil.checkJsForStr(user.getWebSite())){
				return true;
			}
			if(StrUtil.checkJsForStr(user.getProvince()+"")){
				return true;
			}
			if(StrUtil.checkJsForStr(user.getCity()+"")){
				return true;
			}
			if(StrUtil.checkJsForStr(user.getLegalPerson())){
				return true;
			}
			for(Object oj : objs){
				if(StrUtil.checkJsForStr(oj.toString())){
					result= true;
					break;
				}
			}
		}
		return result;
		
	}
	@Action("/user/toExtEmail")
	public String toExtEmail(){
		return "toExtEmailSuc" ;
	}
	
	@Action("/user/vmailExt")
	public String vmailExt(){
		return "vmailExtSuc" ;
	}
	
	/*----------------------------发送激活邮件--------------------------------*/
	@Action("/user/sendverifyMail")
	public String activateMail() throws IOException {
		sendActivateMail(sid,email);
		return "sendMail";
	}
	
	/*----------------------------发送激活邮件(适用于ajax)--------------------------------*/
	@Action("/user/sendverifyMailAjax")
	public void activateMailAjax(){
		String i = "0";
		TbFlypaasUser u = new TbFlypaasUser();
		u.setSid(sid);
		u.setEmail(email);
		u = userService.findeUser(u);
		if(UserConstant.STATUS_1.equals(u.getStatus())){
			i = "1" ;
		}else{
			sendActivateMail(sid,email);
		}
		try {
			response.getWriter().write(i);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("------------------------发送激活邮件(适用于ajax)失败------------");
			log.error(e.getMessage());
		}
	}
	/*----------------------------判断邮箱,手机号码是否已经注册--------------------------------*/
	@Action("/user/ckMobileAndmailEnable")
	public void mailAndMobEnable() {
		if (vemail == null && vmobile == null) {
			printMsg(UserConstant.NOTEXIST);
			return;
		}
		TbFlypaasUser user = new TbFlypaasUser();
		user.setEmail(vemail);
		user.setMobile(vmobile);
		int count = userService.mailAndMobEnable(user);
		if (count>0) {
			printMsg(UserConstant.EXIST);
			return;
		}
		printMsg(UserConstant.NOTEXIST);
		return;
	}
	/*----------------------------判断用户名是否已存在--------------------------------*/
	@Action("/user/ckUserName")
	public void userNameEnable(){
			try {
				if(StrUtil.isEmpty(vuserName)){
					response.getWriter().write(UserConstant.NOTEXIST);
					return;
				}
				int count = userService.getUserCountByUserName(vuserName);
				if(count>0){
					response.getWriter().write(UserConstant.EXIST);
					return;
				}
				response.getWriter().write(UserConstant.NOTEXIST);
				return;
			} catch (IOException e) {
				log.error("判断用户名是否已存在出错。。");
				log.error(e.getMessage());
			}
	}
	/*----------------------------发送重置密码的邮件链接--------------------------------*/
	@Action("/user/sendResetPwd")
	public String sendResetPwd(){
		if(StringUtils.isNotBlank(email) && StrUtil.checkEmailForStr(email)){
			
			TbFlypaasUser u = new TbFlypaasUser();
			u.setEmail(email);
			u = userService.findeUser(u);
			if(StrUtil.isEmpty(u)){
				StrUtil.writeMsg(response, "用户名不存在", null);
				return null ;
			}
			sid = u.getSid();
			
			//更新本地验证文件
			long currentTime = System.currentTimeMillis();
			String ccode = MD5Util.GetMD5Code(UUID.randomUUID().toString());
			newOrUpdateAuthFile(sid, ccode, currentTime);
			log.info("验证code:"+ccode+";发送时间："+currentTime);
			//发送邮件
			mailService.sendResetPasswordMail(email,ccode);
			return "resetPwdMailSuccess" ;
		}else{
			StrUtil.writeMsg(response, "请输入正确的邮箱",null);
			return null;
		}
		
	}
	@Action("/user/toExtMailServer")
	public String toExtMailServer(){
		return "toExtMailServerSuc";
	}
	/*----------------------------通过邮箱激活用户--------------------------------*/
	@Action("/user/verifyMailOpt")
	public String verifyMailOpt(){
		if(StrUtil.isEmpty(user)||StrUtil.isEmpty(user.getSid())){
			StrUtil.writeMsg(response, "参数错误", null);
			return null;
		}
		TbFlypaasUser user1= userService.findUserById(user.getSid());
		if(StrUtil.isEmpty(user1)){
			return "index";
		}
		if(UserConstant.STATUS_1.equals(user1.getStatus())){
			if(!StrUtil.isEmpty(user1.getSuperSid())){
				TbFlypaasUser agent = userService.findUserById(user1.getSuperSid());
				user.setAgentUrl(agent.getAgentUrl());
			}
			return "verifyMailOptSuc" ; 
		}
		user.setPassword(MD5.md5(MD5Util.GetMD5Code(user.getPassword())));
		user.setToken(user1.getToken());
		user.setSuperSid(user1.getSuperSid());
		userService.activaUser(user);
		log.info("------------------激活成功----------------------------");
		
		user = userService.findUserById(user.getSid());
		if(!StrUtil.isEmpty(user.getSuperSid())){
			TbFlypaasUser agent = userService.findUserById(user.getSuperSid());
			user.setAgentUrl(agent.getAgentUrl());
		}
		recordSession(user);
//		UcCenterKit.login(request,user.getUserName(),user.getEmail(),user.getPassword()); by yangmingke
		log.info("------------------登录成功----------------------------");
		
		//发送注册成功邮件
		mailService.sendLogSucMail(user.getEmail()); 
		
		sign = "sign" ;
		return "verifyMailOptSuc" ;
	}
	/*----------------------------验证链接有效性--------------------------------*/
	@Action("/user/verifyMail")
	public String verifyMail(){
		//检测是否是有页面发起，禁止迅雷等下载软件调用检测
		String cookie = request.getHeader("cookie");
		if(StrUtil.isEmpty(cookie)){
			return null;
		}
		if(sid!=null && vcode!=null){
			user= userService.findUserById(sid);
			if(user==null){
				 //用户不存在   进入首页
				log.info("用户不存在!");
				resultCode = "1";
				return "error";
			}
			if(user.getStatus()!=null && !user.getStatus().equals(UserConstant.STATUS_0)){
				//已经激活  进入首页
				log.info("用户已经激活!");
				resultCode = "2";
				return "error";
			}
			Map<String, Object> map= otherService.getAuthFile(sid);
			if(StrUtil.isEmpty(map)){
				//本地验证不存在文件   进入首页
				log.info("数据验证码不存在！");
				resultCode = "3";
				return "error";
			}
			String csid = map.get("sid").toString();
			String ccode = map.get("vcode").toString();
			long vtime = Long.parseLong(map.get("timestamp").toString());
			
			if(!(csid).equals(sid) || !(ccode).equals(vcode)){
				//链接被篡改   进入首页
				log.info("数据验证码被篡改！");
				return "error";
			}
			boolean timeOut = vtime+SysConstant.MAIL_TIMEOUT-System.currentTimeMillis()>0?true:false;
			if(!timeOut){
				//过期 重新发送激活邮件
				log.info("邮件已过期，重新发送激活邮件");
				return "error" ;
			}
			//更新本地验证文件 让链接失效
			ccode = MD5Util.GetMD5Code(UUID.randomUUID().toString());
			newOrUpdateAuthFile(user.getSid(), ccode, System.currentTimeMillis());
			return "verifyMailSuc" ;
		}else{
			//链接失效 返回首页
			resultCode = "4";
			return "error";
		}
	}
	
	
	/*----------------------------验证重置密码链接是否有效--------------------------------*/
	@Action("/user/resetPwd")
	public String loadverifyResetPwd(){
		//检测是否是有页面发起，禁止迅雷等下载软件调用检测
		String cookie = request.getHeader("cookie");
		if(StrUtil.isEmpty(cookie)){
			return null;
		}
		
		if(email!=null && vcode!=null){
			TbFlypaasUser u = new TbFlypaasUser();
			u.setEmail(email);
			user= userService.findeUser(u);
			if(user==null){
				//找不到用户跳到首页
				log.info("------------------用户不存在  重置密码链接无效----------------------------");
				return "index" ;
			}
			Map<String, Object> map = otherService.getAuthFile(user.getSid());
			if(map==null){
				//本地验证文件不存在
				log.info("------------------本地验证文件不存在  重置密码链接无效----------------------------");
				return "index" ;
			}
			String ccode = map.get("vcode").toString();
			long vtime =  Long.parseLong(map.get("timestamp").toString());
			boolean timeOut = vtime+SysConstant.MAIL_TIMEOUT-System.currentTimeMillis()>0?true:false;
			if(!timeOut){
				//过期 
				log.info("------------------链接过期  重置密码链接无效----------------------------");
				return "index" ;
			}
			if(!(ccode).equals(vcode)){
				//code不一样跳到首页
				log.info("------------------验证code被篡改  重置密码链接无效----------------------------");
				return "index" ;
			}
			session.setAttribute("rdmCode",StrUtil.toHMACSHA(SysConstant.sidBaseString + StrUtil.getUUID(), SysConstant.KEY));
			session.setAttribute("email", email);
			return "viewResetPwd" ;
		}else{
			//链接失效
			log.info("------------------验证参数丢失  重置密码链接无效----------------------------");
			return "index";
		}
	}
	/*----------------------------重置密码--------------------------------*/
	@Action("/user/userResetPwd")
	public String resetPwd(){
		if(StrUtil.isEmpty(user)||StrUtil.isEmpty(user.getPassword())){
			StrUtil.writeMsg(response, "请求非法", null);
			return null ;
		}
		if(!StrUtil.checkPwd(user.getPassword())){
			StrUtil.writeMsg(response, "密码不符合规范",null);
			return null;
		}
		if(user.getPassword().length()<8|| user.getPassword().length()>16){
			StrUtil.writeMsg(response, "密码不符合规范",null);
			return null;
		}
		String rdmCode = (String)session.getAttribute("rdmCode");
		session.removeAttribute("rdmCode");
		if(StrUtil.isEmpty(sign)||StrUtil.isEmpty(rdmCode)||!sign.equals(rdmCode)){
			StrUtil.writeMsg(response, "请求非法",null);
			return null;
		}
		String encode = Des3Utils.decodeDes3(vcode);
		
		if(StrUtil.isEmpty(encode)){
			StrUtil.writeMsg(response, "请求非法", null);
			return null ;
		}
		String email = (String)session.getAttribute("email");
		if(StrUtil.isEmpty(email)){
			StrUtil.writeMsg(response, "请求非法",null);
			return null;
		}
		user.setEmail(email);
		TbFlypaasUser u1 = userService.findeUser(user);
		if(StrUtil.isEmpty(u1)){
			StrUtil.writeMsg(response, "请求非法", null);
			return null ;
		}
		Map<String, Object> map = otherService.getAuthFile(u1.getSid());
		if(StrUtil.isEmpty(map)){
			//本地验证文件不存在
			log.info("------------------本地验证文件不存在  重置密码链接无效----------------------------");
			StrUtil.writeMsg(response, "请求非法", null);
			return null ;
		}
		String sid =  map.get("sid").toString();
		String ccode = map.get("vcode").toString();
		
		if(!sid.equals(u1.getSid())||!encode.equals(ccode)){
			StrUtil.writeMsg(response, "请求非法", null);
			return null ;
		}
		user.setSid(u1.getSid());
		update(user);
		int resetCount = otherService.getResetPwdCount(u1.getSid());
		if(resetCount==0){
			map = new HashMap<String, Object>();
			map.put("sid", u1.getSid());
			map.put("email", u1.getEmail());
			map.put("ip", StrUtil.getClientIP(request));
			map.put("createDate",DateUtil.getCurrentDate());
			otherService.addResetPwdLog(map);
			//发送邮件
			mailService.sendResetPasswordMailSuc(u1.getEmail());
			//发送短信
			if(!StrUtil.isEmpty(u1.getMobile())){
				RestClient.SMS(SysConstant.SUPER_ADMIN_SID, SysConstant.SUPER_ADMIN_TOKEN, SysConstant.SYS_APP_SID, "111", u1.getMobile(),"", SysConstant.REST_CHARGE_TYPE_1);
			}
			session.removeAttribute("viewResetPwd21_email");
		}
		//更新本地验证文件
		String code = MD5Util.GetMD5Code(UUID.randomUUID().toString());
		newOrUpdateAuthFile(u1.getSid(),code,System.currentTimeMillis());
//		UcCenterKit.resetPwd(request, u1.getUserName(), u1.getEmail(),u1.getPassword(), user.getPassword()); by yangmingke
		return "resetPwdSuc";
	}
	/*----------------------------修改密码--------------------------------*/
	@Action("/user/correctPwd")
	public void correctPwd(){
		String current_pwd = request.getParameter("current_pwd");
		if(StrUtil.isEmpty(current_pwd)||StrUtil.isEmpty(user)||StrUtil.isEmpty(user.getPassword())||user.getPassword().length()>SysConstant.PWD_MAX_LEN||user.getPassword().length()<SysConstant.PWD_LEN){
			StrUtil.writeMsg(response, "密码不符合规范",null);
			return;
		}
		String oldPwd = MD5.md5(MD5Util.GetMD5Code(current_pwd));
		if(!oldPwd.equals(getSessionUser().getPassword())){
			StrUtil.writeMsg(response, "当前密码不正确",null);
			return;
		}
		if(!StrUtil.checkPwd(user.getPassword())){
			StrUtil.writeMsg(response, "密码不符合规范",null);
			return;
		}
		if(user.getPassword().length()<8|| user.getPassword().length()>16){
			StrUtil.writeMsg(response, "密码不符合规范",null);
			return;
		}
		user.setSid(getSessionUser().getSid());
		update(user);
		//更新session中的密码
		getSessionUser().setPassword(user.getPassword());
		TbFlypaasUser u = getSessionUser();
//		UcCenterKit.resetPwd(request, u.getUserName(), u.getEmail(),oldPwd, u.getPassword()); BY yangmingke
		String fr = request.getParameter("fr");
		if(StringUtils.isNotBlank(fr)){
			try {
				response.sendRedirect(fr);
				return;
			} catch (IOException e) {
				log.error(e.getMessage(),e);
			}
		}
	}
	
	/*----------------------------获取开发者基本信息--------------------------------*/
	@Action("/user/userCenter")
	public String developInfo(){
		String sid = getSessionUser().getSid();
		user = getUserInfo(sid,null,null);
		provinceList = provinceService.getProvince();
		//获取省市
		Integer pro = user.getProvince();
		if(!StrUtil.isEmpty(pro)){
			province = provinceService.getProvinceByid(user.getProvince());
			if(!StrUtil.isEmpty(user.getCity())){
				ccity = cityService.getCityById(user.getCity());
			}
		}
		return "userCenter";
	}
	/*----------------------------主页--------------------------------*/
	@Action("/user/account")
	public String account(){
		String sid = getSessionUser().getSid();
		//用户信息
		user = getUserInfo(sid,null,null);
		//用户余额
		acctBalance = acctBalanceService.getAcctBalanceBySid(sid);
		//上线应用
		onLineAppCount = appService.getOnlineAppCount(user.getSid());
		//统计昨日活跃client数
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sid", user.getSid());
//		activeCount = appService.getActiveCount(params); //by yangmingke
		//rest配置
		paramsList = getParams(SysConstant.REST);
		//用户最近一条消息
		userMsg = userMsgService.getLastUserMsg(sid);
		//套餐
		AcctPackageRel rel = acctPckService.getAcctPackageRel(sid);
		if(rel!=null){
			pck = packService.getPackage(rel.getPackageId());
		}
		//昨日消费
		Map<String, String> map = new HashMap<String, String>();
		map.put("sid", getSessionUser().getSid());
		map.put("time", DateUtil.getYstdDate());
		Map<String,Object> ystCsm= consumeService.getYstCsm(map); 
		if(ystCsm==null){
			ysdConsume = "0.0000";
		}else{
			ysdConsume = ystCsm.get("fee").toString();
		}
		log.info("--------------------进入用户主页 ---------------------");
		return "account";
	}
	
	/*----------------------------余额提醒--------------------------------*/
	@Action("/user/balanceRemind")
	public void balanceRemind(){
		if(StrUtil.isEmpty(remind)){
			StrUtil.writeMsg(response, "余额提醒设置失败", null);
			return;
		}
		remind.setSid(getSessionUser().getSid());
		remind.setMethod("1");
		int result = remindService.get(remind.getSid());
		if(result ==0){
			remind.setStatus(UserConstant.REMIND_STATUS_0);
			remind.setMoney(remind.getMoney()*SysConstant.RATE);
			remindService.add(remind);
		}else{
			remind.setStatus(UserConstant.REMIND_STATUS_0);
			remind.setMoney(remind.getMoney()*SysConstant.RATE);
			remindService.update(remind);
		}
		StrUtil.writeMsg(response, "余额提醒设置成功", null);
	}
	/*----------------------------关闭新手指引--------------------------------*/
	@Action("/user/closeGuide")
	public void closeGuide(){
		TbFlypaasUser user = new TbFlypaasUser();
		user.setSid(getSessionUser().getSid());
		user.setGuide(1);
		update(user);
	}
	/*----------------------------重置token--------------------------------*/
	@Action("/user/resetToken")
	public String resetToken(){
		String sid= getSessionUser().getSid();
		String oldToken = getSessionUser().getToken();
		String token = userService.renewToken(sid, "1");
		TbFlypaasUser user = new TbFlypaasUser();
		user.setSid(sid);
		user.setToken(token);
		update(user);
		//重置token日志
		addResetTokenLog(sid, oldToken);
		//更新本地token
		getSessionUser().setToken(token);
		//刷新redis
		RefreshRedis.updateUser(sid);
		return "restTokenSuc" ;
	}
	//添加重置token日志
	private void addResetTokenLog(String sid ,String token){
		UserLog userLog = new UserLog();
		userLog.setSid(sid);
		userLog.setToken(token);
		userLog.setCreateDate(DateUtil.getCurrentDate());
		userLogService.add(userLog);
	}
	/*----------------------------跳转到编辑开发者信息页--------------------------------*/
	@Action("/user/modifyDevelopPage")
	public String modifyDevelopPage(){
		String sid = getSessionUser().getSid();
		user = getUserInfo(sid,null,null);
		provinceList = provinceService.getProvince();
		return "modifyPage";
	}
	/*----------------------------根据省获取城市--------------------------------*/
	@Action("/user/getCity")
	public void City(){
		List<City> cityList = cityService.getCity(Integer.parseInt(provinceId));
		String jsonStr = JsonUtil.ArrayToJsonStr(cityList);
		try {
			response.getWriter().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("------------------------查找省失败------------");
			log.error(jsonStr);
		}
	}
	/*----------------------------判断密码是否正确--------------------------------*/
	@Action("/user/thePwdIsRight")
	public void thePwdIsRight(){
		TbFlypaasUser user = getSessionUser();
		int code = 0;
		if(user.getPassword().equals(MD5.md5(MD5Util.GetMD5Code(password)))){
			code = 1 ;
		}
		try {
			response.getWriter().write(code+"");
		} catch (IOException e) {
			log.error("------------------------判断密码是否正确失败------------");
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}
	/*----------------------------修改开发者信息--------------------------------*/
	@Action("/user/modifyDevelop")
	public String modifyDevelop(){
		boolean isContainsJs = check(user);
		if(isContainsJs){
			StrUtil.writeMsg(response, "应用信息包含html标签,请检查后重试",null);
			return null;
		}
		user.setSid(getSessionUser().getSid());
		update(user);
		resultCode = SysConstant.SUCCESS;
		return "modifySuc" ;
	}
	/*----------------------------一键登录--------------------------------*/
	@Action("/user/oauth")
	public String oauth(){
		user = getUserInfo(null,email,null);
		recordSession(user);
//		UcCenterKit.login(request,user.getUserName(),user.getEmail(),user.getPassword()); by yangmingke
		return "oauthSuc" ;
	}
	//获取用户信息
	private TbFlypaasUser getUserInfo(String sid,String email,String mobile){
		user = new TbFlypaasUser();
		user.setSid(sid);
		user.setEmail(email);
		user.setMobile(mobile);
		user = userService.findeUser(user);
		if(StringUtils.isNotEmpty(user.getSuperSid())){
			TbFlypaasUser agent = userService.findeUserById(user.getSuperSid());
			user.setAgentLogo(agent.getAgentLogo());
			user.setAgentUrl(agent.getAgentUrl());
		}
		return user ;
	}
	//清空session中存贮的临时信息
	private void removeTempSession(String loginUserName){
		//删除登录错误次数
		session.removeAttribute(loginUserName);
		//删除验证码次数
		session.removeAttribute("codeCount");
		//删除登录随机验证码
		session.removeAttribute("randCheckCode");
	}
	//登录7次未成功禁止登录24小时
	private void forbidLoginControl(String userName){
		int errorCount  = getErrorCount(userName);
		if(errorCount>=7){
			TbFlypaasUser u = new TbFlypaasUser();
			u.setSid(user.getSid());
			u.setStatus(UserConstant.STATUS_5);
			update(u);
		}
	}
	//记录登录错误次数
	private void setCodeErrorCount(){
		Object o =  session.getAttribute("codeCount");
		int errorCount = 0;
		if(o==null){
			session.setAttribute("codeCount",1);
		}else{
			errorCount = (Integer) session.getAttribute("codeCount");
			session.setAttribute("codeCount",++errorCount);
		}
	}
	//记录登录错误次数
	private void setErrorCount(String userName){
		Object o =  session.getAttribute(userName);
		int errorCount = 0;
		if(o==null){
			session.setAttribute(userName,1);
		}else{
			errorCount = (Integer) session.getAttribute(userName);
			session.setAttribute(userName,++errorCount);
		}
	}
	//获取错误次数
	private int getCodeErrorCount(){
		int errorCount =  session.getAttribute("codeCount")==null?0:(Integer)session.getAttribute("codeCount");
		return errorCount ;
	}
	//获取错误次数
	private int getErrorCount(String userName){
		int errorCount =  session.getAttribute(userName)==null?0:(Integer)session.getAttribute(userName);
		return errorCount ;
	}
	//发送激活邮件sid参数必须
	private void sendActivateMail(String sid,String mail){
		String vcode = MD5Util.GetMD5Code(UUID.randomUUID().toString());
		if(mail==null){
			user = userService.findUserById(sid);
			email = user.getEmail();
		}else{
			email = mail ;
		}
		long currentTime = System.currentTimeMillis();
		mailService.sendValidateMail(sid,vcode,currentTime+"",email);
		newOrUpdateAuthFile(sid, vcode, currentTime);
	}
	private void newOrUpdateAuthFile(String sid,String vcode,long currentTime){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sid", sid);
		map.put("vcode", vcode);
		map.put("timestamp", currentTime);
		Object obj = otherService.getAuthFile(sid);
		if(StrUtil.isEmpty(obj)){
			otherService.addAuthFile(map);
		}else{
			otherService.updateAuthFile(map);
		}
	}
	// 更新用户基本信息
	private void update(TbFlypaasUser user) {
		if(null != user){
			if(user.getPassword()!=null && !("").equals(user.getPassword())){
				String pw = user.getPassword();
				user.setPassword(MD5.md5(MD5Util.GetMD5Code(pw)));
			}
			//更新用户信息
			user.setUpdateDate(DateUtil.getCurrentDate());
			userService.updateUser(user);
			//刷新redis
			RefreshRedis.updateUser(user.getSid());
			RefreshRedis.updateTestWhiteList(user.getSid());
			String appSid = appService.getTestApp(user.getSid()).getAppSid();
			RefreshRedis.updateApp(appSid,user.getSid());
		}
		
	}
	@SuppressWarnings("unused")
	private Map<String, String> getFreeClientNum(List<TestNumber> numList ,List<Client> cList){
		List<Client> tempList = new ArrayList<Client>(cList) ;
		String resultNum = null ;
		String resultAppSid = null ;
		Map<String, String> map = null ;
		for(Client c : cList){
			String clientnum = c.getClientNumber();
			for(TestNumber tn : numList){
				String num = tn.getClientnum();
				if(clientnum.equals(num)){
					tempList.remove(c);
				}
			}
		}
		map = new HashMap<String, String>();
		if(tempList==null){
			return null ;
		}
		Client c  = new Client() ;
		for(Client client : tempList){
			if(client!=null){
				c = client;
				break;
			}
		}
		resultNum = c.getClientNumber() ;
		resultAppSid = c.getAppSid();
		map.put("resultNum",resultNum);
		map.put("resultAppSid",resultAppSid);
		return map ;
	}

	/**
	 * 第三方账号登录：明道授权页面
	 * 
	 * @return
	 */
	@Action("/auth/mingdao")
	public String mingdao() {
		authUrl = AuthConstant.mingdao_authorize_url;
		log.debug("【明道账号登录】授权页面：authUrl={}", authUrl);
		return "authUrl";
	}

	/**
	 * 第三方账号登录：明道回调页面
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Action("/auth/mingdaoCallback")
	public String mingdaoCallback() {
		session.removeAttribute("auth_error");
		session.removeAttribute("auth_fail");
		session.removeAttribute("auth_bind");

		String code = request.getParameter("code");
		log.debug("【明道账号登录】授权：code={}", code);
		if (StringUtils.isBlank(code)) {
			session.setAttribute("auth_error", "【明道账号登录】失败：code为空");
			return "authLoginRedirect";
		}
		Map<String, Object> authAccessToken = AuthUtils.doGetReturnMap(AuthConstant.mingdao_access_token_url + code);
		log.debug("【明道账号登录】获取令牌：authAccessToken={}", authAccessToken);
		if (authAccessToken != null && authAccessToken.containsKey("access_token")) {
			Map<String, Object> authUserInfo = AuthUtils.doGetReturnMap(AuthConstant.mingdao_user_info_url
					+ authAccessToken.get("access_token").toString());

			log.debug("【明道账号登录】获取用户信息：authUserInfo={}", authUserInfo);
			if (authUserInfo != null && authUserInfo.containsKey("user")) {
				Map<String, Object> authUser = (Map<String, Object>) authUserInfo.get("user");
				String authType = AuthType.mingdao.getTypeId();
				String authId = authUser.get("id").toString();

				TbFlypaasUser loginUser = new TbFlypaasUser();
				loginUser.setAuthType(authType);
				loginUser.setAuthId(authId);
				TbFlypaasUser user = userService.findeUser(loginUser);

				if (user != null) {// 已经绑定快传账号
					String yzxEmail = user.getEmail();
					String errorCode = login(yzxEmail, user.getPassword());
					if (errorCode != null && errorCode.startsWith(UserConstant.LOG_SUCCESS + "|")) {
						log.debug("【明道账号登录】登录成功：authType={}, authId={}, yzxEmail={}, errorCode={}", new Object[] {
								authType, authId, yzxEmail, errorCode });
						return "authSuccess";
					} else {
						Map<String, Object> authFail = new HashMap<String, Object>();
						authFail.put("yzx_email", yzxEmail);
						authFail.put("error_code", errorCode);
						session.setAttribute("auth_fail", authFail);
						log.debug("【明道账号登录】登录失败：authType={}, authId={}, yzxEmail={}, errorCode={}", new Object[] {
								authType, authId, yzxEmail, errorCode });
						return "authLoginRedirect";
					}
				}

				String authEmail = authUser.get("email").toString();
				Map<String, Object> authBind = new HashMap<String, Object>();
				authBind.put("auth_type", AuthType.mingdao.getTypeId());
				authBind.put("auth_type_name", AuthType.mingdao.getTypeName());
				authBind.put("auth_id", authId);
				authBind.put("auth_username", authEmail.split("@")[0]);
				authBind.put("auth_email", authEmail);
				session.setAttribute("auth_bind", authBind);// 新用户绑定快传账号
				log.debug("【明道账号登录】新用户：authBind={}", authBind);
				return "authLoginRedirect";
			}
		}
		session.setAttribute("auth_error", "【明道账号登录】获取用户信息时发生错误，请稍后重试");
		return "authLoginRedirect";
	}

	/**
	 * 第三方账号登录：登录、注册页面
	 * 
	 * @return
	 */
	@Action("/auth/login")
	public String authLogin() {
		return "authLogin";
	}

	/**
	 * 第三方账号登录：添加用户
	 * 
	 * @return
	 */
	@Action("/auth/userAdd")
	public String authUserAdd() {
		String add = add();
		if ("addUserSuc".equals(add)) {// 添加成功
			TbFlypaasUser loginUser = new TbFlypaasUser();
			loginUser.setAuthType(authType);
			loginUser.setAuthId(authId);
			user = userService.findeUser(loginUser);
			if (StrUtil.isEmpty(user)) {
				return "index";
			}

			userService.activaUser(user);
			log.info("------------------激活成功----------------------------");

			user.setStatus(UserConstant.STATUS_1);
			recordSession(user);
//			UcCenterKit.login(request, user.getUserName(), user.getEmail(), user.getPassword()); by yangmingke
			log.info("------------------登录成功----------------------------");

			// 发送注册成功邮件
			mailService.sendLogSucMail(user.getEmail());
			return "authSuccess";
		}
		return null;
	}
	
	
	/*----------------------------token手机认证 add by zlb--------------------------------*/
	@Action("/user/verifyMobileForToken")
	public String verifyMobileForToken(){
		/*if( StringUtils.isNotBlank(viewTokenCode) &&null != session && viewTokenCode.equals(session.getAttribute("session_check4token"))){
			session.setAttribute("view_token_auth", "true");
		}*/  //暂无短信验证 by yangmingke
		session.setAttribute("view_token_auth", "true");
		return account();
	}
	
	@Action("/user/renewToken")
	public void renewToken(){
		String token  = userService.renewToken(getSessionUser().getSid(),resetTokenType);
		printMsg(token);
	}
	
	/*
	//发送修改密码的邮件
	@Action("/user/sendUpdatePasswordMail")
	public void sendUpdatePasswordMail() {
		if("RW6K7Yejtgzj5JkZWmmBhvTskVYPz8ltZFgWM30XrSnUNuouct".equals(sign)){
			String msg = mailService.sendUpdatePasswordMail(sid);
			printMsg(msg);
		}
	}
	*/
	
	@Action("/user/querySessionPacketLoss")
	public String querySessionPacketLoss(){
		// 用户所有app
		String sid = getSessionUser().getSid();
		appList = appService.getAllAppBySid(sid);
		if(StringUtils.isNotEmpty(cookieId)){
			sessionLoss = appService.getSessionPacketLoss(sid, appSid, cookieId, datetime);
			packetLossTime = (String) sessionLoss.get("packetLossTime");
			resultJson = (String) sessionLoss.get("resultJson");
		}
		return "sessionPacketLossPage";
	}
	

	/************************************* get set 方法 *********************************/
	public TbFlypaasUser getUser() {
		return user;
	}

	public void setUser(TbFlypaasUser user) {
		this.user = user;
	}

	public List<TbFlypaasUser> getUserList() {
		return userList;
	}

	public void setUserList(List<TbFlypaasUser> userList) {
		this.userList = userList;
	}

	public String getVemail() {
		return vemail;
	}

	public void setVemail(String vemail) {
		this.vemail = vemail;
	}

	public String getVmobile() {
		return vmobile;
	}

	public void setVmobile(String vmobile) {
		this.vmobile = vmobile;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getVcode() {
		return vcode;
	}
	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Province> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<Province> provinceList) {
		this.provinceList = provinceList;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public Package getPck() {
		return pck;
	}

	public void setPck(Package pck) {
		this.pck = pck;
	}

	public int getOnLineAppCount() {
		return onLineAppCount;
	}
	public void setOnLineAppCount(int onLineAppCount) {
		this.onLineAppCount = onLineAppCount;
	}

	public List<Params> getRemindParamsList() {
		return remindParamsList;
	}

	public void setRemindParamsList(List<Params> remindParamsList) {
		this.remindParamsList = remindParamsList;
	}

	public Remind getRemind() {
		return remind;
	}

	public void setRemind(Remind remind) {
		this.remind = remind;
	}

	public UserMsg getUserMsg() {
		return userMsg;
	}

	public void setUserMsg(UserMsg userMsg) {
		this.userMsg = userMsg;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}
	public City getCcity() {
		return ccity;
	}

	public void setCcity(City ccity) {
		this.ccity = ccity;
	}
	public String getYsdConsume() {
		return ysdConsume;
	}
	public void setYsdConsume(String ysdConsume) {
		this.ysdConsume = ysdConsume;
	}
	public Map<String, Object> getActiveCount() {
		return activeCount;
	}
	public String getVuserName() {
		return vuserName;
	}
	public void setVuserName(String vuserName) {
		this.vuserName = vuserName;
	}
	public AcctBalance getAcctBalance() {
		return acctBalance;
	}
	public void setAcctBalance(AcctBalance acctBalance) {
		this.acctBalance = acctBalance;
	}
	public String getAuthUrl() {
		return authUrl;
	}
	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getAuthType() {
		return authType;
	}
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	public String getAuthId() {
		return authId;
	}
	public void setAuthId(String authId) {
		this.authId = authId;
	}
	
	public void setViewTokenCode(String viewTokenCode) {
		this.viewTokenCode = viewTokenCode;
	}
	public String getViewTokenCode() {
		return viewTokenCode;
	}
	public String getResetTokenType() {
		return resetTokenType;
	}
	public void setResetTokenType(String resetTokenType) {
		this.resetTokenType = resetTokenType;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getCookieId() {
		return cookieId;
	}
	public void setCookieId(String cookieId) {
		this.cookieId = cookieId;
	}
	public List<Application> getAppList() {
		return appList;
	}
	public void setAppList(List<Application> appList) {
		this.appList = appList;
	}
	public String getAppSid() {
		return appSid;
	}
	public void setAppSid(String appSid) {
		this.appSid = appSid;
	}
	public Map<String, Object> getSessionLoss() {
		return sessionLoss;
	}
	public void setSessionLoss(Map<String, Object> sessionLoss) {
		this.sessionLoss = sessionLoss;
	}
	public String getPacketLossTime() {
		return packetLossTime;
	}
	public void setPacketLossTime(String packetLossTime) {
		this.packetLossTime = packetLossTime;
	}
	public String getResultJson() {
		return resultJson;
	}
	public void setResultJson(String resultJson) {
		this.resultJson = resultJson;
	}
}
