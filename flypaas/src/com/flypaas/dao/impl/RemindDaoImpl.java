package com.flypaas.dao.impl;
import org.springframework.stereotype.Repository;

import com.flypaas.dao.RemindDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.Remind;
@Repository
public class RemindDaoImpl extends MyBatisDao implements RemindDao {
	private static final String ADD="addBalanceRemind";
	private static final String GET="getCountBySid";
	private static final String GETREMIND="getRemindBySid";
	private static final String UPDATE="updateRemindBySid";
	public void add(Remind remind) {
		sqlSessionTemplate.insert(ADD,remind);
	}
	public int get(String sid) {
		return  sqlSessionTemplate.selectOne(GET,sid);
	}
	public void update(Remind remind) {
		sqlSessionTemplate.update(UPDATE, remind);
	}
	public Remind getRemind(String sid) {
		return sqlSessionTemplate.selectOne(GETREMIND, sid);
	}

}
