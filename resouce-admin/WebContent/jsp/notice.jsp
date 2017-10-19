<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>通知用户</title>  
    <link rel="stylesheet" href="${ctx}/css/pintuer.css">
    <link rel="stylesheet" href="${ctx}/css/admin.css">
    <script src="${ctx}/js/jquery.js"></script>
    <script src="${ctx}/js/pintuer.js"></script>  
    <script src="${ctx}/js/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<div class="panel admin-panel">
	  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>通知用户</strong></div>
	  <div class="body-content">
	     <form method="post" class="form-x" action="" id="formInfo">  
	     <div class="form-group">
	        <div class="label">
	          <label for="sitename">TO：</label>
	        </div>
	        <div class="field">
	          <input name="sid" value="${sid}" hidden="hidden">
	          <label style="line-height:33px;">
	           	${username}（${sid}）
	          </label>
	        </div>
      	  </div> 
	      <div class="form-group">
	        <div class="label">
	          <label>标题：</label>
	        </div>
	        <div class="field">
	          <input type="text" class="input w50" value="" name="msgTitle" data-validate="required:请输入标题" id="msgTitle"/>
	          <div class="tips"></div>
	        </div>
	      </div>
	      <div class="form-group">
	        <div class="label">
	          <label>内容：</label>
	        </div>
	        <div class="field">
	          <textarea name="msgDesc" class="input" style="height:450px; border:1px solid #ddd;" id="msgDesc"></textarea>
	          <div class="tips"></div>
	        </div>
	      </div>
	      <div class="form-group" style="text-align: center;padding-top: 40px;">
		      <div class="field">
		        <button id="notice" class="button bg-main icon-check-square-o" type="button" onclick="adminNotice('${netSid}');"> 通知</button>   
		        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		        <button id="back" class="button bg-main icon-check-square-o" type="button" onclick="user('${netSid}');"> 返回</button>
		      </div>
		  </div> 
	    </form>
	  </div>
	</div>
	
	<script type="text/javascript">
	
		function user(netSid){
			$('body').load('/account/queryUsersByNetId?netSid=' + netSid);
		}
		
		function adminNotice(netSid){
			var msgTitle = $('#msgTitle').val();
			var msgDesc = $('#msgDesc').val();
			
			if(empty(msgTitle)){
				window.wxc.xcConfirm("”通知标题“不能为空！", window.wxc.xcConfirm.typeEnum.info);
				return;
			}
			if(empty(msgDesc)){
				window.wxc.xcConfirm("”通知内容“不能为空！", window.wxc.xcConfirm.typeEnum.info);
				return;
			}
			
			$.ajax({
				url:"/notice/adminNotice",
				data:$('#formInfo').serializeArray(),
				type:"post",
				success : function(data) { 
		        	var json = eval("("+data+")");
		        	if(json == 0){
		        		window.wxc.xcConfirm("系统发生错误，通知失败，请联系管理员！", window.wxc.xcConfirm.typeEnum.info);
		        	}
		        	if(json == 1){
		        		window.wxc.xcConfirm("通知成功！", window.wxc.xcConfirm.typeEnum.info,{
		        			onOk:function(v){
				        		user(netSid);
		        			}
		        		});
		        	}
		        }
			}) 
		}
	</script>
</body>
</html>