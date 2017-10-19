package com.ucpaas.commonservice.facade.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ucpaas.commonservice.config.Properties;
import com.ucpaas.commonservice.constant.Constants;
import com.ucpaas.commonservice.constant.ErrorCode;
import com.ucpaas.commonservice.facade.ClientDoubleRegFacade;
import com.ucpaas.commonservice.model.Attr2uinInfo;
import com.ucpaas.commonservice.model.ClientBalanceInfo;
import com.ucpaas.commonservice.model.ClientInfo;
import com.ucpaas.commonservice.model.TestClientInfo;
import com.ucpaas.commonservice.model.UserInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;
import com.ucpaas.commonservice.service.AllocUinService;
import com.ucpaas.commonservice.service.Attr2uinInfoService;
import com.ucpaas.commonservice.service.ClientBalanceService;
import com.ucpaas.commonservice.service.ClientOldService;
import com.ucpaas.commonservice.service.ClientRegService;
import com.ucpaas.commonservice.service.ClientService;
import com.ucpaas.commonservice.service.TestClientService;
import com.ucpaas.commonservice.service.UserService;
import com.ucpaas.commonservice.util.TokenClient;

/**
 * 解决2014client注册 调用2次问题
 * 
 * 
 * @author liulu
 */
@Service("clientDoubleRegFacade")
public class ClientDoubleRegFacadeImpl implements ClientDoubleRegFacade {
	private static final Logger log = LoggerFactory.getLogger(ClientDoubleRegFacadeImpl.class);

	@Resource(name = "clientOldService")
	private ClientOldService clientOldService;

	@Resource(name = "allocUinService")
	private AllocUinService allocUinService;

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
	
	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "properties")
	private Properties properties;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map<String, Object> clientReg(ClientInfo clientInfo, ClientBalanceInfo clientBalanceInfo) {
		Map<String, Object> resultMap = null;
		long startTime = System.currentTimeMillis();
		try {
			// 获取uin
			Integer uin = this.allocUinService.selectUinBySectionId(properties.getUin_section_id());
			// 拼接client_number
			String clientNumber = clientInfo.getClnPrefix() + StringUtils.leftPad(String.valueOf(uin), 9, "0");
			log.debug("【rest2014 double注册子账号】获取uin,uin={},clientNumber={}，appSid ={}", uin, clientNumber,
					clientInfo.getAppSid());
			clientInfo.setUin(uin);
			clientInfo.setClientNumber(clientNumber);
			clientBalanceInfo.setUin(uin);
			clientBalanceInfo.setClientNumber(clientNumber);

			// 2016-05-11begin
			Long myClientBalance = clientBalanceInfo.getBalance();
			// 将client_balance中余额设为0，balance通过调用计费中间件的client充值接口入库。
			if (myClientBalance > 0L) {
				clientBalanceInfo.setBalance(0L);
			}

			// 写入134开始 需要写clientBalance client表 存储过程返回 po_code ，
			// po_client_number
			log.info("【rest2014 double注册子账号】134入库开始,client_number = {},mobile = {},client_balance = {}",
					clientInfo.getClientNumber(), clientInfo.getMobile(), myClientBalance);
			resultMap = this.clientOldService.clientReg(clientInfo, clientBalanceInfo);
			log.info("【rest2014 double注册子账号】134入库结束,resultMap={},耗时timeCount={}", resultMap,
					(System.currentTimeMillis() - startTime));

			// 当写134返回po_code=0,则进行110数据库的插入

			if (resultMap.get("po_code") == null || 0 != ((Integer) resultMap.get("po_code"))) {
				return resultMap;
			}
			// 写110数据库
			clientInfo.setUserid(clientNumber);
			ResultInfo<ClientInfo> resultInfo = insertClientReg2015(clientInfo);

			if (resultInfo.getErrorCode() != ErrorCode.C100000.getCode()) {

				throw new Exception("【rest2014 double注册子账号】入库110库失败，（手动throw异常）errorInfo:"+resultInfo.getErrorInfo());
			}

			// client_balance的余额>0,掉ubs进行client充值，20160511新增
			if (null != myClientBalance && myClientBalance > 0L) {
				clientOldService.clientBalanceChargeInterface(clientInfo.getSid(), clientInfo.getAppSid(),
						clientNumber, String.valueOf(myClientBalance));
			}

		} catch (Exception e) {
			resultMap = new HashMap<String, Object>();
			resultMap.put("po_code", ErrorCode.C200099.getCode());
			resultMap.put("po_desc", ErrorCode.C200099.getMsg());
			try {
				this.clientOldService.rollbackClient(clientInfo);
			} catch (Exception e1) {
				log.error("【rest2014 double注册子账号】入库134 回滚失败！！！e= {}", e1);
			}
			log.error("【rest2014 double注册子账号】错误,clientInfo=" + clientInfo + ",clientBalanceInfo=" + clientBalanceInfo
					+ ",resultMap=" + resultMap + ",timeCount=" + (System.currentTimeMillis() - startTime), e);
		}
		return resultMap;
	}

	/**
	 * 134已经入库成功 110一定要入库成功 否则回滚134数据，保证134 、110数据一致性
	 * 
	 * @param clientInfo
	 * @return
	 */
	public ResultInfo<ClientInfo> insertClientReg2015(ClientInfo clientInfo) {
		ResultInfo<ClientInfo> resultInfo = new ResultInfo<ClientInfo>(ErrorCode.C100000);
		
		int i = 0;
		log.info(
				"【rest2014 double注册子账号】入库110开始,client_number = {},mobile = {},userId = {},appSid = {},clientType = {}",
				clientInfo.getClientNumber(), clientInfo.getMobile(), clientInfo.getUserid(), clientInfo.getAppSid(),
				clientInfo.getClientType());
		try {

			if (StringUtils.isEmpty(clientInfo.getAppSid())) {
				resultInfo.setResultFail(ErrorCode.C121001);
			} else if (StringUtils.isEmpty(clientInfo.getUserid())) {
				resultInfo.setResultFail(ErrorCode.C131003);
			} else if (StringUtils.isEmpty(clientInfo.getClientNumber())) {
				resultInfo.setResultFail(ErrorCode.C131001);
			} else if (null == clientInfo.getUin()) {
				resultInfo.setResultFail(ErrorCode.C131004);
			} else {
				// 判断mobile_appid是否存在反向表
				if (StringUtils.isNotBlank(clientInfo.getMobile())) {
					String mobile_appid = clientInfo.getMobile() + "_" + clientInfo.getAppSid();
					Attr2uinInfo attr_mobile = this.attr2uinInfoService.getByAttr(mobile_appid,
							Constants.ATTR2UIN_TYPE_102);
					if (attr_mobile != null) {
						// 应用下存在该手机号码，返回错误码
						resultInfo.setResultFail(ErrorCode.C121003);
						log.info("【rest2014 double注册子账号】入库110结束,【失败：手机号码重复】timeCount={}", resultInfo.getTimeCount());
						return resultInfo;
					}
				}

				// 判断userid_appid是否存在反向表 对于2014userid
				if (StringUtils.isNotBlank(clientInfo.getUserid())) {
					String userid_appid = clientInfo.getUserid() + "_" + clientInfo.getAppSid();
					Attr2uinInfo attr_userid = this.attr2uinInfoService.getByAttr(userid_appid,
							Constants.ATTR2UIN_TYPE_101);
					if (attr_userid != null) {
						// 应用下存在该userid，返回错误码
						resultInfo.setResultFail(ErrorCode.C121004);
						log.info("【rest2014 double注册子账号】入库110结束,【失败：userid重复】,timeCount={}", resultInfo.getTimeCount());
						return resultInfo;
					}
				}

				//获取userToken生成新的token ----------------
				UserInfo userInfo = userService.getBySid(clientInfo.getSid(), null, null);
				String userToken = userInfo ==null?"":userInfo.getToken();
				String clientToken = TokenClient.getToken(clientInfo.getSid(), userToken, clientInfo.getAppSid(), clientInfo.getUserid());	
				clientInfo.setClientToken(clientToken);
				//------------------end----------------------------
				i = this.clientRegService.regClientAndAttrAndBalance(clientInfo, null);
				resultInfo.setAffectedRows(i);
				// 插入测试client
				if ("0".equals(clientInfo.getClientType())) {
					testClientService.insert(new TestClientInfo(clientInfo));
				}
			}

		} catch (Exception e) {
			resultInfo.setResultFail(ErrorCode.C131009);
			log.error("【rest2014 double注册子账号】入库110错误,clientInfo={}，resultInfo = {}，e= {}", clientInfo, resultInfo, e);
			// 回滚脏数据
			try {
				this.clientRegService.rollbackClient(clientInfo, null);
			} catch (Exception e1) {
				log.error("【rest2014 double注册子账号】回滚错误！！！！！！e= {}", e1);
			}
		}

		log.info("【rest2014 double注册子账号】入库110结束,i={},client_number = {},errorCode={},errorMsg={},timeCount={}", i,
				clientInfo.getClientNumber(), resultInfo.getErrorCode(), resultInfo.getErrorInfo(),
				resultInfo.getTimeCount());
		return resultInfo;
	}

}
