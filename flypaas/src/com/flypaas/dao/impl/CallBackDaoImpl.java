package com.flypaas.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.flypaas.dao.CallBackDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.TbSrvCallback;
@Repository
public class CallBackDaoImpl extends MyBatisDao implements CallBackDao {
	private static final String ADD="addCb" ;
	private static final String DELETE="deleteCbByAppSid" ;
	private static final String GET="getCbByAppSid" ;
	
	public void add(TbSrvCallback cb) {
		sqlSessionTemplate.insert(ADD,cb);
	}
	public void delete(String appSid) {
		sqlSessionTemplate.delete(DELETE, appSid) ;
	}
	public List<TbSrvCallback> get(String appSid) {
		return sqlSessionTemplate.selectList(GET, appSid) ;
	}

}
