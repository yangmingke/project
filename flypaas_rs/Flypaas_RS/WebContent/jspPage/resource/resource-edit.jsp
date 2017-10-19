<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
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
<title>添加资源</title>
</head>
<body>
<div class="pd-20">
  <div class="Huiform">
    <form action="${ctx}/resourceController/editResource.action" method="post"  id="formId">
      <table class="table table-bg" style="font-size: 12px;">
        <tbody>
           <tr style="display: none;">
            <th width="250" class="text-r">资源ID：</th> 
            <td><input type="text" style="width:400px" class="input-text" value="<%=request.getParameter("id") %>" placeholder="" id="Id" name="rtppSid" readonly="readonly"></td>
          	<td><input type="text" value="<%=request.getParameter("countryId") %>" id="countryId"/> </td>
          	<td><input type="text" value="<%=request.getParameter("provinceId") %>" id="provinceId"/> </td>
          </tr>
          <tr>
            <th class="text-r">资源名称：</th> 
            <td><input type="text" style="width:400px" class="input-text" value="" placeholder="" id="nameId" name="name"></td>
          </tr> 
          <tr>
            <th class="text-r">节点公网IP：</th>
            <td><input type="text" style="width:400px" class="input-text" value="" placeholder="" id="ipId" name="ip1" readonly="readonly"></td>
          </tr>
          <tr style="display: none;">
            <th class="text-r">节点公网IP：</th>
            <td><input type="text" style="width:400px" class="input-text" value="" placeholder="" id="ipId1" name="ip"></td>
          </tr>
         <!--  <tr>
            <th class="text-r">节点主网IP：</th>
            <td>
            	<input type="text" style="width:400px" class="input-text" value="" placeholder="" id="mainIPId" name="mainIp">
            </td>
          </tr> -->
          <!-- <tr>
            <th class="text-r">子网掩码：</th>
            <td><input type="text" style="width:400px" class="input-text" value="" placeholder="" id="user-address" name="address"></td>
          </tr> -->
          <tr>
            <th class="text-r">运营商：</th>
             <td>
             	<select class="select" size="1" name="operator" id="operatorId" style="width: 200px;">
             		<option value="0" selected>--请选择运营商--</option>
				</select>
			</td>
          </tr>
          <tr>
            <th class="text-r">节点类型：</th>
             <td>
             	<select class="select" size="1" name="type" id="typeId" style="width: 200px;">
					<option value="0" selected>--请选择节点类型--</option>
					<option value="1">CDN（TCP+UDP）</option>
					<option value="2">CDN（TCP only）</option>
					<option value="3">IDC</option>
					<option value="4">云平台</option>
					<option value="5">运营商</option>
					<option value="6">自营</option>
				</select>
			</td>
          </tr>
          <!-- <tr>
            <th class="text-r">网络层级：</th>
             <td>
             	<select class="select" size="1" name="netLevel" id="netLevelId" style="width: 150px;">
					<option value="0" selected>--请选择网络层级--</option>
					<option value="1">核心层</option>
					<option value="2">骨干层</option>
					<option value="3">接入层</option>
				</select>
			</td>
          </tr> -->
         <tr>
            <th class="text-r">地区：</th>
             <td>
             	<select class="select" size="1" name="country" id="country" style="width: 100px;" >
				</select>&nbsp;&nbsp;
				<select class="select" size="1" name="province" id="province" style="width: 100px;">
				</select>&nbsp;&nbsp;
				<select class="select" size="1" name="zone" id="city" style="width: 100px;">
				</select>
			</td>
          </tr>
          <tr>
            <th class="text-r">流量单价(分/GB)：</th>
            <td>
            	<input type="text" style="width:400px" class="input-text" value="" placeholder="" id="priceId" name="price" onkeyup='this.value=this.value.replace(/\D/gi,"")'/>
            </td>
          </tr>
          <tr>
            <th class="text-r">是否分段定价</th>
            <td>
            	<input type="radio" name="block_Price"  id="Y" onclick="time_limit()" value="1"/> 是
            	&nbsp;&nbsp;&nbsp;&nbsp;
            	<input type="radio" name="block_Price"  id="N" checked="checked" onclick="time_limit1()" value="0"/> 否
            	<div style="float: right">
            		<a onclick="add()">添加</a>&nbsp;&nbsp;&nbsp;&nbsp;
            		<a onclick="del()">删除</a>
            	</div>
            	<!-- <input type="text" style="width:400px" class="input-text" value="" placeholder="" id="blockpriceId" name="blockPrice"/> -->
            </td>
          </tr>
          <tr id="blockTr" style="display: none;">
          	<th class="text-r"></th>
          	<td>
          	 <div id="d1">
          		 <input id="count" hidden="hidden" value="1" name="index">
          		
            </div>
            </td>
          </tr>
          <!-- <tr>
            <th class="text-r">分段定价(分/KB)：</th>
            <td>
            	<input type="text" style="width:400px" class="input-text" value="" placeholder="" id="blockpriceId" name="blockPrice"/>
            </td>
          </tr> -->
         <tr>
            <th class="text-r">是否有专线：</th>
            <td>
            	<input type="radio" id="radio-1" name="isToLine" value="1"  onclick="time_limit2()"> 有
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" id="radio-2" name="isToLine" value="0" onclick="time_limit3()" checked="checked"> 没有
            </td>
          </tr>
          <tr id="toI" hidden="hide">
            <th class="text-r">专线IP：</th>
            <td>
            	<input type="text" style="width:400px" class="input-text" value="" placeholder="" id="toIpId" name="toIp" />
            </td>
          </tr>
           <tr>
            <th class="text-r">带宽上限(MB)：</th>
            <td>
            	<input type="text" style="width:400px" class="input-text" value="" placeholder="" id="bandwidthLimitId" name="bandwidthLimit" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
            </td>
          </tr>
          <!--  <tr>
            <th class="text-r">并发量上线：</th>
            <td>
            	<input type="text" style="width:400px" class="input-text" value="" placeholder="" id="concurrencyLimitId" name="concurrencyLimit"  onkeyup='this.value=this.value.replace(/\D/gi,"")'>
            </td>
          </tr> -->
          <tr>
            <th></th>
            <td style="padding:20px 0 0 130px;"><button class="btn btn-success radius" type="button" id="onSub"><i class="icon-ok"></i> 确定</button></td>
          </tr>
        </tbody>
      </table>
    </form>
  </div>
</div>
<p style="color: red; padding:0px 0 0 221px;">  确定提交后需要重新进行审核</p>

<script type="text/javascript" src="${ctx}/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${ctx}/layer/layer.min.js"></script>
<script type="text/javascript" src="${ctx}/js/resource/editResource.js"></script>
<script type="text/javascript" src="${ctx}/js/Validform_v5.3.2_min.js"></script>
<%-- <script type="text/javascript" src="${ctx}/js/H-ui.js"></script>  --%>
<script type="text/javascript" src="${ctx}/js/H-ui.admin.js"></script> 
<script type="text/javascript">
function add(){
	if($("#Y").attr("checked")=="checked"){
		var index = $('#count').val();
		index++;
		$('#count').val(index);
		var str = '<div id="div' + index + '"><select style="width: 100px" name="startTime'+ index + '" id="startTime' + index +'">';
		str = str + '<option value="" selected="selected">--请选择时间段--</option>';
		str = str + '<option value="0000">00:00</option><option value="0100">01:00</option><option value="0200">02:00</option><option value="0300">03:00</option>';
		str = str + '<option value="0400">04:00</option><option value="0500">05:00</option><option value="0600">06:00</option><option value="0700">07:00</option>';
		str = str + '<option value="0800">08:00</option><option value="0900">09:00</option><option value="1000">10:00</option><option value="1100">11:00</option>';
		str = str + '<option value="1200">12:00</option><option value="1300">13:00</option><option value="1400">17:00</option><option value="1500">15:00</option>';
		str = str + '<option value="1600">16:00</option><option value="1700">17:00</option><option value="1800">18:00</option><option value="1900">19:00</option>';
		str = str + '<option value="2000">20:00</option><option value="2100">21:00</option><option value="2200">22:00</option><option value="2300">23:00</option>';
		str = str + '</select>&nbsp;--&nbsp&nbsp';
		str = str + '<select style="width: 100px" name="endTime'+ index + '" id="endTime' + index +'">';
		str = str + '<option value="" selected="selected">--请选择时间段--</option>';
		str = str + '<option value="0000">00:00</option><option value="0100">01:00</option><option value="0200">02:00</option><option value="0300">03:00</option>';
		str = str + '<option value="0400">04:00</option><option value="0500">05:00</option><option value="0600">06:00</option><option value="0700">07:00</option>';
		str = str + '<option value="0800">08:00</option><option value="0900">09:00</option><option value="1000">10:00</option><option value="1100">11:00</option>';
		str = str + '<option value="1200">12:00</option><option value="1300">13:00</option><option value="1400">17:00</option><option value="1500">15:00</option>';
		str = str + '<option value="1600">16:00</option><option value="1700">17:00</option><option value="1800">18:00</option><option value="1900">19:00</option>';
		str = str + '<option value="2000">20:00</option><option value="2100">21:00</option><option value="2200">22:00</option><option value="2300">23:00</option>';
		str = str + '</select>&nbsp;--&nbsp&nbsp';
		str = str + '<input type="text" name="price'+ index + '" placeholder="请填入价格" style="width: 70px" id="price'+ index + '"/><div><br/></div></div>';
		$('#d1').append(str);
	}
}
function del(){
	if($('#Y').attr("checked")=="checked"){
		var index = $('#count').val();
		/* $('#startTime' + index).remove();
		$('#endTime' + index).remove();
		$('#price' + index).remove(); */
		$('#div' + index).remove(); 
		index--;
		$('#count').val(index);
	}
}
$.fn.serializeObject = function()    
{    
   var o = {};    
   var a = this.serializeArray();    
   $.each(a, function() {    
       if (o[this.name]) {    
           if (!o[this.name].push) {    
               o[this.name] = [o[this.name]];    
           }    
           o[this.name].push(this.value || '');    
       } else {    
           o[this.name] = this.value || '';    
       }    
   });    
   return o;    
};  

$('#onSub').click(function(){
	//ip地址正则表达式
	var ip = /^(\d+)\.(\d+)\.(\d+)\.(\d+)$/g;
	var ip1 = /^(\d+)\.(\d+)\.(\d+)\.(\d+)$/g;
	var nameReg=/^[0-9a-zA-Z_.]*$/;
	//isNaN($('#id').val());
	var name = $('#nameId').val();
	var ipId = $('#ipId').val();
	var mainIPId = $('#mainIPId').val();
	var toIpId = $('#toIpId').val();
	
	var price = Number($('#priceId').val());
	var bandwidthLimitId = $('#bandwidthLimitId').val();
	var concurrencyLimitId = $('#concurrencyLimitId').val();
	if(name==""){
		alert("资源名称不能为空");
		return false;
	}
	if(!nameReg.test(name)){
		alert("节点名称只能输入字母、数字、 ‘.' 、‘_'");
		return false;
	}
	if(!ip.test(ipId)){
		alert("请输入正确格式的公网IP");
		return false;
	}
	/* if(!ip1.test(mainIPId)){
		alert("请输入正确格式的主网IP");
		return false;
	} */
	/* if(toIpId!=""&&!ip2.test(toIpId)){
		alert("请输入正确格式的专线IP");
		return false;
	} */
	if(toIpId!=""){
		var toIpIdList = toIpId.split(","); 
		for(var i = 0; i < toIpIdList.length;i++){
			var ipReg = /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/g;
			var tempIp = toIpIdList[i];
			if(!ipReg.test(tempIp)){
				alert("请输入正确格式的专线IP");
				return false;
			}
		}
	}
	if(price == ""){
		alert("价格不能为空");
		return false;
	}
	var ipId1 = $('#ipId1').val();
	if(ipId1 == ipId){
		$.ajax({
				url:"/resourceController/editResource.action",
				data:$('#formId').serializeArray(),
				type:"post",
				contentType: "application/x-www-form-urlencoded;charset=utf-8",
				dataType:'json',
				success : function(data) { 
		        	var json = eval("("+data+")");
		        	if(json == 0){
		        		layer.msg('修改失败!',1,2,function(){
		        			window.close();
			        		window.parent.location.reload(true);
		        		});
						
		        	}
		        	if(json == 1){
		        		layer.msg('修改成功!',1,1,function(){
		        			window.close();
			        		window.parent.location.reload(true);
		        		});
						
		        	}
		        },
		        error:function(code){   
		        	layer.msg('服务器出错!',1,3,function(){
		    			window.close();
		        		window.parent.location.reload(true);
		    		});
		        }   
			}) 
	}else{
		$.post("/resourceController/checkIp.action",{"ip":ipId},function(data){
			var obj = eval('('+data+')')
			if(obj == 1){
				$.ajax({
					url:"/resourceController/editResource.action",
					data:$('#formId').serializeArray(),
					type:"post",
					contentType: "application/x-www-form-urlencoded;charset=utf-8",
					dataType:'json',
					success : function(data) { 
			        	var json = eval("("+data+")");
			        	if(json == 0){
			        		layer.msg('修改失败!',1,2,function(){
			        			window.close();
				        		window.parent.location.reload(true);
			        		});
							
			        	}
			        	if(json == 1){
			        		layer.msg('修改成功!',1,1,function(){
			        			window.close();
				        		window.parent.location.reload(true);
			        		});
							
			        	}
			        },
			        error:function(code){   
			        	layer.msg('服务器出错!',1,3,function(){
			    			window.close();
			        		window.parent.location.reload(true);
			    		});
			        }   
				}) 
			}else{
				alert("修改失败,此节点IP已经存在");
			}
		})
	}
	
})

function time_limit(){
	$("#Y").attr("checked",true);
	$("#N").attr("checked",false);
	$('#blockTr').show();
}
function time_limit1(){
	$('#blockTr').hide();
}
function time_limit2(){
	$('#toI').show();
}
function time_limit3(){	
	$('#toI').hide();
}
</script>

</body>
</html>