<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
<script src="${ctx}/js/xcConfirm.js" type="text/javascript" charset="utf-8"></script>

</head>
<body>
<div class="panel admin-panel margin-top" id="add">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 资源分时间段定价显示</strong></div>
  <div class="body-content">
   <form id="formInfo" method="post" class="form-x" action="${ctx}/resourceController/editResourceBlcokPrice.action">
   	<input type="text" value="<%= request.getParameter("blockPrice")%>" id="blockPrice" style="display: none;"/>
   	<input type="text" value="<%= request.getParameter("rtppSid")%>" id="rtppSid" name="rtppSid" style="display: none;"/>
    <table style="width: 90%">
    	 <tr class="text-right">
		  	<td align="center" style="height: 60px;">
		       	<h4> 资源分时间段计费表<span id="rtppIp" style="font-size: 14px;"></span></h4>
		       	<div style="float: right">
            		<a onclick="add()">添加</a>&nbsp;&nbsp;&nbsp;&nbsp;
            		<a onclick="del()">删除</a>
            	</div>
		    </td>
          </tr>  
		  <tr class="text-right">
          	<td align="center">
	          	<div id="d1">
	          		 <input id="count" hidden="hidden" value="1" name="index">
	            </div>
            </td>
          </tr>  
	 </table>
	  <div class="form-group" style="text-align: center;padding-top: 40px;">
	      <div class="field">
	        <button id="editBtn" class="button bg-main icon-edit" type="button" > 修改</button>   
	         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	<button id="back" class="button bg-main icon-undo" type="button" onclick="returnBack()"> 返回</button>
	      </div>
	  </div>
	</form>
  </div>
</div>
<script type="text/javascript">
	function returnBack(){
		var rtppSid = $('#rtppSid').val();
		$('body').load('/resourceController/queryResourceById?rtppId=' + rtppSid);
	}

	$('#editBtn').click(function(){
		$.ajax({
			url:"/resourceController/editResourceBlcokPrice.action",
			data:$('#formInfo').serializeArray(),
			type:"post",
			contentType: "application/x-www-form-urlencoded;charset=utf-8",
			dataType:'json',
			success : function(data) { 
	        	var json = eval("("+data+")");
	        	if(json == 0){
	        		window.wxc.xcConfirm("修改失败....", window.wxc.xcConfirm.typeEnum.info);
	        	}
	        	if(json == 1){
	        		window.wxc.xcConfirm("修改成功....", window.wxc.xcConfirm.typeEnum.info);
	        	}
	        },
	        error:function(code){   
	        	window.wxc.xcConfirm("Sorry.服务器出错....", window.wxc.xcConfirm.typeEnum.info);
	        }   
		}) 
	})
	
	$(function(){
		var rtppSid = $('#rtppSid').val();
		$.ajax({
			type:"post",
			url:"/resourceController/queryResourceByIdAjax.action",
			data:{"rtppId":rtppSid},
			dataType:'json',
	        cache: false,
	        async:false,
			success:function(data){
				var index = $('#count').val();
				var json = data.tbRsRTPP;
				var block_price = json.block_price;
				var blockPrice = eval("("+block_price+")");
				$(blockPrice).each(function(){
					var str = '<div id="div' + index + '"><label>时间段'+index+':</label>&nbsp;&nbsp;<select style="width: 150px;height:30px;" name="startTime'+ index + '" id="startTime' + index +'">';
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
					str = str + '<input type="text" name="price'+ index + '" placeholder="请填入价格" style="width: 100px;height:30px;" id="price'+ index + '"/>(分/GB)<div><br/></div></div>';
					$('#d1').append(str);
					$('#startTime'+ index).val(this.starttime);
					$('#endTime' + index).val(this.endtime);
					$('#price' + index).val(this.price);
					index++;
					$('#count').val(index);
				});
				$('#rtppIp').html("("+json.ip+")");
		  	}
	  	 });
	})
	
	function add(){
		var index = $('#count').val();
		var str = '<div id="div' + index + '"><label>时间段'+index+':</label>&nbsp;&nbsp;<select style="width: 150px;height:30px;" name="startTime'+ index + '" id="startTime' + index +'">';
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
		str = str + '<input type="text" name="price'+ index + '" placeholder="请填入价格" style="width: 100px;height:30px;" id="price'+ index + '"/>(分/GB)<div><br/></div></div>';
		index++;
		$('#count').val(index);
		$('#d1').append(str);
	}
	function del(){
		var index = $('#count').val();
		index--;
		/* $('#startTime' + index).remove();
		$('#endTime' + index).remove();
		$('#price' + index).remove(); */
		$('#div' + index).remove(); 
		$('#count').val(index);
	}


</script>
</body>
</html>