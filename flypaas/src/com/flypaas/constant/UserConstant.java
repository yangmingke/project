package com.flypaas.constant;

public class UserConstant {
//用户状态	
	//未激活
	public static final String STATUS_0="0"; 
	//已激活
	public static final String STATUS_1="1";
	//锁定
	public static final String STATUS_5="5";
	//关闭
	public static final String STATUS_6="6";
//认证状态
	//待认证
	public static final String AUTH_STATUS_2="2";
	//证件已认证
	public static final String AUTH_STATUS_3="3";
	//认证不通过
	public static final String AUTH_STATUS_4="4";
	
	//验证邮箱和手机号存在
	public static final String EXIST="1";
	//验证邮箱和手机号不存在
	public static final String NOTEXIST="0";
	
//登录错误码
	//成功
	public static final String LOG_SUCCESS="0" ;
	//用户名错误
	public static final String USERNAME_FAIL="1" ;
	//密码错误
	public static final String PASSWORD_FAIL="2" ;
	//未激活
	public static final String VERIFY_FAIL="3" ;
	//用户已过期
	public static final String USER_INVALID="4" ;
	//用户被锁定
	public static final String USER_FORBID="5" ;
	//用户被锁定
	public static final String SUPERID_FAIL="6" ;
	//代理商被锁定
	public static final String AGENT_LOCK="7" ;
	//代理商身份被取消
	public static final String AGENT_FAIL="8" ;
	//认证信息是否可以修改
	public static final String OUTH_MODIFY_0="0";
	//用户类型
	//个人开发者
	public static final String USER_TYPE_1="1" ;
	//企业
	public static final String USER_TYPE_2="2" ;
	//管理员
	public static final String USER_TYPE_3="3" ;

//认证类型
	//1:身份证(11::身份证正面,10:身份证背面) 2:护照 3:组织机构证 4:税务登记证 5:营业执照
	public static final String OAUTH_TYPE_1="1" ;
	public static final String OAUTH_TYPE_2="2" ;
	public static final String OAUTH_TYPE_3="3" ;
	public static final String OAUTH_TYPE_4="4" ;
	public static final String OAUTH_TYPE_5="5" ;
//测试号码类型
	//默认为认证手机
	public static final String TEST_NUM_TYPE_0="0";
	//用户通过平台自己添加
	public static final String TEST_NUM_TYPE_1="1";
	
//今天消费的类型
	public static final String TODAY_CSM_VOICE="1";
	public static final String TODAY_CSM_SMS="2";
	public static final String TODAY_CSM_IM="3";
	public static final String TODAY_CSM_VOICECODE="4";
	public static final String TODAY_CSM_CLIENT="5";
	public static final String TODAY_CSM_CLOUD="6";
	public static final String TODAY_CSM_VIDEO="7";
	public static final String TODAY_CSM_TRAFFIC="8";
//余额提醒类型
	public static final String REMIND_STATUS_0="0";
	public static final String REMIND_STATUS_1="1";
//国际套餐前缀
	public static final String [] interPrefix = {"1003","1004"};
//最低消费前缀
	public static final String [] lowestCsm = {"1017"};
	
	public static final String [] dispNbr = {"1001","1002","1016"};
	
//协议用户状态   0:解除协议(账户) 1:待审核(资料) 2:审核通过(资料) 3:审核不通过(资料) 4:协议生效(账户)
	public static final String AGREEMENT_STATUS_0="0";
	public static final String AGREEMENT_STATUS_1="1";
	public static final String AGREEMENT_STATUS_2="2";
	public static final String AGREEMENT_STATUS_3="3";
	public static final String AGREEMENT_STATUS_4="4";
//用户账户类型 0云平台账户  1保障金用户
	public static final String ACCT_TYPE_0="0";
	public static final String ACCT_TYPE_1="1";
//解除保障金的去向 1:到快传账户  2：到银行卡
	public static final String [] UNFREEZE_STATUS={"1","2"};
//解除保障金到银行卡申请状态 0：成功   1：待审核  2：审核不通过
	public static final String SECURITY_APPLY_STATUS_0="0";
	public static final String SECURITY_APPLY_STATUS_1="1";
	public static final String SECURITY_APPLY_STATUS_2="2";
//是否是合约用户
	public static final int IS_CONTRACT=1;
	public static final int NOT_CONTRACT=0;
//保障金状态  1:未冻结 2:已冻结3.已转移,已提现
	public static final String SECURITY_BALANCE_STATUS_1="1";
	public static final String SECURITY_BALANCE_STATUS_2="2";
	public static final String SECURITY_BALANCE_STATUS_3="3";
	public static final String SECURITY_BALANCE_STATUS_4="4";
//保障金钱包状态  1:正常2:关闭 3:锁定
	public static final String SECURITY_STATUS_1="1";
	public static final String SECURITY_STATUS_2="2";
	public static final String SECURITY_STATUS_3="3";
//大客户标识
	public static final Integer IS_HEAVY_BUYER=1;
}
