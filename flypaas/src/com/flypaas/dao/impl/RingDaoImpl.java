package com.flypaas.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.RingDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.Ring;
@Repository
public class RingDaoImpl extends MyBatisDao implements RingDao {

	private static final String GETRING="getRing";
	private static final String ADD="addRing";
	private static final String GETINFO="getRingByInfo";
	private static final String GETRINGBYIDSID="getRingByIdSid";
	private static final String UPDATE="updateRing";
	private static final String DELETE="deleteRing";
	
	public List<Ring> getRingListBySid(Ring ring) {
		return sqlSessionTemplate.selectList(GETRING,ring);
	}

	public void add(Ring ring) {
		sqlSessionTemplate.insert(ADD, ring);
	}

	public Ring get(Ring ring) {
		return sqlSessionTemplate.selectOne(GETINFO, ring);
	}
	public Ring getRingByIdSid(Ring ring) {
		return sqlSessionTemplate.selectOne(GETRINGBYIDSID, ring);
	}

	public void update(Ring ring) {
		sqlSessionTemplate.update(UPDATE, ring);
	}

	public void delete(Ring ring) {
		sqlSessionTemplate.delete(DELETE, ring);
	}

}
