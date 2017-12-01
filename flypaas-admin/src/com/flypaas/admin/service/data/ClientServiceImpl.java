package com.flypaas.admin.service.data;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.util.JsonUtils;
import com.flypaas.admin.util.api.RestUtils;
/**
 * 列表查询 client
 * 
 * @author chengxu
 */
@Service
@Transactional
public class ClientServiceImpl  implements ClientService{
	@Autowired
	private MasterDao dao;
	@Override
	public PageContainer query(Map<String, String> params) {
		// TODO Auto-generated method stub
		return dao.getSearchPage("clientTestNum.findClients", "clientTestNum.queryCount", params);
	}
	@Override
	public Map<String, Object> getClient(Map<String, String> params) {
		// TODO Auto-generated method stub
		return dao.getOneInfo("clientTestNum.findById", params);
	}
	@Override
	public PageContainer query2(Map<String, String> params) {
		// TODO Auto-generated method stub
		return dao.getSearchPage("clientTestNum.findclientBill", "clientTestNum.queryCount1", params);
	}
	@Override
	public Map<String, Object> getClientbill(Map<String, String> params) {
		// TODO Auto-generated method stub
		return dao.getOneInfo("clientTestNum.findBillById", params);
	}
	@Override
	public PageContainer query3(Map<String, String> params) {
		// TODO Auto-generated method stub
		return dao.getSearchPage("clientTestNum.findorder", "clientTestNum.queryCount2", params);
	}
	@Override
	public Map<String, Object> getChargebill(Map<String, String> params) {
		// TODO Auto-generated method stub
		return dao.getOneInfo("clientTestNum.findorderbyid", params);
		
	}
	@Override
	public PageContainer query4(Map<String, String> params) {
		// TODO Auto-generated method stub
		return dao.getSearchPage("clientTestNum.findallorder", "clientTestNum.queryCount3", params);
	}
	@Override
	public Map<String, Object> getChargeview(Map<String, String> params) {
		// TODO Auto-generated method stub
		return dao.getOneInfo("test.test1", params);
	}
	@Override
	public Map<String, Object> create(Map<String, String> param) {
		Map<String, Object> data = new HashMap<String, Object>();
		final String sid = param.get("sid");
		final String token = param.get("token");
		String appId = param.get("app_sid");
		int clientCount = Integer.parseInt(param.get("clientCount"));
		if(StringUtils.isAnyEmpty(sid,token,appId)){
			data.put("result", "fail");
			data.put("msg", "创建失败，请联系管理员");
			return data;
		}
		
		final Map<String,Object> content = new HashMap<String, Object>();
		Map<String,String> client = new HashMap<String, String>();
		client.put("appId", appId);
		client.put("clientType", "0");//0 开发者计费。默认为0. 注意：1 云平台计费(已作废)
		content.put("client", client);
		for(int i = 0;i < clientCount; i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					RestUtils.post("/Clients", JsonUtils.toJson(content),sid,token);//调用rest创建client
				}
			}).start();;
		}
		
		data.put("result", "success");
		data.put("msg", "创建成功");
		return data;
	}
	

	
	

}
