package com.flypaas.service;

import java.util.List;
import java.util.Map;

import com.flypaas.entity.Client;
import com.flypaas.entity.vo.PageContainer;

public interface ClientService {
	
	public List<Client> getTestClientBySid(String sid);
	
	public Map<String,Object> getClient(Client client);

	/**
	 * 创建client
	 * @param sid
	 * @param token
	 * @param appSid
	 */
	public void create(String sid, String token, String appSid);
	
	public PageContainer getClientBySid(PageContainer page);
	
}
