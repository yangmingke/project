package com.flypaas.service.impl.city;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flypaas.dao.TbRsIspMapper;
import com.flypaas.model.TbRsIsp;
import com.flypaas.service.city.IspService;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年1月14日 下午5:18:09
* 类说明
*/
@Service("/IspServiceImpl")
public class IspServiceImpl implements IspService{
	@Autowired
	private TbRsIspMapper tbRsIspMapper;
	
	@Override
	public List<TbRsIsp> queryAllIsp() {
		
		return tbRsIspMapper.queryAllIsp();
	}

}
