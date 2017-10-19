package com.flypaas.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.entity.TbFlypaasUser;
import com.flypaas.entity.UserMsg;
import com.flypaas.entity.vo.PageContainer;
import com.flypaas.utils.StrUtil;

@Controller
@Scope("prototype")
@Results({
	@Result(name="notice",location="/page/user/notice.jsp"),
	@Result(name="delSuc",type="redirectAction",location="notice")
})
public class NoticeAction extends BaseAction {
	private List<UserMsg> userMsgList ;
	private String msgId;
	private PageContainer page;
	/*---------------------------------------------消息列表--------------------------------*/
	@Action("/user/notice")
	public String notice(){
		TbFlypaasUser user = getSessionUser();
		if (null == page) {
			page = new PageContainer();
		}
		page.getParams().put("sid", user.getSid());
		page = userMsgService.getUserMsgList(page);
		int count = userMsgService.getCountMsg(user.getSid());
		getSessionUser().setCountMsg(count);
		return "notice" ;
	}
	/*---------------------------------------------删除消息--------------------------------*/
	@Action("/user/delNotice")
	public String delete(){
		UserMsg msg = new UserMsg();
		msg.setSid(getSessionUser().getSid());
		msg.setMsgId(Long.parseLong(msgId));
		userMsgService.delete(msg);
		return "delSuc";
	}
	/*---------------------------------------------更新消息为已读--------------------------------*/
	@Action("/user/updateNotice")
	public void updateHasRead(){
		TbFlypaasUser user = getSessionUser();
		if(StrUtil.isEmpty(msgId)){
			return;
		}
		UserMsg userMsg = new UserMsg();
		userMsg.setMsgId(Long.parseLong(msgId));
		userMsg.setSid(user.getSid());
		userMsgService.updateHasRead(userMsg);
		int count = userMsgService.getCountMsg(user.getSid());
		user.setCountMsg(count);
		recordSession(user);
	}
	/*---------------------------------------------获取消息desc--------------------------------*/
	@Action("/user/getMsgDesc")
	public void getMsgDesc(){
		String desc = userMsgService.getMsgDesc(Long.parseLong(msgId));
		printMsg(desc);
	}
	
	
	/*------------------------------------get set------------------------------------------*/
	public List<UserMsg> getUserMsgList() {
		return userMsgList;
	}
	public void setUserMsgList(List<UserMsg> userMsgList) {
		this.userMsgList = userMsgList;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public PageContainer getPage() {
		return page;
	}
	public void setPage(PageContainer page) {
		this.page = page;
	}
	
}
