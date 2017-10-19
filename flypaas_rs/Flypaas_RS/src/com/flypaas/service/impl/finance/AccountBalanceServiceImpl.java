package com.flypaas.service.impl.finance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flypaas.dao.TbRsAccountBalanceMapper;
import com.flypaas.model.TbRsAccountBalance;
import com.flypaas.service.finance.AccountBalanceService;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2016年12月27日 下午7:23:03
* 类说明
*/

@Service("/accountBalanceServiceImpl")
public class AccountBalanceServiceImpl implements AccountBalanceService{
	@Autowired
	private TbRsAccountBalanceMapper  tbRsAccountBalanceMapper;

	@Override
	public TbRsAccountBalance queryAccountBalanceBynetSid(String netSid) {
		
		
		return tbRsAccountBalanceMapper.queryAccountBalanceBynetSid(netSid);
	}
	
	@Override
	public TbRsAccountBalance queryAccountBalance(String netSid) {
		
		
		return tbRsAccountBalanceMapper.queryAccountBalance(netSid);
	}

	@Override
	public int updateByPrimaryKeySelective(TbRsAccountBalance tbRsAccountBalance) {
		
		return tbRsAccountBalanceMapper.updateByPrimaryKeySelective(tbRsAccountBalance);
	}

	@Override
	public int insertSelective(TbRsAccountBalance record) {
		
		return 0;
	}
}
