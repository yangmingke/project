package com.flypaas.admin.service.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.flypaas.admin.constant.LogConstant.LogType;
import com.flypaas.admin.constant.MsgConstant.TemplateId;
import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.service.LogService;

/**
 * 信息管理-管理员中心-邮件管理
 * 
 * @author xiejiaan
 */
@Service
public class EmailServiceImpl implements EmailService {
	private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
	@Autowired
	private MasterDao dao;
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private MsgService msgService;
	@Autowired
	private LogService logService;
	/**
	 * 发送者
	 */
	private static final String from = "no-reply@flypaas.com";
	/**
	 * 是否正在发送邮件
	 */
	private static boolean isSend = false;

	@Override
	public boolean sendTextEmail(String to, String subject, String body) {
		logger.debug("发送文本格式的Email【开始】：to={}, subject={}, body={}", to, subject, body);
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom(from);
			msg.setTo(to.split(","));
			msg.setSubject(subject);
			msg.setText(body);
			javaMailSender.send(msg);

			logger.debug("发送文本格式的Email【成功】：to={}, subject={}, body={}", to, subject, body);
			return true;
		} catch (Throwable e) {
			logger.error("发送文本格式的Email【失败】：to=" + to + ", subject=" + subject + ", body=" + body, e);
		}
		return false;
	}

	@Override
	public boolean sendHtmlEmail(String to, String subject, String body) {
		logger.debug("发送html格式的Email【开始】：to={}, subject={}, body={}", to, subject, body);
		try {
			MimeMessage msg = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, false, "utf-8");
			helper.setFrom(from);
			helper.setTo(to.split(","));
			helper.setSubject(subject);
			helper.setText(body, true);
			javaMailSender.send(msg);

			logger.debug("发送html格式的Email【成功】：to={}, subject={}, body={}", to, subject, body);
			return true;
		} catch (Throwable e) {
			logger.error("发送html格式的Email【失败】：to=" + to + ", subject=" + subject + ", body=" + body, e);
		}
		return false;
	}

	@Override
	public boolean sendTemplateEmail(String to, TemplateId templateId, Map<String, Object> templateParams) {
		Map<String, String> template = msgService.getTemplate(templateId, templateParams);
		return sendHtmlEmail(to, template.get("title"), template.get("desc"));
	}

	@Override
	public PageContainer query(Map<String, String> params) {
		return dao.getSearchPage("email.query", "email.queryCount", params);
	}

	@Override
	public Map<String, Object> getTemplate() {
		return dao.getOneInfo("email.getTemplate", null);
	}

	@Override
	public Map<String, Object> save(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		if (isSend) {
			data.put("result", "fail");
			data.put("msg", "邮件还没有发送完，请勿再次发送");
			return data;
		}
		isSend = true;
		dao.update("email.updateTemplate", params);// 更新邮件模板
		if (params.get("type").equals("1")) {
			dao.delete("email.deleteLog", null);// 删除老的日志
		}

		Map<String, Object> sqlParams = new HashMap<String, Object>();
		sqlParams.putAll(params);
		if ("0".equals(params.get("developer"))) {
			sqlParams.put("sidArray", params.get("sid").split(","));
		}
		Map<String, Object> template = dao.getOneInfo("email.getTemplate", null);// 获取邮件模板
		List<Map<String, Object>> userList = dao.getSearchList("email.getUserInfo", sqlParams);// 获取用户信息

		EmailThread emailThread = new EmailThread(template, userList);
		emailThread.start();

		logService.add(LogType.add, "信息管理-管理员中心-邮件管理：保存邮件", params);
		data.put("result", "success");
		data.put("msg", "正在发送，请返回并查询发送结果");
		return data;
	}

	/**
	 * 发送邮件线程
	 * 
	 * @author xiejiaan
	 */
	class EmailThread extends Thread {
		private Logger logger = LoggerFactory.getLogger(EmailThread.class);
		private Map<String, Object> template;
		private List<Map<String, Object>> userList;
		/**
		 * 批处理大小
		 */
		private static final int batch = 10;
		/**
		 * 处理完一批后等待时间（毫秒）
		 */
		private static final int sleep = 3 * 1000;

		public EmailThread(Map<String, Object> template, List<Map<String, Object>> userList) {
			this.template = template;
			this.userList = userList;
		}

		@Override
		public void run() {
			String template_id = template.get("id").toString();
			String from = template.get("frm").toString();
			String fromnickname = template.get("fromnickname").toString();
			String subject = template.get("subject").toString();
			String text = template.get("text").toString();
			try {
				logger.debug("发送邮件线程开始：template_id={}, userList.size={}", template_id, userList.size());
				int page = batch;

				for (int i = 0; i < userList.size(); i++) {
					if (i >= page) {
						page += batch;
						try {
							Thread.sleep(sleep);
						} catch (InterruptedException e) {
							logger.error("发送邮件线程：睡眠失败", e);
						}
					}
					Map<String, Object> user = userList.get(i);
					String sid = user.get("sid").toString();
					String email = user.get("email").toString();
					String username = Objects.toString(user.get("username"), "");

					int status = 0;
					String remark = null;
					String desc = text.replace("[@username@]", username);
					try {
						logger.debug("发送邮件开始：i=" + i + ", sid=" + sid + ", email=" + email);

						MimeMessage msg = javaMailSender.createMimeMessage();
						MimeMessageHelper helper = new MimeMessageHelper(msg, false, "utf-8");
						helper.setFrom(from, fromnickname);
						helper.setTo(email);
						helper.setSubject(subject);
						helper.setText(desc, true);
						javaMailSender.send(msg);

						status = 1;
						logger.debug("发送邮件成功：i=" + i + ", sid=" + sid + ", email=" + email);
					} catch (Throwable e) {
						logger.error("发送邮件失败：i=" + i + ", sid=" + sid + ", email=" + email, e);
						remark = e.getMessage();
					} finally {
						Map<String, Object> emailLog = new HashMap<String, Object>();
						emailLog.put("template_id", template_id);
						emailLog.put("sid", sid);
						emailLog.put("from", from);
						emailLog.put("to", email);
						emailLog.put("title", subject);
						emailLog.put("desc", desc);
						emailLog.put("remark", remark);
						emailLog.put("status", status);
						dao.insert("email.addLog", emailLog);// 添加邮件日志
					}
				}

			} finally {
				logger.debug("发送邮件线程结束：template_id={}, userList.size={}", template_id, userList.size());
				isSend = false;
			}
		}
	}

}
