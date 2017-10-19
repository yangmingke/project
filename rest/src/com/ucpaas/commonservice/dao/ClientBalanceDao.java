package com.ucpaas.commonservice.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ucpaas.commonservice.constant.SqlConstant;
import com.ucpaas.commonservice.dao.base.ClientNode1Dao;
import com.ucpaas.commonservice.dao.base.ClientNode2Dao;
import com.ucpaas.commonservice.model.ClientBalanceInfo;


@Repository("clientBalanceDao")
public class ClientBalanceDao {
	
	@Resource(name="clientNode1Dao")
	private ClientNode1Dao clientNode1Dao;
	
	@Resource(name="clientNode2Dao")
	private ClientNode2Dao clientNode2Dao;
	

	/**
	 * 更新子账户余额记录
	 * @param clientBalanceInfo		子账户余额
	 * @param dbNode				分库信息
	 * @return
	 * @throws Exception
	 */
	public int updateByClientNumber(ClientBalanceInfo clientBalanceInfo,String dbNode) throws Exception{
		if("0".equals(dbNode)){
			//如果是0节点，用ClientNode1Dao数据源
			return this.clientNode1Dao.update(SqlConstant.ClientBalanceInfoMapper.updateByClientNumber, clientBalanceInfo);
		}else{
			//如果是1节点，用ClientNode2Dao数据源
			return this.clientNode2Dao.update(SqlConstant.ClientBalanceInfoMapper.updateByClientNumber, clientBalanceInfo);

		}
		
	}
	
	/**
	 * 根据ClientNumber查询记录
	 * @param parameterMap
	 * @param dbNode
	 * @return
	 * @throws Exception
	 */
	public ClientBalanceInfo selectByClientNumber(Map<String,Object> parameterMap,String dbNode) throws Exception{
		if("0".equals(dbNode)){
			//如果是0节点，用ClientNode1Dao数据源
			return this.clientNode1Dao.selectOne(SqlConstant.ClientBalanceInfoMapper.selectByClientNumber, parameterMap);
		}else{
			//如果是1节点，用ClientNode2Dao数据源
			return this.clientNode2Dao.selectOne(SqlConstant.ClientBalanceInfoMapper.selectByClientNumber, parameterMap);
		}
		
	}
	
	
	/**
	 * 根据uin更新子账户余额记录
	 * @param clientBalanceInfo		子账户余额
	 * @param dbNode				分库信息
	 * @return
	 * @throws Exception
	 */
	public int updateByUin(ClientBalanceInfo clientBalanceInfo,String dbNode) throws Exception{
		if("0".equals(dbNode)){
			//如果是0节点，用ClientNode1Dao数据源
			return this.clientNode1Dao.update(SqlConstant.ClientBalanceInfoMapper.updateByUin, clientBalanceInfo);
		}else{
			//如果是1节点，用ClientNode2Dao数据源
			return this.clientNode2Dao.update(SqlConstant.ClientBalanceInfoMapper.updateByUin, clientBalanceInfo);

		}
		
	}
	
	
	/**
	 * 插入子账号余额信息
	 * 2015-09-01添加
	 * @param clientBalanceInfo
	 * @param dbNode
	 * @return
	 * @throws Exception
	 */
	public int insert(ClientBalanceInfo clientBalanceInfo,String dbNode) throws Exception{
		if("0".equals(dbNode)){
			//如果是0节点，用ClientNode1Dao数据源
			return this.clientNode1Dao.insert(SqlConstant.ClientBalanceInfoMapper.insert, clientBalanceInfo);
		}else{
			//如果是1节点，用ClientNode2Dao数据源
			return this.clientNode2Dao.insert(SqlConstant.ClientBalanceInfoMapper.insert, clientBalanceInfo);
		}
	}
	
	
	/**
	 * 根据clientnumber给子账号充值
	 * @param clientBalanceInfo
	 * @param dbNode
	 * @return
	 * @throws Exception
	 */
	public int chargeClientBalanceByClientNumber(Map<String,Object> parameterMap,String dbNode) throws Exception{
		if("0".equals(dbNode)){
			//如果是0节点，用ClientNode1Dao数据源
			return this.clientNode1Dao.update(SqlConstant.ClientBalanceInfoMapper.chargeClientBalanceByClientNumber, parameterMap);
		}else{
			//如果是1节点，用ClientNode2Dao数据源
			return this.clientNode2Dao.update(SqlConstant.ClientBalanceInfoMapper.chargeClientBalanceByClientNumber, parameterMap);

		}
		
	}
	
	public int deleteByUin(ClientBalanceInfo clientBalanceInfo,String dbNode) throws Exception{
		if("0".equals(dbNode)){
			//如果是0节点，用ClientNode1Dao数据源
			return this.clientNode1Dao.delete(SqlConstant.ClientBalanceInfoMapper.deleteByUin, clientBalanceInfo);
		}else{
			//如果是1节点，用ClientNode2Dao数据源
			return this.clientNode2Dao.delete(SqlConstant.ClientBalanceInfoMapper.deleteByUin, clientBalanceInfo);
		}
	}
	
	
	
	
	
	
}