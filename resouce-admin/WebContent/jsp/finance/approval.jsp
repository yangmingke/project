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
<div class="panel admin-panel margin-top" id="add">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 提现审核</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" id="dataForm" action="">    
      <div class="form-group">
        <div class="label">
          <label>资源方ID：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${main_sid}" name="mainSid" readonly="readonly" />
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>资源方名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${username}" name="username"  readonly="readonly"/>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>收款账号（支付宝）：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${alipay_account }" name="alipayAccount"  readonly="readonly"/>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>收款人（支付宝）：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${alipay_name }" name="alipayName"  readonly="readonly"/>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>提款后余额：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" id="balance" name="balance" value="${balance }"  readonly="readonly"/>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>提款金额：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" id="actualFee" name="actualFee" value="${actual_fee }"  readonly="readonly"/>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>备注：</label>
        </div>
        <div class="field">
          <textarea class="input" name="demo" id="demo" style="height:120px;width: 50%;" ><c:out value="${demo }"/></textarea>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field" style="padding-left: 13%;">
        	<c:if test="${status == '0'}">
	          <button class="button bg-main" type="button" onclick="approval('${main_sid}','${id}',1,'${actual_fee }')"> 审核通过</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	</c:if>
        	<c:if test="${status == '0'}">
	          <button class="button bg-dot" type="button" onclick="approval('${main_sid}','${id}',2,'${actual_fee }')"> 审核不通过</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	</c:if>
        	<c:if test="${status == '1'}">
	          <button class="button bg-main" type="button" onclick="approval('${main_sid}','${id}',4,'${actual_fee }')"> 已转账</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	</c:if>
        	<c:if test="${status == '1'}">
	          <button class="button bg-dot" type="button" onclick="approval('${main_sid}','${id}',0,'${actual_fee }')"> 重新审核</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	</c:if>
        	<c:if test="${status == '2'}">
	          <button class="button bg-dot" type="button" onclick="approval('${main_sid}','${id}',5,'${actual_fee }')"> 重新审核</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	</c:if>
        	<c:if test="${status == '0'}">
	          <button class="button bg-dot" type="button" onclick="approval('${main_sid}','${id}',3,'${actual_fee }')"> 作废</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	</c:if>
        	<c:if test="${status == '2'}">
	          <button class="button bg-dot" type="button" onclick="approval('${main_sid}','${id}',6,'${actual_fee }')"> 作废</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	</c:if>
        	
	        <button class="button bg-main" type="button" onclick="window.history.go(-1);"> 返回</button>
        </div>
      </div>
    </form>
  </div>
</div>
<script type="text/javascript">
	function approval(netSid,id,state,actualFee){
		var demo = $('#demo').val();
		var msg="";
		if(state == 0 || state == 5){
			msg = "确定需要重新审核吗？";
		}else if(state == 1){
			msg = "确定审核通过吗？";
		}else if(state == 2){
			msg = "确定审核不通过吗？";
		}else if(state == 3 || state == 6){
			msg = "确定需要作废该申请吗？";
		}else if(state == 4){
			msg = "确定已转账成功吗？";
		}
		window.wxc.xcConfirm(msg, window.wxc.xcConfirm.typeEnum.confirm,{
			onOk:function(v){
				window.location.href=encodeURI("/accountManagement/approval?id=" + id + "&state=" + state + "&netSid=" + netSid + "&actualFee=" + actualFee + "&demo=" + demo);
			}
		});
	}
</script>
</body>
</html>