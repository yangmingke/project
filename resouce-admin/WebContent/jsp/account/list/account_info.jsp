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
<script src="${ctx}/js/editPwd.js"></script>
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
  <div class="panel-head"><strong><span class="icon-key"></span> 资源方信息</strong></div>
  <div class="body-content">
    <form id="formInfo" method="post" class="form-x" action="">
	    <table width="95%">
	    	<tr>
	    		<th>基本信息 > ></th>
	    	<tr>
		    <tr>
		       <th class="text-right" >
		       	   <label>专网ID：</label>
		       </th>
		       <td>
		           <input class="small-input" name="netSid" value="${account.netSid}" readonly="readonly"/>
		       </td>
		        <th class="text-right">
		       	   <label>资源方公司全称：</label>
		       </th>
		       <td>
		           <input class="small-input" name="realname" value="${account.realname}"/>
		       </td>
		    </tr>   
		    <tr>
		       <th class="text-right">
		       	   <label>资源方公司简称：</label>
		       </th>
		       <td>
		           <input class="small-input" name="username" value="${account.username}"/>
		       </td>
		       <th class="text-right">
		       	   <label>手机号：</label>
		       </th>
		       <td>
		           <input class="small-input" name="mobile" value="${account.mobile}" id="mobile"/>
		       </td>
		    </tr>  
		    <tr>
		       <th class="text-right" >
		       	   <label>联系地址：</label>
		       </th>
		       <td>
		           <input class="small-input" name="address" value="${account.address}"/>
		       </td>
		       <th class="text-right">
		       	   <label>邮箱：</label>
		       </th>
		       <td>
		           <input class="small-input" name="email" value="${account.email}" readonly="readonly"/>
		       </td>
		    </tr> 
		    <tr>
		       <th class="text-right">
		       	   <label>注册时间：</label>
		       </th>
		       <td>
		       	   <input class="small-input" value="<fmt:formatDate value="${account.createDate}" pattern="yyyy-MM-dd" />" readonly="readonly"/>
		       </td>
		    </tr> 
		    <tr>
	    		<th>资源方信息 > ></th>
	    	<tr>
	    	<tr>
		       <th class="text-right" >
		       	   <label>收款账号（支付宝）：</label>
		       </th>
		       <td>
		           <input class="small-input" name="alipayAccount" value="${account.alipayAccount}"/>
		       </td>
		       <th class="text-right">
		       	   <label>收款人（支付宝）：</label>
		       </th>
		       <td>
		           <input class="small-input" name="alipayName" value="${account.alipayName}"/>
		       </td>
		    </tr>
	    	<tr>
		       <th class="text-right" >
		       	   <label>路由域名称：</label>
		       </th>
		       <td>
		           <input class="small-input" name="netArea" value="${account.netArea}"/>
		       </td>
		       <th class="text-right">
		       	   <label>是否形成独立路由域：</label>
		       </th>
		       <td>
		       		<c:choose>
			       		<c:when test="${account.isPrivateNet == '1'}">
				       		<input type="radio" name="isPrivateNet" value="1" checked="checked"> 是
				       		 &nbsp;&nbsp;&nbsp;
				       		<input type="radio" name="isPrivateNet" value="0"> 否
			       		</c:when>
			       		<c:otherwise>
			       			<input type="radio" name="isPrivateNet" value="1"> 是
				       		 &nbsp;&nbsp;&nbsp;
				       		<input type="radio" name="isPrivateNet" value="0" checked="checked"> 否
			       		</c:otherwise>
		       		</c:choose>
		       </td>
		    </tr>
		    <tr>
		       <th class="text-right" >
		       	   <label>积分：</label>
		       </th>
		       <td>
		           <input class="small-input" name="point" value="${account.point}" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"/>
		       </td>
		       <th class="text-right">
		       	   <label>等级：</label>
		       </th>
		       <td>
		           <input class="small-input" name="rank" value="${account.rank}" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"/>
		       </td>
		    </tr>
		    <tr>
		       <th class="text-right" >
		       	   <label>公司电话：</label>
		       </th>
		       <td>
		           <input class="small-input" name="companyNbr" value="${account.companyNbr}" id="companyNbr"/>
		       </td>
		       <th class="text-right">
		       	   <label>公司网址：</label>
		       </th>
		       <td>
		           <input class="small-input" name="webSite" value="${account.webSite}"/>
		       </td>
		    </tr>
		    <tr>
		       <th class="text-right" >
		       	   <label>企业法人：</label>
		       </th>
		       <td>
		           <input class="small-input" name="legalPerson" value="${account.legalPerson}"/>
		       </td>
		       <th class="text-right">
		       	   <label>（个人）证件类型：</label>
		       </th>
		       <td>
		           <select id="idType" class="small-input" name="idType" style="width:200px; line-height:17px;">
		           	  <option value=""></option>
		           	  <c:choose>	
			           	  <c:when test="${account.idType == '1'}">
				              <option value="1" selected="selected">身份证</option>
				              <option value="2">护照</option>
			              </c:when>
			              <c:when test="${account.idType == '2'}">	
				              <option value="1">身份证</option>
				              <option value="2" selected="selected">护照</option>
			              </c:when>
			              <c:otherwise>
			              	  <option value="1">身份证</option>
				              <option value="2">护照</option>
			              </c:otherwise>
		              </c:choose>
		           </select>
		       </td>
		    </tr>
		    <tr>
		       <th class="text-right" >
		       	   <label>（公司）机构号：</label>
		       </th>
		       <td>
		           <input class="small-input" name="orgId" value="${account.orgId}"/>
		       </td>
		       <th class="text-right">
		       	   <label>（个人）证件号：</label>
		       </th>
		       <td>
		           <input class="small-input" name="idNbr" value="${account.idNbr}"/>
		       </td>
		    </tr>
	    </table>
    </form>
    <div class="formRow" style="text-align: center;">
       <div class="form_warn" style="display: none;">
         <span class="warn_text" style="color: red"></span>
       </div>
	</div>
    <div class="form-group" style="text-align: center;padding-top: 40px;">
      <div class="field">
        <button id="modify" class="button bg-main icon-edit" type="button" onclick="modify()"> 修改</button>   
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <button id="back" class="button bg-main icon-undo" type="button" onclick="back()"> 返回</button>
      </div>
    </div>      
  </div>
</div>
<script type="text/javascript">
	function back(){
		$('body').load('/account/queryAccount');
	}
	
	function modify(){
		var mobile = $('#mobile').val();
		if(!empty(mobile) && !phoneCheck(mobile)){
			$(".warn_text").text("请输入有效的手机号码！");
	        $(".form_warn").css("display","block");
	        return;
		}
		var companyNbr = $('#companyNbr').val();
		if(!empty(companyNbr) && !phoneNumCheck(companyNbr)){
			$(".warn_text").text("请输入有效的公司电话！");
	        $(".form_warn").css("display","block");
	        return;
		}
		$.ajax({
			url:"/account/modify",
			data:$('#formInfo').serializeArray(),
			type:"post",
			success : function(data) { 
	        	var json = eval("("+data+")");
	        	if(json == 0){
	        		window.wxc.xcConfirm("系统发生错误，修改失败，请联系管理员！", window.wxc.xcConfirm.typeEnum.info);
	        	}
	        	if(json == 1){
	        		window.wxc.xcConfirm("修改成功！", window.wxc.xcConfirm.typeEnum.info,{
	        			onOk:function(v){
			        		back();
	        			}
	        		});
	        	}
	        }
		})
	}
</script>
</body>
</html>