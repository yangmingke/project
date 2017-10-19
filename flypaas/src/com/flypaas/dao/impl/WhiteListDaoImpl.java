package com.flypaas.dao.impl;
import org.springframework.stereotype.Repository;
import com.flypaas.dao.WhiteListDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.TbSrvWhiteList;
@Repository
public class WhiteListDaoImpl extends MyBatisDao implements WhiteListDao {
	private static final String ADD="addWhiteList" ;
	private static final String GET="getWhiteListById" ;
	private static final String UPDATE="updateWhiteList";
	private static final String DELETE="deleteWhiteById";

	public void add(TbSrvWhiteList whitelist) {
		sqlSessionTemplate.insert(ADD, whitelist);
	}

	public TbSrvWhiteList get(String id) {
		return sqlSessionTemplate.selectOne(GET, id);
	}

	public void update(TbSrvWhiteList whiteList) {
		sqlSessionTemplate.update(UPDATE, whiteList);
	}
	public void delete(String appSid){
		sqlSessionTemplate.delete(DELETE,appSid);
	}
}
