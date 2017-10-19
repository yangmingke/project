package com.network.monitor.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.network.monitor.dao.StatDao;
import com.network.monitor.model.Menu;
import com.network.monitor.util.web.AuthorityUtils;

/**
 * 菜单业务
 * 
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {
	@Autowired
	private StatDao dao;
	public static final ConcurrentHashMap<Long, String> titleMap = new ConcurrentHashMap<Long, String>();

	@Override
	public Map<String, Object> getHeaderMenu() {
		Map<String, Object> params = new HashMap<String, Object>();
		// params.put("level", 2);
		params.put("role_id", AuthorityUtils.getLoginRoleId());
		List<Menu> menus = dao.selectList("menu.getRoleMenu", params);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("menuRoot", toTree(menus));// 1级菜单
		return data;
	}

	public static final Menu toTree(List<Menu> menus) {
		Menu result = new Menu();
		result.setMenu_id(0L);
		Map<Long, Menu> map = new HashMap<Long, Menu>();
		map.put(0l, result);
		Long mid = null;
		if (null != menus && !menus.isEmpty()) {
			Menu tmp = null;
			List<Menu> noadd = new ArrayList<Menu>();
			for (Menu menu : menus) {
				map.put(menu.getMenu_id(), menu);
				tmp = map.get(menu.getParent_id());
				if (null != tmp) {
					tmp.addSubMenu(menu);
				} else {
					noadd.add(menu);
				}
			}
			for (Menu menu : noadd) {
				tmp = map.get(menu.getParent_id());
				if (null != tmp) {
					tmp.addSubMenu(menu);
				}
			}
			StringBuffer sb = new StringBuffer();
			boolean isLast = false;
			for (Menu menu : menus) {
				mid = menu.getMenu_id();
				if (!titleMap.containsKey(mid)) {
					sb.setLength(0);
					tmp = menu;
					isLast = true;
					do {
						sb.insert(0, "<a " + (isLast ? "class=\"current\"" : "") + " >" + tmp.getMenu_name() + "</a>");
						isLast = false;
					} while (null != (tmp = map.get(tmp.getParent_id())) && !result.equals(tmp));
					titleMap.put(menu.getMenu_id(), sb.toString());
				}
			}

		}
		return result;
	}
}
