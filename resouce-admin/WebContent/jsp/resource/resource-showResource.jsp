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
  	<input type="text" value="${tbRsRTPP.rtpp_sid}" id="rtppSid" style="display: none;"/>
    <form id="formInfo" method="post" class="form-x" action="">
	    <table width="90%">
		    <tr>
		       <th class="text-right" >
		       	   <label>资源方ID：</label>
		       </th>
		       <td>
		           <input class="small-input" name="netSid" value="${tbRsRTPP.net_sid}" readonly="readonly"/>
		       </td>
		        <th class="text-right">
		       	   <label>资源方名称：</label>
		       </th>
		       <td>
		           <input class="small-input" name="realname" value="${tbRsRTPP.net_name}"/>
		       </td>
		    </tr>   
		    <tr>
		       <th class="text-right">
		       	   <label>节点名称：</label>
		       </th>
		       <td>
		           <input class="small-input" name="username" value="${tbRsRTPP.name}"/>
		       </td>
		       <th class="text-right">
		       	   <label>节点IP地址：</label>
		       </th>
		       <td>
		           <input class="small-input" name="mobile" value="${tbRsRTPP.ip}" id="mobile"/>
		       </td>
		    </tr>  
		    <tr>
		       <th class="text-right">
		       	   <label>运营商：</label>
		       </th>
		       <td>
		           <input class="small-input" name="email" value="${tbRsRTPP.ispName}" readonly="readonly"/>
		       </td>
		       <th class="text-right" >
		       	   <label>所属地区：</label>
		       </th>
		       <td>
		           <input class="small-input" name="address" value="${tbRsRTPP.country} ${tbRsRTPP.province} ${tbRsRTPP.city}"/>
		       </td>
		    </tr> 
	    	<tr>
		       <th class="text-right" >
		       	   <label>并发量上线：</label>
		       </th>
		       <td>
		           <input class="small-input" name="netName" value="${tbRsRTPP.concurrency_limit}"/>
		       </td>
		       <th class="text-right">
		       	   <label>带宽上限(MB)：</label>
		       </th>
		       <td>
		       		<input class="small-input" name="netName" value="${tbRsRTPP.bandwidth_limit}"/>
		       </td>
		    </tr>
		    <tr>
		       <th class="text-right" >
		       	   <label>流量单价(分/GB)：</label>
		       </th>
		       <td>
		           <input class="small-input" name="point" value="${tbRsRTPP.price}" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"/>
		       </td>
		       <th class="text-right">
		       	   <label>分时间段定价:</label>
		       	   <input type="text" value="${tbRsRTPP.block_price}"  id="blockPrice" style="display: none;"/>
		       </th>
		       <td>
		       	   <c:if test="${tbRsRTPP.block_price == null}">
		           		<c:out value="此资源暂无分时间段定价"></c:out>
		           </c:if>
		           <c:if test="${tbRsRTPP.block_price !=null}">
		           		<a style="color: red" onclick="showBlockPrice()" href="javascript:void(0)"><c:out value="点击查看分段定价表"/></a>
		           </c:if>
		           <%-- <input class="small-input" name="rank" value="${tbRsRTPP.block_price}" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"/> --%>
		       </td>
		    </tr>
		    <tr>
		       <th class="text-right" >
		       	   <label>创建时间：</label>
		       </th>
		       <td>
		           <input class="small-input" name="companyNbr" value="<fmt:formatDate value="${tbRsRTPP.create_date}" pattern="yyyy-MM-dd" />" id="companyNbr"/>
		       </td>
		       <th class="text-right" >
		       	   <label>专线目标IP：</label>
		       </th>
		       <td>
		           <c:if test="${tbRsRTPP.is_to_line == 0}">
		           		<c:out value="此资源暂无专线IP"></c:out>
		           </c:if>
		           <c:if test="${tbRsRTPP.is_to_line == 1}">
		           		<input class="small-input" name="companyNbr" value="${tbRsRTPP.to_ip}" id="companyNbr"/>
		           </c:if>
		       </td>
		    </tr>
		    <tr>
		       <th class="text-right" >
		       	   <label>更新时间：</label>
		       </th>
		       <td>
		           <input class="small-input" name="legalPerson" value="<fmt:formatDate value="${tbRsRTPP.update_date}" pattern="yyyy-MM-dd" />"/>
		       </td>
		       <th class="text-right">
		       	   <label>审核时间：</label>
		       </th>
		       <td>
		           <input class="small-input" name="webSite" value="<fmt:formatDate value="${tbRsRTPP.audit_date}" pattern="yyyy-MM-dd" />"/>
		       </td>
		    </tr>
		    <tr>
		       <th class="text-right" >
		       	   <label>路由区域：</label>
		       </th>
		       <td>
		           <input class="small-input" name="orgId" value="${tbRsRTPP.routeArea}"/>
		       </td>
		       <th class="text-right">
		       	   <label>工作状态：</label>
		       </th>
		       <td>
		        <%-- <input class="small-input"  value="${tbRsRTPP.rStatus}"/> --%>
			    <c:if test="${tbRsRTPP.rStatus == 0}">
	        		<input class="small-input"  value="正常工作" readonly="readonly"/>
		        </c:if>
		        <c:if test="${tbRsRTPP.rStatus == 1}">
		        	<input class="small-input"  value="审核中..." readonly="readonly"/>
		        </c:if>
		        <c:if test="${tbRsRTPP.rStatus == 2}">
		        	<input class="small-input"  value="不通过" readonly="readonly"/>
		        </c:if>
		        <c:if test="${tbRsRTPP.rStatus == 3}">
		        	<input class="small-input"  value="故障" readonly="readonly"/>
		        </c:if>
		        <c:if test="${tbRsRTPP.rStatus == 4}">
		        	<input class="small-input"  value="暂停" readonly="readonly"/>
		        </c:if>
		        <c:if test="${tbRsRTPP.rStatus == 5}">
		        	<input class="small-input"  value="下线" readonly="readonly"/>
		        </c:if> 
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
		$('body').load('/resourceController/queryResourceFenYe.action');
	}
	
	function showBlockPrice(){
		var blockPrice = $('#blockPrice').val();
		var rtppSid = $('#rtppSid').val();
		$('body').load('/jsp/resource/resource-showBlockPrice.jsp',{"blockPrice":blockPrice,"rtppSid":rtppSid});
	}
</script>
</body>
</html>