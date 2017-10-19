package com.ucpaas.commonservice.dao.base;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

/**
 * 从库的dao类
 * 
 * @author xiejiaan
 */
@Repository
public class SlaveDao extends BaseDao {

	@Override
	@Resource(name = "slave_sqlSessionTemplate")
	protected void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}
