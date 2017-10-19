<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
<script type="text/javascript" src="${ctx}/js/user/editPwd.js"></script>
<!--[if IE 7]>
<link href="http://www.bootcss.com/p/font-awesome/assets/css/font-awesome-ie7.min.css" rel="stylesheet" type="text/css" />
<![endif]-->
<title>修改密码</title>
</head>
<body>
<div class="pd-20" >
<br/><br/>
  <form class="Huiform" action="##" method="post" >
    <table class="table" >
      <tbody>
      	<tr hidden="hide">
          <th width="100" class="text-r">当前用户：</th>
          <td><input type="text" style="width:200px" class="input-text" value="<%=request.getParameter("id") %>" id="userId" name="" readonly="readonly"></td>
        </tr>
     	<tr>
          <th width="100" class="text-r">当前用户：</th>
          <td><input type="text" style="width:200px" class="input-text" value="<%=new String(request.getParameter("username").getBytes("ISO-8859-1"),"utf-8")%>" id="" name="" readonly="readonly"></td>
        </tr>
        <tr>
          <th width="100" class="text-r">新密码：</th>
          <td><input type="password" style="width:200px" class="input-text"  id="newPwd1" name=""></td>
        </tr>
        <tr>
          <th width="100" class="text-r"> 确认密码：</th>
          <td><input type="password" style="width:200px" class="input-text"  id="newPwd2" name=""><span style="color: red; display: none;" id="errorMessage2">两次密码不一致</span></td>
        </tr>
      </tbody>
    </table>
  </form>
  	 <div style="margin-left: 30%"><button class="btn btn-success radius" onclick="submitEditPwd()"><i class="icon-ok"></i> 确定</button></div>
</div>
</body>
<script type="text/javascript">
function submitEditPwd(){
	var sId = $('#userId').val();
	var password1 = /^(?!\D+$)(?![^a-zA-Z]+$)\S{6,20}$/;
	var password2 = $('#newPwd1').val();
	var password3 = $('#newPwd2').val();
	if(!password1.test(password2)){
		alert("请输入正确格式的密码");
		return false;
	}
	if(password2!= password3){
		alert("两次密码不一致");
		return false;
	}
	$.post("/userController/editUserPwd.action",{"sId":sId,"newPwd":password2},
			function(data){
				var json = eval('('+data+')');
				if(json == 1){
					alert("修改成功!");
					window.parent.location.reload(true);
					window.close();
				}
				if(json == 0){
					alert("修改失败!");
					layer.msg('修改失败!',2,2,function(){
	        			window.close();
		        		window.parent.location.reload(true);
	        		});
				}
			}
	)
}
</script>
<script type="text/javascript" src="${ctx}/js/H-ui.js"></script> 
<script type="text/javascript" src="${ctx}/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.12.3.min.js"></script>
</html>