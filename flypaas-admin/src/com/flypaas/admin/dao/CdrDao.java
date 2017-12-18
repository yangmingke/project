package com.flypaas.admin.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

/**
 * 主库的dao类
 * 
 * @author yangmingke
 */
@Repository
public class CdrDao extends BaseDao {

	@Override
	@Resource(name = "cdr_sqlSessionTemplate")
	protected void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}
