package com.flypaas.service.app;

import com.flypaas.entity.SmsTemplate;
import com.flypaas.entity.vo.PageContainer;

public interface SmsTemplateService {

	public PageContainer getSmsTemplateList(PageContainer page);

	public void delete(String id);
	
	public void deleteByAppsid(String appSid);

	public void add(SmsTemplate t);

	public SmsTemplate getSmsTemplateById(String id);

	public void update(SmsTemplate smsTemplate);
	
}
