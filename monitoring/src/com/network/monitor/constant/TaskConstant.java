package com.network.monitor.constant;

/**
 * 任务常量
 * 
 * @author xiejiaan
 */
public class TaskConstant {

	/**
	 * 任务类型
	 * 
	 * @author xiejiaan
	 */
	public enum TaskType {
		/**
		 * 调用存储过程
		 */
		procedure,
		/**
		 * 生成应用详单
		 */
		app_bill,
		/**
		 * 文件上传
		 */
		file_upload,
		/**
		 * 发送审核通知
		 */
		audit_notice,
		/**
		 * 处理client详单申请
		 */
		bill_apply,
		/**
		 * 余额提醒
		 */
		balance_remind,
		/**
		 * 详单文件预警
		 */
		app_bill_warning,
		/**
		 * 录音文件合成
		 */
		record_file_process,
		/**
		 * 获取CS在线的任务
		 */
		cs_online_stat,
		/**
		 * 国际漫游扣费
		 */
		roam_fee,
		/**
		 * 向VBOSS发送余额信息
		 */
		vboss_balance,
		/**
		 * 录音文件扣费
		 */
		record_fee,
		/**
		 * 一号交易扣费
		 */
		firstpje_fee;

		public static TaskType getInstance(int value) {
			switch (value) {
			case 1:
				return procedure;
			case 2:
				return app_bill;
			case 3:
				return file_upload;
			case 4:
				return audit_notice;
			case 5:
				return bill_apply;
			case 6:
				return balance_remind;
			case 7:
				return app_bill_warning;
			case 8:
				return record_file_process;
			case 9:
				return cs_online_stat;
			case 10:
				return roam_fee;
			case 11:
				return vboss_balance;
			case 12:
				return record_fee;
			case 13:
				return firstpje_fee;
			default:
				return null;
			}
		}
	}

	/**
	 * 扫描类型
	 */
	public enum ScanType {
		/**
		 * 扫描类型：分
		 */
		minute("yyyyMMddHHmm"),
		/**
		 * 扫描类型：时
		 */
		hour("yyyyMMddHH"),
		/**
		 * 扫描类型：日
		 */
		day("yyyyMMdd"),
		/**
		 * 扫描类型：周
		 */
		week("yyyyMMdd"),
		/**
		 * 扫描类型：月
		 */
		month("yyyyMM"),
		/**
		 * 扫描类型：季
		 */
		season("yyyyMM"),
		/**
		 * 扫描类型：年
		 */
		year("yyyy");
		private String format;// 时间格式

		ScanType(String format) {
			this.format = format;
		}

		public static ScanType getInstance(int value) {
			switch (value) {
			case 1:
				return minute;
			case 2:
				return hour;
			case 3:
				return day;
			case 4:
				return week;
			case 5:
				return month;
			case 6:
				return season;
			case 7:
				return year;
			default:
				return null;
			}
		}

		public String getFormat() {
			return format;
		}
	}

	/**
	 * 任务状态
	 * 
	 * @author xiejiaan
	 */
	public enum TaskStatus {
		close(0), enabled(1), running(2), delete(3);
		private int value;

		TaskStatus(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public static TaskStatus getInstance(int value) {
			switch (value) {
			case 0:
				return close;
			case 1:
				return enabled;
			case 2:
				return running;
			case 3:
				return delete;
			default:
				return null;
			}
		}
	}

}
