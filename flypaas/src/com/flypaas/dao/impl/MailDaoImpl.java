package com.flypaas.dao.impl;
import org.springframework.stereotype.Repository;

import com.flypaas.dao.MailDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.MailProp;

@Repository
public class MailDaoImpl extends MyBatisDao implements MailDao {

	private static final String FIND_MAIL_BY_TYPE="getMailByType" ;
	
	public MailProp getMailModelByType(String type) {
		MailProp mail = sqlSessionTemplate.selectOne(FIND_MAIL_BY_TYPE,type);
		return mail;
	}

}
