package com.flypaas.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.CountryMobilePrefix;
import com.flypaas.service.CountryMobilePrefixService;

@Service
@Transactional
public class CountryMobilePrefixServiceImpl extends DaoCenter implements CountryMobilePrefixService{
	
	public List<CountryMobilePrefix> getList() {
		return countryMobilePrefixDao.getList();
	}
	
}
