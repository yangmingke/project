package com.flypaas.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.constant.SysConstant;
import com.flypaas.entity.Params;
import com.flypaas.entity.TbFlypaasUser;
import com.flypaas.utils.DateUtil;
import com.flypaas.utils.JsonUtil;
import com.flypaas.utils.StrUtil;

@Controller
@Scope("prototype")
@Results({
	@Result(name="addPageSuc",location="/page/coupon/edit.jsp"),
	@Result(name="querySuc",location="/page/coupon/query.jsp"),
	@Result(name="scratchSuc",location="/page/scratch/index.jsp"),
	@Result(name="couponSuc",location="/page/coupon/coupon_suc.jsp"),
	@Result(name="couponPageSuc",location="/page/coupon/coupon.jsp")
})
public class CouponAction extends BaseAction {

	private String couponMoney;
	private int count;
	private String meetId;
	private String endDate;
	private String createDate;
	private Map<String,Object> map ;
	private List<Map<String,Object>> list ;
	private List<Map<String,Object>> date ;
	private TbFlypaasUser user;
	private String couponNum;
	private String isMeet;
	private String paramValue;
	private String expDate;
	private List<Map<String, Object>> paramList;
	
	Logger logger = LoggerFactory.getLogger(CouponAction.class);
	
	@Action("/coupon/addPage")
	public String addPage(){
		user = getSessionUser();
		paramList = paramsService.getEventType();
		return "addPageSuc";
	}
	@Action("/coupon/add")
	public void addCoupon(){
		if(check(meetId,couponMoney)){
			StrUtil.writeMsg(response, "请输入展会名称", null);
			return;
		}
		if(count<=0){
			StrUtil.writeMsg(response, "请输入合法的代金券数量", null);
			return;
		}
		String couponNum = null ;
		Date createDate = DateUtil.getCurrentDate();
		if(!StrUtil.isEmpty(paramValue)){
			paramValue = SysConstant.PARAM_EVENTTYPE+paramValue;
		}
		for(int i=0 ;i<count ; i++){
			map = new HashMap<String,Object>();
			couponNum = random8(paramValue);
			map.put("couponNum",couponNum);
			map.put("money",couponMoney);
			map.put("meetId",meetId);
			map.put("endDate",DateUtil.strToDate(endDate,DateUtil.YMR_SLASH));
			map.put("createDate",createDate);
			map.put("expDate",expDate.equals("")?null:expDate);
			if(isMeet!=null && isMeet.equals("2")){
				map.put("sended", 0);
			}else{
				map.put("sended", 1);
			}
			couponService.add(map);
		}
		if(isMeet!=null && isMeet.equals("2")){
			couponService.addMeet(meetId);
		}
		StrUtil.writeMsg(response, "生成完成", null);
	}
	@Action("/coupon/query")
	public String query(){
		map = new HashMap<String, Object>();
		if(!StrUtil.isEmpty(meetId)){
			map.put("meetId",meetId);
		}
		if(!StrUtil.isEmpty(createDate)){
			map.put("createDate",createDate);
		}
		if(!StrUtil.isEmpty(couponMoney)){
			int money = Integer.parseInt(couponMoney);
			map.put("couponMoney",money);
		}
		if(map!=null && map.size()>0){
			date = couponService.getCouponByOtherList(map);
		}
		list = couponService.getMeetIdList();
		return "querySuc";
	}
	@Action("/coupon/queryCouponMoney")
	public void queryCouponMoney(){
		if(StrUtil.isEmpty(couponNum)){
			return;
		}
		map = couponService.getCouponByNum(couponNum);
		printMsg(map==null?"-1":map.get("money").toString());
	}
//	@Action("/coupon/scratch")
//	public String scratch(){
//		meetId = couponService.getCurrentMeet();
//		return "scratchSuc";
//	}
	@Action("/coupon/couponDate")
	public void couponDate(){
		map = couponService.getRodomNum(meetId);
		String json = null;
		if(map!=null){
			json = JsonUtil.toJsonStr(map);
		}
		printMsg(json);
	}
	@Action("/pay/couponPage")
	public String couponView(){
		return "couponPageSuc";
	}
	
	/*----------------------------------------优惠券充值----------------------------------*/
	@Action("/pay/coupon")
	public String coupon(){
		if(StrUtil.isEmpty(couponNum)){
			StrUtil.writeMsg(response, "兑换码不存在", null);
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();;
		String sid = getSessionUser().getSid(); ;
		String meetId = null ;
		int count = 0 ;
		long charge = 0;
		String expDate = null;
		List<Params> ps = paramsService.getParams(SysConstant.PARAM_COUPON);
		String onlyNum = ps.get(0).getParamValue();
		//一码多冲
		if(onlyNum.equals(couponNum)){
			String time = ps.get(2).getParamValue().toString();
			long endDate = DateUtil.strToDate(time,DateUtil.YMR_SLASH).getTime();
			if(endDate<DateUtil.getCurrentDate().getTime()){
				StrUtil.writeMsg(response, "兑换码已过期", null);
				return null;
			}
			map.put("couponNum",couponNum);
			map.put("sid",sid );
			charge = Integer.parseInt(ps.get(1).getParamValue());
		}
		//一码一冲
		else{
			map = couponService.getCouponByNum(couponNum);
			if(StrUtil.isEmpty(map)){
				StrUtil.writeMsg(response, "兑换码不存在", null);
				return null;
			}
			int sended = Integer.parseInt(map.get("sended").toString());
			if(sended!=1){
				logger.info("兑换码还未发放");
				StrUtil.writeMsg(response, "兑换码不存在", null);
				return null;
			}
			Date endDate = DateUtil.strToDate(map.get("end_date").toString(),DateUtil.DATE_TIME_LINE);
			long result= DateUtil.compare(DateUtil.getCurrentDate(), endDate, 0);
			if(result<=0){
				logger.info("兑换码已过期");
				StrUtil.writeMsg(response, "兑换码已过期", null);
				return null;
			}
			count = couponService.getCouponLogCountByNum(couponNum);
			if(count>0){
				StrUtil.writeMsg(response, "兑换码已经被使用过", null);
				return null;
			}
			charge = Integer.parseInt(map.get("money").toString());
			meetId = map.get("meet_id").toString();
			expDate = map.get("exp_date")==null?null:map.get("exp_date").toString();
			map = new HashMap<String, Object>();
			map.put("meetId",meetId);
			map.put("sid",sid );
		}
		//是否已经使用过该展会的券
		count = couponService.getCouponLogCount(map);
		if(count>0){
			StrUtil.writeMsg(response, "一个用户只能兑换一次", null);
			return null;
		}
		//业务充值
		if(couponNum.startsWith(SysConstant.PARAM_EVENTTYPE)){
			map = new HashMap<String, Object>();
			Date date = DateUtil.getCurrentDate();
			String eventId = couponNum.substring(1, 5);
			map.put("sid", sid);
			map.put("balance", charge*SysConstant.RATE);
			map.put("expDate", expDate);
			map.put("eventId", eventId);
			map.put("effDate", date);
			map.put("createDate", date);
			map.put("updateDate", date);
			map.put("status", SysConstant.SERVICE_BANLANCE_TYPE_1);
			map.put("meetId", meetId);
			map.put("couponNum", couponNum);
			eventBalanceService.add(map);
		}
		//主账号充值
		else{
			payMentOrderService.coupon(couponNum,charge,sid,meetId,SysConstant.CHARGETYPE_5);
		}
		couponMoney = charge+"";
		return "couponSuc";
	}
	
	private String random8(String paramValue){
		String couponNum = null ;
		couponNum = StrUtil.random8();
		couponNum = paramValue+couponNum;
		Map<String, Object> map = couponService.getCouponByNum(couponNum);
		if(map!=null){
			random8(paramValue);
		}
		return couponNum;
	}
	
	private boolean check(String ...strings ){
		boolean boo = false;
		for(String s : strings){
			boolean b = StrUtil.isEmpty(s);
			if(b){
				boo = b ;
				break;
			}
		}
		return boo;
	}

	public String getCouponMoney() {
		return couponMoney;
	}

	public void setCouponMoney(String couponMoney) {
		this.couponMoney = couponMoney;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getMeetId() {
		return meetId;
	}

	public void setMeetId(String meetId) {
		this.meetId = meetId;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}
	public TbFlypaasUser getUser() {
		return user;
	}
	public void setUser(TbFlypaasUser user) {
		this.user = user;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public List<Map<String, Object>> getDate() {
		return date;
	}
	public void setDate(List<Map<String, Object>> date) {
		this.date = date;
	}
	public String getCouponNum() {
		return couponNum;
	}
	public void setCouponNum(String couponNum) {
		this.couponNum = couponNum;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public String getIsMeet() {
		return isMeet;
	}
	public List<Map<String, Object>> getParamList() {
		return paramList;
	}
	public void setParamList(List<Map<String, Object>> paramList) {
		this.paramList = paramList;
	}
	public void setIsMeet(String isMeet) {
		this.isMeet = isMeet;
	}
	
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	
	
}
