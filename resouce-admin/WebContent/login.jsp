<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>登录</title>  
    <link rel="stylesheet" href="${ctx}/css/pintuer.css">
    <link rel="stylesheet" href="${ctx}/css/admin.css">
    <script src="${ctx}/js/jquery.js"></script>
    <script src="${ctx}/js/pintuer.js"></script> 
    <script src="${ctx}/js/login.js"></script>   
</head>
<body onkeyup="if (event.keyCode==13) {login()}">
<div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:150px;"></div>
            <div class="media media-y margin-big-bottom">           
            </div>         
            <form>
	            <div class="panel loginbox">
	                <div class="text-center margin-big padding-big-top"><h1>后台管理中心</h1></div>
	                <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
	                    <div class="form-group">
	                        <div class="field field-icon-right">
	                            <input type="text" class="input input-big" id="user" placeholder="登录账号" data-validate="required:请填写账号" />
	                            <span class="icon icon-user margin-small"></span>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <div class="field field-icon-right">
	                            <input type="password" class="input input-big" id="pwd" placeholder="登录密码" data-validate="required:请填写密码" />
	                            <span class="icon icon-key margin-small"></span>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <div class="field">
	                            <input id="veryCode" type="text" class="input input-big" name="code" placeholder="填写右侧的验证码" data-validate="required:请填写右侧的验证码" />
	                           <img id="imgVerify" src="" alt="点击更换验证码" width="100" height="32" class="passcode" style="height:43px;cursor:pointer;" onclick="getVerify(this);">  
	                        </div>
	                    </div>
	                    <div class="formRow">
					        <div class="login_form_warn" style="display: none;">
				               <span class="warn_text" style="color: red"></span>
					        </div>
				    	</div>
	                </div>
	            </div>
            </form>          
	                <div style="padding:30px;"><input type="submit" class="button button-block bg-main text-big input-big" value="登录" onclick="login()"></div>
        </div>
    </div>
</div>

</body>
</html>