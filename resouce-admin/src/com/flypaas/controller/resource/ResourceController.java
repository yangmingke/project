package com.flypaas.controller.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.flypaas.constant.FlypaasConstant;
import com.flypaas.constant.RouterConstant;
import com.flypaas.model.TbRsAccountInfo;
import com.flypaas.model.TbRsCity;
import com.flypaas.model.TbRsIsp;
import com.flypaas.model.TbRsRTPP;
import com.flypaas.model.TbRsUserMsg;
import com.flypaas.model.vo.RouterInfo;
import com.flypaas.model.vo.Rtpps;
import com.flypaas.rest.HttpRequest;
import com.flypaas.service.NoticeService;
import com.flypaas.service.account.AccountService;
import com.flypaas.service.admin.UserInfoService;
import com.flypaas.service.city.CityService;
import com.flypaas.service.impl.RedisService;
import com.flypaas.service.resource.ResourceService;
import com.flypaas.util.PageContainer;
import com.flypaas.util.StrUtil;
import com.google.gson.Gson;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年2月6日 下午12:29:10
* 类说明
*/
@Controller
@RequestMapping("/resourceController")
public class ResourceController {
	public static final Logger logger = Logger.getLogger(ResourceController.class);
	public static final Gson gson = new Gson();
	
	@Autowired
	private ResourceService resourceServiceImpl;
	@Autowired
	private CityService cityServiceImpl;
	@Autowired
	private AccountService accountServiceImpl;
	@Autowired
	private NoticeService noticeServiceImpl;
	@Autowired
	private UserInfoService userServiceImpl;
	
	/**
	 * 根据条件分页查询所有的资源节点  资源列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryResourceFenYe")
	public ModelAndView queryResourceFenYe(HttpServletRequest request,ModelMap model){
		logger.info("根据条件分页查询所有的资源节点----------------------->>");
		String netSid = request.getParameter("netSid");	//资源方netSid
		String status = request.getParameter("status"); //节点状态
		String keyword = request.getParameter("keyword"); //关键字
		String currentPage = request.getParameter("page");//页码
		String pageRowCount = request.getParameter("pageRowCount");//一页显示最大数据条数
		
		int page = currentPage == null ? 1 : Integer.valueOf(currentPage);
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("netSid", StrUtil.isEmpty(netSid) ? null : netSid);
		para.put("status", StrUtil.isEmpty(status) ? null : status);
		para.put("keyword", StrUtil.isEmpty(keyword) ? null : keyword);
		para.put("pageRowCount", StrUtil.isEmpty(pageRowCount) ? null : Integer.valueOf(pageRowCount));
		
		//根据条件操作数据库查询节点
		PageContainer pageContainer = new PageContainer();
		pageContainer = resourceServiceImpl.queryResourceFenYe(para, page);
		model.put("page", pageContainer);
		return new ModelAndView("jsp/resource/resource-showList",model);
	}
	
	
	/**
	 * 按照rtpp_sid删除资源节点
	 * @param id
	 * @return
	 */
	@RequestMapping("/delResource")
	@ResponseBody
	public int delResource(@RequestParam("rtppSid") Integer id){
		logger.info("按照rtpp_sid删除资源节点------------------------>>");
		final TbRsRTPP tbRsRTPP = resourceServiceImpl.selectByPrimaryKey(id);
		int temp = resourceServiceImpl.deleteByPrimaryKey(id);
		final Thread t=new Thread(new Runnable(){
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public void run(){
				try{
					RouterInfo ri = new RouterInfo();
					//添加成功后  通知router组件
					Rtpps rtpps = new Rtpps();
					List list =new ArrayList();
					list.add(tbRsRTPP.getIp());
					rtpps.setAction(ri.getDel());
					rtpps.setRtpps(list);
					rtpps.setArea(StringUtils.isEmpty(tbRsRTPP.getRouteArea()) ? RouterConstant.ROUTE_DEFAULT_KEY_CN : tbRsRTPP.getRouteArea());
					HttpRequest req = new HttpRequest();
					HttpResponse response;
					response = req.post("application/json",ri.getUrl(),rtpps);
					//获取响应实体信息
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						//String result = EntityUtils.toString(entity, "UTF-8");
						// 确保HTTP响应内容全部被读出或者内容流被关闭
						EntityUtils.consume(entity);
						logger.debug("删除"+tbRsRTPP.getIp()+"资源成功,通知router组件成功");
					}
				}catch (Exception e) {
					logger.debug("删除"+tbRsRTPP.getIp()+"资源成功,通知router组件失败");
					e.printStackTrace();
				}
			}
		});
		if(temp > 0){
			t.start();
			return 1;
		}else{
			return 0;
		}
	}
	
	/**
	 * 按照rtpp_sid查询资源节点
	 * @param id
	 * @param tbRsRTPP
	 * @param model
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryResourceById")
	public ModelAndView queryResourceById(@RequestParam("rtppId") Integer id,
			TbRsRTPP tbRsRTPP,ModelMap model,Map map){
		logger.info("按照rtpp_sid查询资源节点------------------------>>");
		map = resourceServiceImpl.queryResourceById(id);
		//查询所有国家
		List<TbRsCity> countryList = new ArrayList<TbRsCity>();
		countryList = cityServiceImpl.queryAllCountry();
		
		  
		//查询该节点所在国家下的所有省份
		logger.info(map.get("countryid"));
		List<TbRsCity> provinceList = new ArrayList<TbRsCity>();
		Integer countryid  = (Integer) map.get("countryid");
		provinceList = cityServiceImpl.queryProvinceByCId(countryid);
		if(provinceList.size()==1 && provinceList.get(0).getProvinceid()==0){
			provinceList.get(0).setProvince("暂无数据");
		}
		
		//查询该节点所在省份下的所有市区
		List<TbRsCity> cityList = new ArrayList<TbRsCity>();
		Integer provinceid  = (Integer) map.get("provinceid");
		cityList = cityServiceImpl.queryCityByPId(provinceid);
		if(cityList.size()==1 && cityList.get(0).getCityid()==0){
			cityList.get(0).setCity("暂无数据");
		}
		
		//查询所有的运营商
		List<TbRsIsp> ispList = new ArrayList<TbRsIsp>();
		ispList = cityServiceImpl.queryAllIsp();
		
		model.put("tbRsRTPP",map);
		model.put("countryList", countryList);
		model.put("provinceList", provinceList);
		model.put("cityList", cityList);
		model.put("ispList", ispList);
		return new ModelAndView("jsp/resource/resource-editResource",model);
	}
	

	/**
	 * 按照rtpp_sid查询资源节点 Ajax请求
	 * @param id
	 * @param tbRsRTPP
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryResourceByIdAjax")
	@ResponseBody
	public Object queryResourceByIdAjax(@RequestParam("rtppId") Integer id,
			TbRsRTPP tbRsRTPP,ModelMap model,Map<String,String> map){
		logger.info("按照rtpp_sid查询资源节点------------------------>>");
		map = resourceServiceImpl.queryResourceById(id);
		model.put("tbRsRTPP",map);
		return JSON.toJSON(model);
	}
	
	@RequestMapping("/batchDelResource")
	@ResponseBody
	public int batchDelResource(@RequestParam("checkedRTPPIP") final String checkedRTPPIP){
		logger.info("批量删除资源节点----------------------------->>");
		int temp = resourceServiceImpl.batchDelResource(checkedRTPPIP);
		final Thread t=new Thread(new Runnable(){
			public void run(){
				try{
					RouterInfo ri = new RouterInfo();
					//添加成功后  通知router组件
					Rtpps rtpps = new Rtpps();
					String[]  arry = checkedRTPPIP.split(",");
					List<String> list = Arrays.asList(arry);
					rtpps.setAction(ri.getDel());
					rtpps.setRtpps(list);
					HttpRequest req = new HttpRequest();
					HttpResponse response;
					response = req.post("application/json",ri.getUrl(),rtpps);
					//获取响应实体信息
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						//String result = EntityUtils.toString(entity, "UTF-8");
						// 确保HTTP响应内容全部被读出或者内容流被关闭
						EntityUtils.consume(entity);
						logger.debug("批量删除"+checkedRTPPIP+"资源成功,通知router组件成功");
					}
				}catch (Exception e) {
					logger.debug("批量删除"+checkedRTPPIP+"资源成功,通知router组件失败");
					e.printStackTrace();
				}
			}
		});
		if(temp > 0){
			t.start();
			return 1;
		}else{
			return 0;
		}
	}
	
	/**
	 * 修改资源节点信息,配置并审核
	 * @param request
	 * @param tbRsRTPP
	 * @return
	 */
	@RequestMapping("/editResource")
	@ResponseBody
	public int editResource(HttpServletRequest request,final TbRsRTPP tbRsRTPP,final String originalRouteArea, String originalIp){
		logger.info("修改资源节点信息Controller.............................>>");
		List<TbRsRTPP>  tbRsRTPPList = resourceServiceImpl.checkRTPP(tbRsRTPP);
		if(tbRsRTPPList != null && tbRsRTPPList.size() > 0){
			if(tbRsRTPPList.size() >= 2){
				logger.info("已存在该节点IP-------");
				return 2;
			}else{
				TbRsRTPP temp = tbRsRTPPList.get(0);
				if(!temp.getRtppSid().equals(tbRsRTPP.getRtppSid())){
					logger.info("该域已存在该节点-------");
					return 2;
				}
			}
		}
		if(null == tbRsRTPP.getToIp() || "".equals(tbRsRTPP.getToIp())){
			tbRsRTPP.setIsToLine(FlypaasConstant.NOLINEIP);
			tbRsRTPP.setToIp(null);
		}else{
			tbRsRTPP.setIsToLine(FlypaasConstant.ISLINEIP);
		}
		if(FlypaasConstant.RTPP_STATUS_AUDITING.equals(tbRsRTPP.getStatus())){
			tbRsRTPP.setAuditDate(null);
			tbRsRTPP.setUpdateDate(new Date());
		}else{
			tbRsRTPP.setAuditDate(new Date());
			tbRsRTPP.setUpdateDate(new Date());
		}
		if(FlypaasConstant.RTPP_STATUS_STOP.equals(tbRsRTPP.getStatus())){//确保ip节点所有域内无数据流动
			Set<String> keys = RedisService.getInstance().getKeys("RTPP_INFO_*" + tbRsRTPP.getIp());
			for(String key : keys){
				Map<String,String> map=RedisService.getInstance().hgetall(key);
				if(map.get("concurrency") != null && !"0".equals(map.get("concurrency"))){
					return 3;
				}
			}
		}
		/*if(!oldRouteArea.equals(tbRsRTPP.getRouteArea())){//当改变域时，需要等待并发量为0的时候将注释打开
			Map<String,String> map=RedisService.getInstance().hgetall("RTPP_INFO_" + oldRouteArea +"_"+ tbRsRTPP.getIp() );
			if(!"0".equals(map.get("concurrency"))){
				return 3;
			}
		}*/
		tbRsRTPP.setAgentPrice(tbRsRTPP.getPrice() + RouterConstant.DEFAULT_DIFFERENTIAL);
		int temp = resourceServiceImpl.updateByPrimaryKeySelective(tbRsRTPP);
		Thread t=new Thread(new Runnable(){
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public void run(){
				try{
					RouterInfo ri = new RouterInfo();
					//添加成功后  通知router组件
					Rtpps rtpps = new Rtpps();
					List list =new ArrayList();
					list.add(tbRsRTPP.getIp());
					rtpps.setArea(StringUtils.isEmpty(tbRsRTPP.getRouteArea()) ? RouterConstant.ROUTE_DEFAULT_KEY_CN : tbRsRTPP.getRouteArea());
					rtpps.setOld_area(originalRouteArea);
					rtpps.setAction(ri.getUpd());
					rtpps.setRtpps(list);
					HttpRequest req = new HttpRequest();
					HttpResponse response;
					response = req.post("application/json",ri.getUrl(),rtpps);
					//获取响应实体信息
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						//String result = EntityUtils.toString(entity, "UTF-8");
						// 确保HTTP响应内容全部被读出或者内容流被关闭
						EntityUtils.consume(entity);
						logger.debug("修改"+tbRsRTPP.getIp()+"资源成功,通知router组件成功");
					}
				}catch (Exception e) {
					logger.debug("修改"+tbRsRTPP.getIp()+"资源成功,通知router组件失败");
					e.printStackTrace();
				}
			}
		});
		/*List<TbRsRTPP> RTPPList = resourceServiceImpl.getRTPPListByIp(originalIp);//保存其他域节点
		for(TbRsRTPP rtpp : RTPPList){
			tbRsRTPP.setNetSid(rtpp.getNetSid());
			tbRsRTPP.setRtppSid(rtpp.getRtppSid());
			tbRsRTPP.setRouteArea(rtpp.getRouteArea());
			tbRsRTPP.setName(rtpp.getName());
			tbRsRTPP.setPrice(rtpp.getPrice());
			tbRsRTPP.setStatus(rtpp.getStatus());
			tbRsRTPP.setAgentPrice(rtpp.getAgentPrice());
			resourceServiceImpl.updateByPrimaryKeySelective(tbRsRTPP);
			Thread thread=new Thread(new Runnable(){
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public void run(){
					try{
						RouterInfo ri = new RouterInfo();
						//添加成功后  通知router组件
						Rtpps rtpps = new Rtpps();
						List list =new ArrayList();
						list.add(tbRsRTPP.getIp());
						rtpps.setArea(tbRsRTPP.getRouteArea());
						rtpps.setOld_area(tbRsRTPP.getRouteArea());
						rtpps.setAction(ri.getUpd());
						rtpps.setRtpps(list);
						HttpRequest req = new HttpRequest();
						HttpResponse response;
						response = req.post("application/json",ri.getUrl(),rtpps);
						//获取响应实体信息
						HttpEntity entity = response.getEntity();
						if (entity != null) {
							//String result = EntityUtils.toString(entity, "UTF-8");
							// 确保HTTP响应内容全部被读出或者内容流被关闭
							EntityUtils.consume(entity);
							logger.debug("修改"+tbRsRTPP.getIp()+"资源成功,通知router组件成功");
						}
					}catch (Exception e) {
						logger.debug("修改"+tbRsRTPP.getIp()+"资源成功,通知router组件失败");
						e.printStackTrace();
					}
				}
			});
			thread.start();
		}*/
		if(temp > 0){
			t.start();
			TbRsUserMsg message = new TbRsUserMsg();
			message.setCreateDate(new Date());
			Map<String,String> map = userServiceImpl.queryManagerSidBynetSid(tbRsRTPP.getNetSid());
			message.setMsgTitle(FlypaasConstant.MSG_TITLE);
			message.setHasread(FlypaasConstant.MSG_HASREAG);
			String  msgDesc = null;
			switch(tbRsRTPP.getStatus()){
				case "0": 
					msgDesc = tbRsRTPP.getIp()+"节点被修改,节点变成初始状态"; 
					break;
				case "1": 
					msgDesc = tbRsRTPP.getIp()+"节点被修改,需要重新进行审核"; 
					break;
				case "2": 
					msgDesc = tbRsRTPP.getIp()+"节点被审核,审核未通过！"; 
					break;
				case "3": 
					msgDesc = tbRsRTPP.getIp()+"节点被审核,该节点审核通过";
					break;
				case "4": 
					msgDesc = tbRsRTPP.getIp()+"节点被修改,该节点被暂停使用"; 
					break;
				case "5": 
					msgDesc = tbRsRTPP.getIp()+"节点被修改,该节点进入离线状态"; 
					break;
				case "6": 
					msgDesc = tbRsRTPP.getIp()+"节点被修改,该节点进入在线状态"; 
					break;
			}
			message.setMsgDesc(msgDesc);
			message.setMsgType(FlypaasConstant.MSG_Type);
			message.setSid(map.get("sid"));
			noticeServiceImpl.createNotice(message);
			return 1;
		}else{
			return 0;
		}
	}
	
	/**
	 * 修改资源的分时间段定价
	 * @param request
	 * @param tbRsRTPP
	 * @return
	 */
	@RequestMapping("/editResourceBlcokPrice")
	@ResponseBody
	public int editResourceBlcokPrice(HttpServletRequest request,final TbRsRTPP tbRsRTPP){
		logger.info("此该资源的分时间段定价------------------------------------>>");
		int index = Integer.valueOf(request.getParameter("index"));
		int Str = index-1;
		if(Str == 0){
			tbRsRTPP.setBlockPrice(null);
		}else{
			StringBuffer sb = new StringBuffer("[");
			for(int i = 1; i<=Str; i++){
				String price = request.getParameter("price"+i);
				String startTime = request.getParameter("startTime"+i);
				String endTime= request.getParameter("endTime"+i);
				if(i==Str){
					sb.append("{\"starttime\":\""+startTime+"\",\"endtime\":\""+endTime+"\",\"price\":\""+price+"\"}");
				}else{
					sb.append("{\"starttime\":\""+startTime+"\",\"endtime\":\""+endTime+"\",\"price\":\""+price+"\"},");
				}
			}
			sb.append("]");
			tbRsRTPP.setBlockPrice(sb.toString());
		}
		int temp = resourceServiceImpl.updateBlockPriceById(tbRsRTPP);
		final Thread t=new Thread(new Runnable(){
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public void run(){
				try{
					RouterInfo ri = new RouterInfo();
					//添加成功后  通知router组件
					Rtpps rtpps = new Rtpps();
					List list =new ArrayList();
					list.add(tbRsRTPP.getIp());
					rtpps.setArea(StringUtils.isEmpty(tbRsRTPP.getRouteArea()) ? RouterConstant.ROUTE_DEFAULT_KEY_CN : tbRsRTPP.getRouteArea());
					rtpps.setAction(ri.getUpd());
					rtpps.setRtpps(list);
					HttpRequest req = new HttpRequest();
					HttpResponse response;
					response = req.post("application/json",ri.getUrl(),rtpps);
					//获取响应实体信息
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						//String result = EntityUtils.toString(entity, "UTF-8");
						// 确保HTTP响应内容全部被读出或者内容流被关闭
						EntityUtils.consume(entity);
						logger.debug("修改节点分段定价"+tbRsRTPP.getIp()+"资源成功,通知router组件成功");
					}
				}catch (Exception e) {
					logger.debug("修改节点分段定价"+tbRsRTPP.getIp()+"资源成功,通知router组件失败");
					e.printStackTrace();
				}
			}
		});
		if(temp > 0){
			t.start();
			logger.info("修改成功-------");
			return 1;
		}else{
			logger.info("修改失败-------");
			return 0;
		}
	}
	
	@RequestMapping("/queryResourceSideById")
	public ModelAndView queryResourceSideById(HttpServletRequest request,TbRsAccountInfo tbUser,ModelMap model){
		logger.info("根据netSid查询资源方信息和系统数据库信息--------------------------->>");
		String netSid = request.getParameter("netSid");
		tbUser = accountServiceImpl.queryAccountById(netSid);
		//查询所有国家
		List<TbRsCity> countryList = new ArrayList<TbRsCity>();
		countryList = cityServiceImpl.queryAllCountry();
				
				  
		//查询该节点所在国家下的所有省份
		List<TbRsCity> provinceList = new ArrayList<TbRsCity>();
		Integer countryid  = FlypaasConstant.DEFAULT_COUNTRY;
		provinceList = cityServiceImpl.queryProvinceByCId(countryid);
		if(provinceList.size()==1 && provinceList.get(0).getProvinceid()==0){
			provinceList.get(0).setProvince("暂无数据");
		}
				
		//查询该节点所在省份下的所有市区
		List<TbRsCity> cityList = new ArrayList<TbRsCity>();
		Integer provinceid  = FlypaasConstant.DEFAULT_PROVINCE;
		cityList = cityServiceImpl.queryCityByPId(provinceid);
		if(cityList.size()==1 && cityList.get(0).getCityid()==0){
			cityList.get(0).setCity("暂无数据");
		}
				
		//查询所有的运营商
		List<TbRsIsp> ispList = new ArrayList<TbRsIsp>();
		ispList = cityServiceImpl.queryAllIsp();
		
		model.put("countryList", countryList);
		model.put("provinceList", provinceList);
		model.put("cityList", cityList);
		model.put("ispList", ispList);
		model.put("resourceSide", tbUser);
		
		return new ModelAndView("jsp/resource/resource-addResource",model);	
	}
	
	@RequestMapping("/addResourcePage")
	public ModelAndView addResourcePage(HttpServletRequest request,TbRsAccountInfo tbUser,ModelMap model){
		List<TbRsAccountInfo> rsNameList = accountServiceImpl.queryAllAccountName();
		//查询所有国家
		List<TbRsCity> countryList = new ArrayList<TbRsCity>();
		countryList = cityServiceImpl.queryAllCountry();
				
		//查询所有的运营商
		List<TbRsIsp> ispList = new ArrayList<TbRsIsp>();
		ispList = cityServiceImpl.queryAllIsp();
		
		model.put("countryList", countryList);
		model.put("ispList", ispList);
		model.put("resourceSide", tbUser);
		model.put("rsNameList", rsNameList);
		
		return new ModelAndView("jsp/resource/resource-addResource",model);	
	}
	
	/**
	 * 添加资源节点
	 * @param request
	 * @param tbRsRTPP
	 * @return
	 */
	@RequestMapping("/addResource")
	@ResponseBody
	public int addResource(HttpServletRequest request,final TbRsRTPP tbRsRTPP){
		logger.info("根据netSid添加资源节点 --------------------------------------->>");
		List<TbRsRTPP>  tbRsRTPPList = resourceServiceImpl.checkRTPP(tbRsRTPP);
		if(tbRsRTPPList != null && !tbRsRTPPList.isEmpty()){
//			logger.info("该域已存在该节点-------");
			logger.info("已存在该节点IP-------");
			return 2;
		}
		//添加节点的默认值
		tbRsRTPP.setCreateDate(new Date());
		tbRsRTPP.setAgentPrice(tbRsRTPP.getPrice() + RouterConstant.DEFAULT_DIFFERENTIAL);
		tbRsRTPP.setRtppSid(StrUtil.randomId());
		tbRsRTPP.setStatus(FlypaasConstant.RTPP_STATUS_AUDITING);
		if(StrUtil.isEmpty(tbRsRTPP.getToIp())){
			tbRsRTPP.setIsToLine(FlypaasConstant.NOLINEIP);
		}else{
			tbRsRTPP.setIsToLine(FlypaasConstant.ISLINEIP);
		}
		int index = Integer.valueOf(request.getParameter("index"));
		int Str = index-1;
		if(Str == 0){
			tbRsRTPP.setBlockPrice(null);
		}else{
			StringBuffer sb = new StringBuffer("[");
			for(int i = 1; i<=Str; i++){
				String price = request.getParameter("price"+i);
				String startTime = request.getParameter("startTime"+i);
				String endTime= request.getParameter("endTime"+i);
				if(i==Str){
					sb.append("{\"starttime\":\""+startTime+"\",\"endtime\":\""+endTime+"\",\"price\":\""+price+"\"}");
				}else{
					sb.append("{\"starttime\":\""+startTime+"\",\"endtime\":\""+endTime+"\",\"price\":\""+price+"\"},");
				}
			}
			sb.append("]");
			tbRsRTPP.setBlockPrice(sb.toString());
		}
		int temp = resourceServiceImpl.insertSelective(tbRsRTPP);
		final Thread t=new Thread(new Runnable(){
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public void run(){
				try{
					RouterInfo ri = new RouterInfo();
					//添加成功后  通知router组件
					Rtpps rtpps = new Rtpps();
					List list =new ArrayList();
					list.add(tbRsRTPP.getIp());
					rtpps.setArea(StringUtils.isEmpty(tbRsRTPP.getRouteArea()) ? RouterConstant.ROUTE_DEFAULT_KEY_CN : tbRsRTPP.getRouteArea());
					rtpps.setAction(ri.getAdd());
					rtpps.setRtpps(list);
					HttpRequest req = new HttpRequest();
					HttpResponse response;
					response = req.post("application/json",ri.getUrl(),rtpps);
					//获取响应实体信息
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						//String result = EntityUtils.toString(entity, "UTF-8");
						// 确保HTTP响应内容全部被读出或者内容流被关闭
						EntityUtils.consume(entity);
						logger.debug("添加"+tbRsRTPP.getIp()+"资源成功,通知router组件成功");
					}
				}catch (Exception e) {
					logger.debug("添加"+tbRsRTPP.getIp()+"资源成功,通知router组件失败");
					e.printStackTrace();
				}
			}
		});
		if(temp > 0){
			t.start();
			logger.info("添加成功-------");
			return 1;
		}else{
			logger.info("添加失败-------");
			return 0;
		}
	}
	
	
	/**
	 * 检测此ip是否存在
	 * @param request
	 * @param tbRs
	 * @return
	 */
	@RequestMapping("/checkIp")
	@ResponseBody
	public String checkIp(HttpServletRequest request,TbRsRTPP tbRs){
		logger.info("检测此Ip是否存在--------------->>");
		String ip = request.getParameter("ip");
		tbRs = resourceServiceImpl.checkIp(ip); 
		if(null == tbRs || tbRs.equals("")){
			return gson.toJson(1);
		}else{
			return gson.toJson(0);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/showNeighbor")
	public ModelAndView showNeighbor(HttpServletRequest request,String ip,String routeDomain){
		Map<String, Object> model = resourceServiceImpl.showNeighbor(ip,routeDomain);
		return new ModelAndView("/jsp/resource/editNeighbor",model);
	}
	
	@RequestMapping("/editNeighbor")
	@ResponseBody
	public int editNeighbor(HttpServletRequest request,String neighbor,String ip, String routeDomain,String notNeighbor){
		if(StrUtil.isEmpty(ip) || StrUtil.isEmpty(routeDomain)){
			return 0;
		}
		String[] neighborArray = null;
		if(StrUtil.isNotEmpty(neighbor)){
			neighborArray = neighbor.split(",");
		}
		String[] notNeighborArray = null;
		if(StrUtil.isNotEmpty(notNeighbor)){
			notNeighborArray = notNeighbor.split(",");
		}
		int result = resourceServiceImpl.updateNeighbor(neighborArray, notNeighborArray, ip, routeDomain);
		
		return result;
		
	}

	
}
