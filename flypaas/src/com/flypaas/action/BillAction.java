package com.flypaas.action;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.constant.SysConstant;
import com.flypaas.constant.UserConstant;
import com.flypaas.entity.AcctBalance;
import com.flypaas.entity.AcctPackageRel;
import com.flypaas.entity.Application;
import com.flypaas.entity.City;
import com.flypaas.entity.Invoice;
import com.flypaas.entity.InvoiceAddresslist;
import com.flypaas.entity.Package;
import com.flypaas.entity.PaymentOrder;
import com.flypaas.entity.Province;
import com.flypaas.entity.TbFlypaasUser;
import com.flypaas.entity.vo.PageContainer;
import com.flypaas.utils.DateUtil;
import com.flypaas.utils.Des3Utils;
import com.flypaas.utils.FileUtil;
import com.flypaas.utils.JsonUtil;
import com.flypaas.utils.StrUtil;
import com.flypaas.utils.SysConfig;

@Controller
@Scope("prototype")
@Results({
	@Result(name="chargeListSuc",location="/page/bill/charge_list.jsp"),
	@Result(name="extChargeListSuc",location="/page/bill/ext_charge_list.jsp"),
	@Result(name="billSuc",location="/page/bill/bill.jsp"),
	@Result(name="billMonthSuc",location="/page/bill/bills_monthly.jsp"),
	@Result(name="appCsmReportSuc",location="/page/bill/bill_csm.jsp"),
	@Result(name="invoiceSuc",location="/page/bill/invoice_manage.jsp"),
	@Result(name="addInvoicePageSuc",location="/page/bill/invoice.jsp"),
	@Result(name="addInvoiceSuc",type="redirectAction",location="invoice"),
	@Result(name="cacelInvoiceSuc",type="redirectAction",location="invoice"),
	@Result(name="invoiceInfoSuc",location="/page/bill/invoiceInfo.jsp"),
	@Result(name="modifyInvoicePageSuc",location="/page/bill/editInvoice.jsp"),
	@Result(name="modifyInvoiceSuc",type="redirectAction",location="invoice")
})
public class BillAction extends BaseAction {
	private Logger logger = LoggerFactory.getLogger(BillAction.class);
	private List<PaymentOrder> payOrderList = null;
	private List<Map<String, Object>> monthList = null;
	private PaymentOrder paymentOrder = null;
	private String orderId;
	private Package pck;
	private AcctBalance AcctBalance;
	private List<Application> appList;
	private String type;
	private String beginDate;
	private String endDate;
	private String appSid;
	private String monthCsm;
	private String month;
	private String year;
	private List<String> yearList;
	private PageContainer page;
	private List<Invoice> invoiceList;
	private List<Province> provinceList;
	private Invoice invoice;
	private Province province;
	private InvoiceAddresslist addr;
	private long invoiceMoney ; 
	private TbFlypaasUser user;
	private File identificationimg;
	private String identificationimgFileName;
	private String title;
	private String invoiceId;
	//充值列表
	@Action("/bill/chargeList")
	public String chargeList() {
		long cOrderId = 0 ;
		if (orderId != null) {
			if (!orderId.equals("")) {
				try {
					cOrderId = Long.parseLong(orderId);
				} catch (Exception e) {
					logger.error("订单号非法");
					logger.error(e.getMessage());
					StrUtil.writeMsg(response, "订单号非法，请重新输入订单号",null);
					orderId = null ;
					paymentOrder = null ;
					return null ;
				}
			}
		}
		if (null == page) {
			page = new PageContainer();
		}
		Object appSid = session.getAttribute("extAppSid");
		if(!StrUtil.isEmpty(appSid)){
			page.getParams().put("appSid", appSid.toString());
		}
		page.getParams().put("sid", getSessionUser().getSid());
		page.getParams().put("orderId", cOrderId);
		page.getParams().put("beginDate", paymentOrder==null?null:paymentOrder.getBeginDate());
		page.getParams().put("endDate", paymentOrder==null?null:paymentOrder.getEndDate());
		page = payMentOrderService.getChargeList(page);
		if(!StrUtil.isEmpty(appSid)){
			return "extChargeListSuc";
		}
		return "chargeListSuc";
	}
	//账单
	@Action("/bill/bill")
	public String bill() {
		// 开发者当前套餐信息
		String sid = getSessionUser().getSid();
		AcctPackageRel rel = packageRelService.getAcctPackageRel(sid);
		if (rel != null) {
			pck = packageService.getPackage(rel.getPackageId());
		}
		// 用户余额
//		AcctBalance = acctBalanceService.getAcctBalanceBySid(sid);
		// 用户所有app
		appList = appService.getAllAppBySid(sid);
		if(null != appList){
			Set<String> apps = new HashSet<String>();
			for (Application app : appList) {
				apps.add(app.getAppSid());
			}
			session.setAttribute("sss_apps", apps);
		}
		Date now = new Date();
		endDate = DateUtil.dateToStr(now, "yyyy-MM-dd");
		beginDate = DateUtil.dateToStr(now, "yyyy-MM-dd");
		
		return "billSuc";
	}
	//app消费
	@Action("/bill/appCsmReport")
	public String appCsmReport() {
		if (null == page) {
			page = new PageContainer();
		}
		page.setPageRowCount(10);
		Map<String, Object> param = new HashMap<String, Object>();
		if (StringUtils.isEmpty(endDate)) {
			endDate = DateUtil.dateToStr(new Date(), "yyyy-MM-dd");
		}
		if (StringUtils.isEmpty(beginDate)) {
			beginDate = DateUtil.dateToStr(new Date(), "yyyy-MM-dd");
		}
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		Date d2 = null;
		Date d3 = null;
		try {
			d1 = date.parse(beginDate);
			d2 = date.parse(endDate);
			d3 = DateUtils.addDays(d1, 45);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(d3.getTime() < d2.getTime()){
			 Date dd = new Date();
			 endDate = DateUtil.dateToStr(dd, "yyyy-MM-dd");
			 beginDate = DateUtil.dateToStr(DateUtils.addDays(dd, -45), "yyyy-MM-dd");
		}
		param.put("beginDate", beginDate);
		param.put("endDate", endDate);
		param.put("appSid", appSid);
		@SuppressWarnings("all")
		Set<String> apps = (Set<String>) session.getAttribute("sss_apps");
		if(null == apps||null == appSid || !apps.contains(appSid)){
			param.put("appSid", "fdsahtndsafdafsafdsa");
		}
		if (null == type) {
			type = "voice";
		}
		param.put("type", type);
		page.setParams(param);
		page = consumeService.getApp45Csm(page);
		return "appCsmReportSuc";
	}
	//月账单
	@Action("/bill/billMonth")
	public String billMonth() {
		try{
			Map<String, Object> param = new HashMap<String, Object>();
			yearList = buildYearList();
			if (month == null || month.equals("")) {
				param.put("billing", DateUtil.dateToStr(DateUtil.getCurrentDate(), "yyyyMM"));
				month = DateUtil.dateToStr(DateUtil.getCurrentDate(), "M");
				year = DateUtil.dateToStr(DateUtil.getCurrentDate(), "yyyy");
			} else {
				int mon = Integer.parseInt(month);
				param.put("billing", year + (mon >= 10 ? mon : "0" + mon));
			}
			param.put("sid", getSessionUser().getSid());
			monthList = consumeService.getBillMonth(param);
			monthCsm = consumeService.getBillCsmSum(param);
		}catch(Exception e){
			logger.error("查询账单异常");
			logger.error(e.getMessage());
			StrUtil.writeMsg(response, "查询账单异常，请稍后再试","/bill/billMonth");
			return null ;
		}
		return "billMonthSuc";
	}
	@Action("/bill/invoice")
	public String invoice(){
		String sid = getSessionUser().getSid();
		long money = payMentOrderService.getAllPayMoneyLastYear(sid);
		long lastMoney = invoiceService.getAllInvoiceMoney(sid);
		invoiceMoney = money-lastMoney ;//可开票金额
		
		if(StrUtil.isEmpty(page)){
			page = new PageContainer();
		}
		page.getParams().put("sid", sid);
		page = invoiceService.getInvoiceList(page);
		return "invoiceSuc" ;
	}
	@Action("/bill/invoiceInfo")
	public String invoiceInfo(){
		if(StrUtil.isEmpty(invoiceId)){
			StrUtil.writeMsg(response, "发票id错误，请检查后重试", null);
			return null ;
		}
		long id = Integer.parseInt(Des3Utils.decodeDes3(invoiceId));
		String sid = getSessionUser().getSid();
		if(invoice==null){
			invoice = new Invoice();
		}
		invoice.setSid(sid);
		invoice.setId(id);
		invoice = invoiceService.getInvoiceBySidAndId(invoice);
		if(!StrUtil.isEmpty(invoice) && invoice.getPostaddr()>0){
			addr = invoiceAddrService.get(invoice.getPostaddr());
		}
		return "invoiceInfoSuc" ;
	}
	@Action("/bill/addInvoicePage")
	public String addInvoicePage(){
		user = userService.findUserById(getSessionUser().getSid());
		if(user.getOauthStatus()==null || !UserConstant.AUTH_STATUS_3.equals(user.getOauthStatus())){
			StrUtil.writeMsg(response, "用户未认证", null);
			return null ;
		}
		long money = payMentOrderService.getAllPayMoneyLastYear(user.getSid());
		long lastMoney = invoiceService.getAllInvoiceMoney(user.getSid());
		invoiceMoney = money-lastMoney ;
		if(invoiceMoney<=0){
			StrUtil.writeMsg(response, "没有可开取发票余额", null);
			return null ;
		}
		if(money<SysConstant.INVOICE_LOWER_MONEY){
			StrUtil.writeMsg(response, "可开取发票余额小于最低限额", null);
			return null ;
		}
		provinceList = provinceService.getProvince();
		return "addInvoicePageSuc";
	}
	@Action("/bill/hasInvoiceing")
	public void hasInvoiceing(){
		String sid = getSessionUser().getSid();
		invoice = invoiceService.getInvoice(sid);
		if(invoice!=null){
			printMsg(SysConstant.NUM_FAIL);
		}else{
			printMsg(SysConstant.NUM_SUCCESS);
		}
	}
	@Action("/bill/cacelInvoice")
	public String cacelInvoice(){
		if(StrUtil.isEmpty(invoiceId)){
			StrUtil.writeMsg(response, "发票id错误，请检查后重试", null);
			return null ;
		}
		if(invoice==null){
			invoice = new Invoice();
		}
		long id = Integer.parseInt(Des3Utils.decodeDes3(invoiceId));
		String sid = getSessionUser().getSid();
		invoice.setSid(sid);
		invoice.setStatus(SysConstant.INVOICE_STATUS_0);
		invoice.setId(id);
		invoiceService.update(invoice,null);
		return "cacelInvoiceSuc";
	}
	@Action("/bill/addInvoice")
	public String  addInvoice(){
		if(checkJsForStr(invoice)){
			StrUtil.writeMsg(response, "提交信息不合法，请检查后重试", null);
			return null;
		}
		String msg = null ;
		if(StrUtil.isEmpty(addr)||StrUtil.isEmpty(invoice)){
			msg="参数出错，请检查后重试";
			StrUtil.writeMsg(response, msg, null);
			return null;
		}
		if(invoice.getMoney()<=0||invoice.getMoney()>invoiceMoney||invoice.getMoney()<=SysConstant.INVOICE_LOWER_MONEY){
			msg="开票金额超出范围，请检查后重试";
			StrUtil.writeMsg(response, msg, null);
			return null;
		}
		if(invoice.getMoney()<=1000){
			invoice.setType("2");
		}
		String type = invoice.getType();
		if(type.equals(SysConstant.INVOICE_STATUS_1)){
			if(!StrUtil.checkIdentification(invoice.getIdentificationnum())){
				msg="纳税人识别号不符合规范，请检查后重试";
				StrUtil.writeMsg(response, msg, null);
				return null;
			}
			/*
			msg = FileUtil.checkFile(identificationimg,SysConstant.SYS_PIC_MAXSIZE);
			if(!StrUtil.isEmpty(msg)){
				StrUtil.writeMsg(response, msg, null);
				return null;
			}
			*/
			if(!StrUtil.checkBankNum(invoice.getBankaccount()+"")){
				msg="银行账户不符合规范，请检查后重试";
				StrUtil.writeMsg(response, msg, null);
				return null;
			}
		}
		if(!StrUtil.checkPostNum(addr.getPostnum())){
			msg="邮编号码不符合规范，请检查后重试";
			StrUtil.writeMsg(response, msg, null);
			return null;
		}
		if(!StrUtil.checkPhoneForStr(addr.getContactmobile()+"")){
			msg="电话号码不符合规范，请检查后重试";
			StrUtil.writeMsg(response, msg, null);
			return null;
		}
		String sid = getSessionUser().getSid();
		Date date = DateUtil.getCurrentDate();
		String picType = null;
		if(!StrUtil.isEmpty(identificationimg)){
			picType = identificationimgFileName.substring(identificationimgFileName.lastIndexOf(".")+1);
			String url = FileUtil.upload(identificationimg, sid,SysConfig.getInstance().getProperty("oauth_pic"),picType);
			invoice.setIdentificationimg(url);
		}
		
		addr.setSid(sid);
		addr.setCreateDate(date);
		addr.setUpdateDate(date);
		addr.setStatus(SysConstant.INVOICE_ADDR_STATUS_1);
		invoiceAddrService.add(addr);
		if(!StrUtil.isEmpty(invoice)){
			long money = invoice.getMoney();
			invoice.setMoney(money*SysConstant.RATE);
			invoice.setCreateDate(date);
			invoice.setUpdateDate(date);
			invoice.setSid(sid);
			invoice.setPostaddr(addr.getId());
			invoice.setStatus(SysConstant.INVOICE_STATUS_1);
			invoiceService.add(invoice);
		}
		return "addInvoiceSuc" ;
	}
	@Action("/bill/modifyInvoicePage")
	public String modifyInvoicePage(){
		if(StrUtil.isEmpty(invoiceId)){
			StrUtil.writeMsg(response, "发票id错误，请检查后重试", null);
			return null ;
		}
		long id = Integer.parseInt(Des3Utils.decodeDes3(invoiceId));
		if(invoice==null){
			invoice = new Invoice();
		}
		user = getSessionUser();
		long money = payMentOrderService.getAllPayMoneyLastYear(user.getSid());
		long lastMoney = invoiceService.getAllInvoiceMoney(user.getSid());
		invoiceMoney = money-lastMoney ;
		invoice.setSid(user.getSid());
		invoice.setId(id);
		invoice = invoiceService.getInvoiceBySidAndId(invoice);
		if(invoice==null){
			String msg="发票信息不存在，请检查后重试";
			StrUtil.writeMsg(response, msg, null);
			return null;
		}
		addr = invoiceAddrService.get(invoice.getPostaddr());
		provinceList = provinceService.getProvince();
		return "modifyInvoicePageSuc";
	}
	@Action("/bill/modifyInvoice")
	public String modifyInvoice(){
		if(StrUtil.isEmpty(invoiceId)){
			StrUtil.writeMsg(response, "发票id错误，请检查后重试", null);
			return null ;
		}
		if(checkJsForStr(invoice)){
			StrUtil.writeMsg(response, "提交信息不合法，请检查后重试", null);
			return null;
		}
		String msg = null ;
		if(StrUtil.isEmpty(addr)||StrUtil.isEmpty(invoice)){
			msg="参数出错，请检查后重试";
			StrUtil.writeMsg(response, msg, null);
			return null;
		}
		if(invoice.getMoney()<=0||invoice.getMoney()>invoiceMoney||invoice.getMoney()<SysConstant.INVOICE_LOWER_MONEY){
			msg="提取金额超出范围";
			StrUtil.writeMsg(response, msg, null);
			return null;
		}
		String type = invoice.getType();
		if(type.equals(SysConstant.INVOICE_STATUS_1)){
			if(!StrUtil.checkIdentification(invoice.getIdentificationnum())){
				msg="税号不符合规范，请检查后重试";
				StrUtil.writeMsg(response, msg, null);
				return null;
			}
			msg = FileUtil.checkFile(identificationimg,SysConstant.SYS_PIC_MAXSIZE);
			if(!StrUtil.isEmpty(msg)){
				StrUtil.writeMsg(response, msg, null);
				return null;
			}
			if(!StrUtil.checkBankNum(invoice.getBankaccount()+"")){
				msg="银行账号不符合规范";
				StrUtil.writeMsg(response, msg, null);
				return null;
			}
		}
		if(!StrUtil.checkPhoneForStr(addr.getContactmobile()+"")){
			msg="联系人号码不符合规范，请检查后重试";
			StrUtil.writeMsg(response, msg, null);
			return null;
		}
		if(!StrUtil.checkPostNum(addr.getPostnum())){
			msg="邮编不符合规范，请检查后重试";
			StrUtil.writeMsg(response, msg, null);
			return null;
		}
		String url = null ;
		String sid = getSessionUser().getSid();
		long id = Integer.parseInt(Des3Utils.decodeDes3(invoiceId));
		Date d = DateUtil.getCurrentDate();
		if(!StrUtil.isEmpty(identificationimg)){
			String picType = identificationimgFileName.substring(identificationimgFileName.lastIndexOf(".")+1);
			url = FileUtil.upload(identificationimg, sid,SysConfig.getInstance().getProperty("oauth_pic"),picType);
		}
		if(url!=null){
			invoice.setIdentificationimg(url);
		}
		invoice.setId(id);
		invoice.setSid(sid);
		invoice.setUpdateDate(d);
		invoice.setStatus(SysConstant.INVOICE_STATUS_1);
		invoice.setMoney(invoice.getMoney()*SysConstant.RATE);
		addr.setSid(sid);
		addr.setUpdateDate(d);
		Invoice i = invoiceService.getInvoiceBySidAndId(invoice);
		addr.setId(i.getPostaddr());
		invoiceService.update(invoice,addr);
		return "modifyInvoiceSuc" ;
	}
	private boolean checkJsForStr(Invoice invoice){
		boolean boo = false;
		if(StrUtil.isEmpty(invoice)){
			boo = true;
			return boo ;
		}
		if(StrUtil.checkJsForStr(invoice.getTitle())){
			boo = true;
			return boo ;
		}
		if(StrUtil.checkJsForStr(invoice.getOpentype())){
			boo = true;
			return boo ;
		}
		if(StrUtil.checkJsForStr(invoice.getType())){
			boo = true;
			return boo ;
		}
		if(StrUtil.checkJsForStr(invoice.getMoney()+"")){
			boo = true;
			return boo ;
		}
		String type = invoice.getType();
		if(type.equals(SysConstant.INVOICE_STATUS_1)){
			if(StrUtil.checkJsForStr(invoice.getBankaccount()+"")){
				boo = true;
				return boo ;
			}
			if(StrUtil.checkJsForStr(invoice.getBankaddr())){
				boo = true;
				return boo ;
			}
			if(StrUtil.checkJsForStr(invoice.getIdentificationimg())){
				boo = true;
				return boo ;
			}
			if(StrUtil.checkJsForStr(invoice.getIdentificationnum())){
				boo = true;
				return boo ;
			}
		}
		return boo ;
	}
	@Action("/bill/printCitys")
	public void printCitys(){
		if(province==null){
			return;
		}
		List<City> citys = cityService.getCity(province.getId());
		String jsonStr = JsonUtil.ArrayToJsonStr(citys);
		printMsg(jsonStr);
	}
	
	private List<String> buildYearList() {
		String cYear = DateUtil.dateToStr(DateUtil.getCurrentDate(), "yyyy");
		String lastYear = DateUtil.getLastYear();
		List<String> list = new ArrayList<String>();
		list.add(cYear);
		list.add(lastYear);
		return list;
	}

	public List<PaymentOrder> getPayOrderList() {
		return payOrderList;
	}

	public void setPayOrderList(List<PaymentOrder> payOrderList) {
		this.payOrderList = payOrderList;
	}

	public PaymentOrder getPaymentOrder() {
		return paymentOrder;
	}

	public void setPaymentOrder(PaymentOrder paymentOrder) {
		this.paymentOrder = paymentOrder;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Package getPck() {
		return pck;
	}

	public void setPck(Package pck) {
		this.pck = pck;
	}

	public List<Application> getAppList() {
		return appList;
	}

	public void setAppList(List<Application> appList) {
		this.appList = appList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Map<String, Object>> getMonthList() {
		return monthList;
	}

	public void setMonthList(List<Map<String, Object>> monthList) {
		this.monthList = monthList;
	}

	public String getMonthCsm() {
		return monthCsm;
	}

	public void setMonthCsm(String monthCsm) {
		this.monthCsm = monthCsm;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public List<String> getYearList() {
		return yearList;
	}

	public void setYearList(List<String> yearList) {
		this.yearList = yearList;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public PageContainer getPage() {
		return page;
	}

	public void setPage(PageContainer page) {
		this.page = page;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getAppSid() {
		return appSid;
	}

	public void setAppSid(String appSid) {
		this.appSid = appSid;
	}

	public AcctBalance getAcctBalance() {
		return AcctBalance;
	}
	public void setAcctBalance(AcctBalance acctBalance) {
		AcctBalance = acctBalance;
	}
	public List<Invoice> getInvoiceList() {
		return invoiceList;
	}
	public void setInvoiceList(List<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public long getInvoiceMoney() {
		return invoiceMoney;
	}
	public void setInvoiceMoney(long invoiceMoney) {
		this.invoiceMoney = invoiceMoney;
	}
	public TbFlypaasUser getUser() {
		return user;
	}
	public void setUser(TbFlypaasUser user) {
		this.user = user;
	}
	public List<Province> getProvinceList() {
		return provinceList;
	}
	public void setProvinceList(List<Province> provinceList) {
		this.provinceList = provinceList;
	}
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	public InvoiceAddresslist getAddr() {
		return addr;
	}
	public void setAddr(InvoiceAddresslist addr) {
		this.addr = addr;
	}
	public File getIdentificationimg() {
		return identificationimg;
	}
	public void setIdentificationimg(File identificationimg) {
		this.identificationimg = identificationimg;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getIdentificationimgFileName() {
		return identificationimgFileName;
	}
	public void setIdentificationimgFileName(String identificationimgFileName) {
		this.identificationimgFileName = identificationimgFileName;
	}
	
}
