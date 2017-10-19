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
<!--[if IE 7]>
<link href="http://www.bootcss.com/p/font-awesome/assets/css/font-awesome-ie7.min.css" rel="stylesheet" type="text/css" />
<![endif]-->
<title>添加用户</title>
</head>
<body>
<div class="pd-20">
  <div class="Huiform">
    <form action="${ctx}/userController/addUser.action" method="post">
      <table class="table table-bg" style="font-size: 12px;">
        <tbody>
        
          <tr>
            <th width="100" class="text-r"> 用户名：</th> 
            <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="user-name" name="username" datatype="*2-16" nullmsg="用户名不能为空"></td>
          </tr> 
          <tr>
            <th class="text-r">手机号码：</th>
            <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="user-tel" name="mobile"></td>
          </tr>
          <tr>
            <th class="text-r">邮箱：</th>
            <td>
            	<input type="text" style="width:300px" class="input-text" value="" placeholder="" id="user-email" name="email">
            	<span style="color: red; display: none;" id="emailError" >邮箱已注册</span>
            </td>
          </tr>
          <tr>
            <th class="text-r">联系地址：</th>
            <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="user-address" name="address"></td>
          </tr>
          <tr>
            <th class="text-r">真实姓名：</th>
             <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="user-realname" name="realname"></td>
          </tr>
          <tr>
            <th class="text-r">分配角色：</th>
             <td>
             	<select class="select" size="1" name="user-role" id="user-role">
					<option value="" selected>--请选择角色--</option>
					<option value="1">运营人员</option>
					<option value="2">财务人员</option>
				</select>
			</td>
          </tr>
          <tr>
            <th></th>
            <td><button class="btn btn-success radius" type="button" id="addUser"><i class="icon-ok"></i> 确定</button></td>
          </tr>
        </tbody>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="http://cdn.bootcss.com/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/Validform_v5.3.2_min.js"></script> 
<script type="text/javascript" src="${ctx}/js/H-ui.js"></script> 
<script type="text/javascript" src="${ctx}/js/H-ui.admin.js"></script> 
<script type="text/javascript" src="${ctx}/js/user/addUser.js"></script>
<script type="text/javascript">
$(".Huiform").Validform(); 
</script>
<script type="text/javascript">
$('#addUser').click(function(){
	var username = $('#user-name').val();
	var mobile = $('#user-tel').val();
	var email = $('#user-email').val();
	var realname = $('#user-realname').val();
	var address = $('#user-address').val();
	var userRole = $('#user-role').val();
	var username1 = /^[\u4e00-\u9fa5a-zA-Z0-9_]+$/;
	var name1 = /^[\u4e00-\u9fa5 ]{2,20}$/;
	var name2 =  /^[a-zA-Z\/ ]{2,20}$/;
	var telephone1 = /^[1]{1}[0-9]{10}/;
	var email1 = /\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/;
	
	/* if(!username1.test(username)){
		alert("请输入正确格式的用户名");
		return false;
	} */
	if(username==""){
		alert("请输入用户名");
		return false;
	}
	if(!telephone1.test(mobile)){
		alert("请输入正确的格式的手机号码");
		return false;
	}
	if(!email1.test(email)){
		alert("请输入正确的格式邮箱");
		return false;
	}
	if(realname==""){
		alert("请输入真实姓名");
		return false;
	}
	if(userRole == ""){
		alert("请选择角色");
		return false;
	}
	$.post("/userController/checkEmail.action",{"email":email,"mobile":mobile},function(data){
		var obj = eval("("+ data +")")
		if(obj == 1){
			$.post("/userController/addUser.action",
					{"username":username,
					 "mobile":mobile,
					 "email":email,
					 "userRole":userRole,
					 "realname":realname,
					 "address":address,
					},
					function(data){
						var data = eval('('+data+')')
						if(data == 1){
							alert("添加成功");
							window.parent.location.reload(true);
							window.close();
						}
						if(data == 0){
							alert("添加失败");
							/* window.parent.location.reload(true);
							window.close(); */
						}
					}
			)
		}if(obj == 0){
			alert("添加失败,此邮箱已被注册");
		}if(obj == 2){
			alert("添加失败,此手机号码已被注册");
		}
	})
});
</script>
</body>
</html>