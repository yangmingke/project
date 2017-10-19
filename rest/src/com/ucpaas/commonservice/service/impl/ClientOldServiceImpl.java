package com.ucpaas.commonservice.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ucpaas.commonservice.config.Properties;
import com.ucpaas.commonservice.constant.Constants;
import com.ucpaas.commonservice.dao.ClientOldDao;
import com.ucpaas.commonservice.model.ClientBalanceInfo;
import com.ucpaas.commonservice.model.ClientInfo;
import com.ucpaas.commonservice.service.ClientOldService;
import com.ucpaas.commonservice.util.JsonUtil;

@Service("clientOldService")
public class ClientOldServiceImpl implements ClientOldService {
	private static final Logger log = LoggerFactory.getLogger(ClientOldServiceImpl.class);

	@Resource(name = "clientOldDao")
	private ClientOldDao clientOldDao;

	@Resource(name = "properties")
	private Properties properties;

	public Map<String, Object> insert(ClientInfo clientInfo, ClientBalanceInfo clientBalanceInfo) throws Exception {
		return this.clientOldDao.insert(clientInfo, clientBalanceInfo);
	}

	@Override
	public Map<String, Object> clientReg(ClientInfo clientInfo, ClientBalanceInfo clientBalanceInfo) throws Exception {
		// 向client老库写入注册client
		Map<String, Object> clientMap = this.clientOldDao.insert(clientInfo, clientBalanceInfo);
		return clientMap;
	}

	public void clientBalanceChargeInterface(String sid, String appSid, String clientNumber, String clientbalance) {
		String requestUrl = properties.getInterface_clientAccount();
		String content = "";
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		try {
			httpclient.start();
			HttpPost httpPost = new HttpPost(requestUrl);
			Map<String, String> params = new HashMap<String, String>();
			params.put("sid", sid);
			params.put("appSid", appSid);
			params.put("clientNumber", clientNumber);
			params.put("charge", clientbalance);
			content = JsonUtil.toJson(params);

			httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
			httpPost.setEntity(new StringEntity(content, "utf-8"));

			Future<HttpResponse> future = httpclient.execute(httpPost, null);
			// 异步请求的响应结果
			HttpResponse response = future.get();
			String statusLine = response.getStatusLine().toString();
			String repContent = EntityUtils.toString(response.getEntity());
			// 调用接口成功
			if (statusLine.contains(String.valueOf(HttpStatus.OK))
					&& repContent.contains(String.valueOf(Constants.RESULT_TRUE))) {
				log.info("【调计费中间件子账号充值接口】成功,requestUrl={},content={},response.status={},response.content={}",
						requestUrl, content, statusLine, repContent);
			} else {
				log.error("【调计费中间件子账号充值接口】失败,requestUrl={},content={},response.status={},response.content={}",
						requestUrl, content, statusLine, repContent);
			}
		} catch (Exception e) {
			log.error("【调计费中间件子账号充值接口】失败,requestUrl=" + requestUrl + ",content=" + content, e);
		}finally{
			try {
				httpclient.close();
			} catch (Exception e) {
				log.error("【调计费中间件子账号充值接口】关闭client失败，e= {}",e);
			}
		}
	}

	/**
	 *  回滚134写入的数据
	 * tb_ucpaas_client表  client_number唯一
	 * tb_client_balance表 client_number唯一
	 * tb_ucpaas_client_pool  appid和mobile唯一
	 */
	@Override
	public void rollbackClient(ClientInfo clientInfo) throws Exception {

		int i = 0;
		Map<String, String> param= new HashMap<String, String>();
		// tb_ucpaas_client_pool
		if (StringUtils.isNotBlank(clientInfo.getMobile())) {
			param.put("appSid", clientInfo.getAppSid());
			param.put("mobile", clientInfo.getMobile());
			i += this.clientOldDao.deletePoolByAppAndMobile("ClientOldMapper.deletePool", param);
		}
		// tb_client_balance
		i += this.clientOldDao.deleteBalanceByClientNumber(clientInfo.getClientNumber());
		// 删除client
		i += this.clientOldDao.deleteClientByClientNumber(clientInfo.getClientNumber());
		
		log.info("【2014回滚子账号注册信息】结束,i={},clientInfo={}", i, clientInfo);

	}

}
