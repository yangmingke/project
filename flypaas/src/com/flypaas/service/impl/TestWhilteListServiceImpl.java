package com.flypaas.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.Application;
import com.flypaas.entity.Client;
import com.flypaas.entity.TestWhiteList;
import com.flypaas.service.TestWhilteListService;
import com.flypaas.utils.StrUtil;
@Service
@Transactional
public class TestWhilteListServiceImpl extends DaoCenter implements TestWhilteListService {

	public TestWhiteList getBindModel(String sid) {
		return testWhilteListDao.getBindModel(sid);
	}
	public TestWhiteList get(TestWhiteList testWhiteList) {
		return testWhilteListDao.get(testWhiteList);
	}
	public void add(TestWhiteList testWhiteList) {
		//添加手机号
		testWhilteListDao.add(testWhiteList);
		//绑定client
		String sid = testWhiteList.getSid();
		String mobile = testWhiteList.getMobile();
		Application app = appDao.getTestApp(sid);
		
		Client client = new Client();
		client.setSid(sid);
		client.setAppSid(app.getAppSid());
		client = clientDao.getNotMobileClientNum(client);
		if(!StrUtil.isEmpty(client)){
			client.setMobile(mobile);
			clientDao.bindMobile(client);
		}
	}

	public void delete(TestWhiteList testWhiteList) {
		//删除手机号
		testWhilteListDao.delete(testWhiteList);
		//删除client手机号码
		String sid = testWhiteList.getSid();
		String mobile = testWhiteList.getMobile();
		Application app = appDao.getTestApp(sid);
		
		Client client = new Client();
		client.setSid(sid);
		client.setAppSid(app.getAppSid());
		client.setMobile(mobile);
		client = clientDao.getTestClientByMobile(client);
		if(!StrUtil.isEmpty(client)){
			clientDao.unBindClientMobile(client.getClientNumber());
		}
	}

	public List<TestWhiteList> list(String sid) {
		return testWhilteListDao.list(sid);
	}

	public void updateBindMobile(TestWhiteList testWhiteList,TestWhiteList old) {
		testWhilteListDao.updateBindMobile(testWhiteList);
		String sid = old.getSid();
		String mobile = old.getMobile();
		String appSid = appDao.getTestApp(sid).getAppSid();
		Client client = new Client();
		client.setSid(sid);
		client.setAppSid(appSid);
		client.setMobile(mobile);
		client = clientDao.getTestClientByMobile(client);
		if(!StrUtil.isEmpty(client)){
			client.setMobile(testWhiteList.getMobile());
			clientDao.updateClientMobile(client);
		}
	}
	public void updateAddMobileToBind(TestWhiteList testWhiteList) {
		testWhilteListDao.updateAddMobileToBind(testWhiteList);
	}
	public void resetBindMobile(TestWhiteList bind, TestWhiteList temp) {
		//删除原来绑定的手机
		testWhilteListDao.delete(bind);
		
		//重置新输入的手机号码为绑定手机
		testWhilteListDao.updateAddMobileToBind(temp);
		
		//修改client绑定的手机号码
		String appSid = appDao.getTestApp(bind.getSid()).getAppSid();
		
		Client client = new Client();
		client.setSid(bind.getSid());
		client.setAppSid(appSid);
		client.setMobile(temp.getMobile());
		
		//client取消绑定的删除手机号码
		client = clientDao.getTestClientByMobile(client);
		if(!StrUtil.isEmpty(client)){
			clientDao.unBindClientMobile(client.getClientNumber());
		}
		
		client.setMobile(bind.getMobile());
		client = clientDao.getTestClientByMobile(client);
		client.setMobile(temp.getMobile());
		if(!StrUtil.isEmpty(client)){
			clientDao.updateClientMobile(client);
		}
		
	}

}
