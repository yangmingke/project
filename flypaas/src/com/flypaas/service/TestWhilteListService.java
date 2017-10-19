package com.flypaas.service;

import java.util.List;

import com.flypaas.entity.TestWhiteList;

public interface TestWhilteListService {

	public TestWhiteList getBindModel(String sid);
	
	public TestWhiteList get(TestWhiteList testWhiteList);
	
	public void add(TestWhiteList testWhiteList);
	
	public void delete(TestWhiteList testWhiteList);
	
	public List<TestWhiteList> list(String sid);
	
	public void updateBindMobile(TestWhiteList testWhiteList,TestWhiteList old);
	
	public void updateAddMobileToBind(TestWhiteList testWhiteList);
	
	public void resetBindMobile(TestWhiteList bind,TestWhiteList temp);
	
}
