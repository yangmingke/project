package com.ucpaas.commonservice.model.base;

import java.util.List;

import com.ucpaas.commonservice.constant.Constants;
import com.ucpaas.commonservice.constant.ErrorCode;

/**
 * 操作结果对象
 * 
 * @author luke
 * 
 */
public class ResultInfo<T> extends BaseInfo {
	private static final long serialVersionUID = -2145638187033109909L;

	/**
	 * 主键
	 */
	private String primaryKey;

	/**
	 * 受到影响的行数
	 */
	private int affectedRows;

	/**
	 * 存放单个对象
	 */
	private T data;

	/**
	 * 存放集合对象
	 */
	private List<T> listData;

	/**
	 * 初始化时间
	 */
	private long startTime = System.currentTimeMillis();

	/**
	 * 结束时间
	 */
	private long endTime = 0L;

	/**
	 * 调用方法耗时 endTime-startTime
	 */
	private long timeCount = 0L;

	public ResultInfo() {
	}

	public ResultInfo(ErrorCode errorCode) {
		this.errorCode = errorCode.getCode();
		this.errorInfo = errorCode.getMsg();
		this.actionResult=Constants.RESULT_TRUE;
	}

	public T getData() {
		return data;
	}

	/**
	 * 设置单个对象
	 * 
	 * @param data
	 */
	public void setData(T data) {
		this.data = data;
	}

	public List<T> getListData() {
		return listData;
	}

	/**
	 * 设置集合对象
	 * 
	 * @param listData
	 */
	public void setListData(List<T> listData) {
		this.listData = listData;
	}

	public ResultInfo(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public ResultInfo(String primaryKey, int affectedRows) {
		this.primaryKey = primaryKey;
		this.affectedRows = affectedRows;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public int getAffectedRows() {
		return affectedRows;
	}

	public void setAffectedRows(int affectedRows) {
		this.affectedRows = affectedRows;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public long getTimeCount() {
		this.endTime = System.currentTimeMillis();
		this.timeCount = this.endTime - this.startTime;
		return this.timeCount;
	}

	public void setTimeCount(long timeCount) {
		this.timeCount = timeCount;
	}

	/**
	 * 操作成功
	 * 
	 * @param affectedRows
	 *            影响行数
	 */
	public void setResultSuccess(int affectedRows) {
		this.affectedRows = affectedRows;
		setResultSuccess();
	}

	/**
	 * 操作成功
	 * 
	 * @param primaryKey
	 *            主键
	 * @param affectedRows
	 *            影响行数
	 */
	public void setResultSuccess(String primaryKey, int affectedRows) {
		this.primaryKey = primaryKey;
		setResultSuccess(affectedRows);
	}

	/**
	 * 操作失败
	 * 
	 * @param primaryKey
	 *            主键
	 * @param errorCode
	 *            错误码对象
	 */
	public void setResultFail(int affectedRows, ErrorCode errorCode) {
		this.affectedRows = affectedRows;
		setResultFail(errorCode);
	}

	/**
	 * 操作失败
	 * 
	 * @param primaryKey
	 *            主键
	 * @param affectedRows
	 *            影响行数
	 * @param errorCode
	 *            错误码对象
	 */
	public void setResultFail(String primaryKey, int affectedRows, ErrorCode errorCode) {
		this.primaryKey = primaryKey;
		setResultFail(affectedRows, errorCode);
	}
	
	
	

}
