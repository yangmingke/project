package com.yzx.rest.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.ucpaas.commonservice.facade.ClientAsyncFacade;
import com.ucpaas.commonservice.facade.ClientDoubleRegFacade;
import com.ucpaas.commonservice.model.ClientBalanceInfo;
import com.ucpaas.commonservice.model.ClientInfo;
import com.ucpaas.commonservice.model.TestClientInfo;
import com.yzx.rest.bean.MemInfo;
import com.yzx.rest.models.Client;
import com.yzx.rest.models.PayMessage;
import com.yzx.rest.mq.MqProducer;
import com.yzx.rest.util.Consts;
import com.yzx.rest.util.DateConvert;
import com.yzx.rest.util.DateUtil;
import com.yzx.rest.util.Encrypt;
import com.yzx.rest.util.HMACSHAUtil;
import com.yzx.rest.util.HttpHelper;
import com.yzx.rest.util.HttpUtils;
import com.yzx.rest.util.IpUtils;
import com.yzx.rest.util.JsonUtils;
import com.yzx.rest.util.MemActionTools;
import com.yzx.rest.util.SqlCode;
import com.yzx.rest.util.SysConfig;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ServiceManager {
	private static final Logger logger = LoggerFactory.getLogger(ServiceManager.class);
	private static final Logger clientChargeFailLog = LoggerFactory.getLogger("clientChargeFail");
	@Autowired
	private ClientAsyncFacade clientAsyncFacade;
	@Autowired
	private MqProducer mqProducer;
	@Autowired
	private ClientDoubleRegFacade clientDoubleRegFacade;
	/**
	 * 查询主账户信息
	 * 
	 * @param request
	 * @param response
	 */
	public String accountInfo(String accountSid, String appId, String ip, String host) {
		try {
			if (!StringUtils.isEmpty(ip)) {//
				try {
					if (StringUtils.isNotEmpty(appId)) {
						String key = Consts.getKey(Consts.KEY_WHITE_LIST, appId);
						List<Object[]> paramList = new ArrayList<Object[]>();
						paramList.add(new Object[]{"string", appId});
						String json = getRedis(SqlCode.QUERY_WHITE_LIST_BY_APPID, key, paramList,Consts.CON_MASTER);
						if (json != null) {
							JSONArray jArray = JSONArray.fromObject(json);
							String allowip = jArray.getJSONObject(0).getString("white_address");
							boolean pass = false;
							String[] allowips = allowip.split(";");
							for (int i = 0; i < allowips.length; i++) {
								if (ip.equals(allowips[i]) || host.equals(allowips[i])) {
									pass = true;
									break;
								}
							}
							if (!pass) {
								logger.warn("不合法访问ip=" + ip);
								return "{resultcode:2,result:[]}";
							}
						}
					}
				} catch (Exception e) {
					logger.error("失败");
					return "{resultcode:2,result:[]}";
				}
			}
			List<Object[]> paramList = new ArrayList<Object[]>();
			paramList.add(new Object[]{"string", accountSid});
			String key = Consts.getKey(Consts.KEY_ACCOUNT, accountSid);
			Map<String, String> map = getHashMapRedis(SqlCode.QUERY_ACCOUNT_BY_ID, key, paramList,Consts.CON_MASTER);
			if (map != null) {
				String accountStutas = map.get("status");
				if("0".equals(accountStutas)){
					return "{resultcode:-104,result:[]}";
				}else if("2".equals(accountStutas)){
					return "{resultcode:-105,result:[]}";
				} else if("5".equals(accountStutas)){
					return "{resultcode:-106,result:[]}";
				}else if(!"1".equals(accountStutas)){
					return "{resultcode:-103,result:[]}";
				}
				// 检测用户应用
				if (StringUtils.isNotEmpty(appId) && !appId.equals("0")) {
					List<Object[]> appPList = new ArrayList<Object[]>();
					appPList.add(new Object[]{"string", appId});
					String appkey = Consts.getKey(Consts.KEY_APP, appId);
					String appJson = getRedis(SqlCode.QUERY_APP_BY_ID, appkey, appPList,Consts.CON_MASTER);
					if (appJson != null) {
						JSONArray jArray = JSONArray.fromObject(appJson);
						JSONObject jObject = jArray.getJSONObject(0);
						String status = jObject.getString("status");
						String appstype = jObject.getString("app_type");
//						if (appstype.equals("1") && !status.equals("0") && !status.equals("1") && !status.equals("6")) {
						/*if (appstype.equals("1") && !status.equals("1") ) {
							return "{resultcode:-101,result:[]}";
						}*/
						if (status.equals("0") ) {
							return "{resultcode:-94,result:[]}";
						}else if (status.equals("2") ) {
							return "{resultcode:-95,result:[]}";
						}else if (status.equals("3") ) {
							return "{resultcode:-96,result:[]}";
						}else if (status.equals("4") ) {
							return "{resultcode:-97,result:[]}";
						}else if (status.equals("5") ) {
							return "{resultcode:-98,result:[]}";
						}else if (status.equals("6") ) {
							return "{resultcode:-99,result:[]}";
						}
					} else {
						return "{resultcode:-102,result:[]}";
					}
					String sql = "select count(1) from tb_ucpaas_application where sid=? and app_sid=?";
					List<Object[]> appList = new ArrayList<Object[]>();
					appList.add(new Object[]{"string", accountSid});
					appList.add(new Object[]{"string", appId});
					int count = DBCommService.getInstance().queryForInt(sql, appList,Consts.CON_MASTER);
					if (count == 0) {
						return "{resultcode:-100}";
					}
				}
				JSONArray jArray = JSONArray.fromObject(map);
				JSONObject jObject = jArray.getJSONObject(0);
				List<Object[]> paramListx = new ArrayList<Object[]>();
				paramListx.add(new Object[]{"string", accountSid});
				long balance = DBCommService.getInstance().queryForLong(SqlCode.QUERY_BALANCE_BY_SID, paramListx,Consts.CON_MASTER);
				double t = balance * 1.0 / (10000 * 100);
				jObject.put("balance", t);
				return "{resultcode:0,result:" + jArray.toString() + "}";
			} else {
				return "{resultcode:1,result:'无数据'}";
			}
		} catch (Exception e) {
			logger.error("查询主账号", e);
		}
		return "{resultcode:-100}";
	}

	/**
	 * 创建子账号
	 * 
	 * @param request
	 * @param response
	 */
	public String createClient(Client client) {
		try {
			if (null != client) {
				String appId = client.getAppId();
				String accountSid = client.getAccountSid();
				String charge = client.getCharge();
				String clientType = client.getClientType();
				String clientName = client.getFriendlyName() == null ? "" : client.getFriendlyName();
				String mobile = client.getMobile();
				String cType = client.getcType();
				List<Object[]> paramList = new ArrayList<Object[]>();
				paramList.add(new Object[]{"string", appId});
				String key = Consts.getKey(Consts.KEY_APP, appId);
				String appJson = getRedis(SqlCode.QUERY_APP_BY_ID, key, paramList,Consts.CON_MASTER);
				boolean is = true;
				boolean unonline = false;
				boolean isDemo = false;
				if (appJson != null) {
					JSONArray jArray = JSONArray.fromObject(appJson);
					JSONObject jObject = jArray.getJSONObject(0);
					String status = jObject.getString("status");
					String appstype = jObject.getString("app_type");
					// 用户创建的应用,如果为其他状态则返回错误
					if (appstype.equals("1") && !status.equals("0") && !status.equals("1") && !status.equals("6")) {
						return "{resultcode:5,result:[]}";
					}
					if (appstype.equals("1") && status.equals("0")) {
						unonline = true;
					}
					if (appstype.equals("0")) {
						isDemo = true;
					}
					if (appstype.equals("0") && StringUtils.isEmpty(cType)) {
						return "{resultcode:8,result:[]}";
					}
				} else {
					is = false;
				}
				if (StringUtils.isEmpty(cType)) {
					cType = "1";
				}
				if (isDemo && cType.equals("1")) {
					return "{resultcode:-19,result:[]}";
				}
				if (unonline) {// 应用未上线，校验创建子账号是否超过100个，校验号码是否白名单里面的号码
					int count = DBCommService.getInstance().queryForInt(SqlCode.QUERY_CLIENT_COUNT2, paramList,Consts.CON_MASTER);
					if (count >= Consts.MAX_CLIENT) {
						return "{resultcode:9,result:[]}";
					}
					if (StringUtils.isNotEmpty(mobile)) {
						String swlkey = Consts.getKey(Consts.KEY_SID_WHITE_LIST, accountSid);
						List<Object[]> whiteParamList = new ArrayList<Object[]>();
						whiteParamList.add(new Object[]{"string", accountSid});
						String whiteList = getRedis(SqlCode.QUERY_WHITE_LIST, swlkey, whiteParamList,Consts.CON_MASTER);
						boolean isc = false;
						//未上线的应用，只能使用白名单里面的手机号码（tb_ucpaas_test_whitelist）
						if (whiteList != null) {
							JSONArray jArray = JSONArray.fromObject(whiteList);
							for (int i = 0; i < jArray.size(); i++) {
								JSONObject jObject = jArray.getJSONObject(i);
								String whiteMobile = "";
								if (jObject.get("mobile") != null) {
									whiteMobile = jObject.getString("mobile");
								}
								if (whiteMobile.equals(mobile)) {
									isc = true;
								}
							}
						}
						if (!isc) {
							return "{resultcode:-18,result:[]}";
						}
					}
				}
				long fee = 0;
				long licenseFee =0 ;
				long dspFee= 0;
				//是否需要业务扣费（tb_user_dsp）
//				boolean isDsp =DBCommService.getInstance().isDsp(accountSid);
				boolean isDsp = false;
				if (is) {
					String nbrStart = "";
					if (cType.equals("0")) {// ctype==0表示测试子账号
						nbrStart = Consts.TEST_VOIP_START;
					} else {
						nbrStart = Consts.VOIP_START;
						//新增设备license扣费判断
						
						if (isDsp || client.getIsFee().equals("1")) {// 子账号是否需要扣费
							long balance = 0;
							List<Object[]> paramList0 = new ArrayList<Object[]>();
							paramList0.add(new Object[]{"string", accountSid});
							paramList0.add(new Object[]{"string", accountSid});
							//查询client license计费费用
							if(isDsp){
								paramList0.set(1,new Object[]{"string", Consts.CLIENT_FEE + "%"});
								dspFee= DBCommService.getInstance().queryForLong(SqlCode.QUERY_FEE_BY_ID, paramList0,Consts.CON_MASTER);
							}
							//查询终端license 计费费用
							if(client.getIsFee().equals("1")){
								paramList0.set(1,new Object[]{"string", Consts.CLIENT_LICENSE + "%"});
								licenseFee= DBCommService.getInstance().queryForLong(SqlCode.QUERY_FEE_BY_ID, paramList0,Consts.CON_MASTER);
							}
							fee = dspFee +licenseFee;
							
							List<Object[]> paramListx = new ArrayList<Object[]>();
							paramListx.add(new Object[]{"string", accountSid});
//							List<Map<String, Object>> balanceList = DBCommService.getInstance().queryForList(
//									SqlCode.QUERY_BALANCE_BY_SID2, paramListx,Consts.CON_MASTER);
							//ubs获取余额
							//获取ubs配置
							String ubsServer = SysConfig.getInstance().getProperty("ubs_server");
							String ubsPort = SysConfig.getInstance().getProperty("ubs_port");
							//请求ubs应用充值接口
							String url =new StringBuffer()
									.append("http://").append(ubsServer).append(":").append(ubsPort)
									.append("/ubs/balance/queryAccount/").append(accountSid).append("/-1").toString();
							String result = HttpUtils.get(url);
							JsonObject obj = JsonUtils.toJsonObj(result);
							String bslt = obj.get("result").getAsString();
							//钱包不可用
							if (bslt==null || !bslt.equalsIgnoreCase("true")) {
								return "{resultcode:-16,result:[]}";
							} 
							String bsbalance = obj.get("balance").getAsString();
							balance = Long.parseLong(bsbalance);
							if (balance < fee) {
								return "{resultcode:2,result:[]}";
							}
						}
					}
					List<Object[]> paramListAcct = new ArrayList<Object[]>();
					paramListAcct.add(new Object[]{"string", accountSid});
					String acctkey = Consts.getKey(Consts.KEY_ACCOUNT, accountSid);
					Map<String, String> acctMap = getHashMapRedis(SqlCode.QUERY_ACCOUNT_BY_ID, acctkey, paramListAcct,Consts.CON_MASTER);
					if (acctMap != null) {
						if (acctMap.get("random_nbr") != null) {
							nbrStart = nbrStart + acctMap.get("random_nbr");
						} else {
							nbrStart = nbrStart + "0000";
						}
					} else {
						nbrStart = nbrStart + "0000";
					}
					long charges = 0l;
					if (clientType.equals("2")) {
						double x = Double.parseDouble(charge);
						x = x * 100 * 10000;
						charges = new Double(x).longValue();
					}
					String uuid = UUID.randomUUID().toString();
					String pwd = UUID.randomUUID().toString().substring(0, 8);
					long keyStr = new Date().getTime();
					String subToken = "";
					try {
						subToken = HMACSHAUtil.getSignature(keyStr + "", uuid);
					} catch (Exception e) {
						logger.error("生成密码出错", e);
						return "{resultcode:-100}";
					}
					String clientId = UUID.randomUUID().toString();
					try {
						clientId = HMACSHAUtil.getSignature(clientId + System.currentTimeMillis(), "key");
					} catch (Exception e) {
						logger.error("生成clientId出錯", e);
						return "{resultcode:-100}";
					}
					if (isSafePhone(mobile)) {
						logger.info("手机号为受保护号码" + mobile);
						return "{resultcode:7}";
					}
					
					Date date = new Date();
					//clientInfo信息组装
					ClientInfo clientInfo = new ClientInfo();
					clientInfo.setClientSid(clientId);
					clientInfo.setAppSid(appId);
					clientInfo.setFriendlyName(clientName);
					clientInfo.setClientPwd(pwd);
					clientInfo.setClientToken(subToken);
					clientInfo.setStatus("1");
					clientInfo.setSid(accountSid);
					clientInfo.setCharge(0L);//为0表示忽略填写client表中的balance
					clientInfo.setMobile(mobile);
					//由于历史原因，字段名称标示错误，导致接口文档中定义的clientType为计费模式(平台计费/开发者计费)，因此clientType实际标示chargeType，cType标示clientType
					//cType 用于标示是否是申请测试client，并且测试demo不允许创建，官网申请是传递的值为cType=0，不为0则表示为正式client
					clientInfo.setClientType(cType);
					clientInfo.setChargeType(clientType);  
					clientInfo.setClnPrefix(Integer.parseInt(nbrStart));
					//新增字段入库isFee 2016年1月12日14:16:11
					clientInfo.setIsFee(Integer.parseInt(client.getIsFee()));
					clientInfo.setCreateDate(date);
					clientInfo.setUpdateDate(date);
					
					
					//balanceInfo信息组装
					ClientBalanceInfo balanceInfo = new ClientBalanceInfo();
					balanceInfo.setBalance(charges);
					balanceInfo.setEnableFlag("1");
					balanceInfo.setSid(accountSid);
					balanceInfo.setCreateTime(date);
					balanceInfo.setUpdateDate(date);
					
					/**
					 * 入库：134&110
					 * 调用common-server 注册client
					 * 返回map示例： 
					 * 注册client成功：{po_code=0, po_client_number=22345050000031} 
					 * 应用下手机号已存在：{po_code=1, po_client_number=null} 
					 * 应用下friendly_name已存在：{po_code=2, po_client_number=null} 
					 * 系统内部错误：{po_code=200099, po_desc=系统内部错误}
					 */
					Map<String, Object> procMap = null ;
					try {
						procMap = clientDoubleRegFacade.clientReg(clientInfo, balanceInfo);
					}catch (Exception e) {
						logger.error("调用dubbo注册失败：sid={},clientId={},clientPwd={},clientToken={}", accountSid ,clientId,pwd,subToken);
						logger.error("异常信息：", e);
						return "{resultcode:-100,result:[]}";
					}
					//po_code为null 返回手机号码已经绑定
					String code = String.valueOf(procMap.get("po_code")==null?"1":procMap.get("po_code"));
					if (StringUtils.isEmpty(code) || code.equals("1")) {
						logger.info("手机号已绑定" + mobile);
						return "{resultcode:6,result:[]}";
					} else if (code.equals("2")) {
						logger.warn("应用appId=" + appId + "下，friendlyName已经存在");
						return "{resultcode:4,result:[]}";
					} else if(code.equals("200099")){
						logger.warn("调用commservice入库110数据库失败，系统内部错误");
						return "{resultcode:-100,result:[]}";
					}
					String clientNumber = procMap.get("po_client_number").toString();
					//需要扣费的clint 执行扣费逻辑
					if (isDsp || client.getIsFee().equals("1")) {
						PayMessage message = new PayMessage();
						String dt = DateConvert.convertD2String(new Date(), "yyyy-MM-dd HH:mm:ss");
						message.setBusinessBeginTime(dt);
						message.setEventId(Consts.CLIENT_FEE+"");
						message.setHostIp(IpUtils.getLocalIP());
						message.setPayId(System.currentTimeMillis()+UUID.randomUUID().toString().replaceAll("-", ""));
						message.setPayMoney(fee+"");
						message.setRequestPayTime(dt);
						message.setSid(accountSid);
						try {
							//发送扣费消息到mq
							String msg = JsonUtils.toJson(message);
							mqProducer.sendQueueMessage(msg);
							// 记录扣费流水
							SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMM");
							String now = sFormat.format(new Date());
							String sql = SqlCode.UPDATE_CLIENT_FEE;
							sql = sql.replace("[DATE]", now);
							List<Object[]> plx = null;
							int ulog =0;
							if(isDsp){
								plx =new ArrayList<Object[]>();
								plx.add(new Object[]{"string", accountSid});
								plx.add(new Object[]{"string", appId});
								plx.add(new Object[]{"number", dspFee});
								plx.add(new Object[]{"number", dspFee});
								plx.add(new Object[]{"number", Consts.CLIENT_FEE});
								plx.add(new Object[]{"string", clientNumber});
								plx.add(new Object[]{"number", 1});
							
								ulog =ulog+ DBLogService.getInstance().update(sql, plx);
							}
							
							if(client.getIsFee().equals("1")){
								plx =new ArrayList<Object[]>();
								plx.add(new Object[]{"string", accountSid});
								plx.add(new Object[]{"string", appId});
								plx.add(new Object[]{"number", licenseFee});
								plx.add(new Object[]{"number", licenseFee});
								plx.add(new Object[]{"number", Consts.CLIENT_LICENSE});
								plx.add(new Object[]{"string", clientNumber});
								plx.add(new Object[]{"number", 5});
							
							    ulog =ulog+ DBLogService.getInstance().update(sql, plx);
							}
							
							if (ulog > 0) {
								logger.info(clientNumber + "添加子账号扣费（业务和终端license）流水成功");
							} else {
								logger.info(clientNumber + "添加子账号扣费（业务和终端license）流水失败");
							}
						} catch (Exception e) {
							logger.error("client创建扣费失败，clientnum:"+clientNumber+",扣费信息："+JsonUtils.toJson(message));
							clientChargeFailLog.error(JsonUtils.toJson(message));
							
						}
					}
					if (cType.equals("0")) {// 测试应用默认开通国际漫游
						List<Object[]> paramList3 = new ArrayList<Object[]>();
						paramList3.add(new Object[]{"number", 1});
						paramList3.add(new Object[]{"string", clientNumber});
						int up = DBCommService.getInstance().update(SqlCode.ROAM_OPEN, paramList3);
						if (up > 0) {
							logger.info(clientNumber + "开通呼转成功...");
						} else {
							logger.info(clientNumber + "开通呼转失败...");
						}
					}
					client.setClientToken(subToken);
					client.setCreateDate(DateUtil.getTimeStr("yyyy-MM-dd HH:mm:ss"));
					client.setClientNumber(clientNumber);
					client.setClientPwd(pwd);
					client.setAccountSid(accountSid);
					client.setBalance(charge);
					List<Client> list = new ArrayList<Client>();
					list.add(client);
					String json = JSONArray.fromObject(list).toString();
					logger.warn("主账号accountSid" + accountSid + ",client号:" + clientNumber + "创建成功&code=" + code);
					return "{resultcode:0,result:" + json + "}";
					}
				} else {
					return "{resultcode:1,result:[]}";
				}
			}catch (Exception e) {
				logger.error("创建子账号", e);
			}
		return "{resultcode:-100}";
	}

	/**
	 * 关闭子账号
	 * 
	 * @param request
	 * @param response
	 */
	public String closeClient(String clientNumber, String sid, String appId) {
		try {
			String key = Consts.getKey(Consts.KEY_CLIENT, clientNumber);
			List<Object[]> paramList = new ArrayList<Object[]>();
			paramList.add(new Object[]{"string", clientNumber});
			paramList.add(new Object[]{"string", sid});
			Map<String, String> map = getHashMapRedis(SqlCode.QUERY_CLIENT_BY_NAME, key, paramList,Consts.CON_MASTER);
			// 2015年10月13日16:48:41 判断client是否属于应用
			if (map != null) {
				if (!appId.equals(map.get("app_sid"))) {
					return "{resultcode:6,result:[]}";
				}
				JSONArray jArray = JSONArray.fromObject(map);
				String status = jArray.getJSONObject(0).getString("status");
				if (status != null && status.equals("0")) {
					return "{resultcode:4,result:[]}";
				}
			} else {
				return "{resultcode:5,result:[]}";
			}
			List<Object[]> paramListu = new ArrayList<Object[]>();
			paramListu.add(new Object[]{"string", clientNumber});
			int u = DBCommService.getInstance().update(SqlCode.UPDATE_CLIENT_BALANCE, paramListu);
			if (u > 0) {
				logger.info("冻结子账号余额成功：" + clientNumber);
				
				List<Object[]> alist = new ArrayList<Object[]>();
				alist.add(new Object[]{"string", clientNumber});
				alist.add(new Object[]{"string", appId});
				int del = DBCommService.getInstance().update(SqlCode.DEL_MOBILE, alist);
				if (del > 0) {
					logger.info("删除号码池成功clientNumber=" + clientNumber);
				} else {
					logger.info("删除号码池失败clientNumber=" + clientNumber);
				}
			} else {
				logger.info("冻结子账号余额失败：" + clientNumber);
			}
			int update = DBCommService.getInstance().update(SqlCode.UPDATE_CLIENT_STATUS, paramListu);
			if (update > 0) {
				// 新增 update tb_ucpaas_client set
				// `status`='0',mobile='',update_date=now() where
				// client_number=?
				try {
					ClientInfo clientInfo = new ClientInfo();
					clientInfo.setStatus("0");
					clientInfo.setAppSid(appId);
					clientInfo.setUpdateDate(new Date());
					clientInfo.setClientNumber(clientNumber);
					clientInfo.setUserid(clientNumber);
					clientInfo.setMobile(map.get("mobile") == null ? "" : map.get("mobile"));
					// 新增 UPDATE tb_bill_client_balance set
					// enable_flag='0',update_date=now() where client_number=?
					ClientBalanceInfo balanceInfo = new ClientBalanceInfo();
					balanceInfo.setEnableFlag("0");
					balanceInfo.setUpdateDate(new Date());
					balanceInfo.setClientNumber(clientNumber);
					clientAsyncFacade.closeClientByClientNumber(clientInfo, balanceInfo);
					// -------------------------end------------------------------------------
					
					// 关闭测试client单独
					if (map.get("client_type").equals("0")) {
						// 新增 修改测试client的mobile
						TestClientInfo testClient = new TestClientInfo();
						testClient.setMobile("");
						testClient.setUpdateDate(new Date());
						testClient.setStatus("0");
						testClient.setClientNumber(clientNumber);
						clientAsyncFacade.updateByClientNumber(testClient);
					}

				} catch (Exception e) {
					logger.error("调用dubbo组件失败：" + e.getMessage());
				}

				// --------------------------end--------------------------------------------
				MemActionTools.getInstance().addQuene(getMemInfo(key, null, "delete", 0, ""));
				return "{resultcode:0,result:[]}";
			} else {
				return "{resultcode:1,result:[]}";
			}
		} catch (Exception e) {
			logger.error("关闭子账号", e);
		}
		return "{resultcode:-100}";
	}

	/**
	 * 分页查询子账号
	 * 
	 * @param request
	 * @param response
	 */
	public String clientList(String appId, int start, int limit) {
		try {
			List<Object[]> paramList = new ArrayList<Object[]>();
			paramList.add(new Object[]{"string", appId});
			paramList.add(new Object[]{"number", start});
			paramList.add(new Object[]{"number", limit});
			List<Map<String, Object>> list = DBCommService.getInstance().queryForList(SqlCode.QUERY_CLIENTS, paramList,Consts.CON_SLAVE);
			if (list != null && list.size() > 0) {
				String json = JSONArray.fromObject(list).toString();
				return "{resultcode:0,result:" + json + "}";
			} else {
				return "{resultcode:1,result:[]}";
			}
		} catch (Exception e) {
			logger.error("分页查询子账号", e);
		}
		return "{resultcode:-100}";
	}

	/**
	 * 通过手机号查询子账号
	 * 
	 * @param request
	 * @param response
	 */
	public String queryClientByMobile(String appId, String mobile) {
		try {
			List<Object[]> paramList = new ArrayList<Object[]>();
			paramList.add(new Object[]{"string", appId});
			paramList.add(new Object[]{"string", mobile});
			List<Map<String, Object>> list = DBCommService.getInstance().queryForList(SqlCode.QUERY_CLIENT_BY_MOBILE,
					paramList,Consts.CON_SLAVE);
			if (list != null && list.size() > 0) {
				Map<String, Object> map = list.get(0);
				String clientNumber = map.get("client_number") == null ? "" : map.get("client_number").toString();
				JSONArray jArray = JSONArray.fromObject(map);
				JSONObject jObject = jArray.getJSONObject(0);
				if (clientNumber.equals("")) {
					jObject.put("balance", 0);
				} else {
					List<Object[]> paramListx = new ArrayList<Object[]>();
					paramListx.add(new Object[]{"string", clientNumber});
					long balance = DBCommService.getInstance().queryForLong(SqlCode.QUERY_CLIENT_BALANCE_BY_NBR,paramListx,Consts.CON_SLAVE);
					double t = balance * 1.0 / (10000 * 100);
					jObject.put("balance", t);
				}
				return "{resultcode:0,result:" + jArray.toString() + "}";
			} else {
				return "{resultcode:1,result:[]}";
			}
		} catch (Exception e) {
			logger.error("通过手机号查询子账号", e);
		}
		return "{resultcode:-100}";
	}

	/**
	 * 通过clientNumber查询子账号
	 * 
	 * @param request
	 * @param response
	 */
	public String queryClientByClientNumber(String clientNumber, String sid, String appId) {
		try {
			String key = Consts.getKey(Consts.KEY_CLIENT, clientNumber);
			List<Object[]> paramList = new ArrayList<Object[]>();
			paramList.add(new Object[]{"string", clientNumber});
			paramList.add(new Object[]{"string", sid});
			Map<String, String> map = getHashMapRedis(SqlCode.QUERY_CLIENT_BY_NAME, key, paramList,Consts.CON_SLAVE);
			if (map != null) {

				// 如果当前的client对应的应用id和传入的应用id不一致，则表示该clientNumber不属于该应用，解决不同sid不同appid调用client查询接口，可以查到相同的clientid的问题。
				if (!appId.equals(map.get("app_sid"))) {
					return "{resultcode:1,result:[]}";
				}

				JSONArray jArray = JSONArray.fromObject(map);
				JSONObject jObject = jArray.getJSONObject(0);
				List<Object[]> paramListx = new ArrayList<Object[]>();
				paramListx.add(new Object[]{"string", clientNumber});
				long balance = DBCommService.getInstance().queryForLong(SqlCode.QUERY_CLIENT_BALANCE_BY_NBR,paramListx,Consts.CON_SLAVE);
				double t = balance * 1.0 / (10000 * 100);
				jObject.put("balance", t);
				return "{resultcode:0,result:" + jArray.toString() + "}";
			} else {
				return "{resultcode:1,result:[]}";
			}
		} catch (Exception e) {
			logger.error("通过clientNumber查询子账号", e);
		}
		return null;
	}
	/**
	 * 通过昵称查询子账号
	 * 
	 * @param request
	 * @param response
	 */
	public String queryByFriendlyName(String appId, String friendlyName) {
		try {
			List<Object[]> paramList = new ArrayList<Object[]>();
			paramList.add(new Object[]{"string", friendlyName});
			paramList.add(new Object[]{"string", appId});
			List<Map<String, Object>> list = DBCommService.getInstance().queryForList(
					SqlCode.QUERY_CLIENT_BY_FRIENDLYNAME, paramList,Consts.CON_SLAVE);
			if (list != null && list.size() > 0) {
				Map<String, Object> map = list.get(0);
				String clientNumber = map.get("client_number") == null ? "" : map.get("client_number").toString();
				JSONArray jArray = JSONArray.fromObject(map);
				JSONObject jObject = jArray.getJSONObject(0);
				if (clientNumber.equals("")) {
					jObject.put("balance", 0);
				} else {
					List<Object[]> paramListx = new ArrayList<Object[]>();
					paramListx.add(new Object[]{"string", clientNumber});
					long balance = DBCommService.getInstance().queryForLong(SqlCode.QUERY_CLIENT_BALANCE_BY_NBR,paramListx,Consts.CON_SLAVE);
					double t = balance * 1.0 / (10000 * 100);
					jObject.put("balance", t);
				}
				return "{resultcode:0,result:" + jArray.toString() + "}";
			} else {
				return "{resultcode:1,result:[]}";
			}
		} catch (Exception e) {
			logger.error("通过昵称查询子账号", e);
		}
		return "{resultcode:-100}";
	}
	/**
	 * 子账号充值
	 * 
	 * @param request
	 * @param response
	 */
	public String chargeClient(String sid, String chargeType, String money, String clientNumber, String appId) {
		try {
			List<Object[]> paramList = new ArrayList<Object[]>();
			String key = Consts.getKey(Consts.KEY_CLIENT, clientNumber);
			paramList.add(new Object[]{"string", clientNumber});
			paramList.add(new Object[]{"string", sid});
			Map<String, String> map = getHashMapRedis(SqlCode.QUERY_CLIENT_BY_NAME, key, paramList,Consts.CON_MASTER);
			// 2015年10月13日16:49:43 判断client是否属于应用 C103122
			if (map != null) {
				if (!appId.equals(map.get("app_sid"))) {
					return "{resultcode:6,result:[]}";
				}
				JSONArray jArray = JSONArray.fromObject(map);
				String status = jArray.getJSONObject(0).getString("status");
				if (status != null && status.equals("0")) {
					return "{resultcode:4,result:[]}";
				}
			} else {
				return "{resultcode:5,result:[]}";
			}
			// ************
			double dm = Double.parseDouble(money);
			long idm = new Double(Math.round(dm * 100)).longValue();
			long fee = (Consts.PARAM_TRIFF * idm);
			//充值为+钱  回收-钱
			if (!chargeType.equals("0")) {
				fee *= -1;
			}
			try {
				//ucpaas_statistics
				List<Object[]> pList = new ArrayList<Object[]>();
				pList.add(new Object[]{"string", clientNumber});
				// long
				// balance=DBCommService.getInstance().queryForLong(SqlCode.QUERY_CLIENT_BALANCE_BY_NBR,
				// pList,true);
				// get client balance
				String sqlx = "select balance from tb_bill_client_balance where client_number=?";
				long clientBalance = DBCommService.getInstance().queryForLong(sqlx, pList,Consts.CON_MASTER);

				String sql1 = "insert into ucpaas_statistics.tb_srv_client_fee_[DATE](sid,client_number,fee,pre_balance,stype,app_sid,client_count,create_date)values(?,?,?,?,?,?,?,now())";
				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMM");
				String now = sdFormat.format(new Date());
				sql1 = sql1.replace("[DATE]", now);
				List<Object[]> pList2 = new ArrayList<Object[]>();
				pList2.add(new Object[]{"string", sid});
				pList2.add(new Object[]{"string", clientNumber});
				pList2.add(new Object[]{"number", fee});
				pList2.add(new Object[]{"number", clientBalance});
				if (chargeType.equals("0")) {
					pList2.add(new Object[]{"string", Consts.CLIENT_CHARGE_IN});
				} else {
					pList2.add(new Object[]{"string", Consts.CLIENT_CHARGE_OUT});
				}
				pList2.add(new Object[]{"string", appId});
				pList2.add(new Object[]{"number", 1});
				int orderId = DBLogService.getInstance().updateAndGetKey(sql1, pList2);
				
				//ucpaas库
				try{
					String sql2 = "update tb_bill_client_balance set balance=balance+? where client_number=?";
					List<Object[]> pList3 = new ArrayList<Object[]>();
					pList3.add(new Object[]{"number", fee});
					pList3.add(new Object[]{"string", clientNumber});
					DBCommService.getInstance().update(sql2, pList3);
				} catch (Exception e) {
					//回滚ucpaas_statistics数据库
					deleteOrder(orderId);
					logger.error("子账号充值,异常：{}",e);
					return "{resultcode:1,result:[]}";
				}
				
				//获取ubs配置
				/*String ubsServer = SysConfig.getInstance().getProperty("ubs_server");
				String ubsPort = SysConfig.getInstance().getProperty("ubs_port");
				//请求ubs应用充值接口
				String url =new StringBuffer().append("http://").append(ubsServer).append(":")
						.append(ubsPort).append("/ubs/charge/clientAccount").toString();
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("sid", sid);
				param.put("appSid", appId);
				param.put("clientNumber",clientNumber);
				param.put("charge", fee);
				String result = HttpUtils.postJson(url, param);
				//如果返回值为空，则说明出现ubs出现bug
				if(StringUtils.isBlank(result)){
					return "{resultcode:1,result:[]}";
				}
				//如果返回值resultcode不为true，这说明充值失败
				JsonObject obj = JsonUtils.toJsonObj(result);
				String resultCode = obj.get("result")==null?"":obj.get("result").getAsString();
				if (StringUtils.isBlank(resultCode) || !resultCode.equalsIgnoreCase("true") ) {
					logger.info("client充值失败：appSid="+appId+",金额："+fee+",原因："+obj.get("desc").getAsString());
					return "{resultcode:1,result:[]}";
				}*/
				//ucpaas_client
				try {
					ClientBalanceInfo balanceInfo = new ClientBalanceInfo();
					balanceInfo.setBalance(clientBalance - fee);
					balanceInfo.setClientNumber(clientNumber);
					balanceInfo.setUpdateDate(new Date());
					//client接口 直接传页面参数即可
					clientAsyncFacade.chargeClientBalanceByClientNumber(clientNumber, chargeType, Math.abs(fee));
				} catch (Exception e) {
					//回滚ucpaas_statistics数据库
					deleteOrder(orderId);
					//回滚ucpaas数据库
					String sql2 = "update tb_bill_client_balance set balance=balance-? where client_number=?";
					List<Object[]> pList3 = new ArrayList<Object[]>();
					pList3.add(new Object[]{"number", fee});
					pList3.add(new Object[]{"string", clientNumber});
					DBCommService.getInstance().update(sql2, pList3);
					logger.error("调用dubbo组件失败：" + e.getMessage());
					return "{resultcode:1,result:[]}";
				}
				return "{resultcode:0,result:[]}";
			} catch (Exception e) {
				logger.error("子账号充值,异常：{}",e);
				return "{resultcode:1,result:[]}";
			}
		} catch (Exception e) {
			logger.error("子账号充值", e);
			return "{resultcode:1,result:[]}";
		}
	}

	/**
	 * 应用详单
	 * 
	 * @param request
	 * @param response
	 */
	public String billList(String appId, String date, String nowday) {
		try {
			try {
				String fileServer = SysConfig.getInstance().getProperty("file_server") + "/queryFile?type=" + date
						+ "&appSid=" + appId + "&date=" + nowday;
				logger.info(fileServer);
				String json = HttpHelper.httpConnectionGet(fileServer);
				try {
					JSONObject jsonObject = JSONObject.fromObject(json);
					if (jsonObject.get("result") != null && jsonObject.getString("result").equals("success")) {
						return "{resultcode:0,result:[" + json + "]}";
					} else {
						return "{resultcode:1,result:[]}";
					}
				} catch (Exception e) {
					return "{resultcode:1,result:[]}";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			logger.error("应用详单", e);
		}
		return "{resultcode:-100}";
	}
	
	/**
	 * client login
	 * 
	 * @param request
	 * @param response
	 */
	public String clientLogin(String acct, String password) {
		try {
			String newPass = Encrypt.deEncrypt(password, null);
			List<Object[]> plList = new ArrayList<Object[]>();
			plList.add(new Object[]{"string", newPass});
			plList.add(new Object[]{"string", acct});
			plList.add(new Object[]{"string", acct});
			List<Map<String, Object>> list = DBCommService.getInstance().queryForList(SqlCode.QUERY_USER_BY_LOGIN,
					plList,Consts.CON_MASTER);
			if (list != null && list.size() > 0) {
				String status = list.get(0).get("status").toString();
				if (status.equals("0")) {
					return "{resultcode:4}";
				} else if (status.equals("6")) {
					return "{resultcode:5}";
				} else {
					JSONObject jsObject = JSONObject.fromObject(list.get(0));
					String appSid = list.get(0).get("app_sid").toString();
					List<Object[]> plList2 = new ArrayList<Object[]>();
					plList2.add(new Object[]{"string", appSid});
					List<Map<String, Object>> clientList = DBCommService.getInstance().queryForList(
							SqlCode.QUERY_CLIENT_BY_APPSID_FOR, plList2,Consts.CON_MASTER);
					if (clientList != null && clientList.size() > 0) {
						jsObject.put("resultcode", "0");
						jsObject.put("client", JSONArray.fromObject(clientList).toString());
					} else {
						jsObject.put("resultcode", "6");
					}
					return jsObject.toString();
				}
			} else {
				return "{resultcode:3}";
			}
		} catch (Exception e) {
			logger.error("子账号登录", e);
		}
		return null;
	}
	
	/**
	 * client login
	 * 
	 * @param request
	 * @param response
	 */
	public String clientLoginNew(String acct, String password) {
		try {
			String newPass = Encrypt.deEncrypt(password, null);
			List<Object[]> plList = new ArrayList<Object[]>();
			plList.add(new Object[]{"string", newPass});
			plList.add(new Object[]{"string", acct});
			plList.add(new Object[]{"string", acct});
			List<Map<String, Object>> list = DBCommService.getInstance().queryForList(SqlCode.QUERY_USER_BY_LOGIN,
					plList,Consts.CON_MASTER);
			if (list != null && list.size() > 1) {
				String status = list.get(1).get("status").toString();
				if (status.equals("0")) {
					return "{resultcode:4}";
				} else if (status.equals("6")) {
					return "{resultcode:5}";
				} else {
					JSONObject jsObject = JSONObject.fromObject(list.get(1));
					String appSid = list.get(1).get("app_sid").toString();
					List<Object[]> plList2 = new ArrayList<Object[]>();
					plList2.add(new Object[]{"string", appSid});
					List<Map<String, Object>> clientList = DBCommService.getInstance().queryForList(
							SqlCode.QUERY_CLIENT_BY_APPSID_FOR, plList2,Consts.CON_MASTER);
					if (clientList != null && clientList.size() > 0) {
						jsObject.put("resultcode", "0");
						jsObject.put("client", JSONArray.fromObject(clientList).toString());
					} else {
						jsObject.put("resultcode", "6");
					}
					return jsObject.toString();
				}
			} else {
				return "{resultcode:3}";
			}
		} catch (Exception e) {
			logger.error("子账号登录", e);
		}
		return null;
	}
	
	
	/**
	 * 修改client密码
	 * 
	 * @param request
	 * @param response
	 */
	public String auditPwd(String appId, String clientNumber, String password) {
		try {
			List<Object[]> plx = new ArrayList<Object[]>();
			plx.add(new Object[]{"string", password});
			plx.add(new Object[]{"string", clientNumber});
			plx.add(new Object[]{"string", appId});
			int up = DBCommService.getInstance().update(SqlCode.UPDATE_CLIENT_PWD, plx);
			if (up > 0) {
				// 新增update tb_ucpaas_client set client_pwd=? where
				// client_number=? and app_sid=?
				try {
					ClientInfo clientInfo = new ClientInfo();
					clientInfo.setClientPwd(password);
					clientInfo.setClientNumber(clientNumber);
					clientInfo.setUpdateDate(new Date());
					clientInfo.setAppSid(appId);
					clientAsyncFacade.updateByClientNumberCache(clientInfo);
				} catch (Exception e) {
					logger.error("调用dubbo组件失败：" + e.getMessage());

				}
				// --------------------------------------end-------------------------------------
				RedisService.getInstance().delKey(Consts.getKey(Consts.KEY_CLIENT, clientNumber));
				return "{resultcode:0,result:[]}";
			} else {
				return "{resultcode:1,result:[]}";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 绑定子账号
	 * 
	 * @param request
	 * @param response
	 */
	public String bandClient(String clientNumber, String mobile, String appId, String sid, String clientType,
			String cType) {
		try {
			if (isSafePhone(mobile)) {
				return "{resultcode:2,result:[]}";
			}
			
			//-------------------2015年10月28日10:24:49--------------------------------
			//未上线应用只能绑定白名单里面的号码  公共验证已经查重 这里不做验证 
			List<Object[]> appPList = new ArrayList<Object[]>();
			appPList.add(new Object[]{"string", appId});
			String appkey = Consts.getKey(Consts.KEY_APP, appId);
			String appJson = getRedis(SqlCode.QUERY_APP_BY_ID, appkey, appPList,Consts.CON_MASTER);
			JSONArray jArray = JSONArray.fromObject(appJson);
			JSONObject jObject = jArray.getJSONObject(0);
			String status = jObject.getString("status");
			String appstype = jObject.getString("app_type");
			if (appstype.equals("1") && status.equals("0")) {
				//未上线的
				String swlkey = Consts.getKey(Consts.KEY_SID_WHITE_LIST, sid);
				List<Object[]> whiteParamList = new ArrayList<Object[]>();
				whiteParamList.add(new Object[]{"string", sid});
				String whiteList = getRedis(SqlCode.QUERY_WHITE_LIST, swlkey, whiteParamList,Consts.CON_MASTER);
				boolean isc = false;
				if (whiteList != null) {
					JSONArray jArray_white = JSONArray.fromObject(whiteList);
					for (int i = 0; i < jArray_white.size(); i++) {
						JSONObject jObject_white = jArray_white.getJSONObject(i);
						String whiteMobile = "";
						if (jObject_white.get("mobile") != null) {
							whiteMobile = jObject_white.getString("mobile");
						}
						if (whiteMobile.equals(mobile)) {
							isc = true;
						}
					}
				}
				if (!isc) {
					return "{resultcode:7,result:[]}";
				}
					
				}
			
			String key = Consts.getKey(Consts.KEY_CLIENT, clientNumber);
			List<Object[]> paramList = new ArrayList<Object[]>();
			paramList.add(new Object[]{"string", clientNumber});
			paramList.add(new Object[]{"string", sid});
			Map<String, String> map = getHashMapRedis(SqlCode.QUERY_CLIENT_BY_NAME, key, paramList,Consts.CON_MASTER);
			// 2015年10月13日16:48:41 判断client是否属于应用
			if (map != null) {
				 jArray = JSONArray.fromObject(map);
				 status = jArray.getJSONObject(0).getString("status");
				if (status != null && status.equals("0")) {
					return "{resultcode:5,result:[]}";
				}
			} else {
				return "{resultcode:6,result:[]}";
			}

			if (!clientType.equals("2")) {// 2不检查
				List<Object[]> plmobile = new ArrayList<Object[]>();
				plmobile.add(new Object[]{"string", appId});
				plmobile.add(new Object[]{"string", mobile});
				int count = DBCommService.getInstance().queryForInt(SqlCode.QUERY_CLIENT_BAND_MOBILE, plmobile,Consts.CON_MASTER);
				if (count > 0) {
					return "{resultcode:3,result:[]}";
				}
			}
			List<Object[]> pkl2 = new ArrayList<Object[]>();
			pkl2.add(new Object[]{"string", clientNumber});
			pkl2.add(new Object[]{"string", appId});
			int check = DBCommService.getInstance().queryForInt(SqlCode.CHECK_CLIENT_APP, pkl2,Consts.CON_MASTER);
			if (check == 0) {
				return "{resultcode:4,result:[]}";
			}
			
			if (clientType.equals("2")) {// 2删除号码池
				List<Object[]> alist = new ArrayList<Object[]>();
				alist.add(new Object[]{"string", clientNumber});
				alist.add(new Object[]{"string", appId});
				int del = DBCommService.getInstance().update(SqlCode.DEL_MOBILE, alist);
				if (del > 0) {
					logger.info("删除号码池成功clientNumber=" + clientNumber);
				} else {
					logger.info("删除号码池失败clientNumber=" + clientNumber);
				}
			}
			List<Object[]> plList = new ArrayList<Object[]>();
			plList.add(new Object[]{"string", mobile});
			plList.add(new Object[]{"string", clientNumber});
			int update = DBCommService.getInstance().update(SqlCode.BAND_CLIENT_MOBILE, plList);
			if (update > 0) {
				// 新增update tb_ucpaas_client set mobile=?,update_date=now()
				// where
				// client_number=?
				try {
					ClientInfo clientInfo = new ClientInfo();
					// clientInfo.setMobile(mobile);
					clientInfo.setUpdateDate(new Date());
					clientInfo.setClientNumber(clientNumber);
					clientInfo.setIsVerify(0);
					clientInfo.setAppSid(appId);
					// clientAsyncFacade.updateByClientNumberCache(clientInfo);
					clientInfo.setMobile(map.get("mobile") == null ? "" : map.get("mobile"));
					clientAsyncFacade.updateMobileCacheByClientNumber(clientInfo, mobile);

					if (cType.equals("0")) {
						// 新增 修改测试client的mobile
						TestClientInfo testClient = new TestClientInfo();
						testClient.setMobile(mobile);
						testClient.setUpdateDate(new Date());
						testClient.setClientNumber(clientNumber);
						clientAsyncFacade.updateByClientNumber(testClient);
					}
					// --------------------end-----------------------------------------------
				} catch (Exception e) {

					logger.error("调用dubbo组件失败：" + e.getMessage());
				}

				key = Consts.getKey(Consts.KEY_CLIENT, clientNumber);
				logger.info("删除redis,key=" + key);
				MemActionTools.getInstance().addQuene(getMemInfo(key, null, "delete", 0, ""));
				return "{resultcode:0}";
			} else {
				return "{resultcode:1}";
			}
		} catch (Exception e) {
			logger.error("绑定子账号", e);
		}
		return "{resultcode:-100}";
	}


	/**
	 * 解绑子账号
	 * 
	 * @param request
	 * @param response
	 */
	public String delBand(String clientNumber, String appId, String sid) {
		try {
			List<Object[]> pkl2 = new ArrayList<Object[]>();
			pkl2.add(new Object[]{"string", clientNumber});
			pkl2.add(new Object[]{"string", appId});
			int check = DBCommService.getInstance().queryForInt(SqlCode.CHECK_CLIENT_APP, pkl2,Consts.CON_MASTER);
			if (check == 0) {
				return "{resultcode:4,result:[]}";
			}

			String key = Consts.getKey(Consts.KEY_CLIENT, clientNumber);
			List<Object[]> paramList = new ArrayList<Object[]>();
			paramList.add(new Object[]{"string", clientNumber});
			paramList.add(new Object[]{"string", sid});
			Map<String, String> map = getHashMapRedis(SqlCode.QUERY_CLIENT_BY_NAME, key, paramList,Consts.CON_MASTER);
			// 2015年10月13日16:48:41 判断client是否属于应用
			if (map != null) {
				JSONArray jArray = JSONArray.fromObject(map);
				String status = jArray.getJSONObject(0).getString("status");
				if (status != null && status.equals("0")) {
					return "{resultcode:5,result:[]}";
				}
			} else {
				return "{resultcode:6,result:[]}";
			}
			List<Object[]> alist = new ArrayList<Object[]>();
			alist.add(new Object[]{"string", clientNumber});
			alist.add(new Object[]{"string", appId});
			int del = DBCommService.getInstance().update(SqlCode.DEL_MOBILE, alist);
			if (del > 0) {
				logger.info("删除号码池成功clientNumber=" + clientNumber);
			} else {
				logger.info("删除号码池失败clientNumber=" + clientNumber);
				// return "{resultcode:4}";
			}
			List<Object[]> plList = new ArrayList<Object[]>();
			plList.add(new Object[]{"string", ""});
			plList.add(new Object[]{"string", clientNumber});
			int update = DBCommService.getInstance().update(SqlCode.BAND_CLIENT_MOBILE, plList);
			if (update > 0) {
				// 新增update tb_ucpaas_client set mobile=?,update_date=now()
				// where
				// client_number=?
				try {
					ClientInfo clientInfo = new ClientInfo();
					clientInfo.setClientNumber(clientNumber);
					clientInfo.setUpdateDate(new Date());
					// clientInfo.setMobile("");
					clientInfo.setIsVerify(0);
					clientInfo.setAppSid(appId);
					clientInfo.setMobile(map.get("mobile") == null ? "" : map.get("mobile"));
					// clientAsyncFacade.updateByClientNumberCache(clientInfo);
					clientAsyncFacade.updateMobileCacheByClientNumber(clientInfo, "");
					// 关闭测试client单独
					if (map.get("client_type").equals("0")) {
						// 新增 修改测试client的mobile
						TestClientInfo testClient = new TestClientInfo();
						testClient.setMobile("");
						testClient.setUpdateDate(new Date());
						testClient.setClientNumber(clientNumber);
						clientAsyncFacade.updateByClientNumber(testClient);
					}
				} catch (Exception e) {
					logger.error("调用dubbo组件失败：" + e.getMessage());
				}
				// -----------------------------------end-----------------------------------------
				key = Consts.getKey(Consts.KEY_CLIENT, clientNumber);
				logger.info("删除redis,key=" + key);
				MemActionTools.getInstance().addQuene(getMemInfo(key, null, "delete", 0, ""));
				return "{resultcode:0}";
			} else {
				return "{resultcode:1}";
			}
		} catch (Exception e) {
			logger.error("绑定子账号", e);
		}
		return "{resultcode:-100}";
	}

	/**
	 * 换绑/解绑
	 * @param clientNumber
	 * @param verify
	 * @return
	 */
	public int updateClientVerify(String clientNumber, String verify) {
		try {
			String key = Consts.getKey(Consts.KEY_CLIENT, clientNumber);
			List<Object[]> paramList = new ArrayList<Object[]>();
			paramList.add(new Object[]{"number", verify});
			paramList.add(new Object[]{"string", clientNumber});
			int up = DBCommService.getInstance().update(SqlCode.UPDATE_CLIENT_VERIFY, paramList);
			if (up > 0) {
				logger.info("更新验证状态成功" + clientNumber);
				// 删除缓存
				RedisService.getInstance().delKey(key);
				logger.info("删除缓存key=" + Consts.getKey(Consts.KEY_CLIENT, clientNumber));
			} else {
				logger.info("更新验证状态失败" + clientNumber);
			}
			return up;
		} catch (Exception e) {
			logger.error("换绑/解绑失败，异常：{}",e);
		}
		return 0;
	}

	public String monitor() {
		// TODO 运维监控-查询数据是否正常
		List<Map<String, String>> list = DBCommService.getInstance().queryForListStr(SqlCode.MONITOR, null, Consts.CON_MASTER);
		if(list==null || list.size()==0 || list.get(0)==null || list.get(0).size()==0){
			return "1";
		}
		// TODO 运维监控-查询REDIS是否正常
		String appSid = "21a5b46c25a44d438313efbd3c34b965";
		String appkey = Consts.getKey(Consts.KEY_APP, appSid);
		try {
			RedisService.getInstance().monitorRedis(appkey);
		} catch (Exception e) {
			return "2";
		}
		return "0";
	}
	
	public Map<String, String> getHashMapRedis(String sqlCode, String key, List<Object[]> paramList,String conPoolName) {
		try {
			long timered = new Date().getTime();
//			Map<String, String> object = RedisService.getInstance().hgetall(key);//屏蔽redis
			Map<String, String> object = null;
			logger.info("CCC访问redis时长" + (new Date().getTime() - timered));
			if (object == null || object.size() == 0) {
				long timesjk = new Date().getTime();
				List<Map<String, String>> list = DBCommService.getInstance().queryForListStr(sqlCode, paramList,conPoolName);
				logger.info(sqlCode + "CCC访问数据库时长" + (new Date().getTime() - timesjk));
				if (list != null && list.size() > 0) {
					try {
						MemActionTools.getInstance().addQuene(getMemInfo(key, list.get(0), "hmset", 0));
					} catch (Exception e) {
						e.printStackTrace();
					}
					return list.get(0);
				} else {
					return null;
				}
			} else {
				return object;
			}
		} catch (Exception e) {
			List<Map<String, String>> list = DBCommService.getInstance().queryForListStr(sqlCode, paramList,Consts.CON_MASTER);
			if (list != null && list.size() > 0) {
				return list.get(0);
			} else {
				return null;
			}
		}
	}
	
	private boolean isSafePhone(String phone) {
		List<Object[]> pl = new ArrayList<Object[]>();
		pl.add(new Object[]{"string", phone});
		int count = DBCommService.getInstance().queryForInt(SqlCode.QUERY_SAFE_PHOE, pl,Consts.CON_MASTER);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	private String getRedis(String sqlCode, String key, List<Object[]> paramList,String conPoolName) {
		try {
			long timered = new Date().getTime();
			Object object = null;
//			Object object = RedisService.getInstance().get(key);//屏蔽redis
			logger.info(sqlCode + "AAA访问redis时长" + (new Date().getTime() - timered));
			if (object == null) {
				long timesjk = new Date().getTime();
				List<Map<String, Object>> list = DBCommService.getInstance().queryForList(sqlCode, paramList,conPoolName);
				logger.info(sqlCode + "AAA访问数据库时长" + (new Date().getTime() - timesjk));
				if (list != null && list.size() > 0) {
					String json = JSONArray.fromObject(list).toString();
					// add cache
					try {
						MemActionTools.getInstance().addQuene(getMemInfo(key, json, "query", 0, ""));
					} catch (Exception e) {
					}
					return json;
				} else {
					return null;
				}
			} else {
				return object.toString();
			}
		} catch (Exception e) {
			List<Map<String, Object>> list = DBCommService.getInstance().queryForList(sqlCode, paramList,conPoolName);
			if (list != null && list.size() > 0) {
				return JSONArray.fromObject(list).toString();
			} else {
				return null;
			}
		}
	}

	private MemInfo getMemInfo(String key, String value, String type, int expries, String field) {
		MemInfo memInfo = new MemInfo();
		memInfo.setField(field);
		memInfo.setKey(key);
		memInfo.setValue(value);
		memInfo.setType(type);
		memInfo.setExpries(expries);
		return memInfo;
	}

	private MemInfo getMemInfo(String key, Map<String, String> hashMap, String type, int expries) {
		MemInfo memInfo = new MemInfo();
		memInfo.setKey(key);
		memInfo.setHashMap(hashMap);
		memInfo.setType(type);
		memInfo.setExpries(expries);
		return memInfo;
	}
	
	private void deleteOrder(int orderId){
		//ucpaas_statistics
		String sql = "delete from ucpaas_statistics.tb_srv_client_fee_[DATE] where order_id=?";
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMM");
		String now = sdFormat.format(new Date());
		sql = sql.replace("[DATE]", now);
		List<Object[]> pList = new ArrayList<Object[]>();
		pList.add(new Object[]{"string", orderId});
		DBLogService.getInstance().update(sql, pList);
	}
}
