package com.ucpaas.commonservice.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ucpaas.commonservice.constant.SqlConstant;
import com.ucpaas.commonservice.dao.base.SlaveDao;
import com.ucpaas.commonservice.model.TestClientInfo;

/**
 * 测试子账号Dao
 * 
 * @author luke
 *
 */
@Repository("testClientDao")
public class TestClientDao extends SlaveDao {

	/**
	 * 根据clientNumber更新测试子账号记录
	 * 
	 * @param testClientInfo
	 * @return
	 */
	public int updateByClientNumber(TestClientInfo testClientInfo) throws Exception {
		return this.update(SqlConstant.TestClientInfoMapper.updateByClientNumber, testClientInfo);
	}

	/**
	 * 查询子账号集合
	 * 
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	public List<TestClientInfo> selectListByMap(Map<String, Object> parameterMap) throws Exception {
		return this.selectList(SqlConstant.TestClientInfoMapper.selectListByMap, parameterMap);
	}

	/**
	 * 根据clientNumber查询测试子账号
	 * 
	 * @param clientNumber
	 * @return
	 * @throws Exception
	 */
	public TestClientInfo selectByClientNumber(String clientNumber) throws Exception {
		return this.selectOne(SqlConstant.TestClientInfoMapper.selectByClientNumber, clientNumber);
	}

	/**
	 * 插入测试子账号
	 * 
	 * @param testClientInfo
	 * @return
	 * @throws Exception
	 */
	public int insert(TestClientInfo testClientInfo) throws Exception {
		return this.insert(SqlConstant.TestClientInfoMapper.insert, testClientInfo);
	}

	/**
	 * 删除测试子账号
	 * 
	 * @param uin
	 * @return
	 * @throws Exception
	 */
	public int deleteByUin(int uin) throws Exception {
		return this.delete(SqlConstant.TestClientInfoMapper.delete, uin);
	}

}