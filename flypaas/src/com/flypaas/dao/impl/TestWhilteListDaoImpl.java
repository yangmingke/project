package com.flypaas.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.TestWhilteListDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.TestWhiteList;
@Repository
public class TestWhilteListDaoImpl  extends MyBatisDao implements TestWhilteListDao {
	
	private static final String add="addTestWhilte";
	private static final String getBindModel="getBindModel";
	private static final String delete="deleteTestWhilte";
	private static final String list="listTestWhilte";
	private static final String updateBindMobile="updateBindMobile";
	private static final String deleteBindMobile="deleteBindMobile";
	private static final String getModel="oneTestWhilte";
	private static final String updateAddMobileToBind="updateAddMobileToBind";

	public void add(TestWhiteList testWhiteList) {
		sqlSessionTemplate.insert(add, testWhiteList);
	}
	
	public TestWhiteList get(TestWhiteList testWhiteList) {
		return sqlSessionTemplate.selectOne(getModel, testWhiteList);
	}

	public void delete(TestWhiteList testWhiteList) {
		sqlSessionTemplate.update(delete, testWhiteList);
	}

	public List<TestWhiteList> list(String sid) {
		return sqlSessionTemplate.selectList(list, sid);
	}

	public TestWhiteList getBindModel(String sid) {
		return sqlSessionTemplate.selectOne(getBindModel,sid);
	}
	
	public void updateBindMobile(TestWhiteList testWhiteList){
		sqlSessionTemplate.update(updateBindMobile, testWhiteList);
	}

	public void deleteBindMobile(TestWhiteList testWhiteList) {
		sqlSessionTemplate.update(deleteBindMobile, testWhiteList);
	}

	public void updateAddMobileToBind(TestWhiteList testWhiteList) {
		sqlSessionTemplate.update(updateAddMobileToBind, testWhiteList);
	}

}
