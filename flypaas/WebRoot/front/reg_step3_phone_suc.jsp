<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>绑定手机成功</title>
<%@ include file="/front/resource.jsp" %>
</head>

<body>
<!--公共头部header bof-->
<%@ include file="/front/head.jsp" %>
<!--公共头部header eof--> 


<!--主体部分content bof-->
<div class="ft_content ft_content_log">
  <div class="ft_content_wp"> 
    
    <!--用户注册 bof--->
    <div class="reg_box">
      <div class="reg_info reg_success phone_success">
        <p class="success">绑定手机成功</p>
        <p>进入<a href="<%=path %>/user/account">管理平台</a></p>
      </div>
    </div>
    <!--用户注册 eof--> 
  </div>  

</div>
<!--主体部分content eof--> 


<!--公共底部footer bof-->
<%@ include file="/front/foot.jsp" %>
<!--公共底部footer bof--> 

<!--
参数说明
_orderno：注册用户ID，需要替换为注册用户的ID值
註冊成功用戶統計
-->
<script>
!function(w,d,e){
var _orderno='${user.sid}'; //替换此处!;
var b=location.href,c=d.referrer,f,s,g=d.cookie,h=g.match(/(^|;)\s*ipycookie=([^;]*)/),i=g.match(/(^|;)\s*ipysession=([^;]*)/);if (w.parent!=w){f=b;b=c;c=f;};u='//stats.ipinyou.com/cvt?a='+e('0N.Tg.3QUTUOO4YSnbrJqTq3JHJ0')+'&c='+e(h?h[2]:'')+'&s='+e(i?i[2].match(/jump\%3D(\d+)/)[1]:'')+'&u='+e(b)+'&r='+e(c)+'&rd='+(new Date()).getTime()+'&OrderNo='+e(_orderno)+'&e=';
function _(){if(!d.body){setTimeout(_(),100);}else{s= d.createElement('script');s.src = u;d.body.insertBefore(s,d.body.firstChild);}}_();
}(window,document,encodeURIComponent);
</script>

</body>
</html>
