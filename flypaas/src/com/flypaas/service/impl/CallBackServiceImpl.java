package com.flypaas.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.TbSrvCallback;
import com.flypaas.service.CallBackService;
@Service
@Transactional
public class CallBackServiceImpl extends DaoCenter implements CallBackService {
	public void add(TbSrvCallback cb) {
		callBackDao.add(cb);
	}

	public void delete(String appSid) {
		callBackDao.delete(appSid);
	}

	public List<TbSrvCallback> get(String appSid) {
		return callBackDao.get(appSid);
	}
}
