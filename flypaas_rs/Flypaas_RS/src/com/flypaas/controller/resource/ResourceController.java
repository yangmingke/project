package com.flypaas.controller.resource;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.flypaas.annotation.Log;
import com.flypaas.constant.FlypaasConstant;
import com.flypaas.constant.RouterConstant;
import com.flypaas.model.TbRsAccountInfo;
import com.flypaas.model.TbRsRTPP;
import com.flypaas.model.vo.RouterInfo;
import com.flypaas.model.vo.Rtpps;
import com.flypaas.rest.HttpRequest;
import com.flypaas.service.RedisService;
import com.flypaas.service.resource.ResourceService;
import com.flypaas.util.DateUtil;
import com.flypaas.util.PageContainer;
import com.flypaas.util.RandomUtil;
import com.google.gson.Gson;

/**
 * 资源管理
 * 1.查看账号的所有资源
 * 2.按资源id查询某个资源节点的信息
 * 3.给该账号下添加节点
 * 4.删除某个资源节点 按节点ID
 * 5.批量删除资源节点
 * 6.修改资源节点信息
 * @author win10
 *
 */
@Controller
@RequestMapping("/resourceController")
public class ResourceController {
	public static Logger logger = Logger.getLogger(ResourceController.class);
	public static Gson gson = new Gson();
	
	@Autowired
	private ResourceService resourceServiceImpl;
	
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
	
	
	
	
	/**
	 * 查询该账号的所有资源
	 * 1.从session获取当前登录的netSid
	 * 2.获取该netSid所有的资源节点
	 * 3.获取该netSid滋源节点数量
	 * 4.将获取的信息保存在ModelMap中返回到界面
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/queryAllResourceFenYe")
	public ModelAndView queryAllResourceFenYe(HttpSession session,TbRsRTPP tbRsRTPP,ModelMap model,HttpServletRequest request){
		logger.info("查询该账号下所有资源节点--------->>");
		int page;
		String page01 =request.getParameter("page");
		String dateMin = request.getParameter("dateMin");
		String dateMax = request.getParameter("dateMax");
		String ipOrName = request.getParameter("ipOrName");
		model.put("dateMin", dateMin);
		model.put("dateMax", dateMax);
		model.put("ipOrName", ipOrName);
		if(null == page01){
			page = 1;
		}else{
			page = Integer.valueOf(page01);
		}
		if(null == dateMin || dateMin.equals("") ){
			dateMin = "1770-01-01";
		}
		if(null == dateMax || dateMax.equals("")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			dateMax = sdf.format(new Date());
		}
		
		TbRsAccountInfo tbRsAccountInfo = (TbRsAccountInfo) session.getAttribute("userSideSession");
		String netArea = StringUtils.isEmpty(tbRsAccountInfo.getNetArea()) ? RouterConstant.ROUTE_DEFAULT_KEY_CN : tbRsAccountInfo.getNetArea();
		PageContainer  pageContainer  = resourceServiceImpl.queryAllResourceFenYe(tbRsAccountInfo.getNetSid(),netArea,page,ipOrName,dateMin,dateMax);
//		int count = resourceServiceImpl.queryResourceCount(tbRsAccountInfo.getNetSid(),ipOrName,dateMin,dateMax);
		
		List<Map<String, String>> list =  pageContainer.getResultMap();
		model.put("resourceList",list);
		model.put("resourceCount", list.size());
		model.put("page", pageContainer);
		return new ModelAndView("jspPage/resource/resource-list",model);
	}
	
	/**
	 * 
	 * @param session
	 * @param tbRsRTPP
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryAllResourceToStatusPic")
	public ModelAndView queryAllResourceToStatusPic(HttpSession session,TbRsRTPP tbRsRTPP,ModelMap model,int page,HttpServletRequest request){
		logger.info("查询该账号下所有资源节点--------->>");
		TbRsAccountInfo tbRsAccountInfo = (TbRsAccountInfo) session.getAttribute("userSideSession");
		String dateMin = request.getParameter("dateMin");
		String dateMax = request.getParameter("dateMax");
		String ipOrName = request.getParameter("ipOrName");
		model.put("dateMin", dateMin);
		model.put("dateMax", dateMax);
		model.put("ipOrName", ipOrName);
		if(null == dateMin || dateMin == "" || dateMin.equals("") ){
			dateMin = "1770-01-01";
		}
		if(null == dateMax || dateMax == "" || dateMax.equals("") ){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");  
			dateMax = sdf.format(new Date());
		}
		String netArea = StringUtils.isEmpty(tbRsAccountInfo.getNetArea()) ? RouterConstant.ROUTE_DEFAULT_KEY_CN : tbRsAccountInfo.getNetArea();
		PageContainer  pageContainer  = resourceServiceImpl.queryAllResourceFenYe(tbRsAccountInfo.getNetSid(),netArea,page,ipOrName,dateMin,dateMax);
		int count = resourceServiceImpl.queryResourceCount(tbRsAccountInfo.getNetSid(),ipOrName,dateMin,dateMax);
		List<Map<String, String>> list =  pageContainer.getResultMap();
		model.put("resourceList",list);
		model.put("resourceCount", count);
		model.put("page", pageContainer);
		return new ModelAndView("jspPage/resource/resource-statusPic",model);
	}
	
	/*@RequestMapping("/queryAllResource1")
	public ModelAndView queryAllResource1(HttpSession session,TbRsRTPP tbRsRTPP,ModelMap model){
		logger.info("查询该账号下所有资源节点--------->>");
		TbRsAccountInfo tbRsAccountInfo = (TbRsAccountInfo) session.getAttribute("userSideSession");
		List<TbRsRTPP> list = resourceServiceImpl.queryAllResource(tbRsAccountInfo.getNetSid());
		int count = resourceServiceImpl.queryResourceCount(tbRsAccountInfo.getNetSid());
		model.put("resourceList",list);
		model.put("resourceCount", count);
		return new ModelAndView("jspPage/finance/resource-list_2",model);
	}*/
	
	/**
	 * 通过IP或者Name查询该账号下的所有节点  AJax请求
	 * @param request
	 * @param tbRsRTPP
	 * @param session
	 * @return
	 */
	@RequestMapping("/queryResourceByIpOrName")
	@ResponseBody
	public Object queryResourceByIpOrName(HttpServletRequest request,TbRsRTPP tbRsRTPP,HttpSession session){

		logger.info("按照资源IP或者Name查询节点--------------------->>");
		String IpOrName = request.getParameter("IpOrName");
		TbRsAccountInfo tbInfo = (TbRsAccountInfo) session.getAttribute("userSideSession");
		TbRsRTPP tbRsRTPP1 = new TbRsRTPP();
		tbRsRTPP1.setName(IpOrName);
		tbRsRTPP1.setNetSid(tbInfo.getNetSid());
		tbRsRTPP = resourceServiceImpl.queryResourceByIpOrName(tbRsRTPP1);
		return gson.toJson(tbRsRTPP);
	}
	
	
	
	/**
	 * 按rttpSid查询某个节点
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryResourceById")
	@ResponseBody
	public Object queryResourceById(HttpServletRequest request,TbRsRTPP tbRsRTPP){
		logger.info("按rtppSid查询资源节点--------->>");
		int rtppSid = Integer.valueOf(request.getParameter("rtppSid"));
		tbRsRTPP = resourceServiceImpl.selectByPrimaryKey(rtppSid);
//		List<TbRsCity>  cityList = cityServiceImpl.queryProvinceByCountryId(tbRsRTPP.getCountry());
//		List<TbRsCity> provinceList = cityServiceImpl.queryCityBypId(tbRsRTPP.getProvince());
//		Map model = new HashMap();
//		model.put("tbRsCity", tbRsRTPP);
//		model.put("cityList", cityList);
//		model.put("provinceList", provinceList);
		return gson.toJson(tbRsRTPP);
	}
	
	/**
	 * 给该账号下 添加资源节点
	 * 1.获取session中的netSid
	 * 2.随机生成rtppSid
	 * 3.获取页面资源信息
	 * 4.得到当前时间
	 * 5.执行添加方法
	 * @param session
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/addResource")
	@ResponseBody
	@Log(name = "添加资源节点", type = "3")
	public String  addResource(HttpSession session,final TbRsRTPP tbRsRTPP,HttpServletRequest request) throws Exception{
		logger.info("添加资源节点--------->>");
		TbRsAccountInfo tbRsAccountInfo = (TbRsAccountInfo) session.getAttribute("userSideSession");
		tbRsRTPP.setNetSid(tbRsAccountInfo.getNetSid());
		tbRsRTPP.setRtppSid(RandomUtil.randomToInt());
		tbRsRTPP.setCreateDate(DateUtil.getCurrentDate());
		tbRsRTPP.setRouteArea(RouterConstant.ROUTE_DEFAULT_KEY_CN);
		tbRsRTPP.setStatus(FlypaasConstant.status_0);
		int blockPrice = Integer.valueOf(request.getParameter("block_Price"));
		if(blockPrice == 1){
			int index = Integer.valueOf(request.getParameter("index"));
			StringBuffer sb = new StringBuffer("[");
			for(int i = 1; i<=index; i++){
				String price = request.getParameter("price"+i);
				String startTime = request.getParameter("startTime"+i);
				String endTime= request.getParameter("endTime"+i);
				if(i==index){
					sb.append("{\"starttime\":\""+startTime+"\",\"endtime\":\""+endTime+"\",\"price\":\""+price+"\"}");
				}else{
					sb.append("{\"starttime\":\""+startTime+"\",\"endtime\":\""+endTime+"\",\"price\":\""+price+"\"},");
				}
			}
			sb.append("]");
			tbRsRTPP.setBlockPrice(sb.toString());
		}else{
			tbRsRTPP.setBlockPrice(null);
		}
		int isIpLine = Integer.valueOf(request.getParameter("isToLine"));
		if(isIpLine == 0){
			tbRsRTPP.setToIp(null);
		}
		int temp = resourceServiceImpl.insertSelective(tbRsRTPP);
		final Thread t=new Thread(new Runnable(){
			public void run(){
				try{
					RouterInfo ri = new RouterInfo();
					//添加成功后  通知router组件
					Rtpps rtpps = new Rtpps();
					List list =new ArrayList();
					list.add(tbRsRTPP.getIp());
					rtpps.setAction(ri.getAdd());
					rtpps.setArea(tbRsRTPP.getRouteArea());
					rtpps.setRtpps(list);
					HttpRequest req = new HttpRequest();
					HttpResponse response;
					response = req.post("application/json",ri.getUrl(),rtpps);
					//获取响应实体信息
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						String result = EntityUtils.toString(entity, "UTF-8");
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
			return gson.toJson(1);
		}
		return gson.toJson(0);
	}
	

	/**
	 * 删除该账号下的某个资源节点（不建议使用）
	 * @param session
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/delResouce")
	@ResponseBody
	@Log(name = "删除资源节点", type = "5")
	public String delResource(HttpSession session,HttpServletRequest request) throws Exception{
		logger.info("删除资源节点--------->>");
		int rtppSid = Integer.valueOf(request.getParameter("rtppSid"));
		final String ip = request.getParameter("ip");
		final TbRsRTPP tbRsRTPP = resourceServiceImpl.selectByPrimaryKey(rtppSid);
		if(tbRsRTPP.getStatus().equals(FlypaasConstant.RTPP_STATUS_READY)){
			return gson.toJson(2);
		}
		int temp = resourceServiceImpl.deleteByPrimaryKey(rtppSid);
		Thread t=new Thread(new Runnable(){
			public void run(){
				RouterInfo ri = new RouterInfo();
				//删除成功后  通知router组件
				Rtpps rtpps = new Rtpps();
				List list =new ArrayList();
				list.add(ip);
				rtpps.setAction(ri.getDel());
				rtpps.setArea(StringUtils.isEmpty(tbRsRTPP.getRouteArea()) ? RouterConstant.ROUTE_DEFAULT_KEY_CN : tbRsRTPP.getRouteArea());
				rtpps.setRtpps(list);
				HttpRequest req = new HttpRequest();
				HttpResponse response;
				try {
					response = req.post("application/json",ri.getUrl(),rtpps);
					//获取响应实体信息
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						String result = EntityUtils.toString(entity, "UTF-8");
						logger.info("---------------------------返回实体信息-------------------------");
						logger.info(result);
						// 确保HTTP响应内容全部被读出或者内容流被关闭
						EntityUtils.consume(entity);
						logger.debug("删除"+ip+",并成功通知router组件");
					}
				} catch (Exception e) {
					logger.debug("删除"+ip+",通知router组件失败");
					e.printStackTrace();
				}
			}
		});
		/*List<TbRsRTPP> RTPPList = resourceServiceImpl.getRTPPListByIp(ip);
		for(TbRsRTPP rtpp : RTPPList){
			tbRsRTPP.setRouteArea(rtpp.getRouteArea());
			resourceServiceImpl.deleteByPrimaryKey(Integer.valueOf(rtpp.getRtppSid()));
			Thread thread=new Thread(new Runnable(){
				public void run(){
					RouterInfo ri = new RouterInfo();
					//删除成功后  通知router组件
					Rtpps rtpps = new Rtpps();
					List list =new ArrayList();
					list.add(ip);
					rtpps.setAction(ri.getDel());
					rtpps.setArea(tbRsRTPP.getRouteArea());
					rtpps.setRtpps(list);
					HttpRequest req = new HttpRequest();
					HttpResponse response;
					try {
						response = req.post("application/json",ri.getUrl(),rtpps);
						//获取响应实体信息
						HttpEntity entity = response.getEntity();
						if (entity != null) {
							String result = EntityUtils.toString(entity, "UTF-8");
							logger.info("---------------------------返回实体信息-------------------------");
							logger.info(result);
							// 确保HTTP响应内容全部被读出或者内容流被关闭
							EntityUtils.consume(entity);
							logger.debug("删除"+ip+",并成功通知router组件");
						}
					} catch (Exception e) {
						logger.debug("删除"+ip+",通知router组件失败");
						e.printStackTrace();
					}
				}
			});
			thread.start();
		}*/
		if(temp > 0){
			t.start();
			return gson.toJson(1);
		}else{
			return gson.toJson(0);
		}
	}
	
	/**
	 * 批量删除节点
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/batchDelResource")
	@ResponseBody
	@Log(name = "批量删除资源节点", type = "5")
	public String batchDelResource(HttpSession session,HttpServletRequest request,final String checkedId,TbRsRTPP tb) throws Exception{
		logger.info("批量删除资源节点--------->>"+checkedId);
		int temp = resourceServiceImpl.batchDelResource(checkedId); 
		Thread t = new Thread(new Runnable(){
			@Override
			public void run() {
				RouterInfo ri = new RouterInfo();
				Map<String, Object> map=new HashMap<String, Object>();
				//删除成功后  通知router组件
				Rtpps rtpps = new Rtpps();
				String[]  arry = checkedId.split(",");
				List<String> list = Arrays.asList(arry);
				rtpps.setAction(ri.getDel());
				
				rtpps.setRtpps(list);
				HttpRequest req = new HttpRequest();
				HttpResponse response;
				try {
					response = req.post("application/json",ri.getUrl(),rtpps);
					//获取响应实体信息
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						String result = EntityUtils.toString(entity, "UTF-8");
						// 确保HTTP响应内容全部被读出或者内容流被关闭
						EntityUtils.consume(entity);
						logger.debug("批量删除资源节点成功,并成功通知router删除的节点："+arry);
					}
				} catch (Exception e) {
					logger.debug("批量删除资源节点成功,通知router失败删除的节点："+arry);
					e.printStackTrace();
				}
			}
			
		});
		if(temp > 0){
			t.start();
			return gson.toJson(1);
		}
		return gson.toJson(0);
	}
	
	/**
	 * 修改某个资源节点的信息
	 * 1.得到页面资源节点rtppSid
	 * 2.查出该节点的原始信息
	 * 3.获取当前时间作为updateCreate
	 * 4.如果不是下线状态      则 修改资源方状态为审核中
	 * 5.将页面的值赋给要修改的对象
	 * 5.执行修改操作
	 * 6.返回执行结果
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/editResource")
	@ResponseBody
	@Log(name = "修改资源节点信息", type = "4")
	public String editResource(HttpSession session,HttpServletRequest request,TbRsRTPP rtpp01) throws Exception{
		logger.info("保存修改的资源节点--------->>");
		request.getParameterMap();
		int rtppSid = rtpp01.getRtppSid();
		final TbRsRTPP rtpp02 = resourceServiceImpl.selectByPrimaryKey(rtppSid);
		String status1 = rtpp02.getStatus();
		String status2 = null;
		//如果在下线状态下  再次修改状态  则状态不变
		//否则状态变为审核中 
		if(status1.equals(FlypaasConstant.RTPP_STATUS_INIT)){
			status2 = FlypaasConstant.RTPP_STATUS_INIT;
		}else{
			status2 = FlypaasConstant.RTPP_STATUS_AUDITING;
		}
		rtpp02.setName(rtpp01.getName());
		rtpp02.setIp(rtpp01.getIp());
		rtpp02.setBandwidthLimit(rtpp01.getBandwidthLimit());
		int flag = Integer.valueOf(request.getParameter("block_Price"));
		if(flag==1){
			int index = Integer.valueOf(request.getParameter("index"));
			StringBuffer sb = new StringBuffer("[");
			for(int i = 1; i<=index; i++){
				String price = request.getParameter("price"+i);
				String startTime = request.getParameter("startTime"+i);
				String endTime= request.getParameter("endTime"+i);
				if(i==index){
					sb.append("{\"starttime\":\""+startTime+"\",\"endtime\":\""+endTime+"\",\"price\":\""+price+"\"},");
				}else{
					sb.append("{\"starttime\":\""+startTime+"\",\"endtime\":\""+endTime+"\",\"price\":\""+price+"\"}");
				}
			}
			sb.append("]");
			rtpp02.setBlockPrice(sb.toString());
		}
		rtpp02.setConcurrencyLimit(rtpp01.getConcurrencyLimit());
		rtpp02.setIsToLine(rtpp01.getIsToLine());
		rtpp02.setToIp(rtpp01.getToIp());;
		rtpp02.setRegion(rtpp01.getRegion());
		rtpp02.setType(rtpp01.getType());
		rtpp02.setNetLevel(rtpp01.getNetLevel());
		rtpp02.setOperator(rtpp01.getOperator());
		rtpp02.setPrice(rtpp01.getPrice());
		rtpp02.setZone(rtpp01.getZone());
		rtpp02.setProvince(rtpp01.getProvince());
		rtpp02.setCountry(rtpp01.getCountry());
		rtpp02.setUpdateDate(DateUtil.getCurrentDate());
		rtpp02.setStatus(status2);
		rtpp01.setName(java.net.URLDecoder.decode(rtpp01.getName(), "UTF-8"));
		int temp = resourceServiceImpl.updateByPrimaryKeySelective(rtpp02);//保存该域节点
		Thread t = new Thread(new Runnable(){
			@Override
			public void run() {
				RouterInfo ri = new RouterInfo();
				logger.info(ri.getUrl());
				//修改成功后  通知router组件
				Rtpps rtpps = new Rtpps();
				List list =new ArrayList();
				list.add(rtpp02.getIp());
				rtpps.setAction(ri.getUpd());
				rtpps.setArea(StringUtils.isEmpty(rtpp02.getRouteArea()) ? RouterConstant.ROUTE_DEFAULT_KEY_CN : rtpp02.getRouteArea());
				rtpps.setRtpps(list);
				HttpRequest req = new HttpRequest();
				HttpResponse response;
				try {
					System.out.println(ri);
					response = req.post("application/json",ri.getUrl(),rtpps);
					//获取响应实体信息
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						String result = EntityUtils.toString(entity, "UTF-8");
						// 确保HTTP响应内容全部被读出或者内容流被关闭
						EntityUtils.consume(entity);
						logger.debug("修改资源节点成功，并成功通知router组件成功,资源Ip"+rtpp02.getIp());
					}
				} catch (Exception e1) {
					logger.debug("修改资源节点成功，通知router组件失败,资源Ip"+rtpp02.getIp());
					e1.printStackTrace();
				}
			}
		});
		
		/*List<TbRsRTPP> RTPPList = resourceServiceImpl.getRTPPListByIp(originalIP);//保存其他域节点
		for(TbRsRTPP rtpp : RTPPList){
			rtpp.setNetSid(rtpp.getNetSid());
			rtpp.setRtppSid(rtpp.getRtppSid());
			rtpp.setRouteArea(rtpp.getRouteArea());
			rtpp.setName(rtpp.getName());
			rtpp.setPrice(rtpp.getPrice());
			rtpp.setAgentPrice(rtpp.getAgentPrice());
			resourceServiceImpl.updateByPrimaryKeySelective(rtpp02);
			Thread thread = new Thread(new Runnable(){
				@Override
				public void run() {
					RouterInfo ri = new RouterInfo();
					logger.info(ri.getUrl());
					//修改成功后  通知router组件
					Rtpps rtpps = new Rtpps();
					List list =new ArrayList();
					list.add(rtpp02.getIp());
					rtpps.setArea(rtpp02.getRouteArea());
					rtpps.setAction(ri.getUpd());
					rtpps.setRtpps(list);
//					rtpps.setArea(area);
					HttpRequest req = new HttpRequest();
					HttpResponse response;
					try {
						System.out.println(ri);
						response = req.post("application/json",ri.getUrl(),rtpps);
						//获取响应实体信息
						HttpEntity entity = response.getEntity();
						if (entity != null) {
							String result = EntityUtils.toString(entity, "UTF-8");
							// 确保HTTP响应内容全部被读出或者内容流被关闭
							EntityUtils.consume(entity);
							logger.debug("修改资源节点成功，并成功通知router组件成功,资源Ip"+rtpp02.getIp());
						}
					} catch (Exception e1) {
						logger.debug("修改资源节点成功，通知router组件失败,资源Ip"+rtpp02.getIp());
						e1.printStackTrace();
					}
				}
			});
			thread.start();
		}*/
		
		if(temp > 0){
			t.start();
			return gson.toJson(1);
		}
		return gson.toJson(0);
	}
	
	
	
	/**
	 * 修改资源节点的状态
	 * @param request
	 * @param rtpp01
	 * @return
	 */
	@RequestMapping("/editStatus")
	@ResponseBody
	@Log(name = "修改资源节点状态", type = "4")
	public String editStatus(HttpServletRequest request,TbRsRTPP rtpp){
		logger.info("修改滋源节点的状态--------------->>");
//		TbRsAccountInfo tbRsAccountInfo = (TbRsAccountInfo) request.getSession().getAttribute("userSideSession");
//		String routeDomain = tbRsAccountInfo.getNetArea() == null ? RouterConstant.ROUTE_DEFAULT_KEY_CN :tbRsAccountInfo.getNetArea();
		int rtppSid = Integer.valueOf(request.getParameter("rtppSid"));
		String status = request.getParameter("status");
		rtpp = resourceServiceImpl.selectByPrimaryKey(rtppSid);
		final String ip = rtpp.getIp();
		Set<String> keys = RedisService.getInstance().getKeys("RTPP_INFO_*" + ip);
		if(FlypaasConstant.RTPP_STATUS_STOP.equals(status)){
			for(String key : keys){
				Map<String,String> map=RedisService.getInstance().hgetall(key);
				if(map.get("concurrency") != null && !"0".equals(map.get("concurrency"))){
					return gson.toJson(2);
				}
			}
		}
		rtpp.setStatus(status);
		int temp = resourceServiceImpl.updateByPrimaryKeySelective(rtpp);
		final String routeArea = StringUtils.isEmpty(rtpp.getRouteArea()) ? RouterConstant.ROUTE_DEFAULT_KEY_CN : rtpp.getRouteArea();
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run() {
				RouterInfo ri = new RouterInfo();
				logger.info(ri.getUrl());
				//修改成功后  通知router组件
				Rtpps rtpps = new Rtpps();
				List list =new ArrayList();
				list.add(ip);
				rtpps.setArea(routeArea);
				rtpps.setAction(ri.getUpd());
				rtpps.setRtpps(list);
//				rtpps.setArea(area);
				HttpRequest req = new HttpRequest();
				HttpResponse response;
				try {
					System.out.println(ri);
					response = req.post("application/json",ri.getUrl(),rtpps);
					//获取响应实体信息
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						String result = EntityUtils.toString(entity, "UTF-8");
						// 确保HTTP响应内容全部被读出或者内容流被关闭
						EntityUtils.consume(entity);
						logger.debug("修改资源节点成功，并成功通知router组件成功,资源Ip"+ip);
					}
				} catch (Exception e1) {
					logger.debug("修改资源节点成功，通知router组件失败,资源Ip"+ip);
					e1.printStackTrace();
				}
			}
		});
		/*List<TbRsRTPP> RTPPList = resourceServiceImpl.getRTPPListByIp(ip);//保存其他域节点
		if(FlypaasConstant.RTPP_STATUS_STOP.equals(status)){
			for(TbRsRTPP tbRsRTPP : RTPPList){
				String routeDomain = tbRsRTPP.getRouteArea();
				Map<String,String> map=RedisService.getInstance().hgetall("RTPP_INFO_" + routeDomain +"_"+ ip);
				if(map.get("concurrency") != null && !"0".equals(map.get("concurrency"))){
					return gson.toJson(2);
				}
			}
		}*/
//		rtpp.setStatus(status);
		/*int temp = 1;
		for(TbRsRTPP tbRsRTPP : RTPPList){
			final String routeArea = tbRsRTPP.getRouteArea();
			rtpp.setRtppSid(tbRsRTPP.getRtppSid());
			rtpp.setRouteArea(routeArea);
			rtpp.setName(tbRsRTPP.getName());
			rtpp.setPrice(tbRsRTPP.getPrice());
			rtpp.setStatus(status);
			resourceServiceImpl.updateByPrimaryKeySelective(rtpp);
			Thread thread = new Thread(new Runnable(){
				@Override
				public void run() {
					RouterInfo ri = new RouterInfo();
					logger.info(ri.getUrl());
					//修改成功后  通知router组件
					Rtpps rtpps = new Rtpps();
					List list =new ArrayList();
					list.add(ip);
					rtpps.setArea(routeArea);
					rtpps.setAction(ri.getUpd());
					rtpps.setRtpps(list);
//					rtpps.setArea(area);
					HttpRequest req = new HttpRequest();
					HttpResponse response;
					try {
						System.out.println(ri);
						response = req.post("application/json",ri.getUrl(),rtpps);
						//获取响应实体信息
						HttpEntity entity = response.getEntity();
						if (entity != null) {
							String result = EntityUtils.toString(entity, "UTF-8");
							// 确保HTTP响应内容全部被读出或者内容流被关闭
							EntityUtils.consume(entity);
							logger.debug("修改资源节点成功，并成功通知router组件成功,资源Ip"+ip);
						}
					} catch (Exception e1) {
						logger.debug("修改资源节点成功，通知router组件失败,资源Ip"+ip);
						e1.printStackTrace();
					}
				}
			});
		}*/
		if(temp > 0){
			thread.start();
			return gson.toJson(1);
		}else{
			return gson.toJson(0);
		}
	}
	
}
