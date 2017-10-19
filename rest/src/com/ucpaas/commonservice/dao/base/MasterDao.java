package com.ucpaas.commonservice.dao.base;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

/**
 * 主库的dao类
 * 
 * @author xiejiaan
 */
@Repository
public class MasterDao extends BaseDao {

	@Override
	@Resource(name = "master_sqlSessionTemplate")
	protected void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}
