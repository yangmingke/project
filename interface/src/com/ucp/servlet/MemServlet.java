package com.ucp.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ucp.bean.MemInfo;
import com.ucp.bean.Ulog;
import com.ucp.service.DBCommService;
import com.ucp.service.DBLogService;
import com.ucp.service.RedisService;
import com.ucp.util.BodyMsgUtil;
import com.ucp.util.CommonUtil;
import com.ucp.util.Consts;
import com.ucp.util.DateUtil;
import com.ucp.util.Encrypt;
import com.ucp.util.HttpHelper;
import com.ucp.util.LogAction;
import com.ucp.util.MemActionTools;
import com.ucp.util.SqlCode;
import com.ucp.util.SysConfig;
public class MemServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(MemServlet.class);
	/**
	 * Constructor of the object.
	 */
	public MemServlet() {
		super();
	}
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	/**
	 * The doGet method of the servlet. <br>
	 * This method is called when a form has its tag value method equals to get.
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@SuppressWarnings("deprecation")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/javascript;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String func = NullToEmpty(request.getParameter("func"));
		String ip=request.getParameter("ip");
		String host=request.getParameter("host")==null?"":request.getParameter("host");
		try {
			if (!StringUtils.isEmpty(ip)) {//
				try {
					String appSid=request.getParameter("appId");
					if (StringUtils.isNotEmpty(appSid)) {
						String key=Consts.getKey(Consts.KEY_WHITE_LIST, appSid);
						List<Object[]> paramList=new ArrayList<Object[]>();
						paramList.add(new Object[]{"string",appSid});
						String json=getRedis(SqlCode.QUERY_WHITE_LIST_BY_APPID,key,paramList);
						if (json!=null) {
							JSONArray jArray=JSONArray.fromObject(json);
							String allowip=jArray.getJSONObject(0).getString("white_address");
							boolean pass=false;
							String[] allowips=allowip.split(";");
							for (int i = 0; i < allowips.length; i++) {
								if (ip.equals(allowips[i])||host.equals(allowips[i])) {
									pass=true;
									break;
								}
							}
							if (!pass) {
								response.getWriter().print("{resultcode:2,result:[]}");
								logger.warn("不合法访问ip="+ip);
								return;
							}
						}
					}
				} catch (Exception e) {
					response.getWriter().print("{resultcode:2,result:[]}");
					logger.error("失败");
					return;
				}
			}
			if (Consts.INTFACE_M_SEND_MSG_CALLBACK.equals(func)) {//短信回调
				smsCallBack(request, response);
			}else if (Consts.INTFACE_M_VCBACK.equals(func)) {//语音验证码回调
				voiceCallBack(request, response);
			}else if (Consts.INTFACE_M_GET_KEY.equals(func)) {//获取redis缓存
				String sql="SELECT 1 from DUAL";
				try {
					int u=DBCommService.getInstance().queryForInt(sql,null,false);
					if (u==0) {
						response.getWriter().print("{resultcode:-1}");
						return;
					}
					u=DBLogService.getInstance().queryForInt(sql,null,false);
					if (u==0) {
						response.getWriter().print("{resultcode:-1}");
						return;
					}
				} catch (Exception e) {
					response.getWriter().print("{resultcode:-1}");
					return;
				}
				String key = request.getParameter("key");
				String object=RedisService.getInstance().get(key);
				response.getWriter().print("{resultcode:0,result:"+object+"}");
			}else if (Consts.INTFACE_M_DEL_KEY.equals(func)) {//删除redis缓存
				String key = request.getParameter("key");
				long l=RedisService.getInstance().delKey(key);
				
				logger.info("redis IP:" + RedisService.getInstance().getRedisInfo().getIp()+ " key="+key+",del res="+l);
				response.getWriter().print("{resultcode:0,result:"+l+"}");
			}else if (Consts.INTFACE_M_NOTIFY.equals(func)) {//内部发送语音通知,admin管理系统调用
				voiceNotify(request, response);
			}else if (Consts.INTFACE_M_NOTIFYBACK.equals(func)) {//语音通知回调
				notifyBack(request, response);
			}else {
				logger.error("不合法的请求"+func);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}
	/**
	 * 短信回调
	 * @param request
	 * @param response
	 */
	private void smsCallBack(HttpServletRequest request, HttpServletResponse response){
		try {
			response.getWriter().print("{resultcode:0}");
			response.getWriter().flush();
			response.getWriter().close();
			String result=request.getParameter("result");
			String msgindex=request.getParameter("msgindex");
			String accountSid=request.getParameter("accountSid");
			String smsId=request.getParameter("smsId");
			String sfee=request.getParameter("fee");
			String free=request.getParameter("free");
			String appId=request.getParameter("appId");
			String appBalance=request.getParameter("appBalance");
			String svb=request.getParameter("svb");
			String svbId=request.getParameter("svbId");
			String templateId=request.getParameter("templateId");
			if (StringUtils.isNotEmpty(free)&&free.equals("1")) {
				free="1";
			}else {
				free="0";
			}
			
			String status="0";//状态默认是失败
			long fee=0;//费用默认是0
			int index=Integer.parseInt(msgindex);
			logger.info("短信回调参数显示[result = "+result+",smsId = "+smsId+",fee = "+sfee+",free = "+free+",index = "+index+",accountSid = "
			+accountSid+",appId = "+appId+",appBalance = "+appBalance+",svb = "+svb+",svbId = "+svbId+",templateId = "+templateId+"]");
			
			SimpleDateFormat sFormat=new SimpleDateFormat("yyyyMMdd");
			String now=sFormat.format(new Date());
			//smsp返回成功状态码，则进行扣费操作
			if (result.equals("0")) {
				if (!free.equals("1")) {
					status="1";//状态是成功，且需要扣费
					fee=Long.parseLong(sfee);
					msgFee(accountSid,fee,appId,Long.parseLong(appBalance),svb,svbId);
				}else{
					status="2";//状态是成功，不需要扣费
				}
			}else {
				//如果短信发送失败，上报错误码
				Ulog ulog=new Ulog();
				ulog.setLogEvent("smsp");
				ulog.setErrorCode(result);
				ulog.setAppId(appId);
				ulog.setSid(accountSid);
				ulog.setSmsId(smsId);
				ulog.setTemplateId(templateId);
				addLog(ulog);
			}
				
			//新方案，从redis读取短信日志信息，并写入数据库,key模板“tb_sms_log_:99cda0123ffaa8734e13b884fdfdd6e3_1”
			String smsLogKey = request.getParameter("smsLogKey");
			logger.info("interface获取到的smsLogKey = " + smsLogKey);
			Map<String, String> smsLogMap=RedisService.getInstance().hgetall(smsLogKey);
			if(smsLogMap==null || smsLogMap.isEmpty()){
				logger.error("模板短信获取smsLogKey = " + smsLogKey + "失败或者无值");
				smsLogMap = new HashMap<String,String>();
			}
			
			List<Object[]> plx = new ArrayList<Object[]>();
			plx.add(new Object[]{"string",smsId});
			plx.add(new Object[]{"string",smsLogMap.get("fromNumber")});
			plx.add(new Object[]{"string",smsLogMap.get("toNumber")});
			plx.add(new Object[]{"string",smsLogMap.get("content")});
			plx.add(new Object[]{"string",smsLogMap.get("sid")});
			plx.add(new Object[]{"string",status});	//扣费成功为1，失败为0
			plx.add(new Object[]{"string",smsLogMap.get("appSid")});
			plx.add(new Object[]{"string",smsLogMap.get("lenType")});
			plx.add(new Object[]{"string",smsLogMap.get("msgType")});
			plx.add(new Object[]{"number",smsLogMap.get("templateId")!=null?Long.parseLong(smsLogMap.get("templateId")):null});
			plx.add(new Object[]{"number",fee}); //设置smsp传递的参数fee
			plx.add(new Object[]{"number",index});
			plx.add(new Object[]{"string",smsLogMap.get("templateParam")});
			plx.add(new Object[]{"string",smsLogMap.get("restIp")});
			plx.add(new Object[]{"string",smsLogMap.get("meetId")});
			plx.add(new Object[]{"string",CommonUtil.getUnixIP()}); //字段interface_ip
			
			String sql=DBCommService.getInstance().getSql(SqlCode.ADD_MSG_LOG);
			sql=sql.replace("[DATE]",now);
			DBLogService.getInstance().update(sql, plx, true);
			
			logger.info("模板短信日志入库成功：smsId = " + smsId + " , status = " + status + " , fee = " + fee + " , index = " + index + ", smsLogMap = " + smsLogMap);
		} catch (Throwable e) {
			logger.error("短信回调",e);
		}
	}
	private void addLog(Ulog ulog){
		LogAction.getInstance().addQuene(ulog);
	}
	/**
	 * 语音验证码回调
	 * @param request
	 * @param response
	 */
	private void voiceCallBack(HttpServletRequest request, HttpServletResponse response){
		try {
			response.getWriter().print("{\"result\":0}");
			response.getWriter().flush();
			response.getWriter().close();
			String callId=request.getParameter("callId");
			String result=request.getParameter("cb_return_code");
			String accountSid=request.getParameter("accountSid");
			String sfee=request.getParameter("fee");
			String free=request.getParameter("free");
			String appId=request.getParameter("appId");
			String appBalance=request.getParameter("appBalance");
			String svb=request.getParameter("svb");
			String svbId=request.getParameter("svbId");
			String to=request.getParameter("to");
			String intern_rate=request.getParameter("intern_rate");
			String Areqtime=request.getParameter("Areqtime");//A路请求时间
			String Aringtime=request.getParameter("Aringtime");//A路振铃时间
			String Aconntime=request.getParameter("Aconntime");//A路接通时间
			String Aendtime=request.getParameter("Aendtime");//A路结束时间
			String endreason=request.getParameter("endreason");//错误码
			String display=request.getParameter("display");//是否显号
			String displayNum=request.getParameter("displayNum");//显号号码
			String cbdisplay=request.getParameter("CBdisplay");//显号号码
			long delay=0;
			if (StringUtils.isNotEmpty(Areqtime)&&StringUtils.isNotEmpty(Aringtime)) {
				delay=Long.parseLong(Aringtime)-Long.parseLong(Areqtime);
			}
			
			logger.info("语音验证码回调参数显示callId = " + callId + ",result = " + result + ",accountSid = " + accountSid + ",sfee = " + sfee + ",free = " + free + ",appId = " + appId
				+",appBalance = " + appBalance + ",svb = " + svb + ",svbId = " + svbId + ",to = " + to + ",intern_rate = " + intern_rate + ",Areqtime = " + Areqtime + ",Aringtime = " + Aringtime
				+",Aconntime = " + Aconntime + ",Aendtime = " + Aendtime + ",endreason = " + endreason + ",display = " + display + ",displayNum = " + displayNum + ",cbdisplay = " + cbdisplay + ",delay = " + delay);
			
			if (StringUtils.isNotEmpty(to)) {
				if (to.startsWith("00")&&!to.startsWith("0086")) {//国际号码
					int size=0;
					if (to.length()>11) {//长度大于11从11位开始轮询
						size=11;
					}else {//长度低于11位从当前长度为轮询
						size=to.length();
					}
					for (int i = 0; i<size; i++) {
						String prifix=to.substring(0,size-i);
						List<Object[]> plx = new ArrayList<Object[]>();
						plx.add(new Object[]{"string",prifix});
						long fee=DBCommService.getInstance().queryForLong(SqlCode.QUERY_TARIFF, plx, true);
						if (fee>0) {
							BigDecimal rateDecimal=new BigDecimal(intern_rate);
							BigDecimal feeDecimal=new BigDecimal(fee);
							BigDecimal diDecimal=new BigDecimal(100);
							BigDecimal resultDecimal=feeDecimal.multiply(rateDecimal).divide(diDecimal);
							fee=resultDecimal.longValue();
							logger.info("国际费率计算折扣率："+intern_rate+",折后费用:"+fee);
							sfee=String.valueOf(fee);
							break;
						}
					}
				}
			}
			int Acalltime=Integer.parseInt(request.getParameter("Acalltime")==null?"0":request.getParameter("Acalltime"));
			if (StringUtils.isNotEmpty(free)&&free.equals("1")) {
				free="1";
			}else {
				free="0";
			}
			
			String status="0";//状态默认是失败
			long fee=0;//费用默认是0
			if (result==null||result.equals("0")) {
				if (Acalltime>0) {
					if (!free.equals("1")) {
						status="1";
						fee=Long.parseLong(sfee);
						msgFee(accountSid,fee,appId,Long.parseLong(appBalance),svb,svbId);
					}else{
						status="2";
						logger.info("语音验证码通话时长大于0，不扣费，status = " + status + " , callId = " + callId);
					}
				}else {
					status="3";
					logger.info("语音验证码通话时长为0，不扣费，status = " + status + " , callId = " + callId);
				}
			}else{
				//语音验证码错误上报
				Ulog ulog=new Ulog();
				ulog.setLogEvent("voice");
				ulog.setErrorCode(endreason);
				ulog.setAppId(appId);
				ulog.setSid(accountSid);
				ulog.setCallId(callId);
				ulog.setTo(to);
				ulog.setDisplay(display);
				ulog.setDisplayNum(displayNum);
				addLog(ulog);
			}
			
			try {
				//新方案，从redis读取短信日志信息，并写入数据库,key模板“tb_verify_code_log_:0344aea7d303f30261a6946fe55b22b5”
				String verifyCodeLogKey = request.getParameter("verifyCodeLogKey");
				logger.info("interface获取到的verifyCodeLogKey = " + verifyCodeLogKey);
				Map<String, String> verifyCodeLogMap=RedisService.getInstance().hgetall(verifyCodeLogKey);
				if(verifyCodeLogMap==null || verifyCodeLogMap.isEmpty()){
					logger.error("语音验证码获取verifyCodeLogKey = " + verifyCodeLogKey + "失败或者无值");
					verifyCodeLogMap = new HashMap<String,String>(); //如果缓存丢失，也插入默认null值
				}	
				List<Object[]> plx = new ArrayList<Object[]>();
				plx.add(new Object[]{"string",verifyCodeLogMap.get("verifyCode")});
				plx.add(new Object[]{"string",verifyCodeLogMap.get("to")});
				plx.add(new Object[]{"string",verifyCodeLogMap.get("displayNum")});
				plx.add(new Object[]{"string",verifyCodeLogMap.get("accountSid")});
				plx.add(new Object[]{"string",verifyCodeLogMap.get("appId")});
				plx.add(new Object[]{"number",fee});
				plx.add(new Object[]{"string",callId});
				plx.add(new Object[]{"string",verifyCodeLogMap.get("vType")});
				plx.add(new Object[]{"string",status});
				plx.add(new Object[]{"string",DateUtil.getTime(Areqtime)});
				plx.add(new Object[]{"string",DateUtil.getTime(Aringtime)});
				plx.add(new Object[]{"string",DateUtil.getTime(Aconntime)});
				plx.add(new Object[]{"string",DateUtil.getTime(Aendtime)});
				plx.add(new Object[]{"string",request.getRemoteAddr()});
				plx.add(new Object[]{"string",verifyCodeLogMap.get("localip")});
				plx.add(new Object[]{"number",delay});
				plx.add(new Object[]{"string",endreason});
				plx.add(new Object[]{"string",cbdisplay});
				plx.add(new Object[]{"string",CommonUtil.getUnixIP()}); //字段interface_ip
				
				String sql=DBCommService.getInstance().getSql(SqlCode.ADD_VERIFY_CODE_REDIS);
				SimpleDateFormat sFormat=new SimpleDateFormat("yyyyMMdd");
				String now=sFormat.format(new Date());
				sql=sql.replace("[DATE]", now);
				DBLogService.getInstance().update(sql, plx,true);
				
				logger.info("语音验证码日志入库成功：callId = " + callId + " , fee = " + fee + " , status = " + status + " , delay = " + delay + " , endreason = " + endreason + " , cbdisplay = " 
					+ cbdisplay + ", verifyCodeLogMap = " + verifyCodeLogMap);
			} catch (Throwable e) {
				logger.error("更新语音验证码日志失败",e);
			}
		} catch (Throwable e) {
			logger.error("语音验证码回调出错",e);
		}
	}
	/**
	 * 内部发送语音通知,admin管理系统调用
	 * @param request
	 * @param response
	 */
	private void voiceNotify(HttpServletRequest request, HttpServletResponse response){
		try {
			String appId=request.getParameter("appId");
			String sid=request.getParameter("sid");
			String to=request.getParameter("to");
			String toSerNum=request.getParameter("toSerNum");
			String type=request.getParameter("type");
			String playTimes=request.getParameter("playTimes")==null?"1":request.getParameter("playTimes");
			String remotePath=request.getParameter("remotePath")==null?"":request.getParameter("remotePath");
			List<Object[]> sidList = new ArrayList<Object[]>();
			sidList.add(new Object[]{"string",sid});
			int is_contract=DBCommService.getInstance().queryForInt(SqlCode.QUERY_USER_C,sidList);
			String key=Consts.getKey(Consts.KEY_APP, appId);
			List<Object[]> paramList = new ArrayList<Object[]>();
			paramList.add(new Object[]{"string",appId});
			String jsonx=getRedis(SqlCode.QUERY_APP_BY_ID,key,paramList);
			String isShowNbr="";
			String brand="yzx";
			boolean unonline=false;
			boolean isDemo=false;
			if (jsonx!=null) {
				JSONArray jsonArray=JSONArray.fromObject(jsonx);
				JSONObject jObject=(JSONObject)jsonArray.get(0);
				String appStatus=jObject.getString("status");
				String appstype=jObject.getString("app_type");
				isShowNbr=jObject.get("is_show_nbr")==null?"0":jObject.getString("is_show_nbr");
				brand=jObject.getString("brand");
				if (appstype.equals("1")&&appStatus.equals("0")) {
					unonline=true;
				}
				if (appstype.equals("0")) {
					isDemo=true;
				}
			}else {
				response.getWriter().print("{resultcode:-2,result:[]}");
				return;
			}
			JSONArray jArray=null;
			if (unonline||isDemo) {
				if (StringUtils.isNotEmpty(to)) {
					String swlkey = Consts.getKey(Consts.KEY_SID_WHITE_LIST, sid);
					List<Object[]> whiteParamList = new ArrayList<Object[]>();
					whiteParamList.add(new Object[]{"string",sid});
					String whiteList=getRedis(SqlCode.QUERY_WHITE_LIST, swlkey, whiteParamList);
					boolean isc=false;
					if (whiteList!=null) {
						jArray=JSONArray.fromObject(whiteList);
						for (int i = 0; i < jArray.size(); i++) {
							JSONObject jObject=jArray.getJSONObject(i);
							String whiteMobile="";
							if (jObject.get("mobile")!=null) {
								whiteMobile=jObject.getString("mobile");
							}
							if (whiteMobile.equals(to)) {
								isc=true;
							}
						}
					}
					if (!isc) {
						response.getWriter().print("{resultcode:-20,result:[]}");
						return ;
					}
				}
			}
			String callId=UUID.randomUUID().toString();
			long time=new Date().getTime();
			callId=Encrypt.deEncrypt(callId+time,null);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			String callTime=sdf.format(new Date());
			
			if (type.equals("0")) {//text
				JSONObject jcall = JSONObject.fromObject(BodyMsgUtil.commonProcess(request.getInputStream()));
				String content=jcall.getString("content");
				
				Map<String, String> keyWordMap=getHashMapRedis(SqlCode.QUERY_ALL_KEY_WORDS, Consts.KEY_KEY_WORD, paramList);
				if (Consts.isKeyWord(keyWordMap,content)) {
					response.getWriter().print("{resultcode:-21}");
					return ;
				}
				List<Object[]> notifyList=new ArrayList<Object[]>();
				notifyList.add(new Object[]{"string",sid});
				notifyList.add(new Object[]{"string",appId});
				notifyList.add(new Object[]{"string",content});
				notifyList.add(new Object[]{"string",to});
				notifyList.add(new Object[]{"string",toSerNum});
				notifyList.add(new Object[]{"number",playTimes});
				notifyList.add(new Object[]{"number",is_contract});
				notifyList.add(new Object[]{"string",callId});
				notifyList.add(new Object[]{"string",callTime});
				int update=DBCommService.getInstance().update(SqlCode.ADD_NOTIFY, notifyList);
				if (update>0) {
					response.getWriter().print("{resultcode:0,callId:\""+callId+"\",createDate:\""+callTime+"\"}");
					return;
				}else {
					response.getWriter().print("{resultcode:-1,result:[]}");
					return;
				}
			}else {//voice
				long fee=0;
				long balance=0;
	 			long creditBalance=0;
				long eventId=Consts.VOICE_NOTIFY_FEE;
				List<Object[]> paramList0 = new ArrayList<Object[]>();
 				paramList0.add(new Object[]{"string",sid});
 		    	paramList0.add(new Object[]{"string",eventId+"%"});
 				fee=DBCommService.getInstance().queryForLong(SqlCode.QUERY_FEE_BY_ID, paramList0,true);
 				List<Object[]> paramListx = new ArrayList<Object[]>();
 	        	paramListx.add(new Object[]{"string",sid});
 	        	List<Map<String, Object>> balanceList = DBCommService.getInstance().queryForList(SqlCode.QUERY_BALANCE_BY_SID2, paramListx);
 				if (balanceList.size()>0) {
					String enable_flag=balanceList.get(0).get("enable_flag").toString();
					if (enable_flag.equals("0")) {
						response.getWriter().print("{resultcode:-16,result:[]}");
						return;
					}else if (enable_flag.equals("3")) {
						response.getWriter().print("{resultcode:-17,result:[]}");
						return;
					}
					creditBalance=balanceList.get(0).get("credit_balance")==null?0:Long.parseLong(balanceList.get(0).get("credit_balance").toString());
					balance=Long.parseLong(balanceList.get(0).get("balance").toString());
 				}
 				if ((balance+creditBalance)<fee) {
 	 				response.getWriter().print("{resultcode:-3,result:[]}");
 	 				return;
 				}
 				List<Object[]> appListx = new ArrayList<Object[]>();
	 			appListx.add(new Object[]{"string",appId});
				long appBalance=DBCommService.getInstance().queryForLong2(SqlCode.QUERY_APP_BALANCE, appListx, true);
				if (appBalance!=-100&&appBalance<fee) {//
					response.getWriter().print("{resultcode:-18,result:[]}");
					return;
				}
				String voiceId="";
				String stype="1";
				String voiceNotifyUrl=DBCommService.getInstance().queryForString(SqlCode.QUERY_CALL_ADDRESS, paramList);
				if (voiceNotifyUrl==null) {
					voiceNotifyUrl="";
				}
				String officialNum="";
//				List<Map<String, Object>> list = DBCommService.getInstance().queryForList(SqlCode.QUERY_SHOW_NUMBER, paramList);
				if (StringUtils.isNotEmpty(toSerNum)) {
//					String keys=Consts.getKey(Consts.KEY_SHOW_NBR, appId);
					if (unonline||isDemo) {
						if (jArray!=null) {
							for (int i = 0; i < jArray.size(); i++) {
								JSONObject jObject=jArray.getJSONObject(i);
								String whiteMobile="";
								if (jObject.get("mobile")!=null) {
									whiteMobile=jObject.getString("mobile");
								}
								if (whiteMobile.equals(toSerNum)) {
									officialNum=whiteMobile;
								}
							}
							if (StringUtils.isEmpty(officialNum)) {
								isShowNbr="0";
							}
						}
					}else {
						List<Object[]> paramList2 = new ArrayList<Object[]>();
						paramList2.add(new Object[]{"string",appId});
						paramList2.add(new Object[]{"string",toSerNum});
						String nbr= DBCommService.getInstance().queryForString(SqlCode.QUERY_SHOW_NBR, paramList2);
						if (StringUtils.isNotEmpty(nbr)) {
							officialNum=nbr;
						}else {
							isShowNbr="0";
						}
					}
					/*String showJson=getRedis(SqlCode.QUERY_SHOW_NBR,keys,paramList2);
					if (showJson!=null) {
						JSONArray jsonArray=JSONArray.fromObject(showJson);
						JSONObject jObject=(JSONObject)jsonArray.get(0);
						String num=jObject.getString("nbr");
						if (StringUtils.isNotEmpty(num)) {
							officialNum=num;
						}
					}*/
				}else {
					toSerNum="";
				}
				String voiceName="";
				if (type.equals("1")) {//自己取
					JSONObject jcall = JSONObject.fromObject(BodyMsgUtil.commonProcess(request.getInputStream()));
					String content=jcall.getString("content");
					voiceId=content;
					/*List<Object[]> fileNameList = new ArrayList<Object[]>();
					fileNameList.add(new Object[]{"number",voiceId});
					List<Map<String,Object>> fileList=DBCommService.getInstance().queryForList(, fileNameList);*/
					String sql=DBCommService.getInstance().getSql(SqlCode.QUERY_FILENAME);
					sql=sql.replace("[VOICEID]", voiceId);
					List<Map<String,Object>> fileList=DBCommService.getInstance().queryForList(sql, null, true);
					if (fileList==null||fileList.size()<1) {
						response.getWriter().print("{resultcode:-4,result:[]}");
						return;
					}
					for (int i = 0; i < fileList.size(); i++) {
						String filename=fileList.get(i).get("file_name").toString();
						String file_path=fileList.get(i).get("file_path").toString();
						if (StringUtils.isEmpty(filename)||StringUtils.isEmpty(file_path)) {
							response.getWriter().print("{resultcode:-4,result:[]}");
							return;
						}
						filename=filename.substring(0,filename.lastIndexOf("."));
						if (i<fileList.size()-1) {
							voiceName+=file_path+"/"+filename+",";
						}else {
							voiceName+=file_path+"/"+filename;
						}
					}
				}else if (type.equals("2")) {//任务传
					voiceName=request.getParameter("voiceName");
					voiceId=request.getParameter("voiceId");
					stype="0";
					callId=request.getParameter("callId");
					callTime=request.getParameter("callTime");
				}
				
				String voiceNotifyLogKey = Consts.KEY_NOTIFY_LOG + callId; //语音通知日志键值
				logger.info("内部语音通知redis的key值voiceNotifyLogKey = "+voiceNotifyLogKey);
				try {//添加语音通知记录
					long time6 = new Date().getTime();
					//新方案，添加redis缓存语音通知日志记录
					String localip=CommonUtil.getUnixIP(); //字段rest_ip
					logger.info("本地ip="+localip);
					int redisLogExpire=Integer.parseInt(SysConfig.getInstance().getProperty("redis_log_expire")); //日志键过期时间
					
					Map<String,String> voiceNotifyLogMap = new HashMap<String,String>();
					voiceNotifyLogMap.put("to", to);
					voiceNotifyLogMap.put("stype", stype);
					voiceNotifyLogMap.put("sid", sid);
					voiceNotifyLogMap.put("appId", appId);
					voiceNotifyLogMap.put("callId", callId);
					voiceNotifyLogMap.put("playTimes", playTimes);
					voiceNotifyLogMap.put("toSerNum", toSerNum);
					voiceNotifyLogMap.put("voiceId", voiceId);
					voiceNotifyLogMap.put("callTime", callTime);
					if (isShowNbr.equals("0")) {
	 					voiceNotifyLogMap.put("isShow", "0");
					}else {
						voiceNotifyLogMap.put("isShow", "1");
					}
					voiceNotifyLogMap.put("localip", localip);
					RedisService.getInstance().hmset(voiceNotifyLogKey, voiceNotifyLogMap, redisLogExpire * 60); //默认过期时间为15分钟
					
					logger.info("内部语音通知入redis请求时长"+(new Date().getTime()-time6)+",callId="+callId);
				} catch (Throwable e) {
					logger.error("内部语音通知入redis错误：" + e);
				}
				if (StringUtils.isNotEmpty(to)&&StringUtils.isNotEmpty(toSerNum)) {
					if (to.trim().equals(toSerNum.trim())) {
						logger.warn("被叫号码to["+to+"]与显号号码toSerNum["+toSerNum+"]相同，则不显号");
						isShowNbr="0";
					}
				}
				List<Object[]> mainparamList = new ArrayList<Object[]>();
				mainparamList.add(new Object[]{"string",sid});
				String mainkey = Consts.getKey(Consts.KEY_ACCOUNT,sid);
				Map<String, String> map = getHashMapRedis(SqlCode.QUERY_ACCOUNT_BY_ID,mainkey,mainparamList);
				String intern_rate="100";
				if (map!=null&&StringUtils.isNotEmpty(map.get("intern_rate"))) {
					intern_rate=map.get("intern_rate");
				}
				JSONObject jObject=new JSONObject();
				jObject.put("rest",1);
				jObject.put("appbrand",brand);
				jObject.put("callid", callId);
				jObject.put("accountid", sid);
				jObject.put("appid", appId);
				jObject.put("officialNum", officialNum);
				jObject.put("voiceNotifyUrl", voiceNotifyUrl);
				jObject.put("is_contract", is_contract);
				jObject.put("ivrplaytimes", Integer.parseInt(playTimes));
				jObject.put("remotePath", remotePath);
				String baseUrl=SysConfig.getInstance().getProperty("sms_server");
				String callbackurl=SysConfig.getInstance().getProperty("this_server");
				StringBuffer sBuffer=new StringBuffer();
				sBuffer.append(baseUrl).append("/autocall?brandid=yzx").append("&caller=")
				.append(to).append("&uid=1").append("&callId=").append(callId)
				.append("&displaynum=").append(toSerNum)
				.append("&cbtype=1&display=").append(isShowNbr).append("&resv=").append(jObject)
				.append("&voicename=").append(voiceName)
				.append("&fromurl=").append(URLEncoder.encode(callbackurl+"/interface/mem?func=notifyBack&callId="+callId+"&sid="+sid+"&fee="+fee+"&appId="+appId+"&appBalance="+appBalance+"&to="+to+"&intern_rate="+intern_rate+"&voiceNotifyLogKey="+voiceNotifyLogKey));
				//模拟扣费
//				sBuffer=new StringBuffer();
//				sBuffer.append(callbackurl+"/interface/mem?func=notifyBack&callId="+callId+"&sid="+sid+"&fee="+fee+"&appId="+appId+"&appBalance="+appBalance+"&to="+to+"&intern_rate="+intern_rate+"&displaynum="+toSerNum+"&display="+isShowNbr+"&voiceNotifyLogKey="+voiceNotifyLogKey+"&cb_return_code="+"0"+"&Acalltime="+10);
				logger.info("requeUrl="+sBuffer.toString());
				String json=null;
				try {
					json=HttpHelper.httpConnectionGet(sBuffer.toString());
				} catch (Exception e) {
					response.getWriter().print("{resultcode:-2,result:[]}");
					return;
				}
				JSONObject jsObject=JSONObject.fromObject(json);
				String result=jsObject.getString("result");
				/*SimpleDateFormat sdDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
				String createDate=sdDateFormat.format(new Date());*/
				if (result.equals("0")) {
					response.getWriter().print("{resultcode:0,callId:\""+callId+"\",createDate:\""+callTime+"\"}");
				}else {
					response.getWriter().print("{resultcode:"+result+",result:[]}");
				}
			}
		} catch (Exception e) {
			logger.error("内部语音通知出错",e);
		}
	}
	/**
	 * 语音通知回调
	 * @param request
	 * @param response
	 */
	private void notifyBack(HttpServletRequest request, HttpServletResponse response){
		try {
			response.getWriter().print("{resultcode:0}");
			response.getWriter().flush();
			response.getWriter().close();
			String result=request.getParameter("cb_return_code");
			long sfee=Long.parseLong(request.getParameter("fee")); //传递的单价
			String callId=request.getParameter("callId");
			String sid=request.getParameter("sid");
			int Acalltime=Integer.parseInt(request.getParameter("Acalltime")==null?"0":request.getParameter("Acalltime"));
			String appId=request.getParameter("appId");
			String appBalance=request.getParameter("appBalance");
			String to=request.getParameter("to");
			String intern_rate=request.getParameter("intern_rate");
			String accountSid=request.getParameter("sid");
			String Areqtime=request.getParameter("Areqtime");//A路请求时间
			String Aringtime=request.getParameter("Aringtime");//A路振铃时间
			String Aconntime=request.getParameter("Aconntime");//A路接通时间
			String Aendtime=request.getParameter("Aendtime");//A路结束时间
			String endreason=request.getParameter("endreason");//错误码
			String display=request.getParameter("display");//是否显号
			String displayNum=request.getParameter("displayNum");//显号号码
			String cbdisplay=request.getParameter("CBdisplay");//显号号码
			long delay=0;
			if (StringUtils.isNotEmpty(Areqtime)&&StringUtils.isNotEmpty(Aringtime)) {
				delay=Long.parseLong(Aringtime)-Long.parseLong(Areqtime);
			}
			
			logger.info("语音通知回调参数显示result = " + result + ",sfee = " + sfee + ",callId = " + callId + ",sid = " + sid + ",Acalltime = " + Acalltime + ",appId = " + appId + ",appBalance = " + appBalance
				+",to = " + to + ",intern_rate = " + intern_rate + ",accountSid = " + accountSid + ",Areqtime = " + Areqtime + ",Aringtime = " + Aringtime + ",Aconntime = " + Aconntime
				+",Aendtime = " + Aendtime + ",endreason = " + endreason + ",display = " + display + ",displayNum = " + displayNum + ",cbdisplay = " + cbdisplay + ",delay = " + delay);
			
			String status="0"; //默认状态为失败
			long fee = 0; //默认计算后的单价为0
			if (result.equals("0")) {//cb成功
				List<Object[]> paramList = new ArrayList<Object[]>();
				paramList.add(new Object[]{"string",accountSid});
				String key = Consts.getKey(Consts.KEY_ACCOUNT, accountSid);
				Map<String, String> map = getHashMapRedis(SqlCode.QUERY_ACCOUNT_BY_ID,key,paramList);
				if (map!=null){
					intern_rate=map.get("intern_rate");
				}
				if (StringUtils.isNotEmpty(to)) {
					if (to.startsWith("00")&&!to.startsWith("0086")) {//国际号码
						int size=0;
						if (to.length()>11) {//长度大于11从11位开始轮询
							size=11;
						}else {//长度低于11位从当前长度为轮询
							size=to.length();
						}
						for (int i = 0; i<size; i++) {
							String prifix=to.substring(0,size-i);
							List<Object[]> plx = new ArrayList<Object[]>();
							plx.add(new Object[]{"string",prifix});
							long interfee=DBCommService.getInstance().queryForLong(SqlCode.QUERY_TARIFF, plx, true);
							if (interfee>0) {
								BigDecimal rateDecimal=new BigDecimal(intern_rate);
								BigDecimal feeDecimal=new BigDecimal(interfee);
								BigDecimal diDecimal=new BigDecimal(100);
								BigDecimal resultDecimal=feeDecimal.multiply(rateDecimal).divide(diDecimal);
								sfee=resultDecimal.longValue();
								logger.info("国际费率计算折扣率："+intern_rate+",折后费用:"+sfee);
								break;
							}
						}
					}
				}
				int m=0;
				if(Acalltime > 0){
					if (Acalltime%60==0) {
						m=Acalltime/60;
					}else {
						m=Acalltime/60+1;
					}
					fee = sfee * m;
					status="1";
					msgFee(sid,fee,appId,Long.parseLong(appBalance),"0",""); //语音通知扣费
				}else {
					status="2";
					logger.info("语音验通知通话时长等于0，不扣费，status = " + status + " , callId = " + callId + " , Acalltime = " + Acalltime);
				}
			}else {
				//语音通知错误上报
				Ulog ulog=new Ulog();
				ulog.setLogEvent("notify");
				ulog.setErrorCode(endreason);
				ulog.setAppId(appId);
				ulog.setSid(accountSid);
				ulog.setCallId(callId);
				ulog.setTo(to);
				ulog.setDisplay(display);
				ulog.setDisplayNum(displayNum);
				addLog(ulog);
			}
			
			try {//添加语音通知记录
 				String voiceNotifyLogKey = request.getParameter("voiceNotifyLogKey");
 				logger.info("interface获取到的voiceNotifyLogKey = " + voiceNotifyLogKey);
 				Map<String, String> voiceNotifyLogMap=RedisService.getInstance().hgetall(voiceNotifyLogKey);
 				if(voiceNotifyLogMap==null || voiceNotifyLogMap.isEmpty()){
 					logger.error("语音通知获取voiceNotifyLogKey = " + voiceNotifyLogKey + "失败或者无值");
 					voiceNotifyLogMap = new HashMap<String, String>(); //如果缓存丢失，也插入默认null值
 				}
 				
				List<Object[]> plx = new ArrayList<Object[]>();
				plx.add(new Object[]{"string",voiceNotifyLogMap.get("to")});
				plx.add(new Object[]{"number",voiceNotifyLogMap.get("stype")!=null?Integer.parseInt(voiceNotifyLogMap.get("stype")):-1});
				plx.add(new Object[]{"string",voiceNotifyLogMap.get("sid")});
				plx.add(new Object[]{"string",voiceNotifyLogMap.get("appId")});
				plx.add(new Object[]{"number",fee});
				plx.add(new Object[]{"number",Acalltime});
				plx.add(new Object[]{"string",callId});
				plx.add(new Object[]{"number",status});
				plx.add(new Object[]{"number",voiceNotifyLogMap.get("playTimes")});
				plx.add(new Object[]{"string",voiceNotifyLogMap.get("toSerNum")}); //字段displayNum
				plx.add(new Object[]{"string",voiceNotifyLogMap.get("voiceId")}); //字段content
				plx.add(new Object[]{"string",voiceNotifyLogMap.get("callTime")}); //字段send_time
				plx.add(new Object[]{"number",voiceNotifyLogMap.get("isShow")}); 
				plx.add(new Object[]{"string",DateUtil.getTime(Areqtime)});
				plx.add(new Object[]{"string",DateUtil.getTime(Aringtime)});
				plx.add(new Object[]{"string",DateUtil.getTime(Aconntime)});
				plx.add(new Object[]{"string",DateUtil.getTime(Aendtime)});
				plx.add(new Object[]{"string",request.getRemoteAddr()}); //字段cb_ip
				plx.add(new Object[]{"string",voiceNotifyLogMap.get("localip")}); //字段rest_ip
				plx.add(new Object[]{"number",delay});
				plx.add(new Object[]{"string",endreason});
				plx.add(new Object[]{"string",cbdisplay});
				plx.add(new Object[]{"string",CommonUtil.getUnixIP()}); //字段interface_ip
				
				String sql=DBCommService.getInstance().getSql(SqlCode.ADD_VOICE_NOTIFY_REDIS);
				SimpleDateFormat sFormat=new SimpleDateFormat("yyyyMMdd");
				String now=sFormat.format(new Date());
				sql=sql.replace("[DATE]", now);
				DBLogService.getInstance().update(sql, plx,true);
 				
				logger.info("语音通知日志入库成功：fee = " + fee + " , Acalltime = " + Acalltime + " , callId = " + callId + " , status = " + status + " , Areqtime = " + DateUtil.getTime(Areqtime) + " , Aringtime = " 
					+ DateUtil.getTime(Aringtime) + " , Aconntime = " + DateUtil.getTime(Aconntime) + " , Aendtime = " + DateUtil.getTime(Aendtime) + " , delay = " + delay
					+ " , endreason = " + endreason + ", cbdisplay = " + cbdisplay + " , voiceNotifyLogMap = " + voiceNotifyLogMap);
			} catch (Exception e) {
				logger.error("语音通知记录入库" , e);
			}
		} catch (Exception e) {
			logger.error("语音通知回调出错",e);
		}
	}
	/**
	 * 短信、语音通知、语音验证码扣费
	 * @param accountSid
	 * @param fee
	 * @param appId
	 * @param appBalance
	 * @param svb
	 * @param svbId 业务余额表id
	 */
	private void msgFee(String accountSid,long fee,String appId,long appBalance,String svb, String svbId){
		/*List<Object[]> paramListx = new ArrayList<Object[]>();
    	paramListx.add(new Object[]{"string",accountSid});*/
		/*long balance=DBCommService.getInstance().queryForLong(SqlCode.QUERY_BALANCE_BY_SID, paramListx);
		//扣费
		long nowBalance=balance-fee;*/
		List<Object[]> px = new ArrayList<Object[]>();
		px.add(new Object[]{"number",fee});
		px.add(new Object[]{"string",accountSid});
		if (svb.equals("1")) {
			long res = 0;
			if(StringUtils.isNotBlank(svbId)){//短信、语音验证码：有svbId
				px = new ArrayList<Object[]>();
				px.add(new Object[]{"number",fee});
				px.add(new Object[]{"number",svbId});
				res=DBCommService.getInstance().update(SqlCode.UPDATE_SERVICE_BALANCE_BY_ID,px);
				logger.info("短信、语音验证码,有svbId = " + svbId +",fee = " + fee + ",accountSid = " + accountSid);
			}else{//短信、语音验证码、语音通知：没有svbId
				px.add(new Object[]{"number",Consts.CLODE_CODE});
				res=DBCommService.getInstance().update(SqlCode.UPDATE_SERVICE_BALANCE,px);
				logger.info("短信、语音验证码、语音通知,没有svbId = " + svbId + ",fee = " + fee + ",accountSid = " + accountSid);
			}
			if (res>0) {
				logger.info("sid=["+accountSid+"]扣费成功");
			}
		}else {
			long res=DBCommService.getInstance().update(SqlCode.UPDATE_BALANCE,px);
			if (res>0) {
				logger.info("sid=["+accountSid+"]扣费成功");
			}
			if (appBalance>0) {
				List<Object[]> appList = new ArrayList<Object[]>();
				appList.add(new Object[]{"number",fee});
				appList.add(new Object[]{"string",appId});
				long res2=DBCommService.getInstance().update(SqlCode.UPDATE_APP_BALANCE,appList);
				if (res2>0) {
					logger.info("appId=["+appId+"]应用余额扣费成功");
				}
			}
		}
	}
	private Map<String, String> getHashMapRedis(String sqlCode,String key,List<Object[]> paramList){
		try {
			Map<String, String> object=RedisService.getInstance().hgetall(key);
			if (object==null||object.size()==0) {
				List<Map<String, String>> list=DBCommService.getInstance().queryForListStr(sqlCode, paramList);
				if (list!=null&&list.size()>0) {
					/*for (Map.Entry<String, String> entry:list.get(0).entrySet()) {
						try {
							MemActionTools.getInstance().addQuene(getMemInfo(key,entry.getValue(),"hset",0,entry.getKey()));
						} catch (Exception e) {
						}
					}*/
					try {
						MemActionTools.getInstance().addQuene(getMemInfo(key,list.get(0),"hmset",0));
					} catch (Exception e) {
						e.printStackTrace();
					}
					return list.get(0);
				}else {
					return null;
				}
			}else {
				return object;
			}
		} catch (Exception e) {
			List<Map<String, String>> list=DBCommService.getInstance().queryForListStr(sqlCode, paramList);
			if (list!=null&&list.size()>0) {
				return list.get(0);
			}else {
				return null;
			}
		}
	}
	private String getRedis(String sqlCode,String key,List<Object[]> paramList){
		try {
			Object object=RedisService.getInstance().get(key);
			if (object==null) {
				List<Map<String, Object>> list=DBCommService.getInstance().queryForList(sqlCode, paramList);
				if (list!=null&&list.size()>0) {
					String json=JSONArray.fromObject(list).toString();
					//add cache
					try {
						MemActionTools.getInstance().addQuene(getMemInfo(key,json,"query",0,""));
					} catch (Exception e) {
					}
					return json;
				}else {
					return null;
				}
			}else {
				return object.toString();
			}
		} catch (Exception e) {
			List<Map<String, Object>> list=DBCommService.getInstance().queryForList(sqlCode, paramList);
			if (list!=null&&list.size()>0) {
				return JSONArray.fromObject(list).toString();
			}else {
				return null;
			}
		}
	}
	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	private String NullToEmpty(String str){
		if (str==null) {
			return "";
		}else {
			return str;
		}
	}
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		
	}
	private MemInfo getMemInfo(String key,String value,String type,int expries,String field){
		MemInfo memInfo=new MemInfo();
		memInfo.setField(field);
		memInfo.setKey(key);
		memInfo.setValue(value);
		memInfo.setType(type);
		memInfo.setExpries(expries);
		return memInfo;
	}
	private MemInfo getMemInfo(String key,Map<String, String> hashMap,String type,int expries){
		MemInfo memInfo=new MemInfo();
		memInfo.setKey(key);
		memInfo.setHashMap(hashMap);
		memInfo.setType(type);
		memInfo.setExpries(expries);
		return memInfo;
	}
}
