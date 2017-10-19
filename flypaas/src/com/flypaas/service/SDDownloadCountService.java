package com.flypaas.service;

import java.util.List;
import java.util.Map;

public interface SDDownloadCountService {

	public Map<String, Object> getCount(Map<String,Object> map);
	
	public List<Map<String, Object>> getCountList();
	
	public void update(Map<String,Object> map);
	
	public List<Map<String,Object>> desc(String key);
	
	public Map<String,Object> getStatictisSdk(Map<String,Object> map);
	
	public Map<String,Object> getStatictisDemo(Map<String,Object> map);
	
	public void addStatictisSdk(Map<String,Object> map);
	
	public void addStatictisDemo(Map<String,Object> map);
	
	public void updateStatictisSdk(Map<String,Object> map);
	
	public void updateStatictisDemo(Map<String,Object> map);
}
