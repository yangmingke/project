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
	<link rel="stylesheet" type="text/css" href="<%=path %>/page/css/style.css">
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
.recharge-outer .tab-ctn table { width:90%; margin:0 auto;}
.recharge-outer .tab-ctn table th,.recharge-outer .tab-ctn table td { text-align: center; height: 32px; line-height: 32px; font-size: 12px; font-family: 微软雅黑;}
.recharge-outer .tab-ctn table th { font-weight: bold; background: #efefef;}
.recharge-outer .tab-ctn .pagenum { margin-top:20px; float:right; display:inline; font-size:12px; margin-right:5%;}
</style>

	<div class="recharge-outer">
		<div class="tab-tit">
			<ul>
				<li id="recharege">充值</li>
				<li class="current">记录</li>
			</ul>
		</div>
		<s:form theme="simple" namespace="/bill" action="chargeList" method="post" name="form" id="form">
	            <input type="hidden" id="currentPage" name="page.currentPage"/>
        </s:form>
		<div class="tab-ctn">
			<div class="ctn">
				<table cellpadding="0" cellspacing="0">
					<tbody>
						<tr><th>订单号</th><th>金额</th><th>状态</th><th>订单时间</th></tr>
						 <s:if test="page.list.size()>0">
      						<s:iterator value="page.list" var="pay">
								<tr>
								<td>${pay.order_id }</td>
								<td>¥${pay.charge }</td>
								<s:if test="#pay.status==1">
						      		<td class="red">
						      		未支付
						      		</td>
						      	</s:if>
						      	<s:if test="#pay.status==2">
						      		<td class="orange">
						      		订单已提交
						      		</td>
						      	</s:if>
						      	<s:if test="#pay.status==3">
						      		<td>
						      		支付成功
						      		</td>
						      	</s:if>
						      	<s:if test="#pay.status==4">
						      		<td class="red">
						      		支付失败
						      		</td>
						      	</s:if>
						      	<s:if test="#pay.status==5">
						      		<td class="red">
						      		已关闭
						      		</td>
						      	</s:if>
						      	<td><s:date name="#pay.create_date" format="yyyy/MM/dd HH:mm:ss"/></td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
								<tr><th>暂无数据!</th><th>&nbsp;</th><th>&nbsp;</th><th>&nbsp;</th></tr>
						</s:else>
					</tbody>
				</table>
				<u:page page="${page}" formId="form" ></u:page>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$("#recharege").click(function(){
		var sid="${sessionScope.user.sid}";
		var appSid="${sessionScope.extAppSid}";
		var token="${sessionScope.extToken}";
		location.href="<%=path%>/pay/extrecharge?sid="+sid+"&appSid="+appSid+"&token="+token;
	});

</script>

</html>