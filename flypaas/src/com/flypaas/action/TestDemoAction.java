package com.flypaas.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.constant.AppConstant;
import com.flypaas.constant.SysConstant;
import com.flypaas.constant.UserConstant;
import com.flypaas.entity.Application;
import com.flypaas.entity.Client;
import com.flypaas.entity.Params;
import com.flypaas.entity.TbFlypaasUser;
import com.flypaas.entity.TestWhiteList;
import com.flypaas.httpclient.impl.RefreshRedis;
import com.flypaas.utils.DateUtil;
import com.flypaas.utils.StrUtil;
@Controller
@Scope("prototype")
@Results({
	@Result(name="testDemoSuc",location="/page/app/test_demo.jsp"),
	@Result(name="testMobileSuc",location="/page/app/test_mobile.jsp"),
	@Result(name="demoOpt",type="redirectAction",location="testMobile")
})
public class TestDemoAction extends BaseAction {
	private TbFlypaasUser user ;
	private List<Params> paramsList;
	private List<Client> clientList;
	private Application app;
	private TestWhiteList testWhiteListObj;
	private List<TestWhiteList> testWhiteList;
	private Client client;
	private String mobile ;
	Logger logger = LoggerFactory.getLogger(TestDemoAction.class);
	

	/*---------------------------------------------测试demo--------------------------------*/
	@Action("/app/testDemo")
	public String testDemo(){
		user = getSessionUser();
		paramsList = getParams(SysConstant.REST);
		clientList = clientService.getTestClientBySid(user.getSid());
		app = appService.getTestApp(user.getSid());
		return "testDemoSuc" ;
	}
	/*---------------------------------------------测试手机--------------------------------*/
	@Action("/app/testMobile")
	public String testMobile(){
		user = getSessionUser();
		testWhiteList = testWhilteListService.list(user.getSid());
		return "testMobileSuc" ;
	}
	@Action("/app/testNumAdd")
	public String addTestNum(){
		if(StrUtil.isEmpty(testWhiteListObj)||StrUtil.isEmpty(testWhiteListObj.getMobile())){
			logger.error("参数不正确,请检查后重试");
			StrUtil.writeMsg(response, "参数不正确,请检查后重试",null);
			return null ;
		}
		if(!StrUtil.checkPhoneForStr(testWhiteListObj.getMobile())){
			logger.error("参数不正确,请检查后重试");
			StrUtil.writeMsg(response, "参数不正确,请检查后重试",null);
			return null ;
		}
		String sid = getSessionUser().getSid();
		testWhiteListObj.setSid(sid);
		testWhiteListObj.setType(AppConstant.TEST_DEMO_MOBILE_TYPE_2);
		testWhiteListObj.setStatus(AppConstant.TEST_DEMO_MOBILE_STATUS_1);
		testWhiteListObj.setCreateDate(DateUtil.getCurrentDate());
		testWhilteListService.add(testWhiteListObj);
		RefreshRedis.updateTestWhiteList(sid);
		return "demoOpt";
	}
	@Action("/app/testNumUpdate")
	public String updateTestNum(){
		if(StrUtil.isEmpty(mobile)){
			logger.error("参数不正确,请检查后重试");
			StrUtil.writeMsg(response, "参数不正确,请检查后重试",null);
			return null ;
		}
		String sid = getSessionUser().getSid();
		testWhiteListObj = new TestWhiteList();
		testWhiteListObj.setSid(sid);
		testWhiteListObj.setMobile(mobile);
		testWhiteListObj.setUpdateDate(DateUtil.getCurrentDate());
		testWhilteListService.delete(testWhiteListObj);
		RefreshRedis.updateTestWhiteList(sid);
		return "demoOpt";
	}
	/*--------------------------------------------判断号码是否已被绑定--------------------------------*/
	@Action("/app/testNumExits")
	public void testNumExits(){
		TestWhiteList testWhiteList = new TestWhiteList();
		testWhiteList.setSid(getSessionUser().getSid());
		testWhiteList.setMobile(mobile);
		testWhiteList = testWhilteListService.get(testWhiteList);
		printMsg(testWhiteList==null?UserConstant.NOTEXIST:UserConstant.EXIST);
	}
	
	
	public TbFlypaasUser getUser() {
		return user;
	}
	public void setUser(TbFlypaasUser user) {
		this.user = user;
	}
	public List<Params> getParamsList() {
		return paramsList;
	}
	public void setParamsList(List<Params> paramsList) {
		this.paramsList = paramsList;
	}
	public List<Client> getClientList() {
		return clientList;
	}
	public void setClientList(List<Client> clientList) {
		this.clientList = clientList;
	}
	public Application getApp() {
		return app;
	}
	public void setApp(Application app) {
		this.app = app;
	}
	public TestWhiteList getTestWhiteListObj() {
		return testWhiteListObj;
	}
	public void setTestWhiteListObj(TestWhiteList testWhiteListObj) {
		this.testWhiteListObj = testWhiteListObj;
	}
	public List<TestWhiteList> getTestWhiteList() {
		return testWhiteList;
	}
	public void setTestWhiteList(List<TestWhiteList> testWhiteList) {
		this.testWhiteList = testWhiteList;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
}
