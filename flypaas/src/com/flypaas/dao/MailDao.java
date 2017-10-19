package com.flypaas.dao;

import com.flypaas.entity.MailProp;

/** 
* TODO 邮件接口
* @author 29p9g02
* @version 
*/
public interface MailDao {

	/**
	 * TODO 根据模板类型获取邮件模板
	 * @author 29p9g02
	 * 2014-4-8
	 */
	public MailProp getMailModelByType(String type);
}
