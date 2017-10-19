/**
 * 
 */
package com.flypaas.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.flypaas.daocenter.CdrMyBatisDao;

@Repository
public class SessionPacketLossDaoImpl extends CdrMyBatisDao implements SessionPacketLossDao{
	private static final String GETSESSIONPACKETLOSS="getSessionPacketLoss";

	@Override
	public List<Map<String, Object>> getSessionPacketLoss(Map<String, Object> model) {
		return sqlSessionTemplate.selectList(GETSESSIONPACKETLOSS, model);
	}
}
