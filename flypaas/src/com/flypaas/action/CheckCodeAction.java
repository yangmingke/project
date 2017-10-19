package com.flypaas.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import javax.servlet.ServletException;

import org.apache.struts2.convention.annotation.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.constant.SysConstant;
import com.flypaas.entity.Application;
import com.flypaas.entity.Client;
import com.flypaas.entity.TbFlypaasUser;
import com.flypaas.entity.TestWhiteList;
import com.flypaas.rest.RestClient;
import com.flypaas.timer.TmConstant;
import com.flypaas.timer.TmTask;
import com.flypaas.utils.CheckCodeUtil;
import com.flypaas.utils.DateUtil;
import com.flypaas.utils.MD5Util;
import com.flypaas.utils.StrUtil;
import com.flypaas.utils.SysConfig;

@Controller
@Scope("prototype")
public class CheckCodeAction extends BaseAction {
	
	private String phone;
	private Logger logger = LoggerFactory.getLogger(CheckCodeAction.class);
	private String smsTempType;
	private String expType;
	private String content;

	/*---------------------------------------------打印验证码--------------------------------*/
	@Action("/checkcode/picCheckCode")
	public void checkCode(){
		try {
			CheckCodeUtil.makeCheckCode(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			logger.error("-----------------获取图片验证码失败------------------");
			logger.error(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("-----------------获取图片验证码失败------------------");
			logger.error(e.getMessage());
		}
	}
	
	/*---------------------------------------------语音验证码--------------------------------*/
	@Action("/checkcode/voiceCheckCode")
	public void voiceCode(){
		if(isReqCode(phone)){
			String sid = SysConstant.SUPER_ADMIN_SID;
			String token = SysConstant.SUPER_ADMIN_TOKEN;
			String appSid = SysConstant.SYS_APP_SID;
			String to = phone;
			String verifyCode = StrUtil.getRandom4()+"";
			RestClient.voiceCode(sid, token, appSid, to,verifyCode,SysConstant.REST_CHARGE_TYPE_1);
			try {
				printMsg(MD5Util.GetMD5Code(to+verifyCode));
			} catch (Exception e) {
				logger.error("--------------------输出短信验证码错误------------");
				e.printStackTrace();
			}
			gg(to);
		}
	}
	/*---------------------------------------------短信验证码--------------------------------*/
	@Action("/checkcode/smsCheckCode")
	public void smsCode(){
		if(isReqCode(phone)){
			String sid = SysConstant.SUPER_ADMIN_SID;
			String token = SysConstant.SUPER_ADMIN_TOKEN;
			String appSid = SysConstant.SYS_APP_SID;
			String to = phone;
			String verifyCode = StrUtil.getRandom4()+"";
			String smsTempID = null ;
			if(SysConstant.SMS_TYPE_LOG.equals(smsTempType)){
				smsTempID = SysConstant.SMS_TEMP_LOG_ID;
			}else{
				smsTempID = SysConstant.SMS_TEMP_OTHER_ID;
			}
			RestClient.SMS(sid, token, appSid,smsTempID, to, verifyCode,SysConstant.REST_CHARGE_TYPE_1);
			try {
				printMsg(MD5Util.GetMD5Code(to+verifyCode));
			} catch (Exception e) {
				logger.error("--------------------输出短信验证码错误------------");
				e.printStackTrace();
			}
			gg(to);
		}
	}
	@Action("/checkcode/getSessionCountdown")
	public void getSessionCountdown(){
		Object obj = session.getAttribute(phone);
		if(StrUtil.isEmpty(obj)){
			printMsg("0");
		}else{
			printMsg(obj.toString());
		}
	}
	private void gg(String mobile){
		Timer t = new Timer();
		t.schedule(new TmTask(t,session,mobile,TmConstant.count), 0,TmConstant.time);
	}
	
	private boolean isReqCode(String phone){
		Object obj = session.getAttribute(phone);
		boolean boo = obj==null?true:false;
		return boo;
	}
	private void checkCodeExt(String type){
		String sid = getSessionUser().getSid();
		String token = getSessionUser().getToken();
		Application app = appService.getTestApp(sid);
		String appId = app.getAppSid() ;
		String to = phone;
		String verifyCode = StrUtil.getRandom4()+"";
		if("sms".equals(type)){
			String smsTempID = SysConstant.SMS_TEMP_OTHER_ID;
			RestClient.SMS(sid, token, appId,smsTempID, to, verifyCode,SysConstant.REST_CHARGE_TYPE_0);
		}else if("voice".equals(type)){
			RestClient.voiceCode(sid, token, appId, to,verifyCode,SysConstant.REST_CHARGE_TYPE_0);
		}
		try {
			printMsg(MD5Util.GetMD5Code(to+verifyCode));
		} catch (Exception e) {
			logger.error("--------------------输出短信验证码错误------------");
			e.printStackTrace();
		}
		gg(to);
	}
	/*---------------------------------------------首页短信验证码体验--------------------------------*/
	@Action("/checkcode/smsExtCode")
	public void smsCodeExt(){
		if(isReqCode(phone)){
			expr();
			//收费体验(使用测试demo)
			checkCodeExt("sms");
		}
	}
	/*---------------------------------------------首页语音验证码体验--------------------------------*/
	@Action("/checkcode/voiceExtCode")
	public void voiceCodeExt(){
		if(isReqCode(phone)){
			expr();
			//收费体验(使用测试demo)
			checkCodeExt("voice");
		}
	}
	private void expr(){
		String ip = StrUtil.getClientIP(request);
		String to = phone;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mobile", to);
		map.put("ip", ip);
		map.put("type", SysConstant.EXPR_TYPE_1);
		int mcount = otherService.getExperienceCountByMobile(map);
		int ipcount = otherService.getExperienceCountByIp(map);
		int s_ip_count = Integer.parseInt(SysConfig.getInstance().getProperty("exp_ip_count"));
		int s_mobile_count = Integer.parseInt(SysConfig.getInstance().getProperty("exp_mobile_count"));
		if(mcount>=s_mobile_count || ipcount>=s_ip_count){
			printMsg("fail");
			return;
		}
		map.put("create_date", DateUtil.getCurrentDate());
		otherService.addExperience(map);
	}
	@Action("/checkcode/exprCount")
	public void exprCount(){
		String ip = StrUtil.getClientIP(request);
		String to = phone;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mobile", to);
		map.put("ip", ip);
		map.put("type", expType);
		int mcount = otherService.getExperienceCountByMobile(map);
		int ipcount = otherService.getExperienceCountByIp(map);
		int s_ip_count = Integer.parseInt(SysConfig.getInstance().getProperty("exp_ip_count"));
		int s_mobile_count = Integer.parseInt(SysConfig.getInstance().getProperty("exp_mobile_count"));
		if(mcount>=s_mobile_count || ipcount>=s_ip_count){
			printMsg("fail");
		}else{
			printMsg("suc");
		}
	}
	@Action("/notify/cloudNotify")
	public void cloudNotify(){
		TbFlypaasUser user = getSessionUser();
		Application app = appService.getTestApp(user.getSid());
		String appId = app.getAppSid() ;
		String to = phone;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sid", user.getSid());
		params.put("app_sid", appId);
		params.put("content",content);
		params.put("to", to);
		otherService.insertCloudNotifyCall(params);
	}
	
	@Action("/checkcode/check4token")
	public void check4token(){
		String user_sid = getSessionUser().getSid();
		String to = userService.findUserById(user_sid).getMobile();
		String verifyCode = StrUtil.getRandom4()+"";
		String sid = SysConstant.SUPER_ADMIN_SID;
		String token = SysConstant.SUPER_ADMIN_TOKEN;
		String appId = SysConstant.SYS_APP_SID;
		int code = 0;
		if("sms".equals(expType)){
			RestClient.SMS(sid, token, appId,SysConstant.SMS_TEMP_OTHER_ID,to,verifyCode,SysConstant.REST_CHARGE_TYPE_0);
		}else if("voicecode".equals(expType)){
			RestClient.voiceCode(sid, token, appId, to,verifyCode,SysConstant.REST_CHARGE_TYPE_0);
		}else{
			code = 1;
		}
		try {
			if(null != session){
				session.setAttribute("session_check4token",verifyCode);
			}
			printMsg(code + MD5Util.GetMD5Code(user_sid+verifyCode));
		} catch (Exception e) {
			logger.error("--------------------输出短信验证码错误------------");
			e.printStackTrace();
		}
	}
	/*---------------------------------------------回拨--------------------------------*/
	@Action("/freetrial/callBack")
	public void callBack(){
		expr();
		callBack1();
		
	}
	
	private void callBack1(){
		String sid = getSessionUser().getSid();
		String mobile = getSessionUser().getMobile();
		String token = getSessionUser().getToken();
		Application app = appService.getTestApp(sid);
		String appSid = app.getAppSid() ;
		Client client = new Client();
		client.setSid(sid);
		client.setAppSid(appSid);
		client.setMobile(mobile);
		Map<String, Object> map = clientService.getClient(client);
		if(StrUtil.isEmpty(map)){
			logger.info("号码:"+mobile+",未绑定clientnum");
			return;
		}
		TestWhiteList testWhiteList = new TestWhiteList();
		testWhiteList.setSid(sid);
		testWhiteList.setMobile(phone);
		testWhiteList = testWhilteListService.get(testWhiteList);
		if(StrUtil.isEmpty(map)){
			logger.info("号码:"+phone+",不合法(非白名单号码)");
			return;
		}
		RestClient.callback(sid, token, appSid, map.get("client_number").toString(), phone, null);
	}

	
	/*---------------------------------------------get set--------------------------------*/
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSmsTempType() {
		return smsTempType;
	}
	public void setSmsTempType(String smsTempType) {
		this.smsTempType = smsTempType;
	}
	public String getExpType() {
		return expType;
	}
	public void setExpType(String expType) {
		this.expType = expType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
