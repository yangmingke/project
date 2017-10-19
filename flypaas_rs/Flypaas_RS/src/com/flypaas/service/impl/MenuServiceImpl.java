package com.flypaas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flypaas.dao.TbRsMenuMapper;
import com.flypaas.model.TbRsMenu;
import com.flypaas.service.MenuService;

@Service("/menuServiceImpl")
public class MenuServiceImpl implements MenuService{
	@Autowired
	private TbRsMenuMapper tbRsMenuMapper;

	@Override
	public List<TbRsMenu> queryMenu_1(String sid) {
		return tbRsMenuMapper.queryMenu_1(sid);
	}

	@Override
	public List<TbRsMenu> queryMenu_2(String sid) {
		return tbRsMenuMapper.queryMenu_2(sid);
	}
	

}
