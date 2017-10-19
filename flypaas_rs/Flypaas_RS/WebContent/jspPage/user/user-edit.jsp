<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
<link type="text/css" rel="stylesheet" href="${ctx}/css/1.0.8/iconfont.css"/>
<!--[if IE 7]>
<link href="http://www.bootcss.com/p/font-awesome/assets/css/font-awesome-ie7.min.css" rel="stylesheet" type="text/css" />
<![endif]-->
<title>用户查看</title>
</head>
<body>
<div class="pd-20">
  <table class="table" style="font-size: 12px;">
    <tbody>
      <tr hidden="hide">
        <th class="text-r" width="100">ID：</th>
        <td><input type="text" name="sid" id="userId" class="input-text" value="<%= request.getParameter("id")%>" readonly="readonly"/> </td>
      </tr>
      <tr>
        <th class="text-r" width="100">用户名：</th>
        <td><input type="text" name="username" id="usernameId" class="input-text" /> </td>
      </tr>
      <tr>
        <th class="text-r" width="100">邮箱：</th>
        <td><input type="text" name="email" id="emailId" class="input-text"/></td>
      </tr>
      <tr>
        <th class="text-r">手机：</th>
        <td><input type="text" name="mobile" id="mobileId" class="input-text"/></td>
      </tr>
      <tr>
        <th class="text-r">真实姓名：</th>
        <td><input type="text" name="realname" id="realnameId" class="input-text"/></td>
      </tr>
      <tr>
        <th class="text-r">地址：</th>
        <td><input type="text" name="address" id="addressId" class="input-text"/></td>
      </tr>
      <!-- <tr>
        <th class="text-r">聊天工具：</th>
        <td>
        	<select name="chatType" class="select" size="1" id="select2">
        		<option value="0">--该用户未填写该选项--</option>
        		<option value="1">QQ</option>
        		<option value="2">gtalk</option>
        		<option value="3">移动设备</option>
        		<option value="4">其他</option>
        	</select>
        </td>
      </tr>
      <tr>
        <th class="text-r">社交号码：</th>
        <td><input type="text" name="chatnbr" id="chatnbrId" class="input-text"/></td>
      </tr> -->
      <tr>
        <th class="text-r">担任角色：</th>
        <td>
        	<select name="role" class="select" size="1" id="select1">
        		<!-- <option value="3" selected="selected">--该用户还未分配角色--</option> -->
        		<!-- <option value="0">管理员</option>
        		<option value="1">运营人员</option>
        		<option value="2">财务人员</option> -->
        	</select>
        </td>
      </tr>
      <tr>
      	<th class="text-r"></th>
      	<td>
      		<input type="button" value="保存" class="btn btn-primary radius" id="editUser"/>
      	</td>
      </tr>
    </tbody>
  </table>
</div>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script> 
<%-- <script type="text/javascript" src="${ctx}/js/H-ui.js"></script>  --%>
<%-- <script type="text/javascript" src="${ctx}/js/user/editUser.js"></script> --%>
<script>
	$(function(){
		var id = $('#userId').val();
		$.ajax({
			type:"post",
			url:"/userController/queryUserInfo.action",
			data:{"sid":id},
			dataType:'json',
	        cache: false,
			success:function(data){
				var obj = eval("("+data+")");
				$('#usernameId').val(obj.username);
				$('#mobileId').val(obj.mobile);
				$('#emailId').val(obj.email);
				$('#addressId').val(obj.address);
				$('#realnameId').val(obj.realname);
				if(obj.role[0].roleId==1){
					$("#select1").append("<option value='1'>运营人员</option>");
					$("#select1").append("<option value='2'>财务人员</option>");
					$("#select1").val("1");
				}else if(obj.role[0].roleId==2){
					$("#select1").append("<option value='1'>运营人员</option>");
					$("#select1").append("<option value='2'>财务人员</option>");
					$("#select1").val("2");
				}else if(obj.role[0].roleId==0){
					$("#select1").append("<option value='0'>管理员</option>");
					$("#select1").val("0");
				}/* else{
					$("#select1").val("3");
				} */
				
			/* 	if(obj.chatType==1){
					$("#select2").val("1");
				}else if(obj.chatType==2){
					$("#select2").val("2");
				}else if(obj.chatType==0){
					$("#select2").val("3");
				}else if(obj.chatType==0){
					$("#select2").val("4");
				}else{
					$("#select2").val("0");
				} */
				
				$('#chatnbrId').val(obj.chatNbr);
				
			}
		})
	}); 
</script>
<script type="text/javascript">
$('#editUser').click(function(){
	var sid = $('#userId').val();
	var username = $('#usernameId').val();
	var mobile = $('#mobileId').val();
	var email = $('#emailId').val();
	var roleId = $('#select1').val();
	var realname = $('#realnameId').val();
	var address = $('#addressId').val();
	var chatType = $('#select1').val();
	var chatNbr = $('#chatnbrId').val();
	
	var username1 = /^[\u4e00-\u9fa5a-zA-Z0-9_]+$/;
	var name1 = /^[\u4e00-\u9fa5 ]{2,20}$/;
	var name2 =  /^[a-zA-Z\/ ]{2,20}$/;
	var telephone1 = /^[1]{1}[0-9]{10}/;
	var email1 = /^[0-9a-z_]+@(([0-9a-z]+)[.]){1,2}[a-z]{2,3}$/
	if(!username1.test(username)){
		alert("请输入正确格式的用户名");
		return false;
	}
	if(!email1.test(email)){
		alert("请输入正确的格式邮箱");
		return false;
	}
	if(!telephone1.test(mobile)){
		alert("请输入正确的格式的电话");
		return false;
	}
	if(realname==null){
		alert("请输入姓名");
		return false;
	}
	$.post("/userController/editUser.action",
			{"sid":sid,
			 "username":username,
			 "mobile":mobile,
			 "email":email,
			 "roleId":roleId,
			 "realname":realname,
			 "address":address,
			 "chatType":chatType,
			 "chatNbr":chatNbr
			},
			function(data){
				var json = eval('('+data+')');
				if(json == 1){
					alert("修改成功");
					window.parent.location.reload(true);
					window.close();
				}
				if(json == 0){
					alert("修改失败，该邮箱已经被占用");
				}
				if(json == 2){
					alert("修改失败，该手机已经被占用");
				}
			}
	)
});
</script>
</body>
</html>