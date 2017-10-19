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
<title></title>
</head>
<body>
 <s:form namespace="/pay" action="toPay" theme="simple" method="post" id="toPay" name="toPay"> 
 <input type="hidden" id="orderId" name="orderId" value="<%=request.getParameter("orderId")%>"/>
 <input type="hidden" id="vpayType" name="chargeType" value="<%=request.getParameter("chargeType")%>"/>
 </s:form>
<script type="text/javascript">
 document.toPay.submit();
</script>
</body>
</html>