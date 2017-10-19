package com.flypaas.service.impl;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.constant.AppConstant;
import com.flypaas.constant.SysConstant;
import com.flypaas.constant.UserConstant;
import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.AcctBalance;
import com.flypaas.entity.AppBalance;
import com.flypaas.entity.PaymentOrder;
import com.flypaas.entity.Security;
import com.flypaas.entity.SecurityBalance;
import com.flypaas.entity.TbFlypaasUser;
import com.flypaas.entity.vo.PageContainer;
import com.flypaas.service.PayMentOrderService;
import com.flypaas.utils.DateUtil;
import com.flypaas.utils.StrUtil;
@Service
@Transactional
public class PayMentOrderServiceImpl extends DaoCenter implements PayMentOrderService {
	
	private Logger logger = LoggerFactory.getLogger(PayMentOrderServiceImpl.class);
	
	public PaymentOrder addPaymentOrder(long charge,String sid,String accountType,String chargeType,String appSid) {
		PaymentOrder order = addPayMentOrder(charge, sid, accountType, chargeType,appSid);
		add(order);
		return order;
	}
	public void add(PaymentOrder order) {
		payMentOrderDao.add(order);
	}

	public PaymentOrder get(long id) {
		return payMentOrderDao.get(id);
	}

	public void update(PaymentOrder order) { 
		payMentOrderDao.update(order);
	}
	
	public PageContainer getChargeList(PageContainer page){
		return payMentOrderDao.getChargeList(page);
	}

	public void updateOrderId(Map<String, Object> map) {
		payMentOrderDao.updateOrderId(map);
	}

	public PaymentOrder getByPayId(PaymentOrder order) {
		return payMentOrderDao.getByPayId(order);
	}

	public long getAllPayMoneyLastYear(String sid) {
		return payMentOrderDao.getAllPayMoneyLastYear(sid);
	}

	public void callBack(PaymentOrder order,String payAmount,String sid) {
		Security security = null;
		AcctBalance acctBalance = null ;
		
		//更新订单状态
		long money = Integer.parseInt(payAmount)/SysConstant.payRate;
		logger.info("订单号:"+order.getOrderId()+"  充值成功！充值金额："+money);
//		AcctBalance b = acctBalanceService.getAcctBalanceBySid(sid);
		PaymentOrder o = new PaymentOrder();
		o.setCharge(money*SysConstant.RATE);
		o.setStatus(SysConstant.CHARGERESULT_3);
		o.setOrderId(order.getOrderId());
		o.setPayResultDate(DateUtil.getCurrentDate());
		o.setSid(sid);
//		o.setChargeBalance(Long.parseLong(new BigDecimal(b.getBalance()*SysConstant.RATE+"").toPlainString())+money*SysConstant.RATE);
		payMentOrderDao.update(o);
		logger.info("-----------------更新本地订单状态成功！--------------------");
		
		
		String acctType = order.getAccountType();
		//保障金账户充值
		if(!StrUtil.isEmpty(acctType) && acctType.equals(UserConstant.ACCT_TYPE_1)){
			
			//更新保障金账户信息
			security = new Security();
			security.setSid(sid);
			security.setStatus(UserConstant.AGREEMENT_STATUS_4);
			security.setUpdateDate(DateUtil.getCurrentDate());
			agreementDao.update(security);
			
			//更新用户信息
			TbFlypaasUser user = new TbFlypaasUser();
			user.setSid(sid);
			user.setIsContract(UserConstant.IS_CONTRACT);
			userDao.updateUser(user);
			
			//保障金充值
			long securityId = agreementDao.getAgreementUser(sid).getId();
			SecurityBalance securityBalance = new SecurityBalance();
			securityBalance.setSid(sid);
			securityBalance.setBalance(money*SysConstant.RATE);
			securityBalance.setSecurityId(securityId);
			securityBalance.setRelieveType(UserConstant.SECURITY_BALANCE_STATUS_2);
			securityBalanceDao.addMoney(securityBalance);
		}
		//普通账户充值
		else{
			
			//云平台充值
			acctBalance = new AcctBalance();
			acctBalance.setSid(sid);
			acctBalance.setBalance(money*SysConstant.RATE);
			acctBalanceDao.update(acctBalance);
			logger.info("------------------更新余额成功！-------------------");
			
			//判断是否是应用充值
			String appSid = order.getAppSid();
			if(!StrUtil.isEmpty(appSid)){
				AppBalance appBalance = new AppBalance();
				appBalance.setAppSid(appSid);
				appBalance.setSid(sid);
				appBalance.setStatus(AppConstant.APP_BALANCE_STATUS_1);
				appBalance.setBalance(money*SysConstant.RATE);
				appBalanceDao.updateAppBalance(appBalance);
				logger.info("------------------更新应用余额成功！-------------------");
			}
			
		}
	}
	//生成充值订单
	private PaymentOrder addPayMentOrder(long charge,String sid,String accountType,String chargeType,String appSid){
		PaymentOrder order = new PaymentOrder();
		order.setSid(sid);
		order.setCharge(charge*SysConstant.RATE);
		order.setCreateDate(DateUtil.getCurrentDate());
		order.setPayDate(DateUtil.getCurrentDate());
		order.setStatus(SysConstant.CHARGERESULT_1);
		order.setPayId(StrUtil.getUUID());
		order.setChargeType(chargeType);
		if(!StrUtil.isEmpty(appSid)){
			order.setAppSid(appSid);
		}
		if(accountType!=null){
			order.setAccountType(accountType);
		}else{
			order.setAccountType("0");
		}
		return order;
	}
	public void coupon(String couponNum,long charge,String sid,String meetId,String chargeType){
		//订单
		PaymentOrder order = addPayMentOrder(charge, sid,null,chargeType,null); 
		order.setStatus(SysConstant.CHARGERESULT_3);
		payMentOrderDao.add(order);
		//充值
		AcctBalance acct = new AcctBalance();
		acct.setSid(sid);
		acct.setBalance(charge*SysConstant.RATE);
		acctBalanceDao.update(acct);
		//代金券领取记录
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sid", sid);
		map.put("couponNum", couponNum);
		map.put("meetId", meetId);
		map.put("money", charge);
		map.put("createDate", DateUtil.getCurrentDate());
		couponDao.addCouponLog(map);
	}
	
	/**
	 * 检验订单状态
	 **/
	public boolean checkOrder(PaymentOrder order){
		order = getByPayId(order);
		if(StrUtil.isEmpty(order)){
			logger.info("------------------充值失败!订单不存在-------------------");
			return false;
		}
		
		//已经充值的响应比较慢的直接返回success，
		if(order.getStatus().equals(SysConstant.CHARGERESULT_3)){
			logger.info("------------------充值已经成功!无需再次更新-------------------");
			return false;
		}
		return true;
	}
}
