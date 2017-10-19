package com.flypaas.action.app;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;








import com.flypaas.action.BaseAction;
import com.flypaas.constant.AppConstant;
import com.flypaas.constant.SysConstant;
import com.flypaas.entity.Application;
import com.flypaas.entity.NotifyCallFile;
import com.flypaas.entity.FlypaasId;
import com.flypaas.entity.vo.PageContainer;
import com.flypaas.utils.Des3Utils;
import com.flypaas.utils.FileUtil;
import com.flypaas.utils.StrUtil;
import com.flypaas.utils.SysConfig;

@Controller
@Scope("prototype")
@Results({
	@Result(name="cloudNoticeSuc",location="/page/cloudNotice/cloud_notice.jsp"),
	@Result(name="addCnPageSuc",location="/page/cloudNotice/add.jsp"),
	@Result(name="cloudNotice",type="redirectAction",location="cloudNotice")
})
public class CloudNoticeAction extends BaseAction {
	private PageContainer page;
	private NotifyCallFile ncf;
	private File voice;
	private String voiceFileName;
	private List<Application> appList;
	private String id;
	@Action("/app/cloudNotice")
	public String cloudNotice(){
		String sid = getSessionUser().getSid();
		if(page==null){
			page = new PageContainer();
		}
		page.getParams().put("sid", sid);
		String text = request.getParameter("text");
		if(StringUtils.isNotBlank(text)){
			page.getParams().put("text",text);
		}
		page = cloudNoticeService.getCloudNtc(page);
		buildPage(page);
		return "cloudNoticeSuc";
	}
	
	private void buildPage(PageContainer page){
		if(page==null){
			return ;
		}
		String voicePath = SysConfig.getInstance().getProperty("oauth_ring");
		List<Map<String, Object>> list = page.getList();
		for(Map<String, Object> map : list){
			String fileName = map.get("file_name").toString();
			String filePath = map.get("file_path").toString();
			String quaName = voicePath+filePath+"/"+fileName;
			map.put("file_path", Des3Utils.encodeDes3(quaName));
		}
	}
	
	@Action("/app/addCnPage")
	public String addCnPage(){
		String sid = getSessionUser().getSid();
		appList = appService.getAllAppBySid(sid);
		return "addCnPageSuc";
	}
	@Action("/app/addCloudNotice")
	public String add(){
		String cbFileType = null ;
		if(StrUtil.isEmpty(voice)||StrUtil.isEmpty(ncf)){
			StrUtil.writeMsg(response, "提交参数有错误，请检查收重试", null);
			return null;
		}
		String result = FileUtil.checkFile(voice,SysConstant.SYS_RING_MAXSIZE);
		if(result!=null){
			StrUtil.writeMsg(response,result,null);
			return null ;
		}
		cbFileType = voiceFileName.substring(voiceFileName.lastIndexOf(".")+1);
		String filePath = SysConfig.getInstance().getProperty("oauth_ring_temp")+AppConstant.APP_CLOUD_VOICE_PATH+"/";
		
		FlypaasId ucpId = otherService.getId();
		String fileName = ucpId.getId().toString()+"."+cbFileType;
		
		FileUtil.baseUpload(voice,filePath,fileName);
		
		if(!StrUtil.isEmpty(voice)){
			boolean boo =FileUtil.convertMp3(ucpId.getId().toString(), cbFileType,filePath);
			if(!boo){
				FileUtil.deleteFile(filePath+voiceFileName);
				StrUtil.writeMsg(response, "语音文件只支持mp3和wav格式", null);
				return null ;
			}
		}
		ncf.setFileType(cbFileType);
		ncf.setSid(getSessionUser().getSid());
		ncf.setUserFileName(voiceFileName);
		ncf.setFileName(fileName);
		cloudNoticeService.add(voice,ncf);
		return "cloudNotice";
	}
	
	@Action("/app/delCloudNotice")
	public String delete(){
		if(StrUtil.isEmpty(id)){
			StrUtil.writeMsg(response, "删除失败", null);
			return null;
		}
		id = Des3Utils.decodeDes3(id);
		ncf = new NotifyCallFile();
		ncf.setId(Long.parseLong(id));
		ncf.setSid(getSessionUser().getSid());
		cloudNoticeService.delete(ncf);
		return "cloudNotice";
	}
	
	public File getVoice() {
		return voice;
	}
	public void setVoice(File voice) {
		this.voice = voice;
	}
	public NotifyCallFile getNcf() {
		return ncf;
	}
	public void setNcf(NotifyCallFile ncf) {
		this.ncf = ncf;
	}
	public String getVoiceFileName() {
		return voiceFileName;
	}
	public void setVoiceFileName(String voiceFileName) {
		this.voiceFileName = voiceFileName;
	}
	public List<Application> getAppList() {
		return appList;
	}
	public void setAppList(List<Application> appList) {
		this.appList = appList;
	}
	public PageContainer getPage() {
		return page;
	}
	public void setPage(PageContainer page) {
		this.page = page;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
