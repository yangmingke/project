<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
<script type="text/javascript" src="http://libs.useso.com/js/respond.js/1.4.2/respond.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/css3pie/2.0beta1/PIE_IE678.js"></script>
<![endif]-->
<link type="text/css" rel="stylesheet" href="${ctx}/css/H-ui.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/css/H-ui.admin.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/font/font-awesome.min.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/css/form/form.css">
<!--[if IE 7]>
<link href="http://www.bootcss.com/p/font-awesome/assets/css/font-awesome-ie7.min.css" rel="stylesheet" type="text/css" />
<![endif]-->
</head>
<body>
	<div class="codeView docs-example">
				<table class="table table-border table-bordered table-striped" style="font-size: 12px;" id="table1">
					<tbody>
						<tr>
							<td><label>用户名称:</label></td>
							<td width="70%"><input type="text" class="input-text" value="${account.username}" id="uId" readonly="readonly"></td>
						</tr>
						<tr>
							<td><label>申 &nbsp;请&nbsp;人:</label></td>
							<td width="70%"><input type="text" class="input-text" value="${account.username}" id="uId" readonly="readonly"></td>
						</tr>
						<tr>
							<td><label>收款账号（支付宝）:</label></td>
							<td width="70%"><input type="text" class="input-text" value="${account.alipayAccount}" id="alipayAccount" readonly="readonly"></td>
						</tr>
						<tr>
							<td><label>收款人（支付宝）:</label></td>
							<td width="70%"><input type="text" class="input-text" value="${account.alipayName}" id="alipayName" readonly="readonly"></td>
						</tr>
						<tr>
							<td><label>账户余额:</label></td>
							<td width="70%">
								<input type="text" class="input-text" value="<%=request.getParameter("balance") %>" id="balanceId" hidden="hidden">
								<input type="text" class="input-text" value="${balanceShow}" readonly="readonly">
							</td>
						</tr>
						<tr>
							<td><label>提款金额:</label></td>
							<td width="70%"><input type="text" class="input-text" value="" id="moneyId" onkeyup='this.value=this.value.replace(/\D/gi,"")' ></td>
						</tr>
						<tr>
							<td valign="top"><label>备&nbsp;&nbsp;注:</label></td>
							<td >
								<textarea rows="5" cols="47" style="text-align: left; line-height: 25px;" id="demoId"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="2" style="text-align: center"> 
								<button class="btn btn-success radius" id="cancel_btn" onclick="javascript:window.close();window.parent.location.reload(true);">取消</button>&nbsp;&nbsp;&nbsp;&nbsp;
								<button class="btn btn-success radius" id="submit_btn" >提交</button>
							</td>
						</tr>
					</tbody>
				</table>
		</div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${ctx}/layer/layer.min.js"></script>
<script type="text/javascript">
	$('#submit_btn').click(function(){
		var money = Number($('#moneyId').val());
		var balance = Number($('#balanceId').val());
		var alipayAccount = $('#alipayAccount').val();
		var alipayName = $('#alipayName').val();
		var demo = $('#demoId').val();
		if(alipayAccount =="" || alipayName == ""){
			alert("提款失败！支付宝账户或支付宝姓名为空，请在“用户资料”中完善资料");
			return false;
		}
		if(money == 0){
			alert("提款金额必须大于0元");
			return false;
		}
		if(money > balance){
			alert("余额不足,请重新输入");
			return false;
		}else{
			$.post("/billFlowController/addBillFlow.action",{"money":money,"balance":balance,"demo":demo,"alipayAccount":alipayAccount,"alipayName":alipayName},function(data){
				var json = eval("("+ data +")");
				if(json == 1){
					layer.msg('申请成功!',1,1,function(){
	        			window.close();
		        		window.parent.location.reload(true);
	        		});
				}
				if(json == 0){
					layer.msg('申请失败!',1,2,function(){
	        			window.close();
		        		window.parent.location.reload(true);
	        		});
				}
				if(json == -1){
					/* layer.msg('钱包信息改变，申请失败，请重新申请!',3,3,function(){
	        			window.close();
		        		window.parent.location.reload(true);
	        		}); */
					alert('钱包信息改变，申请失败，请重新申请!');
					window.close();
	        		window.parent.location.reload(true);
				}
			})
		}
	})
</script>
</html>