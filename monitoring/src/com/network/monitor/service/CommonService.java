package com.network.monitor.service;

import java.util.List;
import java.util.Map;

import com.network.monitor.constant.DbConstant.TablePrefix;
import com.network.monitor.model.TaskInfo;


/**
 * 公共业务
 * 
 * @author xiejiaan
 */
public interface CommonService {

	/**
	 * 用户登录
	 * 
	 * @param username
	 * @param password
	 *            未加密字符串
	 * @return
	 */
	Map<String, Object> login(String username, String password);

	/**
	 * 数据库中是否含有此表
	 * 
	 * @param table_name
	 * @param table_schema
	 * @return
	 */
	boolean hasTable(String table_name, String table_schema);

	/**
	 * 查询远程文件是否存在
	 * 
	 * @param table
	 * @param localPath
	 * @return
	 */
	String queryRemotePath(String table, String localPath);

	/**
	 * 业务监控
	 * 
	 * @return
	 */
	Map<String, Object> monitor();
	
	/**
	 * 查询在时间范围内存在的表格
	 * 
	 * @param tablePrefix
	 *            查询表格前缀
	 * @param startDate
	 *            开始时间，格式：yyyy-MM-dd或者yyyy-MM-dd HH:mm:ss
	 * @param endDate
	 *            结束时间，格式：yyyy-MM-dd或者yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public List<String> getExistTable(TablePrefix tablePrefix, String startDate, String endDate);
	
	
	/**
	 * @todo 获取下载excel数据
	 * @return List<Map<String,Object>>
	 * @params 
	 */
	public List<Map<String, Object>> getExcelData(Map<String, Object> map);
	
	/**
	 * 调用存储过程
	 * 
	 * @param taskInfo
	 * @return 是否成功
	 */
	boolean callProcedure(TaskInfo taskInfo);
}
