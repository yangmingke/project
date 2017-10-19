package com.flypaas.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.constant.SysConstant;
import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.MailProp;
import com.flypaas.entity.TbFlypaasUser;
import com.flypaas.service.MailService;
import com.flypaas.service.OtherService;
import com.flypaas.service.UserService;
import com.flypaas.utils.DateUtil;
import com.flypaas.utils.MD5Util;
import com.flypaas.utils.StrUtil;
import com.flypaas.utils.SysConfig;

@Service
@Transactional
public class MailServiceImpl extends DaoCenter implements MailService {
	@Autowired
	protected UserService userService;
	@Autowired
	protected OtherService otherService;
	
	private MimeMessage mimeMessage ;
	
	private Logger log = LoggerFactory.getLogger(MailServiceImpl.class);
	/**
	 * 是否正在发送邮件
	 */
	private static boolean isSend = false;
	
	private synchronized MimeMessage getMimeMessage(){
		mimeMessage = mailSender.createMimeMessage();
		return mimeMessage;
	}
	private MailProp getMailByType(String type){
		MailProp p = mailDao.getMailModelByType(type);
		return p ;
	}
	private Map<String, Object> getRodamCuctomerManager(){
		return otherDao.getRodamCuctomerManager();
	}
	public void sendValidateMail(String id,String vcode,String vtime,String email) {
		log.info("开始发送邮件："+email);
		MailProp p = getMailByType(SysConstant.V_MIAL_TYPE);
		mimeMessage = getMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, SysConstant.CHARSET);
			messageHelper.setFrom(p.getFrm(), p.getFromNickname());
			messageHelper.setTo(email);
			messageHelper.setSubject(p.getSubject());
			String text = p.getText();
			text = text.replaceAll("vhost",  SysConfig.getInstance().getProperty("host")).replaceAll("vsid",id).replaceAll("vvcode", vcode).replaceAll("vtime", DateUtil.dateToStr(DateUtil.getCurrentDate(), "yyyy年MM月dd"));
			messageHelper.setText(text, true);
			mailSender.send(mimeMessage);
			log.info("发送邮件成功:"+email);
		} catch (MessagingException e) {
			log.error("发送邮件失败:"+email);
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			log.error("发送邮件失败:"+email);
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	public void sendLogSucMail(String email) {
		log.info("开始发送邮件:"+email);
		MailProp p = getMailByType(SysConstant.LOGSUC_MIAL_TYPE);
		mimeMessage = getMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, SysConstant.CHARSET);
			messageHelper.setFrom(p.getFrm(), p.getFromNickname());
			messageHelper.setTo(email);
			messageHelper.setSubject(p.getSubject());
			String text = p.getText();
			Map<String, Object> map = getRodamCuctomerManager();
			String name = map.get("name").toString();
			String phone = map.get("phone").toString();
			String qq = map.get("qq").toString();
			String Cemail = map.get("email").toString();
			String bcc = p.getBcc();
			if(bcc!=null){
				bcc = bcc+","+Cemail;
				String [] bccA = StrUtil.split(bcc);
				messageHelper.setBcc(bccA);
			}
			text = text.replaceAll("vEmail",  email).replaceAll("vName",name).replaceAll("vPhone", phone).replaceAll("vQQ", qq).replaceAll("vCemail", Cemail);
			messageHelper.setText(text, true);
			mailSender.send(mimeMessage);
			log.info("发送邮件成功:"+email);
		} catch (MessagingException e) {
			log.error("发送邮件失败:"+email);
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			log.error("发送邮件失败:"+email);
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	public void sendMailAttachment() {
		log.info("开始发送邮件：");
		MailProp p = getMailByType(SysConstant.LOGSUC_MIAL_TYPE);
		mimeMessage = getMimeMessage();
		String toto = p.getToNbr();
		String [] toA = StrUtil.split(toto);
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, SysConstant.CHARSET);
			messageHelper.setFrom(p.getFrm(), p.getFromNickname());
			messageHelper.setTo(toA);
			messageHelper.setSubject(p.getSubject());
			messageHelper.setText(p.getText(), true);
			
			String cc = p.getCc() ;
			if(cc!=null){
				String [] ccA = StrUtil.split(cc);
				messageHelper.setCc(ccA);
			}
			String bcc = p.getBcc();
			if(bcc!=null){
				String [] bccA = StrUtil.split(bcc);
				messageHelper.setBcc(bccA);
			}
			String att = p.getAttUrl();
			if(att!=null){
				File file = new File(att);
				messageHelper.addAttachment(MimeUtility.encodeWord(file.getName()), file);
			}

			mailSender.send(mimeMessage);
			log.info("发送成功：");
			log.info("发送成功：");
		} catch (MessagingException e) {
			log.error("发送失败：");
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			log.error("发送失败：");
			log.error(e.getMessage());
			e.printStackTrace();
		}
		
	}
	public void sendResetPasswordMail(String email, String vcode) {
		log.info("开始发送邮件："+email);
		MailProp p = getMailByType(SysConstant.RESETPASSWORD_MIAL_TYPE);
		mimeMessage = getMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, SysConstant.CHARSET);
			messageHelper.setFrom(p.getFrm(), p.getFromNickname());
			messageHelper.setTo(email);
			messageHelper.setSubject(p.getSubject());
			String text = p.getText();
			text = text.replaceAll("rhost",  SysConfig.getInstance().getProperty("host")).replaceAll("remail",email).replaceAll("rvcode", vcode).replaceAll("rtime", DateUtil.dateToStr(DateUtil.getCurrentDate(), "yyyy年MM月dd"));
			messageHelper.setText(text, true);
			mailSender.send(mimeMessage);
			log.info("发送邮箱成功："+email+";重置密码vcode："+vcode);
		} catch (MessagingException e) {
			log.error("发送邮件失败："+email);
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			log.error("发送邮件失败："+email);
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void sendResetPasswordMailSuc(String email) {
		log.info("开始发送邮件");
		MailProp p = getMailByType(SysConstant.RESETPASSWORD_SUC_MIAL_TYPE);
		mimeMessage = getMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, SysConstant.CHARSET);
			messageHelper.setFrom(p.getFrm(), p.getFromNickname());
			messageHelper.setTo(email);
			messageHelper.setSubject(p.getSubject());
			Date date = DateUtil.getCurrentDate();
			String ymd = DateUtil.dateToStr(date, DateUtil.DATE_TIME_LINE);
			String y = DateUtil.dateToStr(date, DateUtil.YMR_SLASH);
			String text = p.getText();
			text = text.replaceAll("vymd",  ymd).replaceAll("vy",  y);
			messageHelper.setText(text, true);
			mailSender.send(mimeMessage);
			log.info("发送重置密码成功提示邮箱地址："+email);
		} catch (MessagingException e) {
			log.error("发送邮件失败："+email);
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			log.error("发送邮件失败："+email);
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	private void newOrUpdateAuthFile(String sid,String vcode,long currentTime){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sid", sid);
		map.put("vcode", vcode);
		map.put("timestamp", currentTime);
		Object obj = otherService.getAuthFile(sid);
		if(StrUtil.isEmpty(obj)){
			otherService.addAuthFile(map);
		}else{
			otherService.updateAuthFile(map);
		}
	}
	public String sendUpdatePasswordMail(String sid) {
		if (isSend) {
			return "已经在发送邮件，请勿重复提交";
		}
		isSend = true;
		MailProp p = getMailByType(SysConstant.UPDATE_PASSWORD_MIAL_TYPE);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sid", sid);
		params.put("template_id", p.getId());
		List<TbFlypaasUser> userList = userService.findeUpdatePasswordUser(params);

		MailThread mailThread = new MailThread(p, userList);
		mailThread.start();
		return "正在发送，请稍后";
	}

	/**
	 * 发送邮件线程
	 * 
	 * @author xiejiaan
	 */
	class MailThread extends Thread {
		private Logger logger = LoggerFactory.getLogger(MailThread.class);
		private MailProp p;
		private List<TbFlypaasUser> userList;
		/**
		 * 批处理大小
		 */
		private static final int batch = 10;
		/**
		 * 处理完一批后等待时间
		 */
		private static final int sleep = 3 * 1000;

		public MailThread(MailProp p, List<TbFlypaasUser> userList) {
			this.p = p;
			this.userList = userList;
		}

		@Override
		public void run() {
			try {
				logger.debug("发送邮件线程开始：template_id={}, userList.size={}", p.getId(), userList.size());
				int page = batch;
				String rhost = SysConfig.getInstance().getProperty("host");
				String rtime = DateUtil.dateToStr(DateUtil.getCurrentDate(), "yyyy-MM-dd");

				for (int i = 0; i < userList.size(); i++) {
					if (i >= page) {
						page += batch;
						try {
							Thread.sleep(sleep);
						} catch (InterruptedException e) {
							log.error("发送邮件线程：睡眠失败", e);
						}
					}
					TbFlypaasUser user = userList.get(i);

					String sid = user.getSid();
					String email = user.getEmail();
					String text = null;
					String remark = null;
					int status = 0;

					try {
						// 更新本地验证文件
						long currentTime = System.currentTimeMillis();
						String vcode = MD5Util.GetMD5Code(UUID.randomUUID().toString());
						newOrUpdateAuthFile(sid, vcode, currentTime);

						log.debug("发送邮件开始：i="+i+", sid=" + sid + ", email=" + email + ", 验证code:" + vcode + ", 发送时间："
								+ currentTime);
						mimeMessage = getMimeMessage();
						MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, SysConstant.CHARSET);
						messageHelper.setFrom(p.getFrm(), p.getFromNickname());
						messageHelper.setTo(email);
						messageHelper.setSubject(p.getSubject());
						text = p.getText().replaceAll("rhost", rhost).replaceAll("remail", email)
								.replaceAll("rvcode", vcode).replaceAll("rtime", rtime);
						messageHelper.setText(text, true);
						mailSender.send(mimeMessage);

						status = 1;
						log.debug("发送邮件成功：i="+i+", sid=" + sid + ", email=" + email + ", vcode=" + vcode);
					} catch (Throwable e) {
						log.error("发送邮件失败：i="+i+", sid=" + sid + ", email=" + email, e);
						remark = e.getMessage();
					} finally {
						Map<String, Object> mailLog = new HashMap<String, Object>();
						mailLog.put("template_id", p.getId());
						mailLog.put("sid", sid);
						mailLog.put("from", p.getFrm());
						mailLog.put("to", email);
						mailLog.put("title", p.getSubject());
						mailLog.put("desc", text);
						mailLog.put("remark", remark);
						mailLog.put("status", status);
						userService.deleteMailLog(mailLog);
						userService.addMailLog(mailLog);// 添加邮件日志
					}
				}

			} finally {
				logger.debug("发送邮件线程结束：template_id={}, userList.size={}", p.getId(), userList.size());
				isSend = false;
			}
		}
	}


}
