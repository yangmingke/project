package com.flypaas.service;

public interface MailService {
	
	/**
	 * TODO 发送激活账号的邮件
	 * @author chenxijun
	 * 2014-4-1
	 */
	public void sendValidateMail(String id,String vcode,String vtime,String email);
	/**
	 * TODO 发送重置密码的邮件
	 * @author 29p9g02
	 * 2014-4-14
	 */
	public void sendResetPasswordMail(String email,String vcode);
	/**
	 * TODO 发送重置密码成功提示邮件
	 * @author 29p9g02
	 * 2014-4-14
	 */
	public void sendResetPasswordMailSuc(String email);
	/**
	 * TODO 发送邮件
	 * @author chenxijun
	 * 2014-4-1
	 */
	public void sendLogSucMail(String email);
	/**
	 * TODO	发送带有附件的邮件
	 * @author chenxijun
	 * 2014-4-1
	 */
	public void sendMailAttachment();
	

	/**发送修改密码的邮件
	 * @param sid
	 */
	public String sendUpdatePasswordMail(String sid);
	
}
