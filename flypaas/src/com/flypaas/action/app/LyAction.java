package com.flypaas.action.app;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.action.BaseAction;
import com.flypaas.entity.Application;
import com.flypaas.entity.TbFlypaasUser;
import com.flypaas.entity.vo.PageContainer;
import com.flypaas.utils.Des3Utils;
import com.flypaas.utils.StrUtil;

@Controller
@Scope("prototype")
@Results({
	@Result(name="lyList",location="/page/app/lyList.jsp"),
	@Result(name="lyListAc",type="redirectAction",params={"appSid","${appSid}"},location="lyList"),
	@Result(name="lyDetailView",location="/page/app/lyDetail.jsp")
})
public class LyAction extends BaseAction {
	private String appSid;
	private Application app;
	private PageContainer page;
	private Map<String, Object> map;
	private String statId;
	private TbFlypaasUser user;
	
	@Action("/app/lyList")
	public String lyList(){
		appSid = Des3Utils.decodeDes3(appSid);
		if(StrUtil.isEmpty(appSid)){
			StrUtil.writeMsg(response, "应用不存在", null);
			return null;
		}
		if(StrUtil.isEmpty(page)){
			page = new PageContainer();
		}
		page.getParams().put("sid", getSessionUser().getSid());
		page.getParams().put("appSid", appSid);
		map = new HashMap<String, Object>();
		map.put("sid", getSessionUser().getSid());
		map.put("appSid", appSid);
		app =appService.getAppById(appSid);
		page = otherService.lyList(page);
		map = otherService.lyFileSize(map);
		user = userService.findUserById(getSessionUser().getSid());
		return "lyList";
	}
	@Action("/app/lyDel")
	public String lyDel(){
		String _appSid = Des3Utils.decodeDes3(appSid);
		if(StrUtil.isEmpty(_appSid)||StrUtil.isEmpty(statId)){
			StrUtil.writeMsg(response, "参数不合法", null);
			return null;
		}
		map = new HashMap<String, Object>();
		map.put("sid", getSessionUser().getSid());
		map.put("appSid", _appSid);
		map.put("statId", statId);
		otherService.lyDel(map);
		return "lyListAc";
	}
	@Action("/app/appFileimeSet")
	public String appFileimeSet(){
		if(StrUtil.isEmpty(app)||StrUtil.isEmpty(app.getAppSid())){
			StrUtil.writeMsg(response, "参数错误", null);
			return null;
		}
		String _appSid = Des3Utils.decodeDes3(app.getAppSid());
		if(StrUtil.isEmpty(_appSid)){
			StrUtil.writeMsg(response, "参数错误", null);
			return null;
		}
		appSid = app.getAppSid();
		app.setAppSid(_appSid);
		app.setSid(getSessionUser().getSid());
		appService.update(app);
		return "lyListAc";
	}
	@Action("/app/lyDetail")
	public String lyDetail(){
		if(page == null){
			page = new PageContainer();
		}
		map = new HashMap<String, Object>();
		map.put("sid", getSessionUser().getSid());
		map.put("id", statId);
		map = otherService.ly(map);
		if(map.size()>0){
			page.getParams().put("sid", getSessionUser().getSid());
			page.getParams().put("appSid", map.get("app_sid"));
			page.getParams().put("date", map.get("stat_date"));
			page = consumeService.lyDetail(page);
		}
		return "lyDetailView";
	}
	public String getAppSid() {
		return appSid;
	}

	public void setAppSid(String appSid) {
		this.appSid = appSid;
	}

	public Application getApp() {
		return app;
	}

	public void setApp(Application app) {
		this.app = app;
	}

	public PageContainer getPage() {
		return page;
	}

	public void setPage(PageContainer page) {
		this.page = page;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public String getStatId() {
		return statId;
	}
	public void setStatId(String statId) {
		this.statId = statId;
	}
	public TbFlypaasUser getUser() {
		return user;
	}
	public void setUser(TbFlypaasUser user) {
		this.user = user;
	}
	
	
}
