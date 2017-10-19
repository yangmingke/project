package com.network.monitor.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.network.monitor.constant.TaskConstant.TaskStatus;
import com.network.monitor.model.TaskInfo;
import com.network.monitor.service.CommonService;
import com.network.monitor.service.task.TaskService;
import com.network.monitor.task.Task;
import com.network.monitor.util.TaskUtils;

/**
 * 任务处理线程
 * 
 * @author xiejiaan
 */
@Service
public class ProcessThread {
	private static final Logger logger = LoggerFactory.getLogger(ProcessThread.class);
	@Autowired
	private TaskService taskService;
	@Autowired
	private CommonService commonService;

	/**
	 * 执行任务
	 * 
	 * @param taskInfo
	 */
	public void runTask(TaskInfo taskInfo) {
		boolean result = false;
		try {
			do {
				logger.debug("执行任务【开始】：taskInfo={}", taskInfo);
				Long logId = taskService.insertLog(taskInfo, null);

				switch (taskInfo.getTaskType()) {
				case procedure:// 调用存储过程
					result = commonService.callProcedure(taskInfo);
					break;
				case app_bill:// 生成应用详单
//					result = billService.generate(taskInfo);
					break;
				case file_upload:// 文件上传
//					result = billService.fileUpload(taskInfo);
					break;
				case audit_notice:// 发送审核通知
//					result = noticeService.auditNotice(taskInfo);
					break;
				case bill_apply:// 处理client详单申请
//					result = billApplyService.execute(taskInfo);
					break;
				case balance_remind:// 余额提醒
//					result = noticeService.balanceRemind();
					break;
				case app_bill_warning:// 详单文件预警
//					result = billService.warning(taskInfo);
					break;
				case record_file_process:// 录音文件合成
//					result = recordFileProcessService.doProcess();
					break;
				case cs_online_stat:// 获取CS在线的任务
//					result = csOnlineService.doProcess(taskInfo);
					break;
				case roam_fee:// 国际漫游扣费
//					result = roamFeeService.doProcess(taskInfo);
					break;
				case vboss_balance:// 向VBOSS发送余额信息
//					result = vbossService.doProcess();
					break;
				case record_fee:// 录音文件扣费
//					result = recordFeeService.doProcess();
					break;
				case firstpje_fee:// 一号交易扣费
//					result = firstProjectFeeService.doProcess();
					break;
				}
				taskService.updateLog(logId, result, null);
				logger.debug("执行任务【结束】：result={}, taskInfo={}", result, taskInfo);
				if (result) {
					if (taskInfo.getExecuteNext() != null && taskInfo.getExecuteNextDate().isBeforeNow()) {
						taskService.updateExecuteNext(taskInfo);// 修改下次执行时间

						taskInfo.setExecuteNext(taskInfo.getNewExecuteNext());
						taskInfo = TaskUtils.setNewExecuteNext(taskInfo);
						if (taskInfo.getExecuteNextDate().isBeforeNow()) {
							continue;
						}
					}
				}
				break;
			} while (true);
		} catch (Throwable e) {
			logger.error("执行任务【失败】：taskInfo=" + taskInfo, e);
		} finally {
			if (result) {
				taskService.updateScanNext(taskInfo);// 修改下次扫描时间
			}
			Integer taskId = taskInfo.getTaskId();
			taskService.updateStatus(taskId, TaskStatus.enabled);
			Task.running_task_set.remove(taskId);
		}
	}

}
