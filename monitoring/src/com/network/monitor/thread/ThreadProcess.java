package com.network.monitor.thread;

/**
 * 处理方法
 * 
 * @author xiejiaan
 */
public abstract class ThreadProcess<T> {

	/**
	 * 默认超时时间（秒），默认不超时
	 */
	static final long default_timeout = -1;

	/**
	 * 处理方法
	 * 
	 * @param count
	 *            对象总数
	 * @param index
	 *            处理的对象索引，从0开始
	 * @param obj
	 *            处理的对象
	 */
	public abstract void process(int count, int index, T obj);

	/**
	 * 获取超时时间（秒），默认不超时
	 * 
	 * @param count
	 *            对象总数
	 * @param index
	 *            处理的对象索引，从0开始
	 * @param obj
	 *            处理的对象
	 * @return
	 */
	public long getTimeout(int count, int index, T obj) {
		return default_timeout;
	}

}
