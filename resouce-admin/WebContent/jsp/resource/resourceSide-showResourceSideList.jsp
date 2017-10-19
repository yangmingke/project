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
</head>
<body>
<form method="post" action="">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 资源方列表</strong></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
          <li>
            <select id="isPrivateNet" class="input" style="width:200px; line-height:17px;">
	            <option value="">私网加速：所有</option>
	            <c:choose>
	            	<c:when test="${isPrivateNet == '1'}">
	            		<option value="1" selected="selected">是</option>
	            	</c:when>
	            	<c:otherwise>
	            		<option value="1">是</option>
	            	</c:otherwise>
	            </c:choose>
	            <c:choose>
	            	<c:when test="${isPrivateNet == '1'}">
	            		<option value="0" selected="selected">否</option>
	            	</c:when>
	            	<c:otherwise>
	            		<option value="0">否</option>
	            	</c:otherwise>
	            </c:choose>
            </select>
          </li>
          <li>
            <select id="status" class="input" style="width:200px; line-height:17px;">
              <option value="">状态：所有</option>
               <c:choose>
	            <c:when test="${isPrivateNet == '1'}">
	            	 <option value="1" selected="selected">正常</option>
	            </c:when>
	            <c:otherwise>
	            	 <option value="1">正常</option>
	            </c:otherwise>
	           </c:choose>
	           <c:choose>
	            <c:when test="${isPrivateNet == '1'}">
	            	 <option value="5" selected="selected">锁定</option>
	            </c:when>
	            <c:otherwise>
	            	 <option value="5">锁定</option>
	            </c:otherwise>
	            </c:choose>
	            <c:choose>
	            <c:when test="${isPrivateNet == '1'}">
	            	<option value="6" selected="selected">关闭</option>
	            </c:when>
	            <c:otherwise>
	            	<option value="6">关闭</option>
	            </c:otherwise>
	          </c:choose>
            </select>
          </li>
          <li>
          	<input type="text" placeholder="资源方名称关键字" id="realname" class="input" value="${realname }" style="width:250px; line-height:17px;display:inline-block" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:void(0)" class="button border-main icon-search" onclick="jumpTo()" > 查询</a>
          </li>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th width="5%">序号</th>
        <th>专网名称</th>
        <th>资源方名称</th>  
        <th>私网加速</th>
        <th>状态</th>
        <th>联系方式</th>
        <th>创建日期</th>
        <th>操作</th>       
      </tr> 
      <% int i = 1; %>
   	  <c:forEach var="account" items="${page.result}">   
   	  <tr>
         <td><%= i++ %></td>
         <td>${account.netName}</td>
         <td>${account.realname}</td>
         <td>
         	<c:choose>
         		<c:when test="${account.isPrivateNet == '1'}">是</c:when>
         		<c:otherwise>否</c:otherwise>
         	</c:choose>
         </td>  
         <td>
         	<c:choose>
         		<c:when test="${account.status == '1'}"><font color=green>正常</font></c:when>
         		<c:when test="${account.status == '5'}"><font color=red>锁定</font></c:when>
         		<c:when test="${account.status == '6'}"><font color=red>关闭</font></c:when>
         		<c:otherwise><font color=red>异常</font></c:otherwise>
         	</c:choose>
         </td>         
         <td>${account.mobile}</td>
         <td><fmt:formatDate value="${account.createDate}" pattern="yyyy-MM-dd" /></td>
         <td>
         	<div class="button-group"> <a class="button border-blue" href="javascript:void(0)" onclick="addResource('${account.netSid}')"><span class="icon-edit"></span> 添加资源</a> </div>
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
        		  <%-- 第<input type="text" id="jumpTo" width="30px;"/>页 <a class="button border-green" href="javascript:void(0)" onclick="jumpTo(${page.totalPage})"><span class="icon-trash-o"></span> 删除</a> --%>
        	 </div>
         </td>
      </tr>
    </table>
  </div>
</form>
<script type="text/javascript">

function addResource(netSid){
	$('body').load('/resourceController/addResourcePage');
}

function jumpTo(page){
	if (typeof(page) == "undefined") { 
		page = 1;
	}
	var isPrivateNet = $('#isPrivateNet').val();
	var status = $('#status').val();
	var realname = $('#realname').val();
	$('body').load('/account/queryAccount',{"page":page,"status":status,"isPrivateNet":isPrivateNet,"realname":realname});
}

</script>
</body></html>