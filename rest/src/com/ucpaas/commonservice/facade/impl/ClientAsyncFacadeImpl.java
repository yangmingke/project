package com.ucpaas.commonservice.facade.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ucpaas.commonservice.constant.Constants;
import com.ucpaas.commonservice.constant.ErrorCode;
import com.ucpaas.commonservice.facade.ClientAsyncFacade;
import com.ucpaas.commonservice.model.Attr2uinInfo;
import com.ucpaas.commonservice.model.ClientBalanceInfo;
import com.ucpaas.commonservice.model.ClientInfo;
import com.ucpaas.commonservice.model.TestClientInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;
import com.ucpaas.commonservice.service.AllocUinService;
import com.ucpaas.commonservice.service.Attr2uinInfoService;
import com.ucpaas.commonservice.service.ClientBalanceService;
import com.ucpaas.commonservice.service.ClientRegService;
import com.ucpaas.commonservice.service.ClientService;
import com.ucpaas.commonservice.service.TestClientService;

@Service("clientAsyncFacade")
public class ClientAsyncFacadeImpl implements ClientAsyncFacade {
	private static final Logger log = LoggerFactory.getLogger(ClientAsyncFacadeImpl.class);
	
	@Resource(name = "clientService")
	private ClientService clientService;
	
	@Resource(name = "clientBalanceService")
	private ClientBalanceService clientBalanceService;
	
	@Resource(name = "testClientService")
	private TestClientService testClientService;
	
	@Resource(name = "attr2uinInfoService")
	private Attr2uinInfoService attr2uinInfoService;
	
	@Resource(name = "clientRegService")
	private ClientRegService clientRegService;
	
	@Resource(name = "allocUinService")
	private AllocUinService allocUinService;
	
	
	
	@Override
	public void insertClientAndAttr(ClientInfo clientInfo) {
		ResultInfo<ClientInfo> resultInfo = new ResultInfo<ClientInfo>(ErrorCode.C100000);
		log.info("【rest2014double添加Client】开始, clientInfo={}", clientInfo);

		try {
			if(clientInfo == null){
				resultInfo.setResultFail(ErrorCode.C200001);
			}else if(StringUtils.isEmpty(clientInfo.getClientNumber())){
				resultInfo.setResultFail(ErrorCode.C131001);
			}else if(StringUtils.isEmpty(clientInfo.getAppSid())){
				resultInfo.setResultFail(ErrorCode.C121001);
			}else {
				clientInfo.setCreateDate(new Date());
				clientInfo.setUpdateDate(new Date());
				resultInfo.setAffectedRows(this.clientService.insetClientAndAttr(clientInfo));
			}

		} catch (Exception e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【rest2014double添加Client】错误,clientInfo=" + clientInfo + ",resultInfo=" + resultInfo, e);
		}
		log.info("【rest2014double添加Client】结束,logId={},result={},errorCode={},timeCount={}",resultInfo.getLogId(),resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
	}
	
	
	@Override
	public void insert(ClientBalanceInfo clientBalanceInfo) {
		ResultInfo<ClientBalanceInfo> resultInfo = new ResultInfo<ClientBalanceInfo>(ErrorCode.C100000);
		log.info("【rest2014double添加子账号余额】开始,clientBalanceInfo={}", clientBalanceInfo);

		try {
			if (clientBalanceInfo == null) {
				resultInfo.setResultFail(ErrorCode.C200001);
			}else {
				clientBalanceInfo.setCreateTime(new Date());
				clientBalanceInfo.setUpdateDate(new Date());
				resultInfo.setAffectedRows(this.clientBalanceService.insert(clientBalanceInfo));
			}

		} catch (Throwable e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【rest2014double添加子账号余额】错误,clientBalanceInfo=" + clientBalanceInfo + ",resultInfo=" + resultInfo, e);

		}
		
		log.info("【rest2014double添加子账号余额】结束,logId={},result={},errorCode={},timeCount={}",resultInfo.getLogId(),resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
	}

	@Override
	public void insert(TestClientInfo testClientInfo) {
		ResultInfo<TestClientInfo> resultInfo = new ResultInfo<TestClientInfo>(ErrorCode.C100000);
		log.info("【rest2014double添加测试子账号】开始,testClientInfo={}", testClientInfo);

		try {
			if (testClientInfo == null) {
				resultInfo.setResultFail(ErrorCode.C200001);
			} else if (testClientInfo.getClientNumber() == null) {
				resultInfo.setResultFail(ErrorCode.C131001);
			} else if (StringUtils.isEmpty(testClientInfo.getClientSid())) {
				resultInfo.setResultFail(ErrorCode.C131005);
			} else {
				testClientInfo.setCreateDate(new Date());
				testClientInfo.setUpdateDate(new Date());
				resultInfo.setAffectedRows(this.testClientService.insert(testClientInfo));
			}

		} catch (Throwable e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【rest2014double添加测试子账号】错误,testClientInfo=" + testClientInfo + ",resultInfo=" + resultInfo, e);
		}
		log.info("【rest2014double添加测试子账号】结束,logId={},result={},errorCode={},timeCount={}",resultInfo.getLogId(),resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
	}
	

	
	@Override
	public void updateByClientNumberCache(ClientInfo clientInfo) {
		ResultInfo<ClientInfo> resultInfo = new ResultInfo<ClientInfo>(ErrorCode.C100000);
		log.info("【rest2014double更新Client】开始,clientInfo={}", clientInfo);

		try {
			if (clientInfo == null) {
				resultInfo.setResultFail(ErrorCode.C200001);
			} else {
				clientInfo.setUpdateDate(new Date());
				resultInfo.setAffectedRows(this.clientService.updateByClientNumberCache(clientInfo));
			}
		} catch (Throwable e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【rest2014double更新Client】错误,clientInfo=" + clientInfo + ",resultInfo=" + resultInfo, e);
		}
		
		log.info("【rest2014double更新Client】结束,logId={},result={},errorCode={},timeCount={}",resultInfo.getLogId(),resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
	}
	
	
	@Override
	public void updateByClientNumber(ClientBalanceInfo clientBalanceInfo) {
		ResultInfo<ClientBalanceInfo> resultInfo = new ResultInfo<ClientBalanceInfo>(ErrorCode.C100000);
		log.info("【rest2014double更新子账号余额】开始,clientBalanceInfo={}", clientBalanceInfo);

		try {
			if (clientBalanceInfo == null) {
				resultInfo.setResultFail(ErrorCode.C200001);
			}else {
				clientBalanceInfo.setUpdateDate(new Date());
				resultInfo.setAffectedRows(this.clientBalanceService.updateByClientNumber(clientBalanceInfo));
			}

		} catch (Throwable e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【rest2014double更新子账号余额】错误,clientBalanceInfo=" + clientBalanceInfo + ",resultInfo=" + resultInfo, e);

		}
		log.info("【rest2014double更新子账号余额】结束,logId={},result={},errorCode={},timeCount={}",resultInfo.getLogId(),resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
	}
	
	
	@Override
	public void updateByClientNumber(TestClientInfo testClientInfo) {
		ResultInfo<TestClientInfo> resultInfo = new ResultInfo<TestClientInfo>(ErrorCode.C100000);
		log.info("【rest2014double更新测试子账号】开始,testClientInfo={}", testClientInfo);

		try {
			if (testClientInfo == null) {
				resultInfo.setResultFail(ErrorCode.C200001);
			} else {
				testClientInfo.setUpdateDate(new Date());
				resultInfo.setAffectedRows(this.testClientService.updateByClientNumber(testClientInfo));
			}

		} catch (Throwable e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【rest2014double更新测试子账号】错误,testClientInfo=" + testClientInfo + ",resultInfo=" + resultInfo, e);

		}

		log.info("【rest2014double更新测试子账号】结束,logId={},result={},errorCode={},timeCount={}",resultInfo.getLogId(),resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
	}


	@Override
	public void updateMobileCacheByClientNumber(ClientInfo clientInfo, String newMobile) {
		ResultInfo<ClientInfo> resultInfo = new ResultInfo<ClientInfo>(ErrorCode.C100000);
		log.info("【rest2014double更新Client的mobile】开始, clientInfo={},newMobile={}", clientInfo,newMobile);

		try {
			if(clientInfo == null){
				resultInfo.setResultFail(ErrorCode.C200001);
			}else if (StringUtils.isEmpty(clientInfo.getAppSid())) {
				resultInfo.setResultFail(ErrorCode.C121001);
			}else if(StringUtils.isEmpty(clientInfo.getClientNumber())){
				resultInfo.setResultFail(ErrorCode.C131001);
			}else {
				clientInfo.setUpdateDate(new Date());
				resultInfo.setAffectedRows(this.clientService.updateMobileCacheByClientNumber(clientInfo, newMobile));
			}

		} catch (Exception e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【rest2014double更新Client的mobile】错误,clientInfo=" + clientInfo +",newMobile=" + newMobile + ",resultInfo=" + resultInfo, e);
		}
		log.info("【rest2014double更新Client的mobile】结束,logId={},result={},errorCode={},timeCount={}",resultInfo.getLogId(),resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
	}


	@Override
	public void chargeClientBalanceByClientNumber(String clientNumber, String clientChargeType, Long balance) {
		ResultInfo<TestClientInfo> resultInfo = new ResultInfo<TestClientInfo>(ErrorCode.C100000);
		log.info("【rest2014double子账号充值】开始,clientNumber={},clientChargeType={},balance={}", clientNumber,clientChargeType,balance);

		try {
			if (StringUtils.isEmpty(clientNumber)) {
				resultInfo.setResultFail(ErrorCode.C131001);
			}else if(StringUtils.isEmpty(clientChargeType)){
				resultInfo.setResultFail(ErrorCode.C131006);
			}else if(balance == null){
				resultInfo.setResultFail(ErrorCode.C132001);
			}else {
				resultInfo.setAffectedRows(this.clientBalanceService.chargeClientBalanceByClientNumber(clientNumber, clientChargeType, balance));
			}
		} catch (Throwable e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【rest2014double子账号充值】错误,clientNumber=" + clientNumber +",clientChargeType="+clientChargeType+",balance="+balance + ",resultInfo=" + resultInfo, e);
		}
		log.info("【rest2014double子账号充值】结束,logId={},result={},errorCode={},timeCount={}",resultInfo.getLogId(),resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
	}


	@Override
	public void closeClientByClientNumber(ClientInfo clientInfo,ClientBalanceInfo clientBalanceInfo) {
		ResultInfo<ClientInfo> resultInfo = new ResultInfo<ClientInfo>(ErrorCode.C100000);
		log.info("【rest2014double关闭client】开始, clientInfo={},clientBalanceInfo={}", clientInfo,clientBalanceInfo);

		try {
			if(clientInfo == null){
				resultInfo.setResultFail(ErrorCode.C200001);
			}else if (StringUtils.isEmpty(clientInfo.getAppSid())) {
				resultInfo.setResultFail(ErrorCode.C121001);
			}else if(StringUtils.isEmpty(clientInfo.getClientNumber())){
				resultInfo.setResultFail(ErrorCode.C131001);
			}else if(StringUtils.isEmpty(clientBalanceInfo.getClientNumber())){
				resultInfo.setResultFail(ErrorCode.C132003);
			}else if(!clientInfo.getClientNumber().equals(clientBalanceInfo.getClientNumber())){
				resultInfo.setResultFail(ErrorCode.C131007);
			}else {
				clientInfo.setUpdateDate(new Date());
				clientBalanceInfo.setUpdateDate(new Date());
				resultInfo.setAffectedRows(this.clientService.closeClientByClientNumber(clientInfo, clientBalanceInfo));
			}

		} catch (Exception e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【rest2014double关闭client】错误,clientInfo=" + clientInfo +",clientBalanceInfo="+clientBalanceInfo + ",resultInfo=" + resultInfo, e);
		}
		log.info("【rest2014double关闭client】结束,logId={},result={},errorCode={},timeCount={}",resultInfo.getLogId(),resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
	}


	@Override
	public void copyClientReg2015(ClientInfo clientInfo, ClientBalanceInfo clientBalanceInfo) {
		ResultInfo<ClientInfo> resultInfo = new ResultInfo<ClientInfo>(ErrorCode.C100000);
		int i = 0;
		try {
			if (StringUtils.isEmpty(clientInfo.getAppSid())) {
				resultInfo.setResultFail(ErrorCode.C121001);
			}else if(StringUtils.isEmpty(clientInfo.getUserid())){
				resultInfo.setResultFail(ErrorCode.C131003);
			}else if(StringUtils.isEmpty(clientInfo.getClientNumber())){
				resultInfo.setResultFail(ErrorCode.C131001);
			}else if(null == clientInfo.getUin()){
				resultInfo.setResultFail(ErrorCode.C131004);
			}
			else{
				//判断mobile_appid是否存在反向表
				if(StringUtils.isNotBlank(clientInfo.getMobile())){
					String mobile_appid = clientInfo.getMobile()+"_"+clientInfo.getAppSid();
					Attr2uinInfo attr_mobile = this.attr2uinInfoService.getByAttr(mobile_appid, Constants.ATTR2UIN_TYPE_102);
					if(attr_mobile != null){
						//应用下存在该手机号码，返回错误码
						resultInfo.setResultFail(ErrorCode.C121003);
						return ;
					}
				}
				
				//判断userid_appid是否存在反向表
				if(StringUtils.isNotBlank(clientInfo.getUserid())){
					String userid_appid = clientInfo.getUserid()+"_"+clientInfo.getAppSid();
					Attr2uinInfo attr_userid = this.attr2uinInfoService.getByAttr(userid_appid, Constants.ATTR2UIN_TYPE_101);
					if(attr_userid != null){
						//应用下存在该userid，返回错误码
						resultInfo.setResultFail(ErrorCode.C121004);
						return ;
					}
				}
				
				log.info("【rest2014double注册子账号】开始,clientInfo={},clientBalanceInfo={}",clientInfo,clientBalanceInfo);
				i = this.clientRegService.regClientAndAttrAndBalance(clientInfo, clientBalanceInfo);
				resultInfo.setAffectedRows(i);
			}
			
		} catch (Exception e) {
			resultInfo.setResultFail(ErrorCode.C131009);
			log.error("【rest2014double注册子账号】错误,clientInfo=" + clientInfo +",clientBalanceInfo="+clientBalanceInfo + ",resultInfo=" + resultInfo, e);
			//回滚脏数据
			try {
				this.clientRegService.rollbackClient(clientInfo, clientBalanceInfo);
			} catch (Exception e1) {
				log.error("【rest2014double回滚子账号】错误！！！！！！",e1);
			}
		}
		
		log.info("【rest2014double注册子账号】结束,i={},resultInfo={},timeCount={}",i,resultInfo,resultInfo.getTimeCount());
	}
	
	
	
	
	
	
	
	
	



}
