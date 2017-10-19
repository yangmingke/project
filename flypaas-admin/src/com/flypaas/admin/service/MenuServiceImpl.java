package com.flypaas.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.util.web.AuthorityUtils;

/**
 * 菜单业务
 * 
 * @author xiejiaan
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MasterDao dao;

	@Override
	public Map<String, Object> getHeaderMenu() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("level", 1);
		params.put("role_id", AuthorityUtils.getLoginRoleId());
		List<Map<String, Object>> menu1 = dao.getSearchList("menu.getRoleMenu", params);
		params.put("level", 2);
		List<Map<String, Object>> menu2 = dao.getSearchList("menu.getRoleMenu", params);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("menu1", menu1);// 1级菜单
		data.put("menu2", menu2);// 2级菜单
		return data;
	}

	@Override
	public Map<String, Object> getSideMenu(String parentId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("level", 3);
		params.put("role_id", AuthorityUtils.getLoginRoleId());
		params.put("parent_id", parentId);
		List<Map<String, Object>> menu3 = dao.getSearchList("menu.getRoleMenu", params);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("menu3", menu3);// 3级菜单
		return data;
	}

}
