package com.network.monitor.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;

import com.network.monitor.constant.DbConstant.DbType;
import com.network.monitor.constant.TaskConstant.ScanType;
import com.network.monitor.constant.TaskConstant.TaskType;


/**
 * 任务信息
 * 
 * @author xiejiaan
 */
public class TaskInfo {
	private Integer taskId;// 任务id
	private String taskName;// 任务名称
	private TaskType taskType;// 任务类型
	private DbType dbType;// 连接的数据库
	private String procedureName;// 存储过程名称
	private ScanType scanType;// 扫描类型
	private Integer scanPeriod;// 扫描周期
	private DateTime scanNext;// 下次扫描时间
	private String executeNext;// 下次执行时间
	private Integer timeout;// 超时时间（分钟），超时后会强制中断任务

	private String executePrev;// 上次执行时间
	private DateTime executeNextDate;// 下次执行时间
	private String newExecuteNext;// 新的executeNext

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	public DbType getDbType() {
		return dbType;
	}

	public void setDbType(DbType dbType) {
		this.dbType = dbType;
	}

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public ScanType getScanType() {
		return scanType;
	}

	public void setScanType(ScanType scanType) {
		this.scanType = scanType;
	}

	public Integer getScanPeriod() {
		return scanPeriod;
	}

	public void setScanPeriod(Integer scanPeriod) {
		this.scanPeriod = scanPeriod;
	}

	public DateTime getScanNext() {
		return scanNext;
	}

	public void setScanNext(DateTime scanNext) {
		this.scanNext = scanNext;
	}

	public String getExecuteNext() {
		return executeNext;
	}

	public void setExecuteNext(String executeNext) {
		this.executeNext = executeNext;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public String getExecutePrev() {
		return executePrev;
	}

	public void setExecutePrev(String executePrev) {
		this.executePrev = executePrev;
	}

	public DateTime getExecuteNextDate() {
		return executeNextDate;
	}

	public void setExecuteNextDate(DateTime executeNextDate) {
		this.executeNextDate = executeNextDate;
	}

	public String getNewExecuteNext() {
		return newExecuteNext;
	}

	public void setNewExecuteNext(String newExecuteNext) {
		this.newExecuteNext = newExecuteNext;
	}

}
