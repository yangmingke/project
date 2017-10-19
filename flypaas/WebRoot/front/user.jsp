<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<% com.flypaas.entity.TbFlypaasUser u =(com.flypaas.entity.TbFlypaasUser)session.getAttribute("user");
response.setContentType("text/javascript;charset=UTF-8");
if(null != u){
	out.print("jQuery(\"div.top_right\").html('<div class=\"top_link\">"+u.getEmail()+"<span>|</span><a href=\"'+main_host+'/user/logout\" id=\"logout\">退出</a></div><div class=\"log_link\" style=\"margin-top:52px;\"><a href=\"'+main_host+'/user/account\" title=\"管理中心\" class=\"admin_center\" style=\"padding:10px 20px;\">管理中心</a></div>');");
}
%>