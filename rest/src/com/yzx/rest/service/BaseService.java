package com.yzx.rest.service;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzx.rest.util.Consts;
import com.yzx.rest.util.DateUtil;
import com.yzx.rest.util.EncryptUtil;
import com.yzx.rest.util.SysConfig;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class BaseService {
	@Autowired
	protected ServiceManager serviceManager;

	public boolean checkIP(String str) {
		String regex = "^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$";
		boolean is = str.matches(regex);
		return is;
	}
	public boolean checkStr(String str) {
		String regex = "^[a-zA-Z0-9_]+$";
		boolean is = str.matches(regex);
		return is;
	}
	public boolean checkNbr(String str) {
		String regex = "^[0-9.]+$";
		boolean is = str.matches(regex);
		return is;
	}
	public boolean checkPosNum(String str) {
		String regex = "^[0-9]*[1-9][0-9]*$";
		boolean is = str.matches(regex);
		return is;
	}
	public boolean checkNonnegNum(String str) {
		String regex = "^\\d+$";
		boolean is = str.matches(regex);
		return is;
	}
	public boolean checkNum(String str) {
		String regex = "^[0-9]+$";
		boolean is = str.matches(regex);
		return is;
	}

	public String getMobile(String mobile) {
		if (StringUtils.isNotEmpty(mobile)) {
			if (mobile.startsWith("0086")) {
				return mobile.substring(4, mobile.length());
			}
		}
		return mobile;
	}

	public String getMobileNew(String mobile) {
		if (StringUtils.isNotEmpty(mobile)) {
			mobile = formatFreeCallNumNew(mobile);

		} else {
			mobile = "";
		}
		return mobile;
	}
	public boolean checkMobilephone(String phone) {
		phone = formatFreeCallNum(phone);
		return Pattern.compile("^1[3,4,5,7,8]\\d{9}$").matcher(phone != null ? phone : "").matches();
	}

	public boolean checkMobilephoneNew(String phone) {
		// phone=formatFreeCallNum(phone);
		if (phone.startsWith("0") && !phone.startsWith("0086") && phone.length() > 7) {
			// 座机或者国外手机号码
			return Pattern.compile("^0\\d+").matcher(phone != null ? phone : "").matches();
		} else {
			// 国内手机号
			return Pattern.compile("^1[3,4,5,7,8]\\d{9}$").matcher(phone != null ? phone : "").matches();
		}

	}

	public boolean checkInter(String phone) {
		boolean check = false;
		if (checkNum(phone)) {
			if (phone.startsWith("00") && phone.length() >= 10) {
				check = true;
			}
		}
		return check;
	}
	public boolean checkTelphoneNumber(String phone) {
		Matcher m = Pattern.compile("^0(([1-9]\\d)|([3-9]\\d{2}))\\d{8}$")
				.matcher(removePrefix(phone.replace(" ", "")));
		return m.find() || phone.startsWith("400");
	}
	public String removePrefix(String num) {
		if (num != null) {
			try {
				num = num.replaceAll(" ", "");
				num = num.replaceAll("-", "");
				num = num.replace("+", "");
				if (num.matches("^86.*") && num.length() != 7 && num.length() != 8)
					num = num.substring(2);
				if (num.matches("^12593.*|17951.*|17909.*|17911.*")) {
					num = num.substring(5);
				}
				if (num.length() == 12) {
					String newNumber = num.substring(1, num.length());
					if (checkMobilephone(newNumber)) {
						num = newNumber;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return num;
	}

	// 新的手机号码格式规则
	public String formatFreeCallNumNew(String mobile) {

		if ((mobile.startsWith("0") && !mobile.startsWith("0086"))) {

		} else if (mobile.matches("^00861\\d+")) {
			mobile = mobile.substring(4);

		} else if (mobile.matches("^\\+861\\d+")) {
			mobile = mobile.substring(3);

		} else if (mobile.matches("^\\+1\\d+")) {
			mobile = mobile.substring(1);

		} else if (mobile.matches("^861\\d+")) {
			mobile = mobile.substring(2);

		} else if (mobile.matches("^125931\\d+")) {
			mobile = mobile.substring(5);

		} else if (mobile.matches("^179511\\+")) {
			mobile = mobile.substring(5);

		} else if (mobile.matches("^179091\\d+")) {
			mobile = mobile.substring(5);

		} else if (mobile.matches("^179111\\d+")) {
			mobile = mobile.substring(5);

		}
		return mobile;
	}

	public String formatFreeCallNum(String num) {
		// 去除"-","+"字符
		if (num == null)
			return "";
		String oldString = num.replace("-", "");
		oldString = oldString.replace("+", "").replace(" ", "");
		if (oldString.length() < 3) {
			return oldString;
		}
		if (oldString.length() > 4 && oldString.startsWith("0086")) {
			oldString = oldString.substring(4);
		}
		// 去除86前缀
		if (oldString.matches("^86.*") && oldString.length() != 7 && oldString.length() != 8) {
			oldString = oldString.substring(2);
		} else if (oldString.length() == 7 || oldString.length() == 8) { // 没有区号的固话
			return oldString;
		}
		// 去除12593 17951 17909 17911前缀
		if (oldString.matches("^12593.*|17951.*|17909.*|17911.*")) {
			oldString = oldString.substring(5);
		}
		// 以0起始，以数字串结束，并且8-12位
		if (oldString.matches("^(0){1}[1-9]*$") && oldString.matches("[0-9]{8,12}")) {
			return oldString;
		} else {
			if (oldString.startsWith("1")) {
				// 以13，14,15,18起始，并且11位数字
				if (oldString.matches("^13.*|14.*|15.*|18.*")) {
					if (oldString.matches("[0-9]{11}")) {
						return oldString;
					} else {
						return oldString;
					}
				} else {
					return oldString;
				}
			}
		}
		return oldString;
	}
	public boolean isMobileNO(String mobiles) {
		String regex = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
		boolean is = mobiles.matches(regex);
		return is;
	}
	public String getRemoteIp(MessageContext messgeContext) {
		HttpServletRequest request = messgeContext.getHttpServletRequest();
		String ip = request.getHeader("X-Real-IP");
		if (ip == null || ip.equals("")) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	/**
	 * 基础鉴权
	 * @param logger
	 * @param auth
	 * @param sig
	 * @param accountSid
	 * @return int checkAuth
	 */
	public String checkAuth(MessageContext messgeContext, Logger logger, String auth, String sig, String accountSid,
			String version, String appId) {
		String code = auth(messgeContext, logger, auth, sig, accountSid, version, appId);
		return code;
	}
	
	/**
	 * 基础鉴权
	 * @param logger
	 * @param auth
	 * @param sig
	 * @param accountSid
	 * @return int checkAuth
	 */
	public String checkAuth(MessageContext messgeContext, Logger logger, String auth, String sig, String accountSid,
			String version, String appId,boolean isNewVersion) {
		String code = auth(messgeContext, logger, auth, sig, accountSid, version, appId, isNewVersion);
		return code;
	}
	
	/**
	 * 基础鉴权（不包含appId）
	 * @param messgeContext
	 * @param logger
	 * @param auth
	 * @param sig
	 * @param accountSid
	 * @param version
	 * @param isNewVersion
	 * @return
	 */
	public String checkAuth(MessageContext messgeContext, Logger logger, String auth, String sig, String accountSid,
			String version,boolean isNewVersion) {
		String code = auth(messgeContext, logger, auth, sig, accountSid, version, isNewVersion);
		return code;
	}
	
	
	/**
	 * client基础鉴权
	 * 基础鉴权+超级号码鉴权
	 * @param logger
	 * @param auth
	 * @param sig
	 * @param accountSid
	 * @return int checkAuth
	 */
	public String checkAuthForClient(MessageContext messgeContext, Logger logger, String auth, String sig, String accountSid,
			String version, String appId,String clientNum) {
		String code = auth(messgeContext, logger, auth, sig, accountSid, version, appId);
		if(Consts.C000000.equals(code)){
			if(clientNum.startsWith(Consts.SUPER_VOIP_START)){
				code = Consts.C103134 ;
			}
		}
		return code;
	}
	
	/**
	 * 主账号鉴权
	 * @param logger
	 * @param auth
	 * @param sig
	 * @param accountSid
	 * @return JSONObject checkAuth
	 */
	public JSONObject checkAuthForAccount(MessageContext messgeContext, Logger logger, String auth, String sig,
			String accountSid, String version, String appId) {
		EncryptUtil encryptUtil = new EncryptUtil();
		JSONObject jObject = new JSONObject();
		String code = Consts.C000000;
		if (StringUtils.isEmpty(auth)) {
			logger.warn("主账号[" + accountSid + "]请求包头Authorization参数为空");
			code = Consts.C101100;
			jObject.put("code", code);
			return jObject;
		}
		try {
			auth = encryptUtil.base64Decoder(auth);
		} catch (Exception e) {
			logger.warn("主账号[" + accountSid + "]Authorization参数Base64解码失败");
			code = Consts.C101101;
			jObject.put("code", code);
			return jObject;
		}
		String[] auths = auth.split(":");
		if (StringUtils.isEmpty(auths[0])) {
			logger.warn("主账号[" + accountSid + "]Authorization参数解码后账户ID为空");
			code = Consts.C101102;
			jObject.put("code", code);
			return jObject;
		}
		if (StringUtils.isEmpty(auths[1])) {
			logger.warn("主账号[" + accountSid + "]Authorization参数解码后时间戳为空");
			code = Consts.C101103;
			jObject.put("code", code);
			return jObject;
		}
		if (auths[0].length() != 32) {
			logger.warn("主账号[" + accountSid + "]Authorization参数解码后格式有误");
			code = Consts.C101104;
			jObject.put("code", code);
			return jObject;
		}
		if (StringUtils.isEmpty(accountSid)) {
			logger.warn("主账号[" + accountSid + "]主账户ID为空");
			code = Consts.C101112;
			jObject.put("code", code);
			return jObject;
		}
		if (!auths[0].equals(accountSid)) {
			logger.warn("主账号[" + accountSid + "]Authorization参数中账户ID跟请求地址中的账户ID不一致");
			code = Consts.C101113;
			jObject.put("code", code);
			return jObject;
		}
		if (StringUtils.isEmpty(sig)) {
			logger.warn("主账号[" + accountSid + "]Sig参数为空");
			code = Consts.C101114;
			jObject.put("code", code);
			return jObject;
		}
		if (accountSid.length() != 32) {
			logger.warn("主账号[" + accountSid + "]主账户ID长度有误");
			code = Consts.C101104;
			jObject.put("code", code);
			return jObject;
		}
		String regex = "^[a-zA-Z0-9]+$";
		boolean is = accountSid.matches(regex);
		if (!is) {
			logger.warn("主账号[" + accountSid + "]存在非法字符");
			code = Consts.C101105;
			jObject.put("code", code);
			return jObject;
		}
		long reqTime = 0;
		try {
			reqTime = DateUtil.getTime(auths[1]);
		} catch (Exception e) {
		}
		long timeOut = SysConfig.getInstance().getPropertyLong("req_time_out", 10000);
		long nowTime = new Date().getTime();
		if ((reqTime + timeOut * 1000) < nowTime) {
			logger.warn("主账号[" + accountSid + "]Authorization参数解码后时间戳过期");
			code = Consts.C101106;
			jObject.put("code", code);
			return jObject;
		}
		String cversion = SysConfig.getInstance().getProperty("version");
		if (!cversion.equals(version)) {
			logger.warn("主账号[" + accountSid + "]SoftVersion参数有误");
			code = Consts.C101107;
			jObject.put("code", code);
			return jObject;
		}
		String remoteIP = getRemoteIp(messgeContext);
		String host = messgeContext.getHttpServletRequest().getServerName();
		String result = serviceManager.accountInfo(accountSid, appId, remoteIP, host);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String token = null;
		String status = "";
		if (jsonObject == null) {
			logger.warn("HTTP状态码不等于200");
			code = Consts.C100500;
			jObject.put("code", code);
			return jObject;
		} else {
			int retCode = jsonObject.getInt("resultcode");
			if (retCode == 0) {// 0：成功，其他失败
				jObject = jsonObject;
				JSONArray jsonArr = jsonObject.getJSONArray("result");
				JSONObject object = JSONObject.fromObject(jsonArr.get(0));
				token = object.getString("token");
				status = object.getString("status");
				if (StringUtils.isEmpty(status)) {
					code = Consts.C101108;
					jObject.put("code", code);
					return jObject;
				}
				if (status.equals("0")) {
					logger.warn("主账号[" + accountSid + "]未激活");
					code = Consts.C101109;
					jObject.put("code", code);
					return jObject;
				} else if (status.equals("6")) {
					logger.warn("主账号[" + accountSid + "]已关闭");
					code = Consts.C101108;
					jObject.put("code", code);
					return jObject;
				}
			} else if (retCode == 2) {
				logger.warn("访问ip[" + auths[0] + "非法");
				code = Consts.C100005;
				jObject.put("code", code);
				return jObject;
			} else {
				logger.warn("主账号[" + auths[0] + "不存在");
				code = Consts.C101111;
				jObject.put("code", code);
				return jObject;
			}
		}
		if (token == null) {
			logger.warn("token[" + auths[0] + "不存在");
			code = Consts.C101117;
			jObject.put("code", code);
			return jObject;
		}
		String md5 = "";
		try {
			md5 = encryptUtil.md5Digest(auths[0] + token + auths[1]);
		} catch (Exception e) {
		}
		if (!md5.equals(sig)) {
			logger.warn("主账号[" + auths[0] + "]Sig校验失败");
			code = Consts.C101116;
			jObject.put("code", code);
			return jObject;
		}
		code = Consts.C000000;
		jObject.put("code", code);
		return jObject;
	}
	
	private String auth(MessageContext messgeContext, Logger logger, String auth, String sig, String accountSid,
			String version, String appId){
		EncryptUtil encryptUtil = new EncryptUtil();
		String code = Consts.C000000;
		if (StringUtils.isEmpty(auth)) {
			logger.warn("主账号[" + accountSid + "]请求包头Authorization参数为空");
			code = Consts.C101100;
			return code;
		}
		try {
			auth = encryptUtil.base64Decoder(auth);
		} catch (Exception e) {
			logger.warn("主账号[" + accountSid + "]Authorization参数Base64解码失败");
			code = Consts.C101101;
			return code;
		}
		String[] auths = auth.split(":");
		if (StringUtils.isEmpty(auths[0])) {
			logger.warn("主账号[" + accountSid + "]Authorization参数解码后账户ID为空");
			code = Consts.C101102;
			return code;
		}
		if (StringUtils.isEmpty(auths[1])) {
			logger.warn("主账号[" + accountSid + "]Authorization参数解码后时间戳为空");
			code = Consts.C101103;
			return code;
		}
		if (auths[0].length() != 32) {
			logger.warn("主账号[" + accountSid + "]Authorization参数解码后格式有误");
			code = Consts.C101104;
			return code;
		}
		if (StringUtils.isEmpty(accountSid)) {
			logger.warn("主账号[" + accountSid + "]主账户ID为空");
			code = Consts.C101112;
			return code;
		}
		if (!auths[0].equals(accountSid)) {
			logger.warn("主账号[" + accountSid + "]Authorization参数中账户ID跟请求地址中的账户ID不一致");
			code = Consts.C101113;
			return code;
		}
		if (StringUtils.isEmpty(sig)) {
			logger.warn("主账号[" + accountSid + "]Sig参数为空");
			code = Consts.C101114;
			return code;
		}
		if (accountSid.length() != 32) {
			logger.warn("主账号[" + accountSid + "]主账户ID长度有误");
			code = Consts.C101104;
			return code;
		}
		String regex = "^[a-zA-Z0-9]+$";
		boolean is = accountSid.matches(regex);
		if (!is) {
			logger.warn("主账号[" + accountSid + "]存在非法字符");
			code = Consts.C101105;
			return code;
		}
		long reqTime = 0;
		try {
			reqTime = DateUtil.getTime(auths[1]);
		} catch (Exception e) {
		}
		long timeOut = SysConfig.getInstance().getPropertyLong("req_time_out", 300);
		long nowTime = new Date().getTime();
		if ((reqTime + timeOut * 1000) < nowTime) {
			logger.warn("主账号[" + accountSid + "]Authorization参数解码后时间戳过期");
			code = Consts.C101106;
			return code;
		}
		String cversion = SysConfig.getInstance().getProperty("version");
		if (!cversion.equals(version)) {
			logger.warn("主账号[" + accountSid + "]SoftVersion参数有误");
			code = Consts.C101107;
			return code;
		}
		String remoteIP = getRemoteIp(messgeContext);
		String host = messgeContext.getHttpServletRequest().getServerName();
		String result = serviceManager.accountInfo(accountSid, appId, remoteIP, host);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String token = null;
		String status = "";
		if (jsonObject == null) {
			logger.warn("HTTP状态码不等于200");
			code = Consts.C100500;
			return code;
		} else {
			int retCode = jsonObject.getInt("resultcode");
			if (retCode == 0) {// 0：成功，其他失败
				JSONArray jsonArr = jsonObject.getJSONArray("result");
				JSONObject object = JSONObject.fromObject(jsonArr.get(0));
				token = object.getString("token");
				status = object.getString("status");
				if (StringUtils.isEmpty(status)) {
					code = Consts.C101108;
					return code;
				}
				if (status.equals("0")) {
					logger.warn("主账号[" + accountSid + "]未激活");
					code = Consts.C101109;
					return code;
				}
				// else if (status.equals("5")) {
				// logger.warn("主账号["+accountSid+"]已锁定");
				// code=Consts.C101110;
				// return code;
				// }
				else if (status.equals("6")) {
					logger.warn("主账号[" + accountSid + "]已关闭");
					code = Consts.C101108;
					return code;
				}
			} else if (retCode == 2) {
				logger.warn("访问ip[" + remoteIP + "]非法");
				code = Consts.C100005;
				return code;
			}else if (retCode == -94) {
				logger.warn("应用[" + appId + "]未上线");
				code = Consts.C102115;
				return code;
			} else if (retCode == -95) {
				logger.warn("应用[" + appId + "]未审核通过");
				code = Consts.C102116;
				return code;
			} else if (retCode == -96) {
				logger.warn("应用[" + appId + "]已删除");
				code = Consts.C102117;
				return code;
			} else if (retCode == -97) {
				logger.warn("应用[" + appId + "]待审核");
				code = Consts.C102118;
				return code;
			} else if (retCode == -98) {
				logger.warn("应用[" + appId + "]强制下线");
				code = Consts.C102119;
				return code;
			} else if (retCode == -99) {
				logger.warn("应用[" + appId + "]待审核");
				code = Consts.C102118;
				return code;
			} else if (retCode == -100) {
				logger.warn("应用[" + appId + "]不属于该主账号[" + accountSid + "]");
				code = Consts.C102105;
				return code;
			} else if (retCode == -101) {
				logger.warn("应用[" + appId + "]未审核通过");
				code = Consts.C102103;
				return code;
			} else if (retCode == -102) {
				logger.warn("应用[" + appId + "]不存在");
				code = Consts.C102102;
				return code;
			} else if (retCode == -103) {
				logger.warn("主账号[" + auths[0] + "]状态异常");
				code = Consts.C101119;
				return code;
			} else if (retCode == -104) {
				logger.warn("主账号[" + auths[0] + "]注册未激活");
				code = Consts.C101120;
				return code;
			} else if (retCode == -105) {
				logger.warn("主账号[" + auths[0] + "]未填写注册信息");
				code = Consts.C101121;
				return code;
			} else if (retCode == -106) {
				logger.warn("主账号[" + auths[0] + "]已锁定");
				code = Consts.C101122;
				return code;
			} else {
				logger.warn("主账号[" + auths[0] + "]不存在");
				code = Consts.C101111;
				return code;
			}
		}
		if (token == null) {
			logger.warn("token[" + auths[0] + "]不存在");
			code = Consts.C101117;
			return code;
		}
		String md5 = "";
		try {
			md5 = encryptUtil.md5Digest(auths[0] + token + auths[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!md5.equals(sig)) {
			logger.warn("主账号[" + auths[0] + "]Sig校验失败");
			code = Consts.C101115;
			return code;
		}
		return code;
	}
	
	private String auth(MessageContext messgeContext, Logger logger, String auth, String sig, String accountSid,
			String version, String appId, boolean isNewVersion){
		EncryptUtil encryptUtil = new EncryptUtil();
		String code = Consts.C000000;
		if (StringUtils.isEmpty(auth)) {
			logger.warn("主账号[" + accountSid + "]请求包头Authorization参数为空");
			code = Consts.C101100;
			return code;
		}
		try {
			auth = encryptUtil.base64Decoder(auth);
		} catch (Exception e) {
			logger.warn("主账号[" + accountSid + "]Authorization参数Base64解码失败");
			code = Consts.C101101;
			return code;
		}
		String[] auths = auth.split(":");
		if (StringUtils.isEmpty(auths[0])) {
			logger.warn("主账号[" + accountSid + "]Authorization参数解码后账户ID为空");
			code = Consts.C101102;
			return code;
		}
		if (StringUtils.isEmpty(auths[1])) {
			logger.warn("主账号[" + accountSid + "]Authorization参数解码后时间戳为空");
			code = Consts.C101103;
			return code;
		}
		if (auths[0].length() != 32) {
			logger.warn("主账号[" + accountSid + "]Authorization参数解码后格式有误");
			code = Consts.C101104;
			return code;
		}
		if (StringUtils.isEmpty(accountSid)) {
			logger.warn("主账号[" + accountSid + "]主账户ID为空");
			code = Consts.C101112;
			return code;
		}
		if (!auths[0].equals(accountSid)) {
			logger.warn("主账号[" + accountSid + "]Authorization参数中账户ID跟请求地址中的账户ID不一致");
			code = Consts.C101113;
			return code;
		}
		if (StringUtils.isEmpty(sig)) {
			logger.warn("主账号[" + accountSid + "]Sig参数为空");
			code = Consts.C101114;
			return code;
		}
		if (accountSid.length() != 32) {
			logger.warn("主账号[" + accountSid + "]主账户ID长度有误");
			code = Consts.C101104;
			return code;
		}
		String regex = "^[a-zA-Z0-9]+$";
		boolean is = accountSid.matches(regex);
		if (!is) {
			logger.warn("主账号[" + accountSid + "]存在非法字符");
			code = Consts.C101105;
			return code;
		}
		long reqTime = 0;
		try {
			reqTime = DateUtil.getTime(auths[1]);
		} catch (Exception e) {
		}
		long timeOut = SysConfig.getInstance().getPropertyLong("req_time_out", 300);
		long nowTime = new Date().getTime();
		if ((reqTime + timeOut * 1000) < nowTime) {
			logger.warn("主账号[" + accountSid + "]Authorization参数解码后时间戳过期");
			code = Consts.C101106;
			return code;
		}
		String cversion;
		if(isNewVersion){
			cversion = SysConfig.getInstance().getProperty("new_version");
		}else{
			cversion = SysConfig.getInstance().getProperty("version");
		}
		if (!cversion.equals(version)) {
			logger.warn("主账号[" + accountSid + "]SoftVersion参数有误");
			code = Consts.C101107;
			return code;
		}
		String remoteIP = getRemoteIp(messgeContext);
		String host = messgeContext.getHttpServletRequest().getServerName();
		String result = serviceManager.accountInfo(accountSid, appId, remoteIP, host);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String token = null;
		String status = "";
		if (jsonObject == null) {
			logger.warn("HTTP状态码不等于200");
			code = Consts.C100500;
			return code;
		} else {
			int retCode = jsonObject.getInt("resultcode");
			if (retCode == 0) {// 0：成功，其他失败
				JSONArray jsonArr = jsonObject.getJSONArray("result");
				JSONObject object = JSONObject.fromObject(jsonArr.get(0));
				token = object.getString("token");
				status = object.getString("status");
				if (StringUtils.isEmpty(status)) {
					code = Consts.C101108;
					return code;
				}
				if (status.equals("0")) {
					logger.warn("主账号[" + accountSid + "]未激活");
					code = Consts.C101109;
					return code;
				}
				// else if (status.equals("5")) {
				// logger.warn("主账号["+accountSid+"]已锁定");
				// code=Consts.C101110;
				// return code;
				// }
				else if (status.equals("6")) {
					logger.warn("主账号[" + accountSid + "]已关闭");
					code = Consts.C101108;
					return code;
				}
			} else if (retCode == 2) {
				logger.warn("访问ip[" + remoteIP + "]非法");
				code = Consts.C100005;
				return code;
			}else if (retCode == -94) {
				logger.warn("应用[" + appId + "]未上线");
				code = Consts.C102115;
				return code;
			} else if (retCode == -95) {
				logger.warn("应用[" + appId + "]未审核通过");
				code = Consts.C102116;
				return code;
			} else if (retCode == -96) {
				logger.warn("应用[" + appId + "]已删除");
				code = Consts.C102117;
				return code;
			} else if (retCode == -97) {
				logger.warn("应用[" + appId + "]待审核");
				code = Consts.C102118;
				return code;
			} else if (retCode == -98) {
				logger.warn("应用[" + appId + "]强制下线");
				code = Consts.C102119;
				return code;
			} else if (retCode == -99) {
				logger.warn("应用[" + appId + "]待审核");
				code = Consts.C102118;
				return code;
			} else if (retCode == -100) {
				logger.warn("应用[" + appId + "]不属于该主账号[" + accountSid + "]");
				code = Consts.C102105;
				return code;
			} else if (retCode == -101) {
				logger.warn("应用[" + appId + "]未上线");
				code = Consts.C102103;
				return code;
			} else if (retCode == -102) {
				logger.warn("应用[" + appId + "]不存在");
				code = Consts.C102102;
				return code;
			} else if (retCode == -103) {
				logger.warn("主账号[" + auths[0] + "]状态异常");
				code = Consts.C101119;
				return code;
			} else if (retCode == -104) {
				logger.warn("主账号[" + auths[0] + "]注册未激活");
				code = Consts.C101120;
				return code;
			} else if (retCode == -105) {
				logger.warn("主账号[" + auths[0] + "]未填写注册信息");
				code = Consts.C101121;
				return code;
			} else if (retCode == -106) {
				logger.warn("主账号[" + auths[0] + "]已锁定");
				code = Consts.C101122;
				return code;
			} else {
				logger.warn("主账号[" + auths[0] + "]不存在");
				code = Consts.C101111;
				return code;
			}
		}
		if (token == null) {
			logger.warn("token[" + auths[0] + "]不存在");
			code = Consts.C101117;
			return code;
		}
		String md5 = "";
		try {
			md5 = encryptUtil.md5Digest(auths[0] + token + auths[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!md5.equals(sig)) {
			logger.warn("主账号[" + auths[0] + "]Sig校验失败");
			code = Consts.C101115;
			return code;
		}
		return code;
	}
	
	/**
	 * 不包括appId校验
	 * @param messgeContext
	 * @param logger
	 * @param auth
	 * @param sig
	 * @param accountSid
	 * @param version
	 * @param isNewVersion
	 * @return
	 */
	private String auth(MessageContext messgeContext, Logger logger, String auth, String sig, String accountSid,
			String version, boolean isNewVersion){
		EncryptUtil encryptUtil = new EncryptUtil();
		String code = Consts.C000000;
		if (StringUtils.isEmpty(auth)) {
			logger.warn("主账号[" + accountSid + "]请求包头Authorization参数为空");
			code = Consts.C101100;
			return code;
		}
		try {
			auth = encryptUtil.base64Decoder(auth);
		} catch (Exception e) {
			logger.warn("主账号[" + accountSid + "]Authorization参数Base64解码失败");
			code = Consts.C101101;
			return code;
		}
		String[] auths = auth.split(":");
		if (StringUtils.isEmpty(auths[0])) {
			logger.warn("主账号[" + accountSid + "]Authorization参数解码后账户ID为空");
			code = Consts.C101102;
			return code;
		}
		if (StringUtils.isEmpty(auths[1])) {
			logger.warn("主账号[" + accountSid + "]Authorization参数解码后时间戳为空");
			code = Consts.C101103;
			return code;
		}
		if (auths[0].length() != 32) {
			logger.warn("主账号[" + accountSid + "]Authorization参数解码后格式有误");
			code = Consts.C101104;
			return code;
		}
		if (StringUtils.isEmpty(accountSid)) {
			logger.warn("主账号[" + accountSid + "]主账户ID为空");
			code = Consts.C101112;
			return code;
		}
		if (!auths[0].equals(accountSid)) {
			logger.warn("主账号[" + accountSid + "]Authorization参数中账户ID跟请求地址中的账户ID不一致");
			code = Consts.C101113;
			return code;
		}
		if (StringUtils.isEmpty(sig)) {
			logger.warn("主账号[" + accountSid + "]Sig参数为空");
			code = Consts.C101114;
			return code;
		}
		if (accountSid.length() != 32) {
			logger.warn("主账号[" + accountSid + "]主账户ID长度有误");
			code = Consts.C101104;
			return code;
		}
		String regex = "^[a-zA-Z0-9]+$";
		boolean is = accountSid.matches(regex);
		if (!is) {
			logger.warn("主账号[" + accountSid + "]存在非法字符");
			code = Consts.C101105;
			return code;
		}
		long reqTime = 0;
		try {
			reqTime = DateUtil.getTime(auths[1]);
		} catch (Exception e) {
		}
		long timeOut = SysConfig.getInstance().getPropertyLong("req_time_out", 300);
		long nowTime = new Date().getTime();
		if ((reqTime + timeOut * 1000) < nowTime) {
			logger.warn("主账号[" + accountSid + "]Authorization参数解码后时间戳过期");
			code = Consts.C101106;
			return code;
		}
		String cversion;
		if(isNewVersion){
			cversion = SysConfig.getInstance().getProperty("new_version");
		}else{
			cversion = SysConfig.getInstance().getProperty("version");
		}
		if (!cversion.equals(version)) {
			logger.warn("主账号[" + accountSid + "]SoftVersion参数有误");
			code = Consts.C101107;
			return code;
		}
		String remoteIP = getRemoteIp(messgeContext);
		String host = messgeContext.getHttpServletRequest().getServerName();
		String result = serviceManager.accountInfo(accountSid, null, remoteIP, host);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String token = null;
		String status = "";
		if (jsonObject == null) {
			logger.warn("HTTP状态码不等于200");
			code = Consts.C100500;
			return code;
		} else {
			int retCode = jsonObject.getInt("resultcode");
			if (retCode == 0) {// 0：成功，其他失败
				JSONArray jsonArr = jsonObject.getJSONArray("result");
				JSONObject object = JSONObject.fromObject(jsonArr.get(0));
				token = object.getString("token");
				status = object.getString("status");
				if (StringUtils.isEmpty(status)) {
					code = Consts.C101108;
					return code;
				}
				if (status.equals("0")) {
					logger.warn("主账号[" + accountSid + "]未激活");
					code = Consts.C101109;
					return code;
				}
				// else if (status.equals("5")) {
				// logger.warn("主账号["+accountSid+"]已锁定");
				// code=Consts.C101110;
				// return code;
				// }
				else if (status.equals("6")) {
					logger.warn("主账号[" + accountSid + "]已关闭");
					code = Consts.C101108;
					return code;
				}
			} else if (retCode == 2) {
				logger.warn("访问ip[" + remoteIP + "]非法");
				code = Consts.C100005;
				return code;
			} else if (retCode == -103) {
				logger.warn("主账号[" + auths[0] + "]状态异常");
				code = Consts.C101119;
				return code;
			} else if (retCode == -104) {
				logger.warn("主账号[" + auths[0] + "]注册未激活");
				code = Consts.C101120;
				return code;
			} else if (retCode == -105) {
				logger.warn("主账号[" + auths[0] + "]未填写注册信息");
				code = Consts.C101121;
				return code;
			} else if (retCode == -106) {
				logger.warn("主账号[" + auths[0] + "]已锁定");
				code = Consts.C101122;
				return code;
			} else {
				logger.warn("主账号[" + auths[0] + "]不存在");
				code = Consts.C101111;
				return code;
			}
		}
		if (token == null) {
			logger.warn("token[" + auths[0] + "]不存在");
			code = Consts.C101117;
			return code;
		}
		String md5 = "";
		try {
			md5 = encryptUtil.md5Digest(auths[0] + token + auths[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!md5.equals(sig)) {
			logger.warn("主账号[" + auths[0] + "]Sig校验失败");
			code = Consts.C101115;
			return code;
		}
		return code;
	}
}
