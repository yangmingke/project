<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
<script type="text/javascript" src="http://libs.useso.com/js/respond.js/1.4.2/respond.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/css3pie/2.0beta1/PIE_IE678.js"></script>
<![endif]-->
<link type="text/css" rel="stylesheet" href="${ctx}/css/H-ui.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/css/H-ui.admin.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/font/font-awesome.min.css"/>
<!--[if IE 7]>
<link href="http://www.bootcss.com/p/font-awesome/assets/css/font-awesome-ie7.min.css" rel="stylesheet" type="text/css" />
<![endif]-->
<title>余额显示界面</title>
<style type="text/css">
	tr{
		line-height: 30px;
	}
</style>
</head>
<body>
<nav class="Hui-breadcrumb"><i class="icon-home"></i> 首页 <span class="c-gray en">&gt;</span> 账务管理<span class="c-gray en">&gt;</span> 余额管理<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="icon-refresh"></i>刷新</a></nav>
<div class="codeView docs-example">
    <table class="table table-border table-bordered table-striped" style="font-size: 14px;">
		<tbody>
			<tr >
				<td colspan="2">资源方余额账单>></td>
			</tr>
			<tr>
				<td width="10%">用户名称:</td>
				<td >${account.username}</td>
			</tr>
			<tr>
				<td>账户状态:</td>
				<c:if test="${accountBalance.enableFlag == 1}">
					<td ><font color="green">正常</font></td>
				</c:if>
				<c:if test="${accountBalance.enableFlag == 0}">
					<td ><font color="red">冻结</font></td>
				</c:if>
			</tr>
			<tr>
				<td>创建日期:</td>
				<td ><fmt:formatDate value="${accountBalance.createTime}" pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<td>更新日期:</td>
				<td ><fmt:formatDate value="${accountBalance.updateDate}" pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<td>当前余额(元 ):</td>
				<td >${balanceShow}</td>
			</tr>
			<tr>
				<td>收款账号（支付宝）:</td>
				<td >${account.alipayAccount}</td>
			</tr>
			<tr>
				<td>收款人（支付宝）:</td>
				<td >${account.alipayName}</td>
			</tr>
			<tr>
				<td>操作:</td>
				<td >
					<c:if test="${accountBalance.enableFlag == 0}">
						<a style="text-decoration:none" onclick="alert('钱包为冻结状态，不能进行“提款”');"title="填写提款申请订单" href="javascript:;"  class="ml-5" ><button  class="btn btn-primary size-XL radius" id="getBill">提款</button></a> 
					</c:if>
					<c:if test="${accountBalance.enableFlag == 1}">
						<a style="text-decoration:none" onclick="payOrder_show('650','550','填写提款申请订单','${ctx}/accountBalanceController/createOrder','${accountBalance.balance}')"title="填写提款申请订单" href="javascript:;"  class="ml-5" ><button  class="btn btn-primary size-XL radius" id="getBill">提款</button></a> 
					</c:if>
				</td>
			</tr>
		</tbody>
	</table>
   </div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${ctx}/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${ctx}/layer/layer.min.js"></script>

</html>