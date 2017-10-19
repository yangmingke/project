package com.flypaas.admin.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.flypaas.admin.constant.DbConstant.DbType;

/**
 * 公共的dao类
 * 
 * @author xiejiaan
 */
@Repository
public class CommonDao {
	@Autowired
	private MasterDao masterDao;
	@Autowired
	private StatDao statDao;
	@Autowired
	private CpsDao cpsDao;

	/**
	 * 根据DbType获取dao类
	 * 
	 * @param dbType
	 * @return
	 */
	public BaseDao getDao(DbType dbType) {
		switch (dbType) {
		case master:
			return masterDao;
		case stat:
			return statDao;
		case cps:
			return cpsDao;
		default:
			return null;
		}
	}

	public MasterDao getMasterDao() {
		return masterDao;
	}

	public StatDao getStatDao() {
		return statDao;
	}

	public CpsDao getCpsDao() {
		return cpsDao;
	}

}
