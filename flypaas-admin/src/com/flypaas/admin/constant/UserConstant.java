package com.flypaas.admin.constant;

import com.flypaas.admin.util.StrUtils;

/**
 * 用户常量
 * 
 * @author xiejiaan
 */
public class UserConstant {
	/**
	 * 用户状态：注册未激活
	 */
	public static final int STATUS_0 = 0;
	/**
	 * 用户状态：邮箱已激活
	 */
	public static final int STATUS_1 = 1;
	/**
	 * 用户状态：锁定
	 */
	public static final int STATUS_5 = 5;
	/**
	 * 用户状态：关闭、删除
	 */
	public static final int STATUS_6 = 6;

	/**
	 * 认证状态：等待认证
	 */
	public static final int OAUTH_STATUS_2 = 2;
	/**
	 * 认证状态：证件已认证(正常)
	 */
	public static final int OAUTH_STATUS_3 = 3;
	/**
	 * 认证状态：认证不通过
	 */
	public static final int OAUTH_STATUS_4 = 4;

	/**
	 * 钱包状态：冻结
	 */
	public static final int WALLET_STATUS_0 = 0;
	/**
	 * 钱包状态：正常
	 */
	public static final int WALLET_STATUS_1 = 1;
	/**
	 * 钱包状态：欠费
	 */
	public static final int WALLET_STATUS_2 = 2;
	/**
	 * 钱包状态：已注销
	 */
	public static final int WALLET_STATUS_3 = 3;

	/**
	 * 合约用户钱包状态：解除合约
	 */
	public static final int SECURITY_STATUS_0 = 0;

	/**
	 * 合约用户钱包状态：待审核
	 */
	public static final int SECURITY_STATUS_1 = 1;

	/**
	 * 合约用户钱包状态：审核通过
	 */
	public static final int SECURITY_STATUS_2 = 2;

	/**
	 * 合约用户钱包状态：审核不通过
	 */
	public static final int SECURITY_STATUS_3 = 3;
	/**
	 * 合约用户钱包状态：合约生效
	 */
	public static final int SECURITY_STATUS_4 = 4;
	
	/**
	 * 个人开发者
	 */
	public static final String USER_TYPE_1 = "1";

	/**
	 * 企业开发者
	 */
	public static final String USER_TYPE_2 = "2";
	/**
	 * 系统管理员
	 */
	public static final String USER_TYPE_3 = "3";
	//系统生成sid秘钥
	public static final String KEY="ucp"+StrUtils.getRandom4() ;
	public static final String sidBaseString="ucp2014_sid"+StrUtils.getRandom4();
	public static final String tokenBaseString="ucp2014_token"+StrUtils.getRandom4();
	//默认渠道表
	public static final int CHANNEL_ID = 100000;
	public static final String DEFUALT_PASSWORD = "kc123456";
	public static final int cNum = 16;
	//系统金额换算
	public static final int RATE=1000000;
	//赠送金额
	public static final int persent=10;
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
	
	
	

}
