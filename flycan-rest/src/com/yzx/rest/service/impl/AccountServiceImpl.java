package com.yzx.rest.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.yzx.rest.models.Account;
import com.yzx.rest.models.Clientt;
import com.yzx.rest.models.SessionInfo;
import com.yzx.rest.resp.ListResp;
import com.yzx.rest.resp.ListRespt;
import com.yzx.rest.service.AccountService;
import com.yzx.rest.service.BaseService;
import com.yzx.rest.service.DBCommService;
import com.yzx.rest.service.RedisService;
import com.yzx.rest.util.Consts;
import com.yzx.rest.util.DateUtil;
import com.yzx.rest.util.EncryptUtil;
import com.yzx.rest.util.HttpRequest;
import com.yzx.rest.util.JsonUtils;
import com.yzx.rest.util.SqlCode;
import com.yzx.rest.util.SysConfig;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service("accountService")
public class AccountServiceImpl extends BaseService implements AccountService {
	private static Logger logger = Logger.getLogger(AccountService.class);
	@Context
	public MessageContext messgeContext;
	
	@Override
	public ListResp authentic(String version, String accountSid, String auth, String sig) {
		ListResp listResp = new ListResp();
		long time1 = new Date().getTime();
		String code = checkAuth(messgeContext, logger, auth, sig, accountSid, version, true);
		if (code.equals(Consts.C000000)) {
			listResp.setRespCode(Consts.C000000, Consts.authentic, "", "");
		}else{
			listResp.setRespCode(code, Consts.authentic, "", "");
		}
		long time2 = new Date().getTime();
		logger.info("用户基础信息鉴权请求时长" + (time2 - time1));
		return listResp;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ListResp getAplist(String version, String accountSid, String Authorization, String sig,SessionInfo sessionInfo) {
		ListResp listResp = new ListResp();
		long time1 = new Date().getTime();
		String appId = sessionInfo.getAppId();
		String ip = sessionInfo.getIp();
		if (StringUtils.isEmpty(appId)) {
			listResp.setRespCode(Consts.C102100, Consts.applyRoute, appId, "");
			return listResp;
		}
		if (!checkStr(appId)) {
			listResp.setRespCode(Consts.C102101, Consts.applyRoute, appId, "");
			return listResp;
		}
		if(StringUtils.isNotEmpty(ip)){
			if (!checkIP(ip)) {
				listResp.setRespCode(Consts.C106002, Consts.applyRoute, appId, "");
				return listResp;
			}
		}else{
			Message message = PhaseInterceptorChain.getCurrentMessage();  
			HttpServletRequest httprequest = (HttpServletRequest)message.get(AbstractHTTPDestination.HTTP_REQUEST);  
			ip = httprequest.getRemoteAddr();
		}
		
		String code = checkAuth(messgeContext, logger, Authorization, sig, accountSid, version, appId, true);
		if (code.equals(Consts.C000000)) {
			String routeArea = getRouteArea(appId);
			String routerRtppUrl = SysConfig.getInstance().getProperty("router_rtpp");
			StringBuffer para= new StringBuffer();
			para.append("?srcip=").append(ip);
			HttpRequest req = new HttpRequest();
			HttpResponse response;
			try {
				//获取源IP信息
				response = req.get(routerRtppUrl+"?ip="+ip+"&area="+routeArea);
				//获取响应实体信息
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String result = EntityUtils.toString(entity, "UTF-8");
					logger.info(routerRtppUrl + "获取resultInfo:"+result);
					Map map = JsonUtils.toObject(result, Map.class);
					List rtppList = (List) map.get("rtpp");
					// 确保HTTP响应内容全部被读出或者内容流被关闭
					EntityUtils.consume(entity);
					logger.info("获取源IP"+ip+"信息成功");
					listResp.setApList(rtppList);
					listResp.setRespCode(Consts.C000000, Consts.applyRoute, appId, "");
				}
			} catch (Exception e) {
				e.printStackTrace();
				listResp.setRespCode(Consts.C100699, Consts.applyRoute, appId, "");
			}
		}else{
			listResp.setRespCode(code, Consts.releaseRoute, appId, "");
		}
		long time2 = new Date().getTime();
		logger.info("获取源IP信息请求时长" + (time2 - time1));
		return listResp;
	}
	

	
	
	@SuppressWarnings("unchecked")
	@Override
	public ListResp createSession(String version, String accountSid, String Authorization,String sig, SessionInfo sessionInfo) {
		ListResp listResp = new ListResp();
		long time1 = new Date().getTime();
		String appId = sessionInfo.getAppId();
//		String cookie = sessionInfo.getSessionId();
		String udpPortNum = sessionInfo.getUdpPortNum();
		String vflag = sessionInfo.getVflag();
		String policy = sessionInfo.getPolicy();
		String hopNum = sessionInfo.getHopNum();
		String routeNum = sessionInfo.getRouteNum();
		String protocol = sessionInfo.getProtocol();
		String srcIp = sessionInfo.getSrcIp();
		String srcPort = sessionInfo.getSrcPort();
		String dstIp = sessionInfo.getDstIp();
		String dstPort = sessionInfo.getDstPort();
		List srcApList = sessionInfo.getSrcApList();
		List dstApList = sessionInfo.getDstApList();
		
		if (StringUtils.isEmpty(appId)) {
			listResp.setRespCode(Consts.C102100, Consts.applyRoute, appId, "");
			return listResp;
		}
		if (!checkStr(appId)) {
			listResp.setRespCode(Consts.C102101, Consts.applyRoute, appId, "");
			return listResp;
		}
		/*if (StringUtils.isEmpty(cookie)) {
			listResp.setRespCode(Consts.C106000, Consts.applyRoute, appId, "");
			return listResp;
		}else{
			try {
				String ip = InetAddress.getLocalHost().getHostAddress();
				cookie = ip+"@"+cookie+"@"+ Consts.cookie_suffix;
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}*/
		if (StringUtils.isEmpty(srcIp)) {
			listResp.setRespCode(Consts.C106001, Consts.applyRoute, appId, "");
			return listResp;
		}
		if (!checkIP(srcIp)) {
			listResp.setRespCode(Consts.C106002, Consts.applyRoute, appId, "");
			return listResp;
		}
		if (StringUtils.isEmpty(dstIp)) {
			listResp.setRespCode(Consts.C106003, Consts.applyRoute, appId, "");
			return listResp;
		}
		if (!checkIP(dstIp)) {
			listResp.setRespCode(Consts.C106004, Consts.applyRoute, appId, "");
			return listResp;
		}
		if(StringUtils.isEmpty(policy)){
			policy = "1";
		}else{
			if(!checkNonnegNum(policy)){
				listResp.setRespCode(Consts.C106005, Consts.applyRoute, appId, "");
				return listResp;
			}
			int policyNum = Integer.valueOf(policy);
			if(policyNum > 3){
				listResp.setRespCode(Consts.C106005, Consts.applyRoute, appId, "");
				return listResp;
			}
		}
		if(StringUtils.isEmpty(hopNum)){
			hopNum = "0";
		}else if(!checkNonnegNum(hopNum)){
			listResp.setRespCode(Consts.C106006, Consts.applyRoute, appId, "");
			return listResp;
		}
		if(StringUtils.isEmpty(routeNum)){
			routeNum = "1";
		}else if(!checkPosNum(routeNum) || Integer.valueOf(routeNum) > 2){
			listResp.setRespCode(Consts.C106007, Consts.applyRoute, appId, "");
			return listResp;
		}
		if(StringUtils.isEmpty(protocol)){
			protocol = "udp";
		}
		if(StringUtils.isEmpty(udpPortNum)){
			udpPortNum = "1";
		}else if(!checkPosNum(udpPortNum)){
			listResp.setRespCode(Consts.C106013, Consts.applyRoute, appId, "");
			return listResp;
		}
		if(StringUtils.isEmpty(vflag)){
			vflag = "1";
		}else if(!"1".equals(vflag) && !"0".equals(vflag)){
			listResp.setRespCode(Consts.C106014, Consts.applyRoute, appId, "");
			return listResp;
		}
		
		String code = checkAuth(messgeContext, logger, Authorization, sig, accountSid, version, appId,true);
		if (code.equals(Consts.C000000)) {
			List  paramList =  new ArrayList();
			paramList.add(new Object[]{"string", accountSid});
			List<Map<String,String>> acctBalanceList = DBCommService.getInstance().queryForListStr(SqlCode.QUERY_BALANCE, paramList, Consts.CON_MASTER);
			Map<String,String> acctBalanceMap = acctBalanceList.get(0);
			String enableFlag = acctBalanceMap.get("enable_flag");
			if(!"1".equals(enableFlag)){
				listResp.setRespCode(Consts.C106015, Consts.applyRoute, appId, "");
				return listResp;
			}
			long acctBalance = Long.valueOf(acctBalanceMap.get("balance"));
			if(acctBalance <= 0){
				listResp.setRespCode(Consts.C106009, Consts.applyRoute, appId, "");
				return listResp;
			}
			String routeArea = getRouteArea(appId);
			HttpRequest req = new HttpRequest();
			HttpResponse response;
			HttpEntity entity;
			String routerRtppUrl = SysConfig.getInstance().getProperty("router_rtpp");
			String srcinfo = new String();
			String dstinfo = new String();
			try {
				if(srcApList == null || srcApList.size() == 0){
					//获取源IP信息
					long apptime1 = new Date().getTime();
					response = req.get(routerRtppUrl+"?ip="+srcIp+"&area="+routeArea);
					//获取响应实体信息
					entity = response.getEntity();
					if (entity != null) {
						String result = EntityUtils.toString(entity, "UTF-8");
						logger.info(routerRtppUrl + "获取resultInfo:"+result);
						Map map = JsonUtils.toObject(result, Map.class);
						List rtppList = (List) map.get("rtpp");
						srcinfo = createIpInfo(srcIp,srcPort,rtppList);
						// 确保HTTP响应内容全部被读出或者内容流被关闭
						EntityUtils.consume(entity);
						long apptime2 = new Date().getTime();
						logger.info("获取源IP"+srcIp+"&area="+routeArea+"信息成功,请求时长:"+(apptime2 - apptime1));
					}
				}else{
					srcinfo = createApInfo(srcIp,srcPort,srcApList);
					if(StringUtils.isEmpty(srcinfo)){
						listResp.setRespCode(Consts.C106011, Consts.applyRoute, appId, "");
						return listResp;
					}
				}
				if(dstApList == null || dstApList.size() == 0){
					//获取目的IP信息
					long apptime1 = new Date().getTime();
					response = req.get(routerRtppUrl+"?ip="+dstIp+"&area="+routeArea);
					//获取响应实体信息
					entity = response.getEntity();
					if (entity != null) {
						String result = EntityUtils.toString(entity, "UTF-8");
						logger.info(routerRtppUrl + "获取resultInfo:"+result);
						Map map = JsonUtils.toObject(result, Map.class);
						List rtppList = (List) map.get("rtpp");
						dstinfo = createIpInfo(dstIp,dstPort,rtppList);
						// 确保HTTP响应内容全部被读出或者内容流被关闭
						EntityUtils.consume(entity);
						long apptime2 = new Date().getTime();
						logger.info("获取目的"+dstIp+"&area="+routeArea+"信息成功,请求时长:"+(apptime2 - apptime1));
					}
				}else{
					dstinfo = createApInfo(dstIp,dstPort,dstApList);
					if(StringUtils.isEmpty(dstinfo)){
						listResp.setRespCode(Consts.C106012, Consts.applyRoute, appId, "");
						return listResp;
					}
				}
				//访问route
				srcinfo = JsonUtils.json2URLCode(srcinfo); 
				dstinfo = JsonUtils.json2URLCode(dstinfo); 
				StringBuffer para= new StringBuffer();
				para.append("?sid=").append(accountSid).append("&appid=").append(appId).append("&policy=").append(policy)
				.append("&hopnum=").append(hopNum).append("&routenum=").append(routeNum).append("&protocol=")
				.append(protocol).append("&srcinfo=").append(srcinfo).append("&dstinfo=").append(dstinfo)
				.append("&udpportnum=").append(udpPortNum).append("&vflag=").append(vflag).append("&area=").append(routeArea);
				
				String sessionCreateUrl = SysConfig.getInstance().getProperty("session_create");
				long apptime1 = new Date().getTime();
				response = req.get(sessionCreateUrl+para);
				//获取响应实体信息
				entity = response.getEntity();
				if (entity != null) {
					String result = EntityUtils.toString(entity, "UTF-8");
					logger.info("创建会话路径参数:"+sessionCreateUrl+para);
					logger.info(sessionCreateUrl + "获取resultInfo:"+result);
					Map map = JsonUtils.toObject(result, Map.class);
					String respCode = (String) map.get("result");
					if(respCode !=null && !Consts.C000000.equals(respCode)){
						listResp.setRespCode(Consts.C106010, Consts.applyRoute, appId, "");
						return listResp;
					}
					List RouteList = new ArrayList();
					String sessionId = (String) map.get("sessionid");
					listResp.setSessionId(sessionId);
					List<Map> tempList = (List) map.get("routes");
					for(Map tempMap : tempList){
						SessionInfo temp = new SessionInfo();
						double drouteid = (double) tempMap.get("routeid");
						Integer routeid = (int)drouteid;
						temp.setRouteid(routeid.toString());
						temp.setSrcApIp(tempMap.get("srcapip").toString());
						temp.setSrcApPort(tempMap.get("srcapport").toString());
						temp.setDstApIp(tempMap.get("dstapip").toString());
						temp.setDstApPort(tempMap.get("dstapport").toString());
						RouteList.add(temp);
					}
					listResp.setSessionInfo(RouteList);
					listResp.setRespCode(Consts.C000000, Consts.applyRoute, appId, "");
					// 确保HTTP响应内容全部被读出或者内容流被关闭
					EntityUtils.consume(entity);
					long apptime2 = new Date().getTime();
					logger.info("申请"+appId+"路由成功,请求时长:"+(apptime2 - apptime1));
				}
			} catch (Exception e) {
				e.printStackTrace();
				listResp.setRespCode(Consts.C100699, Consts.applyRoute, appId, "");
				return listResp;
			}
		}else{
			listResp.setRespCode(code, Consts.applyRoute, appId, "");
		}
		long time2 = new Date().getTime();
		logger.info("申请路由请求时长" + (time2 - time1));
		return listResp;
	}
	
	private String createApInfo(String srcIp, String port, List<SessionInfo> srcApList) {
		JsonObject ipInfo = new JsonObject();
		ipInfo.addProperty("ip", srcIp);
		if(port != null && !"".equals(port)){
			ipInfo.addProperty("port", port);
		}
		ipInfo.addProperty("uid", srcIp);
		JsonArray aplist = new JsonArray();
		for(int i = 0; i < srcApList.size(); i++){
			JsonObject temp = new JsonObject();
			SessionInfo srcAp = srcApList.get(i);
			String ip = srcAp.getIp();
			String delay = srcAp.getDelay();
			if(checkIP(ip)){
				temp.addProperty("ip", ip);
			}else{
				return null;
			}
			if(StringUtils.isEmpty(delay)){
				temp.addProperty("delay", 0);
			}else{
				temp.addProperty("delay", Integer.parseInt(delay));
			}
			temp.addProperty("lost", 0);
			aplist.add(temp);
		}
		ipInfo.add("aplist", aplist);
		return ipInfo.toString();
	}

	private String createIpInfo(String srcIp, String port, List rtppList) {
		JsonObject ipInfo = new JsonObject();
		ipInfo.addProperty("ip", srcIp);
		if(port != null && !"".equals(port)){
			ipInfo.addProperty("port", port);
		}
		ipInfo.addProperty("uid", srcIp);
		JsonArray aplist = new JsonArray();
		for(int i = 0; i < rtppList.size(); i++){
			JsonObject temp = new JsonObject();
			String ip = (String) rtppList.get(i);
			temp.addProperty("ip", ip.split(":")[0]);
			temp.addProperty("delay", 0);
			temp.addProperty("lost", 0);
			aplist.add(temp);
		}
		ipInfo.add("aplist", aplist);
		return ipInfo.toString();
	}

	@Override
	public ListResp releaseSession(String version, String accountSid, String Authorization, String sig, SessionInfo sessionInfo) {
		ListResp listResp = new ListResp();
		long time1 = new Date().getTime();
		String appId = sessionInfo.getAppId();
		String sessionId = sessionInfo.getSessionId();
		String routeid = sessionInfo.getRouteid();
		if (StringUtils.isEmpty(appId)) {
			listResp.setRespCode(Consts.C102100, Consts.applyRoute, appId, "");
			return listResp;
		}
		if (!checkStr(appId)) {
			listResp.setRespCode(Consts.C102101, Consts.applyRoute, appId, "");
			return listResp;
		}
		if (StringUtils.isEmpty(sessionId)) {
			listResp.setRespCode(Consts.C106000, Consts.applyRoute, appId, "");
			return listResp;
		}
		
		String code = checkAuth(messgeContext, logger, Authorization, sig, accountSid, version, appId, true);
		if (code.equals(Consts.C000000)) {
			String sessionReleaseUrl = SysConfig.getInstance().getProperty("session_release");
			StringBuffer para= new StringBuffer();
			para.append("?sid=").append(accountSid).append("&appid=").append(appId).append("&sessionid=").append(sessionId);
			if(StringUtils.isNotEmpty(routeid)){
				para.append("&routeid=").append(routeid);
			}
			HttpRequest req = new HttpRequest();
			HttpResponse response;
			try {
				response = req.get(sessionReleaseUrl+para);
				//获取响应实体信息
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String result = EntityUtils.toString(entity, "UTF-8");
					logger.info(sessionReleaseUrl + "获取resultInfo:"+result);
					Map map = JsonUtils.toObject(result, Map.class);
					String respCode = (String) map.get("result");
					if(!Consts.C000000.equals(respCode)){
						listResp.setDescb("false");
						listResp.setRespCode(Consts.C106010, Consts.applyRoute, appId, "");
						return listResp;
					}
					listResp.setDescb("success");
					listResp.setRespCode(Consts.C000000, Consts.applyRoute, appId, "");
					// 确保HTTP响应内容全部被读出或者内容流被关闭
					EntityUtils.consume(entity);
					logger.info("释放"+appId+":"+sessionId+"路由成功");
				}
			} catch (Exception e) {
				e.printStackTrace();
				listResp.setRespCode(Consts.C100699, Consts.applyRoute, appId, "");
			}
		}else{
			listResp.setRespCode(code, Consts.releaseRoute, appId, "");
		}
		long time2 = new Date().getTime();
		logger.info("释放路由请求时长" + (time2 - time1));
		return listResp;
	}
	
	
	@Override
	public ListRespt login(String version, String auth, String sig, Account account) {
		ListRespt listResp = new ListRespt();
		long time1 = new Date().getTime();
		try {
			String acct = account.getUsername();
			String password = account.getPassword();
			if (StringUtils.isEmpty(acct)) {
				listResp.setRespCode(Consts.C100010, Consts.login, "", "");
				return listResp;
			} else {
				if (acct.indexOf("@") < 0) {
					acct = acct.trim();
					if (!checkMobilephone(acct)) {
						listResp.setRespCode(Consts.C100006, Consts.login, "", "");
						return listResp;
					}
				} else {
					if (acct.indexOf("@") < 1 || acct.indexOf(".") < 1) {
						listResp.setRespCode(Consts.C100011, Consts.login, "", "");
						return listResp;
					}
				}
			}
			if (StringUtils.isEmpty(password)) {
				listResp.setRespCode(Consts.C100012, Consts.login, "", "");
				return listResp;
			}
			EncryptUtil encryptUtil = new EncryptUtil();
			if (StringUtils.isEmpty(auth)) {
				logger.warn("登录用户[" + acct + "]请求包头Authorization参数为空");
				listResp.setRespCode(Consts.C101100, Consts.login, "", "");
				return listResp;
			}
			try {
				auth = encryptUtil.base64Decoder(auth);
			} catch (Exception e) {
				logger.warn("登录用户[" + acct + "]Authorization参数Base64解码失败");
				listResp.setRespCode(Consts.C101101, Consts.login, "", "");
				return listResp;
			}
			String[] auths = auth.split(":");
			if (StringUtils.isEmpty(auths[0])) {
				logger.warn("登录用户[" + acct + "]Authorization参数解码后账户ID为空");
				listResp.setRespCode(Consts.C101102, Consts.login, "", "");
				return listResp;
			}
			if (StringUtils.isEmpty(auths[1])) {
				logger.warn("登录用户[" + acct + "]Authorization参数解码后时间戳为空");
				listResp.setRespCode(Consts.C101103, Consts.login, "", "");
				return listResp;
			}
			if (!auths[0].equals(acct)) {
				logger.warn("登录用户[" + acct + "]Authorization参数中账户跟请求地址中的账户不一致");
				listResp.setRespCode(Consts.C101113, Consts.login, "", "");
				return listResp;
			}
			if (StringUtils.isEmpty(sig)) {
				logger.warn("主账号[" + acct + "]Sig参数为空");
				listResp.setRespCode(Consts.C101114, Consts.login, "", "");
				return listResp;
			}
			long reqTime = 0;
			try {
				reqTime = DateUtil.getTime(auths[1]);
			} catch (Exception e) {
			}
			long timeOut = SysConfig.getInstance().getPropertyLong("req_time_out", 300);
			long nowTime = new Date().getTime();
			if ((reqTime + timeOut * 1000) < nowTime) {
				logger.warn("主账号[" + acct + "]Authorization参数解码后时间戳过期");
				listResp.setRespCode(Consts.C101106, Consts.login, "", "");
				return listResp;
			}
			String cversion = SysConfig.getInstance().getProperty("version");
			if (!cversion.equals(version)) {
				logger.warn("主账号[" + acct + "]SoftVersion参数有误");
				listResp.setRespCode(Consts.C101107, Consts.login, "", "");
				return listResp;
			}
			String result = serviceManager.clientLogin(acct, password);
			JSONObject jsonObject = JSONObject.fromObject(result);
			if (jsonObject == null) {
				logger.warn("HTTP状态码不等于200");
				listResp.setRespCode(Consts.C100500, Consts.login, "", "");
				return listResp;
			} else {
				int retCode = jsonObject.getInt("resultcode");
				if (retCode == 0) {// 0：成功，其他失败
					listResp.setRespCode(Consts.C000000, Consts.login, "", "");
					listResp.setAppId(jsonObject.getString("app_sid"));
					listResp.setSid(jsonObject.getString("sid"));
					listResp.setToken(jsonObject.getString("token"));
					JSONArray jArray = JSONArray.fromObject(jsonObject.get("client"));
					List<Clientt> list = new ArrayList<Clientt>();
					if (jArray != null && jArray.size() > 0) {
						for (int i = 0; i < jArray.size(); i++) {
							JSONObject jsonObjSec = JSONObject.fromObject(jArray.get(i));
							Clientt client = new Clientt();
							client.setMobile(jsonObjSec.getString("mobile"));
							client.setClient_pwd(jsonObjSec.getString("client_pwd"));
							client.setClient_number(jsonObjSec.getString("client_number"));
							list.add(client);
						}
					}
					listResp.setClients(list);
				} else if (retCode == 2) {
					logger.warn("访问ip[" + auths[0] + "非法");
					listResp.setRespCode(Consts.C100005, Consts.login, "", "");
					return listResp;
				} else if (retCode == 3) {
					listResp.setRespCode(Consts.C101111, Consts.login, "", "");
					return listResp;
				} else if (retCode == 4) {
					logger.warn("主账号[" + acct + "]未激活");
					listResp.setRespCode(Consts.C101109, Consts.login, "", "");
					return listResp;
				} else if (retCode == 5) {
					logger.warn("主账号[" + acct + "]已关闭");
					listResp.setRespCode(Consts.C101108, Consts.login, "", "");
					return listResp;
				} else if (retCode == 6) {
					listResp.setRespCode(Consts.C100013, Consts.login, "", "");
					return listResp;
				}
			}
		} catch (Exception e) {
			logger.error(e);
			listResp.setRespCode(Consts.C100699, Consts.login, "", "");
		}
		long time2 = new Date().getTime();
		logger.info("登录请求时长" + (time2 - time1));
		return listResp;
	}
	
	@Override
	public ListRespt loginNewDemo(String version, String auth, String sig, Account account) {
		ListRespt listResp = new ListRespt();
		long time1 = new Date().getTime();
		try {
			String acct = account.getUsername();
			String password = account.getPassword();
			if (StringUtils.isEmpty(acct)) {
				listResp.setRespCode(Consts.C100010, Consts.login, "", "");
				return listResp;
			} else {
				if (acct.indexOf("@") < 0) {
					acct = acct.trim();
					if (!checkMobilephone(acct)) {
						listResp.setRespCode(Consts.C100006, Consts.login, "", "");
						return listResp;
					}
				} else {
					if (acct.indexOf("@") < 1 || acct.indexOf(".") < 1) {
						listResp.setRespCode(Consts.C100011, Consts.login, "", "");
						return listResp;
					}
				}
			}
			if (StringUtils.isEmpty(password)) {
				listResp.setRespCode(Consts.C100012, Consts.login, "", "");
				return listResp;
			}
			EncryptUtil encryptUtil = new EncryptUtil();
			if (StringUtils.isEmpty(auth)) {
				logger.warn("登录用户[" + acct + "]请求包头Authorization参数为空");
				listResp.setRespCode(Consts.C101100, Consts.login, "", "");
				return listResp;
			}
			try {
				auth = encryptUtil.base64Decoder(auth);
			} catch (Exception e) {
				logger.warn("登录用户[" + acct + "]Authorization参数Base64解码失败");
				listResp.setRespCode(Consts.C101101, Consts.login, "", "");
				return listResp;
			}
			String[] auths = auth.split(":");
			if (StringUtils.isEmpty(auths[0])) {
				logger.warn("登录用户[" + acct + "]Authorization参数解码后账户ID为空");
				listResp.setRespCode(Consts.C101102, Consts.login, "", "");
				return listResp;
			}
			if (StringUtils.isEmpty(auths[1])) {
				logger.warn("登录用户[" + acct + "]Authorization参数解码后时间戳为空");
				listResp.setRespCode(Consts.C101103, Consts.login, "", "");
				return listResp;
			}
			if (!auths[0].equals(acct)) {
				logger.warn("登录用户[" + acct + "]Authorization参数中账户跟请求地址中的账户不一致");
				listResp.setRespCode(Consts.C101113, Consts.login, "", "");
				return listResp;
			}
			if (StringUtils.isEmpty(sig)) {
				logger.warn("主账号[" + acct + "]Sig参数为空");
				listResp.setRespCode(Consts.C101114, Consts.login, "", "");
				return listResp;
			}
			long reqTime = 0;
			try {
				reqTime = DateUtil.getTime(auths[1]);
			} catch (Exception e) {
			}
			long timeOut = SysConfig.getInstance().getPropertyLong("req_time_out", 300);
			long nowTime = new Date().getTime();
			if ((reqTime + timeOut * 1000) < nowTime) {
				logger.warn("主账号[" + acct + "]Authorization参数解码后时间戳过期");
				listResp.setRespCode(Consts.C101106, Consts.login, "", "");
				return listResp;
			}
			String cversion = SysConfig.getInstance().getProperty("version");
			if (!cversion.equals(version)) {
				logger.warn("主账号[" + acct + "]SoftVersion参数有误");
				listResp.setRespCode(Consts.C101107, Consts.login, "", "");
				return listResp;
			}
			String result = serviceManager.clientLoginNew(acct, password);
			JSONObject jsonObject = JSONObject.fromObject(result);
			if (jsonObject == null) {
				logger.warn("HTTP状态码不等于200");
				listResp.setRespCode(Consts.C100500, Consts.login, "", "");
				return listResp;
			} else {
				int retCode = jsonObject.getInt("resultcode");
				if (retCode == 0) {// 0：成功，其他失败
					listResp.setRespCode(Consts.C000000, Consts.login, "", "");
					listResp.setAppId(jsonObject.getString("app_sid"));
					listResp.setSid(jsonObject.getString("sid"));
					listResp.setToken(jsonObject.getString("token"));
					JSONArray jArray = JSONArray.fromObject(jsonObject.get("client"));
					List<Clientt> list = new ArrayList<Clientt>();
					if (jArray != null && jArray.size() > 0) {
						for (int i = 0; i < jArray.size(); i++) {
							JSONObject jsonObjSec = JSONObject.fromObject(jArray.get(i));
							Clientt client = new Clientt();
							client.setMobile(jsonObjSec.getString("mobile"));
							client.setClient_pwd(jsonObjSec.getString("client_pwd"));
							client.setClient_number(jsonObjSec.getString("client_number"));
							list.add(client);
						}
					}
					listResp.setClients(list);
				} else if (retCode == 2) {
					logger.warn("访问ip[" + auths[0] + "非法");
					listResp.setRespCode(Consts.C100005, Consts.login, "", "");
					return listResp;
				} else if (retCode == 3) {
					listResp.setRespCode(Consts.C101118, Consts.login, "", "");
					return listResp;
				} else if (retCode == 4) {
					logger.warn("主账号[" + acct + "]未激活");
					listResp.setRespCode(Consts.C101109, Consts.login, "", "");
					return listResp;
				} else if (retCode == 5) {
					logger.warn("主账号[" + acct + "]已关闭");
					listResp.setRespCode(Consts.C101108, Consts.login, "", "");
					return listResp;
				} else if (retCode == 6) {
					listResp.setRespCode(Consts.C100013, Consts.login, "", "");
					return listResp;
				}
			}
		} catch (Exception e) {
			logger.error(e);
			listResp.setRespCode(Consts.C100699, Consts.login, "", "");
		}
		long time2 = new Date().getTime();
		logger.info("登录请求时长" + (time2 - time1));
		return listResp;
	}


	@Override
	public ListResp userState(String version, String accountSid, String userInfoKey) {
		ListResp listResp = new ListResp();
		listResp.setRespCode(Consts.C000000, Consts.userState, null, null);
		if(userInfoKey == null || "".equals(userInfoKey)){
			listResp.setRespCode(Consts.C100108, Consts.userState, null, null);
		}
		String[] infoKey = userInfoKey.split(":");
		if(infoKey.length != 2){
			listResp.setRespCode(Consts.C100109, Consts.userState, null, null);
		}
		String usrinfo = Consts.usrinfo;
		String id = infoKey[0];
		String equipment = infoKey[1];
		if(Consts.pc.equals(equipment) || Consts.webRTC.equals(equipment)){
			Map<String, String> object = RedisService.getInstance().hgetall(userInfoKey);
			if(object == null){
				listResp.setState(Consts.offline);
			}else{
				String state = object.get("state");
				listResp.setState(state);
			}
		}else if(Consts.Android.equals(equipment) || Consts.IOS.equals(equipment)){
			Map<String, String> Android = RedisService.getInstance().hgetall(usrinfo + ":" + id + ":" + Consts.Android);
			String state = Consts.offline;
			if(Android != null){
				state = Android.get("state");
				listResp.setState(state);
			}
			if(!Consts.online.equals(state)){
				Map<String, String> IOS = RedisService.getInstance().hgetall(usrinfo + ":" + id + ":" + Consts.IOS);
				if(IOS == null){
					listResp.setState(Consts.offline);
				}else{
					state = IOS.get("state");
					listResp.setState(state);
				}
			}
		}else{
			listResp.setRespCode(Consts.C100109, Consts.userState, null, null);
		}
		return listResp;
	}
	public static String base64Encode(String str) throws Exception{
		byte[] binaryData = str.getBytes();
        String code = Base64.encodeBase64String(binaryData); //encryptBASE64(byteArray); 
        return code.replace("/", "-");//解决URL参数带/问题。
	}
	
	public String getRouteArea(String appId){
		String route_area;
		List<Object[]> paramList = new ArrayList<Object[]>();
		paramList.add(new Object[]{"string", appId});
		List<Map<String, Object>> list = DBCommService.getInstance().queryForList(SqlCode.QUERY_APP_BY_ID, paramList,Consts.CON_MASTER);
		if(list == null || list.isEmpty()){
			return Consts.default_area;
		}
		Map app = list.get(0);
		route_area = (String) app.get("route_area");
		if(route_area == null || "".equals(route_area)){
			route_area = Consts.default_area;
		}
		return route_area;
	}
}
