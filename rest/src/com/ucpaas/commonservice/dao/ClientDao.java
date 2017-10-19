package com.ucpaas.commonservice.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ucpaas.commonservice.constant.SqlConstant;
import com.ucpaas.commonservice.dao.base.ClientNode1Dao;
import com.ucpaas.commonservice.dao.base.ClientNode2Dao;
import com.ucpaas.commonservice.model.ClientInfo;

/**
 * 子账号分表clientDao
 * 
 * @author luke
 * 
 */
@Repository("clientDao")
public class ClientDao {
	private static final Logger log = LoggerFactory.getLogger(ClientDao.class);
	
	
	@Resource(name="clientNode1Dao")
	private ClientNode1Dao clientNode1Dao;
	
	@Resource(name="clientNode2Dao")
	private ClientNode2Dao clientNode2Dao;

	/**
	 * 根据主键查询client记录
	 * @param parameterMap
	 * @param dbNode
	 * @return
	 * @throws Exception
	 */
	public ClientInfo selectByUin(Map<String,Object> parameterMap,String dbNode) throws Exception {
		log.debug("parameterMap={},dbNode={}",parameterMap, dbNode);
		if("0".equals(dbNode)){
			//如果是0节点，用ClientNode1Dao数据源
			return this.clientNode1Dao.selectOne(SqlConstant.ClientInfoMapper.selectByUin, parameterMap);
		}else{
			//如果是1节点，用ClientNode2Dao数据源
			return this.clientNode2Dao.selectOne(SqlConstant.ClientInfoMapper.selectByUin, parameterMap);
		}
	}

	
	/**
	 * 根据ClientNumber查询client记录
	 * @param parameterMap
	 * @param dbNode
	 * @return
	 * @throws Exception
	 */
	public ClientInfo selectByClientNumber(Map<String,Object> parameterMap,String dbNode) throws Exception{
		if("0".equals(dbNode)){
			//如果是0节点，用ClientNode1Dao数据源
			return this.clientNode1Dao.selectOne(SqlConstant.ClientInfoMapper.selectByClientNumber, parameterMap);
		}else{
			//如果是1节点，用ClientNode2Dao数据源
			return this.clientNode2Dao.selectOne(SqlConstant.ClientInfoMapper.selectByClientNumber, parameterMap);
		}
	}
	
	
	/**
	 * 根据ClientNumber更新client记录
	 * @param clientInfo
	 * @param dbNode
	 * @return
	 * @throws Exception
	 */
	public int updateByClientNumber(ClientInfo clientInfo,String dbNode) throws Exception {
		if("0".equals(dbNode)){
			//如果是0节点，用ClientNode1Dao数据源
			return this.clientNode1Dao.update(SqlConstant.ClientInfoMapper.updateByClientNumber, clientInfo);
		}else{
			//如果是1节点，用ClientNode2Dao数据源
			return this.clientNode2Dao.update(SqlConstant.ClientInfoMapper.updateByClientNumber, clientInfo);
		}
	}
	
	/**
	 * 根据uin更新client记录
	 * @param clientInfo
	 * @param dbNode
	 * @return
	 * @throws Exception
	 */
	public int updateByUin(ClientInfo clientInfo,String dbNode) throws Exception{
		if("0".equals(dbNode)){
			//如果是0节点，用ClientNode1Dao数据源
			return this.clientNode1Dao.update(SqlConstant.ClientInfoMapper.updateByUin, clientInfo);
		}else{
			//如果是1节点，用ClientNode2Dao数据源
			return this.clientNode2Dao.update(SqlConstant.ClientInfoMapper.updateByUin, clientInfo);
		}
	}
	
	/**
	 * 插入子账号信息
	 * @param clientInfo
	 * @param dbNode
	 * @return
	 * @throws Exception
	 */
	public int insert(ClientInfo clientInfo,String dbNode) throws Exception{
		if("0".equals(dbNode)){
			//如果是0节点，用ClientNode1Dao数据源
			return this.clientNode1Dao.insert(SqlConstant.ClientInfoMapper.insert, clientInfo);
		}else{
			//如果是1节点，用ClientNode2Dao数据源
			return this.clientNode2Dao.insert(SqlConstant.ClientInfoMapper.insert, clientInfo);
		}
	}
	
	
	public int deleteByUin(ClientInfo clientInfo,String dbNode) throws Exception{
		if("0".equals(dbNode)){
			//如果是0节点，用ClientNode1Dao数据源
			return this.clientNode1Dao.delete(SqlConstant.ClientInfoMapper.deleteByUin, clientInfo);
		}else{
			//如果是1节点，用ClientNode2Dao数据源
			return this.clientNode2Dao.delete(SqlConstant.ClientInfoMapper.deleteByUin, clientInfo);
		}
	}


	public List<ClientInfo> selectByAppSidAndIndex(Map<String, Object> parameterMap, String dbNode) {
		
		log.debug("parameterMap={},dbNode={}",parameterMap, dbNode);
		if("0".equals(dbNode)){
			//如果是0节点，用ClientNode1Dao数据源
			return this.clientNode1Dao.selectList(SqlConstant.ClientInfoMapper.selectByAppSidAndIndex, parameterMap);
		}else{
			//如果是1节点，用ClientNode2Dao数据源
			return this.clientNode2Dao.selectList(SqlConstant.ClientInfoMapper.selectByAppSidAndIndex, parameterMap);
		}
	}
	
	
	
	
	

}