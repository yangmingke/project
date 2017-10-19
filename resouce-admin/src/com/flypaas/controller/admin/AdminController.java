package com.flypaas.controller.admin;


import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.flypaas.model.TbRsUserInfo;
import com.flypaas.service.admin.UserInfoService;
import com.flypaas.util.RandomValidateCode;
import com.flypaas.util.StrUtil;
/**
 * 登陆验证
 * @author win10
 *
 */
@Controller
@RequestMapping("/adminController")
public class AdminController{
	@Autowired
	UserInfoService userInfoServiceImpl;
	
	public static Logger logger = Logger.getLogger(AdminController.class);
	
	/**
	 * 登陆判断
	 * @param inputStr
	 * @param session
	 * @param request
	 * @param userInfo
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/checkVerify")
    @ResponseBody
    public int checkVerify(String inputStr,HttpSession session,HttpServletRequest request,TbRsUserInfo userInfo) {
		//从session中获取随机数
        String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
        if(random.equals(inputStr)){
        	//验证码正确
        	TbRsUserInfo user = userInfoServiceImpl.queryUserInfo(userInfo);
    		if(user==null){
    			logger.error("用户名或密码错误");
    			return 1;
    		}else{
    			logger.debug("登录成功");
    			user.setLoginTimes(user.getLoginTimes()+1);
    			userInfoServiceImpl.updateByPrimaryKeySelective(user);
    			session.setAttribute("adminSession", user);
    			return 0;
    		}
        }else{
        	logger.error("验证码错误");
            return 2;//验证码错误
        }
    } 
	
	
	/**
	 * 登录页面生成验证码
	 * @param request
	 * @param response
	 */
    @RequestMapping("/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片  
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容  
        response.setHeader("Cache-Control", "no-cache"); 
        response.setDateHeader("Expire", 0); 
        RandomValidateCode randomValidateCode = new RandomValidateCode(); 
        try { 
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法  
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    } 
    
    /**
	 * 修改密码
	 * @param request
	 * @param response
	 */
    @RequestMapping("/editpass")
    @ResponseBody
    public int editpass(HttpServletRequest request, HttpServletResponse response,String newpass,String mpass){
    	
    	
    	HttpSession session = request.getSession();
    	TbRsUserInfo user = (TbRsUserInfo) session.getAttribute("adminSession");
    	if(StrUtil.isEmpty(mpass) || !mpass.equals(user.getPassword())){
    		//原始密码错误
    		return 2;
    	}
    	user.setPassword(newpass);
    	int result = userInfoServiceImpl.editUserPwd(user);
    	if(result == 0){
    		//参数发生异常
    		return 1;
    	}
    	return 0;
    }
    
    @RequestMapping("/logout")
	public String logout(HttpServletRequest request){
    	HttpSession session = request.getSession();
    	logger.info("用户退出--------->>");
		session.removeAttribute("adminSession");
		return ("redirect:/login.jsp");
	}
    
    /**
	 * 登录成功后进入菜单
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpSession session){
		logger.info("进入查询菜单Controller");
		TbRsUserInfo user = (TbRsUserInfo) session.getAttribute("adminSession");
		if(user == null){
			return new ModelAndView("403");
		}else{
			return new ModelAndView("index");
		}
	}
	
}
