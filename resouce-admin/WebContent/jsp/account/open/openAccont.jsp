<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<script src="${ctx}/js/validation.js"></script>
<script src="${ctx}/js/xcConfirm.js" type="text/javascript" charset="utf-8"></script>

<style type="text/css">
	tr{
		line-height: 40px;
	}
</style>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-key"></span> 资源方开户</strong></div>
  <div class="body-content">
    <form id="formInfo" method="post" class="form-x" action="">
	    <table width="95%" class="rsInfo">
	    	<tr>
	    		<th>资源方信息 > ></th>
	    	</tr>
		    <tr>
		       <th class="text-right" >
		       	   <label><span style="color:red">*</span>路由域名称：</label>
		       </th>
		       <td>
		           <input class="small-input" name="netArea" id="netArea"/>
		       </td>
		        <th class="text-right">
		       	   <label><span style="color:red">*</span>公司全称：</label>
		       </th>
		       <td>
		           <input class="small-input" name="realname" id="realname"/>
		       </td>
		    </tr>   
		    <tr>
		       <th class="text-right">
		       	   <label><span style="color:red">*</span>邮箱：</label>
		       </th>
		       <td>
		           <input class="small-input" name="email" id="email"/>
		       </td>
		       <th class="text-right">
		       	   <label><span style="color:red">*</span>公司简称：</label>
		       </th>
		       <td>
		           <input class="small-input" name="username" id="username"/>
		       </td>
		    </tr>  
		    <tr>
		       <th class="text-right">
		       	   <label>手机号：</label>
		       </th>
		       <td>
		           <input class="small-input" name="mobile" id="mobile"/>
		       </td>
		       <th class="text-right" >
		       	   <label>联系地址：</label>
		       </th>
		       <td>
		           <input class="small-input" name="address"/>
		       </td>
		    </tr> 
		    <tr>
		       <th class="text-right" >
		       	   <label>收款账号（支付宝）：</label>
		       </th>
		       <td>
		           <input class="small-input" name="alipayAccount"/>
		       </td>
		       <th class="text-right">
		       	   <label>收款人（支付宝）：</label>
		       </th>
		       <td>
		           <input class="small-input" name="alipayName"/>
		       </td>
		    </tr>
		    <tr>
		       <th class="text-right" >
		       	   <label>公司电话：</label>
		       </th>
		       <td>
		           <input class="small-input" name="companyNbr"/>
		       </td>
		       <th class="text-right">
		       	   <label>公司网址：</label>
		       </th>
		       <td>
		           <input class="small-input" name="webSite"/>
		       </td>
		    </tr>
		    <tr>
		       <th class="text-right" >
		       	   <label>企业法人：</label>
		       </th>
		       <td>
		           <input class="small-input" name="legalPerson"/>
		       </td>
		       <th class="text-right">
		       	   <label>（个人）证件类型：</label>
		       </th>
		       <td>
		           <select id="idType" class="small-input" name="idType" style="width:200px; line-height:17px;">
		           	  <option value=""></option>
	              	  <option value="1">身份证</option>
		              <option value="2">护照</option>
		           </select>
		       </td>
		    </tr>
		    <tr>
		       <th class="text-right" >
		       	   <label>（公司）机构号：</label>
		       </th>
		       <td>
		           <input class="small-input" name="orgId"/>
		       </td>
		       <th class="text-right">
		       	   <label>（个人）证件号：</label>
		       </th>
		       <td>
		           <input class="small-input" name="idNbr"/>
		       </td>
		    </tr>
		    <tr>
		       <th class="text-right">
		       	   <label>是否形成独立路由域：</label>
		       </th>
		       <td>
	       			<input type="radio" name="isPrivateNet" value="1"> 是
		       		 &nbsp;&nbsp;&nbsp;
		       		<input type="radio" name="isPrivateNet" value="0" checked="checked"> 否
		       </td>
		    </tr>
	    </table>
    </form>
    <div class="formRow" style="text-align: center;">
       <div class="form_warn" style="display: none;">
         <span class="warn_text" style="color: red"></span>
       </div>
	</div>
    <div class="form-group" style="text-align: center;padding-top: 10px;">
      <div class="field rsInfo">
        <button id="back" class="button bg-main icon-check-square-o" type="button" onclick="openAccount()">确认开户</button>
      </div>	
    </div>      
  </div>
</div>
<script type="text/javascript">
	function openAccount(){
		var netArea = $('#netArea').val();
		var realname = $('#realname').val();
		var email = $('#email').val();
		var username = $('#username').val();
		var mobile = $('#mobile').val();
		
		if(empty(netArea)){
			 $(".warn_text").text("”路由域名称“不能为空！");
             $(".form_warn").css("display","block");
             return;
		}
		if(empty(realname)){
			$(".warn_text").text("”真实姓名“不能为空！");
            $(".form_warn").css("display","block");
            return;
		}
		if(!emailCheck(email)){
			$(".warn_text").text("请输入有效的邮箱地址！");
	        $(".form_warn").css("display","block");
	        return;
		}
		if(empty(username)){
			$(".warn_text").text("”用户姓名“不能为空！");
            $(".form_warn").css("display","block");
            return;
		}
		if(!empty(mobile) && !phoneCheck(mobile)){
			$(".warn_text").text("请输入有效的手机号码！");
	        $(".form_warn").css("display","block");
	        return;
		}
		$.ajax({
			url:"/account/open",
			data:$('#formInfo').serializeArray(),
			type:"post",
			success : function(data) { 
	        	var json = eval("("+data+")");
	        	if(json == 0){
	        		window.wxc.xcConfirm("开户失败，邮箱地址或者手机号已存在！", window.wxc.xcConfirm.typeEnum.info);
	        	}
	        	if(json == 1){
	        		window.wxc.xcConfirm("开户成功", window.wxc.xcConfirm.typeEnum.info,{
	        			onOk:function(v){
			        		window.location.href="/account/queryAccount";
	        			}
	        		});
	        	}
	        }
		})
	}
</script>
</body>
</html>