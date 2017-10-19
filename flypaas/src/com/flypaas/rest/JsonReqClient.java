package com.flypaas.rest;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flypaas.entity.vo.CallbackVo;
import com.flypaas.entity.vo.ClientRestVo;
import com.flypaas.entity.vo.RestClientVo;
import com.flypaas.entity.vo.RestVoiceCodeVo;
import com.flypaas.entity.vo.VoiceNotifyVo;
import com.flypaas.entity.vo.templateSMSVo;
import com.flypaas.utils.DateUtil;
import com.flypaas.utils.EncryptUtil;
import com.flypaas.utils.JsonUtil;
import com.flypaas.utils.SysConfig;
public class JsonReqClient extends AbsRestClient {
	private Logger logger = LoggerFactory.getLogger(JsonReqClient.class);
	@Override
	public String createClient(String accountSid, String authToken, 
			String appId,String clientName,String chargeType
			,String charge,String mobile,String clientType) {
		String result = "";
		try {
			//构造请求URL内容
			String timestamp = DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_NO_SLASH);
			String url = buildUrl(accountSid, authToken, appId, timestamp,"/Clients");
			logger.info("请求client url:"+url);
			RestClientVo client=new RestClientVo();
			client.setAppId(appId);
			client.setFriendlyName(clientName);
			client.setClientType(chargeType);
			client.setCharge(charge);
			client.setMobile(mobile);
			client.setcType(clientType);
			String body = "{\"client\":"+JsonUtil.toJsonStr(client)+"}";
			logger.info("---------------------------创建子账号参数串-------------------------");
			logger.info(body);
			HttpResponse response=post("application/json",accountSid, authToken, timestamp, url, body);
			//获取响应实体信息
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
			logger.info("---------------------------返回实体信息-------------------------");
			logger.info(result);
			// 确保HTTP响应内容全部被读出或者内容流被关闭
			EntityUtils.consume(entity);
		}catch (Exception e) {
			logger.error("---------------------------创建子账号失败-------------------------");
			logger.error(e.getMessage());
		}
		return result;
	}
	
	@Override
	public String createClient(String accountSid, String authToken, String appId) {
		String result = "";
		try {
			//构造请求URL内容
			String timestamp = DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_NO_SLASH);
			String url = buildUrlByNewVersion(accountSid, authToken, appId, timestamp,"/applySDKID");
			logger.info("请求client url:"+url);
			RestClientVo client=new RestClientVo();
			client.setAppId(appId);
			String body = "{\"SDK\":"+JsonUtil.toJsonStr(client)+"}";
			logger.info("---------------------------创建子账号参数串-------------------------");
			logger.info(body);
			HttpResponse response=post("application/json",accountSid, authToken, timestamp, url, body);
			//获取响应实体信息
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
			logger.info("---------------------------返回实体信息-------------------------");
			logger.info(result);
			// 确保HTTP响应内容全部被读出或者内容流被关闭
			EntityUtils.consume(entity);
		}catch (Exception e) {
			logger.error("---------------------------创建子账号失败-------------------------");
			logger.error(e.getMessage());
		}
		return result;
	}
	
	@Override
	public String voiceCode(String accountSid, String authToken, String appId,
			String to, String verifyCode, String displayNum,String chargeType) {
		String result = "";
		try {
			// 构造请求URL内容
			String timestamp = DateUtil.dateToStr(new Date(),
					DateUtil.DATE_TIME_NO_SLASH);// 时间戳
			String url = buildUrl(accountSid, authToken, appId, timestamp,"/Calls/voiceCode");
			logger.info("请求voicecode url:"+url);
			RestVoiceCodeVo voice = new RestVoiceCodeVo();
			voice.setAppId(appId);
			voice.setVerifyCode(verifyCode);
			voice.setTo(to);
			voice.setVoiceCode(displayNum);
			voice.setFree(chargeType);
			voice.setBandWhite("1");//不校验白名单
			String body = "{\"voiceCode\":"+JsonUtil.toJsonStr(voice)+"}";
			logger.info("---------------------------语音验证码请求参数-------------------------");
			logger.info(body);
			HttpResponse response=post("application/json",accountSid, authToken, timestamp, url,body);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("---------------------------语音验证码请求失败-------------------------");
			logger.error(e.getMessage());
		}
		logger.info("---------------------------语音验证码请求返回结果-------------------------");
		logger.info(result);
		return result;
	}
	@Override
	public String templateSMS(String accountSid, String authToken,
			String appId, String templateId, String to, String param,String chargeType) {
		String result = "";
		try {
			// 构造请求URL内容
			String timestamp = DateUtil.dateToStr(new Date(),
					DateUtil.DATE_TIME_NO_SLASH);// 时间戳
			String url = buildUrl(accountSid, authToken, appId, timestamp,"/Messages/templateSMS");
			logger.info("请求smscode url:"+url);
			templateSMSVo vo = new templateSMSVo();
			vo.setAppId(appId);
			vo.setTemplateId(templateId);
			vo.setTo(to);
			vo.setParam(param);
			vo.setFree(chargeType);
			vo.setBandWhite("1");//不校验白名单
			String body = "{\"templateSMS\":"+JsonUtil.toJsonStr(vo)+"}" ;
			logger.info("---------------------------短信验证码请求参数-------------------------");
			logger.info(body);
			HttpResponse response=post("application/json",accountSid, authToken, timestamp, url,body);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
			EntityUtils.consume(entity);
		} catch (Exception e) {
			logger.info("---------------------------短信验证码请求失败！-------------------------");
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("---------------------------短信验证码返回结果-------------------------");
		logger.info(result);
		return result;
	}
	@Override
	public String callback(String accountSid, String authToken, String appId,
			String fromClient, String to, String toSerNum) {
		String result = "";
		try {
			// 构造请求URL内容
			String timestamp = DateUtil.dateToStr(new Date(),DateUtil.DATE_TIME_NO_SLASH);// 时间戳
			String url = buildUrl(accountSid, authToken, appId, timestamp,"/Calls/callBack");
			logger.info("--------------请求回拨的地址:------------"+url);
			CallbackVo vo = new CallbackVo();
			vo.setAppId(appId);
			vo.setFromClient(fromClient);
			vo.setTo(to);
			vo.setToSerNum(toSerNum);
			String body = "{\"callback\":"+JsonUtil.toJsonStr(vo)+"}";
			logger.info("------------请求回拨的参数:------------"+body);
			HttpResponse response=post("application/json",accountSid, authToken, timestamp, url,body);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("-----------回拨返回结果------------------"+result);
		return result;
	}
	@Override
	public String bandClients(String accountSid, String authToken,String appId,
			String clientNumber,String mobile,String cType) {
		String result = "";
		try {
			// 构造请求URL内容
			String timestamp = DateUtil.dateToStr(new Date(),
					DateUtil.DATE_TIME_NO_SLASH);// 时间戳
			String url = buildUrl(accountSid, authToken, appId, timestamp,"/Clients/band");
			logger.info("绑定测试号码请求rest url:"+url);
			ClientRestVo client=new ClientRestVo();
			client.setClientNumber(clientNumber);
			client.setMobile(mobile);
			client.setAppId(appId);
			client.setcType(cType);
			String body="{\"client\":"+JsonUtil.toJsonStr(client)+"}";
			logger.info("绑定测试号码请求参数:"+body);
			HttpResponse response=post("application/json",accountSid, authToken, timestamp, url,body);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("绑定测试号码返回结果:"+result);
		return result;
	}
	@Override
	public String cloudNotify(String accountSid, String authToken, String appId,
			String to, String type, String content) {
		String result = "";
		try {
			// 构造请求URL内容
			String timestamp = DateUtil.dateToStr(new Date(),
					DateUtil.DATE_TIME_NO_SLASH);// 时间戳
			String url = buildUrl(accountSid, authToken, appId, timestamp,"/Calls/voiceNotify");
			logger.info("语音通知请求url:"+url);
			VoiceNotifyVo notify=new VoiceNotifyVo();
			notify.setAppId(appId);
			notify.setTo(to);
			notify.setContent(content);
			notify.setType(type);
			String body="{\"voiceNotify\":"+JsonUtil.toJsonStr(notify)+"}";
			logger.info("语音通知请求:"+body);
			HttpResponse response=post("application/json",accountSid, authToken, timestamp, url, body);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("语音通知请求:"+result);
		return result;
	}
	
	public String buildUrl(String accountSid, String authToken, String appId,String timestamp,String prUrl) throws Exception{
		//MD5加密
		EncryptUtil encryptUtil = new EncryptUtil();
		String signature =getSignature(accountSid,authToken,timestamp,encryptUtil);
		String url = getStringBuffer().append("/").append(SysConfig.getInstance().getProperty("rest_version"))
				.append("/Accounts/").append(accountSid)
				.append(prUrl)
				.append("?sig=").append(signature).toString();
		return url;
	}
	
	public String buildUrlByNewVersion(String accountSid, String authToken, String appId,String timestamp,String prUrl) throws Exception{
		//MD5加密
		EncryptUtil encryptUtil = new EncryptUtil();
		String signature =getSignature(accountSid,authToken,timestamp,encryptUtil);
		String url = getStringBuffer().append("/").append(SysConfig.getInstance().getProperty("new_rest_version"))
				.append("/Accounts/").append(accountSid)
				.append(prUrl)
				.append("?sig=").append(signature).toString();
		return url;
	}
	
}
