package com.flypaas.service.impl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.TbSrvWhiteList;
import com.flypaas.service.WhiteListService;
@Service
@Transactional
public class WhiteListServiceImpl extends DaoCenter implements WhiteListService {
	
	public void add(TbSrvWhiteList whiteList) {
		whiteListDao.add(whiteList);
	}
	public TbSrvWhiteList get(String id){
		return whiteListDao.get(id);
	}
	public void update(TbSrvWhiteList whiteList) {
		whiteListDao.update(whiteList);
	}
	public void delete(String appSid){
		whiteListDao.delete(appSid);
	}
}
