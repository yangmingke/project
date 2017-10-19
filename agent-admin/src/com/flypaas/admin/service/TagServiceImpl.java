package com.flypaas.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.constant.PackageConstant;
import com.flypaas.admin.constant.SysConstant;
import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.service.data.AuthorityService;

/**
 * 自定义标签业务
 * 
 * @author xiejiaan
 */
@Service
@Transactional
public class TagServiceImpl implements TagService {
	@Autowired
	private MasterDao dao;
	@Autowired
	private AuthorityService authorityService;

	@Override
	public List<Map<String, Object>> getTagDataForDictionary(String dictionaryType, Map<String, Object> sqlParams) {
		sqlParams.put("dictionaryType", dictionaryType);
		return dao.getSearchList("tag.queryDictionary", sqlParams);
	}

	@Override
	public List<Map<String, Object>> getTagDataForSql(String sqlId, Map<String, Object> sqlParams) {
		sqlParams.put("money_rate", SysConstant.money_rate);
		sqlParams.put("event_id_1017", PackageConstant.event_id_1017);
		return dao.getSearchList("tag." + sqlId, sqlParams);
	}

	@Override
	public boolean isAuthority(int menuId) {
		return authorityService.isAuthority(menuId);
	}
}
