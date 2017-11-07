package com.flypaas.action.app;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.flypaas.action.BaseAction;
import com.flypaas.constant.AppConstant;
import com.flypaas.constant.SysConstant;
import com.flypaas.entity.AcctBalance;
import com.flypaas.entity.AppShowNbrs;
import com.flypaas.entity.Application;
import com.flypaas.entity.Client;
import com.flypaas.entity.CountryMobilePrefix;
import com.flypaas.entity.Package;
import com.flypaas.entity.TbSrvCallback;
import com.flypaas.entity.TbSrvWhiteList;
import com.flypaas.entity.TbFlypaasUser;
import com.flypaas.entity.TestNumber;
import com.flypaas.entity.Params;
import com.flypaas.entity.vo.PageContainer;
import com.flypaas.httpclient.impl.RefreshRedis;
import com.flypaas.rest.RestClient;
import com.flypaas.utils.DateUtil;
import com.flypaas.utils.Des3Utils;
import com.flypaas.utils.FileUtil;
import com.flypaas.utils.StrUtil;
@Controller
@Scope("prototype")
@Results({
	@Result(name="appManager",location="/page/app/app.jsp"),
	@Result(name="addSuc",type="redirectAction",location="appManager"),
	@Result(name="addPage",location="/page/app/app_create.jsp"),
	@Result(name="editPage",location="/page/app/editor.jsp"),
	@Result(name="updateSuc",type="redirectAction",location="appManager"),
	@Result(name="deleteSuc",type="redirectAction",location="appManager"),
	@Result(name="onLineSuc",type="redirectAction",location="appManager"),
	@Result(name="testDemoSuc",location="/page/app/test_demo.jsp"),
	@Result(name="testMobileSuc",location="/page/app/test_mobile.jsp"),
	@Result(name="demoOpt",type="redirectAction",location="testMobile"),
	@Result(name="appInfoSuc",location="/page/app/appinfo.jsp"),
	@Result(name="clientList",location="/page/app/clientList.jsp"),
	@Result(name="createClinetSuc",type="redirectAction",location="appManager")
})
public class AppAction extends BaseAction{
	private Logger logger = LoggerFactory.getLogger(AppAction.class);
	private Application app ;
	private PageContainer page;
	private List<Application> appList;
	private List<Params> cbfunList ;
	private List<Params> appKindList;
	private List<Params> routePolicyList;
	private List<Params> cloudList ;
	private List<Params> routeNumList;
	private List<TbSrvCallback> cbInfoList ;
	private List<Client> clientList;
	private List<TestNumber> testNumList;
	private List<CountryMobilePrefix> mobilePrefixList;
	private TbSrvWhiteList whiteList;
	private TbFlypaasUser user;
	private TestNumber testNumber;
	private String appName ;
	private String type;
	private String appSid;
	private String whiteListStr ;
	private String cbfunStr;
	private AcctBalance acctBalance;
	private Package pck;
	private String cbfunurlStr;
	private String cbfunmethodStr;
	private String mobile;
	private List<String> whiteStrList;
	private List<Client> notBindClientList;
	private File validFile = null ;
	private String validFileFileName;
	
	private String showNbr1;
	private String showNbr2;
	private String showNbr3;
	private String showNbr4;
	
	private List<AppShowNbrs> appShowNbrs ;
	private AppShowNbrs voiceCodeNbr ;
	private Client client;
	private String znyzck;
	
	/*---------------------------------------------新增应用--------------------------------*/
	@Action("/app/add")
	public String add(){
		boolean isContainsJs = check(app, whiteListStr,cbfunStr,cbfunurlStr,cbfunmethodStr);
		if(isContainsJs){
			StrUtil.writeMsg(response, "应用信息不合法,请检查后重试",null);
			return null;
		}
		if(validFile != null){
			String result = FileUtil.checkFile(validFile,SysConstant.VALID_FILE_MAXSIZE);
			if(result!=null){
				StrUtil.writeMsg(response,result,null);
				return null ;
			}
			type = validFileFileName.substring(validFileFileName.lastIndexOf(".")+1);
			boolean  imgType = FileUtil.checkAppFlieType(type);
			if(!imgType){
				StrUtil.writeMsg(response, "文件格式不正确", null);
				return null ;
			}
		}
		
		//构造application
		TbFlypaasUser user = getSessionUser();
		String uuid = StrUtil.getUUID();
		String sid = user.getSid();
		app.setAppSid(uuid);
		app.setSid(sid);
		app.setCreateDate(new Date());
		app.setUpdateDate(new Date());
		app.setAppType(AppConstant.APP_TYPE);
		app.setStatus(1==user.getIsProxy()?AppConstant.STATUS_1:AppConstant.STATUS_0);
		app.setCallFr(app.getCallFr()==null?0:app.getCallFr());
		app.setCkNum(app.getCkNum()==null?0:app.getCkNum());
		app.setBrand(AppConstant.APP_BRAND);
		int count = appService.addApp(app,whiteListStr,cbfunStr,cbfunurlStr,cbfunmethodStr,validFile,type);
		if(count>0){
			StrUtil.writeMsg(response, "应用名称已被占用！创建失败.",null);
			return null;
		}
		return "addSuc";
	}
	/*---------------------------------------------应用管理--------------------------------*/
	@Action("/app/appManager")
	public String appManager(){
		user = getSessionUser();
		String sid = user.getSid();
		if(StrUtil.isEmpty(page)){
			page = new PageContainer();
		}
		page.getParams().put("sid", sid);
		page.getParams().put("appName", app==null?"":app.getAppName());
		page = appService.getApp(page);
		acctBalance = acctBalanceService.getAcctBalanceBySid(sid);
		double allAppBalance = appBalanceService.getAllBalance(sid);
		BigDecimal balance = new BigDecimal(acctBalance.getBalance()+"");
		BigDecimal creditBalance = new BigDecimal(acctBalance.getCreditBalance()+"");
		BigDecimal allAppBalance1 = new BigDecimal(allAppBalance+"");
		acctBalance.setRechargeBalance(balance.add(creditBalance).subtract(allAppBalance1).doubleValue());
		acctBalance.setBalance(acctBalance.getRechargeBalance()>0?acctBalance.getRechargeBalance():0);
		return "appManager" ;
	}
	
	/*---------------------------------------------跳转到新增页--------------------------------*/
	@Action("/app/addPage")
	public String addPage(){
		appKindList = getParams(SysConstant.APP_KIND);
		paramsList = getParams(SysConstant.INDUSTRY);
		cbfunList = getParams(SysConstant.CALLBACKFUN);
		cloudList = getParams(SysConstant.PARAMS_KEY);
		user= getSessionUser();
		return "addPage";
	}
	
	/*---------------------------------------------应用名是否存在--------------------------------*/
	@Action("/app/appNameExist")
	public void appNameExist(){
		Application app = new Application();
		app.setSid(getSessionUser().getSid());
		app.setAppName(appName);
		int count = appService.appNameExist(app);
		try {
			response.getWriter().write(count+"");
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("----------------------------应用名是否存在检测失败--------------------");
			logger.error(e.getMessage());
		}
	}
	
	/*--------------------------------------------根据id获取应用详细信息--------------------------------*/
	@Action("/app/edit")
	public String edit(){
		appSid = Des3Utils.decodeDes3(appSid);
		app =appService.getAppById(appSid);
		if(app==null){
			String url = "/app/appManager" ;
			StrUtil.writeMsg(response, "应用不存在", url);
			return null ;
		}
		//行业类型
		appKindList = getParams(SysConstant.APP_KIND);
		paramsList = getParams(SysConstant.INDUSTRY);
		cloudList = getParams(SysConstant.PARAMS_KEY);
		routePolicyList = getParams(SysConstant.ROUTE_POLICY);
		routeNumList = getParams(SysConstant.ROUTE_NUM);
		//功能
		cbfunList = getParams(SysConstant.CALLBACKFUN);
		//回调
		cbInfoList = cbService.get(appSid) ;
		//组装结果
		cbfunList = buildMapList(cbfunList, cbInfoList);
		//白名单
		whiteList = getWhiteList(appSid);
		user = getSessionUser();
		return "editPage";
	}
	
	private  List<Params> buildMapList(List<Params> cbfunList,List<TbSrvCallback> cbInfoList){
		for(Params p : cbfunList){
			String pk = p.getParamKey();
			for(TbSrvCallback tb : cbInfoList){
				String tbpk = tb.getCallType()+"";
				if(pk!=null && pk.equals(tbpk)){
					p.setTcallBack(tb);
				}
			}
		}
		return cbfunList;
	}
	/*---------------------------------------------删除应用--------------------------------*/
	@Action("/app/delete")
	public String delete(){
		String sid = getSessionUser().getSid();
		Application app = new Application();
		app.setAppSid(appSid);
		app.setSid(sid);
		app.setStatus(AppConstant.STATUS_3);
		app.setUpdateDate(DateUtil.getCurrentDate());
		
		Application application = appService.getAppBySidAppSid(app);
		if(StrUtil.isEmpty(application)){
			StrUtil.writeMsg(response, "应用不存在", null);
			return null ;
		}
		//删除app
		appService.delete(app);
		//刷新redis
		RefreshRedis.updateApp(app.getAppSid(),sid);
		return "deleteSuc" ;
	}
	/*---------------------------------------------应用上线--------------------------------*/
	@Action("/app/onLine")
	public String onLine(){
		Application app = new Application();
		app.setAppSid(appSid);
		app.setSid(getSessionUser().getSid());
		app.setStatus(AppConstant.STATUS_4);
		updateApp(app);
		return "onLineSuc" ;
	}
	
	/*---------------------------------------------是否认证--------------------------------*/
	@Action("/app/isAuth")
	public void isAuth(){
		String sid = getSessionUser().getSid();
		String status = userService.findUserById(sid).getOauthStatus();
		try {
			response.getWriter().write(status==null?"":status);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("-------------------------检测是否认证失败----------------------------");
			logger.error(e.getMessage());
		}
	}
	/*---------------------------------------------应用更新--------------------------------*/
	@Action("/app/update")
	public String update(){
		boolean isContainsJs = check(app, whiteListStr,cbfunStr,cbfunurlStr,cbfunmethodStr);
		if(isContainsJs){
			StrUtil.writeMsg(response, "应用信息不合法,请检查后重试",null);
			return null;
		}
		if(validFile != null){
			String result = FileUtil.checkFile(validFile,SysConstant.VALID_FILE_MAXSIZE);
			if(result!=null){
				StrUtil.writeMsg(response,result,null);
				return null ;
			}
			type = validFileFileName.substring(validFileFileName.lastIndexOf(".")+1);
			boolean  fileType = FileUtil.checkAppFlieType(type);
			if(!fileType){
				StrUtil.writeMsg(response, "文件格式不正确", null);
				return null ;
			}
		}
		
		znyzck = Des3Utils.decodeDes3(znyzck);
		if(!znyzck.equals("1")&&!znyzck.equals("2")){
			StrUtil.writeMsg(response, "参数错误",null);
			return null;
		}
		if(znyzck.equals("2")){
			app.setCkKey("");
			app.setCkCallbackUrl("");
			app.setCkWay(0);
			app.setCkEnddate(0);
		}
		String sid = getSessionUser().getSid();
		app.setSid(sid);
		/*if(StrUtil.isEmpty(app.getMaxHopNum())){
			app.setMaxHopNum("0");;
		}
		if(StrUtil.isEmpty(app.getRouteNum())){
			app.setRouteNum("1");;
		}
		if(StrUtil.isEmpty(app.getRoutePolicy())){
			app.setRoutePolicy("1");;
		}
		if(StrUtil.isEmpty(app.getNodeMaxPrice())){
			app.setNodeMaxPrice("0");;
		}*/
		appService.updateApp(app, whiteListStr, cbfunStr, cbfunurlStr, cbfunmethodStr, response, validFile,type);
		//刷新redis
		RefreshRedis.updateApp(app.getAppSid(),sid);
		return "updateSuc" ;
	}
	/*--------------------------------------------应用详细信息--------------------------------*/
	@Action("/app/appInfo")
	public String appInfo(){
		appSid = Des3Utils.decodeDes3(appSid);
		app =appService.getAppById(appSid);
		if(app==null){
			String url = "/app/appManager" ;
			StrUtil.writeMsg(response, "应用不存在", url);
			return null ;
		}
		//行业类型
		appKindList = getParams(SysConstant.APP_KIND);
		paramsList = getParams(SysConstant.INDUSTRY);
		cloudList = getParams(SysConstant.PARAMS_KEY);
		//功能
		cbfunList = getParams(SysConstant.CALLBACKFUN);
		//回调
		cbInfoList = cbService.get(appSid) ;
		//组装结果
		cbfunList = buildMapList(cbfunList, cbInfoList);
		//白名单
		whiteList = getWhiteList(appSid);
		user = getSessionUser();
		return "appInfoSuc";
	}
	/*--------------------------------------------创建client--------------------------------*/
	@Action("/app/createClient")
	public String createClient(){
		TbFlypaasUser user = getSessionUser();
		for(int i=0;i<SysConstant.cNum;i++){
			RestClient.createClient(user.getSid(), user.getToken(), appSid);//同步创建SDKID
		}
		return "createClinetSuc";
	}
	/*--------------------------------------------client列表--------------------------------*/
	@Action("/app/clientList")
	public String clientList(){
		if(StrUtil.isEmpty(page)){
			page = new PageContainer();
		}
		page.getParams().put("app_sid", appSid);
		page = clientService.getClientBySid(page);
		return "clientList";
	}
	private void updateApp(Application app){
		app.setUpdateDate(DateUtil.getCurrentDate());
		appService.update(app);
		//刷新redis
		String sid = getSessionUser().getSid();
		RefreshRedis.updateApp(app.getAppSid(),sid);
	}
	private TbSrvWhiteList getWhiteList(String appSid){
		return whiteService.get(appSid);
	}
	private boolean check(Application app,Object...objs){
		boolean result = false;
		if(!StrUtil.isEmpty(app)){
			if(StrUtil.isEmpty(app.getAppName())){
				result =  true;
			}
			if(StrUtil.isEmpty(app.getAppKind())){
				result =  true;
			}
			if(StrUtil.isEmpty(app.getIndustry())||Integer.parseInt(app.getIndustry())==0){
				result =  true;
			}
			if(StrUtil.checkJsForStr(app.getAppName())){
				result =  true;
			}
			if(StrUtil.constainsSymbol(app.getAppName())){
				result =  true;
			}
			if(StrUtil.checkJsForStr(app.getAppKind())){
				result =  true;
			}
			if(StrUtil.checkJsForStr(app.getIndustry())){
				result =  true;
			}
			if(StrUtil.checkJsForStr(app.getIsShowNbr())){
				result =  true;
			}
			for(Object oj : objs){
				if(StrUtil.isEmpty(oj)){
					continue;
				}
				if(StrUtil.checkJsForStr(oj.toString())){
					result= true;
					break;
				}
			}
		}
		return result;
		
	}
	/******************************************************************get set******************************************************************************/
	
	public Application getApp() {
		return app;
	}
	public void setApp(Application app) {
		this.app = app;
	}

	public void setAppList(List<Application> appList) {
		this.appList = appList;
	}

	public String getWhiteListStr() {
		return whiteListStr;
	}

	public void setWhiteListStr(String whiteListStr) {
		this.whiteListStr = whiteListStr;
	}


	public List<Application> getAppList() {
		return appList;
	}

	public List<Params> getCbfunList() {
		return cbfunList;
	}

	public void setCbfunList(List<Params> cbfunList) {
		this.cbfunList = cbfunList;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getCbfunStr() {
		return cbfunStr;
	}
	public void setCbfunStr(String cbfunStr) {
		this.cbfunStr = cbfunStr;
	}
	public String getAppSid() {
		return appSid;
	}
	public TbSrvWhiteList getWhiteList() {
		return whiteList;
	}
	public void setWhiteList(TbSrvWhiteList whiteList) {
		this.whiteList = whiteList;
	}
	public void setAppSid(String appSid) {
		this.appSid = appSid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<TbSrvCallback> getCbInfoList() {
		return cbInfoList;
	}
	public void setCbInfoList(List<TbSrvCallback> cbInfoList) {
		this.cbInfoList = cbInfoList;
	}
	public TbFlypaasUser getUser() {
		return user;
	}
	public void setUser(TbFlypaasUser user) {
		this.user = user;
	}
	public List<Client> getClientList() {
		return clientList;
	}
	public void setClientList(List<Client> clientList) {
		this.clientList = clientList;
	}
	public List<TestNumber> getTestNumList() {
		return testNumList;
	}
	public void setTestNumList(List<TestNumber> testNumList) {
		this.testNumList = testNumList;
	}
	public List<CountryMobilePrefix> getMobilePrefixList() {
		return mobilePrefixList;
	}
	public void setMobilePrefixList(List<CountryMobilePrefix> mobilePrefixList) {
		this.mobilePrefixList = mobilePrefixList;
	}
	public TestNumber getTestNumber() {
		return testNumber;
	}
	public void setTestNumber(TestNumber testNumber) {
		this.testNumber = testNumber;
	}
	public AcctBalance getAcctBalance() {
		return acctBalance;
	}
	public void setAcctBalance(AcctBalance acctBalance) {
		this.acctBalance = acctBalance;
	}
	public Package getPck() {
		return pck;
	}
	public void setPck(Package pck) {
		this.pck = pck;
	}
	public String getCbfunurlStr() {
		return cbfunurlStr;
	}
	public void setCbfunurlStr(String cbfunurlStr) {
		this.cbfunurlStr = cbfunurlStr;
	}
	public String getCbfunmethodStr() {
		return cbfunmethodStr;
	}
	public void setCbfunmethodStr(String cbfunmethodStr) {
		this.cbfunmethodStr = cbfunmethodStr;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public List<String> getWhiteStrList() {
		return whiteStrList;
	}
	public void setWhiteStrList(List<String> whiteStrList) {
		this.whiteStrList = whiteStrList;
	}
	public List<Client> getNotBindClientList() {
		return notBindClientList;
	}
	public void setNotBindClientList(List<Client> notBindClientList) {
		this.notBindClientList = notBindClientList;
	}
	public String getShowNbr1() {
		return showNbr1;
	}
	public void setShowNbr1(String showNbr1) {
		this.showNbr1 = showNbr1;
	}
	public String getShowNbr2() {
		return showNbr2;
	}
	public void setShowNbr2(String showNbr2) {
		this.showNbr2 = showNbr2;
	}
	public String getShowNbr3() {
		return showNbr3;
	}
	public void setShowNbr3(String showNbr3) {
		this.showNbr3 = showNbr3;
	}
	public String getShowNbr4() {
		return showNbr4;
	}
	public void setShowNbr4(String showNbr4) {
		this.showNbr4 = showNbr4;
	}
	public List<AppShowNbrs> getAppShowNbrs() {
		return appShowNbrs;
	}
	public void setAppShowNbrs(List<AppShowNbrs> appShowNbrs) {
		this.appShowNbrs = appShowNbrs;
	}
	public AppShowNbrs getVoiceCodeNbr() {
		return voiceCodeNbr;
	}
	public void setVoiceCodeNbr(AppShowNbrs voiceCodeNbr) {
		this.voiceCodeNbr = voiceCodeNbr;
	}
	public List<Params> getCloudList() {
		return cloudList;
	}
	public void setCloudList(List<Params> cloudList) {
		this.cloudList = cloudList;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public List<Params> getAppKindList() {
		return appKindList;
	}
	public void setAppKindList(List<Params> appKindList) {
		this.appKindList = appKindList;
	}
	public PageContainer getPage() {
		return page;
	}
	public void setPage(PageContainer page) {
		this.page = page;
	}
	public String getZnyzck() {
		return znyzck;
	}
	public void setZnyzck(String znyzck) {
		this.znyzck = znyzck;
	}
	public File getValidFile() {
		return validFile;
	}
	public void setValidFile(File validFile) {
		this.validFile = validFile;
	}
	public String getValidFileFileName() {
		return validFileFileName;
	}
	public void setValidFileFileName(String validFileFileName) {
		this.validFileFileName = validFileFileName;
	}
	public List<Params> getRoutePolicyList() {
		return routePolicyList;
	}
	public void setRoutePolicyList(List<Params> routePolicyList) {
		this.routePolicyList = routePolicyList;
	}
	public List<Params> getRouteNumList() {
		return routeNumList;
	}
	public void setRouteNumList(List<Params> routeNumList) {
		this.routeNumList = routeNumList;
	}
	
	
}
