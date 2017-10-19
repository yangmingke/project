package com.flypaas.service.impl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.UserLog;
import com.flypaas.service.UserLogService;
@Service
@Transactional
public class UserLogServiceImpl extends DaoCenter implements UserLogService {

	public void add(UserLog userLog) {
		userLogDao.add(userLog);
	}

}
