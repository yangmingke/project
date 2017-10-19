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
    	<input type="text" value="${tbRsRTPP.rtpp_sid}" id="rtppSid" name="rtppSid" style="display: none;"/>
	    <table width="90%">
		    <tr>
		       <th class="text-right">
		       	   <label>资源方ID：</label>
		       </th>
		       <td>
		           <input class="small-input" id="netSid" name="netSid" value="${resourceSide.netSid}" readonly="readonly"/>
		       </td>
		        <th class="text-right">
		       	   <label>资源方公司简称：</label>
		       </th>
		       <td>
		           <select id="netName" class="small-input" style=" line-height:10px;">
		           	   <option value="">------请选择资源方------</option>
			           <c:forEach var="rsNameList" items="${rsNameList}">
		           			<option value="${rsNameList.netSid}">${rsNameList.username}</option>
			           </c:forEach>
            	   </select>
		       </td>
		    </tr>   
		    <tr>
		       <th class="text-right">
		       	   <label>节点名称：</label>
		       </th>
		       <td>
		           <input class="small-input" name="name" id="name" value="${tbRsRTPP.name}"/>
		       </td>
		       <th class="text-right">
		       	   <label>节点IP地址：</label>
		       </th>
		       <td>
		           <input class="small-input" name="ip" value="${tbRsRTPP.ip}" id="ip"/>
		       </td>
		    </tr>  
		    <tr>
		       <th class="text-right">
		       	   <label>运营商：</label>
		       </th>
		       <td>
		           <select id="operator" class="small-input" style="width:200px; line-height:10px;" name="operator">
			           <c:forEach var="ispList" items="${ispList}">
			           		<c:if test="${ispList.ispid == tbRsRTPP.ispid}">
			           			<option value="${ispList.ispid}" selected="selected">${ispList.name}</option>
			           		</c:if>
			           		<option value="${ispList.ispid}">${ispList.name}</option>
			           </c:forEach>
            	   </select>
            	   &nbsp; &nbsp; &nbsp;
            	   <label>节点类型：</label>
            	   <select class="small-input" size="1" name="type" style="width: 200px;">
						<option value="0" selected>--请选择节点类型--</option>
						<option value="1">CDN（TCP+UDP）</option>
						<option value="2">CDN（TCP only）</option>
						<option value="3">IDC</option>
						<option value="4">云平台</option>
						<option value="5">运营商</option>
						<option value="6">自营</option>
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
		       <th class="text-right" >
		       	   <label>流量单价(分/GB)：</label>
		       </th>
		       <td>
		           <input class="small-input" name="price" id="price" value="${tbRsRTPP.price}" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"/>
		       </td>
		    </tr>
	    	<tr>
		       <th class="text-right" >
		       	   <label>专线目标IP：</label>
		       </th>
		       <td>
		           <input class="small-input" id="toIp"  name="toIp" value="${tbRsRTPP.to_ip}"/>
		       </td>
		       <th class="text-right">
		       	   <label>分时间段定价:</label>
		       	   <input type="text" value="${tbRsRTPP.block_price}"  id="blockPrice" name="block_price" style="display: none;"/>
		       </th>
		       <td>
		      	   	<a onclick="add()">&nbsp;&nbsp;&nbsp;&nbsp;添加时间段</a>&nbsp;&nbsp;&nbsp;&nbsp;
	            	<a onclick="del()">删除时间段</a>
		          	<div id="d1">
		          		 <input id="count" hidden="hidden" value="1" name="index">
		            </div>
	          </tr>  
	          <tr>
		       <th class="text-right" >
		       	   <label>路由区域：</label>
		       </th>
		       <td>
		           <input class="small-input" id="routeArea" name="routeArea"/>
		       </td>
	          </tr>
		      	   	<%-- <c:choose>
		      	   		<c:when test="${blockPrice == null || blockPrice == ''}">
		      	   			<a onclick="addBlockPrice()" href="javascript:void(0)"><c:out value="点击添加分时间段定价"/></a>
		      	   		</c:when>
		      	   		<c:otherwise>
		      	   			<a onclick="addBlockPrice()" href="javascript:void(0)"><c:out value="点击添加分时间段定价"/></a>
		      	   		</c:otherwise>
		      	   	</c:choose> --%>
		    <%-- <tr>
		       
		       <th class="text-right" >
		       	   <label>并发量上线：</label>
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
		       	   <label>路由区域(<span style="color: red">*</span>)：</label>
		       </th>
		       <td>
		           <input class="small-input" id="routeArea" name="routeArea" value="${tbRsRTPP.routeArea}"/>
		       </td>
		       <th class="text-right">
		       	   <label>工作状态(<span style="color: red">*</span>)：</label>
		       </th>
		       <td>
		       		<input type="text" value="${tbRsRTPP.rStatus}" id="Rstatus" style="display: none;"/>
			        <span id="normal" ><input type="radio" name="status" value="0" id="normal1"/>正常工作&nbsp;&nbsp;&nbsp;&nbsp;</span>
				    <span id="auditing"><input type="radio" name="status" value="1" id="auditing1" checked="checked"/>审核中...&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<span id="failPass"><input type="radio" name="status" value="2" id="failPass1"/>审核不通过&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<span id="fault"><input type="radio" name="status" value="3" id="fault1"/>故障&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<span id="suspend"><input type="radio" name="status" value="4" id="suspend1"/>暂停&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<span id="stop"><input type="radio" name="status" value="5" id="stop1"/>下线&nbsp;&nbsp;&nbsp;&nbsp;</span>
		       </td>
		    </tr>
		    <tr>
		       
		       <th class="text-right" >
		       	   <label>是否是边界路由(<span style="color: red">*</span>)：</label>
		       </th>
		       <td>
			        <span><input type="radio" name="isBdr" value="1" id="isbdr"/>&nbsp;是</span>&nbsp;&nbsp;
				    <span><input type="radio" name="isBdr" value="0" id="nobdr"  checked="checked" />&nbsp;否</span>&nbsp;&nbsp;
		       </td>
		       
		    </tr> --%>
	    </table>
    </form>
    <div class="form-group" style="text-align: center;padding-top: 40px;">
      <div class="field">
        <button id="addBtn" class="button bg-main icon-edit" type="button" > 提交</button>   
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <button id="back" class="button bg-main icon-undo" type="button" onclick="back()"> 返回</button>
      </div>
    </div>
    </div>
</div>
<script type="text/javascript">
//--------------------------------------------返回按钮----------------------------------------------------//
	function back(){
		window.history.go(-1);
	}

//--------------------------------------------提交表单---------------------------------------------------//		
	$('#addBtn').click(function(){
		//ip地址正则表达式
		var ip = /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/g;
		//var ip1 = /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/g;
		var nameReg=/^[0-9a-zA-Z_.]*$/;
		var ipId = $('#ip').val();
		var toIpId = $('#toIp').val();
		var price = $('#price').val();
		var name = $('#name').val();
		var netSid = $('#netSid').val();
		var netName = $('#netName').val();
		var routeArea = $('#routeArea').val();
		
		if(netSid=="" || netName==""){
			window.wxc.xcConfirm("请选择资源方", window.wxc.xcConfirm.typeEnum.info);
			return false;
		}
		if(!nameReg.test(name)){
			window.wxc.xcConfirm("节点名称只能输入字母、数字、 ‘.' 、‘_'", window.wxc.xcConfirm.typeEnum.info);
			return false;
		}
		if(name==""){
			window.wxc.xcConfirm("节点名称不能为空", window.wxc.xcConfirm.typeEnum.info);
			return false;
		}
		if(!ip.test(ipId)){
			window.wxc.xcConfirm("请输入正确的节点IP地址格式", window.wxc.xcConfirm.typeEnum.info);
			return false;
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
		/*$.post("/resourceController/checkIp.action",{"ip":ipId},function(data){
				 var obj = eval('('+data+')')
				if(obj == 1){ */
		$.ajax({
			url:"/resourceController/addResource.action",
			data:$('#formInfo').serializeArray(),
			type:"post",
			contentType: "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) { 
	        	var json = eval("("+data+")");
	        	if(json == 0){
	        		window.wxc.xcConfirm("添加失败", window.wxc.xcConfirm.typeEnum.info);
	        	}
	        	if(json == 1){
	        		window.wxc.xcConfirm("添加成功！", window.wxc.xcConfirm.typeEnum.info,{
	        			onOk:function(v){
	        				window.location.href ="/resourceController/queryResourceFenYe.action";
	        			}
	        		});
	        	}
	        	if(json == 2){
	        		window.wxc.xcConfirm("添加失败，已存在该节点IP", window.wxc.xcConfirm.typeEnum.info);
	        	}
	        },
	        error:function(code){   
	        	window.wxc.xcConfirm("Sorry.服务器出错", window.wxc.xcConfirm.typeEnum.info);
	        }   
		});
				/* }
				if(obj == 0){
					window.wxc.xcConfirm("添加失败,此IP已经存在", window.wxc.xcConfirm.typeEnum.info);
					return false;
				} 
		})*/
		
	})

	
	function add(){
		var index = $('#count').val();
		var str = '<div id="div' + index + '"><label>时间段'+index+':</label>&nbsp;&nbsp;<select style="width: 150px;height:30px;" name="startTime'+ index + '" id="startTime'+ index + '">';
		str = str + '<option value="" selected="selected">--请选择时间段--</option>';
		str = str + '<option value="0000">00:00</option><option value="0100">01:00</option><option value="0200">02:00</option><option value="0300">03:00</option>';
		str = str + '<option value="0400">04:00</option><option value="0500">05:00</option><option value="0600">06:00</option><option value="0700">07:00</option>';
		str = str + '<option value="0800">08:00</option><option value="0900">09:00</option><option value="1000">10:00</option><option value="1100">11:00</option>';
		str = str + '<option value="1200">12:00</option><option value="1300">13:00</option><option value="1400">17:00</option><option value="1500">15:00</option>';
		str = str + '<option value="1600">16:00</option><option value="1700">17:00</option><option value="1800">18:00</option><option value="1900">19:00</option>';
		str = str + '<option value="2000">20:00</option><option value="2100">21:00</option><option value="2200">22:00</option><option value="2300">23:00</option>';
		str = str + '</select>&nbsp;--&nbsp;';
		str = str + '<select style="width: 150px;height:30px;" name="endTime'+ index + '" id="endTime' + index +'">';
		str = str + '<option value="" selected="selected">--请选择时间段--</option>';
		str = str + '<option value="0000">00:00</option><option value="0100">01:00</option><option value="0200">02:00</option><option value="0300">03:00</option>';
		str = str + '<option value="0400">04:00</option><option value="0500">05:00</option><option value="0600">06:00</option><option value="0700">07:00</option>';
		str = str + '<option value="0800">08:00</option><option value="0900">09:00</option><option value="1000">10:00</option><option value="1100">11:00</option>';
		str = str + '<option value="1200">12:00</option><option value="1300">13:00</option><option value="1400">17:00</option><option value="1500">15:00</option>';
		str = str + '<option value="1600">16:00</option><option value="1700">17:00</option><option value="1800">18:00</option><option value="1900">19:00</option>';
		str = str + '<option value="2000">20:00</option><option value="2100">21:00</option><option value="2200">22:00</option><option value="2300">23:00</option>';
		str = str + '</select>&nbsp;--&nbsp;';
		str = str + '<input type="text" name="price'+ index + '" placeholder="请填入价格" style="width: 100px;height:30px;" id="price'+ index + '"/>(分/GB)</div>';
		index++;
		$('#count').val(index);
		$('#d1').append(str);
	}
	function del(){
		var index = $('#count').val();
		if(index == 1){
			index = index;
		}else{
			index--;
		}
		$('#div' + index).remove(); 
		$('#count').val(index);
	}
//----------------------------------------------------省  市 区三级联动-----------------------------------------------------//
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
	$('#country').change();
	
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
	
	$('#netName').change(function(){
		var netSid = $(this).val();
		$('#netSid').val(netSid);
	});
	
</script>
</body>
</html>