package com.flypaas.admin.action.audit;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ch.qos.logback.core.util.CloseUtil;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.CommonService;
import com.flypaas.admin.service.audit.RingAuditService;
import com.flypaas.admin.util.ConfigUtils;
import com.flypaas.admin.util.ProcessObject;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 审核管理-铃声审核
 * 
 * @author zenglb
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/audit/ringAudit/query.jsp")})
public class RingAuditAction extends BaseAction {
	static final Logger logger = LoggerFactory.getLogger(RingAuditAction.class);
	private static final long serialVersionUID = -3109783061638805774L;
	
	private RingAuditService ringAuditService;
	private CommonService commonService;
	@Resource
	public void setRingAuditService(RingAuditService ringAuditService) {
		this.ringAuditService = ringAuditService;
	}
	@Resource
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	/**
	 * 查询
	 * 
	 * @return
	 */
	@Action("/ringAudit/query")
	public String query() {
		StrutsUtils.setAttribute("RING_BASE", ConfigUtils.ring_base_path);
		page = ringAuditService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 审核
	 * 
	 * @return
	 */
	@Action("/ringAudit/audit")
	public void audit() {
		data = ringAuditService.audit(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}
	
	@Action("/ringAudit/uplaod")
	public void uplaod() {
		String id = StrutsUtils.getParameterTrim("id");
		Map<String,Object> ring = ringAuditService.getOneById(id);
		data = new HashMap<String, Object>();
		if(null != ring){
			String appSid = (String) ring.get("app_sid");
			String fileName = (String) ring.get("file_name");
			Integer type = (Integer) ring.get("type");
			Integer content_type = (Integer) ring.get("content_type");
			String content = (String) ring.get("content");
			
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("id", id);
			params.put("upload_status", 2);
			if(Integer.valueOf(2).equals(content_type)){
				String key = "";
				if(Integer.valueOf(1).equals(type)){
					key = appSid + "_a";
				}else{
					key = commonService.generatedGlobalId() + (Integer.valueOf(1).equals(type)?"_a":"_c");
				}
				fileName = key + ".text";
				params.put("file_name", key+".pcm");
			}
			try {
				callShell(id ,appSid,fileName,content);
				ringAuditService.saveUploadResult(params,true);
				data.put("result", "success");
				data.put("msg", "已提交申请");
			} catch (Throwable e) {
				data.put("result", "fail");
				logger.error(e.getMessage(), e);
				data.put("msg", "提交申请失败");
			}
		}else{
			data.put("result", "fail");
			data.put("msg", "提交申请失败,数据不存在");
		}
		StrutsUtils.renderJson(data);
	}
	
	public void callShell(final String id,final String app_sid,final String fileName,final String content){
		final String[] cmds = {"脚本地址","文件名","文件地址","转换类型","内容","cb地址列表","cb侧的文件夹名称"};
		cmds[0] = ConfigUtils.rsync_shell;
		String[] ss = fileName.split("\\.");
		if(ss.length !=2){
			ss = fileName.split(".");
		}
		cmds[1] = ss[0];
		cmds[2] = ConfigUtils.ring_base_path;
		cmds[3] = ss[1];
		cmds[4] = null != content?content:"";

		final List<Map<String,Object>> list = ringAuditService.reloadCbIpList(true);
		StringBuffer sb = new StringBuffer();
		for (Map<String, Object> map : list) {
			sb.append((String)map.get("cb_ip")).append(",");
		}
		int len = sb.length();
		if(len > 0 ){
			sb.setLength(len - 1);
		}
		cmds[5] = sb.toString();
		cmds[6] = "voicefile";
		System.err.println(Arrays.toString(cmds));
		new Thread(){
			public void run() {
				final AtomicBoolean boo = new AtomicBoolean(false);
				ProcessObject.execute(cmds, new ProcessObject(){
					protected boolean processOutStreamLine(String line) {
						if(!line.startsWith("Frame")){
							logger.info("===="+line);
						}
						if(line.startsWith("result=")){
							if("result=OK".equals(line.trim())){
								logger.info("====转语音成功！");
								boo.set(true);
							}else{
								logger.info("====转语音失败！" + line);
								boo.set(false);
							}
							return true;
						}
						return false;
					}
				});
				callback(boo.get(),id,cmds[1],list);
			};
		}.start();
	}
	
	/**
	 * 审核
	 * 
	 * @return
	 */
	public void callback(boolean shell_result,String id,String fileName,List<Map<String,Object>> cbList) {
		final Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", id);
		if(shell_result){
			boolean b = true;
			if(fileName.length() == 34){
				String cb_ip = null;
				int cb_port = 0;
				String t = null;
				for (Map<String, Object> map : cbList) {
					cb_ip = (String) map.get("cb_ip");
					cb_port = (Integer)map.get("cb_port");
					t = socket(cb_ip,cb_port,fileName);
					b = b & "OK".equals(t);
					if(!b){
						break;
					}
				}
			}
			if(b){
				params.put("upload_status", "3");
			}else{
				params.put("upload_status", "4");
			}
		}else{
			params.put("upload_status", "4");
		}
		ringAuditService.saveUploadResult(params,false);
	}
	
	public String socket(String cb_ip,int cb_port,String params){
		String result = null;
		Socket sock = null;
		OutputStream out = null;
		InputStream in = null;
		try {
			sock = new Socket(cb_ip,cb_port);
			out = sock.getOutputStream();
			byte[] bytes = params.getBytes("UTF-8");
			out.write(bytes);
			in = sock.getInputStream();
			bytes = new byte[2];
			in.read(bytes);
			result = new String(bytes,"UTF-8");
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			result = "E-";
		}finally{
			CloseUtil.closeQuietly(out);
			CloseUtil.closeQuietly(in);
			CloseUtil.closeQuietly(sock);
		}
		logger.info("sock == " + cb_ip + ":" + cb_port +  " >> result = " + result);
		return result;
	}

	/**
	 * 保存补充原因
	 * 
	 * @return
	 */
	@Action("/ringAudit/saveReason")
	public void saveReason() {
		data = ringAuditService.saveReason(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}
}
