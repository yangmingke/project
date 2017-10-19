package com.flypaas.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.Client;
import com.flypaas.entity.vo.PageContainer;
import com.flypaas.rest.CreateAppClientThread;
import com.flypaas.service.ClientService;
import com.flypaas.utils.ThreadPool;
@Service
@Transactional
public class ClientServiceImpl extends DaoCenter implements ClientService {
	
	public List<Client> getTestClientBySid(String sid) {
		return clientDao.getTestClientBySid(sid);
	}
	
	@Override
	public PageContainer getClientBySid(PageContainer page) {
		return clientDao.getClientByAppSid(page);
	}

	public Map<String,Object> getClient(Client client) {
		return myBatisDao.getOneInfo("getClient", client);
	}

	@Override
	public void create(String sid, String token, String appSid) {
		CreateAppClientThread thread = new CreateAppClientThread(sid, token, appSid);
		ThreadPool.excute(thread);
	}

}
