package com.flypaas.service.impl;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.constant.SysConstant;
import com.flypaas.constant.UserConstant;
import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.AcctBalance;
import com.flypaas.entity.PaymentOrder;
import com.flypaas.entity.Security;
import com.flypaas.entity.SecurityBalance;
import com.flypaas.entity.SecurityDeductionLog;
import com.flypaas.entity.SecurityRelieveApplyfor;
import com.flypaas.entity.TbFlypaasUser;
import com.flypaas.service.AgreementService;
import com.flypaas.utils.DateUtil;
import com.flypaas.utils.FileUtil;
import com.flypaas.utils.StrUtil;
import com.flypaas.utils.SysConfig;

@Service
@Transactional
public class AgreementServiceImpl extends DaoCenter implements AgreementService {

	private Security security;
	private Map<String, Object> map ;
	private SecurityBalance securityBalance;
	private SecurityRelieveApplyfor apply;
	
	public void addAgreementUser(String sid, File file,String type) {
		String imgUrl = FileUtil.upload(file, sid,SysConfig.getInstance().getProperty("oauth_pic"),type);
		Date date = DateUtil.getCurrentDate();
		security = agreementDao.getAgreementUser(sid);
		if(security==null){
			security = new Security();
			security.setAgreenmentFile(imgUrl);
			security.setSid(sid);
			security.setCreateDate(date);
			security.setUpdateDate(date);
			security.setStatus(UserConstant.AGREEMENT_STATUS_1);
			agreementDao.addAgreementUser(security);
		}else{
			Security s1 = new Security();
			s1.setStatus(UserConstant.AGREEMENT_STATUS_1);
			s1.setAgreenmentFile(imgUrl);
			s1.setUpdateDate(date);
			s1.setSid(sid);
			update(s1);
		}
		security = agreementDao.getAgreementUser(sid);
		map = new HashMap<String, Object>();
		map.put("sid", sid);
		map.put("securityId", security.getId());
		SecurityBalance sb = securityBalanceDao.get(map);
		if(sb==null){
			securityBalance = new SecurityBalance();
			securityBalance.setSid(sid);
			securityBalance.setCreateDate(date);
			securityBalance.setRelieveType(UserConstant.SECURITY_BALANCE_STATUS_1);
			securityBalance.setSecurityId(security.getId());
			securityBalance.setUpdateDate(date);
			securityBalance.setStatus(UserConstant.SECURITY_STATUS_1);
			securityBalanceDao.add(securityBalance);
		}else{
			securityBalance = new SecurityBalance();
			securityBalance.setRelieveType(UserConstant.SECURITY_BALANCE_STATUS_1);
			securityBalance.setStatus(UserConstant.SECURITY_STATUS_1);
			securityBalance.setUpdateDate(date);
			securityBalance.setSid(sid);
			securityBalance.setSecurityId(security.getId());
			securityBalanceDao.update(securityBalance);
		}
	}

	public void update(Security security) {
		agreementDao.update(security);
	}

	public Security getAgreementUser(String sid) {
		return agreementDao.getAgreementUser(sid);
	}

	public void unfreezeBalanceToCloud(String sid) {
		
		Date date = DateUtil.getCurrentDate();
		
		AcctBalance acct = acctBalanceDao.getAcctBySid(sid);
		Security security = agreementDao.getAgreementUser(sid);
		
		map = new HashMap<String, Object>();
		map.put("sid", sid);
		map.put("securityId", security.getId());
		
		securityBalance = securityBalanceDao.get(map);
		apply = securityRelieveApplyforDao.get(security.getId());
		
		long balance = securityBalance.getBalance();
		long toId = acct.getAcctId();
		
		//扣费
		map.put("updateDate", date);
		securityBalanceDao.unfreezeBalance(map);
		
		//写流水
		SecurityDeductionLog log = new SecurityDeductionLog();
		log.setSid(sid);
		log.setCharge(balance*SysConstant.RATE);
		log.setFrmAccountType(UserConstant.ACCT_TYPE_1);
		log.setFrmId(security.getId());
		log.setToAccountType(UserConstant.ACCT_TYPE_0);
		log.setToId(toId);
		log.setBankaddr(apply==null?"":apply.getBankaddr());
		log.setCompany(apply==null?"":apply.getCompany());
		log.setCreateDate(date);
		securityDeductionLogDao.addSecurityDeductionLog(log);
		
		//充值订单
		PaymentOrder order = new PaymentOrder();
		order.setSid(sid);
		order.setPayId(StrUtil.getUUID());
		order.setCreateDate(date);
		order.setPayDate(date);
		order.setStatus(SysConstant.CHARGERESULT_3);
		order.setCharge(balance*SysConstant.RATE);
		order.setChargeType(SysConstant.CHARGETYPE_4);
		order.setAccountType(UserConstant.ACCT_TYPE_0);
		payMentOrderDao.add(order);
		
		//更新快传余额
		AcctBalance acctb = new AcctBalance();
		acctb.setBalance(balance*SysConstant.RATE);
		acctb.setSid(sid);
		acctb.setAcctId(toId);
		acctBalanceDao.update(acctb);
	}

	public void relieveSecurity(Security security, TbFlypaasUser user) {
		agreementDao.update(security);
		userDao.updateUser(user);
	}

}
