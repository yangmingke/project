package com.flypaas.constant;

import com.flypaas.util.StrUtil;
import com.flypaas.util.SysConfig;

public class SysConstant {

	//系统生成sid秘钥
	public static final String KEY="ucp"+StrUtil.getRandom4() ;
	public static final String sidBaseString="ucp2014_sid"+StrUtil.getRandom4();
	public static final String tokenBaseString="ucp2014_token"+StrUtil.getRandom4();
	
	//邮箱验证
	public static final String V_MIAL_TYPE="1";
	
	//邮箱验证
	public static final String LOGSUC_MIAL_TYPE="4";
	
	//系统邮件
	public static final String SYS_MIAL_TYPE="2";
	
	//重置密码
	public static final String RESETPASSWORD_MIAL_TYPE="3";
	//群发修改密码邮件
	public static final String UPDATE_PASSWORD_MIAL_TYPE="6";
	
	//重置密码
	public static final String RESETPASSWORD_SUC_MIAL_TYPE="5";
	
	//系统采用字符集
	public static final String CHARSET="UTF-8" ;
	
	//邮件认证超时时间（小时）
	public static final int MAIL_TIMEOUT=24*60*60*1000;
	
	//连续7次登录错误禁止登录时间设置
	public static final long FORBID_TIME=24*60*60*1000;
	
	//系统为新开发者创建的client数
	public static final int cNum = 6 ;
	
	
//支付类型
	//支付宝
	public static final String CHARGETYPE_1= "1";
	//银联(易宝)
	public static final String CHARGETYPE_2= "6";
	//赠送
	public static final String CHARGETYPE_3= "B1";
	//保障金账户转账
	public static final String CHARGETYPE_4= "B2";
	//优惠券充值
	public static final String CHARGETYPE_5= "B3";
	
//支付结果
	//未支付
	public static final String CHARGERESULT_1="1";
	//提交到支付网关
	public static final String CHARGERESULT_2="2";
	//支付成功
	public static final String CHARGERESULT_3="3";
	//支付失败
	public static final String CHARGERESULT_4="4";
	
//余额结果码
	//冻结
	public static final String ENABLEFLAG_0="0";
	//正常
	public static final String ENABLEFLAG_1="1";
	
//数据字典类型	
	//应用行业数据字典标示
	public static final String INDUSTRY="industry" ;
	//应用类型
	public static final String APP_KIND="app_kind" ;
	//充值类型
	public static final String RECHARGE_TYPE="recharge_type" ;
	//回调函数功能数据字典标示
	public static final String CALLBACKFUN="callbackfun" ;
	//rest
	public static final String REST="rest";
	//余额不足提醒
	public static final String REMIND="remind";
	//云验证params标识
	public static final String PARAMS_KEY="cloud_check";
	
//注册时的短信验证码模板
	public static final String SIGN_SMS_TEMPLATE="sign_sms_template";
	
//统计首页曲线图数据库使用的关键字
	public static final String REPORT_TIME="hr";
	public static final String REPORT_COUNT="csm";
	
	
//系统设置费率（转化成元）
	//系统金额换算
	public static final int RATE=1000000;
	//支付宝对接金额换算
	public static final int payRate=100;
	
//成功
	public static final String SUCCESS="success";
	
//失败	
	public static final String FAIL="fail";
	
//成功
	public static final String NUM_SUCCESS="2";
	
//失败	
	public static final String NUM_FAIL="1";
	
//系统应用类型 其他编码
	public static final String OTAPP="14" ;
	
// 通用支付网关地址、KEY
    public static String PAY_GATEWAY_URL = null;
    public static String PAY_GATEWAY_KEY = null;
    public static String MERID=null;
    public static String PAY_CALLBACK_URL=null ;
    public static final Integer RESULT_SUCCESS = 0; // 成功
    public static final Integer RESULT_PARAMETER_ERROR = 1; // 参数错误
    public static final Integer RESULT_TIME_OUT = 2; // 超时（连支付网关）
    public static final Integer RESULT_REPEAT_ORDER = 3; // 重复订单
    public static final Integer RESULT_GW_RESULT_ERROR = 4; // 支付网关返回错误
    public static final Integer RESULT_ORDER_INTERNAL_ERROR = 5; // 订单系统内部错误
    public static final String MAC="05-16-DC-59-C2-34"; //易宝支付临时mac
    
//管理员使用语音短信资源所需资源
    //系统应用的app_sid
	public static final String SYS_APP_SID = "0";
	//超级管理员的sid
	public static final String SUPER_ADMIN_SID = "d137a9184dd1b84a6eae1ff5ccbc6bc9";
	//超级管理员的token
	public static final String SUPER_ADMIN_TOKEN = "d137a9184dd1b84a6eae1ff5ccbc6bc8";
	//系统应用默认clientnumber
	public static final String CLIENTNUMBER="60000000000000";
	//系统短信模板id1
	public static final String SMS_TEMP_LOG_ID="1" ;
	//系统短信模板id2
	public static final String SMS_TEMP_OTHER_ID="100" ;
	//短信类型(注册SMS_TEMP_LOG_ID)
	public static final String SMS_TYPE_LOG="log" ;
	//短信类型(其他SMS_TEMP_OTHER_ID)
	public static final String SMS_TYPE_OTHER="update" ;
	
//系统充值
	public static final long MAX_CHARGE=1000000;
	
//rest成功结果码
	public static final String REST_RESULT_SUC="000000";
	
//uuid长度
	public static final int PWD_MAX_LEN=20 ;
	
//密码长度
	public static final int PWD_LEN=6 ;
	
//sdk、demo、rest-api
	public static final String SDK="11" ;
	public static final String CLIENT_DEMO="21" ;
	public static final String REST_DEMO="22" ;
	
//新闻中的图片地址
	public static  String UEDITOR_ICO_PREFIX= null ;
	public static  String UCP_UEICO_PREFIX=null;
	public static  String UCP_ADMIN_PATH=null ;
	public static final String NEWS_IMG_PREFIX="(/flypaas-admin/file/view\\?path=)(.+?)\"";
	public static final String UCP_NEWS_IMG_PREFIX="/file/view?FileName=";
	public static final String NEWS_DOWNLOAD_IMG_PREFIX="(/flypaas-admin/file/download\\?path=)(.+?)\"";
	public static final String UCP_NEWS_DOWNIMG_PREFIX="/download/downLocal?path=";
//调用rest扣费类型
	//扣费
	public static final String REST_CHARGE_TYPE_0="0" ;
	//免费
	public static final String REST_CHARGE_TYPE_1="1" ;
//私钥存贮地址
	public static String DES_FILE_PATH=null;
//是否显号 0:不显好  1:显号 (显号、显示官方号码) 2:(显示手机号)
	public static int IS_SHOWNBR=1;
//个性化套餐
	public static int IS_PERSONALPCK=1;
//下载文件的查询语句
	public static String DOWNLOAD_SQL="SELECT f.remote_path FROM [table] f WHERE f.`status` = 1 AND f.remote_path IS NOT NULL AND f.local_path = '[local_path]' LIMIT 1" ;
//套餐中间表
	//套餐状态 1：待变更
	public static String TEMP_PCK_STATUS_1="1";
	//套餐变更类型 1:手动变更
	public static String TEMP_PCK_TYPE_1="1" ;
//文件下载的最大字节设置 5M
	public static long DOWNLOAD_FILE_BYTE=5*1024*1024 ;
//时间后缀
	public static final String BEGINDATEPOSTFIX =" 00:00:00" ;
	public static final String ENDDATEPOSTFIX =" 23:59:59" ;
//保障金金额
	public static final int SECURITY_MONEY=50000;
//图片类型
	public static final String [] IMG_TYPE_LIST = {"jpg", "jpeg","gif","png"};
//短信模板签名符合
//	public static final String MSG_PREFIX="[";
//	public static final String MSG_POSTFIX="]";
//系统图片大小
	public static final long SYS_PIC_MAXSIZE=2*1024*1024;
//系统图片大小
	public static final long SYS_RING_MAXSIZE=1*1024*1024;
//发票状态
	public static final String INVOICE_STATUS_1="1";
	public static final String INVOICE_STATUS_0="0";
	public static final String INVOICE_ADDR_STATUS_1="1";
	public static final int INVOICE_LOWER_MONEY=100;
//发票状态 1：专票  2：普票
	public static final String INVOICE_TYPE_1="1";
	public static final String INVOICE_TYPE_2="2";
//默认渠道表
	public static final int CHANNEL_ID = 100000;
//指定兑换码
	public static final String PARAM_COUPON="coupon_num";
	public static final String PARAM_EVENTTYPE="S";
	public static final int SERVICE_BANLANCE_TYPE_1=1;
//体验业务类型 1:验证码类 、2:云通知、3:回拨
	public static final int EXPR_TYPE_1=1 ;
	public static final int EXPR_TYPE_2=2 ;
	public static final int EXPR_TYPE_3=3 ;
//注册密码长度限制
	public static final int SYS_PWD_MIN_LEN=8;
	public static final int SYS_PWD_MAX_LEN=16;
//系统账户类型 0:云平台账户  1:保障金用户
	public static final String [] ACCOUNT_TYPE={"0","1"};
//平台下载账单类型
	public static final String DOWN_BILL_TYPE="day" ;
//系统分隔符	
	public static final String SYS_DIVISION="#" ;
    static{
    	PAY_GATEWAY_URL = SysConfig.getInstance().getProperty("pay_gateway_url");
    	PAY_GATEWAY_KEY = SysConfig.getInstance().getProperty("pay_gateway_key");
    	PAY_CALLBACK_URL ="http://"+SysConfig.getInstance().getProperty("host")+ SysConfig.getInstance().getProperty("pay_callback_url");
    	MERID = SysConfig.getInstance().getProperty("merId");
    	UEDITOR_ICO_PREFIX="("+SysConfig.getInstance().getProperty("flypaas_admin_domain")+"/flypaas-admin/|"+SysConfig.getInstance().getProperty("flypaas_admin_host")+"/flypaas-admin/)(.+?)\"";
    	UCP_UEICO_PREFIX=SysConfig.getInstance().getProperty("host")+"/";
    	UCP_ADMIN_PATH=SysConfig.getInstance().getProperty("flypaas_admin_tomcat_path");
    	DES_FILE_PATH=SysConfig.getInstance().getProperty("des_file_path");
    }
}
