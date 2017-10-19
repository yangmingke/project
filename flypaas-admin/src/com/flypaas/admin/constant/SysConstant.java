package com.flypaas.admin.constant;

/**
 * 系统常量
 * 
 * @author xiejiaan
 */
public class SysConstant {

	/**
	 * 项目中的金额费率，1元=1000000
	 */
	public static final String money_rate = "1000000";
	public static final int money_rate_int = 1000000;

	/**
	 * 系统应用的app_sid
	 */
	public static final String sys_app_sid = "0";
	/**
	 * 超级管理员的sid
	 */
	public static final String super_admin_sid = "d137a9184dd1b84a6eae1ff5ccbc6bc9";
	/**
	 * 超级管理员的token
	 */
	public static final String super_admin_token = "d137a9184dd1b84a6eae1ff5ccbc6bc8";

	/**
	 * 保障金金额
	 */
	public static final long SECURITY_MONEY_NO_RATE = 50000l;
	public static final long SECURITY_MONEY = SECURITY_MONEY_NO_RATE * money_rate_int;
}
