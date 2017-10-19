package com.flypaas.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.SmsTemplateDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.SmsTemplate;

@Repository
public class SmsTemplateDaoImpl extends MyBatisDao implements SmsTemplateDao {

	private static final String GETSMSTEMPLATELIST = "getSmsTemplateList";
	private static final String DELSMSTEMPLATE = "delSmsTemplate";
	private static final String ADDSMSTEMPLATE = "addSmsTemplate";
	private static final String GETSMSTEMPLATE = "getSmsTemplate";
	private static final String UPDATESMSTEMPLATE = "modifySmsTemplate";
	private static final String DELETEBYAPPSID="delSmsTemplateByAppsid";

	public List<SmsTemplate> getSmsTemplateList(String sid) {
		return sqlSessionTemplate.selectList(GETSMSTEMPLATELIST, sid);
	}

	public void delete(String id) {
		sqlSessionTemplate.update(DELSMSTEMPLATE, id);
	}

	public void add(SmsTemplate t) {
		sqlSessionTemplate.insert(ADDSMSTEMPLATE, t);
	}

	public SmsTemplate getSmsTemplateById(String id) {
		return sqlSessionTemplate.selectOne(GETSMSTEMPLATE, id);
	}

	public void update(SmsTemplate smsTemplate) {
		sqlSessionTemplate.update(UPDATESMSTEMPLATE, smsTemplate);
	}

	public void deleteByAppsid(String appSid) {
		sqlSessionTemplate.update(DELETEBYAPPSID, appSid);
	}

}
