package com.flypaas.admin.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

/**
 * 统计库的dao类
 * 
 * @author xiejiaan
 */
@Repository
public class StatDao extends BaseDao {

	@Override
	@Resource(name = "stat_sqlSessionTemplate")
	protected void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}
