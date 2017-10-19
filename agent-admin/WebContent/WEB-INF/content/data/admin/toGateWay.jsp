<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<form name="f" action='${data.commitUrl}'>
	<input type="hidden" value="${data.payAmount}" name="payAmount"/>
	<input type="hidden" value="${data.merData}" name="merData"/>
	<input type="hidden" value="${data.productDesc}" name="productDesc"/>
</form>
<script type="text/javascript">
 document.f.submit();
</script>
</body>
</html>