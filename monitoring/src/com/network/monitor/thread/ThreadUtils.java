package com.network.monitor.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 线程工具类
 * 
 * @author xiejiaan
 */
public class ThreadUtils {
	private static final Logger logger = LoggerFactory.getLogger("noErrorEmail");

	/**
	 * 同步多线程处理，等待所有处理完成后才返回
	 * 
	 * @param poolSize
	 *            线程池大小
	 * @param objList
	 *            等待处理的对象列表
	 * @param threadProcess
	 *            处理方法
	 */
	@SuppressWarnings("rawtypes")
	public static <T> void syncProcess(final int poolSize, final List<T> objList, final ThreadProcess<T> threadProcess) {
		final int count = objList.size();
		ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
		List<Future> futureList = new ArrayList<Future>();
		try {
			for (int i = 0; i < count; i++) {
				final int j = i;
				final T obj = objList.get(j);
				Future future = executorService.submit(new Runnable() {
					@Override
					public void run() {
						try {
							threadProcess.process(count, j, obj);
						} catch (Throwable e) {
							logger.error("【多线程处理】处理失败：poolSize=" + poolSize + ", count=" + count + ", j=" + j
									+ ", obj=" + obj, e);
						}
					}
				});
				futureList.add(future);
			}
			for (int i = 0; i < count; i++) {
				T obj = objList.get(i);
				long timeout = threadProcess.getTimeout(count, i, obj);
				Future future = futureList.get(i);
				try {
					if (timeout == ThreadProcess.default_timeout) {
						future.get();
					} else {
						future.get(timeout, TimeUnit.SECONDS);
					}
				} catch (Throwable e) {
					logger.error("【多线程处理】线程超时：poolSize=" + poolSize + ", count=" + count + ", i=" + i + ", obj=" + obj
							+ ", timeout=" + timeout, e);
					future.cancel(true);
				}
			}
		} catch (Throwable e) {
			logger.error("【多线程处理】失败：poolSize=" + poolSize + ", count=" + count, e);
		} finally {
			executorService.shutdown();
		}
	}

	/**
	 * 异步多线程处理，立即返回
	 * 
	 * @param poolSize
	 *            线程池大小
	 * @param objList
	 *            等待处理的对象列表
	 * @param threadProcess
	 *            处理方法
	 */
	public static <T> void asyncProcess(final int poolSize, final List<T> objList, final ThreadProcess<T> threadProcess) {
		new Thread() {
			@Override
			public void run() {
				syncProcess(poolSize, objList, threadProcess);
			}
		}.start();
	}

	public static void main(String[] args) {
		logger.debug("main开始");
		List<String> objList = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			objList.add("对象" + i);
		}

		ThreadUtils.syncProcess(5, objList, new ThreadProcess<String>() {
			@Override
			public void process(int count, int index, String obj) {
				long threadId = Thread.currentThread().getId();
				logger.debug("开始：{}\t{}\t{}", threadId, index, obj);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (index == 5) {
					index = 5 / 0;
				}
				logger.debug("\t结束：{}\t{}\t{}", threadId, index, obj);
			}

			@Override
			public long getTimeout(int count, int index, String obj) {
				if (index == 10) {
					return 2;
				}
				return super.getTimeout(count, index, obj);
			}
		});
		logger.debug("main结束");
	}

}
