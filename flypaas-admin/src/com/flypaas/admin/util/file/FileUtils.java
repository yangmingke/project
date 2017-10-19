package com.flypaas.admin.util.file;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 文件工具类
 * 
 * @author xiejiaan
 */
public class FileUtils {
	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

	/**
	 * 查看文件
	 * 
	 * @param path
	 *            文件路径
	 */
	public static void view(String path) {
		if(path.endsWith(".amr")){
			StrutsUtils.getResponse().setContentType("audio/amr;charset=utf8;");
		}
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(path);
			out = new BufferedOutputStream(StrutsUtils.getResponse().getOutputStream());
			byte[] buffer = new byte[16 * 1024];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			out.flush();

		} catch (FileNotFoundException e) {
			logger.debug("查看文件【文件不存在】：path=" + path);
		} catch (IOException e) {
			logger.error("查看文件【失败】：path=" + path, e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				logger.error("关闭文件【失败】：path=" + path, e);
			}
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param path
	 *            文件路径
	 */
	public static void download(String path) {
		String fileName = path.substring(path.lastIndexOf("/") + 1);
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(path); // 文件流
			// 设置response的Header
			HttpServletResponse response = StrutsUtils.getResponse();
			response.reset();
			response.setCharacterEncoding("GBK");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String(fileName.getBytes("GBK"), "ISO-8859-1"));
			response.setContentType(FileContentTypes.getContentType(fileName));
			out = new BufferedOutputStream(response.getOutputStream());
			byte[] buffer = new byte[16 * 1024];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			out.flush();
			logger.debug("下载文件【成功】：path=" + path);

		} catch (FileNotFoundException e) {
			logger.debug("下载文件【文件不存在】：path=" + path);
		} catch (Throwable e) {
			logger.error("下载文件【失败】：path=" + path, e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				logger.error("关闭文件【失败】：path=" + path, e);
			}
		}
	}
}
