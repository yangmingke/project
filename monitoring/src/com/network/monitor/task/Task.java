package com.network.monitor.task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.network.monitor.constant.TaskConstant.TaskStatus;
import com.network.monitor.model.TaskInfo;
import com.network.monitor.service.task.TaskService;
import com.network.monitor.thread.ProcessThread;
import com.network.monitor.thread.ThreadProcess;
import com.network.monitor.thread.ThreadUtils;
import com.network.monitor.util.TaskUtils;

@Component
public class Task {

	private static final Logger logger = LoggerFactory.getLogger(Task.class);
	@Autowired
	private TaskService taskService;
	@Autowired
	private ProcessThread processThread;
	/**
	 * 任务线程池大小
	 */
	private static final int task_thread_pool_size = 50;
	/**
	 * 正在执行的任务
	 */
	public static final Set<Integer> running_task_set = new CopyOnWriteArraySet<Integer>();
	
	/**
	 * 执行任务
	 */
	public void execute() {
		String time = DateTime.now().toString("【yyyy-MM-dd HH:mm:ss】");
		logger.debug("\n\n-------------------------定时任务开始{}", time);
		logger.debug("正在执行的任务：{}", running_task_set);

		List<Map<String, Object>> taskList = taskService.queryTask();
		logger.debug("查询需要执行的任务：{}", taskList);
		if (taskList.size() < 1) {
			logger.debug("没有定时任务需要执行{}", time);
			return;
		}
		Set<Integer> taskSet = new HashSet<Integer>();
		taskSet.addAll(running_task_set);
		for (Map<String, Object> map : taskList) {
			taskSet.add(Integer.valueOf(map.get("task_id").toString()));
		}

		Integer taskId;
		String dependency;
		Iterator<Map<String, Object>> it = taskList.iterator();
		Map<String, Object> map;
		while (it.hasNext()) {
			map = it.next();
			dependency = (null == map.get("dependency")?"":map.get("dependency").toString());
			if (StringUtils.isNotBlank(dependency)) {
				for (String s : dependency.split(",")) {
					taskId = Integer.valueOf(s);
					if (taskSet.contains(taskId)) {
						it.remove();// 依赖的任务正在执行或需要执行，则现在不执行
					}
				}
			}
		}

		List<TaskInfo> tList = new ArrayList<TaskInfo>();
		TaskStatus status = null;
		for (Map<String, Object> taskMap : taskList) {
			taskId = Integer.valueOf(taskMap.get("task_id").toString());
			status = TaskStatus.getInstance(Integer.parseInt(taskMap.get("status").toString()));
			if (running_task_set.contains(taskId) && status == TaskStatus.running) {
				logger.debug("任务结束【正在执行】：taskMap={}", taskMap);// 正在执行，则现在不执行
				continue;
			}
			running_task_set.add(taskId);
			taskService.updateStatus(taskId, TaskStatus.running);

			tList.add(TaskUtils.getTaskInfo(taskMap));
		}
		if (tList.size() < 1) {
			logger.debug("任务列表为空，定时任务结束{}", time);
			return;
		}

		ThreadUtils.asyncProcess(task_thread_pool_size, tList, new ThreadProcess<TaskInfo>() {
			@Override
			public void process(int count, int index, TaskInfo obj) {
				processThread.runTask(obj);
			}

			@Override
			public long getTimeout(int count, int index, TaskInfo obj) {
				if (obj.getTimeout() == null) {
					return super.getTimeout(count, index, obj);
				} else {
					return obj.getTimeout() * 60;
				}
			}
		});
		logger.debug("定时任务结束{}", time);
	}
}
