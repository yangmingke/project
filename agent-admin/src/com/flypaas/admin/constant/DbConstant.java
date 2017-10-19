package com.flypaas.admin.constant;

/**
 * 数据库常量
 * 
 * @author xiejiaan
 */
public class DbConstant {

	/**
	 * 数据库类型
	 * 
	 * @author xiejiaan
	 */
	public enum DbType {
		/**
		 * 主库
		 */
		master,
		/**
		 * 统计库
		 */
		stat,
		/**
		 * 策略服务(CPS)
		 */
		cps;
	}

	/**
	 * 数据库模型
	 * 
	 * @author xiejiaan
	 */
	public enum TableSchema {
		flypaas, flypaas_statistics, statistics;
	}

	/**
	 * 表格前缀
	 * 
	 * @author xiejiaan
	 */
	public enum TablePrefix {
		/**
		 * 语音日志表（tb_bill_call_log_yyyyMMdd）
		 */
		tb_bill_call_log_,
		/**
		 * 短信日志表（tb_sms_log_yyyyMMdd）
		 */
		tb_sms_log_,
		/**
		 * 即时消息日志表（tb_im_log_yyyyMMdd）
		 */
		tb_im_log_;
	}
}
