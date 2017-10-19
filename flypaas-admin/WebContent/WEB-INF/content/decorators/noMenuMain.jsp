<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><decorator:title /></title>
	<cache:cache key="meta_jsp" time="-1">
		<%@ include file="/common/meta.jsp"%>
	</cache:cache>
	
	<decorator:head />
</head>

<body>
	<decorator:body />
</body>
</html>