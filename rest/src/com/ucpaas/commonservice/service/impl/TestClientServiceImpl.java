package com.ucpaas.commonservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ucpaas.commonservice.dao.TestClientDao;
import com.ucpaas.commonservice.model.TestClientInfo;
import com.ucpaas.commonservice.service.TestClientService;
import com.ucpaas.commonservice.util.db.DBShardingUtil;


@Service("testClientService")
public class TestClientServiceImpl implements TestClientService {
	private static final Logger log = LoggerFactory.getLogger(TestClientServiceImpl.class);

	@Resource(name="testClientDao")
	private TestClientDao testClientDao;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public int updateByClientNumber(TestClientInfo testClientInfo) throws Exception {
		return  this.testClientDao.updateByClientNumber(testClientInfo);
	}

	@Override
	public List<TestClientInfo> getListByAppSid(String appSid) throws Exception {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("appSid", appSid);
		return this.testClientDao.selectListByMap(parameterMap);
	}

	@Override
	public TestClientInfo getByClientNumber(String clientNumber) throws Exception {
		return this.testClientDao.selectByClientNumber(clientNumber);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public int insert(TestClientInfo testClientInfo) throws Exception {
		Map<String,String> dbMap = DBShardingUtil.getMapDBNodeByClientNumber(testClientInfo.getClientNumber());		
		log.debug("【插入测试子账号】,dbMap={},testClientInfo={}",dbMap,testClientInfo);
		//设置uin
		testClientInfo.setUin(Integer.parseInt(dbMap.get("uin")));
		return this.testClientDao.insert(testClientInfo);
	}

	@Override
	public List<TestClientInfo> getListBySid(String sid) throws Exception {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("sid", sid);
		return this.testClientDao.selectListByMap(parameterMap);
	}

	@Override
	public int deleteTestClientByUin(int uin) throws Exception {
		return testClientDao.deleteByUin(uin);
	}

}
