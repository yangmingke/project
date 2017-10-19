package com.flypaas.admin.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

/**
 * 策略服务(CPS)的dao类
 * 
 * @author xiejiaan
 */
@Repository
public class CpsDao extends BaseDao {

	@Override
	@Resource(name = "cps_sqlSessionTemplate")
	protected void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}
