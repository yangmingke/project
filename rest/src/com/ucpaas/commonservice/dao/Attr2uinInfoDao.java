package com.ucpaas.commonservice.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ucpaas.commonservice.constant.SqlConstant;
import com.ucpaas.commonservice.dao.base.ClientNode1Dao;
import com.ucpaas.commonservice.dao.base.ClientNode2Dao;
import com.ucpaas.commonservice.model.Attr2uinInfo;

/**
 * 反向表dao
 * @author luke
 *
 */
@Repository("attr2uinInfoDao")
public class Attr2uinInfoDao {
	private static final Logger log = LoggerFactory.getLogger(Attr2uinInfoDao.class);
	
	
	@Resource(name="clientNode1Dao")
	private ClientNode1Dao clientNode1Dao;
	
	@Resource(name="clientNode2Dao")
	private ClientNode2Dao clientNode2Dao;



    public Attr2uinInfo selectByMap(Map<String,Object> parameterMap,String dbNode) throws Exception{
    	log.debug("parameterMap={},dbNode={}",parameterMap, dbNode);
		if("0".equals(dbNode)){
			//如果是0节点，用ClientNode1Dao数据源
			return this.clientNode1Dao.selectOne(SqlConstant.Attr2uinInfoMapper.selectByMap, parameterMap);
		}else{
			//如果是1节点，用ClientNode2Dao数据源
			return this.clientNode2Dao.selectOne(SqlConstant.Attr2uinInfoMapper.selectByMap, parameterMap);
		}
    	
    }
    
    
    public int insert(Attr2uinInfo attr2uinInfo,String dbNode) throws Exception{
    	log.debug("attr2uinInfo={},dbNode={}",attr2uinInfo, dbNode);
		if("0".equals(dbNode)){
			//如果是0节点，用ClientNode1Dao数据源
			return this.clientNode1Dao.insert(SqlConstant.Attr2uinInfoMapper.insert, attr2uinInfo);
		}else{
			//如果是1节点，用ClientNode2Dao数据源
			return this.clientNode2Dao.insert(SqlConstant.Attr2uinInfoMapper.insert, attr2uinInfo);
		}
    }
    
    /**
     * 根据attr和type删除反向表信息
     * @param attr2uinInfo
     * @param dbNode
     * @return
     * @throws Exception
     */
    public int deleteByAttrType(Attr2uinInfo attr2uinInfo,String dbNode) throws Exception{
    	log.debug("attr2uinInfo={},dbNode={}",attr2uinInfo, dbNode);
		if("0".equals(dbNode)){
			//如果是0节点，用ClientNode1Dao数据源
			return this.clientNode1Dao.delete(SqlConstant.Attr2uinInfoMapper.deleteByAttrType, attr2uinInfo);	
		}else{
			//如果是1节点，用ClientNode2Dao数据源
			return this.clientNode2Dao.delete(SqlConstant.Attr2uinInfoMapper.deleteByAttrType, attr2uinInfo);	
		}
    }
    
    /**
     * 根据uin和type删除反向表信息
     * @param attr2uinInfo
     * @param dbNode
     * @return
     * @throws Exception
     * 2016-03-22新增
     */
    public int deleteByUinType(Attr2uinInfo attr2uinInfo,String dbNode) throws Exception{
    	log.debug("attr2uinInfo={},dbNode={}",attr2uinInfo, dbNode);
		if("0".equals(dbNode)){
			//如果是0节点，用ClientNode1Dao数据源
			return this.clientNode1Dao.delete(SqlConstant.Attr2uinInfoMapper.deleteByUinType, attr2uinInfo);	
		}else{
			//如果是1节点，用ClientNode2Dao数据源
			return this.clientNode2Dao.delete(SqlConstant.Attr2uinInfoMapper.deleteByUinType, attr2uinInfo);	
		}
    }
    
    


}