package com.flypaas.dao;

import java.util.List;

import com.flypaas.entity.Client;
import com.flypaas.entity.vo.PageContainer;

public interface ClientDao {
	
	public List<Client> getTestClientBySid(String sid);
	
	public void bindMobile(Client client);
	
	public Client getNotMobileClientNum(Client client);
	
	public Client getTestClientByMobile(Client client);
	
	public void unBindClientMobile(String clientNumber);
	
	public void updateClientMobile(Client client);

	public PageContainer getClientByAppSid(PageContainer page);
	
}
