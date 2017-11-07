package com.yzx.rest.service.impl;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
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
import com.yzx.rest.models.AppBill;
import com.yzx.rest.models.Client;
import com.yzx.rest.models.Clientt;
import com.yzx.rest.models.SDK;
import com.yzx.rest.models.SessionInfo;
import com.yzx.rest.resp.ListResp;
import com.yzx.rest.resp.ListRespt;
import com.yzx.rest.service.AccountService;
import com.yzx.rest.service.BaseService;
import com.yzx.rest.service.DBCommService;
import com.yzx.rest.service.RedisService;
import com.yzx.rest.util.Consts;
import com.yzx.rest.util.DateConvert;
import com.yzx.rest.util.DateUtil;
import com.yzx.rest.util.DesUtil;
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
	public ListResp AccountInfo(String version, String accountSid, String Authorization, String sig) {
		ListResp listResp = new ListResp();
		long time1 = new Date().getTime();
		try {
			JSONObject jObject = checkAuthForAccount(messgeContext, logger, Authorization, sig, accountSid, version, "");
			String code = jObject.getString("code");
			if (code.equals(Consts.C000000)) {
				int retCode = jObject.getInt("resultcode");
				if (retCode == 0) {// 0：成功，其他失败
					listResp.setRespCode(jObject.getString("code"), Consts.AccountInfo, "", "");
					JSONArray jsonArr = jObject.getJSONArray("result");
					JSONObject jsonObjSec = JSONObject.fromObject(jsonArr.get(0));
					Account account = new Account();
					account.setAccountSid(jsonObjSec.getString("sid"));
					account.setEmail(jsonObjSec.getString("email"));
					account.setMobile(jsonObjSec.getString("mobile"));
					account.setNickName(jsonObjSec.getString("realname"));
					account.setCreateDate(jsonObjSec.getString("create_date"));
					account.setUpdateDate(jsonObjSec.getString("update_date"));
					account.setType(jsonObjSec.getString("user_type"));
					account.setStatus(jsonObjSec.getString("status"));
					account.setBalance(jsonObjSec.getString("balance"));
					List<Account> list = new LinkedList<Account>();
					list.add(account);
					listResp.setAccount(list);
				} else if (retCode == -100) {
					listResp.setRespCode(Consts.C100699, Consts.AccountInfo, "", "");
				} else {
					listResp.setRespCode(jObject.getString("code"), Consts.AccountInfo, "", "");
				}
			} else {
				listResp.setRespCode(jObject.getString("code"), Consts.AccountInfo, "", "");
			}
		} catch (Exception e) {
			logger.error(e);
			listResp.setRespCode(Consts.C100699, Consts.AccountInfo, "", "");
		}
		long time2 = new Date().getTime();
		logger.info("主账号查询请求时长" + (time2 - time1));
		return listResp;
	}

	@Override
	public ListResp createClient(String version, String accountSid, String Authorization, String sig, Client client) {
		ListResp listResp = new ListResp();
		String appId = client.getAppId();
		long time1 = new Date().getTime();
		try {
			String subAccountName = client.getFriendlyName();
			String clientType = client.getClientType();
			String charge = client.getCharge();
			String mobile = getMobileNew(client.getMobile());
			String isFee= client.getIsFee();
			if (StringUtils.isEmpty(appId)) {
				listResp.setRespCode(Consts.C102100, Consts.createClient, appId, "");
				return listResp;
			}
			if (!checkStr(appId)) {
				listResp.setRespCode(Consts.C102101, Consts.createClient, appId, "");
				return listResp;
			}
			if (!StringUtils.isEmpty(mobile)) {
				mobile = mobile.trim();
				if (!checkMobilephoneNew(mobile)) {
					listResp.setRespCode(Consts.C100006, Consts.createClient, appId, "");
					return listResp;
				}
			}
			if (StringUtils.isNotEmpty(subAccountName)) {
				if (!checkStr(subAccountName)) {
					listResp.setRespCode(Consts.C103105, Consts.createClient, appId, "");
					return listResp;
				}
				if (subAccountName.length() > 50) {
					listResp.setRespCode(Consts.C103102, Consts.createClient, appId, "");
					return listResp;
				}
			}
			if (StringUtils.isNotEmpty(clientType)) {
				if (!clientType.equals("0") && !clientType.equals("1")) {
					listResp.setRespCode(Consts.C103111, Consts.createClient, appId, "");
					return listResp;
				}
			} else {
				listResp.setRespCode(Consts.C103110, Consts.createClient, appId, "");
				return listResp;
			}
			//2016年1月12日11:36:25 新增终端client计费 判断传值类型
			if (StringUtils.isNotEmpty(isFee)) {
				if (!isFee.equals("0") && !isFee.equals("1")) {
					listResp.setRespCode(Consts.C100004, Consts.createClient, appId, "");
					return listResp;
				}
			} else {
				//赋予默认值
				client.setIsFee("0");
			}
			
			if (clientType.equals("0")) {
				client.setCharge("0");
			} else {
				if (StringUtils.isNotEmpty(charge)) {
					if (!checkNbr(charge)) {
						listResp.setRespCode(Consts.C100000, Consts.createClient, appId, "");
						return listResp;
					}
					if (charge.length() > Consts.MAX_NUMBER) {
						listResp.setRespCode(Consts.C100014, Consts.createClient, appId, "");
						return listResp;
					}
				} else {
					listResp.setRespCode(Consts.C103112, Consts.createClient, appId, "");
					return listResp;
				}
			}
			String code = checkAuth(messgeContext, logger, Authorization, sig, accountSid, version, appId);
			if (code.equals(Consts.C000000)) {
				String clienttype = "1";
				//由于历史原因，字段名称标示错误，导致接口文档中定义的clientType为计费模式(平台计费/开发者计费)，因此clientType实际标示chargeType，cType标示clientType
				//扣费模式数据库定义为1：主号计费、2 主子都计费，因此需要把客户传递的值进行转化为数据库的值
				if (clientType.equals("1")) {
					clienttype = "2";
				}
				client.setAccountSid(accountSid);
				client.setAppId(appId);
				client.setClientType(clienttype);
				client.setMobile(mobile);
				String result = this.serviceManager.createClient(client);

				JSONObject jsonObject = JSONObject.fromObject(result);
				if (jsonObject == null) {
					logger.warn("HTTP状态码不等于200");
					listResp.setRespCode(Consts.C100500, Consts.createClient, appId, "");
					return listResp;
				} else {
					int retCode = jsonObject.getInt("resultcode");
					if (retCode == 0) {// 0：成功，其他失败
						JSONArray jsonArr = jsonObject.getJSONArray("result");
						JSONObject object = JSONObject.fromObject(jsonArr.get(0));
						Client subAccount2 = new Client();
						subAccount2.setCreateDate(object.getString("createDate"));
						subAccount2.setClientNumber(object.getString("clientNumber"));
						subAccount2.setClientPwd(object.getString("clientPwd"));
						subAccount2.setBalance(object.getString("balance"));
						List<Client> list = new LinkedList<Client>();
						list.add(subAccount2);
						listResp.setRespCode(Consts.C000000, Consts.createClient, appId, "");
						listResp.setClients(list);
					} else if (retCode == 1) {
						listResp.setRespCode(Consts.C102102, Consts.createClient, appId, "");
					} else if (retCode == 2) {
						listResp.setRespCode(Consts.C100001, Consts.createClient, appId, "");
					} else if (retCode == 3) {
						listResp.setRespCode(Consts.C100500, Consts.createClient, appId, "");
					} else if (retCode == 4) {
						listResp.setRespCode(Consts.C103104, Consts.createClient, appId, "");
					} else if (retCode == 5) {
						listResp.setRespCode(Consts.C102103, Consts.createClient, appId, "");
					} else if (retCode == 6) {
						listResp.setRespCode(Consts.C103114, Consts.createClient, appId, "");
					} else if (retCode == 7) {
						listResp.setRespCode(Consts.C100009, Consts.createClient, appId, "");
					} else if (retCode == 8) {
						listResp.setRespCode(Consts.C102104, Consts.createClient, appId, "");
					} else if (retCode == 9) {
						listResp.setRespCode(Consts.C103123, Consts.createClient, appId, "");
					} else if (retCode == -16) {
						listResp.setRespCode(Consts.C100016, Consts.createClient, appId, "");
					} else if (retCode == -18) {
						listResp.setRespCode(Consts.C103126, Consts.createClient, appId, "");
					} else if (retCode == -19) {
						listResp.setRespCode(Consts.C103127, Consts.createClient, appId, "");
					} else {
						listResp.setRespCode(Consts.C100699, Consts.createClient, appId, "");
					}
				}
			} else {
				listResp.setRespCode(code, Consts.createClient, appId, "");
			}
		} catch (Exception e) {
			logger.error(e);
			listResp.setRespCode(Consts.C100699, Consts.createClient, appId, "");
		}
		long time2 = new Date().getTime();
		logger.info("创建子账号请求时长" + (time2 - time1));
		return listResp;
	}
	
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
	

	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
		String maxPrice = sessionInfo.getMaxPrice();
		String protocol = sessionInfo.getProtocol();
		String srcIp = sessionInfo.getSrcIp();
		String srcPort = sessionInfo.getSrcPort();
		String dstIp = sessionInfo.getDstIp();
		String dstPort = sessionInfo.getDstPort();
		String switchFlag = sessionInfo.getSwitchFlag();
		String oneSide = sessionInfo.getOneSide();
		SessionInfo serverStatus = sessionInfo.getServerStatus();
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
		if(StringUtils.isNotEmpty(policy)){
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
		if(StringUtils.isNotEmpty(hopNum) && !checkNonnegNum(hopNum)){
			listResp.setRespCode(Consts.C106006, Consts.applyRoute, appId, "");
			return listResp;
		}
		if(StringUtils.isNotEmpty(routeNum) && (!checkPosNum(routeNum) || Integer.valueOf(routeNum) > 2)){
			listResp.setRespCode(Consts.C106007, Consts.applyRoute, appId, "");
			return listResp;
		}
		if(StringUtils.isNotEmpty(maxPrice) && !checkNonnegNum(maxPrice)){
			listResp.setRespCode(Consts.C106017, Consts.applyRoute, appId, "");
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
		if(StringUtils.isNotEmpty(switchFlag) && !"1".equals(switchFlag) && !"0".equals(switchFlag)){
			listResp.setRespCode(Consts.C106016, Consts.applyRoute, appId, "");
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
				String notifyUrl = sessionInfo.getNotifyUrl();
				if(StringUtils.isNotEmpty(notifyUrl)){
					notifyUrl = JsonUtils.json2URLCode(sessionInfo.getNotifyUrl()); 
				}
				Map<String, Object> app = getAppMap(appId);
				if(StringUtils.isEmpty(policy)){
					Integer route_policy = (Integer) app.get("route_policy");
					if(route_policy == null){
						policy = "1";
					}else{
						policy = String.valueOf(route_policy);
					}
				}
				if(StringUtils.isEmpty(hopNum)){
					Integer max_hop_num = (Integer) app.get("max_hop_num");
					if(max_hop_num == null){
						hopNum = "0";
					}else{
						hopNum = String.valueOf(max_hop_num);
					}
				}
				if(StringUtils.isEmpty(routeNum)){
					Integer route_num = (Integer) app.get("route_num");
					if(route_num == null){
						routeNum = "1";
					}else{
						routeNum = String.valueOf(route_num);
					}
				}
				if(StringUtils.isEmpty(maxPrice)){
					Integer node_max_price = (Integer) app.get("node_max_price");
					if(node_max_price == null){
						maxPrice = "0";
					}else{
						maxPrice = String.valueOf(node_max_price);
					}
				}
				StringBuffer para= new StringBuffer();
				para.append("?sid=").append(accountSid).append("&appid=").append(appId).append("&policy=").append(policy)
				.append("&hopnum=").append(hopNum).append("&routenum=").append(routeNum).append("&maxprice=").append(maxPrice).append("&protocol=")
				.append(protocol).append("&srcinfo=").append(srcinfo).append("&dstinfo=").append(dstinfo)
				.append("&udpportnum=").append(udpPortNum).append("&vflag=").append(vflag).append("&area=").append(routeArea);
				if(StringUtils.isNotEmpty(notifyUrl)){
					para.append("&notifyurl=").append(notifyUrl);
				}
				if(StringUtils.isNotEmpty(switchFlag)){
					para.append("&switch=").append(switchFlag);
				}
				if(StringUtils.isNotEmpty(oneSide)){
					para.append("&oneSide=").append(oneSide);
				}
				if(serverStatus != null){
					String serverStatusJson = JsonUtils.toJson(serverStatus);
					para.append("&serverStatus=").append(JsonUtils.json2URLCode(serverStatusJson));
				}
				
				String sessionCreateUrl = SysConfig.getInstance().getProperty("session_create");
				logger.info("创建会话路径参数:"+sessionCreateUrl+para);
				long apptime1 = new Date().getTime();
				response = req.get(sessionCreateUrl+para);
				//获取响应实体信息
				entity = response.getEntity();
				if (entity != null) {
					String result = EntityUtils.toString(entity, "UTF-8");
					logger.info(sessionCreateUrl + "获取resultInfo:"+result);
					Map map = JsonUtils.toObject(result, Map.class);
					String respCode = (String) map.get("result");
					if(respCode !=null && !Consts.C000000.equals(respCode)){
						listResp.setRespCode(Consts.C106010, Consts.applyRoute, appId, "");
						return listResp;
					}
					List RouteList = new ArrayList();
					if(map.get("sessionid") != null){
						String sessionId = (String) map.get("sessionid");
						listResp.setSessionId(sessionId);
					}
					if(map.get("direct") != null){
						String direct = String.valueOf(map.get("direct")).split("[.]")[0];
						if(!"-1".equals(direct)){
							listResp.setDirect(direct);
						}
					}
					if(map.get("routes") != null){
						List<Map> tempList = (List) map.get("routes");
						listResp.setRouteSize(tempList.size());
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
					}
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
			Integer delay = srcAp.getDelay();
			if(checkIP(ip)){
				temp.addProperty("ip", ip);
			}else{
				return null;
			}
			if(delay == null){
				temp.addProperty("delay", 0);
			}else{
				temp.addProperty("delay", delay);
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
	public ListResp closeClient(String version, String accountSid, String auth, String sig, Client client) {
		ListResp listResp = new ListResp();
		String appId = client.getAppId();
		String clientId = client.getClientNumber();
		long time1 = new Date().getTime();
		try {
			// String clientId=client.getClientId();//改成delete方法后注释
			if (StringUtils.isEmpty(appId)) {
				listResp.setRespCode(Consts.C102100, Consts.closeClient, appId, clientId);
				return listResp;
			}
			if (!checkStr(appId)) {
				listResp.setRespCode(Consts.C102101, Consts.closeClient, appId, clientId);
				return listResp;
			}

			if (StringUtils.isEmpty(clientId)) {
				listResp.setRespCode(Consts.C103103, Consts.closeClient, appId, clientId);
				return listResp;
			}
			if (clientId.length() != 14) {
				listResp.setRespCode(Consts.C103106, Consts.closeClient, appId, clientId);
				return listResp;
			}
			String code = checkAuthForClient(messgeContext, logger, auth, sig, accountSid, version, appId,clientId);
			if (code.equals(Consts.C000000)) {
				String result = serviceManager.closeClient(clientId, accountSid, appId);
				JSONObject jsonObject = JSONObject.fromObject(result);
				if (jsonObject == null) {
					logger.warn("HTTP状态码不等于200");
					listResp.setRespCode(Consts.C100500, Consts.closeClient, appId, clientId);
				} else {
					int retCode = jsonObject.getInt("resultcode");
					if (retCode == 0) {// 0：成功，其他失败
						listResp.setRespCode(Consts.C000000, Consts.closeClient, appId, clientId);
					} else if (retCode == 4) {
						listResp.setRespCode(Consts.C103108, Consts.closeClient, appId, clientId);
					} else if (retCode == 5) {
						listResp.setRespCode(Consts.C103107, Consts.closeClient, appId, clientId);
					} else if (retCode == 6) {
						listResp.setRespCode(Consts.C103122, Consts.closeClient, appId, clientId);
					} else if (retCode == -100) {
						listResp.setRespCode(Consts.C100699, Consts.closeClient, appId, clientId);
					} else {
						listResp.setRespCode(Consts.C103107, Consts.closeClient, appId, clientId);
					}
				}
			} else {
				listResp.setRespCode(code, Consts.closeClient, appId, clientId);
			}
		} catch (Exception e) {
			logger.error(e);
			listResp.setRespCode(Consts.C100699, Consts.closeClient, appId, clientId);
		}
		long time2 = new Date().getTime();
		logger.info("关闭请求时长" + (time2 - time1));
		return listResp;
	}
	
	@Override
	public ListResp findClients(String version, String accountSid, String auth, String sig, Client client) {
		ListResp listResp = new ListResp();
		String appId = client.getAppId();
		long time1 = new Date().getTime();
		try {
			String start = client.getStart();
			String limit = client.getLimit();
			if (StringUtils.isEmpty(start)) {
				start = "0";
			}
			if (StringUtils.isEmpty(limit)) {
				limit = "10";
			}
			if (Integer.parseInt(limit) > 100) {
				listResp.setRespCode(Consts.C103115, Consts.findClients, appId, "");
				return listResp;
			}
			if (!checkNum(start) || !checkNum(limit)) {
				listResp.setRespCode(Consts.C100002, Consts.findClients, appId, "");
				return listResp;
			}
			if (StringUtils.isEmpty(appId)) {
				listResp.setRespCode(Consts.C102100, Consts.findClients, appId, "");
				return listResp;
			}
			if (!checkStr(appId)) {
				listResp.setRespCode(Consts.C102101, Consts.findClients, appId, "");
				return listResp;
			}
			String code = checkAuth(messgeContext, logger, auth, sig, accountSid, version, appId);
			if (code.equals(Consts.C000000)) {
				String result = serviceManager.clientList(appId, Integer.parseInt(start), Integer.parseInt(limit));
				JSONObject jsonObject = JSONObject.fromObject(result);
				if (jsonObject == null) {
					logger.warn("HTTP状态码不等于200");
					listResp.setRespCode(Consts.C100500, Consts.findClients, appId, "");
				} else {
					int retCode = jsonObject.getInt("resultcode");
					if (retCode == 0) {// 0：成功，其他失败
						listResp.setRespCode(Consts.C000000, Consts.findClients, appId, "");
						JSONArray jsonArr = jsonObject.getJSONArray("result");
						if (jsonArr != null && jsonArr.size() > 0) {
							listResp.setCount(jsonArr.size());
							List<Client> list = new ArrayList<Client>();
							for (int i = 0; i < jsonArr.size(); i++) {
								JSONObject jsonObjSec = JSONObject.fromObject(jsonArr.get(i));
								Client client2 = new Client();
								client2.setFriendlyName(jsonObjSec.getString("friendly_name"));
								client2.setClientType(jsonObjSec.getString("client_type"));
								client2.setMobile(jsonObjSec.getString("mobile"));
								String bl = jsonObjSec.getString("balance");
								if (StringUtils.isNotEmpty(bl)) {
									client2.setBalance(bl);
								} else {
									client2.setBalance("0");
								}
								client2.setCreateDate(jsonObjSec.getString("create_date"));
								client2.setClientNumber(jsonObjSec.getString("client_number"));
								client2.setClientPwd(jsonObjSec.getString("client_pwd"));
								list.add(client2);
							}
							listResp.setRespCode(Consts.C000000, Consts.findClients, appId, "");
							listResp.setClients(list);
						}
					} else if (retCode == -100) {
						listResp.setRespCode(Consts.C100699, Consts.findClients, appId, "");
					} else {
						listResp.setRespCode(Consts.C100007, Consts.findClients, appId, "");
					}
				}
			} else {
				listResp.setRespCode(code, Consts.findClients, appId, "");
			}
		} catch (Exception e) {
			logger.error(e);
			listResp.setRespCode(Consts.C100699, Consts.findClients, appId, "");
		}
		long time2 = new Date().getTime();
		logger.info("查找子账号列表请求时长" + (time2 - time1));
		return listResp;
	}
	
	@Override
	public ListResp findClientByName(String version, String accountSid, String auth, String sig, String clientNumber,
			String appId) {// , Client client
		ListResp listResp = new ListResp();
		long time1 = new Date().getTime();
		try {
			if (StringUtils.isEmpty(appId)) {
				listResp.setRespCode(Consts.C102100, Consts.findClientByName, appId, clientNumber);
				return listResp;
			}
			if (!checkStr(appId)) {
				listResp.setRespCode(Consts.C102101, Consts.findClientByName, appId, clientNumber);
				return listResp;
			}
			if (StringUtils.isEmpty(clientNumber)) {
				listResp.setRespCode(Consts.C103103, Consts.findClientByName, appId, clientNumber);
				return listResp;
			}
			if (clientNumber.length() != 14) {
				listResp.setRespCode(Consts.C103106, Consts.findClientByName, appId, clientNumber);
				return listResp;
			}
			if (!checkNbr(clientNumber)) {
				listResp.setRespCode(Consts.C100002, Consts.findClientByName, appId, clientNumber);
				return listResp;
			}
			String code = checkAuthForClient(messgeContext, logger, auth, sig, accountSid, version, appId,clientNumber);
			if (code.equals(Consts.C000000)) {
				String result = serviceManager.queryClientByClientNumber(clientNumber, accountSid, appId);
				JSONObject jsonObject = JSONObject.fromObject(result);
				if (jsonObject == null) {
					logger.warn("HTTP状态码不等于200");
					listResp.setRespCode(Consts.C100500, Consts.findClientByName, appId, clientNumber);
				} else {
					int retCode = jsonObject.getInt("resultcode");
					if (retCode == 0) {// 0：成功，其他失败
						listResp.setRespCode(Consts.C000000, Consts.findClientByName, appId, clientNumber);
						JSONArray jsonArr = jsonObject.getJSONArray("result");
						if (jsonArr != null && jsonArr.size() > 0) {
							listResp.setCount(jsonArr.size());
							List<Client> list = new ArrayList<Client>();
							for (int i = 0; i < jsonArr.size(); i++) {
								JSONObject jsonObjSec = JSONObject.fromObject(jsonArr.get(i));
								Client client2 = new Client();
								client2.setFriendlyName(jsonObjSec.getString("friendly_name"));
								client2.setClientType(jsonObjSec.getString("client_type"));
								client2.setMobile(jsonObjSec.getString("mobile"));
								String bl = jsonObjSec.getString("balance");
								if (StringUtils.isNotEmpty(bl)) {
									client2.setBalance(bl);
								} else {
									client2.setBalance("0");
								}
								client2.setCreateDate(jsonObjSec.getString("create_date"));
								client2.setClientNumber(jsonObjSec.getString("client_number"));
								client2.setClientPwd(jsonObjSec.getString("client_pwd"));
								try {
									if (jsonObjSec.get("is_call_fr") != null) {
										client2.setRoam(jsonObjSec.getString("is_call_fr"));
									}
								} catch (Exception e) {
									logger.error(e);
								}
								list.add(client2);
							}
							listResp.setRespCode(Consts.C000000, Consts.findClientByName, appId, clientNumber);
							listResp.setClients(list);
						}
					} else if (retCode == -100) {
						listResp.setRespCode(Consts.C100699, Consts.findClientByName, appId, clientNumber);
					} else {
						listResp.setRespCode(Consts.C100007, Consts.findClientByName, appId, clientNumber);
					}
				}
			} else {
				listResp.setRespCode(code, Consts.findClientByName, appId, clientNumber);
			}
		} catch (Exception e) {
			logger.error(e);
			listResp.setRespCode(Consts.C100699, Consts.findClientByName, appId, clientNumber);
		}
		long time2 = new Date().getTime();
		logger.info("clientNumber查询子账号请求时长" + (time2 - time1));
		return listResp;
	}
	
	@Override
	public ListResp findClientByMobile(String version, String accountSid, String auth, String sig, String mobile,
			String appId) {// , Client client
		ListResp listResp = new ListResp();
		long time1 = new Date().getTime();
		try {
			mobile = getMobileNew(mobile);
			if (StringUtils.isEmpty(appId)) {
				listResp.setRespCode(Consts.C102100, Consts.findClientByMobile, appId, mobile);
				return listResp;
			}
			if (!checkStr(appId)) {
				listResp.setRespCode(Consts.C102101, Consts.findClientByMobile, appId, mobile);
				return listResp;
			}
			if (StringUtils.isEmpty(mobile)) {
				listResp.setRespCode(Consts.C100008, Consts.findClientByMobile, appId, mobile);
				return listResp;
			} else {
				mobile = mobile.trim();
				if (!checkMobilephoneNew(mobile)) {
					listResp.setRespCode(Consts.C100006, Consts.findClientByMobile, appId, mobile);
					return listResp;
				}
			}
			String code = checkAuth(messgeContext, logger, auth, sig, accountSid, version, appId);
			if (code.equals(Consts.C000000)) {
				String result = serviceManager.queryClientByMobile(appId, mobile);
				JSONObject jsonObject = JSONObject.fromObject(result);
				if (jsonObject == null) {
					logger.warn("HTTP状态码不等于200");
					listResp.setRespCode(Consts.C100500, Consts.findClientByMobile, appId, mobile);
				} else {
					int retCode = jsonObject.getInt("resultcode");
					if (retCode == 0) {// 0：成功，其他失败
						listResp.setRespCode(Consts.C000000, Consts.findClientByMobile, appId, mobile);
						JSONArray jsonArr = jsonObject.getJSONArray("result");
						if (jsonArr != null && jsonArr.size() > 0) {
							listResp.setCount(jsonArr.size());
							List<Client> list = new ArrayList<Client>();
							for (int i = 0; i < jsonArr.size(); i++) {
								JSONObject jsonObjSec = JSONObject.fromObject(jsonArr.get(i));
								Client client2 = new Client();
								client2.setFriendlyName(jsonObjSec.getString("friendly_name"));
								client2.setClientType(jsonObjSec.getString("client_type"));
								client2.setMobile(jsonObjSec.getString("mobile"));
								String bl = jsonObjSec.getString("balance");
								if (StringUtils.isNotEmpty(bl)) {
									client2.setBalance(bl);
								} else {
									client2.setBalance("0");
								}
								client2.setCreateDate(jsonObjSec.getString("create_date"));
								client2.setClientNumber(jsonObjSec.getString("client_number"));
								client2.setClientPwd(jsonObjSec.getString("client_pwd"));
								try {
									if (jsonObjSec.get("is_call_fr") != null) {
										client2.setRoam(jsonObjSec.getString("is_call_fr"));
									}
								} catch (Exception e) {
									logger.error(e);
								}
								list.add(client2);
							}
							listResp.setRespCode(Consts.C000000, Consts.findClientByMobile, appId, mobile);
							listResp.setClients(list);
						}
					} else if (retCode == -100) {
						listResp.setRespCode(Consts.C100699, Consts.findClientByMobile, appId, mobile);
					} else {
						listResp.setRespCode(Consts.C100007, Consts.findClientByMobile, appId, mobile);
					}
				}
			} else {
				listResp.setRespCode(code, Consts.findClientByMobile, appId, mobile);
			}
		} catch (Exception e) {
			logger.error(e);
			listResp.setRespCode(Consts.C100699, Consts.findClientByMobile, appId, mobile);
		}
		long time2 = new Date().getTime();
		logger.info("手机号查询子账号请求时长" + (time2 - time1));
		return listResp;
	}
	
	@Override
	public ListResp chargeClient(String version, String accountSid, String auth, String sig, Client client) {
		ListResp listResp = new ListResp();
		String clientNumber = client.getClientNumber();
		String appId = client.getAppId();
		long time1 = new Date().getTime();
		try {
			String chargeType = client.getChargeType();
			String money = client.getCharge();
			if (StringUtils.isEmpty(appId)) {
				listResp.setRespCode(Consts.C102100, Consts.chargeClient, appId, clientNumber);
				return listResp;
			}
			if (!checkStr(appId)) {
				listResp.setRespCode(Consts.C102101, Consts.chargeClient, appId, clientNumber);
				return listResp;
			}
			if (StringUtils.isEmpty(chargeType) || StringUtils.isEmpty(money) || StringUtils.isEmpty(clientNumber)) {
				listResp.setRespCode(Consts.C100003, Consts.chargeClient, appId, clientNumber);
				return listResp;
			}
			if (!chargeType.equals("0") && !chargeType.equals("1")) {
				listResp.setRespCode(Consts.C100004, Consts.chargeClient, appId, clientNumber);
				return listResp;
			}
			if (!checkNbr(money)) {
				listResp.setRespCode(Consts.C100002, Consts.chargeClient, appId, clientNumber);
				return listResp;
			}
			if (money.length() > Consts.MAX_NUMBER) {
				listResp.setRespCode(Consts.C100014, Consts.chargeClient, appId, clientNumber);
				return listResp;
			}
			if (StringUtils.isEmpty(clientNumber)) {
				listResp.setRespCode(Consts.C103103, Consts.chargeClient, appId, clientNumber);
				return listResp;
			}
			if (clientNumber.length() != 14) {
				listResp.setRespCode(Consts.C103106, Consts.chargeClient, appId, clientNumber);
				return listResp;
			}
			if (!checkNbr(clientNumber)) {
				listResp.setRespCode(Consts.C100002, Consts.chargeClient, appId, clientNumber);
				return listResp;
			}
			String code = checkAuthForClient(messgeContext, logger, auth, sig, accountSid, version, appId,clientNumber);
			if (code.equals(Consts.C000000)) {
				String result = serviceManager.chargeClient(accountSid, chargeType, money, clientNumber, appId);
				JSONObject jsonObject = JSONObject.fromObject(result);
				int retCode = jsonObject.getInt("resultcode");
				if (retCode == 0) {// 0：成功，其他失败
					listResp.setRespCode(Consts.C000000, Consts.chargeClient, appId, clientNumber);
				} else if (retCode == 4) {
					listResp.setRespCode(Consts.C103108, Consts.chargeClient, appId, clientNumber);
				} else if (retCode == 5) {
					listResp.setRespCode(Consts.C103107, Consts.chargeClient, appId, clientNumber);
				} else if (retCode == 6) {
					listResp.setRespCode(Consts.C103122, Consts.chargeClient, appId, clientNumber);
				} else {
					listResp.setRespCode(Consts.C103109, Consts.chargeClient, appId, clientNumber);
				}
			} else {
				listResp.setRespCode(code, Consts.chargeClient, appId, clientNumber);
			}
		} catch (Exception e) {
			logger.error(e);
			listResp.setRespCode(Consts.C100699, Consts.chargeClient, appId, clientNumber);
		}
		long time2 = new Date().getTime();
		logger.info("子账号充值请求时长" + (time2 - time1));
		return listResp;
	}

	@Override
	public ListResp DelBand(String version, String accountSid, String auth, String sig, Client client) {
		ListResp listResp = new ListResp();
		String clientNumber = client.getClientNumber();
		String appId = client.getAppId();
		long time1 = new Date().getTime();
		try {
			if (StringUtils.isEmpty(appId)) {
				listResp.setRespCode(Consts.C102100, Consts.delBand, appId, clientNumber);
				return listResp;
			}
			if (!checkStr(appId)) {
				listResp.setRespCode(Consts.C102101, Consts.delBand, appId, clientNumber);
				return listResp;
			}
			if (StringUtils.isEmpty(clientNumber)) {
				listResp.setRespCode(Consts.C103103, Consts.delBand, appId, clientNumber);
				return listResp;
			}
			if (clientNumber.length() != 14) {
				listResp.setRespCode(Consts.C103106, Consts.delBand, appId, clientNumber);
				return listResp;
			}
			if (!checkNbr(clientNumber)) {
				listResp.setRespCode(Consts.C100002, Consts.delBand, appId, clientNumber);
				return listResp;
			}
			String code = checkAuthForClient(messgeContext, logger, auth, sig, accountSid, version, appId,clientNumber);
			if (code.equals(Consts.C000000)) {
				String result = serviceManager.delBand(clientNumber, appId, accountSid);
				JSONObject jsonObject = JSONObject.fromObject(result);
				if (jsonObject == null) {
					logger.warn("HTTP状态码不等于200");
					listResp.setRespCode(Consts.C100500, Consts.delBand, appId, clientNumber);
				} else {
					int retCode = jsonObject.getInt("resultcode");
					if (retCode == 0) {// 0：成功，其他失败
						serviceManager.updateClientVerify(clientNumber, "0");
						logger.info("解绑后,更新号码为非验证状态");
						listResp.setRespCode(Consts.C000000, Consts.delBand, appId, clientNumber);
					} else if (retCode == 1) {
						listResp.setRespCode(Consts.C103131, Consts.delBand, appId, clientNumber);
					} else if (retCode == 2) {
						listResp.setRespCode(Consts.C100009, Consts.delBand, appId, clientNumber);
					} else if (retCode == 3) {
						listResp.setRespCode(Consts.C103114, Consts.delBand, appId, clientNumber);
					} else if (retCode == 4) {
						listResp.setRespCode(Consts.C103131, Consts.delBand, appId, clientNumber);
					}else if (retCode == 6) {
						listResp.setRespCode(Consts.C103107, Consts.delBand, appId, clientNumber);
					} else if (retCode == -100) {
						listResp.setRespCode(Consts.C100699, Consts.delBand, appId, clientNumber);
					}
				}
			} else {
				listResp.setRespCode(code, Consts.delBand, appId, clientNumber);
			}
		} catch (Exception e) {
			logger.error(e);
			listResp.setRespCode(Consts.C100699, Consts.delBand, appId, clientNumber);
		}
		long time2 = new Date().getTime();
		logger.info("绑定子账号请求时长" + (time2 - time1));
		return listResp;
	}
	
	@Override
	public ListResp bandClients(String version, String accountSid, String auth, String sig, Client client) {
		ListResp listResp = new ListResp();
		String clientNumber = client.getClientNumber();
		String appId = client.getAppId();
		long time1 = new Date().getTime();
		try {
			String mobile = getMobileNew(client.getMobile());
			String cType = client.getcType();
			String clientType = client.getClientType();
			if (StringUtils.isEmpty(appId)) {
				listResp.setRespCode(Consts.C102100, Consts.bandClients, appId, clientNumber);
				return listResp;
			}
			if (!checkStr(appId)) {
				listResp.setRespCode(Consts.C102101, Consts.bandClients, appId, clientNumber);
				return listResp;
			}
			if (StringUtils.isEmpty(clientNumber)) {
				listResp.setRespCode(Consts.C103103, Consts.bandClients, appId, clientNumber);
				return listResp;
			}
			if (clientNumber.length() != 14) {
				listResp.setRespCode(Consts.C103106, Consts.bandClients, appId, clientNumber);
				return listResp;
			}
			if (!checkNbr(clientNumber)) {
				listResp.setRespCode(Consts.C100002, Consts.bandClients, appId, clientNumber);
				return listResp;
			}
			if (StringUtils.isEmpty(mobile)) {
				listResp.setRespCode(Consts.C100008, Consts.bandClients, appId, clientNumber);
				return listResp;
			} else {
				mobile = mobile.trim();
				if (!checkMobilephoneNew(mobile)) {
					listResp.setRespCode(Consts.C100006, Consts.bandClients, appId, clientNumber);
					return listResp;
				}
			}
			String code = checkAuthForClient(messgeContext, logger, auth, sig, accountSid, version, appId,clientNumber);
			if (code.equals(Consts.C000000)) {
				if (StringUtils.isEmpty(cType)) {
					cType = "0";
				}
				if (StringUtils.isEmpty(clientType)) {
					clientType = "0";
				}
				String result = serviceManager.bandClient(clientNumber, mobile, appId, accountSid, clientType, cType);
				JSONObject jsonObject = JSONObject.fromObject(result);
				if (jsonObject == null) {
					logger.warn("HTTP状态码不等于200");
					listResp.setRespCode(Consts.C100500, Consts.bandClients, appId, clientNumber);
				} else {
					int retCode = jsonObject.getInt("resultcode");
					if (retCode == 0) {// 0：成功，其他失败
						serviceManager.updateClientVerify(clientNumber, "0");
						logger.info("换绑后,更新号码为非验证状态");
						listResp.setRespCode(Consts.C000000, Consts.bandClients, appId, clientNumber);
					} else if (retCode == 1) {
						listResp.setRespCode(Consts.C103116, Consts.bandClients, appId, clientNumber);
					} else if (retCode == 2) {
						listResp.setRespCode(Consts.C100009, Consts.bandClients, appId, clientNumber);
					} else if (retCode == 3) {
						listResp.setRespCode(Consts.C103114, Consts.bandClients, appId, clientNumber);
					} else if (retCode == 4) {
						listResp.setRespCode(Consts.C103122, Consts.bandClients, appId, clientNumber);
					} else if (retCode == 5) {
						listResp.setRespCode(Consts.C103108, Consts.bandClients, appId, clientNumber);
					} else if (retCode == 6) {
						listResp.setRespCode(Consts.C103107, Consts.bandClients, appId, clientNumber);
					}else if (retCode == 7) {
						//新增 未上线应用只能绑定白名单里面号码 2015年10月28日10:28:35
						listResp.setRespCode(Consts.C103126, Consts.bandClients, appId, clientNumber);
					} else if (retCode == -100) {
						listResp.setRespCode(Consts.C100699, Consts.bandClients, appId, clientNumber);
					}
				}
			} else {
				listResp.setRespCode(code, Consts.bandClients, appId, clientNumber);
			}
		} catch (Exception e) {
			logger.error(e);
			listResp.setRespCode(Consts.C100699, Consts.bandClients, appId, clientNumber);
		}
		long time2 = new Date().getTime();
		logger.info("绑定子账号请求时长" + (time2 - time1));
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
	public ListResp auditPwd(String version, String accountSid, String auth, String sig, Client client) {
		ListResp listResp = new ListResp();
		long time1 = new Date().getTime();
		String appId = client.getAppId();
		String clientNumber = client.getClientNumber();
		try {
			String password = client.getPassword();
			if (StringUtils.isEmpty(appId)) {
				listResp.setRespCode(Consts.C102100, Consts.auditPwd, appId, clientNumber);
				return listResp;
			}
			if (!checkStr(appId)) {
				listResp.setRespCode(Consts.C102101, Consts.auditPwd, appId, clientNumber);
				return listResp;
			}
			if (StringUtils.isEmpty(clientNumber)) {
				listResp.setRespCode(Consts.C103103, Consts.auditPwd, appId, clientNumber);
				return listResp;
			}
			if (clientNumber.length() != 14) {
				listResp.setRespCode(Consts.C103106, Consts.auditPwd, appId, clientNumber);
				return listResp;
			}
			if (!checkNbr(clientNumber)) {
				listResp.setRespCode(Consts.C100002, Consts.auditPwd, appId, clientNumber);
				return listResp;
			}
			if (StringUtils.isEmpty(password)) {
				listResp.setRespCode(Consts.C100012, Consts.auditPwd, appId, clientNumber);
				return listResp;
			} else {
				if (password.length() > 8) {
					listResp.setRespCode(Consts.C100106, Consts.auditPwd, appId, clientNumber);
					return listResp;
				}
				if (!checkStr(password)) {
					listResp.setRespCode(Consts.C100106, Consts.auditPwd, appId, clientNumber);
					return listResp;
				}
			}
			String code = checkAuthForClient(messgeContext, logger, auth, sig, accountSid, version, appId,clientNumber);
			if (code.equals(Consts.C000000)) {
				String result = serviceManager.auditPwd(appId, clientNumber, password);
				JSONObject jsonObject = JSONObject.fromObject(result);
				if (jsonObject == null) {
					logger.warn("HTTP状态码不等于200");
					listResp.setRespCode(Consts.C100500, Consts.auditPwd, appId, clientNumber);
				} else {
					int retCode = jsonObject.getInt("resultcode");
					if (retCode == 0) {// 0：成功，其他失败
						listResp.setRespCode(Consts.C000000, Consts.auditPwd, appId, clientNumber);
					} else if (retCode == 1) {
						listResp.setRespCode(Consts.C100107, Consts.auditPwd, appId, clientNumber);
					}
				}
			} else {
				listResp.setRespCode(code, Consts.auditPwd, appId, clientNumber);
			}
		} catch (Exception e) {
			logger.error(e);
			listResp.setRespCode(Consts.C100699, Consts.auditPwd, appId, clientNumber);
		}
		long time2 = new Date().getTime();
		logger.info("修改密码消耗时长" + (time2 - time1));
		return listResp;

	}
	
	@Override
	public ListResp findClientByFriendName(String version, String accountSid, String auth, String sig,
			String friendlyName, String appId) {
		ListResp listResp = new ListResp();
		long time1 = new Date().getTime();
		try {
			if (StringUtils.isEmpty(appId)) {
				listResp.setRespCode(Consts.C102100, Consts.findClientByFriendName, appId, "");
				return listResp;
			}
			if (!checkStr(appId)) {
				listResp.setRespCode(Consts.C102101, Consts.findClientByFriendName, appId, "");
				return listResp;
			}
			if (StringUtils.isEmpty(friendlyName)) {
				listResp.setRespCode(Consts.C103120, Consts.findClientByFriendName, appId, "");
				return listResp;
			}
			String code = checkAuth(messgeContext, logger, auth, sig, accountSid, version, appId);
			if (code.equals(Consts.C000000)) {
				String result = serviceManager.queryByFriendlyName(appId, friendlyName);
				JSONObject jsonObject = JSONObject.fromObject(result);
				if (jsonObject == null) {
					logger.warn("HTTP状态码不等于200");
					listResp.setRespCode(Consts.C100500, Consts.findClientByFriendName, appId, "");
				} else {
					int retCode = jsonObject.getInt("resultcode");
					if (retCode == 0) {// 0：成功，其他失败
						listResp.setRespCode(Consts.C000000, Consts.findClientByFriendName, appId, "");
						JSONArray jsonArr = jsonObject.getJSONArray("result");
						if (jsonArr != null && jsonArr.size() > 0) {
							listResp.setCount(jsonArr.size());
							List<Client> list = new ArrayList<Client>();
							for (int i = 0; i < jsonArr.size(); i++) {
								JSONObject jsonObjSec = JSONObject.fromObject(jsonArr.get(i));
								Client client2 = new Client();
								client2.setFriendlyName(jsonObjSec.getString("friendly_name"));
								client2.setClientType(jsonObjSec.getString("client_type"));
								client2.setMobile(jsonObjSec.getString("mobile"));
								String bl = jsonObjSec.getString("balance");
								if (StringUtils.isNotEmpty(bl)) {
									client2.setBalance(bl);
								} else {
									client2.setBalance("0");
								}
								client2.setCreateDate(jsonObjSec.getString("create_date"));
								client2.setClientNumber(jsonObjSec.getString("client_number"));
								client2.setClientPwd(jsonObjSec.getString("client_pwd"));
								list.add(client2);
							}
							listResp.setRespCode(Consts.C000000, Consts.findClientByFriendName, appId, "");
							listResp.setClients(list);
						}
					} else {
						listResp.setRespCode(Consts.C100007, Consts.findClientByFriendName, appId, "");
					}
				}
			} else {
				listResp.setRespCode(code, Consts.findClientByFriendName, appId, "");
			}
		} catch (Exception e) {
			logger.error(e);
			listResp.setRespCode(Consts.C100699, Consts.findClientByFriendName, appId, "");
		}
		long time2 = new Date().getTime();
		logger.info("通过昵称查询子账号请求时长" + (time2 - time1));
		return listResp;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public ListResp billList(String version, String accountSid, String auth, String sig, AppBill appBill) {
		ListResp listResp = new ListResp();
		String appId = appBill.getAppId();
		long time1 = new Date().getTime();
		try {
			String date = appBill.getDate();
			if (StringUtils.isEmpty(date) || StringUtils.isEmpty(appId)) {
				listResp.setRespCode(Consts.C100003, Consts.billList, appId, "");
				return listResp;
			}
			if (!checkStr(appId)) {
				listResp.setRespCode(Consts.C102101, Consts.billList, appId, "");
				return listResp;
			}
			if (!date.equals("day") && !date.equals("week") && !date.equals("month") && !date.startsWith("20")) {
				listResp.setRespCode(Consts.C100004, Consts.billList, appId, "");
				return listResp;
			}
			String code = checkAuth(messgeContext, logger, auth, sig, accountSid, version, appId);
			if (code.equals(Consts.C000000)) {
				String nowday = DateConvert.convertD2String(new Date(), "yyyyMMdd");
				// String tableDay=nowday;
				if (date.equals("day")) {
					nowday = DateConvert.dateAddDay(nowday, -1);
				} else if (date.equals("week")) {
					// tableDay=DateConvert.getPrivFirstDateOfWeek(nowday,0);
					nowday = DateConvert.getPrivFirstDateOfWeek(nowday, -1);
				} else if (date.equals("month")) {
					// tableDay=DateConvert.getFirstDateOfMonth(nowday);//DateConvert.getMonth(nowday,0);
					nowday = DateConvert.getMonth(nowday, -1);
				} else {
					if (date.length() != 8 && !checkNum(date)) {
						listResp.setRespCode(Consts.C100004, Consts.billList, appId, "");
						return listResp;
					}
					nowday = date;
					date = "day";
				}
				String result = serviceManager.billList(appId, date, nowday);
				JSONObject jsonObject = JSONObject.fromObject(result);
				int retCode = jsonObject.getInt("resultcode");
				if (retCode == 0) {// 0：成功，其他失败
					listResp.setRespCode(Consts.C000000, Consts.billList, appId, "");
					List<AppBill> list = new ArrayList<AppBill>();
					AppBill acctBill = new AppBill();
					String url = SysConfig.getInstance().getProperty("check_file_server");
					JSONArray jArray = jsonObject.getJSONArray("result");
					JSONObject jObject = jArray.getJSONObject(0);
					String realPath = jObject.getString("remote_path");
					String token = jObject.getString("token");
					realPath = realPath + ":" + token;
					String dPath = "";
					try {
						dPath = DesUtil.encrypt(realPath, Consts.des_key);
					} catch (Exception e) {
						logger.error("billList des加密出错...");
					}
					url += "?fileName=" + URLEncoder.encode(dPath);
					acctBill.setDownUrl(url);
					acctBill.setToken(token);
					list.add(acctBill);
					listResp.setAccountBills(list);
				} else if (retCode == -100) {
					listResp.setRespCode(Consts.C100699, Consts.billList, appId, "");
				} else {
					listResp.setRespCode(Consts.C100007, Consts.billList, appId, "");
				}
			} else {
				listResp.setRespCode(code, Consts.billList, appId, "");
			}
		} catch (Exception e) {
			logger.error(e);
			listResp.setRespCode(Consts.C100699, Consts.billList, appId, "");
		}
		long time2 = new Date().getTime();
		logger.info("应用话单请求时长" + (time2 - time1));
		return listResp;
	}
	
	@Override
	public ListResp findClientByParam(String version, String accountSid, String auth, String sig, String param,
			String appId) {
		ListResp listResp = new ListResp();
		long time1 = new Date().getTime();
		try {
			if (StringUtils.isEmpty(appId)) {
				listResp.setRespCode(Consts.C102100, Consts.findClientByParam, appId, "");
				return listResp;
			}
			if (!checkStr(appId)) {
				listResp.setRespCode(Consts.C102101, Consts.findClientByParam, appId, "");
				return listResp;
			}
			if (StringUtils.isEmpty(param)) {
				listResp.setRespCode(Consts.C103121, Consts.findClientByParam, appId, "");
				return listResp;
			}
			String method = "";
			if (param.startsWith("6") || param.startsWith("7")) {
				if (param.length() == 14 && checkNbr(param)) {// clientnumber
					method = "1";
				}
			}
			if (checkMobilephone(param)) {// mobile
				method = "2";
			}
			if (method.equals("")) {// friendlyName
				method = "3";
			}
			String code = checkAuth(messgeContext, logger, auth, sig, accountSid, version, appId);
			if (code.equals(Consts.C000000)) {
				String result = "";
				if (method.equals("1")) {
					result = serviceManager.queryClientByClientNumber(param, accountSid, appId);
				} else if (method.equals("2")) {
					result = serviceManager.queryClientByMobile(appId, param);
				} else if (method.equals("3")) {
					result = serviceManager.queryByFriendlyName(appId, param);
				}
				JSONObject jsonObject = JSONObject.fromObject(result);
				if (jsonObject == null) {
					logger.warn("HTTP状态码不等于200");
					listResp.setRespCode(Consts.C100500, Consts.findClientByParam, appId, "");
				} else {
					int retCode = jsonObject.getInt("resultcode");
					if (retCode == 0) {// 0：成功，其他失败
						listResp.setRespCode(Consts.C000000, Consts.findClientByParam, appId, "");
						JSONArray jsonArr = jsonObject.getJSONArray("result");
						if (jsonArr != null && jsonArr.size() > 0) {
							listResp.setCount(jsonArr.size());
							List<Client> list = new ArrayList<Client>();
							for (int i = 0; i < jsonArr.size(); i++) {
								JSONObject jsonObjSec = JSONObject.fromObject(jsonArr.get(i));
								Client client2 = new Client();
								client2.setFriendlyName(jsonObjSec.getString("friendly_name"));
								client2.setClientType(jsonObjSec.getString("client_type"));
								client2.setMobile(jsonObjSec.getString("mobile"));
								String bl = jsonObjSec.getString("balance");
								if (StringUtils.isNotEmpty(bl)) {
									client2.setBalance(bl);
								} else {
									client2.setBalance("0");
								}
								client2.setCreateDate(jsonObjSec.getString("create_date"));
								client2.setClientNumber(jsonObjSec.getString("client_number"));
								client2.setClientPwd(jsonObjSec.getString("client_pwd"));
								list.add(client2);
							}
							listResp.setRespCode(Consts.C000000, Consts.findClientByParam, appId, "");
							listResp.setClients(list);
						}
					} else {
						listResp.setRespCode(Consts.C100007, Consts.findClientByParam, appId, "");
					}
				}
			} else {
				listResp.setRespCode(code, Consts.findClientByParam, appId, "");
			}
		} catch (Exception e) {
			logger.error(e);
			listResp.setRespCode(Consts.C100699, Consts.findClientByParam, appId, "");
		}
		long time2 = new Date().getTime();
		logger.info("不定参数查询子账号请求时长" + (time2 - time1));
		return listResp;
	}
	
	@Override
	public String monitor() {
		//返回值定义
		// 0 正常
		// 1 查询msyql错误
		// 2 查询redis错误
		String status = serviceManager.monitor();
		return status;
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

	@Override
	public ListResp applySDKID(String version, String accountSid, String auth, String sig, SDK sdk) {
		Client client = new Client();
		client.setAppId(sdk.getAppId());
		client.setClientType("0");//0 开发者计费。默认为0. 注意：1 云平台计费(已作废)
		version = changeVersion(version);
		ListResp listResp = createClient(version, accountSid, auth, sig, client);
		if(Consts.C000000.equals(listResp.getRespCode())){
			Client result = listResp.getClients().get(0);
			sdk.setCreateDate(result.getCreateDate());
			sdk.setSDKID(result.getClientNumber());
			listResp.setClients(null);
			List<SDK> sdks = new ArrayList<SDK>();
			sdks.add(sdk);
			listResp.setSdks(sdks);
		}
		return listResp;
	}

	@Override
	public ListResp dropSDKID(String version, String accountSid, String auth, String sig, SDK sdk) {
		Client client = new Client();
		client.setAppId(sdk.getAppId());
		client.setClientNumber(sdk.getSDKID());
		version = changeVersion(version);
		return closeClient(version, accountSid, auth, sig, client);
	}
	
	@Override
	public ListResp findSDKIDs(String version, String accountSid, String auth, String sig, SDK sdk) {
		ListResp listResp = new ListResp();
		Client client = new Client();
		client.setAppId(sdk.getAppId());
		client.setStart(sdk.getStart());
		client.setLimit(sdk.getLimit()
				);
		String appId = client.getAppId();
		long time1 = new Date().getTime();
		try {
			String start = client.getStart();
			String limit = client.getLimit();
			if (StringUtils.isEmpty(start)) {
				start = "0";
			}
			if (StringUtils.isEmpty(limit)) {
				limit = "10";
			}
			if (Integer.parseInt(limit) > 100) {
				listResp.setRespCode(Consts.C103115, Consts.findClients, appId, "");
				return listResp;
			}
			if (!checkNum(start) || !checkNum(limit)) {
				listResp.setRespCode(Consts.C100002, Consts.findClients, appId, "");
				return listResp;
			}
			if (StringUtils.isEmpty(appId)) {
				listResp.setRespCode(Consts.C102100, Consts.findClients, appId, "");
				return listResp;
			}
			if (!checkStr(appId)) {
				listResp.setRespCode(Consts.C102101, Consts.findClients, appId, "");
				return listResp;
			}
			version = changeVersion(version);
			String code = checkAuth(messgeContext, logger, auth, sig, accountSid, version, appId);
			if (code.equals(Consts.C000000)) {
				String result = serviceManager.clientList(appId, Integer.parseInt(start), Integer.parseInt(limit));
				JSONObject jsonObject = JSONObject.fromObject(result);
				if (jsonObject == null) {
					logger.warn("HTTP状态码不等于200");
					listResp.setRespCode(Consts.C100500, Consts.findClients, appId, "");
				} else {
					int retCode = jsonObject.getInt("resultcode");
					if (retCode == 0) {// 0：成功，其他失败
						listResp.setRespCode(Consts.C000000, Consts.findClients, appId, "");
						JSONArray jsonArr = jsonObject.getJSONArray("result");
						if (jsonArr != null && jsonArr.size() > 0) {
							listResp.setCount(jsonArr.size());
							List<SDK> list = new ArrayList<SDK>();
							for (int i = 0; i < jsonArr.size(); i++) {
								JSONObject jsonObjSec = JSONObject.fromObject(jsonArr.get(i));
								SDK SDKtemp = new SDK();
								SDKtemp.setSDKID(jsonObjSec.getString("client_number"));
								SDKtemp.setCreateDate(jsonObjSec.getString("create_date"));
								list.add(SDKtemp);
							}
							listResp.setRespCode(Consts.C000000, Consts.findClients, appId, "");
							listResp.setSdks(list);
						}
					} else if (retCode == -100) {
						listResp.setRespCode(Consts.C100699, Consts.findClients, appId, "");
					} else {
						listResp.setRespCode(Consts.C100007, Consts.findClients, appId, "");
					}
				}
			} else {
				listResp.setRespCode(code, Consts.findClients, appId, "");
			}
		} catch (Exception e) {
			logger.error(e);
			listResp.setRespCode(Consts.C100699, Consts.findClients, appId, "");
		}
		long time2 = new Date().getTime();
		logger.info("查找子账号列表请求时长" + (time2 - time1));
		return listResp;
	}

	public String changeVersion(String version){
		String cversion = SysConfig.getInstance().getProperty("new_version");
		if(version.equals(cversion)){//新版本号换旧版本号以调用旧版本号方法
			version = SysConfig.getInstance().getProperty("version");
		}else{
			version = cversion;//使用新版本号调用旧版本号号方法，使其报版本错误的提示
		}
		return version;
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
		Map<String, Object> app = list.get(0);
		route_area = (String) app.get("route_area");
		if(route_area == null || "".equals(route_area)){
			route_area = Consts.default_area;
		}
		return route_area;
	}
	
	public Map<String, Object> getAppMap(String appId){
		List<Object[]> paramList = new ArrayList<Object[]>();
		paramList.add(new Object[]{"string", appId});
		List<Map<String, Object>> list = DBCommService.getInstance().queryForList(SqlCode.QUERY_APP_BY_ID, paramList,Consts.CON_MASTER);
		return list.get(0);
	}
}
