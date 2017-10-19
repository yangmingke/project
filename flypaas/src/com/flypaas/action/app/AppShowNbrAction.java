package com.flypaas.action.app;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.action.BaseAction;
import com.flypaas.constant.AppConstant;
import com.flypaas.entity.AppShowNbrs;
import com.flypaas.entity.Application;
import com.flypaas.entity.vo.PageContainer;
import com.flypaas.utils.DateUtil;
import com.flypaas.utils.Des3Utils;
import com.flypaas.utils.StrUtil;

@Controller
@Scope("prototype")
@Results({
	@Result(name="showNbr",location="/page/app/app_mobile.jsp"),
	@Result(name="showNbrIndex",type="redirectAction",params={"appSid","${des3AppSid}"},location="showNbrList"),
	@Result(name="addSuc",type="redirectAction",params={"appSid","${des3AppSid}"},location="showNbrList"),
})
public class AppShowNbrAction extends BaseAction {

	private String appSid;
	private String des3AppSid;
	private AppShowNbrs appShowNbrs;
	private PageContainer page;
	private String nbrList;
	private String nbr;
	private Application app;
	
	@Action("/app/showNbrList")
	public String showNbrList(){
		appSid = Des3Utils.decodeDes3(appSid);
		app =appService.getAppById(appSid);
		if(app==null){
			String url = "/app/appManager" ;
			StrUtil.writeMsg(response, "应用不存在", url);
			return null ;
		}
		if(StrUtil.isEmpty(page)){
			page = new PageContainer();
		}
		page.getParams().put("appSid",appSid);
		page.getParams().put("sid", getSessionUser().getSid());
		if(!StrUtil.isEmpty(nbr)){
			page.getParams().put("nbr",nbr);
		}
		page = appShowNbrsService.getAppShowNbrs(page);
		return "showNbr";
	}
	@Action("/app/addShowNbr")
	public String addShowNbr(){
		if(StrUtil.isEmpty(nbrList)){
			StrUtil.writeMsg(response, "参数错误", null);
			return null ;
		}
		des3AppSid = appSid;
		appSid = Des3Utils.decodeDes3(appSid);
		if(StrUtil.isEmpty(appSid)){
			StrUtil.writeMsg(response, "参数错误", null);
			return null;
		}
		nbrList = StrUtil.getStringNoEnter(nbrList, "#");
		String nbrAarray [] = nbrList.split("#");
		AppShowNbrs appShowNbrs = null ;
		for(String str:nbrAarray){
			if(!StrUtil.check400AndFixPhone(str)){
				StrUtil.writeMsg(response, "号码非法", null);
				return null;
			}
			appShowNbrs = new AppShowNbrs();
			appShowNbrs.setAppSid(appSid);
			appShowNbrs.setSid(getSessionUser().getSid());
			appShowNbrs.setNbr(str);
			AppShowNbrs temp =  appShowNbrsService.get(appShowNbrs);
			appShowNbrs.setSid(getSessionUser().getSid());
			appShowNbrs.setStatus(AppConstant.SHOW_NBR_STATUS_2);
			appShowNbrs.setCreateDate(DateUtil.getCurrentDate());
			appShowNbrs.setUpdateDate(DateUtil.getCurrentDate());
			if(StrUtil.isEmpty(temp)){
				appShowNbrsService.add(appShowNbrs);
			}
		}
		return "addSuc";
	}
	@Action("/app/delShowNbr")
	public String delShowNbr(){
		if(StrUtil.isEmpty(appShowNbrs.getAppSid())||StrUtil.isEmpty(appShowNbrs.getNbr())){
			StrUtil.writeMsg(response, "参数错误", null);
			return null ;
		}
		des3AppSid = appShowNbrs.getAppSid();
		appSid = Des3Utils.decodeDes3(des3AppSid);
		appShowNbrs.setAppSid(appSid);
		appShowNbrs.setSid(getSessionUser().getSid());
		appShowNbrs.setStatus(AppConstant.SHOW_NBR_STATUS_1);
		appShowNbrs.setUpdateDate(DateUtil.getCurrentDate());
		appShowNbrsService.updateStatus(appShowNbrs);
		return "showNbrIndex";
	}
	@Action("/app/updateShowNbr")
	public String updateShowNbr(){
		if(StrUtil.isEmpty(appShowNbrs)||StrUtil.isEmpty(appShowNbrs.getAppSid())||StrUtil.isEmpty(appShowNbrs.getNbr())){
			StrUtil.writeMsg(response, "参数错误", null);
			return null ;
		}
		des3AppSid = appShowNbrs.getAppSid();
		appSid = Des3Utils.decodeDes3(des3AppSid);
		appShowNbrs.setAppSid(appSid);
		appShowNbrs.setSid(getSessionUser().getSid());
		appShowNbrs.setStatus(AppConstant.SHOW_NBR_STATUS_2);
		appShowNbrs.setUpdateDate(DateUtil.getCurrentDate());
		appShowNbrsService.updateStatus(appShowNbrs);
		return "showNbrIndex";
	}
	public String getAppSid() {
		return appSid;
	}
	public void setAppSid(String appSid) {
		this.appSid = appSid;
	}
	
	public PageContainer getPage() {
		return page;
	}
	public void setPage(PageContainer page) {
		this.page = page;
	}
	public AppShowNbrs getAppShowNbrs() {
		return appShowNbrs;
	}
	public void setAppShowNbrs(AppShowNbrs appShowNbrs) {
		this.appShowNbrs = appShowNbrs;
	}
	public String getNbrList() {
		return nbrList;
	}
	public void setNbrList(String nbrList) {
		this.nbrList = nbrList;
	}
	public String getDes3AppSid() {
		return des3AppSid;
	}
	public void setDes3AppSid(String des3AppSid) {
		this.des3AppSid = des3AppSid;
	}
	public String getNbr() {
		return nbr;
	}
	public void setNbr(String nbr) {
		this.nbr = nbr;
	}
	public Application getApp() {
		return app;
	}
	public void setApp(Application app) {
		this.app = app;
	}	
	
	
}
