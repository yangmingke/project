package com.flypaas.admin.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 调用程序处理对象方法
 * 
 * @author zenglb
 */
public class ProcessObject {
	private final static Logger logger = LoggerFactory.getLogger(ProcessObject.class);

	/**
	 * 执行脚本,阻塞状态
	 * 
	 * @param cmds
	 * @param callBack
	 * @return
	 */
	public final static void execute(String[] cmds, final ProcessObject callBack) {
		Process process = null;
		final CountDownLatch cd = new CountDownLatch(2);
		try {
			final Process p = Runtime.getRuntime().exec(cmds);
			process = p;
			new Thread() {
				public void run() {
					print(callBack, p.getErrorStream());
					cd.countDown();
				};
			}.start();

			new Thread() {
				public void run() {
					print(callBack, p.getInputStream());
					cd.countDown();
				};
			}.start();
			cd.await();
			int i = p.waitFor();
			if (i != 0) {
				logger.error("发生执行脚本异常 [" + i + "] :  " + Arrays.toString(cmds));
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (null != process) {
				process.destroy();
			}
		}
	}

	private final static void print(ProcessObject callBack, InputStream inputStream) {
		String line = null;
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));// 这个输入流是获取shell输出的
			boolean t1 = logger.isDebugEnabled() && null == callBack;
			while ((line = input.readLine()) != null) {
				line = line.trim();
				if (t1) {
					logger.debug(line);
				}
				if (null != callBack && callBack.processOutStreamLine(line)) {
					break;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * 返回true 中断日志 返回false 执行下一行
	 * 
	 * @param line
	 * @return
	 */
	protected boolean processOutStreamLine(String line) {
		return false;
	}
}
