package com.network.monitor.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

/**
 * 日志库的dao类
 * 
 * @author xiejiaan
 */
@Repository
public class LogDao extends BaseDao {

	@Override
	@Resource(name = "log_sqlSessionTemplate")
	protected void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}
