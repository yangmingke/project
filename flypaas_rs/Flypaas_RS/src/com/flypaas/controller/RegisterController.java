package com.flypaas.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.flypaas.constant.FlypaasConstant;
import com.flypaas.model.TbRsAccountBalance;
import com.flypaas.model.TbRsAccountInfo;
import com.flypaas.model.TbRsUserInfo;
import com.flypaas.service.MailService;
import com.flypaas.service.ResourceSideService;
import com.flypaas.service.UserInfoService;
import com.flypaas.util.DateUtil;
import com.flypaas.util.RandomUtil;
import com.flypaas.util.RandomValidateCode;
import com.google.gson.Gson;
/**
 * 注册（验证邮箱，激活邮箱，注册信息，修改状态）
 * @author win10
 *
 */

@Controller
@RequestMapping("/registerController")
public class RegisterController {
	public static Logger logger = Logger.getLogger(RegisterController.class);
	private static Gson gson = new Gson(); 
	@Autowired
	private MailService mailServiceImpl;
	@Autowired
	private UserInfoService userInfoServiceImpl;
	@Autowired
	private ResourceSideService resourceSideServiceImpl;
	
	/**
	 * 发送邮件
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	
	@RequestMapping("/sendValiCode")
	@ResponseBody
	public String sendValiCode(HttpServletRequest request) throws ParseException{
		logger.info("进入发送邮件Controller");
		
		//分配账号表和信息主键,并生成验证码
		RandomValidateCode randomValidateCode = new RandomValidateCode(); 
		String rId = RandomUtil.randomToStr();
		String netId = RandomUtil.randomToStr();
		String code = randomValidateCode.getRandomString(2)+(int)(Math.random()*10000);
		String date = DateUtil.dateToStr(DateUtil.getCurrentDate(), "yyyyMMdd");
		String email = request.getParameter("email").trim();
		
		//初始化资源方信息,并添加分配的(netId,status)
		TbRsAccountInfo tbRsAccountInfo = new TbRsAccountInfo();
		tbRsAccountInfo.setNetSid(netId);
		tbRsAccountInfo.setStatus(FlypaasConstant.status_0);
		tbRsAccountInfo.setEmail(email);
		
		//初始化用户信息，并添加分配的(sId,status,netId,code)
		TbRsUserInfo tbRsUserInfo1 = new TbRsUserInfo();
 		TbRsUserInfo tbRsUserInfo2 = new TbRsUserInfo();
		tbRsUserInfo2.setSid(rId);
		tbRsUserInfo2.setNetSid(netId);
		tbRsUserInfo2.setStatus(FlypaasConstant.status_i0);
		tbRsUserInfo2.setEmail(email);
		tbRsUserInfo2.setValiCode(code);
		
		//用户状态 0:未激活  1:激活状态
		//判断邮箱是否被注册且是激活状态
		tbRsUserInfo1 = userInfoServiceImpl.queryUserInfo2(email);
		if(tbRsUserInfo1 == null){
			logger.info("邮箱还没有被注册...");
			//添加资源方信息  如果添加成功，再添加用户 
			int temp = resourceSideServiceImpl.insertSelective(tbRsAccountInfo);
			if(temp > 0){
				//添加邮箱 (sid,netId,email,status,code)
				int flag = userInfoServiceImpl.insertSelective(tbRsUserInfo2);
				if(flag > 0){
					//添加成功，发送链接进行激活
					mailServiceImpl.sendValidateMail(tbRsUserInfo2.getSid(),tbRsUserInfo2.getNetSid(),tbRsUserInfo2.getValiCode(), date, email);
				}else{
					logger.info("系统出错...");
					return "500";
				}
			}else{
				logger.info("系统出错...");
				return "500";
			}
			//Map<String, Object> model = new HashMap<String, Object>();
			//model.put("message", "激活码已发送至该邮箱,登录邮箱点击激活");
			return gson.toJson(0);
		}
		//如果用户已经存在  且是激活状态  
		if(tbRsUserInfo1 != null && tbRsUserInfo1.getStatus() == 1){
			logger.info("邮箱已经被注册...");
			//Map<String, Object> model = new HashMap<String, Object>();
			//model.put("message", "邮箱已注册,请重新输入");
			//model.put("message", "1");
			//return new ModelAndView("user-register2",model);
			return gson.toJson(1);
		}
		//如果用户已经存在 但是未激活状态  重新发送激活码到用户邮箱
		if(tbRsUserInfo1 != null && tbRsUserInfo1.getStatus() == 0){
			logger.info("以注册过但未激活,激活码已发送至该邮箱,登录邮箱点击激活");
			tbRsUserInfo1.setValiCode(code);
			String date1 = DateUtil.dateToStr(DateUtil.getCurrentDate(), "yyyyMMdd");
			userInfoServiceImpl.updateUserValiCode(tbRsUserInfo1);
			mailServiceImpl.sendValidateMail(tbRsUserInfo1.getSid(),tbRsUserInfo1.getNetSid(),code,date1, email);
			return gson.toJson(2);
		}else{
			logger.info("系统出错...");
			return "500";
		}
		//Map<String, Object> model = new HashMap<String, Object>();
		//model.put("message", "激活码已发送至该邮箱,点击激活");
		//model.put("message", "2");
		//return new ModelAndView("user-register2",model);
	}
	/**
	 * 判断验证码
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	//点击链接进行激活  判断激活码是否正确   时间是否过期     修改用户状态
	@RequestMapping("/activationUser")
	public ModelAndView activationUser(HttpServletRequest request) throws ParseException{
		logger.info("用户点击链接进入激活账号Controller"+request.getParameter("rid"));
		String rid = request.getParameter("rid");
		String date1 = request.getParameter("time");
		String code = request.getParameter("code");
		//String netId = request.getParameter("netId");
		
		//判断时间是否过期
		String date2 = DateUtil.dateToStr(DateUtil.getCurrentDate(), "yyyyMMdd");
//		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
//		Long c = null;
//		try {
//			c = sf.parse(date2).getTime()-sf.parse(date1).getTime();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		//计算验证码发送时间
//		long d = c/1000/60/60/24;//天
		
		long d = Integer.valueOf(date2) - Integer.valueOf(date1);
		//根据sId查询出该用户的激活码
		TbRsUserInfo tbRsUserInfo = userInfoServiceImpl.selectByPrimaryKey(rid);
		String valiCode = tbRsUserInfo.getValiCode();
		
		//判断验证码是否过期和激活码是否正确
		if(d<1 && valiCode == code || valiCode.endsWith(code)){
			/*int flag = userInfoServiceImpl.updateUserStatusBySid(rid);
			if(flag > 0){
				logger.info("激活成功");
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("message", "激活成功");
				return new ModelAndView("/register",model);
			}else{
				logger.info("激活失败");
				return new ModelAndView("/500");
			}
			 */
			logger.info("激活成功,跳到填写信息界面");
			Map<String, Object> model = new HashMap<String, Object>();
			//model.put("message", "激活成功,跳到填写信息界面");
			model.put("sid", tbRsUserInfo.getSid());
			model.put("email", tbRsUserInfo.getEmail());
			model.put("netId", tbRsUserInfo.getNetSid());
			return new ModelAndView("user-register",model);
		}else{
			logger.info("时间过期,请重新激活");
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("message", "时间过期,请重新激活");
			return new ModelAndView("expire",model);
		}
	}
	/**
	 * 注册激活邮箱
	 * 填写用户账号表与资源方信息表信息
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/registerUser")
	@ResponseBody
	public String registerUser(HttpServletRequest request,TbRsUserInfo tbRsUserInfo,TbRsAccountInfo tbRsAccountInfo,TbRsAccountBalance tbRsAccountBalance) throws UnsupportedEncodingException{
		logger.info("用户注册Controller");
		request.setCharacterEncoding("utf-8");
		String sid = request.getParameter("sid");
		String netId = request.getParameter("netId");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String username = request.getParameter("username");
		String realname = request.getParameter("realname");
		String password = request.getParameter("password");
		String alipayAccount = request.getParameter("alipayAccount");
		String alipayName = request.getParameter("alipayName");
		
		Date createDate = new Date();
		//添加资源方信息
		tbRsAccountInfo.setNetSid(netId);
		tbRsAccountInfo.setUsername(username);
		tbRsAccountInfo.setCreateDate(createDate);
		tbRsAccountInfo.setPassword(password);
		tbRsAccountInfo.setEmail(email);
		tbRsAccountInfo.setMobile(phone);
		tbRsAccountInfo.setRank(FlypaasConstant.status_1);
		tbRsAccountInfo.setPoint((long) FlypaasConstant.status_i0);
		tbRsAccountInfo.setStatus(FlypaasConstant.status_1);
		tbRsAccountInfo.setAlipayAccount(alipayAccount);
		tbRsAccountInfo.setAlipayName(alipayName);
		
		//添加用户信息
		tbRsUserInfo.setSid(sid);
		tbRsUserInfo.setUsername(username);
		tbRsUserInfo.setMobile(phone);
		tbRsUserInfo.setEmail(email);
		tbRsUserInfo.setNetSid(netId);
		tbRsUserInfo.setRealname(realname);
		tbRsUserInfo.setPassword(password);
		tbRsUserInfo.setCreateDate(createDate);
		tbRsUserInfo.setLoginTimes(FlypaasConstant.status_i0);
		
		//激活成功后  给用户余额表中添加数据  默认数据
		tbRsAccountBalance.setBalance((double) FlypaasConstant.status_i0);
		tbRsAccountBalance.setCreateTime(createDate);
		tbRsAccountBalance.setEnableFlag(FlypaasConstant.status_1);
		tbRsAccountBalance.setNetSid(netId);
		tbRsAccountBalance.setId((long) RandomUtil.randomToInt());
		tbRsAccountBalance.setCreditBalance((long) FlypaasConstant.status_i0);
		
		
		//添加(修改)用户信息  修改资源方信息   添加资源方账户
		int flag = userInfoServiceImpl.updateByPrimaryKeySelective(tbRsUserInfo,tbRsAccountInfo,tbRsAccountBalance);
		
		//int temp = resourceSideServiceImpl.updateByPrimaryKeySelective(tbRsAccountInfo);
		if(flag > 0){
			logger.info("注册成功");
			return gson.toJson(1);
		}else{
			logger.info("注册失败");
			return gson.toJson(0);
		}
	}
}
