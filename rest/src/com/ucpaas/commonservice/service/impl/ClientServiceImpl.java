package com.ucpaas.commonservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ucpaas.commonservice.config.Properties;
import com.ucpaas.commonservice.constant.Constants;
import com.ucpaas.commonservice.dao.AllocUinDao;
import com.ucpaas.commonservice.dao.ClientDao;
import com.ucpaas.commonservice.dao.ClientOldDao;
import com.ucpaas.commonservice.model.Attr2uinInfo;
import com.ucpaas.commonservice.model.ClientBalanceInfo;
import com.ucpaas.commonservice.model.ClientInfo;
import com.ucpaas.commonservice.service.Attr2uinInfoService;
import com.ucpaas.commonservice.service.ClientBalanceService;
import com.ucpaas.commonservice.service.ClientService;
import com.ucpaas.commonservice.service.base.RedisBaseService;
import com.ucpaas.commonservice.util.db.DBShardingUtil;

@Service("clientService")
public class ClientServiceImpl extends RedisBaseService<String,ClientInfo> implements ClientService {
	private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

	@Resource(name = "clientDao")
	private ClientDao clientDao;

	@Resource(name = "attr2uinInfoService")
	private Attr2uinInfoService attr2uinInfoService;
	
	@Resource(name="clientBalanceService")
	private ClientBalanceService clientBalanceService;
	
	@Resource(name="clientOldDao")
	private ClientOldDao clientOldDao;
	
	/**
	 * uin分配器
	 */
	@Resource(name="allocUinDao")
	private AllocUinDao allocUinDao;
	
	/**
	 * 配置文件
	 */
	@Resource(name="properties")
	private Properties properties;

	public int updateByClientNumber(ClientInfo clientInfo) throws Exception {
		String[] dbNodeArr = DBShardingUtil.getDBNodeByClientNumber(clientInfo.getClientNumber());
		clientInfo.setUin_mod(dbNodeArr[2]);
		log.debug("clientInfo={},dbNodeArr={}", clientInfo, dbNodeArr);
		return this.clientDao.updateByClientNumber(clientInfo, dbNodeArr[1]);
	}

	@Override
	public ClientInfo getByUin(Integer uin) throws Exception {
		Map<String, Object> parameterMap = new HashMap<String, Object>();

		Map<String, String> dbMap = DBShardingUtil.getDBNodeByUin(uin);
		parameterMap.put("uin_mod", dbMap.get("uin_mod"));
		parameterMap.put("uin", uin);
		log.debug("uin={},dbMap={},parameterMap={}", uin, dbMap, parameterMap);
		return this.clientDao.selectByUin(parameterMap, dbMap.get("db_node"));
	}

	@Override
	public ClientInfo getByClientNumber(String clientNumber) throws Exception {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		String[] dbNodeArr = DBShardingUtil.getDBNodeByClientNumber(clientNumber);
		parameterMap.put("uin_mod", dbNodeArr[2]);
		parameterMap.put("clientNumber", clientNumber);

		return this.clientDao.selectByClientNumber(parameterMap, dbNodeArr[1]);
	}

	@Override
	public ClientInfo getByMobileAndAppSidCache(String mobile, String appSid) throws Exception {
		String attr = mobile + "_" + appSid;
		// 1,根据attr从缓存查询反向表uin
		Integer uin = this.attr2uinInfoService.getUinCache(attr, Constants.ATTR2UIN_TYPE_102);
		log.debug("mobile={},appSid={},uin={}", mobile, appSid, uin);

		// 2,根据反向表中的uin，查询子账号信息
		if (uin != null) {
			return this.getByUin(uin);
		} else {
			return null;
		}
	}

	@Override
	public ClientInfo getByUserIdAndAppSidCache(String userId, String appSid) throws Exception {
		String attr = userId + "_" + appSid;
		// 1,根据attr从缓存查询反向表uin
		Integer uin = this.attr2uinInfoService.getUinCache(attr, Constants.ATTR2UIN_TYPE_101);
		log.debug("userId={},appSid={},uin={}", userId, appSid, uin);

		// 2,根据反向表中的uin，查询子账号信息
		if (uin != null) {
			return this.getByUin(uin);
		} else {
			return null;
		}
	}

	@Override
	public int updateByUin(ClientInfo clientInfo) throws Exception {
		Map<String, String> dbMap = DBShardingUtil.getDBNodeByUin(clientInfo.getUin());
		clientInfo.setUin_mod(dbMap.get("uin_mod"));
		log.debug("clientInfo={},dbMap={}", clientInfo, dbMap);
		return this.clientDao.updateByUin(clientInfo, dbMap.get("db_node"));
	}

	@Override
	public int updateByUserIdAndAppSid(ClientInfo clientInfo) throws Exception {
		String attr = clientInfo.getUserid() + "_" + clientInfo.getAppSid();
		// 1,根据attr从缓存查询反向表信息
		Integer uin = this.attr2uinInfoService.getUinCache(attr, Constants.ATTR2UIN_TYPE_101);
		if (uin != null) {
			clientInfo.setUin(uin);
			return this.updateByUin(clientInfo);
		}
		return 0;
	}

	@Override
	public int insert(ClientInfo clientInfo) throws Exception {
		Map<String, String> dbMap = DBShardingUtil.getMapDBNodeByClientNumber(clientInfo.getClientNumber());
		log.debug("【插入子账号】,dbMap={},clientInfo={}", dbMap, clientInfo);
		// 设置表后缀
		clientInfo.setUin_mod(dbMap.get("uin_mod"));
		// 设置uin
		clientInfo.setUin(Integer.parseInt(dbMap.get("uin")));
		return this.clientDao.insert(clientInfo, dbMap.get("db_node"));
	}

	/**
	 * 同时插入子账号信息和反向表信息
	 * rest2014双写版
	 * @param clientInfo
	 * @return
	 * @throws Exception
	 */
	public int insetClientAndAttr(ClientInfo clientInfo) throws Exception {
		// 1,插入子账号信息
		int crows = this.insert(clientInfo);
		Map<String, String> dbMap = DBShardingUtil.getMapDBNodeByClientNumber(clientInfo.getClientNumber());
		Attr2uinInfo attr2uinInfo = null;

		// 2,插入反向表信息
		if (StringUtils.isNotEmpty(clientInfo.getUserid())) {
			// 如果子账号的userid非空，则插入type=101：attr={userId}_{appId}
			attr2uinInfo = new Attr2uinInfo();
			attr2uinInfo.setAttr(clientInfo.getUserid() + "_" + clientInfo.getAppSid());
			attr2uinInfo.setType(Constants.ATTR2UIN_TYPE_101);
			attr2uinInfo.setUin(Integer.parseInt(dbMap.get("uin")));
			crows += this.attr2uinInfoService.insert(attr2uinInfo);
		} else {
			// 如果子账号的userid为空，则插入type=101：attr={clientNumber}_{appId}
			attr2uinInfo = new Attr2uinInfo();
			attr2uinInfo.setAttr(clientInfo.getClientNumber() + "_" + clientInfo.getAppSid());
			attr2uinInfo.setType(Constants.ATTR2UIN_TYPE_101);
			attr2uinInfo.setUin(Integer.parseInt(dbMap.get("uin")));
			crows += this.attr2uinInfoService.insert(attr2uinInfo);
		}

		if (StringUtils.isNotEmpty(clientInfo.getMobile())) {
			// 如果手机号不为空，则插入type=102：attr={mobile}_{appId}
			attr2uinInfo = new Attr2uinInfo();
			attr2uinInfo.setAttr(clientInfo.getMobile() + "_" + clientInfo.getAppSid());
			attr2uinInfo.setType(Constants.ATTR2UIN_TYPE_102);
			attr2uinInfo.setUin(Integer.parseInt(dbMap.get("uin")));
			crows += this.attr2uinInfoService.insert(attr2uinInfo);
		}
		return crows;
	}

	/**
	 * 调用晓健提供的删除client缓存的接口 示例：113.31.89.149:9998/UpdateClient.cgi?uin=123456
	 * 
	 * @param uin
	 */
	public void deleteClientCache(String uin) throws Exception {
		long startTime = System.currentTimeMillis();
		String requestUrl = this.properties.getClient_delete_cache();
		requestUrl += "?uin=" + uin;
		//
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		try {
			httpclient.start();
			HttpGet httpget = new HttpGet(requestUrl);
			log.info("【异步请求删除client缓存】开始,request=" + httpget.getRequestLine());
			Future<HttpResponse> future = httpclient.execute(httpget, null);

			//异步请求的响应结果
			 HttpResponse response = future.get();
			 log.info("【异步请求删除client缓存】响应,response=" + response.getStatusLine());
		} catch (Exception e) {
			log.error("【异步请求删除client缓存】错误,requestUrl=" + requestUrl, e);
		} finally {
			log.info("【异步请求删除client缓存】结束,HttpAsyncTimeCount=" + (System.currentTimeMillis()-startTime));
			httpclient.close();
		}

	}

	/**
	 * 更新client信息；同时调用接口，删除缓存中的信息 rest2升级用
	 * 
	 * @param clientInfo
	 * @return
	 * @throws Exception
	 */
	public int updateByClientNumberCache(ClientInfo clientInfo) throws Exception {
		int rows = this.updateByClientNumber(clientInfo);
		Map<String, String> dbMap = DBShardingUtil.getMapDBNodeByClientNumber(clientInfo.getClientNumber());
		this.deleteClientCache(dbMap.get("uin"));
		return rows;
	}

	@Override
	public int updateByUserIdAndAppSidCache(ClientInfo clientInfo) throws Exception {
		String attr = clientInfo.getUserid() + "_" + clientInfo.getAppSid();
		// 1.根据attr从缓存查询反向表信息
		Integer uin = this.attr2uinInfoService.getUinCache(attr, Constants.ATTR2UIN_TYPE_101);
		if (uin != null) {
			clientInfo.setUin(uin);
			int rows =  this.updateByUin(clientInfo);
			//2.通知晓健删除他维护的client缓存
			this.deleteClientCache(String.valueOf(uin));
			return rows;
		}
		return 0;
	}

	@Override
	public int updateMobileCache(ClientInfo clientInfo,String newMobile)throws Exception {
		int rows = 0;
		log.debug("【更新client的mobile】开始,clientInfo={},newMobile={}",clientInfo,newMobile);
		//1.删除反向表中旧mobile信息；type=102：attr={mobile}_{appId}
		if(StringUtils.isNotEmpty(clientInfo.getMobile())){
			Attr2uinInfo attrOld = new Attr2uinInfo();
			attrOld.setAttr(clientInfo.getMobile()+"_"+clientInfo.getAppSid());
			attrOld.setType(Constants.ATTR2UIN_TYPE_102);
			rows += this.attr2uinInfoService.deleteByAttrTypeCache(attrOld);
		}
		
		//2.更新client信息
		clientInfo.setMobile(newMobile);
		rows += this.updateByUin(clientInfo);
		
		//3.如果新手机号newMobile不为空，则插入新数据到反向表
		if(StringUtils.isNotEmpty(newMobile)){
			Attr2uinInfo attrNew = new Attr2uinInfo();
			attrNew.setAttr(newMobile+"_"+clientInfo.getAppSid());
			attrNew.setType(Constants.ATTR2UIN_TYPE_102);
			//先删除表中的数据，再插入，
			//rest2014双写版本上线前，解绑换绑操作134库，110库的反向表没有删除数据，产生脏数据
			rows += this.attr2uinInfoService.deleteByAttrTypeCache(attrNew);
			
			attrNew.setUin(clientInfo.getUin());
			rows += this.attr2uinInfoService.insertCache(attrNew);
		}
		//4.通知晓健删除他维护的client缓存
		this.deleteClientCache(""+clientInfo.getUin());
		log.debug("【更新client的mobile】结束,clientInfo={},newMobile={},rows={}",
				clientInfo,newMobile,rows);
		return rows;
	}

	@Override
	public int updateMobileCacheByClientNumber(ClientInfo clientInfo, String newMobile) throws Exception {
		//1.根据client_number获取uin
		Map<String, String> dbMap = DBShardingUtil.getMapDBNodeByClientNumber(clientInfo.getClientNumber());
		clientInfo.setUin(Integer.parseInt(dbMap.get("uin")));
		return updateMobileCache(clientInfo, newMobile);
	}

	@Override
	public ClientInfo getByClientNumberCache(String clientNumber) throws Exception {
		ClientInfo clientInfo = null;
		//1.从缓存拿数据
		clientInfo = this.getFromCache("ucpaas:client:clientnumber:"+clientNumber);
		if(clientInfo == null){
			// 2.如果缓存中没有数据，从数据库拿数据
			clientInfo = this.getByClientNumber(clientNumber);
			if (clientInfo != null) {
				// 3.将数据放入缓存中,有效期n天
				this.setToCacheDays("ucpaas:client:clientnumber:"+clientNumber, clientInfo, RandomUtils.nextInt(Constants.REDIS_DAY_MIN, Constants.REDIS_DAY_MAX));
			}
		}
		return clientInfo;
	}

	@Override
	public ClientInfo getByUinCache(Integer uin) throws Exception {
		ClientInfo clientInfo = null;
		//1.从缓存拿数据
		clientInfo = this.getFromCache("ucpaas:client:uin:"+uin);
		if(clientInfo == null){
			// 2.如果缓存中没有数据，从数据库拿数据
			clientInfo = this.getByUin(uin);
			if (clientInfo != null) {
				// 3.将数据放入缓存中,有效期n天
				this.setToCacheDays("ucpaas:client:uin:"+uin, clientInfo, RandomUtils.nextInt(Constants.REDIS_DAY_MIN, Constants.REDIS_DAY_MAX));
			}
		}
		return clientInfo;
	}

	@Override
	public int closeClient(ClientInfo clientInfo,ClientBalanceInfo clientBalanceInfo) throws Exception {
		log.debug("【关闭client】开始,clientInfo={}",clientInfo);
		int rows = 0;
		
		//1.关闭client，通知晓健删除他维护的client和反向表缓存
//		this.deleteClientAndAttrCache(""+clientInfo.getUin(),  clientInfo.getAppSid(), clientInfo.getMobile(), clientInfo.getUserid()); //DEL by yangmingke
		
		//2.1删除反向表中userId信息；type=101：attr={userId}_{appId}
		if(StringUtils.isNotBlank(clientInfo.getUserid())){
			Attr2uinInfo attrUserId = new Attr2uinInfo();
			attrUserId.setAttr(clientInfo.getUserid() + "_" + clientInfo.getAppSid());
			attrUserId.setType(Constants.ATTR2UIN_TYPE_101);
			rows += this.attr2uinInfoService.deleteByAttrTypeCache(attrUserId);
		}
		
		//2.2删除反向表中mobile信息；type=102：attr={mobile}_{appId}
		if(StringUtils.isNotBlank(clientInfo.getMobile())){
			Attr2uinInfo attrMobile = new Attr2uinInfo();
			attrMobile.setAttr(clientInfo.getMobile() + "_" + clientInfo.getAppSid());
			attrMobile.setType(Constants.ATTR2UIN_TYPE_102);
			rows += this.attr2uinInfoService.deleteByAttrTypeCache(attrMobile);
		}
		
		//3.根据uin更新client状态为已关闭。
		clientInfo.setUserid("");
		clientInfo.setMobile("");
		rows += this.updateByUin(clientInfo);
		
		//4.根据uin更新clientbalance状态
		rows += this.clientBalanceService.updateByUin(clientBalanceInfo);
		
		log.debug("【关闭client】结束,clientInfo={},rows={}",clientInfo,rows);
		return rows;
	}

	@Override
	public int closeClientByClientNumber(ClientInfo clientInfo,ClientBalanceInfo clientBalanceInfo) throws Exception {
		Map<String, String> dbMap = DBShardingUtil.getMapDBNodeByClientNumber(clientInfo.getClientNumber());
		clientInfo.setUin(Integer.parseInt(dbMap.get("uin")));
		clientBalanceInfo.setUin(Integer.parseInt(dbMap.get("uin")));
		
		return this.closeClient(clientInfo,clientBalanceInfo);
	}

	//==========================2016-03-21迁移注册client中间件新增
	@Override
	public int deleteByUin(ClientInfo clientInfo) throws Exception {
		Map<String, String> dbMap = DBShardingUtil.getDBNodeByUin(clientInfo.getUin());
		clientInfo.setUin_mod(dbMap.get("uin_mod"));
		return this.clientDao.deleteByUin(clientInfo, dbMap.get("db_node"));
	}
	
	
	//===========================2016-04-12新增调刘晓健删除client和反向表缓存的接口
	/**
	 * 	 * 调用晓健提供的删除client和反向表缓存的接口 
	 * 示例：http://113.31.89.149:9998/DelClientAndAttr.cgi?uin=123456&&app_sid=xx&mobile=xx&userid=xx	
	 * @param uin
	 * @param appSid
	 * @param mobile
	 * @param userid
	 * @throws Exception
	 */
	public void deleteClientAndAttrCache(String uin,String appSid,String mobile,String userid) throws Exception {
		long startTime = System.currentTimeMillis();
		String requestUrl = this.properties.getClientAndAttr_delete_cache();
		requestUrl += "?uin=" + uin +"&app_sid=" + appSid + "&mobile=" + mobile + "&userid=" + userid;
		//
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		try {
			httpclient.start();
			HttpGet httpget = new HttpGet(requestUrl);
			log.info("【关闭client,异步请求删除clientAndAttr缓存】开始,request=" + httpget.getRequestLine());
			Future<HttpResponse> future = httpclient.execute(httpget, null);

			//异步请求的响应结果
			 HttpResponse response = future.get();
			 log.info("【关闭client,异步请求删除clientAndAttr缓存】响应,response=" + response.getStatusLine());
		} catch (Exception e) {
			log.error("【关闭client,异步请求删除clientAndAttr缓存】错误,requestUrl=" + requestUrl, e);
		} finally {
			log.info("【关闭client,异步请求删除clientAndAttr缓存】结束,HttpAsyncTimeCount=" + (System.currentTimeMillis()-startTime));
			httpclient.close();
		}

	}

	@Override
	public List<ClientInfo> getByAppSid(String appSid, int tablie_index) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		String node = DBShardingUtil.getDBNodeByIndex(tablie_index);
		parameterMap.put("appSid", appSid);
		parameterMap.put("uin_mod", ""+tablie_index);
		log.debug("parameterMap={},node = {}", parameterMap,node);
		return clientDao.selectByAppSidAndIndex(parameterMap,node);
		
	}
	


	


}
