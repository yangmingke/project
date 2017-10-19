package com.network.monitor.constant;

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
		 * 主库的ucpaas
		 */
		master,
		/**
		 * 日志库的ucpaas
		 */
		log,
		/**
		 * 统计库的ucpaas
		 */
		stat;

		public static DbType getInstance(int value) {
			switch (value) {
			case 1:
				return master;
			case 2:
				return log;
			case 3:
				return stat;
			default:
				return null;
			}
		}
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
		tb_im_log_,
		/**
		 * sdk语音质量流水表（t_voice_quality_log_yyyyMMdd）
		 */
		t_voice_quality_log_,
		/**
		 * vps语音质量统计表（t_voice_quality_log_vps_yyyyMMdd）
		 */
		t_voice_quality_log_vps_,
		/**
		 * cb语音质量统计表（t_voice_quality_log_cb_yyyyMMdd）
		 */
		t_voice_quality_log_cb_,
		
		/**
		 * 语音验证码日志表
		 */
		tb_verify_code_log_,
		
		/**
		 * 语音通知日志表
		 */
		tb_notify_log_;
		
		
	}
}
