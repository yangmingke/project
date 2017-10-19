package com.flypaas.constant;

public class AppConstant {
//应用类型
	//测试应用
	public static final String APP_TYPE_TEST="0" ;
	//正式应用
	public static final String APP_TYPE="1" ;
//应用类型
	//待审核
	public static final String STATUS_0="0" ;
	//已审核
	public static final String STATUS_1="1" ;
	//审核不通过
	public static final String STATUS_2="2";
	//已删除
	public static final String STATUS_3="3";
	//待审核
	public static final String STATUS_4="4";
	//强制下线
	public static final String STATUS_5="5";
	//重新提交
	public static final String STATUS_6="6";
//审核类型
	//1 待审核
	public static final String CHECK_STATUS="1";
	
	
//白名单类型
	public static final String WHITE_TYPE="0";
	
//测试号码类型
	//默认注册手机
	public static final String TEST_NUM_TYPE_0="0" ;
	//新增测试号
	public static final String TEST_NUM_TYPE_1="1" ;
//分配client
	//true：json  false：xml
	public static final boolean RESULT_TYPE=true;
	//计费模式（0主号计费、1 主子都计费）
	public static final String CHARGETYPE="0";
	public static final String CHARGE="0";
	
//短信模板状态
	//待审核
	public static final String SMS_TEMPLATE_1="1";
	//审核通过
	public static final String SMS_TEMPLATE_2="2";
	//审核不通过
	public static final String SMS_TEMPLATE_3="3";
	//已删除
	public static final String SMS_TEMPLATE_4="4";
//应用短信号码状态
	//有效
	public static final String APP_SMS_NBR_STATUS_0="0" ;
	//失效
	public static final String APP_SMS_NBR_STATUS_1="1" ;
//显号
	public static final String APP_IS_SHOW_NBR="1";
	public static final String APP_NOT_SHOW_NBR="0";
//显号号码类型 1:官号 2:语音验证码
	public static final String APP_IS_SHOW_NBR_TYPE_1="1";
	public static final String APP_IS_SHOW_NBR_TYPE_2="2";
//显号号码状态 0：已删除  1：(application)正常 (temp_application)待审核
	public static final String APP_IS_SHOW_NBR_STATUS_0="0";
	public static final String APP_IS_SHOW_NBR_STATUS_1="1";
//铃音状态
	public static final int RING_STATUS_1=1;
	public static final int RING_STATUS_2=2;
	public static final int RING_STATUS_3=3;
	public static final int RING_STATUS_4=4;
	public static final int RING_UPLOAD_STATUS_1=1;
	
	public static final int RING_TYPE_1 = 1;
	public static final int RING_TYPE_2 = 2;
	
	public static final Integer RING_CONTENT_TYPE_1=1;
	public static final Integer RING_CONTENT_TYPE_2=2;
//app钱包状态
	//正常
	public static final int APP_BALANCE_STATUS_1=1;
	//删除
	public static final int APP_BALANCE_STATUS_2=2;
//云通知语音状态
	//待审核
	public static final int APP_CLOUD_VOICE_STATUS=1;
	//voice
	public static final int APP_CLOUD_VOICE_TYPE=1;
	//voice相对文件目录
	public static final String APP_CLOUD_VOICE_PATH="voicemsg1";
	
	public static final String APP_BRAND="yzx";
	//测试手机状态 1:正常  2:删除
	public static final int TEST_DEMO_MOBILE_STATUS_1=1;
	//测试手机来源 1:注册手机  2:新增
	public static final int TEST_DEMO_MOBILE_TYPE_2=2;
	//透传号码状态
	public static final String SHOW_NBR_STATUS_1="1";
	public static final String SHOW_NBR_STATUS_2="2";
	//测试demo 1正常  2删除
	public static final Integer TEST_NBR_STATUS_1=1;
	public static final Integer TEST_NBR_STATUS_2=2;
	//测试demo 1注册手机  2新增测试
	public static final Integer TEST_NBR_TYPE_1=1;
	public static final Integer TEST_NBR_TYPE_2=2;
	
	public static final String CALL_LOG_SUFFIX=".txt";
}
