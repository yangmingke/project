package com.flypaas.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.UserDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.TbFlypaasUser;

/**
 * @author chenxijun
 * @version 
 */
@Repository
public class UserDaoImpl extends MyBatisDao implements UserDao {
	//用户注册[第一步]
	private static final String INSERT_USER = "insertUser";
	//更新帐号信息
	private static final String UPDATE_USER = "updateUser";
	//查询帐号信息
	private static final String FIND_USER_BY_ID = "findUserById";
	//查询帐号信息
	private static final String FIND_USER = "findUser";
	//登陆验证
	public static final String USER_LOGIN = "userLogin";
	//验证邮箱和手机
	public static final String V_MAIL_MOBILE="vMailandMobile";
	//根据用户名查找用户个数
	public static final String GET_USERCOUNT_BY_USERNAME="getUserCountByUserName";
	//监控检测
	public static final String MONITORHEARTBEAT="monitorHeartbeat";

	public void insertUser(TbFlypaasUser user) {
		sqlSessionTemplate.insert(INSERT_USER, user);
	}

	public void updateUser(TbFlypaasUser user) {
		sqlSessionTemplate.update(UPDATE_USER, user);
	}

	public TbFlypaasUser findUserByid(String sid) {
		return sqlSessionTemplate.selectOne(FIND_USER_BY_ID, sid);
	}

	public TbFlypaasUser userLogin(TbFlypaasUser user) {
		return sqlSessionTemplate.selectOne(USER_LOGIN, user);
	}

	public  int mailAndMobEnable(TbFlypaasUser user) {
		return sqlSessionTemplate.selectOne(V_MAIL_MOBILE, user);
	}

	public TbFlypaasUser findeUser(TbFlypaasUser user) {
		return sqlSessionTemplate.selectOne(FIND_USER, user);
	}

	public int getUserCountByUserName(String userName) {
		return sqlSessionTemplate.selectOne(GET_USERCOUNT_BY_USERNAME, userName);
	}

	public TbFlypaasUser monitorHeartbeat() {
		return sqlSessionTemplate.selectOne(MONITORHEARTBEAT);
	}

	public String queryRenewToken(String sid) {
		return sqlSessionTemplate.selectOne("UserDaoImpl_queryRenewToken", sid);
	}

	public void saveRenewToken(String sid, String token) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sid", sid);
		params.put("token", token);
		sqlSessionTemplate.update("UserDaoImpl_saveRenewToken",params);
	}

	public List<TbFlypaasUser> findeUpdatePasswordUser(Map<String,Object> params) {
		return sqlSessionTemplate.selectList("findeUpdatePasswordUser", params);
	}

	public void deleteMailLog(Map<String, Object> mailLog) {
		sqlSessionTemplate.delete("deleteMailLog", mailLog);
	}

	public void addMailLog(Map<String, Object> mailLog) {
		sqlSessionTemplate.insert("addMailLog", mailLog);
	}

	public Map<String, Object> getFinanceInfo(String sid) {
		return sqlSessionTemplate.selectOne("getFinanceInfo", sid);
	}
}
