package com.flypaas.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.flypaas.dao.PackageDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.Package;
import com.flypaas.entity.vo.PageContainer;
@Repository
public class PackageDaoImpl extends MyBatisDao implements PackageDao {
	@Autowired
	private MyBatisDao myBatisDao;
	private static final String GETPACKAGE="getPackage" ;
	private static final String GETPACKAGELIST="getPackageList";
	private static final String GETBASEPACKAGELIST="getBasePackageList";
	private static final String GETDEFAULTPCK="getDefaultPck";
	private static final String GETRATELIST="getRateList";
	private static final String GETRATELISTCOUNT="getRateListCount";
	private static final String GETPRICEBYID="getPriceById";
	private static final String GETMESPRICEBYID = "getMesPriceById";
	public Package getPackage(long id){
		return sqlSessionTemplate.selectOne(GETPACKAGE, id);
	}
	public List<Package> getPackageList(long id) {
		return sqlSessionTemplate.selectList(GETPACKAGELIST,id);
	}
	public Package getDefaultPck() {
		return sqlSessionTemplate.selectOne(GETDEFAULTPCK);
	}
	public PageContainer rateList(PageContainer page) {
		PageContainer result = null;
		Map<String, Object> params = null;
		result = myBatisDao.getSearchPage(GETRATELIST, GETRATELISTCOUNT, page);
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
	public List<Package> getBasePackageList() {
		return sqlSessionTemplate.selectList(GETBASEPACKAGELIST);
	}
	public List<Map<String, Object>> getChEnListbyParam(Map<String,String> param){
		return sqlSessionTemplate.selectList(GETRATELIST, param);
	}
	public List<Map<String, Object>> getPriceByPrefix(Map<String,String> param){
		return sqlSessionTemplate.selectList(GETPRICEBYID, param);
	}
	public Map<String, String> getMesPriceByPrefix(Map<String,String> param){
		return sqlSessionTemplate.selectOne(GETMESPRICEBYID, param);
	}
}
