package com.yzx.rest.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yzx.rest.bean.MemInfo;
import com.yzx.rest.util.Consts;
import com.yzx.rest.util.Encrypt;
import com.yzx.rest.util.MemActionTools;
import com.yzx.rest.util.SqlCode;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ServiceManager {
	private static final Logger logger = LoggerFactory.getLogger(ServiceManager.class);
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
	
	private String getRedis(String sqlCode, String key, List<Object[]> paramList,String conPoolName) {
		try {
//			long timered = new Date().getTime();
			Object object = null;
//			Object object = RedisService.getInstance().get(key);//屏蔽redis
//			logger.info(sqlCode + "AAA访问redis时长" + (new Date().getTime() - timered));
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
}
