package com.flypaas.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.UserMsg;
import com.flypaas.entity.vo.PageContainer;
import com.flypaas.service.UserMsgService;
@Service
@Transactional
public class UserMsgServiceImpl extends DaoCenter implements UserMsgService {
	public PageContainer getUserMsgList(PageContainer page) {
		return userMsgDao.getUserMsgList(page);
	}
	public void delete(UserMsg msg) {
		userMsgDao.delete(msg);
	}
	public UserMsg getLastUserMsg(String sid) {
		return userMsgDao.getLastUserMsg(sid);
	}
	public int getCountMsg(String sid) {
		return userMsgDao.getCountMsg(sid);
	}
	public void updateHasRead(UserMsg userMsg) {
		userMsgDao.updateHasRead(userMsg);
	}
	public String getMsgDesc(long id) {
		return userMsgDao.getMsgDesc(id);
	}

}
