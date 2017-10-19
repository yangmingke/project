package com.flypaas.admin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.ueditor.ActionEnter;
import com.flypaas.admin.util.ConfigUtils;

/**
 * 处理UEditor请求
 * 
 * @author xiejiaan
 */
public class UEditorServlet extends HttpServlet {
	private static final long serialVersionUID = -6060069359013255785L;
	private static final Logger logger = LoggerFactory.getLogger(UEditorServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setHeader("Content-Type", "text/html");

		String data = new ActionEnter(req, ConfigUtils.ueditor_config_file_path).exec();
		logger.debug("UEditor请求结果：data=" + data);

		PrintWriter out = resp.getWriter();
		out.write(data);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
