package com.flypaas.constant;

public class FlypaasConstant {
	//消息类型 1：公告  2：资费变更 3：充值确认  4：余额提醒 5系统消息 6管理员指定消息
	public static final int ANNOUNCEMENT = 1;
	public static final int RATE_NOTICE = 2;
	public static final int RECHARGE_NOTICE = 3;
	public static final int BALANCE_NOTICE = 4;
	public static final int SYSTEM_NOTICE = 5;
	public static final int ADMIN_NOTICE = 6;
	
	//是否已读 0：未读  1：已读
	public static final String NOTICE_UNREAD = "0";
	public static final String NOTICE_READ = "1";
	
	//开户默认参数
	public static final long DEFUALT_POINT = 0L;
	public static final String DEFUALT_RANK = "1";
	public static final String OPEN_STATUS = "1";
	public static final double DEFUALT_BALANCE = 0D;
	public static final int DEFUALT_LOGIN_TIMES = 0;
	public static final String DEFUALT_PWD = "123456";
	
	//是否有专线IP 有：1    无：0
	public static final String ISLINEIP = "1";
	public static final String NOLINEIP = "0";
	
	//RTPP状态 0:正常工作    1:审核中...    2: 不通过    3:故障   4:暂停    5:下线 
	/*public static final String RTPP_NORMAl = "0";
	public static final String RTPP_AUDITING = "1";
	public static final String RTPP_FAILPASS = "2";
	public static final String RTPP_FAULT = "3";
	public static final String RTPP_SUSPEND = "4";
	public static final String RTPP_STOP = "5";*/
	public static final String  RTPP_STATUS_INIT = "0";    //初始状态
	public static final String  RTPP_STATUS_AUDITING = "1";   // 审核中
	public static final String  RTPP_STATUS_AUDIT_FAIL = "2";   // 审核失败
	public static final String  RTPP_STATUS_READY = "3";   // 就绪状态
	public static final String  RTPP_STATUS_STOP = "4"; // 暂停使用
	public static final String  RTPP_STATUS_OFFLINE = "5";   // 离线状态
	public static final String  RTPP_STATUS_ONLINE = "6";   //在线状态
	
	//查询默认国家,默认省份,默认省份下的城市  86  1  10   中国   北京  北京
	
	public static final int DEFAULT_COUNTRY = 86;
	public static final int DEFAULT_PROVINCE = 1;
	public static final int DEFAULT_CITY = 10;
	
	//默认路由区域 cn
	public static final String DEFAULT_ROUTEAREA = "cn";
	
	//默认消息参数
	public static final String MSG_TITLE = "您有一个节点被审核";
	public static final int MSG_Type = 5;
	public static final String MSG_HASREAG = "0";
	
	//提款流水状态
	public static final String FLOW_APPLY = "0";
	public static final String FLOW_PASS = "1";
	public static final String FLOW_REFUSED  = "2";
	public static final String FLOW_CANCEL = "3";
	public static final String FLOW_FINISHED = "4";
	
	//管理员名称
	public static final String ADMIN_NAME = "系统管理员";
}
