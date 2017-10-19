package com.flypaas.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flypaas.constant.SysConstant;


public class StrUtil {

	public static boolean isEmpty(Object o){
		if(o instanceof String){
			if(o==null || o.toString().trim().equals("")){
				return true;
			}
		}else{
			if(o==null){
				return true;
			}
		}
		return false;
	}
	
	public static String[] split(String str){
		String [] strArray = null ;
		if(str.contains(",") ){
			strArray = str.split(",");
			return strArray;
		}
		return new String[]{str} ;
	}
	public static String getUUID() {  
        UUID uuid = UUID.randomUUID();  
        String str = uuid.toString();  
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);  
        return temp;  
    }  
    //获得指定数量的UUID  
    public static String[] getUUID(int number) {  
        if (number < 1) {  
            return null;  
        }  
        String[] ss = new String[number];  
        for (int i = 0; i < number; i++) {  
            ss[i] = getUUID();  
        }  
        return ss;  
    }
    //获取4位随机数
    public static int getRandom4(){
    	return (int) (Math.random()*9000+1000);
    }
	
	public static void writeMsg(HttpServletResponse response, String msg,String url){
		if(StrUtil.isEmpty(url)){
			url = "history.go(-1)";
		}else{
			url = "location.href=\""+url+"\"" ;
		}
		StringBuilder sb = new StringBuilder();
		String host = SysConfig.getInstance().getProperty("host");
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
	//验证手机是否包含js
	public static boolean checkJsForStr(String inputStr){
		final String regex = "[\\s\\S]*<[\\s\\S]*>[\\s\\S]*"; 
		return check(regex, inputStr);
	}
	//验证邮箱
	public static boolean checkEmailForStr(String inputStr){
		String regex ="^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))$";
		return check(regex, inputStr);
	}
	//验证用户名
	public static boolean checkUserName(String inputStr){
		String regex ="^[a-zA-Z0-9]{4,15}$";
		return check(regex, inputStr);
	}
	//验证座机和手机
	public static boolean checkPhoneForStr(String inputStr){
		String regex ="(^1[3|4|5|7|8]\\d{9}$)|(^0\\d{2,3}\\d{8}$)";
		return check(regex, inputStr);
	}
	//验证座机,手机，400
	public static boolean check400PhoneForStr(String inputStr){
		String regex ="(^1[3|4|5|7|8]\\d{9}$)|(^0\\d{2,3}\\d{8}$)|(^400\\d{7}$)";
		return check(regex, inputStr);
	}
	//验证400号码和座机
	public static boolean check400AndFixPhone(String inputStr){
		String regex="(^400\\d{7}$)|(^0\\d{2,3}\\d{8}$)";
		return check(regex, inputStr);
	}
	//验证组织机构号
	public static boolean checkOrgId(String inputStr){
		String regex ="^[a-zA-Z0-9]{8}-[a-zA-Z0-9]$";
		return check(regex, inputStr);
	}
	//验证税务登记号
	public static boolean checkIttsId(String inputStr){
		String regex ="^[a-zA-Z0-9]{15,20}$";
		return check(regex, inputStr);
	}
	//营业执照
	public static boolean checkBsNum(String inputStr){
		String regex ="^[a-zA-Z0-9]{15}$";
		return check(regex, inputStr);
	}
	//验证银行卡号
	public static boolean checkBankNum(String inputStr){
		String regex ="^[0-9]+$";
		return check(regex, inputStr);
	}
	//验证邮编
	public static boolean checkPostNum(String inputStr){
		String regex ="^[0-9][0-9]{5,6}$";
		return check(regex, inputStr);
	}
	//验证是否包含特殊符号
	public static boolean constainsSymbol(String inputStr){
		if(isEmpty(inputStr)){
			return false;
		}
		String [] a = new String []{"@","#","￥","$","%","<", ">","/","!","~","`","^","&","*","(",")","=","+","{","}","?","《","》",",",".","。",":",";"," "};
	    for (int i=0; i < a.length; i++) {
	        if (inputStr.indexOf(a[i]) >= 0) { 
	            return true; 
	        }
	    }
	    return false;
	}
	//验证纳税人识别号
	public static boolean checkIdentification(String inputStr){
		String regex="(\\d{15})|(\\d{17})|(\\d{18})|(\\d{20})";
		return check(regex, inputStr);
	}
	//验证金额
	public static boolean checkMoney(String inputStr){
		String regex="^[1-9]\\d*$";
		return check(regex, inputStr);
	}
	private static boolean check(String regex,String inputStr){
		if(isEmpty(inputStr)){
			return false;
		}
		boolean boo = Pattern.matches(regex, inputStr);
		if(boo){
			return true;
		}
		return false;
	}
	public static boolean checkPwd(String inputStr){
		if(inputStr==null){
			return false;
		}
		String regex="^([A-Za-z]+\\d+|\\d+[A-Za-z]+)[A-Za-z\\d]*$";
		boolean boo = Pattern.matches(regex, inputStr);
		if(boo){
			return true;
		}
		return false;
	}
	
	public static String replaceUeditorImageStr(String target){
		String regex = SysConstant.UEDITOR_ICO_PREFIX;
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(target);
		while(m.find()){
			String all=m.group(0);
			String url=m.group(2);
			target = target.replace(all, SysConstant.UCP_UEICO_PREFIX+url+"\"");
		}
		return target;
	}
	
	//加密sid token
	public static String toHMACSHA(String baseStr,String key){
		String temp=null;
		try {
			temp = HMACSHAUtil.getSignature(baseStr,key);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return temp ;
	}
	public static String checkStrArray(String...str){
		String msg  = null ;
		for(String s : str){
			if(StrUtil.isEmpty(s)){
				msg = "false" ;
				break;
			}
		}
		return msg ;
	}
	public static String random8(){
		long a = (long)(Math.random()*90000000+10000000);
		return a+"" ;
	}
	public static boolean validationBracket(String str){
		String [] a = {"【","】","[","]"};
		boolean boo = false;
		if(StrUtil.isEmpty(str)){
			return boo;
		}
	    for (int i=0; i < a.length; i++) {
	        if (str.indexOf(a[i]) >= 0) {
	        	boo = true;
	        	break;
	        }
	    }
	    return boo;
	}
	public static String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (ip == null || ip.equals("")) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	public static String getStringNoEnter(String str,String ff) {
        if(str!=null && !"".equals(str)) {      
            Pattern p = Pattern.compile("\\r|\\n");      
            Matcher m = p.matcher(str);      
            String strNoBlank = m.replaceAll(ff);
            String ss = strNoBlank.replaceAll(ff+ff,ff);
            return ss;      
        }else {      
            return str;      
        }           
    }
	
	public static String getPoint4(String str){
		String s[] = str.split("\\,");
		if(s.length>5){
			String a="";
			for(int i=0;i<s.length;i++){
				if(i%5==0&&i!=0)a=a+"<br>";
				a = a+s[i]+",";
			}
			return a;
		}else return str;
	}
}
