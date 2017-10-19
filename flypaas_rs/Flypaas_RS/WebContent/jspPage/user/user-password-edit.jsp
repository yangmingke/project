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
<!--[if IE 7]>
<link href="http://www.bootcss.com/p/font-awesome/assets/css/font-awesome-ie7.min.css" rel="stylesheet" type="text/css" />
<![endif]-->
<title>修改密码</title>
</head>
<body>
<nav class="Hui-breadcrumb"><i class="icon-home"></i> 首页 <span class="c-gray en">&gt;</span> 用户管理 <span class="c-gray en">&gt;</span> 修改密码 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="icon-refresh"></i>刷新</a></nav>
<div class="pd-20" style="padding-left: 30%;">
<br/><br/>
  <form class="Huiform" action="/userController/editPwd.action" method="post" onsubmit="return toVaild()" >
    <table class="table" >
      <tbody>
     	 <tr>
          <th width="100" class="text-r">当前用户：</th>
          <td><input type="text" style="width:200px" class="input-text" value="${userSession.username}" id="" name="" readonly="readonly"></td>
        </tr>
        <tr>
          <th width="100" class="text-r">原始密码：</th>
          <td><input type="password" style="width:200px" class="input-text"  id="oldPwd1" name="oldPwd1"><span style="color: red;" id="errorMessage1">${msg2}</span></td>
        </tr>
        <tr>
          <th width="100" class="text-r">新密码：</th>
          <td><input type="password" style="width:200px" class="input-text"  id="newPwd1" name="newPwd1" placeholder="请输入6~20位的数字字母组合"></td>
        </tr>
        <tr>
          <th width="100" class="text-r"> 确认密码：</th>
          <td><input type="password" style="width:200px" class="input-text"  id="newPwd2" name="newPwd2" onblur="newPwd()" placeholder="重复上面的密码"><span style="color: red; display: none;" id="errorMessage2">两次密码不一致</span></td>
        </tr>
        <tr>
          <th width="100" class="text-r"></th>
          <td><span style="color: red;" id="errorMessage1">${msg}</span><td>
        </tr>
        <tr>
          <th><input type="password" style="width:200px;display: none;" class="input-text" value="${userSession.password}" id="oldPwd2" name="oldPwd2"></th>
          <td><button class="btn btn-success radius" type="submit"><i class="icon-ok"></i> 确定</button></td>
        </tr>
      </tbody>
    </table>
  </form>
</div>
<script type="text/javascript" src="${ctx}/js/H-ui.js"></script> 
<script type="text/javascript" src="${ctx}/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript">
	function toVaild(){
		var password1 = /^(?!\D+$)(?![^a-zA-Z]+$)\S{6,20}$/;
		var password2 = $('#newPwd1').val();
		var password3 = $('#newPwd2').val();
		var oldPwd = $('#oldPwd1').val();
		if(oldPwd==""){
			alert("原密码不能为空");
			return false;
		}
		if(!password1.test(password2)){
			alert("请输入正确格式的密码");
			return false;
		}
		if(password2!= password3){
			alert("两次密码不一致");
			return false;
		}
	}
	
	function newPwd(){
		var newPwd1 = $('#newPwd1').val();
		var newPwd2 = $('#newPwd2').val();
		if(newPwd1!=newPwd2){
			$('#errorMessage2').css("display","block");
			//$('#newPwd2').focus();
		}else{
			$('#errorMessage2').css("display","none");
		}
	}
</script>

</body>
</html>