package com.flypaas.action;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.context.MySessionContext;
import com.flypaas.entity.TbFlypaasUser;
import com.flypaas.utils.StrUtil;

@Controller
@Scope("prototype")
public class ExtNotcieAction extends BaseAction {

	private String sid;
	Logger logger = LoggerFactory.getLogger(ExtNotcieAction.class);
	
	@SuppressWarnings("static-access")
	@Action("/ext/closeAcct")
	public void closeAcct(){
		MySessionContext myc= MySessionContext.getInstance();
		Map<String,Object> map = myc.mymap;
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		HttpSession sessionRm = null ;
		while(it.hasNext()){
			String key = it.next();
			HttpSession session = (HttpSession) map.get(key);
			TbFlypaasUser u = (TbFlypaasUser) session.getAttribute("user");
			if(u!=null && u.getSid().equals(sid)){
				sessionRm = session ;
				break;
			}
		}
		if(sessionRm!=null){
			sessionRm.invalidate();
			myc.DelSession(sessionRm);
		}
		
	}
	@Action("/ext/updateUser")
	public void updateUser(){
		TbFlypaasUser user = userService.findUserById(sid);
		recordSession(user);
	}
	@Action("/ext/monitorHeartbeat")
	public void monitorHeartbeat(){
		String stu_1="{\"result\":\"1\"}";
		String stu_0="{\"result\":\"0\"}";
		try {
			TbFlypaasUser user = userService.monitorHeartbeat();
			if(!StrUtil.isEmpty(user) && !StrUtil.isEmpty(user.getSid())){
				printMsg(stu_1);
			}else{
				printMsg(stu_0);
			}
		} catch (Exception e) {
			logger.error("监控心跳检测日志打印,<br/>flypaas异常");
			logger.error(e.getMessage());
			printMsg(stu_0);
		}
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
	
	
}
