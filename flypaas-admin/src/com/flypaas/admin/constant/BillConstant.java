package com.flypaas.admin.constant;

/**
 * 账单
 * 
 * @author zenglb
 */
public class BillConstant {

	/**
	 * 0：冻结，1 正常，2 欠费，3已注销<br/>
	 * 冻结
	 */
	public static final int ENABLE_FLAG_FROZEN = 0;
	/**
	 * 正常
	 */
	public static final int ENABLE_FLAG_NORMAL = 1;
	/**
	 * 欠费
	 */
	public static final int ENABLE_FLAG_ARREARS = 2;
	/**
	 * 已注销
	 */
	public static final int ENABLE_FLAG_CANCELLED = 3;

	/**
	 * 支付状态：1:未支付 2:已提交至支付网关 3:支付成功 4:支付失败
	 */
	public static final int PAY_STATUS_1 = 1;
	public static final int PAY_STATUS_2 = 2;
	public static final int PAY_STATUS_3 = 3;
	public static final int PAY_STATUS_4 = 4;
	/**
	 * 充值方式，A开头表示管理员的操作（A1:充值，A2:赠送，A3:扣费）；B开头表示开发者触发的赠送（B1:注册赠送）；<br/>
	 * 数字表示开发者的充值（1: 支付宝 6:银联……）； 配置tb_flypaas_params.param_type=recharge_type
	 */
	/**
	 * 充值
	 */
	public static final String RECHARGE_TYPE_RECHARG = "A1";
	/**
	 * 赠送
	 */
	public static final String RECHARGE_TYPE_GIFTS = "A2";
	/**
	 * 扣费
	 */
	public static final String RECHARGE_TYPE_DEDUCTION = "A3";
	/**
	 * 注册赠送
	 */
	public static final String RECHARGE_TYPE_REG = "B1";

	/**
	 * 支付宝
	 */
	public static final String RECHARGE_TYPE_ALIPAY = "1";
	/**
	 * 银联
	 */
	public static final String RECHARGE_TYPE_UNIONPAY = "6";

	/**
	 * 发票状态 0:已取消 1:待审核 2:开票中 3:已邮寄 4:审核不通过
	 */
	public static final int BILL_STATUS_0 = 0;
	public static final int BILL_STATUS_1 = 1;
	public static final int BILL_STATUS_2 = 2;
	public static final int BILL_STATUS_3 = 3;
	public static final int BILL_STATUS_4 = 4;
}
