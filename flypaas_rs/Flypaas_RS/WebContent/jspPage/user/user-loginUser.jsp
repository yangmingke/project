<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<link type="text/css" rel="stylesheet" href="${ctx}/css/form/form.css">
<!--[if IE 7]>
<link href="http://www.bootcss.com/p/font-awesome/assets/css/font-awesome-ie7.min.css" rel="stylesheet" type="text/css" />
<![endif]-->
</head>
<body>
<nav class="Hui-breadcrumb"><i class="icon-home"></i> 首页 <span class="c-gray en">&gt;</span> 用户管理<span class="c-gray en">&gt;</span> 用户资料</nav>
	<div class="codeView docs-example">
		<form action="/userController/updateUser.action" method="post" id="userForm" >
				<input type="text" value="${message}" id="msgId" style="display: none;"/>
				<table class="table table-border table-bordered table-striped" style="font-size: 12px;" id="table1">
					<thead>
						<tr>
							<th colspan="4">基本信息>></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td width="10%;"><label>邮箱:</label><input type="text" value="${user.sid}" style="display: none;" id="sId"/> </td>
							<td width="40%"><input type="text" class="input-text" value="${user.email}" name="email" readonly="readonly"></td>
							<td width="10%;" ><label>用户名:</label></td>
							<td width="40%;"><input type="text" class="input-text" value="${user.username}" name="username" id="username"></td>
						</tr>
						<tr>
							<td><label>真实姓名:</label></td>
							<td><input type="text" class="input-text" value="${user.realname}" name="realname" id="realname"></td>
							<td><label>手机号:</label></td>
							<td ><input type="text" class="input-text" value="${user.mobile}" name="mobile" id="mobile"></td>
						</tr>
						<tr>
							<td><label>联系地址:</label></td>
							<td><input type="text" class="input-text" value="${user.address}" name="address"></td>
							<td><label>国籍:</label></td>
							<td >
								<input type="text" value="${user.national}" style="display: none" id="nationalId"/>
								<select class="select" size="1" name="national" id="national" onchange="queryProvince(this.value)">
								    <option value="0" selected>请选择国籍</option>
								</select>
							</td>
						</tr>
						<tr>
							<td><label>注册时间:</label></td>
							<td><input type="text" class="input-text" value="<fmt:formatDate value="${user.createDate}" pattern="yyyy-MM-dd"/>" readonly="readonly"></input></td>
							<td><label>区域:</label></td>
							<td>
							<nobr>
							<input type="text" value="${user.province}" style="display: none" id="provinceId"/>
								<select class="select" style="width: 200px;" size="1" name="province" id="province" onchange="queryCity()">
								   
								</select>
							<input type="text" value="${user.city}" style="display: none" id="cityId"/>
								<select class="select" style="width: 200px;" size="1" name="city" id="city">
								  
								</select>
							</td>
							</nobr>
						</tr>
					</tbody>
				</table>
				<input value="${roleId}" hidden="hidden" name="roleId">
				<table class="table table-border table-bordered table-striped" id="table2" style='font-size: 12px; <c:if test="${roleId != 1}">>display: none;</c:if>' >
					<thead>
						<tr>
							<th colspan="4">资源方信息>></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td width="10%"><label>收款账号（支付宝）:</label></td>
							<td width="40%"><input type="text" class="input-text" value="${userSide.alipayAccount}" name="alipayAccount" id="alipayAccount"></td>	
							<td width="10%"><label>收款人（支付宝）:</label></td>
							<td width="40%"><input type="text" class="input-text" value="${userSide.alipayName}" name="alipayName" id="alipayName"></td>
						</tr>
						<tr>
							<td width="150"><label>是否形成私有加速网:</label></td>
							<td>
								<c:choose>
									<c:when test="${userSide.isPrivateNet == 1}">
										<input type="text" class="input-text" value="是" readonly="readonly" id="isPrivateNetId"/>
									</c:when>
									<c:otherwise>
										<input type="text" class="input-text" value="否" readonly="readonly" id="isPrivateNetId"/>
									</c:otherwise>
								</c:choose>
							</td>
							<td><label>等级:</label></td>
							<td><input type="text" class="input-text" value="${userSide.rank}" name="rank" readonly="readonly"></td>
						</tr>
						<tr>
							<td><label>公司电话:</label></td>
							<td><input type="text" class="input-text" value="${userSide.companyNbr}" name="companyNbr"></td>
							<td><label>公司网址:</label></td>
							<td><input type="text" class="input-text" value="${userSide.webSite}" name="webSite"></td>
						</tr>
						<tr>
							<td><label>企业法人：</label></td>
							<td><input type="text" class="input-text" value="${userSide.legalPerson}" name="legalPerson"></td>
							<td ><label>证件类型:</label></td>
							<td>
								<input type="text" value="${userSide.idType}" style="display: none" id="idTypeId"/>
								<select class="select" size="1" name="idType" id="idType">
								    <option value="0" selected>身份证</option>
								    <option value="1">护照</option>
								</select>
							</td>
						</tr>
						<tr>
							<td><label>机构号:</label></td>
							<td><input type="text" class="input-text"  value="${userSide.orgId}" name="orgId"><!-- *公司须填 --></td>
							<td><label>证件号:</label></td>
							<td><input type="text" class="input-text"  value="${userSide.idNbr}" name="idNbr"><!-- *个人须填 --></td>
						</tr>
						</tbody>
				</table>
				<div style="text-align: center;padding-top: 10px;" id="btn"><button type="button" id ="commit" class="btn btn-success radius" style="height: 30px;width: 60px;">保存</button></div>
			</form>
		</div>
<script type="text/javascript" src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
	$('#commit').click(function(){
		$('b').remove();
		var username = $('#username');
		if(username.val() == ""){
			error(username,"用户名不能为空");
			return false;
		}
		var realname = $('#realname');
		if(realname.val() == ""){
			error(realname,"真实姓名不能为空");
			return false;
		}
		var mobile = $('#mobile');
		var mobileReg = /^[1]{1}[0-9]{10}/;
		if(!mobileReg.test(mobile.val())){
			error(mobile,"手机号码格式错误");
			return false;
		}
		var alipayAccount = $('#alipayAccount');
		if(alipayAccount.val() == ""){
			error(alipayAccount,"收款账号不能为空");
			return false;
		}
		var alipayName = $('#alipayName');
		if(alipayName.val() == ""){
			error(alipayName,"收款人不能为空");
			return false;
		}
		$.ajax({
			url:"/userController/updateUser",
			data:$('#userForm').serializeArray(),
			type:"post",
			success : function(data) { 
	        	var json = eval("("+data+")");
	        	if(json == 0){
	        		alert("修改失败，服务器繁忙！");
	        	}
	        	if(json == 1){
	        		alert("修改成功！")
	        	}
	        }
		})
	});
	
	function error(element,error){
		element.focus();
		element.after("<b style='color: red'>"+error+"</b>");
	}

	$(function(){
		queryAllCountry();
		//fun1();
		fun2();
		if("${message}" != ""){
			alert("${message}");
		}
	});
	function fun1(){
osi 	}
	
	function fun2(){
		var isPrivateNetId = $('#isPrivateNetId').val();
		var chatTypeId = $('#chatTypeId').val();
		var nationalId = $('#nationalId').val();
		var idTypeId = $('#idTypeId').val();
		var provinceId = $('#provinceId').val();
		var cityId = $('#cityId').val();
		if(isPrivateNetId == '是'){
			$('#radio-1').attr("checked","checked");
		}else{
			$('#radio-0').attr("checked","checked");
		}
		$('#chatType').val(chatTypeId);
		$('#national').val(nationalId);
		$('#idType').val(idTypeId);
		$('#province').val(provinceId);
		$('#city').val(cityId);
		//查询所有的省份  城市
		/* $.ajax({
			url:"/provinceController/queryAllProvince.action",
			type:"post",
			success:function(data){
				var objArray = eval("("+data+")");
				var json = eval("("+objArray+")");
				var provinceId = $('#provinceId').val();
				//遍历json数组 
				$.each(json, function(i, item) {
					if(item.id == provinceId){
						$('#province').append("<option value="+item.id+" selected>"+item.name+"</option>");
					}else{
						$('#province').append("<option value="+item.id+">"+item.name+"</option>");
					}
				})
				queryCity();
			}
		});		 */
	}
	
	function queryAllCountry(){
		$.ajax({
			url:"/cityController/queryAllCountryAjax.action",
			type:"post",
			success:function(data){
				var objArray = eval("("+data+")");
				var json = eval("("+objArray+")");
				//遍历json数组 
				$.each(json, function(i, item) {
					if(item.countryid == "${user.national}"){
						$('#national').append("<option value="+item.countryid+" selected>"+item.country+"</option>");
					}else{
						$('#national').append("<option value="+item.countryid+">"+item.country+"</option>");
					}
				});
				$('#national').change();
			}
		});		
	}
	
	function queryProvince(countryId){
		$.ajax({
			url:"/cityController/queryProvinceByCountryId.action",
			data:{
				"countryId":countryId
			},
			type:"post",
			success:function(data){
				var objArray = eval("("+data+")");
				var json = eval("("+objArray+")");
				$('#province').html("<option value='0' selected>请选择省份</option>");
				//遍历json数组 
				$.each(json, function(i, item) {
					if(item.provinceid == "${user.province}"){
						$('#province').append("<option value="+item.provinceid+" selected>"+item.province+"</option>");
					}else{
						$('#province').append("<option value="+item.provinceid+">"+item.province+"</option>");
					}
				});
				$('#province').change();
			}
		});		 
	}
	
	function queryCity(){
		$("#city").empty();
		var cityId = Number($('#cityId').val());
		var provinceId = $('#province option:selected') .val();//选中的值
		$.ajax({
			url:"/cityController/queryCityBypId.action",
			type:"post",
			data:{
				"provinceId":provinceId
			},
			success:function(data){
				var objArray = eval("("+data+")");
				var json = eval("("+objArray+")");
				$('#city').append("<option value='0' selected>请选择城市</option>");
				//遍历json数组 
				$.each(json, function(i, item) {
					if(item.cityid == cityId){
						$('#city').append("<option value="+item.cityid+" selected>"+item.city+"</option>");
					}else{
						$('#city').append("<option value="+item.cityid+">"+item.city+"</option>");
					}
					//$('#city').append("<option value="+item.cityid+" selected>"+item.city+"</option>");
				}); 
			}
			
		});		
	}
</script>
</body>
</html>