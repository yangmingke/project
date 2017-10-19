<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
    <%
    	String checkcode=request.getParameter("checkCode");
    	String aa = (String)session.getAttribute("randCheckCode");
    		if(!checkcode.equalsIgnoreCase(aa)){
    			out.print("2");
    		}else{
    			out.print("1");
    		}
 %>
