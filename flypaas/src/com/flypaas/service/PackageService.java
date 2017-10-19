package com.flypaas.service;

import java.util.List;
import java.util.Map;

import com.flypaas.entity.Package;
import com.flypaas.entity.vo.PageContainer;

public interface PackageService {
	public Package getPackage(long id);
	public List<Package> getPackageList(long pckId);
	public List<Package> getBasePackageList();
	public Package getDefaultPck();
	public PageContainer rateList(PageContainer page);
	public List<Map<String, Object>> getChEnListbyParam(Map<String,String> param);
	public List<Map<String, Object>> getPriceByPrefix(Map<String,String> param);
	public Map<String, String> getMesPriceByPrefix(Map<String,String> param);
}
