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
    <form action="${ctx}/resourceController/editResource.action" method="post" onsubmit="return toVaild()">
      <table class="table table-bg" style="font-size: 12px;">
        <tbody>
           <tr style="display: none;">
            <th width="150" class="text-r">资源ID：</th> 
            <td><input type="text" style="width:300px" class="input-text" value="<%=request.getParameter("id") %>" placeholder="" id="Id" name="rtppSid" readonly="readonly"></td>
          	<td><input type="text" value="<%=request.getParameter("countryId") %>" id="countryId"/> </td>
          	<td><input type="text" value="<%=request.getParameter("provinceId") %>" id="provinceId"/> </td>
          </tr>
          <tr>
            <th class="text-r">资源名称：</th> 
            <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="nameId" name="name"></td>
          </tr> 
          <tr>
            <th class="text-r">节点公网IP：</th>
            <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="ipId" name="ip"></td>
          </tr>
          <!-- <tr>
            <th class="text-r">节点主网IP：</th>
            <td>
            	<input type="text" style="width:300px" class="input-text" value="" placeholder="" id="mainIPId" name="mainIp">
            </td>
          </tr> -->
          <!-- <tr>
            <th class="text-r">子网掩码：</th>
            <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="user-address" name="address"></td>
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
         <!--  <tr>
            <th class="text-r">网络层级：</th>
             <td>
             	<select class="select" size="1" name="netLevel" id="netLevelId">
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
					<option value="0" selected>--请选择国家--</option>
				</select>&nbsp;&nbsp;
				<select class="select" size="1" name="province" id="province" style="width: 100px;">
					<option value="0" selected >--请选择省份--</option>
				</select>&nbsp;&nbsp;
				<select class="select" size="1" name="zone" id="city" style="width: 100px;">
					<option value="0" selected>--请选择城市--</option> 
				</select>
			</td>
          </tr>
          <tr>
            <th class="text-r">流量单价(分/GB)：</th>
            <td>
            	<input type="text" style="width:300px" class="input-text" value="" placeholder="" id="priceId" name="price" onkeyup='this.value=this.value.replace(/\D/gi,"")'/>
            </td>
          </tr>
          <tr>
            <th class="text-r">是否分段定价</th>
            <td>
            	<input type="radio" name="block_Price"  id="Y" onclick="time_limit()" value="1"/> 是
            	&nbsp;&nbsp;&nbsp;&nbsp;
            	<input type="radio" name="block_Price"  id="N" checked="checked" onclick="time_limit1()" value="0"/> 否
            	<!-- <input type="text" style="width:300px" class="input-text" value="" placeholder="" id="blockpriceId" name="blockPrice"/> -->
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
            	<input type="text" style="width:300px" class="input-text" value="" placeholder="" id="blockpriceId" name="blockPrice"/>
            </td>
          </tr> -->
         <tr>
            <th class="text-r">是否有专线：</th>
            <td>
            	<input type="radio" id="radio-1" name="isToLine" value="1" onclick="time_limit2()"> 有
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" id="radio-2" name="isToLine" value="0"  checked="checked"  onclick="time_limit3()"> 没有
            </td>
          </tr>
          <tr id="toI" hidden="hide">
            <th class="text-r">专线IP：</th>
            <td>
            	<input type="text" style="width:300px" class="input-text" value="" placeholder="" id="toIpId" name="toIp" />
            </td>
          </tr>
           <tr>
            <th class="text-r">带宽上限(MB)：</th>
            <td>
            	<input type="text" style="width:300px" class="input-text" value="" placeholder="" id="bandwidthLimitId" name="bandwidthLimit" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
            </td>
          </tr>
           <!-- <tr>
            <th class="text-r">并发量上线：</th>
            <td>
            	<input type="text" style="width:300px" class="input-text" value="" placeholder="" id="concurrencyLimitId" name="concurrencyLimit"  onkeyup='this.value=this.value.replace(/\D/gi,"")'>
            </td>
          </tr> -->
        </tbody>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">

$(function(){
	fun1();//查询所有国家列表（）
    fun2();//根据默认国家查询其所有省份列表
	fun3();//查询省份下面的市
	fun5();//查询所有运营商
	fun4();//节点赋值
});

 function fun1(){
	 $('#country').empty();
		$.ajax({
			url:"/cityController/queryAllCountryAjax.action",
			dataType:"json",
			async:false,
			success:function (data){
				var json = eval("(" +data + ")");
				$.each(json, function (index, item) {  
					$('#country').append("<option value = '"+item.countryid+"'>"+item.country+"</option>"); 
				})
			}
		});
}

   function fun2(){
		$('#province').empty();
		var countryId = $('#countryId').val();
		$.ajax({
			url:'/cityController/queryProvinceByCountryId',
			data:{
				countryId:countryId
			},
			async:false,
			success:function(msg){
				var obj = eval("(" + msg + ")");
				var json = eval("(" + obj + ")");
				if(json.length == 1 && json[0].privinceid == undefined || json[0].privinceid == 0){
					$('#province').append("<option value = '0'>"+'暂无信息'+"</option>"); 
				}else{
					$.each(json, function (index, item) {  
						$('#province').append("<option value = '"+item.provinceid+"'>"+item.province+"</option>"); 
					});
				}
			},
			error:function(){
				alert("出错了...");
			}
		})
	} 

	function fun3(){
		$('#city').empty();
		var provinceId = $('#provinceId').val();
		$.ajax({
			url:'/cityController/queryCityBypId',
			data:{
				provinceId:provinceId
			},
			async:false,
			success:function(msg){
				var obj = eval("(" + msg + ")");
				var json = eval("(" + obj + ")");
				if(json.length == 1 && json[0].cityid == undefined || json[0].cityid == 0){
					$('#city').append("<option value = '0'>"+'暂无信息'+"</option>"); 
				}else{
					$.each(json, function (index, item) {  
						$('#city').append("<option value = '"+item.cityid+"'>"+item.city+"</option>"); 
					});
				}
			}
		})
	} 
/* 
 function fun2(){
$('#province').empty();
$.ajax({
	url:"/provinceController/queryAllProvince.action",
	dataType:"json",
	async:false,
	success:function (data){
		var json = eval("(" +data + ")");
		$('#province').append("<option value = '0'>--请选择省份--</option>");
		$.each(json, function (index, item) {  
			$('#province').append("<option value = '"+item.id+"'>"+item.name+"</option>"); 
		});
	}
})
}

function fun3(){
$('#city').empty();
$.ajax({
	url:"/cityController/queryAllcity.action",
	dataType:"json",
	async:false,
	success:function (data){
		var json = eval("(" +data + ")");
		$('#city').append("<option value = '0'>--请选择市区--</option>");
		$.each(json, function (index, item) {  
			$('#city').append("<option value = '"+item.cityid+"'>"+item.city+"</option>"); 
		});
	}
})
}
	 */
$('#country').change(function(){
	$('#province').empty();
	$('#city').empty();
	var countryId = $('#country option:selected').val();
	$.post("/cityController/queryProvinceByCountryId?countryId="+countryId,function(data){
		var obj = eval("(" + data + ")");
		var json = eval("(" + obj + ")");
		if(json.length == 1 && json[0].privinceid == undefined || json[0].privinceid == 0){
			$('#province').append("<option value = '0'>"+'暂无信息'+"</option>"); 
		}else{
			$.each(json, function (index, item) {  
				$('#province').append("<option value = '"+item.provinceid+"'>"+item.province+"</option>"); 
			});
		}
		$('#province').change();
	})
})

$('#province').change(function(){
	$('#city').empty();
	var provinceId = $('#province option:selected').val();
	$.post("/cityController/queryCityBypId?provinceId="+provinceId,function(data){
		var obj = eval("(" + data + ")");
		var json = eval("(" + obj + ")");
		if(json.length == 1 && json[0].cityid == undefined || json[0].cityid == 0){
			$('#city').append("<option value = '0'>"+'暂无信息'+"</option>"); 
		}else{
			$.each(json, function (index, item) {  
				$('#city').append("<option value = '"+item.cityid+"'>"+item.city+"</option>"); 
			});
		}
	})
})

function fun5(){
	 $.ajax({
		url:"/cityController/queryAllIsp.action",
		dataType:"json",
		async:false,
		success:function (data){
			var json = eval("(" +data + ")");
			$.each(json, function (index, item) {  
				$('#operatorId').append("<option value = '"+item.ispid+"'>"+item.name+"</option>"); 
			})
		}
	});
 }
function fun4 (){
	var id = $('#Id').val();
	$.ajax({
		type:"post",
		url:"/resourceController/queryResourceById.action",
		data:{"rtppSid":id},
		dataType:'json',
        cache: false,
        async:false,
		success:function(data){
			var obj = eval("("+data+")");
			$('#nameId').val(obj.name);
			$('#ipId').val(obj.ip);
			$('#mainIPId').val(obj.mainIp);
			$('#priceId').val(obj.price);
			$('#blockpriceId').val(obj.blockPrice);
			$('#operatorId').val(obj.operator);
			$('#typeId').val(obj.type);
			$('#netLevelId').val(obj.netLevel);
			$('#country').val(obj.country);
			$('#province').val(obj.province);
			$('#city').val(obj.zone);
			if(obj.isToLine==1){
				$("#radio-1").attr("checked","true");
				$("#toI").show();
			}
			if(obj.isToLine==0){
				$("#radio-2").attr("checked","true");
				$("#toI").hide();
			}
			$('#toIpId').val(obj.toIp);
			$('#bandwidthLimitId').val(obj.bandwidthLimit);
			$('#concurrencyLimitId').val(obj.concurrencyLimit);
			var objPrice = obj.blockPrice;
			if(objPrice !=null ){
				$('#Y').attr("checked","true");
				$('#blockTr').show();
				var objP = eval("(" + objPrice + ")");
				//var jsonList =[{"starttime":"0100","endtime":"0100","price":"120"},{"starttime":"0200","endtime":"0300","price":"130"}];
				var index = 1;
				$(objP).each(function(){
					var str = '<div id="div' + index + '"><select style="width: 100px" name="startTime'+ index + '" id="startTime' + index +'">';
					str = str + '<option value="" selected="selected">--请选择时间段--</option>';
					str = str + '<option value="0000">00:00</option><option value="0100">01:00</option><option value="0200">02:00</option><option value="0300">03:00</option>';
					str = str + '<option value="0400">04:00</option><option value="0500">05:00</option><option value="0600">06:00</option><option value="0700">07:00</option>';
					str = str + '<option value="0800">08:00</option><option value="0900">09:00</option><option value="1000">10:00</option><option value="1100">11:00</option>';
					str = str + '<option value="1200">12:00</option><option value="1300">13:00</option><option value="1400">17:00</option><option value="1500">15:00</option>';
					str = str + '<option value="1600">16:00</option><option value="1700">17:00</option><option value="1800">18:00</option><option value="1900">19:00</option>';
					str = str + '<option value="2000">20:00</option><option value="2100">21:00</option><option value="2200">22:00</option><option value="2300">23:00</option>';
					str = str + '</select>&nbsp;--&nbsp;';
					str = str + '<select style="width: 100px" name="endTime'+ index + '" id="endTime' + index +'">';
					str = str + '<option value="" selected="selected">--请选择时间段--</option>';
					str = str + '<option value="0000">00:00</option><option value="0100">01:00</option><option value="0200">02:00</option><option value="0300">03:00</option>';
					str = str + '<option value="0400">04:00</option><option value="0500">05:00</option><option value="0600">06:00</option><option value="0700">07:00</option>';
					str = str + '<option value="0800">08:00</option><option value="0900">09:00</option><option value="1000">10:00</option><option value="1100">11:00</option>';
					str = str + '<option value="1200">12:00</option><option value="1300">13:00</option><option value="1400">17:00</option><option value="1500">15:00</option>';
					str = str + '<option value="1600">16:00</option><option value="1700">17:00</option><option value="1800">18:00</option><option value="1900">19:00</option>';
					str = str + '<option value="2000">20:00</option><option value="2100">21:00</option><option value="2200">22:00</option><option value="2300">23:00</option>';
					str = str + '</select>&nbsp;--&nbsp;';
					str = str + '<input type="text" name="price'+ index + '" placeholder="请填入价格" style="width: 70px" id="price'+ index + '"/><div><br/></div></div>';
					$('#d1').append(str);
					$('#startTime'+ index).val(this.starttime);
					$('#endTime' + index).val(this.endtime);
					$('#price' + index).val(this.price);
					index++;
				});
			}else{
				$('#blockPriceTr').hidden();
				$('#N').attr("checked");
			}
		}
	});
	
}
function add(){
	if($('#Y').attr("checked")=="checked"){
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
		str = str + '</select>&nbsp;--&nbsp;';
		str = str + '<select style="width: 100px" name="endTime'+ index + '" id="endTime' + index +'">';
		str = str + '<option value="" selected="selected">--请选择时间段--</option>';
		str = str + '<option value="0000">00:00</option><option value="0100">01:00</option><option value="0200">02:00</option><option value="0300">03:00</option>';
		str = str + '<option value="0400">04:00</option><option value="0500">05:00</option><option value="0600">06:00</option><option value="0700">07:00</option>';
		str = str + '<option value="0800">08:00</option><option value="0900">09:00</option><option value="1000">10:00</option><option value="1100">11:00</option>';
		str = str + '<option value="1200">12:00</option><option value="1300">13:00</option><option value="1400">17:00</option><option value="1500">15:00</option>';
		str = str + '<option value="1600">16:00</option><option value="1700">17:00</option><option value="1800">18:00</option><option value="1900">19:00</option>';
		str = str + '<option value="2000">20:00</option><option value="2100">21:00</option><option value="2200">22:00</option><option value="2300">23:00</option>';
		str = str + '</select>&nbsp;--&nbsp;';
		str = str + '<input type="text" name="price'+ index + '" placeholder="请填入价格" style="width: 70px" id="price'+ index + '"/><div><br/></div></div>';
		$('#d1').append(str);
	}
	
}
function del(){
	if($('#Y').attr("checked")=="checked"){
		var index = $('#count').val();
		//$('#startTime' + index).remove();
		//$('#endTime' + index).remove();
		//$('#price' + index).remove(); 
		$('#div' + index).remove(); 
		index--;
		$('#count').val(index);
	}
}

</script>
<script type="text/javascript" src="${ctx}/js/jquery-1.8.1.min.js"></script> 
<script type="text/javascript" src="${ctx}/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="${ctx}/js/H-ui.js"></script> 
<script type="text/javascript" src="${ctx}/js/H-ui.admin.js"></script> 

</body>
</html>