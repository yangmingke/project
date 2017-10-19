package com.flypaas.admin.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.oscache.util.StringUtil;

/**
 * 字符串工具类
 * 
 * @author xiejiaan
 */
public class StrUtils {

	/**
	 * 替换字符串中的占位符，占位符的正则为\[@\w+@\]
	 * 
	 * @param data
	 *            处理的字符串
	 * @param params
	 *            替换的参数
	 * @return
	 */
	public static String replacePlaceholder(String data, Map<String, Object> params) {
		Pattern p = Pattern.compile("\\[@\\w+@\\]");
		Matcher m = p.matcher(data);
		String key;
		Object value;
		while (m.find()) {
			key = m.group();
			value = params.get(key.substring(2, key.length() - 2));
			if (value != null) {
				data = data.replace(key, value.toString());
			}
		}
		return data;
	}
	
	public static String getUUID() {  
        UUID uuid = UUID.randomUUID();  
        String str = uuid.toString();  
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);  
        return temp;  
    } 
	
	public static void writeMsg(HttpServletResponse response,HttpServletRequest request, String msg,String url){
		if(StringUtil.isEmpty(url)){
			url = "history.go(-1)";
		}else{
			url = "location.href=\""+url+"\"" ;
		}
		StringBuilder sb = new StringBuilder();
		String host = request.getLocalAddr() + ":" +request.getLocalPort();
		sb.append("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>");
		sb.append("<html xmlns='http://www.w3.org/1999/xhtml'>");
		sb.append("<head>");
		sb.append("<title>提示</title>");
		sb.append("<link rel='stylesheet' type='text/css' href='http://"+host+"/page/css/style.css' />");
		sb.append("<link rel='stylesheet' type='text/css' href='http://"+host+"/page/css/form.css' />");
		sb.append("</head>");
		sb.append("<body style='padding-left: 30%;padding-top: 10%;background:none;'>");
		sb.append("<div class='background_box' style='display:block'>&nbsp;</div>");
		sb.append("<div class='float_box addnum_box'  style='display:block'>"+
		 " <div class='float_tit'>"+
		   " <h3>&nbsp;提示</h3>"+
		 " </div>"+
		 " <div class='float_ctn'>"+
		   " <div  class='float_field' style='font-size: 14px;color: #4c4c4c;'>"+msg+"!</div>"+
		   "<div class='float_btn'>"+
		      "<input type='button' class='confirm_btn' value='确 定' onclick='"+url+"'>"+
		   " </div>"+
		 " </div>"+
		"</div>");
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
