package com.flypaas.controller.user;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.flypaas.model.TbRsUserInfo;
import com.flypaas.model.TbRsUserMsg;
import com.flypaas.service.user.MessageService;
import com.flypaas.util.StrUtil;
import com.google.gson.Gson;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2016年12月22日 下午7:08:03
* 类说明
*/
@Controller
@RequestMapping("/messageController")
public class MessageController {
	public static Logger logger = Logger.getLogger(UserController.class);
	public static Gson gson = new Gson(); 
	
	@Autowired
	private MessageService messageServiceImpl;
	
	/**
	 * 用户登陆后可以查看消息
	 * @return
	 */
	@RequestMapping("/queryAllMsg1")
	public ModelAndView queryAllMsg1(HttpSession session,ModelMap map){
		logger.info("查询当前用户的消息列表------->>");
		TbRsUserInfo user =(TbRsUserInfo) session.getAttribute("userSession");
		List<TbRsUserMsg> msgList = messageServiceImpl.queryAllMsg(user.getSid());
		map.put("msgList", msgList);
		return new ModelAndView("welcome",map);
	}
	
	/**
	 * 用户登陆后可以查看消息
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/queryAllMsg")
	@ResponseBody
	public String queryAllMsg(HttpSession session,ModelMap map) throws ParseException{
		logger.info("查询当前用户的消息列表------->>");
		TbRsUserInfo user =(TbRsUserInfo) session.getAttribute("userSession");
		List<TbRsUserMsg> msgList = messageServiceImpl.queryAllMsg(user.getSid());
		/*String createDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(int i = 0;i<msgList.size();i++){
			createDate = msgList.get(i).getCreateDate().toString();
			Date date = sdf.parse(createDate);
			msgList.get(i).setCreateDate(date);
		}*/
		return gson.toJson(msgList);
	}
	
	@RequestMapping("/updateMsg")
	@ResponseBody
	public ModelAndView updateMsg(HttpServletRequest request){
		logger.info("修改消息的状态---------------->>");
		Integer msgId = Integer.valueOf(request.getParameter("id"));
		messageServiceImpl.updateMsgStatus(msgId);
		return new ModelAndView("readMsg");
	}
	
	@RequestMapping("/delMsg")
	@ResponseBody
	public int delMsg(HttpServletRequest request, HttpSession session){
		logger.info("删除消息---------------->>");
		String msgIdsStr = request.getParameter("msgIds");
		if(StrUtil.isEmpty(msgIdsStr)){
			return 0;
		}
		String[] msgIds = msgIdsStr.split(",");
		TbRsUserInfo user =(TbRsUserInfo) session.getAttribute("userSession");
		Map para = new HashMap();
		para.put("msgIds", msgIds);
		para.put("sid", user.getSid());
		int count = messageServiceImpl.delMsg(para);
		
		return count;
	}
}
