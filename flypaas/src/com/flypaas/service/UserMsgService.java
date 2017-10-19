package com.flypaas.service;

import com.flypaas.entity.UserMsg;
import com.flypaas.entity.vo.PageContainer;

public interface UserMsgService {
	public PageContainer getUserMsgList(PageContainer page);
	
	public void delete(UserMsg msg);
	
	public UserMsg getLastUserMsg(String sid);
	
	public int getCountMsg(String sid);
	
	public void updateHasRead(UserMsg userMsg);
	
	public String getMsgDesc(long id);
}
