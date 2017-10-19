package com.flypaas.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.CountryMobilePrefixDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.CountryMobilePrefix;
@Repository
public class CountryMobilePrefixDaoImpl extends MyBatisDao implements CountryMobilePrefixDao {
	private static final String GETCOUNTRYMOBILEPREFIX="getCountryMobilePrefix" ;
	
	public List<CountryMobilePrefix> getList() {
		return sqlSessionTemplate.selectList(GETCOUNTRYMOBILEPREFIX);
	}

}
