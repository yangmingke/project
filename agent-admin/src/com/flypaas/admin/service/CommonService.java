package com.flypaas.admin.service;

import java.util.List;
import java.util.Map;

import com.flypaas.admin.constant.DbConstant.DbType;
import com.flypaas.admin.constant.DbConstant.TablePrefix;
import com.flypaas.admin.constant.DbConstant.TableSchema;

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
	 * @param dbType
	 *            数据库类型
	 * @param tableSchema
	 *            数据库模型
	 * @param tableName
	 *            表名
	 * @return
	 */
	boolean hasTable(DbType dbType, TableSchema tableSchema, String tableName);

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
	 * 加载城市
	 * 
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> loadCityByProvinceId(Map<String, Object> params);

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
	 * ID生成器
	 * @return
	 */
	public Long generatedGlobalId();
}
