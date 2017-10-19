package com.flypaas.admin.constant;

/**
 * 审核常量
 * 
 * @author xiejiaan
 */
public class AuditConstant {

	/**
	 * 审核类型：资质
	 */
	public static final int AUDIT_TYPE_QUALIFICATION = 1;

	/**
	 * 审核类型：应用
	 */
	public static final int AUDIT_TYPE_APP = 2;

	/**
	 * 审核类型： 短信模板
	 */
	public static final int AUDIT_TYPE_SMS = 3;

	/**
	 * 审核类型： 协议用户审核
	 */
	public static final int AUDIT_TYPE_SECURITY = 4;

	/**
	 * 审核类型：发票审核
	 */
	public static final int AUDIT_TYPE_BILL = 5;

	/**
	 * 审核类型：保障金提现审核
	 */
	public static final int AUDIT_TYPE_SECURITY_RELIEVE_APPLYFOR = 6;
	
	/**
	 * 审核类型： 铃声审核
	 */
	public static final int AUDIT_TYPE_RING = 7;
	
	/**
	 * 审核类型：语音通知审核
	 */
	public static final int AUDIT_TYPE_NOTIFY_CALL = 8;

	/**
	 * 审核类型：号码白名单审核
	 */
	public static final int AUDIT_TYPE_SHOW_NBR_CALL = 9;
	/**
	 * 审核结果：通过
	 */
	public static final int AUDIT_STATUS_PASS = 1;

	/**
	 * 审核结果：不通过
	 */
	public static final int AUDIT_STATUS_NO_PASS = 0;

}
