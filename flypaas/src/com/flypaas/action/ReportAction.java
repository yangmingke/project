package com.flypaas.action;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.constant.UserConstant;
import com.flypaas.entity.AppBalance;
import com.flypaas.entity.Application;
import com.flypaas.utils.DateUtil;
import com.flypaas.utils.JsonUtil;
import com.flypaas.utils.StrUtil;

@Controller
@Scope("prototype")
@Results({
	@Result(name="billGraphSuc",location="/page/bill/billGraph.jsp")
})
public class ReportAction extends BaseAction {
	private double [] array1 = null ;
	private String appSid;
	private Application app;
	private List<Application> appList;
	
	/*----------------------------今天的流量消费--------------------------------*/
	@Action("/user/todayTrafficCs")
	public void todayTrafficCs(){
		String sid = getSessionUser().getSid();
		List<Map<String, Object>> data = consumeService.getCurrentCsm(buildTrafficParamaterMap(sid, UserConstant.TODAY_CSM_TRAFFIC));
		buildTrafficCsCsmDayResult(data,UserConstant.TODAY_CSM_TRAFFIC);
	}

	/*----------------------------今天的语音消费--------------------------------*/
	@Action("/user/todayVoiceCs")
	public void todayVoiceCs(){
		String sid = getSessionUser().getSid();
		List<Map<String, Object>> date = consumeService.getCurrentCsm(buildParamaterMap(sid, UserConstant.TODAY_CSM_VOICE));
		buildCsmDayResult(date,"voice");
	}
	
	/*----------------------------今天的视频消费--------------------------------*/
	@Action("/user/todayVideoCs")
	public void todayVideoCs(){
		String sid = getSessionUser().getSid();
		List<Map<String, Object>> date = consumeService.getCurrentCsm(buildParamaterMap(sid, UserConstant.TODAY_CSM_VIDEO));
		buildCsmDayResult(date,"video");
	}
	
	/*----------------------------今天的sms发送量--------------------------------*/
	@Action("/user/todaySmsCs")
	public void todaySmsCs(){
		String sid = getSessionUser().getSid();
		List<Map<String, Object>> date = consumeService.getCurrentCsm(buildParamaterMap(sid, UserConstant.TODAY_CSM_SMS));
		buildCsmDayResult(date,"sms");
	}
	/*----------------------------今天的im发送量--------------------------------*/
	@Action("/user/todayImCs")
	public void todayImCs(){
		String sid = getSessionUser().getSid();
		List<Map<String, Object>> date = consumeService.getCurrentCsm(buildParamaterMap(sid, UserConstant.TODAY_CSM_IM));
		buildCsmDayResult(date,"im");
	}
	/*----------------------------今天的语音验证码消费--------------------------------*/
	@Action("/user/todayVoicecheckcodeCs")
	public void todayVoicecheckcodeCs(){
		String sid = getSessionUser().getSid();
		List<Map<String, Object>> date = consumeService.getCurrentCsm(buildParamaterMap(sid, UserConstant.TODAY_CSM_VOICECODE));
		buildCsmDayResult(date,"code");
	}
	/*----------------------------今天的活跃的client--------------------------------*/
	@Action("/user/todayClientCs")
	public void todayClientCs(){
		String sid = getSessionUser().getSid();
		List<Map<String, Object>> date = consumeService.getCurrentCsm(buildParamaterMap(sid, UserConstant.TODAY_CSM_CLIENT));
		buildCsmDayResult(date,"client");
	}
	/*----------------------------今天的云验证--------------------------------*/
	@Action("/user/todayCloudCs")
	public void todayCloudCs(){
		String sid = getSessionUser().getSid();
		List<Map<String, Object>> date = consumeService.getCurrentCsm(buildParamaterMap(sid, UserConstant.TODAY_CSM_CLOUD));
		buildCsmDayResult(date,"cloud");
	}
	@Action("/bill/billGraph")
	public String billGraph(){
		appList = appService.getAllAppBySid(getSessionUser().getSid());
		app = appList.get(0);
		return "billGraphSuc";
	}
	@Action("/bill/appCsmDataByMonth")
	public void appCsmDataByMonth(){
		if(StrUtil.isEmpty(appSid)){
			StrUtil.writeMsg(response, "appSid不能为空", null);
			return;
		}
		AppBalance appBalance = new AppBalance();
		appBalance.setSid(getSessionUser().getSid());
		appBalance.setAppSid(appSid);
		appBalance = appBalanceService.getAppBalance(appBalance);
		Map<String, String> map = new HashMap<String, String>();
		map.put("appSid",appSid);
		map.put("month",DateUtil.getCurrentMonth());
		List<Map<String, Object>> data = consumeService.appCsmDataByMonth(map);
		buildCsmAppMonthResult(data,appBalance==null?0:appBalance.getBalance());
		
	}
	private void buildCsmAppMonthResult(List<Map<String, Object>> dataList,double appBalance){
		if(StrUtil.isEmpty(dataList)){
			return;
		}
		array1 = new double[dataList.size()];
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> mapRt = new HashMap<String, Object>();
		List<String> titleList = new ArrayList<String>();
		double allCsmCount = 0 ;
		for(int i =0 ;i<dataList.size();i++){
			Map<String, Object> map = dataList.get(i);
			double fee = Double.parseDouble(map.get("fee").toString());
			BigDecimal dec = new BigDecimal(fee+"");
			BigDecimal dec1 = new BigDecimal(allCsmCount+"");
			String date = map.get("date").toString()+"号";
			array1[i]=fee;
			allCsmCount = dec.add(dec1).doubleValue();
			titleList.add(date);
		}
		mapRt.put("name", "消费量");
		mapRt.put("data", array1);
		list.add(mapRt);
		String json = allCsmCount+"T"+appBalance+"T"+JsonUtil.toJsonStr(titleList)+"T"+JsonUtil.toJsonStr(list);
		printMsg(json);
	}
	private void buildTrafficCsCsmDayResult(List<Map<String, Object>> dataList,String type){
		String temp = DateUtil.dateToStr(DateUtil.getCurrentDate(),DateUtil.HM ).substring(0, 2);
		int hm = Integer.parseInt(temp) + 1;
		array1 = new double[hm];//大客户消费
		if(dataList!=null && dataList.size()>0){
			int size = dataList.size();
			for(int i=0 ; i<size ; i++){
				Map<String, Object> map = dataList.get(i);
				long big_cust_fee = map.get("traffic_out")==null?0:(long)map.get("traffic_out");
				Date date = (Date) map.get("datetime");
				date = DateUtil.addMinutes(date, -30);
				int hr = date.getHours();
				array1[hr]=array1[hr]+big_cust_fee;
			}
		}
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> mapRt = new HashMap<String, Object>();
		if(type.equals("voice")){
			mapRt.put("name", "消费量");
		}else if(type.equals("sms")){
			mapRt.put("name", "发送量");
		}else if(type.equals("im")){
			mapRt.put("name", "发送量");
		}else if(type.equals("code")){
			mapRt.put("name", "发送量");
		}else if(type.equals("client")){
			mapRt.put("name", "活跃个数");
		}else{
			mapRt.put("name", "消费量");
		}
		mapRt.put("data", array1);
		list.add(mapRt);
		String json = JsonUtil.toJsonStr(list);
		printMsg(json);
	}
	private void buildCsmDayResult(List<Map<String, Object>> dataList,String type){
		String temp = DateUtil.dateToStr(DateUtil.getCurrentDate(),DateUtil.HM ).substring(0, 2);
		int hm = Integer.parseInt(temp);
		array1 = new double[hm];//大客户消费
		if(dataList!=null && dataList.size()>0){
			int size = dataList.size();
			for(int i=0 ; i<size ; i++){
				Map<String, Object> map = dataList.get(i);
				String big_cust_fee = map.get("csm")==null?"0":map.get("csm").toString();
				int hr = Integer.parseInt(map.get("hr").toString());
				array1[hr]=Double.parseDouble(big_cust_fee);
			}
		}
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> mapRt = new HashMap<String, Object>();
		if(type.equals("voice")){
			mapRt.put("name", "消费量");
		}else if(type.equals("sms")){
			mapRt.put("name", "发送量");
		}else if(type.equals("im")){
			mapRt.put("name", "发送量");
		}else if(type.equals("code")){
			mapRt.put("name", "发送量");
		}else if(type.equals("client")){
			mapRt.put("name", "活跃个数");
		}else{
			mapRt.put("name", "消费量");
		}
		mapRt.put("data", array1);
		list.add(mapRt);
		String json = JsonUtil.toJsonStr(list);
		printMsg(json);
	}
	private Map<String, Object> buildParamaterMap(String sid,String type){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sid", sid);
		map.put("type", type);
		map.put("time",DateUtil.dateToStr(DateUtil.getCurrentDate(), DateUtil.YM_NO_SLASH));
		map.put("datetime",DateUtil.dateToStr(DateUtil.getCurrentDate(), DateUtil.YMR_SLASH));
		return map;
	}
	private Map<String, Object> buildTrafficParamaterMap(String sid,String type){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sid", sid);
		map.put("type", type);
		map.put("time",DateUtil.dateToStr(DateUtil.getCurrentDate(), DateUtil.NO_SLASH));
		return map;
	}
	public String getAppSid() {
		return appSid;
	}
	public void setAppSid(String appSid) {
		this.appSid = appSid;
	}
	public List<Application> getAppList() {
		return appList;
	}
	public void setAppList(List<Application> appList) {
		this.appList = appList;
	}
	public Application getApp() {
		return app;
	}
	public void setApp(Application app) {
		this.app = app;
	}
	
	
}
