package com.flypaas.admin.service.data;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.model.PageContainer;
/**
 * 列表查询 client
 * 
 * @author chengxu
 */
@Service
@Transactional
public class ClientServiceImpl  implements ClientService{
	private Logger logger = LoggerFactory.getLogger(getClass());
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
	

	
	

}
