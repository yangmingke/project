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
</head>
<body>
<div class="panel admin-panel margin-top" id="add">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 增加内容</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" id="dataForm" action="/accountManagement/apply">    
      <div class="form-group">
        <div class="label">
          <label>资源方ID：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${net_sid}" name="mainSid" readonly="readonly" />
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
          <label>账户余额：</label>
        </div>
        <div class="field">
          <input id="balance" name="balance" value="${balance }"  hidden="hidden"/>
          <input type="text" class="input w50"  value="${balanceShow }"  readonly="readonly"/>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>提款金额：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" id="actualFee" name="actualFee" value=""  onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"/>
          <span id="actualFeeTips" style="color: red"></span>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>备注：</label>
        </div>
        <div class="field">
          <textarea class="input" name="demo" style="height:120px;width: 50%;" ></textarea>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field" style="padding-left: 13%;">
          <button class="button bg-main icon-check-square-o" type="button" onclick="apply_ench()"> 提交</button>&nbsp;&nbsp;&nbsp;&nbsp;
          <button class="button bg-main icon-check-square-o" type="button" onclick="backToAccount()"> 返回</button>
        </div>
      </div>
    </form>
  </div>
</div>
<script type="text/javascript">
	function backToAccount() {
		window.history.go(-1);
	}
	function apply_ench(){
		var balance = Number($('#balance').val());
		var actualFee = Number($('#actualFee').val());
		if(balance < actualFee){
			 $("#actualFeeTips").text("“提款金额”不能大于“账户余额”");
			return false;
		}
		if(actualFee == 0){
			 $("#actualFeeTips").text("“提款金额”不能为0");
			return false;
		}
		$('#dataForm').submit();
	}
</script>
</body>
</html>