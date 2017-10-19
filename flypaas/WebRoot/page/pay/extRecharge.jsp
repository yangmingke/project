<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="u" uri="/flypaas-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta content="telephone=no" name="format-detection">
	<title>在线充值</title>
	<script type="text/javascript" src="<%=path %>/js/zepto.js"></script>
	<script type="text/javascript" src="<%=path %>/js/form.js"></script>
</head>
<body>
<style type="text/css">
* { margin:0px; padding:0px;}
a,abbr,acronym,address,applet,article,aside,audio,b,big,blockquote,body,canvas,caption,center,cite,code,dd,del,details,dfn,dialog,div,dl,dt,em,embed,fieldset,figcaption,figure,font,footer,form,h1,h2,h3,h4,h5,h6,header,hgroup,hr,html,i,iframe,img,ins,kbd,label,legend,li,main,mark,menu,meter,nav,object,ol,output,p,pre,progress,q,rp,rt,ruby,s,samp,section,small,span,strike,strong,sub,summary,sup,table,tbody,td,tfoot,th,thead,time,tr,tt,u,ul,var,video,xmp { border: 0; margin: 0; padding: 0; font-size: 100%;}
html,body { height: auto;}
article,aside,details,figcaption,figure,footer,header,hgroup,main,menu,nav,section {display: block;}
b,strong { font-weight: bold;}
img { color: transparent; font-size: 0; vertical-align: middle; -ms-interpolation-mode: bicubic;}
ol,ul { list-style: none;}
li {display: list-item;}
table { border-collapse: collapse; border-spacing: 0;}
th,td,caption {vertical-align: top;}
body { font-size:12px; line-height:1.5; font-family:微软雅黑; width:100%;background:#fff; color:#263849;}
h1,h2,h3,h4,h5,h6 { font-weight:normal; font-size:12px;}
ul,li,dl,dt,dd { list-style:none;}
img { border:0;}
a { text-decoration:none; color:#263849; outline:none; cursor:pointer;}
input { cursor:pointer; outline:none;}
.recharge-outer { width: 100%; max-width: 640px; margin: 0 auto;}
.recharge-outer .tab-tit { height: 35px; line-height: 35px;font-size: 14px;}
.recharge-outer .tab-tit ul li { float: left; display: inline; width: 50%; text-align: center; border-top:1px #0075EB solid; border-bottom:1px #0075EB solid; background: #fff; cursor: pointer;}
.recharge-outer .tab-tit ul li.current,.recharge-outer .tab-tit ul li:hover { color: #fff; background: #0075EB; }
.recharge-outer .tab-ctn { padding-top: 10%; color: #333; font-size: 14px; font-family: 微软雅黑; text-align: center;}
.edit-field { margin-bottom: 5%; position:relative;}
.edit-field label { display: inline-block; width: 15%;}
.edit-field input[type='text'] { height: 30px; line-height: 30px; border:1px #ccc solid; background: #fff; width: 50%; cursor: text; font-family:微软雅黑;}
.edit-btn input[type='button'] { color: #fff; background: #0075EB; height: 32px; line-height: 32px; text-align: center; border:none; width: 50%; margin-left: 15%; font-family: 微软雅黑;}
.float-bg { position:absolute; top:0; left:0px; width:100%; height:100%; opacity:0.4; background:#000; display:none;}
.float-box { border:1px #ccc solid; width:50%; margin:0 auto; position:relative; top:15%; background:#fff; z-index:1; max-width:640px; overflow:hidden; padding:40px 0px; display:none;}
.float-box p { text-align:center;}
.float-box p a { background:#0075EB; height:30px; line-height:30px; display:inline-block; padding:0px 10px; color:#fff;}
.float-box span { position:absolute; right:10px; top:3px; font-size:18px; color:#999; cursor:pointer;}
.error { color:#f00; display:none;}
.edit-field .error { position:absolute; top:-20px; left:31.5%;}
</style>

	<div class="recharge-outer">
		<div class="tab-tit">
			<ul>
				<li class="current">充值</li>
				<li onclick="location.href='<%=path %>/bill/chargeList'">记录</li>
			</ul>
		</div>
		<div class="tab-ctn">
			<div class="ctn">
				<form method="post" action="/pay/newOrderSmt" id="newOrderFrm" name="newOrderFrm" target="_blank">
					<div class="edit-field">
						<label for="money">金额：</label>
						<input type="hidden" id="vpayType" value="<u:des3 value='1' />"  name="chargeType"/>
						<input type="text"  placeholder="请输入金额" id="money" value="${chargeStr }" name="chargeStr"/>元
						<span id="money_error" class="error"></span>
					</div>
					<div class="edit-btn">
						<input type="button" id="butnsbt" value="提交" />
					</div> 
				</form>
			</div>
		</div>
	</div>
	<div class="float-bg">&nbsp;</div>
	<div class="float-box">
	<span class="close">X</span>
	<p><a href="<%=path %>/bill/chargeList">查看支付结果</a></p>
	</div>
</body>


<script type="text/javascript">
	var frm = {
		money:function(){
			var money = $("#money").val();
				if (!verfiyMoney(money)) {
					$("#money_error").text("请输入合法的金额");
					$("#money_error").show();
					return false;
				} else if (money > 1000000) {
					$("#money_error").text("充值金额大于零小于一百万");
					$("#money_error").show();
					return false;
				}
				$("#money_error").hide();
				return true;
			}
	};
	$("#butnsbt").click(function(){
		if(frm.money()){
			$(".float-bg").show();
			$(".float-box").show();
			$("#newOrderFrm").submit();
		}
	});
	$(".float-box .close").click(function(){
		$(".float-bg").hide();
		$(".float-box").hide();
	})
</script>
</html>