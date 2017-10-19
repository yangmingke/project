package com.flypaas.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.flypaas.entity.Application;
import com.flypaas.entity.vo.PageContainer;

/**
 * TODO 应用service
 * 
 * @author 29p9g02
 * @version
 */
public interface AppService {
	/**
	 * TODO 增加应用
	 * 
	 * @author 29p9g02 2014-4-21
	 * @param type 
	 * @param 
	 */
	public int addApp(Application app,String whiteListStr,String cbfunStr,String cbfunurlStr,String cbfunmethodStr, File validFile, String type);
	
	/**
	 * TODO 增加应用
	 * 
	 * @author 29p9g02 2014-4-21
	 */
	public int add(Application app);


	/**
	 * TODO 更新应用
	 * 
	 * @author 29p9g02 2014-4-21
	 * @param type 
	 * @param validFile 
	 */
	public void updateApp(Application app,String whiteListStr,String cbfunStr,String cbfunurlStr,String cbfunmethodStr,HttpServletResponse response, File validFile, String type);
	
	/**
	 * TODO 更新应用
	 * 
	 * @author 29p9g02 2014-4-21
	 */
	public void update(Application app);
	
	/**
	 * 删除应用
	 * @param app
	 */
	public void delete(Application app);

	/**
	 * TODO 查询条件应用
	 * 
	 * @author 29p9g02 2014-4-21
	 */
	public PageContainer getApp(PageContainer page);

	/**
	 * TODO 根据sid查询应用
	 * 
	 * @author 29p9g02 2014-4-21
	 */
	public List<Application> getAppBySid(String sid);
	
	/**
	 * TODO 根据sid查询应用(不包含测试app)
	 * 
	 * @author 29p9g02 2014-4-21
	 */
	public List<Application> getAppsNotContainsTestAppBySid(String sid);

	/**
	 * 
	 * TODO 应用名称是否存在
	 * 
	 * @author 29p9g02 2014-4-22
	 */
	public int appNameExist(Application app);

	/**
	 * TODO 获取一个应用
	 * 
	 * @author 29p9g02 2014-4-21
	 */
	public Application getAppById(String id);

	/**
	 * TODO 获取测试应用
	 * 
	 * @author 29p9g02 2014-4-28
	 */
	public Application getTestApp(String sid);

	/**
	 * 获取上线app
	 * 
	 * @param sid
	 * @return
	 */
	public int getOnlineAppCount(String sid);

	/**
	 * 获取已经审核过的应用
	 * 
	 * @param sid
	 * @return
	 */
	public List<Application> getAppSmsNumBySid(String sid);

	/**
	 * 获取活跃的client数
	 * 
	 * @param sid
	 * @return
	 */
	public Map<String, Object> getActiveCount(Map<String, Object> params);
	/**
	 * 所有的app  包括测试
	 * @param sid
	 * @return
	 */
	public List<Application> getAllAppBySid(String sid);
	/**
	 * 根据appSid获取app
	 * @param app
	 * @return
	 */
	public Application getAppBySidAppSid(Application app);

	/**
	 * 获取会话丢包情况
	 * @param sid
	 * @param appSid
	 * @param cookieId
	 * @param datetime
	 * @return
	 */
	public Map<String, Object> getSessionPacketLoss(String sid, String appSid, String cookieId, String datetime);
}
