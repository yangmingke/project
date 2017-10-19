package com.flypaas.admin.constant;

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
	public static final String USER_TYOE_1 = "1";

	/**
	 * 企业开发者
	 */
	public static final String USER_TYOE_2 = "2";
	/**
	 * 系统管理员
	 */
	public static final String USER_TYOE_3 = "3";
	

}
