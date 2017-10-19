package com.flypaas.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.constant.SysConstant;
import com.flypaas.constant.UserConstant;
import com.flypaas.entity.AcctBalance;
import com.flypaas.entity.AcctPackageRel;
import com.flypaas.entity.FeeItem;
import com.flypaas.entity.FeeItemRel;
import com.flypaas.entity.Package;
import com.flypaas.entity.TbFlypaasUser;
import com.flypaas.entity.vo.PageContainer;
import com.flypaas.utils.DateUtil;
import com.flypaas.utils.StrUtil;
import com.flypaas.utils.SysConfig;

@Controller
@Scope(value="prototype")
@Results({
	@Result(name="packageListSuc",location="/page/finance/fee_config.jsp"),
	@Result(name="priceView",location="/front/price_new.jsp"),
	@Result(name="feeRateSuc",location="/page/user/fee_rate.jsp"),
	@Result(name="modifyPckSuc",type="redirectAction",location="feeConfig"),
	@Result(name="serviceChargeSuc",location="/page/user/service_charge.jsp")
})
public class FeeConfigAction extends BaseAction {
	private Package pck;
	private List<Package> pckList;
	private List<Package> BasePckList;
	private List<Map<String,Object>> feeItemRelList;
	private List<FeeItemRel> currentItemList;
	private Map<String,String> acctBalanceMap;
	private Map<String, Object> feeConfigMap;
	private AcctBalance acctBalance;
	private TbFlypaasUser user;
	private String pckId;
	private String cpHotNum;
	private String phone;
	private List<Map<String, Object>> rateList ;
	private String countryNum;
	private String prefix;
	private PageContainer page;
	private String namePrefix;
	private List<Map<String, Object>> list;
	private String ch_name;
	
	/*---------------------------------------------跳转到查询价格页面--------------------------------*/
	@Action("/price")
	public String price(){
		return "priceView";
	}
	
	/*---------------------------------------------根据国家编码或英文字母查找国家--------------------------------*/
	@Action("/ctList")
	public void ctList(){
		Map<String, String> param = new HashMap<String, String>();
		if(StringUtils.isNotEmpty(prefix) && !prefix.equals("null")){
			param.put("prefix", prefix);
		}
		if(StringUtils.isNotEmpty(namePrefix) && !namePrefix.equals("null")){
			param.put("namePrefix", namePrefix);
		}
		list = packageService.getChEnListbyParam(param);
		final String aa = "<li onClick=\"findPrice('%s','%s','%s','%s')\"><span class=\"code\">%s</span><span class=\"country\">%s<img src=\"%s\"></span></li>";
		String ss = null;
		StringBuffer bf = new StringBuffer();
		for(Map<String, Object> en : list){
			ss = aa;
			ss = String.format(ss, en.get("prefix").toString().trim(),en.get("en_name").toString().trim(),en.get("ch_name").toString().trim(),en.get("picName").toString().trim(),en.get("prefix").toString().trim(),en.get("ch_name").toString().trim(),"/front/images1/flag/"+en.get("picName").toString().trim()+".png");
			bf.append(ss);
		}
		printMsg(bf.toString());
	}
	
	/*---------------------------------------------查找通话价格--------------------------------*/
	@Action("/findPrice")
	public void findPrice(){
		int flag = 0;
		Map<String, String> param = new HashMap<String, String>();
		namePrefix = namePrefix.trim();
		prefix = prefix.trim();
		param.put("area_name", namePrefix);
		param.put("prefix", prefix);
		list = packageService.getPriceByPrefix(param);
		//有国家名称的循环，语音通话
		final String aa = "<tr><td>%s</td><td class='td2'><span><em>%s</em>元 / 分钟</span></td><td>%s</td><tr>";
		//不显示国家名称的循环
		final String aa2 = "<tr><td></td><td class='td2'><span><em>%s</em>元 / 分钟</span></td><td>%s</td><tr>";
		final String head = "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" class=\"table1\"><tbody><tr id=\"ct_content\"><th width=\"235\">国家</th><th width=\"301\">语音通话资费</th><th width=\"644\">地区码</th></tr>";
		final String head1 = "</tbody></table>";
		String ss = null;
		StringBuffer bf = new StringBuffer();
		bf.append(head);
		for(Map<String, Object> en : list){
			String str=en.get("prefixs").toString();
			if(flag==0){
				str = StrUtil.getPoint4(str);
				ss = aa;
				ss = String.format(ss,ch_name,en.get("unit_fee").toString(),str);
				flag = 1;
			}else {
				str = StrUtil.getPoint4(str);
				ss = aa2;
				ss = String.format(ss,en.get("unit_fee").toString(),str);
			}
			bf.append(ss);
		}
		bf.append(head1);
		Map<String, String> param1 = new HashMap<String, String>();
        Map<String, String> param2 = new HashMap<String, String>();
        param1.put("prefix", prefix);
        param2 = packageService.getMesPriceByPrefix(param1);
        //短信资费
        final String foot =  "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" class=\"table2\"><tbody><tr><th width=\"235\" class=\"th1\"></th><th width=\"301\">短信(SMS)</th><th width=\"644\"></th></tr><tr><td></td>"
                            +"<td class='td2'><span><em>%s</em>元 / 条</span></td><td class='td3'><span>%s</span></td></tr></tbody></table>";
		String ff = foot;
		if(null==param2||param2.size()==0){
			
		}else {
			ff = String.format(ff, param2.get("fee"),param2.get("prefix"));
			bf.append(ff);
		}
		printMsg(bf.toString());
        
	}
	
	
	/*---------------------------------------------获取当前套餐，资费项--------------------------------*/
	@Action("/user/find")
	public String packageList(){
		//开发者基础信息
		user = getSessionUser();
		//开发者当前套餐信息
		AcctPackageRel rel = getPackageRel(user.getSid());
		if(rel!=null){
			pck = getPackage(rel.getPackageId());
		}
		int isPersonalPck = pck.getPackageType();

		//资费信息
		feeItemRelList= getFeeItemAndRel();
		
		//获取基础套餐
		BasePckList = getBasePackageList();
		
		//组装套餐
		if(isPersonalPck==SysConstant.IS_PERSONALPCK){
			//获取当前开发者自定义套餐+基础套餐
			pckList = getPackageList(pck.getPackageId());
			pck = pack(feeItemRelList,pck);
		}
		
		feeConfigMap = otherService.getFeeConfig();
		return "packageListSuc";
	}
	
	/*---------------------------------------------更新套餐信息--------------------------------*/
	@Action("/user/modifyPck")
	public void modifyPck(){
		String sid = getSessionUser().getSid();
		acctBalance = acctBalanceService.getAcctBalanceBySid(sid);
		double b = acctBalance.getBalance();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pckId", Long.parseLong(pckId));
		map.put("lowerCsm", UserConstant.lowestCsm[0]);
		double lowerCsm = itemService.getLowerCsm(map);
		if(lowerCsm>b){
			StrUtil.writeMsg(response, "余额不足,修改套餐不成功。<br/>无法满足(<a href='http://"+SysConfig.getInstance().getProperty("host")+"/price'>套餐|"+lowerCsm+"/月最低</a>),请保证账户有足够余额",null);
			return;
		}
		AcctPackageRel pckRel = pckRelService.getAcctPackageRel(sid);
		long oldPckId = pckRel.getPackageId();
		map = new HashMap<String, Object>();
		map.put("sid", sid);
		map.put("packageId", Long.parseLong(pckId));
		map.put("createDate", DateUtil.getCurrentDate());
		map.put("updateDate", DateUtil.getCurrentDate());
		map.put("status",SysConstant.TEMP_PCK_STATUS_1 );
		map.put("type",SysConstant.TEMP_PCK_TYPE_1 );
		map.put("oldPckId", oldPckId);
		Map<String, Object> m = pckRelService.getTempPck(map);
		if(m!=null && m.size()>0){
			String id = m.get("id").toString();
			map.put("id", id);
			map.put("sid",sid);
			pckRelService.updateTempPck(map);
		}else{
			pckRelService.insertTempPck(map);
		}
		StrUtil.writeMsg(response, "您的套餐变更申请已接收，变更将在下月生效",null);
		return;
	}
	/*---------------------------------------------服务资费--------------------------------*/
	@Action("/user/serviceCharge")
	public String serviceCharge(){
		return "serviceChargeSuc";
	}
	/*--------------------------------------------查看费率--------------------------------*/
	@Action("/user/feeRate")
	public String feeRate(){
		if (null == page) {
			page = new PageContainer();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if(!StrUtil.isEmpty(countryNum)){
			map.put("namePrefix", countryNum);
		}
		if(!StrUtil.isEmpty(prefix)){
			map.put("prefix", prefix);
		}
		page.setParams(map);
		page = packageService.rateList(page);
		return "feeRateSuc";
	}
	//获取当前用户套餐
	private Package getPackage(long id){
		return packageService.getPackage(id);
	}
	//获取主账户套餐关系
	private AcctPackageRel getPackageRel(String sid){
		return packageRelService.getAcctPackageRel(sid);
	}
	//获取套餐列表
	private List<Package> getPackageList(long pckId){
		return packageService.getPackageList(pckId);
	}
	//获取套餐列表
	private List<Package> getBasePackageList(){
		return packageService.getBasePackageList();
	}
	//获取所有的rel和item实体
	private List<Map<String,Object>> getFeeItemAndRel(){
		return itemRelService.getFeeItemRelList();
	}
	//组装套餐
	private Package pack(List<Map<String,Object>> RelList,Package pck){
		
		String []interPrefix = UserConstant.interPrefix;
		String []lowestCharge = UserConstant.lowestCsm;
		String []dispNbr = UserConstant.dispNbr;
		long pid = pck.getPackageId();
			List<FeeItem> itemList = new ArrayList<FeeItem>();
			for(Map<String,Object> map:RelList){
				long packageId = Long.parseLong(map.get("packageId").toString());
				FeeItem item = new FeeItem();
				if(packageId==pid){
					String feeName = map.get("feeName").toString();
					String feeType= map.get("feeType").toString();
					int isShowNb= Integer.parseInt(map.get("isShowNbr").toString());
					int feeId= (Integer) map.get("feeId");
					double fee = Double.parseDouble(map.get("fee").toString());
					item.setFee(fee);
					item.setFeeId(feeId);
					item.setFeeName(feeName);
					item.setFeeType(feeType);
					for (String fix : dispNbr) {
						if((""+feeId).startsWith(fix)){
							if(isShowNb>0){
								item.setIsShowNbr(isShowNb);
								break;
							}
						}
					}
					for(String pf : interPrefix){
						if((""+feeId).startsWith(pf)){
							item.setInterFee(2);
							break;
						}
					}
					itemList.add(item);
				}
				}
			pck.setFeeItemList(itemList);
			if(pid==pck.getPackageId()){
				pck.setFeeItemList(itemList);
				for(FeeItem f : itemList){
					if(pck.getLowestCharge()==null){
						String feeId = f.getFeeId()+"";
						for(String pf : lowestCharge){
							if((""+feeId).startsWith(pf)){
								pck.setLowestCharge(f.getFee()+"");
								break;
							}
						}
					}
				}
			}
		return pck ;
	}
	/*---------------------------------------------get set--------------------------------*/
	public Package getPck() {
		return pck;
	}

	public void setPck(Package pck) {
		this.pck = pck;
	}

	public TbFlypaasUser getUser() {
		return user;
	}

	public void setUser(TbFlypaasUser user) {
		this.user = user;
	}

	public List<Package> getPckList() {
		return pckList;
	}

	public void setPckList(List<Package> pckList) {
		this.pckList = pckList;
	}

	public List<FeeItemRel> getCurrentItemList() {
		return currentItemList;
	}

	public void setCurrentItemList(List<FeeItemRel> currentItemList) {
		this.currentItemList = currentItemList;
	}

	public String getPckId() {
		return pckId;
	}

	public void setPckId(String pckId) {
		this.pckId = pckId;
	}
	public String getCpHotNum() {
		return cpHotNum;
	}
	public void setCpHotNum(String cpHotNum) {
		this.cpHotNum = cpHotNum;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<Map<String, Object>> getFeeItemRelList() {
		return feeItemRelList;
	}
	public void setFeeItemRelList(List<Map<String, Object>> feeItemRelList) {
		this.feeItemRelList = feeItemRelList;
	}

	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}


	public List<Map<String, Object>> getRateList() {
		return rateList;
	}

	public void setRateList(List<Map<String, Object>> rateList) {
		this.rateList = rateList;
	}

	public PageContainer getPage() {
		return page;
	}

	public void setPage(PageContainer page) {
		this.page = page;
	}

	public String getCountryNum() {
		return countryNum;
	}

	public void setCountryNum(String countryNum) {
		this.countryNum = countryNum;
	}

	public Map<String, String> getAcctBalanceMap() {
		return acctBalanceMap;
	}

	public void setAcctBalanceMap(Map<String, String> acctBalanceMap) {
		this.acctBalanceMap = acctBalanceMap;
	}

	public List<Package> getBasePckList() {
		return BasePckList;
	}

	public void setBasePckList(List<Package> basePckList) {
		BasePckList = basePckList;
	}

	public Map<String, Object> getFeeConfigMap() {
		return feeConfigMap;
	}

	public void setFeeConfigMap(Map<String, Object> feeConfigMap) {
		this.feeConfigMap = feeConfigMap;
	}


	public String getNamePrefix() {
		return namePrefix;
	}


	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}
	public List<Map<String, Object>> getList() {
		return list;
	}
	public String getCh_name() {
		return ch_name;
	}

	public void setCh_name(String ch_name) {
		this.ch_name = ch_name;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

	

	
}
