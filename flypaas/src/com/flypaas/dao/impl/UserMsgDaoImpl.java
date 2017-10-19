package com.flypaas.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.UserMsgDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.UserMsg;
import com.flypaas.entity.vo.PageContainer;
@Repository
public class UserMsgDaoImpl extends MyBatisDao implements UserMsgDao {
	private static final String GETUSERMSGBYSID="getUserMsgBySid";
	private static final String DELUSERMSG="delUserMsg";
	private static final String GETLASTUSERMSG="getLastUserMsg";
	private static final String GETCOUNTMSG="getCountMsg";
	private static final String UPDATEHASREAD="updateHasRead";
	private static final String GETMSGDESC="getMsgDesc";
	private static final String GETNOTICECOUNT="getNoticeCount";
	public PageContainer getUserMsgList(PageContainer page) {
		PageContainer result = null;
		Map<String, Object> params = null;
		result = getSearchPage(GETUSERMSGBYSID, GETNOTICECOUNT, page);
		params = result.getParams();
		if (null == params) {
			params = new HashMap<String, Object>();
			result.setParams(params);
		}
		if (null == params.get("total")) {
			params.put("total", 0);
		}
		return result;
	}
	public void delete(UserMsg msg) {
		sqlSessionTemplate.delete(DELUSERMSG, msg);
	}
	public UserMsg getLastUserMsg(String sid) {
		return sqlSessionTemplate.selectOne(GETLASTUSERMSG, sid);
	}
	public int getCountMsg(String sid){
		return sqlSessionTemplate.selectOne(GETCOUNTMSG, sid);
	}
	public void updateHasRead(UserMsg msg) {
		sqlSessionTemplate.update(UPDATEHASREAD, msg);
	}
	public String getMsgDesc(long id) {
		return sqlSessionTemplate.selectOne(GETMSGDESC, id);
	}

}
