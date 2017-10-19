package com.ucpaas.commonservice.facade.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ucpaas.commonservice.config.Properties;
import com.ucpaas.commonservice.constant.Constants;
import com.ucpaas.commonservice.constant.ErrorCode;
import com.ucpaas.commonservice.facade.ClientRegFacade;
import com.ucpaas.commonservice.model.Attr2uinInfo;
import com.ucpaas.commonservice.model.ClientBalanceInfo;
import com.ucpaas.commonservice.model.ClientInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;
import com.ucpaas.commonservice.service.AllocUinService;
import com.ucpaas.commonservice.service.Attr2uinInfoService;
import com.ucpaas.commonservice.service.ClientRegService;
import com.ucpaas.commonservice.util.TokenClient;

@Service("clientRegFacade")
public class ClientRegFacadeImpl implements ClientRegFacade {
	private static final Logger log = LoggerFactory.getLogger(ClientRegFacadeImpl.class);
	
	@Resource(name = "attr2uinInfoService")
	private Attr2uinInfoService attr2uinInfoService;
	
	@Resource(name = "clientRegService")
	private ClientRegService clientRegService;
	
	@Resource(name = "allocUinService")
	private AllocUinService allocUinService;
	
	@Resource(name = "properties")
	private Properties properties;
	


	@Override
	public ResultInfo<ClientInfo> clientReg(ClientInfo clientInfo, ClientBalanceInfo clientBalanceInfo) {
		ResultInfo<ClientInfo> resultInfo = new ResultInfo<ClientInfo>(ErrorCode.C100000);
		int i = 0;
		try {
			if (StringUtils.isEmpty(clientInfo.getAppSid())) {
				resultInfo.setResultFail(ErrorCode.C121001);
			}else if(null == clientInfo.getClnPrefix()){
				resultInfo.setResultFail(ErrorCode.C131010);
			}else{
				//判断mobile_appid是否存在反向表
				if(StringUtils.isNotBlank(clientInfo.getMobile())){
					String mobile_appid = clientInfo.getMobile()+"_"+clientInfo.getAppSid();
					Attr2uinInfo attr_mobile = this.attr2uinInfoService.getByAttr(mobile_appid, Constants.ATTR2UIN_TYPE_102);
					if(attr_mobile != null){
						//应用下存在该手机号码，返回错误码
						resultInfo.setResultFail(ErrorCode.C121003);
						log.info("【注册子账号】mobile重复,i={},resultInfo={},timeCount={}",i,resultInfo,resultInfo.getTimeCount());
						return resultInfo;
					}
				}
				
				//判断userid_appid是否存在反向表
				if(StringUtils.isNotBlank(clientInfo.getUserid())){
					String userid_appid = clientInfo.getUserid()+"_"+clientInfo.getAppSid();
					Attr2uinInfo attr_userid = this.attr2uinInfoService.getByAttr(userid_appid, Constants.ATTR2UIN_TYPE_101);
					if(attr_userid != null){
						//应用下存在该userid，返回错误码
						resultInfo.setResultFail(ErrorCode.C121004);
						log.info("【注册子账号】userid重复,i={},resultInfo={},timeCount={}",i,resultInfo,resultInfo.getTimeCount());
						return resultInfo;
					}
				}
				
				//获取uin
				Integer uin = this.allocUinService.selectUinBySectionId(properties.getUin_section_id());
				
				//clientNumber=clientNumber的5位前缀 + uin转成9位字符串(不足9位则左补零)
				String clientNumber = clientInfo.getClnPrefix()+StringUtils.leftPad(String.valueOf(uin), 9, "0");		
				
				//注册client
				clientInfo.setUin(uin);
				clientInfo.setClientNumber(clientNumber);
				
				//userid为空，则赋clientNumber的值
				if(StringUtils.isBlank(clientInfo.getUserid())){
					clientInfo.setUserid(clientNumber);
				}
				
				if(null != clientBalanceInfo){
					clientBalanceInfo.setUin(uin);
					clientBalanceInfo.setClientNumber(clientNumber);
				}

				//生成新token，2016-03-29添加
				String clientToken = TokenClient.getToken(clientInfo.getSid(), clientInfo.getUserToken(), clientInfo.getAppSid(), clientInfo.getUserid());	
				clientInfo.setClientToken(clientToken);
				
				log.info("【注册子账号信息】开始,clientInfo={},clientBalanceInfo={}",clientInfo,clientBalanceInfo);
				i = this.clientRegService.regClientAndAttrAndBalance(clientInfo, clientBalanceInfo);
				resultInfo.setAffectedRows(i);
				resultInfo.setData(clientInfo);
			}
		} catch (Exception e) {
			resultInfo.setResultFail(ErrorCode.C131009);
			log.error("【注册子账号】错误,clientInfo=" + clientInfo +",clientBalanceInfo="+clientBalanceInfo + ",resultInfo=" + resultInfo, e);
			//回滚脏数据
			try {
				if(null != clientInfo.getUin()){
					this.clientRegService.rollbackClient(clientInfo, clientBalanceInfo);
				}
				
			} catch (Exception e1) {
				log.error("【回滚子账号】错误！！！！！！",e1);
			}
		}
		
		log.info("【注册子账号信息】结束,i={},resultInfo={},timeCount={}",i,resultInfo,resultInfo.getTimeCount());
		return resultInfo;
	}



}
