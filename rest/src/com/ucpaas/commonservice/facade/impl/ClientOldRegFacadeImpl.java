package com.ucpaas.commonservice.facade.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ucpaas.commonservice.config.Properties;
import com.ucpaas.commonservice.constant.ErrorCode;
import com.ucpaas.commonservice.facade.ClientOldRegFacade;
import com.ucpaas.commonservice.model.ClientBalanceInfo;
import com.ucpaas.commonservice.model.ClientInfo;
import com.ucpaas.commonservice.service.AllocUinService;
import com.ucpaas.commonservice.service.ClientOldService;

@Service("clientOldRegFacade")
public class ClientOldRegFacadeImpl implements ClientOldRegFacade {
	private static final Logger log = LoggerFactory.getLogger(ClientOldRegFacadeImpl.class);
			
	@Resource(name = "clientOldService")
	private ClientOldService clientOldService;
	
	@Resource(name = "allocUinService")
	private AllocUinService allocUinService;
	
	@Resource(name="properties")
	private Properties properties;
	
	
	@Override
	public Map<String, Object> clientReg(ClientInfo clientInfo, ClientBalanceInfo clientBalanceInfo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		long startTime = System.currentTimeMillis();
		try {
			
			//获取uin
			Integer uin = this.allocUinService.selectUinBySectionId(properties.getUin_section_id());
			String clientNumber = clientInfo.getClnPrefix()+StringUtils.leftPad(String.valueOf(uin), 9, "0");		
			log.info("【rest2014注册子账号】获取uin,uin={},clientNumber={}",uin,clientNumber);
			clientInfo.setUin(uin);
			clientInfo.setClientNumber(clientNumber);
			clientBalanceInfo.setUin(uin);
			clientBalanceInfo.setClientNumber(clientNumber);
			
			//2016-05-11begin
			Long myClientBalance = clientBalanceInfo.getBalance();
			//将clientbalance中余额设为0，balance通过调用计费中间件的client充值接口入库。
			if(myClientBalance > 0L){
				clientBalanceInfo.setBalance(0L);
			}
			
			//2016-05-11end
			log.info("【rest2014注册子账号】开始,clientInfo={},clientBalanceInfo={}",clientInfo,clientBalanceInfo);
			resultMap = this.clientOldService.clientReg(clientInfo, clientBalanceInfo);
			log.info("【rest2014注册子账号】结束,resultMap={},耗时timeCount={}",resultMap,(System.currentTimeMillis()-startTime));
			
			//2016-05-11begin
			//clientbalance的余额>0,则调用计费中间件的余额充值接口，20160511新增
			if(null != myClientBalance && myClientBalance > 0L){
				clientOldService.clientBalanceChargeInterface(clientInfo.getSid(), clientInfo.getAppSid(), clientNumber, String.valueOf(myClientBalance));					
			}
			//2016-05-11end
		} catch (Exception e) {
			resultMap.put("po_code", ErrorCode.C200099.getCode());
			resultMap.put("po_desc", ErrorCode.C200099.getMsg());
			log.error("【rest2014注册子账号】错误,clientInfo=" + clientInfo +",clientBalanceInfo="+clientBalanceInfo 
					+ ",resultMap=" + resultMap+",timeCount="+(System.currentTimeMillis()-startTime), e);
		}
		return resultMap;
	}

}
