package com.flypaas.dao.impl;
import org.springframework.stereotype.Repository;

import com.flypaas.dao.UserLogDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.UserLog;
@Repository
public class UserLogDaoImpl extends MyBatisDao implements UserLogDao {
	private static final String ADDRESETTOKEN="addResetToken";
	public void add(UserLog userLog) {
		sqlSessionTemplate.insert(ADDRESETTOKEN, userLog);
	}

}
