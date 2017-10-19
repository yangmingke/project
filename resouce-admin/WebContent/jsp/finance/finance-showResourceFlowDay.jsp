<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <script src="${ctx}/js/jquery.js"></script>
    <script src="${ctx}/js/pintuer.js"></script>
    <script src="${ctx}/plugin/My97DatePicker/WdatePicker.js"></script>  
</head>
<body>
<form method="post" action="">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 节点流量日消耗</strong></div>
    <div class="padding border-bottom">
	    <ul class="search">
	   		<li>
        		日期筛选：
        	</li>
        	<li>
	     	 	<input type="text"  placeholder="请选择日期" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" id="dateTime" class="input-text Wdate" value="${dateTime}" style="width:150px; height:38px; display:inline-block"  name="dateTime">
		  		&nbsp;&nbsp;&nbsp;&nbsp;
        	</li>
	      	<li>
		       <input type="text" placeholder="IP地址关键字" id="keyWord" class="input" style="width:250px; line-height:16px;display:inline-block" value="${keyWord}"/>&nbsp;&nbsp;&nbsp;
		       <a href="javascript:void(0)" class="button border-main icon-search" onclick="jumpTo()" title="查询"> </a>
	      	</li>
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
    <table class="table table-hover text-center" >
      <tr class="text-c">
        <th >序号</th>
        <th >资源方公司简称</th>
        <th >资源IP地址</th>
        <th >接收流量(KB)</th>
        <th >发送流量(KB)</th>
      <!--   <th >总流量(KB)</th> -->
        <th >单价(元/GB)</th>
        <th >收入(元)</th>
        <th >日期</th>
      </tr>
      <%int i = 1; %> 
      <c:forEach var="resourceList" items="${page.resultMap}"> 
      <tr>
        <td ><%=i++%></td>
        <td >${resourceList.userName}</td>
        <td >${resourceList.node_ip}</td>
        <td >${resourceList.traffic_in}</td>
        <td >${resourceList.traffic_out}</td>
       <%--  <td >${resourceList.traffic_total}</td> --%>
        <td >${resourceList.traffic_feerate}</td>
        <td >${resourceList.traffic_fee}</td>
        <td ><fmt:formatDate value="${resourceList.datetime}"  pattern="yyyy-MM-dd"/></td>
      </tr>
     </c:forEach>
      <tr>
        <td colspan="11">
        	<div class="pagelist">
        		<c:if test="${page.currentPage != 1}">
        	 		<a onclick="jumpToPage(${page.currentPage-1})">上一页</a>
        	 	</c:if>
        	 	<c:forEach items="${page.pageList}" var="item">
				<c:if test="${page.currentPage != item}">
					<a onclick="jumpToPage(${item})">${item}</a>
				</c:if>
				<c:if test="${page.currentPage == item}">
					<a class="current">${item}</a>
				</c:if>
				</c:forEach>
				<c:if test="${page.currentPage != page.totalPage}">
	        	 	<a onclick="jumpToPage(${page.currentPage+1})">下一页</a>
				</c:if>
         		 <label for="sitename">共&nbsp; ${page.totalPage}&nbsp; 页</label>
        	 </div>
         </td>
      </tr>
    </table>
  </div>
</form>
<script type="text/javascript">

function jumpTo(page){
	if (typeof(page) == "undefined") { 
		page = 1;
	}
	var keyWord = $('#keyWord').val();
	var dateTime = $('#dateTime').val();
	var pageRowCount = $('#pageRowCount').val(); 
	$('body').load('/resourceFlowController/queryResourceFlowDay',{"page":page,"keyWord":keyWord,"dateTime":dateTime,"pageRowCount":pageRowCount});
}

//页码跳转
function jumpToPage(page){
	if (typeof(page) == "undefined") { 
		page = 1;
	}
	var keyWord = $('#keyWord').val();
	var dateTime = $('#dateTime').val();
	var pageRowCount = $('#pageRowCount').val(); 
	$('body').load('/resourceFlowController/queryResourceFlowDay',{"page":page,"keyWord":keyWord,"dateTime":dateTime,"pageRowCount":pageRowCount});
}

$("#checkall").click(function(){ 
  $("input[name='id[]']").each(function(){
	  if (this.checked) {
		  this.checked = false;
	  }
	  else {
		  this.checked = true;
	  }
  });
})

$("#delSelect").click(function(){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){
		var t=confirm("您确认要删除选中的内容吗？");
		if (t==false) {
			return false;	
		}else{
			// 获取所有选中的checked框  
			var option = $('input[name="id[]"]:checked');  
			var checkedId = "";  
			var boo=true;  
			//拼接除全选框外,所有选中的id,  
			for (var i = 0, len = option.length; i < len; i++) {  
			    if (boo) {  
			        if (option[i].batchDel=='allSelect') {  
			            boo=true;  
			        }else {  
			            boo=false;  
			            checkedId += option[i].value;  
			        }  
			    }else{  
			        checkedId += ","+option[i].value;  
			    }  
			}
			$.ajax({  
		        type: "post",  
		        url:'/resourceController/batchDelResource.action',  
		        data:{  
		        "checkedRTPPIP":checkedId
		        },  
		        dataType:"json",  
		        success : function(data) { 
				    var json = eval("("+data+")");
				    if(json == 0){
				    	window.wxc.xcConfirm("删除失败...", window.wxc.xcConfirm.typeEnum.info);
				    }
				    if(json == 1){
				    	window.wxc.xcConfirm("删除成功...", window.wxc.xcConfirm.typeEnum.info);
				    }
			    },
			    error:function(code){   
			        window.wxc.xcConfirm("Sorry.服务器出错", window.wxc.xcConfirm.typeEnum.info);
			    }   
		     });  
		} 		
	}
	else{
		window.wxc.xcConfirm("请选择您要删除的内容!", window.wxc.xcConfirm.typeEnum.info);
		return false;
	}
})

</script>
</body></html>