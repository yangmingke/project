<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>变更历史</title>
</head>

<body>
    <h1>
    	<a href="javascript:;" class="back" onclick="history.back()">返 回</a>套餐变更日志/变更历史
    </h1>
    <div class="main_ctn1 main_account">
    <ul class="ul_left">
    <li><label>开发者ID</label><span>${data.developer.sid}</span></li>
    <li><label>用户名</label><span>${data.developer.email}</span></li>
    <li><label>钱包余额</label><span>${data.developer.balance}</span></li>
    <li><label>当前套餐</label><span><u:ucparams sqlId="allPackage" key="${data.developer.package_id}" /></span></li>
    <li><label>保底消费（元）</label><span>${data.developer.min_consumption}</span></li>
    </ul>
    <ul class="ul_right">
    <li class="tit">变更历史</li>
    <li>
    <table cellpadding="0" cellspacing="0" border="0">
    <tbody>
    	<tr>
			<th>序号</th>
	    	<th>变更日志id</th>
	    	<th>变更前的套餐</th>
	    	<th>变更后的套餐</th>
	    	<th>状态</th>
	    	<th>类型</th>
	    	<th>创建时间</th>
	    	<th>更新时间</th>
		</tr>
    	<s:iterator value="data.history" status="s">
		    <tr>
				  <td>${s.index+1}</td>
			      <td>${id}</td>
			      <td><u:ucparams sqlId="allPackage" key="${old_package_id}" /></td>
			      <td><u:ucparams sqlId="allPackage" key="${new_package_id}" /></td>
			      <td>${status_name}</td>
			      <td>${type_name}</td>
			      <td>${create_date}</td>
			      <td>${update_date}</td>
			</tr>
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