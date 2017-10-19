package com.flypaas.service.impl.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flypaas.dao.TbRsUserMsgMapper;
import com.flypaas.model.TbRsUserMsg;
import com.flypaas.service.user.MessageService;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2016年12月22日 下午7:09:07
* 类说明
*/
@Service("/messageServiceImpl")
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private TbRsUserMsgMapper tbRsUserMsgMapper;
	@Override
	public int deleteByPrimaryKey(Long msgId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(TbRsUserMsg record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(TbRsUserMsg record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TbRsUserMsg selectByPrimaryKey(Long msgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(TbRsUserMsg record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(TbRsUserMsg record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TbRsUserMsg> queryAllMsg(String sid) {
		
		return tbRsUserMsgMapper.queryAllMsg(sid);
	}

	@Override
	public int updateMsgStatus(int msgId) {
		
		return tbRsUserMsgMapper.updateMsgStatus(msgId);
	}

	@Override
	public int delMsg(Map para) {
		return tbRsUserMsgMapper.delMsg(para);
	}

}
