package com.network.monitor.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.network.monitor.constant.DbConstant.DbType;

/**
 * 公共的dao类
 * 
 * @author xiejiaan
 */
@Repository
public class CommonDao {
	@Autowired
	private LogDao logDao;
	@Autowired
	private StatDao statDao;

	/**
	 * 根据DbType获取dao类
	 * 
	 * @param dbType
	 * @return
	 */
	public BaseDao getDao(DbType dbType) {
		switch (dbType) {
		case log:
			return logDao;
		case stat:
			return statDao;
		default:
			return null;
		}
	}

	public LogDao getLogDao() {
		return logDao;
	}

	public StatDao getStatDao() {
		return statDao;
	}

}
