<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder" %>
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
    <script src="${ctx}/js/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<form method="post" action="">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 资源方列表</strong></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
          <a href="javascript:void(0)" class="button border-main icon-plus" onclick="showNewConfig()" title="添加" >添加</a>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th width="5%">序号</th>
        <th>属性</th>
        <th>属性值</th>  
        <th>注释</th>
        <th>操作</th>       
      </tr> 
  	  <tr id="newConfig" style="display: none;">
  	  	<td><font color="green">添加属性</font></td>
  	  	<td><input id="newKey"></td>
  	  	<td><input id="newValue"></td>
  	  	<td><input id="newNode"></td>
  	  	<td>
         	<a class="button border-blue" href="javascript:void(0)" onclick="addConfig()" title="确定"><span class="icon-check"></span></a>
         	<a class="button border-blue" href="javascript:void(0)" onclick="returnList()" title="取消"><span class="icon-undo"></span></a>
         </td>
  	  </tr>   
      <% int i = 1; %>
   	  <c:forEach var="config" items="${tbRsConfig}">
	   	  <tr>
	         <td><%= i++ %></td>
	         <td class="${config.tKey}_text">${config.tKey}</td>
	         <td class="${config.tKey}_text">${config.tValue}</td>
	         <td class="${config.tKey}_text">${config.tNote}</td>
	         <td class="${config.tKey}_input" style="display: none;"><input id='${config.tKey}_key' value='${config.tKey}'></td>
	         <td class="${config.tKey}_input" style="display: none;"><input id='${config.tKey}_value' value='${config.tValue}'></td>
	         <td class="${config.tKey}_input" style="display: none;"><input id='${config.tKey}_node' value='${config.tNote}'></td>
	         <td>
	         	<a class="button border-blue ${config.tKey}_text" href="javascript:void(0)" onclick="showInput('${config.tKey}')" title="修改"><span class="icon-edit"></span></a>
	         	<a class="button border-red ${config.tKey}_text" href="javascript:void(0)" onclick="delConfig('${config.tKey}')" title="删除"><span class="icon-trash-o"></span></a>
	         	<a style="display: none;" class="button border-blue ${config.tKey}_input" href="javascript:void(0)" onclick="updateConfig('${config.tKey}')" title="确定"><span class="icon-check"></span></a>
	         	<a style="display: none;" class="button border-blue ${config.tKey}_input" href="javascript:void(0)" onclick="configList('${config.tKey}')" title="取消"><span class="icon-undo"></span></a>
	         </td>
	      </tr>
      </c:forEach>
    </table>
  </div>
</form>
<script type="text/javascript">
	function showInput(key){
		$('#newConfig').hide();
		$("[class$='_input']").hide();
		$("[class$='_text']").show();
		$('.'+key+'_input').show();
		$('.'+key+'_text').hide();
	}

	function configList(key){
		$('.'+key+'_input').hide();
		$('.'+key+'_text').show();
	}
	
	function updateConfig(key){
		var newKey = $('#'+key+'_key').val();
		var value = $('#'+key+'_value').val();
		var node = $('#'+key+'_node').val();
		$.post("${ctx}/systemContrller/updateConfig",{tKey:newKey,tValue:value,tNote:node,oldKey:key},function(result){
			if(eval("("+result+")") == "success"){
				window.location.href="${ctx}/systemContrller/sysConfigList";
			}else if(eval("("+result+")") == "duplicateKey"){
				window.wxc.xcConfirm("属性名称重复,修改失败", window.wxc.xcConfirm.typeEnum.info);
			}else{
				window.wxc.xcConfirm("系统发生错误，修改失败，请联系管理员！", window.wxc.xcConfirm.typeEnum.info);
			}
		});
	}
	
	function showNewConfig(){
		$("[class$='_input']").hide();
		$("[class$='_text']").show();
		$('#newConfig').show();
	}

	function returnList(){
		$('#newConfig').hide();
	}
	
	function addConfig(){
		var newKey = $('#newKey').val();
		var newValue = $('#newValue').val();
		var newNode = $('#newNode').val();
		$.post("${ctx}/systemContrller/addConfig",{tKey:newKey,tValue:newValue,tNote:newNode},function(result){
			if(eval("("+result+")") == "success"){
				alert("添加成功");
				window.location.href="${ctx}/systemContrller/sysConfigList";
			}else if(eval("("+result+")") == "duplicateKey"){
				window.wxc.xcConfirm("属性名称重复,添加失败", window.wxc.xcConfirm.typeEnum.info);
			}else{
				window.wxc.xcConfirm("系统发生错误，修改失败，请联系管理员！", window.wxc.xcConfirm.typeEnum.info);
			}
		});
	}
	
	function delConfig(key){
		window.wxc.xcConfirm("您确定要删除“"+key+"”属性吗?", window.wxc.xcConfirm.typeEnum.confirm,{
			onOk:function(v){
				$.post("${ctx}/systemContrller/delConfig",{tKey:key},function(result){
					if(eval("("+result+")") == "success"){
						window.location.href="${ctx}/systemContrller/sysConfigList";
					}else{
						window.wxc.xcConfirm("系统发生错误，修改失败，请联系管理员！", window.wxc.xcConfirm.typeEnum.info);
					}
				});
			}
		});
	}
</script>
</body></html>