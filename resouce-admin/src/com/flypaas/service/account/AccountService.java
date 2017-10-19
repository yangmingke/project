package com.flypaas.service.account;

import java.util.List;
import java.util.Map;

import com.flypaas.model.TbRsAccountBalance;
import com.flypaas.model.TbRsAccountInfo;
import com.flypaas.model.TbRsRoleUser;
import com.flypaas.model.TbRsUserInfo;
import com.flypaas.util.PageContainer;

public interface AccountService{
	/**
	 * 根据条件查询资源方列表
	 * @param conditions
	 * @return
	 */
	public PageContainer queryAccount(Map<String, Object> para, int page);

	TbRsAccountInfo queryAccountById(String netSid);

	int updateAccount(TbRsAccountInfo record);

	int unLockorLock(String netSid, String status);

	public List<TbRsUserInfo> queryUsersByNetId(String netSid);

	public void openAccount(TbRsAccountInfo tbRsAccountInfo, TbRsUserInfo tbRsUserInfo,
			TbRsAccountBalance tbRsAccountBalance, TbRsRoleUser tbRsRoleUser);

	public int queryUsers(TbRsAccountInfo tbRsAccountInfo);

	List<TbRsAccountInfo> queryAllAccountName();

	String getRouteArea(String netSid);

	public List<TbRsAccountInfo> queryAllAccountNameByName(String accountName);

	
}
