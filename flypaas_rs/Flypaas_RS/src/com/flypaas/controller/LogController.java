package com.flypaas.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.flypaas.constant.FlypaasConstant;
import com.flypaas.model.TbRsAccountInfo;
import com.flypaas.model.vo.LogUser;
import com.flypaas.service.LogService;
import com.flypaas.util.PageContainer;


/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2016年12月29日 下午4:47:47
* 类说明
*/
@Controller
@RequestMapping("/logController")
public class LogController {
	public static Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private LogService logServiceImpl;
	
	/**
	 * 查看系统日志文件
	 * @param session
	 * @param model
	 * @param list
	 * @return
	 */
	@RequestMapping("/queryLog")
	public ModelAndView queryLog(HttpSession session,ModelMap model,HttpServletRequest request){
		logger.info("查看日志文件------------------->>");
		int page;
		String page01 =request.getParameter("page");
		String dateMin = request.getParameter("dateMin");
		String dateMax = request.getParameter("dateMax");
		model.put("dateMin", dateMin);
		model.put("dateMax", dateMax);
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
		TbRsAccountInfo user =(TbRsAccountInfo) session.getAttribute("userSideSession");
		PageContainer  pageContainer  = logServiceImpl.queryDemo(user.getNetSid(),page,dateMin,dateMax);
		
		List<Object> list = pageContainer.getResult();
		for(int i = 0;i<list.size();i++){
			String opType = ((LogUser) list.get(i)).getOpType();
			if(opType.equals(FlypaasConstant.status_1)){
				((LogUser) list.get(i)).setOpType("查看");
			}else if(opType.equals(FlypaasConstant.status_2)){
				((LogUser) list.get(i)).setOpType("查询");
			}else if(opType.equals(FlypaasConstant.status_3)){
				((LogUser) list.get(i)).setOpType("添加");
			}else if(opType.equals(FlypaasConstant.status_4)){
				((LogUser) list.get(i)).setOpType("修改");
			}else{
				((LogUser) list.get(i)).setOpType("删除");
			}
		}
		model.put("logList", list);
		model.put("count", 	pageContainer.getTotalCount());
		model.put("page", pageContainer);
		return new ModelAndView("jspPage/log/log-show",model);
	}
	
}
