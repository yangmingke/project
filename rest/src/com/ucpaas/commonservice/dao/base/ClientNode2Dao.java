package com.ucpaas.commonservice.dao.base;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

/**
 * client节点2的dao类
 * 
 * @author xiejiaan
 */
@Repository
public class ClientNode2Dao extends BaseDao {

	@Override
	@Resource(name = "client_node_2_sqlSessionTemplate")
	protected void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}
