package com.ucpaas.commonservice.constant;

/**
 * 错误码枚举类
 * 
 * @author luke
 * 
 */
public enum ErrorCode {
	/** 操作成功 */
	C100000(100000, "ok"),
	// ======================用户begin=============================
	/** 主账号sid为空 */
	C111001(111001, "主账号sid为空"),
	C111002(111002, "主账号密码为空"),
	C111003(111003, "主账号邮箱或手机号为空"),
	C111004(111004,"主账号信息不存在"),

	// ======================用户余额begin==========================

	C112001(112001, "用户余额"),

	// ======================app应用begin=============================
	/** appSid为空 */
	C121001(121001, "应用appSid为空"),
	/** 应用子账号总数clientCount为空 */
	C121002(121002, "应用clientCount为空"),
	
	/** 应用已绑定该mobile */
	C121003(121003, "应用已绑定该mobile"),
	/** 应用已绑定该userID */
	C121004(121004, "应用已绑定该userID"),
	C121005(121005, "应用信息不存在"),
	

	// ======================appBalance应用余额begin===================
	C122001(122001, "应用余额"),
	// ======================client子账号begin=========================
	/** 子账号clientNumber为空 */
	C131001(131001, "子账号clientNumber为空"),
	/** 子账号mobile为空 */
	C131002(131002, "子账号mobile为空"),
	/** 子账号userId为空 */
	C131003(131003, "子账号userId为空"),
	/** uin为空 */
	C131004(131004, "uin为空"),
	/** clientSid为空 */
	C131005(131005, "clientSid为空"),
	/** client充值类型为空 */
	C131006(131006, "client充值类型为空"),
	
	/** client的clientnumber和balance的clientnumber不一致 */
	C131007(131007, "client的clientnumber和balance的clientnumber不一致"),
	
	/** client的uin和balance的uin不一致 */
	C131008(131008, "client的uin和balance的uin不一致"),
	
	C131009(131009, "注册client失败"),
	
	/** clientNumber前缀为空 */
	C131010(131010, "clientNumber前缀为空"),
	// ======================clientbalance子账号余额begin===============
	/**子账号余额为空*/
	C132001(132001, "子账号余额为空"),
	
	/** attr为空 */
	C132002(132002, "attr为空"),
	
	/** clientbalance的clientnumber为空 */
	C132003(132003, "clientbalance的clientnumber为空"),
	
	/**clientbalance的uin为空*/
	C132004(132004, "clientbalance的uin为空"),
	
	C132005(132005, "client信息不存在"),
	
	

	// ======================公共部分===================================
	/** 系统内部错误 */
	C200099(200099, "系统内部错误"),
	/** 对象为空 */
	C200001(200001, "对象为空"),

	/** 暂无数据 */
	C200003(200003, "暂无数据"),

	/** 分页查询start为空 */
	C200004(200004, "分页查询start为空"),

	/** 分页查询pageSize为空 */
	C200005(200005, "分页查询pageSize为空"),
	
	//ip鉴权失败
	C200006(200006,"ip鉴权失败"),

	// demo
	C999999(999999, "服务返回码");
	

	/** 错误码 */
	Integer code;

	/** 错误信息 */
	String msg;

	ErrorCode(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
