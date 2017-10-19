package com.flypaas.action;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.constant.SysConstant;
import com.flypaas.constant.UserConstant;
import com.flypaas.entity.Security;
import com.flypaas.entity.SecurityBalance;
import com.flypaas.entity.SecurityRelieveApplyfor;
import com.flypaas.entity.TbFlypaasUser;
import com.flypaas.utils.DateUtil;
import com.flypaas.utils.Des3Utils;
import com.flypaas.utils.FileUtil;
import com.flypaas.utils.StrUtil;

@Controller
@Scope("prototype")
@Results({
	@Result(name = "addAgrtPageSuc", location = "/page/user/agreenment.jsp"),
//	@Result(name = "addAgrtPageUserSuc", location = "/page/user/agreenment1.jsp"),
	@Result(name = "addAgrtUserSuc",type="redirectAction", location = "addAgreenmetPage"),
	@Result(name = "paySecurityPageSuc", location = "/page/user/agreenment3.jsp"),
	@Result(name = "relieveSecuritySuc", type="redirectAction" ,location = "addAgreenmetPage"),
	@Result(name = "unfreezeBalanceSuc", type="redirectAction" ,location = "addAgreenmetPage")
})
public class AgreementAction extends BaseAction {

	private File agreenmentFile;
	private String agreenmentFileFileName;
	private Security security;
	private SecurityBalance securityBalance;
	private String sid;
	private TbFlypaasUser user;
	private String bankNum;
	private String bankAddr;
	private String company;
	private String unfreeze;
	private long money;
	private Map<String, Object> map;
	private SecurityRelieveApplyfor applyFor;
	
	//协议用户首页
	@Action("/agreenment/addAgreenmetPage")
	public String addAgreenmetPage(){
		//涉及到审核状态改变所以不能取缓存
		user = userService.findUserById(getSessionUser().getSid());
		sid = user.getSid();
		security = agreementService.getAgreementUser(sid);
		applyFor = securityRelieveApplyforService.getBySid(sid);
		if(security!=null){
			map = new HashMap<String, Object>();
			map.put("sid", sid);
			map.put("securityId", security.getId());
			securityBalance = securityBalanceService.get(map);
		}
		return "addAgrtPageSuc" ;
	}
	
	//添加协议用户页面
//	@Action("/agreenment/addAgreenmetUserPage")
//	public String addAgreenmetUserPage(){
//		return "addAgrtPageUserSuc";
//	}
	
	//添加协议用户
	@Action("/agreenment/addAgreenmet")
	public String addAgreementUser(){
		sid = getSessionUser().getSid();
		security = agreementService.getAgreementUser(sid);
		if(security!=null&&!UserConstant.AGREEMENT_STATUS_0.equals(security.getStatus())&&!UserConstant.AGREEMENT_STATUS_3.equals(security.getStatus())){
			StrUtil.writeMsg(response, "请求正在处理中，返回协议用户首页", "/agreenment/addAgreenmetPage");
			return null;
		}
		security = new Security();
		boolean  imgType = FileUtil.checkImgType(agreenmentFile);
		if(!imgType){
			StrUtil.writeMsg(response, "图片格式不正确", null);
			return null ;
		}
		
		String msg = FileUtil.checkFile(agreenmentFile,SysConstant.SYS_PIC_MAXSIZE);
		if(msg!=null){
			StrUtil.writeMsg(response, msg, null);
			return null ;
		}
		String type = agreenmentFileFileName.substring(agreenmentFileFileName.lastIndexOf(".")+1);
		agreementService.addAgreementUser(sid, agreenmentFile,type);
		security = agreementService.getAgreementUser(sid);
		return "addAgrtUserSuc";
	}
	
	//支付保障金页面
//	@Action("/agreenment/paySecurityPage")
//	public String paySecurityPage(){
//		return "paySecurityPageSuc" ;
//	}
	
	//解除协议
	@Action("/agreenment/relieveSecurity")
	public String relieveSecurity(){
		sid = getSessionUser().getSid();
		security = new Security();
		security.setStatus(UserConstant.AGREEMENT_STATUS_0);
		security.setSid(sid);
		security.setUpdateDate(DateUtil.getCurrentDate());
		
		TbFlypaasUser user = new TbFlypaasUser();
		user.setSid(sid);
		user.setIsContract(UserConstant.NOT_CONTRACT);
		
		agreementService.relieveSecurity(security,user);
		
		security = agreementService.getAgreementUser(sid);
		return "relieveSecuritySuc" ;
	}
	
	//解除保障金
	@Action("/agreenment/unfreezeBalance")
	public String unfreezeBalance(){
		unfreeze = Des3Utils.decodeDes3(unfreeze);
		if(StrUtil.isEmpty(unfreeze)){
			StrUtil.writeMsg(response, "参数非法，请检查后重试", null);
			return null ;
		}
		if(!Arrays.asList(UserConstant.UNFREEZE_STATUS).contains(unfreeze)){
			StrUtil.writeMsg(response, "参数非法，请检查后重试", null);
			return null ;
		}
		sid = getSessionUser().getSid();
		if(unfreeze.equals(UserConstant.UNFREEZE_STATUS[0])){
			agreementService.unfreezeBalanceToCloud(sid);
			return "unfreezeBalanceSuc";
		}else{
			if(!StrUtil.checkBankNum(bankNum)){
				StrUtil.writeMsg(response, "银行卡号不合法", null);
				return null ;
			}
			Security security = agreementService.getAgreementUser(sid);
			SecurityRelieveApplyfor applyFor = securityRelieveApplyforService.get(security.getId());
			map = new HashMap<String, Object>();
			map.put("sid", sid);
			map.put("securityId", security.getId());
			securityBalance = securityBalanceService.get(map);
			if(applyFor==null){
				SecurityRelieveApplyfor apply = new SecurityRelieveApplyfor();
				Date date = DateUtil.getCurrentDate();
				apply.setBanknum(Long.parseLong(bankNum));
				apply.setBankaddr(bankAddr);
				apply.setCompany(company);
				apply.setSid(sid);
				apply.setStatus(UserConstant.SECURITY_APPLY_STATUS_1);
				apply.setCreateDate(date);
				apply.setUpdateDate(date);
				apply.setSecurityId(security.getId());
				apply.setMoney(securityBalance==null?0:securityBalance.getBalance()*SysConstant.RATE);
				securityRelieveApplyforService.add(apply);
			}
			StrUtil.writeMsg(response, "你的申请已提交，请耐心等待后台处理", "/agreenment/addAgreenmetPage");
			return null;
		}
		
	}
	@Action("/agreenment/hasUnfreezed")
	public void hasUnfreezed(){
		sid = getSessionUser().getSid();
		Security security = agreementService.getAgreementUser(sid);
		SecurityRelieveApplyfor applyFor = securityRelieveApplyforService.get(security.getId());
		if(applyFor==null){
			printMsg(SysConstant.NUM_FAIL);
		}else{
			printMsg(SysConstant.NUM_SUCCESS);
		}
	}
	@Action("/agreenment/hasApplyFor")
	public void hasApplyFor(){
		sid = getSessionUser().getSid();
		SecurityRelieveApplyfor apply = securityRelieveApplyforService.getBySid(sid);
		if(apply==null){
			printMsg(SysConstant.NUM_FAIL);
		}else{
			printMsg(SysConstant.NUM_SUCCESS);
		}
	}

	public File getAgreenmentFile() {
		return agreenmentFile;
	}

	public void setAgreenmentFile(File agreenmentFile) {
		this.agreenmentFile = agreenmentFile;
	}

	public TbFlypaasUser getUser() {
		return user;
	}

	public void setUser(TbFlypaasUser user) {
		this.user = user;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}
	public String getBankNum() {
		return bankNum;
	}
	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}
	
	public String getUnfreeze() {
		return unfreeze;
	}

	public void setUnfreeze(String unfreeze) {
		this.unfreeze = unfreeze;
	}

	public String getBankAddr() {
		return bankAddr;
	}

	public void setBankAddr(String bankAddr) {
		this.bankAddr = bankAddr;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		this.money = money;
	}

	public SecurityBalance getSecurityBalance() {
		return securityBalance;
	}

	public void setSecurityBalance(SecurityBalance securityBalance) {
		this.securityBalance = securityBalance;
	}

	public String getAgreenmentFileFileName() {
		return agreenmentFileFileName;
	}

	public void setAgreenmentFileFileName(String agreenmentFileFileName) {
		this.agreenmentFileFileName = agreenmentFileFileName;
	}

	public SecurityRelieveApplyfor getApplyFor() {
		return applyFor;
	}

	public void setApplyFor(SecurityRelieveApplyfor applyFor) {
		this.applyFor = applyFor;
	}
	
	
}
