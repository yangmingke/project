package com.flypaas.admin.action;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.util.encrypt.EncryptUtils;
import com.flypaas.admin.util.file.FileUtils;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 文件操作
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
public class FileAction extends BaseAction {
	private static final long serialVersionUID = -8191017938135207055L;
	private static final Logger logger = LoggerFactory.getLogger(FileAction.class);

	/**
	 * 查看文件
	 * 
	 * @return
	 */
	@Action("/file/view")
	public void view() {
		String path = getPath();
		if (StringUtils.isNotBlank(path)) {
			FileUtils.view(path);
		}
	}

	/**
	 * 下载文件
	 * 
	 * @return
	 */
	@Action("/file/download")
	public void download() {
		String path = getPath();
		if (StringUtils.isNotBlank(path)) {
			FileUtils.download(path);
		}
	}

	/**
	 * 获取文件路径
	 * 
	 * @return
	 */
	private String getPath() {
		String path = StrutsUtils.getParameterTrim("path");// 原始路径
		if (StringUtils.isNotBlank(path)) {
			try {
				path = new String(path.getBytes("iso-8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("文件路径编码转换失败：path=" + path, e);
			}
		} else {
			String encodePath = StrutsUtils.getParameterTrim("encode_path");// des3加密路径
			if (StringUtils.isNotBlank(encodePath)) {
				path = EncryptUtils.decodeDes3(encodePath);
			}
		}
		return path;
	}

}
