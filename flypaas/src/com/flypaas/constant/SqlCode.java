package com.flypaas.constant;

/**
 * sql配置，对应tb_srv_sql.sql_code字段
 * 
 * @author xiejiaan
 */
public class SqlCode {
	/**
	 * 文件日志表前缀
	 */
	public static final String FILE_TABLE_PREFIX = "tb_srv_file_";
	/**
	 * 库名称
	 */
	public static final String FILE_DATA_PREFIX = "statistics";

	/**
	 * 查询FastDFS服务器上的文件路径
	 */
	public static final String TASK_QUERY_REMOTE_PATH = "task_query_remote_path";
	
}
