<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="u"  uri="/flypaas-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8"><meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>flypaas开放平台管理中心——开具发票</title>
	<%@include file="/page/resource.jsp"%>
	<script type="text/javascript" src="<%=path%>/js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="<%=path%>/js/Highcharts-4.0.1/highcharts.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/Highcharts-4.0.1/modules/exporting.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/form.js"></script>
	<script type="text/javascript" src="<%=path%>/js/md5.js"></script>
</head>
<body id="03-4">
	<!--头部header bof-->
	<%@include file="/page/head.jsp" %>
	<!--头部header eof-->

	<!--主体content bof-->
	<div class="content">

		<!--侧边side bof-->
		<%@include file="/page/left.jsp" %>
		<!--侧边side bof-->
		<input type="hidden" value="<%=path%>" id="path_fo_js" />
		<!--侧边side bof-->

		<!--右侧main bof-->
		<div class="main">
		    <div class="breadcrumbs">
				<ul>
					<li><a href="javascript:;">财务管理</a></li>
					<li class="active"><a href="javascript:;">发票管理</a></li>
				</ul>
			</div>
		    <div class="main_tab_tit">
		      <ul>
		        <li class="active">发票管理</li>
		      </ul>
		    </div>
		    
		    <!--说明state_box bof-->
		    <div class="state_box">
		      <p>* 申请开票金额1000元以上可开具增值税专票，开票金额100元以上可开具增值税普票（国税发票）</p>
		      <p>* 官方活动赠送金额不计算在开票金额内</p>
		    </div>
		    <!--说明state_box eof-->
		    
		        <div class="table_relate relate_invoice" style="overflow:hidden;">
		          <ul>
		            <li class="li2">
		              <dl>
		                <dt>可开票金额：</dt>
		                <dd><span class="green1">￥${invoiceMoney}</span>元<span class="tips">* 通过活动兑换码充值进账户及已充值的保证金无法开具发票</span></dd>
		              </dl>
		              <dl class="relate_link">
			              <a href="javascript:;" class="save" id="sbmt">提交</a>
			              <a href="javascript:;" class="cancel" onclick="history.go(-1)">取消</a>
		              </dl>
		            </li>
		          </ul>
		        </div>
		
		        <!--开具发票表单 bof-->
		        <div class="invoice_edit">
		          <em class="arrow_up">&nbsp;</em>
		          <form method="post" id="invoiceForm" action="<%=path%>/bill/addInvoice">
		          	
		          	<input type="hidden" name="invoiceMoney" value="${invoiceMoney}" />
		          	<input type="hidden" name="invoice.opentype" value="${user.userType}" />
		          	<input type="hidden" name="invoice.title" value="${user.realname}" />
		          	
		            <div class="edit_form">
		              <dl>
		                <dt>开票金额：</dt>
		                <dd>
		                	<input type="text" id="money" name="invoice.money" placeholder="输入金额" onfocus="inputControl.setNumber(this, 10, 0, false)" />
		                	<span class="info">输入金额需要 &gt; 100元</span>
		                	<span class="error" id="moneyError" style="display:none"></span>
		                </dd>
		              </dl>
		              <dl>
		                <dt>开票类型：</dt>
		                <dd class="type_radio">
		                  <label class="radio"><input type="radio" id="type_2" name="invoice.type" value="2" checked="checked" />增值税普票（国税发票）</label>
		                  <s:if test="user.userType==2">
		                  	<label class="radio spe_radio"><input type="radio" id="type_1" name="invoice.type" value="1" disabled="disabled" />增值税专票（金额&gt;1000可选择）</label>
		                  </s:if>
		                </dd>
		              </dl>
		              <dl>
		                <dt>开票抬头：</dt>
		                <dd><span class="txt">${user.realname}</span></dd>
		              </dl>
		              <s:if test="user.userType==2">
			              <div class="special_list" style="display:none;">
			                <dl>
			                  <dt>纳税人识别号：</dt>
			                  <dd>
				                  <input type="text" id="identificationnum" name="invoice.identificationnum" placeholder="纳税人识别号" onfocus="inputControl.setNumber(this, 20, 0, false)" />
				                  <span class="info">不超过20位数字号码</span>
				                  <span class="error" id="idenError" style="display:none"></span>
			                  </dd>
			                </dl>
			                <dl>
			                  <dt>银行账户：</dt>
			                  <dd>
				                  <input type="text" id="bankaccount" name="invoice.bankaccount" placeholder="银行账户" onfocus="inputControl.setNumber(this, 20, 0, false)" />
				                  <span class="info">请输入银行账户</span>
				                  <span class="error" id="bankacctError" style="display:none"></span>
			                  </dd>
			                </dl>
			                <dl>
			                  <dt>开户行：</dt>
			                  <dd>
					              <textarea id="bankaddr" name="invoice.bankaddr" placeholder="开户行" maxlength="200" style="width:460px;"></textarea>
				                  <span class="info">需要具体到支行</span>
				                  <span class="error" id="bankaddrError" style="display:none"></span>
			                  </dd>
			                </dl>
			              </div>
		              </s:if>
		            </div>
		
		            <div class="edit_form edit_form1">
		              <dl>
		                <dt>邮寄地址：</dt>
		                <dd class="textarea">
			                <textarea id="street" name="addr.street" placeholder="邮寄地址" maxlength="200" style="width:460px;"></textarea>
			                <span class="info">请不要超过200个字符</span>
			                <span class="error" id="streetError" style="display:none"></span>
		                </dd>
		              </dl>
		              <dl>
		                <dt>邮编号码：</dt>
		                <dd>
			                <input type="text" id="postnum" name="addr.postnum" placeholder="邮编号码" onfocus="inputControl.setNumber(this, 6, 0, false)" />
			                <span class="info">输入6位数字邮编号码</span>
			                <span class="error" id="postnumError" style="display:none"></span>
		                </dd>
		              </dl>
		              <dl>
		                <dt>收件人：</dt>
		                <dd>
			                <input type="text" id="contacts" name="addr.contacts" placeholder="收件人姓名" maxlength="20" />
			                <span class="info">请不要超过20个字符</span>
			                <span class="error" id="contactsError" style="display:none"></span>
		                </dd>
		              </dl>
		              <dl>
		                <dt>电话号码：</dt>
		                <dd>
			                <input type="text" id="contactmobile" name="addr.contactmobile" placeholder="手机号码或座机号码" onfocus="inputControl.setNumber(this, 20, 0, false)" />
			                <span class="info">不超过20位数字号码</span>
			                <span class="error" id="mobileError" style="display:none"></span>
		                </dd>
		              </dl>
		            </div>
		          </form>
		
		
		        </div>
		        <!--开具发票表单 eof-->
		
		  </div>  
		  <!--右侧main bof-->

	</div>
	<!--主体content eof-->
	
	<!--底部footer bof-->
	<%@include file="/page/foot.jsp" %>
	<!--底部footer eof-->

<script type="text/javascript">
$(function(){
	//属于专票的填写项显示隐藏
	if($(".spe_radio input[type='radio']").attr("checked")){
		$(".special_list").slideDown();
	}
	
	$(".type_radio input[type='radio']").click(function(){
		if($(this).parent("label").hasClass("spe_radio")){
			$(".special_list").slideDown();
		}else{
			$(".special_list").slideUp();
			$("#identificationnum, #bankaccount, #bankaddr").val("");
		}
	});
	
	$("#money").blur(function(){
		changeMoney();
	});
	
	$("#sbmt").click(function(){
		var invoiceMoney = ${invoiceMoney};
		var money = $("#money").val();
		var type = $("[name='invoice.type']:checked").val();
		var identificationnum = $("#identificationnum").val();
		var bankaccount = $("#bankaccount").val();
		var bankaddr = $("#bankaddr").val();
		var street = $("#street").val();
		var postnum = $("#postnum").val();
		var contacts = $("#contacts").val();
		var contactmobile = $("#contactmobile").val();
		
		if(!verfiyMoney(money)){
			$("#moneyError").text("金额输入错误，请检查后重试.").show();
			return;
		}else if(money>invoiceMoney){
			$("#moneyError").text("开票金额超过最大可开金额，请检查后重试.").show();
			return;
		}else if(money<=100){
			$("#moneyError").text("金额需要 > 100元，请检查后重试.").show();
			return;
		}else{
			$("#moneyError").hide();
		}
		changeMoney();
		if(type=="1"){
			if(!verifyIdentificationnum(identificationnum)){
				$("#idenError").text("纳税人识别号输入错误，请检查后重试.");
				$("#idenError").show();
				return;
			}else{
				$("#idenError").hide();
			}
			if(!verifyBankNum(bankaccount)){
				$("#bankacctError").text("银行卡号输入错误，请检查后重试.");
				$("#bankacctError").show();
				return;
			}else{
				$("#bankacctError").hide();
			}
			if(bankaddr==""){
				$("#bankaddrError").text("开户地址不能为空");
				$("#bankaddrError").show();
				return;
			}else{
				$("#bankaddrError").hide();
			}
		}
		if(street==""){
			$("#streetError").text("邮寄地址不能为空");
			$("#streetError").show();
			return;
		}else{
			$("#streetError").hide();
		}
		if(!verifyPostNum(postnum)){
			$("#postnumError").text("邮编输入错误，请检查后重试.");
			$("#postnumError").show();
			return  ;
		}else{
			$("#postnumError").hide();
		}
		if(contacts==""){
			$("#contactsError").text("收件人不能为空");
			$("#contactsError").show();
			return  ;
		}else{
			$("#contactsError").hide();
		}
		if(!verifyMobileAndFixPhone(contactmobile)){
			$("#mobileError").text("电话号码输入错误，请检查后重试.");
			$("#mobileError").show();
			return  ;
		}else{
			$("#mobileError").hide();
		}
		
		$("#sbmt").unbind("click");
		$("#sbmt").text("保存中...");
		$("#invoiceForm").submit();
	});
});

function changeMoney(){
	var money = parseInt($("#money").val());
	if(money>1000){
		$("#type_1").attr("disabled", false);
	}else{
		$("#type_1").attr("disabled", true);
		$("#type_2").click();
	}
}
</script>
</body>
</html>