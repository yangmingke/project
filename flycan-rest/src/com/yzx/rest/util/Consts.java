package com.yzx.rest.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


public class Consts{
    public static final int NEW_VERSION = 100;
    public static final int pageSize = 12;
    public static final int search_pageSize=4;
    public static final String des_key="ucpaas_key";
    public static final String AccountInfo="AccountInfo";
    public static final String createClient="createClient";
    public static final String applyRoute="applyRoute";
    public static final String authentic="authentic"; 
    public static final String releaseRoute="releaseRoute";
    public static final String createSuperClient="createSuperClient";
    public static final String resetSuperCliRstPwd="resetSuperCliRstPwd";
    public static final String superCliGet="superCliGet";
    public static final String closeClient="closeClient";
    public static final String findClients="findClients";
    public static final String findClientByName="findClientByName";
    public static final String findClientByMobile="findClientByMobile";
    public static final String chargeClient="chargeClient";
    public static final String billList="billList";
    public static final String clientBillList="clientBillList";
    public static final String callBack="callBack";
    public static final String voiceCode="voiceCode";
    public static final String SMS="SMS";
    public static final String templateSMS="templateSMS";
    public static final String bandClients="bandClients";
    public static final String voiceNotify="voiceNotify";
    public static final String login="login";
    public static final String dispalyNumber="dispalyNumber";
    public static final String callLog="callLog";
    public static final String findClientByFriendName="findClientByFriendName";
    public static final String findClientByParam="findClientByParam";
    public static final String Mid="Mid";
    public static final String getVoiceNotify="getVoiceNotify";
    public static final String appBalance="appBalance";
    public static final String SMSTemplate="SMSTemplate";
    public static final String createApp="createApp";
    public static final String EditSMSTemplate="EditSMSTemplate";
    public static final String cancel="cancel";
    public static final String roamopen="roamopen";
    public static final String roamclose="roamclose";
    public static final String roomCreate="roomCreate";
    public static final String roomJoin="roomJoin";
    public static final String roomKick="roomKick";
    public static final String roomQuit="roomCreate";
    public static final String roomDel="roomDel";
    public static final String roomQuery="roomQuery";
    public static final String delBand="delBand";
    public static final String verifyPhone="verifyPhone";
    public static final String checkCode="checkCode";
    public static final String evt_conc="conc";
    public static final String evt_errorCode="errorCode";
    public static final String applyNumber="applyNumber";
    public static final String releaseNumber="releaseNumber";
    public static final String queryOrder="queryOrder";
    public static final String queryCons="queryCons";
    public static final String auditPwd="auditPwd";
    public static final String userState="userState";
    public static final String usrinfo = "usrinfo";
    public static final String default_area = "cn";
    
    //设备
    public static final String pc="1";
    public static final String Android="2";
    public static final String IOS="4";
    public static final String webRTC="5";
    
    //state cilen状态
    public static final String online="1";
    public static final String offline="2";
    
    //key
    public final static String KEY_ACCOUNT="main:";
    public final static String KEY_APP="app:";
    public final static String KEY_CLIENT="client:";
    public final static String KEY_WHITE_LIST="wl:";
    public final static String KEY_TEMPLATE="tl:";
    public final static String KEY_SHOW_NBR="show:";
    public final static String KEY_SID_WHITE_LIST="swl:";
    public final static String KEY_APP_TL="apptl:";
    public final static String KEY_SMS_PHONE="mp:";
    public final static String KEY_SMS_LOG="tb_sms_log_:"; //模板短信日志
    public final static String KEY_VERIFY_CODE_LOG="tb_verify_code_log_:";//语音验证码日志
    public final static String KEY_NOTIFY_LOG="tb_notify_log_:";//语音通知日志
    public final static String KEY_INDIV_VERIFYCODE="vc_appid_:"; //个性化语音验证码
//    public final static String KEY_CURNUM="curNum";
    public final static String KEY_CURNBR="curNbr";
    public final static String KEY_ROUTE="c:";
    public final static String KEY_KEY_WORD="keyword";
    public final static int REDIS_EXPIRE_TIME=7200;
    public final static String CLIENT_CHARGE_IN="2";
    public final static String CLIENT_CHARGE_OUT="3";
    //正式client
    public final static String VOIP_START="6";
    //测试client
    public final static String TEST_VOIP_START="7";
    //超级client
    public final static String SUPER_VOIP_START="9";
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
//    public final static String CON_SLAVE = "slave"; by yangmingke 暂时屏蔽读写分离
    public final static String CON_SLAVE = "master";
    public final static String CON_MASTER = "master";
    public final static String CON_LOG = "log";
    //费率，一分钱兑换多少
    public static final int PARAM_TRIFF=10000;
    public final static long CLIENT_FEE=1000;
    public final static long CLIENT_LICENSE=1040;
    public final static long COUNTRY_MSG_FEE=1005;
    public final static long VOICE_CODE_FEE=1016;
    public final static long CLODE_CODE=1018;
    public final static long VOICE_NOTIFY_FEE=1019;
    public final static long ROME_FEE=1021;
    public final static long TRADE_FEE=1025;//一号交易号码占用费用
    //未上线应用最大创建子账号数
    public final static long MAX_CLIENT=100;
    
    /**
     * 验证码短信单个参数长度
     */
    public final static long MAX_PARAM_LEN=10;
    public final static long MAX_SMS_LEN=200; //模板短信整体长度不能超过1000位(暂时定为200，待smsp代码修改完后设置为1000)
    //1
    //00 公共错误 01：主账号资源    02：应用资源     03：子账号资源    04：呼叫资源    05：短信资源 
    //100-199：接口错误 200-499：业务逻辑错误 500-699：系统内部错误
    public static final String C000000="000000";
    public static final String C100000="100000";//金额不为整数
    public static final String C100001="100001";//余额不足
    public static final String C100002="100002"; //数字非法
    public static final String C100003="100003"; //不允许有空值
    public static final String C100004="100004";//枚举类型取值错误
    public static final String C100005="100005";//访问IP不合法
    public static final String C100006="100006";//手机号不合法
    public static final String C100015="100015";//号码不合法
    public static final String C100500="100500";//HTTP状态码不等于200
    public static final String C100007="100007";//查无数据
    public static final String C100008="100008";//手机号码为空
    public static final String C100009="100009";//手机号为受保护的号码
    public static final String C100010="100010";//登录邮箱或手机号为空
    public static final String C100011="100011";//邮箱不合法
    public static final String C100012="100012";//密码不能为空
    public static final String C100013="100013";//没有测试子账号
    public static final String C100014="100014";//金额过大,不要超过12位数字
    public static final String C100016="100016";//钱包不可用(被冻结/已注销)
//    public static final String C100017="100017";//余额已注销
    public static final String C100018="100018";//通话时长需大于60秒
    public static final String C100699="100699";//系统内部错误
    public static final String C100019="100019";//应用餘額不足
    public static final String C100020="100020";//字符长度太长
    public static final String C100021="100021";//包含非法特殊字符
    public static final String C100104="100104";//callId不能为空
    public static final String C100105="100105";//日期格式错误
    public static final String C100106="100106";//密码的长度不能超过8位，且只有数字和字母 -add
    public static final String C100107="100107";//修改密码失败 -add
    public static final String C100108="100108";//userInfoKey不能为空
    public static final String C100109="100109";//userInfoKey格式错误
    
    public static final String C101100="101100";//请求包头Authorization参数为空
    public static final String C101101="101101";//请求包头Authorization参数Base64解码失败
    public static final String C101102="101102";//请求包头Authorization参数解码后账户ID为空
    public static final String C101103="101103";//请求包头Authorization参数解码后时间戳为空
    public static final String C101104="101104";//请求包头Authorization参数解码后格式有误
    public static final String C101105="101105";//主账户ID存在非法字符
    public static final String C101106="101106";//请求包头Authorization参数解码后时间戳过期
    public static final String C101107="101107";//请求地址SoftVersion参数有误
    public static final String C101108="101108";//主账户已关闭
    public static final String C101109="101109";//主账户未激活
    public static final String C101110="101110";//主账户已锁定
    public static final String C101111="101111";//主账户不存在
    public static final String C101112="101112";//主账户ID为空
    public static final String C101113="101113";//请求包头Authorization参数中账户ID跟请求地址中的账户ID不一致
    public static final String C101114="101114";//请求地址Sig参数为空
    public static final String C101115="101115";//请求token校验失败
    public static final String C101116="101116";//主账号sig加密串不匹配
    public static final String C101117="101117";//主账号token不存在
    public static final String C101118="101118";//主账户/密码错误或者不存在新demo
    public static final String C101119="101119";//主账户状态异常
    public static final String C101120="101120";//主账户注册未激活
    public static final String C101121="101121";//主账户未填写注册信息
    public static final String C101122="101122";//主账户已锁定
    
    public static final String C102100="102100";//应用ID为空
    public static final String C102101="102101";//应用ID存在非法字符
    public static final String C102102="102102";//应用不存在
    public static final String C102103="102103";//应用未上线
    public static final String C102104="102104";//测试应用不允许创建client
    public static final String C102105="102105";//应用不属于该主账号
    
    public static final String C102106="102106";//应用类型错误
    public static final String C102107="102107";//应用类型为空
    public static final String C102108="102108";//应用名为空
    public static final String C102109="102109";//行业类型为空
    public static final String C102110="102110";//行业信息错误
    public static final String C102111="102111";//是否允许拨打国际填写错误
    public static final String C102112="102112";//是否允许拨打国际不能为空
    public static final String C102113="102113";//创建应用失败
    public static final String C102114="102114";//应用名称已存在
    public static final String C102115="102115";//应用未上线
    public static final String C102116="102116";//应用未审核通过
    public static final String C102117="102117";//应用已删除
    public static final String C102118="102118";//应用待审核状态
    public static final String C102119="102119";//应用强制下线
    
    public static final String C103100="103100";//子账户昵称为空
    public static final String C103101="103101";//子账户名称存在非法字符
    public static final String C103102="103102";//子账户昵称长度有误
    public static final String C103103="103103";//子账户clientNumber为空
    public static final String C103104="103104";//同一应用下，friendlyname重复
    public static final String C103105="103105";//子账户friendlyname只能包含数字和字母和下划线
    public static final String C103106="103106";//client_number长度有误
    public static final String C103107="103107";//client_number不存在或不属于该主账号
    public static final String C103108="103108";//client已经关闭
    public static final String C103109="103109";//client充值失败
    public static final String C103110="103110";//client计费类型为空
    public static final String C103111="103111";//clientType只能取值0,1
    public static final String C103112="103112";//clientType为1时，charge不能为空
    public static final String C103113="103113";//clientNumber未绑定手机号
    public static final String C103114="103114";//同一应用下同一手机号只能绑定一次
    public static final String C103115="103115";//单次查询记录数不能超过100
    public static final String C103116="103116";//绑定手机号失败
    public static final String C103117="103117";//子账号是否显号(display)不能为空 
    public static final String C103118="103118";//子账号是否显号(display)取值只能是0(不显号)和1(显号) 
    public static final String C103119="103119";//应用下该子账号不存在 
    public static final String C103120="103120";//friendlyname不能为空 
    public static final String C103121="103121";//查询client参数不能为空 
    public static final String C103122="103122";//client不属于应用
    public static final String C103123="103123";//未上线应用不能超过100个client
    public static final String C103124="103124";//已经是开通状态
    public static final String C103125="103125";//子账号余额不足
    public static final String C103126="103126";//未上线应用或demo只能使用白名单中号码
    public static final String C103127="103127";//测试demo不能创建子账号
    public static final String C103128="103128";//校验码不能为空
    public static final String C103129="103129";//校验码错误或失效
    public static final String C103130="103130";//校验号码失败
    public static final String C103131="103131";//解绑失败,信息错误或不存在绑定关系
    public static final String C103132="103132";//应用已经存在超级号码
    public static final String C103133="103133";//应用下没有超级号码
    public static final String C103134="103134";//该接口没有权限操作超级号码
    //call
    public static final String C104100="104100";//主叫clientNumber为空
    public static final String C104101="104101";//主叫clientNumber未绑定手机号
    public static final String C104102="104102";//验证码为空
    public static final String C104103="104103";//显示号码不合法
    public static final String C104104="104104";//语音验证码位4-8位数字
    public static final String C104105="104105";//语音验证码位4-8位数字
    public static final String C104106="104106";//语音通知类型错误 
    public static final String C104107="104107";//语音通知内容为空 
    public static final String C104108="104108";//语音ID非法 
    public static final String C104109="104109";//文本内容存储失败 
    public static final String C104110="104110";//语音文件不存在或未审核
    public static final String C104111="104111";//号码与绑定的号码不一致
    public static final String C104112="104112";//开通或关闭呼转失败
    public static final String C104113="104113";//不能同时呼叫同一被叫
    public static final String C104114="104114";//内容包含敏感词
    public static final String C104115="104115";//语音通知发送多语音ID不能超过5个
    public static final String C104116="104116";//不在线呼转模式只能取1,2,3
    public static final String C104117="104117";//呼转模式为2则必须填写forwardPhone
    public static final String C104118="104118";//呼转模式为2、4则前转号码与绑定手机号码不能相等
    public static final String C104119="104119";//群聊列表格式不合法
    public static final String C104120="104120";//群聊呼叫模式只能是1免费,2直拨,3智能拨打
    public static final String C104121="104121";//会议ID不能为空 
    public static final String C104122="104122";//群聊超过最大方数 
    public static final String C104123="104123";//群聊ID发送错误
    public static final String C104124="104124";//群聊操作失败
    public static final String C104125="104125";//呼转号码不存在
    public static final String C104126="104126";//订单号不能为空
    public static final String C104127="104127";//订单号不存在
    public static final String C104128="104128";//号码释放失败或号码已经自动释放
    public static final String C104129="104129";//显手机号必须是呼叫列表中的号码
    public static final String C104130="104130";//主被叫不能相同
    public static final String C104131="104131";//开通国际漫游禁止回拨呼叫
    public static final String C104132="104132";//callid不能为空 --add
    public static final String C104133="104133";//roomOwner发起者不能为空 --add
    public static final String C104134="104134";//主被叫不能同时为空  -add
    public static final String C104135="104135";//开始时间结束时间不能为空 -add
    public static final String C104136="104136";//calla不能为空 -add
    //msg
    public static final String C105100="105100";//短信服务请求异常
    public static final String C105101="105101";//101:url关键参数为空
    public static final String C105102="105102";//2:号码不合法
    public static final String C105103="105103";//3：没有通道类别
    public static final String C105104="105104";//4：该类别为冻结状态
    public static final String C105105="105105";//5：没有足够金额
    public static final String C105106="105106";//6：不是国内手机号码并且不是国际电话
    public static final String C105107="105107";//7：黑名单
    public static final String C105108="105108";//8：含非法关键字
    public static final String C105109="105109";//9：该通道类型没有第三方通道
    public static final String C105110="105110";//短信模板ID不存在
    public static final String C105111="105111";//短信模板未审核通过
    public static final String C105121="105121";//短信模板已删除
    public static final String C105112="105112";//短信模板替换个数与实际参数个数不匹配
    public static final String C105113="105113";//短信模板ID为空
    public static final String C105114="105114";//短信内容为空
    public static final String C105115="105115";//短信类型长度应为1
    public static final String C105116="105116";//同一天同一用户不能发超过3条相同的短信
    public static final String C105117="105117";//模板ID含非法字符
    public static final String C105118="105118";//短信模板有替换内容，但参数为空
    public static final String C105119="105119";//短信模板替换内容过长，不能超过70个字符
    public static final String C105120="105120";//手机号码不能超过100个
    public static final String C105122="105122";//同一天同一用户不能发超过N条验证码(n为用户自己配置)
    public static final String C105123="105123";//短信模板名称为空
    public static final String C105124="105124";//短信模板内容为空
    public static final String C105125="105125";//创建短信模板失败
    public static final String C105126="105126";//短信模板名称错误
    public static final String C105127="105127";//短信模板内容错误
    public static final String C105128="105128";//短信模板id为空
    public static final String C105129="105129";//短信模板id不存在
    public static final String C105130="105130";//30秒内不能连续发同样的内容 
    public static final String C105131="105131";//30秒内不能给同一号码发送相同模板消息 
    public static final String C105132="105132";//验证码短信参数长度不能超过10位
    public static final String C105133="105133";//模板短信整体长度不能超过1000位
    //短信错误码
    public static final String S101="101";//101:url关键参数为空
    public static final String C102="102";//102:号码不合法
    public static final String C103="103";//103：没有通道类别
    public static final String C104="104";//104：该类别为冻结状态
    public static final String C105="105";//105：没有足够金额
    public static final String C106="106";//106：不是国内手机号码并且不是国际电话
    public static final String C107="107";//107：黑名单
    public static final String C108="108";//108：含非法关键字
    public static final String C109="109";//109：该通道类型没有第三方通道
    public static final int MAX_NUMBER=12;//
    //路由申请释放
    public static final String C106000="C106000";//会话cookie为空
    public static final String C106001="C106001";//来源方的公网IP为空
    public static final String C106002="C106002";//来源方的公网IP格式不匹配
    public static final String C106003="C106003";//目的方的公网IP为空
    public static final String C106004="C106004";//目的方的公网IP格式不匹配
    public static final String C106005="C106005";//不存在的资源申请路由策略
    public static final String C106006="C106006";//非法最大跳数限制
    public static final String C106007="C106007";//非法请求的路由数
    public static final String C106008="C106008";//不存在的协议类型
    public static final String C106009="C106009";//账户余额不足
    public static final String C106010="C106010";//请求参数错误，或cookie对应会话未释放
    public static final String C106011="C106011";//请求参数srcApList格式错误
    public static final String C106012="C106012";//请求参数dstApList格式错误
    public static final String C106013="C106013";//请求参数udpportnum格式错误
    public static final String C106014="C106014";//请求参数vflag格式错误
    public static final String C106015="C106015";//用户钱包状态异常
    
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
    public static final String key_code="code:";
    public static final int code_exprie=120;
    public static final int room_max_user=16;
    public static final String cookie_suffix="12345678";
    public static boolean isKeyWord(Map<String, String> keyWordMap,String content){
    	if (keyWordMap!=null) {
    		Iterator<Entry<String, String>> iter = keyWordMap.entrySet().iterator();
    		while (iter.hasNext()) {
    			Map.Entry<String,String> entry = (Map.Entry<String,String>) iter.next();
    			String value = entry.getValue();
    			if (content.toLowerCase().contains(value)) {
					return true;
				}
    		}
		}
    	return false;
    }
}
