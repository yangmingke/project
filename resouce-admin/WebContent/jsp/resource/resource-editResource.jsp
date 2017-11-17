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
<script src="${ctx}/js/xcConfirm.js" type="text/javascript" charset="utf-8"></script>

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
    <form id="formInfo" method="post" class="form-x" action="">
    	<input type="text" value="${tbRsRTPP.rnode_id}" id="rtppSid" name="rtppSid" style="display: none;"/>
	    <table width="90%">
		    <tr>
		       <th class="text-right">
		       	   <label>资源方ID：</label>
		       </th>
		       <td>
		           <input class="small-input" name="netSid" id="netSid" value="${tbRsRTPP.net_sid}" readonly="readonly"/>
		       </td>
		        <th class="text-right">
		       	   <label>资源方公司简称：</label>
		       </th>
		       <td>
		           <input class="small-input" name="netName" id="netName" value="${tbRsRTPP.username}" readonly="readonly"/>
		       </td>
		    </tr>   
		    <tr>
		       <th class="text-right">
		       	   <label>节点名称：</label>
		       </th>
		       <td>
		           <input class="small-input" name="name" value="${tbRsRTPP.name}" id="name"/>
		       </td>
		       <th class="text-right">
		       	   <label>节点IP地址：</label>
		       </th>
		       <td>
		           <input class="small-input" name="ip" value="${tbRsRTPP.ip}" id="ip" readonly="readonly"/>
		           <input hidden="hidden" id="originalIp" name="originalIp" value="${tbRsRTPP.ip}"/>
		       </td>
		    </tr>
		    <tr>
		       <th class="text-right">
		       	   <label>tcp来源IP：</label>
		       </th>
		       <td>
		           <input class="small-input" name="fromIp" value="${tbRsRTPP.from_ip}" id="fromIp"/>
		       </td>
		       <th class="text-right">
		       	   <label>接收UDP信令IP：</label>
		       </th>
		       <td>
		           <input class="small-input" name="signIp" value="${tbRsRTPP.sign_ip}" id="signIp" />
		       </td>
		    </tr>
		    <tr>
		       <th class="text-right">
		       	   <label>探测UPDping包IP：</label>
		       </th>
		       <td>
		           <input class="small-input" name="pingIp" value="${tbRsRTPP.ping_ip}" id="pingIp"/>
		       </td>
		       <th class="text-right">
		       	   <label>收发媒体IP：</label>
		       </th>
		       <td>
		           <input class="small-input" name="mediaIp" value="${tbRsRTPP.media_ip}" id="mediaIp" />
		       </td>
		    </tr>  
		    <tr>
		       <th class="text-right">
		       	   <label>运营商：</label>
		       </th>
		       <td>
		           <select id="operator" class="small-input" style="width:200px; line-height:10px;" name="operator">
			           <c:forEach var="ispList" items="${ispList}">
			           			<option value="${ispList.ispid}" 
			           			<c:if test="${ispList.ispid == tbRsRTPP.ispid}">
			           				selected="selected"
			           			</c:if>
			           			>${ispList.name}</option>
			           </c:forEach>
            	   </select>
            	   &nbsp; &nbsp; &nbsp;
            	   <label>节点类型：</label>
            	   <select id="type" class="small-input" style="width:200px; line-height:10px;" name="type">
			           <c:choose>
			           		<c:when test="${tbRsRTPP.type == 1}">
			           			<option value="0">--请选择节点类型--</option>
			           			<option value="1" selected="selected">CDN（TCP+UDP）</option>
								<option value="2">CDN（TCP only）</option>
								<option value="3">IDC</option>
								<option value="4">云平台</option>
								<option value="5">运营商</option>
								<option value="6">自营</option>
			           		</c:when>
			           		<c:when test="${tbRsRTPP.type == 2}">
			           			<option value="0">--请选择节点类型--</option>
			           			<option value="1">CDN（TCP+UDP）</option>
								<option value="2" selected="selected">CDN（TCP only）</option>
								<option value="3">IDC</option>
								<option value="4">云平台</option>
								<option value="5">运营商</option>
								<option value="6">自营</option>
			           		</c:when>
			           		<c:when test="${tbRsRTPP.type == 3}">
			           			<option value="0">--请选择节点类型--</option>
			           			<option value="1">CDN（TCP+UDP）</option>
								<option value="2">CDN（TCP only）</option>
								<option value="3" selected="selected">IDC</option>
								<option value="4">云平台</option>
								<option value="5">运营商</option>
								<option value="6">自营</option>
			           		</c:when>
			           		<c:when test="${tbRsRTPP.type == 4}">
			           			<option value="0">--请选择节点类型--</option>
			           			<option value="1">CDN（TCP+UDP）</option>
								<option value="2">CDN（TCP only）</option>
								<option value="3">IDC</option>
								<option value="4" selected="selected">云平台</option>
								<option value="5">运营商</option>
								<option value="6">自营</option>
			           		</c:when>
			           		<c:when test="${tbRsRTPP.type == 5}">
			           			<option value="0">--请选择节点类型--</option>
			           			<option value="1">CDN（TCP+UDP）</option>
								<option value="2">CDN（TCP only）</option>
								<option value="3">IDC</option>
								<option value="4">云平台</option>
								<option value="5" selected="selected">运营商</option>
								<option value="6">自营</option>
			           		</c:when>
			           		<c:when test="${tbRsRTPP.type == 6}">
			           			<option value="0">--请选择节点类型--</option>
			           			<option value="1">CDN（TCP+UDP）</option>
								<option value="2">CDN（TCP only）</option>
								<option value="3">IDC</option>
								<option value="4">云平台</option>
								<option value="5">运营商</option>
								<option value="6" selected="selected">自营</option>
			           		</c:when>
			           		<c:otherwise>
			           			<option value="0" selected="selected">--请选择节点类型--</option>
			           			<option value="1">CDN（TCP+UDP）</option>
								<option value="2">CDN（TCP only）</option>
								<option value="3">IDC</option>
								<option value="4">云平台</option>
								<option value="5">运营商</option>
								<option value="6">自营</option>
			           		</c:otherwise>
			           </c:choose>
            	   </select>
		       </td>
		       <th class="text-right" >
		       	   <label>所属地区：</label>
		       </th>
		       <td width="550px;">
		           <select id="country" name="country" class="small-input" style="width:120px; line-height:10px;">
			           <c:forEach var="countryList" items="${countryList}">
			           		<c:choose>
				           		<c:when test="${countryList.countryid == tbRsRTPP.countryid}">
				           			<option value="${countryList.countryid}" selected="selected">${countryList.country}</option>
				           		</c:when>
				           		<c:otherwise>
				           			<option value="${countryList.countryid}">${countryList.country}</option>
				           		</c:otherwise>
			           		</c:choose>
			           </c:forEach>
            	   </select>
            	   &nbsp;&nbsp;--&nbsp;&nbsp;
		           <select id="province" name= "province" class="small-input" style="width:120px; line-height:10px;">
			           <c:forEach var="provinceList" items="${provinceList}">
			           		<c:choose>
			           			<c:when test="${provinceList.provinceid == tbRsRTPP.provinceid}">
			           				<option value="${provinceList.provinceid}" selected="selected">${provinceList.province}</option>
			           			</c:when>
			           			<c:otherwise>
			           				<option value="${provinceList.provinceid}">${provinceList.province}</option>
			           			</c:otherwise>
			           		</c:choose>
			           </c:forEach>
            	   </select>
            	   &nbsp;&nbsp;--&nbsp;&nbsp;
		           <select id="city" name="zone" class="small-input" style="width:120px; line-height:10px;">
				       <c:forEach var="cityList" items="${cityList}">
				           <c:choose>
				           		<c:when test="${cityList.cityid == tbRsRTPP.cityid}">
				           			<option value="${cityList.cityid}" selected="selected">${cityList.city}</option>
				           		</c:when>
				           		<c:otherwise>
				           			<option value="${cityList.cityid}">${cityList.city}</option>
				           		</c:otherwise>
				           	</c:choose>
				       </c:forEach>
            	   </select>
		       </td>
		    </tr> 
	    	<tr>
		       <th class="text-right">
		       	   <label>带宽上限(MB)：</label>
		       </th>
		       <td>
		       		<input class="small-input" name="bandwidthLimit" value="${tbRsRTPP.bandwidth_limit}" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"/>
		       </td>
		       <th class="text-right">
		       	   <label>分时间段定价:</label>
		       	   <input type="text" value="${tbRsRTPP.block_price}"  id="blockPrice" name="block_price" style="display: none;"/>
		       </th>
		       <td>
		      	   <c:choose>
		      	   		<c:when test="${tbRsRTPP.block_price == null || tbRsRTPP.block_price == ''}">
		      	   			<a onclick="showBlockPrice()" href="javascript:void(0)"><c:out value="此节点暂无分时间段定价,点击添加"/></a>
		      	   		</c:when>
		      	   		<c:otherwise>
		      	   			<a style="color: red" onclick="showBlockPrice()" href="javascript:void(0)"><c:out value="点击查看分时间段定价表"/></a>
		      	   		</c:otherwise>
		      	   </c:choose>
		       </td>
		    </tr>
		    <tr>
		       <th class="text-right" >
		       	   <label>流量单价(分/GB)：</label>
		       </th>
		       <td>
		           <input class="small-input" name="price" value="${tbRsRTPP.price}" id="price" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"/>
		       </td>
		       
		       <th class="text-right" >
		       	   <label>专线目标IP：</label>
		       </th>
		       <td>
		           <input class="small-input" id="toIp"  name="toIp" value="${tbRsRTPP.to_ip}"/>
		       </td>
		    </tr>
		    <tr>
		       
		       <th class="text-right" >
		       	   <label>并发量上限：</label>
		       </th>
		       <td>
		           <input class="small-input" name="concurrencyLimit" value="${tbRsRTPP.concurrency_limit}" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"/>
		       </td>
		       <th class="text-right" >
		       	   <label>网络层级(<span style="color: red">*</span>)：</label>
		       </th>
		       <td>
		           <select id="netLevel" name= "netLevel" class="small-input" style="width:200px; line-height:10px;">
			           <c:choose>
			           	 <c:when test="${tbRsRTPP.net_level == 1}">
			           		<option value="0">未知网络</option>
			           		<option value="1" selected="selected">核心层</option>
			           		<option value="2">骨干层</option>
			           		<option value="3">接入层</option>
			           	 </c:when>
			           	 <c:when test="${tbRsRTPP.net_level == 2}">
			           		<option value="0">未知网络</option>
			           		<option value="1">核心层</option>
			           		<option value="2" selected="selected">骨干层</option>
			           		<option value="3">接入层</option>
			           	 </c:when>
			           	 <c:when test="${tbRsRTPP.net_level == 3}">
			           		<option value="0">未知网络</option>
			           		<option value="1">核心层</option>
			           		<option value="2">骨干层</option>
			           		<option value="3" selected="selected">接入层</option>
			           	 </c:when>
			           	 <c:otherwise>
			           		<option value="0" selected="selected">未知网络</option>
			           		<option value="1">核心层</option>
			           		<option value="2">骨干层</option>
			           		<option value="3">接入层</option>
			           	 </c:otherwise>
			           </c:choose>
            	   </select>
		       </td>
		    </tr>
		    <tr>
		       <th class="text-right" >
		       	   <label>创建时间：</label>
		       </th>
		       <td>
		           <input class="small-input" id="createTime"  name="createTime" value="<fmt:formatDate value="${tbRsRTPP.create_date}" pattern="yyyy-MM-dd HH:mm:ss" />" readonly="readonly"/>
		       </td>
		       <th class="text-right" >
		       	   <label>路由区域(<span style="color: red">*</span>)：</label>
		       </th>
		       <td>
		           <input class="small-input" id="routeArea" name="routeArea" value="${tbRsRTPP.routeArea}"/>
		           <input hidden="hidden" id="originalRouteArea" name="originalRouteArea" value="${tbRsRTPP.routeArea}"/>
		       </td>
		    </tr>
		    <tr>
		        <th class="text-right" >
		       	   <label>更新时间：</label>
		       </th>
		       <td>
		           <input class="small-input" id="updateTime" name="updateTime" value="<fmt:formatDate value="${tbRsRTPP.update_date}" pattern="yyyy-MM-dd HH:mm:ss" />" readonly="readonly"/>
		       </td>
		       
		       <th class="text-right" >
		       	   <label>是否是边界路由(<span style="color: red">*</span>)：</label>
		       </th>
		       <td>
		           <c:choose>
			           <c:when test="${tbRsRTPP.is_bdr == 1}">
			           		<span><input type="radio" name="isBdr" value="1" id="isbdr" checked="checked" />&nbsp;是</span>&nbsp;&nbsp;
				            <span><input type="radio" name="isBdr" value="0" id="nobdr"/>&nbsp;否</span>&nbsp;&nbsp;
			           </c:when>
			           <c:when test="${tbRsRTPP.is_bdr == 0}">
			           		<span><input type="radio" name="isBdr" value="1" id="isbdr"/>&nbsp;是</span>&nbsp;&nbsp;
				            <span><input type="radio" name="isBdr"  value="0" id="nobdr" checked="checked"/>&nbsp;否</span>&nbsp;&nbsp;
			           </c:when>
			       </c:choose>
		       </td>
		    </tr>
		    <tr>
		       <th class="text-right">
		       	   <label>审核时间：</label>
		       </th>
		       <td>
		           <input class="small-input" id="auditTime" name="auditTime" value="<fmt:formatDate value="${tbRsRTPP.audit_date}" pattern="yyyy-MM-dd HH:mm:ss" />" readonly="readonly"/>
		       </td>
		       <th class="text-right">
		       	   <label>工作状态(<span style="color: red">*</span>)：</label>
		       </th>
		       <td>
		       		<input type="text" value="${tbRsRTPP.rStatus}" id="Rstatus" style="display: none;"/>
		           <c:choose>
			           <c:when test="${tbRsRTPP.rStatus == 0}">
			           		<span id="normal" ><input type="radio" name="status" value="0" " checked="checked"/>初始状态&nbsp;&nbsp;&nbsp;&nbsp;</span>
			           </c:when>
			           <c:when test="${tbRsRTPP.rStatus == 1}">
				            <span ><input type="radio" name="status" value="3" />审核通过&nbsp;&nbsp;&nbsp;&nbsp;</span>
				            <span ><input type="radio" name="status" value="1"  checked="checked"/>审核中&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<span ><input type="radio" name="status" value="2" />审核失败&nbsp;&nbsp;&nbsp;&nbsp;</span>
			           </c:when>
			           <c:when test="${tbRsRTPP.rStatus == 2}">
			           		<span ><input type="radio" name="status" value="1"/>重新审核&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<span ><input type="radio" name="status" value="2"  checked="checked"/>审核失败&nbsp;&nbsp;&nbsp;&nbsp;</span>
			           </c:when>
			           <c:when test="${tbRsRTPP.rStatus == 3}">
							<span ><input type="radio" name="status" value="3"  checked="checked"/>就绪状态&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<span ><input type="radio" name="status" value="4" />暂停&nbsp;&nbsp;&nbsp;&nbsp;</span>
			           </c:when>
			           <c:when test="${tbRsRTPP.rStatus == 4}">
			           		<span ><input type="radio" name="status" value="3" />就绪状态&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<span ><input type="radio" name="status" value="4"  checked="checked"/>暂停&nbsp;&nbsp;&nbsp;&nbsp;</span>
			           </c:when>
			           <c:when test="${tbRsRTPP.rStatus == 5}">
							<span ><input type="radio" name="status" value="4" />暂停&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<span ><input type="radio" name="status" value="5" checked="checked"/> 离线状态&nbsp;&nbsp;&nbsp;&nbsp;</span>
			           </c:when>
			           <c:when test="${tbRsRTPP.rStatus == 6}">
							<span ><input type="radio" name="status" value="4" />暂停&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<span ><input type="radio" name="status" value="6" checked="checked"/> 在线状态&nbsp;&nbsp;&nbsp;&nbsp;</span>
			           </c:when>
			       </c:choose>    
		       </td>
		    </tr>
		    <tr>
		       <th class="text-right">
		       	  <label>备注：</label>
		       </th>
		       <td>
		          <textarea name="note" class="input" style="height:80px; border:1px solid #ddd;" id="note">${tbRsRTPP.note}</textarea>
		       </td>
		    </tr>
	    </table>
    </form>
    <div class="form-group" style="text-align: center;padding-top: 40px;">
      <div class="field">
        <button id="editBtn" class="button bg-main icon-edit" type="button" > 修改</button>   
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

	
	$('#editBtn').click(function(){
		//ip地址正则表达式
		var ip = /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/g;
		var fromIpReg = /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/g;
		var signIpReg = /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/g;
		var mediaIpReg = /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/g;
		var pingIpReg = /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/g;
		var nameReg=/^[0-9a-zA-Z_.]*$/;
		var portReg=/^[1-9]$|(^[1-9][0-9]$)|(^[1-9][0-9][0-9]$)|(^[1-9][0-9][0-9][0-9]$)|(^[1-6][0-5][0-5][0-3][0-5]$)/;
		var ipId = $('#ip').val();
		var toIpId = $('#toIp').val();
		var price = $('#price').val();
		var name = $('#name').val();
		var netSid = $('#netSid').val();
		var netName = $('#netName').val();
		var routeArea = $('#routeArea').val();
		var fromIp = $('#fromIp').val();
		var signIp = $('#signIp').val();
		var mediaIp = $('#mediaIp').val();
		var pingIp = $('#pingIp').val();
		
		if(netSid=="" || netName==""){
			window.wxc.xcConfirm("请选择资源方", window.wxc.xcConfirm.typeEnum.info);
			return false;
		}
		
		if(name==""){
			window.wxc.xcConfirm("节点名称不能为空", window.wxc.xcConfirm.typeEnum.info);
			return false;
		}
		if(!nameReg.test(name)){
			window.wxc.xcConfirm("节点名称只能输入字母、数字、 ‘.' 、‘_'", window.wxc.xcConfirm.typeEnum.info);
			return false;
		}
		if(!ip.test(ipId)){
			window.wxc.xcConfirm("请输入正确的节点IP地址格式", window.wxc.xcConfirm.typeEnum.info);
			return false;
		}
		if(fromIp !="" && !fromIpReg.test(fromIp) && fromIp != "0.0.0.0"){
			window.wxc.xcConfirm("请输入正确的tcp来源IP格式", window.wxc.xcConfirm.typeEnum.info);
			return false;
		}
		if(mediaIp !="" && !mediaIpReg.test(mediaIp) && "0.0.0.0" != mediaIp){
			window.wxc.xcConfirm("请输入正确的收发媒体IP格式", window.wxc.xcConfirm.typeEnum.info);
			return false;
		}
		if(pingIp !="" && !pingIpReg.test(pingIp) && pingIp !="0.0.0.0"){
			window.wxc.xcConfirm("请输入正确的探测UPDping包IP格式", window.wxc.xcConfirm.typeEnum.info);
			return false;
		}
		if(signIp !=""){
			var address = signIp.split(":");
			if(address.length != 2){
				window.wxc.xcConfirm("请输入正确的接收UDP信令IP格式", window.wxc.xcConfirm.typeEnum.info);
				return false;
			}
			signIp = address[0];
			if(!signIpReg.test(signIp)  && "0.0.0.0" != signIp){
				window.wxc.xcConfirm("请输入正确的接收UDP信令IP格式", window.wxc.xcConfirm.typeEnum.info);
				return false;
			}
			var signPort = address[1];
			if(!portReg.test(signPort)){
				window.wxc.xcConfirm("请输入正确的接收UDP信令IP端口格式", window.wxc.xcConfirm.typeEnum.info);
				return false;
			}
		}
		
		if(toIpId!=""){
			var toIpIdList = toIpId.split(","); 
			for(var i = 0; i < toIpIdList.length;i++){
				var ipReg = /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/g;
				var tempIp = toIpIdList[i];
				if(!ipReg.test(tempIp)){
					window.wxc.xcConfirm("请输入正确格式的专线IP", window.wxc.xcConfirm.typeEnum.info);
					return false;
				}
			}
		}
		if(price == ""){
			window.wxc.xcConfirm("价格不能为空", window.wxc.xcConfirm.typeEnum.info);
			return false;
		}
		if(routeArea==""){
			window.wxc.xcConfirm("路由区域不能为空", window.wxc.xcConfirm.typeEnum.info);
			return false;
		}
		$.ajax({
			url:"/resourceController/editResource.action",
			data:$('#formInfo').serializeArray(),
			type:"post",
			contentType: "application/x-www-form-urlencoded;charset=utf-8",
			dataType:'json',
			success : function(data) { 
	        	var json = eval("("+data+")");
	        	if(json == 0){
	        		window.wxc.xcConfirm("修改失败", window.wxc.xcConfirm.typeEnum.info);
					
	        	}
	        	if(json == 1){
	        		window.wxc.xcConfirm("修改成功", window.wxc.xcConfirm.typeEnum.info,{
	        			onOk:function(v){
		        			window.location.href="/resourceController/queryResourceFenYe.action";
	        			}
	        		});
	        	}
	        	if(json == 2){
	        		window.wxc.xcConfirm("修改失败，域内已存在该节点", window.wxc.xcConfirm.typeEnum.info);
	        	}
	        	if(json == 3){
	        		window.wxc.xcConfirm("暂停失败，节点目前正在被使用", window.wxc.xcConfirm.typeEnum.info);
	        	}
	        },
	        error:function(code){   
	        	window.wxc.xcConfirm("Sorry.服务器出错....", window.wxc.xcConfirm.typeEnum.info);
	        }   
		}) 
	})

	$('#country').change(function(){
		$('#province').empty();
		$('#city').empty();
		var countryId = $('#country option:selected').val();
		$.post("/cityController/queryProvinceByCountryId?countryId="+countryId,function(data){
			var obj = eval("(" + data + ")");
			var json = eval("(" + obj + ")");
			/* if(json.length == 1 && json[0].privinceid == undefined || json[0].privinceid == 0){
				$('#province').append("<option value = '0'>"+'暂无信息'+"</option>"); 
			}else{
				$.each(json, function (index, item) {  
					$('#province').append("<option value = '"+item.provinceid+"'>"+item.province+"</option>"); 
				});
			} */
			$.each(json, function (index, item) {  
				$('#province').append("<option value = '"+item.provinceid+"'>"+item.province+"</option>"); 
			});
			$('#province').change();
		})
	})

	$('#province').change(function(){
		$('#city').empty();
		var provinceId = $('#province option:selected').val();
		$.post("/cityController/queryCityBypId?provinceId="+provinceId,function(data){
			var obj = eval("(" + data + ")");
			var json = eval("(" + obj + ")");
			/* if(json.length == 1 && json[0].cityid == undefined || json[0].cityid == 0){
				$('#city').append("<option value = '0'>"+'暂无信息'+"</option>"); 
			}else{
				$.each(json, function (index, item) {  
					$('#city').append("<option value = '"+item.cityid+"'>"+item.city+"</option>"); 
				});
			} */
			$.each(json, function (index, item) {  
				$('#city').append("<option value = '"+item.cityid+"'>"+item.city+"</option>"); 
			});
		})
	})
</script>
</body>
</html>