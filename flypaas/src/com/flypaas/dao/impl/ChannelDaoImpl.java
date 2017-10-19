package com.flypaas.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.ChannelDao;
import com.flypaas.daocenter.StatisticsMyBatisDao;
@Repository
public class ChannelDaoImpl extends StatisticsMyBatisDao implements ChannelDao {

	private static final String ADD="addChannel";
	public void add(Map<String, Object> map) {
		sqlSessionTemplate.insert(ADD, map);
	}

}
