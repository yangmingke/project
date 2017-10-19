package com.ucpaas.commonservice.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ucpaas.commonservice.dao.ClientBalanceDao;
import com.ucpaas.commonservice.model.ClientBalanceInfo;
import com.ucpaas.commonservice.service.Attr2uinInfoService;
import com.ucpaas.commonservice.service.ClientBalanceService;
import com.ucpaas.commonservice.util.db.DBShardingUtil;


@Service("clientBalanceService")
public class ClientBalanceServiceImpl implements ClientBalanceService {
	private static final Logger log = LoggerFactory.getLogger(ClientBalanceServiceImpl.class);

	@Resource(name = "clientBalanceDao")
	private ClientBalanceDao clientBalanceDao;
	
	@Resource(name = "attr2uinInfoService")
	private Attr2uinInfoService attr2uinInfoService;
	
	@Override
	public ClientBalanceInfo getByClientNumber(String clientNumber) throws Exception {
		String logId = UUID.randomUUID().toString();
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		String[] dbNodeArr = DBShardingUtil.getDBNodeByClientNumber(clientNumber);
		parameterMap.put("uin_mod", dbNodeArr[2]);
		parameterMap.put("clientNumber", clientNumber);
		log.debug("【子账号余额】开始,logId={}, clientNumber={},dbNodeArr={},parameterMap={}", logId, clientNumber,dbNodeArr,parameterMap);
		return this.clientBalanceDao.selectByClientNumber(parameterMap, dbNodeArr[1]);
	}
	
	
	
	
	public int updateByClientNumber(ClientBalanceInfo clientBalanceInfo) throws Exception {
		String[] dbNodeArr = DBShardingUtil.getDBNodeByClientNumber(clientBalanceInfo.getClientNumber());
		clientBalanceInfo.setUin_mod(dbNodeArr[2]);
		log.debug("【更新子账号余额】开始,dbNodeArr={},clientBalanceInfo={}",dbNodeArr, clientBalanceInfo);
		return this.clientBalanceDao.updateByClientNumber(clientBalanceInfo, dbNodeArr[1]);
	}




	@Override
	public int updateByUin(ClientBalanceInfo clientBalanceInfo) throws Exception {
		Map<String, String> dbMap = DBShardingUtil.getDBNodeByUin(clientBalanceInfo.getUin());
		clientBalanceInfo.setUin_mod(dbMap.get("uin_mod"));
		log.debug("clientBalanceInfo={},dbMap={}", clientBalanceInfo, dbMap);
		return this.clientBalanceDao.updateByUin(clientBalanceInfo, dbMap.get("db_node"));
	}



	@Override
	public int insert(ClientBalanceInfo clientBalanceInfo) throws Exception {
		Map<String,String> dbMap = DBShardingUtil.getMapDBNodeByClientNumber(clientBalanceInfo.getClientNumber());	
		log.debug("【插入子账号余额】,dbMap={},clientBalanceInfo={}",dbMap,clientBalanceInfo);
		//设置表后缀
		clientBalanceInfo.setUin_mod(dbMap.get("uin_mod"));
		//设置uin
		clientBalanceInfo.setUin(Integer.parseInt(dbMap.get("uin")));
		return this.clientBalanceDao.insert(clientBalanceInfo, dbMap.get("db_node"));
	}



	/**
	 * chargeType		0 充值；1 回收。
	 */
	@Override
	public int chargeClientBalanceByClientNumber(String clientNumber, String chargeType, Long balance) throws Exception {
		log.debug("【子账号余额充值】参数,clientNumber={},chargeType={},balance={}",clientNumber, chargeType,balance);
		String[] dbNodeArr = DBShardingUtil.getDBNodeByClientNumber(clientNumber);
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("clientNumber", clientNumber);
		parameterMap.put("chargeType", chargeType);
		parameterMap.put("balance", balance);
		parameterMap.put("updateDate", new Date());
		parameterMap.put("uin_mod", dbNodeArr[2]);
		
		log.debug("【子账号余额充值】开始,dbNodeArr={},parameterMap={}",dbNodeArr, parameterMap);
		return this.clientBalanceDao.chargeClientBalanceByClientNumber(parameterMap, dbNodeArr[1]);
	}




	@Override
	public int deleteByUin(ClientBalanceInfo clientBalanceInfo) throws Exception {
		Map<String, String> dbMap = DBShardingUtil.getDBNodeByUin(clientBalanceInfo.getUin());
		clientBalanceInfo.setUin_mod(dbMap.get("uin_mod"));
		return this.clientBalanceDao.deleteByUin(clientBalanceInfo, dbMap.get("db_node"));
	}




}
