package com.flypaas.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
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
import com.flypaas.entity.News;
import com.flypaas.entity.TbFlypaasUser;
import com.flypaas.entity.TestWhiteList;
import com.flypaas.entity.vo.PageContainer;
import com.flypaas.utils.StrUtil;

@Controller
@Scope("prototype")
@Results({
	@Result(name = "document", location = "/doc/document.jsp"),
	@Result(name = "freeVoice", location = "/front/solution1_new.jsp"),
	@Result(name = "ip", location = "/front/solution2_new.jsp"),
	@Result(name = "corporateCommt", location = "/front/solution3_new.jsp"),
	@Result(name = "interRoaming", location = "/front/solution4_new.jsp"),
	@Result(name = "secureCommt", location = "/front/solution5.jsp"),
	@Result(name = "hiddenNum", location = "/front/solution6.jsp"),
	@Result(name = "message", location = "/front/message.jsp"),
	@Result(name = "im", location = "/front/im_new.jsp"),
	@Result(name = "voice", location = "/front/voice_new.jsp"),
	@Result(name = "freetrial", location = "/front/experience.jsp"),
	@Result(name = "download", location = "/front/download_new.jsp"),
	@Result(name = "multmediasms", location = "/front/media.jsp"),
	@Result(name = "VoIP", location = "/front/net_voice.jsp"),
	@Result(name = "toll", location = "/front/land_call.jsp"),
	@Result(name = "callinOut", location = "/front/experience_2.jsp"),
	@Result(name = "VoiceVerificationCode", location = "/front/experience_1.jsp"),
	@Result(name = "VoiceVerificationCode_demo", location = "/front/experience_1_demo.jsp"),
	@Result(name = "telemarket", location = "/front/video.jsp"),
	@Result(name = "voice_1", location = "/front/voice_new_1.jsp"),
	@Result(name = "voice_2", location = "/front/voice_new_2.jsp"),
	@Result(name = "companyIntro", location = "/front/company_intro.jsp"),
	@Result(name = "service", location = "/front/service.jsp"),
	@Result(name = "items", location = "/front/items.jsp"),
	@Result(name = "cooperation", location = "/front/cooperation.jsp"),
	@Result(name = "report", location = "/front/report.jsp"),
	@Result(name = "news", location = "/front/news.jsp"),
	@Result(name = "newsInfo", location = "/front/news_detail.jsp"),
	@Result(name = "upDocListSuc", location = "/page/upDocList.jsp"),
	@Result(name="verifyCodeSuc",location="/front/verifycode.jsp"),
	@Result(name="smsCodeSuc",location="/front/verifycode_1.jsp"),
	@Result(name="codeDescSuc",location="/front/verifycode_2.jsp"),
	@Result(name="partners",location="/front/partners.jsp"),
	@Result(name="cloudNotify",location="/front/experience_3.jsp"),
	@Result(name="social",location="/front/social.jsp"),
	@Result(name="o2o",location="/front/o2o.jsp"),
	@Result(name="category",location="/front/category.jsp"),
	@Result(name="medical",location="/front/medical.jsp"),
	@Result(name="can",location="/front/can.jsp"),
	@Result(name="sdk",location="/front/sdk.jsp")
	})
public class DocAction extends BaseAction {

	private List<Map<String, Object>> sdkAList;
	private List<Map<String, Object>> sdkIList;
	private List<Map<String, Object>> sdkWList;
	private List<Map<String, Object>> clientList;
	private List<Map<String, Object>> restList;
	private PageContainer page;
	private String newsId;
	private News news;
	private File [] file;
	// 上传文件的类型 ContentType  
	private String [] fileContentType;  
	// 上传文件的名称  
	private String [] fileFileName; 
	private List<TestWhiteList> nbrList;
	private TbFlypaasUser user;
	
	
//	@Action("/ff")
//	public void ff(){
//			logger.info("***********************导入数据开始****************************");
//			File file  = new File(SysConfig.getInstance().getProperty("v_path"));
//			File [] files = file.listFiles();
//			BufferedReader input = null;
//			
//			if(files==null || files.length==0){
//				return;
//			}
//			for(File f : files){
//				if(f==null){
//					continue;
//				}
//				String ct = null;
//				try {
//					input = new BufferedReader(new FileReader(f));
//					ct = input.readLine();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				String ar [] = ct.split("\\,");
//				try {
//					final Map<String, Object> map = new HashMap<String, Object>();
//					map.put("sid", ar[0]);
//					map.put("vcode", ar[1]);
//					map.put("timestamp", ar[2]);
//					System.out.println("开始处理："+ar[0]);
//					otherService.addAuthFile(map);
//				} catch (Exception e) {
//					logger.error("导入验证数据失败,文件id："+ar);
//				}
//			}
//			logger.info("***********************导入数据结束****************************");
//	}
	
	@Action("/document")
	public String doc(){
		return "document" ;
	}
	@Action("/solution/freeVoice")
	public String freeVoice(){
		return "freeVoice" ;
	}
	@Action("/solution/ip")
	public String slIp(){
		return "ip" ;
	}
	@Action("/solution/corporateCommt")
	public String corporateCommt(){
		return "corporateCommt" ;
	}
	@Action("/solution/interRoaming")
	public String InterRoaming(){
		return "interRoaming" ;
	}
	@Action("/solution/secureCommt")
	public String secureCommt(){
		return "secureCommt" ;
	}
	@Action("/solution/hiddenNum")
	public String hiddenNum(){
		return "hiddenNum" ;
	}
	@Action("/product_service/im")
	public String im(){
		return "im";
	}
	@Action("/product_service/voice")
	public String voice(){
		return "voice";
	}
	@Action("/product_service/voice_1")
	public String voice_1(){
		return "voice_1";
	}
	@Action("/product_service/voice_2")
	public String voice_2(){
		return "voice_2";
	}
	@Action("/product_service/freetrial")
	public String freetrial(){
		return "freetrial";
	}
	@Action("/freetrial/multmediasms")
	public String multmediasms(){
		return "multmediasms";
	}
	@Action("/freetrial/VoIP")
	public String VoIP(){
		return "VoIP";
	}
	@Action("/freetrial/toll")
	public String toll(){
		return "toll";
	}
	@Action("/freetrial/callinout")
	public String CallinOut(){
		user = getSessionUser();
		if(!StrUtil.isEmpty(user)){
			nbrList = testWhilteListService.list(getSessionUser().getSid());
		}
		return "callinOut";
	}
	@Action("/freetrial/VoiceVerificationCode")
	public String VoiceVerificationCode(){
		return "VoiceVerificationCode";
	}
	@Action("/freetrial/VoiceVerificationCode_demo")
	public String VoiceVerificationCode_demo(){
		return "VoiceVerificationCode_demo";
	}
	@Action("/freetrial/telemarket")
	public String telemarket(){
		return "telemarket" ;
	}
	@Action("/message")
	public String message(){
		return "message";
	}
	@Action("/product_service/download")
	public String download(){
		//下载ios,android,windows类型
		String downloadType = null;
		if(StringUtils.isNotEmpty(request.getParameter("downloadType"))){
			downloadType = request.getParameter("downloadType");
		}
		request.setAttribute("downloadType", downloadType);
		
		String count = null ;
		String type = null ;
		String version = null ;
		String name = null ;
		String path = null ;
		String key = null ;
		String id = null ;
		Date pubTime = null ;
		Date updateDate = null;
		Map<String, Object> dataMap = null;
		List<Map<String, Object>> mapList = sDDownloadCountService.getCountList();
		sdkAList = new ArrayList<Map<String,Object>>();
		sdkWList = new ArrayList<Map<String,Object>>();
		sdkIList = new ArrayList<Map<String,Object>>();
		clientList = new ArrayList<Map<String,Object>>();
		restList = new ArrayList<Map<String,Object>>();
		if(mapList != null && mapList.size()>0){
			for(Map<String, Object> map : mapList){
				type = map.get("version_type").toString();
				count = map.get("download_count").toString();
				count = count==null?"0":count ;
				version = map.get("version_index").toString();
				name = map.get("version_name").toString();
				pubTime = (Date) map.get("create_date");
				path = map.get("version_path").toString();
				key = map.get("version_name_key").toString();
				id = map.get("id").toString();
				updateDate =(Date) map.get("update_date");
				if(type.startsWith(SysConstant.SDK)){
					dataMap = new HashMap<String, Object>();
					dataMap.put("version", version);
					dataMap.put("count", count);
					dataMap.put("pubTime", pubTime);
					dataMap.put("name", name);
					dataMap.put("path", path);
					dataMap.put("key", key);
					dataMap.put("id", id);
					dataMap.put("version", version);
					dataMap.put("updateDate", updateDate);
					if(key.endsWith("1")){
						sdkAList.add(dataMap);
					}else if(key.endsWith("2")){
						sdkIList.add(dataMap);
					}else{
						sdkWList.add(dataMap);
					}
					
				}
				if(type.startsWith(SysConstant.CLIENT_DEMO)){
					dataMap = new HashMap<String, Object>();
					dataMap.put("version", version);
					dataMap.put("count", count);
					dataMap.put("pubTime", pubTime);
					dataMap.put("name", name);
					dataMap.put("path", path);
					dataMap.put("key", key);
					dataMap.put("updateDate", updateDate);
					clientList.add(dataMap);
				}
				if(type.startsWith(SysConstant.REST_DEMO)){
					dataMap = new HashMap<String, Object>();
					dataMap.put("version", version);
					dataMap.put("count", count);
					dataMap.put("pubTime", pubTime);
					dataMap.put("name", name);
					dataMap.put("path", path);
					dataMap.put("key", key);
					dataMap.put("updateDate", updateDate);
					restList.add(dataMap);
				}
			}
		}
		return "download";
	}
	@Action("/doc/upDocList")
	public String upDocList(){
		return "upDocListSuc";
	}
	@Action("/doc/verifyCode")
	public String verifyCode(){
		return "verifyCodeSuc";
	}
	@Action("/doc/smsCode")
	public String smsCode(){
		return "smsCodeSuc";
	}
	@Action("/doc/codeDesc")
	public String codeDesc(){
		return "codeDescSuc";
	}
	@Action("/case/social")
	public String social(){
		return "social";
	}
	@Action("/case/o2o")
	public String o2o(){
		return "o2o";
	}
	@Action("/case/category")
	public String category(){
		return "category";
	}
	@Action("/case/medical")
	public String medical(){
		return "medical";
	}
	@Action("/freetrial/cloudNotify")
	public String cloudNotify(){
		return "cloudNotify";
	}
	
	@Action("/freetrial/can")
	public String can(){
		return "can";
	}
	
	@Action("/freetrial/sdk")
	public String sdk(){
		return "sdk";
	}
	
	public PageContainer getPage() {
		return page;
	}
	public void setPage(PageContainer page) {
		this.page = page;
	}
	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	public File[] getFile() {
		return file;
	}
	public void setFile(File[] file) {
		this.file = file;
	}
	public String[] getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String[] fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String[] getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}
	
	public List<Map<String, Object>> getClientList() {
		return clientList;
	}
	public void setClientList(List<Map<String, Object>> clientList) {
		this.clientList = clientList;
	}
	public List<Map<String, Object>> getRestList() {
		return restList;
	}
	public void setRestList(List<Map<String, Object>> restList) {
		this.restList = restList;
	}
	public List<Map<String, Object>> getSdkAList() {
		return sdkAList;
	}
	public void setSdkAList(List<Map<String, Object>> sdkAList) {
		this.sdkAList = sdkAList;
	}
	public List<Map<String, Object>> getSdkIList() {
		return sdkIList;
	}
	public void setSdkIList(List<Map<String, Object>> sdkIList) {
		this.sdkIList = sdkIList;
	}
	public List<Map<String, Object>> getSdkWList() {
		return sdkWList;
	}
	public void setSdkWList(List<Map<String, Object>> sdkWList) {
		this.sdkWList = sdkWList;
	}
	public List<TestWhiteList> getNbrList() {
		return nbrList;
	}
	public void setNbrList(List<TestWhiteList> nbrList) {
		this.nbrList = nbrList;
	}
	public TbFlypaasUser getUser() {
		return user;
	}
	public void setUser(TbFlypaasUser user) {
		this.user = user;
	}
	
}
