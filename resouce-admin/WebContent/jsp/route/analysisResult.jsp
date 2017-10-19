<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	    <meta name="renderer" content="webkit">
	    <title></title>  
	    <link rel="stylesheet" href="${ctx}/css/pintuer.css">
	    <link rel="stylesheet" href="${ctx}/css/admin.css">
	    <style type="text/css">
			.floatLayer{ width:100%; height:100%; position:fixed; background:#000;  z-index:9990; top:0px; left:0px;filter:alpha(Opacity=50);-moz-opacity:0.50;opacity: 0.50;}
			.liadloging{ width:100%; height:auto; position:absolute; top:35%; left:43%; z-index:9995;  }
		</style>
	    <script src="${ctx}/js/jquery.js"></script>
	    <script src="${ctx}/js/pintuer.js"></script>  
	</head>
	<body>
		<div id="loadingDiv" style="display: none;">
		<div class="liadloging">
	        <img src="${ctx}/img/loadingImg.gif"/>
		</div>
		<!--灰色遮罩层 begin-->
		<div class="floatLayer"></div> 
		<!--灰色遮罩层 end-->
		</div>
		<form method="post" action="">
		  <div class="panel admin-panel">
		    <div class="panel-head"><strong class="icon-reorder"> ${routeDomain}域路由分析</strong></div>
		    <div class="padding border-bottom">
		      <ul class="search">
				<li>
					<select id="resultSelection" class="input" style="width:150px;line-height:17px;" onchange="filterResult()">
						<option value=""  <c:if test="${resultSelection == ''}">selected="selected"</c:if> >-----结果-----</option>
						<option value="1" <c:if test="${resultSelection == 1}">selected="selected"</c:if> >变好</option>
						<option value="2" <c:if test="${resultSelection == 2}">selected="selected"</c:if> >变差</option>
						<option value="3" <c:if test="${resultSelection == 3}">selected="selected"</c:if> >不定</option>
						<option value="4" <c:if test="${resultSelection == 4}">selected="selected"</c:if> >不变</option>
						<option value="5" <c:if test="${resultSelection == 5}">selected="selected"</c:if> >变好/变差/不变</option>
					</select>
				</li>
			    <span class="pageCount">
		         	每页显示
		          <select id="pageRowCount" onchange="jumpTo()">
		       		<option value="10" <c:if test="${page.pageRowCount == 10}">selected="selected"</c:if> >10</option>
		       		<option value="30" <c:if test="${page.pageRowCount == 30}">selected="selected"</c:if> >30</option>
		       		<option value="50" <c:if test="${page.pageRowCount == 50}">selected="selected"</c:if> >50</option>
		       		<option value="100" <c:if test="${page.pageRowCount == 100}">selected="selected"</c:if> >100</option>
		       		<%-- <option value="${page.parameter.ipCount-1}" <c:if test="${page.pageRowCount == page.parameter.ipCount-1}">selected="selected"</c:if> >按源节点分页</option> --%>
			      </select>条
		         </span>
		      </ul>
		    </div>
		    <table class="table table-hover text-center">
		      <tr class="text-c">
		        <th >序号</th>
		        <th >源节点</th>
		        <th >目的节点</th>
		        <th >最优路由</th>
		        <th >最优路由COST</th>
		        <th >点对点COST</th>
		        <th >结果</th>
		      </tr>
		       <% int i = 1; %>
		      <c:forEach var="routeAnalysis" items="${page.result}">  
		      <tr>
		        <td ><%= i++ %></td>
		        <td >${routeAnalysis.srcIp}</td>
		        <td >${routeAnalysis.destIp}</td>
		        <td >${routeAnalysis.bestPath}</td>
		        <td ><c:out value="${routeAnalysis.weightQuality}" default="无"></c:out></td>
		        <td ><c:out value="${routeAnalysis.nbrQuality}" default="无"></c:out></td>
		        <td >
		        	<c:choose>
		        		<c:when test="${routeAnalysis.weightQuality == null  || routeAnalysis.nbrQuality == null}"><font color="blue">不定</font></c:when>
		        		<c:when test="${routeAnalysis.weightQuality*1 lt routeAnalysis.nbrQuality*1}"><font color="green">变好</font></c:when>
		        		<c:when test="${routeAnalysis.weightQuality*1 gt routeAnalysis.nbrQuality*1}"><font color="red">变差</font></c:when>
		        		<c:otherwise>不变</c:otherwise>
		        	</c:choose>
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
	</body>
	<script type="text/javascript">
		function jumpTo(page){
			var  pageRowCount = $('#pageRowCount').val();
			$("#loadingDiv").show();
			$('body').load("/route/analysisChangePage", {
				"pageRowCount" : pageRowCount,
				"currentPage" : page,
				"analysisStr":'${analysisStr}',
				"routeDomain":'${routeDomain}'
			});
		}	
		function filterResult(){
			$("#loadingDiv").show();
			$('body').load("/route/analysis", {
				"analysisStr":'${analysisStr}',
				"resultSelection":$('#resultSelection').val(),
				"routeDomain":'${routeDomain}'
			});
		}
	</script>
</html>