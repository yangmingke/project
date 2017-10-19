package com.flypaas.dao;

import java.util.List;

import com.flypaas.entity.TestWhiteList;

public interface TestWhilteListDao {

	public TestWhiteList getBindModel(String sid);
	
	public TestWhiteList get(TestWhiteList testWhiteList);
	
	public void add(TestWhiteList testWhiteList);
	
	public void delete(TestWhiteList testWhiteList);
	
	public List<TestWhiteList> list(String sid);
	
	public void updateBindMobile(TestWhiteList testWhiteList);
	
	public void deleteBindMobile(TestWhiteList testWhiteList);
	
	public void updateAddMobileToBind(TestWhiteList testWhiteList);
}
