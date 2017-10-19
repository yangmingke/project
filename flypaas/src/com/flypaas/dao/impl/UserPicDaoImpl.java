package com.flypaas.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.UserPicDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.UserPic;
@Repository
public class UserPicDaoImpl extends MyBatisDao implements UserPicDao {
	private static final String ISEXIST="isExist" ;
	private static final String ADDUSERPIC="addUserPic";
	private static final String GET="getUserPic" ;
	private static final String UPDATE="updateUserPic";
	private static final String DELETE="deleteUserPic";
	private static final String IDNBRENABLE="idNbrEnable";
	private static final String IDNBRANDSIDENABLE="idNbrAndSidEnable";
	private static final String GETUSERPICBYSIDANDTYPE="getUserPicBySidAndType";
	public int isExist(String sid) {
		return sqlSessionTemplate.selectOne(ISEXIST, sid);
	}

	public void add(UserPic userPic) {
		sqlSessionTemplate.selectOne(ADDUSERPIC, userPic);
	}

	public UserPic getUserPic(String sid) {
		return sqlSessionTemplate.selectOne(GET, sid);
	}

	public List<UserPic> getUserPicList(String sid) {
		return sqlSessionTemplate.selectList(GET, sid);
	}

	public void update(UserPic userPic) {
		sqlSessionTemplate.update(UPDATE, userPic);
	}

	public void delete(String sid) {
		sqlSessionTemplate.delete(DELETE, sid);
	}

	public int idNbrEnable(String idNbr) {
		return sqlSessionTemplate.selectOne(IDNBRENABLE,idNbr);
	}
	
	public int idNbrEnable(UserPic userPic) {
		return sqlSessionTemplate.selectOne(IDNBRANDSIDENABLE,userPic);
	}

	public UserPic getUserPicBySidAndType(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne(GETUSERPICBYSIDANDTYPE, map);
	}

}
