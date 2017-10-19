package com.flypaas.action.app;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.action.BaseAction;
import com.flypaas.constant.AppConstant;
import com.flypaas.constant.SysConstant;
import com.flypaas.entity.Application;
import com.flypaas.entity.Ring;
import com.flypaas.entity.FlypaasId;
import com.flypaas.entity.vo.PageContainer;
import com.flypaas.utils.DateUtil;
import com.flypaas.utils.Des3Utils;
import com.flypaas.utils.FileUtil;
import com.flypaas.utils.StrUtil;
import com.flypaas.utils.SysConfig;

@Controller
@Scope("prototype")
@Results({
	@Result(name="list",location="/page/ring/ring.jsp"),
	@Result(name="toQuery",type="redirectAction",location="query"),
	@Result(name="edit",location="/page/ring/ring_edit.jsp"),
})
public class RingAction extends BaseAction {
	private PageContainer page;
	private Ring ring;
	private List<Application> appList;
	private File voice;
	private String voiceFileName;
	private String id;
	
	Logger logger = LoggerFactory.getLogger(RingAction.class);
	@Action("/app/ring/query")
	public String ring(){
		if (null == page) {
			page = new PageContainer();
		}
		String sid = getSessionUser().getSid();
		if (ring==null) {
			ring = new Ring();
		}
		
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("sid", sid);
		String text=  request.getParameter("text");
		if(StringUtils.isNotBlank(text)){
			param.put("text",text);
		}
		page.setParams(param);
		page = ringService.query(page);
		List<Map<String, Object>> lst = page.getList();
		for(Map<String,Object> map : lst){
			Object obj = map.get("file_name");
			if(!StrUtil.isEmpty(obj)){
				map.put("fileFullPath", Des3Utils.encodeDes3(SysConfig.getInstance().getProperty("oauth_ring")+obj));
			}
		}
		return "list";
	}
	@Action("/app/ring/del")
	public String deleteRing(){
		if(StrUtil.isEmpty(id)){
			StrUtil.writeMsg(response, "参数错误", null);
			return null;
		}
		try {
			id = Des3Utils.decodeDes3(id);
		} catch (Exception e) {
			StrUtil.writeMsg(response, "参数错误", null);
			return null;
		}
		String sid = getSessionUser().getSid();
		ring = new Ring();
		ring.setId(Long.parseLong(id));
		ring.setSid(sid);
		ringService.delete(ring);
		return "toQuery";
	}
	
	@Action("/app/ring/edit")
	public String editRing(){
		String sid = getSessionUser().getSid();
		if(!StrUtil.isEmpty(id)){
			try {
				id = Des3Utils.decodeDes3(id);
				ring = new Ring();
				ring.setId(Long.parseLong(id));
				ring.setSid(sid);
				ring = ringService.getRingByIdSid(ring);
			} catch (Exception e) {
				StrUtil.writeMsg(response, "参数错误", null);
				return null;
			}
		}
		appList = appService.getAllAppBySid(sid);
		return "edit";
	}
	
	@Action("/app/ring/save")
	public String save(){
		if(StrUtil.isEmpty(ring)||StrUtil.isEmpty(ring.getAppSid())||StrUtil.isEmpty(ring.getType()) || StrUtil.isEmpty(ring.getContentType())){
			StrUtil.writeMsg(response, "提交铃音信息不合法，请检查后重试", null);
		}
		if(Integer.valueOf(2).equals(ring.getContentType()) && StrUtil.isEmpty(ring.getContent())){
			StrUtil.writeMsg(response, "提交铃音信息不合法，请检查后重试", null);
			return null;
		}else if(Integer.valueOf(1).equals(ring.getContentType()) && StrUtil.isEmpty(voice)){
			StrUtil.writeMsg(response, "提交铃音信息不合法，请检查后重试", null);
			return null;
		}
		String cbFileType = null ;
		String cbFileName = null;
	
		String fileId = null;
		if(AppConstant.RING_TYPE_1==ring.getType()){
			fileId = ring.getAppSid();
		}else{
			fileId = otherService.getId().getId().toString();
		}
		
		if(!StrUtil.isEmpty(voice)){
			String result = FileUtil.checkFile(voice,SysConstant.SYS_RING_MAXSIZE);
			if(result!=null){
				StrUtil.writeMsg(response,result,null);
				return null ;
			}
			String filePath = SysConfig.getInstance().getProperty("oauth_ring_temp");
			cbFileType = voiceFileName.substring(voiceFileName.lastIndexOf(".")+1);
			cbFileName = FileUtil.uploadRing(voice,AppConstant.RING_TYPE_1==ring.getType()?ring.getAppSid():fileId,filePath,AppConstant.RING_TYPE_1==ring.getType()?"a":"c",cbFileType);
			cbFileName = cbFileName.substring(0, cbFileName.lastIndexOf("."));
			boolean boo = FileUtil.convertMp3(cbFileName, cbFileType,filePath);
			if(!boo){
				FileUtil.deleteFile(filePath+cbFileName+"."+cbFileType);
				voice = null ;
				StrUtil.writeMsg(response, "语音文件只支持mp3和wav格式", null);
				return null ;
			}
			ring.setUserFileName(voiceFileName);
		}
		String sid = getSessionUser().getSid();
		ring.setSid(sid);
		ring.setUpdateDate(DateUtil.getCurrentDate());
		ring.setAuditStatus(1);
		ring.setUploadStatus(1);
		if(!StrUtil.isEmpty(id)){
			try {
				id = Des3Utils.decodeDes3(id);
				ring.setId(Long.parseLong(id));
				ringService.update(ring, voice, AppConstant.RING_TYPE_1==ring.getType()?"a":"c", cbFileType,fileId);
			} catch (Exception e) {
				logger.error(e.getMessage());
				StrUtil.writeMsg(response, "参数错误", null);
				return null;
			}
		}else{
			if(AppConstant.RING_TYPE_1==ring.getType()){
				Ring r = ringService.get(ring);
				if(!StrUtil.isEmpty(r)){
					ringService.delete(r);
				}
			}
			FlypaasId ucpId = otherService.getId();
			ring.setId(ucpId.getId());
			ring.setCreateDate(DateUtil.getCurrentDate());
			ringService.add(ring, voice, AppConstant.RING_TYPE_1==ring.getType()?"a":"c", cbFileType,fileId);
		}
		return "toQuery";
	}

	public Ring getRing() {
		return ring;
	}

	public void setRing(Ring ring) {
		this.ring = ring;
	}

	public List<Application> getAppList() {
		return appList;
	}

	public void setAppList(List<Application> appList) {
		this.appList = appList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PageContainer getPage() {
		return page;
	}
	public void setPage(PageContainer page) {
		this.page = page;
	}
	public File getVoice() {
		return voice;
	}
	public void setVoice(File voice) {
		this.voice = voice;
	}
	public String getVoiceFileName() {
		return voiceFileName;
	}
	public void setVoiceFileName(String voiceFileName) {
		this.voiceFileName = voiceFileName;
	}
}
