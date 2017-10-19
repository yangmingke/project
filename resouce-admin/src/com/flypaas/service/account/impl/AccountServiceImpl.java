package com.flypaas.service.account.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flypaas.constant.FlypaasConstant;
import com.flypaas.constant.RouterConstant;
import com.flypaas.dao.TbRsAccountBalanceMapper;
import com.flypaas.dao.TbRsAccountInfoMapper;
import com.flypaas.dao.TbRsRoleUserMapper;
import com.flypaas.dao.TbRsUserInfoMapper;
import com.flypaas.model.TbRsAccountBalance;
import com.flypaas.model.TbRsAccountInfo;
import com.flypaas.model.TbRsRoleUser;
import com.flypaas.model.TbRsUserInfo;
import com.flypaas.service.NoticeService;
import com.flypaas.service.account.AccountService;
import com.flypaas.util.PageContainer;
import com.flypaas.util.StrUtil;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	TbRsAccountInfoMapper tbRsAccountInfoMapper;
	@Autowired
	TbRsUserInfoMapper tbRsUserInfoMapper;
	@Autowired
	TbRsAccountBalanceMapper tbRsAccountBalanceMapper;
	@Autowired
	TbRsRoleUserMapper tbRsRoleUserMapper;
	@Autowired
	NoticeService noticeServiceImpl;
	


	@Override
	public PageContainer queryAccount(Map<String, Object> para, int page) {
		int total = tbRsAccountInfoMapper.queryCount(para);//查询数据总数
		PageContainer pageContainer = new PageContainer();
		para = pageContainer.createParameter(para, page, total);
		 
		List<Object> result = tbRsAccountInfoMapper.queryAccount(para);
		pageContainer.setResult(result);
		
		return pageContainer;
	}
	
	@Override
	public TbRsAccountInfo  queryAccountById(String netSid) {
		TbRsAccountInfo account = tbRsAccountInfoMapper.selectByPrimaryKey(netSid);
		
		return account;
	}
	
	@Override
	public List<TbRsAccountInfo> queryAllAccountName(){
		return tbRsAccountInfoMapper.queryAllAccountName();
	}
	
	@Override
	public int updateAccount(TbRsAccountInfo record) {
		TbRsUserInfo tbRsUserInfo = new TbRsUserInfo();
		tbRsUserInfo.setNetSid(record.getNetSid());
		tbRsUserInfo.setRealname(record.getRealname());
		tbRsUserInfo.setUsername(record.getUsername());
		tbRsUserInfo.setMobile(record.getMobile());
		tbRsUserInfo.setAddress(record.getAddress());
		tbRsUserInfo.setEmail(record.getEmail());
		int result = tbRsAccountInfoMapper.updateByPrimaryKeySelective(record);
		if(result == 1){
			result = tbRsUserInfoMapper.updateByEmial(tbRsUserInfo);
		}
		
		if(result == 1){//通知资源方
			noticeServiceImpl.createNotice("资源方资料被修改", "请注意，您的资源方信息已被平台管理员修改", record.getNetSid(), FlypaasConstant.SYSTEM_NOTICE);
		}
		return result;
	}
	
	@Override
	public int unLockorLock(String netSid,String status) {
		if(StrUtil.isEmpty(netSid) || StrUtil.isEmpty(status)){
			return 0;
		}
		TbRsAccountInfo acccout = new TbRsAccountInfo();
		acccout.setNetSid(netSid);
		acccout.setStatus(status);
		
		int result = tbRsAccountInfoMapper.updateByPrimaryKeySelective(acccout);
		
		return result;
	}

	@Override
	public List<TbRsUserInfo> queryUsersByNetId(String netSid) {
		
		List<TbRsUserInfo> userList = tbRsUserInfoMapper.queryAllUser(netSid);
		
		return userList;
	}

	@Override
	public void openAccount(TbRsAccountInfo tbRsAccountInfo, TbRsUserInfo tbRsUserInfo,
		TbRsAccountBalance tbRsAccountBalance, TbRsRoleUser tbRsRoleUser) {
		tbRsAccountInfoMapper.insertSelective(tbRsAccountInfo);
		tbRsUserInfoMapper.insertSelective(tbRsUserInfo);
		tbRsAccountBalanceMapper.insertSelective(tbRsAccountBalance);
		tbRsRoleUserMapper.insert(tbRsRoleUser);
	}

	@Override
	public int queryUsers(TbRsAccountInfo tbRsAccountInfo) {
		TbRsUserInfo tbRsUserInfo = new TbRsUserInfo();
		tbRsUserInfo.setEmail(tbRsAccountInfo.getEmail());
		tbRsUserInfo.setMobile(StrUtil.isEmpty(tbRsAccountInfo.getMobile()) ? null : tbRsAccountInfo.getMobile());
		return tbRsUserInfoMapper.queryUsersCount(tbRsUserInfo);
	}
	
	@Override
	public String getRouteArea(String netSid){
		TbRsAccountInfo tbRsAccountInfo = tbRsAccountInfoMapper.selectByPrimaryKey(netSid);
		String routeArea = tbRsAccountInfo.getNetArea();
		String isPrivateNet = tbRsAccountInfo.getIsPrivateNet();//是否开启私有域
		if(isPrivateNet == null || !"1".equals(isPrivateNet)){
			routeArea = RouterConstant.ROUTE_DEFAULT_KEY_CN;
		}
		return routeArea;
	}

	@Override
	public List<TbRsAccountInfo> queryAllAccountNameByName(String username) {
		return tbRsAccountInfoMapper.queryAllAccountNameByName(username);
	}

}
