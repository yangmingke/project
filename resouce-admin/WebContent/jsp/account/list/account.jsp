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
            <select id="isPrivateNet" class="input" style="width:200px; line-height:17px;">
	            <option value="">独立路由域：所有</option>
	            <c:choose>
	            	<c:when test="${isPrivateNet == '1'}">
	            		<option value="1" selected="selected">是</option>
	            	</c:when>
	            	<c:otherwise>
	            		<option value="1">是</option>
	            	</c:otherwise>
	            </c:choose>
	            <c:choose>
	            	<c:when test="${isPrivateNet == '0'}">
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
	            <c:when test="${status == '1'}">
	            	 <option value="1" selected="selected">正常</option>
	            </c:when>
	            <c:otherwise>
	            	 <option value="1">正常</option>
	            </c:otherwise>
	           </c:choose>
	           <c:choose>
	            <c:when test="${status == '5'}">
	            	 <option value="5" selected="selected">锁定</option>
	            </c:when>
	            <c:otherwise>
	            	 <option value="5">锁定</option>
	            </c:otherwise>
	            </c:choose>
            </select>
          </li>
          <input type="text" placeholder="资源方名称关键字" id="realname" class="input" value="${realname }" style="width:250px; line-height:17px;display:inline-block" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <a href="javascript:void(0)" class="button border-main icon-search" onclick="jumpTo()" title="查询" ></a>
          <span class="pageCount">
          	每页显示
	          <select id="pageRowCount" onchange="changePageRowCount()">
	       		<option value="10" <c:if test="${page.pageRowCount == 10}">selected="selected"</c:if> >10</option>
	       		<option value="30" <c:if test="${page.pageRowCount == 30}">selected="selected"</c:if> >30</option>
	       		<option value="50" <c:if test="${page.pageRowCount == 50}">selected="selected"</c:if> >50</option>
	       		<option value="100" <c:if test="${page.pageRowCount == 100}">selected="selected"</c:if> >100</option>
		      </select>条
	      </span>
      </ul>
    </div>
    <table class="table table-hover text-center">
   	<!--   <tr style="white-space:nowrap;">
   	  	<td style="text-align: right;">
   	  		每页显示<span>
	   	  	<select>
	       		<option value="10">10</option>
	       		<option value="50">50</option>
	       	</select>条
	       	
	       	</span>
	    </td>
      </tr> -->
      <tr>
        <th width="5%">序号</th>
        <th>路由域名称</th>
        <th>资源方公司简称</th>  
        <th>独立路由域</th>
        <th>状态</th>
        <th>联系方式</th>
        <th>创建日期</th>
        <th>操作</th>       
      </tr> 
      <% int i = 1; %>
   	  <c:forEach var="account" items="${page.result}">   
   	  <tr>
         <td><%= i++ %></td>
         <td>${account.netArea}<%-- <c:if test="${account.netArea == null}">cn</c:if> --%></td>
         <td>${account.username}</td>
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
         	<div class="button-group"> <a class="button border-green" href="javascript:void(0)" onclick="queryUsers('${account.netSid}')" title="查看用户"><span class="icon-user"></span> </a> </div>
         	<c:if test="${account.status == '1'}">
         		<div class="button-group"> <a class="button border-gray" href="javascript:void(0)" onclick="lock('${account.netSid}',5)" title="锁定"><span class="icon-lock"></span> </a> </div>
         	</c:if>
         	<c:if test="${account.status == '5'}">
         		<div class="button-group"> <a class="button border-black" href="javascript:void(0)" onclick="unlock('${account.netSid}',1)" title="解锁"><span class="icon-unlock"></span></a> </div>
         	</c:if>
         	<div class="button-group"> <a class="button border-blue" href="javascript:void(0)" onclick="detail('${account.netSid}')" title="详细信息"><span class="icon-edit"></span> </a> </div>
         	<div class="button-group"> <a class="button border-green" href="javascript:void(0)" onclick="log('${account.netSid}')" title="操作日志"><span class="icon-list-alt"></span> </a> </div>
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
    
    <%-- <div id="pageControl">
		<!-- 上一页 按钮 -->
		<c:if test="${page.currentPage != 1}">
			<a href="/demoController/queryDemo?page=${page.currentPage-1}"><input type="button" name="lastPage" value="上一页" /></a>
		</c:if>
		<!-- 页数列表 -->
		<c:forEach items="${page.pageList}" var="item">
			<c:if test="${page.currentPage != item}">
			<a href="/demoController/queryDemo?page=${item}" >${item}</a>
			</c:if>
			<c:if test="${page.currentPage == item}">
				<a class="currentPage">${item}</a>
			</c:if>
		</c:forEach>
		<!-- 下一页 按钮 -->
		<c:if test="${page.currentPage != page.totalPage}">
			<a href="/demoController/queryDemo?page=${page.currentPage + 1}"><input type="button" name="nextPage" value="下一页" /></a>
		</c:if>
		<!-- 直接跳转 -->
		共${page.totalPage}页  第<input type="text" id="jumpTo" />页 <input type="button" value="跳转" onclick="jumpTo(${page.totalPage})" />
	</div> --%>
	
  </div>
</form>
<script type="text/javascript">

function changePageRowCount(pageRowCount){
	jumpTo();
}

function lock(netSid,status){
	window.wxc.xcConfirm("您确定要锁定该用户吗?", window.wxc.xcConfirm.typeEnum.confirm,{
		onOk:function(v){
			$.post('/account/unLockorLock',{"netSid":netSid,"status":status},function(data){
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

function unlock(netSid,status){
	window.wxc.xcConfirm("您确定要解锁该用户吗?", window.wxc.xcConfirm.typeEnum.confirm,{
		onOk:function(v){
			$.post('/account/unLockorLock',{"netSid":netSid,"status":status},function(data){
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

function detail(netSid){
	$('body').load('/account/queryAccountById?netSid=' + netSid);
}

function log(netSid){
	$('body').load('/log/queryLog?netSid=' + netSid);
}

function queryUsers(netSid){
	$('body').load('/account/queryUsersByNetId?netSid=' + netSid);
}

function jumpTo(page){
	if (typeof(page) == "undefined") { 
		page = 1;
	}
	var pageRowCount = $('#pageRowCount').val(); 
	var isPrivateNet = $('#isPrivateNet').val();
	var status = $('#status').val();
	var realname = $('#realname').val();
	$('body').load('/account/queryAccount',{"page":page,"status":status,"isPrivateNet":isPrivateNet,"realname":realname,"pageRowCount":pageRowCount});
}

</script>
</body></html>