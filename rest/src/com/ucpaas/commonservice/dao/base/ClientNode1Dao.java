package com.ucpaas.commonservice.dao.base;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

/**
 * client节点1的dao类
 * 
 * @author xiejiaan
 */
@Repository
public class ClientNode1Dao extends BaseDao {

	@Override
	@Resource(name = "client_node_1_sqlSessionTemplate")
	protected void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}
