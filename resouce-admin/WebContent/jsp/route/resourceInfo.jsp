<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<style type="text/css">
	tr{
		line-height: 50px;
	}
</style>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-key"></span> 资源节点信息</strong></div>
  <div class="body-content">
  	<input type="text" value="${rtpp_sid}" id="rtppSid" style="display: none;"/>
    <form id="formInfo" method="post" class="form-x" action="">
	    <table width="90%">
		    <tr>
		       <th class="text-right" >
		       	   <label>资源方ID：</label>
		       </th>
		       <td>
		           <input class="small-input" name="netSid" value="${net_sid}" readonly="readonly"/>
		       </td>
		       <th class="text-right">
		       	   <label>工作状态：</label>
		       </th>
		       <td>
		        <%-- <input class="small-input"  value="${status}"/> --%>
			    <c:if test="${status == 0}">
	        		<input class="small-input"  value="初始状态" readonly="readonly"/>
		        </c:if>
		        <c:if test="${status == 1}">
		        	<input class="small-input"  value="审核中" readonly="readonly"/>
		        </c:if>
		        <c:if test="${status == 2}">
		        	<input class="small-input"  value="不通过" readonly="readonly"/>
		        </c:if>
		        <c:if test="${status == 3}">
		        	<input class="small-input"  value="就绪状态" readonly="readonly"/>
		        </c:if>
		        <c:if test="${status == 4}">
		        	<input class="small-input"  value="暂停使用" readonly="readonly"/>
		        </c:if>
		        <c:if test="${status == 5}">
		        	<input class="small-input"  value="下线" readonly="readonly"/>
		        </c:if> 
		        <c:if test="${status ==6}">
		        	<input class="small-input"  value="在线" readonly="readonly"/>
		        </c:if> 
		       </td>
		    </tr>   
		    <tr>
		       <th class="text-right">
		       	   <label>节点名称：</label>
		       </th>
		       <td>
		           <input class="small-input" name="username" value="${name}" readonly/>
		       </td>
		       <th class="text-right">
		       	   <label>节点IP地址：</label>
		       </th>
		       <td>
		           <input class="small-input" name="mobile" value="${ip}" id="mobile" readonly/>
		       </td>
		    </tr>  
		    <tr>
		       <th class="text-right">
		       	   <label>运营商：</label>
		       </th>
		       <td>
		           <input class="small-input" name="email" value="${operatorName}" readonly="readonly"/>
		       </td>
		       <th class="text-right" >
		       	   <label>所属地区：</label>
		       </th>
		       <td>
		           <input class="small-input" name="address" value="${cityName}" readonly/>
		       </td>
		    </tr> 
	    	<tr>
		       <th class="text-right" >
		       	   <label>并发量上限：</label>
		       </th>
		       <td>
		           <input class="small-input" name="netName" value="${concurrency_limit}" readonly/>
		       </td>
		       <th class="text-right">
		       	   <label>带宽上限(MB)：</label>
		       </th>
		       <td>
		       		<input class="small-input" name="netName" value="${bandwidth_limit}" readonly/>
		       </td>
		    </tr>
		    <tr>
		       <th class="text-right" >
		       	   <label>流量单价(分/GB)：</label>
		       </th>
		       <td>
		           <input class="small-input" name="point" value="${price}" readonly/>
		       </td>
		       <th class="text-right">
		       	   <label>分时间段定价:</label>
		       	   <input type="text" value="${block_price}"  id="blockPrice" style="display: none;"/>
		       </th>
		       <td>
		       	   <c:if test="${block_price == null}">
		           		<c:out value="此资源暂无分时间段定价"></c:out>
		           </c:if>
		           <c:if test="${block_price !=null}">
		           		<a style="color: red" onclick="showBlockPrice()" href="javascript:void(0)"><c:out value="点击查看分段定价表"/></a>
		           </c:if>
		       </td>
		    </tr>
		    <tr>
		       <th class="text-right" >
		       	   <label>创建时间：</label>
		       </th>
		       <td>
		           <input class="small-input" name="netName" value="${create_date}" readonly/>
		       </td>
		       <th class="text-right" >
		       	   <label>专线目标IP：</label>
		       </th>
		       <td>
		           <c:if test="${is_to_line == 0}">
		           		<c:out value="此资源暂无专线IP"></c:out>
		           </c:if>
		           <c:if test="${is_to_line == 1}">
		           		<input class="small-input" name="companyNbr" value="${to_ip}" id="companyNbr" readonly/>
		           </c:if>
		       </td>
		    </tr>
		    <tr>
		       <th class="text-right" >
		       	   <label>更新时间：</label>
		       </th>
		       <td>
		       	   <input class="small-input" name="netName" value="${update_date}" readonly/>
		       </td>
		       <th class="text-right">
		       	   <label>审核时间：</label>
		       </th>
		       <td>
		       	   <input class="small-input" name="netName" value="${audit_date}" readonly/>
		       </td>
		    </tr>
		    <tr>
		       <th class="text-right" >
		       	   <label>路由区域：</label>
		       </th>
		       <td>
		           <input class="small-input" name="orgId" value="${routeDomain}" readonly/>
		       </td>
		    </tr>
	    </table>
    </form>
    <div class="form-group" style="text-align: center;padding-top: 40px;">
      <div class="field">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <button id="back" class="button bg-main icon-undo" type="button" onclick="back()"> 返回</button>
      </div>
    </div>      
  </div>
</div>
<script type="text/javascript">
	function back(){
		$('body').load('/route/queryDomain');
	}
	
	function showBlockPrice(){
		var blockPrice = $('#blockPrice').val();
		var rtppSid = $('#rtppSid').val();
		$('body').load('/jsp/resource/resource-showBlockPrice.jsp',{"blockPrice":blockPrice,"rtppSid":rtppSid});
	}
</script>
</body>
</html>