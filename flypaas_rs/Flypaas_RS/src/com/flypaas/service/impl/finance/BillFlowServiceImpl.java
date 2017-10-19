package com.flypaas.service.impl.finance;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flypaas.dao.TbRsAccountBalanceMapper;
import com.flypaas.dao.TbRsBillFlowMapper;
import com.flypaas.model.TbRsAccountBalance;
import com.flypaas.model.TbRsAccountInfo;
import com.flypaas.model.TbRsBillFlow;
import com.flypaas.service.finance.AccountBalanceService;
import com.flypaas.service.finance.BillFlowService;
import com.flypaas.util.DateUtil;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年1月11日 下午7:08:59
* 类说明
*/
@Service("/billFlowServiceImpl")
public class BillFlowServiceImpl implements BillFlowService{
	@Autowired
	private TbRsBillFlowMapper tbRsBillFlowMapper;
	
	@Autowired
	private TbRsAccountBalanceMapper tbRsAccountBalanceMapper;
	
	@Autowired
	private AccountBalanceService accountBalanceServiceImpl;
	
	
	@Override
	public List<TbRsBillFlow> queryAllBillFlowBynetSid(String netSid,String dateMin,String dateMax) {
		Map<String, String> para = new HashMap<String, String>();
		para.put("netSid", netSid);
		para.put("dateMin", dateMin);
		para.put("dateMax", DateUtil.add(dateMax, 1));
		return tbRsBillFlowMapper.queryAllBillFlowBynetSid(para);
	}


	@Override
	public int insertSelective(TbRsBillFlow tbRsBillFlow, TbRsAccountInfo tbRsUser, double balance, long money) {
		TbRsAccountBalance tbRsAccountBalance = accountBalanceServiceImpl.queryAccountBalance(tbRsUser.getNetSid());
		if(tbRsAccountBalance.getBalance() != balance){//钱包余额改变，提现申请失败
			return -1;
		}
		tbRsAccountBalance.setUpdateDate(new Date());
		tbRsAccountBalance.setBalance(BigDecimal.valueOf(balance).subtract(BigDecimal.valueOf(money)).doubleValue());
		tbRsBillFlowMapper.insertSelective(tbRsBillFlow);
		int temp = tbRsAccountBalanceMapper.updateByPrimaryKeySelective(tbRsAccountBalance);
		return temp;
	}


	@Override
	public TbRsBillFlow selectByPrimaryKey(Long id) {

		return tbRsBillFlowMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TbRsBillFlow tbRsBillFlow,TbRsAccountBalance tbRsAccountBalance) {
		tbRsBillFlowMapper.updateByPrimaryKeySelective(tbRsBillFlow);
		int temp = tbRsAccountBalanceMapper.updateByPrimaryKeySelective(tbRsAccountBalance);
		return temp;
	}

}
