package com.flypaas.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.Package;
import com.flypaas.entity.TariffChEn;
import com.flypaas.entity.vo.PageContainer;
import com.flypaas.service.PackageService;
@Service
@Transactional
public class PackageServiceImpl extends DaoCenter implements PackageService {
	
	public Package getPackage(long id) {
		return packageDao.getPackage(id);
	}
	public List<Package> getPackageList(long pckId) {
		return packageDao.getPackageList(pckId);
	}
	public Package getDefaultPck() {
		return packageDao.getDefaultPck();
	}
	public PageContainer rateList(PageContainer page) {
		return packageDao.rateList(page);
	}
	public List<Package> getBasePackageList() {
		return packageDao.getBasePackageList();
	}
	public List<Map<String, Object>> getChEnListbyParam(Map<String,String> param){
		return packageDao.getChEnListbyParam(param);
	}
	public List<Map<String, Object>> getPriceByPrefix(Map<String,String> param){
		return packageDao.getPriceByPrefix(param);
	}
	public Map<String, String> getMesPriceByPrefix(Map<String,String> param){
		return packageDao.getMesPriceByPrefix(param);
	}

}
