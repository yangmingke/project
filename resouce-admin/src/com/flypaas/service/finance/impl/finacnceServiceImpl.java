package com.flypaas.service.finance.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.flypaas.cdr.dao.TrafficNodeConsumMapper;
import com.flypaas.constant.FlypaasConstant;
import com.flypaas.dao.TbRsAccountBalanceMapper;
import com.flypaas.dao.TbRsAccountInfoMapper;
import com.flypaas.dao.TbRsBillAcctRTPPMapper;
import com.flypaas.dao.TbRsBillFlowMapper;
import com.flypaas.model.TbRsAccountBalance;
import com.flypaas.model.TbRsBillFlow;
import com.flypaas.service.NoticeService;
import com.flypaas.service.finance.financeService;
import com.flypaas.util.PageContainer;
import com.flypaas.util.StrUtil;
import com.flypaas.util.TransformUtil;

/**
* @author 作者 yangmingke:
* @version 创建时间：2017年2月13日 下午6:50:55
* 类说明
*/
@Service("/finacnceServiceImpl")
public class finacnceServiceImpl implements financeService{
	//资源节点流量管理
	@Autowired
	private TbRsBillAcctRTPPMapper tbRsBillAcctRTPPMapper;
	//账号管理
	@Autowired
	private TbRsAccountBalanceMapper tbRsAccountBalanceMapper;
	//提现记录
	@Autowired
	private TbRsBillFlowMapper tbRsBillFlowMapper;
	@Autowired
	NoticeService noticeServiceImpl;
	@Autowired
	private TrafficNodeConsumMapper trafficNodeConsumMapper;
	@Autowired
	TbRsAccountInfoMapper tbRsAccountInfoMapper;
	
	@Override
	public PageContainer queryResourceFlowDay(String keyWord,String dateTime,int page) {
		PageContainer pageContainer = new PageContainer();
		Map<String,Object> para = new HashMap<String,Object>();
		dateTime = dateTime.replaceAll("-", "");
		para.put("keyWord", keyWord);
		para.put("dateTime", dateTime);
		para.put("month", dateTime.substring(0, 6));
		int total = trafficNodeConsumMapper.queryCountResourceFlowDay(para);//查询数据总数
		para = pageContainer.createParameter(para, page, total);
		List<Map<String,String>> result = trafficNodeConsumMapper.queryResourceFlowDay(para);
		pageContainer.setResultMap(result);
		return pageContainer;
	}
	
	@Override
	public int queryCount(String monthTime) {
		Map<String,Object> para = new HashMap<String,Object>();
		return tbRsBillAcctRTPPMapper.queryCount(para);
	}


	@Override
	public PageContainer queryResourceFlowMonth(String keyWord, String dateTime, int page) {
		PageContainer pageContainer = new PageContainer();
		Map<String,Object> para = new HashMap<String,Object>();
		dateTime = dateTime.replaceAll("-", "");
		para.put("keyWord", keyWord);
		para.put("dateTime", dateTime);
		int total = trafficNodeConsumMapper.queryCountResourceFlowMonth(para);//查询数据总数
		para = pageContainer.createParameter(para, page, total);
		List<Map<String,String>> result = trafficNodeConsumMapper.queryResourceFlowMonth(para);
		pageContainer.setResultMap(result);
		return pageContainer;
	}

	@Override
	public Map<String,String> statisticsFlowDay(String dateTime) {
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("dateTime", dateTime);
		return tbRsBillAcctRTPPMapper.statisticsFlowDay(para);
	}
	
/*--------------------------------------------------资源方账单表-------------------------------------------------------------*/
	
	@Override
	public PageContainer queryResourceSideFlowDay(List<String> sidList, String dateTime, int page) {
		PageContainer pageContainer = new PageContainer();
		Map<String,Object> para = new HashMap<String,Object>();
		dateTime = dateTime.replaceAll("-", "");
		para.put("sidList", sidList);
		para.put("dateTime", dateTime);
		para.put("month", dateTime.substring(0, 6));
		int total = trafficNodeConsumMapper.queryResourceSideCount(para);//查询数据总数
		para = pageContainer.createParameter(para, page, total);
		List<Map<String,String>> result = trafficNodeConsumMapper.queryResourceSideFlowDay(para);
		pageContainer.setResultMap(result);
		return pageContainer;
	}

	@Override
	public PageContainer queryResourceSideFlowMonth(List<String> sidList, String dateTime, int page) {
		PageContainer pageContainer = new PageContainer();
		Map<String,Object> para = new HashMap<String,Object>();
		dateTime = dateTime.replaceAll("-", "");
		para.put("sidList", sidList);
		para.put("dateTime", dateTime);
		int total = trafficNodeConsumMapper.queryResourceSideCountFlowMonth(para);//查询数据总数
		para = pageContainer.createParameter(para, page, total);
		List<Map<String,String>> result = trafficNodeConsumMapper.queryResourceSideFlowMonth(para);
		pageContainer.setResultMap(result);
		return pageContainer;
	}

	
/*-------------------------------------------------账号管理-------------------------------------------------------*/	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageContainer queryAccount(Map<String, Object> para, int page) {
		int total = tbRsAccountBalanceMapper.queryCount(para);//查询数据总数
		PageContainer pageContainer = new PageContainer();
		para = pageContainer.createParameter(para, page, total);
		 
		List<Map<String,String>> resultMap = tbRsAccountBalanceMapper.queryAccount(para);
		for(Map account : resultMap){
			Double balance = Double.parseDouble(account.get("balance").toString());
			account.put("balance", TransformUtil.doubleDown2Str(balance, 2));
			account.put("fee1", account.get("fee1") == null ? 0 : account.get("fee1"));
			account.put("fee2", account.get("fee2") == null ? 0 : account.get("fee2"));
		}
		pageContainer.setResultMap(resultMap);
		
		return pageContainer;
	}


	@Override
	public int unLockorLock(String netSid, String status) {
		if(StrUtil.isEmpty(netSid) || StrUtil.isEmpty(status)){
			return 0;
		}
		TbRsAccountBalance balance = new TbRsAccountBalance();
		balance.setNetSid(netSid);
		balance.setEnableFlag(status);
		int result = tbRsAccountBalanceMapper.editAccountStatus(balance);
		
		return result;
	}

	@Override
	public Map<String, Object> enchashment(String netSid) {
		Map<String, Object> info = tbRsAccountBalanceMapper.getEnchashment(netSid);
		double balance =  Double.parseDouble(info.get("balance").toString());
		info.put("balanceShow", TransformUtil.doubleDown2Str(balance, 2));
		return info;
	}

	@Override
	public int createApply(TbRsBillFlow tbRsBillFlow) {
		//计算提款后金额
		double balance = tbRsBillFlow.getBalance();
		String netSid = tbRsBillFlow.getMainSid();
		//采用悲观锁检测提现期间是否金额是否遭到修改
		TbRsAccountBalance accountBalance = tbRsAccountBalanceMapper.queryAccountBalance(netSid);
		if(accountBalance.getBalance() != balance){
			return -1;
		}
		
		long fee = tbRsBillFlow.getActualFee(); 
		balance =  balance - fee;
		tbRsBillFlow.setBalance(balance);
		tbRsBillFlow.setCreateDate(new Date());
		tbRsBillFlow.setOperUser(FlypaasConstant.ADMIN_NAME);
		tbRsBillFlow.setStatus(FlypaasConstant.FLOW_APPLY);
		tbRsBillFlowMapper.insertSelective(tbRsBillFlow);
		
		TbRsAccountBalance tbRsAccountBalance = new TbRsAccountBalance();
		tbRsAccountBalance.setNetSid(netSid);
		tbRsAccountBalance.setUpdateDate(new Date());
		tbRsAccountBalance.setBalance(balance);
		int count = tbRsAccountBalanceMapper.updateByPrimaryKeySelective(tbRsAccountBalance);
		if(count != 1){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//数据库回滚
		}
		//通知资源方
		noticeServiceImpl.createNotice("发起提现申请", "请注意，平台管理员为您发起一笔提现申请，提现金额为" + fee +"元，请注意查看账户。", netSid, FlypaasConstant.SYSTEM_NOTICE);
		return count;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PageContainer queryAllBillFlow(Map<String, Object> para){
		int total = tbRsBillFlowMapper.queryCount(para);//查询数据总数
		para.put("total", total);
		PageContainer pageContainer = new PageContainer();
		para = pageContainer.createParameter(para);
		 
		List<Map<String,String>> resultMap = tbRsBillFlowMapper.queryAllBillFlow(para);
		for(Map flow : resultMap){
			double balance = Double.parseDouble(flow.get("balance").toString());
			double actual_fee = Double.parseDouble(flow.get("actual_fee").toString());
			flow.put("balance", TransformUtil.doubleDown2Str(balance, 2));
			flow.put("actual_fee", TransformUtil.doubleDown2Str(actual_fee, 2));
		}
		pageContainer.setResultMap(resultMap);
		
		return pageContainer;
	}

	@Override
	public Map<String, String> queryBillFlow(String id) {
		
		return tbRsBillFlowMapper.queryBillFlow(id);
	}

	@Override
	public int updateFlow(String netSid, String id, String status, long actualFee, String demo) {
		
		//修改提现申请表
		TbRsBillFlow tbRsBillFlow = new TbRsBillFlow();
		tbRsBillFlow.setId(Long.valueOf(id));
		if("5".equals(status)){//不通过->重新审核
			tbRsBillFlow.setStatus(FlypaasConstant.FLOW_APPLY);
		}else if("6".equals(status)){//不通过->作废
			tbRsBillFlow.setStatus(FlypaasConstant.FLOW_CANCEL);
		}else{
			tbRsBillFlow.setStatus(status);
		}
		if("4".equals(status)){//已转账
			tbRsBillFlow.setFinshDate(new Date());
		}else{
			tbRsBillFlow.setAdultDate(new Date());
		}
		tbRsBillFlow.setDemo(demo);
		int count = tbRsBillFlowMapper.updateByPrimaryKeySelective(tbRsBillFlow);
		if(count == 0){//更新失败
			return count;
		}
		//修改用户钱包
		if("2".equals(status) || "3".equals(status)){//申请作废或者未通过
			TbRsAccountBalance tbRsAccountBalance = new TbRsAccountBalance();
			tbRsAccountBalance.setNetSid(netSid);
			tbRsAccountBalance.setBalance((double)actualFee);
			int result = tbRsAccountBalanceMapper.updateBalanceAdd(tbRsAccountBalance);//增加余额
			if(result == 0){//更新失败
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//数据库回滚
				return result;
			}
		}
		if("5".equals(status)){//不通过->重新审核
			TbRsAccountBalance tbRsAccountBalance = new TbRsAccountBalance();
			tbRsAccountBalance.setNetSid(netSid);
			tbRsAccountBalance.setBalance((double)actualFee);
			int result = tbRsAccountBalanceMapper.updateBalanceSub(tbRsAccountBalance);//减少余额
			if(result == 0){//更新失败,钱包余额不足
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//数据库回滚
				return -1;
			}
		}
		String  msgDesc = null;
		String  msgTitle = null;
		switch(status){
			case "0": 
				msgTitle = "流水号“"+id+"”提现申请被重新审核";
				msgDesc = "请注意，流水号为“"+id+"”提现 " + actualFee +"元 的申请被管理员重新审核为“待审核”状态，原状态为“审核通过”，可通过菜单“财务管理->提现记录”进行查看。"; 
				break;
			case "1": 
				msgTitle = "流水号“"+id+"”提现申请审核通过";
				msgDesc = "请注意，流水号为“"+id+"”提现 " + actualFee +"元 的申请“审核通过”，原状态为“待审核”，可通过菜单“财务管理->提现记录”进行查看。"; 
				break;
			case "2": 
				msgTitle = "流水号“"+id+"”提现申请审核未通过";
				msgDesc = "请注意，流水号为“"+id+"”提现 " + actualFee +"元 的申请“审核未通过”，原状态为“待审核”，可通过菜单“财务管理->提现记录”进行查看。"; 
				break;
			case "3": 
				msgTitle = "流水号“"+id+"”提现申请作废";
				msgDesc = "请注意，流水号为“"+id+"”提现 " + actualFee +"元 的申请“作废”，原状态为“待审核”，可通过菜单“财务管理->提现记录”进行查看。"; 
				break;
			case "4": 
				msgTitle = "流水号“"+id+"”提现申请已转账成功";
				msgDesc = "请注意，流水号为“"+id+"”提现 " + actualFee +"元 的申请审核“已转账”，原状态为“审核通过”，可通过菜单“财务管理->提现记录”进行查看。"; 
				break;
			case "5": 
				msgTitle = "流水号“"+id+"”提现申请被重新审核";
				msgDesc = "请注意，流水号为“"+id+"”提现 " + actualFee +"元 的申请被管理员重新审核为“待审核”状态，原状态为“审核未通过”，可通过菜单“财务管理->提现记录”进行查看。"; 
				break;
			case "6": 
				msgTitle = "流水号“"+id+"”提现申请作废";
				msgDesc = "请注意，流水号为“"+id+"”提现 " + actualFee +"元 的申请“作废”，原状态为“审核未通过”，可通过菜单“财务管理->提现记录”进行查看。"; 
				break;
		}
		//通知资源方
		noticeServiceImpl.createNotice(msgTitle, msgDesc, netSid, FlypaasConstant.SYSTEM_NOTICE);
		return count;
		
	}

	@Override
	public List<Map<String, String>> queryAccountByName(String username) {
		return tbRsAccountInfoMapper.queryAccountByName(username);
	}

	@Override
	public List<Map<String, String>> findRsAccountBySids(ArrayList arrayList) {
		return tbRsAccountInfoMapper.findRsAccountBySids(arrayList);
	}

}
