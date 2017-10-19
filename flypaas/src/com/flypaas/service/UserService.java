package com.flypaas.service;

import java.util.List;
import java.util.Map;

import com.flypaas.entity.TbFlypaasUser;

/**
 * @author  chenxijun
 * @version 
 */
public interface UserService {
	/**
	 * TODO 添加用户
	 * @author chenxijun
	 * 2014-3-31
	 */
	public int addUser(TbFlypaasUser user);
	
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
	public TbFlypaasUser findUserById(String userId);
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
	
	/**
	 * 根据用户名查找用户个数
	 * @param userName
	 * @return
	 */
	public int getUserCountByUserName(String userName);
	/**
	 * 监控心跳检测
	 * @param userName
	 * @return
	 */
	public TbFlypaasUser monitorHeartbeat();
	/**
	 * 激活用户
	 * @param u
	 */
	public void activaUser(TbFlypaasUser u);

	public String renewToken(String sid, String resetTokenType);

	/**
	 * 获取需要修改密码的用户信息
	 * @return
	 */
	public List<TbFlypaasUser> findeUpdatePasswordUser(Map<String,Object> params);

	/**删除邮件日志
	 * @param mailLog
	 */
	public void deleteMailLog(Map<String, Object> mailLog);
	
	/**添加邮件日志
	 * @param mailLog
	 */
	public void addMailLog(Map<String, Object> mailLog);

	/**获取账务信息
	 * @param sid
	 * @return
	 */
	public Map<String, Object> getFinanceInfo(String sid);

	public TbFlypaasUser findeUserById(String agentId);

}