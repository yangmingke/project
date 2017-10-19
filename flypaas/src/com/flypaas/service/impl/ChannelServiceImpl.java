package com.flypaas.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.daocenter.DaoCenter;
import com.flypaas.service.ChannelService;
@Service
@Transactional
public class ChannelServiceImpl extends DaoCenter implements ChannelService {

	public void add(Map<String, Object> map) {
		channelDao.add(map);
	}

}
