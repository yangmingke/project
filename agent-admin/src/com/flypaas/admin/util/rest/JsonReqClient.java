package com.flypaas.admin.util.rest;

import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flypaas.admin.util.ConfigUtils;
import com.flypaas.admin.util.rest.utils.DateUtil;
import com.flypaas.admin.util.rest.utils.EncryptUtil;
import com.flypaas.admin.util.rest.utils.JsonUtil;
import com.flypaas.admin.util.rest.vo.CallbackVo;
import com.flypaas.admin.util.rest.vo.ClientRestVo;
import com.flypaas.admin.util.rest.vo.RestClientVo;
import com.flypaas.admin.util.rest.vo.RestVoiceCodeVo;
import com.flypaas.admin.util.rest.vo.templateSMSVo;

@SuppressWarnings("all")
public class JsonReqClient extends AbsRestClient {
	private Logger logger = LoggerFactory.getLogger(JsonReqClient.class);

	@Override
	public String createClient(String accountSid, String authToken, String appId, String clientName, String chargeType,
			String charge, String mobile, String clientType) {
		String result = "";
		DefaultHttpClient httpclient = getDefaultHttpClient();
		try {
			// MD5加密
			EncryptUtil encryptUtil = new EncryptUtil();
			// 构造请求URL内容
			String timestamp = DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_NO_SLASH);
			String signature = getSignature(accountSid, authToken, timestamp, encryptUtil);
			String url = getStringBuffer().append("/" + getRestVersion() + "/Accounts/").append(accountSid)
					.append("/Clients").append("?sig=").append(signature).toString();
			logger.info("请求client url:" + url);
			RestClientVo client = new RestClientVo();
			client.setAppId(appId);
			client.setFriendlyName(clientName);
			client.setClientType(chargeType);
			client.setCharge(charge);
			client.setMobile(mobile);
			client.setcType(clientType);
			String body = "{\"client\":" + JsonUtil.toJsonStr(client) + "}";
			logger.info("---------------------------创建子账号参数串-------------------------");
			logger.info(body);
			HttpResponse response = post("application/json", accountSid, authToken, timestamp, url, httpclient,
					encryptUtil, body);
			// 获取响应实体信息
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
			logger.info("---------------------------返回实体信息-------------------------");
			logger.info(result);
			// 确保HTTP响应内容全部被读出或者内容流被关闭
			EntityUtils.consume(entity);
		} catch (Throwable e) {
			e.printStackTrace();
			logger.info("---------------------------创建子账号请求失败-------------------------");
			logger.error(e.getMessage());
		} finally {
			// 关闭连接
			httpclient.getConnectionManager().shutdown();
		}
		return result;
	}

	@Override
	public String voiceCode(String accountSid, String authToken, String appId, String to, String verifyCode,
			String displayNum, String chargeType) {
		String result = "";
		DefaultHttpClient httpclient = getDefaultHttpClient();
		try {
			// MD5加密
			EncryptUtil encryptUtil = new EncryptUtil();
			// 构造请求URL内容
			String timestamp = DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_NO_SLASH);// 时间戳
			String signature = getSignature(accountSid, authToken, timestamp, encryptUtil);
			String url = getStringBuffer().append("/" + getRestVersion() + "/Accounts/").append(accountSid)
					.append("/Calls/voiceCode").append("?sig=").append(signature).toString();
			logger.info("请求voicecode url:" + url);
			RestVoiceCodeVo voice = new RestVoiceCodeVo();
			voice.setAppId(appId);
			voice.setVerifyCode(verifyCode);
			voice.setTo(to);
			voice.setVoiceCode(displayNum);
			voice.setFree(chargeType);
			String body = "{\"voiceCode\":" + JsonUtil.toJsonStr(voice) + "}";
			logger.info("---------------------------语音验证码请求参数-------------------------");
			logger.info(body);
			HttpResponse response = post("application/json", accountSid, authToken, timestamp, url, httpclient,
					encryptUtil, body);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
			EntityUtils.consume(entity);
		} catch (Throwable e) {
			e.printStackTrace();
			logger.info("---------------------------语音验证码请求失败-------------------------");
			logger.error(e.getMessage());
		} finally {
			// 关闭连接
			httpclient.getConnectionManager().shutdown();
		}
		logger.info("---------------------------语音验证码请求返回结果-------------------------");
		logger.info(result);
		return result;
	}

	@Override
	public String templateSMS(String accountSid, String authToken, String appId, String templateId, String to,
			String param, String chargeType) {
		String result = "";
		DefaultHttpClient httpclient = getDefaultHttpClient();
		try {
			// MD5加密
			EncryptUtil encryptUtil = new EncryptUtil();
			// 构造请求URL内容
			String timestamp = DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_NO_SLASH);// 时间戳
			String signature = getSignature(accountSid, authToken, timestamp, encryptUtil);
			String url = getStringBuffer().append("/" + getRestVersion() + "/Accounts/").append(accountSid)
					.append("/Messages/templateSMS").append("?sig=").append(signature).toString();
			logger.info("请求smscode url:" + url);
			templateSMSVo vo = new templateSMSVo();
			vo.setAppId(appId);
			vo.setTemplateId(templateId);
			vo.setTo(to);
			vo.setParam(param);
			vo.setFree(chargeType);
			String body = "{\"templateSMS\":" + JsonUtil.toJsonStr(vo) + "}";
			logger.info("---------------------------短信验证码请求参数-------------------------");
			logger.info(body);
			HttpResponse response = post("application/json", accountSid, authToken, timestamp, url, httpclient,
					encryptUtil, body);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
			EntityUtils.consume(entity);
		} catch (Throwable e) {
			logger.info("---------------------------短信验证码请求失败！-------------------------");
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			// 关闭连接
			httpclient.getConnectionManager().shutdown();
		}
		logger.info("---------------------------短信验证码返回结果-------------------------");
		logger.info(result);
		return result;
	}

	@Override
	public String callback(String accountSid, String authToken, String appId, String fromClient, String to,
			String toSerNum) {
		String result = "";
		DefaultHttpClient httpclient = getDefaultHttpClient();
		try {
			// MD5加密
			EncryptUtil encryptUtil = new EncryptUtil();
			// 构造请求URL内容
			String timestamp = DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_NO_SLASH);// 时间戳
			String signature = getSignature(accountSid, authToken, timestamp, encryptUtil);
			String url = getStringBuffer().append("/" + getRestVersion() + "/Accounts/").append(accountSid)
					.append("/Calls/callBack").append("?sig=").append(signature).toString();
			logger.info("--------------请求回拨的地址:------------" + url);
			CallbackVo vo = new CallbackVo();
			vo.setAppId(appId);
			vo.setFromClient(fromClient);
			vo.setTo(to);
			vo.setToSerNum(toSerNum);
			String body = "{\"callback\":" + JsonUtil.toJsonStr(vo) + "}";
			logger.info("------------请求回拨的参数:------------" + body);
			HttpResponse response = post("application/json", accountSid, authToken, timestamp, url, httpclient,
					encryptUtil, body);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
			EntityUtils.consume(entity);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			httpclient.getConnectionManager().shutdown();
		}
		logger.info("-----------回拨返回结果------------------" + result);
		return result;
	}

	@Override
	public String bandClients(String accountSid, String auth, String appId, String clientNumber, String mobile) {
		// TODO Auto-generated method stub
		String result = "";
		DefaultHttpClient httpclient = getDefaultHttpClient();
		try {
			// MD5加密
			EncryptUtil encryptUtil = new EncryptUtil();
			// 构造请求URL内容
			String timestamp = DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_NO_SLASH);// 时间戳
			String signature = getSignature(accountSid, auth, timestamp, encryptUtil);
			String url = getStringBuffer().append("/").append(getRestVersion()).append("/Accounts/").append(accountSid)
					.append("/Clients/band").append("?sig=").append(signature).toString();
			logger.info("绑定测试号码请求rest url:" + url);
			ClientRestVo client = new ClientRestVo();
			client.setClientNumber(clientNumber);
			client.setMobile(mobile);
			client.setAppId(appId);
			String body = "{\"client\":" + JsonUtil.toJsonStr(client) + "}";
			logger.info("绑定测试号码请求参数:" + body);
			HttpResponse response = post("application/json", accountSid, auth, timestamp, url, httpclient, encryptUtil,
					body);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
			EntityUtils.consume(entity);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			httpclient.getConnectionManager().shutdown();
		}
		logger.info("绑定测试号码返回结果:" + result);
		return result;
	}

	protected String getRestVersion() {
		return ConfigUtils.rest_version;
	}
}
