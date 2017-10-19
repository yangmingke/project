package com.flypaas.service.app;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.vo.PageContainer;

@Service
@Transactional
public class SmsNumServiceImpl extends DaoCenter implements SmsNumService {
	public PageContainer query(PageContainer page) {
		return myBatisDao.getSearchPage("smsNumServiceImpl_query", "smsNumServiceImpl_count", page);
	}
}
