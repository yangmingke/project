package com.flypaas.dao.impl;

import java.util.List;
import java.util.Map;

import com.flypaas.entity.Application;
import com.flypaas.entity.vo.PageContainer;

/**
 * @author chenxijun
 * @version
 */
public interface AppDao {
	/**
	 * TODO 增加应用
	 * 
	 * @author 29p9g02 2014-4-21
	 */
	public void add(Application app);

	/**
	 * TODO 更新应用
	 * 
	 * @author 29p9g02 2014-4-21
	 */
	public void update(Application app);

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
	 * TODO 应用名是否存在
	 * 
	 * @author 29p9g02 2014-4-21
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
	 * @author 29p9g02 2014-4-21
	 */
	public Application getTestApp(String sid);

	/**
	 * TODO 获取上线app个数
	 * 
	 * @author 29p9g02 2014-4-21
	 */
	public int getOnlineAppCount(String sid);

	public List<Application> getAppSmsNumBySid(String sid);

	/**
	 * 获取活跃数据
	 * 
	 * @param sid
	 * @return
	 */
	public Map<String, Object> getActiveCount(Map<String, Object> params);
	
	/**
	 * 获取所有的app 包括测试
	 * @return
	 */
	public List<Application> getAllAppBySid(String sid);
	/**
	 * 获得apps不包含测试app
	 * @param sid
	 * @return
	 */
	public List<Application> getAppsNotContainsTestAppBySid(String sid);
	
	public Application getAppBySidAppSid(Application app);

	public List<Map<String, Object>> getSessionPacketLoss(Map<String, Object> model);

}