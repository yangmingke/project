package com.ucpaas.commonservice.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ucpaas.commonservice.dao.base.MasterDao;
import com.ucpaas.commonservice.model.ClientBalanceInfo;
import com.ucpaas.commonservice.model.ClientInfo;

/**
 * 线上134数据库tb_ucpaas_client(老子账号表)
 * 
 *
 */

@Repository("clientOldDao")
public class ClientOldDao extends MasterDao {
	private static final Logger log = LoggerFactory.getLogger(ClientOldDao.class);

	/**
	 * 调用存储过程， 向以下表插入子账号相关信息 tb_ucpaas_client(子账号) tb_bill_client_balance(子账号余额)
	 * tb_ucpaas_client_pool(手机号码池)
	 * 
	 * 
	 * @param clientInfo
	 * @param clientBalanceInfo
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> insert(ClientInfo clientInfo, ClientBalanceInfo clientBalanceInfo) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pi_client_sid", clientInfo.getClientSid());
		paramMap.put("pi_app_sid", clientInfo.getAppSid());
		paramMap.put("pi_friendly_name", clientInfo.getFriendlyName());
		paramMap.put("pi_client_pwd", clientInfo.getClientPwd());
		paramMap.put("pi_client_token", clientInfo.getClientToken());

		paramMap.put("pi_status", clientInfo.getStatus());
		paramMap.put("pi_sid", clientInfo.getSid());
		paramMap.put("pi_charge", clientInfo.getCharge());
		paramMap.put("pi_chargetype", clientInfo.getChargeType());
		paramMap.put("pi_mobile", clientInfo.getMobile());

		paramMap.put("pi_client_type", clientInfo.getClientType());
		paramMap.put("pi_balance", clientBalanceInfo.getBalance());
		paramMap.put("pi_app_prefix", clientInfo.getClnPrefix());
		paramMap.put("pi_is_fee", clientInfo.getIsFee());
		paramMap.put("pi_app_uin", clientInfo.getUin());

		this.selectOne("ClientOldMapper.call_create_clientandbalance", paramMap);
		log.debug("【rest2014注册子账号】调存储过程结束,paramMap={}", paramMap);
		return paramMap;
	}

	public int deletePoolByAppAndMobile(String string, Map<String, String> param) {

		return this.delete("ClientOldMapper.deletePoolByAppAndMobile", param);
	}

	public int deleteBalanceByClientNumber(String clientNumber) {

		return this.delete("ClientOldMapper.deleteBalanceByClientNumber", clientNumber);
	}

	public int deleteClientByClientNumber(String clientNumber) {

		return this.delete("ClientOldMapper.deleteClientByClientNumber", clientNumber);
	}

}
