package com.flypaas.dao;

import java.util.List;

import com.flypaas.entity.SmsTemplate;

public interface SmsTemplateDao {

	public List<SmsTemplate> getSmsTemplateList(String sid);

	public void delete(String id);

	public void deleteByAppsid(String appSid);
	
	public void add(SmsTemplate t);

	public SmsTemplate getSmsTemplateById(String id);

	public void update(SmsTemplate smsTemplate);
}
