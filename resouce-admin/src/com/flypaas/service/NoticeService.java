package com.flypaas.service;

import com.flypaas.model.TbRsUserMsg;

public interface NoticeService{
	/**
	 * 通知用户
	 * @param conditions
	 * @return
	 */
	int createNotice(TbRsUserMsg record);

	/**
	 * 通知用户
	 * @param conditions
	 * @return
	 */
	int createNotice(String msgTitle, String msgDesc, String netSid, int msgType);

	
}
