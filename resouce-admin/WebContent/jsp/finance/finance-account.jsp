<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title></title>  
    <link rel="stylesheet" href="${ctx}/css/pintuer.css">
    <link rel="stylesheet" href="${ctx}/css/admin.css">
    <script src="${ctx}/js/jquery.js"></script>
    <script src="${ctx}/js/pintuer.js"></script>  
	<script src="${ctx}/js/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<form method="post" action="">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 资源方列表</strong></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
          <li>
            <select id="status" class="input" style="width:200px; line-height:17px;">
              <option value="">钱包状态：所有</option>
                <c:choose>
		            <c:when test="${page.parameter.status == '1'}">
		            	 <option value="1" selected="selected">正常</option>
		            </c:when>
		            <c:otherwise>
		            	 <option value="1">正常</option>
		            </c:otherwise>
	            </c:choose>
	            <c:choose>
		            <c:when test="${page.parameter.status == '0'}">
		            	 <option value="0" selected="selected">冻结</option>
		            </c:when>
		            <c:otherwise>
		            	 <option value="0">冻结</option>
		            </c:otherwise>
	            </c:choose>
            </select>
          </li>
          <input type="text" placeholder="资源方公司简称关键字" id="username" class="input" value="${page.parameter.username }" style="width:250px; line-height:17px;display:inline-block" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <a href="javascript:void(0)" class="button border-main icon-search" onclick="jumpTo()" title="查询"> </a>
          <span class="pageCount">
          	每页显示
	          <select id="pageRowCount" onchange="jumpTo()">
	       		<option value="10" <c:if test="${page.pageRowCount == 10}">selected="selected"</c:if> >10</option>
	       		<option value="30" <c:if test="${page.pageRowCount == 30}">selected="selected"</c:if> >30</option>
	       		<option value="50" <c:if test="${page.pageRowCount == 50}">selected="selected"</c:if> >50</option>
	       		<option value="100" <c:if test="${page.pageRowCount == 100}">selected="selected"</c:if> >100</option>
		      </select>条
	      </span>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th width="5%">序号</th>
        <th>资源方id</th>
        <th>资源方公司简称</th>  
        <th>余额</th>
        <th>申请中的提现金额</th>
        <th>成功提现总金额</th>
        <th>钱包状态</th>
        <th>收款账号（支付宝）</th>
        <th>收款人（支付宝）</th>
        <th>操作</th>       
      </tr> 
      <% int i = 1; %>
   	  <c:forEach var="account" items="${page.resultMap}">   
   	  <tr>
         <td><%= i++ %></td>
         <td>${account.net_sid}</td>
         <td>${account.username}</td>
         <td><c:out value="${account.balance}" default="0" /></td>
         <td><c:out value="${account.fee1}.00" default="0" /></td>
         <td><c:out value="${account.fee2}.00" default="0" /></td>
         <td>
         	<c:choose>
         		<c:when test="${account.enable_flag == '1'}"><font color=green>正常</font></c:when>
         		<c:when test="${account.enable_flag == '0'}"><font color=red>冻结</font></c:when>
         		<c:otherwise><font color=red>异常</font></c:otherwise>
         	</c:choose>
         </td>
         <td>${account.alipay_account}</td>
         <td>${account.alipay_name}</td>
         <td>
			<div class="button-group"> <a class="button border-green" href="javascript:void(0)" onclick="enchashment('${account.net_sid}','${account.enable_flag}')" title="申请提现"><span class="icon-money" ></span> </a> </div>
         	<c:if test="${account.enable_flag == '1'}">
         		<div class="button-group"> <a class="button border-red" href="javascript:void(0)" onclick="unLockorLock('${account.net_sid}',0)" title="冻结钱包"><span class="icon-lock"></span> </a> </div>
         	</c:if>
         	<c:if test="${account.enable_flag == '0'}">
         		<div class="button-group"> <a class="button border-green" href="javascript:void(0)" onclick="unLockorLock('${account.net_sid}',1)" title="解冻钱包"><span class="icon-unlock" ></span></a> </div>
         	</c:if>
		</td>
      </tr>
      </c:forEach>
      <tr>
        <td colspan="9">
        	<div class="pagelist">
        		<c:if test="${page.currentPage != 1}">
        	 		<a onclick="jumpTo(${page.currentPage-1})">上一页</a>
        	 	</c:if>
        	 	<c:forEach items="${page.pageList}" var="item">
				<c:if test="${page.currentPage != item}">
					<a onclick="jumpTo(${item})">${item}</a>
				</c:if>
				<c:if test="${page.currentPage == item}">
					<a class="current">${item}</a>
				</c:if>
				</c:forEach>
				<c:if test="${page.currentPage != page.totalPage}">
	        	 	<a onclick="jumpTo(${page.currentPage+1})">下一页</a>
				</c:if>
         		 <label for="sitename">共&nbsp; ${page.totalPage}&nbsp; 页</label>
        	 </div>
         </td>
      </tr>
    </table>
  </div>
</form>
<script type="text/javascript">
function unLockorLock(netSid,status){
	var tip = status == 0 ?  "您确定要冻结该用户钱包吗?" : "您确定要解冻该用户钱包吗?";
	var v = window.wxc.xcConfirm(tip, window.wxc.xcConfirm.typeEnum.confirm,{
		onOk:function(v){
			$.post('/accountManagement/unLockorLock',{"netSid":netSid,"status":status},function(data){
				var json = eval("("+data+")");
	        	if(json == 0){
	        		window.wxc.xcConfirm("系统发生错误，修改失败，请联系管理员！", window.wxc.xcConfirm.typeEnum.info);
	        	}
	        	if(json == 1){
	        		location.reload();
	        	}
			});
		}
	});
}

function jumpTo(page){
	if (typeof(page) == "undefined") { 
		page = 1;
	}
	var status = $('#status').val();
	var username = $('#username').val();
	var pageRowCount = $('#pageRowCount').val(); 
	$('body').load('/accountManagement/queryAccount',{"page":page,"status":status,"username":username,"pageRowCount":pageRowCount});
}

function enchashment(netSid,enableFlag){
	if(empty(netSid)){
		window.wxc.xcConfirm("系统参数发生异常，请联系管理员！", window.wxc.xcConfirm.typeEnum.info);
		return false;
	}
	if(enableFlag != '1'){
		window.wxc.xcConfirm("钱包状态冻结，不能进行“申请提现”", window.wxc.xcConfirm.typeEnum.info);
		return false;
	}
	
	window.location.href="/accountManagement/enchashment?netSid="+netSid; 
}

</script>
</body></html>