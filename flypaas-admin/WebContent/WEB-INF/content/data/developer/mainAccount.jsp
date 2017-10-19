<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>主账号</title>
</head>

<body>
    <h1><a href="javascript:;" class="back" onclick="history.back()">返 回</a>开发者管理/主账号信息</h1>
    <div class="main_ctn1 main_account">
    <ul class="ul_left">
    <li><label>ACCOUNT SID</label><span>${data.sid}</span></li>
    <li><label>AUTH TOKEN</label><span>${data.token}</span></li>
    <li>
	    <label>Rest URL</label>
	    <span>
	    ${data.rest_ip}<s:if test="data.rest_port!=null && data.rest_port!=''">:${data.rest_port}</s:if>
	    </span>
    </li>
    </ul>
    <ul class="ul_right">
    <li class="tit">变更日志</li>
    <li>
    <table cellpadding="0" cellspacing="0" border="0">
    <tbody>
    	<tr><th>AUTH TOKEN</th><th>变更时间</th></tr>
    	<s:iterator value="data.tokenLog">
		    <tr><td>${token}</td><td>${create_date}</td></tr>
    	</s:iterator>
    </tbody>
    </table>
    </li>
    </ul>
    <div class="clear"></div>
    <hr class="hr" />
    </div>
</body>
</html>