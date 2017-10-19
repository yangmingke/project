package com.flypaas.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.ClientDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.Client;
import com.flypaas.entity.vo.PageContainer;
@Repository
public class ClientDaoImpl extends MyBatisDao implements ClientDao {
	
	private static final String GETTESTCLIENTBYSID="getTestClientBySid";
	private static final String GETCLIENTBYAPPSID="getClientByAppSid";
	private static final String GETCLIENTBYAPPSIDCOUNT="getClientByAppSidCount";
	private static final String BINDMOBILE="bindMobile";
	private static final String GETNOTMOBILECLIENTNUM="getNotMobileClientNum";
	private static final String GETTESTCLIENTBYMOBILE="getTestClientByMobile";
	private static final String UNBINDCLIENTMOBILE="unBindClientMobile";
	private static final String UPDATECLIENTMOBILE="updateClientMobile";

	public List<Client> getTestClientBySid(String sid) {
		return sqlSessionTemplate.selectList(GETTESTCLIENTBYSID, sid);
	}

	@Override
	public PageContainer getClientByAppSid(PageContainer page) {
		PageContainer result = null;
		Map<String, Object> params = null;
		result = getSearchPage(GETCLIENTBYAPPSID, GETCLIENTBYAPPSIDCOUNT, page);
		params = result.getParams();
		if (null == params) {
			params = new HashMap<String, Object>();
			result.setParams(params);
		}
		if (null == params.get("total")) {
			params.put("total", 0);
		}
		return result;
	}

	public void bindMobile(Client client) {
		sqlSessionTemplate.update(BINDMOBILE, client);
	}

	public Client getNotMobileClientNum(Client client) {
		return sqlSessionTemplate.selectOne(GETNOTMOBILECLIENTNUM,client);
	}

	public Client getTestClientByMobile(Client client) {
		return sqlSessionTemplate.selectOne(GETTESTCLIENTBYMOBILE,client);
	}

	public void unBindClientMobile(String clientNumber) {
		sqlSessionTemplate.update(UNBINDCLIENTMOBILE,clientNumber);
	}

	public void updateClientMobile(Client client) {
		sqlSessionTemplate.update(UPDATECLIENTMOBILE,client);
	}

	
}
