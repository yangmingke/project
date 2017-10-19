package com.flypaas.service.impl;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.constant.AppConstant;
import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.AppBalance;
import com.flypaas.entity.Application;
import com.flypaas.entity.TbSrvCallback;
import com.flypaas.entity.TbSrvWhiteList;
import com.flypaas.entity.vo.PageContainer;
import com.flypaas.httpclient.impl.RefreshRedis;
import com.flypaas.service.AppService;
import com.flypaas.service.CallBackService;
import com.flypaas.service.WhiteListService;
import com.flypaas.service.app.SmsTemplateService;
import com.flypaas.utils.DateUtil;
import com.flypaas.utils.FileUtil;
import com.flypaas.utils.JsonUtil;
import com.flypaas.utils.StrUtil;
import com.flypaas.utils.SysConfig;

@Service
@Transactional
public class AppServiceImpl extends DaoCenter implements AppService {

	@Autowired
	private SmsTemplateService smsTemplateService;
	@Autowired
	private WhiteListService whiteService;
	@Autowired
	private CallBackService cbService;
	
	private String appSid ;
	public synchronized int addApp(Application app,String whiteListStr,String cbfunStr,String cbfunurlStr,String cbfunmethodStr, File validFile,String type) {
		int count = appDao.appNameExist(app);
		if(count==0){
			if(validFile != null){
				String url = FileUtil.uploadAppFile(validFile,app.getAppSid(),SysConfig.getInstance().getProperty("oauth_file"),type);
				app.setOauthFileUrl(url);
			}
			//应用
			appDao.add(app);
			//白名单
			String uuid = app.getAppSid();
			if(whiteListStr!=null && whiteListStr.length()>0){
				updateWhiteList(createWhiteList(uuid,whiteListStr,null),uuid);
			}
			//回调
			if(cbfunStr!=null&&cbfunStr.length()>0){
				cbFunOpt(uuid,cbfunStr,cbfunurlStr,cbfunmethodStr);
			}
		}
		return count;
	}
	public int add(Application app) {
		int count = appDao.appNameExist(app);
		if(count==0){
			appDao.add(app);
		}
		return count;
	}
	
	public void updateApp(Application app,String whiteListStr,String cbfunStr,String cbfunurlStr,String cbfunmethodStr,HttpServletResponse response, File validFile, String type) {
			if(validFile != null){
				String url = FileUtil.uploadAppFile(validFile,app.getAppSid(),SysConfig.getInstance().getProperty("oauth_file"),type);
				app.setOauthFileUrl(url);
			}
			if(StrUtil.isEmpty(app.getOauthAppId())){
				app.setOauthAppId(null);
			}
			if(validFile != null || app.getOauthAppId() != null){
				app.setStatus(AppConstant.STATUS_0);//认证文件或认证APPID修改将导致APP重新进入审核状态
			}
			app.setUpdateDate(DateUtil.getCurrentDate());
			update(app);
			
			String appSid = app.getAppSid();
			if(whiteListStr!=null&&whiteListStr.length()>0){
				updateWhiteList(createWhiteList(appSid,whiteListStr,null),appSid);
			}else{
				whiteService.delete(appSid);
			}
			RefreshRedis.updateWhiteList(appSid);
			
			if(cbfunStr!=null&&cbfunStr.length()>0){
				updateCbFun(appSid, cbfunStr, cbfunurlStr, cbfunmethodStr);
			}else{
				cbService.delete(appSid);
			}
	}
	
	public void update(Application app){
		appDao.update(app);
	}
	public void delete(Application app) {
		appSid = app.getAppSid();
		
		//删除应用
		update(app);
		
		//删除client(由于考虑性能问题不处理client)
		//clientService.delete(appSid);
		
		//删除短信模板
		smsTemplateService.deleteByAppsid(appSid);
		
		//删除应用下显号
//		appShowNbrsDao.outDateShowNbr(appSid);
		
		//删除对应的钱包
		AppBalance ab = new AppBalance();
		ab.setSid(app.getSid());
		ab.setAppSid(appSid);
		appBalanceDao.deleteAppBalance(ab);
	}

	public PageContainer getApp(PageContainer page) {
		return appDao.getApp(page);
	}

	public List<Application> getAppBySid(String sid) {
		return appDao.getAppBySid(sid);
	}

	public synchronized int appNameExist(Application app) {
		return appDao.appNameExist(app);
	}

	public Application getAppById(String id) {
		return appDao.getAppById(id);
	}

	public Application getTestApp(String sid) {
		return appDao.getTestApp(sid);
	}

	public int getOnlineAppCount(String sid) {
		return appDao.getOnlineAppCount(sid);
	}

	public List<Application> getAppSmsNumBySid(String sid) {
		return appDao.getAppSmsNumBySid(sid);
	}

	public Map<String, Object> getActiveCount(Map<String, Object> params) {
		return appDao.getActiveCount(params);
	}

	public List<Application> getAllAppBySid(String sid) {
		return appDao.getAllAppBySid(sid);
	}
	/*
	public void addTempApp(Map<String, Object> map) {
		appDao.addTempApp(map);
	}

	public int isExitsTempApp(String appSid) {
		return appDao.isExitsTempApp(appSid);
	}

	public void updateTempApp(Map<String, Object> map) {
		appDao.updateTempApp(map);
	}

	public void deleteTempApp(Map<String, Object> map) {
		appDao.deleteTempApp(map);
	}
	*/
	public List<Application> getAppsNotContainsTestAppBySid(String sid) {
		return appDao.getAppsNotContainsTestAppBySid(sid);
	}

	public Application getAppBySidAppSid(Application app) {
		return appDao.getAppBySidAppSid(app);
	}
	private void updateCbFun(String appSid,String cbfunStr, String cbfunurlStr, String cbfunmethodStr){
		List<TbSrvCallback> tc = cbService.get(appSid);
		if(tc!=null && tc.size()>0){
			cbService.delete(appSid);
		}
		cbFunOpt(appSid,cbfunStr, cbfunurlStr, cbfunmethodStr);
	}
	private void updateWhiteList(TbSrvWhiteList whiteList,String appSid){
		TbSrvWhiteList wl = whiteService.get(appSid);
		if(wl!=null){
			whiteService.update(whiteList);
		}else{
			whiteService.add(whiteList);
		}
	}
	private void cbFunOpt(String appSid,String cbfunStr,String cbfunurlStr,String cbfunmethodStr){
		TbSrvCallback cb = new TbSrvCallback();
		String [] array = cbfunStr.split(",");
		String [] array1 = cbfunurlStr.split(",");
		String [] array2 = cbfunmethodStr.split(",");
		
		for(int i =0 ; i<array.length ; i++){
			if(array[i]!=null && !array[i].equals("null")){
			cb.setAppSid(appSid);
			cb.setCallAddress(array1[i]);
			cb.setCallType(Integer.parseInt(array[i]));
			cb.setMethod(array2[i]);
			cbService.add(cb);
			}
		}
	}
	private TbSrvWhiteList createWhiteList(String appSid,String whiteListStr,Date updateDate){
		TbSrvWhiteList whiteList = new TbSrvWhiteList();
		whiteList.setAppSid(appSid);
		whiteList.setWhiteAddress(whiteListStr);
		whiteList.setwType(AppConstant.WHITE_TYPE);
		whiteList.setCreateDate(DateUtil.getCurrentDate());
		if(updateDate!=null){
			whiteList.setUpdateDate(updateDate);
		}else{
			whiteList.setUpdateDate(DateUtil.getCurrentDate());
		}
		whiteList.setStatus(AppConstant.CHECK_STATUS);
		return whiteList;
	}
	@Override
	public Map<String, Object> getSessionPacketLoss(String sid, String appSid, String cookieId, String datetime) {
		Map<String, Object> sessionPacketLoss = new HashMap<String, Object>();
		sessionPacketLoss.put("dateTime", datetime.replaceAll("-", ""));
		sessionPacketLoss.put("userSid", StringUtils.isEmpty(sid) ? null : sid);
		sessionPacketLoss.put("appSid", StringUtils.isEmpty(appSid) ? null : appSid);
		sessionPacketLoss.put("cookie", StringUtils.isEmpty(cookieId) ? null : cookieId);
		List<Map<String, Object>> analysisResultList = null;
		try {
			analysisResultList = sessionPacketLossDao.getSessionPacketLoss(sessionPacketLoss);
		} catch (BadSqlGrammarException e) {
			System.out.println(e);
			return sessionPacketLoss;
		}
		LinkedList<String> packetLossTime = new LinkedList<String>(Arrays.asList("00:00:00", "01:00:00", "02:00:00",
				"03:00:00", "04:00:00", "05:00:00", "06:00:00", "07:00:00", "08:00:00", "09:00:00", "10:00:00",
				"11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00",
				"19:00:00", "20:00:00", "21:00:00", "22:00:00", "23:00:00", "24:00:00"));
		for (Map<String, Object> analysisResult : analysisResultList) {
			String snapshoot = analysisResult.get("snapshoot").toString();
			String packetLossString = snapshoot.replace("[", "").replace("]", "").trim();
			String[] packetLossArray = packetLossString.split("  ");
			for (int i = 0; StringUtils.isNotEmpty(packetLossString) && i < packetLossArray.length; i++) {
				for (int j = 0; j < packetLossTime.size(); j++) {
					String time = packetLossArray[i].split(" ")[0];
					if (time.compareTo(packetLossTime.get(j)) <= 0) {
						if (!time.equals(packetLossTime.get(j))) {
							packetLossTime.add(j, time);
						}
						break;
					}
				}
			}
		}
		sessionPacketLoss.put("packetLossTime", JsonUtil.toJsonStr(packetLossTime));//时间轴
		sessionPacketLoss.put("resultList", analysisResultList);//用于列表显示
		sessionPacketLoss.put("resultJson", JsonUtil.toJsonStr(analysisResultList));//用于画曲线图
		return sessionPacketLoss;
	}
}
