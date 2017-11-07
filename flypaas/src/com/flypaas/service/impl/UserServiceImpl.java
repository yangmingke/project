package com.flypaas.service.impl;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.constant.AppConstant;
import com.flypaas.constant.SysConstant;
import com.flypaas.constant.UserConstant;
import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.AcctBalance;
import com.flypaas.entity.AcctPackageRel;
import com.flypaas.entity.Application;
import com.flypaas.entity.Client;
import com.flypaas.entity.Package;
import com.flypaas.entity.PaymentOrder;
import com.flypaas.entity.TbFlypaasUser;
import com.flypaas.entity.TestWhiteList;
import com.flypaas.rest.CreateClientRestThread;
import com.flypaas.service.TestWhilteListService;
import com.flypaas.service.UserService;
import com.flypaas.utils.DateUtil;
import com.flypaas.utils.StrUtil;
import com.flypaas.utils.SysConfig;
import com.flypaas.utils.ThreadPool;

/**
 * @author  chenxijun
 * @version 
 */
@Service
@Transactional
public class UserServiceImpl extends DaoCenter implements UserService {
	
	Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private TestWhilteListService testWhilteListService;

	public synchronized int addUser(TbFlypaasUser user) {
		int count = mailAndMobEnable(user);
		if(count==0){
			userDao.insertUser(user);
		}
		return count;
	}

	public void updateUser(TbFlypaasUser user) {
		userDao.updateUser(user);
		//修改默认测试号码
		if(!StrUtil.isEmpty(user.getMobile())){
			Date date = DateUtil.getCurrentDate();
			
			TestWhiteList testWhiteList = new TestWhiteList();
			testWhiteList.setSid(user.getSid());
			testWhiteList.setMobile(user.getMobile());
			testWhiteList.setUpdateDate(date);
			
			//获取已经绑定的手机号码
			TestWhiteList temp = testWhilteListDao.getBindModel(user.getSid());
			if(StrUtil.isEmpty(temp)){
				TestWhiteList temp1 = testWhilteListDao.get(testWhiteList);
				if(StrUtil.isEmpty(temp1)){
					testWhiteList.setStatus(AppConstant.TEST_NBR_STATUS_1);
					testWhiteList.setType(AppConstant.TEST_NBR_TYPE_1);
					testWhiteList.setCreateDate(date);
					testWhilteListService.add(testWhiteList);
				}else{
					temp1.setUpdateDate(DateUtil.getCurrentDate());
					testWhilteListService.updateAddMobileToBind(temp1);
				}
			}else{
				if(!temp.getMobile().equals(user.getMobile())){
					TestWhiteList temp2 = testWhilteListDao.get(testWhiteList);
					if(StrUtil.isEmpty(temp2)){
						testWhiteList.setId(temp.getId());
						testWhilteListService.updateBindMobile(testWhiteList,temp);
						
					}else{
						temp.setUpdateDate(DateUtil.getCurrentDate());
						testWhilteListService.resetBindMobile(temp,temp2);
					}
				}
			}
		}
	}

	public TbFlypaasUser findUserById(String userId) {
		return userDao.findUserByid(userId);
	}

	public TbFlypaasUser userLogin(TbFlypaasUser user) {
		return userDao.userLogin(user);
	}

	public synchronized int mailAndMobEnable(TbFlypaasUser user) {
		return userDao.mailAndMobEnable(user);
	}

	public TbFlypaasUser findeUser(TbFlypaasUser user) {
		return userDao.findeUser(user);
	}
	
	public TbFlypaasUser findeUserById(String sid) {
		return userDao.findUserByid(sid);
	}

	public synchronized int getUserCountByUserName(String userName) {
		return userDao.getUserCountByUserName(userName);
	}

	public TbFlypaasUser monitorHeartbeat() {
		return userDao.monitorHeartbeat();
	}

	public synchronized void activaUser(TbFlypaasUser user) {
		String sid = user.getSid();
		user.setStatus(UserConstant.STATUS_1);
		user.setOauthStatus(UserConstant.AUTH_STATUS_3);
		user.setUserType(UserConstant.USER_TYPE_2);
		//生成测试应用
		String appSid = StrUtil.getUUID();
		addApp(appSid,sid);
		String appSidNew = StrUtil.getUUID();
		addApp(appSidNew,sid);
		
		//生成测试client
		addClient(sid, user.getToken(), appSid);
		//生成测试client
		addClient(sid, user.getToken(), appSidNew);
			//赠送余额
		addAcctBalance(sid);
		
	
		//生成套餐信息
		addPackage(sid);
		
		updateUser(user);
	}
	//增加余额
	private synchronized void addAcctBalance(String sid){
		AcctBalance acctBalance = acctBalanceDao.getAcctBalanceBySid(sid);
		if(acctBalance==null){
			addPayMentOrder(sid);
			AcctBalance acctb = new AcctBalance();
			acctb.setBalance(Long.parseLong(SysConfig.getInstance().getProperty("persent"))*SysConstant.RATE);
			acctb.setCreateTime(DateUtil.getCurrentDate());
			acctb.setEnableFlag(SysConstant.ENABLEFLAG_1);
			acctb.setSid(sid);
			acctBalanceDao.add(acctb);
			log.info("------------------赠送余额成功----------------------------");
		}
	}
	//添加测试应用
	@Transactional(propagation = Propagation.NESTED)
	private synchronized void addApp(String appSid ,String sid){
		/*Application tApp = appDao.getTestApp(sid);
		if(tApp==null){*/
			Application app = new Application();
			Date d = DateUtil.getCurrentDate() ;
			app.setAppType(AppConstant.APP_TYPE_TEST);
			app.setCreateDate(d);
			app.setAuditDate(d);
			app.setAppSid(appSid);
			app.setAppName("测试DEMO");
			app.setStatus(AppConstant.STATUS_1);
			app.setSid(sid);
			app.setIndustry("14");
			app.setIsShowNbr("1");
			app.setCkNum(0);
			app.setCallFr(0);
			app.setBrand("yzxvip");
			
			long s = Math.abs(UUID.randomUUID().getMostSignificantBits());
			String sms_msg_nbr = String.valueOf(s);
			if(sms_msg_nbr.length()>11){
				sms_msg_nbr = sms_msg_nbr.substring(0,11);
			}
			app.setSmsMsgNbr(sms_msg_nbr);

			appDao.add(app);
			
			log.info("------------------生成测试应用成功----------------------------");
		/*}*/
	}
	
	private synchronized void addPackage(String sid){
		AcctPackageRel prel = acctPackageRelDao.getAcctPackageRel(sid);
		if(prel==null){
			Package p = packageDao.getDefaultPck();
			AcctPackageRel rel = new AcctPackageRel();
			rel.setPackageId(p.getPackageId());
			rel.setSid(sid);
			rel.setCreateDate(DateUtil.getCurrentDate());
			rel.setUpdateDate(DateUtil.getCurrentDate());
			acctPackageRelDao.addDefaultPck(rel);
			log.info("------------------生成套餐成功----------------------------");
		}
	}
	//添加测试client
	private synchronized void addClient(String sid,String token,String appSid){
		List<Client> cl = clientDao.getTestClientBySid(sid);
		if(cl.isEmpty() || cl.size()==0){
			CreateClientRestThread thread = new CreateClientRestThread(sid, token, appSid);
//			new Thread(thread).start();
			ThreadPool.excute(thread);
		}
	}

	//生成充值订单
	private void addPayMentOrder(String sid){
		PaymentOrder order = new PaymentOrder();
		order.setSid(sid);
		order.setCharge(Integer.parseInt(SysConfig.getInstance().getProperty("persent"))*SysConstant.RATE);
		order.setChargeType(SysConstant.CHARGETYPE_3);
		order.setCreateDate(DateUtil.getCurrentDate());
		order.setPayDate(DateUtil.getCurrentDate());
		order.setStatus(SysConstant.CHARGERESULT_2);
		payMentOrderDao.add(order);
	}

	public String renewToken(String sid, String resetTokenType) {
		String token=null;
		synchronized (UserServiceImpl.class) {
			if("1".equals(resetTokenType)){
				 token = userDao.queryRenewToken(sid);
			}
			if(null == token ){
				token = StrUtil.toHMACSHA(SysConstant.tokenBaseString + StrUtil.getUUID(), SysConstant.KEY);
				userDao.saveRenewToken(sid,token);
			}
		}
		return token;
	}

	public List<TbFlypaasUser> findeUpdatePasswordUser(Map<String,Object> params) {
		return userDao.findeUpdatePasswordUser(params);
	}

	public void deleteMailLog(Map<String, Object> mailLog) {
		userDao.deleteMailLog(mailLog);
	}
	
	public void addMailLog(Map<String, Object> mailLog) {
		userDao.addMailLog(mailLog);
	}

	public Map<String, Object> getFinanceInfo(String sid) {
		return userDao.getFinanceInfo(sid);
	}
}
