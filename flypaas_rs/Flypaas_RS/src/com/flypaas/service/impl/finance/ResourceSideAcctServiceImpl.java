package com.flypaas.service.impl.finance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flypaas.dao.TbRsBillAcctMapper;
import com.flypaas.model.TbRsBillAcct;
import com.flypaas.service.finance.ResourceSideAcctService;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年1月11日 上午11:24:53
* 类说明
*/
@Service("/resourceSideAcctServiceImpl")
public class ResourceSideAcctServiceImpl implements ResourceSideAcctService{
	@Autowired
	private TbRsBillAcctMapper tbRsBillAcctMapper;
	
	
	@Override
	public List<TbRsBillAcct> queryResourceSideTDC(String netSid, String date) {
		
		return tbRsBillAcctMapper.queryResourceSideTDC(netSid, date);
	}

	@Override
	public List<TbRsBillAcct> queryResourceSideTMC(String netSid, String date) {
		
		return tbRsBillAcctMapper.queryResourceSideTMC(netSid, date);
	}

}
