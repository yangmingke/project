package com.flypaas.admin.util.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.flypaas.admin.util.rest.vo.RestClientVo;

public class RestClient {
	private static final Logger logger = LoggerFactory.getLogger(RestClient.class);
	private static String displayNum = null ;
	
	static AbsRestClient InstantiationRestAPI() {
			return new JsonReqClient();
	}
	/**
	 * 创建子账号
	 * @param json
	 * @param accountSid
	 * @param authToken
	 * @param appId
	 * @param clientName
	 * @param chargeType
	 * @param charge
	 * @param mobile
	 * @param clientType
	 * @return
	 */
	public static String createClient(String accountSid,String authToken,String appId,String clientName
			,String chargeType,String charge,String mobile,String clientType){
		String result = null ;
		try {
			result=InstantiationRestAPI().createClient(accountSid, authToken, appId, clientName, chargeType, charge,mobile,clientType);
		} catch (Throwable e) {
			e.printStackTrace();
			logger.error("--------------------------生成client number 失败------------------------");
			logger.error(e.getMessage());
		}
		logger.info("client："+result);
		return result ;
	}
	
	/**
	 * 语音验证码
	 * @param json
	 * @param accountSid
	 * @param authToken
	 * @param appId
	 * @param to
	 * @param verifyCode
	 * @param displayNum
	 * @return
	 */
	public static String voiceCode(String accountSid, String authToken, String appId,
			String to,String verifyCode,String chargeType) {
		String result = null ;
		try {
			result=InstantiationRestAPI().voiceCode(accountSid, authToken, appId, to, verifyCode, displayNum,chargeType);
		} catch (Throwable e) {
			e.printStackTrace();
			logger.error("--------------------------生成client number 失败------------------------");
			logger.error(e.getMessage());
		}
		return result ;
	}
	/**
	 * 短信验证码
	 * @param json
	 * @param accountSid
	 * @param authToken
	 * @param appId
	 * @param bodys
	 * @param to
	 * @param msgType
	 * @return
	 */
	public static String SMS(String accountSid, String authToken,
			String appId, String templateId, String to, String verifyCode,String chargeType) {
		String result = null ;
		try {
			result=InstantiationRestAPI().templateSMS(accountSid, authToken, appId, templateId, to, verifyCode,chargeType);
		} catch (Throwable e) {
			e.printStackTrace();
			logger.error("--------------------------短信验证码发送 失败------------------------");
			logger.error(e.getMessage());
		}
		return result ;
	}
	/**
	 * 回拨
	 * @param accountSid
	 * @param authToken
	 * @param appId
	 * @param fromClient
	 * @param to
	 * @param toSerNum
	 * @return
	 */
	public static String callback(String accountSid, String authToken, String appId,
			String fromClient, String to, String toSerNum){
		String result = null ;
		try {
			result=InstantiationRestAPI().callback(accountSid,authToken,appId,fromClient,to,toSerNum);
		} catch (Throwable e) {
			e.printStackTrace();
			logger.error("--------------------------回拨失败------------------------");
			logger.error(e.getMessage());
		}
		return result ;
	}
	/**
	 * 绑定测试号码 通知rest
	 * @param accountSid
	 * @param auth
	 * @param appId
	 * @param clientNumber
	 * @param mobile
	 * @return
	 */
	public static String bandClients(String accountSid, String auth,String appId,
			String clientNumber,String mobile){
		String result = null ;
		try {
			result=InstantiationRestAPI().bandClients(accountSid, auth, appId, clientNumber, mobile);
		} catch (Throwable e) {
			e.printStackTrace();
			logger.error("--------------------------回拨失败------------------------");
			logger.error(e.getMessage());
		}
		return result ;
	}
	
	/**
	 * @param args
	 * void
	 * main
	 */
	public static void main(String[] args) {
		
		String accountSid = "03f865fb206786a62d9a03fc7df95df2";// 主账户Id
		String authToken="00f7dc847841d3b5872772daf4b7da69";
		String appId="2f4f6928596443c19d0c2209b2223f15";
		String chargeType="0";
		String charge="0";
		String mobile="13513513512";
		String clientName="marix";
		String result = createClient(accountSid, authToken, appId, clientName, chargeType, charge,mobile,"0");
		Gson gson = new Gson();
		RestClientVo vo = gson.fromJson(result, RestClientVo.class);
		System.out.println(vo.getClientNumber());
	}
}
