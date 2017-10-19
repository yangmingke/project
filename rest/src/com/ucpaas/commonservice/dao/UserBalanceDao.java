package com.ucpaas.commonservice.dao;

import org.springframework.stereotype.Repository;

import com.ucpaas.commonservice.constant.SqlConstant;
import com.ucpaas.commonservice.dao.base.MasterDao;
import com.ucpaas.commonservice.model.UserBalanceInfo;

/**
 * 用户余额Dao
 * 
 * @author luke
 * 
 */

@Repository("userBalanceDao")
public class UserBalanceDao extends MasterDao {
	/**
	 * 根据sid更新用户余额记录
	 * 
	 * @param userBalance
	 *            用户余额
	 * @return
	 */
	public int updateBySid(UserBalanceInfo userBalance) throws Exception {
		return this.update(SqlConstant.UserBalanceInfoMapper.updateBySid, userBalance);

	}

	/**
	 * 根据用户sid查询用户余额记录
	 * 
	 * @param sid
	 * @return
	 * @throws Exception
	 */
	public UserBalanceInfo selectBySid(String sid) throws Exception {
		return this.selectOne(SqlConstant.UserBalanceInfoMapper.selectBySid, sid);
	}

}