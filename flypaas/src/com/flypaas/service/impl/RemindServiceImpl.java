package com.flypaas.service.impl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.Remind;
import com.flypaas.service.RemindService;
@Service
@Transactional
public class RemindServiceImpl extends DaoCenter implements RemindService {
	
	public void add(Remind remind) {
		remindDao.add(remind);
	}
	public synchronized int get(String sid){
		return remindDao.get(sid);
	}
	public void update(Remind remind) {
		remindDao.update(remind);
	}
	public Remind getRemind(String sid){
		return remindDao.getRemind(sid);
	}

}
