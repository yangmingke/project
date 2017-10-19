package com.flypaas.admin.service.audit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 审核管理-语音通知审核-任务程序
 * 
 * @author zenglb
 */
@Service
public class NotifyCallAuditTaskService extends TimerTask {
	private static final Logger logger = LoggerFactory.getLogger(NotifyCallAuditTaskService.class);
	@Autowired
	private NotifyCallAuditService notifyCallAuditService;
	/**
	 * 延迟10秒开始运行
	 */
	private static final long delay = 10 * 1000;
	/**
	 * 语音通知的扫表间隔
	 */
	private static final int period = 30;
	/**
	 * 语音通知的执行的线程数
	 */
	private static final int pool_size = 100;

	/**
	 * 是否正在执行
	 */
	private static boolean isRun = false;

	@PostConstruct
	public void init() {
		String notify_call_task_run = System.getProperty("notify_call_task_run", "true");

		if (!"false".equals(notify_call_task_run)) {
			new Timer().schedule(this, delay, period * 1000);
		}
	}

	@Override
	public void run() {
		/*String time = DateTime.now().toString("【yyyy-MM-dd HH:mm:ss】");
		logger.debug("【语音通知任务】开始{}", time);
		if (isRun) {
			logger.debug("【语音通知任务】正在执行{}", time);
			return;
		}
		isRun = true;

		try {
			List<Map<String, Object>> list = notifyCallAuditService.findAllTasks();
			final int count = list.size();
			logger.debug("【语音通知任务】查询需要执行的任务：count={}", count);
			if (count == 0) {
				logger.debug("【语音通知任务】没有需要执行的任务{}", time);
				return;
			}
			notifyCallAuditService.updateRun(list);// 设置中间状态：发送中

			List<Callable<Object>> taskList = new ArrayList<Callable<Object>>();
			for (int i = 0; i < count; i++) {
				final int j = i;
				final Map<String, Object> map = list.get(j);
				taskList.add(new Callable<Object>() {
					@Override
					public Object call() throws Exception {
						Object id = map.get("id");
						try {
							logger.debug("【语音通知任务】处理开始：count={}, now={}, id={}", count, j + 1, id);
							long startTime = System.currentTimeMillis();

							notifyCallAuditService.processNotifyCall(map);
							double time = (System.currentTimeMillis() - startTime) / 1000.0;
							logger.debug("【语音通知任务】处理结束：count={}, now={}, id={}, time={}秒", count, j + 1, id, time);
						} catch (Throwable e) {
							logger.error("【语音通知任务】处理失败：count=" + count + ", now=" + (j + 1) + ", id=" + id, e);
						}
						return null;
					}
				});
			}
			ExecutorService pool = Executors.newFixedThreadPool(pool_size);
			pool.invokeAll(taskList);// 等待所有任务完成
			pool.shutdown();
		} catch (Throwable e) {
			logger.error("【语音通知任务】失败" + time, e);
		} finally {
			logger.debug("【语音通知任务】结束{}", time);
			isRun = false;
		}*/
		
	}

}
