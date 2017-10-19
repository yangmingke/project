package com.ucpaas.commonservice.dao;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ucpaas.commonservice.dao.base.ClientNode1Dao;


@Repository("allocUinDao")
public class AllocUinDao {
	private static final Logger log = LoggerFactory.getLogger(AllocUinDao.class);
	
	@Resource(name="clientNode1Dao")
	private ClientNode1Dao clientNode1Dao;

	
	/**
	 * uin分配器
	 * 分配原则：更新uin=uin+1，返回LAST_INSERT_ID()为分配的uin
	 * 
	 * @param sectionId 分片id
	 * @return
	 * @throws Exception
	 */
	public Integer selectUinBySectionId(int sectionId) throws Exception {
		long startTime = System.currentTimeMillis(); 
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sectionid", sectionId);
		this.clientNode1Dao.selectOne("AllocUinMapper.call_distribute_uin", paramMap);
		Integer uin =  (Integer) paramMap.get("po_uin");
		log.info("【uin分配】uin={},timeCount={}",uin,(System.currentTimeMillis()-startTime));
		if(null == uin || uin == 0){
			throw new RuntimeException("【调存储过程分配uin】异常,uin="+uin);
		}
		return uin;
	}
	

}
