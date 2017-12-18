package com.yzx.rest.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.StandardServletEnvironment;

import com.yzx.rest.util.DateUtil;

@Service
public class MailService {
	private Logger log = LoggerFactory.getLogger(MailService.class);
	private MimeMessage mimeMessage ;
	@Autowired
	private JavaMailSender mailSender;
	
	/**
	 * 程序发生错误时，发送邮件
	 * @param errorCode 错误码
	 * @param errorContext 错误内容
	 * @param requestPath  rest请求路径
	 * @param requestBody  rest请求body
	 * @param interfaceUrl 访问失败的接口
	 * @param interfaceUrl 请求接口body
	 */
	public void sendErrorEmail(String errorCode, String requestPath, String requestBody, String interfaceUrl, String interfaceBody,String interfaceResult) {
		Properties properties = getProperties();
		log.info("开始发送邮件："+properties.getProperty("mail.to.error"));
		mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			messageHelper.setFrom(properties.getProperty("mail.username"), properties.getProperty("mail.fromnickname.error"));
			messageHelper.setTo(properties.getProperty("mail.to.error"));
			messageHelper.setSubject(properties.getProperty("mail.subject.error"));
			String text=properties.getProperty("mail.text.error");
			text = text.replaceAll("errorCode", errorCode).replaceAll("requestPath", requestPath)
				.replaceAll("requestBody", requestBody).replaceAll("interfaceUrl", interfaceUrl)
				.replaceAll("interfaceBody", interfaceBody).replaceAll("interfaceResult", interfaceResult)
				.replaceAll("time", DateUtil.getTimeStr("yyyy-MM-dd HH:mm:ss"));
			messageHelper.setText(text, true);
			mailSender.send(mimeMessage);
			log.info("发送邮件成功:"+properties.getProperty("mail.to.error")); 
		} catch (MessagingException e) {
			log.error("发送邮件失败:"+properties.getProperty("mail.to.error"));
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			log.error("发送邮件失败:"+properties.getProperty("mail.to.error"));
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据key读取配置文件属性值
	 * @return
	 */
	private Properties getProperties(){
		Properties properties = null;
		InputStream iStr = null;
        try {
        	properties = new Properties();
    		Environment environment = new StandardServletEnvironment();
    		String spring_profiles_active = environment.getProperty("spring.profiles.active");
            iStr=this.getClass().getResourceAsStream("/config/config_"+spring_profiles_active+".properties");
			properties.load(iStr);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				iStr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        return properties;
	}


}
