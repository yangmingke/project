package com.flypaas.service.impl.city;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flypaas.dao.TbRsAreaMapper;
import com.flypaas.model.TbRsArea;
import com.flypaas.service.city.AreaService;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年1月6日 下午4:40:56
* 类说明
*/
@Service("/areaServiceImpl")
public class AreaServiceImpl implements AreaService{
	@Autowired
	private TbRsAreaMapper tbRsAreaMapper;
	@Override
	public List<TbRsArea> queryAllArea() {
		
		return tbRsAreaMapper.queryAllArea();
	}

}
