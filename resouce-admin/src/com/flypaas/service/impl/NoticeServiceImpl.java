package com.flypaas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flypaas.dao.TbRsUserInfoMapper;
import com.flypaas.dao.TbRsUserMsgMapper;
import com.flypaas.model.TbRsUserMsg;
import com.flypaas.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	TbRsUserMsgMapper tbRsUserMsgMapper;
	@Autowired
	public TbRsUserInfoMapper tbRsUserInfoMapper;

	@Override
	public int createNotice(TbRsUserMsg record) {
		
		return tbRsUserMsgMapper.insert(record);
	}
	
	@Override
	public int createNotice(String msgTitle, String msgDesc, String netSid, int msgType) {
		String sid = tbRsUserInfoMapper.queryManagerSid(netSid);
		TbRsUserMsg record = new TbRsUserMsg();
		record.setMsgTitle(msgTitle);
		record.setMsgDesc(msgDesc);
		record.setSid(sid);
		record.setMsgType(msgType);
		
		return createNotice(record);
	}
	

}
