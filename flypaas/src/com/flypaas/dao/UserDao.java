package com.flypaas.dao;

import java.util.List;
import java.util.Map;

import com.flypaas.entity.TbFlypaasUser;

/**
 * @author chenxijun
 * @version
 */
public interface UserDao {

	/**
	 * TODO 添加用户
	 * @author chenxijun
	 * 2014-3-31
	 */
	public void insertUser(TbFlypaasUser user);

	/**
	 * TODO 更新用户基本信息
	 * @author chenxijun
	 * 2014-3-31
	 */
	public void updateUser(TbFlypaasUser user);

	/**
	 * TODO 根据用户id查找用户信息
	 * @author chenxijun
	 * 2014-3-31
	 */
	public TbFlypaasUser findUserByid(String sid);
	/**
	 * TODO 获取用户信息
	 * @author chenxijun
	 * 2014-3-31
	 */
	public TbFlypaasUser findeUser(TbFlypaasUser user);

	/**
	 * TODO 登陆
	 * @author chenxijun
	 * 2014-3-31
	 */
	public TbFlypaasUser userLogin(TbFlypaasUser user);
	/**
	 * TODO 验证手机号码 邮箱是否已存在
	 * @author 29p9g02
	 * 2014-4-10
	 */
	public int mailAndMobEnable(TbFlypaasUser user);
	
	public int getUserCountByUserName(String userName);
	
	public TbFlypaasUser monitorHeartbeat();

	public String queryRenewToken(String sid);
	public void saveRenewToken(String sid, String token);
	
	public List<TbFlypaasUser> findeUpdatePasswordUser(Map<String,Object> params);

	public void deleteMailLog(Map<String, Object> mailLog);

	public void addMailLog(Map<String, Object> mailLog);

	public Map<String, Object> getFinanceInfo(String sid);


}