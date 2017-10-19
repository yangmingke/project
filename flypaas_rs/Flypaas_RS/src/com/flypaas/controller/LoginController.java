package com.flypaas.controller;


import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flypaas.model.TbRsAccountInfo;
import com.flypaas.model.TbRsUserInfo;
import com.flypaas.service.ResourceSideService;
import com.flypaas.service.UserInfoService;
import com.flypaas.util.RandomValidateCode;
import com.google.gson.Gson;
/**
 * 登陆验证
 * @author win10
 *
 */
@Controller
@RequestMapping("/loginController")
public class LoginController{
	public static Logger logger = Logger.getLogger(LoginController.class);
	private static Gson gson = new Gson();  
	
	@Autowired
	public UserInfoService userInfoServiceImpl;
	@Autowired
	public ResourceSideService resourceSideServiceImpl;

	
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
    public String checkVerify(String inputStr,HttpSession session,HttpServletRequest request,TbRsUserInfo userInfo) {
		//从session中获取随机数
        String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
        if(random.equals(inputStr)){
        	//验证码正确
        	Map statusMap = userInfoServiceImpl.queryUserInfo(userInfo);
    		if(statusMap != null && !statusMap.isEmpty()){
    			int userStatus = (int) statusMap.get("userStatus");
    			String accountStatus = (String) statusMap.get("accountStatus");
    			if(1 != userStatus){
    				logger.error("用户已锁定");
    	            return gson.toJson(3);
    			}
    			if("5".equals(accountStatus)){
    				logger.error("资源方已锁定");
    	            return gson.toJson(4);
    			}
    			logger.error("登录成功");
    			TbRsUserInfo user = userInfoServiceImpl.queryUserInfo2(userInfo.getEmail());
    			user.setLoginTimes(user.getLoginTimes()+1);
    			userInfoServiceImpl.updateByPrimaryKeySelective(user,null,null);
    			TbRsAccountInfo userSide = resourceSideServiceImpl.selectByPrimaryKey(user.getNetSid());
    			session.setAttribute("userSession", user);
    			session.setAttribute("userSideSession", userSide);
    			return gson.toJson(1);
    		}else{
    			logger.error("用户名或密码错误");
    			return gson.toJson(0);
    		}
        }else{
        	logger.error("验证码错误");
            return gson.toJson(2);//验证码错误
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
	
}
