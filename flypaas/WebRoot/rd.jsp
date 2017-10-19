<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% Object object = com.flypaas.utils.UcCenterKit.getScript(request);
	if(null != object){
		out.println((String)object);
	}
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>正在进入</title>
 <meta http-equiv="refresh" content="2;URL=${param.fr}">
</head>
<body>
正在进入...
</body>
</html>