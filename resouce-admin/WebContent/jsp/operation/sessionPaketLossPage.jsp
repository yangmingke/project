<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
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
    <script src="${ctx}/plugin/My97DatePicker/WdatePicker.js"></script>  
</head>
<body>
<form method="post" action="">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 会话丢包查询</strong></div>
    <div class="padding border-bottom">
	    <ul class="search">
	   		<li>
        		日期筛选：
        	</li>
        	<li>
	     	 	<input type="text"  placeholder="请选择日期" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" id="dateTime" class="input-text Wdate" value="${dateTime}" style="width:150px; height:38px; display:inline-block"  name="dateTime">
		  		&nbsp;&nbsp;&nbsp;&nbsp;
        	</li>
	      	<li>
		       <input type="text" placeholder="用户id" id="userSid" class="input" style="width:250px; line-height:16px;display:inline-block" value="${userSid}"/>&nbsp;&nbsp;&nbsp;
		       <input type="text" placeholder="应用id" id="appSid" class="input" style="width:250px; line-height:16px;display:inline-block" value="${appSid}"/>&nbsp;&nbsp;&nbsp;
		       <input type="text" placeholder="会话id" id="sessionID" class="input" style="width:250px; line-height:16px;display:inline-block" value="${sessionID}"/>&nbsp;&nbsp;&nbsp;
		       <a href="javascript:void(0)" class="button border-main icon-search" onclick="query()" title="查询"></a>
		       <span ><font color="red" id="error" style="display: none;"></font></span>
	      	</li>
	    </ul>
    </div>
    <div id="data">
	    <table class="table table-hover text-center" id="list">
	    </table>
	    <div id="picture" style="height:480px;" ></div>
    </div>
    <div class="form-group" style="text-align: center;padding-top: 40px;">
      <div class="field">
        <button id="showPicture" class="button bg-main icon-edit" type="button" onclick="show('showPicture')" style="display: none;">查看统计图</button>  
        <button id="showList" class="button bg-main icon-edit" type="button" onclick="show('showList')" style="display: none;">查看列表</button> 
      </div>
    </div> 
  </div>
</form>
<script type="text/javascript">
	function show(order){
		if(order == 'showPicture'){
			$('#showList').show();
			$('#picture').show();
			$('#showPicture').hide();
			$('#list').hide();
		}else if(order == 'showList'){
			$('#showPicture').show();
			$('#list').show();
			$('#picture').hide();
			$('#showList').hide();
		}
	}

	function query(){
		var dateTime= $('#dateTime').val();
		var userSid= $('#userSid').val();
		var appSid= $('#appSid').val();
		var sessionID= $('#sessionID').val();
		if(dateTime == ""){
			$('#error').text('请输入查询日期');
			$('#error').show();
			return false;
		}
		if(sessionID == ""){
			$('#error').text('请至输入会话ID');
			$('#error').show();
			return false;
		}
		/* if(!(userSid != "" || appSid != "" || sessionID != "")){
			$('#error').text('请至少输入一个查询id');
			$('#error').show();
			return false;
		} */
		$('#error').hide();
		$('#data').load("/operation/querySessionPaketLoss", {
			"dateTime" : dateTime,
			"userSid" : userSid,
			"appSid" : appSid,
			"sessionID" : sessionID
		},function(response,status,xhr){
			if(status != "success"){
				$('#error').text('系统发生错误，请联系管理员');
				$('#error').show();
			}
		});
	}
	function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = year + seperator1 + month + seperator1 + strDate;
        return currentdate;
    }
	$('#dateTime').val(getNowFormatDate());
</script>
</body></html>