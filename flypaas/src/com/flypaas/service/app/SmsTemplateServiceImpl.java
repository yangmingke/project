package com.flypaas.service.app;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.SmsTemplate;
import com.flypaas.entity.vo.PageContainer;

@Service
@Transactional
public class SmsTemplateServiceImpl extends DaoCenter implements SmsTemplateService {

	public PageContainer getSmsTemplateList(PageContainer page) {
		return myBatisDao.getSearchPage("getSmsTemplateList_query", "getSmsTemplateList_count", page);
	}
	public List<SmsTemplate> getSmsTemplateList(String sid) {
		return smsTemplateDao.getSmsTemplateList(sid);
	}

	public void delete(String id) {
		smsTemplateDao.delete(id);
	}

	public void add(SmsTemplate t) {
		smsTemplateDao.add(t);
	}

	public SmsTemplate getSmsTemplateById(String id) {
		return smsTemplateDao.getSmsTemplateById(id);
	}

	public void update(SmsTemplate smsTemplate) {
		smsTemplateDao.update(smsTemplate);
	}

	public void deleteByAppsid(String appSid) {
		smsTemplateDao.deleteByAppsid(appSid);
	}

}
