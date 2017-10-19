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
<form name="f" action='${gatewayVo.commitUrl}'>
	<input type="hidden" value="${gatewayVo.merId}" name="gatewayVo.merId"/>
	<input type="hidden" value="${gatewayVo.orderId}" name="gatewayVo.orderId"/>
	<input type="hidden" value="${gatewayVo.payType}" name="gatewayVo.payType"/>
	<input type="hidden" value="${gatewayVo.bankId}" name="gatewayVo.bankId">
	<input type="hidden" value="${gatewayVo.payAmount}" name="gatewayVo.payAmount"/>
	<input type="hidden" value="${gatewayVo.payTime}" name="gatewayVo.payTime"/>
	<input type="hidden" value="${gatewayVo.notifyUrl}" name="gatewayVo.notifyUrl"/>
	<input type="hidden" value="${gatewayVo.merData}" name="gatewayVo.merData"/>
	<input type="hidden" value="${gatewayVo.cardId}" name="gatewayVo.cardId"/>
	<input type="hidden" value="${gatewayVo.cardPassword}" name="gatewayVo.cardPassword"/>
	<input type="hidden" value="${gatewayVo.cardAmount}" name="gatewayVo.cardAmount"/>
	<input type="hidden" value="${gatewayVo.userId}" name="gatewayVo.userId"/>
	<input type="hidden" value="${gatewayVo.productId}" name="gatewayVo.productId"/>
	<input type="hidden" value="${gatewayVo.productDesc}" name="gatewayVo.productDesc"/>
	<input type="hidden" value="${gatewayVo.sign}" name="gatewayVo.sign">
</form>
<script type="text/javascript">
 document.f.submit();
</script>
</body>
</html>