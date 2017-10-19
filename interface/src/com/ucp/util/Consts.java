package com.ucp.util;

import java.util.Iterator;
import java.util.Map;

public class Consts{
    /**
     * **********************redis********************
     */
	//method
    public final static String INTFACE_M_ACCOUNTINFO = "m:accountInfo";
    public final static String INTFACE_M_GET_SQL = "m:getSql";
    public final static String INTFACE_M_REFRESH_SQL = "m:refreshSql";
    public final static String INTFACE_M_GET_KEY = "m:getKey";
    public final static String INTFACE_M_CREATE_SUB = "m:createClient";
    public final static String INTFACE_M_CLOSE_SUB = "m:closeClient";
    public final static String INTFACE_M_FIND_CLIENT_PAGE = "m:findClients";
    public final static String INTFACE_M_FIND_CLIENT_NAME = "m:queryClientByName";
    public final static String INTFACE_M_FIND_FRIENDLY_NAME = "m:queryByFriendlyName";
    public final static String INTFACE_M_FIND_CLIENT_MOBILE = "m:queryClientByMobile";
    public final static String INTFACE_M_CHARGE_CLIENT = "m:chargeClient";
    public final static String INTFACE_M_BILL_LIST = "m:billList";
    public final static String INTFACE_M_CLIENT_BILL_LIST = "m:clientBillList";
    public final static String INTFACE_M_CLIENTLIST = "m:clientList";
    public final static String INTFACE_M_BANDCLIENT = "m:bandClient";
    public final static String INTFACE_M_DISPLAYCLIENT = "m:displayClient";
    public final static String INTFACE_M_CALLLOG = "m:callLog";
    public final static String INTFACE_M_MID = "mid";
    public final static String INTFACE_M_NOTIFY = "voiceNotify";
    public final static String INTFACE_M_NOTIFYBACK = "notifyBack";
    public final static String INTFACE_M_DEL_KEY = "delKey";
    public final static String INTFACE_M_GET_MSG_NBR = "getMsgNbr";
    public final static String INTFACE_M_SEND_MSG = "sendMsg";
    public final static String INTFACE_M_SEND_MSG_CALLBACK = "callback";
    public final static String INTFACE_M_TEMPLATE_MSG = "templateMsg";
    public final static String INTFACE_M_CALLBACK = "callback2";
    public final static String INTFACE_M_VOICE_CODE = "voiceCode";
    public final static String INTFACE_M_VCBACK = "vcback";
    public final static String INTFACE_M_BATCH_DEL_KEYS = "delKeys";
    //key
    public final static String KEY_ACCOUNT="main:";
    public final static String KEY_APP="app:";
    public final static String KEY_CLIENT="client:";
    public final static String KEY_WHITE_LIST="wl:";
    public final static String KEY_TEMPLATE="tl:";
    public final static String KEY_SHOW_NBR="show:";
    public final static String KEY_SID_WHITE_LIST="swl:";
    public final static String KEY_NOTIFY_LOG="tb_notify_log_:";//语音通知
//    public final static String KEY_CURNUM="curNum";
    public final static String KEY_CURNBR="curNbr";
    public final static String KEY_ROUTE="c:";
    public final static String KEY_KEY_WORD="keyword";
    public final static int REDIS_EXPIRE_TIME=7200;
    public final static String CLIENT_CHARGE_IN="2";
    public final static String CLIENT_CHARGE_OUT="3";
    
    public final static String VOIP_START="6";
    public final static String TEST_VOIP_START="7";
    public final static int NBR_LEN=9;
    //默认应用分配短信号码
    public final static String SMS_DEFAULT_APP_NBR="8888";
    //余额不足提醒短信号码
    public final static String SMS_DEFAULT_REMIND_NBR="1001";
    
//    public static final String PARAM_CLIENT_FEE="CLIENT_FEE";
//    public static final String PARAM_MSG_FEE="MSG_FEE";
    public static final int MSG_NBR_LEN=6;
    /**
     * @param key
     * @param str
     * @return
     * String
     * getKey
     */
    public static String getKey(String key,String str){
    	StringBuffer sBuffer=new StringBuffer();
    	sBuffer.append(key).append(str);
    	return sBuffer.toString();
    }
    /**
     * connection pool
     */
    public final static String CON_POOL = "atlas";
    public final static String CON_MAIN = "pool";
    public final static String CON_LOG = "log";
    /**
     * 新版本编码
     */
    public static final int NEW_VERSION = 100;
    public static final int pageSize = 12;
    public static final int search_pageSize=4;
    //费率，一分钱兑换多少
    public static final int PARAM_TRIFF=10000;
//    public final static long CLIENT_FEE=1000001;
//    public final static long COUNTRY_MSG_FEE=1005001;
//    public final static long VOICE_CODE_FEE=1016001;
    public final static long CLIENT_FEE=1000;
    public final static long COUNTRY_MSG_FEE=1005;
    public final static long VOICE_CODE_FEE=1016;
    public final static long CLODE_CODE=1018;
    public final static long VOICE_NOTIFY_FEE=1019;
    public final static long ROME_FEE=1021;
    //未上线应用最大创建子账号数
    public final static long MAX_CLIENT=100;
    public static String hex2Ten(String clientNbr){
    	String voipAccount="6000";
    	String voip=clientNbr.substring(10,clientNbr.length());
    	long temp=Long.parseLong(voip,16);
    	String temps=String.valueOf(temp);
    	int length=temps.length();
		if (length<10) {
			for (int j = 0; j < 10-length; j++) {
				temps="0"+temps;
			}
		}
		return voipAccount+temps;
    }
    public static String ten2Hex(String clientNbr){
    	String hexvoipAccount="0x00003C00";
    	String voip=clientNbr.substring(4,clientNbr.length());
    	long start=Long.parseLong(voip);
    	String hextemp=Long.toHexString(start).toString();
    	int hexlength=hextemp.length();
		if (hexlength<8) {
			for (int j = 0; j < 8-hexlength; j++) {
				hextemp="0"+hextemp;
			}
		}
		return hexvoipAccount+hextemp;
    }
    public static boolean isKeyWord(Map<String, String> keyWordMap,String content){
    	if (keyWordMap!=null) {
    		Iterator iter = keyWordMap.entrySet().iterator();
    		while (iter.hasNext()) {
    			Map.Entry<String,String> entry = (Map.Entry<String,String>) iter.next();
//    			String key = entry.getKey();
    			String value = entry.getValue();
    			if (content.contains(value)) {
					return true;
				}
    		}
		}
    	return false;
    }
}
