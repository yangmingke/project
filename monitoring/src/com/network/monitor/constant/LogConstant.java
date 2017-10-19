package com.network.monitor.constant;

/**
 * 日志常量
 * 
 * @author xiejiaan
 */
public class LogConstant {

	/** 日志类型 */
	public enum LogType {
		/** 查看 */
		view(1),

		/** 查询 */
		query(2),

		/** 增加 */
		add(3),

		/** 修改 */
		update(4),

		/** 删除 */
		delete(5);
		private int value;

		LogType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
}
